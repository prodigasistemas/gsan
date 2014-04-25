package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativacaoComando;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.bean.DadosInclusoesComandoNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorMovimentoReg;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class ExibirConsultarDadosInclusoesAction extends GcomAction {

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

//   	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirConsultarDadosInclusoes");
		HttpSession sessao = httpServletRequest.getSession(false);
		ConsultarDadosInclusoesNegativacaoComandoActionForm form = (ConsultarDadosInclusoesNegativacaoComandoActionForm) actionForm; 
		
		Fachada fachada = Fachada.getInstancia();
		
		if ((httpServletRequest.getParameter("idSelecionado") != null && !httpServletRequest.getParameter("idSelecionado").equals("")) ||
				(sessao.getAttribute("idSelecionado") != null && !sessao.getAttribute("idSelecionado").equals(""))){
			String idSelecionado = "";
			if (httpServletRequest.getParameter("idSelecionado") != null && !httpServletRequest.getParameter("idSelecionado").equals("")){
				idSelecionado = httpServletRequest.getParameter("idSelecionado");
				sessao.setAttribute("idSelecionado", httpServletRequest.getParameter("idSelecionado"));
			} else {
				idSelecionado = (String) sessao.getAttribute("idSelecionado");
			}
			
			FiltroNegativacaoComando filtroNegativacaoComando = new FiltroNegativacaoComando();
			filtroNegativacaoComando.adicionarParametro(new ParametroSimples(FiltroNegativacaoComando.ID, new Integer(idSelecionado)));
			filtroNegativacaoComando.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
			
			Collection collNegativacaoComando = Fachada.getInstancia().pesquisar(filtroNegativacaoComando, NegativacaoComando.class.getName());
			NegativacaoComando negativacaoComando = (NegativacaoComando)collNegativacaoComando.iterator().next();
			
			if (negativacaoComando.getNegativador().getCliente().getNome() == null){
				form.setNegativador("");
			} else {
				form.setNegativador(negativacaoComando.getNegativador().getCliente().getNome());
			}
			
			if (negativacaoComando.getQuantidadeInclusoes() == null){
				form.setQuantidadeInclusoes("");
			} else {
				form.setQuantidadeInclusoes(negativacaoComando.getQuantidadeInclusoes().toString());
			}
			
			if (negativacaoComando.getValorDebito() == null){
				form.setValorTotalDebito("");
			} else {
				form.setValorTotalDebito(Util.formatarMoedaReal(negativacaoComando.getValorDebito()));
			}
			
			if (negativacaoComando.getQuantidadeItensIncluidos() == null){
				form.setQuantidadeItensIncluidos("");
			} else {
				form.setQuantidadeItensIncluidos(negativacaoComando.getQuantidadeItensIncluidos().toString());
			}
			
			FiltroNegativadorMovimentoReg filtroNegMovReg = new FiltroNegativadorMovimentoReg();
			filtroNegMovReg.adicionarParametro(new ParametroNaoNulo("imovel.id"));
			filtroNegMovReg.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoReg.NEGATIVADOR_MOVIMENTO_NEGATIVACAO_COMANDO_ID, negativacaoComando.getId()));
			filtroNegMovReg.adicionarCaminhoParaCarregamentoEntidade("imovel");
			filtroNegMovReg.adicionarCaminhoParaCarregamentoEntidade("cobrancaDebitoSituacao");
			filtroNegMovReg.adicionarCaminhoParaCarregamentoEntidade("usuario");
			
//			Map resultado = controlarPaginacao(httpServletRequest, retorno,
//					filtroNegMovReg, NegativadorMovimentoReg.class.getName());
//			
//			
//			Collection collNegativadorMovimentoReg = (Collection) resultado.get("colecaoRetorno");
//			retorno = (ActionForward) resultado.get("destinoActionForward");
			
