package gcom.api.ordemservico.dto;

public class HidrometroDTO {

	private String numero;
	private String tipoMedicao;
	private String dataRetirada;
	private Integer leituraRetirada;
	private Integer situacao;
	private Integer localArmazenagem;

	public HidrometroDTO(String numero, String tipoMedicao) {
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

	public String getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(String dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public Integer getLeituraRetirada() {
		return leituraRetirada;
	}

	public void setLeituraRetirada(Integer leituraRetirada) {
		this.leituraRetirada = leituraRetirada;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public Integer getLocalArmazenagem() {
		return localArmazenagem;
	}

	public void setLocalArmazenagem(Integer localArmazenagem) {
		this.localArmazenagem = localArmazenagem;
	}
}