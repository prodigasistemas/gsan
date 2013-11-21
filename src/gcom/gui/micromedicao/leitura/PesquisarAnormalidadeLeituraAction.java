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
package gcom.gui.micromedicao.leitura;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PesquisarAnormalidadeLeituraAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("listaLeituraAnormalidades");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarLeituraAnormalidadeActionForm pesquisarLeituraAnormalidadeActionForm = (PesquisarLeituraAnormalidadeActionForm) actionForm;

		// Recupera os parametros

		String descricao = pesquisarLeituraAnormalidadeActionForm
				.getDescricao();
		String anormalidadeHidrometro = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadeRelativaHidrometro();
		String anormalidadeAceitaSemHidrometro = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadeSemHidrometro();
		String anormalidadeUsoSistema = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadeRestritoSistema();
		String anormalidadePerdaTarifaSocial = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadePerdaTarifaSocial();
		String anormalidadeOrdemServicoAutomatica = pesquisarLeituraAnormalidadeActionForm
				.getAnormalidadeOrdemServicoAutomatica();
		Integer tipoServico =  new Integer(pesquisarLeituraAnormalidadeActionForm
				.getTipoServico());
		Integer consumoLeituraInformada = new Integer(pesquisarLeituraAnormalidadeActionForm
				.getConsumoCobradoLeituraInformada());
		Integer consumoLeituraNaoInformada = new Integer(pesquisarLeituraAnormalidadeActionForm
				.getConsumoCobradoLeituraNaoInformada());
		Integer leituraFaturamentoLeituraInformada = new Integer(pesquisarLeituraAnormalidadeActionForm
				.getLeituraFaturamentoLeituraInformada());
		Integer leituraFaturamentoLeituraNaoInformada = new Integer(pesquisarLeituraAnormalidadeActionForm
				.getLeituraFaturamentoLeituraNaoInformada());

		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade(
				FiltroLeituraAnormalidade.ID);

		boolean peloMenosUmParametroInformado = false;

		// Insere os parâmetros informados no filtro

		if (descricao != null && !descricao.equals("")) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ComparacaoTexto(
					FiltroLeituraAnormalidade.DESCRICAO, descricao));
		}

		if (anormalidadeHidrometro != null
				&& !anormalidadeHidrometro.equals("") && 
				!(new Short(anormalidadeHidrometro)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_RELATIVO_HIDROMETRO,
					anormalidadeHidrometro));
		}

		if (anormalidadeAceitaSemHidrometro != null
				&& !anormalidadeAceitaSemHidrometro.equals("") && !(new Short(anormalidadeAceitaSemHidrometro)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_IMOVEL_SEM_HIDROMETRO,
					anormalidadeAceitaSemHidrometro));
		}

		if (anormalidadeUsoSistema != null
				&& !anormalidadeUsoSistema.equals("") && !(new Short(anormalidadeUsoSistema)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_USO_SISTEMA,
					anormalidadeUsoSistema));
		}

		if (anormalidadePerdaTarifaSocial != null
				&& !anormalidadePerdaTarifaSocial.equals("") && !(new Short(anormalidadePerdaTarifaSocial)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_PERDA_TARIFA_SOCIAL,
					anormalidadePerdaTarifaSocial));
		}

		if (anormalidadeOrdemServicoAutomatica != null
				&& !anormalidadeOrdemServicoAutomatica.equals("") && !(new Short(anormalidadeOrdemServicoAutomatica)).equals(ConstantesSistema.TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.INDICADOR_EMISSAO_ORDEM_SERVICO,
					anormalidadeOrdemServicoAutomatica));
		}

		if (tipoServico != null
				&& tipoServico.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_TIPO_SERVICO, tipoServico));
		}

		if (consumoLeituraInformada != null
				&& consumoLeituraInformada.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_CONSUMO_A_COBRAR_COM_LEITURA,
					consumoLeituraInformada));
		}

		if (consumoLeituraNaoInformada != null
				&& consumoLeituraNaoInformada.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_CONSUMO_A_COBRAR_SEM_LEITURA,
					consumoLeituraNaoInformada));
		}

		if (leituraFaturamentoLeituraInformada != null
				&& leituraFaturamentoLeituraInformada.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_LEITURA_A_FATURAR_COM_LEITURA,
					leituraFaturamentoLeituraInformada));
		}

		if (leituraFaturamentoLeituraNaoInformada != null
				&& leituraFaturamentoLeituraNaoInformada.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			peloMenosUmParametroInformado = true;
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(
					FiltroLeituraAnormalidade.ID_LEITURA_A_FATURAR_SEM_LEITURA,
					leituraFaturamentoLeituraNaoInformada));
		}


		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		
		Collection leituraAnormalidades = null;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// adiciona as dependências para serem mostradas na página
		filtroLeituraAnormalidade
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoSemleitura");
		filtroLeituraAnormalidade
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeConsumoComleitura");
		filtroLeituraAnormalidade
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraSemleitura");
		filtroLeituraAnormalidade
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeLeituraComleitura");

		// Faz a busca das empresas
		leituraAnormalidades = fachada.pesquisar(filtroLeituraAnormalidade,
				LeituraAnormalidade.class.getName());

		if (leituraAnormalidades == null || leituraAnormalidades.isEmpty()) {

			// Nenhum municipio cadastrado
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null,
					"Anormalidade de Leitura");
		} else if (leituraAnormalidades.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
			// Muitos registros encontrados
			throw new ActionServletException(
					"atencao.pesquisa.muitosregistros");
		} else {
			if (leituraAnormalidades.size() == ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
				httpServletRequest.setAttribute("limitePesquisa", "");
			}

			// Coloca a coleção na sessão
			sessao.setAttribute("leituraAnormalidades", leituraAnormalidades);

		}

		return retorno;
	}

}
