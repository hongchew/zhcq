/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ProductEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.ProductNotFoundException;

/**
 *
 * @author chengyang
 */
@Stateless
public class ProductController implements ProductControllerLocal {

    @PersistenceContext(unitName = "ZhcqRetailSystem-ejbPU")
    private EntityManager em;
    
    public ProductEntity retrieveProductById(Long id) throws ProductNotFoundException{
        ProductEntity product = em.find(ProductEntity.class, id);
        if(product != null) {
            return product;
        } else {
            throw new ProductNotFoundException("Product with id " + id + " not found!");
        }
    }
    
    
}
