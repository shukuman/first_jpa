package anuar.jpa;

import anuar.jpa.entity.Category;
import anuar.jpa.entity.Product;
import anuar.jpa.entity.Specification;
import anuar.jpa.entity.Value;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class CreateNewProduct {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Выберите категорию товара (укажите цифру):\n1. Процессоры\n2. Мониторы\n");
        int categoryId = scan.nextInt();
        scan.nextLine();

        System.out.print("Введите название товара: ");
        String productName = scan.nextLine();

        System.out.print("Введите стоимость товара: ");
        int productPrice = scan.nextInt();
        scan.nextLine();


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Category category = manager.find(Category.class, categoryId);
        List<Specification> specificationsList = category.getSpecifications();

        try {
            manager.getTransaction().begin();

            // Product
            Product product = new Product();
            product.setName(productName);
            product.setCategory(category);
            product.setPrice(productPrice);
            manager.persist(product);

            // Value
            for (int i = 0; i < specificationsList.size(); i++) {
                Value value = new Value();
                System.out.printf("Введите характеристику для '%s':\n", specificationsList.get(i).getName());
                String valueName = scan.nextLine();
                value.setName(valueName);
                value.setSpecifications(specificationsList.get(i));
                value.setProducts(product);
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
