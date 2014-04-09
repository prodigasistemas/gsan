package gcom.gui.seguranca.acesso;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroGrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.FiltroModulo;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacaoPK;
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
 * Action responsável pelo pré-processamento da página de definir os acessos do grupo
 *
 * @author Pedro Alexandre
 * @date 03/07/2006
 */
public class ExibirAtualizarGrupoAcessosGrupoAction extends GcomAction {

	
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * [UC0279] - Manter Grupo
	 *
	 * @author Pedro Alexandre
	 * @date 03/07/2006
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

		//Recupera o form de atualizar grupo
		DynaValidatorForm atualizarGrupoActionForm = (DynaValidatorForm) actionForm;

		//Seta o mapeamento para a tela de definir acessos do grupo
		ActionForward retorno = actionMapping.findForward("atualizarGrupoAcessosGrupo");

		Usuario usuarioLogado = (Usuario) this.getSessao(httpServletRequest).getAttribute("usuarioLogado");
		
		//Recupera o grupo da sessão 
		Grupo grupo = (Grupo) this.getSessao(httpServletRequest).getAttribute("grupo");

		// Cria o filtro de modulo
		FiltroModulo filtroModulo = new FiltroModulo();

		// Seta a ordenação da pesquisa
		filtroModulo.setCampoOrderBy(FiltroModulo.DESCRICAO_MODULO);

		// Cria o filtro de funcionalidade
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();

