package com.repen.cardatabase.domain;

import jakarta.persistence.*;


@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String brand, model, color, registerNumber;
	private int whatYear;
	private int	price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")
	private Owner owner;

	public Car() {}

	public Car(String brand, String model, String color, String registerNumber, int whatYear, int price, Owner owner) {
		super();
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.registerNumber = registerNumber;
		this.whatYear = whatYear;
		this.price = price;
		this.owner = owner;
	}

	//		Getters and Setters
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getBrand() {
		return brand;
	};
	public void setBrand(String brand) {
		this.brand = brand;
	};

	public String getModel() {
		return model;
	};
	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public int getYear() {
		return whatYear;
	}
	public void setYear(int whatYear) {
		this.whatYear = whatYear;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
