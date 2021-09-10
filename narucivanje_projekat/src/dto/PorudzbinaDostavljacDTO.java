package dto;

public class PorudzbinaDostavljacDTO {
	private String idDostavljaca;
	private String idPorudzbine;
	public PorudzbinaDostavljacDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PorudzbinaDostavljacDTO(String idDostavljaca, String idPorudzbine) {
		super();
		this.idDostavljaca = idDostavljaca;
		this.idPorudzbine = idPorudzbine;
	}
	public String getIdDostavljaca() {
		return idDostavljaca;
	}
	public void setIdDostavljaca(String idDostavljaca) {
		this.idDostavljaca = idDostavljaca;
	}
	public String getIdPorudzbine() {
		return idPorudzbine;
	}
	public void setIdPorudzbine(String idPorudzbine) {
		this.idPorudzbine = idPorudzbine;
	}
	
}
