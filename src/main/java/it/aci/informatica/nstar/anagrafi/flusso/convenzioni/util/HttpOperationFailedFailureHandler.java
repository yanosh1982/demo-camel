package it.aci.informatica.nstar.anagrafi.flusso.convenzioni.util;

import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.stereotype.Component;

@Component
public class HttpOperationFailedFailureHandler extends AbstractConvenzioneLegacyFailureHandler {

	@Override
	protected int getResponseCode(Exchange exchange) {
		HttpOperationFailedException caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, HttpOperationFailedException.class);
		return caused.getHttpResponseCode();
		
	}
	
	@Override
	protected String getResponseBody(Exchange exchange) {
		HttpOperationFailedException caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, HttpOperationFailedException.class);
		
		String result = Optional.ofNullable(caused.getResponseBody()).map(s -> {
			String responseBody = "";
			if(caused.getResponseBody().indexOf("message") != -1 && caused.getResponseBody().indexOf("path") != -1) {
				responseBody = caused.getResponseBody().substring(caused.getResponseBody().indexOf("message") + 9, caused.getResponseBody().indexOf("path")-2);
			} else {
				responseBody = caused.getResponseBody();
			}
			return responseBody;
		}).orElse(caused.getClass().getName() + ": " + caused.getMessage());
		
		if(result.length() > 200) {
			result = result.substring(0,200);
		}
		
		return result;
	}

}
