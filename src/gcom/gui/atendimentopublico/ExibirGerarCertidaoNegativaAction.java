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


package gcom.gui.atendimentopublico;

import gcom.cadastro.cliente.Cliente;import gcom.cadastro.cliente.ClienteTipo;import gcom.cadastro.cliente.FiltroCliente;import gcom.cadastro.imovel.FiltroImovel;import gcom.cadastro.imovel.Imovel;import gcom.fachada.Fachada;import gcom.gui.GcomAction;import gcom.util.ControladorException;import gcom.util.FachadaException;import gcom.util.Util;import gcom.util.filtro.ParametroSimples;import java.util.Collection;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;

/**
 * [UC0738] Gerar Relatório de Imóveis com Faturas em Atraso
 * 
 * @author Bruno Barros
 *
 * @date 22/01/2008
 */


public class ExibirGerarCertidaoNegativaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,			ActionForm actionForm, HttpServletRequest httpServletRequest,			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno		ActionForward retorno = actionMapping.findForward("exibirGerarCertidaoNegativa");
		GerarCertidaoNegativaActionForm form = 			(GerarCertidaoNegativaActionForm) actionForm;
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		// Pesquisar Imovel		if (objetoConsulta != null && !objetoConsulta.trim().equals("") ) {			if ( objetoConsulta.trim().equals( "1" ) ){				// Faz a consulta do Imovel				this.pesquisarImovel(form,objetoConsulta, httpServletRequest);			}		}		return retorno;	}
	
	/**	 * Pesquisa Localidade	 *	 * @author Bruno Barros	 * @date 22/01/2008	 */
	private void pesquisarImovel(GerarCertidaoNegativaActionForm form,		String objetoConsulta, HttpServletRequest httpServletRequest) {
		String idImovel = form.getIdImovel();
		FiltroImovel filtroImovel = new FiltroImovel();		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "setorComercial" );		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "quadra" );		filtroImovel.adicionarParametro(			new ParametroSimples(FiltroImovel.ID, idImovel));
		// Recupera o Imovel		Collection colecaoImovel = 			this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
			httpServletRequest.setAttribute("idImovelNaoEncontrado", null );
			Imovel imovel = 				(Imovel) Util.retonarObjetoDeColecao(colecaoImovel);						form.setIdImovel(imovel.getId().toString());			form.setMatriculaImovel(imovel.getInscricaoFormatada());
			// Encontramos o cliente Usuario			Cliente cliente = Fachada.getInstancia().pesquisarClienteUsuarioImovel( imovel.getId() );
			// Carregamos as informações			FiltroCliente filtroCliente = new FiltroCliente();			filtroCliente.adicionarCaminhoParaCarregamentoEntidade( "clienteTipo" );			filtroCliente.adicionarParametro(				new ParametroSimples(FiltroCliente.ID, cliente.getId()));
			// Recupera o Cliente			Collection colecaoCliente = 				this.getFachada().pesquisar(filtroCliente, Cliente.class.getName());
			cliente = 				(Cliente) Util.retonarObjetoDeColecao(colecaoCliente);			
			if ( cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().intValue() == ClienteTipo.INDICADOR_PESSOA_JURIDICA.intValue()  ){				form.setCpfCnpj( cliente.getCnpjFormatado() );			} else {				form.setCpfCnpj( cliente.getCpfFormatado() );			}
			form.setNomeClienteUsuario( cliente.getNome() );
			try {				form.setEnderecoImovel( Fachada.getInstancia().pesquisarEnderecoFormatado( imovel.getId() ) );			} catch (ControladorException e) {				throw new FachadaException( e.getMessage() );			}			
		} else {
			form.setIdImovel( null );			form.setNomeClienteUsuario( null );			form.setCpfCnpj( null );			form.setEnderecoImovel( null );			form.setMatriculaImovel("Imovel inexistente");			httpServletRequest.setAttribute("idImovelNaoEncontrado", "s" );		}	}	
}
