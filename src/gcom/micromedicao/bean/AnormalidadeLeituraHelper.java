package gcom.micromedicao.bean;



/**
 * @author Raphael Rossiter
 * @date 03/03/2007
 */
public class AnormalidadeLeituraHelper {
	
	private Integer idAnormalidadeLeitura;
	private String descricaoAnormalidadeLeitura;
	private Integer quantidade;
	private Integer idGrupoFaturamento;
	private Integer idEmpresa;
	private String nomeEmpresa;
	
	/**
	 * @return Retorna o campo idEmpresa.
	 */
	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa O idEmpresa a ser setado.
	 */
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return Retorna o campo idGrupoFaturamento.
	 */
	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	/**
	 * @param idGrupoFaturamento O idGrupoFaturamento a ser setado.
	 */
	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	/**
	 * @return Retorna o campo nomeEmpresa.
	 */
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	/**
	 * @param nomeEmpresa O nomeEmpresa a ser setado.
	 */
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public AnormalidadeLeituraHelper(){}

	/**
	 * @return Retorna o campo descricaoAnormalidadeLeitura.
	 */
	public String getDescricaoAnormalidadeLeitura() {
		return descricaoAnormalidadeLeitura;
	}

	/**
	 * @param descricaoAnormalidadeLeitura O descricaoAnormalidadeLeitura a ser setado.
	 */
	public void setDescricaoAnormalidadeLeitura(String descricaoAnormalidadeLeitura) {
		this.descricaoAnormalidadeLeitura = descricaoAnormalidadeLeitura;
	}

	/**
	 * @return Retorna o campo idAnormalidadeLeitura.
	 */
	public Integer getIdAnormalidadeLeitura() {
		return idAnormalidadeLeitura;
	}

	/**
	 * @param idAnormalidadeLeitura O idAnormalidadeLeitura a ser setado.
	 */
	public void setIdAnormalidadeLeitura(Integer idAnormalidadeLeitura) {
		this.idAnormalidadeLeitura = idAnormalidadeLeitura;
	}

	/**
	 * @return Retorna o campo quantidade.
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade O quantidade a ser setado.
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
