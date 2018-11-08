package Utils.Helpers;

import org.json.JSONObject;

import Utils.Responses.IResponse;
import Utils.Responses.Stamp.SuccessV4Response;

public class BuildResponseV4 extends ResponseStamp {
	public IResponse getResponse() { 
		if(!response.trim().isEmpty()) {
			JSONObject body = new JSONObject(response);
			if(status == 200) {
				JSONObject data = body.getJSONObject("data");
				return new SuccessV4Response(status, body.getString("status"),
						data.getString("cfdi"), data.getString("cadenaOriginalSAT"),
						data.getString("noCertificadoSAT"), data.getString("noCertificadoCFDI"),
						data.getString("uuid"), data.getString("selloSAT"),
						data.getString("selloCFDI"), data.getString("fechaTimbrado"),
						data.getString("qrCode"), reason.getReasonPhrase(), reason.getReasonPhrase());
			}
			else {
				return new SuccessV4Response(status, body.getString("status"), "", "", "", "", "", "", "", "", "", body.getString("message"), body.getString("messageDetail"));
			}
		}
		else {
			return new SuccessV4Response(status, "error", "", "", "", "", "", "", "", "", "", "Error con c�digo "+status+": "+reason.getReasonPhrase(), reason.getProtocolVersion().getProtocol());
		}
	}
}
