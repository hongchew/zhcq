/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.CategoryControllerLocal;
import ejb.stateless.ProductControllerLocal;
import ejb.stateless.ProductTagControllerLocal;
import entity.Category;
import entity.ProductEntity;
import entity.ProductTag;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import util.enumeration.ColourEnum;
import util.enumeration.SizeEnum;
import util.exception.CreateNewProductException;
import util.exception.InputDataValidationException;
import util.exception.ProductNotFoundException;

/**
 *
 * @author Hong Chew
 */
@Named(value = "productManagementManagedBean")
@ViewScoped
public class ProductManagementManagedBean implements Serializable {

    @EJB
    private ProductTagControllerLocal productTagController;

    @EJB
    private CategoryControllerLocal categoryController;

    @EJB
    private ProductControllerLocal productController;

            
    private List<ProductEntity> productEntities;
    private List<ProductEntity> filteredProductEntities;
    private ColourEnum[] colours;
    private SizeEnum[] sizes;
    
    
    private ProductEntity newProductEntity;
    private Long categoryIdNew;
    private List<String> tagIdsStringNew;
    private UploadedFile productPhotoNew;
    private List<Category> categoryEntities;
    private List<ProductTag> tagEntities;

    private List<String> tagIdsStringFilter;
    
    private ProductEntity selectedProductEntityToView;        
    private ProductEntity selectedProductEntityToUpdate;
    private ProductEntity selectedProductEntityToDelete;
    
    private Long categoryIdUpdate;
    private List<String> tagIdsStringUpdate;
    private ColourEnum colourEnumUpdate;
    private SizeEnum sizeEnumUpdate;
    
    private String newFilePath;
    /**
     * Creates a new instance of ProductManagementManagedBean
     */
    public ProductManagementManagedBean() {
        newProductEntity = new ProductEntity();
    }
    
    @PostConstruct
    public void postConstruct()
    {
        setProductEntities(productController.retrieveAllProducts());
        setCategoryEntities(categoryController.retrieveAllCategories());
        setTagEntities(productTagController.retrieveAllProductTags());
        setColours(ColourEnum.values());
        setSizes(SizeEnum.values());
    }
    
    
    
    public void createNewProduct(ActionEvent event)
    {
//        System.err.println("********* Real path JSF: " + FacesContext.getCurrentInstance().getExternalContext().getRealPath("/systemAdministration/images"));
//    
//        String picturePath = "" ;
//        
//        if(productPhotoNew == null){
//                System.err.println("NULLNULLNULL");
//            }else{
//                System.err.println("File Name: " + productPhotoNew.getFileName());
//        }
//        
//        try{
//            InputStream initialStream = productPhotoNew.getInputstream();
//                
//            byte[] buffer = new byte[initialStream.available()];
//            initialStream.read(buffer);
//            
//            picturePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/systemAdministration/images/");
//            //picturePath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "\\web\\systemAdministration\\images\\" + productPhotoNew.getFileName();
//            System.err.println("images/" + productPhotoNew.getFileName() );
//            File targetFile = new File(picturePath);
//            System.err.println(targetFile.getAbsolutePath());
//            OutputStream outStream = new FileOutputStream(targetFile);
//            outStream.write(buffer);
////            InputStream input = productPhotoNew.getInputstream();
////            //System.err.println(new File("images/", productPhotoNew.getFileName()).toPath());
////            Path path = Paths.get("images", productPhotoNew.getFileName());
////            Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
//        }
//        catch (IOException e) {
//            System.err.println(e.getMessage());
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "File Upload Error: " + e.getMessage(), null));
//
//        }
//        
        List<Long> tagIdsNew = null;
        
        if(getCategoryIdNew() == 0)
        {
            setCategoryIdNew(null);
        }
        
        if(getTagIdsStringNew() != null && (!tagIdsStringNew.isEmpty()))
        {
            tagIdsNew = new ArrayList<>();
            
            for(String tagIdString:getTagIdsStringNew())
            {
                tagIdsNew.add(Long.valueOf(tagIdString));
            }
        }
        
        try
        {
            newProductEntity.setPicturePath(newFilePath);
            ProductEntity pe = productController.createNewProduct(getNewProductEntity(), getCategoryIdNew(), tagIdsNew);
            getProductEntities().add(pe);
            
            setNewProductEntity(new ProductEntity());
            setCategoryIdNew(null);
            setTagIdsStringNew(null);
            

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New product created successfully (Product ID: " + pe.getProductId() + ")", null));
        }
        catch(InputDataValidationException | CreateNewProductException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new product: " + ex.getMessage(), null));
        }
    }
    
    public void handleFileUpload(FileUploadEvent event)
    {
        try
        {   
            this.newFilePath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("alternatedocroot_1") + System.getProperty("file.separator") + "uploadedFiles//" + event.getFile().getFileName();

            
            System.err.println("********** Create Product File Upload: File name: " + event.getFile().getFileName());
            System.err.println("********** Create Product File Upload: newFilePath: " + newFilePath);

            File file = new File(newFilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            int a;
            int BUFFER_SIZE = 8192;
            byte[] buffer = new byte[BUFFER_SIZE];

            InputStream inputStream = event.getFile().getInputstream();

            while (true)
            {
                a = inputStream.read(buffer);

                if (a < 0)
                {
                    break;
                }

                fileOutputStream.write(buffer, 0, a);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();
            newFilePath = "/images/uploadedFiles/" + event.getFile().getFileName();
            System.err.println("********** Create Outfit File Upload: saved File Path: " + newFilePath);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "File uploaded successfully", ""));
        }
        catch(IOException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  "File upload error: " + ex.getMessage(), ""));
        }
    }
    
