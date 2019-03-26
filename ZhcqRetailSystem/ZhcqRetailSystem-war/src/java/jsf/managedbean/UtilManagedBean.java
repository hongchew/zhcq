
package jsf.managedbean;


import ejb.stateless.PromotionControllerLocal;
import entity.Promotion;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named(value = "utilManagedBean")
@RequestScoped
public class UtilManagedBean {

    @EJB(name = "PromotionControllerLocal")
    private PromotionControllerLocal promotionControllerLocal;
    
    private List<Promotion> promotions;

    
    public UtilManagedBean() {
    }
    
    public void postConstruct() {
        promotions = promotionControllerLocal.retrieveAllPromotions();
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
    
    public String formatDate(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
        return simpleDateFormat.format(date);
    }
}
