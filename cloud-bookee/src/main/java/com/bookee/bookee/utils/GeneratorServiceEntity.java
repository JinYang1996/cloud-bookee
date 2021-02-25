package com.bookee.bookee.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * <p>================================================
 * <p>Title: 代码生成
 * <p>Description: http://mp.baomidou.com/#/generate-code
 * <p>Date: 2018/11/1
 * <p>================================================
 *
 * @author no one
 * @version 1.0
 */
public class GeneratorServiceEntity {

    @Test
    public void generateCode() {
        /*String packageName = "com.digitalchina.livable.modules";
        generateByTables(packageName, "live_door_device");*/
        String packageName = "com.bookee.bookee.product";
        generateByTables(packageName, "product");
    }

    /**
     * @param serviceNameStartWithI user -> UserService, 设置成true: user -> IUserService
     * @param packageName
     * @param tableNames
     */
    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        //String dbUrl = "jdbc:mysql://39.105.43.181:13306/housing-info-dev";
        String dbUrl = "jdbc:mysql://localhost:23306/bookee?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("123456789")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("CAF_", "live_", "biz")// 此处可以修改为您的表前缀
                .setEntityColumnConstant(true)  // 生成字段常量
                .setInclude(tableNames);        // 修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setEnableCache(false)  // XML 二级缓存
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setAuthor("no one")
                //.setOutputDir("D:\\workspace\\idea\\housing-rental\\housing-rental-service\\src\\main\\java\\")
                .setOutputDir("D:\\Project\\generateByTables\\")
//                .setOutputDir("/Users/xujiangyun/Projects/Git/generateByTables")
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator()
//                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }

    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }

}
