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
* Genival Soares Barbosa Filho
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

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ConectorAnd;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0905] Relatorio de Acompanhamento das Acoes de Cobranca
 * 
 * @author Genival Barbosa
 * 
 */

public class ExibirRelatorioAcompanhamentoAcoesCobrancaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("relatorioAcompanhamentoAcoesCobranca");

		ExibirRelatorioAcompanhamentoAcoesCobrancaActionForm form = (ExibirRelatorioAcompanhamentoAcoesCobrancaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

//		 Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);			
			
			FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
			filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.INDICADOR_USO, 
				ConstantesSistema.SIM));
			filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);

			Collection colecaoCobrancaAcao = fachada.pesquisar(
					filtroCobrancaAcao, CobrancaAcao.class.getName());

			sessao.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);
			
			
			
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO,
					ConstantesSistema.SIM));
			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
			
			Collection colecaoGerenciaRegional = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());
			
			sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
			
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.SIM));
			
			String pesquisarUnidadeLocalidade = httpServletRequest
			.getParameter("pesquisarUnidadeLocalidade");
			if(pesquisarUnidadeLocalidade != null){
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO,
						ConstantesSistema.SIM, ConectorAnd.CONECTOR_AND));
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.GERENCIA,
						form.getIdGerenciaRegional()));
			}
			else 
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO,
						ConstantesSistema.SIM));
			
			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
			
			Collection colecaoUnidadeNegocio = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			
			sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
			
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.SIM));
			
			String pesquisarLocalidade = httpServletRequest
			.getParameter("pesquisarLocalidade");
			
			if(pesquisarUnidadeLocalidade != null){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.SIM, ConectorAnd.CONECTOR_AND));
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.GERENCIA,
						form.getIdGerenciaRegional()));
			}else if(pesquisarLocalidade != null){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.SIM, ConectorAnd.CONECTOR_AND));
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.UNIDADE_NEGOCIO,
						form.getIdUnidadeNegocio()));
			}else 
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.SIM));
			
			filtroLocalidade.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
			
			Collection colecaoLocalidade = fachada.pesquisar(
					filtroLocalidade, Localidade.class.getName());
			
			sessao.setAttribute("colecaoLocalidade", colecaoLocalidade);
			
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO,
					ConstantesSistema.SIM));
			
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
			
			Collection colecaoEmpresa = fachada.pesquisar(
					filtroEmpresa, Empresa.class.getName());
			
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		
		
		return retorno;

	}

}