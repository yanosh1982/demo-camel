package it.aci.informatica.nstar.anagrafi.flusso.convenzioni.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.camel.component.jpa.Consumed;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flusso_agevolazioni_lazio", schema = "public")
@Data
@NoArgsConstructor
public class ConvenzioneLegacy implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7957711560817042039L;

	@Id
	private Integer id;
	
	@Column(name = "codice_fiscale")
	private String codiceFiscale;
	
	@Column(name = "partita_iva")
	private String partitaIva;
	
	private String targa;
	
	@Column(name = "codice_tipo_veicolo")
	private String codiceTipoVeicolo;
	
	@Column(name = "codice_tipo_agevolazione", insertable=false, updatable=false)
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
	
	@Column(name= "consumed")
	private Boolean consumed = Boolean.FALSE;
	
	@Column(name= "consumed_at")
	private LocalDateTime consumedAt;
	
	@Consumed
	public void onConsumed() {
		consumed = Boolean.TRUE;
		consumedAt = LocalDateTime.now();
	}
	
	

}
