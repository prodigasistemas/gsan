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
package gcom.gui.relatorio.cadastro.localidade;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cadastro.localidade.FiltrarQuadraActionForm;
import gcom.micromedicao.Rota;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.localidade.RelatorioManterQuadra;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Rafael Corrêa
 * @created 7 de Novembro de 2005
 */
public class GerarRelatorioQuadraManterAction extends ExibidorProcessamentoTarefaRelatorio {

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

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarQuadraActionForm filtrarQuadraActionForm = (FiltrarQuadraActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		FiltroQuadra filtroQuadra = (FiltroQuadra) sessao.getAttribute("filtroQuadra");

		// Inicio da parte que vai mandar os parametros para o relatório

		Quadra quadraParametros = new Quadra();

		String idLocalidade = (String) filtrarQuadraActionForm
				.getLocalidadeID();
		String nomeLocalidade = (String) filtrarQuadraActionForm
				.getLocalidadeNome();

		Localidade localidade = new Localidade();

		if (idLocalidade != null && !idLocalidade.equals("")) {
			if (nomeLocalidade != null && !nomeLocalidade.equals("")) {
				localidade.setId(new Integer(idLocalidade));
				localidade.setDescricao(nomeLocalidade);

			} else {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, idLocalidade));

				Collection localidades = fachada.pesquisar(filtroLocalidade,
						Localidade.class.getName());

				if (localidades != null && !localidades.isEmpty()) {
					// A localidade foi encontrada
					Iterator localidadeIterator = localidades.iterator();

					Localidade localidadePesquisa = (Localidade) localidadeIterator
							.next();

					localidade.setId(localidadePesquisa.getId());
					localidade.setDescricao(localidadePesquisa.getDescricao());

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Localidade");
				}
			}

		}

		String idSetorComercial = (String) filtrarQuadraActionForm
				.getSetorComercialCD();
		String nomeSetorComercial = (String) filtrarQuadraActionForm
				.getSetorComercialNome();

		SetorComercial setorComercial = new SetorComercial();

		if (idSetorComercial != null && !idSetorComercial.equals("")) {
			if (nomeSetorComercial != null && !nomeSetorComercial.equals("")) {
				setorComercial.setCodigo(new Integer(idSetorComercial));
				setorComercial.setDescricao(nomeSetorComercial);
			} else {
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, idSetorComercial));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));

				Collection setoresComerciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				if (setoresComerciais != null && !setoresComerciais.isEmpty()) {
					// O Setor Comercial foi encontrado
					Iterator setorComercialIterator = setoresComerciais
							.iterator();

					SetorComercial setorComercialPesquisa = (SetorComercial) setorComercialIterator
							.next();

					setorComercial.setCodigo(setorComercialPesquisa.getCodigo());
					setorComercial.setDescricao(setorComercialPesquisa
							.getDescricao());

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Setor Comercial");
				}
			}

		}

		String quadraNM = null;

		String quadraNMPesquisar = (String) filtrarQuadraActionForm
				.getQuadraNM();

		if (quadraNMPesquisar != null && !quadraNMPesquisar.equals("")) {
			quadraNM = quadraNMPesquisar;
		}

		Short indicadorDeUso = null;

		if (filtrarQuadraActionForm.getIndicadorUso() != null
				&& !filtrarQuadraActionForm.getIndicadorUso()
						.equals("")) {

			indicadorDeUso = new Short(filtrarQuadraActionForm
					.getIndicadorUso());
		}

		// seta os parametros que serão mostrados no relatório

		setorComercial.setLocalidade(localidade);
		quadraParametros.setSetorComercial(setorComercial);
		if (quadraNM != null) {
			quadraParametros
					.setNumeroQuadra(Integer
							.parseInt(quadraNM));
		} else {
			quadraParametros
			.setNumeroQuadra(-1);
		}
		
		if (filtrarQuadraActionForm.getCodigoRota() != null && !filtrarQuadraActionForm.getCodigoRota().trim().equals("")) {
			Rota rota = new Rota();
			rota.setCodigo(new Short(filtrarQuadraActionForm.getCodigoRota()));
			quadraParametros.setRota(rota);
		}

		quadraParametros.setIndicadorUso(indicadorDeUso);

		// Fim da parte que vai mandar os parametros para o relatório

			// cria uma instância da classe do relatório
			RelatorioManterQuadra relatorioManterQuadra = new RelatorioManterQuadra(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorioManterQuadra.addParametro("filtroQuadra", filtroQuadra);
			relatorioManterQuadra.addParametro("quadraParametros",
					quadraParametros);

			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioManterQuadra.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorioManterQuadra,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
