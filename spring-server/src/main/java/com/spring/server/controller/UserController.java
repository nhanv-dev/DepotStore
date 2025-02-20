package com.spring.server.controller;

import com.spring.server.model.dto.UserAddressDto;
import com.spring.server.model.dto.UserDto;
import com.spring.server.model.entity.User;
import com.spring.server.payload.response.MessageResponse;
import com.spring.server.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping("/user-profile/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) {
        User currentUser = userService.findById(id);
        currentUser.setName(userDto.getName());
        currentUser.setPhoneNumber(userDto.getPhoneNumber());
        userService.update(currentUser);
        return ResponseEntity.ok(new MessageResponse("User saved successfully."));
    }

    @GetMapping("/{id}/addresses")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getAddressByUserId(@PathVariable(value = "id") Long id) {
        Set<UserAddressDto> address = userService.findAddressByUserId(id);
        if (address.size() == 0) return ResponseEntity.ok(new MessageResponse("User address is empty."));
        return ResponseEntity.ok(address);
    }

    @PostMapping("/{id}/addresses")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> saveAddress(
            @PathVariable("id") Long id,
            Authentication authentication,
            @Valid @RequestBody UserAddressDto userAddressDto
    ) {
        User user = userService.findOneByEmail(authentication.getName());
        if (!Objects.equals(user.getId(), id))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("User saved failed."));
        userAddressDto.setUserId(id);
        userAddressDto = userService.saveAddress(userAddressDto);
        return ResponseEntity.ok(userAddressDto);
    }

    @PutMapping("/{id}/addresses/{addressId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updateAddress(Authentication authentication,
                                           @PathVariable Long id,
                                           @PathVariable Long addressId,
                                           @RequestBody UserAddressDto userAddressDto
    ) {
        User user = userService.findOneByEmail(authentication.getName());
        if (!Objects.equals(user.getId(), id))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("User saved failed."));
        UserAddressDto address = userService.findAddressById(addressId);
        if (address == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Don't find user address."));
        userAddressDto.setUserId(id);
        userAddressDto = userService.updateAddress(userAddressDto);
        return ResponseEntity.ok(userAddressDto);
    }

    @DeleteMapping("/{id}/addresses/{addressId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteAddress(
            Authentication authentication,
            @PathVariable Long id,
            @PathVariable Long addressId
    ) {
        User user = userService.findOneByEmail(authentication.getName());
        if (!Objects.equals(user.getId(), id))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("User don't have permission."));
        userService.deleteAddress(addressId);
        return ResponseEntity.ok(new MessageResponse("Deleted user address."));
    }
}

