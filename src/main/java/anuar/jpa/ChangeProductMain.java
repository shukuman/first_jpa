package anuar.jpa;

import anuar.jpa.entity.Product;
import anuar.jpa.entity.Value;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class ChangeProductMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите 'id' товара для изменения: ");
        int productId = scan.nextInt();
        scan.nextLine();

        System.out.print("Введите новое название товара: ");
        String productName = scan.nextLine();

        System.out.print("Введите новую стоимость товара: ");
        int productPrice = scan.nextInt();

        scan.nextLine();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Product product = manager.find(Product.class, productId);

        try {
            manager.getTransaction().begin();

            product.setName(productName);
            product.setPrice(productPrice);
            manager.persist(product);

            List<Value> values = product.getValues();

            for(Value value : values) {
                System.out.printf("Введите новую характеристику для '%s':\n", value.getSpecifications().getName());
                String newValueName = scan.nextLine();
                value.setName(newValueName);
                manager.persist(value);
            }

            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

        manager.close();
        factory.close();
    }
}
