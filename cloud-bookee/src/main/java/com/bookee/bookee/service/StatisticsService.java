package com.bookee.bookee.service;

import com.bookee.bookee.entity.StatisticsEo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatisticsService {

    List<StatisticsEo> selectAllTypeCount(List<String> tenantIds);

    List<StatisticsEo> selectAllTypeCountMore10(@Param("tenantIds") List<String> tenantIds);

    List<StatisticsEo> selectDetailTenantCount(@Param("tenantIds") List<String> tenantIds);

    List<StatisticsEo> selectDetailTenantCountMore10(@Param("tenantIds") List<String> tenantIds);

    List<String> selectAllTenant();
}
