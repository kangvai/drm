package com.kangvai.demo.mapper;

import com.kangvai.demo.model.RecordDto;
import com.kangvai.demo.model.entity.Record;
import com.kangvai.demo.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RecordMapper extends Mapper<RecordDto> {
    // 根据用户id得到所有记录数据
    List<RecordDto> findRecordsListByAuthorID(@Param("map") PageQueryUtil pageUtil, @Param("authorId") Integer authorId);

    // 根据用户id得到所有记录数目
    Integer getTotalRecordsByAuthorID(@Param("map") PageQueryUtil pageUtil, @Param("authorId") Integer authorId);

    void insertRecord(Map<String, Object> recordMap);
}