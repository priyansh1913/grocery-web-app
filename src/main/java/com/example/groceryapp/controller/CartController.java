package com.example.groceryapp.controller;

import com.example.groceryapp.model.Cart;
import com.example.groceryapp.model.User;
import com.example.groceryapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String viewCart(@AuthenticationPrincipal User user, Model model) {
        Cart cart = cartService.getOrCreateCart(user);
        model.addAttribute("cart", cart);
        return "cart/view";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1") Integer quantity) {
        cartService.addToCart(user, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update/{productId}")
    public String updateCartItem(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId,
            @RequestParam Integer quantity) {
        cartService.updateCartItem(user, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId) {
        cartService.removeFromCart(user, productId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(@AuthenticationPrincipal User user) {
        cartService.clearCart(user);
        return "redirect:/cart";
    }
}
