package com.visionki.wechat.exceptions;

import com.visionki.wechat.constant.REnum;
import com.visionki.wechat.util.RUtil;
import com.visionki.wechat.vo.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuboyi
 * @Description: 统一异常处理
 * @create 2019-04-12 17:02
 */

@ControllerAdvice
public class MyControllerAdvice {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public R errorHandler(Exception ex) {
        ex.printStackTrace();
        return RUtil.error(REnum.ERROR);
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public R myErrorHandler(BaseException ex) {
        return RUtil.error(ex.getREnum());
    }
}
