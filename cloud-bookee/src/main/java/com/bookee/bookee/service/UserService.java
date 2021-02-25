package com.bookee.bookee.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bookee.bookee.entity.UserEo;
import com.bookee.bookee.mapper.UserMapper;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, UserEo> {

	@Cacheable(value="selectUser",key="#userEo.id")
    public List<UserEo> selectOne(UserEo userEo) {
    	System.out.println("缓存没有命中！！！！");
        return baseMapper.selectList(new EntityWrapper<>(userEo));
    }

    public static void main(String[] args) {
//        org.apache.commons.lang3.StringUtils.stripEnd
    }
}
