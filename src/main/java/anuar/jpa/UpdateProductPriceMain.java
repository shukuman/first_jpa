package anuar.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.Scanner;

public class UpdateProductPriceMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите название категории: ");
        String categoryName = scan.nextLine();

        System.out.print("Введите процент увеличения стоимости товара данной категории: ");
        int percentToIncrease = scan.nextInt();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();

            Query increaseProductPriceByPercent = manager.createQuery(
                    "update Product p set p.price = p.price + (p.price * ?1 / 100) where p.category.name = ?2"
            );
            increaseProductPriceByPercent.setParameter(1, percentToIncrease);
            increaseProductPriceByPercent.setParameter(2, categoryName);
            increaseProductPriceByPercent.executeUpdate();

            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

        manager.close();
        factory.close();
    }
}
