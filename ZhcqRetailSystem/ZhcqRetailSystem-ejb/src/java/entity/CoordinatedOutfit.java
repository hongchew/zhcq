/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author chengyang
 */
@Entity
public class CoordinatedOutfit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coordinatedOutfitId;
    private Date dateCreated;

    public CoordinatedOutfit() {
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
