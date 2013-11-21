package gcom.gui.cobranca.cobrancaporresultado;

import java.io.Serializable;
import java.math.BigDecimal;

public class MovimentarOrdemServicoGerarOSHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idComandoContaCobranca;
	
	private BigDecimal valorMinimo;
	
	private BigDecimal valorMaximo;
	
	private String[] idsCategoria;
	
	private String[] idsImovelPerfil;
	
	private String[] idsLigacaoAguaSituacao;

	public Integer getIdComandoContaCobranca() {
		return idComandoContaCobranca;
	}

	public void setIdComandoContaCobranca(Integer idComandoContaCobranca) {
		this.idComandoContaCobranca = idComandoContaCobranca;
	}

	public String[] getIdsCategoria() {
		return idsCategoria;
	}

	public void setIdsCategoria(String[] idsCategoria) {
		this.idsCategoria = idsCategoria;
	}

	public String[] getIdsImovelPerfil() {
		return idsImovelPerfil;
	}

	public void setIdsImovelPerfil(String[] idsImovelPerfil) {
		this.idsImovelPerfil = idsImovelPerfil;
	}

	public String[] getIdsLigacaoAguaSituacao() {
		return idsLigacaoAguaSituacao;
	}

	public void setIdsLigacaoAguaSituacao(String[] idsLigacaoAguaSituacao) {
		this.idsLigacaoAguaSituacao = idsLigacaoAguaSituacao;
	}

	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	
	
}
