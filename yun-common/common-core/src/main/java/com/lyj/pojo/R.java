package com.lyj.pojo;
import java.io.Serializable;
import cn.hutool.http.HttpStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @date 2019/10/28
 * @author lyj
 */

@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@ApiModel("响应包装实体")
public class R<T> implements Serializable {

    @Getter
    @Setter
    @ApiModelProperty("状态码：200 成功，500 失败")
    public int code = HttpStatus.HTTP_OK;

    @Getter
    @Setter
    @ApiModelProperty("响应消息")
    public String msg = "success";

    @Getter
    @Setter
    @ApiModelProperty("响应数据")
    public T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = HttpStatus.HTTP_INTERNAL_ERROR;
    }

    public static R ok(){
        return R
                .builder()
                .code(HttpStatus.HTTP_OK)
                .msg("success")
                .build();
    }

    public static R error(){
        return R
                .builder()
                .code(HttpStatus.HTTP_INTERNAL_ERROR)
                .msg("error")
                .build();
    }

    public static R errorParam(Object param){
        return R
                .builder()
                .code(HttpStatus.HTTP_INTERNAL_ERROR)
                .msg("param error :" + param)
                .build();
    }

    public static R error(String msg){
        return R
                .builder()
                .code(HttpStatus.HTTP_INTERNAL_ERROR)
                .msg(msg)
                .build();
    }

    public static R error(int code,String msg){
        return R
                .builder()
                .code(code)
                .msg(msg)
                .build();
    }

    public static R ok(Object data){
        return R
                .builder()
                .code(HttpStatus.HTTP_OK)
                .data(data)
                .msg("success")
                .build();
    }
}