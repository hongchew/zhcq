/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.SaleTransaction;
import java.util.List;

/**
 *
 * @author Hong Chew
 */
public class RetrieveSalesTransactionByUserIdRsp {
    private List<SaleTransaction> salesTransactions;

    public RetrieveSalesTransactionByUserIdRsp() {
    }

    public RetrieveSalesTransactionByUserIdRsp(List<SaleTransaction> salesTransactions) {
        this.salesTransactions = salesTransactions;
    }
    
    
    
    /**
     * @return the salesTransactions
     */
    public List<SaleTransaction> getSalesTransactions() {
        return salesTransactions;
    }

    /**
     * @param salesTransactions the salesTransactions to set
     */
    public void setSalesTransactions(List<SaleTransaction> salesTransactions) {
        this.salesTransactions = salesTransactions;
    }
    
    
}
