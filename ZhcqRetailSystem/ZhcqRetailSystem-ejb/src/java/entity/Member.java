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
import javax.validation.constraints.Size;

/**
 *
 * @author chengyang
 */
@Entity
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    
    @Column(nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    private String firstName;
    @Column(nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    private String lastName;    
    
    @Column(nullable = false, unique = true, length = 32)
    @NotNull
    @Size(min = 4, max = 32)
    private String username;
    // Updated in v4.4 to use CHAR instead of VARCHAR
    //@Column(nullable = false, length = 32)
    @Column(columnDefinition = "CHAR(32) NOT NULL")
    @NotNull
    // The following bean validation constraint is not applicable since we are only storing the password hashsum which is always 128 bit represented as 32 characters (16 hexadecimal digits)
    //@Size(min = 8, max = 32)
    private String password;
    
    @Column(columnDefinition = "CHAR(32) NOT NULL")
    private String salt;
    
    
    @OneToOne(cascade = CascadeType.REMOVE)
    private WishList wishList;
    
    @OneToMany
    private List<SaleTransaction> saleTransactions;

    public Member() {
        saleTransactions = new ArrayList<SaleTransaction>();
    }
    

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the wishList
     */
    public WishList getWishList() {
        return wishList;
    }

    /**
     * @param wishList the wishList to set
     */
    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    /**
     * @return the saleTransactions
     */
    public List<SaleTransaction> getSaleTransactions() {
        return saleTransactions;
    }

    /**
     * @param saleTransactions the saleTransactions to set
     */
    public void setSaleTransactions(List<SaleTransaction> saleTransactions) {
        this.saleTransactions = saleTransactions;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    
}
