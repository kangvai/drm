package com.kangvai.demo.mapper;

import com.kangvai.demo.model.TradeDto;
import com.kangvai.demo.model.entity.Trade;
import com.kangvai.demo.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TradeMapper extends Mapper<TradeDto> {

    List<TradeDto> findTradesListByReaderId(@Param("pageUtil")PageQueryUtil pageUtil, @Param("readerId")Integer readerId);

    Integer getTotalTradesByReaderId(@Param("pageUtil")PageQueryUtil pageUtil, @Param("readerId")Integer readerId);
}