package com.zkteco.ProductService.query.api.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.ProductService.command.api.model.ProductRestModel;
import com.zkteco.ProductService.query.api.queries.GetProductsQuery;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

	private QueryGateway queryGateway;

	public ProductQueryController(QueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}

	@GetMapping
	public List<ProductRestModel> getALllProducts() {
		GetProductsQuery getProductsQuery = new GetProductsQuery();

		List<ProductRestModel> productRestModel = queryGateway
				.query(getProductsQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
		return productRestModel;
	}
}
