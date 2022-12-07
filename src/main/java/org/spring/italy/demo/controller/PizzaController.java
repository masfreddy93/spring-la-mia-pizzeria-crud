package org.spring.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.spring.italy.demo.pojo.Pizza;
import org.spring.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
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
	public String storePizza(@Valid Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			
			System.err.println("ERRORE");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			
			return "redirect:/pizze/create";
		}
		
		
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
	public String updatePizza(@Valid Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			
			System.err.println("ERRORE");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			
			return "redirect:/pizze/update/" + pizza.getId();
		}
		
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
	
	@GetMapping("pizza/search")
	public String getSearchDrinkByName(Model model, @RequestParam(name = "q", required = false) String query) {
		
		List<Pizza> pizze = null;
		if (query == null) {
			
			pizze = pizzaService.findAll();
			
		} else {
			
			pizze = pizzaService.findByName(query);
		}
		
		
		model.addAttribute("pizze", pizze);
		model.addAttribute("query", query);
		
		
		return "pizza-search";
	}
	
}
