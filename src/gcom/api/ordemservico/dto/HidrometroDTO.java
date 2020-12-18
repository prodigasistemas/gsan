package gcom.api.ordemservico.dto;

public class HidrometroDTO {

	private String numero;

	private Integer tipoMedicao;

	private String dataRetirada;

	private String leituraRetirada;

	private Integer situacao;

	private Integer localArmazenagem;

	public HidrometroDTO(String numero, Integer tipoMedicao) {
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

	public Integer getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(Integer tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public String getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(String dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public Integer getLeituraRetirada() {
		if (leituraRetirada != null && !leituraRetirada.trim().equals("")) {
			return Integer.parseInt(leituraRetirada);
		}
		return null;
	}

	public void setLeituraRetirada(String leituraRetirada) {
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