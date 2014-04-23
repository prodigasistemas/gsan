package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.bean.MovimentoArrecadadoresPorNSAHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioAcompanhamentoMovimentoArrecadadoresPorNSA;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioAcompanhamentoMovimentoArrecadadoresPorNSAAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		Integer codigoFormaArrecadacao = null;
		if(httpServletRequest.getAttribute("idFormaArrecadacao") != null && !httpServletRequest.getAttribute("idFormaArrecadacao").equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			codigoFormaArrecadacao = new Integer((String)httpServletRequest.getAttribute("idFormaArrecadacao"));
		}
		
		//AcompanharMovimentoArrecadadoresActionForm acompanharMovimentoArrecadadoresActionForm = (AcompanharMovimentoArrecadadoresActionForm) actionForm;

//		 cria uma instância da classe do relatório
		RelatorioAcompanhamentoMovimentoArrecadadoresPorNSA relatorio = new RelatorioAcompanhamentoMovimentoArrecadadoresPorNSA((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<Integer> colecaoIdsMovimentoArrecadador =(Collection) sessao.getAttribute("colecaoMovimentoArrecadador");
		Collection<MovimentoArrecadadoresPorNSAHelper> colecaoMovimentoArrecadadoresPorNSA = null;
		
		if(colecaoIdsMovimentoArrecadador != null && !colecaoIdsMovimentoArrecadador.isEmpty()){
          colecaoMovimentoArrecadadoresPorNSA = 
        	  fachada.gerarMovimentoArrecadadoresNSA(colecaoIdsMovimentoArrecadador, 
        			  codigoFormaArrecadacao);
		}else{
			 throw new ActionServletException("atencao.pesquisa.nenhumresultado");	
		}
		
		if(colecaoMovimentoArrecadadoresPorNSA != null && !colecaoMovimentoArrecadadoresPorNSA.isEmpty()){
			relatorio.addParametro("colecaoMovimentoArrecadadoresPorNSA", colecaoMovimentoArrecadadoresPorNSA);
		}else{
			 throw new ActionServletException("atencao.pesquisa.nenhumresultado");	
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));

/*		// Inicio da parte que vai mandar os parametros para o relatório
		String mesAnoReferencia = acompanharMovimentoArrecadadoresActionForm.getMesAnoReferencia();

		String idArrecadador = acompanharMovimentoArrecadadoresActionForm.getIdArrecadador();

		Arrecadador arrecadador = null;

		if (idArrecadador != null && !idArrecadador.equals("")) {
			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.ID, idArrecadador));
			filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection colecaoArrecadadores = fachada.pesquisar(filtroArrecadador,
					Arrecadador.class.getName());

			if (colecaoArrecadadores != null && !colecaoArrecadadores.isEmpty()) {
				// O arrecadador foi encontrado
				arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(colecaoArrecadadores);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Arrecadador");
			}

		} else {
			arrecadador = new Arrecadador();
		}

		String idArrecadacaoForma = acompanharMovimentoArrecadadoresActionForm.getIdFormaArrecadacao();
		
		ArrecadacaoForma arrecadacaoForma = null;

		if (idArrecadacaoForma != null
				&& !idArrecadacaoForma.equals("")
				&& !idArrecadacaoForma.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();

			filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(
					FiltroArrecadacaoForma.CODIGO, idArrecadacaoForma));

			Collection colecaoArrecadacaoForma = fachada.pesquisar(filtroArrecadacaoForma,
					ArrecadacaoForma.class.getName());

			if (colecaoArrecadacaoForma != null && !colecaoArrecadacaoForma.isEmpty()) {
				// O arrecadador foi encontrado
				arrecadacaoForma = (ArrecadacaoForma) Util.retonarObjetoDeColecao(colecaoArrecadacaoForma);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Forma de Arrecadação");
			}

		} else {
			arrecadacaoForma = new ArrecadacaoForma();
		}

		fachada.gerarResumoAcompanhamentoMovimentoArrecadadores((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), mesAnoReferencia, arrecadador, arrecadacaoForma);
		
		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Movimento Arrecadadores Enviado para Processamento", "Voltar",
				"/exibirAcompanharMovimentoArrecadadoresAction.do");*/

		// devolve o mapeamento contido na variável retorno
	try {
			
			retorno = processarExibicaoRelatorio(relatorio,
					tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
			
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
