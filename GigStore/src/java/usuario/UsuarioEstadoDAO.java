/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class UsuarioEstadoDAO {
    private EntityManagerFactory emf;

    public UsuarioEstadoDAO() {        
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public UsuarioEstado consultaEstado(int id){
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        UsuarioEstado resultado=new UsuarioEstado();
        try{
            tx.begin();
            resultado=em.find(UsuarioEstado.class, id);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            Logger.getLogger(UsuarioEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            em.close();
        }
        
        return resultado;
    }
    
    public List devuelveEstados(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select e from UsuarioEstado e order by e.id");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveEstados(String nombre){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select e from UsuarioEstado e where upper(e.estado) like :nombre order by e.estado");
        q.setParameter("nombre", "%" + nombre.toUpperCase() + "%");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaEstado(UsuarioEstado estado)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(estado);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(UsuarioEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaEstado(UsuarioEstado estado)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            UsuarioEstado aux=em.merge(estado);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(UsuarioEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaEstado(UsuarioEstado estado)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            estado=em.find(UsuarioEstado.class, estado.getId());
            em.remove(estado);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(UsuarioEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
