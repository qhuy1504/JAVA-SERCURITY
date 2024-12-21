package iuh.fit.se.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iuh.fit.se.dtos.CategoryDTO;
import iuh.fit.se.dtos.ProductDTO;
import iuh.fit.se.services.CategoryService;
import iuh.fit.se.services.ProductService;
import jakarta.validation.Valid;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/products")
	public String getAllProducts(@Valid @ModelAttribute("product") ProductDTO productDTO, BindingResult result,
			Model model) {
		List<ProductDTO> products = productService.findAll();
		List<CategoryDTO> categories = categoryService.getAll();

		System.out.println(products);
		ProductDTO product = new ProductDTO();

		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);

		return "product-list";
	}

	//Hiển thị form thêm mới, cập nhật sản phẩm
	@GetMapping({"/products/add", "/products/edit/{id}"})
	public String showForm(@PathVariable (value = "id", required = false) Integer id, Model model) {
		ProductDTO product;
		//Kiểm tra nếu có id, tìm sản phẩm theo id để sửa
		if (id != null && id > 0) {
			product = productService.findById(id);
			if (product == null) {
				return "redirect:/products?error"; //Nếu không tìm thấy sản phẩm";
			}
			
		}else {
			// Tạo mới sản phẩm khi không có id
			product = new ProductDTO();
			product.setIsActive(false);
		}
		//Lấy danh sách category
		List<CategoryDTO> categories = categoryService.getAll();
		model.addAttribute("product", product);
		model.addAttribute("categories", categories);
		return "product-form";
		
		
	}
	@PostMapping({"/products/add", "/products/edit/{id}"})
	public String addOrUpdateProduct (@ModelAttribute ("product") @Valid ProductDTO product, BindingResult result, Model model) {
		//Kiểm tra nếu có lỗi
		if (result.hasErrors()) {
			model.addAttribute("categories", categoryService.getAll());
			return "product-form";
		}
		//Nếu không có lỗi, xử lý thêm mới hoặc cập nhật sản phẩm
		if (product.getId()>0) {
			//Cập nhật sản phẩm
			productService.update(product.getId(), product);
		}else {
			// Thêm mới sản phẩm
			productService.save(product);
		}
		return "redirect:/products?success"; // quay lại màn hình sau khi thêm thành công
		
	}
	//Xóa sản phâm
	@GetMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable(value = "id", required = false) Integer id) {
		productService.delete(id);
		return "redirect:/products?success";
	}
	@GetMapping("/products/search")
	//Integer categoryID, Boolean isActive, Date registerDate, String keyword
	public String searchProducts (@RequestParam(required = false) Integer categoryID, @RequestParam(required = false) Boolean isActive,
			@RequestParam(required = false) LocalDate registerDate, @RequestParam(required = false) String keyword, Model model) {
		List<ProductDTO> products = productService.searchProducts(categoryID, isActive, registerDate, keyword);
		List<CategoryDTO> categories = categoryService.getAll();
		ProductDTO product = new ProductDTO();
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		return "product-list";
		
	}
	
	
	
	

	

}
