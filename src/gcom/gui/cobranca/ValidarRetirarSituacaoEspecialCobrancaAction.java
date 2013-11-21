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
package gcom.gui.cobranca;

import java.util.Collection;

import gcom.cadastro.imovel.Categoria;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ValidarRetirarSituacaoEspecialCobrancaAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("telaSucesso");

		SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm = (SituacaoEspecialCobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		
		Collection pesquisarImoveisParaSerRetirados = (Collection) sessao.getAttribute("COMSituacaoEspecialCobranca");
		
		fachada.retirarSituacaoEspecialCobranca(transferirActionFormParaHelper(situacaoEspecialCobrancaActionForm,usuarioLogado), pesquisarImoveisParaSerRetirados, usuarioLogado);

		
		montarPaginaSucesso(
				httpServletRequest,
				situacaoEspecialCobrancaActionForm.getQuantidadeImoveisCOMSituacaoEspecialCobranca()
						+ " imóvel(eis) retirado(s) da Situação Especial de Cobrança com sucesso.",
				"Realizar outra Manutenção de Situação Especial de Cobrança",
				"exibirSituacaoEspecialCobrancaInformarAction.do?menu=sim");

		
		situacaoEspecialCobrancaActionForm.reset(actionMapping, httpServletRequest);
		
		return retorno;
	}


	
	private SituacaoEspecialCobrancaHelper transferirActionFormParaHelper(
			SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm,
			Usuario usuarioLogado) {

		SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper = new SituacaoEspecialCobrancaHelper();

		situacaoEspecialCobrancaHelper
				.setIdImovel(situacaoEspecialCobrancaActionForm
						.getIdImovel() == null ? ""
						: situacaoEspecialCobrancaActionForm.getIdImovel());

		situacaoEspecialCobrancaHelper
				.setInscricaoTipo(situacaoEspecialCobrancaActionForm
						.getInscricaoTipo() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getInscricaoTipo());

		situacaoEspecialCobrancaHelper
				.setLoteDestino(situacaoEspecialCobrancaActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getLoteDestino());

		situacaoEspecialCobrancaHelper
				.setQuadraDestinoNM(situacaoEspecialCobrancaActionForm
						.getQuadraDestinoNM() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraDestinoNM());

		situacaoEspecialCobrancaHelper
				.setLoteOrigem(situacaoEspecialCobrancaActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm.getLoteOrigem());

		situacaoEspecialCobrancaHelper
				.setNomeLocalidadeOrigem(situacaoEspecialCobrancaActionForm
						.getNomeLocalidadeOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getNomeLocalidadeOrigem());

		situacaoEspecialCobrancaHelper
				.setNomeSetorComercialOrigem(situacaoEspecialCobrancaActionForm
						.getNomeSetorComercialOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getNomeSetorComercialOrigem());

		situacaoEspecialCobrancaHelper
				.setQuadraOrigemNM(situacaoEspecialCobrancaActionForm
						.getQuadraOrigemNM() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraOrigemNM());

		situacaoEspecialCobrancaHelper
				.setQuadraMensagemOrigem(situacaoEspecialCobrancaActionForm
						.getQuadraMensagemOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraMensagemOrigem());

		situacaoEspecialCobrancaHelper
				.setNomeLocalidadeDestino(situacaoEspecialCobrancaActionForm
						.getNomeLocalidadeDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getNomeLocalidadeDestino());

		situacaoEspecialCobrancaHelper
				.setSetorComercialDestinoCD(situacaoEspecialCobrancaActionForm
						.getSetorComercialDestinoCD() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSetorComercialDestinoCD());

		situacaoEspecialCobrancaHelper
				.setSetorComercialOrigemCD(situacaoEspecialCobrancaActionForm
						.getSetorComercialOrigemCD() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSetorComercialOrigemCD());

		situacaoEspecialCobrancaHelper
				.setSetorComercialOrigemID(situacaoEspecialCobrancaActionForm
						.getSetorComercialOrigemID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSetorComercialOrigemID());

		situacaoEspecialCobrancaHelper
				.setQuadraOrigemID(situacaoEspecialCobrancaActionForm
						.getQuadraOrigemID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraOrigemID());

		situacaoEspecialCobrancaHelper
				.setLocalidadeDestinoID(situacaoEspecialCobrancaActionForm
						.getLocalidadeDestinoID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getLocalidadeDestinoID());

		situacaoEspecialCobrancaHelper
				.setLocalidadeOrigemID(situacaoEspecialCobrancaActionForm
						.getLocalidadeOrigemID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getLocalidadeOrigemID());

		situacaoEspecialCobrancaHelper
				.setNomeSetorComercialDestino(situacaoEspecialCobrancaActionForm
						.getNomeSetorComercialDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getNomeSetorComercialDestino());

		situacaoEspecialCobrancaHelper
				.setSetorComercialDestinoID(situacaoEspecialCobrancaActionForm
						.getSetorComercialDestinoID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSetorComercialDestinoID());

		situacaoEspecialCobrancaHelper
				.setQuadraMensagemDestino(situacaoEspecialCobrancaActionForm
						.getQuadraMensagemDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraMensagemDestino());

		situacaoEspecialCobrancaHelper
				.setQuadraDestinoID(situacaoEspecialCobrancaActionForm
						.getQuadraDestinoID() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuadraDestinoID());

		situacaoEspecialCobrancaHelper
				.setTipoSituacaoEspecialCobranca(situacaoEspecialCobrancaActionForm
						.getTipoSituacaoEspecialCobranca() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getTipoSituacaoEspecialCobranca());

		situacaoEspecialCobrancaHelper
				.setLoteOrigem(situacaoEspecialCobrancaActionForm
						.getLoteOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm.getLoteOrigem());

		situacaoEspecialCobrancaHelper
				.setLoteDestino(situacaoEspecialCobrancaActionForm
						.getLoteDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getLoteDestino());

		situacaoEspecialCobrancaHelper
				.setSubloteOrigem(situacaoEspecialCobrancaActionForm
						.getSubloteOrigem() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSubloteOrigem());

		situacaoEspecialCobrancaHelper
				.setSubloteDestino(situacaoEspecialCobrancaActionForm
						.getSubloteDestino() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getSubloteDestino());

		situacaoEspecialCobrancaHelper
				.setIdCobrancaSituacaoMotivo(situacaoEspecialCobrancaActionForm
						.getIdCobrancaSituacaoMotivo() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getIdCobrancaSituacaoMotivo());

		situacaoEspecialCobrancaHelper
				.setIdCobrancaSituacaoTipo(situacaoEspecialCobrancaActionForm
						.getIdCobrancaSituacaoTipo() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getIdCobrancaSituacaoTipo());

		situacaoEspecialCobrancaHelper
				.setMesAnoReferenciaCobrancaInicial(situacaoEspecialCobrancaActionForm
						.getMesAnoReferenciaCobrancaInicial() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getMesAnoReferenciaCobrancaInicial());

		situacaoEspecialCobrancaHelper
				.setMesAnoReferenciaCobrancaFinal(situacaoEspecialCobrancaActionForm
						.getMesAnoReferenciaCobrancaFinal() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getMesAnoReferenciaCobrancaFinal());

		situacaoEspecialCobrancaHelper
				.setQuantidadeImoveisCOMSituacaoEspecialCobranca(situacaoEspecialCobrancaActionForm
						.getQuantidadeImoveisCOMSituacaoEspecialCobranca() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuantidadeImoveisCOMSituacaoEspecialCobranca());

		situacaoEspecialCobrancaHelper
				.setQuantidadeImoveisSEMSituacaoEspecialCobranca(situacaoEspecialCobrancaActionForm
						.getQuantidadeImoveisSEMSituacaoEspecialCobranca() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuantidadeImoveisSEMSituacaoEspecialCobranca());

		situacaoEspecialCobrancaHelper
				.setQuantidadeImoveisAtualizados(situacaoEspecialCobrancaActionForm
						.getQuantidadeImoveisAtualizados() == null ? ""
						: situacaoEspecialCobrancaActionForm
								.getQuantidadeImoveisAtualizados());
		
		situacaoEspecialCobrancaHelper
		.setCodigoRotaInicial(situacaoEspecialCobrancaActionForm
				.getCdRotaInicial() == null ? ""
				: situacaoEspecialCobrancaActionForm.getCdRotaInicial());
		
		situacaoEspecialCobrancaHelper
		.setCodigoRotaFinal(situacaoEspecialCobrancaActionForm
				.getCdRotaFinal() == null ? ""
				: situacaoEspecialCobrancaActionForm.getCdRotaFinal());
		
		situacaoEspecialCobrancaHelper
		.setSequencialRotaInicial(situacaoEspecialCobrancaActionForm
				.getSequencialRotaInicial() == null ? ""
				: situacaoEspecialCobrancaActionForm.getSequencialRotaInicial());
		
		situacaoEspecialCobrancaHelper
		.setSequencialRotaFinal(situacaoEspecialCobrancaActionForm
				.getSequencialRotaFinal() == null ? ""
				: situacaoEspecialCobrancaActionForm.getSequencialRotaFinal());
		
		//Colocado por Raphael Rossiter em 11/08/2008 - Analista:Rosana Carvalho
		if (situacaoEspecialCobrancaActionForm.getObservacaoRetira() != null &&
			!situacaoEspecialCobrancaActionForm.getObservacaoRetira().equals("")){
						
			situacaoEspecialCobrancaHelper.setObservacaoRetira(
			situacaoEspecialCobrancaActionForm.getObservacaoRetira());
		}
		
		situacaoEspecialCobrancaHelper.setIdUsuarioRetira(usuarioLogado.getId().toString());
		
		
		
		situacaoEspecialCobrancaHelper.setIdsCategoria(situacaoEspecialCobrancaActionForm.getIdsCategoria());
		
		if (situacaoEspecialCobrancaActionForm.getIdsCategoria() != null) {
			
			String [] idsCategoria = situacaoEspecialCobrancaActionForm.getIdsCategoria();
			
			for (int i = 0; i < idsCategoria.length; i++) {
				
				if (idsCategoria[i].equals(Categoria.COMERCIAL.toString())) {
					situacaoEspecialCobrancaHelper.setIndicadorComercial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.INDUSTRIAL.toString())) {
					situacaoEspecialCobrancaHelper.setIndicadorIndustrial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.RESIDENCIAL.toString())) {
					situacaoEspecialCobrancaHelper.setIndicadorResidencial(ConstantesSistema.SIM.toString());
				} else if (idsCategoria[i].equals(Categoria.PUBLICO.toString())) {
					situacaoEspecialCobrancaHelper.setIndicadorPublico(ConstantesSistema.SIM.toString());
				}
				
			}
		} 
		
		Integer quantidadeImoveisCom = 0;
		
		if (situacaoEspecialCobrancaActionForm.getQuantidadeImoveisCOMSituacaoEspecialCobranca()!=null 
				&& !situacaoEspecialCobrancaActionForm.getQuantidadeImoveisCOMSituacaoEspecialCobranca().equals("")){
			
			quantidadeImoveisCom =new Integer(situacaoEspecialCobrancaActionForm.getQuantidadeImoveisCOMSituacaoEspecialCobranca());
			
			situacaoEspecialCobrancaHelper.setQuantidadeImoveisAtualizados(quantidadeImoveisCom.toString());
		}
		
		return situacaoEspecialCobrancaHelper;
	}
}
