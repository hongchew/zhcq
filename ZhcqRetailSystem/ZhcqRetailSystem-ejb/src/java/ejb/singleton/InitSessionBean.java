
package ejb.singleton;

import ejb.stateless.CategoryControllerLocal;
import ejb.stateless.CoordinatedOutfitControllerLocal;
import ejb.stateless.MemberControllerLocal;
import ejb.stateless.ProductControllerLocal;
import ejb.stateless.ProductTagControllerLocal;
import ejb.stateless.PromotionControllerLocal;
import ejb.stateless.StaffControllerLocal;
import entity.Category;
import entity.CoordinatedOutfit;
import entity.Member;
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
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.enumeration.ColourEnum;
import util.enumeration.SizeEnum;
import util.exception.CreateNewCategoryException;
import util.exception.CreateNewOutfitException;
import util.exception.CreateNewProductException;
import util.exception.CreateNewProductTagException;
import util.exception.CreatePromotionException;
import util.exception.InputDataValidationException;
import util.exception.ProductNotFoundException;
import util.exception.PromotionNotFoundException;
import util.exception.StaffNotFoundException;


@Singleton
@LocalBean
@Startup
public class InitSessionBean {

    @EJB(name = "CoordinatedOutfitControllerLocal")
    private CoordinatedOutfitControllerLocal coordinatedOutfitControllerLocal;

    @EJB
    private MemberControllerLocal memberControllerLocal;

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
    
    @PersistenceContext(unitName = "ZhcqRetailSystem-ejbPU")
    private EntityManager em;
    
    
    
    

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
            memberControllerLocal.createNewMember(new Member("Member", "Default", "member", "password", 0));
            
            Category dresses = categoryControllerLocal.createNewCategoryEntity(new Category("DRESSES","DRESSES"));
            Category tops = categoryControllerLocal.createNewCategoryEntity(new Category("TOPS","TOPS"));
            Category skirts = categoryControllerLocal.createNewCategoryEntity(new Category("SKIRTS","SKIRTS"));
            Category bottoms = categoryControllerLocal.createNewCategoryEntity(new Category("BOTTOMS","BOTTOMS"));
            
