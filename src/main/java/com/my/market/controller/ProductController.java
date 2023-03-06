package com.my.market.controller;

import com.my.market.model.Product;
import com.my.market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    @GetMapping("/")
    public String showProducts(@RequestParam(name = "title", required = false) String title, Principal principal, Model model){
        List<Product> productList = productService.getProductByTitle(title);

        if(productList.isEmpty())
            productList = productService.getAllProducts();

        model.addAttribute("products", productList);
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam(name = "file1") MultipartFile file1,
                                @RequestParam(name = "file2") MultipartFile file2,
                                @RequestParam(name = "file3") MultipartFile file3, Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping ("/product/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String showProductInfo(@PathVariable int id, Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImageList());
        return "product_info";
    }
}
