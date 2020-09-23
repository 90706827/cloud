package com.zgcenv.core.exception;

import com.zgcenv.core.context.RespCode;
import lombok.Getter;

/**
 * @ClassName BizException
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-10
 * @Version 1.0
 **/
@Getter
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -3843907364558373817L;

    private RespCode respCode;

    public BizException(RespCode respCode, Throwable cause) {
        super(cause);
        this.respCode = respCode;
    }
    public BizException(RespCode respCode) {
        this.respCode = respCode;
    }
    @Override
    public String toString() {
        return "BizException [message=" + this.respCode.getMsg() + ", code=" + this.respCode.getCode() + "]";
    }

}
