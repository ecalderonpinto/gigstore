/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.util.MessageResources;
import org.jmesa.core.filter.DateFilterMatcher;
import org.jmesa.core.filter.MatcherKey;
import org.jmesa.limit.ExportType;
import org.jmesa.model.TableModel;
import org.jmesa.util.ItemUtils;
import org.jmesa.view.component.Column;
import org.jmesa.view.component.Row;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.DateCellEditor;
import org.jmesa.view.editor.NumberCellEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class GeneradorTablas {
    private final static String URL_DETALLE_PRODUCTOS = "/administrar/productos/detalle.do?metodo=mostrar_detalle&id=";
    private final static String URL_ELIMINAR_PROD = "/administrar/productos/eliminar.do?metodo=eliminar&id=";
    private final static String URL_EDITAR_DESC_PROD = "/administrar/productos/descripciones/editar.do?metodo=editar_inicio&id=";
    private final static String URL_ELIMINAR_DESC_PROD = "/administrar/productos/descripciones/eliminar.do?metodo=eliminar&id=";
    private final static String URL_EDITAR_OPT_PROD = "/administrar/productos/opciones/editar.do?metodo=editar_inicio&id=";
    private final static String URL_ELIMINAR_OPT_PROD = "/administrar/productos/opciones/eliminar.do?metodo=eliminar&id=";
    private final static String URL_EDITAR_ESTADOS_PROD = "/administrar/productos/estados/editar.do?metodo=editar_inicio&id=";
    private final static String URL_ELIMINAR_ESTADOS_PROD = "/administrar/productos/estados/eliminar.do?metodo=eliminar&id=";
    private final static String URL_DETALLE_CATEGORIAS = "/administrar/productos/categorias/detalle.do?metodo=mostrar_detalle&id=";
    private final static String URL_ELIMINAR_CATEGORIAS = "/administrar/productos/categorias/eliminar.do?metodo=eliminar&id=";
    private final static String URL_EDITAR_CATEGORIAS_DESC="/administrar/productos/categorias/descripciones/editar.do?metodo=editar_inicio&id=";
    private final static String URL_ELIMINAR_CATEGORIAS_DESC="/administrar/productos/categorias/descripciones/eliminar.do?metodo=eliminar&id=";
    private final static String URL_EDITAR_TEXTOS_TIENDA = "/administrar/tienda/textos/editar.do?metodo=editar_inicio&id=";
    private final static String URL_ELIMINAR_TEXTOS_TIENDA = "/administrar/tienda/textos/eliminar.do?metodo=eliminar&id=";
    private final static String URL_EDITAR_TIPOS_IVA = "/administrar/tienda/tiposiva/editar.do?metodo=editar_inicio&id=";
    private final static String URL_ELIMINAR_TIPOS_IVA = "/administrar/tienda/tiposiva/eliminar.do?metodo=eliminar&id=";
    private final static String URL_DETALLE_USUARIOS = "/administrar/usuarios/detalle.do?metodo=mostrar_detalle&id=";
    private final static String URL_EDITAR_ESTADOS_USR = "/administrar/usuarios/estados/editar.do?metodo=editar_inicio&id=";
    private final static String URL_ELIMINAR_ESTADOS_USR = "/administrar/usuarios/estados/eliminar.do?metodo=eliminar&id=";
    private final static String URL_EDITAR_DIR_USR = "/administrar/usuarios/direcciones/editar.do?metodo=editar_inicio&usr_id=";
    private final static String URL_ELIMINAR_DIR_USR = "/administrar/usuarios/direcciones/eliminar.do?metodo=eliminar&usr_id=";
    private final static String URL_EDITAR_DIR_U = "/usuario/datos-personales/direcciones/editar/";
    private final static String URL_ELIMINAR_DIR_U = "/usuario/direcciones/eliminar.do?metodo=eliminar&id=";
    private final static String URL_DETALLE_PEDIDO_U = "/usuario/datos-personales/pedidos/ver-detalles/";
    private final static String URL_DETALLE_PEDIDOS = "/administrar/pedidos/detalle.do?metodo=mostrar_detalle&id=";
    private final static String URL_EDITAR_ESTADOS_PED = "/administrar/pedidos/estados/editar.do?metodo=editar_inicio&id=";
    private final static String URL_ELIMINAR_ESTADOS_PED = "/administrar/pedidos/estados/eliminar.do?metodo=eliminar&id=";
    private final static String URL_EDITAR_LINEAS_PED = "/administrar/pedidos/lineas/editar.do?metodo=editar_inicio&id=";
    private final static String URL_ELIMINAR_LINEAS_PED = "/administrar/pedidos/lineas/eliminar.do?metodo=eliminar&id=";
    private final static String URL_EDITAR_COSTE_ENVIO = "/administrar/pedidos/costeenvio/editar.do?metodo=editar_inicio&id="; 
    private final static String URL_ELIMINAR_COSTE_ENVIO = "/administrar/pedidos/costeenvio/eliminar.do?metodo=eliminar&id=";    
    private final static String URL_EDITAR_CORREO_LISTA = "/administrar/listacorreo/editar.do?metodo=editar_inicio&id=";
    private final static String URL_ELIMINAR_CORREO_LISTA = "/administrar/listacorreo/eliminar.do?metodo=eliminar&id=";
    private final static String URL_EDITAR_OPT = "/administrar/opciones/detalle.do?metodo=mostrar_detalle&id=";
    private final static String URL_ELIMINAR_OPT = "/administrar/opciones/eliminar.do?metodo=eliminar&id=";
    private final static String URL_EDITAR_OPT_VAL = "/administrar/opciones/valores/editar.do?metodo=editar_inicio&id=";
    private final static String URL_ELIMINAR_OPT_VAL = "/administrar/opciones/valores/eliminar.do?metodo=eliminar&id=";
    
    public static String tablaProductos(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_Productos", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("referencia").title(mensajes.getMessage(request.getLocale(), "texto.referencia"));
            row.addColumn(col);

            col = new Column("nombre").title(mensajes.getMessage(request.getLocale(), "texto.nombre"));
            row.addColumn(col);

            col = new Column("precio").title(mensajes.getMessage(request.getLocale(), "texto.precio"));
            row.addColumn(col);

            col = new Column("destacado").title(mensajes.getMessage(request.getLocale(), "texto.destacado"));
            row.addColumn(col);

            col = new Column("iva").title(mensajes.getMessage(request.getLocale(), "texto.iva"));
            row.addColumn(col);

            col = new Column("categoria").title(mensajes.getMessage(request.getLocale(), "texto.categoria"));
            row.addColumn(col);

            col = new Column("stock").title(mensajes.getMessage(request.getLocale(), "texto.stock"));
            row.addColumn(col);

            col = new Column("estado.estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("referencia").title(mensajes.getMessage(request.getLocale(), "texto.referencia"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("nombre").title(mensajes.getMessage(request.getLocale(), "texto.nombre"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("precio").title(mensajes.getMessage(request.getLocale(), "texto.precio"));
            col.setCellEditor(new NumberCellEditor("#.##"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("categoria.nombre").title(mensajes.getMessage(request.getLocale(), "texto.categoria"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("stock").title(mensajes.getMessage(request.getLocale(), "texto.stock"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("estado.estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_DETALLE_PRODUCTOS + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_PROD + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaDescProductos(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_DescProductos", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("idioma").title(mensajes.getMessage(request.getLocale(), "texto.idioma"));
            row.addColumn(col);

            col = new Column("nombre").title(mensajes.getMessage(request.getLocale(), "texto.nombre"));
            row.addColumn(col);

            col = new Column("formato").title(mensajes.getMessage(request.getLocale(), "texto.formato"));
            row.addColumn(col);

            col = new Column("dimensiones").title(mensajes.getMessage(request.getLocale(), "texto.dimensiones"));
            row.addColumn(col);

            col = new Column("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("idioma").title(mensajes.getMessage(request.getLocale(), "texto.idioma"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("nombre").title(mensajes.getMessage(request.getLocale(), "texto.nombre"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("formato").title(mensajes.getMessage(request.getLocale(), "texto.formato"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("dimensiones").title(mensajes.getMessage(request.getLocale(), "texto.dimensiones"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_DESC_PROD + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_DESC_PROD + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaOpcionesProductos(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_OptProductos", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("opcion").title(mensajes.getMessage(request.getLocale(), "texto.opcion"));
            row.addColumn(col);

            col = new Column("precio").title(mensajes.getMessage(request.getLocale(), "texto.precio"));
            row.addColumn(col);

            col = new Column("stock").title(mensajes.getMessage(request.getLocale(), "texto.stock"));
            row.addColumn(col);
            
            col = new Column("activa").title(mensajes.getMessage(request.getLocale(), "texto.activa"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("opcion").title(mensajes.getMessage(request.getLocale(), "texto.opcion"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("precio").title(mensajes.getMessage(request.getLocale(), "texto.precio"));
            col.setCellEditor(new NumberCellEditor("#.##"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("stock").title(mensajes.getMessage(request.getLocale(), "texto.stock"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("activa").title(mensajes.getMessage(request.getLocale(), "texto.activa"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    boolean val=Boolean.parseBoolean(ItemUtils.getItemValue(item, property).toString());
                    Object value;
                    
                    if(val)
                        value= "<img src=\"" + request.getContextPath() + "/images/tienda/Tick.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "texto.true") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "texto.true") + "\" />";
                    else
                        value = "<img src=\"" + request.getContextPath() + "/images/tienda/Cross.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "texto.false") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "texto.false") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_OPT_PROD + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_OPT_PROD + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaCategorias(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_Categorias", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("nombre").title(mensajes.getMessage(request.getLocale(), "texto.nombre"));
            row.addColumn(col);

            col = new Column("activo").title(mensajes.getMessage(request.getLocale(), "texto.activo"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("nombre").title(mensajes.getMessage(request.getLocale(), "texto.nombre"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("nodo").title(mensajes.getMessage(request.getLocale(), "texto.subcategoria"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    int val=Integer.parseInt(ItemUtils.getItemValue(item, property).toString());
                    Object value;
                    
                    if(val>0)
                        value= mensajes.getMessage(request.getLocale(), "texto.si");
                    else
                        value = mensajes.getMessage(request.getLocale(), "texto.no");
                    HtmlBuilder html = new HtmlBuilder();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("activo").title(mensajes.getMessage(request.getLocale(), "texto.activo"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    boolean val=Boolean.parseBoolean(ItemUtils.getItemValue(item, property).toString());
                    Object value;
                    
                    if(val)
                        value= "<img src=\"" + request.getContextPath() + "/images/tienda/Tick.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "texto.true") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "texto.true") + "\" />";
                    else
                        value = "<img src=\"" + request.getContextPath() + "/images/tienda/Cross.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "texto.false") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "texto.false") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_DETALLE_CATEGORIAS + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_CATEGORIAS + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaDescCategorias(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_DescCategorias", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("idioma").title(mensajes.getMessage(request.getLocale(), "texto.idioma"));
            row.addColumn(col);

            col = new Column("nombre").title(mensajes.getMessage(request.getLocale(), "texto.nombre"));
            row.addColumn(col);

            col = new Column("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("idioma").title(mensajes.getMessage(request.getLocale(), "texto.idioma"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("nombre").title(mensajes.getMessage(request.getLocale(), "texto.nombre"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_CATEGORIAS_DESC + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_CATEGORIAS_DESC + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaEstadosProd(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_EstadosProd", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            row.addColumn(col);

            col = new Column("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_ESTADOS_PROD + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_ESTADOS_PROD + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaTextosTienda(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_TextosTienda", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("idioma").title(mensajes.getMessage(request.getLocale(), "texto.idioma"));
            row.addColumn(col);

            col = new Column("tituloTienda").title(mensajes.getMessage(request.getLocale(), "texto.titulo"));
            row.addColumn(col);

            col = new Column("subtituloTienda").title(mensajes.getMessage(request.getLocale(), "texto.subtitulo"));
            row.addColumn(col);

            col = new Column("textoBienvenida").title(mensajes.getMessage(request.getLocale(), "texto.bienvenida"));
            row.addColumn(col);

            col = new Column("textoConocenos").title(mensajes.getMessage(request.getLocale(), "texto.conocenos"));
            row.addColumn(col);

            col = new Column("textoComprar").title(mensajes.getMessage(request.getLocale(), "texto.comprar"));
            row.addColumn(col);

            col = new Column("textoContactar").title(mensajes.getMessage(request.getLocale(), "texto.contactar"));
            row.addColumn(col);

            col = new Column("contactarDias").title(mensajes.getMessage(request.getLocale(), "texto.contactardias"));
            row.addColumn(col);

            col = new Column("contactarHorario").title(mensajes.getMessage(request.getLocale(), "texto.contactarhorario"));
            row.addColumn(col);

            col = new Column("contactarTelefono").title(mensajes.getMessage(request.getLocale(), "texto.contactartlf"));
            row.addColumn(col);

            col = new Column("textoPrivacidad").title(mensajes.getMessage(request.getLocale(), "texto.privacidad"));
            row.addColumn(col);

            col = new Column("textoVenta").title(mensajes.getMessage(request.getLocale(), "texto.venta"));
            row.addColumn(col);

            col = new Column("textoCostes").title(mensajes.getMessage(request.getLocale(), "texto.costes"));
            row.addColumn(col);

            col = new Column("mtHome").title(mensajes.getMessage(request.getLocale(), "texto.mthome"));
            row.addColumn(col);

            col = new Column("mtCategorias").title(mensajes.getMessage(request.getLocale(), "texto.mtcategorias"));
            row.addColumn(col);

            col = new Column("mtProductos").title(mensajes.getMessage(request.getLocale(), "texto.mtproductos"));
            row.addColumn(col);

            col = new Column("mdHome").title(mensajes.getMessage(request.getLocale(), "texto.mdhome"));
            row.addColumn(col);

            col = new Column("mdCategorias").title(mensajes.getMessage(request.getLocale(), "texto.mdcategorias"));
            row.addColumn(col);

            col = new Column("mdProductos").title(mensajes.getMessage(request.getLocale(), "texto.mdproductos"));
            row.addColumn(col);


            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("idioma").title(mensajes.getMessage(request.getLocale(), "texto.idioma"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("tituloTienda").title(mensajes.getMessage(request.getLocale(), "texto.titulo"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("subtituloTienda").title(mensajes.getMessage(request.getLocale(), "texto.subtitulo"));
            htmlRow.addColumn(col);

//            col = new HtmlColumn("textoBienvenida").title(mensajes.getMessage(request.getLocale(), "texto.bienvenida"));
//            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_TEXTOS_TIENDA + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_TEXTOS_TIENDA + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaIvas(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_Ivas", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("iva").title(mensajes.getMessage(request.getLocale(), "texto.iva"));
            row.addColumn(col);

            col = new Column("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("iva").title(mensajes.getMessage(request.getLocale(), "texto.iva"));
            col.setCellEditor(new NumberCellEditor("#"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_TIPOS_IVA + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_TIPOS_IVA + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaUsuarios(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_Usuarios", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("nif").title(mensajes.getMessage(request.getLocale(), "texto.nif"));
            row.addColumn(col);

            col = new Column("nombre").title(mensajes.getMessage(request.getLocale(), "texto.nombre"));
            row.addColumn(col);

            col = new Column("apellidos").title(mensajes.getMessage(request.getLocale(), "texto.apellidos"));
            row.addColumn(col);

            col = new Column("fechaNacimiento").cellEditor(new DateCellEditor("dd/MM/yyyy")).title(mensajes.getMessage(request.getLocale(), "texto.fechanaac"));
            row.addColumn(col);

            col = new Column("telefono").title(mensajes.getMessage(request.getLocale(), "texto.telf"));
            row.addColumn(col);

            col = new Column("email").title(mensajes.getMessage(request.getLocale(), "texto.email"));
            row.addColumn(col);

            col = new Column("usuario").title(mensajes.getMessage(request.getLocale(), "texto.usuario"));
            row.addColumn(col);

            col = new Column("contrasenya").title(mensajes.getMessage(request.getLocale(), "texto.pass"));
            row.addColumn(col);

            col = new Column("rol").title(mensajes.getMessage(request.getLocale(), "texto.rol"));
            row.addColumn(col);

            col = new Column("fechaAlta").cellEditor(new DateCellEditor("dd/MM/yyyy HH:mm:ss")).title(mensajes.getMessage(request.getLocale(), "texto.fechaalta"));
            row.addColumn(col);

            col = new Column("estado.estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("nif").title(mensajes.getMessage(request.getLocale(), "texto.nif"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("nombre").title(mensajes.getMessage(request.getLocale(), "texto.nombre"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("apellidos").title(mensajes.getMessage(request.getLocale(), "texto.apellidos"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("email").title(mensajes.getMessage(request.getLocale(), "texto.email"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("fechaAlta").cellEditor(new DateCellEditor("dd/MM/yyyy")).title(mensajes.getMessage(request.getLocale(), "texto.fechaalta"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("estado.estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_DETALLE_USUARIOS + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.addFilterMatcher(new MatcherKey(Date.class, "fechaAlta"), new DateFilterMatcher("dd/MM/yyyy"));
            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaEstadosUsr(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_EstadosUsr", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            row.addColumn(col);

            col = new Column("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_ESTADOS_USR + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_ESTADOS_USR + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaDireccionesUsr(Collection listado, final HttpServletRequest request, HttpServletResponse response, final String usrId){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_DireccionesUsr", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("direcciona").title(mensajes.getMessage(request.getLocale(), "texto.direcciona"));
            row.addColumn(col);

            col = new Column("direccionb").title(mensajes.getMessage(request.getLocale(), "texto.direccionb"));
            row.addColumn(col);

            col = new Column("cp").title(mensajes.getMessage(request.getLocale(), "texto.cp"));
            row.addColumn(col);

            col = new Column("poblacion").title(mensajes.getMessage(request.getLocale(), "texto.poblacion"));
            row.addColumn(col);

            col = new Column("provincia").title(mensajes.getMessage(request.getLocale(), "texto.provincia"));
            row.addColumn(col);

            col = new Column("pais").title(mensajes.getMessage(request.getLocale(), "texto.pais"));
            row.addColumn(col);

            col = new Column("observaciones").title(mensajes.getMessage(request.getLocale(), "texto.observaciones"));
            row.addColumn(col);
            
            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("direcciona").title(mensajes.getMessage(request.getLocale(), "texto.direcciona"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("cp").title(mensajes.getMessage(request.getLocale(), "texto.cp"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("provincia").title(mensajes.getMessage(request.getLocale(), "texto.provincia"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("pais").title(mensajes.getMessage(request.getLocale(), "texto.pais"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_DIR_USR + usrId + "&id=" + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_DIR_USR + usrId + "&id="  + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaDireccionesUsr(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_DireccionesU", request, response);
        tableModel.setItems(listado); 
        
        if (!tableModel.isExporting()) {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("direcciona").title(mensajes.getMessage(request.getLocale(), "texto.direcciona"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("cp").title(mensajes.getMessage(request.getLocale(), "texto.cp"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("provincia").title(mensajes.getMessage(request.getLocale(), "texto.provincia"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("pais").title(mensajes.getMessage(request.getLocale(), "texto.pais"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_DIR_U + ItemUtils.getItemValue(item, property).toString() + "/").quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_DIR_U + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);
            
            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaPedidosUsr(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_PedidosU", request, response);
        tableModel.setItems(listado); 
        
        if (!tableModel.isExporting()) {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("fecha").cellEditor(new DateCellEditor("dd/MM/yyyy HH:mm:ss")).title(mensajes.getMessage(request.getLocale(), "texto.fecha"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("usuario.usuario").title(mensajes.getMessage(request.getLocale(), "texto.user"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("tipoenv.tipo").title(mensajes.getMessage(request.getLocale(), "texto.envio"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("total").title(mensajes.getMessage(request.getLocale(), "texto.total"));
            col.setCellEditor(new NumberCellEditor("#.##"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("formaPago").title(mensajes.getMessage(request.getLocale(), "texto.formapago"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    String val=ItemUtils.getItemValue(item, property).toString();
                    Object value;
                    
                    if(val.equalsIgnoreCase("pp"))
                        value= mensajes.getMessage(request.getLocale(), "texto.paypal");
                    else if(val.equalsIgnoreCase("tr"))
                        value = mensajes.getMessage(request.getLocale(), "texto.transferencia");
                    else if(val.equalsIgnoreCase("ef"))
                        value = mensajes.getMessage(request.getLocale(), "texto.efectivo");
                    else 
                        value = mensajes.getMessage(request.getLocale(), "texto.creembolso");
                    
                    HtmlBuilder html = new HtmlBuilder();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("estado.estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.ver"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Search.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.ver") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.ver") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_DETALLE_PEDIDO_U + ItemUtils.getItemValue(item, property).toString() + "/").quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.addFilterMatcher(new MatcherKey(Date.class, "fecha"), new DateFilterMatcher("dd/MM/yyyy"));
            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaLineasPedidoUsr(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_LineasPedU", request, response);
        tableModel.setItems(listado); 
        
        if (!tableModel.isExporting()) {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("nlinea").title(mensajes.getMessage(request.getLocale(), "texto.nlinea"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("producto.referencia").title(mensajes.getMessage(request.getLocale(), "texto.referencia"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("cantidad").title(mensajes.getMessage(request.getLocale(), "texto.cantidad"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("precio").title(mensajes.getMessage(request.getLocale(), "texto.precio"));
            col.setCellEditor(new NumberCellEditor("#.##"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("subtotal").title(mensajes.getMessage(request.getLocale(), "texto.subtotal"));
            col.setCellEditor(new NumberCellEditor("#.##"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("opcionProducto").title(mensajes.getMessage(request.getLocale(), "texto.opcion"));
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaPedidos(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_Pedido", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("fecha").cellEditor(new DateCellEditor("dd/MM/yyyy HH:mm:ss")).title(mensajes.getMessage(request.getLocale(), "texto.fecha"));
            row.addColumn(col);

            col = new Column("usuario.usuario").title(mensajes.getMessage(request.getLocale(), "texto.user"));
            row.addColumn(col);
            
            col = new Column("direccionEnvio").title(mensajes.getMessage(request.getLocale(), "texto.elijadirenv"));
            row.addColumn(col);

            col = new Column("direccionFactura").title(mensajes.getMessage(request.getLocale(), "texto.elijadirfact"));
            row.addColumn(col);

//            col = new Column("gestion").title(mensajes.getMessage(request.getLocale(), "texto.gestion"));
//            row.addColumn(col);
            
            col = new Column("tipoenv.tipo").title(mensajes.getMessage(request.getLocale(), "texto.envio"));
            row.addColumn(col);

            col = new Column("formaPago").title(mensajes.getMessage(request.getLocale(), "texto.formapago"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    String val=ItemUtils.getItemValue(item, property).toString();
                    Object value;
                    
                    if(val.equalsIgnoreCase("pp"))
                        value= mensajes.getMessage(request.getLocale(), "texto.paypal");
                    else if(val.equalsIgnoreCase("tr"))
                        value = mensajes.getMessage(request.getLocale(), "texto.transferencia");
                    else if(val.equalsIgnoreCase("ef"))
                        value = mensajes.getMessage(request.getLocale(), "texto.efectivo");
                    else 
                        value = mensajes.getMessage(request.getLocale(), "texto.creembolso");
                    
                    HtmlBuilder html = new HtmlBuilder();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            row.addColumn(col);

            col = new Column("total").title(mensajes.getMessage(request.getLocale(), "texto.total"));
            row.addColumn(col);

            col = new Column("estado.estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("fecha").cellEditor(new DateCellEditor("dd/MM/yyyy HH:mm:ss")).title(mensajes.getMessage(request.getLocale(), "texto.fecha"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("usuario.usuario").title(mensajes.getMessage(request.getLocale(), "texto.user"));
            htmlRow.addColumn(col);
            
//            col = new HtmlColumn("gestion").title(mensajes.getMessage(request.getLocale(), "texto.gestion"));
//            htmlRow.addColumn(col);

            col = new HtmlColumn("tipoenv.tipo").title(mensajes.getMessage(request.getLocale(), "texto.envio"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("total").title(mensajes.getMessage(request.getLocale(), "texto.total"));
            col.setCellEditor(new NumberCellEditor("#.##"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("formaPago").title(mensajes.getMessage(request.getLocale(), "texto.formapago"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    String val=ItemUtils.getItemValue(item, property).toString();
                    Object value;
                    
                    if(val.equalsIgnoreCase("pp"))
                        value= mensajes.getMessage(request.getLocale(), "texto.paypal");
                    else if(val.equalsIgnoreCase("tr"))
                        value = mensajes.getMessage(request.getLocale(), "texto.transferencia");
                    else if(val.equalsIgnoreCase("ef"))
                        value = mensajes.getMessage(request.getLocale(), "texto.efectivo");
                    else 
                        value = mensajes.getMessage(request.getLocale(), "texto.creembolso");
                    
                    HtmlBuilder html = new HtmlBuilder();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("estado.estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_DETALLE_PEDIDOS + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);
            
            tableModel.addFilterMatcher(new MatcherKey(Date.class, "fecha"), new DateFilterMatcher("dd/MM/yyyy"));
            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaEstadosPed(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_EstadosPed", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            row.addColumn(col);

            col = new Column("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("estado").title(mensajes.getMessage(request.getLocale(), "texto.estado"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_ESTADOS_PED + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_ESTADOS_PED + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);
            
            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaLineasPedido(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_LineasPed", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("pedido.id").title(mensajes.getMessage(request.getLocale(), "texto.pedidoid"));
            row.addColumn(col);

            col = new Column("nlinea").title(mensajes.getMessage(request.getLocale(), "texto.nlinea"));
            row.addColumn(col);

            col = new Column("producto.referencia").title(mensajes.getMessage(request.getLocale(), "texto.referencia"));
            row.addColumn(col);

            col = new Column("cantidad").title(mensajes.getMessage(request.getLocale(), "texto.cantidad"));
            row.addColumn(col);

            col = new Column("precio").title(mensajes.getMessage(request.getLocale(), "texto.precio"));
            row.addColumn(col);

            col = new Column("iva").title(mensajes.getMessage(request.getLocale(), "texto.iva"));
            row.addColumn(col);

            col = new Column("subtotal").title(mensajes.getMessage(request.getLocale(), "texto.subtotal"));
            row.addColumn(col);

            col = new Column("opcionProducto").title(mensajes.getMessage(request.getLocale(), "texto.opcion"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("nlinea").title(mensajes.getMessage(request.getLocale(), "texto.nlinea"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("producto.referencia").title(mensajes.getMessage(request.getLocale(), "texto.referencia"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("cantidad").title(mensajes.getMessage(request.getLocale(), "texto.cantidad"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("precio").title(mensajes.getMessage(request.getLocale(), "texto.precio"));
            col.setCellEditor(new NumberCellEditor("#.##"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("subtotal").title(mensajes.getMessage(request.getLocale(), "texto.subtotal"));
            col.setCellEditor(new NumberCellEditor("#.##"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("opcionProducto").title(mensajes.getMessage(request.getLocale(), "texto.opcion"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_LINEAS_PED + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_LINEAS_PED + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaListaCorreos(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_DescCategorias", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("email").title(mensajes.getMessage(request.getLocale(), "texto.email"));
            row.addColumn(col);

            col = new Column("activo").title(mensajes.getMessage(request.getLocale(), "texto.activo"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("email").title(mensajes.getMessage(request.getLocale(), "texto.email"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("activo").title(mensajes.getMessage(request.getLocale(), "texto.activo"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    boolean val=Boolean.parseBoolean(ItemUtils.getItemValue(item, property).toString());
                    Object value;
                    
                    if(val)
                        value= "<img src=\"" + request.getContextPath() + "/images/tienda/Tick.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "texto.true") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "texto.true") + "\" />";
                    else
                        value = "<img src=\"" + request.getContextPath() + "/images/tienda/Cross.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "texto.false") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "texto.false") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_CORREO_LISTA + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_CORREO_LISTA + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaCostesEnvio(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_CostesEnvio", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("tipo").title(mensajes.getMessage(request.getLocale(), "texto.tipo"));
            row.addColumn(col);

            col = new Column("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            row.addColumn(col);

            col = new Column("precio").title(mensajes.getMessage(request.getLocale(), "texto.precio"));
            row.addColumn(col);

            col = new Column("activo").title(mensajes.getMessage(request.getLocale(), "texto.activo"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("tipo").title(mensajes.getMessage(request.getLocale(), "texto.tipo"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            htmlRow.addColumn(col);

            col = new HtmlColumn("precio").title(mensajes.getMessage(request.getLocale(), "texto.precio"));
            col.setCellEditor(new NumberCellEditor("#.##"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("activo").title(mensajes.getMessage(request.getLocale(), "texto.activo"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    boolean val=Boolean.parseBoolean(ItemUtils.getItemValue(item, property).toString());
                    Object value;
                    
                    if(val)
                        value= "<img src=\"" + request.getContextPath() + "/images/tienda/Tick.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "texto.true") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "texto.true") + "\" />";
                    else
                        value = "<img src=\"" + request.getContextPath() + "/images/tienda/Cross.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "texto.false") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "texto.false") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_COSTE_ENVIO + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_COSTE_ENVIO + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaOpciones(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_Opciones", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("opcion").title(mensajes.getMessage(request.getLocale(), "texto.opcion"));
            row.addColumn(col);

            col = new Column("descripcion").title(mensajes.getMessage(request.getLocale(), "texto.descripcion"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("opcion").title(mensajes.getMessage(request.getLocale(), "texto.opcion"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_OPT + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_OPT + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
    
    public static String tablaOpcionesValores(Collection listado, final HttpServletRequest request, HttpServletResponse response){
        MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        TableModel tableModel = new TableModel("Tabla_OpcionesValores", request, response);
        tableModel.setExportTypes(ExportType.CSV, ExportType.JEXCEL);
        tableModel.setItems(listado); 
        
        if (tableModel.isExporting()) {
            Table table = new Table();

            Row row = new Row();
            table.setRow(row);

            Column col = new Column("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            row.addColumn(col);

            col = new Column("valor").title(mensajes.getMessage(request.getLocale(), "texto.valor"));
            row.addColumn(col);

            tableModel.setTable(table);
        } else {
            HtmlTable htmlTable = new HtmlTable().width("570px");

            HtmlRow htmlRow = new HtmlRow();
            htmlTable.setRow(htmlRow);

            HtmlColumn col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "texto.id"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("valor").title(mensajes.getMessage(request.getLocale(), "texto.valor"));
            htmlRow.addColumn(col);
            
            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.verdetalles"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Pencil.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.verdetalles") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_EDITAR_OPT_VAL + ItemUtils.getItemValue(item, property).toString()).quote().close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            col = new HtmlColumn("id").title(mensajes.getMessage(request.getLocale(), "campos.eliminar"));
            col.setCellEditor(new CellEditor() {
                @Override
                public Object getValue(Object item, String property, int rowcount) {
                    MessageResources mensajes = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
                    Object value = "<img src=\"" + request.getContextPath() + "/images/tienda/Trash.png\" alt=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" title=\"" + mensajes.getMessage(request.getLocale(), "campos.eliminar") + "\" />";
                    HtmlBuilder html = new HtmlBuilder();
                    html.a().href().quote().append(request.getContextPath() + URL_ELIMINAR_OPT_VAL + ItemUtils.getItemValue(item, property).toString()).quote().onclick("javascript:return confirmar('" + mensajes.getMessage(request.getLocale(), "texto.confirmar") + "')").close();
                    html.append(value);
                    html.aEnd();
                    return html.toString();
                }
            });
            col.styleClass("centrado");
            htmlRow.addColumn(col);

            tableModel.setTable(htmlTable);
        }
        String resultado = tableModel.render();
        
        return resultado;
    }
}
