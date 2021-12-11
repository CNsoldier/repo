package com.soldier;

import com.soldier.domian.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    /**
     * 快速入门测试方法
     * 查询用户信息
     */
    @Test
    public void mybatisQuickStart() throws IOException {

        // 1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        // 2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4.执行sql 参数：statementid：namespace.id
        List<User> users = sqlSession.selectList("userMapper.findAll");

        // 5.打印结果
        for (User user : users){
            System.out.println(user);
        }

        // 6.释放资源
        sqlSession.close();
    }

    /**
     * 测试新增用户
     */
    @Test
    public void testSave() throws IOException {

        // 1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        // 2.获取SQLSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3.获取SQLSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4.创建User对象，将插入的数据封装到User中
        User user = new User();
        user.setUsername("貂蝉");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("三国");

        // 5.执行SQL
        int row = sqlSession.insert("userMapper.saveUser", user);

        // 手动提交事务
        sqlSession.commit();

        // 6.打印结果
        System.out.println(row);

        // 7.释放资源
        sqlSession.close();
    }

    /**
     * 修改用户信息
     */
    @Test
    public void testUpdate() throws IOException {

        // 1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        // 2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4.创建User对象，将修改的数据封装到User对象中
        User user = new User();
        user.setId(4);
        user.setUsername("西施");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("春秋时期");

        // 5.执行SQL语句
        int row = sqlSession.update("userMapper.update", user);

        // 6.手动提交事务
        sqlSession.commit();

        // 7.输出结果
        System.out.println(row);

        // 8.释放资源
        sqlSession.close();
    }

    /**
     * 删除用户
     */
    @Test
    public void testDelete() throws IOException {
        // 1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        // 2.获取SQLSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3.获取SQLSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4.执行SQL语句
        int row = sqlSession.delete("userMapper.delete", 5);

        // 5.手动提交事务
        sqlSession.commit();

        // 6.打印结果
        System.out.println(row);

        // 7.释放资源
        sqlSession.close();
    }
}
