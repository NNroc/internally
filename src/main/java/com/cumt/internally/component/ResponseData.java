package com.cumt.internally.component;

import com.cumt.internally.model.Result;
import org.springframework.stereotype.Component;

/**
 * @author NNroc
 * @date 2020/5/12 14:56
 */
@Component
public class ResponseData {
    public Result write(String msg, int code, Object data) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
