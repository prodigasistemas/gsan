package gcom.gui.relatorio.micromedicao;

import java.io.Serializable;

public class FiltroRelatorioLeituraConsultarArquivosTextoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String anoMes;
	String idEmpresa;
	String idGrupoFaturamento;
	String idSituacaoTransmissaoLeitura;
	String idLeiturista;
	String idServicoTipoCelular;
	String idLocalidade;
	
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}
	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	public String getIdLeiturista() {
		return idLeiturista;
	}
	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}
	public String getIdServicoTipoCelular() {
		return idServicoTipoCelular;
	}
	public void setIdServicoTipoCelular(String idServicoTipoCelular) {
		this.idServicoTipoCelular = idServicoTipoCelular;
	}
	public String getIdSituacaoTransmissaoLeitura() {
		return idSituacaoTransmissaoLeitura;
	}
	public void setIdSituacaoTransmissaoLeitura(String idSituacaoTransmissaoLeitura) {
		this.idSituacaoTransmissaoLeitura = idSituacaoTransmissaoLeitura;
	}
	public String getAnoMes() {
		return anoMes;
	}
	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	
	
	
	
	
}
