package it.aci.informatica.nstar.anagrafi.flusso.convenzioni.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.aci.informatica.nstar.anagrafi.flusso.convenzioni.dto.ConvenzioneLegacyDTO;
import it.aci.informatica.nstar.anagrafi.flusso.convenzioni.util.DefaultFlussoConvenzioneFailureHandler;
import it.aci.informatica.nstar.anagrafi.flusso.convenzioni.util.HttpOperationFailedFailureHandler;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ImportConvenzioneLegacyRoute extends RouteBuilder {

	private final String ROUTE_ID = "Import _Convenzioni_Legacy";
	
	private final String START_ROUTING_MESSAGE = "Consuming ${body}";
	
	private final String SUCCESS_MESSAGE = "Item consumed successfully";
	
	private final String FAILURE_MESSAGE = "Item consumed with errors";
	
	private final String SKIP_MESSAGE = "Unprocessable message type ${body.codiceTipoConvenzione}, skipping";
	
	private final String TYPE_CHECK_EXPRESSION = "${body.codiceTipoConvenzione} == 'LEASING'";
	

	@Value("${camel.jpa.consumer.endpoint}")
	private String jpaConsumerEndpoint;

	@Value("${camel.rest.producer.endpoint}")
	private String restProducerEndpoint;

	@Value("${camel.rest.producer.host}")
	private String restProducerHost;

	@Value("${camel.rest.producer.component}")
	private String restProducerHttp;

	@Autowired
	private HttpOperationFailedFailureHandler httpOperationFailedFailureHandler;

	@Autowired
	private DefaultFlussoConvenzioneFailureHandler defaultFlussoConvenzioneFailureHandler;

	@Override
	public void configure() {
		restConfiguration().host(restProducerHost).producerComponent(restProducerHttp)
				.bindingMode(RestBindingMode.json);

		onException(HttpOperationFailedException.class).process(httpOperationFailedFailureHandler).handled(true)
				.useOriginalMessage().log(LoggingLevel.INFO, FAILURE_MESSAGE);
		onException(Throwable.class).process(defaultFlussoConvenzioneFailureHandler).handled(true).useOriginalMessage()
				.log(LoggingLevel.INFO, FAILURE_MESSAGE);

		from(jpaConsumerEndpoint).routeId(ROUTE_ID).log(LoggingLevel.INFO, START_ROUTING_MESSAGE)
				.convertBodyTo(ConvenzioneLegacyDTO.class)
					.choice()
						.when()
							.simple(TYPE_CHECK_EXPRESSION).to(restProducerEndpoint)
							.log(LoggingLevel.INFO, SUCCESS_MESSAGE)
						.otherwise().log(SKIP_MESSAGE)
					.end();
		 
	}

}