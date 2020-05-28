package com.kangvai.demo.service;

import com.kangvai.demo.model.TradeDto;
import com.kangvai.demo.model.UserDto;
import com.kangvai.demo.util.PageQueryUtil;
import com.kangvai.demo.util.PageResult;
import org.springframework.stereotype.Service;

/**
 * @author kangvai
 * @date 2020/5/3 19:47
 */
@Service
public interface TradeService extends BaseService<TradeDto>{

    PageResult getTradesPageByID(PageQueryUtil pageUtil, Integer readerId);

    PageResult getOthersTradesPage(PageQueryUtil pageUtil, Integer readerId);
}
