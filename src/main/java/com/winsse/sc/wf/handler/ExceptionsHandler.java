package com.winsse.sc.wf.handler;

import com.sc.commomlib.ge.utils.support.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xiaheng
 * @date 2021年10月18日14:44
 */
@RestControllerAdvice
public class ExceptionsHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /** 运行时异常 */
    @ExceptionHandler(RuntimeException.class)
    public ResultBean runtimeExceptionHandler(RuntimeException ex) {
        log.error("运行时异常：{}", ex.getMessage(), ex);
        return ResultBean.failure().msg(ex.getMessage());
    }

    /** 空指针异常 */
    @ExceptionHandler(NullPointerException.class)
    public ResultBean nullPointerExceptionHandler(NullPointerException ex) {
        log.error("空指针异常：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("空指针异常");
    }

    /** 类型转换异常 */
    @ExceptionHandler(ClassCastException.class)
    public ResultBean classCastExceptionHandler(ClassCastException ex) {
        log.error("类型转换异常：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("类型转换异常");
    }
    /** 文件未找到异常 */
    @ExceptionHandler(FileNotFoundException.class)
    public ResultBean FileNotFoundException(FileNotFoundException ex) {
        log.error("文件未找到异常：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("文件未找到异常");
    }
    /** 数字格式异常 */
    @ExceptionHandler(NumberFormatException.class)
    public ResultBean NumberFormatException(NumberFormatException ex) {
        log.error("数字格式异常：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("数字格式异常");
    }
    /** 安全异常 */
    @ExceptionHandler(SecurityException.class)
    public ResultBean SecurityException(SecurityException ex) {
        log.error("安全异常：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("安全异常");
    }
    /** sql异常 */
    @ExceptionHandler(SQLException.class)
    public ResultBean SQLException(SQLException ex) {
        log.error("sql异常：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("sql异常");
    }
    /** 类型不存在异常 */
    @ExceptionHandler(TypeNotPresentException.class)
    public ResultBean TypeNotPresentException(TypeNotPresentException ex) {
        log.error("类型不存在异常：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("类型不存在异常");
    }

    /** IO异常 */
    @ExceptionHandler(IOException.class)
    public ResultBean iOExceptionHandler(IOException ex) {
        log.error("IO异常：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("IO异常");
    }

    /** 未知方法异常 */
    @ExceptionHandler(NoSuchMethodException.class)
    public ResultBean noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        log.error("未知方法异常：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("未知方法异常");
    }

    /** 数组越界异常 */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResultBean indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        log.error("数组越界异常：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("数组越界异常");
    }
    /** sql语法错误异常 */
    @ExceptionHandler(BadSqlGrammarException.class)
    public ResultBean BadSqlGrammarException(BadSqlGrammarException ex) {
        log.error("sql语法错误异常：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("sql语法错误异常");
    }

    /** 无法注入bean异常 */
    @ExceptionHandler(NoSuchBeanDefinitionException.class)
    public ResultBean NoSuchBeanDefinitionException(NoSuchBeanDefinitionException ex) {
        log.error("无法注入bean异常 ：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("无法注入bean");
    }

    /** Http消息不可读异常 */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResultBean requestNotReadable(HttpMessageNotReadableException ex) {
        log.error("400错误..requestNotReadable：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("Http消息不可读");
    }

    /** 400错误 */
    @ExceptionHandler({TypeMismatchException.class})
    public ResultBean requestTypeMismatch(TypeMismatchException ex) {
        log.error("400错误..TypeMismatchException：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("服务器异常");
    }

    /** 500错误 */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public ResultBean server500(RuntimeException ex) {
        log.error("500错误：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("服务器异常");
    }

    /** 栈溢出 */
    @ExceptionHandler({StackOverflowError.class})
    public ResultBean requestStackOverflow(StackOverflowError ex) {
        log.error("栈溢出：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("栈溢出异常");
    }

    /** 除数不能为0 */
    @ExceptionHandler({ArithmeticException.class})
    public ResultBean arithmeticException(ArithmeticException ex) {
        log.error("除数不能为0：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("除数不能为0异常");
    }

    /** 其他错误 */
    @ExceptionHandler({Exception.class})
    public ResultBean exception(Exception ex) {
        log.error("其他错误：{} ", ex.getMessage(), ex);
        return ResultBean.failure().msg("网络连接失败，请退出后再试");
    }
}
