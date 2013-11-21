package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;import gcom.util.ConstantesSistema;import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0826] Gerar Relatório Análise da Arrecação
 * 
 * @see gcom.gui.relatorio.arrecadacao.GerarRelatorioAnaliseArrecadacaoActionForm
 * @see gcom.gui.relatorio.arrecadacao.GerarRelatorioAnaliseArrecadacaoAction
 * @see gcom.relatorio.arrecadacao.RelatorioAnaliseArrecadacao
 * 
 * @author Victor Cisneiros
 * @date 23/07/2008
 */
public class ExibirGerarRelatorioAnaliseArrecadacaoAction extends GcomAction {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ActionForward retorno = mapping.findForward("exibirGerarRelatorioAnaliseArrecadacaoAction");
		Fachada fachada = Fachada.getInstancia();
		
		// ------------------------------
		// -- Por Arrecadador
		// ------------------------------
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");		filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.INDICADOR_USO,		ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection collectionArrecadador = fachada.pesquisar(filtroArrecadador, Arrecadador.class.getName());
		request.setAttribute("collectionArrecadador", collectionArrecadador);
		
		// ------------------------------
		// -- Por Formar de Arrecadação
		// ------------------------------
		FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
		Collection collectionArrecadacaoForma = fachada.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
		request.setAttribute("collectionArrecadacaoForma", collectionArrecadacaoForma);
		
		return retorno;
	}

}
