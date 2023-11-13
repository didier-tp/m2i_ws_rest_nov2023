package tp.web.service.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.Singleton;
import javax.enterprise.inject.Default;

import tp.web.dto.Devise;
import tp.web.service.ServiceDevise;

//Service interne (business/metier) qui pourrait utiliser jpa/hibernate et une base de données
//pour simuler cela sur un cours de 2j : HashMap
@Singleton //javax.ejb.Singleton un peu comme @Stateless (javax.ejb)
@Default
public class ServiceDeviseEjbImpl implements ServiceDevise {
	
	
	private Map<Long,Devise> mapDevises= new HashMap<>();
	private Long numDeviseMax;
	
	
	public ServiceDeviseEjbImpl() {
		System.out.println("ServiceDevise()this="+this);
		mapDevises.put(1L, new Devise(1L,"EUR","Euro",1.0));
		mapDevises.put(2L, new Devise(2L,"USD","Dollar",1.05));
		mapDevises.put(3L, new Devise(3L,"GBP","Livre",0.9));
		mapDevises.put(4L, new Devise(4L,"JPY","Yen",123.78));
		numDeviseMax=4L;
	}
	
	@Override
	public Devise insertDevise(Devise devise) {
		devise.setId(++numDeviseMax);//simuler auto_incr
		mapDevises.put(devise.getId(),devise);//simuler ajout en base
		return devise; //on retourne l'entité sauvegardée avec clef primaire auto_incrémentée
	}
	
	@Override
	public Devise updateDevise(Devise devise) {
		mapDevises.put(devise.getId(),devise);//simuler update en base
		return devise; //on retourne l'entité sauvegardée 
	}
	
	@Override
	public void removeDevise(Long id) {
		mapDevises.remove(id);
	}
	
	@Override
	public Devise getDeviseById(Long id){
		return mapDevises.get(id);
	}
	
	@Override
	public Devise getDeviseByCode(String code){
		return mapDevises.values()
				.stream()
				.filter(d->code.equalsIgnoreCase(d.getCode()))
				.findFirst().orElse(null);
	}
	
	@Override
	public List<Devise> getAllDevises(){
		return mapDevises.values()
				.stream()
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Devise> geDevisesWithChangeMini(double changeMini){
		return mapDevises.values()
				.stream()
				.filter(d->d.getChange()>changeMini)
				.collect(Collectors.toList());
	}

	

}
