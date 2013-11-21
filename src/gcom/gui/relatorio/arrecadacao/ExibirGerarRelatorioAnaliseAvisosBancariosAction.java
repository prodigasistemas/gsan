package gcom.gui.relatorio.arrecadacao;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

/**
 * [UC0827] Gerar Relatório Análise dos Avisos Bancários
 * 
 * @see gcom.gui.relatorio.arrecadacao.GerarRelatorioAnaliseAvisosBancariosActionForm
 * @see gcom.gui.relatorio.arrecadacao.GerarRelatorioAnaliseAvisosBancariosAction
 * @see gcom.relatorio.arrecadacao.RelatorioAnaliseAvisosBancarios
 * 
 * @author Victor Cisneiros
 * @date 30/07/2008
 */
public class ExibirGerarRelatorioAnaliseAvisosBancariosAction extends GcomAction {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ActionForward retorno = mapping.findForward("exibirGerarRelatorioAnaliseAvisosBancariosAction");
		Fachada fachada = Fachada.getInstancia();
		
		// ------------------------------
		// -- Por Arrecadador
		// ------------------------------
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
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
