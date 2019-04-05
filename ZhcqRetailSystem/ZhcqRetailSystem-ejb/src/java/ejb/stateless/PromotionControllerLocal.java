/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.Promotion;
import java.util.List;
import javax.ejb.Local;

import util.exception.InputDataValidationException;
import util.exception.ProductNotFoundException;
import util.exception.PromotionExistException;

import util.exception.PromotionNotFoundException;



@Local
public interface PromotionControllerLocal {

    public List<Promotion> retrieveAllPromotions();


    public Promotion createNewPromotion(Promotion newPromotion, List<Long> productIds) throws ProductNotFoundException, PromotionExistException, InputDataValidationException;

    public Promotion retrievePromotionByPromotionId(Long promotionId) throws PromotionNotFoundException;


}
