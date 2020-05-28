package com.kangvai.demo.controller;

import com.kangvai.demo.service.NewsService;
import com.kangvai.demo.util.PageQueryUtil;
import com.kangvai.demo.util.Result;
import com.kangvai.demo.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2020/5/2 16:14
 */
@Controller
public class NewsController {
    @Resource
    private NewsService newsService;

    @GetMapping("/user/news/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(newsService.getNewsPage(pageUtil));
    }
}
