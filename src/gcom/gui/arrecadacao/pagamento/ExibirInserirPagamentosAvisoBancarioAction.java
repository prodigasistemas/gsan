package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

/**
 * Action que inicializa a primeira página do processo de inserir pagamentos
 * 
 * @author Pedro Alexandre
 * @created 16/02/2006
 */
public class ExibirInserirPagamentosAvisoBancarioAction extends GcomAction {

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
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
        //seta o mapeamento de retorno para a página da primeira aba
        ActionForward retorno = actionMapping.findForward("inserirPagamentosAvisoBancario");
        
        DynaValidatorActionForm pagamentoActionForm = (DynaValidatorActionForm) actionForm;

        //cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //cria o filtro de forma de arrecadação
        FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
        
        //seta a ordenação do resultado da pesquisa
        filtroArrecadacaoForma.setCampoOrderBy(FiltroArrecadacaoForma.DESCRICAO);

        //pesquisa as formas de arrecadação no sistema
        Collection<ArrecadacaoForma> colecaoFormaArrecadacao = fachada.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
        	
        //se a coleção de forma de arrecadação estiver nula ou vazia 
        if(colecaoFormaArrecadacao == null || colecaoFormaArrecadacao.isEmpty()){
        	//[FS0002] levanta exceção
        	throw new ActionServletException("atencao.naocadastrado", null,"Forma de Arrecadação");
        }else{
        	//manda a coleção de forma de arrecadação para a sessão
        	sessao.setAttribute("colecaoFormaArrecadacao", colecaoFormaArrecadacao);	
        }
        
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        //Data Corrente
        httpServletRequest.setAttribute("dataAtual", formatoData.format(dataCorrente.getTime()));
        
        String idAvisoBancario = (String) pagamentoActionForm.get("idAvisoBancario");
        
        if (idAvisoBancario != null && !idAvisoBancario.trim().equals("")) {
        	FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
        	filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
        	filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
        	
        	Collection colecaoAvisoBancario = fachada.pesquisar(filtroAvisoBancario, AvisoBancario.class.getName());
        	
        	if (colecaoAvisoBancario != null && !colecaoAvisoBancario.isEmpty()) {
        		AvisoBancario avisoBancario = (AvisoBancario) Util.retonarObjetoDeColecao(colecaoAvisoBancario);
        		
        		pagamentoActionForm.set("codigoAgenteArrecadador", avisoBancario.getArrecadador().getCodigoAgente().toString());
        		pagamentoActionForm.set("dataLancamentoAviso", Util.formatarData(avisoBancario.getDataLancamento()));
        		pagamentoActionForm.set("numeroSequencialAviso", avisoBancario.getNumeroSequencial().toString());
        		
        	} else {
        		
        		pagamentoActionForm.set("idAvisoBancario", "");
        		pagamentoActionForm.set("codigoAgenteArrecadador", "");
        		pagamentoActionForm.set("dataLancamentoAviso", "AVISO INEXISTENTE");
        		pagamentoActionForm.set("numeroSequencialAviso", "");
        		
        		httpServletRequest.setAttribute("avisoInexistente", true);
        	}
        } else {
        	pagamentoActionForm.set("dataLancamentoAviso", "");
        }
        
    	
        
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
}
