package gcom.micromedicao.bean;

import java.io.Serializable;

public class ConsultarArquivoTextoRoteiroEmpresaHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idEmpresa;
	
	private String idFaturamentoGrupo;
	
	private String anoMesReferencia;
	
	private String idSituacaoTransmissaoLeitura;
	
	private String idLeiturista;
	
	private String idServicoTipoCelular;
	
	private String idLocalidade;
	
	private String numeroImei;
	
	public ConsultarArquivoTextoRoteiroEmpresaHelper(){}

	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(String idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public String getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public String getIdSituacaoTransmissaoLeitura() {
		return idSituacaoTransmissaoLeitura;
	}

	public void setIdSituacaoTransmissaoLeitura(String idSituacaoTransmissaoLeitura) {
		this.idSituacaoTransmissaoLeitura = idSituacaoTransmissaoLeitura;
	}

	public String getIdServicoTipoCelular() {
		return idServicoTipoCelular;
	}

	public void setIdServicoTipoCelular(String idServicoTipoCelular) {
		this.idServicoTipoCelular = idServicoTipoCelular;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNumeroImei() {
		return numeroImei;
	}

	public void setNumeroImei(String numeroImei) {
		this.numeroImei = numeroImei;
	}
	
}

