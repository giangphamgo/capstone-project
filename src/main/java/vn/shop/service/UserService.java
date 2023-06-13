package vn.shop.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {


    void addUserRole(long userId, long roleId) ;

    double countUserMonth();
    double countUserDay();
}
