package com.kangvai.demo.service.impl;

import com.kangvai.demo.mapper.BlockMapper;
import com.kangvai.demo.mapper.TradeMapper;
import com.kangvai.demo.model.BlockDto;
import com.kangvai.demo.model.TradeDto;
import com.kangvai.demo.model.UserDto;
import com.kangvai.demo.service.TradeService;
import com.kangvai.demo.service.UserService;
import com.kangvai.demo.util.PageQueryUtil;
import com.kangvai.demo.util.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kangvai
 * @date 2020/5/3 19:48
 */
@Service
public class TradeServiceImpl extends BaseServiceImpl<TradeDto> implements TradeService {
    @Resource
    private TradeMapper tradeMapper;

    @Override
    public PageResult getTradesPageByID(PageQueryUtil pageUtil, Integer readerId) {
        List<TradeDto> trades = tradeMapper.findTradesListByReaderId(pageUtil,readerId);
        Integer total = tradeMapper.getTotalTradesByReaderId(pageUtil,readerId);
        PageResult pageResult = new PageResult(trades, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public PageResult getOthersTradesPage(PageQueryUtil pageUtil, Integer readerId) {
        List<TradeDto> trades = tradeMapper.findTradesListByReaderId(pageUtil,readerId);
        Integer total = tradeMapper.getTotalTradesByReaderId(pageUtil,readerId);
        PageResult pageResult = new PageResult(trades, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
