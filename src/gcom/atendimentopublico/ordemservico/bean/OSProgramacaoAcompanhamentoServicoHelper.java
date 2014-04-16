package gcom.atendimentopublico.ordemservico.bean;

import java.io.Serializable;

public class OSProgramacaoAcompanhamentoServicoHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idOsProgramacaoAcompanhamentoServico;
	
	private String idOrdemServico;
	
	private String dsEndereco;
	
	private String dsSituacao;
	
	private String dsServicoTipo;
	
	private String nnSequencialProgramacao;

	public String getNnSequencialProgramacao() {
		return nnSequencialProgramacao;
	}

	public void setNnSequencialProgramacao(String nnSequencialProgramacao) {
		this.nnSequencialProgramacao = nnSequencialProgramacao;
	}

	public String getDsEndereco() {
		return dsEndereco;
	}

	public String getIdOsProgramacaoAcompanhamentoServico() {
		return idOsProgramacaoAcompanhamentoServico;
	}

	public void setIdOsProgramacaoAcompanhamentoServico(
			String idOsProgramacaoAcompanhamentoServico) {
		this.idOsProgramacaoAcompanhamentoServico = idOsProgramacaoAcompanhamentoServico;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public String getDsServicoTipo() {
		return dsServicoTipo;
	}

	public void setDsServicoTipo(String dsServicoTipo) {
		this.dsServicoTipo = dsServicoTipo;
	}

	public String getDsSituacao() {
		return dsSituacao;
	}

	public void setDsSituacao(String dsSituacao) {
		this.dsSituacao = dsSituacao;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
}

