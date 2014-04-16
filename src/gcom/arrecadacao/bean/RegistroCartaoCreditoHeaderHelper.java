package gcom.arrecadacao.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Header do TXT do retorno do cartão de crédito
 * 
 * @author Raphael Rossiter
 * @date 28/01/2010
 */
public class RegistroCartaoCreditoHeaderHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Short tipoRegistro;
	
	private String estabelecimentoMatriz;
	
	private Date dataProcessamento;
	
	private Date dataPeriodoInicial;
	
	private Date dataPeriodoFinal;
	
	private String produto;
	
	private String sequencia;
	
	private String empresa;
	
	private String opcaoExtrato;
	
	private String transmissao;
	
	private String descricaoOpcaoExtrato;
	
	//CONSTANTE HEADER
	public final static Short CODIGO_HEADER = new Short("0");
	
	
	public RegistroCartaoCreditoHeaderHelper(){}

	public Date getDataPeriodoFinal() {
		return dataPeriodoFinal;
	}

	public void setDataPeriodoFinal(Date dataPeriodoFinal) {
		this.dataPeriodoFinal = dataPeriodoFinal;
	}

	public Date getDataPeriodoInicial() {
		return dataPeriodoInicial;
	}

	public void setDataPeriodoInicial(Date dataPeriodoInicial) {
		this.dataPeriodoInicial = dataPeriodoInicial;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public String getDescricaoOpcaoExtrato() {
		return descricaoOpcaoExtrato;
	}

	public void setDescricaoOpcaoExtrato(String descricaoOpcaoExtrato) {
		this.descricaoOpcaoExtrato = descricaoOpcaoExtrato;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEstabelecimentoMatriz() {
		return estabelecimentoMatriz;
	}

	public void setEstabelecimentoMatriz(String estabelecimentoMatriz) {
		this.estabelecimentoMatriz = estabelecimentoMatriz;
	}

	public String getOpcaoExtrato() {
		return opcaoExtrato;
	}

	public void setOpcaoExtrato(String opcaoExtrato) {
		this.opcaoExtrato = opcaoExtrato;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getSequencia() {
		return sequencia;
	}

	public void setSequencia(String sequencia) {
		this.sequencia = sequencia;
	}

	public Short getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Short tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getTransmissao() {
		return transmissao;
	}

	public void setTransmissao(String transmissao) {
		this.transmissao = transmissao;
	}
}
