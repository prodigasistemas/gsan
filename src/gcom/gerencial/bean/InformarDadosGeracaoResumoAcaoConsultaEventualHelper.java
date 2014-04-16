package gcom.gerencial.bean;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

public class InformarDadosGeracaoResumoAcaoConsultaEventualHelper {

	private Integer idCobrancaAcaoAtividadeComando;
	
	private String tituloCobrancaAcaoAtividadeComando;

	private Date dataInicialEmissao;

	private Date dataFinalEmissao;

	private Collection colecaoGerenciaRegional;

	private Localidade eloPolo;

	private Localidade localidade;

	private SetorComercial setorComercial;

	private Quadra quadra;

	private Collection colecaoImovelPerfil;

	private Collection colecaoLigacaoAguaSituacao;

	private Collection colecaoLigacaoEsgotoSituacao;

	private Collection colecaoCategoria;

	private Collection colecaoEsferaPoder;

	private Collection colecaoEmpresa;

	private Collection colecaoCobrancaGrupo;
	
	private Collection colecaoUnidadeNegocio;

	public Collection getColecaoCategoria() {
		return colecaoCategoria;
	}

	public void setColecaoCategoria(Collection colecaoCategoria) {
		this.colecaoCategoria = colecaoCategoria;
	}

	public Collection getColecaoCobrancaGrupo() {
		return colecaoCobrancaGrupo;
	}

	public void setColecaoCobrancaGrupo(Collection colecaoCobrancaGrupo) {
		this.colecaoCobrancaGrupo = colecaoCobrancaGrupo;
	}

	public Collection getColecaoEmpresa() {
		return colecaoEmpresa;
	}

	public void setColecaoEmpresa(Collection colecaoEmpresa) {
		this.colecaoEmpresa = colecaoEmpresa;
	}

	public Collection getColecaoEsferaPoder() {
		return colecaoEsferaPoder;
	}

	public void setColecaoEsferaPoder(Collection colecaoEsferaPoder) {
		this.colecaoEsferaPoder = colecaoEsferaPoder;
	}

	public Collection getColecaoImovelPerfil() {
		return colecaoImovelPerfil;
	}

	public void setColecaoImovelPerfil(Collection colecaoImovelPerfil) {
		this.colecaoImovelPerfil = colecaoImovelPerfil;
	}

	public Collection getColecaoLigacaoAguaSituacao() {
		return colecaoLigacaoAguaSituacao;
	}

	public void setColecaoLigacaoAguaSituacao(
			Collection colecaoLigacaoAguaSituacao) {
		this.colecaoLigacaoAguaSituacao = colecaoLigacaoAguaSituacao;
	}

	public Collection getColecaoLigacaoEsgotoSituacao() {
		return colecaoLigacaoEsgotoSituacao;
	}

	public void setColecaoLigacaoEsgotoSituacao(
			Collection colecaoLigacaoEsgotoSituacao) {
		this.colecaoLigacaoEsgotoSituacao = colecaoLigacaoEsgotoSituacao;
	}

	public Localidade getEloPolo() {
		return eloPolo;
	}

	public void setEloPolo(Localidade eloPolo) {
		this.eloPolo = eloPolo;
	}

	public Collection getColecaoGerenciaRegional() {
		return colecaoGerenciaRegional;
	}

	public void setColecaoGerenciaRegional(Collection colecaoGerenciaRegional) {
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public String getFormatarDataEmissaoInicial() {

		String dataEmissaoInicialString = Util
				.formatarData(this.dataInicialEmissao);

		return dataEmissaoInicialString;
	}

	public String getFormatarDataEmissaoFinal() {

		String dataEmissaoFinalString = Util
				.formatarData(this.dataFinalEmissao);

		return dataEmissaoFinalString;
	}

	public Date getDataFinalEmissao() {
		return dataFinalEmissao;
	}

	public void setDataFinalEmissao(Date dataFinalEmissao) {
		this.dataFinalEmissao = dataFinalEmissao;
	}

	public Date getDataInicialEmissao() {
		return dataInicialEmissao;
	}

	public void setDataInicialEmissao(Date dataInicialEmissao) {
		this.dataInicialEmissao = dataInicialEmissao;
	}

	public Integer getIdCobrancaAcaoAtividadeComando() {
		return idCobrancaAcaoAtividadeComando;
	}

	public void setIdCobrancaAcaoAtividadeComando(
			Integer idCobrancaAcaoAtividadeComando) {
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
	 * @return Retorna o campo colecaoUnidadeNegocio.
	 */
	public Collection getColecaoUnidadeNegocio() {
		return colecaoUnidadeNegocio;
	}

	/**
	 * @param colecaoUnidadeNegocio O colecaoUnidadeNegocio a ser setado.
	 */
	public void setColecaoUnidadeNegocio(Collection colecaoUnidadeNegocio) {
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
	}



}
