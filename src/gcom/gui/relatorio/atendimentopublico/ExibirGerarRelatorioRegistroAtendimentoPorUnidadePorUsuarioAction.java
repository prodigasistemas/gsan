package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
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
 * [UC0497] Gerar Relatorio Resumo de Solicitacoes de RA por Unidade
 * 
 * @see gcom.gui.relatorio.atendimentopublico.GerarRelatorioResumoSolicitacoesRAPorUnidadeActionForm
 * @see gcom.gui.relatorio.atendimentopublico.GerarRelatorioResumoSolicitacoesRAPorUnidadeAction
 * @see gcom.relatorio.atendimentopublico.RelatorioResumoSolicitacoesRAPorUnidade
 * 
 * @author Victor Cisneiros
 * @date 20/06/2008
 */
public class ExibirGerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioAction extends GcomAction {
	
	public ActionForward execute(
			ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest request,
			HttpServletResponse response) {
		
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioRegistroAtendimentoPorUnidadePorUsuario");
		HttpSession session = request.getSession();
		
		GerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioActionForm form = (GerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioActionForm) actionForm;
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = request.getParameter("objetoConsulta");
		
		Fachada fachada = Fachada.getInstancia();
		
				
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")) {
			
			// [UC0376] - Pesquisar Unidade
				this.pesquisarUnidadeOrganizacional(request, form, objetoConsulta);
			
		}
		
		return retorno;
	}
	
	public void pesquisarUnidadeOrganizacional(HttpServletRequest request,
			GerarRelatorioRegistroAtendimentoPorUnidadePorUsuarioActionForm form, String objetoConsulta) {
		
		FiltroUnidadeOrganizacional filtroUnidade = new FiltroUnidadeOrganizacional();
		Integer idUnidade = null;
		if (objetoConsulta.equals("1")) {
			idUnidade = new Integer(form.getIdUnidadeAtendimento());
		}
		
		filtroUnidade.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidade));
		Collection<UnidadeOrganizacional> colecaoUnidades = Fachada.getInstancia().pesquisar(
				filtroUnidade, UnidadeOrganizacional.class.getName());
		
		if (colecaoUnidades != null && !colecaoUnidades.isEmpty()) {
			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidades);
			
			if (objetoConsulta.equals("1")) {
				form.setIdUnidadeAtendimento(unidade.getId().toString());
				form.setNomeUnidadeAtendimento(unidade.getDescricao());
				request.getSession().setAttribute("unidadeEncontrada", "true");
			} 
		} else {
			if (objetoConsulta.equals("1")) {
				form.setNomeUnidadeAtendimento("Unidade Organizacional Inexistente");
				request.getSession().removeAttribute("unidadeEncontrada");
			} 
		}
	}

}
