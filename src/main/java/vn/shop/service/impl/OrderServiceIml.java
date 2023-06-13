package vn.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.shop.repository.OrderRepository;
import vn.shop.service.OrderService;

@Service
public class OrderServiceIml implements OrderService {
    @Autowired
    OrderRepository orderRepository;



    @Override
    public double countOder() {
        double a= orderRepository.couldOderMonth();
        double b= orderRepository.couldOderAll();
        double phantram= ((b/a)*100);
        return phantram;
    }

    @Override
    public double countOderCXN() {
        double a= orderRepository.couldOderCXNMonth();
        double b= orderRepository.couldOderAll();
        double phantram= ((b/a)*100);
        return phantram;
    }

    @Override
    public double countOderDGH() {
        double a= orderRepository.couldOderDGHMonth();
        double b= orderRepository.couldOderAll();
        double phantram= ((b/a)*100);
        return phantram;
    }

    @Override
    public double countOderDTT() {
        double a= orderRepository.couldOderDTTMonth();
        double b= orderRepository.couldOderAll()/100;
        double phantram= ((b*a)*10);
        return phantram;
    }

    @Override
    public int countOderMonth() {
        return orderRepository.couldOderMonth();
    }
    @Override
    public int countOderCXNMonth() {
        return orderRepository.couldOderCXNMonth();
    }
    @Override
    public int countOderDGHMonth() {return orderRepository.couldOderDGHMonth();}
    @Override
    public int countOderDTTMonth() {return orderRepository.couldOderDTTMonth();}

}
