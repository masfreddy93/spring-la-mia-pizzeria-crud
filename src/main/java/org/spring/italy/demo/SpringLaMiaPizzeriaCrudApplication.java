package org.spring.italy.demo;

import java.util.List;

import org.spring.italy.demo.pojo.Pizza;
import org.spring.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		
		//inserimento
		Pizza p1 = new Pizza("Margherita", "La classica delle pizze", 4);
		Pizza p2 = new Pizza("Capricciosa", "Deliziosa come sempre", 7);
		Pizza p3 = new Pizza("Tonno e cipolla", "Attento all'alito", 6);
		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		
		
		//lettura
		List<Pizza> pizze = pizzaService.findAll();
		System.out.println(pizze);
	}

}
