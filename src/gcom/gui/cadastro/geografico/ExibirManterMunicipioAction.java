package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
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
 * [UC0502] MANTER MUNICIPIO
 * 
 * @author Kássia Albuquerque
 * @date 04/01/2007
 */

public class ExibirManterMunicipioAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
			ActionForward retorno = actionMapping.findForward("manterMunicipio");
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Collection colecaoMunicipio = null;
	
			// Parte da verificação do filtro
	        FiltroMunicipio filtroMunicipio = null;
	
			// Verifica se o filtro foi informado pela página de filtragem 
			if (sessao.getAttribute("filtroMunicipio") != null) {
				filtroMunicipio = (FiltroMunicipio) sessao
						.getAttribute("filtroMunicipio");
				filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("regiaoDesenvolvimento");
				filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("microrregiao.regiao");
				filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
			}
	
			if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterMunicipio"))) {
	
				Map resultado = controlarPaginacao(httpServletRequest, retorno,
						filtroMunicipio, Municipio.class.getName());
				colecaoMunicipio = (Collection) resultado.get("colecaoRetorno");
				retorno = (ActionForward) resultado.get("destinoActionForward");
				
	
				// [FS0002] Nenhum registro encontrado				
				if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado");
				}
				
				String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
				
				if (colecaoMunicipio.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
					// caso o resultado do filtro só retorne um registro 
					// e o check box Atualizar estiver selecionado
					//o sistema não exibe a tela de manter, exibe a de atualizar 
					retorno = actionMapping.findForward("exibirAtualizarMunicipio");
					Municipio municipio = (Municipio) colecaoMunicipio.iterator().next();
					sessao.setAttribute("idRegistroAtualizar", municipio);
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarMunicipioAction.do");
					//chama ExibirAtualizarMunicipioAction
				}else{
					sessao.setAttribute("colecaoMunicipio", colecaoMunicipio);
					sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterMunicipioAction.do");
					//chama ExibirManterMunicipioAction
				}
	
			}
			sessao.removeAttribute("tipoPesquisaRetorno");
			
			return retorno;
		
		
	} 
	
}
