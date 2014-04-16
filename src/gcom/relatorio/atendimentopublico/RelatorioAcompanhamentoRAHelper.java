package gcom.relatorio.atendimentopublico;

import java.io.Serializable;

/**
 * classe responsável por criar o relatório de Acompanhamentode RA
 * 
 * [UC1056] ? Gerar Relatório de Acompanhamento dos Registros de Atendimento
 * 
 * @author Hugo Leonardo
 *
 * @date 28/09/2010
 */
public class RelatorioAcompanhamentoRAHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idRA;
	private String idUnidadeAtendimento;
	private String descricaoUnidadeAtendimento;
	private String idMunicipio;
	private String descricaoMunicipio;
	private String tipoSolicitacao;
	private String dataAbertura;
	private String dataRecebimento;
	private String dataEncerramento;
	private String idMotivoEncerramento;
	private String motivoEncerramento;
	private Integer quantidade;
	private String descricaoMotivoEncerramento;
	
	public String getDataAbertura() {
		return dataAbertura;
	}
	
	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	
	public String getDataRecebimento() {
		return dataRecebimento;
	}
	
	public void setDataRecebimento(String dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}
	
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}
	
	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public String getIdRA() {
		return idRA;
	}

	public void setIdRA(String idRA) {
		this.idRA = idRA;
	}

	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}

	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}

	public String getDescricaoUnidadeAtendimento() {
		return descricaoUnidadeAtendimento;
	}

	public void setDescricaoUnidadeAtendimento(String descricaoUnidadeAtendimento) {
		this.descricaoUnidadeAtendimento = descricaoUnidadeAtendimento;
	}

	public String getDescricaoMotivoEncerramento() {
		return descricaoMotivoEncerramento;
	}

	public void setDescricaoMotivoEncerramento(String descricaoMotivoEncerramento) {
		this.descricaoMotivoEncerramento = descricaoMotivoEncerramento;
	}

	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
}
