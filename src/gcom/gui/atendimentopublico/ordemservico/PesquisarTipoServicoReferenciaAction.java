package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 05/08/2006
 */
public class PesquisarTipoServicoReferenciaAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Servicço
	 * 
	 * [UC0437] Pesquisar Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 31/07/2006
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
				.findForward("exibirResultadoPesquisaTipoServicoReferencia");

		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarTipoServicoReferenciaActionForm pesquisarTipoServicoReferenciaActionForm = (PesquisarTipoServicoReferenciaActionForm) actionForm;

		FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String descricao = pesquisarTipoServicoReferenciaActionForm
				.getDescricao();

		String descricaoAbreviada = pesquisarTipoServicoReferenciaActionForm
				.getDescricaoAbreviada();

		String indicadorExistenciaOsReferencia = pesquisarTipoServicoReferenciaActionForm
				.getIndicadorExistenciaOsReferencia();

		// Verifica se o campo Descrição foi informado

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoTipoReferencia.adicionarParametro(new ComparacaoTexto(
					FiltroServicoTipoReferencia.DESCRICAO, descricao));

		}

		// Verifica se o campo descrição abreviada foi informado

		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoTipoReferencia.adicionarParametro(new ComparacaoTexto(
					FiltroServicoTipoReferencia.DESCRICAO_ABREVIADA,
					descricaoAbreviada));

		}

		// Verifica se o campo Indicador de existência da OS de Referência foi informado

		if (indicadorExistenciaOsReferencia != null && !indicadorExistenciaOsReferencia.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroServicoTipoReferencia
					.adicionarParametro(new ParametroSimples(
							FiltroServicoTipoReferencia.INDICADOR_EXISTENCIA_OS_REFERENCIA,
							indicadorExistenciaOsReferencia));

		} 
		
		String idTipoServico = pesquisarTipoServicoReferenciaActionForm
		.getIdTipoServico();

		if (idTipoServico != null && !idTipoServico.trim().equals("")) {

			peloMenosUmParametroInformado = true;
			
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.ID, idTipoServico));
			
			filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");
		
			Collection colecaoTipoServico = fachada.pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());
		
			if (colecaoTipoServico != null && !colecaoTipoServico.isEmpty()) {
				
				ServicoTipo servicoTipo = (ServicoTipo) colecaoTipoServico
						.iterator().next();
				if(servicoTipo.getServicoTipoReferencia() != null){
				filtroServicoTipoReferencia.adicionarParametro(new ParametroSimples(
						FiltroServicoTipoReferencia.ID,servicoTipo.getServicoTipoReferencia().getId()));
				}else{
						throw new ActionServletException("atencao.naocadastrado", null,
								"Serviço Referência");
				}
				
				
			}else{
				
				throw new ActionServletException("atencao.tipo_servico_inexistente");
				
			}
			
		}
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroServicoTipoReferencia
				.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

		Collection colecaoServicoTipoReferencia = (Collection) fachada
				.pesquisar(filtroServicoTipoReferencia,
						ServicoTipoReferencia.class.getName());

		if (colecaoServicoTipoReferencia == null
				|| colecaoServicoTipoReferencia.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado","Serviço Referência");
		}

		sessao.setAttribute("colecaoServicoTipoReferencia",
				colecaoServicoTipoReferencia);

		return retorno;
	}

}
