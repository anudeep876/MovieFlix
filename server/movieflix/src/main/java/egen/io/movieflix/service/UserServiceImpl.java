package egen.io.movieflix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import egen.io.movieflix.entity.User;
import egen.io.movieflix.exception.UserAlreadyExistException;
import egen.io.movieflix.exception.UserNotFoundException;
import egen.io.movieflix.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(String userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    @Transactional
    @Override
    public User create(User user) {
        User existing = userRepository.findByEmail(user.getUserEmail());
        if (existing != null) {
            throw new UserAlreadyExistException("User already exists");
        }
        return userRepository.create(user);
    }

    @Transactional
    @Override
    public User update(String userId, User user) {
        User existing = userRepository.findOne(userId);
        if (existing == null) {
            throw new UserNotFoundException("User not found");
        }
        return userRepository.update(user);
    }

    @Transactional
    @Override
    public void remove(String userId) {
        User existing = userRepository.findOne(userId);
        if (existing == null) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.delete(existing);
    }

    @Validated
    public Boolean validateUser(User user) {

        String userEmail_Pattern = " ^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String userPassword_Pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

        if (!user.getUserEmail().matches(userEmail_Pattern)) {
            return false;
        } else if (user.getUserName().isEmpty()) {
            return false;
        } else if (!user.getPassword().matches(userPassword_Pattern)) {
            return false;
        } else if (user.getPassword().length() < 8 || user.getPassword().length() > 20) {
            return false;
        }
        return true;
    }
}
