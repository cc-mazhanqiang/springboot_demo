package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @className:
 * @author: sir
 * @description: TODO
 * @date: 2021/6/26 - 23:12
 */
@Controller
public class UserController {

    //注入service
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/userList")
    public String userList(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        Page<User> users = userService.getUserList(pageNum, pageSize);

        model.addAttribute("allUser", users);
        return "userList";
    }


    @RequestMapping("/doLogin")  //post提交/login.html
    public String doLogin(@RequestParam String username, @RequestParam String password,
                          @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Model model) {

        User user = userService.checkUser(username, password);

        //判断用户是否为空，不为空则把user放入session中
        if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
            if (user.getGrants().equals("管理员")) {

                Page<User> allUser = userService.getUserList(pageNum, pageSize);

                model.addAttribute("allUser", allUser);

                return "userList";

            } else {
                model.addAttribute("msg", "您的权限级别过低！");
                return "login";
            }
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }
    }


    @RequestMapping("/addUser")
    public String addUser(@RequestParam String username, @RequestParam String password,
                          @RequestParam String name, @RequestParam String grants, Model model) {

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setGrants(grants);

        userService.save(user);

        List<User> allUser = userService.findAllUser();
        model.addAttribute("allUser", allUser);

        return "redirect:/userList";

    }

    @RequestMapping("/deleteUser")
    public String delUser(Integer id, Model model) {

        userService.delUserById(id);
        List<User> allUser = userService.findAllUser();
        model.addAttribute("allUser", allUser);

        return "redirect:/userList";
    }

    @RequestMapping("/editUser")
    public String editUser(@RequestParam String username, @RequestParam String password,
                           @RequestParam String name, @RequestParam String grants,
                           @RequestParam Integer id, Model model) {

        userService.editUser(username, password, name, grants, id);
        List<User> allUser = userService.findAllUser();
        model.addAttribute("allUser", allUser);

        return "redirect:/userList";
    }


}

