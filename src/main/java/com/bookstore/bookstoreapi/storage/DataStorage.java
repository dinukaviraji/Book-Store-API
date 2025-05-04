package com.bookstore.bookstoreapi.storage;

import com.bookstore.bookstoreapi.model.*;
import java.util.*;


public class DataStorage {
    
    // Stores all authors with their ID as the key
    public static Map<Integer, Author> authors = new HashMap<>();
    
    // Stores all books 
    public static Map<Integer, Book> books = new HashMap<>(); 
    
    // Stores all customers 
    public static Map<Integer, Customer> customers = new HashMap<>();
    
    // Stores shopping cart items for each customer 
    public static Map<Integer, List<CartItem>> carts = new HashMap<>();
    
    // Stores orders for each customer
    public static Map<Integer, List<Order>> orders = new HashMap<>();
    
    public static int bookIdCounter = 1;
    public static int authorIdCounter = 1;
    public static int customerIdCounter = 1;
    public static int orderIdCounter = 1;
}
