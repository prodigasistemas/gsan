package gcom.gui.atendimentopublico.ordemservico;

import java.util.Date;

import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.util.Util;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<Descrição da classe>>
 * 
 * @author lms
 * @date 26/07/2006
 */
public class InserirTipoRetornoOrdemServicoReferidaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	OsReferidaRetornoTipo osReferidaRetornoTipo = new OsReferidaRetornoTipo();

	String descricao;	
	String abreviatura;
	String servicoTipoReferencia;
	String deferimento;
	String trocaServico;	
	String situacao;
	String atendimentoMotivoEncerramento;
	
	public String getDeferimento() {
		return deferimento;
	}
	public void setDeferimento(String deferimento) {
		this.deferimento = deferimento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAtendimentoMotivoEncerramento() {
		return atendimentoMotivoEncerramento;
	}
	public void setAtendimentoMotivoEncerramento(
			String atendimentoMotivoEncerramento) {
		this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
	}
	public String getServicoTipoReferencia() {
		return servicoTipoReferencia;
	}
	public void setServicoTipoReferencia(String servicoTipoReferencia) {
		this.servicoTipoReferencia = servicoTipoReferencia;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getTrocaServico() {
		return trocaServico;
	}
	public void setTrocaServico(String trocaServico) {
		this.trocaServico = trocaServico;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	
	public OsReferidaRetornoTipo getOsReferidaRetornoTipo() {
		return setFormValues(osReferidaRetornoTipo);
	}
	public void setOsReferidaRetornoTipo(OsReferidaRetornoTipo osReferidaRetornoTipo) {
		this.osReferidaRetornoTipo = osReferidaRetornoTipo;
	}
	
	private OsReferidaRetornoTipo setFormValues(OsReferidaRetornoTipo osReferidaRetornoTipo) {
		
		//descricao
		osReferidaRetornoTipo.setDescricao(getDescricao());
		//abreviatura
		osReferidaRetornoTipo.setDescricaoAbreviada(getAbreviatura());
		//referência tipo serviço
		if (Util.converterStringParaInteger(getServicoTipoReferencia()) != null) {
			ServicoTipoReferencia servicoTipoReferencia = new ServicoTipoReferencia();
			servicoTipoReferencia.setId(Integer.parseInt(getServicoTipoReferencia()));
			osReferidaRetornoTipo.setServicoTipoReferencia(servicoTipoReferencia);
		}
		//indicador deferimento
		if (Util.converterStringParaInteger(getDeferimento()) != null) {
			osReferidaRetornoTipo.setIndicadorDeferimento(Short.parseShort(getDeferimento()));
		}
		//troca serviço
		if (Util.converterStringParaInteger(getTrocaServico()) != null) {
			osReferidaRetornoTipo.setIndicadorTrocaServico(Short.parseShort(getTrocaServico()));
		} else {
			osReferidaRetornoTipo.setIndicadorTrocaServico(new Short("2"));
		}
		//situação
		if (Util.converterStringParaInteger(getSituacao()) != null) {
			osReferidaRetornoTipo.setSituacaoOsReferencia(Util.converterStringParaInteger(getSituacao()).shortValue());
		}
		//motivo de encerramento
		if (Util.validarNumeroMaiorQueZERO(getAtendimentoMotivoEncerramento())) {
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
			atendimentoMotivoEncerramento.setId(Util.converterStringParaInteger(getAtendimentoMotivoEncerramento()));
			osReferidaRetornoTipo.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		}
		//data última alteração
		osReferidaRetornoTipo.setUltimaAlteracao(new Date());
		
		return osReferidaRetornoTipo;
	}
	
}
