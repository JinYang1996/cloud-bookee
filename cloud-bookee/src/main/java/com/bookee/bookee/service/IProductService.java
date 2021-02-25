package com.bookee.bookee.service;

import com.bookee.bookee.entity.Product;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author no one
 * @since 2021-02-19
 */
public interface IProductService extends IService<Product> {

	boolean seckillProduct(Long productId, Integer count);
}
