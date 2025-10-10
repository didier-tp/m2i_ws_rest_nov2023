package tp.springJersey.dto;

public class CatFact {
	private String fact;
	private Integer length;
	
	public CatFact(String fact, Integer length) {
		super();
		this.fact = fact;
		this.length = length;
	}
	
	public CatFact() {
		super();
	}

	public String getFact() {
		return fact;
	}
	public void setFact(String fact) {
		this.fact = fact;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	
	
}
