/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    
    @NotNull
    @Column(nullable = false)
    @OneToOne
    private Member cartOwner; 
    
    
    @ManyToMany(mappedBy = "shoppingCart")
    private List<ProductEntity> products; 

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public ShoppingCart(Member cartOwner) {
        this.cartOwner = cartOwner;
    }
    
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
    @Override
    public String toString() {
        return "entity.ShoppingCart[ id=" + cartId + " ]";
    }

    /**
     * @return the cartOwner
     */
    public Member getCartOwner() {
        return cartOwner;
    }

    /**
     * @param cartOwner the cartOwner to set
     */
    public void setCartOwner(Member cartOwner) {
        this.cartOwner = cartOwner;
    }

    /**
     * @return the products
     */
    public List<ProductEntity> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
    
}
