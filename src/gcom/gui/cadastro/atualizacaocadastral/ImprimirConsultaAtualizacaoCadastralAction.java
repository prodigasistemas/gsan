package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.transacao.RepositorioTransacaoUtil;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ImprimirConsultaAtualizacaoCadastralAction extends GcomAction {
	
	public  List<ImovelRelatorioAtualizacaoCadastral> itensRelatorio = null;
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) {
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> atualizacoes = 
				(Collection<ConsultarMovimentoAtualizacaoCadastralHelper>) sessao.getAttribute("colecaoConsultarMovimentoAtualizacaoCadastralHelper");

		FiltrarAlteracaoAtualizacaoCadastralActionHelper filtro = (FiltrarAlteracaoAtualizacaoCadastralActionHelper) sessao.getAttribute("filtroMovimentoAtualizacaoCadastral");
		
		RepositorioTransacaoUtil repo = new RepositorioTransacaoUtil();

		itensRelatorio = repo.relatorioConsultaAtualizacaoCadastral(atualizacoes, filtro);
		
		for (ImovelRelatorioAtualizacaoCadastral item : itensRelatorio) {
			System.out.println(item);
		}

		TarefaRelatorio tarefa = new TarefaRelatorio(null, null) {
			private static final long serialVersionUID = 1L;

			public Object executar() throws TarefaException {
				RelatorioDataSource ds = new RelatorioDataSource(itensRelatorio);
				byte[] dados = gerarRelatorio(ConstantesRelatorios.RELATORIO_CONSULTA_ATUALIZACAO_CADASTRAL, getParametros(), ds, TarefaRelatorio.TIPO_PDF);
				exibirRelatorio(httpServletResponse, dados);
				return null;
			}
			
			public void agendarTarefaBatch() {
			}
			
			public int calcularTotalRegistrosRelatorio() {
				return 0;
			}
		};
		
		tarefa.executar();

		return null;
	}
	
	private Map<String, String> getParametros() {
		Map<String, String> parametros = new HashMap<String, String>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		return parametros;
	}
}
