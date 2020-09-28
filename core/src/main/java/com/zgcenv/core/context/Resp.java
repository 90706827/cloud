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
    private Boolean success;

    @ApiModelProperty(value = "响应数据")
    private T result;

    @ApiModelProperty(value = "响应错误编码")
    private Integer error;

    @ApiModelProperty(value = "响应错误信息")
    private String error_description;

    @ApiModelProperty(value = "请求路径")

    @JsonIgnore
    private String path;

    @ApiModelProperty(value = "响应时间戳")
    @JsonIgnore
    private Long timestamp = System.currentTimeMillis();

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
    public static <T> Resp<T> success() {
        return new Resp<T>(true, RespCode.SUCCESS.getCode(), RespCode.SUCCESS.getMsg());
    }

    /**
     * 请求成功 有结果
     *
     * @param result 结果
     */
    public static <T> Resp<T> success(T result) {
        return new Resp<T>(true, result);
    }

    /**
     * 系统异常
     */
    public static <T> Resp<T> fail() {
        return new Resp<T>(false, RespCode.SYSTEM_BUSY.getCode(), RespCode.SYSTEM_BUSY.getMsg());
    }

    /**
     * 请求失败消息
     *
     * @param respCode
     */
    public static <T> Resp<T> fail(RespCode respCode) {
        return new Resp<T>(false, respCode.getCode(), respCode.getMsg());
    }

}
