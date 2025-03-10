package vn.minhduc.laptopshop.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.minhduc.laptopshop.domain.*;
import vn.minhduc.laptopshop.repository.*;
import vn.minhduc.laptopshop.specification.ProductSpecification;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductService(
            ProductRepository productRepository,
            CartRepository cartRepository,
            CartDetailRepository cartDetailRepository,
            UserService userService,
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository
    ) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Page<Product> getAllProducts(Pageable page) {
        return this.productRepository.findAll(page);
    }

    // public Page<Product> getProductWithSpecification(Pageable page, String name) {
    // return this.productRepository.findAll(ProductSpecs.nameLike(name), page);
    // }

    // case 1
    // public Page<Product> getProductWithSpecification(Pageable page, double min) {
    // return this.productRepository.findAll(ProductSpecs.minPrice(min), page);
    // }

    // case 2
    // public Page<Product> getProductWithSpecification(Pageable page, double max) {
    // return this.productRepository.findAll(ProductSpecs.maxPrice(max), page);
    // }

    // case 3
    // public Page<Product> getProductWithSpecification(Pageable page, String factory) {
    // return this.productRepository.findAll(ProductSpecs.matchFactory(factory),
    // page);
    // }

    // case 4
    // public Page<Product> getProductWithSpecification(Pageable page, List<String>
    // factory) {
    // return this.productRepository.findAll(ProductSpecs.matchListFactory(factory),
    // page);
    // }

    //case 5
    public Page<Product> getProductWithSpecification(Pageable page, String price) {
        if (price.equals("10-toi-15-trieu")) {
            double min = 10000000;
            double max = 15000000;
            return this.productRepository.findAll(ProductSpecification.matchPrice(min, max),
                    page);

        } else if (price.equals("15-toi-30-trieu")) {
            double min = 15000000;
            double max = 30000000;
            return this.productRepository.findAll(ProductSpecification.matchPrice(min, max),
                    page);
        } else
            return this.productRepository.findAll(page);
    }

    public Page<Product> getProductWithSpecification(Pageable page, List<String> price) {
        Specification<Product> combinedSpec = (root, query, criteriaBuilder) -> criteriaBuilder.disjunction();
        int count = 0;
        for (String p : price) {
            double min = 0;
            double max = 0;
            switch (p) {
                case "10-toi-15-trieu":
                    min = 10000000;
                    max = 15000000;
                    count++;
                    break;
                case "15-toi-20-trieu":
                    min = 15000000;
                    max = 20000000;
                    count++;
                    break;
                case "20-toi-30-trieu":
                    min = 20000000;
                    max = 30000000;
                    count++;
                    break;
            }
            if (min != 0 && max != 0) {
                Specification<Product> rangeSpec = ProductSpecification.matchMultiplePrice(min, max);
                combinedSpec = combinedSpec.or(rangeSpec);
            }
        }
        if (count == 0) {
            return this.productRepository.findAll(page);
        }
        return this.productRepository.findAll(combinedSpec, page);
    }

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Optional<Product> getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    public void handleAddProductToCart(String email, long productId, HttpSession session, long quantity) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);
                cart = this.cartRepository.save(otherCart);
            }
            Optional<Product> productOptional = this.productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product realProduct = productOptional.get();
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
                if (oldDetail == null) {
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(realProduct);
                    cartDetail.setPrice(realProduct.getPrice());
                    cartDetail.setQuantity(quantity);
                    this.cartDetailRepository.save(cartDetail);
                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + quantity);
                    this.cartDetailRepository.save(oldDetail);
                }
            }
        }
    }

    public Cart fetchByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public void removeCartDetail(long cartDetailId, HttpSession session) {
        Optional<CartDetail> cartDetailOptional = this.cartDetailRepository.findById(cartDetailId);
        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();
            Cart currentCart = cartDetail.getCart();
            this.cartDetailRepository.deleteById(cartDetailId);
            if (currentCart.getSum() > 1) {
                int s = currentCart.getSum() - 1;
                currentCart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(currentCart);
            } else {
                this.cartRepository.deleteById(currentCart.getId());
                session.setAttribute("sum", 0);
            }
        }
    }

    public void updateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void placeOrder(
            User user, HttpSession session,
            String receiverName, String receiverAddress,
            String receiverPhone
    ) {
        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();
            if (cartDetails != null) {
                Order order = new Order();
                order.setUser(user);
                order.setReceiverName(receiverName);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverPhone(receiverPhone);
                order.setStatus("PENDING");
                double sum = 0;
                for (CartDetail cartDetail : cartDetails) {
                    sum += cartDetail.getPrice();
                }
                order.setTotalPrice(sum);
                order = this.orderRepository.save(order);
                for (CartDetail cartDetail : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cartDetail.getProduct());
                    orderDetail.setPrice(cartDetail.getPrice());
                    orderDetail.setQuantity(cartDetail.getQuantity());
                    this.orderDetailRepository.save(orderDetail);
                }
                for (CartDetail cartDetail : cartDetails) {
                    this.cartDetailRepository.deleteById(cartDetail.getId());
                }
                this.cartRepository.deleteById(cart.getId());
                session.setAttribute("sum", 0);
            }
        }
    }
}
