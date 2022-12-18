package common.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;

    private String msg;

    private Object data;

    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS_CODE, "", data);
    }

    public static Result fail(Integer code, String message) {
        return new Result(code, message, null);
    }
}
