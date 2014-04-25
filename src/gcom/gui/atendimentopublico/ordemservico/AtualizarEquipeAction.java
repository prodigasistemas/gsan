package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0371] Inserir Equipe
 * 
 * @author Leonardo Regis
 * @created 24 de Julho de 2006
 */
public class AtualizarEquipeAction extends GcomAction {

	/**
	 * [UC0372] Manter Equipe
	 * 
	 * @author Rafael Corrêa
	 * @date   13/11/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta Retorno (Forward = Sucesso)
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Form
		AtualizarEquipeActionForm atualizarEquipeActionForm = (AtualizarEquipeActionForm) actionForm;
		
		// Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String nomeEquipe = atualizarEquipeActionForm.getNomeEquipe();
		String placa = atualizarEquipeActionForm.getPlacaVeiculo();
		String cargaTrabalho = atualizarEquipeActionForm.getCargaTrabalhoDia();
		String idUnidade = atualizarEquipeActionForm.getIdUnidade();
		String idPerfilServico = atualizarEquipeActionForm.getIdServicoPerfilTipo();
		String indicadorUso = atualizarEquipeActionForm.getIndicadorUso();
		String codigoDdd = atualizarEquipeActionForm.getCodigoDdd();
		String numeroTelefone = atualizarEquipeActionForm.getNumeroTelefone();
		String numeroImei = atualizarEquipeActionForm.getNumeroImei();
		String cdUsuarioRespExecServico = atualizarEquipeActionForm.getCdUsuarioRespExecServico();
		
		UnidadeOrganizacional unidade = null;
		
		// Verifica se a unidade organizacional existe e em caso afirmativo
		// seta-a no filtro
		if (idUnidade != null && !idUnidade.trim().equals("")) {

			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional
					.adicionarParametro(new ParametroSimples(
							FiltroUnidadeOrganizacional.ID, idUnidade));

			Collection colecaoUnidade = fachada.pesquisar(
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class
							.getName());

			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Unidade Organizacional");
			}
			unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

		}

		ServicoPerfilTipo servicoPerfilTipo = null;
		
		// Verifica se o serviço perfil tipo existe e em caso afirmativo seta-o
		// no filtro
		if (idPerfilServico != null && !idPerfilServico.trim().equals("")) {

			FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo();
			filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoPerfilTipo.ID, idPerfilServico));

			Collection colecaoPerfilServico = fachada.pesquisar(
					filtroServicoPerfilTipo, ServicoPerfilTipo.class.getName());

			if (colecaoPerfilServico == null || colecaoPerfilServico.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Serviço Perfil Tipo");
			}
			servicoPerfilTipo = (ServicoPerfilTipo) Util.retonarObjetoDeColecao(colecaoPerfilServico);

		}
		
		// Equipe
		Equipe equipe = null;
		if (sessao.getAttribute("equipeAtualizar") != null) {
			
			// Recupera informações da equipe
			equipe = (Equipe) sessao.getAttribute("equipeAtualizar");
			
			equipe.setNome(nomeEquipe);
			
			int cargaHoraria = Integer.parseInt(cargaTrabalho)*60;
			equipe.setCargaTrabalho(cargaHoraria);
			
			if (placa != null && !placa.trim().equals("")) {
				equipe.setPlacaVeiculo(placa);
			} else {
				equipe.setPlacaVeiculo(null);
			}
			
			if (indicadorUso != null && indicadorUso.equals(ConstantesSistema.INDICADOR_USO_ATIVO.toString())) {
				equipe.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			} else {
				equipe.setIndicadorUso(ConstantesSistema.INDICADOR_USO_DESATIVO);
			}
			equipe.setCodigoDdd(codigoDdd);
			equipe.setNumeroTelefone(numeroTelefone);
			equipe.setNumeroImei(new BigDecimal(numeroImei));
			equipe.setUnidadeOrganizacional(unidade);
			equipe.setServicoPerfilTipo(servicoPerfilTipo);
				
			// Coleção de Componentes
			Collection colecaoEquipeComponentes = (Collection) sessao.getAttribute("colecaoEquipeComponentes");
			
			if (servicoPerfilTipo != null) {
				if (servicoPerfilTipo.getComponentesEquipe().intValue() != colecaoEquipeComponentes.size()) {
					throw new ActionServletException(
							"atencao.quantidade.componentes.diferente.permitido");
				}
			}
			
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			/*
			 * -Erivan-
			 * Verifica a existencia do código do usuário informado, 
			 * caso exista, insere na equipe
			 */
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, cdUsuarioRespExecServico));
			Collection colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			
			if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
				equipe.setUsuarioRespExecServico((Usuario)colecaoUsuario.iterator().next());
			}else{
				throw new ActionServletException("atencao.pesquisa.usuario.inexistente");
			}
			
			equipe.setIndicadorProgramacaoAutomatica(new Short(atualizarEquipeActionForm.getIndicadorProgramacaoAutomatica()));
			
			// Atualiza a Equipe e os equipamentos especiais
			fachada.atualizarEquipe(equipe, colecaoEquipeComponentes, usuarioLogado,atualizarEquipeActionForm.getEquipeEquipamentosEspeciais());

			// [FS008] Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest, 
								"Equipe "+equipe.getNome()+" atualizada com sucesso.", 
								"Realizar outra manutenção de Equipe",
								"exibirFiltrarEquipeAction.do?menu=sim");

		}
		return retorno;
	}

}
