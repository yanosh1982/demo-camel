package it.aci.informatica.nstar.anagrafi.flusso.convenzioni.util;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.aci.informatica.nstar.anagrafi.flusso.convenzioni.model.ConvenzioneLegacy;
import it.aci.informatica.nstar.anagrafi.flusso.convenzioni.model.FailedConvenzioneLegacy;
import it.aci.informatica.nstar.anagrafi.flusso.convenzioni.repository.FailedConvenzioneLegacyRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Getter
public abstract class AbstractConvenzioneLegacyFailureHandler implements Processor {
	
	@Autowired
	private FailedConvenzioneLegacyRepository failureRepository;
	
	@Override
	public final void process(Exchange exchange) throws Exception {

		ConvenzioneLegacy convenzione = exchange.getIn().getBody(ConvenzioneLegacy.class);
		
		log.error("process - Processing failure for {}", convenzione);
		
		FailedConvenzioneLegacy convenzioneFailed = new FailedConvenzioneLegacy();
		convenzioneFailed.setCodiceFiscale(convenzione.getCodiceFiscale());
		convenzioneFailed.setCodiceTipoAgevolazione(convenzione.getCodiceTipoAgevolazione());
		convenzioneFailed.setCodiceTipoVeicolo(convenzione.getCodiceTipoVeicolo());
		convenzioneFailed.setCodiceTrattamento(convenzione.getCodiceTrattamento());
		convenzioneFailed.setDataDecorrenza(convenzione.getDataDecorrenza());
		convenzioneFailed.setDataFineValidita(convenzione.getDataFineValidita());
		convenzioneFailed.setDataInizioValidita(convenzione.getDataInizioValidita());
		convenzioneFailed.setDataScadenza(convenzione.getDataScadenza());
		convenzioneFailed.setId(convenzione.getId());
		convenzioneFailed.setPartitaIva(convenzione.getPartitaIva());
		convenzioneFailed.setTarga(convenzione.getTarga());
		
		convenzioneFailed.setResponseCode(getResponseCode(exchange));
		
		convenzioneFailed.setResponseBody(getResponseBody(exchange));
		
		log.error("process - saving {} in failure logging table", convenzioneFailed);
		
		failureRepository.save(convenzioneFailed);
		
		log.error("process - Item stored in failure logging table");

	}
	
	protected abstract int getResponseCode(Exchange exchange);
	
	protected abstract String getResponseBody(Exchange exchange);

}
