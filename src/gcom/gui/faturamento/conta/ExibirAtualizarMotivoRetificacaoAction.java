package gcom.gui.faturamento.conta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.ContaMotivoRetificacaoColuna;
import gcom.faturamento.conta.FiltroContaMotivoRetificacao;
import gcom.faturamento.conta.FiltroContaMotivoRetificacaoColuna;
import gcom.gui.GcomAction;
import gcom.seguranca.transacao.FiltroTabelaColuna;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarMotivoRetificacaoAction extends GcomAction {
	
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		ActionForward retorno = actionMapping.findForward("atualizarMotivoRetificacao");
		
		AtualizarMotivoRetificacaoActionForm form = (AtualizarMotivoRetificacaoActionForm) actionForm;
		
		ContaMotivoRetificacao contaMotivoRetificacao = null;
		List<ContaMotivoRetificacaoColuna> colecaoContaMotivoRetificacaoColuna = new ArrayList();
		List<ContaMotivoRetificacaoColuna> colecaoCamposRemover = new ArrayList();

		if (sessao.getAttribute("colecaoContaMotivoRetificacaoColuna") != null
				&& !sessao.getAttribute("colecaoContaMotivoRetificacaoColuna").equals("")){
			colecaoContaMotivoRetificacaoColuna = (List<ContaMotivoRetificacaoColuna>)
				sessao.getAttribute("colecaoContaMotivoRetificacaoColuna");
		}
		if (sessao.getAttribute("colecaoCamposRemover") != null
				&& !sessao.getAttribute("colecaoCamposRemover").equals("")){
			colecaoCamposRemover = (List<ContaMotivoRetificacaoColuna>)
				sessao.getAttribute("colecaoCamposRemover");
		}
		
		// Verifica se veio do filtrar ou do manter
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
		
		// Se o usuário selecionou um motivo de retificação a partir da tela de manter
		if (httpServletRequest.getParameter("idContaMotivoRetificacao") != null
				&& !httpServletRequest.getParameter("idContaMotivoRetificacao").equals("")) {
			Integer idContaMotivoRetificacao = new Integer(
					httpServletRequest.getParameter("idContaMotivoRetificacao"));
			
			FiltroContaMotivoRetificacao filtroContaMotivoRetificacao = new FiltroContaMotivoRetificacao();
			filtroContaMotivoRetificacao.adicionarParametro(
					new ParametroSimples(FiltroContaMotivoRetificacao.CODIGO, idContaMotivoRetificacao));
			
			Collection colecaoContaMotivoRetificacao = fachada.pesquisar(
					filtroContaMotivoRetificacao, ContaMotivoRetificacao.class.getName());
			if (colecaoContaMotivoRetificacao != null && !colecaoContaMotivoRetificacao.isEmpty()) {
				contaMotivoRetificacao = (ContaMotivoRetificacao)
						Util.retonarObjetoDeColecao(colecaoContaMotivoRetificacao);
			}

		// Se o usuário selecionou um motivo de retificação a partir da tela de filtrar
		} else if (httpServletRequest.getParameter("filtrar") != null
				&& httpServletRequest.getParameter("filtrar").equals("sim")
				&& sessao.getAttribute("objetoContaMotivoRetificacao") != null
				&& !sessao.getAttribute("objetoContaMotivoRetificacao").equals("")){
			contaMotivoRetificacao = (ContaMotivoRetificacao) sessao.getAttribute("objetoContaMotivoRetificacao");
		}
		
		if (contaMotivoRetificacao != null) {
			colecaoContaMotivoRetificacaoColuna = this.setarCamposFormulario(form, sessao, contaMotivoRetificacao);
			
			if (colecaoContaMotivoRetificacaoColuna != null && !colecaoContaMotivoRetificacaoColuna.isEmpty()) {
				sessao.setAttribute("colecaoContaMotivoRetificacaoColuna", colecaoContaMotivoRetificacaoColuna);
			} else {
				sessao.removeAttribute("colecaoContaMotivoRetificacaoColuna");
			}
			sessao.setAttribute("objetoContaMotivoRetificacao", contaMotivoRetificacao);
		} else if (sessao.getAttribute("objetoContaMotivoRetificacao") != null
				&& !sessao.getAttribute("objetoContaMotivoRetificacao").equals("")){
			contaMotivoRetificacao = (ContaMotivoRetificacao) sessao.getAttribute("objetoContaMotivoRetificacao");
		}

		// Desfazer
		if (httpServletRequest.getParameter("acao") != null
				&& httpServletRequest.getParameter("acao").equals("desfazer")) {
			Integer id = contaMotivoRetificacao.getId();
			sessao.removeAttribute("colecaoContaMotivoRetificacaoColuna");
			FiltroContaMotivoRetificacao filtroContaMotivoRetificacao = new FiltroContaMotivoRetificacao();
			filtroContaMotivoRetificacao.adicionarParametro(
					new ParametroSimples(FiltroContaMotivoRetificacao.CODIGO, id));
			
			Collection colecaoContaMotivoRetificacao = fachada.pesquisar(
					filtroContaMotivoRetificacao, ContaMotivoRetificacao.class.getName());
			if (colecaoContaMotivoRetificacao != null && !colecaoContaMotivoRetificacao.isEmpty()) {
				contaMotivoRetificacao = (ContaMotivoRetificacao)
						Util.retonarObjetoDeColecao(colecaoContaMotivoRetificacao);
			}
			
			colecaoContaMotivoRetificacaoColuna = this.setarCamposFormulario(form, sessao, contaMotivoRetificacao);
			
			if (colecaoContaMotivoRetificacaoColuna != null && !colecaoContaMotivoRetificacaoColuna.isEmpty()) {
				sessao.setAttribute("colecaoContaMotivoRetificacaoColuna", colecaoContaMotivoRetificacaoColuna);
			} else {
				sessao.removeAttribute("colecaoContaMotivoRetificacaoColuna");
			}
			sessao.setAttribute("objetoContaMotivoRetificacao", contaMotivoRetificacao);
		}
		
		// Pesquisar Campo
		if (Util.verificarNaoVazio(form.getIdColuna())) {
			TabelaColuna tabelaColuna = this.pesquisarCampo(form, sessao);
			if (tabelaColuna != null) {
				form.setIdColuna(tabelaColuna.getDescricaoColuna());
				form.setDescColuna(tabelaColuna.getDescricaoColuna());
				sessao.setAttribute("colunaEncontrada","");
			}
		}
		
		// Adicionar Campo
		if (httpServletRequest.getParameter("acao") != null
				&& httpServletRequest.getParameter("acao").equals("adicionar")
				&& !form.getIdColuna().equals("")) {
			TabelaColuna tabelaColuna = this.pesquisarCampo(form, sessao);
			
			if (tabelaColuna != null) {
				form.setIdColuna("");
				form.setDescColuna("");
				sessao.removeAttribute("colunaEncontrada");
				
				if (!this.adicionado(colecaoContaMotivoRetificacaoColuna, tabelaColuna)) {
					ContaMotivoRetificacaoColuna contaMotivoRetificacaoColuna = new ContaMotivoRetificacaoColuna();
					contaMotivoRetificacaoColuna.setTabelaColuna(tabelaColuna);
					contaMotivoRetificacaoColuna.setContaMotivoRetificacao(contaMotivoRetificacao);
					colecaoContaMotivoRetificacaoColuna.add(contaMotivoRetificacaoColuna);
					sessao.setAttribute("colecaoContaMotivoRetificacaoColuna", colecaoContaMotivoRetificacaoColuna);
				}
			}
		}
		
		// Remover Campo
		if (httpServletRequest.getParameter("acao") != null
				&& httpServletRequest.getParameter("acao").equals("remover")
				&& httpServletRequest.getParameter("id") != null
				&& !httpServletRequest.getParameter("id").equals("")) {

        	Integer indice = new Integer(httpServletRequest.getParameter("id"));
        	
        	if (colecaoContaMotivoRetificacaoColuna != null
        			&& !colecaoContaMotivoRetificacaoColuna.isEmpty()
        			&& colecaoContaMotivoRetificacaoColuna.size() >= indice) {
        		
        		if (colecaoContaMotivoRetificacaoColuna.get(indice-1).getUltimaAlteracao() != null){
        			colecaoCamposRemover.add(colecaoContaMotivoRetificacaoColuna.get(indice-1));
        			sessao.setAttribute("colecaoCamposRemover",colecaoCamposRemover);
        		}
        		colecaoContaMotivoRetificacaoColuna.remove(indice-1);
        		
        	}
		}
		
		sessao.removeAttribute("podeRetificarContaAction");
		
		return retorno;
	}
	
	private List<ContaMotivoRetificacaoColuna> setarCamposFormulario(AtualizarMotivoRetificacaoActionForm form, HttpSession sessao, ContaMotivoRetificacao contaMotivoRetificacao) {
		Fachada fachada = Fachada.getInstancia();
		List<ContaMotivoRetificacaoColuna> colecaoContaMotivoRetificacaoColuna = null;
		
		sessao.setAttribute("idContaMotivoRetificacao", contaMotivoRetificacao.getId());
		
		if (contaMotivoRetificacao.getDescricao() != null
				&& !contaMotivoRetificacao.getDescricao().equals("")){
			form.setDescricao(contaMotivoRetificacao.getDescricao());
		} else {
			form.setDescricao("");
		}
		if (contaMotivoRetificacao.getIndicadorCompetenciaConsumo()!= null){
			form.setIndicadorCompetenciaConsumo(contaMotivoRetificacao.getIndicadorCompetenciaConsumo().toString());
		} else {
			form.setIndicadorCompetenciaConsumo("");
		}
		if (contaMotivoRetificacao.getIndicadorUso()!= null){
			form.setIndicadorUso(contaMotivoRetificacao.getIndicadorUso().toString());
		} else {
			form.setIndicadorUso("");
		}
		if (contaMotivoRetificacao.getNumeroOcorrenciasNoAno() != null){
			form.setNumeroOcorrenciasNoAno(contaMotivoRetificacao.getNumeroOcorrenciasNoAno().toString());
		} else {
			form.setNumeroOcorrenciasNoAno("");
		}
		
		FiltroContaMotivoRetificacaoColuna filtroContaMotivoRetificacaoColuna = new FiltroContaMotivoRetificacaoColuna();
		filtroContaMotivoRetificacaoColuna.adicionarParametro(
				new ParametroSimples(FiltroContaMotivoRetificacaoColuna.CONTA_MOTIVO_RETIFICACAO_ID, contaMotivoRetificacao.getId()));
		filtroContaMotivoRetificacaoColuna.adicionarCaminhoParaCarregamentoEntidade(
				FiltroContaMotivoRetificacaoColuna.TABELA_COLUNA);
		colecaoContaMotivoRetificacaoColuna = (List<ContaMotivoRetificacaoColuna>) fachada
				.pesquisar(filtroContaMotivoRetificacaoColuna,ContaMotivoRetificacaoColuna.class.getName());
		
		return colecaoContaMotivoRetificacaoColuna;
		
	}

	private TabelaColuna pesquisarCampo(AtualizarMotivoRetificacaoActionForm form, HttpSession sessao) {
		FiltroTabelaColuna filtroTabelaColuna = new FiltroTabelaColuna();
		if (form.getIdColunaPesquisada() != null
				&& !form.getIdColunaPesquisada().equals("")) {
			filtroTabelaColuna.adicionarParametro(
					new ParametroSimples(FiltroTabelaColuna.ID, form.getIdColunaPesquisada()));
		} else {
			filtroTabelaColuna.adicionarParametro(
					new ParametroSimples(FiltroTabelaColuna.DESCRICAO_COLUNA, form.getIdColuna()));
		}
		
		Collection<TabelaColuna> colecaoTabelaColuna = 
			this.getFachada().pesquisar(filtroTabelaColuna, TabelaColuna.class.getName());
	
		// [FS0002] – Verificar existência do campo (coluna)
		if (Util.isVazioOrNulo(colecaoTabelaColuna)) {
			form.setIdColuna("");
			form.setDescColuna("Campo não Localizado");
			sessao.removeAttribute("colunaEncontrada");
		} else {
			TabelaColuna tabelaColuna = (TabelaColuna) Util.retonarObjetoDeColecao(colecaoTabelaColuna);
			
			//[FS0003] – Verificar se o campo (coluna) pode ser associado
			if (!tabelaColuna.getIndicadorPodeRetificarConta().toString().equals(ConstantesSistema.SIM.toString())) {
				form.setIdColuna("");
				form.setDescColuna("Campo não pode ser associado para retificação!");
				sessao.removeAttribute("colunaEncontrada");
			} else {
				return tabelaColuna;
			}
		}
		return null;
	}

	private boolean adicionado(List<ContaMotivoRetificacaoColuna> colecaoContaMotivoRetificacaoColuna, TabelaColuna tabelaColuna) {
		
		Iterator iterator = colecaoContaMotivoRetificacaoColuna.iterator();
		
		while(iterator.hasNext()) {
			TabelaColuna tabelaColunaAdicionada = ((ContaMotivoRetificacaoColuna) iterator.next()).getTabelaColuna();
			
			if (tabelaColunaAdicionada.getId().equals(tabelaColuna.getId())) {
				return true;
			}
		}
		
		return false;
	}
}