		// Seta o filtro para retornar todas as funcionalidades que são ponto de entrada
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.INDICADOR_PONTO_ENTRADA, new Integer(1)));
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.OPERACOES);
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.FUNCIONALIDADE_DEPENDENCIAS_BY_ID_INDEPENDENCIA);
		filtroFuncionalidade.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionalidade.FUNCIONALIDADE_DEPENDENCIAS);
		filtroFuncionalidade.setConsultaSemLimites(true);

		// Cria o filtro de operação
		FiltroOperacao filtroOperacao = new FiltroOperacao();

		// Cria a coleção de funcionalidades
		Collection funcionalidades = null;

		// Cria a coleção de operações
		Collection operacoes = null;

		// Cria a coleção de funcionalidades cadastradas
		List funcionalidadesCadastradas = new ArrayList();

		// Cria a coleção de grupo e funcionalidades
		Collection grupoFuncionalidades = (Collection) this.getSessao(httpServletRequest).getAttribute("grupoFuncionalidades");
		if (grupoFuncionalidades == null) {
			FiltroGrupoFuncionalidadeOperacao filtroGrupoFuncionalidadeOperacao = new FiltroGrupoFuncionalidadeOperacao();
			filtroGrupoFuncionalidadeOperacao.adicionarParametro(new ParametroSimples(FiltroGrupoFuncionalidadeOperacao.GRUPO_ID, grupo.getId()));
			filtroGrupoFuncionalidadeOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroGrupoFuncionalidadeOperacao.FUNCIONALIDADE);
			filtroGrupoFuncionalidadeOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroGrupoFuncionalidadeOperacao.GRUPO);
			filtroGrupoFuncionalidadeOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroGrupoFuncionalidadeOperacao.OPERACAO);
			filtroGrupoFuncionalidadeOperacao.setConsultaSemLimites(true);
			
			grupoFuncionalidades = this.getFachada().pesquisar(filtroGrupoFuncionalidadeOperacao,GrupoFuncionalidadeOperacao.class.getSimpleName());
			
			if (grupoFuncionalidades == null) {
				grupoFuncionalidades = new ArrayList();
			}
			this.getSessao(httpServletRequest).setAttribute("grupoFuncionalidades", grupoFuncionalidades);
		}

		// Cria a coleção de operações cadastradas
		Collection operacoesCadastradas = new ArrayList();

		// Pega o código da funcionalidade selecionada na página
		String codigoFuncionalidade = httpServletRequest.getParameter("codigoFuncionalidade");

		// Cadastrando as operacoes selecionadas
		if (codigoFuncionalidade != null && !"".equals(codigoFuncionalidade) && "true".equalsIgnoreCase(httpServletRequest.getParameter("cadastrarOperacao"))) {

			Funcionalidade funcionalidade = new Funcionalidade();
			funcionalidade.setId(new Integer(codigoFuncionalidade));

			grupoFuncionalidades = (Collection) this.getSessao(httpServletRequest).getAttribute("grupoFuncionalidades");
			if (grupoFuncionalidades == null) {
				grupoFuncionalidades = new ArrayList();
				this.getSessao(httpServletRequest).setAttribute("grupoFuncionalidades",	grupoFuncionalidades);
			}

			// Apaga todas as operacoes da funcionalidade
			// para ser carregadas um a um novamente
			// isso eh no caso de excluir alguma funcionalidade
			Iterator iteratorGrupoFuncionalidades = grupoFuncionalidades.iterator();
			while (iteratorGrupoFuncionalidades.hasNext()) {
				GrupoFuncionalidadeOperacao gfo = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidades.next();
				if (gfo.getFuncionalidade().getId().equals(funcionalidade.getId())) {
					iteratorGrupoFuncionalidades.remove();
				}
			}

			// Pegando as operacoes escolhidas
			String[] operacoesAInserir = (String[]) atualizarGrupoActionForm.get("operacoes");
			if (operacoesAInserir != null) {
				for (int i = 0; i < operacoesAInserir.length; i++) {
					// Para todas as operacoes escolhidas
					String id = operacoesAInserir[i];
					boolean jaTem = false;
					// Verifica se ja tem foi selecionado
					iteratorGrupoFuncionalidades = grupoFuncionalidades.iterator();
					while (iteratorGrupoFuncionalidades.hasNext()) {
						// Verifica se eh a mesma funcionalidade e a mesma
						// operacao para ser excluida
						GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidades.next();
						if (grupoFuncionalidadeOperacao.getFuncionalidade().getId().equals(funcionalidade.getId()) && grupoFuncionalidadeOperacao.getOperacao().getId().toString().equalsIgnoreCase(id)) {
							jaTem = true;
						}
					}

					if (!jaTem) {
						// Se nao tem cria o filtro para adicionar a operacao
						filtroOperacao = new FiltroOperacao();
						filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.FUNCIONALIDADE);
						filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, new Integer(id)));
						filtroOperacao.setConsultaSemLimites(true);
						
						Collection colecaoOperacao = this.getFachada().pesquisar(filtroOperacao, Operacao.class.getSimpleName());
						if (colecaoOperacao != null && !colecaoOperacao.isEmpty()) {
							Operacao operacao = (Operacao) colecaoOperacao.iterator().next();
							GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = new GrupoFuncionalidadeOperacao();
							GrupoFuncionalidadeOperacaoPK grupoFuncionalidadeOperacaoPK = new GrupoFuncionalidadeOperacaoPK();
							
							grupoFuncionalidadeOperacaoPK.setFuncionalidadeId(funcionalidade.getId());
							grupoFuncionalidadeOperacaoPK.setOperacaoId(operacao.getId());
							grupoFuncionalidadeOperacaoPK.setGrupoId(grupo.getId());
							grupoFuncionalidadeOperacao.setOperacao(operacao);
							grupoFuncionalidadeOperacao.setFuncionalidade(funcionalidade);
							grupoFuncionalidadeOperacao.setGrupo(grupo);
							grupoFuncionalidadeOperacao.setComp_id(grupoFuncionalidadeOperacaoPK);
							grupoFuncionalidades.add(grupoFuncionalidadeOperacao);
						}
					}
				}
			}
		}

		// Pesquisa a coleção de funcionalidades cadastradas no sistema
		funcionalidades = this.getFachada().pesquisarTabelaAuxiliar(filtroFuncionalidade,Funcionalidade.class.getName());

		// Se nenhuma funcionalidade cadastrada no sistema
		if (funcionalidades == null || funcionalidades.isEmpty()) {
			// Manda o erro no request atual
			throw new ActionServletException("atencao.naocadastrado.funcionalidade");
		}

		// Coloca a coleção de grupo e funcionalidades no iterator
		Iterator iteratorGrupoFuncionalidades = grupoFuncionalidades.iterator();

		// Laço para adicionar as funcionalidades do grupo na coleção
		while (iteratorGrupoFuncionalidades.hasNext()) {
			// Pega o objeto GrupoFuncionalidadeOperacao da coleção
			GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iteratorGrupoFuncionalidades.next();

			// Pega o objeto funcionalidade
			Funcionalidade funcionalidadeCadastrada = grupoFuncionalidadeOperacao.getFuncionalidade();

			// Se não existe o objeto na coleção
			if (!funcionalidadesCadastradas.contains(funcionalidadeCadastrada)) {
				// Se a funcionalidade for ponto de entrada
				// adiciona o objeto funcionalidade na coleção
				funcionalidadesCadastradas.add(funcionalidadeCadastrada);
			}
		}

		// Chama o metódo para criar a árvore das funcionalidades
		String arvoreFuncionalidades = 
			this.getFachada().construirMenuAcesso(
					usuarioLogado,
					"atualizarGrupoWizardAction.do?action=exibirAtualizarGrupoAcessosGrupoAction",
					grupo.getId());

		// Manda o javascript da árvore para a página no request
		httpServletRequest.setAttribute("arvoreFuncionalidades", arvoreFuncionalidades);

		/**
		 * Com a funcionalidade passada pesquisa todas as funcionalidade Ocultas
		 * com as funcionalidade Ocultas retira as operacoes
		 */

		// Se for informado o código da funcionalidade no request
		if (codigoFuncionalidade != null && !codigoFuncionalidade.equalsIgnoreCase("")) {
			// Cria a variável que vai conter a descrição da funcionalidade
			String descricaoFuncionalidade = null;

			// Coloca a coleção de funcionalidades no iterator
			Iterator iteratorFuncionalidades = funcionalidades.iterator();

			// Cria a coleção de funcionalidades ocultas(dependentes)
			//Collection funcionalidadesOcultas = new ArrayList();

			// Laço para encontrar a funcionalidade informada
			while (iteratorFuncionalidades.hasNext()) {
				// Recupera a funcionalidade do iterator
				Funcionalidade funcionalidade = (Funcionalidade) iteratorFuncionalidades.next();

				// Se o código da funcionalidade for igual ao código da
				// funcionalidade informada
				if (codigoFuncionalidade.equalsIgnoreCase(funcionalidade.getId().toString())) {
					// Recupera a descrição da funcionalidade
					descricaoFuncionalidade = funcionalidade.getDescricao();

					// Recupera a coleção de funcionalidades ocultas
					//funcionalidadesOcultas = funcionalidade.getFuncionalidadeDependencias();

					// Para a pesquisa
					break;
				}
			}
			operacoes = 
				this.getFachada().recuperarOperacoesFuncionalidadesEDependentes(new Integer(codigoFuncionalidade));
			
			
			/**
			 * Pegando todas as FuncionalidadeOperacao e vendo as operacoes que
			 * o usuario tem acesso da funcionlidade escolhido
			 */
			if (grupoFuncionalidades != null && !grupoFuncionalidades.isEmpty()) {
				Iterator iterator = grupoFuncionalidades.iterator();
				while (iterator.hasNext()) {
					GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao = (GrupoFuncionalidadeOperacao) iterator.next();
					if (grupoFuncionalidadeOperacao.getFuncionalidade().getId().toString().equalsIgnoreCase(codigoFuncionalidade)) {
						operacoesCadastradas.add(grupoFuncionalidadeOperacao.getOperacao());
					}
				}
			}

			// Remove a coleção de operações cadastradas da coleção
			operacoes.removeAll(operacoesCadastradas);

			// Cria a coleção de operações não cadastradas
			Collection operacoesNaoCadastradas = new ArrayList(operacoes);

			// Manda a descrição da funcionalidade no request
			httpServletRequest.setAttribute("descricaoFuncionalidade", descricaoFuncionalidade);

			// Manda a código da funcionalidade no request
			httpServletRequest.setAttribute("idFuncionalidade", codigoFuncionalidade);

			// Manda a coleção de operações cadastradas no request
			httpServletRequest.setAttribute("operacoesCadastradas",	operacoesCadastradas);

			// Manda a coleção de operações não cadastradas no request
			httpServletRequest.setAttribute("operacoesNaoCadastradas", operacoesNaoCadastradas);
		}

		// Retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
