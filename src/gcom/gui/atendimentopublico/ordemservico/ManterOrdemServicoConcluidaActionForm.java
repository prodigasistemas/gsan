package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0753] Manter Ordem de Servico Concluida
 * 
 * @author Ivan Sérgio
 * @created 26/03/2008
 * 
 */
public class ManterOrdemServicoConcluidaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idOrdemServico;
	private String idOrdemServicoPesquisado;
	private String dataEmissao;
	private String dataEncerramento;
	private String idImovel;
	private String codigoFiscalizacao;
	private String codigoFiscalizacaoAnterior;
	private String dataFiscalizacao1;
	private String dataFiscalizacao2;
	private String dataFiscalizacao3;
	private String idUsuario;
	private String idFuncionario;
	private String indicadorTrocaProtecao;
	private String indicadorTrocaRegistro;
	private String descricaoHidrometroLocalInstalacao;
	private String dataEncerramentoBoletim;
	private String dataUltimaAlteracao;
	
	public String getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(String dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public String getDataEncerramentoBoletim() {
		return dataEncerramentoBoletim;
	}
	public void setDataEncerramentoBoletim(String dataEncerramentoBoletim) {
		this.dataEncerramentoBoletim = dataEncerramentoBoletim;
	}
	public String getCodigoFiscalizacao() {
		return codigoFiscalizacao;
	}
	public void setCodigoFiscalizacao(String codigoFiscalizacao) {
		this.codigoFiscalizacao = codigoFiscalizacao;
	}
	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public String getDataFiscalizacao1() {
		return dataFiscalizacao1;
	}
	public void setDataFiscalizacao1(String dataFiscalizacao1) {
		this.dataFiscalizacao1 = dataFiscalizacao1;
	}
	public String getDataFiscalizacao2() {
		return dataFiscalizacao2;
	}
	public void setDataFiscalizacao2(String dataFiscalizacao2) {
		this.dataFiscalizacao2 = dataFiscalizacao2;
	}
	public String getDataFiscalizacao3() {
		return dataFiscalizacao3;
	}
	public void setDataFiscalizacao3(String dataFiscalizacao3) {
		this.dataFiscalizacao3 = dataFiscalizacao3;
	}
	public String getDescricaoHidrometroLocalInstalacao() {
		return descricaoHidrometroLocalInstalacao;
	}
	public void setDescricaoHidrometroLocalInstalacao(
			String descricaoHidrometroLocalInstalacao) {
		this.descricaoHidrometroLocalInstalacao = descricaoHidrometroLocalInstalacao;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getIndicadorTrocaProtecao() {
		return indicadorTrocaProtecao;
	}
	public void setIndicadorTrocaProtecao(String indicadorTrocaProtecao) {
		this.indicadorTrocaProtecao = indicadorTrocaProtecao;
	}
	public String getIndicadorTrocaRegistro() {
		return indicadorTrocaRegistro;
	}
	public void setIndicadorTrocaRegistro(String indicadorTrocaRegistro) {
		this.indicadorTrocaRegistro = indicadorTrocaRegistro;
	}
	public String getIdOrdemServicoPesquisado() {
		return idOrdemServicoPesquisado;
	}
	public void setIdOrdemServicoPesquisado(String idOrdemServicoPesquisado) {
		this.idOrdemServicoPesquisado = idOrdemServicoPesquisado;
	}
	public String getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public String getCodigoFiscalizacaoAnterior() {
		return codigoFiscalizacaoAnterior;
	}
	public void setCodigoFiscalizacaoAnterior(String codigoFiscalizacaoAnterior) {
		this.codigoFiscalizacaoAnterior = codigoFiscalizacaoAnterior;
	}
	
	
}
