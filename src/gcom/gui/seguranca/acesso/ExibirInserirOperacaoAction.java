package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.FiltroOperacaoTipo;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoTipo;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.TabelaColuna;
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
 * Action responsável pela exibição da página de inserir operação para uma funcionalidade 
 * 
 * @author Pedro Alexandre
 * @date 05/05/2006
 */
public class ExibirInserirOperacaoAction extends GcomAction {

	
	/**
	 * Inseri uma operação de uma funcionalida de no sistema
	 *
	 * [UC0284] Inserir Operação
	 *
	 * @author Pedro Alexandre
	 * @date 05/05/2006
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

		//Seta o mapeamento de retorno para a tela de inserir operação 
		ActionForward retorno = actionMapping.findForward("operacaoInserir");

		//Recupera o form de inserir operação
		InserirOperacaoActionForm inserirOperacaoActionForm = (InserirOperacaoActionForm) actionForm; 
		
		//Cria uma instância da fachada 
		Fachada fachada = Fachada.getInstancia();

		//Cria uma instância da sessão 
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Cria o filtro de tipo de operação e pesquisa os tipos de operações no sistema
		FiltroOperacaoTipo filtroOperacaoTipo = new FiltroOperacaoTipo();
		Collection<OperacaoTipo> colecaoOperacaoTipo = fachada.pesquisar(filtroOperacaoTipo,OperacaoTipo.class.getName());

		//Caso não exista nenhum tipo de operação cadastrado no sistema 
		//levanta a exceção para o usuário
		if (colecaoOperacaoTipo == null || colecaoOperacaoTipo.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Operação Tipo");
		}

		//Coloca a coleção de tipo de operações na sessão
		sessao.setAttribute("colecaoOperacaoTipo", colecaoOperacaoTipo);
		
		//Recupera a flag para indicar se o usuário apertou o botãode desfazer
		String desfazer = httpServletRequest.getParameter("desfazer");
			
		//Caso o usuário não tenha apertado o botão de desfazer na tela
		//verifica todas as validações e pesquisas
		//Caso contrário limpa o form de inserir operação
		if(desfazer == null){
			//Cria a varia´vel que vai armazenar o código do tipo da operação
			Integer idOperacaoTipo = null;
			
			//Caso o tipo de operação tenha sido informado
			//armazena o código do tipo da operação na variável
			if(inserirOperacaoActionForm.getIdTipoOperacao() != null){
				idOperacaoTipo = new Integer(inserirOperacaoActionForm.getIdTipoOperacao());
			}
			
			//Recupera o código da funcionalidade se ela for digitada
			String idFuncionalidadeDigitada = inserirOperacaoActionForm.getIdFuncionalidade();
			
			//Caso o código da funcionalidade tenha sido informado 
			if (idFuncionalidadeDigitada != null && !idFuncionalidadeDigitada.trim().equalsIgnoreCase("")) {
				
				//Pesquisa a funcionalidade digitada na base de dados
				Funcionalidade funcionalidade = this.pesquisarFuncionalidade(idFuncionalidadeDigitada);
				
				//Caso exista a funcionalidade digitada na base de dados 
				//seta as informações da funcionalidade no form 
				//Caso contrário indica que a funcionalidade digitada não existe 
				if(funcionalidade != null){	
					inserirOperacaoActionForm.setIdFuncionalidade(String.valueOf(funcionalidade.getId()));
					inserirOperacaoActionForm.setDescricaoFuncionalidade(funcionalidade.getDescricao());
					httpServletRequest.setAttribute("funcionalidadeEncontrada", "true");
	
				} else {
					inserirOperacaoActionForm.setIdFuncionalidade("");
					inserirOperacaoActionForm.setDescricaoFuncionalidade("FUNCIONALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("funcionalidadeNaoEncontrada","exception");
				}
			}
			
			//Recupera o código do argumento de pesquisa se ele for digitado
			String idArgumentoPesquisaDigitado = inserirOperacaoActionForm.getIdArgumentoPesquisa();
			
			//Caso o código do argumento de pesquisa tenha sido informado 
			if (idArgumentoPesquisaDigitado != null && !idArgumentoPesquisaDigitado.trim().equalsIgnoreCase("")) {
					
				//Pesquisa o argumento de pesquisa digitado na base de dados
				TabelaColuna argumentoPesquisa = this.pesquisarArgumentoPesquisa(idArgumentoPesquisaDigitado);

				//Caso exista o argumento de pesquisa digitado na base de dados 
				//seta as informações do argumento de pesquisa no form 
				//Caso contrário indica que o argumento de pesquisa digitado não existe 
				if(argumentoPesquisa != null){	
					inserirOperacaoActionForm.setIdArgumentoPesquisa(String.valueOf(argumentoPesquisa.getId()));
					inserirOperacaoActionForm.setDescricaoArgumentoPesquisa(argumentoPesquisa.getDescricaoColuna());
					httpServletRequest.setAttribute("argumentoPesquisaEncontrado", "true");
	
				} else {
					inserirOperacaoActionForm.setIdArgumentoPesquisa("");
					inserirOperacaoActionForm.setDescricaoArgumentoPesquisa("ARGUMENTO DE PESQUISA INEXISTENTE");
					httpServletRequest.setAttribute("argumentoPesquisaNaoEncontrado","exception");
				}
			}
			
			//Recupera o código da operação de pesquisa se ele for digitado
			String idOperacaoPesquisaDigitado = inserirOperacaoActionForm.getIdOperacaoPesquisa();
			
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
					inserirOperacaoActionForm.setIdOperacaoPesquisa(String.valueOf(operacaoPesquisa.getId()));
					inserirOperacaoActionForm.setDescricaoOperacaoPesquisa(operacaoPesquisa.getDescricao());
					httpServletRequest.setAttribute("operacaoPesquisaEncontrado", "true");
	
				} else {
					inserirOperacaoActionForm.setIdOperacaoPesquisa("");
					inserirOperacaoActionForm.setDescricaoOperacaoPesquisa("OPERAÇÃO DE PESQUISA INEXISTENTE");
					httpServletRequest.setAttribute("operacaoPesquisaNaoEncontrado","exception");
				}
			}
			
			
			
			//[SB0001] Habilitar/Desabilitar Argumento de Pesquisa e Lista de Tabelas 
			//Caso o tipo de operação não tenha sido informado ou seja em branco
			//desabilita os campos de argumento de pesquisa e operação de pesquisa limpando-os
			//Caso contrário verifica qual o tipo de operação para habilitar os campos
			if(idOperacaoTipo == null || idOperacaoTipo.intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
				sessao.setAttribute("habilitarArgumentoPesquisa","false");
				sessao.setAttribute("habilitarOperacaoPesquisa","false");
				sessao.setAttribute("colecaoOperacaoTabela",null);
	
				inserirOperacaoActionForm.setIdArgumentoPesquisa("");
				inserirOperacaoActionForm.setDescricaoArgumentoPesquisa("");
				inserirOperacaoActionForm.setIdOperacaoPesquisa("");
				inserirOperacaoActionForm.setDescricaoOperacaoPesquisa("");
			}else{
				
				//Cria a variável que vai armazenar o tipo de operação
				//e pesquisa a operação na coleção pesquisada
				OperacaoTipo operacaoTipoSelecionada = null;
				lacoOperacaoTipo : for(OperacaoTipo operacaoTipoColecao : colecaoOperacaoTipo){
					if(operacaoTipoColecao.getId().intValue() == idOperacaoTipo){
						operacaoTipoSelecionada = operacaoTipoColecao;
						break lacoOperacaoTipo;
					}
				}

				//Caso o tipo de operação seja "pesquisar"
				//habilita o campo argumento de pesquisa
				//Caso contrário desabilita o campo argumento de pesquisa
				if(idOperacaoTipo.intValue() == OperacaoTipo.PESQUISAR){
					sessao.setAttribute("habilitarArgumentoPesquisa","true");
					sessao.setAttribute("habilitarOperacaoPesquisa","false");
					sessao.setAttribute("colecaoOperacaoTabela",null);
					inserirOperacaoActionForm.setIdOperacaoPesquisa("");
					inserirOperacaoActionForm.setDescricaoOperacaoPesquisa("");
					
				}else{
					sessao.setAttribute("habilitarArgumentoPesquisa","false");
					inserirOperacaoActionForm.setIdArgumentoPesquisa("");
					inserirOperacaoActionForm.setDescricaoArgumentoPesquisa("");
				  
				  //Caso o tipo de operação tenha o indicador de atualiza a base de dados 
				  //setado para sim habilita o campo operação de pesquisa
				  //Caso contrário desabilita o campo operação de pesquisa	
				  if(operacaoTipoSelecionada.getIndicadorAtualiza() == ConstantesSistema.SIM){
					  sessao.setAttribute("habilitarOperacaoPesquisa","true");
				  }else{
					  sessao.setAttribute("habilitarOperacaoPesquisa","false");
					  sessao.setAttribute("colecaoOperacaoTabela",null);
					  inserirOperacaoActionForm.setIdOperacaoPesquisa("");
					  inserirOperacaoActionForm.setDescricaoOperacaoPesquisa("");
				  }
				}
			}
			
		}else{
			//Caso o usuário tenha apertado o botão de desfazer na tela 
			//volta o form ao estado inicial
			inserirOperacaoActionForm.setDescricao("");
			inserirOperacaoActionForm.setDescricaoAbreviada("");
			inserirOperacaoActionForm.setCaminhoUrl("");
			inserirOperacaoActionForm.setIdFuncionalidade("");
			inserirOperacaoActionForm.setDescricaoFuncionalidade("");
			inserirOperacaoActionForm.setIdTipoOperacao("-1");
			inserirOperacaoActionForm.setIdArgumentoPesquisa("");
			inserirOperacaoActionForm.setDescricaoArgumentoPesquisa("");
			inserirOperacaoActionForm.setIdOperacaoPesquisa("");
			inserirOperacaoActionForm.setDescricaoOperacaoPesquisa("");
			sessao.removeAttribute("colecaoOperacaoTabela");
			sessao.setAttribute("habilitarArgumentoPesquisa","false");
			sessao.setAttribute("habilitarOperacaoPesquisa","false");
		}
		
		//Retorna o mapeamento contido na variável retorno
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
		//filtroTabelaColuna.adicionarParametro(new ParametroSimples(FiltroTabelaColuna.INDICADOR_CHAVE_PRIMARIA, "2"));
		
		//Pesquisa o argumento de pesquisa na base de dados
		Collection colecaoTabelaColuna = Fachada.getInstancia().pesquisar(filtroTabelaColuna,TabelaColuna.class.getName());

		//Caso exista o argumento de pesquisa cadastrado na base de dados 
		//recupera o argumento de pesquisa da coleção
		
		/*[FS0011] - Verificar argumento de pesquisa 
		 * Caso 1
		 */	
		
		if(colecaoTabelaColuna != null && !colecaoTabelaColuna.isEmpty()){
			argumentoPesquisa = (TabelaColuna) Util.retonarObjetoDeColecao(colecaoTabelaColuna);
			
			if(argumentoPesquisa.getIndicadorPrimaryKey().intValue() == new Integer("2")){
				throw new ActionServletException("atencao.argumento_pesquisa_nao_chave_primaria");
			}
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
	
}
