package com.zgcenv.core.context;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Resp
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-10
 * @Version 1.0
 **/
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Resp<T> implements Serializable {

    @ApiModelProperty(value = "请求状态")
    private boolean success;

    @ApiModelProperty(value = "响应数据")
    private T result;

    @ApiModelProperty(value = "响应错误编码")
    private int error;

    @ApiModelProperty(value = "响应错误信息")
    private String error_description;

    @ApiModelProperty(value = "请求路径")

    @JsonIgnore
    private String path;

    @ApiModelProperty(value = "响应时间戳")
    @JsonIgnore
    private long timestamp = System.currentTimeMillis();

    private Resp(boolean success) {
        this.success = success;
        this.timestamp = System.currentTimeMillis();
    }

    public Resp(boolean success, T result) {
        this.success = success;
        this.result = result;
        this.timestamp = System.currentTimeMillis();
    }

    public Resp(boolean success, int error, String error_description) {
        this.success = success;
        this.error = error;
        this.error_description = error_description;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 请求成功 无结果
     */
    public static <E> Resp<E> success() {
        return new Resp<E>(true);
    }

    /**
     * 请求成功 有结果
     *
     * @param result 结果
     */
    public static <E> Resp<E> success(E result) {
        return new Resp<E>(true, result);
    }

    /**
     * 请求失败消息
     *
     * @param respCode
     */
    public static <E> Resp<E> fail(RespCode respCode) {
        return new Resp<E>(false, respCode.getCode(), respCode.getMsg());
    }
}
