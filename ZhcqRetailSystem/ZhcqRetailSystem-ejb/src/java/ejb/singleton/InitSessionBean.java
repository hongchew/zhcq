/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.singleton;

import ejb.stateless.CategoryControllerLocal;
import ejb.stateless.ProductControllerLocal;
import ejb.stateless.ProductTagControllerLocal;
import ejb.stateless.StaffControllerLocal;
import entity.Category;
import entity.ProductEntity;
import entity.ProductTag;
import entity.Staff;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import util.enumeration.ColourEnum;
import util.enumeration.SizeEnum;
import util.exception.CreateNewCategoryException;
import util.exception.CreateNewProductException;
import util.exception.CreateNewProductTagException;
import util.exception.InputDataValidationException;
import util.exception.StaffNotFoundException;


@Singleton
@LocalBean
@Startup
public class InitSessionBean {

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
            
            Category newProd = categoryControllerLocal.createNewCategoryEntity(new Category("NEW IN","NEW IN"));
            Category classicProd = categoryControllerLocal.createNewCategoryEntity(new Category("CLASSIC","CLASSIC"));
            Category dresses = categoryControllerLocal.createNewCategoryEntity(new Category("DRESSES","DRESSES"));
            Category tops = categoryControllerLocal.createNewCategoryEntity(new Category("TOPS","TOPS"));
            Category skirts = categoryControllerLocal.createNewCategoryEntity(new Category("SKIRTS","SKIRTS"));
            Category jeans = categoryControllerLocal.createNewCategoryEntity(new Category("JEANS","JEANS"));
            Category rompers = categoryControllerLocal.createNewCategoryEntity(new Category("ROMPERS","ROMPERS"));
            Category accessories = categoryControllerLocal.createNewCategoryEntity(new Category("ACCESSORIES","ACCESSORIES"));
            
            ProductTag tagPopular = productTagControllerLocal.createNewProductTag(new ProductTag("POPULAR"));
            ProductTag tagVintage = productTagControllerLocal.createNewProductTag(new ProductTag("VINTAGE"));
            ProductTag tagSummer = productTagControllerLocal.createNewProductTag(new ProductTag("SUMMER"));
            
            
            List<Long> tagIdsEmpty = new ArrayList<>();
            productControllerLocal.createNewProduct(new ProductEntity("PROD1", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), newProd.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD2", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), newProd.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD3", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), newProd.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("PROD4", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), classicProd.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD5", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), classicProd.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD6", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), classicProd.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("PROD7", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD8", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD9", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), dresses.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("PROD10", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD11", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD12", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), tops.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("PROD13", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), skirts.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD14", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), skirts.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD15", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), skirts.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("PROD16", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), jeans.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD17", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), jeans.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD18", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), jeans.getCategoryId() ,tagIdsEmpty);
            
            productControllerLocal.createNewProduct(new ProductEntity("PROD19", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), rompers.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD20", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), rompers.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD21", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), rompers.getCategoryId() ,tagIdsEmpty);
           
            productControllerLocal.createNewProduct(new ProductEntity("PROD22", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), accessories.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD23", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), accessories.getCategoryId() ,tagIdsEmpty);
            productControllerLocal.createNewProduct(new ProductEntity("PROD24", "100% Cotton", BigDecimal.valueOf(10.00), 20, SizeEnum.S, ColourEnum.BLUE,"images/image.jpg"), accessories.getCategoryId() ,tagIdsEmpty);
            
           
        } catch (InputDataValidationException | CreateNewCategoryException | CreateNewProductTagException | CreateNewProductException ex) {
            System.err.println("********** DataInitializationSessionBean.initializeData(): " + ex.getMessage());
    
        }
   }
   
   
   
}
