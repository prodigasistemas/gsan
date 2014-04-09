package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.EquipeComponentes;
import gcom.atendimentopublico.ordemservico.EquipeEquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pré-exibição da página de atualizar ligação de esgoto
 * 
 * @author Leonardo Regis
 * @param <Quantidade>
 * @param <EquipeEquipamentosEspeciaisId>
 * @created 18 de Julho de 2006
 */
public class ExibirInserirEquipeAction extends GcomAction {
	
//	 Fachada
	Fachada fachada = Fachada.getInstancia();
	
	/**
	 * Exibição de inserção de equipe.
	 * 
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta Retorno (Forward = Exibição da Tela de Inserção)
		ActionForward retorno = actionMapping.findForward("inserirEquipe");
		
		InserirEquipeActionForm inserirEquipeActionForm = (InserirEquipeActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//-Erivan Sousa- Verifica e atende uma solicitação de pesquisa de usuário
		if(httpServletRequest.getParameter("tipoPesquisa") != null &&
				!httpServletRequest.getParameter("tipoPesquisa").equals("")){
			
			if(httpServletRequest.getParameter("tipoPesquisa").equals("usuario")){
				Usuario usuario = buscarUsuario(inserirEquipeActionForm.getCdUsuarioRespExecServico());
				if(usuario != null){
					inserirEquipeActionForm.setCdUsuarioRespExecServico(usuario.getId().toString());
					inserirEquipeActionForm.setNomeUsuarioRespExecServico(usuario.getNomeUsuario().toUpperCase());
					sessao.setAttribute("usuarioRespExecServicoEncontrado", true);
				}else{
					inserirEquipeActionForm.setNomeUsuarioRespExecServico("USUÁRIO INEXISTENTE");
					sessao.removeAttribute("usuarioRespExecServicoEncontrado");
				}
			}
			
		}else{
			if(inserirEquipeActionForm.getNomeUsuarioRespExecServico() != null){
				if(inserirEquipeActionForm.getNomeUsuarioRespExecServico().equals("USUÁRIO INEXISTENTE")){
					inserirEquipeActionForm.setCdUsuarioRespExecServico("");
					inserirEquipeActionForm.setNomeUsuarioRespExecServico("");
				}
			}
		}
		// Testa unidade organizacional
		if (inserirEquipeActionForm.getUnidadeOrganizacionalId() != null &&
				!inserirEquipeActionForm.getUnidadeOrganizacionalId().equals("")) {
			getUnidadeOrganizacional(inserirEquipeActionForm, httpServletRequest);
		}

		// Testa tipo perfil serviço
		if(inserirEquipeActionForm.getTipoPerfilServicoId() != null &&
				!inserirEquipeActionForm.getTipoPerfilServicoId().equals("")) {
			getTipoPerfilServico(inserirEquipeActionForm, httpServletRequest);
		}
		
		// Testa se é pra remover equipe componente
		if (inserirEquipeActionForm.getDeleteComponente() != null &&
				!inserirEquipeActionForm.getDeleteComponente().equals("")) {
			
			removeEquipeComponente(inserirEquipeActionForm);
		}else if (inserirEquipeActionForm.getDeleteEquipamento() != null &&
				!inserirEquipeActionForm.getDeleteEquipamento().equals("")) {
			
			removeEquipeEquipamento(inserirEquipeActionForm);
		}  
		else {
			// Testa se é pra adicionar componente ou não
			if (httpServletRequest.getParameter("method") != null) {
				
				if( httpServletRequest.getParameter("method").equals("addEquipamentos") ){

					retorno = actionMapping.findForward("inserirEquipamentosEspeciais");
					
					inserirEquipeActionForm.setEquipamentosEspeciasId("");
					inserirEquipeActionForm.setDescricao("");
					inserirEquipeActionForm.setQuantidade("");
					
					FiltroEquipamentosEspeciais filtro = new FiltroEquipamentosEspeciais();
					filtro.adicionarParametro(
						new ParametroSimples(
							FiltroEquipamentosEspeciais.INDICADORUSO, 
							ConstantesSistema.SIM));
					
					Collection colecaoEquipamentosEspeciais = 
						this.getFachada().pesquisar(filtro, EquipamentosEspeciais.class.getName());
					
					httpServletRequest.setAttribute("colecaoEquipamentosEspeciais",colecaoEquipamentosEspeciais);

					
				}else{
					retorno = actionMapping.findForward("inserirEquipeComponente");
					
					inserirEquipeActionForm.setResponsavelId("");
					inserirEquipeActionForm.setFuncionarioId("");
					inserirEquipeActionForm.setFuncionarioName("");
					inserirEquipeActionForm.setFuncionario(null);
					inserirEquipeActionForm.setEquipeComponenteNome("");
					
					inserirEquipeActionForm.setMethod("add");
					
				}
				
				
			} else {
				
				if (!inserirEquipeActionForm.getMethod().equals("")) {
					
					//retorno = actionMapping.findForward("inserirEquipeComponente");
					
					// Cria objeto componente
					EquipeComponentes equipeComponentes = null;
					EquipeEquipamentosEspeciais equipeEquipamentosEspeciais = null;
					if (((inserirEquipeActionForm.getEquipeComponenteNome() != null && 
						!inserirEquipeActionForm.getEquipeComponenteNome().equals("")) || 
						(inserirEquipeActionForm.getFuncionarioId() != null && 
						!inserirEquipeActionForm.getFuncionarioId().equals(""))) && 
						inserirEquipeActionForm.getValidaFuncionario().equals("false")) {
						
						// Recupera equipe componente vindo do popup
						equipeComponentes = getEquipeComponente(inserirEquipeActionForm);
						
						// Reseta informações vindas do popup
						resetPopupComponente(inserirEquipeActionForm);
						
						// Faz as validações de inserção de equipe
						this.getFachada().validarExibirInsercaoEquipeComponentes(
							inserirEquipeActionForm.getEquipeComponentes(),
							equipeComponentes);

						// Seta componente na coleção
						setColecaoEquipeComponentes(inserirEquipeActionForm,equipeComponentes);
						// Seta retorno
						retorno = actionMapping.findForward("inserirEquipe");
						inserirEquipeActionForm.setMethod("");
					}else if (((inserirEquipeActionForm.getEquipamentosEspeciasId() != null && 
							!inserirEquipeActionForm.getEquipamentosEspeciasId().equals("")) || 
							(inserirEquipeActionForm.getQuantidade() != null && 
							!inserirEquipeActionForm.getQuantidade().equals("")))) {
						
						equipeEquipamentosEspeciais = getEquipeEquipamentosEspeciais(inserirEquipeActionForm);
						
						
						// Seta componente na coleção
						setColecaoEquipeEquipamentosEspeciais(inserirEquipeActionForm,equipeEquipamentosEspeciais);

						// Reseta informações vindas do popup
						resetPopupEquipamento(inserirEquipeActionForm);
						// Faz as validações de inserção de equipe equipamentos especiais
						fachada.validarInsercaoEquipeEquipamentosEspeciais(inserirEquipeActionForm.getEquipeEquipamentosEspeciais());
						
						inserirEquipeActionForm.setMethod("");

						// Seta retorno
						retorno = actionMapping.findForward("inserirEquipe");
						
					} else {
						// Testa funcionário
						if (inserirEquipeActionForm.getFuncionarioId() != null && 
							!inserirEquipeActionForm.getFuncionarioId().equals("")) {
							
							retorno = getFuncionario(actionMapping, inserirEquipeActionForm, httpServletRequest);
						}
					}
				}
			}
		}
		
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
			httpServletRequest.getParameter("tipoConsulta").equalsIgnoreCase("funcionario")) {
			
			String codigoPesquisa = httpServletRequest.getParameter("idCampoEnviarDados");
			
			String descricaoPesquisa = httpServletRequest.getParameter("descricaoCampoEnviarDados");
			
			Funcionario funcionario = new Funcionario();
			inserirEquipeActionForm.setFuncionarioId(codigoPesquisa);
			inserirEquipeActionForm.setFuncionarioName(descricaoPesquisa);
			inserirEquipeActionForm.setEquipeComponenteNome("");
			funcionario.setId(new Integer(codigoPesquisa));
			funcionario.setNome(descricaoPesquisa);
			
			inserirEquipeActionForm.setFuncionario(funcionario);
			
			sessao.setAttribute("funcionarioIdEncontrada","true");

			retorno = actionMapping.findForward("inserirEquipeComponente");
		}
		
		String menu = httpServletRequest.getParameter("menu");
		
		if (menu != null && !menu.equals(null) && menu.equals("sim")){
			inserirEquipeActionForm.setIndicadorProgramacaoAutomatica("2");
		}
		
		return retorno;
	}
		
	
/**
 * Reseta informações vindas do popup 
 *
 * @author Leonardo Regis
 * @date 27/06/2011
 *
 * @param inserirEquipeActionForm
 * @param equipeEquipamentosEspeciais
 */
private void resetPopupComponente(InserirEquipeActionForm inserirEquipeActionForm) {
	inserirEquipeActionForm.setResponsavelId("");
	inserirEquipeActionForm.setEquipeComponenteNome("");
	inserirEquipeActionForm.setFuncionario(null);
	inserirEquipeActionForm.setFuncionarioId("");
	inserirEquipeActionForm.setFuncionarioName("");
}
private void resetPopupEquipamento(InserirEquipeActionForm inserirEquipeActionForm) {
	inserirEquipeActionForm.setEquipamentosEspeciasId("");
	inserirEquipeActionForm.setEquipeComponenteNome("");
	inserirEquipeActionForm.setQuantidade("");
}


