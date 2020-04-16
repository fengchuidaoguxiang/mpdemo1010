package com.atguigu.mpdemo1010;

import com.atguigu.mpdemo1010.entity.User;
import com.atguigu.mpdemo1010.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class Mpdemo1010ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    // 查询user表所有数据
    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    // 添加操作
    @Test
    void addUser(){
        User user = new User();
        user.setName("hello1");
        user.setAge(70);
        user.setEmail("lucy@qq.com");

        int insert = userMapper.insert(user);
        System.out.println( "insert : " + insert);
    }

    // 修改操作
    @Test
    public void updateUser(){

        User user = new User();

        user.setId(1250565780223877122L);
        user.setAge(120);

        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    // 测试乐观锁
    @Test
    public void testOptimisticLocker(){

        // 根据id查询数据
        User user = userMapper.selectById(1250573370756440066L);

        // 进行修改
        user.setAge(200);
        userMapper.updateById(user);
    }

    @Test
    void testSelectDemo1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    @Test
    void testSelectDemo2(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","Tom");
        map.put("age", 28);
        List<User> users = userMapper.selectByMap(map);

        System.out.println(users);
    }

    // 分页查询
    @Test
    void testPage(){
        // 1.创建page对象
        // 传入两个参数：当前页 和 每页显示记录数
        Page<User> page = new Page<>(1, 3);
        // 调用mp分页查询的方法
        // 调用mp分页查询的过程中，底层封装
        // 把分页所有数据封装到page对象里面
        userMapper.selectPage(page,null);

        // 通过page对象获取分页数据
        System.out.println(page.getCurrent()); // 当前页
        System.out.println(page.getRecords()); // 每页数据list集合
        System.out.println(page.getSize()); // 每页显示记录数
        System.out.println(page.getTotal()); // 总记录数
        System.out.println(page.getPages()); // 总页数

        System.out.println(page.hasNext()); // 是否有下一页
        System.out.println(page.hasPrevious()); // 是否有上一页

    }

    // 删除操作 物理删除
    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(1250647286447431681L);
        System.out.println(result);
    }

    // 删除操作 物理删除-批量删除
    @Test
    public void testDeleteByBatchIds(){
        int result = userMapper.deleteBatchIds(Arrays.asList(2,3));
        System.out.println(result);
    }

    // mp实现复杂查询操作
    @Test
    public void testSelectQuery(){

        // 创建QueryWrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // 通过QueryWrapper设置条件

        // ge、gt、le、lt
        // 查询age>=30记录
        // 第一个参数字段名称，第二个参数设置值
//        wrapper.ge("age",30);

        // eq、ne
//        wrapper.eq("name","Sandy");
//        wrapper.ne("name","Sandy");

        // between
        // 查询年龄 20-30
//        wrapper.between("age",20,30);

        // like
//        wrapper.like("name","ma");

        // orderByDesc
//        wrapper.orderByDesc("id");

        // last
//        wrapper.last("limit 1");

        // 指定要查询的列
        wrapper.select("id","name");

        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }





}
