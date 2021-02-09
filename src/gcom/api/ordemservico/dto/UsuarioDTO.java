package gcom.api.ordemservico.dto;

public class UsuarioDTO {

	private Integer id;

	private String nome;

	private Integer unidadeOrganizacionalId;

	private String unidadeOrganizacionalDescricao;

	private Integer funcionarioId;

	public UsuarioDTO(
			Integer id, 
			String nome, 
			Integer unidadeOrganizacionalId, 
			String unidadeOrganizacionalDescricao,
			Integer funcionarioId) {
		super();
		
		this.id = id;
		this.nome = nome;
		this.unidadeOrganizacionalId = unidadeOrganizacionalId;
		this.unidadeOrganizacionalDescricao = unidadeOrganizacionalDescricao;
		this.funcionarioId = funcionarioId;
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

	public Integer getUnidadeOrganizacionalId() {
		return unidadeOrganizacionalId;
	}

	public void setUnidadeOrganizacionalId(Integer unidadeOrganizacionalId) {
		this.unidadeOrganizacionalId = unidadeOrganizacionalId;
	}

	public String getUnidadeOrganizacionalDescricao() {
		return unidadeOrganizacionalDescricao;
	}

	public void setUnidadeOrganizacionalDescricao(String unidadeOrganizacionalDescricao) {
		this.unidadeOrganizacionalDescricao = unidadeOrganizacionalDescricao;
	}

	public Integer getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Integer funcionarioId) {
		this.funcionarioId = funcionarioId;
	}
}
