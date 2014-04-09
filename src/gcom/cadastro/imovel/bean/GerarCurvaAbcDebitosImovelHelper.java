package gcom.cadastro.imovel.bean;

/**
 * Débitos de um imóvel
 * @author Ivan Sérgio
 * @since 11/09/2007
 */
public class GerarCurvaAbcDebitosImovelHelper {
	
	private String idImovel; 
	private String descricaoCategoria;
	private String descricaoSubCategoria;
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String gerenciaRegional;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String codigoSetorComercial;
	private String descricaoSetorComercial;
	
	public GerarCurvaAbcDebitosImovelHelper(
			String idimovel,
			String idGerenciaRegional,
			String gerenciaRegional,
			String nomeGerenciaRegional,
			String idLocalidade,
			String descricaoLocalidade,
			String codigoSetorComercial, 
			String descricaoSetorComercial,			
			String descricaoCategoria, 
			String descricaoSubCategoria) {
		
		// TODO Auto-generated constructor stub
		this.idImovel = idimovel;
		this.idGerenciaRegional = idGerenciaRegional;
		this.gerenciaRegional = gerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.descricaoSetorComercial = descricaoSetorComercial;
		this.descricaoCategoria = descricaoCategoria;
		this.descricaoSubCategoria = descricaoSubCategoria;
	}
	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return Retorna o campo nomeGerenciaRegional.
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	/**
	 * @param nomeGerenciaRegional O nomeGerenciaRegional a ser setado.
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	/**
	 * @return Retorna o campo descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	/**
	 * @param descricaoLocalidade O descricaoLocalidade a ser setado.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}
	public String getDescricaoSubCategoria() {
		return descricaoSubCategoria;
	}
	public void setDescricaoSubCategoria(String descricaoSubCategoria) {
		this.descricaoSubCategoria = descricaoSubCategoria;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
}
