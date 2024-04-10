package org.example.thelooker.controller;

import org.example.thelooker.dto.ProductDto;
import org.example.thelooker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

    @Autowired
    private ProductService productService;

    // Display all products
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin-products";
    }

    // Show form to add a new product
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return "admin-products-add";
    }

    // Save the new or updated product
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") ProductDto productDto) {
        productService.createProduct(productDto);
        return "redirect:/admin/products";
    }

    // Show form to edit an existing product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ProductDto productDto = productService.getProductById(id);
        model.addAttribute("product", productDto);
        return "admin-products-edit";
    }

    // Delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
