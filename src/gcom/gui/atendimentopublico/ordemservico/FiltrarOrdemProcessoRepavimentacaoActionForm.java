package gcom.gui.atendimentopublico.ordemservico;

import javax.servlet.ServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Yara Taciane de Souza
 * @created 29/05/2008
 */

public class FiltrarOrdemProcessoRepavimentacaoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idUnidadeResponsavel;
	private String descricaoUnidadeResponsavel;
	private String situacaoRetorno;
	private String situacaoRetornoDescricao;
	private String periodoEncerramentoOsInicial;
	private String periodoEncerramentoOsFinal;
	private String periodoRetornoServicoInicial;
	private String periodoRetornoServicoFinal;
	private String indicadorAtualizar;
	private String[] idRegistro;
	
	private String dataExecucao;	
	private String idPavimentoCalcadaRet;
	private String descricaoPavimentoCalcadaRet;
	private String idPavimentoRuaRet;	
	private String descricaoPavimentoRuaRet;
	private String areaPavimentoCalcadaRet;
	private String areaPavimentoRuaRet;
	private String escolhaRelatorio;
	private String manterPaginaAux;
	
	private String periodoRejeicaoInicial;
	private String periodoRejeicaoFinal;
	
	private String hint1;
	private String ordensServicoSelecionadas;
	
	private String idMunicipio;
	private String municipioDescricao;
	
	private String codigoBairro;
	private String bairroDescricao;
	private String idBairro;
		
	private String idLogradouro;
	private String logradouroDescricao;
	
	private String indicadorOsObservacaoRetorno;
	
	private String numeroOS;
	
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		super.reset(arg0, arg1);
		this.idUnidadeResponsavel = "";
		this.descricaoUnidadeResponsavel=""; 
		this.situacaoRetorno = "";
		this.periodoEncerramentoOsInicial = "";
		this.periodoEncerramentoOsFinal = "";
		this.periodoRetornoServicoInicial = "";
		this.periodoRetornoServicoFinal = "";	
		this.indicadorAtualizar = "";
		//this.idRegistro = "";
		
		this.dataExecucao="";	
		this.idPavimentoCalcadaRet="";
		this.descricaoPavimentoCalcadaRet="";
		this.idPavimentoRuaRet="";	
		this.descricaoPavimentoRuaRet="";
		
		this.areaPavimentoCalcadaRet ="";
		this.areaPavimentoRuaRet = "";
		this.periodoRejeicaoInicial = "";
		this.periodoRejeicaoFinal = "";
		
		this.hint1 = "";
		this.ordensServicoSelecionadas = "";
		this.idMunicipio = "";
		
		this.municipioDescricao = "";
		
		this.codigoBairro = "";
		this.bairroDescricao = "";
		this.idBairro = "";
			
		this.idLogradouro = "";
		this.logradouroDescricao = "";
		this.indicadorOsObservacaoRetorno = "";
	}

	/**
	 * @return Retorna o campo idUnidadeResponsavel.
	 */
	public String getIdUnidadeResponsavel() {
		return idUnidadeResponsavel;
	}

	/**
	 * @param idUnidadeResponsavel O idUnidadeResponsavel a ser setado.
	 */
	public void setIdUnidadeResponsavel(String idUnidadeResponsavel) {
		this.idUnidadeResponsavel = idUnidadeResponsavel;
	}

	/**
	 * @return Retorna o campo periodoEncerramentoOsFinal.
	 */
	public String getPeriodoEncerramentoOsFinal() {
		return periodoEncerramentoOsFinal;
	}

	/**
	 * @param periodoEncerramentoOsFinal O periodoEncerramentoOsFinal a ser setado.
	 */
	public void setPeriodoEncerramentoOsFinal(String periodoEncerramentoOsFinal) {
		this.periodoEncerramentoOsFinal = periodoEncerramentoOsFinal;
	}

	/**
	 * @return Retorna o campo periodoEncerramentoOsInicial.
	 */
	public String getPeriodoEncerramentoOsInicial() {
		return periodoEncerramentoOsInicial;
	}

	/**
	 * @param periodoEncerramentoOsInicial O periodoEncerramentoOsInicial a ser setado.
	 */
	public void setPeriodoEncerramentoOsInicial(String periodoEncerramentoOsInicial) {
		this.periodoEncerramentoOsInicial = periodoEncerramentoOsInicial;
	}

	/**
	 * @return Retorna o campo periodoRetornoServicoFinal.
	 */
	public String getPeriodoRetornoServicoFinal() {
		return periodoRetornoServicoFinal;
	}

	/**
	 * @param periodoRetornoServicoFinal O periodoRetornoServicoFinal a ser setado.
	 */
	public void setPeriodoRetornoServicoFinal(String periodoRetornoServicoFinal) {
		this.periodoRetornoServicoFinal = periodoRetornoServicoFinal;
	}

	/**
	 * @return Retorna o campo periodoRetornoServicoInicial.
	 */
	public String getPeriodoRetornoServicoInicial() {
		return periodoRetornoServicoInicial;
	}

	/**
	 * @param periodoRetornoServicoInicial O periodoRetornoServicoInicial a ser setado.
	 */
	public void setPeriodoRetornoServicoInicial(String periodoRetornoServicoInicial) {
		this.periodoRetornoServicoInicial = periodoRetornoServicoInicial;
	}

	/**
	 * @return Retorna o campo situacaoRetorno.
	 */
	public String getSituacaoRetorno() {
		return situacaoRetorno;
	}

	/**
	 * @param situacaoRetorno O situacaoRetorno a ser setado.
	 */
	public void setSituacaoRetorno(String situacaoRetorno) {
		this.situacaoRetorno = situacaoRetorno;
	}

	/**
	 * @return Retorna o campo indicadorAtualizar.
	 */
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	/**
	 * @param indicadorAtualizar O indicadorAtualizar a ser setado.
	 */
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	/**
	 * @return Retorna o campo descricaoUnidadeResponsavel.
	 */
	public String getDescricaoUnidadeResponsavel() {
		return descricaoUnidadeResponsavel;
	}

	/**
	 * @param descricaoUnidadeResponsavel O descricaoUnidadeResponsavel a ser setado.
	 */
	public void setDescricaoUnidadeResponsavel(String descricaoUnidadeResponsavel) {
		this.descricaoUnidadeResponsavel = descricaoUnidadeResponsavel;
	}




	/**
	 * @return Retorna o campo idRegistro.
	 */
	public String[] getIdRegistro() {
		return idRegistro;
	}

	/**
	 * @param idRegistro O idRegistro a ser setado.
	 */
	public void setIdRegistro(String[] idRegistro) {
		this.idRegistro = idRegistro;
	}

	/**
	 * @return Retorna o campo dataExecucao.
	 */
	public String getDataExecucao() {
		return dataExecucao;
	}

	/**
	 * @param dataExecucao O dataExecucao a ser setado.
	 */
	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	/**
	 * @return Retorna o campo descricaoPavimentoCalcadaRet.
	 */
	public String getDescricaoPavimentoCalcadaRet() {
		return descricaoPavimentoCalcadaRet;
	}

	/**
	 * @param descricaoPavimentoCalcadaRet O descricaoPavimentoCalcadaRet a ser setado.
	 */
	public void setDescricaoPavimentoCalcadaRet(String descricaoPavimentoCalcadaRet) {
		this.descricaoPavimentoCalcadaRet = descricaoPavimentoCalcadaRet;
	}

	/**
	 * @return Retorna o campo descricaoPavimentoRuaRet.
	 */
	public String getDescricaoPavimentoRuaRet() {
		return descricaoPavimentoRuaRet;
	}

	/**
	 * @param descricaoPavimentoRuaRet O descricaoPavimentoRuaRet a ser setado.
	 */
	public void setDescricaoPavimentoRuaRet(String descricaoPavimentoRuaRet) {
		this.descricaoPavimentoRuaRet = descricaoPavimentoRuaRet;
	}

	/**
	 * @return Retorna o campo idPavimentoCalcadaRet.
	 */
	public String getIdPavimentoCalcadaRet() {
		return idPavimentoCalcadaRet;
	}

	/**
	 * @param idPavimentoCalcadaRet O idPavimentoCalcadaRet a ser setado.
	 */
	public void setIdPavimentoCalcadaRet(String idPavimentoCalcadaRet) {
		this.idPavimentoCalcadaRet = idPavimentoCalcadaRet;
	}

	/**
	 * @return Retorna o campo idPavimentoRuaRet.
	 */
	public String getIdPavimentoRuaRet() {
		return idPavimentoRuaRet;
	}

	/**
	 * @param idPavimentoRuaRet O idPavimentoRuaRet a ser setado.
	 */
	public void setIdPavimentoRuaRet(String idPavimentoRuaRet) {
		this.idPavimentoRuaRet = idPavimentoRuaRet;
	}

	/**
	 * @return Retorna o campo areaPavimentoCalcadaRet.
	 */
	public String getAreaPavimentoCalcadaRet() {
		return areaPavimentoCalcadaRet;
	}

	/**
	 * @param areaPavimentoCalcadaRet O areaPavimentoCalcadaRet a ser setado.
	 */
	public void setAreaPavimentoCalcadaRet(String areaPavimentoCalcadaRet) {
		this.areaPavimentoCalcadaRet = areaPavimentoCalcadaRet;
	}

	/**
	 * @return Retorna o campo areaPavimentoRuaRet.
	 */
	public String getAreaPavimentoRuaRet() {
		return areaPavimentoRuaRet;
	}

	/**
	 * @param areaPavimentoRuaRet O areaPavimentoRuaRet a ser setado.
	 */
	public void setAreaPavimentoRuaRet(String areaPavimentoRuaRet) {
		this.areaPavimentoRuaRet = areaPavimentoRuaRet;
	}



	/**
	 * @return Returns the escolhaRelatorio.
	 */
	public String getEscolhaRelatorio() {
		return escolhaRelatorio;
	}

	/**
	 * @param escolhaRelatorio The escolhaRelatorio to set.
	 */
	public void setEscolhaRelatorio(String escolhaRelatorio) {
		this.escolhaRelatorio = escolhaRelatorio;
	}

	/**
	 * @return Returns the situacaoRetornoDescricao.
	 */
	public String getSituacaoRetornoDescricao() {
		return situacaoRetornoDescricao;
	}

	/**
	 * @param situacaoRetornoDescricao The situacaoRetornoDescricao to set.
	 */
	public void setSituacaoRetornoDescricao(String situacaoRetornoDescricao) {
		this.situacaoRetornoDescricao = situacaoRetornoDescricao;
	}

	/**
	 * @return Returns the manterPaginaAux.
	 */
	public String getManterPaginaAux() {
		return manterPaginaAux;
	}

	/**
	 * @param manterPaginaAux The manterPaginaAux to set.
	 */
	public void setManterPaginaAux(String manterPaginaAux) {
		this.manterPaginaAux = manterPaginaAux;
	}

	public String getPeriodoRejeicaoFinal() {
		return periodoRejeicaoFinal;
	}

	public void setPeriodoRejeicaoFinal(String periodoRejeicaoFinal) {
		this.periodoRejeicaoFinal = periodoRejeicaoFinal;
	}

	public String getPeriodoRejeicaoInicial() {
		return periodoRejeicaoInicial;
	}

	public void setPeriodoRejeicaoInicial(String periodoRejeicaoInicial) {
		this.periodoRejeicaoInicial = periodoRejeicaoInicial;
	}

	public String getHint1() {
		return hint1;
	}

	public void setHint1(String hint1) {
		this.hint1 = hint1;
	}

	public String getOrdensServicoSelecionadas() {
		return ordensServicoSelecionadas;
	}

	public void setOrdensServicoSelecionadas(String ordensServicoSelecionadas) {
		this.ordensServicoSelecionadas = ordensServicoSelecionadas;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getBairroDescricao() {
		return bairroDescricao;
	}

	public void setBairroDescricao(String bairroDescricao) {
		this.bairroDescricao = bairroDescricao;
	}

	public String getCodigoBairro() {
		return codigoBairro;
	}

	public void setCodigoBairro(String codigoBairro) {
		this.codigoBairro = codigoBairro;
	}

	public String getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(String idBairro) {
		this.idBairro = idBairro;
	}

	public String getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(String idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getLogradouroDescricao() {
		return logradouroDescricao;
	}

	public void setLogradouroDescricao(String logradouroDescricao) {
		this.logradouroDescricao = logradouroDescricao;
	}

	public String getMunicipioDescricao() {
		return municipioDescricao;
	}

	public void setMunicipioDescricao(String municipioDescricao) {
		this.municipioDescricao = municipioDescricao;
	}

	public String getIndicadorOsObservacaoRetorno() {
		return indicadorOsObservacaoRetorno;
	}

	public void setIndicadorOsObservacaoRetorno(String indicadorOsObservacaoRetorno) {
		this.indicadorOsObservacaoRetorno = indicadorOsObservacaoRetorno;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	
	
}

