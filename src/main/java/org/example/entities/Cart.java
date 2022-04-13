package org.example.entities;

import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class Cart {

    private ArrayList<Product> newCart;

    public Cart() {
        newCart = new ArrayList<>();
    }

    public ArrayList<Product> getCart() {
        return newCart;
    }

    public void addCart(Product product) {
        newCart.add(product);
    }

    public void deleteCart() {
        if (newCart.get(0) != null) {
            newCart.remove(newCart.size() - 1);
        }
    }

    public void clearCart() {
        newCart.clear();
    }
}
