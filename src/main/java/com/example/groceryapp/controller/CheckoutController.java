package com.example.groceryapp.controller;

import com.example.groceryapp.model.Cart;
import com.example.groceryapp.model.Order;
import com.example.groceryapp.model.User;
import com.example.groceryapp.service.CartService;
import com.example.groceryapp.service.OrderService;
import com.example.groceryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private UserService userService;

    @GetMapping
    public String showCheckoutForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        Cart cart = cartService.getOrCreateCart(user);
        if (cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        return "checkout/form";
    }

    @PostMapping
    public String processCheckout(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String shippingAddress,
            @RequestParam String phoneNumber) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        Order order = orderService.saveOrder(createOrder(user, shippingAddress, phoneNumber));
        return "redirect:/orders/" + order.getId();
    }

    @GetMapping("/confirmation/{orderId}")
    public String showConfirmation(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);
        return "checkout/confirmation";
    }

    private Order createOrder(User user, String shippingAddress, String phoneNumber) {
        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(shippingAddress);
        order.setPhoneNumber(phoneNumber);
        return order;
    }
}
