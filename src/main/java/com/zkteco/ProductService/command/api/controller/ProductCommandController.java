package com.zkteco.ProductService.command.api.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.ProductService.command.api.commands.CreateProductCommand;
import com.zkteco.ProductService.command.api.model.ProductRestModel;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

	private CommandGateway commandGateway;
		
	public ProductCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping
	public String addProduct(@RequestBody ProductRestModel productRestModel) {
		CreateProductCommand createProductCommnd = CreateProductCommand.builder()
				.productId(UUID.randomUUID().toString())
				.name(productRestModel.getName())
				.price(productRestModel.getPrice())
				.quatity(productRestModel.getQuantity())
				.build();
		String result = commandGateway.sendAndWait(createProductCommnd);
		return result;
	}
	
	
}
