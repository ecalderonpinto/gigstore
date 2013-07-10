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
public class ImagenDAO {
    private EntityManagerFactory emf;

    public ImagenDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    
    public Imagen consultaImagen(int id){
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        Imagen resultado=new Imagen();
        try{
            tx.begin();
            resultado=em.find(Imagen.class, id);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            Logger.getLogger(ImagenDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            em.close();
        } 
        
        return resultado;
    }
    
    public List devuelveImagenes(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select i from Imagen i order by i.id");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaImagen(Imagen imagen)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(imagen);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ImagenDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaImagen(Imagen imagen)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            Imagen aux=em.merge(imagen);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ImagenDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaImagen(Imagen imagen)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            imagen=em.find(Imagen.class, imagen.getId());
            em.remove(imagen);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ImagenDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
