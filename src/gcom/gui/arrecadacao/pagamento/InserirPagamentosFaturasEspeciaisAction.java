package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.bean.InserirPagamentoViaCanetaHelper;
import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action responsável por inseri os pagamentos no sistema 
 *
 * [UC0971] Inserir Pagamentos para Faturas Especiais
 * 
 * @author 	Vivianne Sousa
 * @created	21/12/2009
 */
public class InserirPagamentosFaturasEspeciaisAction extends GcomAction {

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
        
        //Recupera o form 
        PagamentosFaturasEspeciaisActionForm pagamentoActionForm = (PagamentosFaturasEspeciaisActionForm) actionForm;
        
        Integer idFormaArrecadacao = new Integer(pagamentoActionForm.getIdFormaArrecadacao());
        
        //[FS0001] - Validar data do pagamento
        //Recupera a data de pagamento e verifica se a data é uma data válida
        String dataPagamentoString = pagamentoActionForm.getDataPagamento();
        Date dataPagamento = null;
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataPagamento = dataFormato.parse(dataPagamentoString);
        } catch (ParseException ex) {
        	throw new ActionServletException("atencao.data_pagamento_invalida");
        }
        
        //Recupera o aviso bancário e pequisa o objeto no sistema
        String idAvisoBancario = pagamentoActionForm.getIdAvisoBancario();
        FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
        filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
        AvisoBancario avisoBancario = (AvisoBancario)(fachada.pesquisar(filtroAvisoBancario,AvisoBancario.class.getName())).iterator().next();
        
    	//Recupera a coleçaõ de documentos da sessão contendo o código de barras e o valor do pagamento
        Collection<InserirPagamentoViaCanetaHelper> colecaoInserirPagamentoViaCanetaHelper = 
        (Collection<InserirPagamentoViaCanetaHelper>) sessao.getAttribute("colecaoInserirPagamentoViaCanetaHelper");
    	
        //[FS0006] - Verificar existência de documento na lista
        //Caso não exista nenhum documento na lista, levanta uma exceção 
        //para o usuário indicando que nenhum documento foi informado
        if(colecaoInserirPagamentoViaCanetaHelper == null || colecaoInserirPagamentoViaCanetaHelper.isEmpty()){
        	throw new ActionServletException("atencao.documento_naoinformado");
        }
       	
    	//Limpa a sessão
        sessao.removeAttribute("colecaoFormaArrecadacao");
        sessao.removeAttribute("colecaoInserirPagamentoViaCanetaHelper");
        
        //Este map levará todos os parâmetros para a inicialização do processo
        Map parametros = new HashMap();
        parametros.put("colecaoInserirPagamentoViaCanetaHelper",colecaoInserirPagamentoViaCanetaHelper);
        parametros.put("usuarioLogado",usuarioLogado);
        parametros.put("avisoBancario",avisoBancario);
        parametros.put("idFormaArrecadacao",idFormaArrecadacao);
        parametros.put("dataPagamento",dataPagamento);
        
    	Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
         		Processo.INSERIR_PAGAMENTOS_FATURAS_ESPECIAIS, this.getUsuarioLogado(httpServletRequest));
   
        montarPaginaSucesso(httpServletRequest, "A inserção de pagamentos para os itens da(s) fatura(s) informada(s) foi direcionado para processamento batch com sucesso.", "", "");
        
        return retorno;
    }
}
