package ca.esolutionsgroup.TaxManager.Model;

import java.io.IOException;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

/**
 * 
 */

@Named
public class FileUpload {

    private String text;

    private UploadedFile file;
 
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
     
    public void upload() {
        try {
            if(file != null) {
                Scanner s = new Scanner(this.file.getInputstream()).useDelimiter("\\A");
                this.text = s.hasNext() ? s.next() : "";
                s.close();
                FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch(IOException ioe) {
            FacesMessage message = new FacesMessage("Error", ioe.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
}