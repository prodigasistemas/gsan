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
package gcom.gui.faturamento.conta;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInserirMensagemContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirMensagemContaAction");

		Fachada fachada = Fachada.getInstancia();
				
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
		filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
		Collection colecaoFaturamentoGrupo = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
		
		
		sessao.setAttribute("colecaoFaturamentoGrupo", colecaoFaturamentoGrupo);
		sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		
		InserirMensagemContaActionForm inserirMensagemContaActionForm = (InserirMensagemContaActionForm) actionForm;
		
		String idLocalidade = (String) httpServletRequest.getParameter("localidade");
		
		String idGerenciaRegional = (String) httpServletRequest.getParameter("gerenciaRegional");
		
		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			
			if (idGerenciaRegional != null && !"".equalsIgnoreCase(idGerenciaRegional)){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_GERENCIA, idGerenciaRegional));
				filtroGerenciaRegional.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
			}
			
			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) colecaoLocalidade.iterator().next();
				inserirMensagemContaActionForm.setLocalidadeDescricao(localidade.getDescricao());
				//httpServletRequest.setAttribute("localidadeInexistente", false);
				
				
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
				Collection colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				
				httpServletRequest.setAttribute("colecaoSetorComercialPorLocalidade",colecaoSetorComercial);
				
				 //Remover o Contrato Tarifa da Colecao
		        if ( httpServletRequest.getParameter("acao") != null && 
		        	httpServletRequest.getParameter("acao").equals("quadra") ) {
		        	Integer idSetorComercial = new Integer(httpServletRequest.getParameter("id")).intValue();
		        	
		        	//Quadra
					FiltroQuadra filtroQuadra = new FiltroQuadra();
					filtroQuadra.setCampoOrderBy(FiltroQuadra.NUMERO_QUADRA);
					filtroQuadra.adicionarParametro( new ParametroSimples( FiltroQuadra.ID_SETORCOMERCIAL, idSetorComercial));
					Collection colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
					
					httpServletRequest.setAttribute("colecaoQuadraPorLocalidade", colecaoQuadra);
					
		        }
				
				
			} else {
				
				inserirMensagemContaActionForm.setLocalidade("");
				inserirMensagemContaActionForm.setLocalidadeDescricao("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("localidadeInexistente", true);
				
				httpServletRequest.removeAttribute("colecaoSetorComercialPorLocalidade");
				httpServletRequest.removeAttribute("colecaoQuadraPorLocalidade");
			}
		
			httpServletRequest.setAttribute("identificadorPesquisa", true);
		}
		
		
		//Obter os setores comerciais a partir da qualidade de água
		String qualidadeAguaTipoArgumento = (String) httpServletRequest.getParameter("qualidadeAgua");
		String indiceInicial = inserirMensagemContaActionForm.getIndiceInicial();
		String indiceFinal = inserirMensagemContaActionForm.getIndiceFinal();
		
		if (qualidadeAguaTipoArgumento != null && !qualidadeAguaTipoArgumento.trim().equals("") &&
			indiceInicial != null && !indiceInicial.trim().equals("") &&
			indiceFinal != null && !indiceFinal.trim().equals("")) {
			
			int tipoArgumento = (new Integer(qualidadeAguaTipoArgumento)).intValue();
			indiceInicial = (indiceInicial.replace(".", "")).replace(",", ".");
			indiceFinal = (indiceFinal.replace(".", "")).replace(",", ".");
			
			Integer anoMesReferencia = 
			Util.formatarMesAnoComBarraParaAnoMes(inserirMensagemContaActionForm.getReferenciaFaturamento());
			
			BigDecimal indiceInicio = new BigDecimal(indiceInicial);
			BigDecimal indiceFim = new BigDecimal(indiceFinal);
			
			Collection colecaoSetorComercial = fachada.pesquisarSetorComercialPorQualidadeAgua(tipoArgumento, 
			indiceInicio, indiceFim, anoMesReferencia);
			
			httpServletRequest.setAttribute("colecaoSetorComercialPorQualidade",colecaoSetorComercial);
		}
		
		 
		
//		String idSetorComercial = inserirMensagemContaActionForm.getSetorComercial();
//		
//		if (idSetorComercial != null && !idSetorComercial.trim().equals("")){
//			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
//			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
//			filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("localidade");
//			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, idSetorComercial));
//			
//			Collection colecaoSetor = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
//			
//			if (colecaoSetor != null && !colecaoSetor.isEmpty()) {
//				SetorComercial setorComercial = (SetorComercial) colecaoSetor.iterator().next();
//				inserirMensagemContaActionForm.setSetorComercialDescricao(setorComercial.getDescricao());
//				//httpServletRequest.setAttribute("localidadeInexistente", false);
//			} else {
//				inserirMensagemContaActionForm.setSetorComercialDescricao("");
//				inserirMensagemContaActionForm.setSetorComercialDescricao("SETOR COMERCIAL INEXISTENTE");
//				httpServletRequest.setAttribute("setorComercialInexistente", true);
//			}
//			httpServletRequest.setAttribute("identificadorPesquisa", true);
//		}
		
		return retorno;

	}

}
