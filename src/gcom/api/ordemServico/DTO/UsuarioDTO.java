package gcom.api.ordemServico.DTO;

public class UsuarioDTO {

	private Integer id;
	private String nome;
	private String unidade;
	private String equipe;
	
	public UsuarioDTO(Integer id, String nome, String unidade, String equipe) {
		super();
		this.id = id;
		this.nome = nome;
		this.unidade = unidade;
		this.equipe = equipe;
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
	
	public String getUnidade() {
		return unidade;
	}
	
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	public String getEquipe() {
		return equipe;
	}
	
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
	

}
