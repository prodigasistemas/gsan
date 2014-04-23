package gcom.gui.relatorio.operacional;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.operacional.FiltrarProducaoAguaActionForm;
import gcom.operacional.FiltroProducaoAgua;
import gcom.operacional.ProducaoAgua;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.operacional.RelatorioManterProducaoAgua;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vinicius Medeiros
 * @version 1.0
 */

public class GerarRelatorioProducaoAguaManterAction extends
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Cria uma instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarProducaoAguaActionForm filtrarProducaoAguaActionForm = (FiltrarProducaoAguaActionForm) actionForm;

		FiltroProducaoAgua filtroProducaoAgua = (FiltroProducaoAgua) sessao
				.getAttribute("filtroProducaoAgua");

		filtroProducaoAgua.adicionarCaminhoParaCarregamentoEntidade(FiltroProducaoAgua.LOCALIDADE);
		
		// Inicio da parte que vai mandar os parametros para o relatório

		ProducaoAgua producaoAguaParametros = new ProducaoAgua();

		String id = null;

		String idProducaoAguaPesquisar = (String) filtrarProducaoAguaActionForm
				.getId();

		if (idProducaoAguaPesquisar != null && !idProducaoAguaPesquisar.equals("")) {
			id = idProducaoAguaPesquisar;
		}
		
		String anoMesReferencia = filtrarProducaoAguaActionForm
		.getAnoMesReferencia();
		
		if(anoMesReferencia != null && !anoMesReferencia.trim().equals("")){
        	String mes = anoMesReferencia.substring(0, 2);
        	String ano = anoMesReferencia.substring(3, 7);
        	String anoMes = ano+mes;
			
        	anoMesReferencia = anoMes;
        	
		}
		
		if (filtrarProducaoAguaActionForm.getLocalidadeID() != null && !filtrarProducaoAguaActionForm.getLocalidadeID().equals("")) {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, filtrarProducaoAguaActionForm.getLocalidadeID()));
			
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
				producaoAguaParametros.setLocalidade(localidade);
			}
			
		}
		
		BigDecimal volumeProduzido = null;
		
		if (filtrarProducaoAguaActionForm.getVolumeProduzido() != null && !filtrarProducaoAguaActionForm.getVolumeProduzido().equals("")){
			
			volumeProduzido = new BigDecimal("" + filtrarProducaoAguaActionForm.getVolumeProduzido().replace(",","."));
		}
		
		// seta os parametros que serão mostrados no relatório


		producaoAguaParametros.setId(id == null ? null : new Integer(
				id));
		if(anoMesReferencia == null || anoMesReferencia.equals("")){
			producaoAguaParametros.setAnoMesReferencia(null);
		} else {
			producaoAguaParametros.setAnoMesReferencia(new Integer(anoMesReferencia));
		}
		
		producaoAguaParametros.setVolumeProduzido(volumeProduzido);
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterProducaoAgua relatorioManterProducaoAgua = new RelatorioManterProducaoAgua(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		relatorioManterProducaoAgua.addParametro("filtroProducaoAgua",
				filtroProducaoAgua);
		relatorioManterProducaoAgua.addParametro(
				"producaoAguaParametros", producaoAguaParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterProducaoAgua.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(
					relatorioManterProducaoAgua, tipoRelatorio,
					httpServletRequest, httpServletResponse, actionMapping);

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
