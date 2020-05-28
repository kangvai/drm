package com.kangvai.demo.service;

import com.kangvai.demo.model.RecordDto;
import com.kangvai.demo.model.UserDto;
import com.kangvai.demo.util.PageQueryUtil;
import com.kangvai.demo.util.PageResult;

import java.util.Map;

/**
 * @author kangvai
 * @date 2020/5/3 10:01
 */
public interface RecordService extends BaseService<RecordDto>{

    void storeRecord(Map<String, Object> recordMap);

    PageResult getRecordsPageByAuthorID(PageQueryUtil pageUtil, Integer authorId);

}
