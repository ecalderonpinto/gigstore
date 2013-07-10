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
public class PedidoLineaDAO {
    private EntityManagerFactory emf;

    public PedidoLineaDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
        
    public PedidoLinea consultaLinea(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        PedidoLinea resultado = new PedidoLinea();
        try {
            tx.begin();
            resultado = em.find(PedidoLinea.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(PedidoLineaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelveLineas(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select l from PedidoLinea l order by l.id");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaLinea(PedidoLinea linea)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(linea);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(PedidoLineaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaLinea(PedidoLinea linea)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            PedidoLinea aux=em.merge(linea);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(PedidoLineaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaLinea(PedidoLinea linea)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            linea=em.find(PedidoLinea.class, linea.getId()/*linea.getPedidoLineaPK()*/);
            em.remove(linea);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(PedidoLineaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
