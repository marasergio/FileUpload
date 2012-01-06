/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author MARA NETBOOK
 */
public class UploadArquivo {
    private String diretorio;
    private String caminho;
    private byte[] arquivo;
    private String nome;

    public UploadArquivo() {
    }

    public String getDiretorio() {
        return this.diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRealPath() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

        return context.getRealPath("/");
    }

    public void fileUpload(FileUploadEvent event, String type, String diretorio) {
        try {
            this.nome = new java.util.Date().getTime() + type;
            this.caminho = getRealPath() + diretorio + getNome();
            this.arquivo = event.getFile().getContents();
            
            File file = new File(getRealPath() + diretorio);
            file.mkdirs();

        } catch (Exception ex) {
            System.out.println("Erro no upload do arquivo" + ex);
        }
    }
    
    public void gravar(){
        
        try {
            
            FileOutputStream fos;
            fos = new FileOutputStream(this.caminho);
            fos.write(this.arquivo);
            fos.close();
            
        } catch (Exception ex) {
            Logger.getLogger(UploadArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}
