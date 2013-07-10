/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class TipoIvaDAO {
    private EntityManagerFactory emf;

    public TipoIvaDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public TipoIVA consultaTipoIva(int id){
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        TipoIVA resultado=new TipoIVA();
        try{
            tx.begin();
            resultado=em.find(TipoIVA.class, id);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            Logger.getLogger(TipoIvaDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            em.close();
        }        
        
        return resultado;
    }
    
    public List devuelveTiposIva(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select t from TipoIVA t order by t.iva");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaTipoIva(TipoIVA iva)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(iva);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(TipoIvaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    } 
    
    public void editaTipoIva(TipoIVA iva)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            TipoIVA aux=em.merge(iva);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(TipoIvaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }   
    
    public void eliminaTipoIva(TipoIVA iva)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            iva=em.find(TipoIVA.class, iva.getId());
            em.remove(iva);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(TipoIvaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }    
    
    /*Obtener el IVA de un producto*/
    public static BigDecimal calcIva(BigDecimal p, BigDecimal IVA) { 
        return p.multiply(IVA.divide(new BigDecimal(100))); 
    }
    
    /*Obtener el precio con IVA de un producto que no tiene IVA*/
    public static BigDecimal sumaIva(BigDecimal p, BigDecimal IVA) { 
        return p.multiply(new BigDecimal(1).add(IVA.divide(new BigDecimal(100))));
    }
    
    /*Dado el precio con IVA de un producto, averiguar su valor sin IVA*/
    public static BigDecimal quitaIva(BigDecimal p, BigDecimal IVA) { 
        return p.divide(new BigDecimal(1).add(IVA.divide(new BigDecimal(100))));
    }
    
    /*Obtener solo el IVA de un producto con IVA*/
    public static BigDecimal extraeIva(BigDecimal p, BigDecimal IVA) { 
        return p.divide(new BigDecimal(1).add(new BigDecimal(100).divide(IVA, 2, RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP);
    }
}
