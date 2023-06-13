package vn.shop.service;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    double countProduct();
    int countProductSale();

    double countProductMonthPT();

    double countProductMonth();
}
