/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opcion;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class ValorDAO {
    private EntityManagerFactory emf;

    public ValorDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public Valor consultaValor(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Valor resultado = new Valor();
        try {
            tx.begin();
            resultado = em.find(Valor.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(ValorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelveValores(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select v from Valor v order by v.opcion.id");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveValores(Opcion opt){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select v from Valor v where v.opcion=:o order by v.valor");
        q.setParameter("o", opt);
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaValor(Valor valor)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(valor);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ValorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaValor(Valor valor)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            Valor aux=em.merge(valor);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ValorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaValor(Valor valor)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            valor=em.find(Valor.class, valor.getId());
            em.remove(valor);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ValorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }    
    
}
