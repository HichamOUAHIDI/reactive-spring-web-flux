/**
 * Foodho
 */
package foodho.core.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import foodho.core.entites.Product;

/**
 * @author hicham
 *
 */
public interface ProduitRepository extends MongoRepository<Product,String>{

}
