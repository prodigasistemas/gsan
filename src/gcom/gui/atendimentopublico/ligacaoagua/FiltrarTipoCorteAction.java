package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.FiltroCorteTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.atendimentopublico.ordemservico.FiltrarTipoPerfilServicoAction;
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
 * [UC1103] - FILTRAR Tipo de Corte
 * 
 * @author Ivan Sergio
 * @date 03/12/2010
 */
public class FiltrarTipoCorteAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterTipoCorte");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarTipoCorteActionForm form = (FiltrarTipoCorteActionForm) actionForm;
		
		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente
		
		String idTipoCorte = form.getIdTipoCorte();
		String descricao = form.getDescricao();
		String indicadorUso = form.getIndicadorUso();
		String indicadorCorteAdministrativo = form.getIndicadorCorteAdministrativo();
		String tipoPesquisa = form.getTipoPesquisa();
		
		String indicadorAtualizar = httpServletRequest.getParameter("atualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}
		
		boolean peloMenosUmParametroInformado = false;

		FiltroCorteTipo filtroCorteTipo = new FiltroCorteTipo(FiltroCorteTipo.DESCRICAO);
		
		// Codigo
		if (idTipoCorte != null && !idTipoCorte.equalsIgnoreCase("")){
			peloMenosUmParametroInformado = true;
			filtroCorteTipo.adicionarParametro(new ParametroSimples(
					FiltroCorteTipo.ID, idTipoCorte));
		}
		
		// Descrição
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if(tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())){
				filtroCorteTipo.adicionarParametro( 
						new ComparacaoTextoCompleto(FiltroCorteTipo.DESCRICAO, descricao));
			} else {
				filtroCorteTipo.adicionarParametro( 
						new ComparacaoTexto(FiltroCorteTipo.DESCRICAO, descricao));
			}
		}
		
		// Indicador de Uso
		if (indicadorUso != null && !indicadorUso.equals(FiltrarTipoPerfilServicoAction.INDICADOR_TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroCorteTipo.adicionarParametro(new ParametroSimples(
					FiltroCorteTipo.INDICADOR_USO, indicadorUso));
		}
		
		// Indicador Corte Administrativo
		if (indicadorCorteAdministrativo != null && !indicadorCorteAdministrativo.equals(FiltrarTipoPerfilServicoAction.INDICADOR_TODOS)) {
			peloMenosUmParametroInformado = true;
			filtroCorteTipo.adicionarParametro(new ParametroSimples(
					FiltroCorteTipo.INDICADOR_CORTE_ADMINISTRATIVO, indicadorCorteAdministrativo));
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		// Manda o filtro pela sessao
		sessao.setAttribute("filtroCorteTipo", filtroCorteTipo);
		
		return retorno;
	}
}
	
