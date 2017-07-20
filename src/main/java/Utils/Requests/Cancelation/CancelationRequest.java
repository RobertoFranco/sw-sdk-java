package Utils.Requests.Cancelation;

import Exceptions.AuthException;
import Exceptions.GeneralException;
import Utils.Requests.IRequest;
import Utils.Requests.IRequestor;
import Utils.Responses.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;


public class CancelationRequest implements IRequestor {
    
    public IResponse sendRequest(IRequest request) throws GeneralException, AuthException {
      
        try {
                      
            HttpResponse<String> response = Unirest.post(request.URI)
                    .header("Authorization","bearer "+request.Token)
                    .header("Content-Type","application/json")
                    .body("{\r\n \"uuid\": \""+((CancelationOptionsRequest) request).getUuid()+"\",\r\n \"password\": \""+((CancelationOptionsRequest) request).getPassword_csd()+"\",\r\n \"rfc\": \""+((CancelationOptionsRequest) request).getRfc()+"\",\r\n \"b64Cer\": \""+((CancelationOptionsRequest) request).getB64Cer()+"\",\r\n \"b64Key\": \""+((CancelationOptionsRequest) request).getB64key()+"\"\r\n}").asString();

            
            if(!response.getBody().equalsIgnoreCase("{}") && !response.getBody().equals("") ) {
                JSONObject body = new JSONObject(response.getBody());
                
                if(response.getStatus()==200){
                    
                    JSONObject data = body.getJSONObject("data");
                    String uuid = ((CancelationOptionsRequest) request).getUuid().toUpperCase();
                    JSONObject uuid_data = data.getJSONObject("uuid");
                    String uuidSC = uuid_data.getString(uuid);
                    return new CancelationResponse(response.getStatus(), body.getString("status"),data.getString("acuse"),uuid, Integer.parseInt(uuidSC));
                }
                else{
                    
                    String messageDetail = null;

                    if (!body.isNull("messageDetail")){
                        messageDetail = body.getString("messageDetail");
                    }
                    return new BadResponse(response.getStatus(),body.getString("status"),body.getString("message").toString(),messageDetail);
                }
            }
            else{
                return new BadResponse(response.getStatus(),"error",response.getStatusText(),response.getStatusText());
            }

        } 
        catch (UnirestException ex) {
            throw  new GeneralException(404,"HOST DESCONOCIDO");
        }
        catch (JSONException e){
            throw  new GeneralException(500,e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    public IResponse sendRequest(IRequest request, boolean isXml) throws GeneralException, AuthException {
        
        try{    
            /*
            File tempFile = File.createTempFile("tmp-", ".xml");
            //BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            BufferedWriter bw = new BufferedWriter
                    (new OutputStreamWriter(new FileOutputStream(tempFile),"UTF-8"));
            bw.write(((CancelationOptionsRequest) request).getXml());
            bw.close();

            tempFile.deleteOnExit();*/
            
            String xmlStr = ((CancelationOptionsRequest) request).getXml();
            String boundary = UUID.randomUUID().toString();
            String raw = "--"+boundary+"\r\nContent-Disposition: form-data; name=xml; filename=xml\r\nContent-Type: application/xml\r\n\r\n"+xmlStr+"\r\n--"+boundary+"--";

            
            /*
            Unirest.setTimeouts(60000, 360000);
            HttpResponse<JsonNode> response = Unirest.post(request.URI)
                    .header("Authorization","bearer "+request.Token)
                    .field("xml",tempFile).asJson();*/
            Unirest.setTimeouts(60000, 360000);
            HttpResponse<JsonNode> response = Unirest.post(request.URI)
                    .header("Authorization","bearer "+request.Token)
                    .header("content-type","multipart/form-data; boundary="+boundary)
                    .body( raw).asJson();
            
            if(!response.getBody().toString().equalsIgnoreCase("{}") && !response.getBody().equals("")){
                JSONObject body = new JSONObject(response.getBody().toString());
                if(response.getStatus()==200){
                    JSONObject data = body.getJSONObject("data");
                    String xml = ((CancelationOptionsRequest) request).getXml();
                    String uuid = xml.substring(xml.indexOf("<UUID>") + 6, xml.indexOf("</UUID>")).toUpperCase();
                    JSONObject uuid_data = data.getJSONObject("uuid");
                    String uuidSC = uuid_data.getString(uuid);
                    return new CancelationResponse(response.getStatus(), body.getString("status"),data.getString("acuse"),uuid, Integer.parseInt(uuidSC));
                }
                else{
                    
                    String messageDetail = null;

                    if (!body.isNull("messageDetail")){
                        messageDetail = body.getString("messageDetail");
                    }
                    return new BadResponse(response.getStatus(),body.getString("status"),body.getString("message"),messageDetail);
                }
            }
            else{
                return new BadResponse(response.getStatus(),"error",response.getStatusText(),response.getStatusText());
            }
            
        }
        
        catch (UnirestException e) {

            throw  new GeneralException(404,"HOST DESCONOCIDO");
        }
        catch (JSONException e){
            throw  new GeneralException(500,e.getMessage());
        }
        
    }
}
