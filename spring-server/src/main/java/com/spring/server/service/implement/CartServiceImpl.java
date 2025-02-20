package com.spring.server.service.implement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.server.model.dto.CartDto;
import com.spring.server.model.dto.CartItemDto;
import com.spring.server.model.entity.Cart;
import com.spring.server.model.entity.CartItem;
import com.spring.server.model.entity.Product;
import com.spring.server.model.entity.ProductVariant;
import com.spring.server.model.entity.User;
import com.spring.server.model.mapper.CartItemMapper;
import com.spring.server.model.mapper.CartMapper;
import com.spring.server.repository.CartItemRepo;
import com.spring.server.repository.CartRepo;
import com.spring.server.repository.ProductRepo;
import com.spring.server.repository.ProductVariantRepo;
import com.spring.server.repository.ShopRepo;
import com.spring.server.service.CartService;

@Component
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    private ShopRepo shopRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductVariantRepo productVariantRepo;

    @Override
    @Transactional
    public CartDto findOneByUserId(Long userId) {
        Cart cart = cartRepo.findOneByUser_Id(userId);
        if (cart != null && cart.getItems() != null && !cart.getItems().isEmpty()) {
            Set<CartItem> items = cart.getItems();
            for (CartItem item : new ArrayList<>(items)) {
                if (item.getProduct() != null && (item.getProduct().getIsDeleted() || !item.getProduct().getIsPublic()))
                    items.remove(item);
                else if (item.getVariant() != null && item.getVariant().isDeleted())
                    items.remove(item);
            }
            cart.setItems(items);
            cart = cartRepo.save(cart);
        }
        return CartMapper.toDto(cart);
    }

    @Override
    public CartDto findByUserIdAndShopId(Long userId, Long shopId) {
        return null;
    }

    @Override
    @Transactional
    public CartDto save(CartDto cartDto) {
        return null;
    }

    @Override
    @Transactional
    public CartItemDto saveCartItem(User user, CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        Product product = productRepo.findOneByIdAndIsPublicAndIsDeleted(cartItemDto.getProductId(), true, false);
        ProductVariant productVariant = productVariantRepo.findOneById(cartItemDto.getVariantId());
        Cart cart = cartRepo.findOneByUser_Id(user.getId());
        if (cart == null) cart = cartRepo.save(new Cart(user, new HashSet<>()));
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setProduct(product);
        cartItem.setVariant(productVariant);
        cartItem.setCart(cart);
        if (cart.containItem(cartItem)) cartItem = cart.updateItem(cartItem);
        else cartItem = cartItemRepo.save(cartItem);
        cartRepo.save(cart);
        return CartItemMapper.toDto(cartItem);
    }

    @Override
    @Transactional
    public void updateCartItem(CartItemDto cartItemDto) {
        cartItemRepo.updateQuantity(cartItemDto.getId(), cartItemDto.getQuantity());
    }

    @Override
    @Transactional
    public void removeCartItem(Long id) {
        cartItemRepo.deleteById(id);
    }

}
