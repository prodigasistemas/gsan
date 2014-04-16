package gcom.relatorio.micromedicao;

import gcom.micromedicao.bean.GerarDadosParaLeituraHelper;
import gcom.relatorio.RelatorioBean;

/**
 * [UC ] 
 * @author Sávio Luiz
 * @date 27/08/2007
 */
public class RelatorioGerarDadosParaLeituraBean implements RelatorioBean {
	
	
	private String codigoRota;
	private String descricaoLocalidade;
	private String anoMesReferncia;
	private String grupo;
	private String codigoSetor;
	private String sequencialRota;
	private String nomeClienteUsuario;
	private String numeroHidrometro;
	private String localInstalacao;
	private String protecao;
	private String inscricao;
	private String matriculaImovel;
	private String enderecoImovel;

	
	
    public RelatorioGerarDadosParaLeituraBean(GerarDadosParaLeituraHelper gerarDadosParaLeituraHelper) {
		this.codigoRota = gerarDadosParaLeituraHelper.getCodigoRota();
		this.descricaoLocalidade = gerarDadosParaLeituraHelper.getDescricaoLocalidade();
		this.anoMesReferncia = gerarDadosParaLeituraHelper.getAnoMesReferncia();
		this.grupo = gerarDadosParaLeituraHelper.getGrupo();
		this.codigoSetor = gerarDadosParaLeituraHelper.getCodigoSetor();
		this.sequencialRota = gerarDadosParaLeituraHelper.getSequencialRota();
		this.nomeClienteUsuario = gerarDadosParaLeituraHelper.getNomeClienteUsuario();
		this.numeroHidrometro = gerarDadosParaLeituraHelper.getNumeroHidrometro();
		this.localInstalacao = gerarDadosParaLeituraHelper.getLocalInstalacao();
		this.protecao = gerarDadosParaLeituraHelper.getProtecao();
		this.inscricao = gerarDadosParaLeituraHelper.getInscricao();
		this.matriculaImovel = gerarDadosParaLeituraHelper.getMatriculaImovel();
		this.enderecoImovel = gerarDadosParaLeituraHelper.getEnderecoImovel();

   }



	public String getAnoMesReferncia() {
		return anoMesReferncia;
	}



	public void setAnoMesReferncia(String anoMesReferncia) {
		this.anoMesReferncia = anoMesReferncia;
	}



	public String getCodigoRota() {
		return codigoRota;
	}



	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}



	public String getCodigoSetor() {
		return codigoSetor;
	}



	public void setCodigoSetor(String codigoSetor) {
		this.codigoSetor = codigoSetor;
	}



	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}



	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}



	public String getGrupo() {
		return grupo;
	}



	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}



	public String getInscricao() {
		return inscricao;
	}



	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}



	public String getLocalInstalacao() {
		return localInstalacao;
	}



	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}



	public String getMatriculaImovel() {
		return matriculaImovel;
	}



	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}



	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}



	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}



	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}



	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}



	public String getProtecao() {
		return protecao;
	}



	public void setProtecao(String protecao) {
		this.protecao = protecao;
	}



	public String getSequencialRota() {
		return sequencialRota;
	}



	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}



	public String getEnderecoImovel() {
		return enderecoImovel;
	}



	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	
		
	
}
