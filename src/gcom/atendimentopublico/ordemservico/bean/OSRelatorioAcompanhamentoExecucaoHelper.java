package gcom.atendimentopublico.ordemservico.bean;

import java.io.Serializable;
import java.util.Date;


/**
 * [UC0450] Filtrar Registro de Atendimento
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Rafael Pinto
 * @date 18/08/2006
 */
public class OSRelatorioAcompanhamentoExecucaoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer idOrdemServico;
	private String situacaoOS;
	private Integer idServicoTipo;
	private String descricaoServicoTipo;
	private Integer idRegistroAtendimento;
	private Date dataSolicitacao;
	private Date dataEncerramento;
	private Date dataProgramacao;
	private Integer idUnidadeAtendimento;
	private String nomeUnidadeAtendimento;
	private String nomeUnidadeAtual;
	private String endereco;
	private String nomeEquipe;
	private Date dataEncerramentoRA;
	private Integer diasPrazo;
	
	/**
	 * @return Retorna o campo diasAtraso.
	 */
	public Integer getDiasPrazo() {
		return diasPrazo;
	}

	/**
	 * @param diasAtraso O diasAtraso a ser setado.
	 */
	public void setDiasPrazo(Integer diasPrazo) {
		this.diasPrazo = diasPrazo;
	}

	/**
	 * @return Retorna o campo nomeEquipe.
	 */
	public String getNomeEquipe() {
		return nomeEquipe;
	}

	/**
	 * @param nomeEquipe O nomeEquipe a ser setado.
	 */
	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}

	public OSRelatorioAcompanhamentoExecucaoHelper(){}

	/**
	 * @return Retorna o campo dataEncerramento.
	 */
	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	/**
	 * @param dataEncerramento O dataEncerramento a ser setado.
	 */
	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	/**
	 * @return Retorna o campo dataProgramacao.
	 */
	public Date getDataProgramacao() {
		return dataProgramacao;
	}

	/**
	 * @param dataProgramacao O dataProgramacao a ser setado.
	 */
	public void setDataProgramacao(Date dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}

	/**
	 * @return Retorna o campo dataSolicitacao.
	 */
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	/**
	 * @param dataSolicitacao O dataSolicitacao a ser setado.
	 */
	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	/**
	 * @return Retorna o campo descricaoServicoTipo.
	 */
	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	/**
	 * @param descricaoServicoTipo O descricaoServicoTipo a ser setado.
	 */
	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo idOrdemServico.
	 */
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	/**
	 * @param idOrdemServico O idOrdemServico a ser setado.
	 */
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	/**
	 * @return Retorna o campo idRegistroAtendimento.
	 */
	public Integer getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}

	/**
	 * @param idRegistroAtendimento O idRegistroAtendimento a ser setado.
	 */
	public void setIdRegistroAtendimento(Integer idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}

	/**
	 * @return Retorna o campo idServicoTipo.
	 */
	public Integer getIdServicoTipo() {
		return idServicoTipo;
	}

	/**
	 * @param idServicoTipo O idServicoTipo a ser setado.
	 */
	public void setIdServicoTipo(Integer idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	/**
	 * @return Retorna o campo idUnidadeAtendimento.
	 */
	public Integer getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}

	/**
	 * @param idUnidadeAtendimento O idUnidadeAtendimento a ser setado.
	 */
	public void setIdUnidadeAtendimento(Integer idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}

	/**
	 * @return Retorna o campo nomeUnidadeAtendimento.
	 */
	public String getNomeUnidadeAtendimento() {
		return nomeUnidadeAtendimento;
	}

	/**
	 * @param nomeUnidadeAtendimento O nomeUnidadeAtendimento a ser setado.
	 */
	public void setNomeUnidadeAtendimento(String nomeUnidadeAtendimento) {
		this.nomeUnidadeAtendimento = nomeUnidadeAtendimento;
	}

	/**
	 * @return Retorna o campo situacaoOS.
	 */
	public String getSituacaoOS() {
		return situacaoOS;
	}

	/**
	 * @param situacaoOS O situacaoOS a ser setado.
	 */
	public void setSituacaoOS(String situacaoOS) {
		this.situacaoOS = situacaoOS;
	}

	/**
	 * @return Retorna o campo nomeUnidadeAtual.
	 */
	public String getNomeUnidadeAtual() {
		return nomeUnidadeAtual;
	}

	/**
	 * @param nomeUnidadeAtual O nomeUnidadeAtual a ser setado.
	 */
	public void setNomeUnidadeAtual(String nomeUnidadeAtual) {
		this.nomeUnidadeAtual = nomeUnidadeAtual;
	}

	/**
	 * @return Retorna o campo dataEncerramentoRA.
	 */
	public Date getDataEncerramentoRA() {
		return dataEncerramentoRA;
	}

	/**
	 * @param dataEncerramentoRA O dataEncerramentoRA a ser setado.
	 */
	public void setDataEncerramentoRA(Date dataEncerramentoRA) {
		this.dataEncerramentoRA = dataEncerramentoRA;
	}
	
	

}
