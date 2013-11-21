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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para geração da relação numérica
 * 
 * @author Raphael Rossiter
 * @date 06/11/2006
 */
public class ExibirGerarNumeracaoManualRegistroAtendimentoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("gerarNumeracaoManualRegistroAtendimento");

		GerarNumeracaoManualRegistroAtendimentoActionForm form = 
		(GerarNumeracaoManualRegistroAtendimentoActionForm) actionForm;

		//HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Integer digitoModulo11 = 
		Util.obterDigitoVerificadorModulo11(Long.parseLong(sistemaParametro.getUltimoRAManual().toString()));
		
		String sequencialString = sistemaParametro.getUltimoRAManual().toString();
		int complementoZeros = 9 - sequencialString.length();
		String sequencialStringFinal =  sequencialString;
		
		for (int y=0; y < complementoZeros; y++){
			sequencialStringFinal = "0" + sequencialStringFinal;
		}
		
		form.setUltimoValorGerado(sequencialStringFinal + "-" + digitoModulo11);
		
		//Carregamento Inicial
		String menu = httpServletRequest.getParameter("menu");
		String desfazer = httpServletRequest.getParameter("desfazer");
		
		if ((menu != null && !menu.equalsIgnoreCase("")) ||
			(desfazer != null && !desfazer.equalsIgnoreCase(""))){
			
			form.setQuantidade("");
			form.setIdUnidadeOrganizacional("");
			form.setDescricaoUnidadeOrganizacional("");
			
			httpServletRequest.setAttribute("nomeCampo", "quantidade");
		}
		
		
		//Pesquisar Unidade
		String pesquisarUnidade = httpServletRequest.getParameter("pesquisarUnidade");
		
		if (pesquisarUnidade != null && !pesquisarUnidade.equalsIgnoreCase("")){
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,
			form.getIdUnidadeOrganizacional()));
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
			FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional,
			UnidadeOrganizacional.class.getName());

			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {

				form.setIdUnidadeOrganizacional("");
				form.setDescricaoUnidadeOrganizacional("Unidade Organizacional inexistente");

				httpServletRequest.setAttribute("corUnidade", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeOrganizacional");

			} else {
				
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util
				.retonarObjetoDeColecao(colecaoUnidade);

				form.setIdUnidadeOrganizacional(unidadeOrganizacional.getId().toString());
				form.setDescricaoUnidadeOrganizacional(unidadeOrganizacional.getDescricao());

				httpServletRequest.setAttribute("nomeCampo", "gerar");
			}
		}

		
		
		return retorno;
	}
}
