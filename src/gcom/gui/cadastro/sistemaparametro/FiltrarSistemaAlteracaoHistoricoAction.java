package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.sistemaparametro.FiltroSistemaAlteracaoHistorico;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0501]Filtrar Sistema Alteração Historico 
 *
 * @author Kássia Albuquerque
 * @date 27/11/2006
 */

public class FiltrarSistemaAlteracaoHistoricoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("exibirConsultarSistemaAlteracaoHistorico");
	
			HttpSession sessao = httpServletRequest.getSession(false);
	
			Fachada fachada = Fachada.getInstancia();
	
			FiltrarSistemaAlteracaoHistoricoActionForm form = (FiltrarSistemaAlteracaoHistoricoActionForm) actionForm;
	
			
			
			// Recupera todos os campos da página para ser colocada no filtro
			// posteriormente
			
			String idFuncionalidade = form.getIdFuncionalidade();
			String descricaoFuncionalidade = form.getDescricaoFuncionalidade();
			String dataAlteracao = form.getDataAlteracao();
			String tituloAlteracao = form.getTituloAlteracao();
	
			
			boolean peloMenosUmParametroInformado = false;
	
	
			FiltroSistemaAlteracaoHistorico filtroSistemaAlteracaoHistorico = new FiltroSistemaAlteracaoHistorico();
	
			filtroSistemaAlteracaoHistorico.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
			
			
			
			// Funcionalidade
			if (idFuncionalidade != null && !idFuncionalidade.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
	
				if (descricaoFuncionalidade == null || descricaoFuncionalidade.equals("")) {
					
					FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();
	
					filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID,
							idFuncionalidade));
	
					Collection colecaoFuncionalidade = fachada.pesquisar(filtroTabelaAuxiliarAbreviada,Funcionalidade.class.getName());
	
					if (colecaoFuncionalidade == null || colecaoFuncionalidade.isEmpty()) {
						throw new ActionServletException("atencao.pesquisa_inexistente", null, "Funcionalidade");
					}
				}
				
				filtroSistemaAlteracaoHistorico.adicionarParametro(new ParametroSimples(FiltroSistemaAlteracaoHistorico.ID_FUNCIONALIDADE, idFuncionalidade));
	
			}
			
			
			
			// Data de alteração
			if (dataAlteracao != null && !dataAlteracao.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
				filtroSistemaAlteracaoHistorico.adicionarParametro(new ParametroSimples(FiltroSistemaAlteracaoHistorico.DATA, Util
						.converteStringParaDate(dataAlteracao)));
			}
			
			
			//	Título da alteração
			if (tituloAlteracao != null	&& !tituloAlteracao.trim().equals("")) {
	
				peloMenosUmParametroInformado = true;
				filtroSistemaAlteracaoHistorico.adicionarParametro(new ComparacaoTexto(FiltroSistemaAlteracaoHistorico.NOME, tituloAlteracao));
	
			}
	
	
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
			if (!peloMenosUmParametroInformado) {
				
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}
	
			// Manda o filtro pela sessao para o
			// ExibirManterSistemaAlteracaoHistoricoAction
			sessao.setAttribute("filtroSistemaAlteracaoHistorico", filtroSistemaAlteracaoHistorico);
			httpServletRequest.setAttribute("Filtrar", "Filtrar");
	
			return retorno;
	}
}
