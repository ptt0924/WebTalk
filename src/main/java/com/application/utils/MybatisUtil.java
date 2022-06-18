package com.application.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author chenLiang
 * @Date 2022/5/30 18:36
 */
public class MybatisUtil  {

    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            //使用mybatis第一获取sqlsessionfactory对象
            String resource = "mybatisConfig.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession(true);
    }


}
