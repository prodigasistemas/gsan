package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.micromedicao.RelatorioAcompanhamentoLeiturista;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarRelatorioAcompanhamentoLeituristaAction extends ExibidorProcessamentoTarefaRelatorio {
	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Seta o mapeamento de retorno
		ActionForward retorno = null;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		Usuario usuario = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
		
		FiltrarRelatorioAcompanhamentoLeituristaForm form = (FiltrarRelatorioAcompanhamentoLeituristaForm) actionForm;
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Integer anoMesReferencia = 0;
		Integer rotas[];
		String idEmpresa = "";
		String idLeiturista = "";
		
		
		if(form.getMesAno()!=null &&
				!form.getMesAno().equals("")){
			Integer anoMesFaturamento = Util.somaUmMesAnoMesReferencia(sistemaParametro.getAnoMesFaturamento());
			anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAno());
			
			if(anoMesReferencia.compareTo(anoMesFaturamento)>0){
				throw new ActionServletException("atencao.mesano_maior_faturamento");
			}	
		}
		
		if(form.getRotasSelecionadas()!=null && form.getRotasSelecionadas().length!=0
				&& form.getRotasSelecionadas()[0]!=-1){
			rotas = new Integer[form.getRotasSelecionadas().length];
			rotas = form.getRotasSelecionadas();
		}else{
			rotas = new Integer[1];
		}
		if(form.getIdEmpresa()!=null && !form.getIdEmpresa().equals("-1")){
			idEmpresa = form.getIdEmpresa();
		}
		if(form.getIdLeiturista()!=null && !form.getIdLeiturista().equals("-1")){
			idLeiturista = form.getIdLeiturista();
		}
		
		
	 	Collection colecaoRotas = fachada.selecionarRotasRelatorioAcompanhamentoLeiturista(
	 			anoMesReferencia,
	 			rotas,
	 			idEmpresa,
	 			idLeiturista);
	 	
	 	if(colecaoRotas!=null &&
	 			colecaoRotas.isEmpty()){
	 		throw new ActionServletException("atencao.pesquisa.nenhumresultado");
	 	}
		
		RelatorioAcompanhamentoLeiturista relatorio = new RelatorioAcompanhamentoLeiturista(usuario);
		
		relatorio.addParametro("colecaoRotas",colecaoRotas);
		relatorio.addParametro("filtrarRelatorioAcompanhamentoLeituristaForm",form);
		
		String tipoRelatorio = null;
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		
		
		return retorno;
	}
}