            ProductTag tagBasic = productTagControllerLocal.createNewProductTag(new ProductTag("BASIC"));
            ProductTag tagPopular = productTagControllerLocal.createNewProductTag(new ProductTag("POPULAR"));
            ProductTag tagPolkaDots = productTagControllerLocal.createNewProductTag(new ProductTag("POLKADOTS"));
            ProductTag tagFloral = productTagControllerLocal.createNewProductTag(new ProductTag("FLORAL"));
            ProductTag tagDenim = productTagControllerLocal.createNewProductTag(new ProductTag("DENIM"));
            ProductTag tagStripes = productTagControllerLocal.createNewProductTag(new ProductTag("STRIPES"));
            ProductTag tagPrints = productTagControllerLocal.createNewProductTag(new ProductTag("PRINTS"));
            ProductTag tagCheckered = productTagControllerLocal.createNewProductTag(new ProductTag("CHECKERED"));
            ProductTag tagLace = productTagControllerLocal.createNewProductTag(new ProductTag("LACE"));
            
            
            List<Long> tagIds = new ArrayList<>();
            tagIds.add(tagBasic.getProductTagId());
            tagIds.add(tagPopular.getProductTagId());
            ProductEntity product1 = productControllerLocal.createNewProduct(new ProductEntity("Bardot Top", "100% Cotton", BigDecimal.valueOf(28.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/bardot_top_black.jpg"), tops.getCategoryId() , tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Bardot Top", "100% Cotton", BigDecimal.valueOf(28.00), 20, SizeEnum.S, ColourEnum.CREAM,"images/bardot_top_cream.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Bardot Top", "100% Cotton", BigDecimal.valueOf(28.00), 20, SizeEnum.S, ColourEnum.PINK,"images/bardot_top_pink.jpg"), tops.getCategoryId() ,tagIds);
            
            ProductEntity product2 = productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/canyon_top_black.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.S, ColourEnum.GREEN,"images/canyon_top_green.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.S, ColourEnum.PINK,"images/canyon_top_pink.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/canyon_top_white.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.M, ColourEnum.BLACK,"images/canyon_top_black.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.M, ColourEnum.GREEN,"images/canyon_top_green.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.M, ColourEnum.PINK,"images/canyon_top_pink.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.M, ColourEnum.WHITE,"images/canyon_top_white.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.L, ColourEnum.BLACK,"images/canyon_top_black.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.L, ColourEnum.GREEN,"images/canyon_top_green.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.L, ColourEnum.PINK,"images/canyon_top_pink.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Canyon Top", "100% Cotton", BigDecimal.valueOf(30.00), 20, SizeEnum.L, ColourEnum.WHITE,"images/canyon_top_white.jpg"), tops.getCategoryId() ,tagIds);
            
            ProductEntity product3 = productControllerLocal.createNewProduct(new ProductEntity("Capulet Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/capulet_top_white.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Capulet Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.M, ColourEnum.WHITE,"images/capulet_top_white.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Capulet Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.L, ColourEnum.WHITE,"images/capulet_top_white.jpg"), tops.getCategoryId() ,tagIds);
            
            ProductEntity product4 = productControllerLocal.createNewProduct(new ProductEntity("Cherry Skirt", "100% Cotton", BigDecimal.valueOf(55.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/cherry_skirt_blue.jpg"),  skirts.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cherry Skirt", "100% Cotton", BigDecimal.valueOf(55.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/cherry_skirt_white.jpg"), skirts.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cherry Skirt", "100% Cotton", BigDecimal.valueOf(55.00), 20, SizeEnum.M, ColourEnum.BLUE,"images/cherry_skirt_blue.jpg"),  skirts.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cherry Skirt", "100% Cotton", BigDecimal.valueOf(55.00), 20, SizeEnum.M, ColourEnum.WHITE,"images/cherry_skirt_white.jpg"), skirts.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cherry Skirt", "100% Cotton", BigDecimal.valueOf(55.00), 20, SizeEnum.L, ColourEnum.BLUE,"images/cherry_skirt_blue.jpg"),  skirts.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cherry Skirt", "100% Cotton", BigDecimal.valueOf(55.00), 20, SizeEnum.L, ColourEnum.WHITE,"images/cherry_skirt_white.jpg"), skirts.getCategoryId() ,tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Christina Dress", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/christina_dress_black.jpg"), dresses.getCategoryId() ,tagIds);
            ProductEntity product5 = productControllerLocal.createNewProduct(new ProductEntity("Christina Dress", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/christina_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christina Dress", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.S, ColourEnum.YELLOW,"images/christina_dress_yellow.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christina Dress", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.M, ColourEnum.BLACK,"images/christina_dress_black.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christina Dress", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.M, ColourEnum.BLUE,"images/christina_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christina Dress", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.M, ColourEnum.YELLOW,"images/christina_dress_yellow.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christina Dress", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.L, ColourEnum.BLACK,"images/christina_dress_black.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christina Dress", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.L, ColourEnum.BLUE,"images/christina_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christina Dress", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.L, ColourEnum.YELLOW,"images/christina_dress_yellow.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christine Dress", "100% Cotton", BigDecimal.valueOf(65.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/christine_dress_black.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christine Dress", "100% Cotton", BigDecimal.valueOf(65.00), 20, SizeEnum.S, ColourEnum.RED,"images/christine_dress_red.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christine Dress", "100% Cotton", BigDecimal.valueOf(65.00), 20, SizeEnum.M, ColourEnum.BLACK,"images/christine_dress_black.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christine Dress", "100% Cotton", BigDecimal.valueOf(65.00), 20, SizeEnum.M, ColourEnum.RED,"images/christine_dress_red.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christine Dress", "100% Cotton", BigDecimal.valueOf(65.00), 20, SizeEnum.L, ColourEnum.BLACK,"images/christine_dress_black.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christine Dress", "100% Cotton", BigDecimal.valueOf(65.00), 20, SizeEnum.L, ColourEnum.RED,"images/christine_dress_red.jpg"), dresses.getCategoryId() ,tagIds);
            
            ProductEntity product6 = productControllerLocal.createNewProduct(new ProductEntity("Calista Dress", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/calista_dress_white.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Calista Dress", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.M, ColourEnum.WHITE,"images/calista_dress_white.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Calista Dress", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.L, ColourEnum.WHITE,"images/calista_dress_white.jpg"), dresses.getCategoryId() ,tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Della Jumpsuit", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/della_jumpsuit_black.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Della Jumpsuit", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.M, ColourEnum.BLACK,"images/della_jumpsuit_black.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Della Jumpsuit", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.L, ColourEnum.BLACK,"images/della_jumpsuit_black.jpg"), dresses.getCategoryId() ,tagIds);
            
            ProductEntity product7 = productControllerLocal.createNewProduct(new ProductEntity("Crimini Dress", "100% Cotton", BigDecimal.valueOf(89.00), 20, SizeEnum.S, ColourEnum.RED,"images/crimini_dress_red.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Crimini Dress", "100% Cotton", BigDecimal.valueOf(89.00), 20, SizeEnum.M, ColourEnum.RED,"images/crimini_dress_red.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Crimini Dress", "100% Cotton", BigDecimal.valueOf(89.00), 20, SizeEnum.L, ColourEnum.RED,"images/crimini_dress_red.jpg"), dresses.getCategoryId() ,tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Emmie Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/emmie_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            ProductEntity product8 = productControllerLocal.createNewProduct(new ProductEntity("Emmie Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.S, ColourEnum.RED,"images/emmie_dress_red.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Emmie Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.M, ColourEnum.RED,"images/emmie_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Eliana Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/eliana_top_white.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Eliana Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.M, ColourEnum.WHITE,"images/eliana_top_white.jpg"), tops.getCategoryId() ,tagIds);
            ProductEntity product9 = productControllerLocal.createNewProduct(new ProductEntity("Eliana Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.L, ColourEnum.WHITE,"images/eliana_top_white.jpg"), tops.getCategoryId() ,tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Finnegan Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/finnegan_top_white.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Finnegan Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.M, ColourEnum.WHITE,"images/finnegan_top_white.jpg"), tops.getCategoryId() ,tagIds);
            ProductEntity product10 = productControllerLocal.createNewProduct(new ProductEntity("Finnegan Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.L, ColourEnum.WHITE,"images/finnegan_top_white.jpg"), tops.getCategoryId() ,tagIds);
           
            productControllerLocal.createNewProduct(new ProductEntity("Flora Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/flora_top_black.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Flora Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.M, ColourEnum.BLACK,"images/flora_top_black.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Flora Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.L, ColourEnum.BLACK,"images/flora_top_black.jpg"), tops.getCategoryId() ,tagIds);
            ProductEntity product11 =  productControllerLocal.createNewProduct(new ProductEntity("Flora Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.S, ColourEnum.CREAM,"images/flora_top_cream.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Flora Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.M, ColourEnum.CREAM,"images/flora_top_cream.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Flora Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.L, ColourEnum.CREAM,"images/flora_top_cream.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Flora Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.S, ColourEnum.RED,"images/flora_top_red.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Flora Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.M, ColourEnum.RED,"images/flora_top_red.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Flora Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.L, ColourEnum.RED,"images/flora_top_red.jpg"), tops.getCategoryId() ,tagIds);
           
            productControllerLocal.createNewProduct(new ProductEntity("Tanner Pant", "100% Cotton", BigDecimal.valueOf(99.00), 20, SizeEnum.S, ColourEnum.CREAM,"images/tanner_pant_cream.jpg"), bottoms.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Tanner Pant", "100% Cotton", BigDecimal.valueOf(99.00), 20, SizeEnum.M, ColourEnum.CREAM,"images/tanner_pant_cream.jpg"), bottoms.getCategoryId(),tagIds);
            ProductEntity product12 = productControllerLocal.createNewProduct(new ProductEntity("Tanner Pant", "100% Cotton", BigDecimal.valueOf(99.00), 20, SizeEnum.L, ColourEnum.CREAM,"images/tanner_pant_cream.jpg"), bottoms.getCategoryId(),tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Overland Top", "100% Cotton", BigDecimal.valueOf(99.00), 20, SizeEnum.L, ColourEnum.BLACK,"images/overland_top_black.jpg"), tops.getCategoryId(),tagIds);
            ProductEntity product13 = productControllerLocal.createNewProduct(new ProductEntity("Overland Top", "100% Cotton", BigDecimal.valueOf(99.00), 20, SizeEnum.M, ColourEnum.BLACK,"images/overland_top_black.jpg"), tops.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Overland Top", "100% Cotton", BigDecimal.valueOf(99.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/overland_top_black.jpg"), tops.getCategoryId(),tagIds);
            
            ProductEntity product14 = productControllerLocal.createNewProduct(new ProductEntity("Ingrid Dress", "100% Cotton", BigDecimal.valueOf(109.00), 20, SizeEnum.S, ColourEnum.PINK,"images/ingrid_dress_pink.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Ingrid Dress", "100% Cotton", BigDecimal.valueOf(109.00), 20, SizeEnum.L, ColourEnum.PINK,"images/ingrid_dress_pink.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Ingrid Dress", "100% Cotton", BigDecimal.valueOf(109.00), 20, SizeEnum.M, ColourEnum.PINK,"images/ingrid_dress_pink.jpg"), dresses.getCategoryId(),tagIds);

            tagIds = new ArrayList<>();
            tagIds.add(tagCheckered.getProductTagId());
            tagIds.add(tagPopular.getProductTagId());
            productControllerLocal.createNewProduct(new ProductEntity("Calista Dress", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/calista_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Calista Dress", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.M, ColourEnum.BLUE,"images/calista_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Calista Dress", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.L, ColourEnum.BLUE,"images/calista_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Simi Dress", "100% Cotton", BigDecimal.valueOf(76.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/simi_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
            ProductEntity product15 = productControllerLocal.createNewProduct(new ProductEntity("Simi Dress", "100% Cotton", BigDecimal.valueOf(76.00), 20, SizeEnum.M, ColourEnum.BLUE,"images/simi_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Simi Dress", "100% Cotton", BigDecimal.valueOf(76.00), 20, SizeEnum.L, ColourEnum.BLUE,"images/simi_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
           
            productControllerLocal.createNewProduct(new ProductEntity("Tori Dress", "100% Cotton", BigDecimal.valueOf(86.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/tori_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Tori Dress", "100% Cotton", BigDecimal.valueOf(86.00), 20, SizeEnum.M, ColourEnum.BLUE,"images/tori_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Tori Dress", "100% Cotton", BigDecimal.valueOf(86.00), 20, SizeEnum.L, ColourEnum.BLUE,"images/tori_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Velma Dress", "100% Cotton", BigDecimal.valueOf(86.00), 20, SizeEnum.S, ColourEnum.GREEN,"images/velma_dress_green.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Velma Dress", "100% Cotton", BigDecimal.valueOf(86.00), 20, SizeEnum.M, ColourEnum.GREEN,"images/velma_dress_green.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Velma Dress", "100% Cotton", BigDecimal.valueOf(86.00), 20, SizeEnum.L, ColourEnum.GREEN,"images/velma_dress_green.jpg"), dresses.getCategoryId(),tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Vikki Dress", "100% Cotton", BigDecimal.valueOf(79.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/vikki_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Vikki Dress", "100% Cotton", BigDecimal.valueOf(79.00), 20, SizeEnum.M, ColourEnum.BLUE,"images/vikki_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Vikki Dress", "100% Cotton", BigDecimal.valueOf(79.00), 20, SizeEnum.L, ColourEnum.BLUE,"images/vikki_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
           
            productControllerLocal.createNewProduct(new ProductEntity("Tanner Pant", "100% Cotton", BigDecimal.valueOf(99.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/tanner_pant_black.jpg"), bottoms.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Tanner Pant", "100% Cotton", BigDecimal.valueOf(99.00), 20, SizeEnum.M, ColourEnum.BLACK,"images/tanner_pant_black.jpg"), bottoms.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Tanner Pant", "100% Cotton", BigDecimal.valueOf(99.00), 20, SizeEnum.L, ColourEnum.BLACK,"images/tanner_pant_black.jpg"), bottoms.getCategoryId(),tagIds);
           
            
            tagIds = new ArrayList<>();
            tagIds.add(tagDenim.getProductTagId());
            tagIds.add(tagBasic.getProductTagId());
            tagIds.add(tagCheckered.getProductTagId());
            productControllerLocal.createNewProduct(new ProductEntity("Cynthia Jeans", "100% Cotton", BigDecimal.valueOf(100.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/cynthia_jeans_black.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cynthia Jeans", "100% Cotton", BigDecimal.valueOf(100.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/cynthia_jeans_blue.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cynthia Jeans", "100% Cotton", BigDecimal.valueOf(100.00), 20, SizeEnum.S, ColourEnum.CREAM,"images/cynthia_jeans_cream.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cynthia Jeans", "100% Cotton", BigDecimal.valueOf(100.00), 20, SizeEnum.M, ColourEnum.BLACK,"images/cynthia_jeans_black.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cynthia Jeans", "100% Cotton", BigDecimal.valueOf(100.00), 20, SizeEnum.M, ColourEnum.BLUE,"images/cynthia_jeans_blue.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cynthia Jeans", "100% Cotton", BigDecimal.valueOf(100.00), 20, SizeEnum.M, ColourEnum.CREAM,"images/cynthia_jeans_cream.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cynthia Jeans", "100% Cotton", BigDecimal.valueOf(100.00), 20, SizeEnum.L, ColourEnum.BLACK,"images/cynthia_jeans_black.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cynthia Jeans", "100% Cotton", BigDecimal.valueOf(100.00), 20, SizeEnum.L, ColourEnum.BLUE,"images/cynthia_jeans_blue.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cynthia Jeans", "100% Cotton", BigDecimal.valueOf(100.00), 20, SizeEnum.L, ColourEnum.CREAM,"images/cynthia_jeans_cream.jpg"), bottoms.getCategoryId() ,tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Dixie Shorts", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.XS, ColourEnum.BLACK,"images/dixie_shorts_black.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Dixie Shorts", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.XS, ColourEnum.WHITE,"images/dixie_shorts_white.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Dixie Shorts", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.L, ColourEnum.WHITE,"images/dixie_shorts_white.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Dixie Shorts", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.L, ColourEnum.BLACK,"images/dixie_shorts_black.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Dixie Shorts", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.XL, ColourEnum.BLACK,"images/dixie_shorts_black.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Dixie Shorts", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.XL, ColourEnum.BLUE,"images/dixie_shorts_blue.jpg"), bottoms.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Dixie Shorts", "100% Cotton", BigDecimal.valueOf(60.00), 20, SizeEnum.XS, ColourEnum.BLUE,"images/dixie_shorts_blue.jpg"), bottoms.getCategoryId() ,tagIds);
            
            tagIds = new ArrayList<>();
            tagIds.add(tagFloral.getProductTagId());
            tagIds.add(tagPrints.getProductTagId());
            productControllerLocal.createNewProduct(new ProductEntity("Christine Dress", "100% Cotton", BigDecimal.valueOf(65.00), 20, SizeEnum.S, ColourEnum.PINK,"images/christine_dress_pink.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christine Dress", "100% Cotton", BigDecimal.valueOf(65.00), 20, SizeEnum.M, ColourEnum.PINK,"images/christine_dress_pink.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Christine Dress", "100% Cotton", BigDecimal.valueOf(65.00), 20, SizeEnum.L, ColourEnum.PINK,"images/christine_dress_pink.jpg"), dresses.getCategoryId() ,tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Crimini Dress", "100% Cotton", BigDecimal.valueOf(89.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/crimini_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Crimini Dress", "100% Cotton", BigDecimal.valueOf(89.00), 20, SizeEnum.M, ColourEnum.BLUE,"images/crimini_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Crimini Dress", "100% Cotton", BigDecimal.valueOf(89.00), 20, SizeEnum.L, ColourEnum.BLUE,"images/crimini_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Crimini Dress", "100% Cotton", BigDecimal.valueOf(89.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/crimini_dress_white.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Crimini Dress", "100% Cotton", BigDecimal.valueOf(89.00), 20, SizeEnum.M, ColourEnum.WHITE,"images/crimini_dress_white.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Crimini Dress", "100% Cotton", BigDecimal.valueOf(89.00), 20, SizeEnum.L, ColourEnum.WHITE,"images/crimini_dress_white.jpg"), dresses.getCategoryId() ,tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Cybill Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/cybill_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cybill Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.S, ColourEnum.PINK,"images/cybill_dress_pink.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cybill Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.S, ColourEnum.RED,"images/cybill_dress_red.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cybill Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.M, ColourEnum.BLUE,"images/cybill_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cybill Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.M, ColourEnum.PINK,"images/cybill_dress_pink.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cybill Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.M, ColourEnum.RED,"images/cybill_dress_red.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cybill Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.L, ColourEnum.BLUE,"images/cybill_dress_blue.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cybill Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.L, ColourEnum.PINK,"images/cybill_dress_pink.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Cybill Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.L, ColourEnum.RED,"images/cybill_dress_red.jpg"), dresses.getCategoryId() ,tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Emmie Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/emmie_dress_white.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Emmie Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.M, ColourEnum.WHITE,"images/emmie_dress_white.jpg"), dresses.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Emmie Dress", "100% Cotton", BigDecimal.valueOf(87.00), 20, SizeEnum.L, ColourEnum.WHITE,"images/emmie_dress_white.jpg"), dresses.getCategoryId() ,tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Toulouse Dress", "100% Cotton", BigDecimal.valueOf(86.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/toulouse_dress_white.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Toulouse Dress", "100% Cotton", BigDecimal.valueOf(86.00), 20, SizeEnum.M, ColourEnum.WHITE,"images/toulouse_dress_white.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Toulouse Dress", "100% Cotton", BigDecimal.valueOf(86.00), 20, SizeEnum.L, ColourEnum.WHITE,"images/toulouse_dress_white.jpg"), dresses.getCategoryId(),tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Vikki Dress", "100% Cotton", BigDecimal.valueOf(79.00), 20, SizeEnum.S, ColourEnum.RED,"images/vikki_dress_red.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Vikki Dress", "100% Cotton", BigDecimal.valueOf(79.00), 20, SizeEnum.M, ColourEnum.RED,"images/vikki_dress_red.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Vikki Dress", "100% Cotton", BigDecimal.valueOf(79.00), 20, SizeEnum.L, ColourEnum.RED,"images/vikki_dress_red.jpg"), dresses.getCategoryId(),tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Overland Top", "100% Cotton", BigDecimal.valueOf(69.00), 20, SizeEnum.L, ColourEnum.WHITE,"images/overland_top_white.jpg"), tops.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Overland Top", "100% Cotton", BigDecimal.valueOf(69.00), 20, SizeEnum.M, ColourEnum.WHITE,"images/overland_top_white.jpg"), tops.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Overland Top", "100% Cotton", BigDecimal.valueOf(69.00), 20, SizeEnum.S, ColourEnum.WHITE,"images/overland_top_white.jpg"), tops.getCategoryId(),tagIds);
            
            productControllerLocal.createNewProduct(new ProductEntity("Ingrid Dress", "100% Cotton", BigDecimal.valueOf(109.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/ingrid_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Ingrid Dress", "100% Cotton", BigDecimal.valueOf(109.00), 20, SizeEnum.M, ColourEnum.BLUE,"images/ingrid_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Ingrid Dress", "100% Cotton", BigDecimal.valueOf(109.00), 20, SizeEnum.L, ColourEnum.BLUE,"images/ingrid_dress_blue.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Ingrid Dress", "100% Cotton", BigDecimal.valueOf(109.00), 20, SizeEnum.S, ColourEnum.CREAM,"images/ingrid_dress_cream.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Ingrid Dress", "100% Cotton", BigDecimal.valueOf(109.00), 20, SizeEnum.M, ColourEnum.CREAM,"images/ingrid_dress_cream.jpg"), dresses.getCategoryId(),tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Ingrid Dress", "100% Cotton", BigDecimal.valueOf(109.00), 20, SizeEnum.L, ColourEnum.CREAM,"images/ingrid_dress_cream.jpg"), dresses.getCategoryId(),tagIds);

            
            tagIds = new ArrayList<>();
            tagIds.add(tagPolkaDots.getProductTagId());
            tagIds.add(tagFloral.getProductTagId());
            productControllerLocal.createNewProduct(new ProductEntity("Della Jumpsuit", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.S, ColourEnum.RED,"images/della_jumpsuit_red.jpg"), dresses.getCategoryId() , tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Della Jumpsuit", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.M, ColourEnum.RED,"images/della_jumpsuit_red.jpg"), dresses.getCategoryId() , tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Della Jumpsuit", "100% Cotton", BigDecimal.valueOf(80.00), 20, SizeEnum.L, ColourEnum.RED,"images/della_jumpsuit_red.jpg"), dresses.getCategoryId() , tagIds);
           
            tagIds = new ArrayList<>();
            tagIds.add(tagStripes.getProductTagId());
            tagIds.add(tagPrints.getProductTagId());
            productControllerLocal.createNewProduct(new ProductEntity("Finnegan Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.S, ColourEnum.RED,"images/finnegan_top_red.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Finnegan Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.M, ColourEnum.RED,"images/finnegan_top_red.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Finnegan Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.L, ColourEnum.RED,"images/finnegan_top_red.jpg"), tops.getCategoryId() ,tagIds);
            
            
            tagIds = new ArrayList<>();
            tagIds.add(tagLace.getProductTagId());
            tagIds.add(tagFloral.getProductTagId());
            productControllerLocal.createNewProduct(new ProductEntity("Emily Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.S, ColourEnum.BLACK,"images/emily_top_black.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Emily Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.S, ColourEnum.CREAM,"images/emily_top_cream.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Emily Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.XXS, ColourEnum.BLACK,"images/emily_top_black.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Emily Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.XXS, ColourEnum.CREAM,"images/emily_top_cream.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Emily Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.XXL, ColourEnum.BLACK,"images/emily_top_black.jpg"), tops.getCategoryId() ,tagIds);
            productControllerLocal.createNewProduct(new ProductEntity("Emily Top", "100% Cotton", BigDecimal.valueOf(50.00), 20, SizeEnum.XXL, ColourEnum.CREAM,"images/emily_top_cream.jpg"), tops.getCategoryId() ,tagIds);
            
            Promotion promotion = new Promotion("Promotion1", new BigDecimal(0.25), new Date(), new Date(119,11, 5));
            List<Long> productIds = new ArrayList<>();
            productIds.add(product1.getProductId());
            productIds.add(product2.getProductId());
            productIds.add(product3.getProductId());    
            productIds.add(product4.getProductId());
            productIds.add(product5.getProductId());
            promotionControllerLocal.createNewPromotion(promotion, productIds);
            
            promotion = new Promotion("Promotion 2", new BigDecimal(0.25), new Date(), new Date(119,4,17));
            productIds = new ArrayList<>();
            productIds.add(product6.getProductId());
            productIds.add(product7.getProductId());
            productIds.add(product8.getProductId());
            productIds.add(product9.getProductId());
            productIds.add(product10.getProductId());
            promotionControllerLocal.createNewPromotion(promotion, productIds);
            
            promotion = new Promotion("Promotion 3", new BigDecimal(0.5), new Date(), new Date(119,5,1));
            productIds = new ArrayList<>();
            productIds.add(product11.getProductId());
            productIds.add(product12.getProductId());
            productIds.add(product13.getProductId());
            productIds.add(product14.getProductId());
            productIds.add(product15.getProductId());
            promotionControllerLocal.createNewPromotion(promotion, productIds);
            
            
            CoordinatedOutfit outfit1 = new CoordinatedOutfit("SPRING LOOK","Seasonal Colours With radiant look","/");
            productIds = new ArrayList<>();
            productIds.add(product1.getProductId());
            productIds.add(product5.getProductId());
            productIds.add(product10.getProductId());
            coordinatedOutfitControllerLocal.createNewOutfit(outfit1, productIds,new Date());
            
  
            CoordinatedOutfit outfit2 = new CoordinatedOutfit("SUMMER LOOK","Seasonal Colours With radiant look","/");
            productIds = new ArrayList<>();
            productIds.add(product2.getProductId());
            productIds.add(product6.getProductId());
            productIds.add(product11.getProductId());
            coordinatedOutfitControllerLocal.createNewOutfit(outfit2, productIds,new Date());
            
            CoordinatedOutfit outfit3 = new CoordinatedOutfit("AUTUMN LOOK","Seasonal Colours With radiant look","/");
            productIds = new ArrayList<>();
            productIds.add(product3.getProductId());
            productIds.add(product7.getProductId());
            productIds.add(product12.getProductId());
            coordinatedOutfitControllerLocal.createNewOutfit(outfit3, productIds,new Date());
            
            CoordinatedOutfit outfit4 = new CoordinatedOutfit("WINTER LOOK","Seasonal Colours With radiant look","/");
            productIds = new ArrayList<>();
            productIds.add(product4.getProductId());
            productIds.add(product8.getProductId());
            productIds.add(product13.getProductId());
            coordinatedOutfitControllerLocal.createNewOutfit(outfit4, productIds,new Date());
            
            
            Member member = new Member("member", "1", "member1", "password", 0);
            memberControllerLocal.createNewMember(member);
            Member member2 = new Member("member", "2", "member2", "password", 0);
            memberControllerLocal.createNewMember(member2);
            Member member3 = new Member("member", "3", "member3", "password", 0);
            memberControllerLocal.createNewMember(member3);
            
           
            
        } catch (CreateNewCategoryException | CreateNewOutfitException | CreateNewProductException | CreateNewProductTagException | CreatePromotionException | InputDataValidationException | ProductNotFoundException ex) {
            System.err.println("********** DataInitializationSessionBean.initializeData(): " + ex.getMessage());
   
        }
   }

    public void persist(Object object) {
        em.persist(object);
    }
   
    @Schedule
    public void deleteOldPromotion(){
        Date today = new Date();
        Query query = em.createQuery("SELECT p FROM Promotion p WHERE p.endDate < :today");
        query.setParameter("today", today);
        
        List<Promotion> oldPromo = query.getResultList();
        
        for(Promotion promo : oldPromo){
            try {
                promotionControllerLocal.deletePromotion(promo.getPromotionId());
            } catch (PromotionNotFoundException ex) {
                System.err.println("Promotion not found");
            }
        }    
    }
   
}
