package vn.minhduc.laptopshop.controller.admin;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vn.minhduc.laptopshop.domain.Product;
import vn.minhduc.laptopshop.service.ProductService;
import vn.minhduc.laptopshop.service.UploadService;

import java.util.List;

@Controller
public class ProductController {
    private final UploadService uploadService;
    private final ProductService productService;

    public ProductController(
            UploadService uploadService,
            ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("listProducts", products);
        return "admin/product/layout";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("createProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProductPage(
            @ModelAttribute("createProduct") @Valid Product product,
            BindingResult createProductBindingResult,
            @RequestParam("file") MultipartFile file
    ) {
        if (createProductBindingResult.hasErrors()) {
            return "admin/product/create";
        }
        String image = this.uploadService.handleUploadFile(file, "product");
        product.setImage(image);
        this.productService.createProduct(product);
        return "redirect:/admin/product";
    }
}
