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
package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.bean.DadosNegativacaoPorImovelHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da inserção de um Comando de Negativação 
 * (Por Matrícula de Imóveis)
 * 
 * @author Ana Maria
 * @date 06/11/2007
 */
public class AdicionarMatriculaImovelNegativacaoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("adicionarComandoNegativacaPorMatricula");
		
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
		
		if(inserirComandoNegativacaoActionForm.getMatriculaImovelDebitos() == null 
				|| inserirComandoNegativacaoActionForm.getMatriculaImovelDebitos().equals("")){
			throw new ActionServletException(
						"atencao.pesquisar_imovel");
		}
		
		Collection<DadosNegativacaoPorImovelHelper> colecaoDadosNegativacaoPorImovelHelper = null;
				
		if(sessao.getAttribute("colecaoDadosNegativacaoPorImovelHelper") != null){
			colecaoDadosNegativacaoPorImovelHelper = (Collection)sessao.getAttribute("colecaoDadosNegativacaoPorImovelHelper");
		}else{
			colecaoDadosNegativacaoPorImovelHelper = new ArrayList<DadosNegativacaoPorImovelHelper>();
		}
		
		//Cliente Selecionado
		Integer idClienteImovel = new Integer(inserirComandoNegativacaoActionForm.getClienteSelecionado());
		ClienteImovel clienteImovel = null;
		
		/*
		 * TODO - COSANPA - Felipe Santos - 06/12/2011
		 * 
		 * Verifica se ClienteImovel tem relação RESPONSÁVEL PELO PARCELAMENTO
		 */
		if(idClienteImovel.equals(new Integer(0))){
			//cliente responsável pelo parcelamento
			clienteImovel = (ClienteImovel)sessao.getAttribute("clienteParcelamento");
		}else{
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();		
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.ID,idClienteImovel));
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
			Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
			clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
		}
		//RM4097 - adicionado por Vivianne Sousa - 29/12/2010 - Ana Cristina
		//[FS0031] – Verificar existência de conta em nome do cliente
		if(inserirComandoNegativacaoActionForm.getIndicadorContaNomeCliente().equals("" + ConstantesSistema.SIM)){
			
			Collection colecaoContasIds = null;
			if (sessao.getAttribute("colecaoContasIds")!= null &&
					!((Collection) sessao.getAttribute("colecaoContasIds")).isEmpty()){
				
				colecaoContasIds = (Collection)sessao.getAttribute("colecaoContasIds");
				
				boolean existeClienteConta = fachada.verificarSeExisteClienteConta(
						clienteImovel.getCliente().getId(),colecaoContasIds);
				
				if(!existeClienteConta){
					//Caso não exista nenhuma conta em nome do cliente selecionado 
					//para negativação, exibir a mensagem “Não há nenhuma conta que compõe 
					//o débito do imóvel <<Matrícula do Imóvel>> em nome do cliente 
					//<<Nome do Cliente Selecionado para Negativação>>. Não é possível negativá-lo.”
					
					throw new ActionServletException("atencao.nao_ha_conta_cliente",
							clienteImovel.getImovel().getId().toString(), 
							clienteImovel.getCliente().getNome());
				}
				
			}
			
		}
		
		
		
		//Atualizar Imóvel já adicionadao(link)
		if(httpServletRequest.getParameter("atualizar") != null){
			if(inserirComandoNegativacaoActionForm.getIdImovelDebitos() != null){
				Integer idImovelAtualizar = new Integer(inserirComandoNegativacaoActionForm.getIdImovelDebitos());			
				if(colecaoDadosNegativacaoPorImovelHelper != null && !colecaoDadosNegativacaoPorImovelHelper.isEmpty()){
					Iterator dadosNegativacaoPorImovelHelperIterator = colecaoDadosNegativacaoPorImovelHelper.iterator();
					while (dadosNegativacaoPorImovelHelperIterator.hasNext()) {
						DadosNegativacaoPorImovelHelper helper = (DadosNegativacaoPorImovelHelper) dadosNegativacaoPorImovelHelperIterator.next();
						if(helper.getIdImovel().equals(idImovelAtualizar)){
							helper.setIdCliente(clienteImovel.getCliente().getId());
							helper.setIdClienteRelacaoTipo(clienteImovel.getClienteRelacaoTipo().getId());
							if(clienteImovel.getCliente().getCpf() != null){
								helper.setCpfCliente(clienteImovel.getCliente().getCpfFormatado());
							}
							if(clienteImovel.getCliente().getCnpj() != null){
								helper.setCpfCliente(clienteImovel.getCliente().getCnpjFormatado());
							}							
						}
					}
					//sessao.setAttribute("colecaoDadosNegativacaoPorImovelHelper", colecaoDadosNegativacaoPorImovelHelper);
				}
			}
		}else{ //Inserir imóvel	selecionado		
			List colecaoConta = (List)sessao.getAttribute("colecaoContaValores");
			List colecaoGuias = (List)sessao.getAttribute("colecaoGuiaPagamentoValores");
			Integer qtdItensDebitoImovel = colecaoConta.size() + colecaoGuias.size();
			String totalDebitosImovel = (String)sessao.getAttribute("valorTotalSemAcrescimo");				
		
			//Dados do Cliente Selecionado e dos débitos do Imóvel
			DadosNegativacaoPorImovelHelper helper = new DadosNegativacaoPorImovelHelper();
			helper.setIdImovel(new Integer(inserirComandoNegativacaoActionForm.getIdImovelDebitos()));
			helper.setIdCliente(clienteImovel.getCliente().getId());
			helper.setIdClienteRelacaoTipo(clienteImovel.getClienteRelacaoTipo().getId());
			if(clienteImovel.getCliente().getCpf() != null){
				helper.setCpfCliente(clienteImovel.getCliente().getCpfFormatado());
			}
			if(clienteImovel.getCliente().getCnpj() != null){
				helper.setCpfCliente(clienteImovel.getCliente().getCnpjFormatado());
			}
			helper.setColecaoConta(colecaoConta);
			helper.setColecaoGuias(colecaoGuias);
			helper.setQtdItensDebitoImovel(qtdItensDebitoImovel);
			helper.setTotalDebitosImovel(Util.formatarMoedaRealparaBigDecimal(totalDebitosImovel));
			
			//[FS0011] Verificar matrícula do imóvel já existe na lista
			if(colecaoDadosNegativacaoPorImovelHelper.contains(helper)){
				throw new ActionServletException(
						"atencao.imovel_ja_existe_lista");
			}
			colecaoDadosNegativacaoPorImovelHelper.add(helper);		
		}
		
		sessao.setAttribute("colecaoDadosNegativacaoPorImovelHelper", colecaoDadosNegativacaoPorImovelHelper);	
		
    	//Realizar um reload na tela
    	httpServletRequest.setAttribute("reloadPage", "OK");
    	
		return retorno;

	}
}
