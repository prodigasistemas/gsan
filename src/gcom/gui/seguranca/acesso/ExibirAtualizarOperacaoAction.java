package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.FiltroOperacaoTabela;
import gcom.seguranca.acesso.FiltroOperacaoTipo;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoTabela;
import gcom.seguranca.acesso.OperacaoTipo;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action responsável pelo pre-processamento da tela de atualizar
 *
 * @author Pedro Alexandre
 * @date 01/08/2006
 */
public class ExibirAtualizarOperacaoAction extends GcomAction {

	/**
	 * [UC0281] - Manter Operação
	 *
	 * @author Pedro Alexandre
	 * @date 01/08/2006
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

		
		//Seta o mapeamento para o action de atualizar
		ActionForward retorno = actionMapping.findForward("atualizarOperacao");

		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		//Cria uma instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o form de atualizar
		AtualizarOperacaoActionForm atualizarOperacaoActionForm = (AtualizarOperacaoActionForm) actionForm;

		//Recupera o objeto de consulta
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		
		//Recupera a flag pra indicar a remoção da tabela 
		String removerTabela = (String) httpServletRequest.getParameter("removerTabela");
		
		//Recupera o tipo de retorno 
		String tipoRetorno = (String) sessao.getAttribute("tipoPesquisaRetorno");
		
		//Cria a variável que vai armazenar o código da operação
		String idOperacao = null;
		
		/*
		 * Caso seja a primeira vez que o usuário esteja entrando na página do atualizar
		 */
		 if ((objetoConsulta == null || objetoConsulta.equalsIgnoreCase(""))
	    	 && (removerTabela == null || removerTabela.equalsIgnoreCase(""))
		 	 && (httpServletRequest.getParameter("desfazer") == null)
		 	 && (tipoRetorno == null || !tipoRetorno.equalsIgnoreCase("localidade"))){
	           //Recupera o id da Localidade que vai ser atualizada
			
			 
			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar")!= null){
				idOperacao = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
	           	//Definindo a volta do botão voltar para filtrar operação
				sessao.setAttribute("voltar", "filtrar");
	   	    	sessao.setAttribute("idRegistroAtualizar",idOperacao);
	   	    	sessao.setAttribute("tipoPesquisa",ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
	        }else if(httpServletRequest.getParameter("idRegistroAtualizar") == null){
	        	idOperacao = (String)sessao.getAttribute("idRegistroAtualizar");
	   			//Definindo a volta do botão voltar para filtrar operação
	        	sessao.setAttribute("voltar", "filtrar");
	        }else if (httpServletRequest.getParameter("idRegistroAtualizar")!= null) {
	        	idOperacao = httpServletRequest.getParameter("idRegistroAtualizar");
		        //Definindo a volta do botão voltar para o manter operação
	        	sessao.setAttribute("voltar", "manter");
		        sessao.setAttribute("idRegistroAtualizar",idOperacao);
	        }
		}else{
			//Recupera o código da operação que vai ser atualizada
			idOperacao = (String)sessao.getAttribute("idRegistroAtualizar");
		}
		 
		 //Seta a flag do voltar no request
		 httpServletRequest.setAttribute("voltar",sessao.getAttribute("voltar"));

		 String atualizarEndereco = (String) httpServletRequest.getParameter("limparCampos");

