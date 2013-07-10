/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class CategoriaDAO {
    private EntityManagerFactory emf;

    public CategoriaDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public Categoria consultaCategoria(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Categoria resultado = new Categoria();
        try {
            tx.begin();
            resultado = em.find(Categoria.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelveCategorias(boolean soloActivas){        
        List<Categoria> resultado=new ArrayList<Categoria>(); 
        
        for(Object obj:devuelveCategoriasPrincipales(soloActivas)){
            Categoria cat=(Categoria) obj;
            resultado.add(cat);
            for(Categoria c: devuelveSubCategorias(cat.getId(),soloActivas)){
                resultado.add(c);
            }
        }
        
        return resultado;
    }
    
    public List devuelveCategoriasPrincipales(boolean soloActivas){
        EntityManager em=emf.createEntityManager();
        String sql;
        if(soloActivas)
            sql="select c from Categoria c where c.madre is null and c.activo=true order by c.nombre";
        else
            sql="select c from Categoria c where c.madre is null order by c.nombre";
        Query q = em.createQuery(sql);
        
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List<Categoria> devuelveSubCategorias(int id, boolean soloActivas){
        EntityManager em=emf.createEntityManager();
        String sql;
        if(soloActivas)
            sql="select c from Categoria c where c.madre.id=:id and c.activo=true order by c.nombre";
        else
            sql="select c from Categoria c where c.madre.id=:id order by c.nombre";
        Query q = em.createQuery(sql);
        q.setParameter("id", id);
        
        List<Categoria> resultado= new ArrayList<Categoria>();        
        for(Object o:q.getResultList()){
            Categoria cat=(Categoria) o;
            resultado.add(cat);
            for(Categoria c:devuelveSubCategorias(cat.getId(), soloActivas))
                resultado.add(c);
        }
        
        em.close();
        
        return resultado;
    }
    
    public int devuelveNCategorias(boolean principal){
        EntityManager em=emf.createEntityManager();
        Query q;
        if(principal)
            q = em.createQuery("select count(c)from Categoria c where c.madre is null");
        else
            q = em.createQuery("select count(c)from Categoria c where c.madre is not null");
        
        int resultado=Integer.parseInt(q.getSingleResult().toString()); 
        em.close();
        
        return resultado;
    }
    
    public void altaCategoria(Categoria cat)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(cat);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaCategoria(Categoria cat)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            Categoria aux=em.merge(cat);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaCategoria(Categoria cat)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            cat=em.find(Categoria.class, cat.getId());
            em.remove(cat);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
