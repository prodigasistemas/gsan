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
 * Gera��o do relat�rio [UC0173] Gerar Relat�rio de Resumo do Faturamento
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
			"M�s/Ano da Arrecada��o");
		}
		
		if (opcaoTotalizacao == null || opcaoTotalizacao.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"Op��o de Totaliza��o ");
		}

		if (opcaoTotalizacao.trim().equals("gerenciaRegional")) {
			gerenciaRegional = Integer.parseInt(form.getGerenciaRegionalId());
			if (gerenciaRegional == null
					|| gerenciaRegional
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
						"Ger�ncia Regional");
			}
		} else if (opcaoTotalizacao.trim().equals("gerenciaRegionalLocalidade")) {
			gerenciaRegional = Integer.parseInt(form
					.getGerenciaRegionalporLocalidadeId());
			if (gerenciaRegional == null
					|| gerenciaRegional
							.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
						"Ger�ncia Regional");
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
				throw new ActionServletException("atencao.required", null, "Munic�pio ");

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
				throw new ActionServletException("atencao.required", null, "Unidade de Neg�cio ");

			}

			unidadeNegocio = Integer.parseInt(idUnidadeNegocio);
		}
		
		
		int mesAnoInteger = Integer.parseInt(mesAno.substring(0, 2)
				+ mesAno.substring(3, 7));

		// Verificar se o Ano/M�s est� valido [FS0002]
		if (!Util.validarMesAno(mesAno)) {
			// Se deu algum erro neste ponto, indica falha no sistema
			throw new ActionServletException("erro.sistema");
		}

		Fachada fachada = Fachada.getInstancia();

		// [FS0002] Verificar refer�ncia do faturamento
		fachada.verificarReferenciaFaturamentoCorrente(mesAno);

		// Parte que vai mandar o relat�rio para a tela

		// cria uma inst�ncia da classe do relat�rio
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
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
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
			// a localidade n�o foi encontrada
			throw new ActionServletException("atencao.localidade.inexistente");

		}
	}
	
	private void pesquisarMunicipio(String idMunicipio, HttpServletRequest httpServletRequest) {

		Fachada fachada = Fachada.getInstancia();

		// Pesquisa o munic�pio na base
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, idMunicipio));
		Collection<Municipio> municipioPesquisado = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());

		if (municipioPesquisado == null || municipioPesquisado.isEmpty()) {
			// o munic�pio n�o foi encontrado
			throw new ActionServletException("atencao.municipio.inexistente");
		}
	}
}
