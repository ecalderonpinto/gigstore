/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manl Márquez Bonilla
 */
public class ProductoEstadoDAO {
    private EntityManagerFactory emf;

    public ProductoEstadoDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public ProductoEstado consultaEstado(int id){
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        ProductoEstado resultado=new ProductoEstado();
        try{
            tx.begin();
            resultado=em.find(ProductoEstado.class, id);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            em.close();
        }  
        
        return resultado;
    }
    
    public List devuelveEstados(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select e from ProductoEstado e order by e.id");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaEstado(ProductoEstado estado)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(estado);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaEstado(ProductoEstado estado)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            ProductoEstado aux=em.merge(estado);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaEstado(ProductoEstado estado)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            estado=em.find(ProductoEstado.class, estado.getId());
            em.remove(estado);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
