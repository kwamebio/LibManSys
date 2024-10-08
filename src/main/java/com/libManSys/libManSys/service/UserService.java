package com.libManSys.libManSys.service;

import java.util.List;
import com.libManSys.libManSys.model.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    
}
