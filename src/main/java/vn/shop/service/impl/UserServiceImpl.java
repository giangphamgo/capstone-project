package vn.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.shop.repository.ProductRepository;
import vn.shop.repository.UserRepository;
import vn.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired ProductRepository productRepository;

    @Override
    public void addUserRole(long userId, long roleId) {
        userRepository.addUserRole(userId,roleId);
    }

    @Override
    public double countUserMonth() {
        double a= productRepository.countProduct();
        return a;
    }

    @Override
    public double countUserDay() {
        double a= userRepository.countUserDay();
        double b= userRepository.countUserAll();

        double phantram= ((b/a)*100);;

        return phantram;
    }

}
