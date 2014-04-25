package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * [UC1055] Filtrar Acompanhamento dos Registros de Atendimento
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de Acompanhamento dos Registros de Atendimento.
 * 
 * @author Hugo Leonardo
 * @date 28/09/2010
 */
public class ExibirFiltrarAcompanhamentoRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.
			findForward("filtrarAcompanhamentoRegistroAtendimento");
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Form
		FiltrarAcompanhamentoRegistroAtendimentoActionForm form = 
			(FiltrarAcompanhamentoRegistroAtendimentoActionForm) actionForm;
		
		// Primeira vez que carrega a pagina
		if ( httpServletRequest.getParameter("menu") != null && 
				httpServletRequest.getParameter("menu").equals("sim")) {
			
			form.setSituacaoRAAbertos("2");
			form.setOpcaoRelatorio("0");
			
			Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
			
			if(Util.verificarNaoVazio(usuarioLogado.getUnidadeOrganizacional().toString()) ){
				
				UnidadeOrganizacional unidadeOrganizacional = usuarioLogado.getUnidadeOrganizacional();
			
				form.setUnidadeAtendimentoId("" + unidadeOrganizacional.getId());
				form.setUnidadeAtendimentoDescricao( unidadeOrganizacional.getDescricao());
				
			}
		}
		
		if (form.getUnidadeAtendimentoId() != null 
				&& !form.getUnidadeAtendimentoId().trim().equals("")) {
			
			this.pesquisarUnidadeAtendimento( httpServletRequest, form);
		}
		
		if(sessao.getAttribute("colecaoAtendimentoMotivoEncerramento") == null ){
			
			this.pesquisarMotivoEncerramento(sessao);
		}
		
		/*
		 * TODO - COSANPA - Mantis 854 - Felipe Santos
		 * 
		 * Não há nenhuma localidade associada a um municipio (muni_idprincipal != null)
		 */
		//this.pesquisarMunicipioAssociado(httpServletRequest);
		
		return retorno;
	}
	
	private void pesquisarMotivoEncerramento(HttpSession sessao){
		
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = 
			new FiltroAtendimentoMotivoEncerramento();
		
		filtroAtendimentoMotivoEncerramento.adicionarParametro(
				new ParametroSimples(
						FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroAtendimentoMotivoEncerramento.setCampoOrderBy(
				FiltroAtendimentoMotivoEncerramento.DESCRICAO);
		
		Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = 
			Fachada.getInstancia().pesquisar( filtroAtendimentoMotivoEncerramento, 
					AtendimentoMotivoEncerramento.class.getName());
		
		if (colecaoAtendimentoMotivoEncerramento == null || colecaoAtendimentoMotivoEncerramento.isEmpty()) {
			
			throw new ActionServletException("atencao.naocadastrado", null, "Motivo Encerramento");
		}else{
			
			sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", colecaoAtendimentoMotivoEncerramento);
		}
	}
	
	/**
	 * Pesquisa Municípios associados à localidade
	 *
	 * @author Diogo Peixoto
	 * @date 26/04/2011
	 */
	private void pesquisarMunicipioAssociado(HttpServletRequest httpServletRequest){
		Collection colecaoMunicipioAssociado = this.getFachada().pesquisarMunicipiosAssociadoLocalidade();
		if (colecaoMunicipioAssociado == null || colecaoMunicipioAssociado.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,"Municípios associados a localidade");
		} else {
			httpServletRequest.setAttribute("colecaoMunicipioAssociado",colecaoMunicipioAssociado);
		}
	}	
	
	private void pesquisarUnidadeAtendimento(HttpServletRequest httpServletRequest, 
				FiltrarAcompanhamentoRegistroAtendimentoActionForm form) {

		// Pesquisa a unidade organizacional na base
		FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, form.getUnidadeAtendimentoId()));

		Collection<UnidadeOrganizacional> collUnidadeOrganizacional = Fachada.getInstancia().pesquisar(
				filtro, UnidadeOrganizacional.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if (collUnidadeOrganizacional != null && !collUnidadeOrganizacional.isEmpty()) {
			
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) 
				Util.retonarObjetoDeColecao(collUnidadeOrganizacional);
			
			form.setUnidadeAtendimentoId("" + unidadeOrganizacional.getId());
			form.setUnidadeAtendimentoDescricao( unidadeOrganizacional.getDescricao());

		} else {
			form.setUnidadeAtendimentoId("");
			form.setUnidadeAtendimentoDescricao("UNIDADE INEXISTENTE");
			httpServletRequest.setAttribute("unidadeInexistente",true);
		}
	}
}
