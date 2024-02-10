package anuar.jpa;

import anuar.jpa.entity.Category;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CreateCategoryMain2 {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Category category = new Category();

        Scanner scan = new Scanner(System.in);
        System.out.print("Введите название категории: ");
        String categoryName = scan.nextLine();

        while (true) {
            TypedQuery<Long> categoriesQuery = manager.createQuery(
                    "select count(c.id) from Category c where c.name = ?1", Long.class
            );
            categoriesQuery.setParameter(1, categoryName);
            long nameCount = categoriesQuery.getSingleResult();

            if (nameCount > 0) {
                System.out.println("Такая категория уже существует, введите новую категорию: ");
                categoryName = scan.nextLine();
            } else {
                category.setName(categoryName);
                break;
            }
        }

        try {
            manager.getTransaction().begin();
            manager.persist(category);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        manager.close();
        factory.close();
    }
}
