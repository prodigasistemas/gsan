package gcom.gui.gerencial.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para informar
 * os dados para geração de relatório/consulta
 *
 * @author Sávio Luiz
 * @date 22/05/2007
 */
public class InformarDadosGeracaoResumoAcaoConsultaEventualActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String[] grupoCobranca;
	private String[] gerencialRegional;
	private String[] unidadeNegocio;
	private String eloPolo;
	private String descricaoEloPolo;
	private String localidade;
	private String descricaoLocalidade;
	private String idSetorComercial;
	private String setorComercial;
	private String descricaoSetorComercial;
	private String quadra;
	private String descricaoQuadra;
	private String[] perfilImovel;
	private String[] situacaoLigacaoAgua;
	private String[] situacaoLigacaoEsgoto;
	private String[] categoria;
	private String[] esferaPoder;
	private String[] empresa;
	private String dataInicialEmissao;
	private String dataFinalEmissao;
	private String idCobrancaAcaoAtividadeComando;
	private String tituloCobrancaAcaoAtividadeComando;
	public String[] getCategoria() {
		return categoria;
	}
	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}
	public String getDescricaoEloPolo() {
		return descricaoEloPolo;
	}
	public void setDescricaoEloPolo(String descricaoEloPolo) {
		this.descricaoEloPolo = descricaoEloPolo;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getDescricaoQuadra() {
		return descricaoQuadra;
	}
	public void setDescricaoQuadra(String descricaoQuadra) {
		this.descricaoQuadra = descricaoQuadra;
	}
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}
	public String getEloPolo() {
		return eloPolo;
	}
	public void setEloPolo(String eloPolo) {
		this.eloPolo = eloPolo;
	}
	public String[] getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String[] empresa) {
		this.empresa = empresa;
	}
	public String[] getEsferaPoder() {
		return esferaPoder;
	}
	public void setEsferaPoder(String[] esferaPoder) {
		this.esferaPoder = esferaPoder;
	}
	
	public String[] getGrupoCobranca() {
		return grupoCobranca;
	}
	public void setGrupoCobranca(String[] grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String[] getPerfilImovel() {
		return perfilImovel;
	}
	public void setPerfilImovel(String[] perfilImovel) {
		this.perfilImovel = perfilImovel;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}
	public String[] getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String[] situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String[] getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String[] situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String[] getGerencialRegional() {
		return gerencialRegional;
	}
	public void setGerencialRegional(String[] gerencialRegional) {
		this.gerencialRegional = gerencialRegional;
	}
	public String getDataFinalEmissao() {
		return dataFinalEmissao;
	}
	public void setDataFinalEmissao(String dataFinalEmissao) {
		this.dataFinalEmissao = dataFinalEmissao;
	}
	public String getDataInicialEmissao() {
		return dataInicialEmissao;
	}
	public void setDataInicialEmissao(String dataInicialEmissao) {
		this.dataInicialEmissao = dataInicialEmissao;
	}
	public String getIdCobrancaAcaoAtividadeComando() {
		return idCobrancaAcaoAtividadeComando;
	}
	public void setIdCobrancaAcaoAtividadeComando(
			String idCobrancaAcaoAtividadeComando) {
		this.idCobrancaAcaoAtividadeComando = idCobrancaAcaoAtividadeComando;
	}
	public String getTituloCobrancaAcaoAtividadeComando() {
		return tituloCobrancaAcaoAtividadeComando;
	}
	public void setTituloCobrancaAcaoAtividadeComando(
			String tituloCobrancaAcaoAtividadeComando) {
		this.tituloCobrancaAcaoAtividadeComando = tituloCobrancaAcaoAtividadeComando;
	}
	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String[] getUnidadeNegocio() {
		return unidadeNegocio;
	}
	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String[] unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	

	
}
