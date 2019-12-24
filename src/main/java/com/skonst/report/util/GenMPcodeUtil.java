package com.skonst.report.util;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * mybaties-plus代码生成
 * @author pmc
 * @since 2019年1月4日
 */
public class GenMPcodeUtil {
    public static void main(String[] args) {
        //generateCode(new String[]{"sys_user_role"});
        System.out.println(IdWorker.getId());
        generateCode("cof_user_account");
    }

    /**
     * @param tableNames 要生成的表
     */
    public static void generateCode(String... tableNames) {
        String packageName = "com.ruoyi";
        String modelName = "business";
        generateByTables(packageName, modelName, tableNames);
    }

    /**
     * @param packageName 项目主包名
     * @param modelName 模块包名
     * @param tableNames 数据库表名们
     */
    private static void generateByTables(String packageName, String modelName, String... tableNames) {
        new AutoGenerator()
                /*数据源配置*/
                .setDataSource(
                        new DataSourceConfig()
                                .setDbType(DbType.MYSQL)
                                .setUrl("jdbc:mysql://localhost:3306/djcof?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC")
                                .setUsername("root")
                                .setPassword("1")
                                .setDriverName("com.mysql.cj.jdbc.Driver")
                )
                /*代码生成策略配置*/
                .setGlobalConfig(
                        new GlobalConfig().setActiveRecord(false)//开启 ActiveRecord 模式
                            .setAuthor("wufeng")
                            .setOutputDir("C:\\Users\\wufeng\\Desktop")
                            .setFileOverride(true)
                            .setServiceName("I%sService")
                            .setBaseResultMap(true)
                            .setBaseColumnList(true)
                )
                .setStrategy(
                        new StrategyConfig()
                            .setCapitalMode(true)
                            .setEntityLombokModel(true)//是否采用Lombok插件
                            .setNaming(NamingStrategy.underline_to_camel)//驼峰
                            .setInclude(tableNames)//修改替换成你需要的表名，多个表名传数组
                )
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("web.controller")//controller包名
                                .setEntity(modelName+".bean")//实体类包名 以下类推
                                .setMapper(modelName+".mapper")
                                .setXml(modelName+".mapper.xml")
                                .setService(modelName+".service")
                                .setServiceImpl(modelName+".service.impl")
                )
                .execute();
    }

}
