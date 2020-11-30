package gcom.api.ordemservico.helper;

import java.io.Serializable;

import gcom.api.ordemservico.dto.OrdemServicoDTO;

public class ArquivoRetornoAplicativoExecucaoOSHelper implements Serializable {

	private static final long serialVersionUID = 1448570895806137609L;

	private OrdemServicoDTO ordemServicoDTO;
	private Integer idServicoTipo;
	private Integer idServicoMotivoNaoCobranca;
	private String valorusuario;
	private Integer valorPercentual;
	private String qtdParcelas;
	private Integer idTipoDebito;
	private String valorDebito;
	private String PercentualCobranca;
	private String DataReligacao;

	public OrdemServicoDTO getOrdemServicoDTO() {
		return ordemServicoDTO;
	}

	public void setOrdemServicoDTO(OrdemServicoDTO ordemServicoDTO) {
		this.ordemServicoDTO = ordemServicoDTO;
	}

	public Integer getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(Integer idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public Integer getIdServicoMotivoNaoCobranca() {
		return idServicoMotivoNaoCobranca;
	}

	public void setIdServicoMotivoNaoCobranca(Integer idServicoMotivoNaoCobranca) {
		this.idServicoMotivoNaoCobranca = idServicoMotivoNaoCobranca;
	}

	public String getValorusuario() {
		return valorusuario;
	}

	public void setValorusuario(String valorusuario) {
		this.valorusuario = valorusuario;
	}

	public Integer getValorPercentual() {
		return valorPercentual;
	}

	public void setValorPercentual(Integer valorPercentual) {
		this.valorPercentual = valorPercentual;
	}

	public String getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public Integer getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(Integer idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getPercentualCobranca() {
		return PercentualCobranca;
	}

	public void setPercentualCobranca(String percentualCobranca) {
		PercentualCobranca = percentualCobranca;
	}

	public String getDataReligacao() {
		return DataReligacao;
	}

	public void setDataReligacao(String dataReligacao) {
		DataReligacao = dataReligacao;
	}
}
