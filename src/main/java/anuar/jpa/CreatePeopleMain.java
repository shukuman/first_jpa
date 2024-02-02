package anuar.jpa;

import anuar.jpa.entity.City;
import anuar.jpa.entity.People;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class CreatePeopleMain {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Select city of residence (enter a number):\n1. Astana\n2. Almaty\n3. London\n");
        int cityId = scan.nextInt();
        scan.nextLine();

        System.out.print("Enter your name: ");
        String personName = scan.nextLine();

        scan.close();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        City city = manager.find(City.class, cityId);

        try {
            manager.getTransaction().begin();

            People person = new People();
            person.setName(personName);
            person.setCity(city);

            manager.persist(person);
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
        manager.close();
        factory.close();
    }
}