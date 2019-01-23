package Utils.Helpers;

import org.json.JSONObject;

import Utils.Responses.IResponse;
import Utils.Responses.Stamp.SuccessV2Response;

public class BuildResponseV2 extends ResponseStamp {
	public IResponse getResponse() { 
		if(!response.trim().isEmpty()) {
			JSONObject body = new JSONObject(response);
			if(status == 200) {
				JSONObject data = body.getJSONObject("data");
				return new SuccessV2Response(status, body.getString("status"), data.getString("tfd"), data.getString("cfdi"), "OK", "OK");
			}
			else {
				String messageDetail = "";
                if (!body.isNull("messageDetail")){
                    messageDetail = body.getString("messageDetail");
                }
				return new SuccessV2Response(status, body.getString("status"), "", "",body.getString("message"), messageDetail);
			}
		}
		else {
			return new SuccessV2Response(status, "error", "", "","Error con c�digo "+status+": "+reason.getReasonPhrase(), reason.getProtocolVersion().getProtocol());
		}
	}
}
