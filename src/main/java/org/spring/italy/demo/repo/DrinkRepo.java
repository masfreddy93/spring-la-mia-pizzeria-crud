package org.spring.italy.demo.repo;

import org.spring.italy.demo.pojo.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepo extends JpaRepository<Drink, Integer>{

}
