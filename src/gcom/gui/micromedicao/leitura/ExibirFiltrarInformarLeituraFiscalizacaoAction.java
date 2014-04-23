package gcom.gui.micromedicao.leitura;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 * 
 * @data 15/05/2007
 *
 */
public class ExibirFiltrarInformarLeituraFiscalizacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite informar ou corrigir leitura de fiscalização
	 * 
	 * [UC0100] Informar Leitura de Fiscalização
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 16/05/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarInformarLeituraFiscalizacao");

		FiltrarInformarLeituraFiscalizacaoActionForm form = (FiltrarInformarLeituraFiscalizacaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Collection<MedicaoTipo> colecaoMedicaoTipo = new ArrayList();

		Collection<LeituraAnormalidade> colecaoLeituraAnormalidade = new ArrayList();

		this.carregarCombos(httpServletRequest, colecaoMedicaoTipo,
				colecaoLeituraAnormalidade);

		String matricula = form.getMatricula();

		//      Obtendo dados do imovel
		if (matricula != null && !matricula.trim().equals("")
				&& Integer.parseInt(matricula) > 0) {

			this.pesquisarImovel(httpServletRequest, form, matricula);

		}

		// Mes e ano de referencia
		if (httpServletRequest.getParameter("menu") != null) {

			SistemaParametro sistemaParametro = fachada
					.pesquisarParametrosDoSistema();
			Integer anoMes = sistemaParametro.getAnoMesFaturamento();

			form.setMesAnoReferencia(Util.formatarAnoMesParaMesAno(anoMes
					.toString()));
		}

		return retorno;

	}

	/* 
	 * Carregamento dos combos
	 */

	public void carregarCombos(HttpServletRequest httpServletRequest,
			Collection colecaoMedicaoTipo, Collection colecaoLeituraAnormalidade) {

		//Tipo de Medicao

		FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();

		filtroMedicaoTipo.setCampoOrderBy(FiltroMedicaoTipo.DESCRICAO);

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		colecaoMedicaoTipo = Fachada.getInstancia().pesquisar(
				filtroMedicaoTipo, MedicaoTipo.class.getName());

		if (colecaoMedicaoTipo == null || colecaoMedicaoTipo.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"MedicaoTipo");
		}

		httpServletRequest.setAttribute("colecaoMedicaoTipo",
				colecaoMedicaoTipo);

		//Anormalidade

		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();

		filtroLeituraAnormalidade
				.setCampoOrderBy(FiltroLeituraAnormalidade.DESCRICAO);

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		colecaoLeituraAnormalidade = Fachada.getInstancia().pesquisar(
				filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

		if (colecaoLeituraAnormalidade == null
				|| colecaoLeituraAnormalidade.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"LeituraAnormalidade");
		}

		httpServletRequest.setAttribute("colecaoLeituraAnormalidade",
				colecaoLeituraAnormalidade);

	}

	public void pesquisarImovel(HttpServletRequest httpServletRequest,
			FiltrarInformarLeituraFiscalizacaoActionForm form, String matricula) {

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroImovel filtroImovel = new FiltroImovel();

		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				matricula));

		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);

		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);

		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);

		Collection colecaoImovel = Fachada.getInstancia().pesquisar(
				filtroImovel, Imovel.class.getName());

		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

			Imovel imovel = (Imovel) colecaoImovel.iterator().next();

			sessao.setAttribute("imovel", imovel);

			form.setInscricaoImovel("" + imovel.getInscricaoFormatada());

		} else {
			httpServletRequest.setAttribute("matriculaNaoEncontrado", "true");
			form.setInscricaoImovel("IMÓVEL INEXISTENTE");
		}
	}

}
