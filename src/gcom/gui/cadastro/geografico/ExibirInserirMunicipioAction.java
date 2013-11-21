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
package gcom.gui.cadastro.geografico;


import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0001]	INSERIR MUNICIPIO
 * 
 * @author Kássia Albuquerque
 * @date 13/12/2006
 */
 
public class ExibirInserirMunicipioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		ActionForward retorno = actionMapping.findForward("inserirMunicipio");	
		
		Fachada fachada = Fachada.getInstancia();
		
		InserirMunicipioActionForm inserirMunicipioActionForm = (InserirMunicipioActionForm) actionForm;
		
		if (httpServletRequest.getParameter("menu") != null && !httpServletRequest.getParameter("menu").trim().equals("")) {
        	//Retorna o maior id de Município existente 
			int id = this.getFachada().pesquisarMaximoIdMunicipio();
			// Acrescenta 1 no valor do id para setar o primeiro id vazio para o usuário
			id = (id + 1);
			inserirMunicipioActionForm.setCodigoMunicipio("" + id);
			httpServletRequest.setAttribute("nomeCampo", "codigoMunicipio");
			inserirMunicipioActionForm.setIndicadorRelacaoQuadraBairro("2");
		}

		
		// [FS0001]-  Verificar a existencia de dados
		
				// UNIDADE FEDERAÇÃO
				
				FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();
				
				filtroUnidadeFederacao.setCampoOrderBy(FiltroUnidadeFederacao.DESCRICAO);
				
				Collection<UnidadeFederacao> colecaoUnidadeFederacao = fachada.pesquisar(
						filtroUnidadeFederacao, UnidadeFederacao.class.getName());
				
				if (colecaoUnidadeFederacao == null || colecaoUnidadeFederacao.isEmpty()) {
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Unidade Federação");
				}
		
				httpServletRequest.setAttribute("colecaoUnidadeFederacao",colecaoUnidadeFederacao);
				
				
				// MICRORREGIAO
				
				FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();
		
				filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);
				
				filtroMicrorregiao.adicionarParametro(new ParametroSimples(
				FiltroMicrorregiao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
				
				Collection<Microrregiao> colecaoMicrorregiao = fachada.pesquisar(
						filtroMicrorregiao, Microrregiao.class.getName());
				
				if (colecaoMicrorregiao == null || colecaoMicrorregiao.isEmpty()) {
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Microrregiao");
				}
		
				httpServletRequest.setAttribute("colecaoMicrorregiao",colecaoMicrorregiao);
		
				
				// REGIAO DESENVOLVIMENTO
				
				FiltroRegiaoDesenvolvimento filtroRegiaoDesenv = new FiltroRegiaoDesenvolvimento();
				
				filtroRegiaoDesenv.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);
				
				filtroRegiaoDesenv.adicionarParametro(new ParametroSimples(
						FiltroRegiaoDesenvolvimento.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				
				Collection<RegiaoDesenvolvimento> colecaoRegiaoDesenv = fachada.pesquisar(
						filtroRegiaoDesenv, RegiaoDesenvolvimento.class.getName());
		
				if (colecaoRegiaoDesenv == null || colecaoRegiaoDesenv.isEmpty()) {
					throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Regiao Desenvolvimento");
				}
		
				httpServletRequest.setAttribute("colecaoRegiaoDesenv",colecaoRegiaoDesenv);

		
		return retorno;
	}
}
