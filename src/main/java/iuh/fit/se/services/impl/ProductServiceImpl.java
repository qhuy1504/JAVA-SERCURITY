package iuh.fit.se.services.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iuh.fit.se.dtos.ProductDTO;
import iuh.fit.se.entities.Product;
import iuh.fit.se.repositories.ProductRepository;
import iuh.fit.se.services.ProductService;
import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	private ProductDTO covertToDTO (Product product) {
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
		return productDTO;
	}
	
	private Product covertToEntity (ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        return product;
	}

	@Override
	public ProductDTO findById(int id) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Khong tim thay Product: " + id));
		return this.covertToDTO(product);
	}

	@Override
	@Transactional
	public List<ProductDTO> findAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll().stream().map(this::covertToDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ProductDTO save(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		Product product = this.covertToEntity(productDTO);
		product = productRepository.save(product);
		System.out.println("Product saved: " + product);
		return this.covertToDTO(product);
	}

	@Override
	public ProductDTO update(int id, ProductDTO productDTO) {
		// TODO Auto-generated method stub
		this.findById(id);
		//update 
		productRepository.save(this.covertToEntity(productDTO));
		return productDTO;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		this.findById(id);
		productRepository.deleteById(id);
		return true;
	}

	@Override
	@Transactional
	public List<ProductDTO> search(String keyword) {
		// TODO Auto-generated method stub
		return productRepository.search(keyword).stream().map(this::covertToDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<ProductDTO> searchByCategory(Integer categoryID) {
		// TODO Auto-generated method stub
		return productRepository.findByCategoryID(categoryID).stream().map(this::covertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> searchProducts(Integer categoryID, Boolean isActive, LocalDate registerDate, String keyword) {
		// TODO Auto-generated method stub
		List<Product> products;
		if (categoryID != null && isActive != null && registerDate != null) {
			products = productRepository.findByCategoryIDAndIsActiveAndRegisterDateBetween(categoryID, isActive,
					registerDate);
		} else if (keyword != null && !keyword.isEmpty()) {
			products = productRepository.search(keyword);
		}
		else if (categoryID != null && isActive != null) {
			products = productRepository.findByCategoryIDAndIsActive(categoryID, isActive);
		} else if (categoryID != null && registerDate != null) {
			products = productRepository.findByCategoryIDAndRegisterDateBetween(categoryID, registerDate);
		} else if (isActive != null && registerDate != null) {
			products = productRepository.findByIsActiveAndRegisterDateBetween(isActive, registerDate);
		} else if (categoryID != null) {
			products = productRepository.findByCategoryID(categoryID);
		} else if (isActive != null) {
			products = productRepository.findByIsActive(isActive);
		} else if (registerDate != null) {
			products = productRepository.findByRegisterDate(registerDate);
		}
		else {
			products = productRepository.findAll();
		}
		System.out.println("products lọc sp: " + products);
		
		return products.stream().map(this::covertToDTO).collect(Collectors.toList());
	}

}
