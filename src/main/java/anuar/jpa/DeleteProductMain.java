package anuar.jpa;

import anuar.jpa.entity.Product;
import anuar.jpa.entity.Value;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class DeleteProductMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите 'id' товара для удаления: ");
        int productId = scan.nextInt();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Product product = manager.find(Product.class, productId);

        try {
            manager.getTransaction().begin();

            List<Value> values = product.getValues();

            for(Value value : values) {
                manager.remove(value);
            }

            manager.remove(product);
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

        manager.close();
        factory.close();
    }
}
