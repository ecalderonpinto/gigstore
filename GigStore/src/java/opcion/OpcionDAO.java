/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opcion;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import producto.Producto;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class OpcionDAO {
    private EntityManagerFactory emf;

    public OpcionDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public Opcion consultaOpcion(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Opcion resultado = new Opcion();
        try {
            tx.begin();
            resultado = em.find(Opcion.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(OpcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelveOpcion(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select o from Opcion o order by o.id");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveOpcion(Producto prod){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select o from Opcion o where o.producto=:p order by o.opcion");
        q.setParameter("p", prod);
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaOpcion(Opcion opcion)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(opcion);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(OpcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaOpcion(Opcion opcion)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            Opcion aux=em.merge(opcion);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(OpcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaOpcion(Opcion opcion)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            opcion=em.find(Opcion.class, opcion.getId());
            em.remove(opcion);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(OpcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }    
}
