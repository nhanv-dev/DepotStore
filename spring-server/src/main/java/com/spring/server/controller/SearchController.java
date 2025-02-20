package com.spring.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.server.model.dto.ProductDto;
import com.spring.server.model.dto.ShopDto;
import com.spring.server.service.ProductService;
import com.spring.server.service.ShopService;


@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;

    @GetMapping("/products")
    public ResponseEntity<?> searchProducts(@RequestParam String search,
                                            @RequestParam(required = false, defaultValue = "0") Integer page,
                                            @RequestParam(required = false, defaultValue = "30") Integer size) {
        Page<ProductDto> products = productService.searchProducts(page, size, search);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/shops")
    public ResponseEntity<?> searchShops(@RequestParam("search") String search,
                                         @RequestParam(required = false, defaultValue = "0") Integer page,
                                         @RequestParam(required = false, defaultValue = "5") Integer size) {
        Page<ShopDto> shops = shopService.searchShop(page, size, search);
        return ResponseEntity.ok(shops);
    }


}
