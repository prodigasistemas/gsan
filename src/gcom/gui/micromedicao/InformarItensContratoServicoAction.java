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
package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.ContratoEmpresaAditivo;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.InformarItensContratoServicoHelper;
import gcom.micromedicao.ItemServicoContrato;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1055] Informar Valor de Item de Servico Por Contrato
 * 
 * @author Hugo Leonardo, Mariana Victor
 * @date 26/07/2010, 23/11/2010
 */

public class InformarItensContratoServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ExibirInformarItensContratoServicoActionForm form = 
			(ExibirInformarItensContratoServicoActionForm) actionForm;
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		List colecaoHelper = (List) sessao.getAttribute("colecaoHelper");
		
		if(form.getIdNumeroContrato() != null && !form.getIdNumeroContrato().equals("")
				&& ((form.getDtFimContrato() != null && !form.getDtFimContrato().equals(""))
						|| (form.getValorGlobalContrato() != null && !form.getValorGlobalContrato().equals(""))
						|| (form.getPercentualTaxaSucesso() != null && !form.getPercentualTaxaSucesso().equals("")))){
			
			Integer posicaoComponente = new Integer(form.getIdNumeroContrato());
			
			InformarItensContratoServicoHelper helper = null;
		
			helper = (InformarItensContratoServicoHelper) colecaoHelper.get(posicaoComponente-1);
			
			ContratoEmpresaServico contratoEmpresaServico = helper.getContratoEmpresaServico();
			if (form.getDtFimContrato() != null && !form.getDtFimContrato().equals("")) {
				contratoEmpresaServico.setDataFimContrato(Util.converteStringParaDate(form.getDtFimContrato()));
			}

			if (form.getValorGlobalContrato() != null && !form.getValorGlobalContrato().equals("")) {
	    		BigDecimal igualZero = new BigDecimal(0);
				
				BigDecimal valorGlobalContratoAtual = null;
	    		String valorGlobalContrato = form.getValorGlobalContrato()
							.toString().replace(".", "");
	    		
	    		valorGlobalContrato = valorGlobalContrato.replace(",", ".");
	    		valorGlobalContratoAtual = new BigDecimal(valorGlobalContrato);
	    		
				
	    		if (valorGlobalContratoAtual.compareTo(igualZero) <= -1){
	    			throw new ActionServletException("atencao.invalido_zero", null ,"O Valor Global do Contrato" );	
	    		}
	    		contratoEmpresaServico.setValorGlobalContrato(valorGlobalContratoAtual);
			}
			
			if (form.getPercentualTaxaSucesso() != null
					&& !form.getPercentualTaxaSucesso()
							.equals("")) {
				
				BigDecimal percentualTaxaSucessoAtual = new BigDecimal("0.00");

				String percentualTaxaSucesso = form.getPercentualTaxaSucesso()
						.toString().replace(".", "");
				
				percentualTaxaSucesso = percentualTaxaSucesso.replace(",", "");
				
				percentualTaxaSucessoAtual = Util
					.formatarMoedaRealparaBigDecimalComUltimos2CamposDecimais(percentualTaxaSucesso);

				contratoEmpresaServico
						.setPercentualTaxaSucesso(percentualTaxaSucessoAtual);

				
			} else {
				contratoEmpresaServico
						.setPercentualTaxaSucesso(null);
			}
			
			if (form.getObservacao() != null
					&& !form.getObservacao()
							.equals("")) {
				
				contratoEmpresaServico
						.setObservacao(form.getObservacao());

			}
			
			List listItemServico = helper.getItemServicoContrato();
			
			colecaoHelper.remove(posicaoComponente-1);
			
			helper.setContratoEmpresaServico(contratoEmpresaServico);
			helper.setItemServicoContrato(listItemServico);
			
			colecaoHelper.add(helper);
		}
		
		List colecaoItemServicoContrato = null;
		List colecaoAditivo = null;
		InformarItensContratoServicoHelper helper = null;
		ContratoEmpresaServico contratoEmpresaServico = null;
		ItemServicoContrato itemServicoContrato = null;
		ContratoEmpresaAditivo contratoEmpresaAditivo = null;
		Integer idContratoEmpresaServico = null;
		String ids = "";
		Iterator iterator = null;
		
		iterator = colecaoHelper.iterator();
		
		while (iterator.hasNext()) {
			
			helper = (InformarItensContratoServicoHelper) iterator.next();
			
			contratoEmpresaServico = helper.getContratoEmpresaServico();
			contratoEmpresaServico.setUltimaAlteracao(new Date());
			
			// Caso seja um novo Contrato.
			if(contratoEmpresaServico.getId() == null){
				
				// ------------ REGISTRAR TRANSAÇÃO ----------------
				RegistradorOperacao registradorOperacaoContratoEmpresaServico = new RegistradorOperacao(
						Operacao.OPERACAO_INFORMAR_ITEM_SERVICO_CONTRATO, contratoEmpresaServico.getId(),
						contratoEmpresaServico.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
				// ------------ REGISTRAR TRANSAÇÃO ----------------
				
				registradorOperacaoContratoEmpresaServico.registrarOperacao(contratoEmpresaServico);
				
				idContratoEmpresaServico = (Integer) fachada.inserir(contratoEmpresaServico);
				
				ids += contratoEmpresaServico.getDescricaoContrato() + ", ";
				
				contratoEmpresaServico.setId(idContratoEmpresaServico);
				
				colecaoItemServicoContrato = helper.getItemServicoContrato();
				
				if(colecaoItemServicoContrato != null && (colecaoItemServicoContrato.size() > 0)){
					
					Iterator iteratorItens = colecaoItemServicoContrato.iterator();
					
					while (iteratorItens.hasNext()) {
						itemServicoContrato = (ItemServicoContrato) iteratorItens.next();
						
						itemServicoContrato.setId(null);
						itemServicoContrato.setContratoEmpresaServico(contratoEmpresaServico);
						itemServicoContrato.setUltimaAlteracao( new Date());
						
						// ------------ REGISTRAR TRANSAÇÃO ----------------
						RegistradorOperacao registradorOperacaoItemServicoContrato = new RegistradorOperacao(
								Operacao.OPERACAO_INFORMAR_ITEM_SERVICO_CONTRATO, itemServicoContrato.getId(),
								itemServicoContrato.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
						// ------------ REGISTRAR TRANSAÇÃO ----------------
						
						registradorOperacaoItemServicoContrato.registrarOperacao(itemServicoContrato);
						
						fachada.inserir(itemServicoContrato);
					}
				}
				colecaoAditivo = helper.getContratoEmpresaAditivo();
				if (colecaoAditivo != null && (colecaoAditivo.size() > 0)) {
					Iterator iteratorAditivos = colecaoAditivo.iterator();
					
					while (iteratorAditivos.hasNext()) {
						contratoEmpresaAditivo = (ContratoEmpresaAditivo) iteratorAditivos.next();
						contratoEmpresaAditivo.setId(null);
						contratoEmpresaAditivo.setContratoEmpresaServico(contratoEmpresaServico);
						contratoEmpresaAditivo.setUltimaAlteracao( new Date());
						
		            	fachada.inserir(contratoEmpresaAditivo);
					}
					
				}
				
			// Caso o Contrato já exista.
			}else{
				
				// ------------ REGISTRAR TRANSAÇÃO ----------------
				RegistradorOperacao registradorOperacaoContratoEmpresaServico = new RegistradorOperacao(
						Operacao.OPERACAO_INFORMAR_ITEM_SERVICO_CONTRATO, contratoEmpresaServico.getId(),
						contratoEmpresaServico.getId(), new UsuarioAcaoUsuarioHelper(usuarioLogado,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
				// ------------ REGISTRAR TRANSAÇÃO ----------------
				registradorOperacaoContratoEmpresaServico.registrarOperacao(contratoEmpresaServico);
				
				fachada.atualizar( contratoEmpresaServico);
				
				// Remover todos os Item Serviço Contrato do Contrato.
				idContratoEmpresaServico = contratoEmpresaServico.getId();
				
				ids += contratoEmpresaServico.getDescricaoContrato() + ", ";
				
				fachada.removerItemServicoDoContrato(idContratoEmpresaServico, usuarioLogado);
				
				colecaoItemServicoContrato = helper.getItemServicoContrato();
				
				if(colecaoItemServicoContrato != null && (colecaoItemServicoContrato.size() > 0)){
					
					Iterator iteratorItens = colecaoItemServicoContrato.iterator();
					
					while (iteratorItens.hasNext()) {
						itemServicoContrato = (ItemServicoContrato) iteratorItens.next();
						
						itemServicoContrato.setContratoEmpresaServico(contratoEmpresaServico);
						itemServicoContrato.setUltimaAlteracao( new Date());
						
						// ------------ REGISTRAR TRANSAÇÃO ----------------
						RegistradorOperacao registradorOperacaoItemServicoContrato = new RegistradorOperacao(
								Operacao.OPERACAO_INFORMAR_ITEM_SERVICO_CONTRATO, 
								itemServicoContrato.getContratoEmpresaServico().getId(),
								itemServicoContrato.getContratoEmpresaServico().getId(), 
								new UsuarioAcaoUsuarioHelper(usuarioLogado,
								UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
						// ------------ REGISTRAR TRANSAÇÃO ----------------
						registradorOperacaoItemServicoContrato.registrarOperacao(itemServicoContrato);
						
						fachada.inserir(itemServicoContrato);
					}
				}
				

				fachada.removerAditivosDoContrato(idContratoEmpresaServico);
				
				colecaoAditivo = helper.getContratoEmpresaAditivo();
				
				if(colecaoAditivo != null && (colecaoAditivo.size() > 0)){
					
					Iterator iteratorItens = colecaoAditivo.iterator();
					
					while (iteratorItens.hasNext()) {
						contratoEmpresaAditivo = (ContratoEmpresaAditivo) iteratorItens.next();
						
						contratoEmpresaAditivo.setContratoEmpresaServico(contratoEmpresaServico);
						contratoEmpresaAditivo.setUltimaAlteracao( new Date());

						fachada.inserir(contratoEmpresaAditivo);
					}
				}
			}
		}
		
		ids = Util.removerUltimosCaracteres(ids, 2);
		
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Contrato(s) Empresa Serviço: "
				+ ids
				+ " atualizado(s) com sucesso.",
				"Realizar outra Manutenção de Contrato Empresa Serviço",
				"exibirInformarItensContratoServicoAction.do?menu=sim");

		sessao.removeAttribute("colecaoItemServicoContratoSelecionados");
		sessao.removeAttribute("colecaoAditivo");
		sessao.removeAttribute("colecaoEmpresa");
		sessao.removeAttribute("colecaoItemServico");
		sessao.removeAttribute("colecaoHelper");
		sessao.removeAttribute("contratoSelecionado");
		sessao.removeAttribute("dataFimContrato");
		sessao.removeAttribute("colecaoEmpresa");

		return retorno;
	}

}
