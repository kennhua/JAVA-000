package com.kennhua.dubbo.demo.record.mapper;

import com.kennhua.dubbo.demo.entity.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author liugenghua
 **/
@Mapper
public interface RecordMapper {

    @Insert("insert into t_record (id,status,user_id,rmb,usd) " +
            " values ( #{id},#{status},#{userId},#{rmb},#{usd})")
    int insert(Record record);

    @Update("update t_record set status=#{status} where id=#{id}")
    int update(Record record);
}
