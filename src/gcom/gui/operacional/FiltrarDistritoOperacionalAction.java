package gcom.gui.operacional;

import gcom.arrecadacao.FiltroArrecadador;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroDistritoOperacional;
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
 * [UC0523]	FILTRAR DISTRITO OPERACIONAL
 * 
 * @author Eduardo Bianchi
 * @date 01/02/2007
 */

public class FiltrarDistritoOperacionalAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirManterDistritoOperacional");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarDistritoOperacionalActionForm form = (FiltrarDistritoOperacionalActionForm) actionForm;
		
		//Recupera todos os campos da página para ser colocada no filtro posteriormente
		String descricao = form.getDescricao();
		String setorAbastecimento = form.getSetorAbastecimento();
		String sistemaAbastecimento = form.getSistemaAbastecimento();
		String tipoPesquisa = form.getTipoPesquisa();
		String indicadorUso = form.getIndicadorUso();
		
		//Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional(FiltroDistritoOperacional.DESCRICAO);
		
		//Objetos secundarios que serão carregados
		filtroDistritoOperacional.adicionarCaminhoParaCarregamentoEntidade("zonaAbastecimento");
		filtroDistritoOperacional.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento");
		
		boolean peloMenosUmParametroInformado = false;
		
		//Adiciona a Descrição do Distrito Operacional ao filtro
		if (descricao != null && !descricao.equalsIgnoreCase("")) {			
			peloMenosUmParametroInformado = true;			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				filtroDistritoOperacional.adicionarParametro(new ComparacaoTextoCompleto(FiltroDistritoOperacional.DESCRICAO, 
						descricao));
			} else {
				filtroDistritoOperacional.adicionarParametro(new ComparacaoTexto(FiltroDistritoOperacional.DESCRICAO, descricao));
			}
		}		
		
		//Adiciona o Setor Abastecimento
		if (setorAbastecimento != null && !setorAbastecimento.equals("-1")) {
			peloMenosUmParametroInformado = true;			
			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.SETORABASTECIMENTO,
					setorAbastecimento));
		}
		
		//Adiciona o Sistema Abastecimento
		if (sistemaAbastecimento != null && !sistemaAbastecimento.equals("-1")) {
			peloMenosUmParametroInformado = true;			
			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroDistritoOperacional.SISTEMAABASTECIMENTO,
					sistemaAbastecimento));
		}		
		
		if ( indicadorUso != null && !indicadorUso.trim().equals("") ){
			peloMenosUmParametroInformado = true;
			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(FiltroArrecadador.INDICADOR_USO, indicadorUso));			
		}		
		
		//Verifica se pelo menos um parametro foi informado
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		// Manda o filtro pela sessao para o
		// ExibirManterDistritoOperacionalAction
		sessao.setAttribute("filtroDistritoOperacional", filtroDistritoOperacional);
		return retorno;
		}
}

				
				
			
				
				
				
				
