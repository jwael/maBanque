package org.sid.metier;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier{

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	
	@Override
	public Compte ConsulterCompte(String codeCpte) {
		Optional<Compte> cp =  compteRepository.findById(codeCpte);
		if(cp==null) throw new RuntimeException("Compte Introuvable");
		return cp.get();
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp = ConsulterCompte(codeCpte);
		Versement v = new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp = ConsulterCompte(codeCpte);
		double facilitesCaisse=0;
		if(cp instanceof CompteCourant)
			facilitesCaisse=((CompteCourant) cp).getDecouvert();
		if(cp.getSolde()+facilitesCaisse<montant)
			throw new RuntimeException("solde insuffisant");
		Retrait r = new Retrait(new Date(), montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde()-montant);
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		if(codeCpte1.equals(codeCpte2))
			throw new RuntimeException("impossible virement sur le meme compte");
		retirer(codeCpte1, montant);
		verser (codeCpte2,montant );
		
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {

		return operationRepository.listOperation(codeCpte, PageRequest.of(page,size));
		
	}

}