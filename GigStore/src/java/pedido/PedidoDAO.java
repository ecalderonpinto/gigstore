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
public class PedidoDAO {
    private EntityManagerFactory emf;

    public PedidoDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public Pedido consultaPedido(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Pedido resultado = new Pedido();
        try {
            tx.begin();
            resultado = em.find(Pedido.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelvePedidos(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select p from Pedido p order by p.fecha desc");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelvePedidos(int num){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select p from Pedido p order by p.fecha desc").setMaxResults(num);
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public int devuelveNPedidos(PedidoEstado estado){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select count(p) from Pedido p where p.estado=:est");
        q.setParameter("est", estado);
        int resultado=Integer.parseInt(q.getSingleResult().toString()); 
        em.close();
        
        return resultado;
    }
    
//    public List devuelvePedidos(int inicio, int fin){
//        EntityManager em=emf.createEntityManager();
//        Query q = em.createQuery("select p from Pedido p order by p.id");
//        List resultado=q.getResultList().subList(inicio, fin); 
//        em.close();
//        
//        return resultado;
//    }
//    
//    public List devuelvePedidos(PedidoEstado estado){
//        EntityManager em=emf.createEntityManager();
//        Query q = em.createQuery("select p from Pedido p where p.estado=:est order by p.id");
//        q.setParameter("est", estado);
//        List resultado=q.getResultList(); 
//        em.close();
//        
//        return resultado;
//    }
//    
//    public List devuelvePedidos(String campo, String valor){
//        EntityManager em=emf.createEntityManager();
//        Query q = em.createQuery("select p from Pedido p where p." + campo + "=:val order by p.id");
//        q.setParameter("val", valor);
//        List resultado=q.getResultList(); 
//        em.close();
//        
//        return resultado;
//    }
    
    public void altaPedido(Pedido pedido)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(pedido);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaPedido(Pedido pedido)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            Pedido aux=em.merge(pedido);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaPedido(Pedido pedido)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            pedido=em.find(Pedido.class, pedido.getId());
            em.remove(pedido);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
