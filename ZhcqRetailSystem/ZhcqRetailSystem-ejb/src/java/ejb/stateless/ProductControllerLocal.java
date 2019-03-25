/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ProductEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CategoryNotFoundException;
import util.exception.CreateNewProductException;
import util.exception.InputDataValidationException;
import util.exception.ProductNotFoundException;


@Local
public interface ProductControllerLocal {

    public ProductEntity createNewProduct(ProductEntity newProductEntity, Long categoryId, List<Long> productTags) throws InputDataValidationException, CreateNewProductException;

    public ProductEntity retrieveProductById(Long id) throws ProductNotFoundException;

    public List<ProductEntity> filterProductsByCategory(Long categoryId) throws CategoryNotFoundException;

    public List<ProductEntity> searchProductsByName(String searchString);

    public List<ProductEntity> filterProductsByTags(List<Long> tagIds, String condition);

    public void deleteProduct(Long productId) throws ProductNotFoundException;
    
}
