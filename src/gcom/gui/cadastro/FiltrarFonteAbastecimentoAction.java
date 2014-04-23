package gcom.gui.cadastro;



import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FonteAbastecimento;
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
 * [UC0838]FILTRAR FONTE ABASTECIMENTO
 * 
 * @author Arthur Carvalho
 * @date 14/08/08
 */

public class FiltrarFonteAbastecimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterFonteAbastecimento");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarFonteAbastecimentoActionForm filtrarFonteAbastecimentoActionForm = (FiltrarFonteAbastecimentoActionForm) actionForm;

		FiltroFonteAbastecimento filtroFonteAbastecimento= new FiltroFonteAbastecimento();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarFonteAbastecimentoActionForm.getId();
		String indicadorCalcularVolumeFixo = filtrarFonteAbastecimentoActionForm.getIndicadorCalcularVolumeFixo();
		String descricao = filtrarFonteAbastecimentoActionForm.getDescricao();
		String indicadorUso = filtrarFonteAbastecimentoActionForm.getIndicadorUso();
		String descricaoAbreviada =  filtrarFonteAbastecimentoActionForm.getDescricaoAbreviada();
		String tipoPesquisa = filtrarFonteAbastecimentoActionForm.getTipoPesquisa();
		String indicadorPermitePoco = filtrarFonteAbastecimentoActionForm.getIndicadorPermitePoco();
		
		//Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		//CODIGO
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				
				filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(
						FiltroFonteAbastecimento.ID, id));
			}
		}
		
		//Indicador Calcular Volume fixo
		if (indicadorCalcularVolumeFixo != null && !indicadorCalcularVolumeFixo.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(
					FiltroFonteAbastecimento.INDICADOR_CALCULAR_VOLUME_FIXO, indicadorCalcularVolumeFixo));
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroFonteAbastecimento
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroFonteAbastecimento.DESCRICAO, descricao));
			} else {

				filtroFonteAbastecimento.adicionarParametro(new ComparacaoTexto(
						FiltroFonteAbastecimento.DESCRICAO, descricao));
			}
		}
		
		//Descricao Abreviada
		if (descricaoAbreviada != null && !descricaoAbreviada.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroFonteAbastecimento
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroFonteAbastecimento.DESCRICAO_ABREVIADA, descricaoAbreviada));
			} else {

				filtroFonteAbastecimento.adicionarParametro(new ComparacaoTexto(
						FiltroFonteAbastecimento.DESCRICAO_ABREVIADA, descricaoAbreviada));
			}
		}
		
		//Indicador Permite Poco
		if (indicadorPermitePoco != null && !indicadorPermitePoco.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(
					FiltroFonteAbastecimento.INDICADOR_PERMITE_POCO, indicadorPermitePoco ) ) ;
		}
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroFonteAbastecimento.adicionarParametro(new ParametroSimples(
					FiltroFonteAbastecimento.INDICADOR_USO, indicadorUso));
		}
		
		
		Collection <FonteAbastecimento> colecaoFonteAbastecimento= fachada
				.pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoFonteAbastecimento.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Fonte de Abastecimento");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros
		if (colecaoFonteAbastecimento == null
				|| colecaoFonteAbastecimento.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Fonte de Abastecimento");
		} else {
			httpServletRequest.setAttribute("colecaoFonteAbastecimento",
					colecaoFonteAbastecimento);
			FonteAbastecimento fonteAbastecimento= new FonteAbastecimento();
			fonteAbastecimento = (FonteAbastecimento) Util
					.retonarObjetoDeColecao(colecaoFonteAbastecimento);
			String idRegistroAtualizar = fonteAbastecimento.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroFonteAbastecimento", filtroFonteAbastecimento);

		httpServletRequest.setAttribute("filtroFonteAbastecimento",
				filtroFonteAbastecimento);

		
		return retorno;
	}
}
