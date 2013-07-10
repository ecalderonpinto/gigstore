/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.util.MessageResources;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class AccesosAction extends LookupDispatchAction {
    private final static String SUCCESS = "success";
    private SimpleDateFormat dfMes=new SimpleDateFormat("MM");
    private SimpleDateFormat dfAny=new SimpleDateFormat("yyyy");
    private SimpleDateFormat dfFechas=new SimpleDateFormat("MMMM yyyy");
    private SimpleDateFormat dfFechaC=new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Provides the mapping from resource key to method name.
     *
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("accion.mostrar_listado", "mostrar_listado");
        map.put("accion.est_nvisitas", "est_nvisitas");
        map.put("accion.est_productos", "est_productos");
        map.put("accion.est_nventas", "est_nventas");
        map.put("accion.est_visitasventas", "est_visitasventas");
        map.put("accion.est_ventasdestino", "est_ventasdestino");
        map.put("accion.est_ncompras", "est_ncompras");
        return map;
    }

    /**
     * Action called on enlace.est_nvisitas button click
     */
    public ActionForward mostrar_listado(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        AccesosDAO accDAO = (AccesosDAO) request.getSession().getServletContext().getAttribute("accDAO");
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        MessageResources msg = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        
        int mes=new GregorianCalendar().get(Calendar.MONTH) + 1;
        int any=new GregorianCalendar().get(Calendar.YEAR);
        
        dataset=devuelveDatasetMes(dataset, mes, any, accDAO.devuelveAccesos(mes, any).toArray(), msg.getMessage(locale, "texto.est_totvisitas"));        
        JFreeChart chart=ChartFactory.createLineChart(msg.getMessage(locale, "texto.est_numvisitas"), "", msg.getMessage(locale, "texto.est_visitas"), dataset, PlotOrientation.VERTICAL , false, false, false);
        CategoryAxis xAxis = chart.getCategoryPlot().getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Inclinamos 45 grados las etiquetas del eje X 
        chart.getCategoryPlot().setDomainAxis(xAxis);
        request.getSession().setAttribute("visitas", chart); 
        
        dataset=devuelveDatasetMes(new DefaultCategoryDataset(), mes, any, accDAO.devuelveAccesosVentas(mes, any).toArray(), msg.getMessage(locale, "texto.est_ventastotales"));        
        chart=ChartFactory.createLineChart(msg.getMessage(locale, "texto.est_ventas"), msg.getMessage(locale, "texto.est_dia"), msg.getMessage(locale, "texto.est_ventas"), dataset, PlotOrientation.VERTICAL , false, false, false);
        chart.getCategoryPlot().getDomainAxis().setVisible(false);
        chart.getCategoryPlot().getRangeAxis().setRange(0, accDAO.devuelveMaxAccesosVentas(mes, any) + 5);
        request.getSession().setAttribute("ventas", chart);  
        
        DefaultPieDataset piedataset=new DefaultPieDataset();
        for(Object lista: accDAO.devuelveDestinoVentas(mes, any)){
            Object o[]=(Object[]) lista;
            piedataset.setValue(o[0].toString(), Double.parseDouble(o[1].toString()));
        }          
        chart=ChartFactory.createPieChart3D(msg.getMessage(locale, "texto.est_destinosfrec"), piedataset, false, false, false);                
        request.getSession().setAttribute("ubicacion", chart);
        
        dataset=new DefaultCategoryDataset();
        for(Object lista: accDAO.devuelveAccesosProductos(mes, any)){
            Object[] o=(Object[]) lista;
            dataset.addValue(Long.parseLong(o[1].toString()), msg.getMessage(locale, "texto.est_prodvisitados"), o[0].toString());
        }                
        for(Object lista: accDAO.devuelveVentasProductos(mes, any)){
            Object[] o=(Object[]) lista;
            dataset.addValue(Long.parseLong(o[1].toString()), msg.getMessage(locale, "texto.est_prodvendidos"), o[0].toString());
        }    
        chart=ChartFactory.createBarChart3D(msg.getMessage(locale, "texto.est_prodvisitados"), msg.getMessage(locale, "texto.est_referencia"), msg.getMessage(locale, "texto.est_visitas"), dataset, PlotOrientation.VERTICAL , false, false, false);
                        
        chart.getCategoryPlot().getDomainAxis().setVisible(false);
        chart.getCategoryPlot().getRangeAxis().setRange(0, accDAO.devuelveMaxAccesosProductos(mes, any) + 5);
        request.getSession().setAttribute("origen", chart);
        
        request.setAttribute("totalVisitas", accDAO.devuelveTotalVisitas());
        request.setAttribute("totalPeticiones", accDAO.devuelveTotalPeticiones());
        request.setAttribute("paginasVisita", accDAO.devuelveRatioPaginasVisita());
        SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss");
        int inOffset = Calendar.getInstance().getTimeZone().getOffset((new java.util.Date()).getTime());
        request.setAttribute("duracion", df.format(new Date(accDAO.devuelveTiempoMedioVisita()-inOffset)));
        request.setAttribute("abandonos", accDAO.devuelvePorcentajeAbandonos());
        request.setAttribute("nuevasVisitas", accDAO.devuelvePorcentajeNuevasVisitas());
        request.setAttribute("urlVisitas", accDAO.devuelveUrlVisitas());
        request.setAttribute("visitasVenta", accDAO.devuelveRatioVisitasVenta());
        request.setAttribute("nuevasVentas", accDAO.devuelvePorcentajeNuevasVentas());
        
        return mapping.findForward(SUCCESS);
    }

    /**
     * Action called on enlace.est_nvisitas button click
     */
    public ActionForward est_nvisitas(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        AccesosDAO accDAO = (AccesosDAO) request.getSession().getServletContext().getAttribute("accDAO");
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        MessageResources msg = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        
        int mes=new GregorianCalendar().get(Calendar.MONTH) + 1;
        int any=new GregorianCalendar().get(Calendar.YEAR);
        
        if(request.getParameter("mes")!=null){
            mes=Integer.parseInt(dfMes.format(dfFechas.parse(request.getParameter("mes"))));
            any=Integer.parseInt(request.getParameter("mes").split(" ")[1]);
        }        
        
        dataset=devuelveDatasetMes(dataset, mes, any, accDAO.devuelveAccesos(mes, any).toArray(), msg.getMessage(locale, "texto.est_totvisitas"));
        dataset=devuelveDatasetMes(dataset, mes, any, accDAO.devuelveAccesos(mes, any, true).toArray(), msg.getMessage(locale, "texto.est_nuevasvisitas"));
        dataset=devuelveDatasetMes(dataset, mes, any, accDAO.devuelveAccesos(mes, any, false).toArray(), msg.getMessage(locale, "texto.est_usrregistrado"));
                
        JFreeChart chart=ChartFactory.createLineChart(msg.getMessage(locale, "texto.est_numvisitas"), msg.getMessage(locale, "texto.est_dia"), msg.getMessage(locale, "texto.est_visitas"), dataset, PlotOrientation.VERTICAL , true, true, false);
        LineAndShapeRenderer lr=(LineAndShapeRenderer) chart.getCategoryPlot().getRenderer();
        lr.setBaseShapesVisible(true);
                        
        CategoryAxis xAxis = chart.getCategoryPlot().getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Inclinamos 45 grados las etiquetas del eje X 
        chart.getCategoryPlot().setDomainAxis(xAxis);
        
        request.getSession().setAttribute("chart", chart);        
        request.setAttribute("fechas", calcularFechas());
        request.setAttribute("met", "est_nvisitas");
            
        return mapping.findForward(SUCCESS);
    }

    /**
     * Action called on enlace.est_productos button click
     */
    public ActionForward est_productos(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        AccesosDAO accDAO = (AccesosDAO) request.getSession().getServletContext().getAttribute("accDAO");
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        MessageResources msg = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        
        int mes=new GregorianCalendar().get(Calendar.MONTH) + 1;
        int any=new GregorianCalendar().get(Calendar.YEAR);
        
        if(request.getParameter("mes")!=null){
            mes=Integer.parseInt(dfMes.format(dfFechas.parse(request.getParameter("mes"))));
            any=Integer.parseInt(request.getParameter("mes").split(" ")[1]);
        }              
        
        for(Object lista: accDAO.devuelveAccesosProductos(mes, any)){
            Object[] o=(Object[]) lista;
            dataset.addValue(Long.parseLong(o[1].toString()), msg.getMessage(locale, "texto.est_prodvisitados"), o[0].toString());
        }        
        
        for(Object lista: accDAO.devuelveVentasProductos(mes, any)){
            Object[] o=(Object[]) lista;
            dataset.addValue(Long.parseLong(o[1].toString()), msg.getMessage(locale, "texto.est_prodvendidos"), o[0].toString());
        }          
                
        JFreeChart chart=ChartFactory.createBarChart3D(msg.getMessage(locale, "texto.est_numvisitas"), msg.getMessage(locale, "texto.est_referencia"), msg.getMessage(locale, "texto.est_visitas"), dataset, PlotOrientation.VERTICAL , true, true, false);
                        
        CategoryAxis xAxis = chart.getCategoryPlot().getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Inclinamos 45 grados las etiquetas del eje X 
        chart.getCategoryPlot().setDomainAxis(xAxis);
                
        request.getSession().setAttribute("chart", chart);
        request.setAttribute("fechas", calcularFechas());
        request.setAttribute("met", "est_productos");
            
        return mapping.findForward(SUCCESS);
    }

    /**
     * Action called on enlace.est_nventas button click
     */
    public ActionForward est_nventas(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        AccesosDAO accDAO = (AccesosDAO) request.getSession().getServletContext().getAttribute("accDAO");
        MessageResources msg = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        
        int mes=new GregorianCalendar().get(Calendar.MONTH) + 1;
        int any=new GregorianCalendar().get(Calendar.YEAR);
        
        if(request.getParameter("mes")!=null){
            mes=Integer.parseInt(dfMes.format(dfFechas.parse(request.getParameter("mes"))));
            any=Integer.parseInt(request.getParameter("mes").split(" ")[1]);
        }         
                
        JFreeChart chart=ChartFactory.createLineChart(msg.getMessage(locale, "texto.est_numventas"), msg.getMessage(locale, "texto.est_dia"), msg.getMessage(locale, "texto.est_ventas"), devuelveDatasetMes(new DefaultCategoryDataset(), mes, any, accDAO.devuelveAccesosVentas(mes, any).toArray(), msg.getMessage(locale, "texto.est_ventastotales")), PlotOrientation.VERTICAL , true, true, false);
        LineAndShapeRenderer lr=(LineAndShapeRenderer) chart.getCategoryPlot().getRenderer();
        lr.setBaseShapesVisible(true);
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setDataset(1, devuelveDatasetMes(new DefaultCategoryDataset(), mes, any, accDAO.devuelveVolumenVentas(mes, any).toArray(), msg.getMessage(locale, "texto.est_volventas")));
        plot.mapDatasetToRangeAxis(1, 1);
        ValueAxis axis2 = new NumberAxis(msg.getMessage(locale, "texto.est_moneda"));
        plot.setRangeAxis(1, axis2);
                        
        CategoryAxis xAxis = chart.getCategoryPlot().getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Inclinamos 45 grados las etiquetas del eje X 
        chart.getCategoryPlot().setDomainAxis(xAxis);
                
        AreaRenderer renderer2 = new AreaRenderer();
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
        
        request.getSession().setAttribute("chart", chart);
        request.setAttribute("fechas", calcularFechas());
        request.setAttribute("met", "est_nventas");
            
        return mapping.findForward(SUCCESS);
    }

    /**
     * Action called on enlace.est_ventasdestino button click
     */
    public ActionForward est_ventasdestino(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        AccesosDAO accDAO = (AccesosDAO) request.getSession().getServletContext().getAttribute("accDAO");
        DefaultPieDataset dataset=new DefaultPieDataset();
        MessageResources msg = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        
        int mes=new GregorianCalendar().get(Calendar.MONTH) + 1;
        int any=new GregorianCalendar().get(Calendar.YEAR);
        
        if(request.getParameter("mes")!=null){
            mes=Integer.parseInt(dfMes.format(dfFechas.parse(request.getParameter("mes"))));
            any=Integer.parseInt(request.getParameter("mes").split(" ")[1]);
        }           
        
        for(Object lista: accDAO.devuelveDestinoVentas(mes, any)){
            Object[] o=(Object[]) lista;
            dataset.setValue(o[0].toString(), Double.parseDouble(o[1].toString()));
        }        
        
        JFreeChart chart=ChartFactory.createPieChart3D(msg.getMessage(locale, "texto.est_destinosfrec"), dataset, true, true, false);
                
        request.getSession().setAttribute("chart", chart);
        request.setAttribute("fechas", calcularFechas());
        request.setAttribute("met", "est_ventasdestino");
            
        return mapping.findForward(SUCCESS);
    }
    
    private ArrayList<String> calcularFechas() throws ParseException{
        ArrayList<String> fechas = new ArrayList<String>();
        
        int iAny=Integer.parseInt(dfAny.format(new Date()));
        while(iAny >= 2012){
            for(int iMes=12; iMes>0; iMes--){
                if(iAny < Integer.parseInt(dfAny.format(new Date())) || (iAny == Integer.parseInt(dfAny.format(new Date())) && iMes <= Integer.parseInt(dfMes.format(new Date()))))
                    fechas.add(dfFechas.format(dfFechaC.parse("01/" + iMes + "/" + iAny )));
            }
            iAny--;
        }
        
        return fechas;
    }
    
    public static DefaultCategoryDataset devuelveDatasetMes(DefaultCategoryDataset dataset, int mes, int any, Object[] lista, String titulo){
        int dias=0;
        switch(mes){
            case 1: case 3: case 5:
            case 7: case 8: case 10:
            case 12:
                dias=31;
                break;
            case 4: case 6:
            case 9: case 11:
                dias = 30;
                break;
            case 2:
                if(new GregorianCalendar().isLeapYear(any))
                    dias=29;
                else
                    dias=28;
        }
        
        for(int i=1;i<=dias;i++){
            Number valor=0;
            for(int j=0;j<lista.length;j++){
                Object[] o=(Object[]) lista[j];
                if(o!=null && o[0].toString().equalsIgnoreCase(String.valueOf(i)))
                    valor=(Number) o[1];
            }
            dataset.addValue(valor, titulo, String.valueOf(i));
        }
        
        return dataset;
    }
}
