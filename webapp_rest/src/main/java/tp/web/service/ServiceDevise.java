package tp.web.service;

import java.util.List;

import javax.ejb.Local;

import tp.web.dto.Devise;

@Local
public interface ServiceDevise {

	Devise insertDevise(Devise devise);

	Devise updateDevise(Devise devise);

	void removeDevise(Long id);

	Devise getDeviseById(Long id);

	Devise getDeviseByCode(String code);

	List<Devise> getAllDevises();

	List<Devise> geDevisesWithChangeMini(double changeMini);

}