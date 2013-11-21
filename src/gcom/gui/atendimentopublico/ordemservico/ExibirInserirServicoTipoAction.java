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

import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoSubgrupo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.FachadaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<Descrição da Classe>>
 * 
 * @author lms
 * @date 26/07/2006
 */
public class ExibirInserirServicoTipoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um serviço tipo.
	 * 
	 * [UC0410] Inserir Serviço Tipo
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("inserirServicoTipo");		
		InserirServicoTipoActionForm form = (InserirServicoTipoActionForm) actionForm;		
		Fachada fachada = Fachada.getInstancia();		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if ("reset".equalsIgnoreCase(httpServletRequest.getParameter("action"))) {
			form.reset();
			sessao.removeAttribute("motivosEncerramentoEscolhidos");
		}

		
		if(sessao.getAttribute("motivosEncerramentoEscolhidos") != null){
			Collection colecaoAtendimentoMotivosEncerramento = (Collection)sessao.getAttribute("motivosEncerramentoEscolhidos");
			form.setServicoTipoMotivoEncerramento(colecaoAtendimentoMotivosEncerramento);
		}
        //Constrói filtro para pesquisa dos serviços tipo subgrupo
		FiltroServicoTipoSubgrupo filtroServicoTipoSubgrupo = new FiltroServicoTipoSubgrupo();
		filtroServicoTipoSubgrupo.setCampoOrderBy(FiltroServicoTipoReferencia.DESCRICAO);
		filtroServicoTipoSubgrupo.adicionarParametro(new ParametroSimples(FiltroServicoTipoSubgrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
		
		Collection colecaoSubgrupo = fachada.pesquisar(filtroServicoTipoSubgrupo, ServicoTipoSubgrupo.class.getName());
		
		sessao.setAttribute("colecaoSubgrupo", colecaoSubgrupo);
		
        //Constrói filtro para pesquisa dos creditos tipo
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		filtroCreditoTipo.setCampoOrderBy(FiltroCreditoTipo.DESCRICAO);
		filtroCreditoTipo.adicionarParametro(new ParametroSimples(FiltroCreditoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
		
		Collection colecaoTipoCredito = fachada.pesquisar(filtroCreditoTipo, CreditoTipo.class.getName());
		
		sessao.setAttribute("colecaoTipoCredito", colecaoTipoCredito);
		
        //Constrói filtro para pesquisa das prioridades serviço
		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();
		filtroServicoTipoPrioridade.setCampoOrderBy(FiltroCreditoTipo.DESCRICAO);
		filtroServicoTipoPrioridade.adicionarParametro(new ParametroSimples(FiltroCreditoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
		
		Collection colecaoPrioridadeServico = fachada.pesquisar(filtroServicoTipoPrioridade, ServicoTipoPrioridade.class.getName());
		
		sessao.setAttribute("colecaoPrioridadeServico", colecaoPrioridadeServico);
		
		ServicoTipo servicoTipo = form.getServicoTipo();
		
		//Tipo de Débito		
		Integer idDebitoTipo = Util.converterStringParaInteger(form.getIdTipoDebito());
		
		if (Util.validarNumeroMaiorQueZERO(idDebitoTipo)) {
			try {
				DebitoTipo debitoTipo = fachada.pesquisarDebitoTipo(idDebitoTipo);
				servicoTipo.setDebitoTipo(debitoTipo);
				form.setIdTipoDebito(debitoTipo.getId().toString());
				form.setDescricaoTipoDebito(debitoTipo.getDescricao());
				form.setTipoCredito(null);
			} catch (FachadaException e) {
				servicoTipo.setDebitoTipo(null);
				form.setDescricaoTipoDebito("Tipo de Débito Inexistente");
			}
		} else {
			servicoTipo.setDebitoTipo(null);
		}
		
		if(form.getTipoCredito() != null && Integer.parseInt(form.getTipoCredito()) != ConstantesSistema.NUMERO_NAO_INFORMADO){
			httpServletRequest.setAttribute("desabilitaCredito", "desabilitaCredito");			
		}
		//Perfil do Serviço
		
		Integer idServicoPerfilTipo = Util.converterStringParaInteger(form.getIdPerfilServico());
		
		if (Util.validarNumeroMaiorQueZERO(idServicoPerfilTipo)) {
			try {
				ServicoPerfilTipo servicoPerfilTipo = fachada.pesquisarServicoPerfilTipo(idServicoPerfilTipo);
				servicoTipo.setServicoPerfilTipo(servicoPerfilTipo);
				form.setIdPerfilServico(servicoPerfilTipo.getId().toString());
				form.setDescricaoPerfilServico(servicoPerfilTipo.getDescricao());
			} catch (FachadaException e) {
				servicoTipo.setServicoPerfilTipo(null);
				form.setDescricaoPerfilServico("Tipo do Perfil Inexistente");
			}
		}
		
		//Tipo do Serviço Referência
		
		Integer idServicoTipoReferencia = Util.converterStringParaInteger(form.getIdTipoServicoReferencia());
		
		if (Util.validarNumeroMaiorQueZERO(idServicoTipoReferencia)) {
			try {
				ServicoTipoReferencia servicoTipoReferencia = fachada.pesquisarServicoTipoReferencia(idServicoTipoReferencia);
				servicoTipo.setServicoTipoReferencia(servicoTipoReferencia);
				form.setIdTipoServicoReferencia(servicoTipoReferencia.getId().toString());
				form.setDescricaoTipoServicoReferencia(servicoTipoReferencia.getDescricao());
			} catch (FachadaException e) {
				servicoTipo.setServicoTipoReferencia(null);
				form.setDescricaoTipoServicoReferencia("Tipo de Serviço de Referência Inexistente");
			}
		}
		
		if("removeRowTableServicoTipoReferencia".equals(form.getMethod())){
			sessao.removeAttribute("servicoTipoReferencia");
			form.setMethod("");
		}
		
		if ("addServicoTipoAtividade".equals(form.getMethod())) {
			form.addServicoTipoAtividade();
			form.setMethod(null);
		} 
		
		if ("removeServicoTipoAtividade".equals(form.getMethod())) {
			form.removeServicoTipoAtividade();
		}
		
		if ("addServicoTipoMaterial".equals(form.getMethod())) {
			form.addServicoTipoMaterial();
		} 
		
		if ("removeServicoTipoMaterial".equals(form.getMethod())) {
			form.removeServicoTipoMaterial();
		}
		
		if ("removeAllServicoTipoAtividade".equals(form.getMethod())) {
			form.removeAllServicoTipoAtividade();
		}
		
		if ("removeServicoTipoMotivoEncerramento".equals(form.getMethod())) {
			form.removeServicoTipoMotivoEncerramento();
		}
		
		//caso não tenha valor seta para o valor de não
		if(form.getIndicadorPermiteAlterarValor() == null || form.getIndicadorPermiteAlterarValor().equals("")){
			form.setIndicadorPermiteAlterarValor("2");
		}
		if(form.getIndicativoObrigatoriedadeAtividade() == null || form.getIndicativoObrigatoriedadeAtividade().equals("")){
			form.setIndicativoObrigatoriedadeAtividade("2");
			form.setIndicativoObrigatoriedadeAtividadeValor("2");
		} else {

			form.setIndicativoObrigatoriedadeAtividade(form.getIndicativoObrigatoriedadeAtividadeValor());
			
		} 
		
		
		form.setIndicadorProgramacaoAutomatica(form.getIndicadorProgramacaoAutomaticaValor());
			
		
		if(form.getIndicadorEncAutomaticoRAQndEncOS() == null || form.getIndicadorEncAutomaticoRAQndEncOS().equals("")){
			form.setIndicadorEncAutomaticoRAQndEncOS("2");
		}
		
		httpServletRequest.setAttribute("servicoTipo", servicoTipo);
		sessao.setAttribute("colecaoServicoTipoAtividade", form.getServicoTipoAtividades());
		sessao.setAttribute("colecaoServicoTipoMaterial", form.getServicoTipoMateriais());	
		sessao.setAttribute("motivosEncerramentoEscolhidos", form.getServicoTipoMotivoEncerramento());
		
		return retorno;
	}

}