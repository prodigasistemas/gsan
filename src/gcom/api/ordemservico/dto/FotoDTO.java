package gcom.api.ordemservico.dto;

public class FotoDTO {

	private Integer ordemServicoId;
	private Integer imovelId;
	private String nome;
	private String descricao;
	private String data;
	private String arquivo;

	public FotoDTO() {
		super();
	}

	public FotoDTO(
			Integer ordemServicoId,
			Integer imovelId,
			String nome,
			String descricao,
			String data,
			String arquivo) {
		super();

		this.ordemServicoId = ordemServicoId;
		this.imovelId = imovelId;
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
		this.arquivo = arquivo;
	}

	public Integer getImovelId() {
		return imovelId;
	}

	public void setImovelId(Integer imovelId) {
		this.imovelId = imovelId;
	}

	public Integer getOrdemServicoId() {
		return ordemServicoId;
	}

	public void setOrdemServicoId(Integer ordemServicoId) {
		this.ordemServicoId = ordemServicoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
}