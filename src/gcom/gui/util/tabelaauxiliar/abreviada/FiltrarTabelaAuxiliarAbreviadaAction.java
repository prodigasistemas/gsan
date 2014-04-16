package gcom.gui.util.tabelaauxiliar.abreviada;

import gcom.gui.ActionServletException;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.FiltroTabelaAuxiliar;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarTabelaAuxiliarAbreviadaAction extends Action {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("retornarFiltroTabelaAuxiliarAbreviada");

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		//Recupera os parâmetros do form
		String id = (String) pesquisarActionForm.get("id");
		String descricao = (String) pesquisarActionForm.get("descricao");
		String descricaoAbreviada = (String) pesquisarActionForm
				.get("descricaoAbreviada");
		String atualizar = (String) pesquisarActionForm.get("atualizar");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");
		String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");

		//cria o filtro para Tabela Auxiliar abreviada
		FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

		boolean peloMenosUmParametroInformado = false;
		
		//Insere os parâmetros informados no filtro
 		if (id != null && !id.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviada
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarAbreviada.ID, id));
		}
		//Adiciona a Descrição da Tabela Auxiliar Abreviada ao filtro
		if (descricao != null && !descricao.equalsIgnoreCase("")) {			
			peloMenosUmParametroInformado = true;			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				filtroTabelaAuxiliarAbreviada.adicionarParametro(new ComparacaoTextoCompleto(FiltroTabelaAuxiliar.DESCRICAO, 
						descricao));
			} else {
				filtroTabelaAuxiliarAbreviada.adicionarParametro(new ComparacaoTexto(FiltroTabelaAuxiliar.DESCRICAO, descricao));
			}
		}
		
		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviada
					.adicionarParametro(new ComparacaoTexto(
							FiltroTabelaAuxiliarAbreviada.DESCRICAOABREVIADA,
							descricaoAbreviada));
		}
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(
					FiltroTabelaAuxiliarAbreviada.INDICADORUSO, indicadorUso));
		}


		if (atualizar != null && atualizar.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar", atualizar);
		}
		
		//Verifica se pelo menos um parametro foi informado
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		//Manda o filtro pelo request para o
		// ExibirManterTabelaAuxiliarAbreviada
		httpServletRequest.setAttribute("filtroTabelaAuxiliarAbreviada",
				filtroTabelaAuxiliarAbreviada);

		//Devolve o mapeamento de retorno
		return retorno;
	}
}
