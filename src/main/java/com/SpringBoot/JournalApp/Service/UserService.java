package com.SpringBoot.JournalApp.Service;

import com.SpringBoot.JournalApp.entry.User;
import com.SpringBoot.JournalApp.repositor.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User saveNewUser(User user) {
        logger.info("Attempting to save new user: {}", user.getUserName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        User savedUser = userRepository.save(user);
        logger.debug("New user saved successfully: {}", savedUser);
        return savedUser;
    }

    public User SaveUser(User user) {
        logger.info("Saving user without password encoding: {}", user.getUserName());
        User saved = userRepository.save(user);
        logger.debug("User saved: {}", saved);
        return saved;
    }

    public void saveAdmin(User user) {
        logger.info("Saving admin user: {}", user.getUserName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
        logger.debug("Admin user saved successfully.");
    }

    public List<User> getAll() {
        logger.info("Retrieving all users from repository.");
        List<User> users = userRepository.findAll();
        logger.debug("Total users retrieved: {}", users.size());
        return users;
    }

    public Optional<User> findById(ObjectId id) {
        logger.info("Finding user by ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        logger.debug("User found: {}", user.isPresent());
        return user;
    }

    public User findByUserName(String userName) {
        logger.info("Finding user by username: {}", userName);
        User user = userRepository.findByUserName(userName);
        logger.debug("User retrieved: {}", user);
        return user;
    }

    public void deleteById(ObjectId id) {
        logger.warn("Deleting user by ID: {}", id);
        userRepository.deleteById(id);
        logger.debug("User with ID {} deleted.", id);
    }

    @Transactional
    public void deleteByUserName(String userName) {
        logger.warn("Deleting user by username: {}", userName);
        userRepository.deleteByUserName(userName);
        logger.debug("User with username {} deleted.", userName);
    }
}