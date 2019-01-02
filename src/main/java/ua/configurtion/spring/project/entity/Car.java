package ua.configurtion.spring.project.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car extends BaseEntity {

	private String make;
	private String model;
	private String color;
	@Column(columnDefinition = "DECIMAL(9,2)")
	private BigDecimal price;
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Car(String make, String model, String color, BigDecimal price) {
		super();
		this.make = make;
		this.model = model;
		this.color = color;
		this.price = price;
	}
	public Car() {
		super();
	}
	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", color=" + color + ", price=" + price + ", getId()="
				+ getId() + "]";
	}
	
	
	
}