//    public void fileUploadListener(FileUploadEvent event){
//        productPhotoNew = event.getFile();
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Photo Uploaded", null));
//
//    }
//    public void updateProduct(ActionEvent event){
//        try {
//            ProductEntity productToUpdate = productController.retrieveProductById(selectedProductEntityToUpdate.getProductId());
//            List<Long> tagIds = new ArrayList<>();
//            for(ProductTag tag : selectedProductEntityToUpdate.getProductTags()){
//                tagIds.add(tag.getProductTagId());
//            }
//            productController.updateProduct(productToUpdate, selectedProductEntityToUpdate.getProductCategory().getCategoryId(), tagIds);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product updated successfully", null));
//        } catch (ProductNotFoundException | InputDataValidationException | CategoryNotFoundException | ProductTagNotFoundException | UpdateProductException ex) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
//        }
//    }
    
    public void doUpdateProduct(ActionEvent event)
    {
        selectedProductEntityToUpdate = (ProductEntity)event.getComponent().getAttributes().get("productEntityToUpdate");
        
        categoryIdUpdate = selectedProductEntityToUpdate.getProductCategory().getCategoryId();
        tagIdsStringUpdate = new ArrayList<>();
        colourEnumUpdate = selectedProductEntityToUpdate.getColourEnum();
        sizeEnumUpdate = selectedProductEntityToUpdate.getSizeEnum();
        
        for(ProductTag tagEntity:selectedProductEntityToUpdate.getProductTags())
        {
            tagIdsStringUpdate.add(tagEntity.getProductTagId().toString());
        }
    }
    
    public void updateProduct(ActionEvent event)
    {
        List<Long> tagIdsUpdate = null;
        
        if(getCategoryIdUpdate()  == 0)
        {
            setCategoryIdUpdate(null);
        }
        
        if(getTagIdsStringUpdate() != null && (!tagIdsStringUpdate.isEmpty()))
        {
            tagIdsUpdate = new ArrayList<>();
            
            for(String tagIdString:getTagIdsStringUpdate())
            {
                tagIdsUpdate.add(Long.valueOf(tagIdString));
            }
        }
        
        try
        {   
            selectedProductEntityToUpdate.setColourEnum(colourEnumUpdate);
            selectedProductEntityToUpdate.setSizeEnum(sizeEnumUpdate);
            
            productController.updateProduct(getSelectedProductEntityToUpdate(), getCategoryIdUpdate(), tagIdsUpdate);
                        
            for(Category c:getCategoryEntities())
            {
                if(c.getCategoryId().equals(getCategoryIdUpdate()))
                {
                    getSelectedProductEntityToUpdate().setProductCategory(c);
                    break;
                }                
            }
            
            getSelectedProductEntityToUpdate().getProductTags().clear();
            
            if(tagIdsUpdate != null){
                for(ProductTag tag:getTagEntities())
                {
                    if(tagIdsUpdate.contains(tag.getProductTagId()))
                    {
                        getSelectedProductEntityToUpdate().getProductTags().add(tag);
                    }                
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product updated successfully", null));
        }
        catch(ProductNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while updating product: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    
    public void deleteProduct(ActionEvent event)
    {
        try
        {
            ProductEntity productEntityToDelete = (ProductEntity)event.getComponent().getAttributes().get("productEntityToDelete");
            productController.deleteProduct(productEntityToDelete.getProductId());
            
            getProductEntities().remove(getSelectedProductEntityToDelete());
            setProductEntities(productController.retrieveAllProducts());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product deleted successfully (Product ID: " + productEntityToDelete.getProductId() + ")" , null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    
    /**
     * @return the productEntities
     */
    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    /**
     * @param productEntities the productEntities to set
     */
    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }

    /**
     * @return the filteredProductEntities
     */
    public List<ProductEntity> getFilteredProductEntities() {
        return filteredProductEntities;
    }

    /**
     * @param filteredProductEntities the filteredProductEntities to set
     */
    public void setFilteredProductEntities(List<ProductEntity> filteredProductEntities) {
        this.filteredProductEntities = filteredProductEntities;
    }

    /**
     * @return the newProductEntity
     */
    public ProductEntity getNewProductEntity() {
        return newProductEntity;
    }

    /**
     * @param newProductEntity the newProductEntity to set
     */
    public void setNewProductEntity(ProductEntity newProductEntity) {
        this.newProductEntity = newProductEntity;
    }

    /**
     * @return the categoryIdNew
     */
    public Long getCategoryIdNew() {
        return categoryIdNew;
    }

    /**
     * @param categoryIdNew the categoryIdNew to set
     */
    public void setCategoryIdNew(Long categoryIdNew) {
        this.categoryIdNew = categoryIdNew;
    }

    /**
     * @return the tagIdsStringNew
     */
    public List<String> getTagIdsStringNew() {
        return tagIdsStringNew;
    }

    /**
     * @param tagIdsStringNew the tagIdsStringNew to set
     */
    public void setTagIdsStringNew(List<String> tagIdsStringNew) {
        this.tagIdsStringNew = tagIdsStringNew;
    }

    /**
     * @return the categoryEntities
     */
    public List<Category> getCategoryEntities() {
        return categoryEntities;
    }

    /**
     * @param categoryEntities the categoryEntities to set
     */
    public void setCategoryEntities(List<Category> categoryEntities) {
        this.categoryEntities = categoryEntities;
    }

    /**
     * @return the tagEntities
     */
    public List<ProductTag> getTagEntities() {
        return tagEntities;
    }

    /**
     * @param tagEntities the tagEntities to set
     */
    public void setTagEntities(List<ProductTag> tagEntities) {
        this.tagEntities = tagEntities;
    }

    /**
     * @return the selectedProductEntityToView
     */
    public ProductEntity getSelectedProductEntityToView() {
        return selectedProductEntityToView;
    }

    /**
     * @param selectedProductEntityToView the selectedProductEntityToView to set
     */
    public void setSelectedProductEntityToView(ProductEntity selectedProductEntityToView) {
        this.selectedProductEntityToView = selectedProductEntityToView;
    }

    /**
     * @return the selectedProductEntityToUpdate
     */
    public ProductEntity getSelectedProductEntityToUpdate() {
        return selectedProductEntityToUpdate;
    }

    /**
     * @param selectedProductEntityToUpdate the selectedProductEntityToUpdate to set
     */
    public void setSelectedProductEntityToUpdate(ProductEntity selectedProductEntityToUpdate) {
        this.selectedProductEntityToUpdate = selectedProductEntityToUpdate;
    }

    /**
     * @return the categoryIdUpdate
     */
    public Long getCategoryIdUpdate() {
        return categoryIdUpdate;
    }

    /**
     * @param categoryIdUpdate the categoryIdUpdate to set
     */
    public void setCategoryIdUpdate(Long categoryIdUpdate) {
        this.categoryIdUpdate = categoryIdUpdate;
    }

    /**
     * @return the tagIdsStringUpdate
     */
    public List<String> getTagIdsStringUpdate() {
        return tagIdsStringUpdate;
    }

    /**
     * @param tagIdsStringUpdate the tagIdsStringUpdate to set
     */
    public void setTagIdsStringUpdate(List<String> tagIdsStringUpdate) {
        this.tagIdsStringUpdate = tagIdsStringUpdate;
    }

    /**
     * @return the colours
     */
    public ColourEnum[] getColours() {
        return colours;
    }


    /**
     * @param colours the colours to set
     */
    public void setColours(ColourEnum[] colours) {
        this.colours = colours;
    }

    /**
     * @return the sizes
     */
    public SizeEnum[] getSizes() {
        return sizes;
    }

    /**
     * @param sizes the sizes to set
     */
    public void setSizes(SizeEnum[] sizes) {
        this.sizes = sizes;
    }

    /**
     * @return the selectedProductEntityToDelete
     */
    public ProductEntity getSelectedProductEntityToDelete() {
        return selectedProductEntityToDelete;
    }

    /**
     * @param selectedProductEntityToDelete the selectedProductEntityToDelete to set
     */
    public void setSelectedProductEntityToDelete(ProductEntity selectedProductEntityToDelete) {
        this.selectedProductEntityToDelete = selectedProductEntityToDelete;
    }

    /**
     * @return the colourEnumUpdate
     */
    public ColourEnum getColourEnumUpdate() {
        return colourEnumUpdate;
    }

    /**
     * @param colourEnumUpdate the colourEnumUpdate to set
     */
    public void setColourEnumUpdate(ColourEnum colourEnumUpdate) {
        this.colourEnumUpdate = colourEnumUpdate;
    }

    /**
     * @return the sizeEnumUpdate
     */
    public SizeEnum getSizeEnumUpdate() {
        return sizeEnumUpdate;
    }

    /**
     * @param sizeEnumUpdate the sizeEnumUpdate to set
     */
    public void setSizeEnumUpdate(SizeEnum sizeEnumUpdate) {
        this.sizeEnumUpdate = sizeEnumUpdate;
    }

    /**
     * @return the productPhotoNew
     */
    public UploadedFile getProductPhotoNew() {
        return productPhotoNew;
    }

    /**
     * @param productPhotoNew the productPhotoNew to set
     */
    public void setProductPhotoNew(UploadedFile productPhotoNew) {
        this.productPhotoNew = productPhotoNew;
    }

    public String getNewFilePath() {
        return newFilePath;
    }

    public void setNewFilePath(String newFilePath) {
        this.newFilePath = newFilePath;
    }

  
}
