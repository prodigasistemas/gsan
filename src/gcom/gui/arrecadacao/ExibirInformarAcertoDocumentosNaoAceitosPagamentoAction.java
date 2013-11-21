package gcom.gui.arrecadacao;

import java.util.Collection;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.InformarAcertoDocumentosNaoAceitosPagamentoHelper;
import gcom.arrecadacao.PagamentoDocumentosNaoAceitosHelper;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1214] Informar Acerto Documentos Não Aceitos
 *
 * @author Mariana Victor
 * @date 23/08/2011
 */
public class ExibirInformarAcertoDocumentosNaoAceitosPagamentoAction extends GcomAction {

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
        ActionForward retorno = actionMapping.findForward("informarAcertoDocumentosNaoAceitosPagamento");
        
        InformarAcertoDocumentosNaoAceitosActionForm form = (InformarAcertoDocumentosNaoAceitosActionForm) actionForm;

        //cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();

    	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        //cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
        filtroArrecadacaoForma.setCampoOrderBy(FiltroArrecadacaoForma.DESCRICAO);
        
        Collection<ArrecadacaoForma> colecaoFormaArrecadacao = fachada.pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
        	
        if(colecaoFormaArrecadacao == null || colecaoFormaArrecadacao.isEmpty()){
        	throw new ActionServletException("atencao.naocadastrado", null,"Forma de Arrecadação");
        }else{
        	sessao.setAttribute("colecaoFormaArrecadacao", colecaoFormaArrecadacao);	
        }
        

		String idArrecadador = form.getIdArrecadador();
		// Pesquisa o arrecadador
		if (idArrecadador != null
				&& !idArrecadador.trim().equals("")) {

			FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
			filtroArrecadador.adicionarParametro(new ParametroSimples(
					FiltroArrecadador.CODIGO_AGENTE,idArrecadador));
			filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			Collection colecaoArrecadador = this.getFachada().pesquisar(
					filtroArrecadador, Arrecadador.class.getName());

			//[FS0001] ? Verificar existência do arrecadador
			if (colecaoArrecadador != null
					&& !colecaoArrecadador.isEmpty()) {

				Arrecadador arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(colecaoArrecadador);

				form.setIdArrecadador(arrecadador.getCodigoAgente().toString());
				form.setNomeArrecadador(arrecadador.getCliente().getNome());
				httpServletRequest.setAttribute(
						"idArrecadadorNaoEncontrado", "true");
				
			} else {

				form.setIdArrecadador("");
				form.setNomeArrecadador("Arrecadador com código " + idArrecadador + " não existe");
				httpServletRequest.setAttribute(
						"idArrecadadorNaoEncontrado", "exception");

			}
		}
		
        String idAvisoBancario = form.getIdAvisoBancario();
        if (idAvisoBancario != null && !idAvisoBancario.trim().equals("")) {
        	FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
        	filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
        	filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
        	
        	Collection colecaoAvisoBancario = fachada.pesquisar(filtroAvisoBancario, AvisoBancario.class.getName());
        	
        	if (colecaoAvisoBancario != null && !colecaoAvisoBancario.isEmpty()) {
        		AvisoBancario avisoBancario = (AvisoBancario) Util.retonarObjetoDeColecao(colecaoAvisoBancario);
        		
        		form.setCodigoAgenteArrecadador(avisoBancario.getArrecadador().getCodigoAgente().toString());
        		form.setDataLancamentoAviso(Util.formatarData(avisoBancario.getDataLancamento()));
        		form.setNumeroSequencialAviso(avisoBancario.getNumeroSequencial().toString());
        		
        	} else {
        		
        		form.setIdAvisoBancario("");
        		form.setCodigoAgenteArrecadador("");
        		form.setDataLancamentoAviso("Aviso Inexistente");
        		form.setNumeroSequencialAviso("");
        		
        		httpServletRequest.setAttribute("avisoInexistente", true);
        	}
        } else {
        	form.setDataLancamentoAviso("");
        }

