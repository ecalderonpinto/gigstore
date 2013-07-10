/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

import direccion.DireccionDAO;
import dominio.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.util.MessageResources;
import producto.Producto;
import producto.ProductoDAO;
import producto.ProductoOpcion;
import producto.ProductoOpcionDAO;
import usuario.Usuario;

/**
 *
 * @author Manel Márquez Bonilla
 */
public class PedidoAction extends LookupDispatchAction {    
    private final static String CANCEL_URL = "http://localhost:8084/ejemplo-tienda/pedido/detalle.do?metodo=mostrar_detalle";
    private final static String RETURN_URL = "http://localhost:8084/ejemplo-tienda/pedido/confirmar/paso2b.do?metodo=capturar_pedido";
//    private final static String CANCEL_URL = "http://www.gignomai.es/ejemplo-tienda/pedido/detalle.do?metodo=mostrar_detalle";
//    private final static String RETURN_URL = "http://www.gignomai.es/ejemplo-tienda/pedido/confirmar/paso2b.do?metodo=capturar_pedido";
    
    private final static String USUARIO_SESION = "usrSesion"; // Nombre de la variable de sesión que contendrá al usuario logeado
    private final static int PEDIDO_ESTADO = 1; // Id del Estado por defecto con el que se generan los pedidos
    private final static String SUCCESS = "success";
    private final static String FAIL = "fail";

