package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

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
public class InserirEquipeAction extends GcomAction {

	/**
	 * [UC0371] Inserir Equipe
	 * 
	 * [UC0107] Registrar Transação
	 * 
	 * @author Leonardo Regis
	 * @date   21/07/2006
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
		InserirEquipeActionForm inserirEquipeActionForm = (InserirEquipeActionForm) actionForm;
		// Fachada
		Fachada fachada = Fachada.getInstancia();
		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");		
		
		// Registrando a operação
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_EQUIPE_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
		
		Operacao operacao = new Operacao();
        operacao.setId(Operacao.OPERACAO_EQUIPE_INSERIR);

        OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
        operacaoEfetuada.setOperacao(operacao);
        
		// Equipe
		Equipe equipe = null;
		if (inserirEquipeActionForm.getUnidadeOrganizacional() != null) {
			
			// Recupera informações da equipe
			equipe = getEquipe(inserirEquipeActionForm);
			
			// Faz as validações de inserção de equipe
			fachada.validarInsercaoEquipe(equipe);
			
			// Faz as validações de inserção de equipe componentes
			fachada.validarInsercaoEquipeComponentes(inserirEquipeActionForm.getEquipeComponentes());
			
			// Faz as validações de inserção de equipe equipamentos especiais
			fachada.validarInsercaoEquipeEquipamentosEspeciais(inserirEquipeActionForm.getEquipeEquipamentosEspeciais());
			
			// Insere Equipe
			long idEquipe = fachada.inserirEquipe(equipe, inserirEquipeActionForm.getEquipeComponentes(), 
					inserirEquipeActionForm.getEquipeEquipamentosEspeciais(), usuario);
			
			// [FS008] Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest, 
								"Inserção da Equipe "+equipe.getNome()+" efetuada com sucesso!", 
								"Efetuar outra Inserção de Equipe",
								"exibirInserirEquipeAction.do?menu=sim",
								"exibirAtualizarEquipeAction.do?equipeID="+ idEquipe,
								"Atualizar Equipe Inserida");

		}
		return retorno;
	}

	/**
	 * Insere Coleção de Componentes devidamente validados na base 
	 *
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param fachada
	 * @param equipe
	 */
	/*private void inserirEquipeComponentes(InserirEquipeActionForm inserirEquipeActionForm, 
		OperacaoEfetuada operacaoEfetuada, Equipe equipe, 
		RegistradorOperacao registradorOperacao,Usuario usuario) {
		
		// Fachada
		Fachada fachada = Fachada.getInstancia();
		
		// Coleção de Componentes
		Collection colecaoEquipeComponentes = inserirEquipeActionForm.getEquipeComponentes();
		for (Iterator iter = colecaoEquipeComponentes.iterator(); iter.hasNext();) {
			EquipeComponentes element = (EquipeComponentes) iter.next();
			element.setEquipe(equipe);
			// Registra operação
			element.setOperacaoEfetuada(operacaoEfetuada);
			element.adicionarUsuario(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
        	registradorOperacao.registrarOperacao(element);
        	// Insere componente
			fachada.inserirEquipeComponentes(element);
		}
	}*/

	/**
	 * Insere Coleção de equipamentos especiais devidamente validados na base
	 * 
	 * @author Nathalia Santos
	 * @date 20/06/2011
	 * 
	 * @param inserirEquipeActionForm
	 * @param Quantidade
	 * @param fachada
	 * @param EquipeEquipamentosEspeciais
	 */

	/*private void inserirEquipeEquipamentosEspeciais(
			InserirEquipeActionForm inserirEquipeActionForm,
			OperacaoEfetuada operacaoEfetuada, Equipe equipe,
			RegistradorOperacao registradorOperacao, Usuario usuario,
			Integer Quantidade) {

		// Fachada
		Fachada fachada = Fachada.getInstancia();

		// Coleção de Equipamentos especiais
		Collection colecaoEquipeEquipamentosEspeciais = inserirEquipeActionForm
				.getEquipeEquipamentosEspeciais();
		for (Iterator iter = colecaoEquipeEquipamentosEspeciais.iterator(); iter
				.hasNext();) {
			EquipeEquipamentosEspeciais element = (EquipeEquipamentosEspeciais) iter
					.next();
			element.setQuantidade(new Integer(inserirEquipeActionForm
					.getQuantidade()));
			// Registra operação
			element.setOperacaoEfetuada(operacaoEfetuada);
			element.adicionarUsuario(usuario,
					UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
			registradorOperacao.registrarOperacao(element);

			fachada.inserirEquipeEquipamentosEspeciais(element);
		}
	}*/

	/**
	 * Recupera Equipe com informações vindas da tela 
	 *
	 * @author Leonardo Regis
	 * @date 29/07/2006
	 *
	 * @param inserirEquipeActionForm
	 * @param fachada
	 * @param equipe
	 */
	private Equipe getEquipe(InserirEquipeActionForm inserirEquipeActionForm) {
		Equipe equipe;
		// Cria objeto Equipe
		equipe = new Equipe();
		equipe.setNome(inserirEquipeActionForm.getNomeEquipe());
		equipe.setPlacaVeiculo(inserirEquipeActionForm.getPlacaVeiculo());
		// Seta valor da carga horária no banco convertido para minuto
		int cargaHoraria = Integer.parseInt(inserirEquipeActionForm.getCargaTrabalhoDia())*60;
		equipe.setCargaTrabalho(cargaHoraria);
		// Unidade Organizacional
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		unidadeOrganizacional.setId(new Integer(inserirEquipeActionForm.getUnidadeOrganizacionalId()));
		equipe.setUnidadeOrganizacional(unidadeOrganizacional);
		// Tipo Perfil Servico
		if(inserirEquipeActionForm.getTipoPerfilServicoId() != null && !inserirEquipeActionForm.getTipoPerfilServicoId().equals("")){
			ServicoPerfilTipo servicoPerfilTipo = new ServicoPerfilTipo();
			servicoPerfilTipo.setId(new Integer(inserirEquipeActionForm.getTipoPerfilServicoId()));
			equipe.setServicoPerfilTipo(servicoPerfilTipo);
		}
		equipe.setUltimaAlteracao(new Date());
		equipe.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		equipe.setCodigoDdd(inserirEquipeActionForm.getCodigoDdd());
		equipe.setNumeroTelefone(inserirEquipeActionForm.getNumeroTelefone());
		equipe.setNumeroImei(new BigDecimal(inserirEquipeActionForm.getNumeroImei()));
		
		/*
		 * -Erivan-
		 * Verifica a existencia do código do usuário informado, 
		 * caso exista, insere na equipe
		 */
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, inserirEquipeActionForm.getCdUsuarioRespExecServico()));
		Collection colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
		
		if(colecaoUsuario != null && !colecaoUsuario.isEmpty()){
			equipe.setUsuarioRespExecServico((Usuario)colecaoUsuario.iterator().next());
		}else{
			throw new ActionServletException("atencao.pesquisa.usuario.inexistente");
		}
		
		equipe.setIndicadorProgramacaoAutomatica(new Short(inserirEquipeActionForm.getIndicadorProgramacaoAutomatica()));
		
		return equipe;
	}
}
