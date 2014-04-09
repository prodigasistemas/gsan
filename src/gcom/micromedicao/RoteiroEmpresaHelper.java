package gcom.micromedicao;


public class RoteiroEmpresaHelper {
	
	private Integer idEmpresa;
	private String nomeEmpresa;
	private Integer idRoteiroEmpresa;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private Integer codigoSetorComercial;
	private Integer idFuncionario;
	private Integer idCliente;
	private String nomeLeiturista;
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Integer getIdRoteiroEmpresa() {
		return idRoteiroEmpresa;
	}
	public void setIdRoteiroEmpresa(Integer idRoteiroEmpresa) {
		this.idRoteiroEmpresa = idRoteiroEmpresa;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getNomeLeiturista() {
		return nomeLeiturista;
	}
	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}
	/**
	 * @return Returns the idEmpresa.
	 */
	public Integer getIdEmpresa() {
		return idEmpresa; 
	}
	/**
	 * @param idEmpresa The idEmpresa to set.
	 */
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	/**
	 * @return Returns the nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	/**
	 * @param nomeLocalidade The nomeLocalidade to set.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
}
	
