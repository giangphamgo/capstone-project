package vn.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.shop.repository.ProductRepository;
import vn.shop.service.ProductService;

@Service
public class ProductServiceIml implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public double countProduct() {
        double a= productRepository.countProductSale();
        double b= productRepository.countProduct();
        double phantram= ((b/a)*100);;

        return phantram;
    }



    @Override
    public int countProductSale() {
        return productRepository.countProductSale();
    }

    @Override
    public double countProductMonthPT() {
        double a= productRepository.countProductMonth();
        double b= productRepository.countProduct();
        double phantram= ((a/b)*100);

        return phantram;
    }

    @Override
    public double countProductMonth() {
        return productRepository.countProductMonth();
    }
}
