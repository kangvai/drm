package com.kangvai.demo.controller;

import com.kangvai.demo.generated.RecordContract;
import com.kangvai.demo.model.RecordDto;
import com.kangvai.demo.service.BlockService;
import com.kangvai.demo.service.RecordService;
import com.kangvai.demo.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author kangvai
 * @date 2020/5/3 9:59
 */
@Controller
@Slf4j
public class RecordController {
    //阈值：定义文本的相似度阈值，若大于该阈值，则不可上链，否则可以。
    private static final double THRESHOLD = 0.8;

    //文件上传路径
    private static final String UPLOAD_FILE_PATH = "src/main/resources/static/user/file/";

    @Resource
    private RecordService recordService;

    @Resource
    private BlockService blockService;

    /**
     * 检测记录
     */
    @GetMapping("/user/record/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Integer authorId = (Integer) request.getSession().getAttribute("uid");
        RecordDto recordDto = new RecordDto();
        recordDto.setAuthorId(authorId);
        if(recordService.selectCount(recordDto) > 0) {
            PageQueryUtil pageUtil = new PageQueryUtil(params);
            return ResultGenerator.genSuccessResult(recordService.getRecordsPageByAuthorID(pageUtil, authorId));
        }
        return ResultGenerator.genFailResult("检测记录不存在");
    }

    /**
     * 文件上传检测
     * @params [multipartFile, request]
     * @return com.kangvai.demo.util.Result
     */
    @PostMapping(value = "/user/upload")
    @ResponseBody
    public Result upload(@RequestParam(required = false, value = "file") MultipartFile multipartFile,
                         HttpServletRequest request) throws Exception {
        //获取基本信息
        Byte resultStatus = 0;      //检测状态
        String feedback = " ";      //反馈内容
        Integer authorId = (Integer) request.getSession().getAttribute("uid");       //作者id
        String userName = (String) request.getSession().getAttribute("username");      //作者姓名
        String timeStamp = VeDate.getStringDate();
        Date currentDate = VeDate.strToDateLong(timeStamp);         //当前时间

        File directory = new File(UPLOAD_FILE_PATH);
        String localFilePath = directory.getCanonicalPath() + "\\upload";
        //前端页面上传文件到服务器
        String originalFileName = multipartFile.getOriginalFilename();
        String newFileName = UUID.randomUUID() + originalFileName;
        File localFile = new File(localFilePath, newFileName);
        multipartFile.transferTo(localFile);

        //从服务器获取上传文件的String类型
        String sourceFilePath = localFilePath + "/" + newFileName;
        String sourceFile = FileUtil.readFile(sourceFilePath);

        //从IPFS上获取存储的文件
        //1.从IPFS中读取已经确权的数字作品Hash值列表的文件
        ArrayList<String> hashList = new ArrayList<String>();
        String fileHashPath = directory.getCanonicalPath() + "\\hash.txt";
        FileUtil.readStringToArray(hashList, fileHashPath);
        //2.利用读取的hash值获取文件到String
        Boolean moreThanThreshold = false;
        if (!hashList.isEmpty()) {
            for (String str : hashList) {
                String targetFile = IPFSUtil.getFileStringByHash(str);
                double similarity = SimHash.calculateSimilariry(sourceFile, targetFile);
                BigDecimal similarityBigDecimal = new BigDecimal(similarity);
                BigDecimal thresholdBigDecimal = new BigDecimal(THRESHOLD);
                //true:相似度大于阈值 false:相似度小于阈值
                moreThanThreshold = (similarityBigDecimal.compareTo(thresholdBigDecimal) == 1);
                if (moreThanThreshold) break;
            }
        } else {
            moreThanThreshold = false;
        }
        RecordContract recordContract;
        if (!moreThanThreshold) {
            //执行IPFS存储
            String hash = IPFSUtil.upload(sourceFilePath);
            FileUtil.writeStringToFile(fileHashPath, hash);
            //将得到的Hash进行上链
            //文章标题：originalFileName
            Web3JClient.setPk("0x6575931f462a3cd3c69255e575fb1d77f7ec19e7c04fa1b22951d23b9ddea8d1");
            String pk = Web3JClient.getPk();
            if (Web3JClient.getContractAddress() == null) {
                recordContract = Web3JClient.deploy(pk);
                String contractAddress = recordContract.getContractAddress();
                Web3JClient.setContractAddress(contractAddress);
            } else {
                recordContract = Web3JClient.load(pk);
            }
            log.info("originalFileName=====>"+originalFileName);
            log.info("userName=====>"+userName);
            log.info("timeStamp=====>"+timeStamp);
            log.info("hash=====>"+hash);
            TransactionReceipt transactionReceipt = recordContract.addRecord(originalFileName, userName, timeStamp, hash).send();
            BigInteger recordLen = recordContract.getRecordLength().send();
            Integer len = Integer.valueOf(String.valueOf(recordLen));
            Tuple5<String, String, String, String, String> result = recordContract.getRecord(BigInteger.valueOf(len - 1)).send();
            String sender = result.component1();
            String workTitle = result.component2();
            String authorName = result.component3();
            String createTime1 = result.component4();
            Date createTime = VeDate.strToDateLong(createTime1);
            String workHash = result.component5();
            String txHash = transactionReceipt.getTransactionHash();
            Map<String, Object> blockMap = new LinkedHashMap<>();
            blockMap.put("txHash", txHash);
            blockMap.put("sender", sender);
            blockMap.put("createTime", createTime);
            blockMap.put("authorId", authorId);
            blockMap.put("authorName", authorName);
            blockMap.put("workTitle", workTitle);
            blockMap.put("workHash", workHash);
            blockService.storeBlock(blockMap);
            resultStatus = 1;
            feedback = "相似度检测通过";
        } else {
            resultStatus = 0;
            feedback = "相似度检测未通过";
        }
        Map<String, Object> recordMap = new LinkedHashMap<>();
        recordMap.put("authorId", authorId);
        recordMap.put("userName", userName);
        recordMap.put("workTitle", originalFileName);
        recordMap.put("checkTime", currentDate);
        recordMap.put("resultStatus", resultStatus);
        recordMap.put("feedback", feedback);
        recordService.storeRecord(recordMap);
        return ResultGenerator.genSuccessResult(true);
    }
}
