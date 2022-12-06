package org.spring.italy.demo;

import java.util.List;

import org.spring.italy.demo.pojo.Drink;
import org.spring.italy.demo.pojo.Pizza;
import org.spring.italy.demo.serv.DrinkService;
import org.spring.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DrinkService drinkService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		//PIZZE
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

		
		//DRINK
		//inserimento
		Drink d1 = new Drink("Coca cola", "Stay classic", 3);
		Drink d2 = new Drink("Fanta", "Ci sta sempre", 3);
		Drink d3 = new Drink("Acqua", "Mai banale", 1);
		
		drinkService.saveDrink(d1);
		drinkService.saveDrink(d2);
		drinkService.saveDrink(d3);
		
		
		//lettura
		List<Drink> drinks = drinkService.findAll();
		System.out.println(drinks);
		
	}
}
