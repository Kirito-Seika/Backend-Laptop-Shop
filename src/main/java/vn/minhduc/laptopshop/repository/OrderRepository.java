package vn.minhduc.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.minhduc.laptopshop.domain.Order;
import vn.minhduc.laptopshop.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    Optional<Order> findByPaymentRef(String paymentRef);
}
