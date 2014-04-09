package gcom.gui.cadastro;



import gcom.cadastro.funcionario.FiltroFuncionarioCargo;
import gcom.cadastro.funcionario.FuncionarioCargo;
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
 * [UC0838]FILTRAR CARGO DO FUNCIONÁRIO
 * 
 * @author Arthur Carvalho
 * @date 11/08/08
 */

public class FiltrarCargoFuncionarioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterCargoFuncionario");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarCargoFuncionarioActionForm filtrarCargoFuncionarioActionForm = (FiltrarCargoFuncionarioActionForm) actionForm;

		FiltroFuncionarioCargo filtroFuncionarioCargo= new FiltroFuncionarioCargo();

		boolean peloMenosUmParametroInformado = false;

		String codigo = filtrarCargoFuncionarioActionForm.getCodigo();
		String descricao = filtrarCargoFuncionarioActionForm.getDescricao();
		String indicadorUso = filtrarCargoFuncionarioActionForm.getIndicadorUso();
		String descricaoAbreviada	=  filtrarCargoFuncionarioActionForm.getDescricaoAbreviada();
		String tipoPesquisa = filtrarCargoFuncionarioActionForm.getTipoPesquisa();
		
//		Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		// CODIGO
		if (codigo != null && !codigo.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(codigo));
			if (achou) {
				peloMenosUmParametroInformado = true;
				
				filtroFuncionarioCargo.adicionarParametro(new ParametroSimples(
						FiltroFuncionarioCargo.CODIGO, codigo));
			}
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroFuncionarioCargo
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroFuncionarioCargo.DESCRICAO, descricao));
			} else {

				filtroFuncionarioCargo.adicionarParametro(new ComparacaoTexto(
						FiltroFuncionarioCargo.DESCRICAO, descricao));
			}
		}
		//Descricao Abreviada
		
		if (descricaoAbreviada != null && !descricaoAbreviada.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroFuncionarioCargo
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroFuncionarioCargo.DESCRICAO_ABREVIADA, descricaoAbreviada));
			} else {

				filtroFuncionarioCargo.adicionarParametro(new ComparacaoTexto(
						FiltroFuncionarioCargo.DESCRICAO_ABREVIADA, descricaoAbreviada));
			}
		}
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFuncionarioCargo.adicionarParametro(new ParametroSimples(
					FiltroFuncionarioCargo.INDICADOR_USO, indicadorUso));
		}
		
		
		Collection <FuncionarioCargo> colecaoFuncionarioCargo= fachada
				.pesquisar(filtroFuncionarioCargo, FuncionarioCargo.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoFuncionarioCargo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Cargo do Funcionário");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoFuncionarioCargo == null
				|| colecaoFuncionarioCargo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Cargo do Funcionário");
		} else {
			httpServletRequest.setAttribute("colecaoFuncionarioCargo",
					colecaoFuncionarioCargo);
			FuncionarioCargo funcionarioCargo= new FuncionarioCargo();
			funcionarioCargo = (FuncionarioCargo) Util
					.retonarObjetoDeColecao(colecaoFuncionarioCargo);
			String idRegistroAtualizar = funcionarioCargo.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroFuncionarioCargo", filtroFuncionarioCargo);

		httpServletRequest.setAttribute("filtroFuncionarioCargo",
				filtroFuncionarioCargo);

		
		return retorno;
	}
}
