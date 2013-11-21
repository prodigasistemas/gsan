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
package gcom.gui.relatorio.faturamento;

import java.util.Collection;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioResumoFaturamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Geração do relatório [UC0173] Gerar Relatório de Resumo do Faturamento
 * 
 * @author Rodrigo
 */

public class GerarRelatorioResumoFaturamentoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		GerarRelatorioResumoFaturamentoActionForm form = (GerarRelatorioResumoFaturamentoActionForm) actionForm;

		String mesAno = form.getMesAno();
		Integer gerenciaRegional = null;
		Integer localidade = null;
		Integer municipio = null;
		Integer unidadeNegocio = null;
		String opcaoTotalizacao = form.getOpcaoTotalizacao();
		String opcaoRelatorio = form.getOpcaoRelatorio();
		
		if(mesAno == null || mesAno.equals("")){
			throw new ActionServletException("atencao.required", null,
			"Mês/Ano da Arrecadação");
		}
		
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
		
		if (opcaoTotalizacao.trim().equals("municipio")) {
			String codigoMunicipio = form.getCodigoMunicipio();

			if (codigoMunicipio == null || codigoMunicipio.equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null, "Município ");

			} else {
				pesquisarMunicipio(codigoMunicipio, httpServletRequest);
			}

			municipio = Integer.parseInt(codigoMunicipio);
		}
		
		if (opcaoTotalizacao.trim().equals("unidadeNegocio")) {
			String idUnidadeNegocio = form.getUnidadeNegocioId();

			if (idUnidadeNegocio == null
					|| idUnidadeNegocio
					.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Unidade de Negócio ");

			}

			unidadeNegocio = Integer.parseInt(idUnidadeNegocio);
		}
		
		
		int mesAnoInteger = Integer.parseInt(mesAno.substring(0, 2)
				+ mesAno.substring(3, 7));

		// Verificar se o Ano/Mês está valido [FS0002]
		if (!Util.validarMesAno(mesAno)) {
			// Se deu algum erro neste ponto, indica falha no sistema
			throw new ActionServletException("erro.sistema");
		}

		Fachada fachada = Fachada.getInstancia();

		// [FS0002] Verificar referência do faturamento
		fachada.verificarReferenciaFaturamentoCorrente(mesAno);

		// Parte que vai mandar o relatório para a tela

		// cria uma instância da classe do relatório
		RelatorioResumoFaturamento relatorioResumoFaturamento = new RelatorioResumoFaturamento(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioResumoFaturamento.addParametro("opcaoTotalizacao",	opcaoTotalizacao);
		relatorioResumoFaturamento.addParametro("mesAnoInteger", mesAnoInteger);
		relatorioResumoFaturamento.addParametro("localidade", localidade);
		relatorioResumoFaturamento.addParametro("gerenciaRegional", gerenciaRegional);
		relatorioResumoFaturamento.addParametro("unidadeNegocio",unidadeNegocio);
		relatorioResumoFaturamento.addParametro("opcaoRelatorio",opcaoRelatorio);
		relatorioResumoFaturamento.addParametro("municipio", municipio);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioResumoFaturamento.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioResumoFaturamento,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}
	
	private void pesquisarLocalidade(String idLocalidade,
			HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, idLocalidade));

		Collection<Localidade> localidadePesquisada = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());

		if (localidadePesquisada == null || localidadePesquisada.isEmpty()) {
			// a localidade não foi encontrada
			throw new ActionServletException("atencao.localidade.inexistente");

		}
	}
	
	private void pesquisarMunicipio(String idMunicipio, HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa o município na base
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMunicipio));
		Collection<Municipio> municipioPesquisado = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());

		if (municipioPesquisado == null || municipioPesquisado.isEmpty()) {
			// o município não foi encontrado
			throw new ActionServletException("atencao.municipio.inexistente");
		}
	}
}