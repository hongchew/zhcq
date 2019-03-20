/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author chengyang
 */
@Entity
public class SaleTransaction implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleTransactionId;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;
    
    @OneToMany(mappedBy="saleTransaction")
    private List<SaleTransactionLineItem> saleTransactionLineItems;
    

    public SaleTransaction() {
        saleTransactionLineItems = new ArrayList<SaleTransactionLineItem>();
    }
    
    

    public Long getSaleTransactionId() {
        return saleTransactionId;
    }

    public void setSaleTransactionId(Long saleTransactionId) {
        this.saleTransactionId = saleTransactionId;
    }
    
}
