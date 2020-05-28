package com.kangvai.demo.service.impl;

import com.kangvai.demo.mapper.BlockMapper;
import com.kangvai.demo.model.BlockDto;
import com.kangvai.demo.service.BaseService;
import com.kangvai.demo.service.BlockService;
import com.kangvai.demo.util.PageQueryUtil;
import com.kangvai.demo.util.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author kangvai
 * @date 2020/5/3 10:43
 */
@Service
public class BlockServiceImpl extends BaseServiceImpl<BlockDto> implements BlockService {

    @Resource
    private BlockMapper blockMapper;

    @Override
    public void storeBlock(Map<String, Object> blockMap) {
        blockMapper.insertBlock(blockMap);
    }

    @Override
    public PageResult getBlocksPage(PageQueryUtil pageUtil) {
        List<BlockDto> blocks = blockMapper.findBlocksList(pageUtil);
        Integer total = blockMapper.getTotalBlocks(pageUtil);
        PageResult pageResult = new PageResult(blocks, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public PageResult getOthersBlocksPage(PageQueryUtil pageUtil, Integer authorId) {
        List<BlockDto> blocks = blockMapper.findOthersBlocksList(pageUtil,authorId);
        Integer total = blockMapper.getOthersTotalBlocks(pageUtil,authorId);
        PageResult pageResult = new PageResult(blocks, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

}
