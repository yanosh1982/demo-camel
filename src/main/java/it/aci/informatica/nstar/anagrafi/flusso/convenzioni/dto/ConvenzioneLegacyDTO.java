package it.aci.informatica.nstar.anagrafi.flusso.convenzioni.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConvenzioneLegacyDTO {
	
	private String codiceFiscale;
	
	private String targa;
	
	private String codiceTipoVeicolo;
	
	private String codiceTipoConvenzione;
	
    private String dataInizioValidita;

    private String dataFineValidita;
	
    private String dataDecorrenza;

    private String dataScadenza;
    
}
