package com.libManSys.libManSys.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.libManSys.libManSys.model.User;
import com.libManSys.libManSys.repository.UserRepository;
import com.libManSys.libManSys.service.UserService;

@Service
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    
}
