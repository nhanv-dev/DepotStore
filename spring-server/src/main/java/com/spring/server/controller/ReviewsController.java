package com.spring.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.server.model.dto.ProductReviewsDto;
import com.spring.server.model.entity.User;
import com.spring.server.repository.UserRepo;
import com.spring.server.service.ProductService;
import com.spring.server.service.ReturnPolicyService;
import com.spring.server.service.ReviewsService;


@RestController
@RequestMapping("/api")
public class ReviewsController {
    @Autowired
    ProductService productService;
    @Autowired
    ReviewsService reviewsService;
    @Autowired
    ReturnPolicyService returnPolicyService;
    @Autowired
    UserRepo userRepo;

    @GetMapping("/users/{userId}/reviews")
    public ResponseEntity<?> getReviewsByUserId(
            @PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        Page<ProductReviewsDto> productReviewsDtos = reviewsService.findProductReviewsByUserId(page, size, userId);
        return ResponseEntity.ok(productReviewsDtos);
    }

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<?> getReviewsByProductId(
            @PathVariable Long productId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        Page<ProductReviewsDto> productReviewsDtos = reviewsService.findProductReviewsByProductId(page, size, productId);
        return ResponseEntity.ok(productReviewsDtos);
    }

    @PostMapping("/products/{id}/reviews")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> evaluateProductById(Authentication authentication, @PathVariable Long id, @Valid @RequestBody ProductReviewsDto productReviews) {
        User user = userRepo.findOneByEmail(authentication.getName());
        if (user == null || user.getId() == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        productReviews.setUserId(user.getId());
        reviewsService.evaluateProduct(productReviews);
        return ResponseEntity.ok("Success");
    }
}

