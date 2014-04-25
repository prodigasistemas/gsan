package gcom.gui.micromedicao.leitura;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * <b>[UC0190]</b> Inserir Anormalidade de Leitura
 * </p>
 * 
 * <p>
 * Esta funcionalidade permite inserir uma Anormalidade de Leitura
 * </p>
 * 
 * @author Thiago Tenório, Magno Gouveia
 * @since 07/02/2007, 23/08/2011
 */
public class ExibirInserirAnormalidadeLeituraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("inserirAnormalidadeLeitura");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirAnormalidadeLeituraActionForm inserirAnormalidadeLeituraActionForm = (InserirAnormalidadeLeituraActionForm) actionForm;

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))) {
			// -------------- bt DESFAZER ---------------

			// Limpando o formulario
			inserirAnormalidadeLeituraActionForm.setDescricao("");
			inserirAnormalidadeLeituraActionForm.setAbreviatura("");
			inserirAnormalidadeLeituraActionForm.setIndicadorRelativoHidrometro("");
			inserirAnormalidadeLeituraActionForm.setIndicadorImovelSemHidrometro("");
			inserirAnormalidadeLeituraActionForm.setUsoRestritoSistema("");
			inserirAnormalidadeLeituraActionForm.setPerdaTarifaSocial("");
			inserirAnormalidadeLeituraActionForm.setOsAutomatico("");
			inserirAnormalidadeLeituraActionForm.setTipoServico("");
			inserirAnormalidadeLeituraActionForm.setConsumoLeituraNaoInformado("");
			inserirAnormalidadeLeituraActionForm.setConsumoLeituraInformado("");
			inserirAnormalidadeLeituraActionForm.setLeituraLeituraInformado("");
			inserirAnormalidadeLeituraActionForm.setLeituraLeituraNaoturaInformado("");
			inserirAnormalidadeLeituraActionForm.setNumeroFatorComLeitura("");
			inserirAnormalidadeLeituraActionForm.setNumeroFatorComLeitura("");
			inserirAnormalidadeLeituraActionForm.setIndicadorLeitura("0");
		}

		if (inserirAnormalidadeLeituraActionForm.getTipoServico() == null
				|| inserirAnormalidadeLeituraActionForm.getTipoServico().equals("")) {
			Collection colecaoPesquisa = null;

			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();

			filtroTipoServico.setCampoOrderBy(FiltroTipoServico.DESCRICAO);

			filtroTipoServico.adicionarParametro(new ParametroSimples(	FiltroTipoServico.INDICADOR_USO,
																		ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Tipo Serviço
			colecaoPesquisa = fachada.pesquisar(filtroTipoServico, ServicoTipo.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Nenhum registro na tabela localidade_porte foi encontrado
				throw new ActionServletException(	"atencao.pesquisa.nenhum_registro_tabela",
													null,
													"Tipo de Servico");
			} else {
				sessao.setAttribute("colecaoTipoServico", colecaoPesquisa);
			}

		}

		// coleção anormalidade consumo

		FiltroLeituraAnormalidadeConsumo filtroLeituraAnormalidadeConsumo = new FiltroLeituraAnormalidadeConsumo();
		filtroLeituraAnormalidadeConsumo.setCampoOrderBy(FiltroLeituraAnormalidadeConsumo.ID);

		Collection colecaoLeituraAnormalidadeConsumo = fachada.pesquisar(filtroLeituraAnormalidadeConsumo, LeituraAnormalidadeConsumo.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeConsumo", colecaoLeituraAnormalidadeConsumo);

		// coleção anormalidade leitura

		FiltroLeituraAnormalidadeLeitura filtroLeituraAnormalidadeLeitura = new FiltroLeituraAnormalidadeLeitura();
		filtroLeituraAnormalidadeLeitura.setCampoOrderBy(FiltroLeituraAnormalidadeLeitura.ID);

		Collection colecaoLeituraAnormalidadeLeitura = fachada.pesquisar(filtroLeituraAnormalidadeLeitura, LeituraAnormalidadeLeitura.class.getName());
		sessao.setAttribute("colecaoLeituraAnormalidadeLeitura", colecaoLeituraAnormalidadeLeitura);

		// devolve o mapeamento de retorno
		return retorno;
	}

}
