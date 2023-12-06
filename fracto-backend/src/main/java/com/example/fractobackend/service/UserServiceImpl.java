package com.example.fractobackend.service;

import com.example.fractobackend.dao.RoleDao;
import com.example.fractobackend.dao.UserDao;
import com.example.fractobackend.entity.Role;
import com.example.fractobackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setName("admin");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setName("user");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setPassword("admin@pass");
        adminUser.setUserEmail("admin@mail.com");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        user.setPassword(user.getPassword());

        return userDao.save(user);
    }
}
