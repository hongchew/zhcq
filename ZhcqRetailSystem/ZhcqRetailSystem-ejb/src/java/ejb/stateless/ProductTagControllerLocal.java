/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ProductTag;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewTagException;
import util.exception.DeleteTagException;
import util.exception.InputDataValidationException;

import util.exception.TagNotFoundException;
import util.exception.UpdateTagException;


@Local
public interface ProductTagControllerLocal {

    public ProductTag retrieveTagByTagId(Long tagId) throws TagNotFoundException;

    public ProductTag createNewTagEntity(ProductTag newTag) throws InputDataValidationException, CreateNewTagException;

    public void deleteTag(Long tagId) throws TagNotFoundException, DeleteTagException;

    public List<ProductTag> retrieveAllTags();
    
    public void updateTag(ProductTag productTag) throws InputDataValidationException, TagNotFoundException, UpdateTagException;

    
}
