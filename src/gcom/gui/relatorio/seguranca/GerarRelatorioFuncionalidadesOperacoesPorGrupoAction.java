package gcom.gui.relatorio.seguranca;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.seguranca.FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper;
import gcom.relatorio.seguranca.RelatorioFuncionalidadesOperacoesPorGrupo;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1039] Gerar Relatório de Funcionalidades e Operacoes por Grupo.
 * 
 * @author Hugo Leonardo
 *
 * @date 15/07/2010
 */

public class GerarRelatorioFuncionalidadesOperacoesPorGrupoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioFuncionalidadesOperacoesPorGrupoActionForm form = 
			(GerarRelatorioFuncionalidadesOperacoesPorGrupoActionForm) actionForm;
		
		FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper helper = 
			new FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper();
		
		Fachada fachada = Fachada.getInstancia();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		// Grupo
		String[] idsGrupos = null;
		
		// caso seja a ação do botão imprimir 
		String  botao = httpServletRequest.getParameter("botao");
		
		Collection<Integer> colecaoGrupos = new ArrayList<Integer>();
		
		if(botao != null && !botao.equals("")){
			
			if(form.getIds() != null && form.getIds().length > 0){
				idsGrupos = form.getIds();
			}

			if (idsGrupos != null) {
				
				for (int i = 0; i < idsGrupos.length; i++) {
					if (!idsGrupos[i].equals("")) {
						FiltroGrupo filtroGrupo = new FiltroGrupo();
						filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.ID, idsGrupos[i]));
						
						Collection colecaoGrupo = fachada.pesquisar(filtroGrupo, Grupo.class.getName());
						
						if (colecaoGrupo != null && !colecaoGrupo.isEmpty()) {
							Grupo grupo = (Grupo) Util.retonarObjetoDeColecao(colecaoGrupo);
							colecaoGrupos.add(grupo.getId());
						}
					}
				}		
			}
		}else{
			String idGrupo = httpServletRequest.getParameter("idRegistroGrupo");
			if(idGrupo != null && !idGrupo.equals("")){
				
				colecaoGrupos.add(new Integer(idGrupo));
			}	
		}
		
		if(colecaoGrupos.size() > 0){
			helper.setGrupo(colecaoGrupos);
		}
 
		TarefaRelatorio relatorio = null;
		
		relatorio = new RelatorioFuncionalidadesOperacoesPorGrupo((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("FiltrarRelatorioFuncionalidadeOperacoesPorGrupoHelper", helper);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			
		return retorno;
	}
}
