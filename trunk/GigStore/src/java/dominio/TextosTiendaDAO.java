/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class TextosTiendaDAO {
    private EntityManagerFactory emf;

    public TextosTiendaDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public TextosTienda consultaTextosTienda(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        TextosTienda resultado = new TextosTienda();
        try {
            tx.begin();
            resultado = em.find(TextosTienda.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(TextosTiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public TextosTienda consultaTextosTienda(String idioma) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        TextosTienda resultado = new TextosTienda();
        try {
            tx.begin();
            Query q = em.createQuery("select t from TextosTienda t where upper(t.idioma) like :idioma order by t.id");
            q.setParameter("idioma", idioma.toUpperCase() + "%");
            List lista=q.getResultList(); 
            for(Object o: lista){
                resultado=(TextosTienda) o;
            }
            //resultado=(TextosTienda) q.getSingleResult();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(TextosTiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelveTextosTienda() {
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select t from TextosTienda t order by t.idioma");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaTextosTienda(TextosTienda txt)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(txt);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(TextosTiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaTextosTienda(TextosTienda txt)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            TextosTienda aux=em.merge(txt);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(TextosTiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaTextosTienda(TextosTienda txt)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            txt=em.find(TextosTienda.class, txt.getId());
            em.remove(txt);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(TextosTiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
