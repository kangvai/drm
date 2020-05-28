package com.kangvai.demo.controller;

import com.kangvai.demo.model.BlockDto;
import com.kangvai.demo.model.TradeDto;
import com.kangvai.demo.model.UserDto;
import com.kangvai.demo.service.BlockService;
import com.kangvai.demo.service.TradeService;
import com.kangvai.demo.service.UserService;
import com.kangvai.demo.util.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author kangvai
 * @date 2020/5/3 14:11
 */
@Controller
@Slf4j
public class TradeController {

    //private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private BlockService blockService;

    @Resource
    private UserService userService;

    @Resource
    private TradeService tradeService;

    /**
     * 上链数据
     * @params [params, request]
     * @return com.kangvai.demo.util.Result
     */
    @GetMapping("/user/trade/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Integer authorId = (Integer) request.getSession().getAttribute("uid");
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(blockService.getOthersBlocksPage(pageUtil,authorId));
    }


    @PostMapping(value = "user/trade/buy")
    @ResponseBody
    public Result buy(HttpServletRequest request,
                      @RequestParam("workTitle") String workTitle,
                      @RequestParam("authorId") String authorId,
                      @RequestParam("authorName") String authorName,
                      @RequestParam("uid") String uid,
                      @RequestParam("username") String username,
                      @RequestParam("publicKey") String publicKey,
                      @RequestParam("buyerpwd") String buyerpwd
                      ) throws ExecutionException, InterruptedException {
        Integer aid = Integer.parseInt(authorId);
        UserDto userDto = new UserDto();
        userDto.setId(aid);
        userDto = userService.selectOne(userDto);
        if (userDto == null) {
            return ResultGenerator.genFailResult("购买失败！");
        }
        String authorAccount = userDto.getPublicKey();
        String readerAccount = publicKey;
        String readerPrivateKey = buyerpwd;
        String transactionHash = Web3JClient.sendEtherFromReaderToAuthor(readerAccount,readerPrivateKey,authorAccount);
        if ( transactionHash != null) {
            BlockDto blockDto = new BlockDto();
            blockDto.setAuthorId(aid);
            blockDto.setWorkTitle(workTitle);
            blockDto.setAuthorName(authorName);
            blockDto = blockService.selectOne(blockDto);
            if ( blockDto == null ) {
                return ResultGenerator.genFailResult("failure");
            } else {
                String timeStamp = VeDate.getStringDate();
                Date currentDate = VeDate.strToDateLong(timeStamp);         //当前时间
                String workHash = blockDto.getWorkHash();
                TradeDto tradeDto = new TradeDto();
                tradeDto.setAuthorId(aid);
                tradeDto.setAuthorName(authorName);
                tradeDto.setWorkTitle(workTitle);
                tradeDto.setReaderId(Integer.parseInt(uid));
                tradeDto.setReaderName(username);
                tradeDto.setBuyTime(currentDate);
                tradeDto.setTxHash(transactionHash);
                tradeDto.setWorkHash(blockDto.getWorkHash());
                tradeDto.setIsdeleted((byte) 0);
                if (tradeService.insert(tradeDto) > 0) {
                    return ResultGenerator.genSuccessResult(workHash);
                }
                else {
                    return ResultGenerator.genFailResult("failure");
                }

            }
        }
        return ResultGenerator.genFailResult("failure");
    }
}
