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
public class ConfiguracionTiendaDAO {
    private EntityManagerFactory emf;

    public ConfiguracionTiendaDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public List devuelveConfiguracionesTienda() {
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select c from ConfiguracionTienda c order by c.id");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public ConfiguracionTienda consultaConfiguracionTienda(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        ConfiguracionTienda resultado = new ConfiguracionTienda();
        try {
            tx.begin();
            resultado = em.find(ConfiguracionTienda.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(ConfiguracionTiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public void editaConfiguracionTienda(ConfiguracionTienda cfg)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            ConfiguracionTienda aux=em.merge(cfg);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ConfiguracionTiendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }  
}
