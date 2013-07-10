/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class ProductoDescripcionDAO {
    private EntityManagerFactory emf;

    public ProductoDescripcionDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public ProductoDescripcion consultaDesc(int id){
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        ProductoDescripcion resultado=new ProductoDescripcion();
        try{
            tx.begin();
            resultado=em.find(ProductoDescripcion.class, id);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoDescripcionDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            em.close();
        }
        
        return resultado;
    }
    
    public List devuelveDesc(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select d from ProductoDescripcion d order by d.idioma");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaDesc(ProductoDescripcion desc)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(desc);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoDescripcion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaDesc(ProductoDescripcion desc)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            ProductoDescripcion aux=em.merge(desc);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoDescripcion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaDesc(ProductoDescripcion desc)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            desc=em.find(ProductoDescripcion.class, desc.getId());
            em.remove(desc);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoDescripcion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
