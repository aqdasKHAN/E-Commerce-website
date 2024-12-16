package com.Practise.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.Practise.DTO.ProductDTO;
import com.Practise.Model.Category;
import com.Practise.Model.Product;
import com.Practise.Service.CategoryService;
import com.Practise.Service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ControllerEcommerce {
	
	@Autowired
	private  CategoryService categoryService;
	
	@Autowired 
	private ProductService productService;

	public static String uploadDirr=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
		 
	@GetMapping("/homePage")
	public String getHomePage() {
		System.out.println("throwing to home page :"+System.getProperty("user.dir"));
		return "homeFeedsPage";
	}
	
	@GetMapping("/login")
	public String getLoginPage(HttpServletRequest request) {
		System.out.println("throwing to Login page");
		if (request.getSession().getAttribute("user") != null) {
	        return "redirect:/dashboard";
	    }
		return "loginPageEcommerce";
	}
	
	@GetMapping("/logout")
	public String getLogout(HttpServletRequest request, HttpServletResponse response ) {
		System.out.println("throwing to admin Logout Page");
		request.getSession().invalidate();
		return "loginPageEcommerce";
	}
	
	@GetMapping("/admin")
	public String getadminHomePage() {
		System.out.println("throwing to admin Home page");
		return "adminHome";
	}
	
	@DeleteMapping("/categories/deleting/{catId}")
	@ResponseBody
	public ResponseEntity<Void> deleteCategory(@PathVariable Long catId) {
		System.out.println("Deleting category: " + catId);
		categoryService.deletingById(catId);
		return ResponseEntity.noContent().build(); // Returns 204 No Content
	}
	 
	@GetMapping("/admin/categories")
	public String getadCategories(Model model) {
		System.out.println("categories clicked!!");
		model.addAttribute("categry", categoryService.getAllCategories());
		return "categories";
	}
	
	@GetMapping("/admin/categories/addCategory") 
	public String addCategory(Category category) {
		System.out.println("Adding category!!");
//		categoryService.savingCat(category);
		return "CategoriesAdd";
	}
	
	@PostMapping("/admin/categories/addingCategory")
	public String addingCategory(@ModelAttribute Category category) {
		categoryService.addingCat(category);
		return "redirect:/admin/categories";
	}
	
	///////////////////////////////////////////////    P  R  O  D  U  C  T    ///////////////////////////////////////////////////////
	
	@GetMapping("/admin/product")
	public String getProduct(Model model) {
		System.out.println("product clicked!!");
		model.addAttribute("prodct", productService.getAllProduct());
		return "product";
	}
	
	@GetMapping("/admin/product/productAdd")
	public String toaddProduct( Model model) {
		model.addAttribute("prodctDTO", new ProductDTO());
		model.addAttribute("categries", categoryService.getAllCategories());
		return "productAdd";
	}
	
	@PostMapping("/admin/product/addingProduct")
	public String addingProduct(@ModelAttribute("prodctDTO")ProductDTO productDTO, 
								@RequestParam("productImage")MultipartFile file,
								@RequestParam("imgName")String imgName) throws IOException {
		
		Product product =new Product();
		product.setProductId(productDTO.getProductId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCategoryById(productDTO.getCat_id()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		String imgUUID;
		if(!file.isEmpty()) {
			imgUUID=file.getOriginalFilename();
			Path filaNameandPath=Paths.get( uploadDirr, imgUUID);
			Files.write(filaNameandPath, file.getBytes());
		}else {
			imgUUID=imgName;	
		}
		product.setImageName(imgUUID);
	
		productService.addingProduct(product);
		return "redirect:/admin/";
	} 
	
	@DeleteMapping("/product/deleting/{productId}")
	@ResponseBody
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
		System.out.println("Deleting product: " + productId);
		productService.deletingProductById(productId);
		return ResponseEntity.noContent().build(); // Returns 204 No Content
	}
	
	@GetMapping("/product/updateProduct/{productId}")
	public String updateProduct(@PathVariable Long productId, Model model) {
		System.out.println("Upadting product: " + productId);
		
		Product productToDto= productService.getProductById(productId).get();
		
		ProductDTO productDTO=new ProductDTO();
		
		productDTO.setProductId(productToDto.getProductId());
		productDTO.setName(productToDto.getName());
		productDTO.setCat_id(productToDto.getCategory().getCatId());
		productDTO.setPrice(productToDto.getPrice());
		productDTO.setWeight(productToDto.getWeight());
		productDTO.setDescription(productToDto.getDescription());
		productDTO.setImageName(productToDto.getImageName());
		
		System.out.println("Image name: "+productToDto.getImageName()); 
		
		model.addAttribute("categries", categoryService.getAllCategories()); 
		model.addAttribute("prodctDTO", productDTO); 
		
		return "productAdd";
	}
	
	
} 