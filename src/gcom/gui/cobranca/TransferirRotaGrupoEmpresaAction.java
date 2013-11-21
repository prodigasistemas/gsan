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
* Anderson Italo Felinto de Lima
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;


/**			
 * @date 20/10/09
 * @author Anderson Italo
 * UC0960 Transferir Rotas Entre Grupos e/ou Empresas
 */
public class TransferirRotaGrupoEmpresaAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");
		
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		TransferirRotasGruposEmpresasActionForm form = (TransferirRotasGruposEmpresasActionForm) actionForm;
		
		FaturamentoGrupo faturamentoGrupoDestino = null;
		CobrancaGrupo cobrancaGrupoDestino = null;
		Empresa empresaFaturamentoDestino = null;
		Empresa empresaCobrancaDestino = null;
		Collection colecaoRotas = (Collection)sessao.getAttribute("rotasSelecionadas");
		
		/*7.1.	Caso não tenha sido informado algum dos parâmetros 
		 * para onde devem ser transferidas as rotas, este campo 
		 * não deve ser alterado.*/
		
		if (form.getIdGrupoFaturamentoDestino() != null && !form.getIdGrupoFaturamentoDestino().equals("")
				&& !form.getIdGrupoFaturamentoDestino().equals("-1")){
			
			FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();
			filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID , new Integer(form.getIdGrupoFaturamentoDestino())));
			Collection colecaoGrupoFaturamento = fachada.pesquisar(filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());
			
			faturamentoGrupoDestino = (FaturamentoGrupo)Util.retonarObjetoDeColecao(colecaoGrupoFaturamento);
		}
		
		if (form.getIdGrupoCobrancaDestino() != null && !form.getIdGrupoCobrancaDestino().equals("")
				&& !form.getIdGrupoCobrancaDestino().equals("-1")){
			

			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID , new Integer(form.getIdGrupoCobrancaDestino())));
			Collection colecaoGrupoCobranca = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			
			cobrancaGrupoDestino = (CobrancaGrupo)Util.retonarObjetoDeColecao(colecaoGrupoCobranca);

		}
		
		if (form.getIdEmpresaFaturamentoDestino() != null && !form.getIdEmpresaFaturamentoDestino().equals("")
				&& !form.getIdEmpresaFaturamentoDestino().equals("-1")){
			
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID , new Integer(form.getIdEmpresaFaturamentoDestino())));
			Collection colecaoEmpresaFaturamento = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			empresaFaturamentoDestino = (Empresa)Util.retonarObjetoDeColecao(colecaoEmpresaFaturamento);
		}
		
		if (form.getIdEmpresaCobrancaDestino() != null && !form.getIdEmpresaCobrancaDestino().equals("")
				&& !form.getIdEmpresaCobrancaDestino().equals("-1")){
			
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID , new Integer(form.getIdEmpresaCobrancaDestino())));
			Collection colecaoEmpresaCobranca = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
			
			empresaCobrancaDestino = (Empresa)Util.retonarObjetoDeColecao(colecaoEmpresaCobranca);
		}
		
		//7. O sistema faz a transferência de rotas solicitadas
		fachada.transferirRotasEntreGrupoEmpresa(faturamentoGrupoDestino, cobrancaGrupoDestino, 
				empresaFaturamentoDestino, empresaCobrancaDestino, colecaoRotas, (Usuario)sessao.getAttribute("usuarioLogado"));
		
		
		//limpa os dados do formulário
		form.setIdLocalidadeInicial(null);
		form.setDescricaoLocalidadeInicial(null);
		form.setIdLocalidadeFinal(null);
		form.setDescricaoLocalidadeFinal(null);
		form.setIdLocalidadeInicial(null);
		form.setDescricaoLocalidadeFinal(null);
		form.setIdSetorComercialInicial(null);
		form.setDescricaoSetorComercialInicial(null);
		form.setIdSetorComercialFinal(null);
		form.setDescricaoSetorComercialFinal(null);
		form.setIdRotaInicial(null);
		form.setIdRotaFinal(null);
		form.setQuantidadeRotas(null);
		form.setIdGrupoCobrancaFiltro(null);
		form.setIdGrupoCobrancaSelecao(null);
		form.setIdGrupoCobrancaDestino(null);
		form.setIdGrupoFaturamentoFiltro(null);
		form.setIdGrupoFaturamentoSelecao(null);
		form.setIdGrupoFaturamentoDestino(null);
		form.setIdEmpresaCobrancaFiltro(null);
		form.setIdEmpresaCobrancaSelecao(null);
		form.setIdEmpresaCobrancaDestino(null);
		form.setIdEmpresaFaturamentoFiltro(null);
		form.setIdEmpresaFaturamentoSelecao(null);
		form.setIdEmpresaFaturamentoDestino(null);
		
		sessao.removeAttribute("rotasSelecionadas");
		httpServletRequest.removeAttribute("colecaoEmpresaCobrancaSelecao");
		httpServletRequest.removeAttribute("colecaoEmpresaFaturamentoSelecao");
		httpServletRequest.removeAttribute("colecaoGrupoCobrancaSelecao");
		httpServletRequest.removeAttribute("colecaoGrupoFaturamentoSelecao");
		httpServletRequest.removeAttribute("colecaoUnidadeNegocio");
		httpServletRequest.removeAttribute("colecaoGerenciaRegional");
		httpServletRequest.removeAttribute("colecaoEmpresaFaturamentoFiltro");
		httpServletRequest.removeAttribute("colecaoGrupoFaturamentoFiltro");
		httpServletRequest.removeAttribute("colecaoGrupoCobrancaFiltro");
		httpServletRequest.removeAttribute("colecaoCobrancaGrupoDestino");
		httpServletRequest.removeAttribute("colecaoFaturamentoGrupoDestino");
		httpServletRequest.removeAttribute("colecaoEmpresaDestino");
		httpServletRequest.removeAttribute("colecaoEmpresaFaturamentoDestino");
		httpServletRequest.removeAttribute("colecaoEmpresaCobrancaDestino");
		
		montarPaginaSucesso(httpServletRequest, "Rota(s) Tranferida(s) com sucesso.",
	                "Realizar outra Tranferencia de Rota(s)",
	                "exibirTransferirRotaGrupoEmpresaAction.do?desfazer=S");
		
		return retorno;
	}

}
