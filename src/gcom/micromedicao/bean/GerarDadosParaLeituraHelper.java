package gcom.micromedicao.bean;

import java.io.Serializable;

/**
 * [UC ] 
 * @author Sávio Luiz
 * @date 23/08/2007
 */
public class GerarDadosParaLeituraHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codigoRota = "";
	private String descricaoLocalidade = "";
	private String anoMesReferncia = "";
	private String grupo = "";
	private String codigoSetor = "";
	private String sequencialRota = "";
	private String nomeClienteUsuario = "";
	private String numeroHidrometro = "";
	private String localInstalacao = "";
	private String protecao = "";
	private String inscricao = "";
	private String matriculaImovel = "";
	private String enderecoImovel = "";
	private String dataPrevistaFaturamento = "";
	
	

	public GerarDadosParaLeituraHelper() {
	}
	
	public GerarDadosParaLeituraHelper(
			String codigoRota,
			String descricaoLocalidade, 
			String anoMesReferncia,
			String grupo, 
			String codigoSetor, 
			String sequencialRota, 
			String nomeClienteUsuario,
			String numeroHidrometro,
			String localInstalacao, 
			String protecao,
			String inscricao,
			String matriculaImovel,
			String enderecoImovel,
			String dataPrevistaFaturamento) {
		
		this.codigoRota =codigoRota; 
		this.descricaoLocalidade = descricaoLocalidade;
		this.anoMesReferncia = anoMesReferncia;
		this.grupo = grupo;
		this.codigoSetor = codigoSetor;
		this.sequencialRota = sequencialRota;
		this.nomeClienteUsuario = nomeClienteUsuario;
		this.numeroHidrometro = numeroHidrometro;
		this.localInstalacao = localInstalacao;
		this.protecao = protecao;
		this.inscricao = inscricao;
		this.matriculaImovel = matriculaImovel;
		this.enderecoImovel = enderecoImovel;
		this.dataPrevistaFaturamento = dataPrevistaFaturamento;
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

	public String getDataPrevistaFaturamento() {
		return dataPrevistaFaturamento;
	}

	public void setDataPrevistaFaturamento(String dataPrevistaFaturamento) {
		this.dataPrevistaFaturamento = dataPrevistaFaturamento;
	}	
	
	
	
}
