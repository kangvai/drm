package com.kangvai.demo.service;

import com.kangvai.demo.model.BlockDto;
import com.kangvai.demo.util.PageQueryUtil;
import com.kangvai.demo.util.PageResult;

import java.util.Map;

/**
 * @author kangvai
 * @date 2020/5/3 10:40
 */
public interface BlockService extends BaseService<BlockDto>{

    void storeBlock(Map<String, Object> blockMap);

    PageResult getBlocksPage(PageQueryUtil pageUtil);

    PageResult getOthersBlocksPage(PageQueryUtil pageUtil, Integer authorId);
}
