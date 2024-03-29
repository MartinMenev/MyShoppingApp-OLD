package com.example.myshoppingapp.web;

import com.example.myshoppingapp.domain.products.InputProductDTO;
import com.example.myshoppingapp.domain.products.OutputProductDTO;
import com.example.myshoppingapp.service.ProductService;
import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/product-list")
    public String showProductListPage(Model model) {
        List<OutputProductDTO> products = this.productService.getListedProducts();
        model.addAttribute("products", products);
        model.addAttribute("boughtProducts", this.productService.showBoughtProducts());
        return "product/product-list";
    }

    @PostMapping("/add-product")
    public String doAddProduct(InputProductDTO inputProductDTO) {
        productService.addProduct(inputProductDTO);
        return "redirect:/product-list";
    }

    @GetMapping("/product/updateProduct/{id}")
    public String updateProduct(@PathVariable(value = "id") Long id, Model model) {
        OutputProductDTO outputProductDTO = productService.getProductById(id);
        model.addAttribute("product", outputProductDTO);
        return "product/updateProduct";
    }

    @PutMapping ("/product/updateProduct/{id}")
    public String doUpdateProduct(@PathVariable(value = "id") Long id, Model model, InputProductDTO inputProductDTO) {
        OutputProductDTO outputProductDTO = productService.getProductById(id);
        model.addAttribute("product", outputProductDTO);
        productService.updateProduct(inputProductDTO);
        return "redirect:/product-list";
    }

    @GetMapping("/moveUpProduct/{position}")
    public String moveUpProduct(@PathVariable(value = "position") long position) {
        productService.moveUpProduct(position);
        return "redirect:/product-list";
    }

    @GetMapping("/moveDownProduct/{position}")
    public String moveDownProduct(@PathVariable(value = "position") long position) {
        productService.moveDownProduct(position);
        return "redirect:/product-list";
    }


    @GetMapping("/deleteProduct/{id}")
    public String deleteById(@PathVariable(value = "id") long id) {
        productService.deleteById(id);
        return "redirect:/product-list";
    }

    @GetMapping("/buy-product/{id}")
    public String buyProduct(@PathVariable(value = "id") long id) {
        productService.buyProduct(id);
        return "redirect:/product-list";
    }

    @GetMapping("/reuse-product/{id}")
    public String reuseProduct(@PathVariable(value = "id") long id) {
        productService.reuseProduct(id);
        return "redirect:/product-list";
    }

}
