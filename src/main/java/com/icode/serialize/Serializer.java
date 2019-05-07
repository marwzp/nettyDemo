package com.icode.serialize;

import com.icode.serialize.impl.JSONSerializer;

/**
 * 序列化接口
 * @author wangz
 * @date 2019/5/7 - 17:14
 **/
public interface Serializer {

    //默认的JSON序列化算法
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
