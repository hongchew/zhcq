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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    
    @NotNull
    @Column(nullable = false)
    @OneToOne(cascade = CascadeType.REMOVE)
    private Member cartOwner; 
    
    
    @OneToMany(mappedBy = "shoppingCart")
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

    public Member getCartOwner() {
        return cartOwner;
    }

    public void setCartOwner(Member cartOwner) {
        this.cartOwner = cartOwner;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
    
}
