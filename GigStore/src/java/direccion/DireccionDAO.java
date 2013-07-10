/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package direccion;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class DireccionDAO {
    private EntityManagerFactory emf;

    public DireccionDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP");
    }
    
    public Direccion consultaDireccion(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Direccion resultado = new Direccion();
        try {
            tx.begin();
            resultado = em.find(Direccion.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(DireccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelveDirecciones(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select d from Direccion d order by d.id");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaDireccion(Direccion dir)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(dir);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(DireccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaDireccion(Direccion dir)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            Direccion aux=em.merge(dir);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(DireccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaDireccion(Direccion dir)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            dir=em.find(Direccion.class, dir.getId());
            em.remove(dir);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(DireccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
