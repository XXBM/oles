package com.oles.service;

import com.oles.domain.User;
import com.oles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService extends BasicService<User,Long> {
    @Autowired
    UserRepository userRepository;

    public User findByUsername(String username){
        return this.userRepository.findByUserName(username);
    }
    public User findById(Long id){
        return userRepository.findOne(id);
    }

    public  User addUser(User user){
        return this.userRepository.save(user);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}