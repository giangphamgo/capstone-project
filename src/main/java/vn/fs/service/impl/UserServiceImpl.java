package vn.fs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fs.entities.User;
import vn.fs.repository.UserRepository;
import vn.fs.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUserRole(long userId, long roleId) {
        userRepository.addUserRole(userId,roleId);
    }

    @Override
    public double countUserMonth() {
        double a= userRepository.countUserMonth();
        double b= userRepository.countUserAll()/100;

        double phantram= ((b*a)*100);

        return phantram;
    }

    @Override
    public double countUserDay() {
        double a= userRepository.countUserDay();
        double b= userRepository.countUserAll()/100;

        double phantram= ((b*a)*100);

        return phantram;
    }

}
