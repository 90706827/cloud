package com.zgcenv.core.jackson.converter;

import com.zgcenv.core.utils.MapHelper;

import java.util.Arrays;
import java.util.Map;

/**
 * @InterfaceName BaseEnum
 * @Description 枚举类型基类
 * @Author Mr.Jangni
 * @Date 2020-9-10
 * @Version 1.0
 **/
public interface BaseEnum {
    /**
     * 将制定的枚举集合转成 map
     * key -> name
     * value -> desc
     *
     * @param list
     * @return
     */
    static Map<String, String> getMap(BaseEnum[] list) {
        return MapHelper.uniqueIndex(Arrays.asList(list), BaseEnum::getCode, BaseEnum::getDesc);
    }

    /**
     * 编码重写
     *
     * @return
     */
    default String getCode() {
        return toString();
    }

    /**
     * 描述
     *
     * @return
     */
    String getDesc();

    /**
     * 判断val是否跟当前枚举相等
     *
     * @param val
     * @return
     */
    default boolean eq(String val) {
        return this.getCode().equalsIgnoreCase(val);
    }

    /**
     * 枚举值
     *
     * @return
     */
    default String getValue() {
        return getCode();
    }
}
