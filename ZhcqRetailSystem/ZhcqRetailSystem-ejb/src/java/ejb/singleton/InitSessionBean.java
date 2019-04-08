
package ejb.singleton;

import ejb.stateless.CategoryControllerLocal;
import ejb.stateless.ProductControllerLocal;
import ejb.stateless.ProductTagControllerLocal;
import ejb.stateless.PromotionControllerLocal;
import ejb.stateless.StaffControllerLocal;
import entity.Category;
import entity.ProductEntity;
import entity.ProductTag;
import entity.Promotion;
import entity.Staff;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import util.enumeration.ColourEnum;
import util.enumeration.SizeEnum;
import util.exception.StaffNotFoundException;


@Singleton
@LocalBean
@Startup
public class InitSessionBean {

    @EJB(name = "PromotionControllerLocal")
    private PromotionControllerLocal promotionControllerLocal;

    @EJB(name = "ProductTagControllerLocal")
    private ProductTagControllerLocal productTagControllerLocal;

    @EJB(name = "ProductControllerLocal")
    private ProductControllerLocal productControllerLocal;

    @EJB(name = "CategoryControllerLocal")
    private CategoryControllerLocal categoryControllerLocal;

    @EJB(name = "StaffControllerLocal")
    private StaffControllerLocal staffControllerLocal;
    
    
    
    

    public InitSessionBean() {
    }

   @PostConstruct
   public void postConstruct()
   {
       try{
           staffControllerLocal.retrieveStaffByUsername("staff");
       } catch(StaffNotFoundException ex) {
           initializeData();
       }
   }
   
   private void initializeData()
   {
        try {
            staffControllerLocal.createNewStaff(new Staff("Admin", "Staff", "staff", "password"));
           
            Category dresses = categoryControllerLocal.createNewCategoryEntity(new Category("DRESSES","DRESSES"));
            Category tops = categoryControllerLocal.createNewCategoryEntity(new Category("TOPS","TOPS"));
            Category skirts = categoryControllerLocal.createNewCategoryEntity(new Category("SKIRTS","SKIRTS"));
            Category bottoms = categoryControllerLocal.createNewCategoryEntity(new Category("BOTTOMS","BOTTOMS"));
            
            ProductTag tagPopular = productTagControllerLocal.createNewProductTag(new ProductTag("POPULAR"));
            ProductTag tagPolkaDots = productTagControllerLocal.createNewProductTag(new ProductTag("POLKADOTS"));
            ProductTag tagFloral = productTagControllerLocal.createNewProductTag(new ProductTag("FLORAL"));
            ProductTag tagDenim = productTagControllerLocal.createNewProductTag(new ProductTag("DENIM"));
            ProductTag tagStripes = productTagControllerLocal.createNewProductTag(new ProductTag("STRIPES"));
            ProductTag tagPrints = productTagControllerLocal.createNewProductTag(new ProductTag("PRINTS"));
            ProductTag tagCheckered = productTagControllerLocal.createNewProductTag(new ProductTag("CHECKERED"));


            
            List<Long> tagIdsEmpty = new ArrayList<>();
            ProductEntity product1 = productControllerLocal.createNewProduct(new ProductEntity("Bardot Top", "100% Cotton", BigDecimal.valueOf(28.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/bardot_top_black.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            ProductEntity product2 = productControllerLocal.createNewProduct(new ProductEntity("Bardot Top", "100% Cotton", BigDecimal.valueOf(28.00), 20, SizeEnum.S, ColourEnum.CREAM,"images/bardot_top_cream.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Bardot Top", "100% Cotton", BigDecimal.valueOf(28.00), 20, SizeEnum.S, ColourEnum.PINK,"images/bardot_top_pink.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("Calista Dress", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/calista_dress_blue.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Calista Dress", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/calista_dress_white.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/canyon_top_black.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.S, ColourEnum.GREEN,"images/canyon_top_green.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.S, ColourEnum.PINK,"images/canyon_top_pink.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/canyon_top_white.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("Capulet Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/capulet_top_white.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Cherry Skirt", "100% Cotton", BigDecimal.valueOf(55.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/cherry_skirt_blue.jpg"),  skirts.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Cherry Skirt", "100% Cotton", BigDecimal.valueOf(55.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/cherry_skirt_white.jpg"), skirts.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("Christina Dress", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/christina_dress_black.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Christina Dress", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/christina_dress_blue.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Christina Dress", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.S, ColourEnum.YELLOW,"images/christina_dress_yellow.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("Christine Dress", "100% Cotton", BigDecimal.valueOf(65.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/christine_dress_black.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Christine Dress", "100% Cotton", BigDecimal.valueOf(65.00), 20, SizeEnum.S, ColourEnum.PINK,"images/christine_dress_pink.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Christine Dress", "100% Cotton", BigDecimal.valueOf(65.00), 20, SizeEnum.S, ColourEnum.RED,"images/christine_dress_red.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("Crimini Dress", "100% Cotton", BigDecimal.valueOf(89.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/crimini_dress_blue.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Crimini Dress", "100% Cotton", BigDecimal.valueOf(89.00), 20, SizeEnum.S, ColourEnum.RED,"images/crimini_dress_red.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Crimini Dress", "100% Cotton", BigDecimal.valueOf(89.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/crimini_dress_white.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
           
            productControllerLocal.createNewProduct(new ProductEntity("Cybill Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/cybill_dress_blue.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Cybill Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.S, ColourEnum.PINK,"images/cybill_dress_pink.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Cybill Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.S, ColourEnum.RED,"images/cybill_dress_red.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("Cynthia Jeans", "100% Cotton", BigDecimal.valueOf(100.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/cynthia_jeans_black.jpg"), bottoms.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Cynthia Jeans", "100% Cotton", BigDecimal.valueOf(100.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/cynthia_jeans_blue.jpg"), bottoms.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Cynthia Jeans", "100% Cotton", BigDecimal.valueOf(100.00), 20, SizeEnum.S, ColourEnum.CREAM,"images/cynthia_jeans_cream.jpg"), bottoms.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("Della Jumpsuit", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/della_jumpsuit_black.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Della Jumpsuit", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.S, ColourEnum.RED,"images/della_jumpsuit_red.jpg"), dresses.getCategoryId() , tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("Dixie Shorts", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/dixie_shorts_black.jpg"), bottoms.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Dixie Shorts", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/dixie_shorts_white.jpg"), bottoms.getCategoryId() ,tagIdsEmpty);
           
            productControllerLocal.createNewProduct(new ProductEntity("Eliana Top", "100% Cotton", BigDecimal.valueOf(55.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/eliana_top_white.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Emily Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/emily_top_black.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("Emily Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.S, ColourEnum.CREAM,"images/emily_top_cream.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            
            Promotion promotion = new Promotion("Promotion1", new BigDecimal(0.25), new Date(), new Date(119,11, 5));
            List<Long> productIds = new ArrayList<Long>();
            productIds.add(product1.getProductId());
            productIds.add(product2.getProductId());
            promotionControllerLocal.createNewPromotion(promotion, productIds);
            
        } catch (Exception ex) {
            System.err.println("********** DataInitializationSessionBean.initializeData(): " + ex.getMessage());
   
        }
   }
   
   
   
}
