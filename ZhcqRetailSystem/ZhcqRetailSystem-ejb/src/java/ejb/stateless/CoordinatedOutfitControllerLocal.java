/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.CoordinatedOutfit;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewOutfitException;
import util.exception.InputDataValidationException;
import util.exception.OutfitNotFoundException;
import util.exception.ProductNotFoundException;
import util.exception.UpdateOutfitException;


@Local
public interface CoordinatedOutfitControllerLocal {

    public List<CoordinatedOutfit> retrieveAllOutfits();

    public CoordinatedOutfit createNewOutfit(CoordinatedOutfit newCoordinatedOutfit, List<Long> productIds) throws InputDataValidationException, CreateNewOutfitException, ProductNotFoundException;

    public void deleteOutfit(Long outfitId) throws OutfitNotFoundException;

    public void updateOutfit(CoordinatedOutfit outfit, List<Long> productIds) throws InputDataValidationException, OutfitNotFoundException, ProductNotFoundException, UpdateOutfitException;

    public CoordinatedOutfit retrieveOutfitById(Long outfitId) throws OutfitNotFoundException;

    
    
}
