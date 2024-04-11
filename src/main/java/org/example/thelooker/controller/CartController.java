package org.example.thelooker.controller;

import org.example.thelooker.security.CustomUserPrincipal;
import org.example.thelooker.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int qty, @AuthenticationPrincipal CustomUserPrincipal customUserPrincipal, RedirectAttributes redirectAttributes) {
        if (customUserPrincipal == null) {
            return "redirect:/login";
        }

        Long customerId = customUserPrincipal.getCustomerId();
        cartService.addToCart(customerId, productId, qty);
        redirectAttributes.addFlashAttribute("message", "Product added to cart successfully");
        return "redirect:/products";
    }

}
