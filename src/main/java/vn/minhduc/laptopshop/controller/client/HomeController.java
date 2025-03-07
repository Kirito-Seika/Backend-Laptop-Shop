package vn.minhduc.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.minhduc.laptopshop.domain.Product;
import vn.minhduc.laptopshop.service.ProductService;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getHomePage(Model model){
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "client/home/layout";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        return "client/auth/register";
    }
}
