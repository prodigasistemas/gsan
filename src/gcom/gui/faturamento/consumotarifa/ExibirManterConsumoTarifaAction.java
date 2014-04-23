package gcom.gui.faturamento.consumotarifa;

import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirManterConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterConsumoTarifa");

		//Fachada fachada = Fachada.getInstancia();
				
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if (sessao.getAttribute("idLigacaoAguaPerfil")!=null){
			sessao.removeAttribute("idLigacaoAguaPerfil");
		}
		
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterConsumoTarifa"))) {
			
			FiltroConsumoTarifaVigencia filtroConsumoTarifaVigencia = (FiltroConsumoTarifaVigencia) 
			sessao.getAttribute("filtroConsumoTarifaVigencia");
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
			filtroConsumoTarifaVigencia, ConsumoTarifaVigencia.class.getName());
			Collection colecaoFiltroConsumoTarifaVigencia = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

			// [FS0004] Nenhum registro encontrado				
			if (colecaoFiltroConsumoTarifaVigencia == null || colecaoFiltroConsumoTarifaVigencia.isEmpty()) {
				// Nenhuma Localidade cadastrado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoFiltroConsumoTarifaVigencia.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarConsumoTarifaVigencia");
				ConsumoTarifaVigencia consumoTarifaVigencia = (ConsumoTarifaVigencia) colecaoFiltroConsumoTarifaVigencia.iterator().next();
				
				httpServletRequest.setAttribute("idVigencia",consumoTarifaVigencia.getId().toString());
				httpServletRequest.setAttribute("atualizar","atualizar");
					
				sessao.setAttribute("atualizar","atualizar");
			}
			else{
				sessao.setAttribute("colecaoFiltroConsumoTarifaVigencia", colecaoFiltroConsumoTarifaVigencia);
			}
		}
		
		
		return retorno;
	}

}
