package com.kangvai.demo.service.impl;

import com.kangvai.demo.mapper.NewsMapper;
import com.kangvai.demo.model.NewsDto;
import com.kangvai.demo.service.NewsService;
import com.kangvai.demo.util.PageQueryUtil;
import com.kangvai.demo.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kangvai
 * @date 2020/5/2 20:48
 */

@Service
public class NewsServiceImpl extends BaseServiceImpl<NewsDto> implements NewsService {

    @Resource
    private NewsMapper newsMapper;

    /**
     * 查询所有新闻
     * @params [pageUtil]
     * @return com.kangvai.demo.util.PageResult
     */
    @Override
    public PageResult getNewsPage(PageQueryUtil pageUtil) {
        List<NewsDto> newsDto = newsMapper.findNewsList(pageUtil);
        Integer total = newsMapper.getTotalNews(pageUtil);
        PageResult pageResult = new PageResult(newsDto, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
