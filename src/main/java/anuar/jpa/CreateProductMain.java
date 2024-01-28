package anuar.jpa;

import anuar.jpa.entity.Category;
import anuar.jpa.entity.Product;
import anuar.jpa.entity.Specification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class CreateProductMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите категорию товара: ");
        String categoryName = scan.nextLine();

        System.out.print("Введите название товара: ");
        String productName = scan.nextLine();

        System.out.print("Введите стоимость товара: ");
        int productPrice = scan.nextInt();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();

            Category category = new Category();
            category.setName(categoryName);
            manager.persist(category);

            Product product = new Product();
            product.setName(productName);
            product.setCategory(category);
            product.setPrice(productPrice);
            manager.persist(product);

            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

        manager.close();
        factory.close();
    }
}
