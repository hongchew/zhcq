
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ProductTag implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productTagId;
    
    @Column(nullable = false, length = 32)
    @NotNull
    @Size(min = 4, max = 32)
    private String productTagName;

    @ManyToMany 
    private List<ProductEntity> productEntities;
    
    public ProductTag() {
    }
    
    public ProductTag(String productTagName) {
        this();
        this.productTagName = productTagName; 
    }
    
    public Long getProductTagId() {
        return productTagId;
    }

    public void setProductTagId(Long productTagId) {
        this.productTagId = productTagId;
    }

    public String getProductTagName() {
        return productTagName;
    }

    public void setProductTagName(String productTagName) {
        this.productTagName = productTagName;
    }

    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }
    
    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }

    
    
}
