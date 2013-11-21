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
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsavel para atualizar o imóvel economia na coleção do cliente
 * imóvel economia
 * 
 * @author Sávio Luiz
 * @created 20 de Maio de 2004
 */
public class AtualizarEconomiaPopupAction extends GcomAction {
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
				.findForward("atualizarEconomiaPopup");

		// Obtém o action form
		EconomiaPopupActionForm economiaPopupActionForm = (EconomiaPopupActionForm) actionForm;

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoClientesImoveisEconomia = null;

		Collection colecaoImovelEconomiasModificadas = null;

		ImovelEconomia imovelEconomia = (ImovelEconomia) sessao
				.getAttribute("imovelEconomia");

		Fachada fachada = Fachada.getInstancia();

		// Coleção vinda do imovel_inserir_economia_popup
		// com a coleção de cliente, imovel e tipo da relação
		if (sessao.getAttribute("colecaoClientesImoveisEconomia") != null) {
			colecaoClientesImoveisEconomia = (Collection) sessao
					.getAttribute("colecaoClientesImoveisEconomia");

		} else {
			colecaoClientesImoveisEconomia = new ArrayList();
		}

		Imovel imovel = (Imovel) sessao.getAttribute("imovel");

		// pega da sessão a coleção para os imoveis economias inseridos e/ou
		// modificados
		colecaoImovelEconomiasModificadas = (Collection) sessao
				.getAttribute("colecaoImovelEconomiasModificadas");

		// Cria objeto areaConstruida
		AreaConstruidaFaixa areaConstruidaFaixa = null;
		BigDecimal  areaConstruida = null;

		Integer areaContruidaFaixaForm = (new Integer(economiaPopupActionForm
				.getIdAreaConstruidaFaixa()));

