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
package gcom.gui.relatorio.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.micromedicao.hidrometro.HidrometroActionForm;
import gcom.micromedicao.FiltrarHidrometroHelper;
import gcom.micromedicao.hidrometro.FiltroHidrometro;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.FiltroHidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.micromedicao.hidrometro.FiltroHidrometroTipo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroTipo;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.micromedicao.hidrometro.RelatorioManterHidrometro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 26 de Setembro de 2005
 */
public class GerarRelatorioHidrometroManterAction extends ExibidorProcessamentoTarefaRelatorio {

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

		// HidrometroActionForm hidrometroActionForm =
		// (HidrometroActionForm) actionForm;
		HidrometroActionForm hidrometroActionForm = (HidrometroActionForm) sessao
				.getAttribute("HidrometroActionForm");

		Fachada fachada = Fachada.getInstancia();

		FiltroHidrometro filtroHidrometro = (FiltroHidrometro) sessao
				.getAttribute("filtroHidrometro");

		// Inicio da parte que vai mandar os parametros para o relatório

		String fixo = (String) sessao.getAttribute("fixo");

		String faixaInicial = (String) sessao.getAttribute("faixaInicial");

		String faixaFinal = (String) sessao.getAttribute("faixaFinal");
		
		FiltrarHidrometroHelper helper = (FiltrarHidrometroHelper) sessao.getAttribute("helper");

		Hidrometro hidrometroParametros = new Hidrometro();

		if (hidrometroActionForm != null) {

			String numero = null;

			String numeroPesquisar = (String) hidrometroActionForm
					.getNumeroHidrometro();

			if (numeroPesquisar != null && !numeroPesquisar.equals("")) {
				numero = numeroPesquisar;
			}

			Date dataAquisicao = null;

			String dataAquisicaoPesquisar = hidrometroActionForm
					.getDataAquisicao();

			if (dataAquisicaoPesquisar != null
					&& !dataAquisicaoPesquisar.equals("")) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

				try {
					dataAquisicao = format.parse(dataAquisicaoPesquisar);
				} catch (ParseException ex) {
					throw new ActionServletException("erro.sistema");
				}

			}

			Short anoFabricacao = null;

			if (hidrometroActionForm.getAnoFabricacao() != null
					&& !hidrometroActionForm.getAnoFabricacao().equals("")) {
				anoFabricacao = new Short(""
						+ hidrometroActionForm.getAnoFabricacao());
			}

			Short finalidade = null;

			if (hidrometroActionForm.getIndicadorMacromedidor() != null
					&& !hidrometroActionForm.getIndicadorMacromedidor().equals(
							"")) {

				finalidade = new Short(""
						+ hidrometroActionForm.getIndicadorMacromedidor());
			}

			String classeMetrologica = (String) hidrometroActionForm
					.getIdHidrometroClasseMetrologica();

			HidrometroClasseMetrologica hidrometroClasseMetrologica = null;

