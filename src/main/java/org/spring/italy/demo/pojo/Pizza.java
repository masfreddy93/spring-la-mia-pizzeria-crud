package org.spring.italy.demo.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name", nullable=false, unique = true)
	@NotEmpty(message="name cannot be null")
	private String name;
	
	@Column
	@Lob
	private String description;
	
	@Column
	@NotNull(message = "Price cannot be null")
	@Min(value = 1, message = "Price must be higher than 0")
	private Integer price;
	
	public Pizza() {}
	
	public Pizza(String name, String description, int price) {
		
		setName(name);
		setDescription(description);
		setPrice(price);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return "\n" + getId() + " - " + getName()
				+ "\n" + getDescription()
				+ "\n" + getPrice() + "€\n";
	}
	
}
