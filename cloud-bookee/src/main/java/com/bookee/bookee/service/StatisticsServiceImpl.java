package com.bookee.bookee.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bookee.bookee.entity.StatisticsEo;
import com.bookee.bookee.mapper.StatisticsMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jinyang
 * @date 2021/4/27 9:29 下午
 */
@Service
@Slf4j
public class StatisticsServiceImpl  extends ServiceImpl<StatisticsMapper, StatisticsEo> implements StatisticsService {
    @Override
    public List<StatisticsEo> selectAllTypeCount(List<String> tenantIds) {
        return this.baseMapper.selectAllTypeCount(tenantIds);
    }

    @Override
    public List<StatisticsEo> selectAllTypeCountMore10(@Param("tenantIds") List<String> tenantIds) {
        return this.baseMapper.selectAllTypeCountMore10(tenantIds);
    }

    @Override
    public List<StatisticsEo> selectDetailTenantCount(@Param("tenantIds") List<String> tenantIds) {
        return this.baseMapper.selectDetailTenantCount(tenantIds);
    }

    @Override
    public List<StatisticsEo> selectDetailTenantCountMore10(@Param("tenantIds") List<String> tenantIds) {
        return this.baseMapper.selectDetailTenantCountMore10(tenantIds);
    }

    @Override
    public List<String> selectAllTenant() {
        return this.baseMapper.selectAllTenant();
    }
}
