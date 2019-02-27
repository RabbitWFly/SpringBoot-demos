package com.rabbit.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的Mapper
 * 这个接口不能被扫描到，不能跟dao这个存放mapper文件放一起。
 * @param <T>
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
