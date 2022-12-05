package org.spring.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.spring.italy.demo.pojo.Pizza;
import org.spring.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@RequestMapping("/")
	public String index(Model model) {
		
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		
		return "index";
	}
	
	
	@GetMapping("/pizza/{id}")
	public String getPizzaById(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		
		if(optPizza.isEmpty()) {
			
			System.out.println("Pizza con id: " + id + " non presente");
		}
		
		Pizza pizza = optPizza.get();
		model.addAttribute("pizza", pizza);
		
		return "pizza";
	}
	
	
	@GetMapping("/pizze/create")
	public String createPizza(Model model) {
		
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		return "pizza-create";
	}
	@PostMapping("/pizze/create")
	public String storePizza(@Valid @ModelAttribute("pizza") Pizza pizza) {
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizze/update/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		
		return "pizza-update";
	}
	@PostMapping("/pizze/store")
	public String updatePizza(@Valid Pizza pizza) {
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizze/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();
		
		pizzaService.delete(pizza);
		
		return "redirect:/";
	}
	
}
