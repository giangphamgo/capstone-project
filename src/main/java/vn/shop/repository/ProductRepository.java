package vn.fs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.fs.entities.Product;

import java.util.List;

/**
 * @author DongTHD
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {



	@Query(value = "Select count(product_image) from products where product_image=?", nativeQuery = true)
	int countProductImage(int random);
	@Query(value = "SELECT COUNT(*) FROM products where discount>0", nativeQuery = true)
	int countProductSale();

	@Query(value = "SELECT COUNT(*) FROM products", nativeQuery = true)
	double countProduct();

	@Query(value = "SELECT * FROM products WHERE status='1'", nativeQuery = true)
	List<Product> findAll();

	@Query(value = "SELECT * FROM products WHERE status='1' and quantity>0 and hide=false ", nativeQuery = true)
	List<Product> findAllClient();

	@Query(value = "SELECT * FROM products WHERE product_id=?1", nativeQuery = true)
	Product findbyid2(Long id);


	@Query(value = "SELECT * FROM products WHERE status='1' and discount>0", nativeQuery = true)
	List<Product> findAllDate();

	@Query(value = "SELECT * FROM products WHERE status='1' and DATEDIFF(end_date,CURDATE())<1", nativeQuery = true)
	List<Product> findAllDateEndDay();

	@Query(value = "SELECT * FROM products WHERE status='1' and quantity<=0", nativeQuery = true)
	List<Product> findAllQuantity();

	@Query(value = "SELECT DATEDIFF( end_date,CURDATE()) AS days_difference FROM products where product_id=?1", nativeQuery = true)
	int EndDateProduct(Long id);
	// List product by category
	@Query(value = "SELECT * FROM products WHERE status='1' and quantity>0 and hide=false  and category_id = ?", nativeQuery = true)
	public List<Product> listProductByCategory(Long categoryId);


	@Query(value = "SELECT * FROM products WHERE product_id = ?", nativeQuery = true)
	public List<Product> listProductByid(Long id);

	// Top 10 product by category
	@Query(value = "SELECT * FROM products WHERE status='1' and quantity>0 and hide=false  and category_id = ?;", nativeQuery = true)
	List<Product> listProductByCategory10(Long categoryId);
	
	// List product new
	@Query(value = "SELECT * FROM products  WHERE status='1' and quantity>0 and hide=false  ORDER BY entered_date DESC limit 20;", nativeQuery = true)
	public List<Product> listProductNew20();
	
	// Search Product
	@Query(value = "SELECT * FROM products WHERE status='1' and quantity>0 and hide=false  and product_name LIKE %?1%" , nativeQuery = true)
	public List<Product> searchProduct(String productName);
	
	// count quantity by product
	@Query(value = "Select categories.category_id, categories.category_name , COUNT(*) AS SoLuong\n" +
			"From products,categories where products.status='1' and products.quantity>0 and products.hide=false and DATEDIFF(products.end_date,CURDATE())>0 and products.`category_id`=categories.`category_id` \n" +
			"GROUP BY categories.`category_id`" , nativeQuery = true)
	public List<Object[]> listCategoryByProductName();
	
	// Top 20 product best sale
	@Query(value = "SELECT p.product_id,COUNT(*) AS SoLuong \n" +
			"FROM \n" +
			"order_details p \n" +
			"JOIN products c ON p.product_id = c.product_id \n" +
			"WHERE c.status='1' and c.quantity>0 and c.hide=false and DATEDIFF(c.end_date,CURDATE())>0\n" +
			"GROUP BY p.product_id ORDER by SoLuong DESC limit 20", nativeQuery = true)
	public List<Object[]> bestSaleProduct20();
	
	@Query(value = "select * from products o where product_id in :ids", nativeQuery = true)
	List<Product> findByInventoryIds(@Param("ids") List<Integer> listProductId);

}
