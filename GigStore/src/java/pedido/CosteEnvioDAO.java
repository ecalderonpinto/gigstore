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
public class CosteEnvioDAO {
    private EntityManagerFactory emf;

    public CosteEnvioDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public CosteEnvio consultaCosteEnvio(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        CosteEnvio resultado = new CosteEnvio();
        try {
            tx.begin();
            resultado = em.find(CosteEnvio.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelveCosteEnvios(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select e from CosteEnvio e order by e.id");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaCosteEnvio(CosteEnvio envio)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(envio);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(CosteEnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaCosteEnvio(CosteEnvio envio)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            CosteEnvio aux=em.merge(envio);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(CosteEnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaCosteEnvio(CosteEnvio envio)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            envio=em.find(CosteEnvio.class, envio.getId());
            em.remove(envio);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(CosteEnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