			if (classeMetrologica != null && !classeMetrologica.equals("")) {
				FiltroHidrometroClasseMetrologica filtroHidrometroClasseMetrologica = new FiltroHidrometroClasseMetrologica();

				filtroHidrometroClasseMetrologica
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroClasseMetrologica.ID,
								classeMetrologica));

				Collection classesMetrologicas = fachada.pesquisar(
						filtroHidrometroClasseMetrologica,
						HidrometroClasseMetrologica.class.getName());

				if (classesMetrologicas != null
						&& !classesMetrologicas.isEmpty()) {
					// Classe Metrologica Foi Encontrada
					Iterator classeMetrologicaIterator = classesMetrologicas
							.iterator();

					hidrometroClasseMetrologica = (HidrometroClasseMetrologica) classeMetrologicaIterator
							.next();

				} else {
					hidrometroClasseMetrologica = new HidrometroClasseMetrologica();
				}

			}

			String marca = (String) hidrometroActionForm.getIdHidrometroMarca();

			HidrometroMarca hidrometroMarca = null;

			if (marca != null && !marca.equals("")) {
				FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca();

				filtroHidrometroMarca.adicionarParametro(new ParametroSimples(
						FiltroHidrometroMarca.ID, marca));

				Collection marcas = fachada.pesquisar(filtroHidrometroMarca,
						FiltroHidrometroMarca.class.getName());

				if (marcas != null && !marcas.isEmpty()) {
					// Marca Foi Encontrada
					Iterator marcaIterator = marcas.iterator();

					hidrometroMarca = (HidrometroMarca) marcaIterator.next();

				} else {
					hidrometroMarca = new HidrometroMarca();
				}
			}

			String diametro = (String) hidrometroActionForm
					.getIdHidrometroDiametro();

			HidrometroDiametro hidrometroDiametro = null;

			if (diametro != null && !diametro.equals("")) {
				FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

				filtroHidrometroDiametro
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroDiametro.ID, diametro));

				Collection diametros = fachada.pesquisar(
						filtroHidrometroDiametro, HidrometroDiametro.class
								.getName());

				if (diametros != null && !diametros.isEmpty()) {
					// Diametro Foi Encontrado
					Iterator diametroIterator = diametros.iterator();

					hidrometroDiametro = (HidrometroDiametro) diametroIterator
							.next();

				} else {
					hidrometroDiametro = new HidrometroDiametro();
				}
			}

			String capacidade = (String) hidrometroActionForm
					.getIdHidrometroCapacidade();

			HidrometroCapacidade hidrometroCapacidade = null;

			if (capacidade != null && !capacidade.equals("")) {
				FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();

				filtroHidrometroCapacidade
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroCapacidade.ID, capacidade));

				Collection capacidades = fachada.pesquisar(
						filtroHidrometroCapacidade, HidrometroCapacidade.class
								.getName());

				if (capacidades != null && !capacidades.isEmpty()) {
					// Capacidade Foi Encontrada
					Iterator capacidadeIterator = capacidades.iterator();

					hidrometroCapacidade = (HidrometroCapacidade) capacidadeIterator
							.next();
				}
			} else {
				hidrometroCapacidade = new HidrometroCapacidade();
			}

			String tipo = (String) hidrometroActionForm.getIdHidrometroTipo();

			HidrometroTipo hidrometroTipo = null;

			if (tipo != null && !tipo.equals("")) {
				FiltroHidrometroTipo filtroHidrometroTipo = new FiltroHidrometroTipo();

				filtroHidrometroTipo.adicionarParametro(new ParametroSimples(
						FiltroHidrometroTipo.ID, tipo));

				Collection tipos = fachada.pesquisar(filtroHidrometroTipo,
						HidrometroTipo.class.getName());

				if (tipos != null && !tipos.isEmpty()) {
					// Tipo Foi Encontrado
					Iterator tipoIterator = tipos.iterator();

					hidrometroTipo = (HidrometroTipo) tipoIterator.next();
				}
			} else {
				hidrometroTipo = new HidrometroTipo();
			}

			String idLocalArmazenagem = (String) hidrometroActionForm
					.getIdLocalArmazenagem();

			HidrometroLocalArmazenagem hidrometroLocalArmazenagem = null;

			if (idLocalArmazenagem != null && !idLocalArmazenagem.equals("")) {
				FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = new FiltroHidrometroLocalArmazenagem();

				filtroHidrometroLocalArmazenagem
						.adicionarParametro(new ParametroSimples(
								FiltroHidrometroLocalArmazenagem.ID,
								idLocalArmazenagem));

				Collection locaisArmazenagens = fachada.pesquisar(
						filtroHidrometroLocalArmazenagem,
						HidrometroLocalArmazenagem.class.getName());

				if (locaisArmazenagens != null && !locaisArmazenagens.isEmpty()) {
					// O Local de Armazenagem foi encontrado
					Iterator localArmazenagemIterator = locaisArmazenagens
							.iterator();

					hidrometroLocalArmazenagem = (HidrometroLocalArmazenagem) localArmazenagemIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Local Armazenagem");
				}

			} else {
				hidrometroLocalArmazenagem = new HidrometroLocalArmazenagem();
			}

			// seta os parametros que serão mostrados no relatório

			hidrometroParametros.setNumero(numero);
			hidrometroParametros.setDataAquisicao(dataAquisicao);
			hidrometroParametros.setAnoFabricacao(anoFabricacao);
			hidrometroParametros.setIndicadorMacromedidor(finalidade);
			hidrometroParametros
					.setHidrometroClasseMetrologica(hidrometroClasseMetrologica);
			hidrometroParametros.setHidrometroMarca(hidrometroMarca);
			hidrometroParametros.setHidrometroDiametro(hidrometroDiametro);
			hidrometroParametros.setHidrometroCapacidade(hidrometroCapacidade);
			hidrometroParametros.setHidrometroTipo(hidrometroTipo);
			hidrometroParametros
					.setHidrometroLocalArmazenagem(hidrometroLocalArmazenagem);
		}

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterHidrometro relatorioManterHidrometro = new RelatorioManterHidrometro(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		relatorioManterHidrometro.addParametro("filtroHidrometro",
				filtroHidrometro);
		relatorioManterHidrometro.addParametro("hidrometroParametros",
				hidrometroParametros);
		relatorioManterHidrometro.addParametro("fixo", fixo);
		relatorioManterHidrometro.addParametro("faixaInicial", faixaInicial);
		relatorioManterHidrometro.addParametro("faixaFinal", faixaFinal);
		
		relatorioManterHidrometro.addParametro("helper",helper);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterHidrometro.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterHidrometro,
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
