package gcom.atendimentopublico.ordemservico.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class AcompanhamentoArquivosRoteiroHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idAcompanhamentoArquivosRoteiro;
	
	private String nmEquipe;
	
	private String idEquipe;
	
	private String qtdOrdemServicoProgramadas;
	
	private String qtdOrdemServicoTransmitidas;
	
	private String dsSituacao;
	
	public Collection<OSProgramacaoAcompanhamentoServicoHelper> osProgramacaoAcompServicoHelper;

	public String getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(String idEquipe) {
		this.idEquipe = idEquipe;
	}
	
	public String getQtdOrdemServicoProgramadas() {
		return qtdOrdemServicoProgramadas;
	}

	public void setQtdOrdemServicoProgramadas(String qtdOrdemServicoProgramadas) {
		this.qtdOrdemServicoProgramadas = qtdOrdemServicoProgramadas;
	}

	public String getQtdOrdemServicoTransmitidas() {
		return qtdOrdemServicoTransmitidas;
	}

	public void setQtdOrdemServicoTransmitidas(String qtdOrdemServicoTransmitidas) {
		this.qtdOrdemServicoTransmitidas = qtdOrdemServicoTransmitidas;
	}

	public AcompanhamentoArquivosRoteiroHelper(){
		osProgramacaoAcompServicoHelper = new ArrayList<OSProgramacaoAcompanhamentoServicoHelper>();
	}

	public String getDsSituacao() {
		return dsSituacao;
	}

	public void setDsSituacao(String dsSituacao) {
		this.dsSituacao = dsSituacao;
	}

	public String getNmEquipe() {
		return nmEquipe;
	}

	public void setNmEquipe(String nmEquipe) {
		this.nmEquipe = nmEquipe;
	}

	public String getIdAcompanhamentoArquivosRoteiro() {
		return idAcompanhamentoArquivosRoteiro;
	}

	public void setIdAcompanhamentoArquivosRoteiro(
			String idAcompanhamentoArquivosRoteiro) {
		this.idAcompanhamentoArquivosRoteiro = idAcompanhamentoArquivosRoteiro;
	}

	public Collection<OSProgramacaoAcompanhamentoServicoHelper> getOsProgramacaoAcompServicoHelper() {
		return osProgramacaoAcompServicoHelper;
	}

	public void setOsProgramacaoAcompServicoHelper(
			Collection<OSProgramacaoAcompanhamentoServicoHelper> osProgramacaoAcompServicoHelper) {
		this.osProgramacaoAcompServicoHelper = osProgramacaoAcompServicoHelper;
	}
}

