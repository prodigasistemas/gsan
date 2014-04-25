package gcom.gerencial.bean;

import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.Rota;

import java.util.Collection;

public class InformarDadosGeracaoRelatorioConsultaHelper {

	private Integer anoMesReferencia;
	private Integer opcaoTotalizacao;
	private String descricaoOpcaoTotalizacao;
	private FaturamentoGrupo faturamentoGrupo;
	private CobrancaGrupo cobrancaGrupo;
	private GerenciaRegional gerenciaRegional;
	private Localidade eloPolo;
	private Localidade localidade;
	private Municipio municipio;
	private SetorComercial setorComercial;
	private Quadra quadra;
	private Collection colecaoImovelPerfil;
	private Collection colecaoLigacaoAguaSituacao;
	private Collection colecaoLigacaoEsgotoSituacao;
	private Collection colecaoCategoria;
	private Collection colecaoEsferaPoder;
	private Integer tipoAnaliseFaturamento;
	private String tipoDetalhe;
	private UnidadeNegocio unidadeNegocio;
	private String tipoQuebra;
	private Rota rota;
	
	private boolean gerarRelatorio = false;
	private Integer tipoRelatorio;
	
	
	public boolean isGerarRelatorio() {
		return gerarRelatorio;
	}


	public void setGerarRelatorio(boolean gerarRelatorio) {
		this.gerarRelatorio = gerarRelatorio;
	}


	public Integer getTipoRelatorio() {
		return tipoRelatorio;
	}


	public void setTipoRelatorio(Integer tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}


	public Integer getTipoAnaliseFaturamento() {
		return tipoAnaliseFaturamento;
	}


	public void setTipoAnaliseFaturamento(Integer tipoAnaliseFaturamento) {
		this.tipoAnaliseFaturamento = tipoAnaliseFaturamento;
	}


	public InformarDadosGeracaoRelatorioConsultaHelper(){}
	
	
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	public Collection getColecaoCategoria() {
		return colecaoCategoria;
	}
	public void setColecaoCategoria(Collection colecaoCategoria) {
		this.colecaoCategoria = colecaoCategoria;
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
	public void setColecaoLigacaoAguaSituacao(Collection colecaoLigacaoAguaSituacao) {
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
	public FaturamentoGrupo getFaturamentoGrupo() {
		return faturamentoGrupo;
	}
	public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public Integer getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}
	public void setOpcaoTotalizacao(Integer opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
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


	public String getDescricaoOpcaoTotalizacao() {
		return descricaoOpcaoTotalizacao;
	}


	public void setDescricaoOpcaoTotalizacao(String descricaoOpcaoTotalizacao) {
		this.descricaoOpcaoTotalizacao = descricaoOpcaoTotalizacao;
	}
	
	public String getFormatarAnoMesParaMesAno() {

		String anoMesRecebido = "" + this.getAnoMesReferencia();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}


	/**
	 * @return Retorna o campo cobrancaGrupo.
	 */
	public CobrancaGrupo getCobrancaGrupo() {
		return cobrancaGrupo;
	}


	/**
	 * @param cobrancaGrupo O cobrancaGrupo a ser setado.
	 */
	public void setCobrancaGrupo(CobrancaGrupo cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}


	public String getTipoDetalhe() {
		return tipoDetalhe;
	}


	public void setTipoDetalhe(String tipoDetalhe) {
		this.tipoDetalhe = tipoDetalhe;
	}


	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}


	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}


	public String getTipoQuebra() {
		return tipoQuebra;
	}


	public void setTipoQuebra(String tipoQuebra) {
		this.tipoQuebra = tipoQuebra;
	}


	public Rota getRota() {
		return rota;
	}


	public void setRota(Rota rota) {
		this.rota = rota;
	}
}
