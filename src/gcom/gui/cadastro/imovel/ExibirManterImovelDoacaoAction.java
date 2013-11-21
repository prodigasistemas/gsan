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

import gcom.cadastro.cliente.ClienteImovelSimplificado;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.FiltroEntidadeBeneficente;
import gcom.cadastro.imovel.FiltroImovelDoacao;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelDoacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

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
public class ExibirManterImovelDoacaoAction extends GcomAction {
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
		String idImovel                                            = null;
		Fachada fachada                                            = null;
		String nomeCliente                                         = null;
		String situacaoAgua                                        = null;
		String situacaoEsgoto                                      = null;
		ActionForward retorno                                      = null;
		Imovel imovelEncontrado                                    = null;
		FiltroImovelDoacao filtroImovelDoacao                      = null;
		FiltroClienteImovel filtroClienteImovel                    = null;
		Collection<ImovelDoacao> colecaoImovelDoacao               = null;
		Collection<ClienteImovelSimplificado> colecaoClienteImovel = null;

		/*** Procedimentos básicos para execução do método ***/
		retorno = actionMapping.findForward("manterImovelDoacao");
		fachada = Fachada.getInstancia();
		
		/*** Testa se é um reload ou se está vindo da tela de sucesso de inserção de imovel doacao ***/
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
			idImovel = httpServletRequest.getParameter("idRegistroAtualizacao"); 	
		} else {
			idImovel = (String)httpServletRequest.getParameter("idImovel");	
		}
		
		if (idImovel != null && !idImovel.trim().equals("")) {
			imovelEncontrado = (Imovel)fachada.pesquisarImovelDigitado(new Integer(idImovel));
			if (imovelEncontrado != null) {
				httpServletRequest.setAttribute("idImovelEncontrado", imovelEncontrado.getId().toString());
				httpServletRequest.setAttribute("inscricaoImovelEncontrado", imovelEncontrado.getInscricaoFormatada());

				/*** Prepara o filtro de cliente imovel para a pesquisa ***/
				filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovelEncontrado.getId()));
				colecaoClienteImovel = (Collection<ClienteImovelSimplificado>)fachada.pesquisarClienteImovel(filtroClienteImovel);
				
				/*** Percorre a coleção para identificar o cliente válido, 
				 *   ou seja, que tem data de fim relaçao nula ***/				
				for (ClienteImovelSimplificado clienteImovel: colecaoClienteImovel) {
					if (clienteImovel.getDataFimRelacao() == null) {
					    nomeCliente = clienteImovel.getCliente().getNome();
					}
				}
								
				
				/*** Define filtro pra pesquisar e alimenta a colecao de entidade beneficente ***/	
				
				//--------------------------------------------------------------
				// CRC933 
				// Autor: Yara T. Souza
				// Data : 06/01/2009
				//--------------------------------------------------------------
				
				HttpSession sessao = httpServletRequest.getSession(false);				
				Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");		
				
				FiltroEntidadeBeneficente filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();		
				filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("cliente");		
				filtroEntidadeBeneficente.adicionarParametro(new ParametroSimples(FiltroEntidadeBeneficente.ID_EMPRESA, usuarioLogado.getEmpresa().getId()));
				Collection collEntidadeBeneficente =  fachada.pesquisar(filtroEntidadeBeneficente, EntidadeBeneficente.class.getName());
				EntidadeBeneficente entidadeBeneficente =  (EntidadeBeneficente)Util.retonarObjetoDeColecao(collEntidadeBeneficente);
		
				//--------------------------------------------------------------
				
				/*** Atribui os de situação água esgoto***/
				situacaoAgua   = imovelEncontrado.getLigacaoAguaSituacao().getDescricao();
				situacaoEsgoto = imovelEncontrado.getLigacaoEsgotoSituacao().getDescricao();
				/*** Seta um atributo no request para cor do campo de inscricao do imóvel para preto***/
				httpServletRequest.setAttribute("corInscricao", "#000000");
				/*** Prepara o filtro de imóvel doacao para a pesquisa ***/
				filtroImovelDoacao = new FiltroImovelDoacao();
				filtroImovelDoacao.adicionarParametro(new ParametroSimples(FiltroImovelDoacao.ID_IMOVEL, imovelEncontrado.getId()));
				
				if(entidadeBeneficente != null){
					filtroImovelDoacao.adicionarParametro(new ParametroSimples(FiltroImovelDoacao.ID_ENTIDADE_BENEFICENTE, entidadeBeneficente.getId()));
					
				}
				
				filtroImovelDoacao.adicionarCaminhoParaCarregamentoEntidade("entidadeBeneficente.cliente");
				filtroImovelDoacao.adicionarCaminhoParaCarregamentoEntidade("usuarioAdesao");
				filtroImovelDoacao.adicionarCaminhoParaCarregamentoEntidade("usuarioCancelamento");
				
				colecaoImovelDoacao = (Collection<ImovelDoacao>)fachada.pesquisar(filtroImovelDoacao, ImovelDoacao.class.getName());
				
				/*** Valida se a coleção está preenchida ***/
				if (colecaoImovelDoacao != null && colecaoImovelDoacao.size() == 0) {
					throw new ActionServletException("atencao.naocadastrado", null, "Imóvel(eis) Doação(ãos)");		
				}
				
		        httpServletRequest.setAttribute("colecaoImovelDoacao", colecaoImovelDoacao);
				
			} else {
				/*** Caso a pesquisa de imóvel tenha não tenha um resultado adequado ***/
				/*** Seta um atributo no request para cor do campo de inscricao do imóvel para preto***/
				httpServletRequest.setAttribute("corInscricao", "#ff0000");
				httpServletRequest.setAttribute("inscricaoImovelEncontrado", ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}
		} else {
			httpServletRequest.setAttribute("idImovelEncontrado", null);
			httpServletRequest.setAttribute("inscricaoImovelEncontrado", null);
		}
		/*** Atribui os dados do imóvel ***/
		httpServletRequest.setAttribute("nomeCliente", nomeCliente);
		httpServletRequest.setAttribute("situacaoAgua", situacaoAgua);
		httpServletRequest.setAttribute("situacaoEsgoto", situacaoEsgoto);

		return retorno;
	}
}
