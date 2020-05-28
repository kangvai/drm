package com.kangvai.demo.mapper;

import com.kangvai.demo.model.NewsDto;
import com.kangvai.demo.model.entity.News;
import com.kangvai.demo.util.PageQueryUtil;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface NewsMapper extends Mapper<NewsDto> {

    List<NewsDto> findNewsList(PageQueryUtil pageUtil);

    Integer getTotalNews(PageQueryUtil pageUtil);
}