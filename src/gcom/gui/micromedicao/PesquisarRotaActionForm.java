package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;


/**
 * 
 * Form da Pesquisa por Rota da página Infomar Leitura por Rota
 * 
 * @author Administrador
 *
 */
public class PesquisarRotaActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idGrupoFaturamento;
	
	private String idLocalidade;
	
	private String localidadeDescricao;
	
	private String codigoSetorComercial;
	
	private String setorComercialDescricao;
	
	private String empresaLeituristica;
	
	private String codigoRota;
	
	private String indicadorUso;
	
	private String indicadorRotaAlternativa;
	
	private String bloquearCampos;

	/**
	 * @return String O campo de indicador rota alternativa
	 */
	public String getIndicadorRotaAlternativa() {
		return indicadorRotaAlternativa;
	}
	
	
	/**
	 * @param indicadorRotaAlternativa - O campo indicador de rota alternativa
	 */
	public void setIndicadorRotaAlternativa(String indicadorRotaAlternativa) {
		this.indicadorRotaAlternativa = indicadorRotaAlternativa;
	}

	/**
	 * @return Retorna o campo bloquearCampos.
	 */
	public String getBloquearCampos() {
		return bloquearCampos;
	}

	/**
	 * @param bloquearCampos O bloquearCampos a ser setado.
	 */
	public void setBloquearCampos(String bloquearCampos) {
		this.bloquearCampos = bloquearCampos;
	}

	/**
	 * @return Retorna o campo codigoRota.
	 */
	public String getCodigoRota() {
		return codigoRota;
	}

	/**
	 * @param codigoRota O codigoRota a ser setado.
	 */
	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Returns the codigoSetorComercial.
	 */
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial The codigoSetorComercial to set.
	 */
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Returns the empresaLeituristica.
	 */
	public String getEmpresaLeituristica() {
		return empresaLeituristica;
	}

	/**
	 * @param empresaLeituristica The empresaLeituristica to set.
	 */
	public void setEmpresaLeituristica(String empresaLeituristica) {
		this.empresaLeituristica = empresaLeituristica;
	}

	/**
	 * @return Returns the idGrupoFaturamento.
	 */
	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	/**
	 * @param idGrupoFaturamento The idGrupoFaturamento to set.
	 */
	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	/**
	 * @return Returns the idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade The idLocalidade to set.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Returns the localidadeDescricao.
	 */
	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}

	/**
	 * @param localidadeDescricao The localidadeDescricao to set.
	 */
	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}

	/**
	 * @return Returns the setorComercialDescricao.
	 */
	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	/**
	 * @param setorComercialDescricao The setorComercialDescricao to set.
	 */
	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}
	
	

}
