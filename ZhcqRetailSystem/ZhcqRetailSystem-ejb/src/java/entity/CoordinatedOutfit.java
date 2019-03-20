/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
public class CoordinatedOutfit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coordinatedOutfitId;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @NotNull
    private Date dateCreated;

    public CoordinatedOutfit() {
    }

    public CoordinatedOutfit(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }


}
