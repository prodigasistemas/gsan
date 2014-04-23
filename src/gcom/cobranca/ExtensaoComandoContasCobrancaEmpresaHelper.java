package gcom.cobranca;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExtensaoComandoContasCobrancaEmpresaHelper implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idComandoEmpresaCobrancaContaExtensao;

	private Integer idUsuario;

	private Integer anoMesInicialContaComandoEmpresaCobrancaContaExtensao;

	private Integer anoMesFinalContaComandoEmpresaCobrancaContaExtensao;

	private Date dataExecucaoComandoEmpresaCobrancaContaExtensao;
	
	private Integer qtdeContasParaCobranca;
	
	private BigDecimal valorTotalContasParaCobranca;

	public Integer getAnoMesFinalContaComandoEmpresaCobrancaContaExtensao() {
		return anoMesFinalContaComandoEmpresaCobrancaContaExtensao;
	}

	public void setAnoMesFinalContaComandoEmpresaCobrancaContaExtensao(
			Integer anoMesFinalContaComandoEmpresaCobrancaContaExtensao) {
		this.anoMesFinalContaComandoEmpresaCobrancaContaExtensao = anoMesFinalContaComandoEmpresaCobrancaContaExtensao;
	}

	public Integer getAnoMesInicialContaComandoEmpresaCobrancaContaExtensao() {
		return anoMesInicialContaComandoEmpresaCobrancaContaExtensao;
	}

	public void setAnoMesInicialContaComandoEmpresaCobrancaContaExtensao(
			Integer anoMesInicialContaComandoEmpresaCobrancaContaExtensao) {
		this.anoMesInicialContaComandoEmpresaCobrancaContaExtensao = anoMesInicialContaComandoEmpresaCobrancaContaExtensao;
	}

	public Date getDataExecucaoComandoEmpresaCobrancaContaExtensao() {
		return dataExecucaoComandoEmpresaCobrancaContaExtensao;
	}

	public void setDataExecucaoComandoEmpresaCobrancaContaExtensao(
			Date dataExecucaoComandoEmpresaCobrancaContaExtensao) {
		this.dataExecucaoComandoEmpresaCobrancaContaExtensao = dataExecucaoComandoEmpresaCobrancaContaExtensao;
	}

	public Integer getIdComandoEmpresaCobrancaContaExtensao() {
		return idComandoEmpresaCobrancaContaExtensao;
	}

	public void setIdComandoEmpresaCobrancaContaExtensao(
			Integer idComandoEmpresaCobrancaContaExtensao) {
		this.idComandoEmpresaCobrancaContaExtensao = idComandoEmpresaCobrancaContaExtensao;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getQtdeContasParaCobranca() {
		return qtdeContasParaCobranca;
	}

	public void setQtdeContasParaCobranca(Integer qtdeContasParaCobranca) {
		this.qtdeContasParaCobranca = qtdeContasParaCobranca;
	}

	public BigDecimal getValorTotalContasParaCobranca() {
		return valorTotalContasParaCobranca;
	}

	public void setValorTotalContasParaCobranca(
			BigDecimal valorTotalContasParaCobranca) {
		this.valorTotalContasParaCobranca = valorTotalContasParaCobranca;
	}

}
