package com.example.groceryapp.controller;

import com.example.groceryapp.model.Product;
import com.example.groceryapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        return "products/form";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/category/{category}")
    public String listProductsByCategory(@PathVariable String category, Model model) {
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("currentCategory", category);
        return "products/list";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam String query, Model model) {
        List<Product> products = productService.searchProducts(query);
        model.addAttribute("products", products);
        model.addAttribute("searchQuery", query);
        return "products/list";
    }

    @GetMapping("/api/all")
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProductsApi() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
