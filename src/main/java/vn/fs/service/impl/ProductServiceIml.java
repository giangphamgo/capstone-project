package vn.fs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fs.repository.ProductRepository;
import vn.fs.service.ProductService;

@Service
public class ProductServiceIml implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public double countProduct() {
        double a= productRepository.countProductSale();
        double b= productRepository.countProduct()/100;
        double phantram= ((b*a)*10);

        System.out.println(b+"88888888888888");
        System.out.println(phantram);
        return phantram;
    }

    @Override
    public int countProductSale() {
        return productRepository.countProductSale();
    }
}
