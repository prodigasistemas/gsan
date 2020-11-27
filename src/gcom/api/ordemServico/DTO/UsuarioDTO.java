package gcom.api.ordemServico.DTO;

public class UsuarioDTO {

	private Integer id;
	private String nomeUsuario;
	
	public UsuarioDTO(Integer id, String nomeUsuario) {
		super();
		this.id = id;
		this.nomeUsuario = nomeUsuario;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
}
