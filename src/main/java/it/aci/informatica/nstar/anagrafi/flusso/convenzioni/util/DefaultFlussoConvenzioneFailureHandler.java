package it.aci.informatica.nstar.anagrafi.flusso.convenzioni.util;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class DefaultFlussoConvenzioneFailureHandler extends AbstractConvenzioneLegacyFailureHandler {

	@Override
	protected int getResponseCode(Exchange exchange) {
		return -1;
	}

	@Override
	protected String getResponseBody(Exchange exchange) {
		Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT,
				Throwable.class);

		String message = caused.getClass().getName() + ": " + caused.getMessage();

		if (message.length() > 200) {
			message = message.substring(0, 200);
		}

		return message;
	}

}
