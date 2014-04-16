package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroLocalidadeEspecificacaoUnidade;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidade;
import gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidadePK;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1091] Informar Associação de Localidade com Especificação e Unidade
 * 
 * @author Hugo Leonardo
 *
 * @date 29/11/2010
 */

public class AssociarLocalidadeComEspecificacaoUnidadePopUpAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("associarLocalidadeComEspecificacaoUnidadePopUp");
		
		HttpSession sessao = httpServletRequest.getSession(false);	
		
		ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm form = 
			(ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm) actionForm;
		
		// Seta Objeto Solicitacao Acesso
		LocalidadeEspecificacaoUnidade localidadeEspecificacaoUnidade = null;
		LocalidadeEspecificacaoUnidadePK localidadeEspecificacaoUnidadePK = null;
		
		Localidade localidade = null;
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = null;
		UnidadeOrganizacional unidadeOrganizacional = null;
		
		ArrayList<LocalidadeEspecificacaoUnidade> colecaoLocalidadeEspecificacaoUnidade = new ArrayList();
		
		if(!Util.verificarNaoVazio(form.getIdLocalidade())){
			throw new ActionServletException("atencao.localidade_nao_informada");
		}
		
		if(!Util.verificarNaoVazio(form.getIdTipoEspecificacao())){
			throw new ActionServletException("atencao.solicitacao_tipo_especificacao_nao_informada");
		}
		
		if(!Util.verificarNaoVazio(form.getIdUnidadeAtendimento())){
			throw new ActionServletException("atencao.unidade_atendimento_nao_informada");
		}
		
		// [FS0005] - Verificar existência da associação na tabela
		FiltroLocalidadeEspecificacaoUnidade filtroLocalidadeEspecificacaoUnidadePesquisada = 
			new FiltroLocalidadeEspecificacaoUnidade();
		
		// Localidade
		filtroLocalidadeEspecificacaoUnidadePesquisada.adicionarParametro(new ParametroSimples(
				FiltroLocalidadeEspecificacaoUnidade.LOCALIDADE_ID, form.getIdLocalidade()));
		
		// Espeficicação
		filtroLocalidadeEspecificacaoUnidadePesquisada.adicionarParametro(new ParametroSimples(
				FiltroLocalidadeEspecificacaoUnidade.SOLICITACAO_TIPO_ESPECIFICACAO_ID, form.getIdTipoEspecificacao()));
		
		filtroLocalidadeEspecificacaoUnidadePesquisada.adicionarCaminhoParaCarregamentoEntidade(
				FiltroLocalidadeEspecificacaoUnidade.LOCALIDADE);
		
		filtroLocalidadeEspecificacaoUnidadePesquisada.adicionarCaminhoParaCarregamentoEntidade(
				FiltroLocalidadeEspecificacaoUnidade.SOLICITACAO_TIPO_ESPECIFICACAO);
		
		filtroLocalidadeEspecificacaoUnidadePesquisada.adicionarCaminhoParaCarregamentoEntidade(
				FiltroLocalidadeEspecificacaoUnidade.UNIDADE_ORGANIZACIONAL);
		
		// Unidade Organizacional
		//filtroLocalidadeEspecificacaoUnidadePesquisada.adicionarParametro(new ParametroSimples(
		//		FiltroLocalidadeEspecificacaoUnidade.UNIDADE_ORGANIZACIONAL_ID, form.getIdUnidadeAtendimento()));
		
		Collection colecaoLocalidadeEspecificacaoUnidadePesquisada = this.getFachada().pesquisar(filtroLocalidadeEspecificacaoUnidadePesquisada, 
				LocalidadeEspecificacaoUnidade.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoLocalidadeEspecificacaoUnidadePesquisada)){
			
			Iterator iterator = colecaoLocalidadeEspecificacaoUnidadePesquisada.iterator();
			
			while (iterator.hasNext()) {
				localidadeEspecificacaoUnidade = (LocalidadeEspecificacaoUnidade) iterator.next();
				
				if(localidadeEspecificacaoUnidade.getComp_id().getLocalidade().getId().toString().equals(form.getIdLocalidade()) && 
						localidadeEspecificacaoUnidade.getComp_id().getSolicitacaoTipoEspecificacao().getId().toString().equals(form.getIdTipoEspecificacao())){
					
					String[] srt = new String[3];
					srt[0] = localidadeEspecificacaoUnidade.getComp_id().getLocalidade().getDescricao();
					srt[1] = localidadeEspecificacaoUnidade.getComp_id().getSolicitacaoTipoEspecificacao().getDescricao();
					srt[2] = localidadeEspecificacaoUnidade.getComp_id().getUnidadeOrganizacional().getDescricao();
					
					form.setIdTipoEspecificacao("");
					form.setIdTipoSolicitacao("");
					form.setIdUnidadeAtendimento("");
					form.setNomeUnidadeAtendimento("");
					
					throw new ActionServletException("atencao.localidade_especificacao_unidade_ja_informado", srt);
				}
			}
			
		}
	
		if(sessao.getAttribute("colecaoLocalidadeEspecificacaoUnidade") == null){
			
			FiltroLocalidadeEspecificacaoUnidade filtroLocalidadeEspecificacaoUnidade = new FiltroLocalidadeEspecificacaoUnidade();
			filtroLocalidadeEspecificacaoUnidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidadeEspecificacaoUnidade.LOCALIDADE_ID, form.getIdLocalidade()));
			
			filtroLocalidadeEspecificacaoUnidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidadeEspecificacaoUnidade.SOLICITACAO_TIPO_ESPECIFICACAO_ID, form.getIdTipoEspecificacao()));
			
			filtroLocalidadeEspecificacaoUnidade.adicionarCaminhoParaCarregamentoEntidade(
					FiltroLocalidadeEspecificacaoUnidade.SOLICITACAO_TIPO_ESPECIFICACAO);
			filtroLocalidadeEspecificacaoUnidade.adicionarCaminhoParaCarregamentoEntidade(
					FiltroLocalidadeEspecificacaoUnidade.UNIDADE_ORGANIZACIONAL);
			
			filtroLocalidadeEspecificacaoUnidade.setCampoOrderBy(
					FiltroLocalidadeEspecificacaoUnidade.SOLICITACAO_TIPO_ESPECIFICACAO_ID);
			filtroLocalidadeEspecificacaoUnidade.setCampoOrderBy(
					FiltroLocalidadeEspecificacaoUnidade.UNIDADE_ORGANIZACIONAL_ID);
			
			colecaoLocalidadeEspecificacaoUnidade = (ArrayList<LocalidadeEspecificacaoUnidade>) this.getFachada().pesquisar(filtroLocalidadeEspecificacaoUnidade, 
					LocalidadeEspecificacaoUnidade.class.getName());
		}else{
			
			colecaoLocalidadeEspecificacaoUnidade = (ArrayList<LocalidadeEspecificacaoUnidade>) sessao.getAttribute("colecaoLocalidadeEspecificacaoUnidade");
		}
		
		// Localidade
		if(sessao.getAttribute("localidadePesquisada") != null){
			localidade  = (Localidade) sessao.getAttribute("localidadePesquisada");
		}else{
		
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, form.getIdLocalidade()));
			
			Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, 
					Localidade.class.getName());
			
			localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
		}
		
		// Solicitacao Tipo Especificacao
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID, form.getIdTipoEspecificacao()));
		
		Collection colecaoSolicitacaoTipoEspecificacao = this.getFachada().pesquisar(filtroSolicitacaoTipoEspecificacao, 
				SolicitacaoTipoEspecificacao.class.getName());
		
		solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);
		
		// Unidade Organizacional
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.ID, form.getIdUnidadeAtendimento()));
		
		Collection colecaoUnidadeOrganizacional = this.getFachada().pesquisar(filtroUnidadeOrganizacional, 
				UnidadeOrganizacional.class.getName());
		
		unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
		
		localidadeEspecificacaoUnidadePK = new LocalidadeEspecificacaoUnidadePK(
				localidade, solicitacaoTipoEspecificacao, unidadeOrganizacional);
		
		localidadeEspecificacaoUnidade = new LocalidadeEspecificacaoUnidade(
				localidadeEspecificacaoUnidadePK, new Date());
		
		// [FS0006] - Verificar existência da associação no grid.
		if(colecaoLocalidadeEspecificacaoUnidade.contains(localidadeEspecificacaoUnidade)){
			
			String[] srt = new String[3];
			srt[0] = localidadeEspecificacaoUnidade.getComp_id().getLocalidade().getDescricao();
			srt[1] = localidadeEspecificacaoUnidade.getComp_id().getSolicitacaoTipoEspecificacao().getDescricao();
			srt[2] = localidadeEspecificacaoUnidade.getComp_id().getUnidadeOrganizacional().getDescricao();
			
			form.setIdTipoEspecificacao("");
			form.setIdTipoSolicitacao("");
			form.setIdUnidadeAtendimento("");
			form.setNomeUnidadeAtendimento("");
			
			throw new ActionServletException("atencao.localidade_especificacao_unidade_ja_informado", srt);
		}

		colecaoLocalidadeEspecificacaoUnidade.add(localidadeEspecificacaoUnidade);
		
		// o sistema classifica a lista de LocalidadeEspecificacaoUnidade
		Collections.sort((List) colecaoLocalidadeEspecificacaoUnidade,
				new Comparator() {
					public int compare(Object a, Object b) {
						String codigo1 = ((LocalidadeEspecificacaoUnidade) a)
								.getComp_id().getSolicitacaoTipoEspecificacao().getDescricao();
						String codigo2 = ((LocalidadeEspecificacaoUnidade) b)
							.getComp_id().getUnidadeOrganizacional().getDescricao();
						if (codigo1 == null || codigo1.equals("")) {
							return -1;
						} else {
							return codigo1.compareTo(codigo2);
						}
					}
				});

		sessao.setAttribute("colecaoLocalidadeEspecificacaoUnidade", 
				colecaoLocalidadeEspecificacaoUnidade);
		
		httpServletRequest.setAttribute("fecharPopup", "OK");
		sessao.setAttribute("fecharPopup", "OK");
		
		form.setIdTipoEspecificacao("");
		form.setIdTipoSolicitacao("");
		form.setIdUnidadeAtendimento("");
		form.setNomeUnidadeAtendimento("");

		return retorno;
	
	}

}
