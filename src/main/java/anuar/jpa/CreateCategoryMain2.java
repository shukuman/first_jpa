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

        Scanner scan = new Scanner(System.in);

        /*
        TypedQuery<Category> categoriesQuery = manager.createQuery(
                "select c from Category c", Category.class
        );
        List<Category> categories = categoriesQuery.getResultList();

        List<String> categoriesName = new ArrayList<>();
        for (Category c : categories) {
            categoriesName.add(c.getName());
        }
        */

        while (true) {
            System.out.print("Введите название категории: ");
            String categoryName = scan.nextLine();
            TypedQuery<Long> categoriesQuery = manager.createQuery(
                    "select count(c.id) from Category c where c.name = ?1", Long.class
            );
            categoriesQuery.setParameter(1, categoryName);
            long nameCount = categoriesQuery.getSingleResult();

            if (nameCount > 0) {
                System.out.println("Такая категория уже существует, введите новую категорию: ");
            } else {
                break;
            }
        }

        Category category = new Category();
        /*
        while (true) {
            if (categoriesName.contains(categoryName)) {
                System.out.print("Такая категория уже существует, введите название категории: ");
                categoryName = scan.nextLine();
            } else {
                category.setName(categoryName);
                break;
            }
        }
        */

        /*
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

         */
    }
}
