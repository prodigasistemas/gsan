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

import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0402] Inserir Especificação da Situação do Imovel
 * 
 * @author Rafael Pinto
 * @created 04/08/2006
 */
public class InserirEspecificacaoSituacaoImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta Retorno (Forward = Sucesso)
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Form
		InserirEspecificacaoSituacaoImovelActionForm inserirEspecificacaoSituacaoImovelActionForm = 
			(InserirEspecificacaoSituacaoImovelActionForm) actionForm;

		// Fachada
		Fachada fachada = Fachada.getInstancia();

		// EspecificacaoImovelSituacao
		EspecificacaoImovelSituacao especificacaoImovelSituacao = new EspecificacaoImovelSituacao();
		
		especificacaoImovelSituacao.setDescricao(
			inserirEspecificacaoSituacaoImovelActionForm.getDescricaoEspecificacao());
		especificacaoImovelSituacao.setUltimaAlteracao(new Date());
		
		// Insere EspecificacaoImovelSituacao
		fachada.inserir(especificacaoImovelSituacao);
		
		// Insere Equipe Componentes
		this.inserirEspecificacaoImovelSituacaoCriterio(inserirEspecificacaoSituacaoImovelActionForm, fachada, especificacaoImovelSituacao);
		
		// [FS008] Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, 
			"Especificação Situação do Imóvel "+especificacaoImovelSituacao.getDescricao()+" inserida com sucesso!", 
			"Inserir outra Especificação Situação do Imóvel",
			"exibirInserirEspecificacaoSituacaoImovelAction.do?menu=sim",
			"exibirAtualizarEspecificacaoSituacaoImovelAction.do?idEspecificacao="+especificacaoImovelSituacao.getId(),
			"Atualizar Especificação Situação do Imóvel Inserida");

		return retorno;
	}

	/**
	 * Insere Coleção de EspecificacaoImovelSituacaoCriterio devidamente validados na base 
	 *
	 * @author Rafael Pinto
	 * @date 04/08/2006
	 *
	 * @param InserirEspecificacaoSituacaoImovelActionForm
	 * @param fachada
	 * @param especificacaoImovelSituacao
	 */
	private void inserirEspecificacaoImovelSituacaoCriterio(InserirEspecificacaoSituacaoImovelActionForm inserirActionForm, 
		Fachada fachada, EspecificacaoImovelSituacao especificacaoImovelSituacao) {
		
		// Coleção de EspecificacaoImovelSituacaoCriterio
		Collection colecaoEspecificacaoImovelSituacaoCriterio = inserirActionForm.getEspecificacaoImovelSituacaoCriterio();
		for (Iterator iter = colecaoEspecificacaoImovelSituacaoCriterio.iterator(); iter.hasNext();) {
			
			EspecificacaoImovSitCriterio element = (EspecificacaoImovSitCriterio) iter.next();
			element.setEspecificacaoImovelSituacao(especificacaoImovelSituacao);
			
			fachada.inserir(element);
		}
	}

}
