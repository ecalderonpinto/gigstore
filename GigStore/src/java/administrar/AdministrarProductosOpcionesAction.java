/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package administrar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opcion.Valor;
import opcion.ValorDAO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import producto.*;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AdministrarProductosOpcionesAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";
    /**
     * Provides the mapping from resource key to method name.
     *
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.alta_inicio", "alta_inicio");
        map.put("accion.alta_ejecutar", "alta_ejecutar");
        map.put("accion.editar_inicio", "editar_inicio");
        map.put("accion.editar_ejecutar", "editar_ejecutar");
        map.put("accion.eliminar", "eliminar");
        return map;
    }

    /** Action called on alta_inicio button click
     */
    public ActionForward alta_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ValorDAO valDAO=(ValorDAO) request.getSession().getServletContext().getAttribute("valDAO");
        
        request.getSession().setAttribute("id_prod", request.getParameter("id"));
        request.getSession().setAttribute("vals", valDAO.devuelveValores());
        return mapping.findForward(SUCCESS);
    }

    /** Action called on alta_ejecutar button click
     */
    public ActionForward alta_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        ProductoOpcionCombinacionesForm cf=(ProductoOpcionCombinacionesForm) form;
        ProductoOpcionDAO optDAO=(ProductoOpcionDAO) request.getSession().getServletContext().getAttribute("poptDAO");
        ValorDAO valDAO=(ValorDAO) request.getSession().getServletContext().getAttribute("valDAO");
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        Producto prod=prodDAO.consultaProducto(Integer.parseInt(request.getSession().getAttribute("id_prod").toString()));
        
        request.getSession().removeAttribute("id_prod");
        request.getSession().removeAttribute("vals");
        
        if(!this.isCancelled(request)){
            String[] valores=combinarValores(cf.getValores());
            
            for(ProductoOpcion o: prod.getOpciones()){
                optDAO.eliminaProductoOpcion(o);
            }
            prod.getOpciones().clear();
            prodDAO.editaProducto(prod);
            
            for(int i=0;i<valores.length;i++){
                ProductoOpcion opt=new ProductoOpcion();
                String[] ids=valores[i].split(";");
                String opcion="";                
                ArrayList<String> trads=new ArrayList<String>();
                Set<String> resultado=new HashSet<String>();
                
                for(int x=0;x<ids.length;x++){
                    Valor val=valDAO.consultaValor(Integer.parseInt(ids[x]));
                    opcion+=val.getValor() + " ";
                    
                    for(String t:val.getTraducciones()){
                        boolean existe=false;                        
                        for(String s:trads){
                            if(s.equalsIgnoreCase(t.split(";")[0]))
                                existe=true;
                        }
                        if(!existe)
                            trads.add(t.split(";")[0]);
                    }
                }
                
                for(String s: trads){
                    String trad="";
                    for(int x=0;x<ids.length;x++){
                        Valor val=valDAO.consultaValor(Integer.parseInt(ids[x]));
                        String txt="";
                        for(String traduccion:val.getTraducciones()){
                            if(s.split(";")[0].equalsIgnoreCase(traduccion.split(";")[0]))
                                txt=traduccion.split(";")[1];
                        }
                        if(txt.isEmpty())
                            txt=val.getValor();
                        trad += txt + " ";
                    }
                    resultado.add(s + ";" + trad.trim());
                }
                
                opt.setProducto(prod);
                opt.setOpcion(opcion.trim());
                opt.setTraducciones(resultado);
                opt.setPrecio(cf.getPrecio());
                opt.setStock(cf.getStock());
                opt.setActiva(true);
            
                prod.getOpciones().add(opt);

                optDAO.altaProductoOpcion(opt);
                prodDAO.editaProducto(prod);
            }
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/detalle.do?metodo=mostrar_detalle&id=" + prod.getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on editar_inicio button click
     */
    public ActionForward editar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ProductoOpcionForm of=(ProductoOpcionForm) form;
        ProductoOpcionDAO optDAO=(ProductoOpcionDAO) request.getSession().getServletContext().getAttribute("poptDAO");
        ProductoOpcion opt=optDAO.consultaProductoOpcion(Integer.parseInt(request.getParameter("id")));
                
        of.setOpcion(opt.getOpcion());
        if(opt.getPrecio()!=null){
            DecimalFormat df=new DecimalFormat("#.##");
            of.setSprecio(df.format(opt.getPrecio()));
        }
        of.setSstock(String.valueOf(opt.getStock()));
        of.setSactiva(String.valueOf(opt.isActiva()));
          
        request.getSession().setAttribute("id", request.getParameter("id"));
        request.getSession().setAttribute("id_prod", opt.getProducto().getId());
        request.getSession().setAttribute("accion", "editar");
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on editar_ejecutar button click
     */
    public ActionForward editar_ejecutar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ProductoOpcionForm of=(ProductoOpcionForm) form;
        ProductoOpcionDAO optDAO=(ProductoOpcionDAO) request.getSession().getServletContext().getAttribute("poptDAO");
        ProductoOpcion opt=optDAO.consultaProductoOpcion(Integer.parseInt(request.getSession().getAttribute("id").toString()));
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
                
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("id_prod");
        request.getSession().removeAttribute("accion");
        
        if(!this.isCancelled(request)){
            opt.setOpcion(of.getOpcion());
            opt.setPrecio(of.getPrecio());
            opt.setStock(of.getStock());
            opt.setActiva(of.isActiva());
            Producto p=prodDAO.consultaProducto(opt.getProducto().getId());
            p.getOpciones().add(opt);
            
            optDAO.editaProductoOpcion(opt);
            prodDAO.editaProducto(p);
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/detalle.do?metodo=mostrar_detalle&id=" + opt.getProducto().getId());
        af.setRedirect(true);
        
        return af; 
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ProductoOpcionDAO optDAO=(ProductoOpcionDAO) request.getSession().getServletContext().getAttribute("poptDAO");
        ProductoOpcion opt=optDAO.consultaProductoOpcion(Integer.parseInt(request.getParameter("id")));
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        
        opt.getProducto().getOpciones().remove(opt);
        prodDAO.editaProducto(opt.getProducto());
        optDAO.eliminaProductoOpcion(opt);
        
        ActionForward af=new ActionForward();
        af.setPath("/administrar/productos/detalle.do?metodo=mostrar_detalle&id=" + opt.getProducto().getId());
        af.setRedirect(true);
        
        return af; 
    }
    
    private String[] combinarValores(String[] valores){
        ArrayList<ArrayList<String>> lista=new ArrayList<ArrayList<String>>();
        ArrayList<Integer> opts=new ArrayList<Integer>();
        
        // RECORRE valores PARA OBTENER LOS IDS DE LAS DIFERENTES opciones 
        // Y LOS ALMACENA EN LA VARIABLE opts
        for(int i=0;i<valores.length;i++){
            boolean existe=false;
            int op=Integer.parseInt(valores[i].split(";")[0]);
            for(int j=0;j<opts.size();j++){
                if((Integer)opts.toArray()[j]==op){
                    existe=true;
                    break;
                }
            }
            if(!existe)
                opts.add(op);
        }

        // RECORRE opts PARA LUEGO RECORRER valores CREANDO UNA SUBLISTA
        // CON CADA UNO DE LOS valores QUE PERTENEZCAN A ESA opcion
        for(int x=0;x<opts.size();x++){
            ArrayList<String> sublista=new ArrayList<String>();
            for(int i=0;i<valores.length;i++){
                int op=Integer.parseInt(valores[i].split(";")[0]);
                if(op==(Integer)opts.toArray()[x])
                    sublista.add(valores[i].split(";")[1]);
            }   
            lista.add(sublista);
        }

        // CALCULA EL TOTAL DE COMBINACIONES QUE SE DEBEN CREAR MULTIPLICANDO
        // EL NÚMERO DE ELEMENTOS DE CADA UNA DE LAS SUBLISTAS
        int total=1;
        for(Object o: lista){
            ArrayList subl=(ArrayList) o;
            total*=subl.size();
        }
        
        //CREA UNA MATRIZ PARA LOS resultados Y LA INICIALIZA EN BLANCO
        String[] res=new String[total];
        for(int i=0;i<res.length;i++)
            res[i]="";

        // RECORRE lista PARA EJECUTAR EL CÓDIGO INTERIOR UNA VEZ POR SUBLISTA
        for(int i=0;i<lista.size();i++){
            ArrayList sublista=(ArrayList) lista.toArray()[i];
            int cont=0; // DECLARA E INICIALIZA CONTADOR PARA CONTROLAR EL NÚMERO DE COMBINACIONES
            // EJECUTA EL BÚCLE HASTA LLEGAR AL NÚMERO total DE COMBINACIONES
            while(cont<total){
                // RECORRE LOS ELEMENTOS DE LA SUBLISTA ACTUAL, ESTO SE REPETIRÁ TANTAS 
                // VECES COMO SEA NECESARIO HASTA LLEGAR AL total DE COMBINACIONES
                for(int j=0;j<sublista.size();j++){
                    String val=(String) sublista.toArray()[j]; //OBTIENE UN valor DE LA SUBLISTA
                    int multi=1; //DECLARA E INICIALIZA EL CONTADOR QUE DETERMINA EL NÚMERO DE VECES QUE SE DEBE REPETIR UN valor
                    // CALCULA EL multiplicador PARA EL VALOR ACTUAL MULTIPLICANDO 
                    // EL NÚMERO DE ELEMENTOS DE LAS SIGUIENTES LISTAS
                    for(int k=i+1;k<lista.size();k++){
                        ArrayList aux=(ArrayList) lista.toArray()[k];
                        multi*=aux.size();
                    }
                    
                    // AÑADE EL valor A LA MATRIZ resultado EN LA SIGUIENTE POSICIÓN LIBRE
                    for(int k=0;k<multi;k++){ 
                        if(res[cont].isEmpty()){
                            res[cont]+= val;
                        }else{
                            res[cont]+= ";" + val;
                        }
                        cont++; //AVANZA EL CONTADOR A LA SIGUIENTE POSICIÓN LIBRE
                    }   
                }
            }
        }
        
        return res;
    }
}
