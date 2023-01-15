package it.aci.informatica.nstar.anagrafi.flusso.convenzioni.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.apache.camel.Converter;

import it.aci.informatica.nstar.anagrafi.flusso.convenzioni.dto.ConvenzioneLegacyDTO;
import it.aci.informatica.nstar.anagrafi.flusso.convenzioni.model.ConvenzioneLegacy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter(generateLoader = true)
public class ConvenzioneLegacyConverter {

	@Converter
	public static ConvenzioneLegacyDTO convert(ConvenzioneLegacy input) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
		
		log.debug("convert - Mapping {} to ConvenzionzioneLegacyDTO", input);

		var result = new ConvenzioneLegacyDTO();

		Optional.ofNullable(input.getPartitaIva()).ifPresentOrElse(p -> result.setCodiceFiscale(p.trim()),
				() -> Optional.ofNullable(input.getCodiceFiscale()).ifPresent(c -> result.setCodiceFiscale(c.trim())));
		Optional.ofNullable(input.getCodiceTipoAgevolazione())
				.ifPresent(c -> result.setCodiceTipoConvenzione(c.trim()));
		Optional.ofNullable(input.getCodiceTipoVeicolo()).ifPresent(c -> result.setCodiceTipoVeicolo(c.trim()));
		Optional.ofNullable(input.getTarga()).ifPresent(t -> result.setTarga(t.trim()));

		Optional.ofNullable(input.getDataDecorrenza())
				.ifPresent(d -> result.setDataDecorrenza(LocalDate.parse(d.trim(), formatter).toString()));
		Optional.ofNullable(input.getDataScadenza())
				.ifPresent(d -> result.setDataScadenza(LocalDate.parse(d.trim(), formatter).toString()));
		Optional.ofNullable(input.getDataInizioValidita())
				.ifPresent(d -> result.setDataInizioValidita(LocalDate.parse(d.trim(), formatter).toString()));
		Optional.ofNullable(input.getDataFineValidita())
				.ifPresent(d -> result.setDataFineValidita(LocalDate.parse(d.trim(), formatter).toString()));

		log.debug("convert - result: {}", result);
		
		return result;
	}

}
