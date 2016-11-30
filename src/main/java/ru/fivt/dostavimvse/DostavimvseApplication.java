package ru.fivt.dostavimvse;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DostavimvseApplication {

	public static void main(String[] args) {
		final Session session = HibernateSessionFactory.getSessionFactory().openSession();
		SpringApplication.run(DostavimvseApplication.class, args);
	}
}
