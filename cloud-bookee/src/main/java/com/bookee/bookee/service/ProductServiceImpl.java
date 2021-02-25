package com.bookee.bookee.service;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.ObjectUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bookee.bookee.entity.Product;
import com.bookee.bookee.mapper.ProductMapper;
import com.bookee.bookee.utils.DateUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author no one
 * @since 2021-02-19
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

	@Autowired
	RedissonClient redissonClient;
	
	@Override
	public boolean seckillProduct(Long productId, Integer count) {
		String keyName = "dec_store_lock_"+productId;
		RLock lock = redissonClient.getLock(keyName);
		try {
			lock.lock();
			Product product = this.baseMapper.selectById(productId);
			if(ObjectUtils.isNotEmpty(product) && product.getCount()==0){
				return false;
			}
			Integer stores = product.getCount()-count;
			if(stores<0){
				return false;
			}
			product.setCount(stores);
			product.setUpdateTime(new Date());
			this.baseMapper.updateById(product);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally{
			lock.unlock();
		}
		return true;
	}

}
