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

import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsavel para inserir o imóvel economia na coleção do cliente
 * imóvel economia
 * 
 * @author Sávio Luiz
 * @created 20 de Maio de 2004
 */
public class InserirEconomiaPopupAction extends GcomAction {
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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("inserirEconomiaPopup");

		// Obtém o action form
		EconomiaPopupActionForm economiaPopupActionForm = (EconomiaPopupActionForm) actionForm;

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoClientesImoveisEconomia = null;

		//Collection colecaoImovelEconomiaClientes = null;

		Collection colecaoImovelEconomiasModificadas = null;

		ImovelSubcategoria imovelSubCategoria = (ImovelSubcategoria) sessao
				.getAttribute("imovelSubCategoria");

		Fachada fachada = Fachada.getInstancia();

		Collection imovelEconomias = null;

		if (imovelSubCategoria.getImovelEconomias() == null
				|| imovelSubCategoria.getImovelEconomias().equals("")) {

			imovelEconomias = new ArrayList();

		} else {
			imovelEconomias = imovelSubCategoria.getImovelEconomias();
		}

		if (imovelEconomias.size() <= imovelSubCategoria
				.getQuantidadeEconomias()) {

			// Coleção vinda do imovel_inserir_economia_popup
			// com a coleção de cliente, imovel e tipo da relação
			if (sessao.getAttribute("colecaoClientesImoveisEconomia") != null) {
				colecaoClientesImoveisEconomia = (Collection) sessao
						.getAttribute("colecaoClientesImoveisEconomia");

			} else {
				colecaoClientesImoveisEconomia = new ArrayList();
			}
			// pega da sessão a coleção para os imoveis economias inseridos e/ou
			// modificados
			colecaoImovelEconomiasModificadas = (Collection) sessao
					.getAttribute("colecaoImovelEconomiasModificadas");

			Imovel imovel = (Imovel) sessao.getAttribute("imovel");

			// Cria objeto areaConstruida
			AreaConstruidaFaixa areaConstruidaFaixa = null;
			BigDecimal areaConstruida = null;
			Integer areaContruidaFaixaForm = (new Integer(
					economiaPopupActionForm.getIdAreaConstruidaFaixa()
							.toString()));

			if (areaContruidaFaixaForm != null
					&& areaContruidaFaixaForm.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				areaConstruidaFaixa = new AreaConstruidaFaixa();
				areaConstruidaFaixa.setId(areaContruidaFaixaForm);
				/*if (economiaPopupActionForm.getAreaConstruida() != null
						&& !economiaPopupActionForm.getAreaConstruida().equals(
								"")) {
					
				}*/
			} else {
				if (economiaPopupActionForm.getAreaConstruida() != null
						&& !economiaPopupActionForm.getAreaConstruida().equals(
								"")) {
					Collection areasConstruidasFaixas = null;
					
					
					String areaConstuidaPrrv = economiaPopupActionForm.getAreaConstruida().replace(".", "");
					areaConstruida = (new BigDecimal(areaConstuidaPrrv.replace(",",".")));

					// Filtro AreaConstruidaFaixa
					FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa();

					// fazer a parte de filtro adicionar parametro maior que
					// e filtro adicionar parametro menos que
					filtroAreaConstruidaFaixa
							.adicionarParametro(new ParametroSimples(
									FiltroAreaConstruidaFaixa.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					filtroAreaConstruidaFaixa.adicionarParametro(new MaiorQue(
							FiltroAreaConstruidaFaixa.MAIOR_FAIXA,
							areaConstruida));

					filtroAreaConstruidaFaixa.adicionarParametro(new MenorQue(
							FiltroAreaConstruidaFaixa.MENOR_FAIXA,
							areaConstruida));

					areasConstruidasFaixas = fachada.pesquisar(
							filtroAreaConstruidaFaixa,
							AreaConstruidaFaixa.class.getName());

					if (areasConstruidasFaixas != null
							&& !areasConstruidasFaixas.isEmpty()) {
						Iterator areaContruidaFaixaIterator = areasConstruidasFaixas
								.iterator();

						areaConstruidaFaixa = (AreaConstruidaFaixa) areaContruidaFaixaIterator
								.next();

					}
				}
			}

			// recupera os dados do form
			String complementoEndereco = (String) economiaPopupActionForm
					.getComplementoEndereco();
			
			Short numeroMoradores = null;
			
			if (economiaPopupActionForm.getNumeroMorador() != null
					&& !economiaPopupActionForm.getNumeroMorador().equalsIgnoreCase("")){
				numeroMoradores = (new Short(economiaPopupActionForm
						.getNumeroMorador().toString()));
			}
			
			Short numeroPontoUtilizacao = null;
			if (economiaPopupActionForm.getNumeroPontosUtilizacao() != null 
					&& !economiaPopupActionForm.getNumeroPontosUtilizacao().equalsIgnoreCase("")){
				numeroPontoUtilizacao = (new Short(economiaPopupActionForm
						.getNumeroPontosUtilizacao().toString()));
			}
			
			BigDecimal numeroIptu = null;
			if (economiaPopupActionForm.getNumeroIptu() != null
					&& !economiaPopupActionForm.getNumeroIptu().equals("")) {
				// verifica se existe no imovel, no imovelEconomia ou na coleção
				// que será
				// inserida se existe algum iptu cadastrado com o numero do iptu
				// digitado no
				// mesmo municipio
				fachada.verificarExistenciaIPTU(
						colecaoImovelEconomiasModificadas, imovel,
						economiaPopupActionForm.getNumeroIptu(), new Date());

				numeroIptu = (new BigDecimal(economiaPopupActionForm
						.getNumeroIptu().toString()));

			}

			Long numeroContratoEnergia = null;
			if (economiaPopupActionForm.getNumeroCelpe() != null
					&& !economiaPopupActionForm.getNumeroCelpe().equals("")) {

				// verifica se existe no imovel, no imovelEconomia ou na coleção
				// que será
				// inserida se existe algum iptu cadastrado com o numero da
				// celpe digitado
				fachada.verificarExistenciaCelpe(
						colecaoImovelEconomiasModificadas, imovel,
						economiaPopupActionForm.getNumeroCelpe(), new Date());
				numeroContratoEnergia = (new Long(economiaPopupActionForm
						.getNumeroCelpe().toString()));

			}

			ImovelEconomia imovelEconomia = new ImovelEconomia(
					complementoEndereco, numeroMoradores,
					numeroPontoUtilizacao, numeroIptu, numeroContratoEnergia,
					areaConstruida, new Date(), areaConstruidaFaixa,
					imovelSubCategoria, new HashSet(
							colecaoClientesImoveisEconomia));

			// seta um codigo modificado para quando for atualizado esse imovel
			// economia
			// não colocar na coleção de colecaoImovelEconomiasModificadas
			imovelEconomia.setCodigoModificado(imovelEconomia.getHashCode());

			if (!imovelEconomias.contains(imovelEconomia)) {
				imovelEconomias.add(imovelEconomia);
				imovelSubCategoria.setImovelEconomias(new HashSet(
						imovelEconomias));
				colecaoImovelEconomiasModificadas.add(imovelEconomia);

			} else {
				throw new ActionServletException(
						"atencao.ja_cadastrado.imovel_economia");
			}
			// para incluir mais relações entre cliente e imoveis, se preciso
			sessao.setAttribute("colecaoClientesImoveisEconomia",
					new ArrayList());

			// manda a coleção para a sessão
			sessao.setAttribute("colecaoImovelEconomiasModificadas",
					colecaoImovelEconomiasModificadas);

			// inicializa o property idClienteImovelUsuarioEconomias para a
			// inclusão de
			// novas relações entre cliente imovel do tipo usuário
			economiaPopupActionForm.setIdClienteImovelUsuario(null);

			// inicializa todos os propertys para nulo
			economiaPopupActionForm.setComplementoEndereco(null);
			economiaPopupActionForm.setNumeroPontosUtilizacao(null);
			economiaPopupActionForm.setNumeroMorador(null);
			economiaPopupActionForm.setNumeroIptu(null);
			economiaPopupActionForm.setNumeroCelpe(null);
			economiaPopupActionForm.setAreaConstruida(null);
			economiaPopupActionForm.setIdAreaConstruidaFaixa(null);
			economiaPopupActionForm.setIdCliente(null);
			economiaPopupActionForm.setNomeCliente(null);

			if (httpServletRequest.getParameter("testeInserir")
					.equalsIgnoreCase("true")) {
				httpServletRequest.setAttribute("testeInserir", "true");
			}

			// Manda a coleção do imovel_inserir_economia_popup
			// com a coleção de cliente, imovel e tipo da relação
			sessao.setAttribute("contIdentificadorTemp", new Integer(
					imovelEconomias.size() + 1));

		} else {
			throw new ActionServletException(
					"atencao.quantidade_ultrapassada.imovel_economia", null,
					new Integer(imovelSubCategoria.getImovelEconomias().size())
							.toString());
		}

		return retorno;
	}
}
