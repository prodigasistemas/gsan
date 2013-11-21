/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
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
