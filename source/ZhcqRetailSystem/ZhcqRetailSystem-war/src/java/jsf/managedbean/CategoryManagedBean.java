/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.CategoryControllerLocal;
import entity.Category;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import util.exception.CategoryNotFoundException;
import util.exception.CreateNewCategoryException;
import util.exception.DeleteCategoryException;
import util.exception.InputDataValidationException;
import util.exception.UpdateCategoryException;


@Named
@ViewScoped
public class CategoryManagedBean implements Serializable {

    @EJB(name = "CategoryControllerLocal")
    private CategoryControllerLocal categoryControllerLocal;

    private List<Category> categories;
    private List<Category> filteredCategories;

    private Category newCategory;
    private Category categoryToView;
    private Category categoryToUpdate;
    private Category categoryToDelete;
    
    private String newFilePath;

    public CategoryManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        categories = categoryControllerLocal.retrieveAllCategories();
        newCategory = new Category();
    }

    public void createNewCategory(ActionEvent event) {
        try {
            newCategory.setPicturePath(newFilePath);
            categoryControllerLocal.createNewCategoryEntity(newCategory);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Category created successfully", null));
            newCategory = new Category();
        } catch (CreateNewCategoryException | InputDataValidationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to create category" + ex.getMessage(), null));
        }
    }
    
    public void handleFileUpload(FileUploadEvent event)
    {
        try
        {   
            this.newFilePath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("alternatedocroot_1") + System.getProperty("file.separator") + "uploadedFiles//" + event.getFile().getFileName();

            
            System.err.println("********** Create Category File Upload: File name: " + event.getFile().getFileName());
            System.err.println("********** Create Category File Upload: newFilePath: " + newFilePath);

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
            System.err.println("********** Create Category File Upload: saved File Path: " + newFilePath);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  "File uploaded successfully", ""));
        }
        catch(IOException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  "File upload error: " + ex.getMessage(), ""));
        }
    }

    public void deleteCategory(ActionEvent event) {
        try {
            categoryToDelete = (Category) event.getComponent().getAttributes().get("categoryToDelete");
            categoryControllerLocal.deleteCategory(categoryToDelete.getCategoryId());
            categories.remove(categoryToDelete);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully deleted category", null));    
        } catch (CategoryNotFoundException | DeleteCategoryException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to delete category " + ex.getMessage(), null));
        }
    }
    
    public void doUpdateCategory(ActionEvent event) {
        categoryToUpdate = (Category)event.getComponent().getAttributes().get("categoryToUpdate");
    }

    public void updateCategory(ActionEvent event) {
        try {
            categoryControllerLocal.updateCategory(categoryToUpdate);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully updated category", null));
        } catch (CategoryNotFoundException | InputDataValidationException | UpdateCategoryException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to update category" + ex.getMessage(), null));
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getFilteredCategories() {
        return filteredCategories;
    }

    public void setFilteredCategories(List<Category> filteredCategories) {
        this.filteredCategories = filteredCategories;
    }

    public Category getCategoryToUpdate() {
        return categoryToUpdate;
    }

    public void setCategoryToUpdate(Category categoryToUpdate) {
        this.categoryToUpdate = categoryToUpdate;
    }

    public Category getCategoryToDelete() {
        return categoryToDelete;
    }

    public void setCategoryToDelete(Category categoryToDelete) {
        this.categoryToDelete = categoryToDelete;
    }

    public Category getCategoryToView() {
        return categoryToView;
    }

    public void setCategoryToView(Category categoryToView) {
        this.categoryToView = categoryToView;
    }

    public Category getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(Category newCategory) {
        this.newCategory = newCategory;
    }
}
