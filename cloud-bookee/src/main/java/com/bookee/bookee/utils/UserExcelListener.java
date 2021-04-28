package com.bookee.bookee.utils;

/**
 * @author jinyang
 * @date 2021/4/27 9:00 下午
 */

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @description: 模型解析监听器--> 2.0.5以后每个模型创建一个监听器
 *               每解析一行会回调invoke()方法，整个excel解析结束会执行doAfterAllAnalysed()方法
 *               有个很重要的点 UserExcelListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 * @author: Li Kang
 * @create: 2020-05-04 11:08
 */
@Slf4j
public class UserExcelListener<User> extends AnalysisEventListener<User> {

    private final List<User> userList = Lists.newArrayList();


    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;


    // -----------------------------------------------------------------------------------

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */

    // -----------------------------------------------------------------------------------


    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    }

    @Override
    public void invoke(User user, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(user));
        userList.add(user);
        // 实际数据量比较大时，rows里的数据可以存到一定量之后进行批量处理（比如存到数据库），
        // 然后清空列表，以防止内存占用过多造成OOM
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (userList.size() >= BATCH_COUNT) {
            saveUserList();
            // 存储完成清理 list
            userList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("read {} rows", userList.size());
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveUserList();
    }

    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
        }
    }

    public List<User> getRows() {
        return userList;
    }

    /**
     * 加上存储数据库
     */
    private void saveUserList() {
        log.info("{}条数据，开始存储数据库！", userList.size());

        log.info("存储数据库成功！");
    }


}

