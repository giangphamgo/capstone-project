package vn.fs.service;

import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    double countOder();
    double countOderCXN();
    double countOderDGH();
    double countOderDTT();

    int countOderMonth();

    int countOderCXNMonth();

    int countOderDGHMonth();

    int countOderDTTMonth();
}
