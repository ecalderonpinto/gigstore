package listacorreo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class ListaCorreoDAO {
    private EntityManagerFactory emf;

    public ListaCorreoDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP");
    }
    
    public ListaCorreo consultaListaCorreo(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        ListaCorreo resultado = new ListaCorreo();
        try {
            tx.begin();
            resultado = em.find(ListaCorreo.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(ListaCorreoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelveListaCorreos(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select l from ListaCorreo l");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaListaCorreo(ListaCorreo lista)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(lista);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ListaCorreoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaListaCorreo(ListaCorreo lista)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            ListaCorreo aux=em.merge(lista);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ListaCorreoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaListaCorreo(ListaCorreo lista)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            lista=em.find(ListaCorreo.class, lista.getId());
            em.remove(lista);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ListaCorreoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
