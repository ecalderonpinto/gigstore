/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

import direccion.Direccion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import javax.persistence.*;
import org.apache.struts.util.MessageResources;
import producto.ProductoDescripcion;
import usuario.Usuario;

/**
 *
 * @author Manel Márquez Bonilla
 */
@Entity
@Table(name="pedidos")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Date fecha;
    private Date fechaPago;
    private CosteGestion tipoges;
    private BigDecimal gestion;
    private CosteEnvio tipoenv;
    private BigDecimal envio;
    private BigDecimal total;
    private PedidoEstado estado;
    private Usuario usuario;
    private Direccion dirFactura;
    private String direccionFactura;
    private Direccion dirEnvio;
    private String direccionEnvio;
    private Set<PedidoLinea> lineas;    
    private String formaPago;
    private BigDecimal precioFP;
    private String respuestaPayPal;

    public Pedido() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ped_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
    @Column(name="ped_envio",columnDefinition="DECIMAL(15,2)")
    public BigDecimal getEnvio() {
        return envio;
    }

    public void setEnvio(BigDecimal envio) {
        this.envio = envio;
    }

    @ManyToOne
    @JoinColumn(name="ped_estadoid")
    public PedidoEstado getEstado() {
        return estado;
    }

    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="ped_fecha")    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="ped_fechapago")  
    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Column(name="ped_gestion",columnDefinition="DECIMAL(15,2)")
    public BigDecimal getGestion() {
        return gestion;
    }

    public void setGestion(BigDecimal gestion) {
        this.gestion = gestion;
    }

    @OneToMany(mappedBy="pedido", fetch= FetchType.EAGER)
    public Set<PedidoLinea> getLineas() {
        return lineas;
    }

    public void setLineas(Set<PedidoLinea> lineas) {
        this.lineas = lineas;
    }

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="ped_usuarioid")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Column(name="ped_total",columnDefinition="DECIMAL(15,2)")
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Lob
    @Column(name="ped_direccionE")
    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    @Lob
    @Column(name="ped_direccionF")
    public String getDireccionFactura() {
        return direccionFactura;
    }

    public void setDireccionFactura(String direccionFactura) {
        this.direccionFactura = direccionFactura;
    }

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="ped_tipoenvid")
    public CosteEnvio getTipoenv() {
        return tipoenv;
    }

    public void setTipoenv(CosteEnvio tipoenv) {
        this.tipoenv = tipoenv;
    }

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="ped_tipogesid")
    public CosteGestion getTipoges() {
        return tipoges;
    }

    public void setTipoges(CosteGestion tipoges) {
        this.tipoges = tipoges;
    }

    @Column(name="ped_formapago", length=30)
    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ped_direnvioid")
    public Direccion getDirEnvio() {
        return dirEnvio;
    }

    public void setDirEnvio(Direccion dirEnvio) {
        this.dirEnvio = dirEnvio;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ped_dirfacturaid")
    public Direccion getDirFactura() {
        return dirFactura;
    }

    public void setDirFactura(Direccion dirFactura) {
        this.dirFactura = dirFactura;
    }

    @Lob
    @Column(name="ped_respuestapaypal")
    public String getRespuestaPayPal() {
        return respuestaPayPal;
    }

    public void setRespuestaPayPal(String respuestaPayPal) {
        this.respuestaPayPal = respuestaPayPal;
    }

    @Column(name="ped_preciofp",columnDefinition="DECIMAL(15,2)")
    public BigDecimal getPrecioFP() {
        return precioFP;
    }

    public void setPrecioFP(BigDecimal precioFP) {
        this.precioFP = precioFP;
    }
     
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        NumberFormat nf=new DecimalFormat("#.##");
        String res="<b>Fecha</b>: " + df.format(fecha) + " <br/> ";
        res+="<b>Direcci&oacute;n Env&iacute;o</b>: " + direccionEnvio + " <br/> ";
        res+="<b>Direcci&oacute;n Factura</b>: " + direccionFactura + " <br/> ";        
        res+="<b>Forma de pago</b>: " + formaPago + " <br/> ";        
        res+="<b>Productos</b>: ";
        res+="<table border=0> ";
        res+="<tr><th>Nombre</th><th>Cant.</th><th>Precio</th><th>Subtotal</th></tr>";        
        BigDecimal st=BigDecimal.ZERO;
        for(PedidoLinea lin: lineas){
            res+="<tr><td>" + lin.getProducto().getNombre();
            if(lin.getOpcionProducto()!=null)
                res+=" " + lin.getOpcionProducto();
            res+="</td><td style=\"text-align:right\">" + lin.getCantidad() + "</td><td style=\"text-align:right\">" + nf.format(lin.getPrecio()) + "€</td><td style=\"text-align:right\">" + nf.format(lin.getSubtotal()) + "€</td></tr>";
            st=st.add(lin.getSubtotal());
        }
        res+="<tr><td colspan=3><b>Subtotal: </b></td><td style=\"text-align:right\">" + nf.format(st) + "€</td></tr>";        
        res+="<tr><td colspan=3><b>Costes de env&iacute;o: </b></td><td style=\"text-align:right\">" + nf.format(envio) + "€</td></tr>";    
        res+="<tr><td colspan=3><b>Costes de froma de pago: </b></td><td style=\"text-align:right\">" + nf.format(precioFP) + "€</td></tr>";     
        res+="<tr><td colspan=3><b>Total pedido: </b></td><td style=\"text-align:right\">" + nf.format(total) + "€</td></tr>";        
        res+="</table> ";
        
        return res;
    }
    
    public String toString(Locale locale) {
        MessageResources msg = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        NumberFormat nf=new DecimalFormat("#.##");
        String res="<b>" + msg.getMessage(locale, "texto.fecha") + "</b>: " + df.format(fecha) + " <br/> ";
        res+="<b>" + msg.getMessage(locale, "texto.direccionEnvio") + "</b>: " + direccionEnvio + " <br/> ";
        res+="<b>" + msg.getMessage(locale, "texto.direccionFactura") + "</b>: " + direccionFactura + " <br/> ";        
        res+="<b>" + msg.getMessage(locale, "texto.formapago") + "</b>: " + formaPago + " <br/> ";        
        res+="<b>" + msg.getMessage(locale, "texto.datosped") + "</b>: ";
        res+="<table border=0> ";
        res+="<tr><th>" + msg.getMessage(locale, "texto.nombre") + "</th><th>" + msg.getMessage(locale, "texto.unidades") + "</th><th>" + msg.getMessage(locale, "texto.precio") + "</th><th>" + msg.getMessage(locale, "texto.subtotal") + "</th></tr>";        
        BigDecimal st=BigDecimal.ZERO;
        for(PedidoLinea lin: lineas){
            String d="";
            for(ProductoDescripcion desc: lin.getProducto().getDescripciones()){
                if(desc.getIdioma().equalsIgnoreCase(locale.getLanguage()))
                    d=desc.getNombre();
            }
            if(d.isEmpty())
                d=lin.getProducto().getNombre();
            res+="<tr><td>" + d;
            if(lin.getOpcionProducto()!=null){
                String opt="";
                if(lin.getOpcion()!=null){
                    for(String trad:lin.getOpcion().getTraducciones()){
                        if(trad.split(";")[0].equalsIgnoreCase(locale.getLanguage()))
                            opt=trad.split(";")[1];
                    }
                }
                if(opt.isEmpty())
                    opt=lin.getOpcionProducto();
                res+=" " + opt;
            }
            res+="</td><td style=\"text-align:right\">" + lin.getCantidad() + "</td><td style=\"text-align:right\">" + nf.format(lin.getPrecio()) + "€</td><td style=\"text-align:right\">" + nf.format(lin.getSubtotal()) + "€</td></tr>";
            st=st.add(lin.getSubtotal());
        }
        res+="<tr><td colspan=3><b>" + msg.getMessage(locale, "texto.subtotal") + ": </b></td><td style=\"text-align:right\">" + nf.format(st) + "€</td></tr>";        
        res+="<tr><td colspan=3><b>" + msg.getMessage(locale, "texto.costes") + ": </b></td><td style=\"text-align:right\">" + nf.format(envio) + "€</td></tr>";  
        res+="<tr><td colspan=3><b>" + msg.getMessage(locale, "texto.costesfp") + ": </b></td><td style=\"text-align:right\">" + nf.format(precioFP) + "€</td></tr>";        
        res+="<tr><td colspan=3><b>" + msg.getMessage(locale, "texto.total") + ": </b></td><td style=\"text-align:right\">" + nf.format(total) + "€</td></tr>";        
        res+="</table> ";
        
        return res;
    }
    
}
