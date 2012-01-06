/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import entidades.Produto;
import dao.ProdutoDao;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.event.FileUploadEvent;
/**
 *
 * @author Mara
 */
@ManagedBean(name="produtoMB")
@SessionScoped
public class ProdutoManagedBean {
    private Produto produto;
    private List<Produto> listarProdutos;
    private UploadArquivo arquivo = new UploadArquivo();

    public ProdutoManagedBean() {
        this.produto = new Produto();
        this.listarProdutos = new ArrayList<Produto>();
    }

    public List<Produto> getListarProdutos() {
        return new ProdutoDao().listar();
        //return this.listarProdutos;
    }

    public void setListarProdutos(List<Produto> listarProdutos) {
        this.listarProdutos = listarProdutos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public void uploadAction (FileUploadEvent event){
        this.arquivo.fileUpload(event, ".jpg", "/image/");
        this.produto.setFoto(this.arquivo.getNome());
    }
    
    public void salvar(){
        new ProdutoDao().salvar(produto);
        this.arquivo.gravar();
        
        this.produto = new Produto();
        this.arquivo = new UploadArquivo();
    }
}
