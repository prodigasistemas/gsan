package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * @author Rômulo Aurélio
 * @date 04/08/2006
 */
public class ExibirInserirTipoServicoReferenciaAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um tipo de serviço de referência.
	 * 
	 * [UC0436] Inserir Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 04/08/2006
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
				.findForward("tipoServicoReferenciaInserir");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirTipoServicoReferenciaActionForm inserirTipoServicoReferenciaActionForm = (InserirTipoServicoReferenciaActionForm) actionForm;

		String situacaoAntes = inserirTipoServicoReferenciaActionForm
				.getSituacaoOSAntes();

		String situacaoApos = inserirTipoServicoReferenciaActionForm
				.getSituacaoOSApos();

		String tipoServico = null;

		String indicadorExistenciaOsReferencia = inserirTipoServicoReferenciaActionForm
				.getIndicadorExistenciaOsReferencia();
		
        if (httpServletRequest.getParameter("limparCampos") != null) {
        	inserirTipoServicoReferenciaActionForm.reset();	
         }
        
		if (indicadorExistenciaOsReferencia != null && !indicadorExistenciaOsReferencia.equals("")) {
			String situacao = indicadorExistenciaOsReferencia;
			httpServletRequest.setAttribute("situacao", situacao);
		}

		if (sessao.getAttribute("tipoServico") != null) {

			tipoServico = (String) sessao.getAttribute("tipoServico");

		} else {

			tipoServico = inserirTipoServicoReferenciaActionForm
					.getTipoServico();
		}

		if (tipoServico != null && !tipoServico.trim().equals("")) {

			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.ID, tipoServico));

			Collection colecaoServicoTipo = fachada.pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());

			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {

				ServicoTipo servicoTipo = (ServicoTipo) colecaoServicoTipo
						.iterator().next();

				String nomeTipoServico = servicoTipo.getDescricao();

				inserirTipoServicoReferenciaActionForm
						.setSituacaoOSAntes(situacaoAntes);
				inserirTipoServicoReferenciaActionForm
						.setSituacaoOSApos(situacaoApos);
				inserirTipoServicoReferenciaActionForm
						.setNomeTipoServico(nomeTipoServico);
				httpServletRequest.setAttribute("nomeCampo", "tipoServico");

			} else {

				httpServletRequest.setAttribute("nomeCampo", "tipoServico");

				inserirTipoServicoReferenciaActionForm
						.setTipoServico(tipoServico);
				inserirTipoServicoReferenciaActionForm.setTipoServico("");

				inserirTipoServicoReferenciaActionForm
						.setNomeTipoServico("TIPO DE SERVIÇO INEXISTENTE");

				httpServletRequest.setAttribute("tipoServicoInexistente",
						"true");
			}

		}
		
		if(httpServletRequest.getParameter("semMenu") != null){
			sessao.setAttribute("semMenu", "S");
		}

		return retorno;
	}

}
