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
package gcom.gui.util.tabelaauxiliar.abreviadatipo;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * <Esse componente serve para SetorAbastecimento e ZonaAbastecimento, sendo o tipo SistemaAbastecimento>
 * 
 * @author Administrador
 */
public class ExibirFiltrarTabelaAuxiliarAbreviadaTipoAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarTabelaAuxiliarAbreviadaTipo");

		//Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		String tela = httpServletRequest.getParameter("tela");
		sessao.setAttribute("tela",tela);

		Fachada fachada = Fachada.getInstancia();

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
		
		pesquisarActionForm.set("atualizar","1");
		
		if (pesquisarActionForm.get("tipoPesquisa") == null
				|| pesquisarActionForm.get("tipoPesquisa").equals("")) {

			pesquisarActionForm.set("tipoPesquisa",
					ConstantesSistema.TIPO_PESQUISA_INICIAL+"");

		}
		
		SistemaParametro sistemaParametro = (SistemaParametro) fachada
				.pesquisarParametrosDoSistema();
		
		sessao.setAttribute("nomeSistema", sistemaParametro.getNomeEmpresa());

		String descricao = "Descrição";
		String descricaoAbreviada = "Descrição Abreviada";

		int tamMaxCampoDescricao = 40;
		int tamMaxCampoDescricaoAbreviada = 3;

        String tituloTipo = null;
        Collection tipos = null;

		DadosTelaTabelaAuxiliarAbreviadaTipo dados = DadosTelaTabelaAuxiliarAbreviadaTipo
				.obterDadosTelaTabelaAuxiliar(tela);

		if (dados.getNomeParametroFuncionalidade().equals("setorAbastecimento")) {
			
			Collection colecaoObject = new ArrayList();	
			
			FiltroSistemaAbastecimento filtro = new FiltroSistemaAbastecimento();
			filtro.adicionarParametro(
				new ParametroSimples(
					FiltroSistemaAbastecimento.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoObject = 
				this.getFachada().pesquisar(filtro,SistemaAbastecimento.class.getName());
			
			sessao.setAttribute("colecaoObject", colecaoObject);
		}

		if (dados.getNomeParametroFuncionalidade().equals("zonaAbastecimento")) {
			
			Collection colecaoObject = new ArrayList();	
			
			FiltroSistemaAbastecimento filtro = new FiltroSistemaAbastecimento();
			filtro.adicionarParametro(
				new ParametroSimples(
					FiltroSistemaAbastecimento.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			
			colecaoObject = 
				this.getFachada().pesquisar(filtro,SistemaAbastecimento.class.getName());
			
			sessao.setAttribute("colecaoObject", colecaoObject);
		}
		

		sessao.setAttribute("dados", dados);
		sessao.setAttribute("titulo", dados.getTitulo());
		sessao.setAttribute("tabela", dados.getTabela());
		sessao.setAttribute("funcionalidadeTabelaAuxiliarAbreviadaTipoInserir",
				dados.getFuncionalidadeTabelaAuxInserir());
		sessao.setAttribute("nomeParametroFuncionalidade", dados
				.getNomeParametroFuncionalidade());
		sessao.setAttribute("descricao",descricao);
		sessao.setAttribute("descricaoAbreviada",descricaoAbreviada);
		sessao.setAttribute("tipoPesquisa","1");
		sessao.setAttribute("tamMaxCampoDescricao", tamMaxCampoDescricao);
		sessao.setAttribute("tamMaxCampoDescricaoAbreviada",tamMaxCampoDescricaoAbreviada);
        sessao.setAttribute("tituloTipo", tituloTipo);
        sessao.setAttribute("tipos", tipos);
        
		//funcionalidadeTabelaAuxiliarAbreviadaManter
		sessao.setAttribute("funcionalidadeTabelaAuxiliarAbreviadaTipoFiltrar", dados
				.getFuncionalidadeTabelaAuxFiltrar());
		sessao.setAttribute("pacoteNomeObjeto", dados.getTabelaAuxiliar().getClass().getName());
		
		
		//Adiciona o objeto no request
		httpServletRequest.setAttribute("tamMaxCampoDescricao", new Integer(
				tamMaxCampoDescricao));
		httpServletRequest.setAttribute("tamMaxCampoDescricaoAbreviada",
				new Integer(tamMaxCampoDescricaoAbreviada));
		httpServletRequest.setAttribute("descricao", descricao);
		httpServletRequest.setAttribute("descricaoAbreviada",
				descricaoAbreviada);

		return retorno;
	}

}
