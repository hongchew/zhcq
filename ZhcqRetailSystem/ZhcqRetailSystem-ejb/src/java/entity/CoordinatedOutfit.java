/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
public class CoordinatedOutfit implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coordinatedOutfitId;
    
    @Column(nullable = false, length = 64)
    @NotNull
    @Size(max = 64)
    private String outfitName;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateCreated;
    
    @Column(nullable = true)
    private String picturePath; 
    
    @Column(length = 128)
    @Size(max = 128)
    private String description;
    
    
    @OneToMany(mappedBy = "coordinatedOutfit")
    private List<ProductEntity> productEntities;
   

    public CoordinatedOutfit() {
        productEntities = new ArrayList<ProductEntity>();
    }


    public CoordinatedOutfit(String outfitName, String description, String picturePath) {
        this();
        this.outfitName = outfitName;
        this.picturePath = picturePath;
        this.description = description;
    }
    
    
    
    public void addProduct(ProductEntity product){
        if(product != null)
        {
            if(!this.productEntities.contains(product))
            {
                this.productEntities.add(product);
                product.setCoordinatedOutfit(this);
                
            }
            
        }
    }
    
    public void removeProduct(ProductEntity product){
        if(product != null)
        {
            if(!this.productEntities.contains(product))
            {
                this.productEntities.remove(product);
                product.setCoordinatedOutfit(null);
                
            }
            
        }
    }
    
    

    public Long getCoordinatedOutfitId() {
        return coordinatedOutfitId;
    }

    public void setCoordinatedOutfitId(Long coordinatedOutfitId) {
        this.coordinatedOutfitId = coordinatedOutfitId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the outfitName
     */
    public String getOutfitName() {
        return outfitName;
    }

    /**
     * @param outfitName the outfitName to set
     */
    public void setOutfitName(String outfitName) {
        this.outfitName = outfitName;
    }

    /**
     * @return the picturePath
     */
    public String getPicturePath() {
        return picturePath;
    }

    /**
     * @param picturePath the picturePath to set
     */
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


}
