package com.bookstore.bookstoreapi.dao;

import com.bookstore.bookstoreapi.model.CartItem;
import com.bookstore.bookstoreapi.storage.DataStorage;
import java.util.ArrayList;
import java.util.List;


public class CartDAO {
       
    public List<CartItem> getCart(int customerId) {
        return DataStorage.carts.get(customerId);
    }
       
    public void createCartIfNotExists(int customerId) {
        DataStorage.carts.putIfAbsent(customerId, new ArrayList<>());
    }

    public void addItemToCart(int customerId, CartItem item) {
        createCartIfNotExists(customerId);
        
        DataStorage.carts.get(customerId).add(item);
    }
    
    public void updateCartItem(int customerId, CartItem updatedItem) {
        List<CartItem> cart = DataStorage.carts.get(customerId);
        if (cart != null) {
            for (CartItem item : cart) {
                if (item.getBookId() == updatedItem.getBookId()) {
                    item.setQuantity(updatedItem.getQuantity());
                    return;
                }
            }
        }
    }

    public void removeItemFromCart(int customerId, int bookId) {
        List<CartItem> cart = DataStorage.carts.get(customerId);
        if (cart != null) {
            cart.removeIf(item -> item.getBookId() == bookId);
        }
    }
    
    public boolean exists(int customerId) {
        return DataStorage.carts.containsKey(customerId);
    }

}