        // 4. O usuário efetua a filtragem 
        if (httpServletRequest.getParameter("filtrarPagamentos") != null
        		&& httpServletRequest.getParameter("filtrarPagamentos").toString().trim().equalsIgnoreCase("sim")) {

        	// [FS0004] - Verificar preenchimento dos campos obrigatórios
        	InformarAcertoDocumentosNaoAceitosPagamentoHelper helper = this.carregarHelperParaPesquisa(form, sistemaParametro);
        	
        	Collection<PagamentoDocumentosNaoAceitosHelper> colecaoPagamentoDocumentosNaoAceitosHelper = 
        		fachada.pesquisarPagamentosDocumentosNaoAceitos(helper);
        	
        	if (colecaoPagamentoDocumentosNaoAceitosHelper != null
        			&& !colecaoPagamentoDocumentosNaoAceitosHelper.isEmpty()) {
        		
        		sessao.setAttribute("colecaoPagamentoDocumentosNaoAceitosHelper", 
        				colecaoPagamentoDocumentosNaoAceitosHelper);
        		
        	} else {
        		sessao.removeAttribute("colecaoPagamentoDocumentosNaoAceitosHelper");
        		
    			throw new ActionServletException(
    					"atencao.pesquisa.nenhumresultado");
        	}
        	
        }
        
        return retorno;
    }
    
    
    /**
     * 
     * 4. O usuário efetua a filtragem [FS004 - Verificar preenchimento dos campos obrigatórios
     * 
     * */
    public InformarAcertoDocumentosNaoAceitosPagamentoHelper carregarHelperParaPesquisa(
    		InformarAcertoDocumentosNaoAceitosActionForm form,
    		SistemaParametro sistemaParametro) {

    	InformarAcertoDocumentosNaoAceitosPagamentoHelper helper = new InformarAcertoDocumentosNaoAceitosPagamentoHelper();
    	
    	
    	if(form.getPeriodoPagamentoInicial() == null
    			|| form.getPeriodoPagamentoInicial().trim().equals("")) {

			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", "Período de Pagamento Inicial");
    	} else {
    		helper.setPeriodoPagamentoInicial(
    				Util.formatarDataInicial(
    						Util.converteStringParaDate(form.getPeriodoPagamentoInicial())));
    	}

    	if(form.getPeriodoPagamentoFinal() == null
    			|| form.getPeriodoPagamentoFinal().trim().equals("")) {

			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", "Período de Pagamento Final");
    	} else {
    		helper.setPeriodoPagamentoFinal(
    				Util.formatarDataFinal(
    						Util.converteStringParaDate(form.getPeriodoPagamentoFinal())));
    	}

    	if (sistemaParametro.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados() == null
    			|| sistemaParametro.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados().getId() == null) {

			throw new ActionServletException(
					"atencao.sistema_nao_parametrizado.para_documentos.nao_aceitos");
			
    	} else {
    		helper.setIdClienteFicticio(
    				sistemaParametro.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados().getId());
    	}

    	if(form.getIdArrecadador() != null
    			&& !form.getIdArrecadador().trim().equals("")) {
    		helper.setIdArrecadador(
    				new Integer(form.getIdArrecadador()));
    	}
    	
    	if(form.getIdAvisoBancario() != null
    			&& !form.getIdAvisoBancario().trim().equals("")) {

    		helper.setIdAvisoBancario(
    				new Integer(form.getIdAvisoBancario()));
    	}
    	
    	if(form.getIdArrecadadorMov() != null
    			&& !form.getIdArrecadadorMov().trim().equals("")) {

    		helper.setIdArrecadadorMov(
    				new Integer(form.getIdArrecadadorMov()));
    	}
    	
    	if(form.getIdFormaArrecadacao() != null
    			&& !form.getIdFormaArrecadacao().trim().equals("")
    			&& !form.getIdFormaArrecadacao().trim().equals("-1")) {

    		helper.setIdFormaArrecadacao(
    				new Integer(form.getIdFormaArrecadacao()));
    	}
    	
    	return helper;
    }

}