    /**
     * Provides the mapping from resource key to method name.
     *
     * @return Resource key / method name map.
     */
    @Override
    protected Map getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("boton.acarrito", "agregar_linea");
        map.put("accion.mostrar_detalle", "mostrar_detalle");
        map.put("accion.eliminar", "eliminar");
        map.put("accion.sumar", "sumar");
        map.put("accion.restar", "restar");
        map.put("accion.confirmar_inicio", "confirmar_inicio");
        map.put("accion.configurar_pedido", "configurar_pedido");
        map.put("accion.capturar_pedido", "capturar_pedido");
        map.put("accion.resumen_pedido", "resumen_pedido");
        map.put("accion.confirmar_pedido", "confirmar_pedido");
        return map;
    }

    /**
     * Action called on boton.acarrito button click
     */
    public ActionForward agregar_linea (ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");
        Producto prod = prodDAO.consultaProducto(Integer.parseInt(request.getParameter("prodId")));
        ProductoOpcionDAO optDAO=(ProductoOpcionDAO) request.getSession().getServletContext().getAttribute("poptDAO");
        ProductoOpcion opt;
        PedidoLinea lin=new PedidoLinea();
        ArrayList<PedidoLinea> carrito=new ArrayList<PedidoLinea>();
        
        try{
            lin.setProducto(prod);
            
            lin.setOpcion(null);  
            opt=new ProductoOpcion();
            if(request.getParameter("opcion")!=null)
                opt=optDAO.consultaProductoOpcion(Integer.parseInt(request.getParameter("opcion").split(";")[0]));
            
            if(opt.getId()>0){
                lin.setOpcionProducto(opt.getOpcion());                
                lin.setOpcion(opt);
            }else{
                lin.setOpcionProducto("");
                opt.setOpcion("");
            }
            
            lin.setCantidad(Integer.parseInt(request.getParameter("unidades")));
            lin.setIva(prod.getIva().getIva());
            lin.setPrecio(prod.getPrecio());
            if(opt.getPrecio()!=null)
                lin.setPrecio(opt.getPrecio());
            if(prod.getDescuento().compareTo(BigDecimal.ZERO)>0){
                if(prod.isDescporcentaje()) {
                    BigDecimal totaldesc=lin.getPrecio().multiply(prod.getDescuento()).divide(new BigDecimal(100));
                    lin.setPrecio(lin.getPrecio().subtract(totaldesc));
                } else
                    lin.setPrecio(lin.getPrecio().subtract(prod.getDescuento()));
            }
            
            if(prod.getCategoria().getDescuento().compareTo(BigDecimal.ZERO)>0){
                if(prod.getCategoria().isDescporcentaje()) {
                    BigDecimal totaldesc=lin.getPrecio().multiply(prod.getCategoria().getDescuento()).divide(new BigDecimal(100));
                    lin.setPrecio(lin.getPrecio().subtract(totaldesc));
                } else
                    lin.setPrecio(lin.getPrecio().subtract(prod.getCategoria().getDescuento()));
            }
            
            lin.setSubtotal(lin.getPrecio().multiply(new BigDecimal(lin.getCantidad())));

            if(request.getSession().getAttribute("carrito")==null){
                lin.setNlinea(1);
                carrito.add(lin);
                request.getSession().setAttribute("carrito", carrito);
            }else{
                carrito=(ArrayList<PedidoLinea>) request.getSession().getAttribute("carrito");
            
                boolean encontrado=false;
                for(PedidoLinea lp: carrito){
                    if(lp.getProducto().getId()==lin.getProducto().getId()){
                        if(((lp.getOpcion()==null && lin.getOpcion()==null) || lp.getOpcion().getId()==lin.getOpcion().getId())){                            
                            lp.setCantidad(lp.getCantidad() + Integer.parseInt(request.getParameter("unidades")));
                            lp.setSubtotal(lp.getPrecio().multiply(new BigDecimal(lp.getCantidad())));
                            encontrado=true;
                        }
                    }
                }
                if(!encontrado){
                    lin.setNlinea(carrito.size() + 1);
                    carrito.add(lin);
                }
                request.getSession().setAttribute("carrito", carrito);                
            }
        }catch(Exception ex){
            request.setAttribute("error", "true"); 
        }
        
        ActionForward af=new ActionForward();
        af.setPath("/categorias/" + Cadenas.formatoUrl(prod.getCategoria().getNombre()) + "/" + prod.getId() + "-" + Cadenas.formatoUrl(prod.getNombre()) + "/");
        af.setRedirect(true);
        
        return af; 
    }

    /**
     * Action called on mostrar_detalle button click
     */
    public ActionForward mostrar_detalle (ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws java.lang.Exception {
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on eliminar button click
     */
    public ActionForward eliminar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ArrayList<PedidoLinea> carrito=(ArrayList<PedidoLinea>) request.getSession().getAttribute("carrito");
        ArrayList<PedidoLinea> aux=new ArrayList<PedidoLinea>();
        int lin=Integer.parseInt(request.getParameter("lin"));
        
        for(PedidoLinea lp: carrito){
            if(lp.getNlinea()!=lin){
                aux.add(lp);
            }
        }                
        
        request.getSession().setAttribute("carrito", aux); 
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on sumar button click
     */
    public ActionForward sumar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ArrayList<PedidoLinea> carrito=(ArrayList<PedidoLinea>) request.getSession().getAttribute("carrito");
        ArrayList<PedidoLinea> aux=new ArrayList<PedidoLinea>();
        int lin=Integer.parseInt(request.getParameter("lin"));
        
        for(PedidoLinea lp: carrito){
            if(lp.getNlinea()==lin){
                lp.setCantidad(lp.getCantidad()+1);
                lp.setSubtotal(lp.getPrecio().multiply(new BigDecimal(lp.getCantidad())));
            }
            aux.add(lp);
        }                
        request.getSession().setAttribute("carrito", aux); 
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on restar button click
     */
    public ActionForward restar(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        ArrayList<PedidoLinea> carrito=(ArrayList<PedidoLinea>) request.getSession().getAttribute("carrito");
        ArrayList<PedidoLinea> aux=new ArrayList<PedidoLinea>();
        int lin=Integer.parseInt(request.getParameter("lin"));
        
        for(PedidoLinea lp: carrito){
            if(lp.getNlinea()==lin && lp.getCantidad()>1){
                lp.setCantidad(lp.getCantidad()-1);
                lp.setSubtotal(lp.getPrecio().multiply(new BigDecimal(lp.getCantidad())));
            }
            aux.add(lp);
        }                 
        request.getSession().setAttribute("carrito", aux); 
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on confirmar_inicio button click
     */
    public ActionForward confirmar_inicio(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        CosteEnvioDAO envDAO=(CosteEnvioDAO) request.getSession().getServletContext().getAttribute("envDAO");
        
        if(request.getSession().getAttribute(USUARIO_SESION)==null){
            request.getSession().setAttribute("login_pedido", "true"); 
            return mapping.findForward(FAIL);
        }else{        
            MessageResources mens = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
            Locale locale=request.getLocale();
            PedidoForm pf=(PedidoForm) form;
            
            pf.setFormaPago("tr");            
            
            request.getSession().setAttribute("envios", envDAO.devuelveCosteEnvios()); 
            request.getSession().setAttribute("origen", "/pedido/confirmar/paso1.do?metodo=" + mens.getMessage(locale, "enlace.confirmar"));
        }
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on configurar_pedido button click
     */
    public ActionForward configurar_pedido(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        PedidoForm pf=(PedidoForm) form;
        Pedido ped = new Pedido();
        CosteEnvioDAO envioDAO=(CosteEnvioDAO) request.getSession().getServletContext().getAttribute("envDAO");
        PedidoEstadoDAO estDAO=(PedidoEstadoDAO) request.getSession().getServletContext().getAttribute("peDAO");
        DireccionDAO dirDAO=(DireccionDAO) request.getSession().getServletContext().getAttribute("dirDAO");
        ArrayList<PedidoLinea> carrito=(ArrayList<PedidoLinea>) request.getSession().getAttribute("carrito");
        ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
        MessageResources msg = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
        Locale locale=request.getLocale();
        DecimalFormat df=new DecimalFormat("#.##");
        BigDecimal totalLineas=BigDecimal.ZERO;
        BigDecimal totalIVA=BigDecimal.ZERO;
        totalLineas.setScale(2);
        totalIVA.setScale(2);
        
        
        if(request.getSession().getAttribute(USUARIO_SESION)==null){
            return mapping.findForward(FAIL);
        }else{      
            CosteEnvio envio=envioDAO.consultaCosteEnvio(pf.getTipoenv());
            
            request.getSession().removeAttribute("origen");
            request.getSession().removeAttribute("envios");
            
            ped.setFormaPago(pf.getFormaPago());                
            if(ped.getFormaPago().equalsIgnoreCase("pp"))
                ped.setPrecioFP(cfg.getPrecioPayPal());
            else if(ped.getFormaPago().equalsIgnoreCase("tr"))
                ped.setPrecioFP(cfg.getPrecioTransferencia());
            else if(ped.getFormaPago().equalsIgnoreCase("ef"))
                ped.setPrecioFP(cfg.getPrecioEfectivo());
            else if(ped.getFormaPago().equalsIgnoreCase("cr"))
                ped.setPrecioFP(cfg.getPrecioReembolso());
            else 
                ped.setPrecioFP(BigDecimal.ZERO);
            
            ped.setTipoenv(envio);
            ped.setEnvio(envio.getPrecio());
            ped.setEstado(estDAO.consultaEstado(PEDIDO_ESTADO));
            ped.setUsuario((Usuario)request.getSession().getAttribute(USUARIO_SESION));
            if(pf.getDireccionEnvio().equalsIgnoreCase(msg.getMessage(locale, "texto.recogida"))){
                ped.setDirEnvio(null);
                ped.setDirFactura(null);
                ped.setDireccionEnvio(msg.getMessage(locale, "texto.recogida"));
                ped.setDireccionFactura(msg.getMessage(locale, "texto.recogida"));
            }else{
                ped.setDirEnvio(dirDAO.consultaDireccion(Integer.parseInt(pf.getDireccionEnvio())));
                ped.setDireccionEnvio(ped.getDirEnvio().toString());  

                if(pf.getDireccionFactura()!=null){
                    ped.setDirFactura(dirDAO.consultaDireccion(Integer.parseInt(pf.getDireccionFactura())));
                    ped.setDireccionFactura(ped.getDirFactura().toString());
                }else
                    ped.setDireccionFactura("N/A");                
            }
            
            ped.setFormaPago(pf.getFormaPago());
            
            ped.setTotal(BigDecimal.ZERO);
            for(PedidoLinea lp: carrito){
                ped.setTotal(ped.getTotal().add(lp.getSubtotal()));
                totalIVA=totalIVA.add(TipoIvaDAO.calcIva(lp.getSubtotal(),lp.getIva()));
            }
            totalLineas=ped.getTotal();
            ped.setTotal(ped.getTotal().add(ped.getTipoenv().getPrecio()));
            ped.setTotal(ped.getTotal().add(ped.getPrecioFP()));
            if(!cfg.isMostrarIVAIncluido())
                ped.setTotal(ped.getTotal().add(totalIVA));
            
            request.getSession().setAttribute("pedido", ped); 
            request.getSession().setAttribute("Payment_Amount", df.format(ped.getTotal()).replace(',', '.'));
            
            /*
            ==================================================================
            PayPal Express Checkout - MARK FLOW : START SNIPPET
            ===================================================================
            */
            //IMPORTANT NOTE: Please import Class paypalfunctions if not in the same package level.
            // import paypalfunctions;

            if (pf.getFormaPago().equals("pp")) {
                /*
                '------------------------------------
                ' The paymentAmount is the total value of 
                ' the shopping cart, that was set 
                ' earlier in a session variable 
                ' by the shopping cart page
                '------------------------------------
                */

                String paymentAmount = (String)request.getSession().getAttribute("Payment_Amount");

                /*
                '------------------------------------
                ' The currencyCodeType and paymentType 
                ' are set to the selections made on the Integration Assistant 
                '------------------------------------
                */

                String currencyCodeType = "EUR";
                String paymentType = "Sale";

                /*
                '------------------------------------
                ' The returnURL is the location where buyers return to when a
                ' payment has been succesfully authorized.
                '
                ' This is set to the value entered on the Integration Assistant 
                '------------------------------------
                */
                String returnURL = RETURN_URL;
                /*
                '------------------------------------
                ' The cancelURL is the location buyers are sent to when they hit the
                ' cancel button during authorization of payment during the PayPal flow
                '
                ' This is set to the value entered on the Integration Assistant 
                '------------------------------------
                */
                String cancelURL = CANCEL_URL;
                /*
                '------------------------------------
                ' When you integrate this code 
                ' set the variables below with 
                ' shipping address details 
                ' entered by the user on the 
                ' Shipping page.
                '------------------------------------
                */

                String shipToName = ped.getUsuario().getNombre() + " " + ped.getUsuario().getApellidos();
                String shipToStreet = ped.getDirEnvio().getDirecciona();
                String shipToStreet2 = ped.getDirEnvio().getDireccionb(); //'Leave it blank if there is no value
                String shipToCity = ped.getDirEnvio().getPoblacion();
                String shipToState = ped.getDirEnvio().getProvincia();
                String shipToCountryCode = "ES"; //' Please refer to the PayPal country codes in the API documentation
                String shipToZip = ped.getDirEnvio().getCp();
                String phoneNum = ped.getUsuario().getTelefono();
                String itemATM=df.format(totalLineas).replace(',', '.');
                String shippingATM=df.format(envio.getPrecio().add(ped.getPrecioFP())).replace(',', '.');
                String taxATM=df.format(totalIVA).replace(',', '.');

                /*
                '------------------------------------
                ' Calls the SetExpressCheckout API call
                '
                ' The CallMarkExpressCheckout function is defined in the file PayPalFunctions.asp,
                ' it is included at the top of this file.
                '-------------------------------------------------
                */
                paypalfunctions ppf = new paypalfunctions();
                java.util.HashMap nvp = ppf.CallMarkExpressCheckout(paymentAmount, returnURL, cancelURL, shipToName, shipToStreet, shipToStreet2, shipToCity, shipToState, shipToCountryCode, shipToZip, phoneNum, currencyCodeType, paymentType, itemATM, shippingATM, taxATM, carrito);

                String strAck = nvp.get("ACK").toString();
                if(strAck==null || !(strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning"))) {
                    // Display a user friendly Error on the page using any of the following error information returned by PayPal
                    String ErrorCode = nvp.get("L_ERRORCODE0").toString();
                    String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
                    String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
                    String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
                    
                    throw new Exception(ErrorCode + " " + ErrorShortMsg + " " + ErrorSeverityCode + " " + ErrorLongMsg);
                } else {  
                    request.getSession().setAttribute("token", nvp.get("TOKEN").toString());
                    //' Redirect to paypal.com
                    ppf.RedirectURL(response, nvp.get("TOKEN").toString());                            
                }
            }
//            else
//            {
//            if (((PaymentOption == "Visa") || (PaymentOption == "MasterCard") || (PaymentOption == "Amex") || (PaymentOption = "Discover"))
//                                    and ( PaymentProcessorSelected == "PayPal Direct Payment"))
//
//                    /*		
//                    '------------------------------------
//                    ' The paymentAmount is the total value of 
//                    ' the shopping cart, that was set 
//                    ' earlier in a session variable 
//                    ' by the shopping cart page
//                    '------------------------------------
//                    */
//                    String paymentAmount = (String)request.getSession().getAttribute("Payment_Amount");
//
//                    /*
//                    '------------------------------------
//                    ' The paymentType that was selected earlier 
//                    '------------------------------------
//                    */
//                    String paymentType = "Sale";
//
//                    /*
//                    ' Set these values based on what was selected by the user on the Billing page Html form
//                    */
//
//                    String creditCardType 		= "<<Visa/MasterCard/Amex/Discover>>"; //' Set this to one of the acceptable values (Visa/MasterCard/Amex/Discover) match it to what was selected on your Billing page
//                    String creditCardNumber 	= "<<CC number>>"; // ' Set this to the string entered as the credit card number on the Billing page
//                    String expDate 				= "<<Expiry Date>>"; // ' Set this to the credit card expiry date entered on the Billing page
//                    String cvv2 				= "<<cvv2>>"; // ' Set this to the CVV2 string entered on the Billing page 
//                    String firstName 			= "<<firstName>>"; // ' Set this to the customer's first name that was entered on the Billing page 
//                    String lastName 			= "<<lastName>>"; // ' Set this to the customer's last name that was entered on the Billing page 
//                    String street 				= "<<street>>"; // ' Set this to the customer's street address that was entered on the Billing page 
//                    String city 				= "<<city>>"; // ' Set this to the customer's city that was entered on the Billing page 
//                    String state 				= "<<state>>"; // ' Set this to the customer's state that was entered on the Billing page 
//                    String zip 					= "<<zip>>"; // ' Set this to the zip code of the customer's address that was entered on the Billing page 
//                    String countryCode 			= "<<PayPal Country Code>>"; // ' Set this to the PayPal code for the Country of the customer's address that was entered on the Billing page 
//                    String currencyCode 		= "<<PayPal Currency Code>>"; // ' Set this to the PayPal code for the Currency used by the customer 
//
//                    /*	
//                    '------------------------------------------------
//                    ' Calls the DoDirectPayment API call
//                    '
//                    ' The DirectPayment function is defined in PayPalFunctions.jsp 
//                    ' included at the top of this file.
//                    '-------------------------------------------------
//                    */
//                    nvp = DirectPayment ( paymentType, paymentAmount, creditCardType, creditCardNumber,
//                                                                    expDate, cvv2, firstName, lastName, street, city, state, zip, 
//                                                                    countryCode, currencyCode ); 
//
//                    String strAck = nvp.get("ACK").toString();
//                    if(strAck ==null || strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning") )
//                    {
//                            // Display a user friendly Error on the page using any of the following error information returned by PayPal
//                            String ErrorCode = nvp.get("L_ERRORCODE0").toString();
//                            String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
//                            String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
//                            String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
//                    }
//            }
            /*
            ==================================================================
            PayPal Express Checkout - MARK FLOW : END SNIPPET
            ===================================================================
            */
        }     
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on capturar_pedido button click
     */
    public ActionForward capturar_pedido(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        if(request.getSession().getAttribute(USUARIO_SESION)==null){
            return mapping.findForward(FAIL);
        }else{   
            /*
            ==================================================================
            PayPal Express Checkout - ORDER REVIEW : START SNIPPET
            ===================================================================
            */
            /* 
                    This step indicates whether the user was sent here by PayPal 
                    if this value is null then it is part of the regular checkout flow in the cart
            */
            String token = (String) request.getSession().getAttribute("token");
            if (token != null) {
            //IMPORTANT NOTE: Please import Class paypalfunctions if not in the same package level.
            // import paypalfunctions;
                    /*
                    '------------------------------------
                    ' Calls the GetExpressCheckoutDetails API call
                    '
                    ' The GetShippingDetails function is defined in PayPalFunctions.jsp
                    ' included at the top of this file.
                    '-------------------------------------------------
                    */
                    token = (String) request.getSession().getAttribute("token");

                    paypalfunctions ppf = new paypalfunctions();
                    java.util.HashMap nvp = ppf.GetShippingDetails(token);

                    if (nvp == null){     
                        ppf.RedirectURL(response, nvp.get("TOKEN").toString());    
                    }
                    
                    String strAck = nvp.get("ACK").toString();
                    if(strAck != null && (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning")))
                    {
                        String email = nvp.get("EMAIL").toString(); // ' Email address of payer.
                        String payerId = nvp.get("PAYERID").toString(); // ' Unique PayPal customer account identification number.

                        request.getSession().setAttribute("payerId", payerId);

//                        String payerStatus = nvp.get("PAYERSTATUS").toString(); // ' Status of payer. Character length and limitations: 10 single-byte alphabetic characters.
//                        String salutation = nvp.get("SALUTATION").toString(); // ' Payer's salutation.
//                        String firstName = nvp.get("FIRSTNAME").toString(); // ' Payer's first name.
//                        String middleName = nvp.get("MIDDLENAME").toString(); // ' Payer's middle name.
//                        String lastName	= nvp.get("LASTNAME").toString(); // ' Payer's last name.
//                        String suffix = nvp.get("SUFFIX").toString(); // ' Payer's suffix.
//                        String cntryCode = nvp.get("COUNTRYCODE").toString(); // ' Payer's country of residence in the form of ISO standard 3166 two-character country codes.
//                        String business	= nvp.get("BUSINESS").toString(); // ' Payer's business name.
//                        String shipToName = nvp.get("PAYMENTREQUEST_0_SHIPTONAME").toString(); // ' Person's name associated with this address.
//                        String shipToStreet = nvp.get("PAYMENTREQUEST_0_SHIPTOSTREET").toString(); // ' First street address.
//                        String shipToStreet2 = nvp.get("PAYMENTREQUEST_0_SHIPTOSTREET2").toString(); // ' Second street address.
//                        String shipToCity = nvp.get("PAYMENTREQUEST_0_SHIPTOCITY").toString(); // ' Name of city.
//                        String shipToState = nvp.get("PAYMENTREQUEST_0_SHIPTOSTATE").toString(); // ' State or province
//                        String shipToCntryCode = nvp.get("PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE").toString(); // ' Country code. 
//                        String shipToZip = nvp.get("PAYMENTREQUEST_0_SHIPTOZIP").toString(); // ' U.S. Zip code or other country-specific postal code.
//                        String addressStatus = nvp.get("ADDRESSSTATUS").toString(); // ' Status of street address on file with PayPal   
//                        String invoiceNumber = nvp.get("INVNUM").toString(); // ' Your own invoice or tracking number, as set by you in the element of the same name in SetExpressCheckout request .
//                        String phonNumber = nvp.get("PHONENUM").toString(); // ' Payer's contact telephone number. Note:  PayPal returns a contact telephone number only if your Merchant account profile settings require that the buyer enter one. 

                        /*
                        ' The information that is returned by the GetExpressCheckoutDetails call should be integrated by the partner into his Order Review 
                        ' page		
                        */
                    }
                    else
                    {  
                        // Display a user friendly Error on the page using any of the following error information returned by PayPal

                        String ErrorCode = nvp.get("L_ERRORCODE0").toString();
                        String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
                        String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
                        String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
                        
                        throw new Exception(ErrorCode + " " + ErrorShortMsg + " " + ErrorSeverityCode + " " + ErrorLongMsg);
                    }
            }
            /*
            ==================================================================
            PayPal Express Checkout - ORDER REVIEW : END SNIPPET
            ===================================================================
            */
        }     
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on resumen_pedido button click
     */
    public ActionForward resumen_pedido(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        
        if(request.getSession().getAttribute(USUARIO_SESION)==null){
            return mapping.findForward(FAIL);
        }    
        
        return mapping.findForward(SUCCESS);
    }

    /** Action called on confirmar_inicio button click
     */
    public ActionForward confirmar_pedido(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        if(request.getSession().getAttribute(USUARIO_SESION)==null){
            return mapping.findForward(FAIL);
        }else{        
            PedidoDAO pedDAO=(PedidoDAO) request.getSession().getServletContext().getAttribute("pedDAO");
            PedidoLineaDAO linDAO=(PedidoLineaDAO) request.getSession().getServletContext().getAttribute("linDAO");
            Usuario user=(Usuario)request.getSession().getAttribute(USUARIO_SESION);
            Pedido ped=(Pedido) request.getSession().getAttribute("pedido"); 
            ArrayList<PedidoLinea> carrito=(ArrayList<PedidoLinea>) request.getSession().getAttribute("carrito");
            ConfiguracionTienda cfg=(ConfiguracionTienda) request.getSession().getServletContext().getAttribute("cfg");
            ProductoDAO prodDAO = (ProductoDAO) request.getSession().getServletContext().getAttribute("prodDAO");   
            String respuestaPayPal="";

            if(ped.getFormaPago().equalsIgnoreCase("pp")){
                /*
                ==================================================================
                PayPal Express Checkout - ORDER CONFIRM : START SNIPPET
                ===================================================================
                */
                String token = (String) request.getSession().getAttribute("token");
                if (token != null) {

                //IMPORTANT NOTE: Please import Class paypalfunctions if not in the same package level.
                // import paypalfunctions;

                /*
                    '------------------------------------
                    ' Get the token parameter value stored in the session 
                    ' from the previous SetExpressCheckout call
                    '------------------------------------
                    */
                    //token =  (String) request.getSession().getAttribute("TOKEN");

                    /*
                    '------------------------------------
                    ' The paymentAmount is the total value of 
                    ' the shopping cart, that was set 
                    ' earlier in a session variable 
                    ' by the shopping cart page
                    '------------------------------------
                    */

                    String serverName =  request.getServerName();
                    String payerId =  (String) request.getSession().getAttribute("payerId");
                    token =  (String) request.getSession().getAttribute("token");
                    String finalPaymentAmount =  (String) request.getSession().getAttribute("Payment_Amount");
                    /*
                    '------------------------------------
                    ' Calls the DoExpressCheckoutPayment API call
                    '
                    ' The ConfirmPayment function is defined in the file PayPalFunctions.jsp,
                    ' that is included at the top of this file.
                    '-------------------------------------------------
                    */

                    paypalfunctions ppf = new paypalfunctions();
                    java.util.HashMap nvp = ppf.ConfirmPayment(token, payerId, finalPaymentAmount, serverName);
                    String strAck = nvp.get("ACK").toString();
                    if(strAck !=null && (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning"))) {
                        /*
                        '********************************************************************************************************************
                        '
                        ' THE PARTNER SHOULD SAVE THE KEY TRANSACTION RELATED INFORMATION LIKE 
                        '                    transactionId & orderTime 
                        '  IN THEIR OWN  DATABASE
                        ' AND THE REST OF THE INFORMATION CAN BE USED TO UNDERSTAND THE STATUS OF THE PAYMENT 
                        '
                        '********************************************************************************************************************
                        */

                        String transactionId = nvp.get("PAYMENTINFO_0_TRANSACTIONID").toString(); // ' Unique transaction ID of the payment. Note:  If the PaymentAction of the request was Authorization or Order, this value is your AuthorizationID for use with the Authorization & Capture APIs. 
                        String transactionType = nvp.get("PAYMENTINFO_0_TRANSACTIONTYPE").toString(); //' The type of transaction Possible values: l  cart l  express-checkout 
                        String paymentType = nvp.get("PAYMENTINFO_0_PAYMENTTYPE").toString();  //' Indicates whether the payment is instant or delayed. Possible values: l  none l  echeck l  instant 
                        String orderTime = nvp.get("PAYMENTINFO_0_ORDERTIME").toString();  //' Time/date stamp of payment
                        String amt = nvp.get("PAYMENTINFO_0_AMT").toString();  //' The final amount charged, including any shipping and taxes from your Merchant Profile.
                        String currencyCode = nvp.get("PAYMENTINFO_0_CURRENCYCODE").toString();  //' A three-character currency code for one of the currencies listed in PayPay-Supported Transactional Currencies. Default: USD. 
                        String feeAmt = nvp.get("PAYMENTINFO_0_FEEAMT").toString();  //' PayPal fee amount charged for the transaction
                        //String settleAmt = nvp.get("PAYMENTINFO_0_SETTLEAMT").toString();  //' Amount deposited in your PayPal account after a currency conversion.
                        String taxAmt = nvp.get("PAYMENTINFO_0_TAXAMT").toString();  //' Tax charged on the transaction.
                        //String exchangeRate = nvp.get("PAYMENTINFO_0_EXCHANGERATE").toString();  //' Exchange rate if a currency conversion occurred. Relevant only if your are billing in their non-primary currency. If the customer chooses to pay with a currency other than the non-primary currency, the conversion occurs in the customer’s account.
                        /*
                        ' Status of the payment: 
                                        'Completed: The payment has been completed, and the funds have been added successfully to your account balance.
                                        'Pending: The payment is pending. See the PendingReason element for more information. 
                        */
                        String paymentStatus = nvp.get("PAYMENTINFO_0_PAYMENTSTATUS").toString(); 
                        /*
                        'The reason the payment is pending:
                        '  none: No pending reason 
                        '  address: The payment is pending because your customer did not include a confirmed shipping address and your Payment Receiving Preferences is set such that you want to manually accept or deny each of these payments. To change your preference, go to the Preferences section of your Profile. 
                        '  echeck: The payment is pending because it was made by an eCheck that has not yet cleared. 
                        '  intl: The payment is pending because you hold a non-U.S. account and do not have a withdrawal mechanism. You must manually accept or deny this payment from your Account Overview. 		
                        '  multi-currency: You do not have a balance in the currency sent, and you do not have your Payment Receiving Preferences set to automatically convert and accept this payment. You must manually accept or deny this payment. 
                        '  verify: The payment is pending because you are not yet verified. You must verify your account before you can accept this payment. 
                        '  other: The payment is pending for a reason other than those listed above. For more information, contact PayPal customer service. 
                        */
                        String pendingReason = nvp.get("PAYMENTINFO_0_PENDINGREASON").toString();  
                        /*
                        'The reason for a reversal if TransactionType is reversal:
                        '  none: No reason code 
                        '  chargeback: A reversal has occurred on this transaction due to a chargeback by your customer. 
                        '  guarantee: A reversal has occurred on this transaction due to your customer triggering a money-back guarantee. 
                        '  buyer-complaint: A reversal has occurred on this transaction due to a complaint about the transaction from your customer. 
                        '  refund: A reversal has occurred on this transaction because you have given the customer a refund. 
                        '  other: A reversal has occurred on this transaction due to a reason not listed above. 
                        */
                        String reasonCode = nvp.get("PAYMENTINFO_0_REASONCODE").toString();   

                        respuestaPayPal=nvp.toString();
                    } else {  
                        // Display a user friendly Error on the page using any of the following error information returned by PayPal

                        String ErrorCode = nvp.get("L_ERRORCODE0").toString();
                        String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
                        String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
                        String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();

                        throw new Exception(ErrorCode + " " + ErrorShortMsg + " " + ErrorSeverityCode + " " + ErrorLongMsg);
                    }
                }		

                /*
                ==================================================================
                PayPal Express Checkout - ORDER CONFIRM : START SNIPPET
                ===================================================================
                */
                ped.setRespuestaPayPal(respuestaPayPal);
                ped.setFechaPago(new Date());
            }
            
            ped.setFecha(new Date());
            pedDAO.altaPedido(ped);
            
            for(PedidoLinea lin: carrito){
                if(cfg.getStockMin()>0){   
                    Producto p=lin.getProducto();
                    int stock=p.getStock()-lin.getCantidad();
                    p.setStock(stock);
                    prodDAO.editaProducto(p);
                }
                lin.setPedido(ped);
                linDAO.altaLinea(lin);
            }            
            
            user.getPedidos().add(ped);
            request.getSession().setAttribute(USUARIO_SESION, user);
            
            request.getSession().removeAttribute("carrito");
            request.getSession().removeAttribute("pedido");
            request.getSession().removeAttribute("payerId");
            request.getSession().removeAttribute("token");
            request.getSession().removeAttribute("Payment_Amount");
            
            MessageResources mgs = MessageResources.getMessageResources("com.myapp.struts.ApplicationResource"); 
            Locale locale=request.getLocale();
            Pedido p=pedDAO.consultaPedido(ped.getId());
            
            Correo mail=new Correo();            
            TextosTienda txt = (TextosTienda) request.getSession().getAttribute("txt");
            
            mail.setFrom(cfg.getEmailSistema());
            mail.setTo(user.getEmail());
            mail.setSubject(mgs.getMessage(locale, "email.pedido"));
            mail.setText(txt.getEmailPedido().replace("[PEDIDO]",p.toString(locale)));

            mail.send();
            
            mail=new Correo();          
            
            mail.setFrom(cfg.getEmailSistema());
            mail.setTo(cfg.getEmailSistema());
            mail.setSubject(mgs.getMessage(locale, "email.admin_ped_subject"));
            mail.setText(mgs.getMessage(locale, "email.admin_ped_body", user.toString(), p.toString(locale)));

            mail.send();
        }
        
        return mapping.findForward(SUCCESS);
    }
}
