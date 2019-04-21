/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.Promotion;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreatePromotionException;

import util.exception.InputDataValidationException;

import util.exception.PromotionNotFoundException;
import util.exception.UpdatePromotionException;

@Local
public interface PromotionControllerLocal {

    public List<Promotion> retrieveAllPromotions();

    public Promotion createNewPromotion(Promotion newPromotion, List<Long> productIds) throws CreatePromotionException, InputDataValidationException;

    public Promotion retrievePromotionByPromotionId(Long promotionId) throws PromotionNotFoundException;

    public void deletePromotion(Long promotionId) throws PromotionNotFoundException;

    public void updatePromotion(Promotion promotion, List<Long> productIds) throws UpdatePromotionException;

}