		if ((objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase(""))
			 || (removerTabela != null && !removerTabela.trim().equalsIgnoreCase(""))
			 || (atualizarEndereco != null && !atualizarEndereco.trim().equalsIgnoreCase(""))) {

			//Recupera o código da funcionalidade se ela for digitada
			String idFuncionalidadeDigitada = atualizarOperacaoActionForm.getIdFuncionalidade();
			
			//Caso o código da funcionalidade tenha sido informado 
			if (idFuncionalidadeDigitada != null && !idFuncionalidadeDigitada.trim().equalsIgnoreCase("")) {
				//Pesquisa a funcionalidade digitada na base de dados
				Funcionalidade funcionalidade = this.pesquisarFuncionalidade(idFuncionalidadeDigitada);
				
				//Caso exista a funcionalidade digitada na base de dados 
				//seta as informações da funcionalidade no form 
				//Caso contrário indica que a funcionalidade digitada não existe 
				if(funcionalidade != null){	
					atualizarOperacaoActionForm.setIdFuncionalidade(String.valueOf(funcionalidade.getId()));
					atualizarOperacaoActionForm.setDescricaoFuncionalidade(funcionalidade.getDescricao());
					httpServletRequest.setAttribute("funcionalidadeEncontrada", "true");
	
				} else {
					atualizarOperacaoActionForm.setIdFuncionalidade("");
					atualizarOperacaoActionForm.setDescricaoFuncionalidade("FUNCIONALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("funcionalidadeNaoEncontrada","exception");
				}
			}
			
			//Recupera o código do argumento de pesquisa se ele for digitado
			String idArgumentoPesquisaDigitado = atualizarOperacaoActionForm.getIdArgumentoPesquisa();
			
			//Caso o código do argumento de pesquisa tenha sido informado 
			if (idArgumentoPesquisaDigitado != null && !idArgumentoPesquisaDigitado.trim().equalsIgnoreCase("")) {
					
				//Pesquisa o argumento de pesquisa digitado na base de dados
				TabelaColuna argumentoPesquisa = this.pesquisarArgumentoPesquisa(idArgumentoPesquisaDigitado);

				//Caso exista o argumento de pesquisa digitado na base de dados 
				//seta as informações do argumento de pesquisa no form 
				//Caso contrário indica que o argumento de pesquisa digitado não existe 
				if(argumentoPesquisa != null){	
					atualizarOperacaoActionForm.setIdArgumentoPesquisa(String.valueOf(argumentoPesquisa.getId()));
					atualizarOperacaoActionForm.setDescricaoArgumentoPesquisa(argumentoPesquisa.getDescricaoColuna());
					httpServletRequest.setAttribute("argumentoPesquisaEncontrado", "true");
	
				} else {
					atualizarOperacaoActionForm.setIdArgumentoPesquisa("");
					atualizarOperacaoActionForm.setDescricaoArgumentoPesquisa("ARGUMENTO DE PESQUISA INEXISTENTE");
					httpServletRequest.setAttribute("argumentoPesquisaNaoEncontrado","exception");
				}
			}
			
			//Recupera o código da operação de pesquisa se ele for digitado
			String idOperacaoPesquisaDigitado = atualizarOperacaoActionForm.getIdOperacaoPesquisa();
			
			//Caso o código da operação de pesquisa tenha sido informado 
			if (idOperacaoPesquisaDigitado != null && !idOperacaoPesquisaDigitado.trim().equalsIgnoreCase("")) {
					
				//Pesquisa a operação de pesquisa digitada na base de dados
				Operacao operacaoPesquisa = this.pesquisarOperacaoPesquisa(idOperacaoPesquisaDigitado);
				
				//Caso exista a operação de pesquisa digitada na base de dados 
				//seta as informações da operação de pesquisa no form 
				//Caso contrário indica que a operação de pesquisa digitada não existe 
				if(operacaoPesquisa != null){	
					//FS0008] - Verificar tipo da operação -Rômulo Aurélio 11/05/2007
					if(operacaoPesquisa.getOperacaoTipo() != null){
						if(operacaoPesquisa.getOperacaoTipo().getId().intValue() != OperacaoTipo.PESQUISAR){
							throw new ActionServletException("atencao.operacao_pesquisa_invalida");
						}
					}
					atualizarOperacaoActionForm.setIdOperacaoPesquisa(String.valueOf(operacaoPesquisa.getId()));
					atualizarOperacaoActionForm.setDescricaoOperacaoPesquisa(operacaoPesquisa.getDescricao());
					httpServletRequest.setAttribute("operacaoPesquisaEncontrado", "true");
	
				} else {
					atualizarOperacaoActionForm.setIdOperacaoPesquisa("");
					atualizarOperacaoActionForm.setDescricaoOperacaoPesquisa("OPERAÇÃO DE PESQUISA INEXISTENTE");
					httpServletRequest.setAttribute("operacaoPesquisaNaoEncontrado","exception");
				}
			}
			
			/*
			 * Caso o botão de desfazer esteja vazio
			 */
		} else if (httpServletRequest.getParameter("desfazer") == null) {
			
			//Recupera o codigo da operação do form
			String idOperacaoForm = atualizarOperacaoActionForm.getIdOperacao();
			
			//Caso não esteja informado o id da operação
			if ((idOperacao == null || idOperacao.equalsIgnoreCase(""))&& (idOperacaoForm == null	|| idOperacaoForm.equalsIgnoreCase(""))) {
				throw new ActionServletException("atencao.codigo_localidade_nao_informado");
			} else {

				// Carregamento inicial do formulário.
				// ===================================================================

				//Se o código da operação tenha sido informado	
				if (idOperacao != null && !idOperacao.equalsIgnoreCase("")) {

					if (sessao.getAttribute("colecaoTabelaOperacao") == null) {

						//Pesquisa os tipos de operação e seta a coleção no request
						FiltroOperacaoTipo filtroOperacaoTipo = new FiltroOperacaoTipo();
						Collection<OperacaoTipo> colecaoTipoOperacao = fachada.pesquisar(filtroOperacaoTipo,OperacaoTipo.class.getName());
						if (colecaoTipoOperacao == null || colecaoTipoOperacao.isEmpty()) {
							throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Tabela Operação");
						}
						httpServletRequest.setAttribute("colecaoTipoOperacao", colecaoTipoOperacao);
					}
					//Chama o metódo para exibir a operação
					this.exibirOperacao( idOperacao,atualizarOperacaoActionForm,fachada, sessao,httpServletRequest);
				}
			}
		}

		
		/*
		 * Caso o usuário tenha apertado o botão de desfazer, retira as coleções de tipo de operação e
		 * a coleção de tabela de operação da sessão
		 * e chama o metódo para exibir a operação 
		 * da forma que ela está cadastrada na base
		 */
		 if (httpServletRequest.getParameter("desfazer") != null && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
			 sessao.removeAttribute("tipoPesquisaRetorno");
			 sessao.removeAttribute("colecaoOperacaoTabela");
			 this.exibirOperacao( idOperacao, atualizarOperacaoActionForm,fachada, sessao, httpServletRequest);
		 }
	        	
		// Devolve o mapeamento de retorno
		return retorno;
	}