	/**
	 * Seta novo Componente na Coleção 
	 *
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param equipeComponentes
	 */
	private void setColecaoEquipeComponentes(InserirEquipeActionForm inserirEquipeActionForm, EquipeComponentes equipeComponentes) {
		inserirEquipeActionForm.getEquipeComponentes().add(equipeComponentes);
		inserirEquipeActionForm.setTamanhoColecao(inserirEquipeActionForm.getEquipeComponentes().size()+"");
	}
	
	
	private void setColecaoEquipeEquipamentosEspeciais(InserirEquipeActionForm inserirEquipeActionForm, EquipeEquipamentosEspeciais equipeEquipamentosEspeciais) {
		inserirEquipeActionForm.getEquipeEquipamentosEspeciais().add(equipeEquipamentosEspeciais);
		inserirEquipeActionForm.setTamanhoColecaoEquipeEquipamenosEspeciais((inserirEquipeActionForm.getEquipeEquipamentosEspeciais().size()+""));
	}
	/**
	 * Recupera objeto Equipe Componente com informações vindas da tela 
	 *
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param equipeComponentes
	 */
	private EquipeComponentes getEquipeComponente(InserirEquipeActionForm inserirEquipeActionForm) {
		EquipeComponentes equipeComponentes;
		equipeComponentes = new EquipeComponentes();
		if (inserirEquipeActionForm.getFuncionarioId() != null 
				&& !inserirEquipeActionForm.getFuncionarioId().equals("")) {
			equipeComponentes.setComponentes("");
			equipeComponentes.setFuncionario(inserirEquipeActionForm.getFuncionario());
			equipeComponentes.setIndicadorResponsavel(new Short(inserirEquipeActionForm.getResponsavelId()));			
		} else {
			equipeComponentes.setComponentes(inserirEquipeActionForm.getEquipeComponenteNome().toUpperCase());
			equipeComponentes.setFuncionario(null);
			
			//Colocado por Raphael Rossiter em 08/02/2007 (Autorizado por Rosana)
			equipeComponentes.setIndicadorResponsavel(new Short(inserirEquipeActionForm.getResponsavelId()));
		}
		equipeComponentes.setUltimaAlteracao(new Date());
		return equipeComponentes;
	}

