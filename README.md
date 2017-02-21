

[![Smarter Web](http://sw.com.mx/images/logo.png)](http://sw.com.mx/)

# SDK JAVA

Registrate en sw.com.mx/

## Requirementos

Java 1.6 o superior

##Dependencias
* [Unirest](http://unirest.io/java.html)

##Instalación
Descargas el modulo mediante Maven:
	
```html
<dependencies>

        <dependency>
            <groupId>mx.com.sw.services</groupId>
            <artifactId>SW-JAVA</artifactId>
            <version>1.0.0</version>
        </dependency>
</dependencies>
```
##Instalación manual
```
git clone https://github.com/lunasoft/sw-sdk-java.git
cd sw-sdk-java/
mvn clean package


```

##Uso rapido...

```java
            try{
                    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
                    //Se especifica el base path, esto para consumir el api de pruebas o productivo
                    SWStampService sdk = new SWStampService("demo","123456789","http://services.test.sw.com.mx");
                    //Se inicializa un objeto response, que obtendra la respuesta del api
                    IResponse response = null;
                    //Se asigna el resultado de la respuesta a dicho objeto
                    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
                    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
                    response = sdk.Stamp("String o File XML","v1");
                    //El objeto response tendra así los atributos:
                    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
                    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
                    // Data: Cuerpo de la respuesta que arroja el servidor
                    //En este caso arrojara el complemento timbre: {"tfd":"<Complemento>"}
                    System.out.println(response.Status);
                    System.out.println(response.HttpStatusCode);
                    System.out.println(response.Data);
                    }
                    catch(Exception e){
                        //En caso de obtener estatus "fail", "error"
                        //Se generara una excepción
                        System.out.println(e.getMessage());
                    }
            
```

##Timbrar CFDI V1
TimbrarV1 Recibe el contenido de un XML ya emitido (sellado) en formato String ó tambien puede ser en Base64, posteriormente si la factura y el token son correctos devuelve el complemento timbre en un string (TFD), en caso contrario lanza una excepción.

##Timbrar XML en formato string utilizando usuario y contraseña
```java
            try{
                    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
                    //Se especifica el base path, esto para consumir el api de pruebas o productivo
                    SWStampService sdk = new SWStampService("demo","123456789","http://services.test.sw.com.mx");
                    //Se inicializa un objeto response, que obtendra la respuesta del api
                    IResponse response = null;
                    //Se asigna el resultado de la respuesta a dicho objeto
                    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
                    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
                    response = sdk.Stamp(stringXML,"v1");
                    //El objeto response tendra así los atributos:
                    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
                    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
                    // Data: Cuerpo de la respuesta que arroja el servidor
                    //En este caso arrojara el complemento timbre: {"tfd":"<Complemento>"}
                    System.out.println(response.Status);
                    System.out.println(response.HttpStatusCode);
                    System.out.println(response.Data);
                    }
                    catch(Exception e){
                        //En caso de obtener estatus "fail", "error"
                        //Se generara una excepción
                        System.out.println(e.getMessage());
                    }
                 
            
```

##Timbrar XML en formato string utilizando token

```java
            try{
                    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
                    //Se especifica el base path, esto para consumir el api de pruebas o productivo
                    SWStampService sdk = new SWStampService("T2lYQ0t4L0R....","http://services.test.sw.com.mx");
                    //Se inicializa un objeto response, que obtendra la respuesta del api
                    IResponse response = null;
                    //Se asigna el resultado de la respuesta a dicho objeto
                    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
                    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
                    response = sdk.Stamp(stringXML,"v1");
                    //El objeto response tendra así los atributos:
                    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
                    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
                    // Data: Cuerpo de la respuesta que arroja el servidor
                    //En este caso arrojara el complemento timbre: {"tfd":"<Complemento>"}
                    System.out.println(response.Status);
                    System.out.println(response.HttpStatusCode);
                    System.out.println(response.Data);
                    }
                    catch(Exception e){
                        //En caso de obtener estatus "fail", "error"
                        //Se generara una excepción
                        System.out.println(e.getMessage());
                    }
                 
            
```

##Timbrar XML en File type utilizando token

```java
            try{
                    //Es preferible inicializar el objeto con el usuario y password de nuestra cuenta, en caso contrario se puede incluir solamente el token de acceso
                    //Se especifica el base path, esto para consumir el api de pruebas o productivo
                    SWStampService sdk = new SWStampService("T2lYQ0t4L0R....","http://services.test.sw.com.mx");
                    //Se inicializa un objeto response, que obtendra la respuesta del api
                    IResponse response = null;
                    //Se asigna el resultado de la respuesta a dicho objeto
                    //Se ejecuta el metodo "Stamp", que timbrara nuestro comprobante posteriormente sellado, asi como la versión del servicio de timbrado,
                    //puede ver mas de estas versiones en el apartado "Versiones de timbrado"
                    File fileXML = new File('xfdi.xml');
                    response = sdk.Stamp(fileXML,"v1");
                    //El objeto response tendra así los atributos:
                    // Status: estado de la petición procesada, puede ser : "success", "fail", "error"
                    // HttpStatusCode: Codigo de respuesta HTTP del servidor: eg. 200, 400, 500
                    // Data: Cuerpo de la respuesta que arroja el servidor
                    //En este caso arrojara el complemento timbre: {"tfd":"<Complemento>"}
                    System.out.println(response.Status);
                    System.out.println(response.HttpStatusCode);
                    System.out.println(response.Data);
                    }
                    catch(Exception e){
                        //En caso de obtener estatus "fail", "error"
                        //Se generara una excepción
                        System.out.println(e.getMessage());
                    }
                 
            
```