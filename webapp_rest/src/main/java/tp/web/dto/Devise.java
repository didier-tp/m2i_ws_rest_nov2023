package tp.web.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement(name = "devise")
public class Devise {
	
	private Long id; //null ou 1 ou 2 (auto_incr)
	private String code; //"EUR", "USD"
	private String nom;  //"Euro" , "Dollar" 
	private Double change; //1.1 (nb unités pour 1 euro)
	
	@JsonIgnore
	private String comment = "comment xyz";

	public Devise() {
		super();
	}


	public Devise(Long id, String code, String nom, Double change) {
		super();
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.change = change;
	}


	@Override
	public String toString() {
		return "Devise [id=" + id + ", code=" + code + ", nom=" + nom + ", change=" + change + "]";
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Double getChange() {
		return change;
	}
	public void setChange(Double change) {
		this.change = change;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}

	
	
}
