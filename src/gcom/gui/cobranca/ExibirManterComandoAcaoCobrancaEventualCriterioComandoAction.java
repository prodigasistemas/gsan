package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.fachada.Fachada;
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
 * [UC0244] Manter Comando de Ação de Conbrança - Tipo de Comando Cronograma
 * @author Rafael Santos
 * @since 24/04/2006
 */
public class ExibirManterComandoAcaoCobrancaEventualCriterioComandoAction  extends GcomAction{
	
	/**
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
                .findForward("exibirManterComandoAcaoCobrancaEventualCriterioComando");

        ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm = (ManterComandoAcaoCobrancaDetalhesActionForm) actionForm;        
        
        //Mudar isso quando implementar a parte de segurança
        HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        
		if(httpServletRequest.getParameter("cobrancaGrupo")== null){
			manterComandoAcaoCobrancaDetalhesActionForm.setCobrancaGrupo("-1");	
		}
		if(httpServletRequest.getParameter("gerenciaRegional")== null){
			manterComandoAcaoCobrancaDetalhesActionForm.setGerenciaRegional("-1");
		}
		if(httpServletRequest.getParameter("unidadeNegocio") == null){
			manterComandoAcaoCobrancaDetalhesActionForm.setUnidadeNegocio("-1");
		}
		if(httpServletRequest.getParameter("clienteRelacaoTipo") == null){
			manterComandoAcaoCobrancaDetalhesActionForm.setClienteRelacaoTipo("-1");
		}
        
        
        
        String idCobrancaAtividade = httpServletRequest.getParameter("idCobrancaAtividade");
        String idCobrancaAcao = httpServletRequest.getParameter("idCobrancaAcao");
        
		String anoMesContaInicial = manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoInicialConta(); 
		String anoMesContaFinal = manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoFinalConta();
		
		String anoMesVencimentoInicial = manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoVencimentoContaInicial();  
		String anoMesVencimentoFinal  = manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoVencimentoContaFinal();

		
		CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = null;
		if(sessao.getAttribute("cobrancaAcaoAtividadeComando") != null){
			cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando) sessao.getAttribute("cobrancaAcaoAtividadeComando");
			httpServletRequest.setAttribute("idCobrancaAcaoAtividadeComando", cobrancaAcaoAtividadeComando.getId().toString());
		}
		
		String idComandoSelecionado = httpServletRequest.getParameter("idComandoSelecionado");
		if(idComandoSelecionado != null){
			if(cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null){
				cobrancaAcaoAtividadeComando.getCobrancaCriterio().setId(new Integer(idComandoSelecionado));
				sessao.setAttribute("cobrancaAcaoAtividadeComando",cobrancaAcaoAtividadeComando);
			}else{
				CobrancaCriterio cobrancaCriterio = new CobrancaCriterio();
				cobrancaCriterio.setId(new Integer(idComandoSelecionado));
				cobrancaAcaoAtividadeComando.setCobrancaCriterio(cobrancaCriterio);
				sessao.setAttribute("cobrancaAcaoAtividadeComando",cobrancaAcaoAtividadeComando);

				
			}
		}
			
			
		//id criterio de cobrança
		//if(cobrancaAcaoAtividadeComando.getCobrancaCriterio() != null){
			//manterComandoAcaoCobrancaDetalhesActionForm.setIdCriterioCobranca(cobrancaAcaoAtividadeComando.getCobrancaCriterio().getId().toString());
		//}

		
		//id criterio de cobranca
		///if(manterComandoAcaoCobrancaDetalhesActionForm.getIdCriterioCobranca() != null 
			//	&& !manterComandoAcaoCobrancaDetalhesActionForm.getIdCriterioCobranca().equals("")){
			//httpServletRequest.setAttribute("idCriterioConbrancaSelecionado",manterComandoAcaoCobrancaDetalhesActionForm.getIdCriterioCobranca());	
		//}
		
		// [FS0012] - Verificar referência final menor que referência inicial
		fachada.validarAnoMesInicialFinalComandoAcaoCobranca(anoMesContaInicial,
				anoMesContaFinal);

		// [FS0014] - Verificar data final menos que data inicial
		fachada.verificarVencimentoContaComandoAcaoCobranca(
				anoMesVencimentoInicial, anoMesVencimentoFinal);
		
        
        //[SB0003] - Selecionar Cretério do Comando
        //pesquisar cobranca atividade
        if(idCobrancaAtividade != null && !idCobrancaAtividade.equals("")){
        	FiltroCobrancaAtividade filtroCobrancaAtividade = new FiltroCobrancaAtividade();
        	filtroCobrancaAtividade.adicionarParametro(new ParametroSimples(FiltroCobrancaAtividade.ID,idCobrancaAtividade));
        	
        	Collection colecaoCobrancaAtividade = fachada.pesquisar(filtroCobrancaAtividade,CobrancaAtividade.class.getName());

        	if(colecaoCobrancaAtividade != null && !colecaoCobrancaAtividade.isEmpty()){
        		
        		CobrancaAtividade cobrancaAtividade = (CobrancaAtividade)colecaoCobrancaAtividade.iterator().next();
        		if(cobrancaAtividade.getIndicadorExecucao().intValue() == 1){
        			httpServletRequest.setAttribute("habilitarExecutar","true");
        		}else{
        			httpServletRequest.setAttribute("habilitarExecutar","false");
        		}
        	}
        	
        }
        
        
        
        //pesquisar cobranca ação
        if(idCobrancaAcao != null && !idCobrancaAcao.equals("")){
        	Collection colecaoCriterioCobranca = null;
        	
        	FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
        	filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID,idCobrancaAcao));
        	
        	Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao,CobrancaAcao.class.getName());

        	if(colecaoCobrancaAcao != null && !colecaoCobrancaAcao.isEmpty()){
        		
        		CobrancaAcao cobrancaAcao = (CobrancaAcao)colecaoCobrancaAcao.iterator().next();
        		manterComandoAcaoCobrancaDetalhesActionForm.setDescricaoAcaoCobranca(cobrancaAcao.getDescricaoCobrancaAcao());
        	}
        
        	FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
        	//filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(FiltroCobrancaCriterio.ID_COBRANCA_ACAO,idCobrancaAcao));
        	
        	colecaoCriterioCobranca = fachada.pesquisar(filtroCobrancaCriterio,CobrancaCriterio.class.getName());
        	
        	
        	if(colecaoCriterioCobranca == null || colecaoCriterioCobranca.isEmpty()){
				throw new ActionServletException("atencao.pesquisa.nenhum_registro_tabela",
						null, "Tabela Cobrança Critério");
        	}
        	
            //carregar criterios de cobranca
            sessao.setAttribute("colecaoCriterioCobranca",colecaoCriterioCobranca);        	
        	
        }
        

        sessao.setAttribute("manterComandoAcaoCobrancaDetalhesActionForm",
        		manterComandoAcaoCobrancaDetalhesActionForm);
        return retorno;
    }

}
