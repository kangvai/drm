package com.kangvai.demo.controller;

import com.kangvai.demo.service.BlockService;
import com.kangvai.demo.util.PageQueryUtil;
import com.kangvai.demo.util.Result;
import com.kangvai.demo.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author kangvai
 * @date 2020/5/3 10:39
 */
@Controller
public class BlockController {

    @Resource
    private BlockService blockService;

    /**
     * 上链数据
     * @params [params, request]
     * @return com.kangvai.demo.util.Result
     */
    @GetMapping("/user/block/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(blockService.getBlocksPage(pageUtil));
    }
}
