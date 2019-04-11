/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.SaleTransaction;

/**
 *
 * @author Hong Chew
 */
public class CheckoutRsp {
    
    private SaleTransaction txn;

    public CheckoutRsp() {
    }

    public CheckoutRsp(SaleTransaction txn) {
        this.txn = txn;
    }

    /**
     * @return the txn
     */
    public SaleTransaction getTxn() {
        return txn;
    }

    /**
     * @param txn the txn to set
     */
    public void setTxn(SaleTransaction txn) {
        this.txn = txn;
    }
    
    
}
