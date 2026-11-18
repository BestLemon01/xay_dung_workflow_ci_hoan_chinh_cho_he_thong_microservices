package com.quickbite.order;

import com.quickbite.order.entity.Order;
import com.quickbite.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceApplicationTests {
    @Autowired(required = false)
    private OrderRepository orderRepository;

    @Test
    void contextLoads() {
        assertNotNull(orderRepository, "Repository bean injected successfully");
    }

    @Test
    void testCreateAndFindOrderInPostgresService() {
        Order order = Order.builder()
                .orderCode("ORD-CI-9901")
                .customerId(5001L)
                .totalAmount(new BigDecimal("249.50"))
                .status("CONFIRMED")
                .build();

        Order saved = orderRepository.save(order);
        assertNotNull(saved.getId(), "Generated ID should not be null in PostgreSQL");
        assertEquals("ORD-CI-9901", saved.getOrderCode());
    }
}
