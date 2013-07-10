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
public class ProductoOpcionDAO {
    private EntityManagerFactory emf;

    public ProductoOpcionDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public ProductoOpcion consultaProductoOpcion(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        ProductoOpcion resultado = new ProductoOpcion();
        try {
            tx.begin();
            resultado = em.find(ProductoOpcion.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(ProductoOpcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelveProductoOpcion(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select o from ProductoOpcion o order by o.id");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveProductoOpcion(Producto prod){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select o from ProductoOpcion o where o.producto=:p order by o.opcion");
        q.setParameter("p", prod);
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaProductoOpcion(ProductoOpcion opcion)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(opcion);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoOpcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaProductoOpcion(ProductoOpcion opcion)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            ProductoOpcion aux=em.merge(opcion);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoOpcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaProductoOpcion(ProductoOpcion opcion)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            opcion=em.find(ProductoOpcion.class, opcion.getId());
            em.remove(opcion);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoOpcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }    
}
