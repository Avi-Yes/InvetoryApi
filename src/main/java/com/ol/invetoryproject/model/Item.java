package com.ol.invetoryproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "Item Properties")
@Entity
public class Item 
{
	@ApiModelProperty(notes = "The Item Number, generated by the DB")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int itemNumber;
	
	@ApiModelProperty(notes = "The Item name")
	@Column(name = "name")
	private String name;
	
	@ApiModelProperty(notes = "The Item amount")
	@Column(name = "amount")
	private int amount;
	
	@ApiModelProperty(notes = "The Item invetory code")
	@Column(name = "invetory_code")
	private String invetoryCode;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getInvetoryCode() {
		return invetoryCode;
	}
	public void setInvetoryCode(String invetoryCode) {
		this.invetoryCode = invetoryCode;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	
}