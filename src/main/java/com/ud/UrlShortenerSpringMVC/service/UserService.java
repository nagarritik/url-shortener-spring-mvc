package com.ud.UrlShortenerSpringMVC.service;

import com.ud.UrlShortenerSpringMVC.model.User;
import com.ud.UrlShortenerSpringMVC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser(){
        return userRepository.getAllUser();
    }

    public boolean userWithUsername(String username){
        boolean uwu = false;

        List<User> userList = getAllUser();

        for (User item:userList){
            if (Objects.equals(item.getUsername(), username)){
                uwu=true;
                break;
            }
        }

        return uwu;
    }

    public boolean userWithUsername(User user){
        boolean uwu = false;

        List<User> userList = getAllUser();

        for (User item:userList){
            if (Objects.equals(item.getUsername(), user.getUsername())){
                uwu=true;
                break;
            }
        }
        return uwu;
    }

    public boolean userWithEmail(String email){
        boolean uwe = false;

        List<User> userList = getAllUser();

        for (User item:userList){
            if (Objects.equals(item.getEmail(), email)){
                uwe=true;
                break;
            }
        }

        return uwe;
    }

    public boolean userWithEmail(User user){
        boolean uwe = false;

        List<User> userList = getAllUser();

        for (User item:userList){
            if (Objects.equals(item.getEmail(), user.getEmail())){
                uwe=true;
                break;
            }
        }

        return uwe;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User getUserWithEmail(String email){
        User user = null;

        List<User> userList = getAllUser();

        for (User item:userList){
            if (Objects.equals(item.getEmail(), email)){
                user = item;
            }
        }

        return user;
    }

    public boolean authenticateUser(User user) {
        boolean au = false;

        User user1 = getUserWithEmail(user.getEmail());

        if (user1!=null&&Objects.equals(user1.getPassword(), user.getPassword())){
            au=true;
        }

        return  au;
    }
}
