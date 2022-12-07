package org.spring.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.spring.italy.demo.pojo.Drink;
import org.spring.italy.demo.serv.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/drink")
public class DrinkController {
	
	@Autowired
	private DrinkService drinkService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Drink> drinks = drinkService.findAll();
		model.addAttribute("drinks", drinks);
		
		return "drinks";
	}
	
	@GetMapping("/{id}")
	public String getDrinkById(@PathVariable("id") int id, Model model) {
		
		Optional<Drink> drinkOpt = drinkService.findDrinkById(id);
		
		if(drinkOpt.isEmpty()) {
			
			System.out.println("Not found drink with id: " + id);
		}
		Drink drink = drinkOpt.get();
		model.addAttribute("drink", drink);
		
		return "drink";
	}
	
	@GetMapping("/create")
	public String createDrink(Model model) {
		
		Drink drink = new Drink();		
		model.addAttribute("drink", drink);
		
		return "drink-create";
	}
	
	@PostMapping("/create")
	public String storeDrink(@Valid Drink drink, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			
			System.err.println("ERRORE");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/drink/create";
		}
		
		
		
		drinkService.saveDrink(drink);
		
		return "redirect:/drink";
	}
	
	@GetMapping("/update/{id}")
	public String editDrink(@PathVariable("id") int id, Model model) {
		
		Optional<Drink> drinkOpt = drinkService.findDrinkById(id);
		Drink drink = drinkOpt.get();
		
		model.addAttribute("drink", drink);
		
		return "drink-update";
	}
	
	@PostMapping("/store")
	public String updateDrink(@Valid Drink drink, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			
			System.err.println("ERRORE");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/drink/update/" + drink.getId();
		}
		
		drinkService.saveDrink(drink);
		
		return "redirect:/drink";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		Optional<Drink> drinkOpt = drinkService.findDrinkById(id);
		Drink drink = drinkOpt.get();
		
		drinkService.delete(drink);
		
		return "redirect:/drink";
	}	
}