	/**
	 * Recupera objeto Equipe Componente com informações vindas da tela 
	 *
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param equipeComponentes
	 */
	private EquipeEquipamentosEspeciais getEquipeEquipamentosEspeciais(InserirEquipeActionForm inserirEquipeActionForm) {
		
		EquipeEquipamentosEspeciais equipeEquipamentosEspeciais = new EquipeEquipamentosEspeciais();
		
		FiltroEquipamentosEspeciais filtroEquipamentosEspeciais = new FiltroEquipamentosEspeciais();
		filtroEquipamentosEspeciais.adicionarParametro(new ParametroSimples(
				FiltroEquipamentosEspeciais.ID, inserirEquipeActionForm.getEquipamentosEspeciasId()));
		
		Collection colecaoEquipamentosEspeciais = fachada.pesquisar(
				filtroEquipamentosEspeciais, EquipamentosEspeciais.class.getName());
		
		EquipamentosEspeciais equipamentosEspeciais = (EquipamentosEspeciais) colecaoEquipamentosEspeciais
				.iterator().next();
		
		if (inserirEquipeActionForm.getQuantidade() != null 
				&& !inserirEquipeActionForm.getQuantidade().equals("")) {
			equipeEquipamentosEspeciais.setQuantidade(new Integer(inserirEquipeActionForm.getQuantidade()) );
		}
		equipeEquipamentosEspeciais.setEquipamentosEspeciais(equipamentosEspeciais);
		
		equipeEquipamentosEspeciais.setUltimaAlteracao(new Date());
		return equipeEquipamentosEspeciais;
	}

