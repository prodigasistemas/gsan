package gcom.gui.relatorio.financeiro;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.financeiro.RelatorioResumoDevedoresDuvidosos;
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
 * [UC0487] Gerar Relatório de Resumo de Devedores Duvidosos
 * 
 * @author Vivianne Sousa
 * @created 20/07/2007
 */

public class GerarRelatorioResumoDevedoresDuvidososAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		GerarRelatorioResumoDevedoresDuvidososActionForm form = (GerarRelatorioResumoDevedoresDuvidososActionForm) actionForm;

		String mesAno = form.getMesAno();
		Integer gerenciaRegional = null;
		Integer localidade = null;
		Integer unidadeNegocio = null;
		String opcaoTotalizacao = form.getOpcaoTotalizacao();
		
		if(mesAno == null || mesAno.equals("")){
			throw new ActionServletException("atencao.required", null, "Mês/Ano da Arrecadação");
		}
		
		if (opcaoTotalizacao == null || opcaoTotalizacao.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null, "Opção de Totalização ");
		}

		if (opcaoTotalizacao.trim().equals("gerenciaRegional")) {
			gerenciaRegional = Integer.parseInt(form.getGerenciaRegionalId());
			if (gerenciaRegional == null || gerenciaRegional.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Gerência Regional");
			}
		} else if (opcaoTotalizacao.trim().equals("gerenciaRegionalLocalidade")) {
			gerenciaRegional = Integer.parseInt(form.getGerenciaRegionalporLocalidadeId());
			if (gerenciaRegional == null || gerenciaRegional.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Gerência Regional");
			}
		}

		if (opcaoTotalizacao.trim().equals("localidade")) {
			String codigoLocalidade = form.getCodigoLocalidade();

			if (codigoLocalidade == null || codigoLocalidade.equalsIgnoreCase("")) {
				throw new ActionServletException("atencao.required", null, "Localidade ");

			} else {
				pesquisarLocalidade(codigoLocalidade, httpServletRequest);
			}

			localidade = Integer.parseInt(codigoLocalidade);
		}
		
		
		if (opcaoTotalizacao.trim().equals("unidadeNegocio")) {
			String idUnidadeNegocio = form.getUnidadeNegocioId();

			if (idUnidadeNegocio == null || idUnidadeNegocio.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null, "Unidade de Negócio ");

			}

			unidadeNegocio = Integer.parseInt(idUnidadeNegocio);
		}
		
		
		int mesAnoInteger = Integer.parseInt(mesAno.substring(0, 2) + mesAno.substring(3, 7));

		// Verificar se o Ano/Mês está valido [FS0002]
		if (!Util.validarMesAno(mesAno)) {
			// Se deu algum erro neste ponto, indica falha no sistema
			throw new ActionServletException("erro.sistema");
		}

		// [FS0002] Verificar Referência Contabil
		verificarReferenciaContabilCorrente(mesAno);

		// Parte que vai mandar o relatório para a tela

		// cria uma instância da classe do relatório
		RelatorioResumoDevedoresDuvidosos relatorioResumoDevedoresDuvidosos = new RelatorioResumoDevedoresDuvidosos(
				(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioResumoDevedoresDuvidosos.addParametro("opcaoTotalizacao",	opcaoTotalizacao);
		relatorioResumoDevedoresDuvidosos.addParametro("mesAnoInteger", mesAnoInteger);
		relatorioResumoDevedoresDuvidosos.addParametro("localidade", localidade);
		relatorioResumoDevedoresDuvidosos.addParametro("gerenciaRegional", gerenciaRegional);
		relatorioResumoDevedoresDuvidosos.addParametro("unidadeNegocio",unidadeNegocio);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioResumoDevedoresDuvidosos.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioResumoDevedoresDuvidosos,
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
	
	//[FS0002] Verificar Referência Contabil
	public void verificarReferenciaContabilCorrente(String anoMes){
		
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		boolean verificacao = Util.compararAnoMesReferencia(anoMes
				.substring(3, 7)
				+ anoMes.substring(0, 2), sistemaParametro
				.getAnoMesArrecadacao().toString(), ">");

		if (verificacao) {
			String anoMesSistema = sistemaParametro.getAnoMesArrecadacao().toString();

			throw new ActionServletException("atencao.mes_ano.contabil.superior", null, 
					anoMesSistema.substring(0, 2)+ "/" + anoMesSistema.substring(3, 7));

		}

	}
	
}
