package tp.web.service;

import java.util.List;

import javax.ejb.Local;

import tp.web.dto.Devise;
import tp.web.exception.MyConflictException;
import tp.web.exception.MyNotFoundException;

@Local
public interface ServiceDevise {

	Devise insertDevise(Devise devise) throws MyConflictException;

	Devise updateDevise(Devise devise) throws MyNotFoundException;

	void removeDevise(Long id)throws MyNotFoundException;

	Devise getDeviseById(Long id)throws MyNotFoundException;

	Devise getDeviseByCode(String code)throws MyNotFoundException;

	List<Devise> getAllDevises();

	List<Devise> geDevisesWithChangeMini(double changeMini);

}