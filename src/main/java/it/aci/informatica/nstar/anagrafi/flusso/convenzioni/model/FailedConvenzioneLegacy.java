package it.aci.informatica.nstar.anagrafi.flusso.convenzioni.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "failed_flusso_agevolazioni_lazio", schema = "public")
@Data
@NoArgsConstructor
public class FailedConvenzioneLegacy {
	
	@Id
	private Integer id;
	
	@Column(name = "codice_fiscale")
	private String codiceFiscale;
	
	@Column(name = "partita_iva")
	private String partitaIva;
	
	private String targa;
	
	@Column(name = "codice_tipo_veicolo")
	private String codiceTipoVeicolo;
	
	@Column(name = "codice_tipo_agevolazione")
	private String codiceTipoAgevolazione;
	
	@Column(name = "codice_trattamento")
	private Integer codiceTrattamento;
	
	@Column(name = "dataInizioValidita")
	private String dataInizioValidita;
	
	@Column(name = "dataFineValidita")
	private String dataFineValidita;
	
	@Column(name = "dataDecorrenza")
	private String dataDecorrenza;
	
	@Column(name = "dataScadenza")
	private String dataScadenza;
	
	@Column(name = "response_body")
	private String responseBody;
	
	@Column(name = "response_code")
	private Integer responseCode;
	

	
	

}
