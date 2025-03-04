package vn.minhduc.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.minhduc.laptopshop.domain.Product;

@Controller
public class ProductController {
    @GetMapping("/admin/product")
    public String getProductPage(){
        return "admin/product/layout";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("createProduct", new Product());
        return "admin/product/create";
    }
}
