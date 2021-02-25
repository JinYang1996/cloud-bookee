package com.bookee.bookee.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author no one
 * @since 2021-02-19
 */
@Getter
@Setter
@ToString
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private BigDecimal price;
    private Integer count;
    private Date createTime;
    private Date updateTime;

}
