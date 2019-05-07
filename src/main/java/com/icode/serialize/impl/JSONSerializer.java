package com.icode.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.icode.serialize.Serializer;
import com.icode.serialize.SerializerAlogrithm;

/**
 * JSON序列化
 * @author wangz
 * @date 2019/5/7 - 17:15
 **/
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        //使用阿里JSON框架，将JSON串转为字节数组
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        //将字节数组转为对象
        return JSON.parseObject(bytes,clazz);
    }
}
