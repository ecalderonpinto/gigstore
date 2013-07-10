/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class CosteGestionDAO {
    private EntityManagerFactory emf;

    public CosteGestionDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP");
    }
    
    public CosteGestion consultaCosteGestion(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        CosteGestion resultado = new CosteGestion();
        try {
            tx.begin();
            resultado = em.find(CosteGestion.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(CosteGestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelveCosteGestiones(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select g from CosteGestion g order by g.id");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaCosteGestion(CosteGestion ges)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(ges);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(CosteGestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaCosteGestion(CosteGestion ges)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            CosteGestion aux=em.merge(ges);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(CosteGestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaCosteGestion(CosteGestion ges)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            ges=em.find(CosteGestion.class, ges.getId());
            em.remove(ges);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(CosteGestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
