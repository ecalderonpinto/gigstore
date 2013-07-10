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
public class UsuarioDAO {
    private EntityManagerFactory emf;

    public UsuarioDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public Usuario consultaUsuario(int id){
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        Usuario resultado=new Usuario();
        try{
            tx.begin();
            resultado=em.find(Usuario.class, id);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            em.close();
        }
        
        return resultado;
    }
    
    public Usuario consultaUsuario(String user, String pass){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select u from Usuario u where u.usuario=:usr and u.contrasenya=:pass order by u.apellidos, u.nombre");
        q.setParameter("usr", user);
        q.setParameter("pass", pass);
        Usuario resultado=(Usuario)q.getSingleResult();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            resultado=em.find(Usuario.class, resultado.getId());
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            em.close();
        }        
        
        return resultado;
    }
    
    public Usuario consultaUsuario(String email){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select u from Usuario u where u.email=:email");
        q.setParameter("email", email);
        Usuario resultado=(Usuario)q.getSingleResult();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            resultado=em.find(Usuario.class, resultado.getId());
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            em.close();
        }        
        
        return resultado;
    }
    
    public boolean existeUsuario(String email){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select u from Usuario u where u.email=:email");
        q.setParameter("email", email);
        Usuario resultado;
        try{
            resultado=(Usuario)q.getSingleResult();
        }catch(Exception ex){
            return false;            
        } finally {
            em.close();
        }        
        
        if(resultado.getId()>0)
            return true;
        else
            return false;
    }
    
    public List devuelveUsuarios(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select u from Usuario u order by u.apellidos, u.nombre");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveUsuarios(UsuarioEstado estado){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select u from Usuario u where u.estado=:est order by u.apellidos, u.nombre");
        q.setParameter("est", estado);
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
//    public List devuelveUsuarios(int inicio, int fin){
//        EntityManager em=emf.createEntityManager();
//        Query q = em.createQuery("select u from Usuario u order by u.apellidos, u.nombre");
//        List resultado=q.getResultList().subList(inicio, fin); 
//        em.close();
//        
//        return resultado;
//    }
//    
//    public List devuelveUsuarios(String campo, String valor){
//        EntityManager em=emf.createEntityManager();
//        String sql;
//        if(campo.equalsIgnoreCase("estado")){
//            sql="select u from Usuario u where upper(u.estado.estado) like :val order by u.apellidos, u.nombre";
//        }else{
//            sql="select u from Usuario u where upper(u." + campo + ") like :val order by u.apellidos, u.nombre";
//        }
//        Query q = em.createQuery(sql);
//        q.setParameter("val", "%" + valor.toUpperCase() + "%");
//        List resultado=q.getResultList(); 
//        em.close();
//        
//        return resultado;
//    }
    
    public void altaUsuario(Usuario usuario)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(usuario);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaUsuario(Usuario usuario)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            Usuario aux=em.merge(usuario);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaUsuario(Usuario usuario)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            usuario=em.find(Usuario.class, usuario.getId());
            em.remove(usuario);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
