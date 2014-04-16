package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroRegiaoDesenvolvimento;
import gcom.cadastro.geografico.FiltroUnidadeFederacao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC005] Manter Municipio [SB0001]Atualizar Municipio
 * 
 * @author Kássia Albuquerque
 * @date 08/01/2007
 */

public class ExibirAtualizarMunicipioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarMunicipio");

		AtualizarMunicipioActionForm atualizarMunicipioActionForm = (AtualizarMunicipioActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltroUnidadeFederacao filtroUnidadeFederacao = new FiltroUnidadeFederacao();

		Collection colecaoUnidadeFederacao = fachada.pesquisar(
				filtroUnidadeFederacao, UnidadeFederacao.class.getName());

		httpServletRequest.setAttribute("colecaoUnidadeFederacao",
				colecaoUnidadeFederacao);

		FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();

		filtroMicrorregiao.setCampoOrderBy(FiltroMicrorregiao.DESCRICAO);

		filtroMicrorregiao.adicionarParametro(new ParametroSimples(
				FiltroMicrorregiao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoMicrorregiao = fachada.pesquisar(filtroMicrorregiao,
				Microrregiao.class.getName());

		httpServletRequest.setAttribute("colecaoMicrorregiao",
				colecaoMicrorregiao);

		FiltroRegiaoDesenvolvimento filtroRegiaoDesenvolvimento = new FiltroRegiaoDesenvolvimento();

		filtroRegiaoDesenvolvimento
				.setCampoOrderBy(FiltroRegiaoDesenvolvimento.DESCRICAO);

		filtroRegiaoDesenvolvimento.adicionarParametro(new ParametroSimples(
				FiltroRegiaoDesenvolvimento.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoRegiaoDesenv = fachada.pesquisar(
				filtroRegiaoDesenvolvimento, RegiaoDesenvolvimento.class
						.getName());

		httpServletRequest.setAttribute("colecaoRegiaoDesenv",
				colecaoRegiaoDesenv);

		Municipio municipio = null;

		String idMunicipio = null;

		if (httpServletRequest.getParameter("idMunicipio") != null) {
			// tela do manter
			idMunicipio = (String) httpServletRequest
					.getParameter("idMunicipio");
			sessao.setAttribute("idMunicipio", idMunicipio);
		} else if (sessao.getAttribute("idMunicipio") != null) {
			// tela do filtrar
			idMunicipio = (String) sessao.getAttribute("idMunicipio");
		} else if (httpServletRequest
				.getParameter("idRegistroInseridoAtualizar") != null) {
			// link na tela de sucesso do inserir municipio
			idMunicipio = (String) httpServletRequest
					.getParameter("idRegistroInseridoAtualizar");
			sessao.setAttribute("caminhoRetornoVoltar",
					"/gsan/exibirFiltrarMunicipioAction.do?menu=sim");
		}

		if (idMunicipio == null) {

			if (sessao.getAttribute("idRegistroAtualizar") != null) {
				municipio = (Municipio) sessao
						.getAttribute("idRegistroAtualizar");
			} else {
				idMunicipio = (String) httpServletRequest.getParameter(
						"idMunicipio").toString();
			}
		}

		// ------Inicio da parte que verifica se vem da página de
		// municipio_manter.jsp

		if (municipio == null) {

			if (idMunicipio != null && !idMunicipio.equals("")) {

				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

				filtroMunicipio
						.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
				filtroMunicipio
						.adicionarCaminhoParaCarregamentoEntidade("microrregiao");
				filtroMunicipio
						.adicionarCaminhoParaCarregamentoEntidade("regiaoDesenvolvimento");

				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.ID, idMunicipio));

				Collection colecaoMunicipio = fachada.pesquisar(
						filtroMunicipio, Municipio.class.getName());

				if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

					municipio = (Municipio) Util
							.retonarObjetoDeColecao(colecaoMunicipio);

				}
			}
		}

		// ------ O municipio foi encontrado

		atualizarMunicipioActionForm.setCodigoMunicipio(municipio.getId()
				.toString());

		atualizarMunicipioActionForm.setNomeMunicipio(municipio.getNome());

		if (municipio.getDdd() != null) {
			atualizarMunicipioActionForm.setCodigoDdd(municipio.getDdd()
					.toString());
		} else {
			atualizarMunicipioActionForm.setCodigoDdd("");
		}

		if (municipio.getCepInicio() != null) {
			atualizarMunicipioActionForm.setCepInicial(municipio.getCepInicio()
					.toString());
		} else {
			atualizarMunicipioActionForm.setCepInicial("");
		}

		if (municipio.getCepFim() != null) {
			atualizarMunicipioActionForm.setCepFinal(municipio.getCepFim()
					.toString());
		} else {
			atualizarMunicipioActionForm.setCepFinal("");
		}

		atualizarMunicipioActionForm.setUnidadeFederacao(municipio
				.getUnidadeFederacao().getId().toString());

		atualizarMunicipioActionForm.setMicroregiao(municipio.getMicrorregiao()
				.getId().toString());

		atualizarMunicipioActionForm.setRegiaoDesenv(municipio
				.getRegiaoDesenvolvimento().getId().toString());

		if (municipio.getDataConcessaoInicio() != null) {
			atualizarMunicipioActionForm.setDataInicioConcessao(Util
					.formatarData(municipio.getDataConcessaoInicio()));
		} else {
			atualizarMunicipioActionForm.setDataInicioConcessao("");
		}

		if (municipio.getDataConcessaoFim() != null) {
			atualizarMunicipioActionForm.setDataFimConcessao(Util
					.formatarData(municipio.getDataConcessaoFim()));
		} else {
			atualizarMunicipioActionForm.setDataFimConcessao("");
		}

		atualizarMunicipioActionForm.setIndicadorUso(""
				+ municipio.getIndicadorUso());

		if (municipio.getCodigoIbge() != null) {

			atualizarMunicipioActionForm.setCodigoIbge(municipio
					.getCodigoIbge());
		}
		
		if(municipio.getIndicadorRelacaoQuadraBairro() != null && 
				municipio.getIndicadorRelacaoQuadraBairro().toString().equals("1")){
			
			atualizarMunicipioActionForm.setIndicadorRelacaoQuadraBairro("1");
		}else{
			atualizarMunicipioActionForm.setIndicadorRelacaoQuadraBairro("2");
		}
		
		sessao.setAttribute("municipio", municipio);

		// ------ Fim da parte que verifica se vem da página de
		// municipio_manter.jsp

		return retorno;
	}

}
