
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ProductTag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productTagId;
    @Column(nullable = false, length = 32)
    @NotNull
    @Size(min = 4, max = 32)
    private String tagName;

    @ManyToMany 
    private ProductEntity productEntity;
    
    public ProductTag() {
    }
    
    public ProductTag(String tagName) {
        this();
        this.tagName = tagName; 
    }
    

    public Long getProductTagId() {
        return productTagId;
    }

    public void setProductTagId(Long productTagId) {
        this.productTagId = productTagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    
}
