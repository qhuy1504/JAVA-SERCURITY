package iuh.fit.se.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import iuh.fit.se.dtos.ProductDTO;

public interface ProductService {
	public ProductDTO findById(int id);
	public List<ProductDTO> findAll();
	public ProductDTO save (ProductDTO productDTO);
	public ProductDTO update (int id, ProductDTO productDTO);
	public boolean delete (int id);
	List<ProductDTO> search (String keyword);
	List<ProductDTO> searchByCategory (Integer categoryID);
	List<ProductDTO> searchProducts(Integer categoryID, Boolean isActive, LocalDate registerDate,String keyword);

}
