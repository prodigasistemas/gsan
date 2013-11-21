/**
 * 
 */
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
 * Rômulo Aurélio de Melo Souza Filho
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

package gcom.gui.relatorio.cobranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioPagamentosContasCobrancaEmpresa;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0868]-Gerar Relatório de Pagamentos das Contas em Cobranca por EmpresaS
 * 
 * @author Rômulo Aurélio
 * @date 08/01/2009
 */
public class GerarRelatorioPagamentosContasCobrancaEmpresaAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		GerarRelatorioPagamentosContasCobrancaEmpresaActionForm form = (GerarRelatorioPagamentosContasCobrancaEmpresaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Inicio da parte que vai mandar os parametros para o relatório

		Empresa empresa = new Empresa();

		String idEmpresa = form.getIdEmpresa();

		Integer gerenciaRegional = null;
		Integer localidade = null;
		Integer unidadeNegocio = null;

		String opcaoTotalizacao = form.getOpcaoTotalizacao();

		if (opcaoTotalizacao == null || opcaoTotalizacao.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"Opção de Totalização ");
		}

		if (opcaoTotalizacao.trim().equals("gerenciaRegional")) {
			
			gerenciaRegional = Integer.parseInt(form.getGerenciaRegionalId());
			if (gerenciaRegional == null
					|| gerenciaRegional
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
						"Gerência Regional");
			}
		} else if (opcaoTotalizacao.trim().equals("gerenciaRegionalLocalidade")) {
			gerenciaRegional = Integer.parseInt(form
					.getGerenciaRegionalporLocalidadeId());
			if (gerenciaRegional == null
					|| gerenciaRegional
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
						"Gerência Regional");
			}
		}

		if (opcaoTotalizacao.trim().equals("localidade")) {
			String codigoLocalidade = form.getCodigoLocalidade();

			if (codigoLocalidade == null
					|| codigoLocalidade.equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null,
						"Localidade ");

			} else {
				pesquisarLocalidade(codigoLocalidade, httpServletRequest);
			}

			localidade = Integer.parseInt(codigoLocalidade);
		}

		if (opcaoTotalizacao.trim().equals("unidadeNegocio")) {
			String idUnidadeNegocio = form.getUnidadeNegocioId();

			if (idUnidadeNegocio == null
					|| idUnidadeNegocio
							.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
						"Unidade de Negócio ");

			}

			unidadeNegocio = Integer.parseInt(idUnidadeNegocio);
		}
		


		Integer referenciaInicialFormatada = null;

		Integer referenciaFinalFormatada = null;

		RelatorioPagamentosContasCobrancaEmpresa relatorioPagamentosContasCobrancaEmpresa = new RelatorioPagamentosContasCobrancaEmpresa(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		if (idEmpresa != null
				&& !idEmpresa.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.ID, idEmpresa));

			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
			}

		}

		String dataInicialForm = form.getPeriodoComandoInicial();
		String dataFinalForm = form.getPeriodoComandoFinal();

		if (dataInicialForm != null && !dataInicialForm.equalsIgnoreCase("")) {
			referenciaInicialFormatada = Util
					.formatarMesAnoComBarraParaAnoMes(dataInicialForm);
			referenciaFinalFormatada = Util
					.formatarMesAnoComBarraParaAnoMes(dataFinalForm);
			if (referenciaInicialFormatada.compareTo(referenciaFinalFormatada) > 0) {
				throw new ActionServletException(
						"atencao.referencia.final.menor.referencia.inicial");
			}

			String opcaoRelatorio = form.getOpcaoRelatorio();

			relatorioPagamentosContasCobrancaEmpresa.addParametro(
					"opcaoTotalizacao", opcaoTotalizacao);

			relatorioPagamentosContasCobrancaEmpresa.addParametro("localidade",
					localidade);

			relatorioPagamentosContasCobrancaEmpresa.addParametro(
					"gerenciaRegional", gerenciaRegional);

			relatorioPagamentosContasCobrancaEmpresa.addParametro(
					"unidadeNegocio", unidadeNegocio);

			relatorioPagamentosContasCobrancaEmpresa.addParametro("empresa",
					empresa);

			relatorioPagamentosContasCobrancaEmpresa.addParametro(
					"opcaoRelatorio", opcaoRelatorio);

			relatorioPagamentosContasCobrancaEmpresa.addParametro(
					"referenciaPagamentoInicial", referenciaInicialFormatada);

			relatorioPagamentosContasCobrancaEmpresa.addParametro(
					"referenciaPagamentoFinal", referenciaFinalFormatada);
		}

		// Verifica se a pesquisa retorno algum resultado
		int qtdeResultados = fachada
				.pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(
						new Integer(idEmpresa), referenciaInicialFormatada,
						referenciaFinalFormatada);

		if (qtdeResultados == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");

		}
		// Flag para tela de sucesso apos a tela de espera de processamento de relatorio
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioPagamentosContasCobrancaEmpresa.addParametro(
				"tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		retorno = processarExibicaoRelatorio(
				relatorioPagamentosContasCobrancaEmpresa, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

	/**
	 * Pesquisa uma localidade informada e prepara os dados para exibição na
	 * tela
	 * 
	 */
	private void pesquisarLocalidade(String idLocalidade,
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, idLocalidade));

		Collection<Localidade> localidadePesquisada = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());

		// Se nenhuma localidade for encontrada a mensagem é enviada para a
		// página
		if (localidadePesquisada == null || localidadePesquisada.isEmpty()) {
			// [FS0001 - Verificar existência de dados]
			httpServletRequest.setAttribute("codigoLocalidade", "");
			httpServletRequest.setAttribute("descricaoLocalidade",
					"Localidade Inexistente".toUpperCase());
		}

		// obtem o imovel pesquisado
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = localidadePesquisada.iterator().next();
			// Manda a Localidade pelo request
			httpServletRequest.setAttribute("codigoLocalidade", idLocalidade);
			httpServletRequest.setAttribute("descricaoLocalidade", localidade
					.getDescricao());
		}

	}

}
