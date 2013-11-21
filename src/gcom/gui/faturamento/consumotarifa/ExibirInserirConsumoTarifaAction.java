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
package gcom.gui.faturamento.consumotarifa;

import gcom.atendimentopublico.ligacaoagua.FiltroPerfilLigacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroTarifaTipoCalculo;
import gcom.faturamento.TarifaTipoCalculo;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

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
public class ExibirInserirConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirConsumoTarifa");

		Fachada fachada = Fachada.getInstancia();
				
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String limparForm = (String) httpServletRequest.getParameter("limparForm");
		
		InserirConsumoTarifaActionForm inserirConsumoTarifaActionForm = (InserirConsumoTarifaActionForm) actionForm;
		
		if ((sessao.getAttribute("Vigencia") != null)
				&& (! sessao.getAttribute("Vigencia").equals(""))) {
			inserirConsumoTarifaActionForm.setDataVigencia((String) sessao.getAttribute("Vigencia"));
		}
		
		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();
		
		filtroConsumoTarifa.adicionarParametro(new ParametroSimples(FiltroConsumoTarifa.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroConsumoTarifa.setCampoOrderBy(FiltroConsumoTarifa.DESCRICAO);
		
		Collection colecaoConsumoTarifa = fachada.pesquisar(filtroConsumoTarifa, ConsumoTarifa.class.getName()); 
		sessao.setAttribute("colecaoConsumoTarifa", colecaoConsumoTarifa);
		
		if (sessao.getAttribute("colecaoLigacaoAguaPerfil") == null){
			//Pesquisa os perfis de ligação de água
			FiltroPerfilLigacao filtroPerfilLigacao = new FiltroPerfilLigacao();
			filtroPerfilLigacao.setCampoOrderBy(FiltroPerfilLigacao.DESCRICAO);
			filtroPerfilLigacao.adicionarParametro(new ParametroSimples(
					FiltroPerfilLigacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection colecaoLigacaoAguaPerfil = fachada.pesquisar(
					filtroPerfilLigacao, LigacaoAguaPerfil.class.getName());
			if (colecaoLigacaoAguaPerfil == null || colecaoLigacaoAguaPerfil.isEmpty()) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",
						null, "Tabela Ligacao Agua Perfil");
			}
				
			sessao.setAttribute("colecaoLigacaoAguaPerfil", colecaoLigacaoAguaPerfil);
		}
		
		if (sessao.getAttribute("colecaoTarifaTipoCalculo") == null){
			//Pesquisa os perfis de ligação de água
			FiltroTarifaTipoCalculo filtroTarifaTipoCalculo = new FiltroTarifaTipoCalculo();
			filtroTarifaTipoCalculo.setCampoOrderBy(FiltroTarifaTipoCalculo.DESCRICAO);
			filtroTarifaTipoCalculo.adicionarParametro(new ParametroSimples(
					FiltroPerfilLigacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection colecaoTarifaTipoCalculo = fachada.pesquisar(
					filtroTarifaTipoCalculo, TarifaTipoCalculo.class.getName());
			if (colecaoTarifaTipoCalculo == null || colecaoTarifaTipoCalculo.isEmpty()) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",
						null, "Tabela Tarifa Tipo Calculo");
			}
				
			sessao.setAttribute("colecaoTarifaTipoCalculo", colecaoTarifaTipoCalculo);
		}
		
		sessao.setAttribute("inserirConsumoTarifaActionForm", inserirConsumoTarifaActionForm);
		
		if (limparForm != null && !limparForm.trim().equalsIgnoreCase("")) {
			inserirConsumoTarifaActionForm.reset(actionMapping,httpServletRequest);
		}	
		
		return retorno;
	}

}
