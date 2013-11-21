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
package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RetornarContaBancariaPesquisarAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		//Inicializacoes de variaveis
		ActionForward retorno = actionMapping
				.findForward("retornarContaBancariaPesquisar");
		HttpSession sessao = httpServletRequest.getSession(false);
		//Fachada fachada = Fachada.getInstancia();
		
		boolean peloMenosUmParametroInformado = false;
		
		ContaBancariaPesquisarActionForm form = (ContaBancariaPesquisarActionForm) actionForm;
		FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
		//Inicializacoes de variaveis
		
		//Carregamento de Entidades para pesquisa com ID de Banco
		filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");
		String idBanco = form.getIdBanco().trim();
		if(idBanco != null && new Integer(idBanco).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO){
			filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.AGENCIA_BANCO_ID, idBanco));
			peloMenosUmParametroInformado = true;
		}
		//Adicionando id de Agencia para a pesquisa
		String idAgencia = form.getIdAgencia().trim();
		if(idAgencia != null && !idAgencia.equals("") 
				&& (new Integer(idAgencia).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO)){
			filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.AGENCIA_ID, idAgencia));
			peloMenosUmParametroInformado = true;
		}
		//Adicionando numero de Conta para a pesquisa
		String numeroConta = form.getNumeroConta().trim();
		if(numeroConta != null && !numeroConta.equals("")){
			filtroContaBancaria.adicionarParametro(new ComparacaoTexto(FiltroContaBancaria.NUMERO_CONTA, numeroConta));
			peloMenosUmParametroInformado = true;
		}
		
		//Testa se pelo menos um parametro foi informado
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		//Pesquisa e retorno da collection de Contas Bancarias
		Collection<ContaBancaria> collectionContaBancaria = null;//
		
		//Alterado para retornar a coleção com o controle novo de paginação - Fernanda Paiva - 10/08/2006
		//Collection<ContaBancaria> collectionContaBancaria =fachada.pesquisar(filtroContaBancaria, ContaBancaria.class.getName());
		
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroContaBancaria, ContaBancaria.class.getName());
		collectionContaBancaria = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		//Validacoes 
		if (collectionContaBancaria == null || collectionContaBancaria.isEmpty()) {
			// Nenhuma cliente cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "conta bancaria");
		} else if (collectionContaBancaria.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException("atencao.pesquisa.muitosregistros");
		} else {
			// Coloca a coleção na sessão
			sessao.setAttribute("collectionContaBancaria", collectionContaBancaria);

		}				
		
		return retorno;
	}

}