	/**
	 * Recupera Funcionário 
	 *
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param equipeComponentes
	 */
	private ActionForward getFuncionario(ActionMapping actionMapping, InserirEquipeActionForm inserirEquipeActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		ActionForward retorno;
		// [F0004] Valida Funcionário
		//if (isValidateFuncionario(inserirEquipeActionForm)) {
			// Filtra Funcionario
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
			filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, 
															inserirEquipeActionForm.getFuncionarioId()));

			// Recupera Funcionario
			Collection colecaoFuncionario = fachada.pesquisar(filtroFuncionario, Funcionario.class.getName());
			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {
				sessao.setAttribute("funcionarioIdEncontrada","true");
				Funcionario funcionario = (Funcionario) colecaoFuncionario.iterator().next();
				inserirEquipeActionForm.setFuncionario(funcionario);
				inserirEquipeActionForm.setFuncionarioName(funcionario.getNome());
			} else {
				sessao.removeAttribute("funcionarioIdEncontrada");
				inserirEquipeActionForm.setFuncionarioId("");
				inserirEquipeActionForm.setFuncionario(null);
				inserirEquipeActionForm.setFuncionarioName("Funcionário inexistente");
			}
			inserirEquipeActionForm.setValidaFuncionario("false");
	
		retorno = actionMapping.findForward("inserirEquipeComponente");
		return retorno;
	}
	
	
