package vn.fs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import vn.fs.convert.ConvertImage;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author DongTHD
 *
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private String productName;
	private int quantity;
	private double price;
	private int discount;
	@Column(length = 5000)
	private String productImage;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date enteredDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;

	private int endDay;

	private Boolean hide;

	private double priceImport;

	private Boolean status;
	public boolean favorite;

	@ManyToOne
	@JoinColumn(name = "categoryId", nullable = false)
	private Category category;


	public String getProductImage() {
		return productImage;
	}

	public String getProductNoImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
}
