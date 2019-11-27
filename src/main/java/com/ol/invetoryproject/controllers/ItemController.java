package com.ol.invetoryproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.invetoryproject.dao.ItemRepository;
import com.ol.invetoryproject.exception.ResourceNotFoundException;
import com.ol.invetoryproject.model.Item;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="Employee Management System")
@RestController
@RequestMapping("/api")
public class ItemController 
{
	@Autowired
	private ItemRepository repo;
	

	@ApiOperation(value = "List of the inventory items list (item no, name, amount, inventory code)")
	@ApiResponses(value= {
			@ApiResponse(code= 200, message = "Successfully retrieved list of Items from stock")
	})
	@GetMapping("/items")
	public List<Item> getItems()
	{
		return repo.findAll();
	}
	
	@ApiOperation(value="Getting item details (by item no)")
	@ApiResponses(value= {
			@ApiResponse(code= 200, message = "Successfully retrieved the item with the given id"),
			@ApiResponse(code = 404, message = "item with that given id not found")
	})
	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItem(@PathVariable("id") int id) throws ResourceNotFoundException
	{
		Optional<Item> item = repo.findById(id);
		if(!item.isPresent())
		{
			throw new ResourceNotFoundException("item with that given id = " + id + " not found");
		}
		
		return new ResponseEntity<Item>(item.get(), HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Creating and adding new item to stock")
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Successfully creating new item and adding to the stock"),
	})
	@PostMapping("/items")
	public ResponseEntity<Item> createItem(@RequestBody Item item) 
	{
		Item itemToSave = new Item();
		item.setName(item.getName());
		itemToSave.setAmount(item.getAmount());
		itemToSave.setInvetoryCode(item.getInvetoryCode());
		return new ResponseEntity<Item>(repo.save(itemToSave), HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Updating item in stock")
	@ApiResponses(value= {
			@ApiResponse(code= 200, message = "Successfully updating the item with the given id in the stock"),
			@ApiResponse(code = 404, message = "item with that given id not found")
	})
	@PutMapping("/items/{id}") 
	public ResponseEntity<Item> updateItem(@PathVariable("id") int id, @RequestBody Item updatedItem) throws ResourceNotFoundException
	{
		Optional<Item> item = repo.findById(id);
		if(!item.isPresent())
		{
			throw new ResourceNotFoundException("item with that given id = " + id + " not found");
		}
		
		item.get().setName(updatedItem.getName());
		item.get().setAmount(updatedItem.getAmount());
		item.get().setInvetoryCode(updatedItem.getInvetoryCode());
		
		return new ResponseEntity<Item>(repo.save(item.get()), HttpStatus.OK);
	}
	
	@ApiResponses(value= {
			@ApiResponse(code= 200, message = "Successfully deleting the item with the given id from the stock"),
			@ApiResponse(code = 404, message = "item with that given id not found")
	})
	@ApiOperation(value="Deleting Item from the stock")
	@DeleteMapping("/items/{id}")
	public void deleteItem(@PathVariable("id") int id) throws ResourceNotFoundException
	{
		Optional<Item> item = repo.findById(id);
		if(!item.isPresent())
		{
			throw new ResourceNotFoundException("item with that given id = " + id + " not found");
		}
		
		repo.delete(item.get());
	}
	
	@ApiOperation(value="Getting the withdrawal quantity of a specific item from stock")
	@ApiResponses(value= {
			@ApiResponse(code= 200, message = "Successfully retrieved the item's amount with the given id"),
			@ApiResponse(code = 404, message = "item with that given id not found")
	})
	@GetMapping("/items/{id}/amount")
	public int getAmount(@PathVariable("id") int id) throws ResourceNotFoundException
	{
		Optional<Item> item = repo.findById(id);
		if(!item.isPresent())
		{
			throw new ResourceNotFoundException("item with that given id = " + id + " not found");
		}
		
		return item.get().getAmount();
	}
	
	@ApiOperation(value="Depositing quantity of a specific item to stock")
	@ApiResponses(value= {
			@ApiResponse(code= 200, message = "Successfully depositing quantity to the item's amount with the given id"),
			@ApiResponse(code = 404, message = "item with that given id not found")
	})
	@PutMapping("/items/{id}/amount") 
	public ResponseEntity<Item> setAmount(@PathVariable("id") int id, @RequestBody Item updatedItem) throws ResourceNotFoundException 
	{
		Optional<Item> item = repo.findById(id);
		if(!item.isPresent())
		{
			throw new ResourceNotFoundException("item with that given id = " + id + " not found");
		}
		int currentAmount = item.get().getAmount();
		item.get().setAmount(currentAmount + updatedItem.getAmount());
		
		return new ResponseEntity<Item>(repo.save(item.get()), HttpStatus.OK);
	}

}
