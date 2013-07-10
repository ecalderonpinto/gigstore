/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

import categoria.Categoria;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class ProductoDAO {
    private EntityManagerFactory emf;

    public ProductoDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public Producto consultaProducto(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Producto resultado = new Producto();
        try {
            tx.begin();
            resultado = em.find(Producto.class, id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }

        return resultado;
    }
    
    public List devuelveProductos(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select p from Producto p order by p.referencia");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveProductos(String busqueda, boolean ocultarAgotados){
        EntityManager em=emf.createEntityManager();
        String sql;
        
        if(ocultarAgotados)
            sql="select p from Producto p left join p.descripciones d where (upper(p.nombre) like :bus or upper(p.marca) like :bus or upper(d.nombre) like :bus or upper(d.descripcion) like :bus or upper(d.etiquetas) like :bus) and p.categoria.activo=:act and p.estado.id<>3  and p.estado.id<>2 group by p.id order by p.destacado, p.nombre";
        else
            sql="select p from Producto p left join p.descripciones d where (upper(p.nombre) like :bus or upper(p.marca) like :bus or upper(d.nombre) like :bus or upper(d.descripcion) like :bus or upper(d.etiquetas) like :bus) and p.categoria.activo=:act and p.estado.id<>3 group by p.id order by p.destacado, p.nombre";
        
        Query q = em.createQuery(sql);
        q.setParameter("bus", "%" + busqueda.toUpperCase() + "%");
        q.setParameter("act", true);
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveProductos(Categoria cat){
        EntityManager em=emf.createEntityManager();
        String sql;
        
        sql="select p from Producto p where p.categoria = :cat order by p.destacado, p.nombre";
        
        Query q = em.createQuery(sql);
        q.setParameter("cat", cat);
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveProductos(Categoria cat, boolean ocultarAgotados){
        EntityManager em=emf.createEntityManager();
        String sql;
        
        if(ocultarAgotados)
            sql="select p from Producto p where p.categoria = :cat and p.categoria.activo=:act and p.estado.id<>3 and p.estado.id<>2 order by p.destacado, p.nombre";
        else
            sql="select p from Producto p where p.categoria = :cat and p.categoria.activo=:act and p.estado.id<>3 order by p.destacado, p.nombre";
        
        Query q = em.createQuery(sql);
        q.setParameter("cat", cat);
        q.setParameter("act", true);
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveVendidos(int nRegs, boolean ocultarAgotados){
        EntityManager em=emf.createEntityManager();
        Query q;
        String sql;
        
        if(ocultarAgotados)
            sql="select p from PedidoLinea lp inner join lp.producto p where p.categoria.activo=:val and p.estado.id<>3 and p.estado.id<>2 group by p.id order by count(p.id) desc";
        else
            sql="select p from PedidoLinea lp inner join lp.producto p where p.categoria.activo=:val and p.estado.id<>3 group by p.id order by count(p.id) desc";
        
        if(nRegs > 0)
            q = em.createQuery(sql).setMaxResults(nRegs);
        else
            q = em.createQuery(sql);
        
        q.setParameter("val", true);
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveDestacados(int nRegs, boolean ocultarAgotados){
        EntityManager em=emf.createEntityManager();
        Query q;
        String sql;
        
        if(ocultarAgotados)
            sql="select p from Producto p where p.destacado=:val and p.categoria.activo=:val and p.estado.id<>3 and p.estado.id<>2 order by p.nombre";
        else
            sql="select p from Producto p where p.destacado=:val and p.categoria.activo=:val and p.estado.id<>3 order by p.nombre";
        
        if(nRegs > 0)
            q = em.createQuery(sql).setMaxResults(nRegs);
        else
            q = em.createQuery(sql);
        
        q.setParameter("val", true);
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveNuevos(int nRegs, boolean ocultarAgotados){
        EntityManager em=emf.createEntityManager();
        Query q;
        String sql;
        
        if(ocultarAgotados)
            sql="select p from Producto p where p.categoria.activo=:val and p.estado.id<>3 and p.estado.id<>2 order by p.id desc";
        else
            sql="select p from Producto p where p.categoria.activo=:val and p.estado.id<>3 order by p.id desc";
        
        if(nRegs > 0)
            q = em.createQuery(sql).setMaxResults(nRegs);
        else
            q = em.createQuery(sql);
        
        q.setParameter("val", true);
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public int devuelveNDisponibles(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select count(p)from Producto p where p.estado.id<>3 and p.estado.id<>2");
        int resultado=Integer.parseInt(q.getSingleResult().toString()); 
        em.close();
        
        return resultado;
    }
    
    public int devuelveNProductos(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select count(p)from Producto p");
        int resultado=Integer.parseInt(q.getSingleResult().toString()); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveStockBajo(int num){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select p from Producto p order by p.stock").setMaxResults(num);
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public List devuelveMarcas(){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select distinct(p.marca) from Producto p");
        List resultado=q.getResultList(); 
        em.close();
        
        return resultado;
    }
    
    public void altaProducto(Producto producto)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(producto);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void editaProducto(Producto producto)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            Producto aux=em.merge(producto);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
    public void eliminaProducto(Producto producto)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            producto=em.find(Producto.class, producto.getId());
            em.remove(producto);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
}
