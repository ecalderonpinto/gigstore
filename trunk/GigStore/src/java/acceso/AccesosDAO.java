/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AccesosDAO {
    private EntityManagerFactory emf;
    private static int MAX_RES_URLVISITAS=5;

    public AccesosDAO() {
        emf=Persistence.createEntityManagerFactory("modeloUP"); 
    }
    
    public Acceso consultaAcceso(int id){
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        Acceso resultado=new Acceso();
        try{
            tx.begin();
            resultado=em.find(Acceso.class, id);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            Logger.getLogger(AccesosDAO.class.getName()).log(Level.SEVERE, null, ex);            
        } finally {
            em.close();
        }
        
        return resultado;
    }
    
    public List devuelveAccesos(int month, int year){
        EntityManager em=emf.createEntityManager();
        
        Query q = em.createQuery("select day(a.cuando), count(distinct a.sesionId) from Acceso a where month(a.cuando)=:month and year(a.cuando)=:year group by day(a.cuando) order by day(a.cuando)");
        
        q.setParameter("month", month);
        q.setParameter("year", year);
        
        List resultado= q.getResultList();
        em.close();
        
        return resultado;
    }
    
    public List devuelveAccesos(int month, int year, boolean nuevas){
        EntityManager em=emf.createEntityManager();
        
        String sql="";
        if(nuevas)
            sql+="select day(a.cuando), count(a.cuando) from Acceso a where month(a.cuando)=:month and year(a.cuando)=:year and a.id in (select a.id from Acceso a group by a.sesionId having sum(a.usuarioId)=0) group by day(a.cuando) order by day(a.cuando)";
        else
            sql+="select day(a.cuando), count(distinct a.sesionId) from Acceso a where month(a.cuando)=:month and year(a.cuando)=:year and a.usuarioId>0 group by day(a.cuando) order by day(a.cuando)";

        Query q = em.createQuery(sql);
        q.setParameter("month", month);
        q.setParameter("year", year);
        
        List resultado=q.getResultList();
        em.close();
        
        return resultado;
    }
    
    public List devuelveAccesosProductos(int month, int year){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select p.referencia, count(a.productoId) from Acceso a, Producto p where a.productoId=p.id and month(a.cuando)=:month and year(a.cuando)=:year group by a.productoId order by count(a.productoId) desc").setMaxResults(10);
        q.setParameter("month", month);
        q.setParameter("year", year);
        
        List resultado=q.getResultList();
        em.close();
        
        return resultado;
    }
    
    public double devuelveMaxAccesosProductos(int month, int year){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select count(a.productoId) from Acceso a, Producto p where a.productoId=p.id and month(a.cuando)=:month and year(a.cuando)=:year group by a.productoId order by count(a.productoId) desc").setMaxResults(1);
        q.setParameter("month", month);
        q.setParameter("year", year);
        
        double resultado=0;
        try{
            resultado=Double.parseDouble(q.getSingleResult().toString());
        }catch(Exception ex){
            resultado=0;
        }finally{
            em.close();
        }
        
        return resultado;
    }
    
    public List devuelveVentasProductos(int month, int year){
        EntityManager em=emf.createEntityManager();
        Query q = em.createQuery("select p.referencia, sum(lp.cantidad) from PedidoLinea lp inner join lp.producto p inner join lp.pedido ped where month(ped.fecha)=:month and year(ped.fecha)=:year group by p.referencia order by sum(lp.cantidad) desc").setMaxResults(10);
        q.setParameter("month", month);
        q.setParameter("year", year);
        
        List resultado=q.getResultList();
        em.close();
        
        return resultado;
    }
    
    public List devuelveAccesosVentas(int month, int year){
        EntityManager em=emf.createEntityManager();
        
        String sql="select day(p.fecha), count(p.fecha) from Pedido p where month(p.fecha)=:month and year(p.fecha)=:year group by day(p.fecha) order by day(p.fecha)";
        Query q = em.createQuery(sql);
        q.setParameter("month", month);
        q.setParameter("year", year);
        
        List resultado=q.getResultList();
        em.close();
        
        return resultado;
    }
    
    public double devuelveMaxAccesosVentas(int month, int year){
        EntityManager em=emf.createEntityManager();
        
        String sql="select count(p.fecha) from Pedido p where month(p.fecha)=:month and year(p.fecha)=:year group by day(p.fecha) order by count(p.fecha) desc";
        Query q = em.createQuery(sql).setMaxResults(1);
        q.setParameter("month", month);
        q.setParameter("year", year);
        
        double resultado=0;        
        try{
            resultado=Double.parseDouble(q.getSingleResult().toString());
        }catch(Exception ex){
            resultado=0;
        }finally{
            em.close();
        }
        
        return resultado;
    }
    
//    public List devuelveAccesosVentas(int month, int year, boolean nuevas){
//        EntityManager em=emf.createEntityManager();
//        String sql="";
//        
//        if(nuevas)
//            sql+="and a.usuarioId=0";
//        else
//            sql+="select day(p.fecha), count(p.fecha), count(u.pedidos) from Pedido p inner join p.usuario u where month(p.fecha)=:month and year(p.fecha)=:year group by day(p.fecha) having count(u.pedidos)>1 order by day(p.fecha)";
//
//        Query q = em.createQuery(sql);
//        q.setParameter("month", month);
//        q.setParameter("year", year);
//        
//        List resultado=q.getResultList();
//        em.close();
//        
//        return resultado;
//    }
    
    public List devuelveVolumenVentas(int month, int year){
        EntityManager em=emf.createEntityManager();
        
        String sql="select day(p.fecha), sum(p.total) from Pedido p where month(p.fecha)=:month and year(p.fecha)=:year group by day(p.fecha) order by day(p.fecha)";
        Query q = em.createQuery(sql);
        q.setParameter("month", month);
        q.setParameter("year", year);
        
        List resultado=q.getResultList();
        em.close();
        
        return resultado;
    }
    
    public double devuelveMaxVolumenVentas(int month, int year){
        EntityManager em=emf.createEntityManager();
        
        String sql="select sum(p.total) from Pedido p where month(p.fecha)=:month and year(p.fecha)=:year group by day(p.fecha) order by sum(p.total) desc";
        Query q = em.createQuery(sql).setMaxResults(1);
        q.setParameter("month", month);
        q.setParameter("year", year);
        
        double resultado;        
        try{
            resultado=Double.parseDouble(q.getSingleResult().toString());
        }catch(Exception ex){
            resultado=0;
        }finally{
            em.close();
        }
        
        return resultado;
    }
    
    public List devuelveDestinoVentas(int month, int year){
        EntityManager em=emf.createEntityManager();
        
        String sql="select p.dirEnvio.provincia, count(p.id) from Pedido p where month(p.fecha)=:month and year(p.fecha)=:year group by p.dirEnvio.provincia order by count(p.id)";
        Query q = em.createQuery(sql);
        q.setParameter("month", month);
        q.setParameter("year", year);
        
        List resultado=q.getResultList();
        em.close();
        
        return resultado;
    }
    
    public long devuelveTotalVisitas(){
        EntityManager em=emf.createEntityManager();
        
        String sql="select count(distinct a.sesionId) from Acceso a";
        Query q = em.createQuery(sql);
        
        long resultado=Long.parseLong(q.getSingleResult().toString());
        em.close();
        
        return resultado;
    }
    
    public long devuelveTotalPeticiones(){
        EntityManager em=emf.createEntityManager();
        
        String sql="select count(a.id) from Acceso a";
        Query q = em.createQuery(sql);
        
        long resultado=Long.parseLong(q.getSingleResult().toString());
        em.close();
        
        return resultado;
    }
    
    public long devuelveTotalVentas(){
        EntityManager em=emf.createEntityManager();
        
        String sql="select count(p.id) from Pedido p";
        Query q = em.createQuery(sql);
        
        long resultado=Long.parseLong(q.getSingleResult().toString());
        em.close();
        
        return resultado;
    }
    
    public float devuelveRatioPaginasVisita(){
        EntityManager em=emf.createEntityManager();
        
        String sql="select (count(a.id)/count(distinct a.sesionId)) from Acceso a";
        Query q = em.createQuery(sql);
        
        float resultado=Long.parseLong(q.getSingleResult().toString());
        em.close();
        
        return resultado;
    }
    
    public float devuelveRatioVisitasVenta(){
        float ventas=devuelveTotalVentas();
        if(ventas>0)
            return devuelveTotalVisitas()/ventas;
        else 
            return 0;
    }
    
    public long devuelveTiempoMedioVisita(){
        EntityManager em=emf.createEntityManager();
        
        String sql="SELECT ROUND(AVG(Tiempo)) AS Media FROM (SELECT MAX(acc_fechahora)-MIN(acc_fechahora) as Tiempo FROM accesos GROUP BY acc_sesionid ORDER BY acc_fechahora DESC) AS t";
        Query q=em.createNativeQuery(sql);
        Number res=(Number) q.getSingleResult();
        long resultado=res.longValue() * 1000;
        em.close();
        
        return resultado;
    }
    
    public float devuelvePorcentajeAbandonos(){
        EntityManager em=emf.createEntityManager();
        
        String sql="SELECT COUNT(N) FROM (SELECT acc_sesionid AS N FROM accesos GROUP BY acc_sesionid HAVING COUNT(acc_sesionid)=1) AS T";
        Query q = em.createNativeQuery(sql);
        
        float resultado=(Integer.parseInt(q.getSingleResult().toString())*100)/devuelveTotalVisitas();
        em.close();
        
        return resultado;
    }
    
    public float devuelvePorcentajeNuevasVisitas(){
        EntityManager em=emf.createEntityManager();
        
        String sql="SELECT COUNT(N) FROM (SELECT acc_sesionid AS N FROM accesos GROUP BY acc_sesionid HAVING SUM(acc_usuarioid )=0) AS T";
        Query q = em.createNativeQuery(sql);
        
        float resultado;
        try{
            resultado=(Integer.parseInt(q.getSingleResult().toString())*100)/devuelveTotalVisitas();
        }catch(Exception ex){
            resultado=0;
        }finally{
            em.close();
        }

        return resultado;
    }
    
    public float devuelvePorcentajeNuevasVentas(){
        EntityManager em=emf.createEntityManager();
        
        String sql="SELECT N FROM (SELECT COUNT(ped_usuarioid) AS N FROM pedidos GROUP BY ped_usuarioid HAVING COUNT(ped_usuarioid)=1) AS T";
        Query q = em.createNativeQuery(sql);
        float resultado;
        
        try{
            resultado=(Integer.parseInt(q.getSingleResult().toString())*100)/devuelveTotalVentas();
        }catch(Exception ex){
            resultado=0;
        }finally{
            em.close();
        }
        
        return resultado;
    }
    
    public ArrayList<ArrayList<String>> devuelveUrlVisitas(){
        EntityManager em=emf.createEntityManager();
        
        String sql="select a.uri, count(a.uri) from Acceso a group by a.uri order by count(a.uri) desc";
        Query q = em.createQuery(sql).setMaxResults(MAX_RES_URLVISITAS);
        
        List aux=q.getResultList();
        float total=devuelveTotalPeticiones();
        ArrayList<ArrayList<String>>resultado=new ArrayList<ArrayList<String>>();
        for(Object lista: aux){
            Object[] o=(Object[]) lista;
            float per=(Integer.parseInt(o[1].toString()) *100)/total;
            ArrayList<String> s=new ArrayList<String>();
            s.add(o[0].toString());
            s.add(o[1].toString()); 
            s.add(String.valueOf(per));
            resultado.add(s);
        }
        
        em.close();
        return resultado;
    }
    
    public void altaAcceso(Acceso acceso)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        try{
            tx.begin();
            
            em.persist(acceso);
            
            tx.commit();
        } catch (Exception ex){
            tx.rollback();
            Logger.getLogger(AccesosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }
    
}