	/**
	 * Pesquisa a funcionalidade digitada na base de dados de acordo com o código passado
	 *
	 * [FS0004 - Pesquisar Funcionalidade]
	 *
	 * @author Pedro Alexandre
	 * @date 11/05/2006
	 *
	 * @param idFuncionalidade
	 * @return
	 */
	private Funcionalidade pesquisarFuncionalidade(String idFuncionalidade){
		//Cria a variável que vai armazenar a funcionalidade pesquisada
		Funcionalidade funcionalidade = null;
		
		//Cria o filtro para pesquisa e seta o código da funcionalidade informada no filtro
		FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
		filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.ID, idFuncionalidade));
		
		//Pesquisa a funcionalidade na base de dados
		Collection colecaoFuncionalidade = Fachada.getInstancia().pesquisar(filtroFuncionalidade,Funcionalidade.class.getName());
		
		//Caso exista a funcionalidade cadastrada na base de dados 
		//recupera a funcionalidade da coleção
		if(colecaoFuncionalidade != null && !colecaoFuncionalidade.isEmpty()){
			funcionalidade = (Funcionalidade) Util.retonarObjetoDeColecao(colecaoFuncionalidade);
		}
		
		//Retorna a funcionalidade pesquisa ou nulo se a funcionalidade não for encontrada
		return funcionalidade;
		
	}
	
	/**
	 * Pesquisa a operação de pesquisa digitada na base de dados de acordo com o código passado
	 *
	 * [FS0007 - Verificar existência da operação]
	 *
	 * @author Pedro Alexandre
	 * @date 11/05/2006
	 *
	 * @param idOperacaoPesquisa
	 * @return
	 */
	private Operacao pesquisarOperacaoPesquisa(String idOperacaoPesquisa){
		//Cria a variável que vai armazenar a operação de pesquisa pesquisada
		Operacao operacaoPesquisa = null;
		
		//Cria o filtro para pesquisa e seta o código da operação de pesquisa informada no filtro
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, idOperacaoPesquisa));
		
		//Pesquisa a operação de pesquisa na base de dados
		Collection colecaoOperacao = Fachada.getInstancia().pesquisar(filtroOperacao,Operacao.class.getName());
		
		//Caso exista a operação de pesquisa cadastrada na base de dados 
		//recupera a operação de pesquisa da coleção
		if(colecaoOperacao != null && !colecaoOperacao.isEmpty()){
			operacaoPesquisa = (Operacao) Util.retonarObjetoDeColecao(colecaoOperacao);
		}
		
		//Retorna a operação de pesquisa ou nulo se a operação de pesquisa não for encontrada
		return operacaoPesquisa;
	}
	
	/**
	 * Pesquisa o argumento de pesquisa digitado na base de dados de acordo com o código passado
	 *
	 * [FS0005 - Verificar argumento de pesquisa]
	 *
	 * @author Pedro Alexandre
	 * @date 11/05/2006
	 *
	 * @param idArgumentoPesquisa
	 * @return
	 */
	private TabelaColuna pesquisarArgumentoPesquisa(String idArgumentoPesquisa){
		
		//Cria a variável que vai armazenar o argumento de pesquisa pesquisado
		TabelaColuna argumentoPesquisa = null;
		
		//Cria o filtro para pesquisa e seta o código do argumento de pesquisa informado no filtro
		FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
		filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.ID, idArgumentoPesquisa));
		
		//Pesquisa o argumento de pesquisa na base de dados
		Collection colecaoTabelaColuna = Fachada.getInstancia().pesquisar(filtroTabelaColuna,TabelaColuna.class.getName());

		//Caso exista o argumento de pesquisa cadastrado na base de dados 
		//recupera o argumento de pesquisa da coleção
		if(colecaoTabelaColuna != null && !colecaoTabelaColuna.isEmpty()){
			argumentoPesquisa = (TabelaColuna) Util.retonarObjetoDeColecao(colecaoTabelaColuna);
			
			//Caso exista o argumento de pesquisa cadastrado na base de dados 
			//recupera o argumento de pesquisa da coleção
			
			/*[FS0011] - Verificar argumento de pesquisa 
			 * Caso 1
			 */	
			
			// Retirando a crítica de obrigar o argumento de pesquisa ser chave-primária - Francisco
//				if(argumentoPesquisa.getIndicadorPrimaryKey().intValue() == new Integer("2")){
//					throw new ActionServletException("atencao.argumento_pesquisa_nao_chave_primaria");
//				}
			}
			
			/*[FS0011] - Verificar argumento de pesquisa 
			 * Caso 2
			 */	
			FiltroOperacao filtroOperacao = new FiltroOperacao();
			
			filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.TABELA_COLUNA_ID,idArgumentoPesquisa));
			
			Collection colecaoVerificacaoArgumentoPesquisa = Fachada.getInstancia().
																pesquisar(filtroOperacao,Operacao.class.getName());
			
			if(colecaoVerificacaoArgumentoPesquisa != null && !colecaoVerificacaoArgumentoPesquisa.isEmpty()){
				
				Operacao operacao = (Operacao) Util.retonarObjetoDeColecao(colecaoVerificacaoArgumentoPesquisa);
				
			throw new ActionServletException("atencao.argumento_de_outra_operacao", null,operacao.getDescricao());
			
		}
		
		//Retorna o argumento de pesquisa ou nulo se o argumento de pesquisa não for encontrado
		return argumentoPesquisa;
	}
	
	/**
	 * Metódo para exibir os dados da operação na página de atualizar 
	 *
	 * @author Administrador
	 * @date 04/08/2006
	 *
	 * @param idOperacao
	 * @param atualizarOperacaoActionForm
	 * @param fachada
	 * @param sessao
	 * @param httpServletRequest
	 */
	private void exibirOperacao(String idOperacao, 
    		AtualizarOperacaoActionForm atualizarOperacaoActionForm,
			Fachada fachada,HttpSession sessao,
			HttpServletRequest httpServletRequest) {
		
		//Cria a variável que vai armazenar a coleção de operação
		Collection colecaoPesquisa = null;

		//Pesquisa a operação de acordo com o id informado
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("operacaoTipo");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("idOperacaoPesquisa");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.ARGUMENTO_PESQUISA);
		filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, idOperacao));
		

		//Atribui a coleção de operação a variável
		colecaoPesquisa = fachada.pesquisar(filtroOperacao,Operacao.class.getName());

		/*
		 * Casonão exista nenhuma operação cadastrada com o id informado 
		 * levanta uma exceção para o usuário indicando que a operação com o
		 * id informado não está cadastrada
		 * Caso contrário exibir os dados da operação na páginado atualizar
		 */
		if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado",null,"Operação");
		} else {
			//Recupera a operação da coleção
			Operacao operacao = (Operacao) Util.retonarObjetoDeColecao(colecaoPesquisa);
			
			//Recupera o tipo da operação 
			OperacaoTipo operacaoTipoSelecionada = operacao.getOperacaoTipo();
			Integer idOperacaoTipo = operacaoTipoSelecionada.getId();

			//Recupera o código do tipo da operação no form caso o usuário 
			//tenha selecionado outro tipo de operação
			String idOperacaoTipoForm = atualizarOperacaoActionForm.getIdTipoOperacao();
			
			/*
			 * Caso o usuário tenha informado o tipo de opreção na página do 
			 * atualizar, atribui o id do tipo da operação do form a variável 
			 * que armazena qual o tipo da operação.
			 */
			if(idOperacaoTipoForm != null && !idOperacaoTipoForm.trim().equalsIgnoreCase("-1") ){
				idOperacaoTipo = new Integer(idOperacaoTipoForm);
				FiltroOperacaoTipo  filtroOperacaoTipoForm = new FiltroOperacaoTipo();
				filtroOperacaoTipoForm.adicionarParametro(new ParametroSimples(FiltroOperacaoTipo.ID,idOperacaoTipo));
				operacaoTipoSelecionada = (OperacaoTipo)Util.retonarObjetoDeColecao(Fachada.getInstancia().pesquisar(filtroOperacaoTipoForm, OperacaoTipo.class.getName()));
			}
			
			//Joga na sessão a operação que vai ser atualizada 
			sessao.setAttribute("operacaoAtualizar", operacao);

			//Seta no form as informações da operação para ser atualizada
			atualizarOperacaoActionForm.setIdOperacao(idOperacao);
			atualizarOperacaoActionForm.setDescricao(operacao.getDescricao());
			atualizarOperacaoActionForm.setDescricaoAbreviada(operacao.getDescricaoAbreviada());
			atualizarOperacaoActionForm.setCaminhoUrl(operacao.getCaminhoUrl());
			atualizarOperacaoActionForm.setIdTipoOperacao(""+idOperacaoTipo);
			atualizarOperacaoActionForm.setIdFuncionalidade(""+operacao.getFuncionalidade().getId());
			atualizarOperacaoActionForm.setDescricaoFuncionalidade(""+operacao.getFuncionalidade().getDescricao());			
			
			if (operacao.getArgumentoPesquisa() != null){
				atualizarOperacaoActionForm.setIdArgumentoPesquisa(operacao.getArgumentoPesquisa().getId() + "");
				atualizarOperacaoActionForm.setDescricaoArgumentoPesquisa(operacao.getArgumentoPesquisa().getDescricaoColuna());
			}
			
			/*
			 * Pesquisa na base as colunas tabelas de operação cadastradas
			 * para a operação que vai ser atualizada
			 */
			
//			if(operacao.getTabelaColuna() != null){
//			FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
//			
//			filtroTabelaColuna.adicionarParametro(new ParametroSimples
//					(FiltroTabelaColuna.ID,operacao.getTabelaColuna().getId()));
//			
//			Collection colecaoTabelaColuna = fachada.pesquisar(filtroTabelaColuna,TabelaColuna.class.getName());
//			
//				if(colecaoTabelaColuna != null && !colecaoTabelaColuna.isEmpty()){
//					
//					TabelaColuna tabelaColuna = (TabelaColuna)colecaoTabelaColuna.iterator().next();
//					
//					atualizarOperacaoActionForm.setIdArgumentoPesquisa(tabelaColuna.getId().toString());
//					
//					atualizarOperacaoActionForm.setDescricaoArgumentoPesquisa(tabelaColuna.getDescricaoColuna());
//					
//				}
//			}
			/*
			 * Pesquisa na base as tabelas de operação cadastradas
			 * para a operação que vai ser atualizada
			 */
			FiltroOperacaoTabela filtroOperacaoTabela = new FiltroOperacaoTabela();
			filtroOperacaoTabela.adicionarParametro(new ParametroSimples(FiltroOperacaoTabela.OPERACAO_ID,idOperacao));
			filtroOperacaoTabela.adicionarCaminhoParaCarregamentoEntidade("tabela");
			Collection colecaoOperacaoTabelaCadastradas = fachada.pesquisar(filtroOperacaoTabela,OperacaoTabela.class.getName());
			
			/*
			 * Caso a coleção de tabela de operação não esteja vazia 
			 * coloca a coleção no itrator para recuperar todos os objetos tabela 
			 * da coleção para ser colados na coleção de tabela e setados 
			 * na sessão 
			 */
			if(colecaoOperacaoTabelaCadastradas != null && !colecaoOperacaoTabelaCadastradas.isEmpty()){
				//Variável que vai armazenar as tabelas para a operação
				Collection<Tabela> colecaoOperacaoTabela = new ArrayList();
				
				//Coloca a coleçaõ no iterator
				Iterator<OperacaoTabela> iteratorOperacaoTabela = colecaoOperacaoTabelaCadastradas.iterator();
				
				//Laço para recuperar as tabelas da operação
				while (iteratorOperacaoTabela.hasNext()) {
					OperacaoTabela operacaoTabela = iteratorOperacaoTabela.next();
					colecaoOperacaoTabela.add(operacaoTabela.getTabela());
				}
				
				//Caso não exista a coleção na sessão seta a coleção de tabelasna sessão
				if(sessao.getAttribute("colecaoOperacaoTabela") == null){
					sessao.setAttribute("colecaoOperacaoTabela",colecaoOperacaoTabela);
				}
			}
			
			/*
			 * [FS0003] - Habilitar/Desabilitar Argumento de Pesquisa e Lista de Tabelas	 
			 * Caso o tipo de operação seja "pesquisar"
			 * habilita o campo argumento de pesquisa
			 * Caso contrário desabilita o campo argumento de pesquisa
			 */
			if(idOperacaoTipo.intValue() == OperacaoTipo.PESQUISAR){
//				sessao.setAttribute("habilitarArgumentoPesquisa","true");
				sessao.setAttribute("habilitarOperacaoPesquisa","false");
				sessao.setAttribute("colecaoOperacaoTabela",null);
				atualizarOperacaoActionForm.setIdOperacaoPesquisa("");
				atualizarOperacaoActionForm.setDescricaoOperacaoPesquisa("");
				
			}else{
//				sessao.setAttribute("habilitarArgumentoPesquisa","false");
//				atualizarOperacaoActionForm.setIdArgumentoPesquisa("");
//				atualizarOperacaoActionForm.setDescricaoArgumentoPesquisa("");
			  
			  //Caso o tipo de operação tenha o indicador de atualiza a base de dados 
			  //setado para sim habilita o campo operação de pesquisa
			  //Caso contrário desabilita o campo operação de pesquisa	
			  if(operacaoTipoSelecionada.getIndicadorAtualiza() == ConstantesSistema.SIM){
				  
				  sessao.setAttribute("habilitarOperacaoPesquisa","true");
				  if(operacao.getIdOperacaoPesquisa()!=null){
				  atualizarOperacaoActionForm.setIdOperacaoPesquisa(
						  operacao.getIdOperacaoPesquisa().getId().toString());
				  
				  atualizarOperacaoActionForm.setDescricaoOperacaoPesquisa(
						  operacao.getIdOperacaoPesquisa().getDescricao());
				  }
				   
				  
			  }else{
				  sessao.setAttribute("habilitarOperacaoPesquisa","false");
				  sessao.setAttribute("colecaoOperacaoTabela",null);
				  atualizarOperacaoActionForm.setIdOperacaoPesquisa("");
				  atualizarOperacaoActionForm.setDescricaoOperacaoPesquisa("");
			  }
			}

			
			
		}
	}
}
