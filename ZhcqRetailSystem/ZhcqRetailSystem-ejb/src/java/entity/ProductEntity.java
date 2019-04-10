/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;

import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.ManyToOne;
import util.enumeration.ColourEnum;
import util.enumeration.SizeEnum;


@Entity
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    
    @Column(nullable = false, length = 64)
    @NotNull
    @Size(max = 64)
    private String productName;
    
    @Column(length = 128)
    @Size(max = 128)
    private String description;
    
    @Column(nullable = false, precision = 11, scale = 2)
    @NotNull
    @DecimalMin("0.00")
    private BigDecimal unitPrice; 
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull
    private Date dateAdded; 
    
    @Column(nullable = false)
    @NotNull
    @Min(0)
    private Integer quantityOnHand; 
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Category productCategory;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private SizeEnum sizeEnum ;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private ColourEnum colourEnum;
    
    @ManyToMany(mappedBy = "productEntities")
    private List<ProductTag> productTags;
    
    @ManyToMany
    @JoinColumn
    private List<WishList> wishLists;

    @ManyToOne(optional = true, cascade = CascadeType.REMOVE)
    @JoinColumn
    private CoordinatedOutfit coordinatedOutfit;
    
    @ManyToMany
    @JoinColumn(nullable = false)
    private List<ShoppingCart> shoppingcarts;
    
    @ManyToOne(optional = true)
    @JoinColumn
    private Promotion promotion; 
    
    @Column(nullable = true)
    private String picturePath;
    
    
    public ProductEntity() {
        productTags = new ArrayList<>();
        wishLists = new ArrayList<>();
        dateAdded = new Date();
        shoppingcarts = new ArrayList<>();
    }

    public ProductEntity(String productName, String description, BigDecimal unitPrice, Integer quantityOnHand, SizeEnum size, ColourEnum colour , String picturePath) {
        this();
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantityOnHand = quantityOnHand;
        this.sizeEnum = size;
        this.colourEnum = colour;
        this.picturePath = picturePath;
    }  
    
    
    public void addTag(ProductTag tagEntity)
    {
        if(tagEntity != null)
        {
            if(!this.productTags.contains(tagEntity))
            {
                this.productTags.add(tagEntity);
                
                if(!tagEntity.getProductEntities().contains(this))
                {                    
                    tagEntity.getProductEntities().add(this);
                }
            }
        }
    }
    
    public void removeTag(ProductTag tagEntity)
    {
        if(tagEntity != null)
        {
            if(this.productTags.contains(tagEntity))
            {
                this.productTags.remove(tagEntity);
                
                if(tagEntity.getProductEntities().contains(this))
                {                    
                    tagEntity.getProductEntities().remove(this);
                }
            }
        }
    }
    
    
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    
    public String getProductName() {
        return productName;
    }

  
    public void setProductName(String productName) {
        this.productName = productName;
    }

   
    public String getDescription() {
        return description;
    }

   
    public void setDescription(String description) {
        this.description = description;
    }

   
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

   
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    
    public Integer getQuantityOnHand() {
        return quantityOnHand;
    }

    
    public void setQuantityOnHand(Integer quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

   
    public Category getProductCategory() {
        return productCategory;
    }

    
    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    
    public List<ProductTag> getProductTags() {
        return productTags;
    }

   
    public void setProductTags(List<ProductTag> productTags) {
        this.productTags = productTags;
    }

   
    public CoordinatedOutfit getCoordinatedOutfit() {
        return coordinatedOutfit;
    }

   
    public void setCoordinatedOutfit(CoordinatedOutfit coordinatedOutfit) {
        this.coordinatedOutfit = coordinatedOutfit;
    }

    /**
     * @return the wishLists
     */
    public List<WishList> getWishLists() {
        return wishLists;
    }

    /**
     * @param wishLists the wishLists to set
     */
    public void setWishLists(List<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    /**
     * @return the sizeEnum
     */
    public SizeEnum getSizeEnum() {
        return sizeEnum;
    }

    /**
     * @param sizeEnum the sizeEnum to set
     */
    public void setSizeEnum(SizeEnum sizeEnum) {
        this.sizeEnum = sizeEnum;
    }

    public List<ShoppingCart> getShoppingcarts() {
        return shoppingcarts;
    }

    public void setShoppingcarts(List<ShoppingCart> shoppingcarts) {
        this.shoppingcarts = shoppingcarts;
    }

    public ColourEnum getColourEnum() {
        return colourEnum;
    }

    public void setColourEnum(ColourEnum colourEnum) {
        this.colourEnum = colourEnum;
    }

    /**
     * @return the promotion
     */
    public Promotion getPromotion() {
        return promotion;
    }

    
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    
    public String getPicturePath() {
        return picturePath;
    }

   
    public void setPicture(String picturePath) {
        this.picturePath = picturePath;
    }

    
}
