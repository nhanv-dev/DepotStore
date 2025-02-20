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

import com.spring.server.model.dto.SalesRegisterDto;
import com.spring.server.model.entity.User;
import com.spring.server.service.SalesRegisterService;
import com.spring.server.service.UserService;


@RestController
@RequestMapping("/api/sales-register")
public class SalesRegisterController {
    @Autowired
    UserService userService;
    @Autowired
    SalesRegisterService salesRegisterService;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllSalesRegister(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "30") Integer size
    ) {
        Page<SalesRegisterDto> salesRegisters = salesRegisterService.findAll(page, size);
        return ResponseEntity.ok(salesRegisters);
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_SHOP','ROLE_ADMIN')")
    public ResponseEntity<?> getSalesRegister(Authentication authentication, @PathVariable Long id) {
        User user = userService.findOneByEmail(authentication.getName());
        if (user == null || !user.getId().equals(id))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        SalesRegisterDto salesRegister = salesRegisterService.findOneByUserId(user.getId());
        return ResponseEntity.ok(salesRegister);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> registerSales(Authentication authentication, @Valid @RequestBody SalesRegisterDto salesRegisterDto) {
        User user = userService.findOneByEmail(authentication.getName());
        salesRegisterDto.setUserId(user.getId());
        SalesRegisterDto salesRegister = salesRegisterService.save(salesRegisterDto);
        return ResponseEntity.ok(salesRegister);
    }

    @PostMapping("/{id}/confirm")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> confirmSalesRegister(@PathVariable Long id) {
        return ResponseEntity.ok(salesRegisterService.confirm(id));
    }

}

