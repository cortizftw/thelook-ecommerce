package org.example.thelooker.controller;

import org.example.thelooker.dto.ProductDto;
import org.example.thelooker.model.Product;
import org.example.thelooker.security.CustomUserDetailsService;
import org.example.thelooker.service.ProductService;
import org.example.thelooker.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.example.thelooker.service.CartServiceImpl;

import java.util.List;

@Controller
public class UserController {

//    @GetMapping("/user/welcome")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public String userAccess() {
//        return
//                "register";
//    }
    public final ProductServiceImpl ProductServiceImpl;

    public UserController(ProductServiceImpl ProductServiceImpl) {
        this.ProductServiceImpl = ProductServiceImpl;
    }

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String showProductList(Model model) {
        List<ProductDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product-detail/{id}")
    public String viewProductDetails(@PathVariable Long id, Model model) {
        ProductDto product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products"; // Redirect or show an error message
        }
        model.addAttribute("product", product);
        return "product-detail"; // name of the Thymeleaf template for product details
    }


}
