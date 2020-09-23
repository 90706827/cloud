package com.zgcenv.core.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
@ApiModel
public class BaseForm<T> {
    /**
     * 用户名
     */
    private String username;

    /**
     * From转化为Po，进行后续业务处理
     *
     * @param clazz
     * @return
     */
    public T toPo(Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, t);
        return t;
    }

}
