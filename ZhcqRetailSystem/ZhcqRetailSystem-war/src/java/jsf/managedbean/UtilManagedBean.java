package jsf.managedbean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.stateless.PromotionControllerLocal;
import entity.Promotion;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author chengyang
 */
@ManagedBean
@RequestScoped
public class UtilManagedBean {

    @EJB(name = "PromotionControllerLocal")
    private PromotionControllerLocal promotionControllerLocal;
    
    private List<Promotion> promotions;
   
    
    public UtilManagedBean() {
    }
    
    public void postConstruct() {
        promotions = promotionControllerLocal.retrieveAllPromotions();
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
    
    
}
