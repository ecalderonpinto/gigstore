**GigStore** es una aplicación de comercio on-line desarrollada con Java EE, Struts 1.3.8, JPA Hibernate, JSTL 1.1, MySQL.

**GigStore** is an e-commerce software developed with Java EE, Struts 1.3.8, JPA Hibernate, JSTL 1.1, MySQL.

La aplicación GigStore está desarrollada con Java, Struts, Hibernate JPA y utiliza bases de datos MySQL. Esto hace que la aplicación sea multiplataforma, para ser instalada en un servidor sólo se requiere Tomcat 6 como contenedor de servlets, e internacionalizada. La aplicación se divide en dos partes básicas el Front Office y el Back Office.




FRONT OFFICE

El Front Office es la parte pública de la aplicación, y la que los usuarios clientes de la tienda pueden ver y navegar. Aquí encontraremos la página de inicio que mostrará los productos destacados; las páginas de secciones fijas con información sobre la tienda y su uso; los listados de productos y categorías; las fichas técnicas de los productos con su descripción detallada, opción de añadir a la lista de seguimiento para usuarios registrados y la funcionalidad de compra; y la sección de datos personales del usuario cliente que le permitirá consultar o modificar sus datos personales, direcciones de envío, consultar los pedidos realizados para conocer el estado de los mismos y gestionar la lista de productos en seguimiento.

En cuanto al proceso de compra, el cliente podrá añadir productos al carro de la compra sin necesidad de estar identificado como usuario, pero será imprescindible acceder con un usuario existente o dar de alta uno nuevo para poder finalizar el proceso de compra. Para la gestión del pago se han implementado las opciones de pago contra reembolso, pago por transferencia bancaria, efectivo (mediante recogida en tienda) y pago mediante PayPal para usuarios de PayPal o tarjetas de crédito.

Todas las urls del Front Office usan reescritura, adaptable a las necesidades de la tienda, para ayudar en las tareas de posicionamiento de la tienda y sus productos.


BACK OFFICE

La administración y configuración de la tienda se lleva a cabo aquí bajo las siguientes secciones:

Gestión de Productos: para crear, modificar o eliminar productos, sus imágenes, categorías, estados y opciones (como tallas, colores, etc.). Además de los datos del producto podremos añadir descripciones en múltiples idiomas si se va a utilizar la internacionalización. Esto mismo se aplica a las opciones de producto, pudiendo mostrar colores o tallas en el idioma del navegador del usuario si se dispone de una traducción para este.

Gestión de Pedidos: permite consultar y modificar los pedidos realizados por los clientes, así como crear o editar los diferentes estados que se asignan a los pedidos. Además en esta sección encontramos la gestión de los tipos de envío, donde podemos modificar las formas de pago que acepta ese tipo de envío, los costes que añade al pedido y su descripción.

Gestión de Usuarios: para gestionar los datos de los usuarios y los estados que se le aplican.

Estadísticas: nos muestra un pequeño conjunto de datos estadísticos filtrados por mes que nos permiten hacer un seguimiento de las visitas, los pedidos y los productos. Esta sección está pensada como un resumen y no sustituye herramientas avanzadas de análisis.

Boletín de noticias: nos permite gestionar el listado de direcciones de correo suscritas al boletín de noticias de la aplicación, así como generar un boletín y su envío a los miembros de la lista de correo. Del mismo modo que las estadísticas esta es una herramienta que permite una gestión simple y no sustituye a herramientas avanzadas.

Configuración General: para la gestión de los datos de contacto de la tienda/empresa, configuración de parámetros generales (como idioma principal, moneda, activación/desactivación de la gestión de stock, registros mostrados por página, etc.), formas de pago aceptadas, traducciones de las secciones fijas y correos automáticos y tipos de impuestos aplicables a los productos.