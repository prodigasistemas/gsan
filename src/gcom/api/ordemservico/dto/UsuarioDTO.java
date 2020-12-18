package gcom.api.ordemservico.dto;

public class UsuarioDTO {

	private Integer id;

	private String nome;

	private Integer unidadeId;

	private String unidadeDescricao;

	public UsuarioDTO(Integer id, String nome, Integer unidadeId, String unidadeDescricao) {
		super();
		
		this.id = id;
		this.nome = nome;
		this.unidadeId = unidadeId;
		this.unidadeDescricao = unidadeDescricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getUnidadeId() {
		return unidadeId;
	}

	public void setUnidadeId(Integer unidadeId) {
		this.unidadeId = unidadeId;
	}

	public String getUnidadeDescricao() {
		return unidadeDescricao;
	}

	public void setUnidadeDescricao(String unidadeDescricao) {
		this.unidadeDescricao = unidadeDescricao;
	}
}
