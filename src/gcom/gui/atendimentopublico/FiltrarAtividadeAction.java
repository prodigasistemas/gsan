package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
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
 * [UC0774]FILTRAR ATIVIDADE
 * 
 * @author Vinícius Medeiros
 * @date 28/04/2008
 */

public class FiltrarAtividadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("exibirManterAtividade");

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarAtividadeActionForm filtrarAtividadeActionForm = (FiltrarAtividadeActionForm) actionForm;

		FiltroAtividade filtroAtividade = new FiltroAtividade();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarAtividadeActionForm.getId();
		String descricao = filtrarAtividadeActionForm.getDescricao();
		String descricaoAbreviada = filtrarAtividadeActionForm.getDescricaoAbreviada();
		String indicadorUso = filtrarAtividadeActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarAtividadeActionForm.getTipoPesquisa();
		String indicadorAtividadeUnica = filtrarAtividadeActionForm.getIndicadorAtividadeUnica();
		
		// Filtro pelo ID
		if (id != null && !id.trim().equals("")) {
			
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			
			if (achou) {
			
				peloMenosUmParametroInformado = true;
				filtroAtividade.adicionarParametro(
						new ParametroSimples(FiltroAtividade.ID, 
								id));
			
			}
		}

		// Filtro pela Descrição
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null && tipoPesquisa.equals(
					ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {

				filtroAtividade.adicionarParametro(
						new ComparacaoTextoCompleto(FiltroAtividade.DESCRICAO, 
								descricao));
			
			} else {

				filtroAtividade.adicionarParametro(
						new ComparacaoTexto(FiltroAtividade.DESCRICAO, 
								descricao));
			}
		}
		
		// Filtro pela Descrição Abreviada
		if (descricaoAbreviada != null && !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroAtividade.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroAtividade.DESCRICAOABREVIADA,
							descricaoAbreviada));
		
		} else {

			filtroAtividade.adicionarParametro(
					new ComparacaoTexto(FiltroAtividade.DESCRICAOABREVIADA,
							descricaoAbreviada));
		}

		// Filtro pelo Indicador Atividade Unica
		if ( !indicadorAtividadeUnica.equals("") ){
			
			peloMenosUmParametroInformado = true;
			filtroAtividade.adicionarParametro(
					new ParametroSimples(FiltroAtividade.INDICADORATIVIDADEUNICA, 
							indicadorAtividadeUnica) );
		}


		// Filtro pelo Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
		
			peloMenosUmParametroInformado = true;
			filtroAtividade.adicionarParametro(
					new ParametroSimples(FiltroAtividade.INDICADORUSO, 
							indicadorUso));
		}
		
		Collection<Atividade> colecaoAtividade = fachada.pesquisar(
				filtroAtividade, Atividade.class.getName());

		// Verificar a existencia de uma atividade
		if (colecaoAtividade.isEmpty()) {
			
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Atividade");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
		
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		// Pesquisa sem registros
		if (colecaoAtividade == null || colecaoAtividade.isEmpty()) {
			
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Atividade");
		
		} else {
		
			httpServletRequest.setAttribute("colecaoAtividade",colecaoAtividade);
			Atividade atividade = new Atividade();
			atividade = (Atividade) Util.retonarObjetoDeColecao(colecaoAtividade);
			String idRegistroAtualizacao = atividade.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroAtividade", filtroAtividade);

		httpServletRequest.setAttribute("filtroAtividade",filtroAtividade);

		return retorno;

	}
}
