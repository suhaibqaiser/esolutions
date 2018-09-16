package ca.esolutionsgroup.TaxManager.Controller;

import java.io.IOException;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 */

@Named
public class FileUpload {

    private String text;

    private String outputText;

    private UploadedFile file;

    @Autowired
    private Inventory inventory;

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(final Inventory value) {
        this.inventory = value;
    }
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getText() {
        return text;
    }

    public void setText(final String value) {
        this.text = value;
    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(final String value) {
        this.outputText = value;
    }
     
    public void handleFileUpload(FileUploadEvent event) {
        this.file = event.getFile();
        try {
            if(!validateFileSize()) {
                FacesMessage message = new FacesMessage("Error", "File size cannot excede 500 KB");
                FacesContext.getCurrentInstance().addMessage(null, message); 
            }
            if(file != null) {
                Scanner s = new Scanner(this.file.getInputstream()).useDelimiter("\\A");
                this.text = s.hasNext() ? s.next() : "";
                this.inventory.setFileText(this.text);
                this.inventory.setReceiptText("");
                FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch(IOException ioe) {
            FacesMessage message = new FacesMessage("Error", ioe.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }

    private boolean validateFileSize() {
        if(this.file.getContents() != null && this.file.getContents().length > 500000) {
            return false;
        }
        return true;
    }



}