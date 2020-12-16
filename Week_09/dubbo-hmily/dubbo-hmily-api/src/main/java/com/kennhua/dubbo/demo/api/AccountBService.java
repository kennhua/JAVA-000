package com.kennhua.dubbo.demo.api;

import com.kennhua.dubbo.demo.entity.RecordDTO;
import org.dromara.hmily.annotation.Hmily;

/**
 * @author liugenghua
 **/
public interface AccountBService {

    @Hmily
    boolean decrease(RecordDTO recordDTO);

    @Hmily
    boolean increase(RecordDTO recordDTO);
}
