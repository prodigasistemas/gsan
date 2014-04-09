package gcom.gui.relatorio.faturamento.conta;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UCXXXX] Gerar Contas
 * @author Rafael Corrêa
 * @since 27/07/2009
 */
public class GerarRelatorioContaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String mesAno;
	private String idGrupoFaturamento;
	private String idLocalidadeInicial;
	private String nomeLocalidadeInicial;
	private String idLocalidadeFinal;
	private String nomeLocalidadeFinal;
	private String codigoSetorComercialInicial;
	private String nomeSetorComercialInicial;
	private String codigoSetorComercialFinal;
	private String nomeSetorComercialFinal;
	private String codigoRotaInicial;
	private String codigoRotaFinal;
	private String sequencialRotaInicial;
	private String sequencialRotaFinal;
	private String indicadorEmissao;
	private String indicadorOrdenacao;
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	/**
	 * @return Retorna o campo mesAno.
	 */
	public String getMesAno() {
		return mesAno;
	}

	/**
	 * @param mesAno O mesAno a ser setado.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	/**
	 * @return Retorna o campo codigoRotaFinal.
	 */
	public String getCodigoRotaFinal() {
		return codigoRotaFinal;
	}

	/**
	 * @param codigoRotaFinal O codigoRotaFinal a ser setado.
	 */
	public void setCodigoRotaFinal(String codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}

	/**
	 * @return Retorna o campo codigoRotaInicial.
	 */
	public String getCodigoRotaInicial() {
		return codigoRotaInicial;
	}

	/**
	 * @param codigoRotaInicial O codigoRotaInicial a ser setado.
	 */
	public void setCodigoRotaInicial(String codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}

	/**
	 * @return Retorna o campo codigoSetorComercialFinal.
	 */
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	/**
	 * @param codigoSetorComercialFinal O codigoSetorComercialFinal a ser setado.
	 */
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	/**
	 * @return Retorna o campo codigoSetorComercialIncial.
	 */
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	/**
	 * @param codigoSetorComercialIncial O codigoSetorComercialIncial a ser setado.
	 */
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	/**
	 * @return Retorna o campo idGrupoFaturamento.
	 */
	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	/**
	 * @param idGrupoFaturamento O idGrupoFaturamento a ser setado.
	 */
	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	/**
	 * @return Retorna o campo idLocalidadeFinal.
	 */
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}

	/**
	 * @param idLocalidadeFinal O idLocalidadeFinal a ser setado.
	 */
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}

	/**
	 * @return Retorna o campo idLocalidadeIncial.
	 */
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}

	/**
	 * @param idLocalidadeIncial O idLocalidadeIncial a ser setado.
	 */
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeFinal.
	 */
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	/**
	 * @param nomeLocalidadeFinal O nomeLocalidadeFinal a ser setado.
	 */
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeInicial.
	 */
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	/**
	 * @param nomeLocalidadeInicial O nomeLocalidadeInicial a ser setado.
	 */
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	/**
	 * @return Retorna o campo nomeSetorComercialFinal.
	 */
	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}

	/**
	 * @param nomeSetorComercialFinal O nomeSetorComercialFinal a ser setado.
	 */
	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}

	/**
	 * @return Retorna o campo nomeSetorComercialInicial.
	 */
	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}

	/**
	 * @param nomeSetorComercialInicial O nomeSetorComercialInicial a ser setado.
	 */
	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}


	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public String getIndicadorEmissao() {
		return indicadorEmissao;
	}

	public void setIndicadorEmissao(String indicadorEmissao) {
		this.indicadorEmissao = indicadorEmissao;
	}

	public String getIndicadorOrdenacao() {
		return indicadorOrdenacao;
	}

	public void setIndicadorOrdenacao(String indicadorOrdenacao) {
		this.indicadorOrdenacao = indicadorOrdenacao;
	}

	
}
