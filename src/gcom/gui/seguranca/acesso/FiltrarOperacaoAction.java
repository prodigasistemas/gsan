package gcom.gui.seguranca.acesso;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroFuncionalidade;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por gerar um filtro  de operação para pesquisa de acordo 
 * com os parâmetros informados pelo usuário
 *
 * @author Pedro Alexandre 
 * @date 12/05/2006
 */
public class FiltrarOperacaoAction   extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
		
		//Cria a variavel de retorno e seta o mapeamento para o exibirManterOperacaoAction
    	ActionForward retorno = actionMapping.findForward("retornarFiltroOperacao");
    	
    	//Recupera o form de filtrar operação
        FiltrarOperacaoActionForm filtrarOperacaoActionForm = (FiltrarOperacaoActionForm) actionForm;
        
        //Cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Cria uma flag que vai indicar se o usuário informou ao menos um campo para a filtragem
        Boolean peloMenosUmParametroInformado = false;
        
        //Recupera os campos informados pelo usuário na página para filtrar as operações 
        String idOperacao = filtrarOperacaoActionForm.getIdOperacao();
		String descricaoOperacao = filtrarOperacaoActionForm.getDescricaoOperacao();
		Integer idTipoOperacao = new Integer(filtrarOperacaoActionForm.getIdTipoOperacao());
		String idFuncionalidade = filtrarOperacaoActionForm.getIdFuncionalidade();	
		String tipoPesquisa = filtrarOperacaoActionForm.getTipoPesquisa();
		
		//Recupera o indicador de atualização do request
		// 1 check   --- null uncheck 
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");

		//Cria o filtro e carrega os objetos necessários no filtro para o exibirManterOperacaoAction
		FiltroOperacao filtroOperacao = new FiltroOperacao();
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("operacaoTipo");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("idOperacaoPesquisa");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("tabelaColuna");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.ARGUMENTO_PESQUISA);

		//Caso o usário tenha informado o código da operação
		//seta o código da operação no filtro e indica que o usuário 
		//selecionou um parâmetro para a filtragem
		if (idOperacao != null && !idOperacao.equals("")){
			filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID,idOperacao)); 
			peloMenosUmParametroInformado = true;
		}		

		//Caso o usário tenha informado descrição da operação
		//seta a descrição da operação no filtro e indica que o usuário 
		//selecionou um parâmetro para a filtragem
		if ((descricaoOperacao != null)&& (!descricaoOperacao.trim().equals(""))) {
			if (tipoPesquisa != null && tipoPesquisa.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				filtroOperacao.adicionarParametro(new ComparacaoTextoCompleto(FiltroOperacao.DESCRICAO, descricaoOperacao));
			} else {
				filtroOperacao.adicionarParametro(new ComparacaoTexto(FiltroOperacao.DESCRICAO,descricaoOperacao));
			}
			peloMenosUmParametroInformado = true;
		}
		
		//Caso o usário tenha informado o tipo da operação
		//seta o código do tipo da operação no filtro e indica que o usuário 
		//selecionou um parâmetro para a filtragem
		if (idTipoOperacao != null && idTipoOperacao.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
    		filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.OPERACAO_TIPO_ID,idTipoOperacao.toString()));
			peloMenosUmParametroInformado = true;
		}
		

		//Caso o usário tenha informado o código da funcionalidade
		//seta o código da funcionalidade no filtro e indica que o usuário 
		//selecionou um parâmetro para a filtragem
		if (idFuncionalidade != null && !idFuncionalidade.trim().equals("")) {

			//[FS0002] - Verificar existência da funcionalidade 
			//Pesquisa a funcionalidade informada pelo usuário no sistema 
			FiltroFuncionalidade filtroFuncionalidade = new FiltroFuncionalidade();
			filtroFuncionalidade.adicionarParametro(new ParametroSimples(FiltroFuncionalidade.ID,idFuncionalidade));
			Collection colecaoFuncionalidade = fachada.pesquisar(filtroFuncionalidade,Funcionalidade.class.getName());
			
			//Caso a funcionalidade informada não exista no sistema 
			//levanta uma execeção indicanf]do que a funcionalidade não existe
			//Caso contrário seta ocódigo da funcionalidade no filtro 
			if(colecaoFuncionalidade == null || colecaoFuncionalidade.isEmpty()){
				throw new ActionServletException("atencao.funcionalidade.inexistente");
			}else{
				filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.FUNCIONALIDADE_ID,idFuncionalidade));
				peloMenosUmParametroInformado = true;
			}
		}

		//[FS0003] - Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		//Seta na sessão o filtro criado para pesquisa de operação e a flag 
		//indicando que o usuário que ir diretopara a página de atualizar
		sessao.setAttribute("filtroOperacao",filtroOperacao);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
	
	   //Retorna o mapeamento contido na variável retorno 	
       return retorno;
    }
}
