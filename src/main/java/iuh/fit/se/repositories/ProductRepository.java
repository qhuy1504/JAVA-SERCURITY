package iuh.fit.se.repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iuh.fit.se.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query(value = "Select p.* from Product p where p.code like %:keyword% or p.name like %:keyword% or p.description like %:keyword%", nativeQuery = true)
	List<Product> search (@Param("keyword") String keyword);
	
	@Query(value = "Select p.* from Product p where p.categoryID = :categoryID", nativeQuery = true)
	List<Product> findByCategoryID(@Param("categoryID") Integer categoryID);
	
	//Tìm sản phẩm theo categoryID, isActive và khoảng thời gian
	@Query(value = "select p.* from product p where p.categoryid = :categoryID and p.is_active = :isActive and p.register_date between :registerDate and CURDATE()", nativeQuery = true)
	List<Product> findByCategoryIDAndIsActiveAndRegisterDateBetween(@Param("categoryID") Integer categoryID, @Param("isActive")  boolean isActive,
			@Param("registerDate") LocalDate registerDate);
	
	
	@Query(value = "select p.* from Product p where p.categoryid = :categoryID and p.register_date between :registerDate and curdate()", nativeQuery = true)
	List<Product> findByCategoryIDAndRegisterDateBetween(@Param("categoryID") Integer categoryID, @Param("registerDate") LocalDate registerDate);
	
	//Tìm sản phảm theo trạng thái và khoản ngày đăng ký
	@Query(value = "select p.* from Product p where p.is_active = :isActive and p.register_date between :registerDate and curdate()" , nativeQuery = true)
	List<Product> findByIsActiveAndRegisterDateBetween(@Param("isActive") boolean isActive, @Param("registerDate") LocalDate registerDate);
	
	//Tìm sản phẩm theo categoryID và trạng thái
	@Query(value = "select p.* from Product p where p.categoryid = :categoryID and p.is_active = :isActive", nativeQuery = true)
	List<Product> findByCategoryIDAndIsActive(@Param("categoryID") Integer categoryID, @Param("isActive") boolean isActive);

	//Tìm sản phẩm theo trạng thái
	@Query(value = "select p.* from Product p where p.is_active = :isActive", nativeQuery = true)
	List<Product> findByIsActive(@Param("isActive") boolean isActive);
	//Tìm sản phẩm theo ngày đăng ký
	@Query(value = "select p.* from Product p where p.register_date between :registerDate and curdate()", nativeQuery = true)
	List<Product> findByRegisterDate(@Param("registerDate") LocalDate registerDate);
}
