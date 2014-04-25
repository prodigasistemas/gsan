package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action responsável por exibir a página de definir o acesso inicial que o
 * grupo vai possuir
 * 
 * @author Pedro Alexandre
 * @date 28/06/2006
 */
public class ExibirInserirGrupoAcessosGrupoAction extends GcomAction {

	/**
	 * [UC0278] - Inserir Grupo
	 * 
	 * @author Pedro Alexandre
	 * @date 29/06/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {

		//Recupera o from de inserir grupo
		DynaValidatorForm inserirGrupoActionForm = (DynaValidatorForm) actionForm;

		//Seta o mapeamento de retorno para a tela de inserir grupo acessos do grupo
		ActionForward retorno = actionMapping.findForward("inserirGrupoAcessosGrupo");

		//Recupera o usuário que está logado no sistema
		Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);

		//Cria o filtro para pesquisar todas as funcionalidades cadastradas no sistema
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(
			new ParametroSimples(FiltroFuncionalidade.INDICADOR_PONTO_ENTRADA, new Integer(1)));
		
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.OPERACOES);
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.FUNCIONALIDADE_DEPENDENCIAS_BY_ID_INDEPENDENCIA);
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.FUNCIONALIDADE_DEPENDENCIAS);
		filtroFuncionalidade.setConsultaSemLimites(true);
		
		//Cria o filtro para a pesquisa de operações
		FiltroOperacao filtroOperacao = new FiltroOperacao();

		//Cria as variáveis para armazenar as coleções de funcionalidade e operações 
		Collection funcionalidades = null;
		Collection operacoes = null;

		//Cria a variável que vai armazenar as funcionalidades armazenadas para o usuário
		List funcionalidadesCadastradas = new ArrayList();

		//Recupera os acessos do usuário na sessão
		Collection grupoFuncionalidades = 
			(Collection) this.getSessao(httpServletRequest).getAttribute("grupoFuncionalidades");
		/*
		 * Caso os acessos do usuário não esteja na sessão
		 * instÂncia a coleção e coloca na sessão a coleção vazia
		 */  
		if (grupoFuncionalidades == null) {
			grupoFuncionalidades = new ArrayList();
			
			this.getSessao(httpServletRequest).setAttribute("grupoFuncionalidades", grupoFuncionalidades);
		}

		//Cria a variável que vai armazenar as operações cadastradas para o usuário 
		Collection operacoesCadastradas = new ArrayList();

		//Recupera o código da funcionalidade do request
		String codigoFuncionalidade = httpServletRequest.getParameter("codigoFuncionalidade");

