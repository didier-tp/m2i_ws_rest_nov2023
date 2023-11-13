package tp.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import tp.web.dto.Devise;

//Service interne (business/metier) qui pourrait utiliser jpa/hibernate et une base de données
//pour simuler cela sur un cours de 2j : HashMap
public class ServiceDevise {
	
	
	private Map<Long,Devise> mapDevises= new HashMap<>();
	
	
	public ServiceDevise() {
		mapDevises.put(1L, new Devise(1L,"EUR","Euro",1.0));
		mapDevises.put(2L, new Devise(2L,"USD","Dollar",1.05));
		mapDevises.put(3L, new Devise(3L,"GBP","Livre",0.9));
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
				.filter(d->d.getChange()<changeMini)
				.collect(Collectors.toList());
	}

	

}
