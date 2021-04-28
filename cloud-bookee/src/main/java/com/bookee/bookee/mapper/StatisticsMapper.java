package com.bookee.bookee.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bookee.bookee.entity.StatisticsEo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author jinyang
 * @date 2021/4/27 9:11 下午
 */
public interface StatisticsMapper extends BaseMapper<StatisticsEo> {
    List<StatisticsEo> selectAllTypeCount(@Param("tenantIds") List<String> tenantIds);

    List<StatisticsEo> selectAllTypeCountMore10(@Param("tenantIds") List<String> tenantIds);

    List<StatisticsEo> selectDetailTenantCount(@Param("tenantIds") List<String> tenantIds);

    List<StatisticsEo> selectDetailTenantCountMore10(@Param("tenantIds") List<String> tenantIds);

    List<String> selectAllTenant();
}
