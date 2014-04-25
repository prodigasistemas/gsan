package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.bean.PesquisarQtdeRotasSemCriteriosParaAcoesCobranca;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de Ação de Conbrança - Tipo de Comando Cronograma
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class InserirComandoAcaoCobrancaCronogramaAction  extends GcomAction{
	
	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("telaSucesso");

        //Mudar isso quando implementar a parte de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        InserirComandoAcaoCobrancaCronogramaActionForm form = (InserirComandoAcaoCobrancaCronogramaActionForm) actionForm;
        
		// -- Validar se todas as rotas do grupo informado possui um criterio definido para a açao de cobrança
        Collection<Integer> idsAcoesCobranca = new ArrayList<Integer>();
        idsAcoesCobranca.add(new Integer(form.getCobrancaAcao()));
        
        PesquisarQtdeRotasSemCriteriosParaAcoesCobranca filtro = new PesquisarQtdeRotasSemCriteriosParaAcoesCobranca();
        filtro.setIdGrupoCobranca(new Integer(form.getCobrancaGrupo()));
        filtro.setIdsAcoesCobranca(idsAcoesCobranca);
        
        Integer qtdeRotasSemCriterios = fachada.pesquisarQtdeRotasSemCriteriosParaAcoesCobranca(filtro);
		if (qtdeRotasSemCriterios != null && qtdeRotasSemCriterios.intValue() > 0) { 
			throw new ActionServletException("atencao.rotas.sem.criterio.para.acao.cobranca");
		}
        // ---------
        
        if(sessao.getAttribute("cobrancaAcaoAtividadeCronograma") != null){
        	
        	CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = 
        		(CobrancaAcaoAtividadeCronograma) sessao.getAttribute("cobrancaAcaoAtividadeCronograma");
        	
        	cobrancaAcaoAtividadeCronograma.setComando(new Date());
        	
        	
            //------------ REGISTRAR TRANSAÇÃO ----------------
            RegistradorOperacao registradorOperacao = new RegistradorOperacao(
    				Operacao.OPERACAO_INSERIR_COMANDO_ACAO_COBRANCA_CRONOGRAMA,
    				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
    						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
            
            Operacao operacao = new Operacao();
            operacao.setId(Operacao.OPERACAO_INSERIR_COMANDO_ACAO_COBRANCA_CRONOGRAMA);

            OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
            operacaoEfetuada.setOperacao(operacao);
            cobrancaAcaoAtividadeCronograma.setOperacaoEfetuada(operacaoEfetuada);
            cobrancaAcaoAtividadeCronograma.adicionarUsuario(Usuario.USUARIO_TESTE, 
     		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
            registradorOperacao.registrarOperacao(cobrancaAcaoAtividadeCronograma);
            //------------ REGISTRAR TRANSAÇÃO ----------------  
        	
        	//atualizar a data e hora do comandoi
        	fachada.atualizar(cobrancaAcaoAtividadeCronograma);
        	
        	
        }
        CobrancaGrupo cobrancaGrupo = null;
        if(sessao.getAttribute("cobrancaGrupo") != null ){
        	cobrancaGrupo = (CobrancaGrupo) sessao.getAttribute("cobrancaGrupo");
        }
        CobrancaAcao cobrancaAcao = null;
		if(sessao.getAttribute("cobrancaAcao") != null ){
			cobrancaAcao  = (CobrancaAcao)sessao.getAttribute("cobrancaAcao");
		}
		
		CobrancaAtividade cobrancaAtividade = null;
		if(sessao.getAttribute("cobrancaAtividade") != null ){
			cobrancaAtividade  = (CobrancaAtividade)sessao.getAttribute("cobrancaAtividade");
		}

        montarPaginaSucesso(httpServletRequest, "A Ação " + cobrancaAcao.getDescricaoCobrancaAcao()  
                + " do grupo "  
                + cobrancaGrupo.getDescricao()
                + ", para a atividade " 
                + cobrancaAtividade.getDescricaoCobrancaAtividade() 
                + " comandada com sucesso.", "Inserir outro Comando de Ação de Cobrança",
                "exibirInserirComandoAcaoCobrancaAction.do?menu=sim");

   		if(sessao.getAttribute("colecaoCobrancaGrupo") != null ){
   			sessao.removeAttribute("colecaoCobrancaGrupo");
   		}
		if(sessao.getAttribute("cobrancaGrupo") != null ){
			sessao.removeAttribute("cobrancaGrupo");
		}
		if(sessao.getAttribute("colecaoCobrancaGrupoCronogramaMensal") != null ){
			sessao.removeAttribute("colecaoCobrancaGrupoCronogramaMensal");
		}
		if(sessao.getAttribute("colecaoCobrancaAcao") != null ){
			sessao.removeAttribute("colecaoCobrancaAcao");
		}
		if(sessao.getAttribute("cobrancaAcao") != null ){
			sessao.removeAttribute("cobrancaAcao");
		}
		if(sessao.getAttribute("cobrancaAcaoCronogama") != null ){
			sessao.removeAttribute("cobrancaAcaoCronogama");
		}
		if(sessao.getAttribute("colecaoCobrancaAtividade") != null ){
			sessao.removeAttribute("colecaoCobrancaAtividade");
		}
		if(sessao.getAttribute("cobrancaAcaoAtividadeCronograma") != null ){
			sessao.removeAttribute("cobrancaAcaoAtividadeCronograma");
		}
		if(sessao.getAttribute("cobrancaAtividade") != null ){
			sessao.removeAttribute("cobrancaAtividade");
		}
        return retorno;
    }

}
