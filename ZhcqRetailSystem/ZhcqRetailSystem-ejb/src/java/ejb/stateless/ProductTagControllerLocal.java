/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ProductTag;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewProductTagException;
import util.exception.DeleteTagException;
import util.exception.InputDataValidationException;

import util.exception.ProductTagNotFoundException;
import util.exception.UpdateProductTagException;


@Local
public interface ProductTagControllerLocal {

    public ProductTag retrieveProductTagByTagId(Long tagId) throws ProductTagNotFoundException;

    public void deleteProductTag(Long tagId) throws ProductTagNotFoundException, DeleteTagException;

    public List<ProductTag> retrieveAllProductTags();
       
    public ProductTag createNewProductTag(ProductTag newTag) throws InputDataValidationException, CreateNewProductTagException;

    public void updateProductTag(ProductTag productTag) throws InputDataValidationException, ProductTagNotFoundException, UpdateProductTagException;

    
}
