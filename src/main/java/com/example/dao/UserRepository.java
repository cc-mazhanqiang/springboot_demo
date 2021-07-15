package com.example.dao;

import com.example.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @className:
 * @author: sir
 * @description: TODO
 * @date: 2021/6/26 - 23:03
 */


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
            //  @Query注解查询
        //    select u from User u where u.emailAddress = ?1 and u.lastname = ?2
        //     update User u set u.firstname = ?1 where u.lastname = ?2
        //        delete from User u where u.role.id = ?1

    /**
     * 根据username、password查询用户，实现用户的登录
     * @param username
     * @param password
     * @return
     */
    @Query(value = "select u from User u where u.username = ?1 and u.password = ?2")
    User findByUserNameAndPassword(String username, String password);

    /**
     * 查询所有的数据
     * @return
     */
    @Query(value = "select u from User u")
    List<User> findAll();


    /**
     * 根据id删除用户
     */
    @Transactional
    @Modifying
    @Query(value = " delete from User u where u.id = ?1 ")
    void delUserById(Integer id);

    /**
     * 根据id修改用户
     * @param username
     * @param password
     * @param name
     * @param grants
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "update User u set u.username = ?1,u.password = ?2,u.name = ?3, u.grants = ?4 where u.id = ?5")
    void updateUser(String username,String password,String name,String grants,Integer id);

}
