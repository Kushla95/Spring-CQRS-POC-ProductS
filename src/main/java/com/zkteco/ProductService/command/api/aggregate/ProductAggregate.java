package com.zkteco.ProductService.command.api.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.zkteco.ProductService.command.api.commands.CreateProductCommand;
import com.zkteco.ProductService.command.api.events.ProductCreatedEvent;

@Aggregate
public class ProductAggregate {
	
	@AggregateIdentifier
	private String productId;
	private String name;
	private BigDecimal price;
	private Integer quantity;

	
	@CommandHandler
	public ProductAggregate(CreateProductCommand createProductCommand) {
		// you can perform all the validations over here
		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
		
		BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
		
		AggregateLifecycle.apply(productCreatedEvent);
	}
	
	public ProductAggregate() {
		
	}
	
	
	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		this.quantity=productCreatedEvent.getQuatity();
		this.productId=productCreatedEvent.getProductId();
		this.price=productCreatedEvent.getPrice();
		this.name=productCreatedEvent.getName();
	}
}
