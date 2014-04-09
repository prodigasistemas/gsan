package gcom.gerencial.bean;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;

import java.util.Collection;

public class InformarDadosGeracaoResumoAcaoConsultaHelper {

	public final static Integer ID_IMPRESSAO_TRADICIONAL = new Integer(1);
	public final static String DESC_IMPRESSAO_TRADICIONAL = "IMPRESSAO TRADICIONAL";
	
	public final static Integer ID_IMPRESSAO_SIMULTANEA = new Integer(2);
	public final static String DESC_IMPRESSAO_SIMULTANEA = "IMPRESSAO SIMULTANEA";
	
	public final static Integer ID_AMBOS = new Integer(3);
	public final static String DESC_AMBOS = "AMBOS";
	
	private Integer anoMesReferencia;
	private Collection colecaoGerenciaRegional;
	private Collection colecaoUnidadeNegocio;
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
	
	private Integer tipoImpressao;
	
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
	
	public Collection getColecaoGerenciaRegional() {
		return colecaoGerenciaRegional;
	}
	public void setColecaoGerenciaRegional(Collection colecaoGerenciaRegional) {
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
	}
	
	public Collection getColecaoUnidadeNegocio() {
		return colecaoUnidadeNegocio;
	}
	public void setColecaoUnidadeNegocio(Collection colecaoUnidadeNegocio) {
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
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
	
	public String getFormatarAnoMesParaMesAno() {

		String anoMesRecebido = "" + this.getAnoMesReferencia();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}
	public Integer getTipoImpressao() {
		return tipoImpressao;
	}
	public void setTipoImpressao(Integer tipoImpressao) {
		this.tipoImpressao = tipoImpressao;
	}
}