		/*
		 * Caso o código da funcionalidade tenha sido informado
		 * pesquisa os acessos ao que o usuário queestá logado tem 
		 * de acordo com os grupos a que ele pertence
		 */ 
		if (codigoFuncionalidade != null && 
			!"".equals(codigoFuncionalidade) && 
			"true".equalsIgnoreCase(httpServletRequest.getParameter("cadastrarOperacao"))) {
			
			//Cria o objeto funcionalidade e seta o id no objeto 
			Funcionalidade funcionalidade = new Funcionalidade();
			funcionalidade.setId(new Integer(codigoFuncionalidade));

			//Cria o iterator das permissões do usuário
			Iterator iteratorGrupoFuncionalidades = grupoFuncionalidades.iterator();
			
			//Laço para remover todas as permissões
			while (iteratorGrupoFuncionalidades.hasNext()) {
				GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = 
					(GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidades.next();
				
				if (grupoFuncionalidadeOperacao.getFuncionalidade().getId().equals(funcionalidade.getId())) {
					iteratorGrupoFuncionalidades.remove();
				}
			}

			//Recupera os ids das operações que foram marcadas pelo usuário 
			String[] operacoesAInserir = (String[]) inserirGrupoActionForm.get("operacoes");
			
			/*
			 * Caso esxista operações marcadas para ser adicionadas as permissões
			 * cria o relacionamento entre a funcionalidade principal e as operações selecionadas
			 */
			if (operacoesAInserir != null) {

				//Laço para gerar as permissões selecionadas 
				for (int i = 0; i < operacoesAInserir.length; i++) {
					
					//Recupera o id da operação
					String id = operacoesAInserir[i];
					
					//Flag para indicar que já existe a permissão na coleção da sessão
					boolean jaTem = false;
					
					//Cria o iterator das permissões
					iteratorGrupoFuncionalidades = grupoFuncionalidades.iterator();
					
					//Laço para verificar se já tem a permissão
					while (iteratorGrupoFuncionalidades.hasNext()) {
						GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = 
							(GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidades.next();
						if (grupoFuncionalidadeOperacao.getOperacao().getId().toString().equalsIgnoreCase(id)) {
							jaTem = true;
						}
					}

					/*
					 * Caso não tenha a permissão 
					 * cria o relacionamento entre a operação e a funcionalidade e seta 
					 * nas permissões do usuário
					 */
					if (!jaTem) {
						//Pesquisa as operações da funcionalidade
						filtroOperacao = new FiltroOperacao();
						filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.FUNCIONALIDADE);
						filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, new Integer(id)));
						Collection colecaoOperacoes = Fachada.getInstancia().pesquisar(filtroOperacao,Operacao.class.getSimpleName());
						
						/*
						 * Caso a funcionalidade tenha operações cadastradas
						 * cria a permissão para a operação e seta na coleção
						 */
						if (colecaoOperacoes != null && !colecaoOperacoes.isEmpty()) {
							Operacao operacao = (Operacao) colecaoOperacoes.iterator().next();
							GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = new GrupoFuncionalidadeOperacao();
							grupoFuncionalidadeOperacao.setOperacao(operacao);
							grupoFuncionalidadeOperacao.setFuncionalidade(funcionalidade);
							grupoFuncionalidades.add(grupoFuncionalidadeOperacao);
						}
					}
				}
			}
		}else if(codigoFuncionalidade != null && !codigoFuncionalidade.equals("") ){
			filtroFuncionalidade.adicionarParametro(
				new ParametroSimples(FiltroFuncionalidade.ID, codigoFuncionalidade));
		}

		//Pesquisa as funcionalidades cadastradas no sistema
		funcionalidades = 
			this.getFachada().pesquisar(filtroFuncionalidade,Funcionalidade.class.getName());

		//Caso não tenha nenhuma funcionalidade cadastrada no sistema levanta uma exceção para o usuário
		if (funcionalidades == null || funcionalidades.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado.funcionalidade");
		}

		//Cria o iterator das permissões do usuário 
		Iterator iteratorGrupoFuncionalidades = grupoFuncionalidades.iterator();

		//Laço para adicionar a permissão a coleção caso ainda não tenha 
		//a permissão para a funcionalidade
		while (iteratorGrupoFuncionalidades.hasNext()) {
			GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidades.next();
			Funcionalidade funcionalidadeCadastrada = grupoFuncionalidadeOperacao.getFuncionalidade();
			
			//Se ainda não tenha a permissão cadastrada adiciona ela a coleção
			if (!funcionalidadesCadastradas.contains(funcionalidadeCadastrada)) {
				funcionalidadesCadastradas.add(funcionalidadeCadastrada);
			}
		}

		//Cria a string que vai conter o link de retorno na construção do menu
		String linkRetorno = "inserirGrupoWizardAction.do?action=exibirInserirGrupoAcessosGrupoAction";
		
		//Cria a arvore contendo as funcionalidades a que o usuário tenha acesso
		//e seta a arvore no request
		String arvoreFuncionalidades = 
			this.getFachada().construirMenuAcesso(usuarioLogado, linkRetorno,null);
		
		httpServletRequest.setAttribute("arvoreFuncionalidades",arvoreFuncionalidades);

		/*
		 * Recupera a descriçaõ da funcionalidade e seta a descrição no request
		 */
		if (codigoFuncionalidade != null && !codigoFuncionalidade.equalsIgnoreCase("")) {
			String descricaoFuncionalidade = null;
			Iterator iteratorFuncionalidades = funcionalidades.iterator();
			while (iteratorFuncionalidades.hasNext()) {
				Funcionalidade funcionalidade = (Funcionalidade) iteratorFuncionalidades.next();
				if (codigoFuncionalidade.equalsIgnoreCase(funcionalidade.getId().toString())) {
					descricaoFuncionalidade = funcionalidade.getDescricao();
					break;
				}
			}

			//Pesquisa as operações registradas para a funcionalidade
			operacoes = 
				this.getFachada().recuperarOperacoesFuncionalidadesEDependentes(new Integer(codigoFuncionalidade));
			
			/*
			 * Pegando todas as FuncionalidadeOperacao e vendo as operacoes que
			 * o usuario tem acesso da funcionalidade escolhida
			 */
			if (grupoFuncionalidades != null && !grupoFuncionalidades.isEmpty()) {
				Iterator iteratorGrupoFuncionalidadeOperacao = grupoFuncionalidades.iterator();
				while (iteratorGrupoFuncionalidadeOperacao.hasNext()) {
					GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidadeOperacao.next();
					if (grupoFuncionalidadeOperacao.getFuncionalidade().getId().toString().equalsIgnoreCase(codigoFuncionalidade)) {
						operacoesCadastradas.add(grupoFuncionalidadeOperacao.getOperacao());
					}
				}
			}

			//Remove as operações já cadastradas para a funcionalidade para 
			//recuperar as operações que ainda não foram cadastradas   
			operacoes.removeAll(operacoesCadastradas);
			Collection operacoesNaoCadastradas = new ArrayList(operacoes);

			/*
			 * Seta descrição da funcionalidade selecionada no request
			 * assim como o código da fucnionalidade e as coleções de operações
			 * cadastradas e não cadastradas.   
			 */
			httpServletRequest.setAttribute("descricaoFuncionalidade",descricaoFuncionalidade);
			httpServletRequest.setAttribute("idFuncionalidade",codigoFuncionalidade);
			httpServletRequest.setAttribute("operacoesCadastradas",operacoesCadastradas);
			httpServletRequest.setAttribute("operacoesNaoCadastradas",operacoesNaoCadastradas);
		}
		//Retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