//		/**
//		 * 
//		 * @author Rodrigo Silveira
//		 * @date /07/2006
//		 * 
//		 * @param httpServletRequest
//		 */
//		private void pesquisarEquipeEquipemantosEspeciais(HttpServletRequest httpServletRequest) {
//			FiltroEquipamentosEspeciais filtroEquipamentosEspeciais= new FiltroEquipamentosEspeciais();
//			Collection<EquipamentosEspeciais> colecao = Fachada.getInstancia()
//					.pesquisar(filtroEquipamentosEspeciais,
//							EquipamentosEspeciais.class.getName());
//			httpServletRequest.setAttribute("colecaoEquipamentosEspeciais ", colecao);
//		}

	/**
	 * Recupera Tipo Perfil Serviço 
	 *
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param equipeComponentes
	 */
	private void getTipoPerfilServico(InserirEquipeActionForm inserirEquipeActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0002] Valida Tipo Perfil Serviço
		//if (isValidateTipoPerfilServico(inserirEquipeActionForm)) {
			// Filtra 
			FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo();
			filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(FiltroServicoPerfilTipo.ID, 
																			inserirEquipeActionForm.getTipoPerfilServicoId()));
			// Recupera 
			Collection colecaoServicoPerfilTipo = fachada.pesquisar(filtroServicoPerfilTipo, ServicoPerfilTipo.class.getName());
			if (colecaoServicoPerfilTipo != null && !colecaoServicoPerfilTipo.isEmpty()) {
				sessao.setAttribute("tipoPerfilServicoIdEncontrada","true");
				ServicoPerfilTipo servicoPerfilTipo = (ServicoPerfilTipo) colecaoServicoPerfilTipo.iterator().next();
				inserirEquipeActionForm.setServicoPerfilTipo(servicoPerfilTipo);
				inserirEquipeActionForm.setTipoPerfilServicoDescricao(servicoPerfilTipo.getDescricao());
				if (servicoPerfilTipo.getComponentesEquipe() != null) {
					inserirEquipeActionForm.setQtdeComponentesEquipe(servicoPerfilTipo.getComponentesEquipe().toString());
				} else {
					inserirEquipeActionForm.setQtdeComponentesEquipe("");
				}
			} else {
				sessao.removeAttribute("tipoPerfilServicoIdEncontrada");
				inserirEquipeActionForm.setTipoPerfilServicoId("");
				inserirEquipeActionForm.setServicoPerfilTipo(null);
				inserirEquipeActionForm.setTipoPerfilServicoDescricao("Tipo Perfil Serviço inexistente");
				inserirEquipeActionForm.setQtdeComponentesEquipe("-1");
			}
			inserirEquipeActionForm.setValidaTipoPerfilServico("false");
		//}
	}
	/**
	 * Recupera Unidade Organizacional 
	 *
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param equipeComponentes
	 */
	private void getUnidadeOrganizacional(InserirEquipeActionForm inserirEquipeActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0001] Valida Unidade Organizacional
		//if (isValidateUnidadeOrganizacional(inserirEquipeActionForm)) {
			// Filtra Unidade Organizacional
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, 
															inserirEquipeActionForm.getUnidadeOrganizacionalId()));
			// Recupera Unidade Organizacional
			Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
				sessao.setAttribute("unidadeOrganizacionalIdEncontrada","true");

				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) colecaoUnidadeOrganizacional.iterator().next();
				inserirEquipeActionForm.setUnidadeOrganizacional(unidadeOrganizacional);
				inserirEquipeActionForm.setUnidadeOrganizacionalDescricao(unidadeOrganizacional.getDescricao());
			} else {
				sessao.removeAttribute("unidadeOrganizacionalIdEncontrada");
				inserirEquipeActionForm.setUnidadeOrganizacionalId("");
				inserirEquipeActionForm.setUnidadeOrganizacional(null);
				inserirEquipeActionForm.setUnidadeOrganizacionalDescricao("Unidade Organizacional inexistente");
			}
			inserirEquipeActionForm.setValidaUnidadeOrganizacional("false");
	
	}

	/**
	 * Remove Equipe Componente da Coleção 
	 *
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param equipeComponentes
	 */
	private void removeEquipeComponente(InserirEquipeActionForm inserirEquipeActionForm) {
		Collection newEquipeComponentesCollection = new ArrayList();
		int index = 0;
		for (Iterator iter = inserirEquipeActionForm.getEquipeComponentes().iterator(); iter
				.hasNext();) {
			index++;
			EquipeComponentes element = (EquipeComponentes) iter.next();
			if (index != new Integer(inserirEquipeActionForm.getDeleteComponente()).intValue()) {
				newEquipeComponentesCollection.add(element);
			}
		}
		inserirEquipeActionForm.setEquipeComponentes(newEquipeComponentesCollection);
		inserirEquipeActionForm.setTamanhoColecao(inserirEquipeActionForm.getEquipeComponentes().size()+"");
		inserirEquipeActionForm.setDeleteComponente("");
		resetPopupComponente(inserirEquipeActionForm);
	}
	
	/**
	 * Remove Equipamento especial da Coleção 
	 *
	 * @author Nathalia Santos
	 * @date 27/06/2011
	 *
	 * @param inserirEquipeActionForm
	 * @param equipamentosEspeciais
	 */
	private void removeEquipeEquipamento(InserirEquipeActionForm inserirEquipeActionForm) {
		Collection colecaoEquipeEquipamentosEspeciais = new ArrayList();
		int index = 0;
		for (Iterator iter = inserirEquipeActionForm.getEquipeEquipamentosEspeciais().iterator(); iter
				.hasNext();) {
			index++;
			EquipeEquipamentosEspeciais element = (EquipeEquipamentosEspeciais) iter.next();
			if (index != new Integer(inserirEquipeActionForm.getDeleteEquipamento()).intValue()) {
				colecaoEquipeEquipamentosEspeciais.add(element);
			}
		}
		inserirEquipeActionForm.setEquipeEquipamentosEspeciais(colecaoEquipeEquipamentosEspeciais);
		inserirEquipeActionForm.setTamanhoColecaoEquipeEquipamenosEspeciais(inserirEquipeActionForm.getEquipeEquipamentosEspeciais().size()+"");
		inserirEquipeActionForm.setDeleteEquipamento("");
		resetPopupEquipamento(inserirEquipeActionForm);
	}
	/**
	 * Pequisa o usuário com o id correspondente ao informado
	 * @author Erivan
	 * @param String codigoUsuario
	 * @return Usuario
	 */
	private Usuario buscarUsuario(String codigoUsuario){
		Usuario usuario = null;
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, codigoUsuario));
		
		Collection colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
		
		if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
			usuario = (Usuario) colecaoUsuario.iterator().next();
		}else{
			filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, codigoUsuario));
			colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
			
			if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
				usuario = (Usuario) colecaoUsuario.iterator().next();
			}else{
				filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, codigoUsuario));
				colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
				
			}
		}
		
		return usuario;
	}
	
	/**
	 * 
	 * Valida Unidade Organizacional
	 *
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 *
	 * @return está validando unidade organizacional?
	 */
	/*private boolean isValidateUnidadeOrganizacional(InserirEquipeActionForm form) {
		boolean toReturn = false;
		if (form.getValidaUnidadeOrganizacional().equalsIgnoreCase("true")) {
			toReturn = true;
		}
		return toReturn;		
	}*/
	
	/**
	 * 
	 * Valida Tipo de Perfil do Serviço
	 *
	 * @author Leonardo Regis
	 * @date 25/07/2006
	 *
	 * @return está validando tipo de perfil do serviço?
	 */
	/*private boolean isValidateTipoPerfilServico(InserirEquipeActionForm form) {
		boolean toReturn = false;
		if (form.getValidaTipoPerfilServico().equalsIgnoreCase("true")) {
			toReturn = true;
		}
		return toReturn;		
	}*/
	
	/**
	 * 
	 * Valida Funcionário
	 *
	 * @author Leonardo Regis
	 * @date 28/07/2006
	 *
	 * @return está validando funcionário?
	 */
	/*private boolean isValidateFuncionario(InserirEquipeActionForm form) {
		boolean toReturn = false;
		if (form.getValidaFuncionario().equalsIgnoreCase("true")) {
			toReturn = true;
		}
		return toReturn;		
	}*/
//	 Testa se é pra remover equipe componente
	
	
}
