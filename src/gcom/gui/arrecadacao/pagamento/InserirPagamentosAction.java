package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.bean.InserirPagamentoViaCanetaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;


/**
 * Action responsável por inseri os pagamentos no sistema 
 *
 * @author Pedro Alexandre
 * @date 15/03/2006
 */
public class InserirPagamentosAction extends GcomAction {

   
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
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
     * @date 16/02/2006
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

    	//Cria a variável de retorno 
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        //Cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        Integer idPagamento = null;

        //Recupera o form 
        DynaValidatorActionForm pagamentoActionForm = (DynaValidatorActionForm) actionForm;
        
        //Recupera o tipo de inclusão
        String tipoInclusao = (String) pagamentoActionForm.get("tipoInclusao");
                
        //Recupera o aviso bancário e pequisa o objeto no sistema
        String idAvisoBancario = (String)pagamentoActionForm.get("idAvisoBancario");
        FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
        filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
        AvisoBancario avisoBancario = (AvisoBancario)(fachada.pesquisar(filtroAvisoBancario,AvisoBancario.class.getName())).iterator().next();
        
        //Caso o tipo de inclusão tenha sido manual
        if(tipoInclusao.equalsIgnoreCase("manual")){
        	
        	/*
        	 * Alterado por Raphael Rossiter em 26/09/2007
        	 * OBJ: Inserir manualmente mais de um pagamento por sessao.
        	 */
        	
        	Collection<Pagamento> colecaoPagamento = (Collection) sessao.getAttribute("colecaoPagamento");
        	
        	idPagamento = fachada.inserirPagamentos(colecaoPagamento, usuarioLogado, avisoBancario);
        	
        	
          //Caso o tipo de inclusão seja por caneta óptica 	 
        }else if(tipoInclusao.equalsIgnoreCase("caneta") || tipoInclusao.equalsIgnoreCase("ficha")){
        	
        	/*
        	 * Alterado por Raphael Rossiter em 30/10/2007
        	 */
        	
        	Collection<Pagamento> colecaoPagamentos = new ArrayList();
        	Collection<Devolucao> colecaoDevolucoes = new ArrayList();
        	
        	//Recupera a coleçaõ de documentos da sessão contendo o código de barras e o valor do pagamento
            Collection<InserirPagamentoViaCanetaHelper> colecaoInserirPagamentoViaCanetaHelper = 
            (Collection<InserirPagamentoViaCanetaHelper>) sessao.getAttribute("colecaoInserirPagamentoViaCanetaHelper");
        	
            //[FS0006] - Verificar existência de documento na lista
            //Caso não exista nenhum documento na lista, levanta uma exceção 
            //para o usuário indicando que nenhum documento foi informado
            if(colecaoInserirPagamentoViaCanetaHelper == null || colecaoInserirPagamentoViaCanetaHelper.isEmpty()){
            	throw new ActionServletException("atencao.documento_naoinformado");
            }
            
            //[FS0025] – Verificar valor do aviso bancário
            //Caso o valor calculado do aviso bancário seja maior que valor informado 
//            if (avisoBancario.getValorArrecadacaoCalculado()
//            		.compareTo(avisoBancario.getValorArrecadacaoInformado()) == 1 ){
//            	throw new ActionServletException("atencao.soma_dos_valores_maior_informado");
//            }
        	
        	for(InserirPagamentoViaCanetaHelper pagamentoViaCanetaHelper : colecaoInserirPagamentoViaCanetaHelper){
            	
            	colecaoPagamentos.addAll(pagamentoViaCanetaHelper.getColecaoPagamento());
            	
            	if (pagamentoViaCanetaHelper.getColecaoDevolucao() != null &&
            		!pagamentoViaCanetaHelper.getColecaoDevolucao().isEmpty()){
            		
            		colecaoDevolucoes.addAll(pagamentoViaCanetaHelper.getColecaoDevolucao());
            	}
        	}
        	
        	idPagamento = fachada.inserirPagamentosCodigoBarras(colecaoPagamentos, colecaoDevolucoes, 
        	usuarioLogado, avisoBancario);
        	
        }else{
        	////Caso o tipo de inclusão não tenha sido informado, levanta uma exceção para o usuário 
        	//indicando que o tipo de inclusão não foi informado 
        	throw new ActionServletException("atencao.naoinformado",null, "Tipo de Inclusão");
        }
        
        //Caso o retorno seja para a telade sucesso,
        //Monta a tela de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
        	
        	//Limpa a sessão
            sessao.removeAttribute("colecaoFormaArrecadacao");
            sessao.removeAttribute("PagamentoActionForm");
            sessao.removeAttribute("colecaoDocumentoTipo");
            sessao.removeAttribute("colecaoPagamentos");
            sessao.removeAttribute("colecaoInserirPagamentoViaCanetaHelper");
        	
           /* montarPaginaSucesso(httpServletRequest, mensagemSucesso,
            "Inserir outro Pagamento", "exibirInserirPagamentosAction.do");*/
            
            
            montarPaginaSucesso(httpServletRequest, "Pagamento inserido com sucesso."
            		, "Inserir outro Pagamento",
                    "exibirInserirPagamentosAction.do?menu=sim",
                    "exibirAtualizarPagamentosAction.do?idRegistroInseridoAtualizar="
					+ idPagamento,
					"Atualizar Pagamento Inserido");

        
        
            
            
        }

        //Retorna o mapeamento contido na variável retorno
        return retorno;
    }
}
