/**
 * Produit
 */
package foodho.core.entites;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hicham
 *
 */
@AllArgsConstructor
@Setter @Getter
@Slf4j
@Document
public class Product {
	@Id
    private String product_code;
    private String product_name;
    private String product_description;
    private String product_url_photo;
    private float product_price;   
}
