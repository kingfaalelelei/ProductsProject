package com.kingfaalelelei.ProductsProject.controller

import com.kingfaalelelei.ProductsProject.entity.Product
import com.kingfaalelelei.ProductsProject.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
public class ProductController {
    private final int ROW_PER_PAGE = 5;

    //Logger logger = org.slf4j.LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private Environment env;

    @Autowired
    private ProductService productService;

//    @Value("${msg.title}")
//    private String title;

    @GetMapping(value = ["/", "/index"])
    public String index(Model model) {
        model.addAttribute("title", env.getProperty("msg.title"));
        return "index";
    }

    @GetMapping(value = "/products")
    public String getProducts(Model model,
                           @RequestParam(value = "page",
                                   defaultValue = "1") int pageNumber) {
        List<Product> products = productService.findAll(pageNumber, ROW_PER_PAGE);

        long count = productService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("products", products);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);

        println("Product Count: $count")
        return "products-list";
    }

    @GetMapping(value = "/products/{productId}")
    public String getProductById(Model model, @PathVariable Integer todoId) {
        Product product = null;
        String errorMessage = null;
        try {
            product = productService.findById(todoId);
        } catch (Exception ex) {
            errorMessage = "Product not found";
        }

        model.addAttribute("product", product);
        model.addAttribute("allowDelete", false);
        model.addAttribute("errorMessage", errorMessage);
        return "product-view";
    }

    @GetMapping(value = "/products/add")
    //@RequestMapping(value = "/todos/add", method = RequestMethod.GET)
    public String showAddProduct(Model model) {
        Product product = new Product();
        model.addAttribute("add", true);
        model.addAttribute("product", product);
        model.addAttribute("actionUrl", "/products/add");

        return "product-edit";
    }

    @PostMapping(value = "/products/add")
    //@RequestMapping(value = "/todos/add", method = RequestMethod.POST)
    public String addProduct(Model model,
                          @ModelAttribute("product") Product product) {
        try {
            Product newProduct = productService.save(product);
            return "redirect:/products/" + String.valueOf(newProduct.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();

            model.addAttribute("errorMessage", errorMessage);

            //model.addAttribute("product", product);
            model.addAttribute("add", true);
            return "product-edit";
        }
    }

    @GetMapping(value = ["/products/{productId}/edit"])
    public String showEditTodo(Model model, @PathVariable Integer productId) {
        Product product = null;
        String errorMessage = null;
        try {
            product = productService.findById(productId);
        } catch (Exception ex) {
            errorMessage = "Product not found";
        }
        model.addAttribute("add", false);
        model.addAttribute("product", product);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("actionUrl",
                "/products/" + (product == null ? 0 : product.getId()) + "/edit");
        return "product-edit";
    }

    @PostMapping(value = ["/products/{productId}/edit"])
    public String updateProduct(Model model,
                             @PathVariable Integer productId,
                             @ModelAttribute("product") Product product) {
        try {
            product.setId(productId);
            productService.update(productId);
            return "redirect:/products/" + String.valueOf(product.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage()
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "product-edit";
        }
    }

    @GetMapping(value = ["/products/{productId}/delete"])
    public String showDeleteTodoById(
            Model model, @PathVariable Integer productId) {
        Product product = null;
        String errorMessage = null;
        try {
            product = productService.findById(productId);
        } catch (Exception ex) {
            errorMessage = "Product not found";

        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("product", product);
        model.addAttribute("errorMessage", errorMessage);
        return "product-view";
    }

    @PostMapping(value = ["/products/{productId}/delete"])
    public String deleteProductById(
            Model model, @PathVariable Integer productId) {
        try {
            productService.deleteById(productId);
            return "redirect:/products";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();

            model.addAttribute("errorMessage", errorMessage);
            return "product-view";
        }
    }
}
