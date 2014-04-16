package gcom.gui.micromedicao.hidrometro;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroMarca;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0139]	FILTRAR MARCA DE HIDROMETRO
 * 
 * @author Bruno Barros
 * @date 18/06/2007
 */

public class FiltrarHidrometroMarcaAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
			ActionForward retorno = actionMapping.findForward("exibirManterHidrometroMarca");
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			FiltrarHidrometroMarcaActionForm form = (FiltrarHidrometroMarcaActionForm) actionForm;									
			
			// Recupera todos os campos da página para ser colocada no filtro
			// posteriormente
			String descricaoMarcaHidrometro = form.getDescricaoMarcaHidrometro();
			String tipoPesquisa = form.getTipoPesquisa();
			String descricaoAbreviada = form.getDescricaoAbreviada();
			String validadeRevisao = form.getValidadeRevisao();		
			String indicadorUso = form.getIndicadorUso();
			
			// Indicador Atualizar
			String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
			
			if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {				
				sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
			} else {
				
				sessao.removeAttribute("indicadorAtualizar");
			}
			
			
			FiltroHidrometroMarca filtroHidrometroMarca = new FiltroHidrometroMarca(FiltroHidrometroMarca.DESCRICAO);
			
			
			boolean peloMenosUmParametroInformado = false;
			
			
			// Descrição do Sistema de Esgoto
			if (descricaoMarcaHidrometro != null && !descricaoMarcaHidrometro.equalsIgnoreCase("")) {
				
				peloMenosUmParametroInformado = true;
				
				if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
					
					filtroHidrometroMarca.adicionarParametro(new ComparacaoTextoCompleto(FiltroHidrometroMarca.DESCRICAO, 
							descricaoMarcaHidrometro));
				} else {
					
					filtroHidrometroMarca.adicionarParametro(new ComparacaoTexto(FiltroSistemaEsgoto.DESCRICAO,
							descricaoMarcaHidrometro));
				}
			}
			
			// Descrição abreviada 
			if (descricaoAbreviada != null && !descricaoAbreviada.trim().equalsIgnoreCase( "" ) ) {
	
				peloMenosUmParametroInformado = true;
				
				filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.DESCRICAO_ABREVIADA,
						descricaoAbreviada));
			}						
			
			// Validade da revisao
			if (validadeRevisao != null && !validadeRevisao.trim().equalsIgnoreCase( "" )) {
	
				peloMenosUmParametroInformado = true;
				
				filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.VALIDADE_REVISAO,
						validadeRevisao));
			}						
			
			// Situacao do Esgoto
			if ( indicadorUso != null && 
				!indicadorUso.trim().equalsIgnoreCase( "" ) ){
				peloMenosUmParametroInformado = true;
				
				filtroHidrometroMarca.adicionarParametro(new ParametroSimples(FiltroHidrometroMarca.INDICADOR_USO,
						indicadorUso));				
			}
			
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}								
			
			// Manda o filtro pela sessao para o ExibirManterHidrometroMarcaAction
			sessao.setAttribute("filtroHidrometroMarcaSessao", filtroHidrometroMarca);
					
			return retorno;
		}
}	
