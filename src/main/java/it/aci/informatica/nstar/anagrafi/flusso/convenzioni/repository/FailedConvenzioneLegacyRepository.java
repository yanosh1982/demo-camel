package it.aci.informatica.nstar.anagrafi.flusso.convenzioni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.aci.informatica.nstar.anagrafi.flusso.convenzioni.model.FailedConvenzioneLegacy;

public interface FailedConvenzioneLegacyRepository extends JpaRepository<FailedConvenzioneLegacy, Integer> {

}
