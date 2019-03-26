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
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.ManyToOne;
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
    
    @Column
    private SizeEnum sizeEnum;
    
    @Column
    private String color;
    
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
    private SizeEnum size ;
    
    @Column(nullable = false)
    @NotNull
    private String colour;
    
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
    
    
    public ProductEntity() {
        productTags = new ArrayList<>();
        wishLists = new ArrayList<WishList>();
    }

    public ProductEntity(String productName, String description, BigDecimal unitPrice, Date dateAdded, Integer quantityOnHand, SizeEnum size, String Colour) {
        this();
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.dateAdded = dateAdded;
        this.quantityOnHand = quantityOnHand;
        this.size = size;
        this.Colour = Colour;
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
            if(!this.productTags.contains(tagEntity))
            {
                this.productTags.remove(tagEntity);
                
                if(!tagEntity.getProductEntities().contains(this))
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
     * @return the size
     */
    public SizeEnum getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(SizeEnum size) {
        this.size = size;
    }

    /**
     * @return the Colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * @param Colour the Colour to set
     */
    public void setColour(String Colour) {
        this.colour = Colour;
    }

    /**
     * @return the shoppingcarts
     */
    public List<ShoppingCart> getShoppingcarts() {
        return shoppingcarts;
    }

    /**
     * @param shoppingcarts the shoppingcarts to set
     */
    public void setShoppingcarts(List<ShoppingCart> shoppingcarts) {
        this.shoppingcarts = shoppingcarts;
    }

    

  
    
}
