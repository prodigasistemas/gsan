package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0755]FILTRAR MOTIVO DE CORTE
 * 
 * @author Vinícius Medeiros
 * @date 02/04/2008
 */

public class FiltrarMotivoCorteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterMotivoCorte");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarMotivoCorteActionForm filtrarMotivoCorteActionForm = (FiltrarMotivoCorteActionForm) actionForm;

		FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();

		boolean peloMenosUmParametroInformado = false;

		String idMotivoCorte = filtrarMotivoCorteActionForm.getIdMotivoCorte();
		String descricao = filtrarMotivoCorteActionForm.getDescricao();
		String indicadorUso = filtrarMotivoCorteActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarMotivoCorteActionForm.getTipoPesquisa();

		// Filtro pelo ID
		if (idMotivoCorte != null && !idMotivoCorte.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(idMotivoCorte));
			
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroMotivoCorte.adicionarParametro(new ParametroSimples(
						FiltroMotivoCorte.ID, idMotivoCorte));
			}
			
		}
		
		// Filtro pela Descrição
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			if (tipoPesquisa != null&& tipoPesquisa.equals(
					ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {

				filtroMotivoCorte
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroMotivoCorte.DESCRICAO, descricao));
			} else {

				filtroMotivoCorte.adicionarParametro(new ComparacaoTexto(
						FiltroMotivoCorte.DESCRICAO, descricao));
			}
		}
			
		// Filtro pelo Indicador de Uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroMotivoCorte.adicionarParametro(new ParametroSimples(
					FiltroMotivoCorte.INDICADOR_USO, indicadorUso));
		}
		
		Collection<MotivoCorte> colecaoMotivoCorte = fachada.pesquisar(
				filtroMotivoCorte, MotivoCorte.class.getName());
		
		//Verificar a existencia de um Motivo de Corte
		if (colecaoMotivoCorte.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.motivoCorte_inexistente");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado) {
	            throw new ActionServletException(
	                    "atencao.filtro.nenhum_parametro_informado");

	    } else {
	            httpServletRequest.setAttribute("filtroMotivoCorte",
	                    filtroMotivoCorte);
	            sessao.setAttribute("filtroMotivoCorte",
	                    filtroMotivoCorte);	            
	            sessao.setAttribute("idMotivoCorte",
	            		idMotivoCorte);
	            
	    }
		
		// Pesquisa sem registros 
		if (colecaoMotivoCorte == null || colecaoMotivoCorte.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null,"Motivo Corte");
		} else {
			httpServletRequest.setAttribute("colecaoMotivoCorte",colecaoMotivoCorte);
			MotivoCorte motivoCorte = new MotivoCorte();
			motivoCorte = (MotivoCorte) Util.retonarObjetoDeColecao(colecaoMotivoCorte);
			String idRegistroAtualizacao = motivoCorte.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroMotivoCorte", filtroMotivoCorte);
		
		httpServletRequest.setAttribute("filtroMotivoCorte", filtroMotivoCorte);

		return retorno;

	}
}
