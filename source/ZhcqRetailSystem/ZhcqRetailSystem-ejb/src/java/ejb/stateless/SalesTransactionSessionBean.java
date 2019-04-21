/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.SaleTransaction;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hong Chew
 */
@Stateless
public class SalesTransactionSessionBean implements SalesTransactionSessionBeanLocal {

    @PersistenceContext(unitName = "ZhcqRetailSystem-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<SaleTransaction> retrieveAllSalesTransactionByUserId(Long userId){
        Query q = em.createQuery("select st from SaleTransaction st where st.member.memberId = :userId");
        q.setParameter("userId", userId);
        
        return q.getResultList();
        
    }
}
