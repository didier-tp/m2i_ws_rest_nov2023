package tp.dto;

public class Produit {
	
	private Long num;
	private String label;
	private Double prix;
	//....

	
	public Produit(Long num, String label, Double prix) {
		super();
		this.num = num;
		this.label = label;
		this.prix = prix;
	}
	
	public Produit() {
		super();
	}


	@Override
	public String toString() {
		return "Produit [num=" + num + ", label=" + label + ", prix=" + prix + "]";
	}
	
	
	public Long getNum() {
		return num;
	}
	
	public void setNum(Long num) {
		this.num = num;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	

}
