package com.spring.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.server.model.dto.CartDto;
import com.spring.server.model.dto.CartItemDto;
import com.spring.server.model.entity.User;
import com.spring.server.payload.response.MessageResponse;
import com.spring.server.service.CartService;
import com.spring.server.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getCartByUserId(Authentication authentication) {
        User user = userService.findOneByEmail(authentication.getName());
        if (user == null) {
            System.out.println("false");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Don't have permission to access");
        }
        CartDto cart = cartService.findOneByUserId(user.getId());
        if (cart == null)
            return ResponseEntity.ok(new MessageResponse("Don't have user's cart"));
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/items")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addCartItem(Authentication authentication, @RequestBody CartItemDto cartItemDto) {
        User user = userService.findOneByEmail(authentication.getName());
        if (user == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Don't have user");
        CartItemDto item = cartService.saveCartItem(user, cartItemDto);
        return ResponseEntity.ok(new MessageResponse("Added cart item", item));
    }

    @PutMapping("/items/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updateCartItem(Authentication authentication, @RequestBody CartItemDto cartItemDto) {
        User user = userService.findOneByEmail(authentication.getName());
        if (user == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Don't have user");
        if (cartItemDto.getVariant() != null && cartItemDto.getQuantity() > cartItemDto.getVariant().getQuantity())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantity of item larger than quantity of variant");
        if (cartItemDto.getProduct() != null && cartItemDto.getQuantity() > cartItemDto.getProduct().getQuantity())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantity of item larger than quantity of product");
        cartService.updateCartItem(cartItemDto);
        return ResponseEntity.ok(new MessageResponse("Updated cart item"));
    }

    @DeleteMapping("/items/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> removeCartItem(Authentication authentication, @PathVariable Long id) {
        User user = userService.findOneByEmail(authentication.getName());
        if (user == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Don't have user");
        cartService.removeCartItem(id);
        return ResponseEntity.ok(new MessageResponse("Removed cart item"));
    }
}

