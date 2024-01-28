package anuar.jpa;

import anuar.jpa.entity.Category;
import anuar.jpa.entity.Specification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class CreateCategoryMain {
    public static void main(String[] args) {
        // Введите название категории: Смартфон
        // Введите характеристики категории: Производитель, ОС, Емкость батареи

        // 1) После ввода названия категории в таблице categories должна быть
        // создана новая запись с введённым названием.

        // 2) Создать каждую введённую характеристику как отдельную запись в
        // таблице options которая должна быть привязана к созданной в первом
        // шаге категории.

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите название категории: ");
        String categoryName = scan.nextLine();

        System.out.print("Введите названия характеристик через запятую: ");
        String options = scan.nextLine();
        String[] optionsArray = options.split(", ");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        /*try {
            manager.getTransaction().begin();

            Category category = new Category();
            category.setName("Smartphone");
            manager.persist(category);
            Category smartphone = manager.find(Category.class, 5);

            Specification specManufacturer = new Specification();
            specManufacturer.setName("Manufacturer");
            specManufacturer.setCategory(smartphone);
            manager.persist(specManufacturer);

            Specification specOS = new Specification();
            specOS.setName("Operating system");
            specOS.setCategory(smartphone);
            manager.persist(specOS);

            Specification specBatteryCapacity = new Specification();
            specBatteryCapacity.setName("Battery capacity");
            specBatteryCapacity.setCategory(smartphone);
            manager.persist(specBatteryCapacity);

            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

        manager.close();
        factory.close();
        */

        // String options = "Manufacturer, Operating system, Battery capacity";
        // String[] optionsArray = options.split(", ");
        // System.out.println(optionsArray[0]);
        // System.out.println(optionsArray[1]);
        // System.out.println(optionsArray[2]);

        // Category category = manager.find(Category.class, 5);
        // List<Specification> specificationList = category.getSpecifications();
        // for (Specification specification : specificationList) {
        //     System.out.printf("Category: %s, Spec: %s\n", category.getName(), specification.getName());
        // }

        try {
            manager.getTransaction().begin();

            Category category = new Category();


            category.setName(categoryName);
            manager.persist(category);

            for (int i = 0; i < optionsArray.length; i++) {
                Specification specManufacturer = new Specification();
                specManufacturer.setName(optionsArray[i]);
                specManufacturer.setCategory(category);
                manager.persist(specManufacturer);
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
