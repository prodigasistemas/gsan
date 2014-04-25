package gcom.gui.faturamento.debito;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action que define o pré-processamento da página de pesquisar débitos a cobrar do imóvel 
 *
 * @author Pedro Alexandre
 * @date 13/03/2006
 */
public class ExibirPesquisarDebitoACobrarAction extends GcomAction {
	/**
	 * Pesquisa os débitos a cobrar existentes para o imóvel 
	 *
	 * [UC0271] Pesquisar Débito a Cobrar
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Pedro Alexandre
	 * @date 13/03/2006
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

		//Seta o mapeamento de retorno para a tela de pesquisar débitos a cobrar do imóvel
		ActionForward retorno = actionMapping.findForward("pesquisarDebitoACobrar");
		
		//Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		//Recupera o código do imóvel do request
		String idImovel = (String)httpServletRequest.getParameter("idImovel");
		
		//PesquisarDebitoACobrarActionForm pesquisarDebitoACobrarActionForm = (PesquisarDebitoACobrarActionForm)actionForm;
		
		
		
		
		/*
		  if (httpServletRequest.getParameter("limparForm") != null
	                && !httpServletRequest.getParameter("limparForm").equalsIgnoreCase("")) {
			  
			  pesquisarDebitoACobrarActionForm.setDataGeracaoDebitoFinal("");
			  pesquisarDebitoACobrarActionForm.setDataGeracaoDebitoInicial("");
			  pesquisarDebitoACobrarActionForm.setReferenciaDebitoFinal("");
			  pesquisarDebitoACobrarActionForm.setReferenciaDebitoInicial("");
			  
			  pesquisarDebitoACobrarActionForm.setIdTipoDebitoSelecionados(null);			  
			  pesquisarDebitoACobrarActionForm.setIdTipoDebito(null);
			  pesquisarDebitoACobrarActionForm.setIdSituacaoDebitoACobrar(null);
			  
			 // pesquisarDebitoACobrarActionForm.reset(actionMapping,httpServletRequest);
			  
		  }
		*/

	
		
		
		//Caso o código do imóvel tenha sido informado pelo caso de uso que chamou a tela de pesquisar débito a cobrar,
		//Caso contrário levanta a exceção para o usuário indicando que o imóvel não foi informado
		if(idImovel != null && !idImovel.trim().equalsIgnoreCase("")){
			
			//Pesquisa se o imóvel informado esta cadastrado no sistema,
			//Caso contrário, levanta a exceção para o usuário indicando que o imóvel não existe
			FiltroImovel filtroImovel = new FiltroImovel();
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovel));
        	Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
        	
        	//Caso o imóvel informado não tenha sido cadastrado no sistema, levantaa exceção para o usuário indicando que o
        	//imóvel informado não está cadastrado no sistema
        	//Caso contrário manda o código do imóvel no request
        	if (colecaoImovel == null || colecaoImovel.isEmpty()){
        		throw new ActionServletException("atencao.naocadastrado", null, "Imóvel");
        	}else{
        		httpServletRequest.setAttribute("idImovel",idImovel);
        	}
        	
		}else{
			throw new ActionServletException("atencao.naoinformado",null, "Imóvel");
		}
		
		//Cria o filtro para pesquisar as situações de conta no sistema
		FiltroDebitoCreditoSituacao filtroDebitoCreditoSituacao = new FiltroDebitoCreditoSituacao();
		filtroDebitoCreditoSituacao.setCampoOrderBy(FiltroDebitoCreditoSituacao.DESCRICAO);
		
		//Pesquisa todas as situações de débito a cobrar cadastradas no sistema
		Collection colecaoSituacaoDebitoACobrar = fachada.pesquisar(filtroDebitoCreditoSituacao, DebitoCreditoSituacao.class.getName());
		
		//[FS0005] Caso não exista nenhuma situação de débito a cobrar no sistema, levanta a exceção para o usuário indicando que 
		//nenhuma situação de débito a cobrar está cadastrada no sistema.
		//Caso contrário, manda as situações de débito a cobrar cadastradas no request 
        if(colecaoSituacaoDebitoACobrar == null || colecaoSituacaoDebitoACobrar.isEmpty()){
        	throw new ActionServletException("atencao.naocadastrado",null, "Situação de Débito a Cobrar");
        }else{
        	httpServletRequest.setAttribute("colecaoSituacaoDebitoACobrar",colecaoSituacaoDebitoACobrar);
        }
        
        //Cria o filtro para pesquisar os tipos de débitos cadastrados no sistema
        //e seta a ordenação do resultado pela descrição do tipo de débito
        FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        filtroDebitoTipo.setConsultaSemLimites(true);
        filtroDebitoTipo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);
        
        //Pesquisa todos os tipos de débitos cadastrados no sistema
        Collection colecaoTipoDebito = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
        
        //[FS0005] Caso não exista nenhum tipo de débito de débito a cobrar no sistema, levanta a exceção para o usuário indicando que 
		//nenhum tipo de débito de débito a cobrar está cadastrado no sistema.
		//Caso contrário, manda os tipos de débito de débito a cobrar cadastrados no request 
        if(colecaoTipoDebito == null || colecaoTipoDebito.isEmpty()){
        	throw new ActionServletException("atencao.naocadastrado",null, "Tipo de Débito");
        }else{
        	httpServletRequest.setAttribute("colecaoTipoDebito",colecaoTipoDebito);
        }
        
        if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaDebitoACobrar") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaDebitoACobrar",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaDebitoACobrar"));
		}
        
        
        //Retorna o mapeamento contido na variável retorno
		return retorno;
	}
}
