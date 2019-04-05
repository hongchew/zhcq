/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.exception.PromotionExistException;


@Entity
public class Promotion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId;
    
    @Column(scale = 2)
    private BigDecimal discountRate;
    
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @OneToMany(mappedBy = "promotion")
    private List<ProductEntity> promotionalProducts;

    public Promotion() {
        promotionalProducts = new ArrayList<>();
    }

    public Promotion(BigDecimal discountRate, Date startDate, Date endDAte) {
        this()
;       this.discountRate = discountRate;
        this.startDate = startDate;
        this.endDate = endDAte;
    }
    
    public void addProduct(ProductEntity product) throws PromotionExistException{
        if(product != null)
        {
            if(!this.promotionalProducts.contains(product))
            {
                this.promotionalProducts.add(product);
                
                if(product.getPromotion() == null)
                {
                    product.setPromotion(this);
                }
                else 
                {
                    throw new PromotionExistException("There is already a promotion attached to this product!!");
                }
            } 
        }
    }
    public void removeProduct(ProductEntity product) throws PromotionExistException{
        if(product != null)
        {
            if(this.promotionalProducts.contains(product))
            {
                this.promotionalProducts.remove(product);
                
                if(product.getPromotion() == this)
                {
                    product.setPromotion(null);
                }
                else 
                {
                    throw new PromotionExistException("There is already a promotion attached to this product!!");
                }
            } 
        }
    }
    

    
    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the promotionalProducts
     */
    public List<ProductEntity> getPromotionalProducts() {
        return promotionalProducts;
    }

    /**
     * @param promotionalProducts the promotionalProducts to set
     */
    public void setPromotionalProducts(List<ProductEntity> promotionalProducts) {
        this.promotionalProducts = promotionalProducts;
    }

    
}
