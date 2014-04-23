package gcom.atendimentopublico.ordemservico.bean;

import java.util.Date;


/**
 * [UC0753] Manter Ordem de Servico Concluida
 * 
 * @author Ivan Sérgio
 * @created 01/04/2008
 * 
 */
public class ManterOrdemServicoConcluidaHelper {
	private Integer idOrdemServico;
	private Date dataEmissao;
	private Date dataEncerramento;
	private Integer idImovel;
	private Short codigoFiscalizacao;
	private Date dataFiscalizacao1;
	private Date dataFiscalizacao2;
	private Date dataFiscalizacao3;
	private Integer idUsuario;
	private Integer idFuncionario;
	private Short indicadorTrocaProtecao;
	private Short indicadorTrocaRegistro;
	private String descricaoHidrometroLocalInstalacao;
	private Date dataEncerramentoBoletim;
	private Date dataUltimaAlteracao;	
	
	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public Date getDataEncerramentoBoletim() {
		return dataEncerramentoBoletim;
	}
	public void setDataEncerramentoBoletim(Date dataEncerramentoBoletim) {
		this.dataEncerramentoBoletim = dataEncerramentoBoletim;
	}
	public Short getCodigoFiscalizacao() {
		return codigoFiscalizacao;
	}
	public void setCodigoFiscalizacao(Short codigoFiscalizacao) {
		this.codigoFiscalizacao = codigoFiscalizacao;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Date getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public Date getDataFiscalizacao1() {
		return dataFiscalizacao1;
	}
	public void setDataFiscalizacao1(Date dataFiscalizacao1) {
		this.dataFiscalizacao1 = dataFiscalizacao1;
	}
	public Date getDataFiscalizacao2() {
		return dataFiscalizacao2;
	}
	public void setDataFiscalizacao2(Date dataFiscalizacao2) {
		this.dataFiscalizacao2 = dataFiscalizacao2;
	}
	public Date getDataFiscalizacao3() {
		return dataFiscalizacao3;
	}
	public void setDataFiscalizacao3(Date dataFiscalizacao3) {
		this.dataFiscalizacao3 = dataFiscalizacao3;
	}
	public String getDescricaoHidrometroLocalInstalacao() {
		return descricaoHidrometroLocalInstalacao;
	}
	public void setDescricaoHidrometroLocalInstalacao(
			String descricaoHidrometroLocalInstalacao) {
		this.descricaoHidrometroLocalInstalacao = descricaoHidrometroLocalInstalacao;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Short getIndicadorTrocaProtecao() {
		return indicadorTrocaProtecao;
	}
	public void setIndicadorTrocaProtecao(Short indicadorTrocaProtecao) {
		this.indicadorTrocaProtecao = indicadorTrocaProtecao;
	}
	public Short getIndicadorTrocaRegistro() {
		return indicadorTrocaRegistro;
	}
	public void setIndicadorTrocaRegistro(Short indicadorTrocaRegistro) {
		this.indicadorTrocaRegistro = indicadorTrocaRegistro;
	}
	public Integer getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
}
