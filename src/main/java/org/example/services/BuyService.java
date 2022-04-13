package org.example.services;

import org.example.entities.Cart;
import org.example.entities.Product;
import org.example.entities.ProductRepository;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class BuyService {
    ProductRepository productRepository;
    Cart cart;

    public BuyService(ProductRepository productRepository, Cart cart) {
        this.productRepository = productRepository;
        this.cart = cart;
    }

    @PostConstruct
    public void start() throws IOException {
        System.out.println("Список товаров: ");
        for (int i = 0; i < productRepository.getProducts().size(); i++) {
            System.out.println("№ " + productRepository.getProducts().get(i).getId() + " " + productRepository.getProducts().get(i).getName() + " цена: " + productRepository.getProducts().get(i).getCost());
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1-5 - добавить соответствующий товар в корзину");
        System.out.println("6 - удалить последний добавленный товар из корзины");
        System.out.println("7 - очистить корзину");
        while (true) {
            String i = in.readLine();
            if ((Integer.parseInt(i) > 0) & (Integer.parseInt(i) < 8)) {
                if (Integer.parseInt(i) < 6) {
                    cart.addCart(productRepository.getProducts().get(Integer.parseInt(i) - 1));
                } else if (Integer.parseInt(i) == 6) {
                    cart.deleteCart();
                } else if (Integer.parseInt(i) == 7) {
                    cart.clearCart();
                }
                double sum = 0;
                System.out.print("Корзина: ");
                for (Product product : cart.getCart()) {
                    System.out.print(product.getName() + " ");
                    sum = sum + product.getCost();
                }
                System.out.println();
                System.out.println("Итого: " + sum);

            } else System.out.println("Введите команду");
        }
    }
}
