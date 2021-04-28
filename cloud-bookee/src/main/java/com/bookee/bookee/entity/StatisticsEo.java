package com.bookee.bookee.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jinyang
 * @date 2021/4/27 9:03 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsEo implements Serializable {
    @ExcelProperty(value = "类型", index = 0)
    private String extension ;

    @ExcelProperty(value = "数量", index = 1)
    private Integer count;

    @ExcelIgnore
    private String tenant;
}
