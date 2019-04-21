
package jsf.managedbean;


import ejb.stateless.PromotionControllerLocal;
import entity.Promotion;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named(value = "utilManagedBean")
@ViewScoped
public class UtilManagedBean implements Serializable{

    @EJB(name = "PromotionControllerLocal")
    private PromotionControllerLocal promotionControllerLocal;
    
    private List<Promotion> promotions;

    
    public UtilManagedBean() {
    }
    
    public void wake(){
        
    }
    
    @PostConstruct
    public void postConstruct() {
        promotions = promotionControllerLocal.retrieveAllPromotions();
        for(Promotion promo : promotions){
            System.out.println(promo.getPromotionName());
        }
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
