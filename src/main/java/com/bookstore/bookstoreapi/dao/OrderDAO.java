package com.bookstore.bookstoreapi.dao;

import com.bookstore.bookstoreapi.model.Order;
import com.bookstore.bookstoreapi.model.CartItem;
import com.bookstore.bookstoreapi.storage.DataStorage;
import static com.bookstore.bookstoreapi.storage.DataStorage.carts;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class OrderDAO {
       
    
    public void createOrderIfNotExists(int customerId) {
        DataStorage.orders.putIfAbsent(customerId, new ArrayList<>());
    }

    public Order placeOrder(int customerId) {
        
        int orderId = DataStorage.orderIdCounter++;
        createOrderIfNotExists(customerId);
        
        Order order = new Order(orderId, customerId, LocalDate.now(), carts.get(customerId), 7100);
        DataStorage.orders.get(customerId).add(order); 
        
        DataStorage.carts.remove(customerId); // clear cart after order
        return order;
    }

    public List<Order> getOrdersByCustomer(int customerId) {
        return DataStorage.orders.get(customerId);
    }

    public Order getOrderById(int customerId, int orderId) {
        List<Order> customerOrders = DataStorage.orders.get(customerId);
        if (customerOrders != null) {
            for (Order order : customerOrders) {
                if (order.getId() == orderId) {
                    return order;
                }
            }
        }
        return null;
    }
//        public boolean exists(int id) {
//        return DataStorage.books.containsKey(id);
//    }
    
}

