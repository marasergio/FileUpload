/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Mara
 */
import entidades.Produto;
import java.util.List;
import org.hibernate.Session;

public class ProdutoDao {
     public void salvar(Produto produto){
        Session session = HibernateUtil.getSession();
        session.beginTransaction().begin();
        session.saveOrUpdate(produto);
        session.getTransaction().commit();
        session.close();
        System.out.println("Salvo com sucesso");
    }
    
    public List<Produto> listar(){
        Session session= HibernateUtil.getSession();
        String sql = "SELECT p FROM Produto p";
        return (List<Produto>) session.createQuery(sql).list();
    }
}
