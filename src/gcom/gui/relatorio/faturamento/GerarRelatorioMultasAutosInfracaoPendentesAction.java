package gcom.gui.relatorio.faturamento;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioMultasAutosInfracaoPendentes;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioMultasAutosInfracaoPendentesAction extends
ExibidorProcessamentoTarefaRelatorio{

	
	/**
	 * 
	 * 
	 * RM 944 - Relatório das Multas de Autos de Infração Pendentes
	 * 
	 * @author Hugo Azevedo 
	 * @date 11/07/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obtï¿½m a instância da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		GerarRelatorioMultasAutosInfracaoPendentesActionForm form = (GerarRelatorioMultasAutosInfracaoPendentesActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");		
		TarefaRelatorio relatorio = null;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio", true);
		
		//Recuperando os dados do formulário
		String idFuncionario = form.getIdFuncionario();
		String grupo = form.getGrupo();

		
		//Inteiros para os IDs
		Integer idFuncionarioINT = null;
		Integer grupoINT = null;
		if(!idFuncionario.equals("") )
			idFuncionarioINT  = new Integer(idFuncionario);
		else
			idFuncionarioINT = new Integer("0");
		if(Integer.parseInt(grupo) != -1)
			grupoINT = new Integer(grupo);
		else
			grupoINT = new Integer("0");

		
		//Nenhum parâmetro informado
		if(grupoINT.intValue() == 0 && idFuncionarioINT.intValue() == 0){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		//Recuperando o funcionário
		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
		filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, idFuncionario));
		Collection colecaoRetornoFunc = fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());
		Funcionario func = null;
		if(colecaoRetornoFunc.size() > 0){
			func = (Funcionario)Util.retonarObjetoDeColecao(colecaoRetornoFunc);
		}

		//Recuperando o grupo
		FiltroFaturamentoGrupo filtroFG = new FiltroFaturamentoGrupo();
		filtroFG.adicionarParametro(new ParametroSimples(FiltroFaturamentoGrupo.ID, grupo));
		Collection colecaoRetornoGru = fachada.pesquisar(filtroFG, FaturamentoGrupo.class.getName());
		FaturamentoGrupo fg = null;
		if(colecaoRetornoGru.size() > 0){
			fg = (FaturamentoGrupo)Util.retonarObjetoDeColecao(colecaoRetornoGru);
		}
		
		Collection colecaoMultas = fachada.pesquisarDadosRelatorioAutoInfracaoPendentes(grupoINT,idFuncionarioINT);
		
		
		//Nenhum parâmetro retornado
		if(colecaoMultas == null || colecaoMultas.isEmpty()){
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "relatório de multas de autos de infração pendentes");
		}
		
		
		
		relatorio = new RelatorioMultasAutosInfracaoPendentes(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		
		
		relatorio.addParametro("colecaoMultas",colecaoMultas);
		relatorio.addParametro("funcionario",func);
		relatorio.addParametro("grupo",fg);

		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		
		
		montarPaginaSucesso(httpServletRequest,
				"Relatório gerado com sucesso",
				"Gerar outro relatório",
				"exibirGerarRelatorioMultasAutosInfracaoPendentesAction.do?limparForm=OK");
		
		return retorno;
	}
}
