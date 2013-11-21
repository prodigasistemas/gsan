package gcom.gui.atendimentopublico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import gcom.atendimentopublico.EspecificacaoUnidadeCobranca;
import gcom.atendimentopublico.EspecificacaoUnidadeCobrancaPK;
import gcom.atendimentopublico.FiltroEspecificacaoUnidadeCobranca;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
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

public class InformarAssociacaoSituacaoCobrancaAction extends GcomAction{
	
	/**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ActionForward retorno = actionMapping
			.findForward("exibirInformarAssociacaoSituacaoCobranca");

		InformarTramiteAssociacaoSituacaoCobrancaActionForm informarTramiteAssociacaoSituacaoCobrancaActionForm = (InformarTramiteAssociacaoSituacaoCobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Integer idSituacaoCobranca = null;
		Integer idTipoEspecificacao = null;
		Integer idUnidade = null;
		
		List<EspecificacaoUnidadeCobranca> colecaoEspecificacaoUnidadeCobranca = new ArrayList();
		if(sessao.getAttribute("colecaoEspecificacaoUnidadeCobranca") != null
				&& !sessao.getAttribute("colecaoEspecificacaoUnidadeCobranca").equals("")){
			
			colecaoEspecificacaoUnidadeCobranca = (ArrayList) 
    			sessao.getAttribute("colecaoEspecificacaoUnidadeCobranca");
			
    	}
		
		if (informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdSituacaoCobranca() != null
				&& !informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdSituacaoCobranca().trim().equals("")) {
			idSituacaoCobranca = new Integer(informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdSituacaoCobranca());
		} else {
			throw new ActionServletException(
					"atencao.informe.situacao_cobranca", null, "Situação de Cobrança");
		}
		
		if (informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdTipoSolicitacao() == null
				|| informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdTipoSolicitacao().trim().equals("")) {
			throw new ActionServletException(
					"atencao.informe.tipo_solicitacao", null, "Tipo de Solicitação");
		}
		
		if (informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdTipoEspecificacao() != null
				&& !informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdTipoEspecificacao().trim().equals("")) {
			idTipoEspecificacao = new Integer(informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdTipoEspecificacao());
		} else {
			throw new ActionServletException(
					"atencao.informe.tipo_especificacao", null, "Tipo de Especificacao");
		}
		
		if (informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdUnidadeAtendimento() != null
				&& !informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdUnidadeAtendimento().trim().equals("")) {
			idUnidade = new Integer(informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdUnidadeAtendimento());
		} else {
			throw new ActionServletException(
					"atencao.informe.unidade_atendimento", null, "Unidade Atendimento");
		}
		
		EspecificacaoUnidadeCobranca especificacaoUnidadeCobranca = new EspecificacaoUnidadeCobranca();
		EspecificacaoUnidadeCobrancaPK comp = new EspecificacaoUnidadeCobrancaPK();
		comp.setCobrancaSituacaoId(idSituacaoCobranca);
		comp.setSolicitacaoTipoEspecificacaoId(idTipoEspecificacao);
		
		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
		filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
				FiltroCobrancaSituacao.ID, idSituacaoCobranca));
		Collection colecaoCobrancaSituacao = fachada.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
		if (colecaoCobrancaSituacao != null && !colecaoCobrancaSituacao.isEmpty()) {
			CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) Util.retonarObjetoDeColecao(colecaoCobrancaSituacao);
			especificacaoUnidadeCobranca.setCobrancaSituacao(cobrancaSituacao);
			comp.setCobrancaSituacaoId(cobrancaSituacao.getId());
		} else {
			throw new ActionServletException(
					"atencao.situacao_cobranca.inexistente", null, "Situação de Cobrança");
		}
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID, idTipoEspecificacao));
		Collection colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
		if (colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
			especificacaoUnidadeCobranca.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
			comp.setSolicitacaoTipoEspecificacaoId(solicitacaoTipoEspecificacao.getId());
		} else {
			throw new ActionServletException(
					"atencao.tipo_especificacao.inexistente", null, "Situação de Cobrança");
		}

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, idUnidade));
		Collection colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);
			especificacaoUnidadeCobranca.setUnidadeOrganizacional(unidadeOrganizacional);
		} else {
			throw new ActionServletException(
					"atencao.unidade_atendimento.inexistente", null, "Unidade Atendimento");
		}
		
		if (associacaoExistente(colecaoEspecificacaoUnidadeCobranca, especificacaoUnidadeCobranca)) {
			
			throw new ActionServletException(
					"atencao.associacao.cobranca_situacao.ja_existente",
					new String[]{
							especificacaoUnidadeCobranca.getCobrancaSituacao().getDescricao(),
							especificacaoUnidadeCobranca.getSolicitacaoTipoEspecificacao().getDescricao()
							});
			
		} else {
			
			FiltroEspecificacaoUnidadeCobranca filtroEspecificacaoUnidadeCobranca = new FiltroEspecificacaoUnidadeCobranca();
			filtroEspecificacaoUnidadeCobranca.adicionarParametro(new ParametroSimples(
					FiltroEspecificacaoUnidadeCobranca.ID_COBRANCA_SITUACAO, idSituacaoCobranca));
			filtroEspecificacaoUnidadeCobranca.adicionarParametro(new ParametroSimples(
					FiltroEspecificacaoUnidadeCobranca.ID_SOLICITACAO_TIPO_ESPECIFICACAO, idTipoEspecificacao));
			filtroEspecificacaoUnidadeCobranca.adicionarParametro(new ParametroSimples(
					FiltroEspecificacaoUnidadeCobranca.ID_UNIDADE_ORGANIZACIONAL, idUnidade));
			filtroEspecificacaoUnidadeCobranca.adicionarCaminhoParaCarregamentoEntidade(
					FiltroEspecificacaoUnidadeCobranca.SOLICITACAO_TIPO_ESPECIFICACAO);
			filtroEspecificacaoUnidadeCobranca.adicionarCaminhoParaCarregamentoEntidade(
					FiltroEspecificacaoUnidadeCobranca.UNIDADE_ORGANIZACIONAL);
			filtroEspecificacaoUnidadeCobranca.adicionarCaminhoParaCarregamentoEntidade(
					FiltroEspecificacaoUnidadeCobranca.COBRANCA_SITUACAO);
			Collection colecao = fachada.pesquisar(filtroEspecificacaoUnidadeCobranca, EspecificacaoUnidadeCobranca.class.getName());
			
			if (colecao != null && !colecao.isEmpty()) {
				
				EspecificacaoUnidadeCobranca especificacaoUnidCobranca = (EspecificacaoUnidadeCobranca)Util.retonarObjetoDeColecao(colecao);
				especificacaoUnidCobranca.setUltimaAlteracao(null);
				colecaoEspecificacaoUnidadeCobranca.add(especificacaoUnidCobranca);
				
			} else {
				especificacaoUnidadeCobranca.setComp_id(comp);
				colecaoEspecificacaoUnidadeCobranca.add(especificacaoUnidadeCobranca);
			}
			
			sessao.setAttribute("colecaoEspecificacaoUnidadeCobranca",colecaoEspecificacaoUnidadeCobranca);
		
		}
		
		sessao.removeAttribute("colecaoTipoSolicitacao");
		sessao.removeAttribute("colecaoTipoEspecificacao");
		informarTramiteAssociacaoSituacaoCobrancaActionForm.setIdSituacaoCobranca("");
		informarTramiteAssociacaoSituacaoCobrancaActionForm.setIdTipoEspecificacao("");
		informarTramiteAssociacaoSituacaoCobrancaActionForm.setIdTipoSolicitacao("");
		informarTramiteAssociacaoSituacaoCobrancaActionForm.setIdUnidadeAtendimento("");
		informarTramiteAssociacaoSituacaoCobrancaActionForm.setDescricaoUnidadeAtendimento("");
		httpServletRequest.setAttribute("fecharPopup", "OK");
		sessao.setAttribute("fecharPopup", "OK");
		
		return retorno;
    }
    
    private boolean associacaoExistente(Collection colecaoEspecificacaoUnidadeCobranca, EspecificacaoUnidadeCobranca especificacaoUnidadeCobranca) {
    	boolean existe = false;
    	
    	if (especificacaoUnidadeCobranca.getCobrancaSituacao() != null && especificacaoUnidadeCobranca.getCobrancaSituacao().getId() != null
				&& especificacaoUnidadeCobranca.getSolicitacaoTipoEspecificacao() != null && especificacaoUnidadeCobranca.getSolicitacaoTipoEspecificacao().getId() != null
				&& colecaoEspecificacaoUnidadeCobranca != null && !colecaoEspecificacaoUnidadeCobranca.isEmpty()) {
    		Iterator iterator = colecaoEspecificacaoUnidadeCobranca.iterator();
    		
    		while (iterator.hasNext()) {
    			EspecificacaoUnidadeCobranca especificacaoUnidadeCobrancaCol = (EspecificacaoUnidadeCobranca) iterator.next();
    			
    			if (especificacaoUnidadeCobranca.getCobrancaSituacao().getId().equals(especificacaoUnidadeCobrancaCol.getCobrancaSituacao().getId())
    					&& especificacaoUnidadeCobranca.getSolicitacaoTipoEspecificacao().getId().equals(especificacaoUnidadeCobrancaCol.getSolicitacaoTipoEspecificacao().getId())) {
    				return true;
    			}
    			
    		}
    	}
    	
    	return existe;
    }

}
