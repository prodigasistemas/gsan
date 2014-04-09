package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0377] Pesquisar Equipe
 * 
 * @author Leonardo Regis
 * @date 31/07/2006
 * 
 */
public class PesquisarEquipeAction extends GcomAction {

	/**
	 * [UC0377] Pesquisar Equipe
	 * 
	 * @author Leonardo Regis
	 * @date 31/07/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirResultadoPesquisaEquipe");

		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarEquipeActionForm pesquisarEquipeActionForm = (PesquisarEquipeActionForm) actionForm;

		FiltroEquipe filtroEquipe = new FiltroEquipe();

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;
		
		// Verifica se o campo id foi informado
		if (pesquisarEquipeActionForm.getEquipeId() != null 
				&& !pesquisarEquipeActionForm.getEquipeId().trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, pesquisarEquipeActionForm.getEquipeId()));
		}
		// Verifica se o campo nome foi informado
		if (pesquisarEquipeActionForm.getNomeEquipe() != null 
				&& !pesquisarEquipeActionForm.getNomeEquipe().trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ComparacaoTexto(FiltroEquipe.NOME, pesquisarEquipeActionForm.getNomeEquipe()));
		}
		// Verifica se o campo placaVeiculo foi informado
		if (pesquisarEquipeActionForm.getPlacaVeiculo() != null 
				&& !pesquisarEquipeActionForm.getPlacaVeiculo().trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ComparacaoTexto(FiltroEquipe.PLACA_VEICULO,	pesquisarEquipeActionForm.getPlacaVeiculo()));
		}
		// Verifica se o campo cargaTrabalho foi informado
		if (pesquisarEquipeActionForm.getCargaTrabalhoDia() != null 
				&& !pesquisarEquipeActionForm.getCargaTrabalhoDia().trim().equals("")) {
			int cargaTrabalhoMinutos = new Integer(pesquisarEquipeActionForm.getCargaTrabalhoDia()).intValue()*60;
			Equipe equipe = new Equipe();
			equipe.setCargaTrabalho(cargaTrabalhoMinutos);
			fachada.validarInsercaoEquipe(equipe);
			peloMenosUmParametroInformado = true;
			
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.CARGA_TRABALHO, cargaTrabalhoMinutos));
		}
		// Verifica se o campo unidadeOrganizacional foi informado
		if (pesquisarEquipeActionForm.getUnidadeOrganizacionalId() != null 
				&& !pesquisarEquipeActionForm.getUnidadeOrganizacionalId().trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID_UNIDADE_ORGANIZACIONAL, pesquisarEquipeActionForm.getUnidadeOrganizacionalId()));
		}
		// Verifica se o campo perfilServico foi informado
		if (pesquisarEquipeActionForm.getTipoPerfilServicoId() != null 
				&& !pesquisarEquipeActionForm.getTipoPerfilServicoId().trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID_SERVICO_PERFIL_TIPO, pesquisarEquipeActionForm.getTipoPerfilServicoId()));
		}
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		Collection colecaoEquipe = (Collection) fachada.pesquisar(filtroEquipe, Equipe.class.getName());
		if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,	"Equipe");
		} else {
			
			// Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
													filtroEquipe, Equipe.class.getName());
			colecaoEquipe = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			sessao.setAttribute("colecaoEquipe", colecaoEquipe);
		}
		

		return retorno;
	}

}
