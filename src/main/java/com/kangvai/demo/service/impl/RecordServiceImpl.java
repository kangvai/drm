package com.kangvai.demo.service.impl;

import com.kangvai.demo.mapper.RecordMapper;
import com.kangvai.demo.model.RecordDto;
import com.kangvai.demo.service.RecordService;
import com.kangvai.demo.util.PageQueryUtil;
import com.kangvai.demo.util.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author kangvai
 * @date 2020/5/3 10:06
 */
@Service
public class RecordServiceImpl extends BaseServiceImpl<RecordDto> implements RecordService {

    @Resource
    private RecordMapper recordMapper;

    @Override
    public void storeRecord(Map<String, Object> recordMap) {
        recordMapper.insertRecord(recordMap);
    }

    @Override
    public PageResult getRecordsPageByAuthorID(PageQueryUtil pageUtil, Integer authorId) {
        List<RecordDto> records = recordMapper.findRecordsListByAuthorID(pageUtil, authorId);
        Integer total = recordMapper.getTotalRecordsByAuthorID(pageUtil, authorId);
        PageResult pageResult = new PageResult(records, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
