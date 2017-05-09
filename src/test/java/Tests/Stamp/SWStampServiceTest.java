package Tests.Stamp;

import Exceptions.GeneralException;
import Services.Authentication.SWAuthenticationService;
import Services.Stamp.SWStampService;
import Tests.Utils;
import Utils.Responses.IResponse;
import Utils.Responses.StampResponse;
import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.*;

import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SWStampServiceTest extends TestCase {



   public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1() throws Exception {
        SWStampService api = new SWStampService("demo","123456789","http://services.test.sw.com.mx");
        IResponse response = null;
        response = api.Stamp(Utils.dummy_xml_string,"v1");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2() throws Exception {
        SWStampService api = new SWStampService("demo","123456789","http://services.test.sw.com.mx");
        IResponse response = null;
        response = api.Stamp(Utils.dummy_xml_string,"v2");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.cfdi);
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3() throws Exception {
        SWStampService api = new SWStampService("demo","123456789","http://swservicestest-rc.azurewebsites.net");
        IResponse response = null;
        response = api.Stamp(Utils.dummy_xml_string,"v3");
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.cfdi);
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4() throws Exception {
        SWStampService api = new SWStampService("demo","123456789","http://swservicestest-rc.azurewebsites.net");
        IResponse response = null;
        response = api.Stamp(Utils.dummy_xml_string,"V4");
        System.out.println(response.Status);
        System.out.println(response.cfdi);
        System.out.println(response.qrCode);
        System.out.println(response.cadenaOriginalSAT);
        System.out.println(response.selloCFDI);
        System.out.println(response.selloSAT);
        System.out.println(response.noCertificadoCFDI);
        System.out.println(response.noCertificadoSAT);
        System.out.println(response.fechaTimbrado);
        System.out.println(response.uuid);

    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V1_b64() throws Exception {
        SWStampService api = new SWStampService("demo","123456789","http://swservicestest-rc.azurewebsites.net");
        IResponse response = null;
        response = api.Stamp(Utils.b64xml,"v1",true);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        Assert.assertTrue(Utils.isValidB64(response.tfd));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V2_b64() throws Exception {
        SWStampService api = new SWStampService("demo","123456789","http://swservicestest-rc.azurewebsites.net");
        IResponse response = null;
        response = api.Stamp(Utils.b64xml,"v2",true);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.tfd);
        System.out.println(response.cfdi);
        boolean cfdi_valid = Utils.isValidB64(response.cfdi), tfd_valid = Utils.isValidB64(response.tfd);
        Assert.assertTrue(cfdi_valid && tfd_valid);

    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V3_b64() throws Exception {
        SWStampService api = new SWStampService("demo","123456789","http://swservicestest-rc.azurewebsites.net");
        IResponse response = null;
        response = api.Stamp(Utils.b64xml,"v3",true);
        System.out.println(response.Status);
        System.out.println(response.HttpStatusCode);
        System.out.println(response.cfdi);
        Assert.assertTrue(Utils.isValidB64(response.cfdi));
    }

    public void testStampREAL_XML_STRING_USER_PASSWORD_AUTH_V4_b64() throws Exception {
        SWStampService api = new SWStampService("demo","123456789","http://swservicestest-rc.azurewebsites.net");
        IResponse response = null;
        response = api.Stamp(Utils.b64xml,"V4",true);
        System.out.println(response.Status);
        System.out.println(response.cfdi);
        System.out.println(response.qrCode);
        System.out.println(response.cadenaOriginalSAT);
        System.out.println(response.selloCFDI);
        System.out.println(response.selloSAT);
        System.out.println(response.noCertificadoCFDI);
        System.out.println(response.noCertificadoSAT);
        System.out.println(response.fechaTimbrado);
        System.out.println(response.uuid);
        boolean valid_cfdi = Utils.isValidB64(response.cfdi),
                valid_qr = Utils.isValidB64(response.qrCode),
                valid_sellocfdi = Utils.isValidB64(response.selloCFDI),
                valid_sellosat = Utils.isValidB64(response.selloSAT);
        Assert.assertTrue(valid_cfdi && valid_qr && valid_sellocfdi && valid_sellosat);
    }


    public void testStampREAL_XML_STRING_EMPTY_PARAMS() throws Exception {

        try{
            SWStampService api = new SWStampService("","","");
            IResponse response = null;
            response = api.Stamp(Utils.dummy_xml_string,"v1");
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.Data);
        }
        catch(Exception e){
            System.out.println("Correcto excepcion lanzada");
            System.out.println(e.getMessage());
            Assert.assertNotNull("some bad happend", e);

        }

    }

    public void testStampREAL_XML_STRING_INCORRECT_PARAMS() throws Exception {

        try{
            SWStampService api = new SWStampService("USER_BAD","PASSWORD_BAD","BAD_URI");
            IResponse response = null;
            response = api.Stamp(Utils.dummy_xml_string,"v1");
            System.out.println(response.Status);
            System.out.println(response.HttpStatusCode);
            System.out.println(response.Data);
        }
        catch(Exception e){
            System.out.println("Correcto excepcion lanzada");
            System.out.println(e.getMessage());
            Assert.assertNotNull("some bad happend", e);

        }

    }

    public void testStampTOKEN_EXPIRES_NOT_USER_NOT_PASSWORD() throws Exception {
        SWStampService api = new SWStampService("demo","123456789","http://services.test.sw.com.mx");
        IResponse response = null;

        int request_number = 50;
        int revoke_token_step = request_number/2;
        int counter;

        for (counter=0;counter<request_number;counter++){
            if (counter==revoke_token_step){
                api.setToken(null);
                api.setUser(null);
                api.setPassword(null);
            }
            try{
                response = api.Stamp(Utils.dummy_xml_string,"v1");


            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Correcto excepcion lanzada");

               Assert.assertNotNull("some bad happend", e);
               break;
            }
        }
    }



}