//			Collection collNegativadorMovimentoReg = fachada.pesquisar(filtroNegMovReg, NegativadorMovimentoReg.class.getName());
//					
//			
//			sessao.setAttribute("collNegativadorMovimentoReg", collNegativadorMovimentoReg);
			
			
			
			//PAGINACAO DA TABELA			
			
			Integer idComandoNegativacao = new Integer(idSelecionado);
		       
        	Integer totalRegistrosSegundaPaginacao = 0;
        	if(sessao.getAttribute("totalRegistrosSegundaPaginacao") == null ){
        		
        		totalRegistrosSegundaPaginacao = (Integer)fachada.pesquisarDadosInclusoesComandoNegativacao(idComandoNegativacao);

        		// 1º Passo - Pegar o total de registros através de um count da consulta que aparecerá na tela
        		 
        		
        	}else{
        		totalRegistrosSegundaPaginacao = (Integer)sessao.getAttribute("totalRegistrosSegundaPaginacao");
        	}
			
			// 2º Passo - Chamar a função de Paginação passando o total de registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,totalRegistrosSegundaPaginacao,false);
	
			// 3º Passo - Obter a coleção da consulta que aparecerá na tela passando o numero de paginas
			// da pesquisa que está no request
//			Collection collectionDadosInclusoesComandoNegativacao = fachada.pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(idComandoNegativacao,
//					(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisaSegundaPaginacao"));
			
			
			Collection collectionDadosInclusoesComandoNegativacao = fachada.pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(idComandoNegativacao,
					(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisaSegundaPaginacao"));
			
			//[FS0006 NENHUM REGISTRO ENCONTRADO]
			if (collectionDadosInclusoesComandoNegativacao == null || collectionDadosInclusoesComandoNegativacao.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
			// SETANDO OS DADOS GERAIS DA TELA
			
			DadosInclusoesComandoNegativacaoHelper dadosInclusoesComandoNegativacaoHelper = (DadosInclusoesComandoNegativacaoHelper)
			collectionDadosInclusoesComandoNegativacao.iterator().next();
			
			if (dadosInclusoesComandoNegativacaoHelper.getNomeCliente() != null && 
					!dadosInclusoesComandoNegativacaoHelper.getNomeCliente().equals("")){
				
				form.setNegativador(dadosInclusoesComandoNegativacaoHelper.getNomeCliente());
			}
			if (dadosInclusoesComandoNegativacaoHelper.getQuantidadeInclusoes()!= null &&
					!dadosInclusoesComandoNegativacaoHelper.getQuantidadeInclusoes().equals("")){
				
				form.setQuantidadeInclusoes(dadosInclusoesComandoNegativacaoHelper.getQuantidadeInclusoes().toString());
			}
			if (dadosInclusoesComandoNegativacaoHelper.getValorTotalDebito()!= null && 
					!dadosInclusoesComandoNegativacaoHelper.getValorTotalDebito().equals("")){
				
				form.setValorTotalDebito(Util.formatarMoedaReal(dadosInclusoesComandoNegativacaoHelper.getValorTotalDebito()));
			}
			if (dadosInclusoesComandoNegativacaoHelper.getQuantidadeItensIncluidos()!= null && 
					!dadosInclusoesComandoNegativacaoHelper.getQuantidadeItensIncluidos().equals("")){
				
				form.setQuantidadeItensIncluidos(dadosInclusoesComandoNegativacaoHelper.getQuantidadeItensIncluidos().toString());
			}
			
			
			sessao.setAttribute("collectionDadosInclusoesComandoNegativacao", collectionDadosInclusoesComandoNegativacao);			
			sessao.setAttribute("totalRegistrosSegundaPaginacao", totalRegistrosSegundaPaginacao);
			sessao.setAttribute("numeroPaginasPesquisaSegundaPaginacao",httpServletRequest.getAttribute("numeroPaginasPesquisaSegundaPaginacao"));
			
			
		}
		return retorno;
        
    }
}
