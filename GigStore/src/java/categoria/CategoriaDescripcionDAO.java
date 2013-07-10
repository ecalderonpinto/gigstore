/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package categoria;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class CategoriaDescripcionDAO {
    private EntityManagerFactory emf;

    public CategoriaDescripcionDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public CategoriaDescripcion consultaDesc(int id){
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        CategoriaDescripcion resultado=new CategoriaDescripcion();
        try{
            tx.begin();
            resultado=em.find(CategoriaDescripcion.class, id);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            Logger.getLogger(CategoriaDescripcionDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            em.close();
        }
        
        return resultado;
    }
    
    public List devuelveDesc(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select d from CategoriaDescripcion d order by d.idioma");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaDesc(CategoriaDescripcion desc)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(desc);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(CategoriaDescripcion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaDesc(CategoriaDescripcion desc)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            CategoriaDescripcion aux=em.merge(desc);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(CategoriaDescripcion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaDesc(CategoriaDescripcion desc)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            desc=em.find(CategoriaDescripcion.class, desc.getId());
            em.remove(desc);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(CategoriaDescripcion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
