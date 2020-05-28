package com.kangvai.demo.mapper;

import com.kangvai.demo.model.BlockDto;
import com.kangvai.demo.model.entity.Block;
import com.kangvai.demo.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface BlockMapper extends Mapper<BlockDto> {
    // 查找所有上链数据
    List<BlockDto> findBlocksList(PageQueryUtil pageUtil);

    // 获取上链数据总数
    Integer getTotalBlocks(PageQueryUtil pageUtil);

    List<BlockDto> findOthersBlocksList(@Param("pageUtil")PageQueryUtil pageUtil, @Param("authorId") Integer authorId);

    Integer getOthersTotalBlocks(@Param("pageUtil") PageQueryUtil pageUtil,@Param("authorId") Integer authorId);

    void insertBlock(Map<String, Object> blockMap);
}