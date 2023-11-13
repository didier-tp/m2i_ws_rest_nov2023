package tp.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import tp.web.dto.Devise;

//Service interne (business/metier) qui pourrait utiliser jpa/hibernate et une base de données
//pour simuler cela sur un cours de 2j : HashMap
@Singleton
public class ServiceDevise {
	
	
	private Map<Long,Devise> mapDevises= new HashMap<>();
	private Long numDeviseMax;
	
	
	public ServiceDevise() {
		mapDevises.put(1L, new Devise(1L,"EUR","Euro",1.0));
		mapDevises.put(2L, new Devise(2L,"USD","Dollar",1.05));
		mapDevises.put(3L, new Devise(3L,"GBP","Livre",0.9));
		mapDevises.put(4L, new Devise(4L,"JPY","Yen",123.78));
		numDeviseMax=4L;
	}
	
	public Devise insertDevise(Devise devise) {
		devise.setId(++numDeviseMax);//simuler auto_incr
		mapDevises.put(devise.getId(),devise);//simuler ajout en base
		return devise; //on retourne l'entité sauvegardée avec clef primaire auto_incrémentée
	}
	
	public Devise updateDevise(Devise devise) {
		mapDevises.put(devise.getId(),devise);//simuler update en base
		return devise; //on retourne l'entité sauvegardée 
	}
	
	public void removeDevise(Long id) {
		mapDevises.remove(id);
	}
	
	public Devise getDeviseById(Long id){
		return mapDevises.get(id);
	}
	
	public Devise getDeviseByCode(String code){
		return mapDevises.values()
				.stream()
				.filter(d->code.equalsIgnoreCase(d.getCode()))
				.findFirst().orElse(null);
	}
	
	public List<Devise> getAllDevises(){
		return mapDevises.values()
				.stream()
				.collect(Collectors.toList());
	}
	
	public List<Devise> geDevisesWithChangeMini(double changeMini){
		return mapDevises.values()
				.stream()
				.filter(d->d.getChange()>changeMini)
				.collect(Collectors.toList());
	}

	

}
