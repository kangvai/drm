package com.kangvai.demo.service;

import com.kangvai.demo.model.NewsDto;
import com.kangvai.demo.model.UserDto;
import com.kangvai.demo.util.PageQueryUtil;
import com.kangvai.demo.util.PageResult;

/**
 * @author kangvai
 * @date 2020/5/2 20:48
 */
public interface NewsService extends BaseService<NewsDto>{

    /**
     * 分页查询
     * @params [pageUtil]
     * @return com.kangvai.demo.util.PageResult
     */
    PageResult getNewsPage(PageQueryUtil pageUtil);
}
