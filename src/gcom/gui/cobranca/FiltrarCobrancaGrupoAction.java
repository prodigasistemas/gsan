package gcom.gui.cobranca;



import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
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
 * [UC0930]FILTRAR GRUPO DE COBRANCA
 * 
 * @author Arthur Carvalho
 * @date 14/08/09
 */

public class FiltrarCobrancaGrupoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterCobrancaGrupo");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarCobrancaGrupoActionForm form = (FiltrarCobrancaGrupoActionForm) actionForm;

		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

		boolean peloMenosUmParametroInformado = false;

		String id = form.getId();
		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String anoMesReferencia = form.getAnoMesReferencia();
		String indicadorUso = form.getIndicadorUso();
		String tipoPesquisa = form.getTipoPesquisa();
		
		
		//Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		//ID
		if (id != null && !id.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupo.ID, id));
		}
		
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroCobrancaGrupo
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroCobrancaGrupo.DESCRICAO, descricao));
			} else {

				filtroCobrancaGrupo.adicionarParametro(new ComparacaoTexto(
						FiltroCobrancaGrupo.DESCRICAO, descricao));
			}
		}
		
		//Descricao Abreviada
		if ( descricaoAbreviada != null && !descricaoAbreviada.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaGrupo.adicionarParametro( new ComparacaoTextoCompleto(
					FiltroCobrancaGrupo.DESCRICAO_ABREVIADA, descricaoAbreviada));
		}
			
		//Ano Mes Referencia
        if(anoMesReferencia != null && !anoMesReferencia.trim().equals("")){
        	String mes = anoMesReferencia.substring(0, 2);
        	String ano = anoMesReferencia.substring(3, 7);
        	String anoMes = ano+mes;
        	peloMenosUmParametroInformado = true;
        	filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
        			FiltroCobrancaGrupo.ANO_MES_REFERENCIA, anoMes));
        	
         }
		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
					FiltroCobrancaGrupo.INDICADOR_USO, indicadorUso));
		}
		
		Collection<CobrancaGrupo> colecaoCobrancaGrupo = fachada
				.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoCobrancaGrupo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Grupo de Cobrança");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoCobrancaGrupo == null
				|| colecaoCobrancaGrupo.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Grupo de Cobrança");
		} else {
			httpServletRequest.setAttribute("colecaoCobrancaGrupo",
					colecaoCobrancaGrupo);
			CobrancaGrupo CobrancaGrupo = new CobrancaGrupo();
			CobrancaGrupo = (CobrancaGrupo) Util
					.retonarObjetoDeColecao(colecaoCobrancaGrupo);
			String idRegistroAtualizar = CobrancaGrupo.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroCobrancaGrupo", filtroCobrancaGrupo);

		httpServletRequest.setAttribute("filtroCobrancaGrupo",
				filtroCobrancaGrupo);

		
		return retorno;
	}
}
