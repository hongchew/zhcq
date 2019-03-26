/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.Promotion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chengyang
 */
@Local
public interface PromotionControllerLocal {

    public List<Promotion> retrieveAllPromotions();
    
}
