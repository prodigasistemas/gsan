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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelDoacao;
import gcom.cadastro.imovel.ImovelDoacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0389] - Inserir Imóvel Doação Action responsável pela pre-exibição da
 * pagina de inserir ImovelDoacao
 * 
 * @author César Araújo
 * @created 22 de agosto de 2006
 */
public class CancelarImovelDoacaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		/*** Declara e inicializa variáveis ***/
		Fachada fachada                                           = null;
		HttpSession sessao                                        = null;
		ActionForward retorno                                     = null;
		String[] idsCancelamento                                  = null;
		ImovelDoacao imovelDoacao                                 = null;
		Usuario usuarioCancelamento                               = null;
		FiltroImovelDoacao filtroImovelDoacao                     = null;
		Integer contadorImovelDoacaoCancelados                    = null;          
		ManutencaoRegistroActionForm manutencaoRegistroActionForm = null;

		/*** Procedimentos básicos para execução do método ***/
		retorno = actionMapping.findForward("telaSucesso");
		manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
		fachada = Fachada.getInstancia();
		sessao = httpServletRequest.getSession(false);
		
		/*** Obtém os ids para cancelamento ***/
		idsCancelamento = manutencaoRegistroActionForm.getIdRegistrosRemocao();
		
		/*** Avalia se existem ids de imovel doacao válidos ***/
		if (idsCancelamento.length == 0) {
			throw new ActionServletException("atencao.manter_imovel_doacao_nenhuma_entidade_beneficente_selecionada");		
		}
		
		/*** Cria filtro imovel doacao***/
		filtroImovelDoacao = new FiltroImovelDoacao();
		contadorImovelDoacaoCancelados = new Integer(0);

        /** alterado por pedro alexandre dia 17/11/2006 
         * Recupera o usuário logado para passar no metódo de cancelar 
         * para verificar se o usuário tem abrangência para cancelar a doação
         */
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
		/*** Manipula cada um dos ids de imovel doacao para cancelamento ***/
		for (String idCancelamento: idsCancelamento) {
			/*** Prepara o filtro para pesquisar o respectivo imovel doacao na base ***/
			filtroImovelDoacao.limparListaParametros();
    		filtroImovelDoacao.adicionarParametro(new ParametroSimples(FiltroImovelDoacao.ID, idCancelamento));
    		filtroImovelDoacao.adicionarCaminhoParaCarregamentoEntidade("imovel");
    		imovelDoacao = (ImovelDoacao)fachada.pesquisar(filtroImovelDoacao, ImovelDoacao.class.getName()).iterator().next();

    		/*** Cria e atribui o usuário de cancelamento ***/
    		usuarioCancelamento = new Usuario();
    		usuarioCancelamento.setId(((Usuario)sessao.getAttribute("usuarioLogado")).getId());
    		
    		/*** Atribui os dados que serão atualizados para o imovel doacao ***/
        	imovelDoacao.setDataCancelamento(new Date());
        	imovelDoacao.setUsuarioCancelamento(usuarioCancelamento);
        
        	fachada.atualizarImovelDoacao(imovelDoacao, usuarioLogado);
        	
        	contadorImovelDoacaoCancelados += 1;  
		}
		
        Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_CATEGORIA_INSERIR);
        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        //------------ REGISTRAR TRANSAÇÃO ----------------//

        /*** Monta tela de sucesso ***/
		montarPaginaSucesso(httpServletRequest,
				            contadorImovelDoacaoCancelados+" Autorização(ões) para Doação Mensal do Imóvel "+imovelDoacao.getImovel().getId().toString()+" cancelada(s) com sucesso.",
				            "Cancelar outra Autorização para Doação Mensal", 
				            "exibirManterImovelDoacaoAction.do");

		return retorno;
	}
}
