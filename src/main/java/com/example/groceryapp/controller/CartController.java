package com.example.groceryapp.controller;

import com.example.groceryapp.model.Cart;
import com.example.groceryapp.model.User;
import com.example.groceryapp.service.CartService;
import com.example.groceryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewCart(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        Cart cart = cartService.getCart(user);
        model.addAttribute("cart", cart);
        return "cart/view";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@AuthenticationPrincipal UserDetails userDetails,
                          @PathVariable Long productId,
                          @RequestParam(defaultValue = "1") int quantity) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        cartService.addToCart(user, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update/{productId}")
    public String updateCartItem(@AuthenticationPrincipal UserDetails userDetails,
                               @PathVariable Long productId,
                               @RequestParam int quantity) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        cartService.updateCartItemQuantity(user, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@AuthenticationPrincipal UserDetails userDetails,
                               @PathVariable Long productId) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        cartService.removeFromCart(user, productId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        cartService.clearCart(user);
        return "redirect:/cart";
    }
}
