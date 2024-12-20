package com.example.groceryapp.controller;

import com.example.groceryapp.model.Cart;
import com.example.groceryapp.model.Order;
import com.example.groceryapp.model.User;
import com.example.groceryapp.service.CartService;
import com.example.groceryapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String showCheckoutForm(@AuthenticationPrincipal User user, Model model) {
        Cart cart = cartService.getOrCreateCart(user);
        if (cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("cart", cart);
        return "checkout/form";
    }

    @PostMapping
    public String processCheckout(
            @AuthenticationPrincipal User user,
            @RequestParam String shippingAddress,
            @RequestParam String phoneNumber) {
        
        Order order = orderService.createOrder(user, shippingAddress, phoneNumber);
        return "redirect:/orders/" + order.getId();
    }

    @GetMapping("/success/{orderId}")
    public String showOrderConfirmation(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "checkout/success";
    }
}