		if (areaContruidaFaixaForm != null
				&& areaContruidaFaixaForm.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			areaConstruidaFaixa = new AreaConstruidaFaixa();
			areaConstruidaFaixa.setId(areaContruidaFaixaForm);
			imovelEconomia.setAreaConstruidaFaixa(areaConstruidaFaixa);
			if (economiaPopupActionForm.getAreaConstruida() != null
					&& !economiaPopupActionForm.getAreaConstruida().equals("")) {
				
				String areaConstuidaPrrv = economiaPopupActionForm.getAreaConstruida().replace(".", "");
				areaConstruida = (new BigDecimal(areaConstuidaPrrv.replace(",",".")));
				imovelEconomia.setAreaConstruida(areaConstruida);
			} else {
				imovelEconomia.setAreaConstruida(null);
			}
		} else {
			if (economiaPopupActionForm.getAreaConstruida() != null
					&& !economiaPopupActionForm.getAreaConstruida().equals("")) {
				Collection areasConstruidasFaixas = null;
				
				String areaConstuidaPrrv = economiaPopupActionForm.getAreaConstruida().replace(".", "");
				areaConstruida = (new BigDecimal(areaConstuidaPrrv.replace(",",".")));
//				areaConstruida = (new BigDecimal(economiaPopupActionForm
//						.getAreaConstruida().toString().replace(",",".")));

				imovelEconomia.setAreaConstruida(areaConstruida);

				// Filtro AreaConstruidaFaixa
				FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa();

				// fazer a parte de filtro adicionar parametro maior que
				// e filtro adicionar parametro menos que
				filtroAreaConstruidaFaixa
						.adicionarParametro(new ParametroSimples(
								FiltroAreaConstruidaFaixa.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				filtroAreaConstruidaFaixa.adicionarParametro(new MaiorQue(
						FiltroAreaConstruidaFaixa.MAIOR_FAIXA, areaConstruida));

				filtroAreaConstruidaFaixa.adicionarParametro(new MenorQue(
						FiltroAreaConstruidaFaixa.MENOR_FAIXA, areaConstruida));

				areasConstruidasFaixas = fachada.pesquisar(
						filtroAreaConstruidaFaixa, AreaConstruidaFaixa.class
								.getName());

				if (areasConstruidasFaixas != null
						&& !areasConstruidasFaixas.isEmpty()) {
					Iterator areaContruidaFaixaIterator = areasConstruidasFaixas
							.iterator();

					areaConstruidaFaixa = (AreaConstruidaFaixa) areaContruidaFaixaIterator
							.next();
					imovelEconomia.setAreaConstruidaFaixa(areaConstruidaFaixa);

				} else {
					imovelEconomia.setAreaConstruidaFaixa(null);
				}

			} else {
				imovelEconomia.setAreaConstruidaFaixa(null);
				imovelEconomia.setAreaConstruida(null);
			}
		}

		// recupera os dados do form
		imovelEconomia.setComplementoEndereco(economiaPopupActionForm
				.getComplementoEndereco());
		
		if (economiaPopupActionForm.getNumeroMorador() != null && !economiaPopupActionForm.getNumeroMorador().equalsIgnoreCase("")){
			imovelEconomia.setNumeroMorador(new Short(economiaPopupActionForm
					.getNumeroMorador().toString()));
		} else {
			imovelEconomia.setNumeroMorador(null);
		}
		
		if (economiaPopupActionForm.getNumeroPontosUtilizacao() != null && !economiaPopupActionForm.getNumeroPontosUtilizacao().equalsIgnoreCase("")){
			imovelEconomia
					.setNumeroPontosUtilizacao(new Short(economiaPopupActionForm
							.getNumeroPontosUtilizacao().toString()));
		}else{
			imovelEconomia.setNumeroPontosUtilizacao(null);
		}

		// verifica se existe um numero de iptu
		if (economiaPopupActionForm.getNumeroIptu() != null
				&& !economiaPopupActionForm.getNumeroIptu().equals("")) {

			// verifica se existe no imovel, no imovelEconomia ou na coleção que
			// será
			// inserida se existe algum iptu cadastrado com o numero do iptu
			// digitado no
			// mesmo municipio.
			fachada.verificarExistenciaIPTU(colecaoImovelEconomiasModificadas,
					imovel, economiaPopupActionForm.getNumeroIptu(),
					imovelEconomia.getUltimaAlteracao());

			imovelEconomia.setNumeroIptu(new BigDecimal(economiaPopupActionForm
					.getNumeroIptu()));
		} else {
			imovelEconomia.setNumeroIptu(null);
		}

		if (economiaPopupActionForm.getNumeroCelpe() != null
				&& !economiaPopupActionForm.getNumeroCelpe().equals("")) {

			// verifica se existe no imovel, no imovelEconomia ou na coleção que
			// será
			// inserida se existe algum iptu cadastrado com o numero da celpe
			// digitado.
			fachada.verificarExistenciaCelpe(colecaoImovelEconomiasModificadas,
					imovel, economiaPopupActionForm.getNumeroCelpe(),
					imovelEconomia.getUltimaAlteracao());

			imovelEconomia.setNumeroCelpe(new Long(economiaPopupActionForm
					.getNumeroCelpe()));
		} else {
			imovelEconomia.setNumeroCelpe(null);
		}

		imovelEconomia.getClienteImovelEconomias().addAll(
				colecaoClientesImoveisEconomia);

		if (imovelEconomia.getCodigoModificado() == 0) {
			imovelEconomia.setCodigoModificado(imovelEconomia.getHashCode());
			colecaoImovelEconomiasModificadas.add(imovelEconomia);
		}

		// para incluir mais relações entre cliente e imoveis, se preciso
		sessao.setAttribute("colecaoClientesImoveisEconomia", new ArrayList());

		// inicializa o property idClienteImovelUsuarioEconomias para a inclusão
		// de
		// novas relações entre cliente imovel do tipo usuário
		economiaPopupActionForm.setIdClienteImovelUsuario(null);

		if (httpServletRequest.getParameter("testeInserir").equalsIgnoreCase(
				"true")) {
			httpServletRequest.setAttribute("testeInserir", "true");
		}

		// inicializa todos os propertys para nulo
		economiaPopupActionForm.setComplementoEndereco(null);
		economiaPopupActionForm.setNumeroPontosUtilizacao(null);
		economiaPopupActionForm.setNumeroMorador(null);
		economiaPopupActionForm.setNumeroIptu(null);
		economiaPopupActionForm.setNumeroCelpe(null);
		economiaPopupActionForm.setAreaConstruida(null);
		economiaPopupActionForm.setIdAreaConstruidaFaixa(null);

		return retorno;
	}
}
