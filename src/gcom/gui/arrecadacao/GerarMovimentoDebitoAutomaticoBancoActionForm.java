package gcom.gui.arrecadacao;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de gerar movimento
 * de débito automático para o banco
 * 
 * @author Sávio Luiz
 * @date 17/04/2006
 */
public class GerarMovimentoDebitoAutomaticoBancoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String opcaoMovimentoDebitoAutomatico;

	private String idGrupoFaturamento;
	
	private String idGrupoFaturamentoSelecionados;

	private String mesAnoFaturamento;

	private String opcaoEnvioParaBanco;
	
	private String idArrecadadorMovimento;

	private String codigoBancoMovimento;

	private String codigoRemessaMovimento;

	private String identificacaoServicoMovimento;

	private String numeroSequencialMovimento;
	
	private String[] idsCodigosBancos;
	
	private Date dataAtual;

	public String getCodigoBancoMovimento() {
		return codigoBancoMovimento;
	}

	public void setCodigoBancoMovimento(String codigoBancoMovimento) {
		this.codigoBancoMovimento = codigoBancoMovimento;
	}

	public String getCodigoRemessaMovimento() {
		return codigoRemessaMovimento;
	}

	public void setCodigoRemessaMovimento(String codigoRemessaMovimento) {
		this.codigoRemessaMovimento = codigoRemessaMovimento;
	}

	public String getIdentificacaoServicoMovimento() {
		return identificacaoServicoMovimento;
	}

	public void setIdentificacaoServicoMovimento(
			String identificacaoServicoMovimento) {
		this.identificacaoServicoMovimento = identificacaoServicoMovimento;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	
	public String getNumeroSequencialMovimento() {
		return numeroSequencialMovimento;
	}

	public void setNumeroSequencialMovimento(String numeroSequencialMovimento) {
		this.numeroSequencialMovimento = numeroSequencialMovimento;
	}

	public String getOpcaoEnvioParaBanco() {
		return opcaoEnvioParaBanco;
	}

	public void setOpcaoEnvioParaBanco(String opcaoEnvioParaBanco) {
		this.opcaoEnvioParaBanco = opcaoEnvioParaBanco;
	}

	public String getOpcaoMovimentoDebitoAutomatico() {
		return opcaoMovimentoDebitoAutomatico;
	}

	public void setOpcaoMovimentoDebitoAutomatico(
			String opcaoMovimentoDebitoAutomatico) {
		this.opcaoMovimentoDebitoAutomatico = opcaoMovimentoDebitoAutomatico;
	}
	
	

	public String[] getIdsCodigosBancos() {
		return idsCodigosBancos;
	}

	public void setIdsCodigosBancos(String[] idsCodigosBancos) {
		this.idsCodigosBancos = idsCodigosBancos;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		opcaoMovimentoDebitoAutomatico = null;
		idGrupoFaturamento = "";
		mesAnoFaturamento = null;
		opcaoEnvioParaBanco = null;
		codigoBancoMovimento = null;
		codigoRemessaMovimento = null;
		identificacaoServicoMovimento = null;
		numeroSequencialMovimento = null;
		idsCodigosBancos = null;
	}

	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public String getIdArrecadadorMovimento() {
		return idArrecadadorMovimento;
	}

	public void setIdArrecadadorMovimento(String idArrecadadorMovimento) {
		this.idArrecadadorMovimento = idArrecadadorMovimento;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getIdGrupoFaturamentoSelecionados() {
		return idGrupoFaturamentoSelecionados;
	}

	public void setIdGrupoFaturamentoSelecionados(
			String idGrupoFaturamentoSelecionados) {
		this.idGrupoFaturamentoSelecionados = idGrupoFaturamentoSelecionados;
	}

}
