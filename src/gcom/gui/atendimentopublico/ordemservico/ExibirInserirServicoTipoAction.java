package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoSubgrupo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.util.ConstantesSistema;
import gcom.util.FachadaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<Descri��o da Classe>>
 * 
 * @author lms
 * @date 26/07/2006
 */
public class ExibirInserirServicoTipoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de um servi�o tipo.
	 * 
	 * [UC0410] Inserir Servi�o Tipo
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
		
		ActionForward retorno = actionMapping.findForward("inserirServicoTipo");		
		InserirServicoTipoActionForm form = (InserirServicoTipoActionForm) actionForm;		
		Fachada fachada = Fachada.getInstancia();		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if ("reset".equalsIgnoreCase(httpServletRequest.getParameter("action"))) {
			form.reset();
			sessao.removeAttribute("motivosEncerramentoEscolhidos");
		}

		
		if(sessao.getAttribute("motivosEncerramentoEscolhidos") != null){
			Collection colecaoAtendimentoMotivosEncerramento = (Collection)sessao.getAttribute("motivosEncerramentoEscolhidos");
			form.setServicoTipoMotivoEncerramento(colecaoAtendimentoMotivosEncerramento);
		}
        //Constr�i filtro para pesquisa dos servi�os tipo subgrupo
		FiltroServicoTipoSubgrupo filtroServicoTipoSubgrupo = new FiltroServicoTipoSubgrupo();
		filtroServicoTipoSubgrupo.setCampoOrderBy(FiltroServicoTipoReferencia.DESCRICAO);
		filtroServicoTipoSubgrupo.adicionarParametro(new ParametroSimples(FiltroServicoTipoSubgrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
		
		Collection colecaoSubgrupo = fachada.pesquisar(filtroServicoTipoSubgrupo, ServicoTipoSubgrupo.class.getName());
		
		sessao.setAttribute("colecaoSubgrupo", colecaoSubgrupo);
		
        //Constr�i filtro para pesquisa dos creditos tipo
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		filtroCreditoTipo.setCampoOrderBy(FiltroCreditoTipo.DESCRICAO);
		filtroCreditoTipo.adicionarParametro(new ParametroSimples(FiltroCreditoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
		
		Collection colecaoTipoCredito = fachada.pesquisar(filtroCreditoTipo, CreditoTipo.class.getName());
		
		sessao.setAttribute("colecaoTipoCredito", colecaoTipoCredito);
		
        //Constr�i filtro para pesquisa das prioridades servi�o
		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();
		filtroServicoTipoPrioridade.setCampoOrderBy(FiltroCreditoTipo.DESCRICAO);
		filtroServicoTipoPrioridade.adicionarParametro(new ParametroSimples(FiltroCreditoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO.toString()));
		
		Collection colecaoPrioridadeServico = fachada.pesquisar(filtroServicoTipoPrioridade, ServicoTipoPrioridade.class.getName());
		
		sessao.setAttribute("colecaoPrioridadeServico", colecaoPrioridadeServico);
		
		ServicoTipo servicoTipo = form.getServicoTipo();
		
		//Tipo de D�bito		
		Integer idDebitoTipo = Util.converterStringParaInteger(form.getIdTipoDebito());
		
		if (Util.validarNumeroMaiorQueZERO(idDebitoTipo)) {
			try {
				DebitoTipo debitoTipo = fachada.pesquisarDebitoTipo(idDebitoTipo);
				servicoTipo.setDebitoTipo(debitoTipo);
				form.setIdTipoDebito(debitoTipo.getId().toString());
				form.setDescricaoTipoDebito(debitoTipo.getDescricao());
				form.setTipoCredito(null);
			} catch (FachadaException e) {
				servicoTipo.setDebitoTipo(null);
				form.setDescricaoTipoDebito("Tipo de D�bito Inexistente");
			}
		} else {
			servicoTipo.setDebitoTipo(null);
		}
		
		if(form.getTipoCredito() != null && Integer.parseInt(form.getTipoCredito()) != ConstantesSistema.NUMERO_NAO_INFORMADO){
			httpServletRequest.setAttribute("desabilitaCredito", "desabilitaCredito");			
		}
		//Perfil do Servi�o
		
		Integer idServicoPerfilTipo = Util.converterStringParaInteger(form.getIdPerfilServico());
		
		if (Util.validarNumeroMaiorQueZERO(idServicoPerfilTipo)) {
			try {
				ServicoPerfilTipo servicoPerfilTipo = fachada.pesquisarServicoPerfilTipo(idServicoPerfilTipo);
				servicoTipo.setServicoPerfilTipo(servicoPerfilTipo);
				form.setIdPerfilServico(servicoPerfilTipo.getId().toString());
				form.setDescricaoPerfilServico(servicoPerfilTipo.getDescricao());
			} catch (FachadaException e) {
				servicoTipo.setServicoPerfilTipo(null);
				form.setDescricaoPerfilServico("Tipo do Perfil Inexistente");
			}
		}
		
		
		// Operacao
		
		Integer idOperacao = Util.converterStringParaInteger(form.getIdOperacao());
		
		if (Util.validarNumeroMaiorQueZERO(idOperacao)) {
			try {
				FiltroOperacao filtroOperacao = new FiltroOperacao();
	        	filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, idOperacao));
	        	
	        	Collection colecaoOperacao = fachada.pesquisar(filtroOperacao, Operacao.class.getName());
	        	
	        	if(colecaoOperacao.isEmpty()) {
	        		form.setDescricaoOperacao("Opera��o Inexistente");
	        		sessao.setAttribute("idOperacao", "");
	        	} else {
	        		Operacao operacao = (Operacao) colecaoOperacao.iterator().next();
	        		form.setIdOperacao(operacao.getId().toString());
	        		form.setDescricaoOperacao(operacao.getDescricao());
	        		sessao.setAttribute("idOperacao", operacao.getId());
	        	}
			} catch (FachadaException e) {
				sessao.setAttribute("idOperacao", "");
				form.setDescricaoOperacao("Operacao Inexistente");
			}
		}
		
		
		//Tipo do Servi�o Refer�ncia
		
		Integer idServicoTipoReferencia = Util.converterStringParaInteger(form.getIdTipoServicoReferencia());
		
		if (Util.validarNumeroMaiorQueZERO(idServicoTipoReferencia)) {
			try {
				ServicoTipoReferencia servicoTipoReferencia = fachada.pesquisarServicoTipoReferencia(idServicoTipoReferencia);
				servicoTipo.setServicoTipoReferencia(servicoTipoReferencia);
				form.setIdTipoServicoReferencia(servicoTipoReferencia.getId().toString());
				form.setDescricaoTipoServicoReferencia(servicoTipoReferencia.getDescricao());
			} catch (FachadaException e) {
				servicoTipo.setServicoTipoReferencia(null);
				form.setDescricaoTipoServicoReferencia("Tipo de Servi�o de Refer�ncia Inexistente");
			}
		}
		
		if("removeRowTableServicoTipoReferencia".equals(form.getMethod())){
			sessao.removeAttribute("servicoTipoReferencia");
			form.setMethod("");
		}
		
		if ("addServicoTipoAtividade".equals(form.getMethod())) {
			form.addServicoTipoAtividade();
			form.setMethod(null);
		} 
		
		if ("removeServicoTipoAtividade".equals(form.getMethod())) {
			form.removeServicoTipoAtividade();
		}
		
		if ("addServicoTipoMaterial".equals(form.getMethod())) {
			form.addServicoTipoMaterial();
		} 
		
		if ("removeServicoTipoMaterial".equals(form.getMethod())) {
			form.removeServicoTipoMaterial();
		}
		
		if ("removeAllServicoTipoAtividade".equals(form.getMethod())) {
			form.removeAllServicoTipoAtividade();
		}
		
		if ("removeServicoTipoMotivoEncerramento".equals(form.getMethod())) {
			form.removeServicoTipoMotivoEncerramento();
		}
		
		//caso n�o tenha valor seta para o valor de n�o
		if(form.getIndicadorPermiteAlterarValor() == null || form.getIndicadorPermiteAlterarValor().equals("")){
			form.setIndicadorPermiteAlterarValor("2");
		}
		if(form.getIndicativoObrigatoriedadeAtividade() == null || form.getIndicativoObrigatoriedadeAtividade().equals("")){
			form.setIndicativoObrigatoriedadeAtividade("2");
			form.setIndicativoObrigatoriedadeAtividadeValor("2");
		} else {

			form.setIndicativoObrigatoriedadeAtividade(form.getIndicativoObrigatoriedadeAtividadeValor());
			
		} 
		
		
		form.setIndicadorProgramacaoAutomatica(form.getIndicadorProgramacaoAutomaticaValor());
			
		
		if(form.getIndicadorEncAutomaticoRAQndEncOS() == null || form.getIndicadorEncAutomaticoRAQndEncOS().equals("")){
			form.setIndicadorEncAutomaticoRAQndEncOS("2");
		}
		
		httpServletRequest.setAttribute("servicoTipo", servicoTipo);
		sessao.setAttribute("colecaoServicoTipoAtividade", form.getServicoTipoAtividades());
		sessao.setAttribute("colecaoServicoTipoMaterial", form.getServicoTipoMateriais());	
		sessao.setAttribute("motivosEncerramentoEscolhidos", form.getServicoTipoMotivoEncerramento());
		
		return retorno;
	}

}
