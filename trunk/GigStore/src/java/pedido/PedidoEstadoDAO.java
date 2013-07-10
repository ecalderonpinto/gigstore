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
public class PedidoEstadoDAO {
    private EntityManagerFactory emf;

    public PedidoEstadoDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public PedidoEstado consultaEstado(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        PedidoEstado resultado = new PedidoEstado();
        try {
            tx.begin();
            resultado = em.find(PedidoEstado.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(PedidoEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelveEstados(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select e from PedidoEstado e order by e.id");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaEstado(PedidoEstado estado)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(estado);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(PedidoEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaEstado(PedidoEstado estado)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            PedidoEstado aux=em.merge(estado);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(PedidoEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaEstado(PedidoEstado estado)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            estado=em.find(PedidoEstado.class, estado.getId());
            em.remove(estado);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(PedidoEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
