package gcom.faturamento;

import gcom.cadastro.imovel.Imovel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExtratoQuitacao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Imovel imovel;
	private Integer anoReferencia;
	private Integer indicadorImpressao;
	private BigDecimal valorTotalDasContas;
	private Date ultimaAlteracao;
	private Integer anoMesMensagemConta;
	private Integer indicadorImpressaoNaConta;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAnoReferencia() {
		return anoReferencia;
	}
	public void setAnoReferencia(Integer anoReferencia) {
		this.anoReferencia = anoReferencia;
	}
	public Integer getIndicadorImpressao() {
		return indicadorImpressao;
	}
	public void setIndicadorImpressao(Integer indicadorImpressao) {
		this.indicadorImpressao = indicadorImpressao;
	}
	public BigDecimal getValorTotalDasContas() {
		return valorTotalDasContas;
	}
	public void setValorTotalDasContas(BigDecimal valorTotalDasContas) {
		this.valorTotalDasContas = valorTotalDasContas;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public String getMatriculaFormatada() {

		String matriculaImovelFormatada = "";
		
		Integer matriculaImovel = this.getImovel().getId();
		
		matriculaImovelFormatada = "" + matriculaImovel;
		
		int quantidadeCaracteresImovel = matriculaImovel.toString()
					.length();
			matriculaImovelFormatada = matriculaImovelFormatada.substring(0,
					quantidadeCaracteresImovel - 1)
					+ "."
					+ matriculaImovelFormatada.substring(
							quantidadeCaracteresImovel - 1,
							quantidadeCaracteresImovel);

		return matriculaImovelFormatada;
	}
	public Integer getAnoMesMensagemConta() {
		return anoMesMensagemConta;
	}
	public void setAnoMesMensagemConta(Integer anoMesMensagemConta) {
		this.anoMesMensagemConta = anoMesMensagemConta;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public Integer getIndicadorImpressaoNaConta() {
		return indicadorImpressaoNaConta;
	}
	public void setIndicadorImpressaoNaConta(Integer indicadorImpressaoNaConta) {
		this.indicadorImpressaoNaConta = indicadorImpressaoNaConta;
	}
	
}
