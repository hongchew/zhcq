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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Promotion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId;

    @Column(nullable = false, length = 64)
    @NotNull
    @Size(max = 64)
    private String promotionName;

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

    public Promotion(String promotionName, BigDecimal discountRate, Date startDate, Date endDate) {
        this();
        this.promotionName = promotionName;
        this.discountRate = discountRate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addProduct(ProductEntity product) {
        if (product != null) {
            if (!this.promotionalProducts.contains(product)) {
                System.out.println("Added Product Id " + product.getProductName() );
                this.promotionalProducts.add(product);
                product.setPromotion(this);
            }
        }
    }

    public void removeProduct(ProductEntity product) {
        if (product != null) {
            if (this.promotionalProducts.contains(product)) {
                this.promotionalProducts.remove(product);
                product.setPromotion(null);
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

    public List<ProductEntity> getPromotionalProducts() {
        return promotionalProducts;
    }

    public void setPromotionalProducts(List<ProductEntity> promotionalProducts) {
        this.promotionalProducts = promotionalProducts;
    }

    /**
     * @return the promotionName
     */
    public String getPromotionName() {
        return promotionName;
    }

    /**
     * @param promotionName the promotionName to set
     */
    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

}
