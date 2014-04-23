package gcom.gui.operacional.abastecimento;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * [UC0526]	FILTRAR SISTEMA DE ESGOTO
 * 
 * @author Kássia Albuquerque
 * @date 12/03/2007
 */

public class FiltrarSistemaEsgotoAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
			ActionForward retorno = actionMapping.findForward("exibirManterSistemaEsgoto");
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			FiltrarSistemaEsgotoActionForm form = (FiltrarSistemaEsgotoActionForm) actionForm;									
			
			// Recupera todos os campos da página para ser colocada no filtro
			// posteriormente
			String descricaoSistemaEsgoto = form.getDescricaoSistemaEsgoto();
			String indicadorUso = form.getIndicadorUso();
			String tipoPesquisa = form.getTipoPesquisa();
			String divisaoEsgoto = form.getDivisaoEsgoto();
			String tipoTratamento = form.getTipoTratamento();						
			
			// Indicador Atualizar
			String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
			
			if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
				
				sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
			} else {
				
				sessao.removeAttribute("indicadorAtualizar");
			}
			
			
			FiltroSistemaEsgoto filtroSistemaEsgoto = new FiltroSistemaEsgoto(FiltroSistemaEsgoto.DESCRICAO);
			
			
			boolean peloMenosUmParametroInformado = false;
			
			
			// Descrição do Sistema de Esgoto
			if (descricaoSistemaEsgoto != null && !descricaoSistemaEsgoto.equalsIgnoreCase("")) {
				
				peloMenosUmParametroInformado = true;
				
				if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
					
					filtroSistemaEsgoto.adicionarParametro(new ComparacaoTextoCompleto(FiltroSistemaEsgoto.DESCRICAO, 
							descricaoSistemaEsgoto));
				} else {
					
					filtroSistemaEsgoto.adicionarParametro(new ComparacaoTexto(FiltroSistemaEsgoto.DESCRICAO,
							descricaoSistemaEsgoto));
				}
			}
			
			
			// Divisão de Esgoto
			if (divisaoEsgoto != null && !divisaoEsgoto.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.
					NUMERO_NAO_INFORMADO))) {
	
				peloMenosUmParametroInformado = true;
				
				filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.DIVISAOESGOTO_ID,
						divisaoEsgoto));
			}
			
			
			// Tipo de Tratamento 
			if (tipoTratamento != null && !tipoTratamento.trim().equalsIgnoreCase(String.valueOf(ConstantesSistema.
					NUMERO_NAO_INFORMADO))) {
	
				peloMenosUmParametroInformado = true;
				
				filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.TIPOTRATAMENTO_ID,
						tipoTratamento));
			}
			
			// Situacao do Esgoto
			if ( indicadorUso != null && 
				!indicadorUso.trim().equalsIgnoreCase( "" ) ){
				peloMenosUmParametroInformado = true;
				
				filtroSistemaEsgoto.adicionarParametro(new ParametroSimples(FiltroSistemaEsgoto.INDICADORUSO,
						indicadorUso));				
			}
			
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}								
			
			// Manda o filtro pela sessao para o ExibirManterSistemaEsgotoAction
			sessao.setAttribute("filtroSistemaEsgotoSessao", filtroSistemaEsgoto);
					
			return retorno;
		}
}	
