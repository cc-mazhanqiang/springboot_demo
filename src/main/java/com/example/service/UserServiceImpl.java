package com.example.service;

import com.example.dao.UserRepository;
import com.example.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className:
 * @author: sir
 * @description: TODO
 * @date: 2021/6/26 - 23:07
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * 根据用户名和密码判断用户是否存在
     * @param username
     * @param password
     * @return
     */
    @Override
    public User checkUser (String username, String password) {
        return userRepository.findByUserNameAndPassword(username,password);
    }

    /**
     * 查询数据库中所有的数据，并进行分页
     * @return
     */
    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<User> getUserList(int pageNum, int pageSize) {

        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNum,pageSize,sort);
        Page<User> users = userRepository.findAll(pageable);
        return users;

    }

    /**
     * 添加用户功能
     * @param
     */
    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    /**
     *
     * @param id
     */
    @Override
    public void delUserById(Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * 根据id修改用户
     * @param username
     * @param password
     * @param name
     * @param grants
     * @param id
     */
    @Override
    public void editUser(String username, String password, String name, String grants, Integer id) {
        userRepository.updateUser(username,password,name,grants,id);
    }


}
