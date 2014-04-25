package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<Descrição da classe>>
 * 
 * @author lms
 * @date 14/08/2006
 */
public class GerarOrdemServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private OrdemServico ordemServico = new OrdemServico();
	
	private String idRegistroAtendimento;
	private String forward;
	
	private String idServicoTipo;
	private String descricaoServicoTipo;	
	private String idOrdemServicoReferencia;
	private String descricaoOrdemServicoReferencia;
	private String idServicoTipoReferencia;
	private String descricaoServicoTipoReferencia;
	private String valorServicoOriginal;
	private String valorServicoAtual;
	private String idPrioridadeServicoOriginal;
	private String descricaoPrioridadeServicoOriginal;
	private String idPrioridadeServicoAtual;	
	private String observacao;
	private String tipoSolicitacao;
	
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		
		this.idServicoTipo = null;
		this.descricaoServicoTipo = null;	
		this.idOrdemServicoReferencia = null;
		this.descricaoOrdemServicoReferencia = null;
		this.idServicoTipoReferencia = null;
		this.descricaoServicoTipoReferencia = null;
		this.valorServicoOriginal = null;
		this.valorServicoAtual = null;
		this.idPrioridadeServicoOriginal = null;
		this.descricaoPrioridadeServicoOriginal = null;
		this.idPrioridadeServicoAtual = null;	
		this.observacao = null;
	}	
	
	public void limparTodosCamposForm() {
		this.idRegistroAtendimento = null;
		this.forward = null;
		this.idServicoTipo = null;
		this.descricaoServicoTipo = null;	
		this.idOrdemServicoReferencia = null;
		this.descricaoOrdemServicoReferencia = null;
		this.idServicoTipoReferencia = null;
		this.descricaoServicoTipoReferencia = null;
		this.valorServicoOriginal = null;
		this.valorServicoAtual = null;
		this.idPrioridadeServicoOriginal = null;
		this.descricaoPrioridadeServicoOriginal = null;
		this.idPrioridadeServicoAtual = null;	
		this.observacao = null;
		this.tipoSolicitacao = null;
	}
	
	public String getDescricaoOrdemServicoReferencia() {
		return descricaoOrdemServicoReferencia;
	}
	public void setDescricaoOrdemServicoReferencia(
			String descricaoOrdemServicoReferencia) {
		this.descricaoOrdemServicoReferencia = descricaoOrdemServicoReferencia;
	}
	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}
	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}
	public String getDescricaoServicoTipoReferencia() {
		return descricaoServicoTipoReferencia;
	}
	public void setDescricaoServicoTipoReferencia(
			String descricaoServicoTipoReferencia) {
		this.descricaoServicoTipoReferencia = descricaoServicoTipoReferencia;
	}
	public String getIdOrdemServicoReferencia() {
		return idOrdemServicoReferencia;
	}
	public void setIdOrdemServicoReferencia(String idOrdemServicoReferencia) {
		this.idOrdemServicoReferencia = idOrdemServicoReferencia;
	}
	public String getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}
	public void setIdRegistroAtendimento(String idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}
	public String getIdServicoTipo() {
		return idServicoTipo;
	}
	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}
	public String getIdServicoTipoReferencia() {
		return idServicoTipoReferencia;
	}
	public void setIdServicoTipoReferencia(String idServicoTipoReferencia) {
		this.idServicoTipoReferencia = idServicoTipoReferencia;
	}
	public String getIdPrioridadeServicoAtual() {
		return idPrioridadeServicoAtual;
	}
	public void setIdPrioridadeServicoAtual(String idPrioridadeServicoAtual) {
		this.idPrioridadeServicoAtual = idPrioridadeServicoAtual;
	}
	public String getDescricaoPrioridadeServicoOriginal() {
		return descricaoPrioridadeServicoOriginal;
	}
	public void setDescricaoPrioridadeServicoOriginal(
			String descricaoPrioridadeServicoOriginal) {
		this.descricaoPrioridadeServicoOriginal = descricaoPrioridadeServicoOriginal;
	}
	public String getValorServicoAtual() {
		return valorServicoAtual;
	}
	public void setValorServicoAtual(String valorServicoAtual) {
		this.valorServicoAtual = valorServicoAtual;
	}
	public String getValorServicoOriginal() {
		return valorServicoOriginal;
	}
	public void setValorServicoOriginal(String valorServicoOriginal) {
		this.valorServicoOriginal = valorServicoOriginal;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	public String getIdPrioridadeServicoOriginal() {
		return idPrioridadeServicoOriginal;
	}
	public void setIdPrioridadeServicoOriginal(String idPrioridadeServicoOriginal) {
		this.idPrioridadeServicoOriginal = idPrioridadeServicoOriginal;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	
	public OrdemServico setFormValues(OrdemServico ordemServico) {

		//valor do serviço atual
		if (getValorServicoAtual() != null) {
			try {
				ordemServico.setValorAtual(new BigDecimal(getValorServicoAtual().replace(',','.')));
			} catch (NumberFormatException e) {			
			}
		}
		
		//prioridade do serviço atual
		Integer idPrioridadeServicoAtual = Util.converterStringParaInteger(getIdPrioridadeServicoAtual());
		
		if (Util.validarNumeroMaiorQueZERO(idPrioridadeServicoAtual)) {
			ServicoTipoPrioridade servicoTipoPrioridadeAtual = new ServicoTipoPrioridade();
			servicoTipoPrioridadeAtual.setId(idPrioridadeServicoAtual);
			ordemServico.setServicoTipoPrioridadeAtual(servicoTipoPrioridadeAtual);
		}
		
		if(getObservacao() != null && getObservacao().trim().equals("")){
			this.setObservacao(null);
		}

		//observacao
		ordemServico.setObservacao(this.getObservacao());
		
		//data última alteração
		ordemServico.setUltimaAlteracao(new Date());
		
		
		
        ordemServico.setServicoTipo(ordemServico.getServicoTipo());
		
        ordemServico.setIndicadorEncerramentoAutomatico(ConstantesSistema.NAO);
        
		return ordemServico;
	}

	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}
	
	
	
}
