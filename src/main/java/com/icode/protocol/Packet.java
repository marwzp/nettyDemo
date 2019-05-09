package com.icode.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 通信过程中 Java 对象的抽象类
 * @author wangz
 * @date 2019/5/7 - 16:52
 **/
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;


    @JSONField(serialize = false)
    public abstract Byte getCommand();


}
