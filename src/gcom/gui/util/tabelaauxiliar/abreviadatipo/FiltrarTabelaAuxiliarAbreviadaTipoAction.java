package gcom.gui.util.tabelaauxiliar.abreviadatipo;

import gcom.gui.ActionServletException;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviadatipo.FiltroTabelaAuxiliarAbreviadaTipo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * <<Esse componente serve para SetorAbastecimento e ZonaAbastecimento, sendo o tipo SistemaAbastecimento>
 * 
 * @author Administrador
 */
public class FiltrarTabelaAuxiliarAbreviadaTipoAction extends Action {
	/**
	 * <<Descrição do Método>>
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
				.findForward("retornarFiltroTabelaAuxiliarAbreviadaTipo");

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

		//Recupera os parâmetros do form
		String id = (String) pesquisarActionForm.get("id");
		String descricao = (String) pesquisarActionForm.get("descricao");
		String descricaoAbreviada = (String) pesquisarActionForm
				.get("descricaoAbreviada");
		String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");
		String atualizar = (String) pesquisarActionForm.get("atualizar");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");
		Integer tipo = (Integer) pesquisarActionForm.get("tipo");
		
		//cria o filtro para Tabela Auxiliar abreviada
		FiltroTabelaAuxiliarAbreviadaTipo filtroTabelaAuxiliarAbreviadaTipo = new FiltroTabelaAuxiliarAbreviadaTipo();

		
		boolean peloMenosUmParametroInformado = false;
		
		//Adiciona a Descrição da Tabela Auxiliar Abreviada Tipo ao filtro
		if (descricao != null && !descricao.equalsIgnoreCase("")) {			
			peloMenosUmParametroInformado = true;			
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				filtroTabelaAuxiliarAbreviadaTipo.adicionarParametro(new ComparacaoTextoCompleto(FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAO, 
						descricao));
			} else {
				filtroTabelaAuxiliarAbreviadaTipo.adicionarParametro(new ComparacaoTexto(FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAO, descricao));
			}
		}
		
		//Insere os parâmetros informados no filtro
		if (id != null && !id.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;	
			filtroTabelaAuxiliarAbreviadaTipo
					.adicionarParametro(new ParametroSimples(
							FiltroTabelaAuxiliarAbreviadaTipo.ID, id));
		}

		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;	
			filtroTabelaAuxiliarAbreviadaTipo
					.adicionarParametro(new ComparacaoTexto(
							FiltroTabelaAuxiliarAbreviadaTipo.DESCRICAOABREVIADA,
							descricaoAbreviada));
		}
		
		// Tipo
		if (tipo != null && tipo != -1) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviadaTipo.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviadaTipo.SISTEMAABASTECIMENTO,tipo));
			
		}
		
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroTabelaAuxiliarAbreviadaTipo.adicionarParametro(new ParametroSimples(
					FiltroTabelaAuxiliarAbreviadaTipo.INDICADORUSO, indicadorUso));
		}
		
		//Verifica se pelo menos um parametro foi informado
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
	
		if (atualizar != null && atualizar.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar", atualizar);
		}

		//Manda o filtro pelo request para o
		// ExibirManterTabelaAuxiliarAbreviadaTipo
		
		httpServletRequest.setAttribute("filtroTabelaAuxiliarAbreviadaTipo",
				filtroTabelaAuxiliarAbreviadaTipo);

		//Devolve o mapeamento de retorno
		return retorno;
	}
}
