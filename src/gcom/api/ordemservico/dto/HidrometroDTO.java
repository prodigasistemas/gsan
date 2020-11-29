package gcom.api.ordemservico.dto;

public class HidrometroDTO {

	private String numero;
	private String tipoMedicao;

	public HidrometroDTO(
			String numero,
			String tipoMedicao) {
		
		super();
		
		this.numero = numero;
		this.tipoMedicao = tipoMedicao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}
}