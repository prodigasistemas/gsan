package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<Descrição da Classe>>
 * 
 * @author lms
 * @date 14/08/2006
 */
public class ExibirGerarOrdemServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {
		
		
		String forward = getRealForward(httpServletRequest.getParameter("forward"));
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		ActionForward retorno = actionMapping.findForward(forward);		
		GerarOrdemServicoActionForm form = (GerarOrdemServicoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();		

		if(httpServletRequest.getParameter("limparTela") != null){
			form.limparTodosCamposForm();
		}
		
		if(httpServletRequest.getParameter("forward") != null){
			form.setObservacao(null);
		}
		
		if(httpServletRequest.getParameter("caminhoRetornoGerarOs") != null){
			sessao.setAttribute("caminhoRetornoGerarOs",httpServletRequest.getParameter("caminhoRetornoGerarOs"));
		}
		if(httpServletRequest.getParameter("veioAcompanhamento") != null){
			sessao.setAttribute("veioAcompanhamento",httpServletRequest.getParameter("veioAcompanhamento"));
		}
		if(httpServletRequest.getParameter("veioAcompanhamentoRoteiro") != null){
			sessao.setAttribute("veioAcompanhamentoRoteiro",httpServletRequest.getParameter("veioAcompanhamentoRoteiro"));
		}

		//Registro de Atendimento
		Integer idRA = Integer.valueOf(httpServletRequest.getParameter("idRegistroAtendimento"));
		ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = fachada.obterDadosRegistroAtendimento(idRA);		
		RegistroAtendimento ra = fachada.validarRegistroAtendimento(idRA);
		//[SF0004] - Verificar unidade do usuário
		fachada.verificarUnidadeUsuario(ra,usuarioLogado);
		form.getOrdemServico().setRegistroAtendimento(ra);
		form.getOrdemServico().setImovel(registroAtendimentoHelper.getRegistroAtendimento().getImovel());

		//Serviço Tipo
		ServicoTipo servicoTipo = null;
		
		Integer idServicoTipo = Util.converterStringParaInteger(form.getIdServicoTipo());
		String descricaoServicoTipo = null;		
		String valorServicoOriginal = null; 
		Integer idServicoTipoPrioridadeOriginal = null;
		String descricaoServicoTipoPrioridadeOriginal = null;
		
		if (Util.validarNumeroMaiorQueZERO(idServicoTipo)) {
			servicoTipo = fachada.pesquisarSevicoTipo(idServicoTipo);
			
			fachada.validarServicoTipo(ra.getId(),idServicoTipo);
			
			if (servicoTipo != null) {
				descricaoServicoTipo = servicoTipo.getDescricao();
				if (servicoTipo.getValor() != null) {
					String valorFormatado = servicoTipo.getValor().toString().replace('.',','); 
					valorServicoOriginal = valorFormatado;
				}
				if (servicoTipo.getServicoTipoPrioridade() != null) {
					idServicoTipoPrioridadeOriginal = servicoTipo.getServicoTipoPrioridade().getId();
					descricaoServicoTipoPrioridadeOriginal = servicoTipo.getServicoTipoPrioridade().getDescricao();
				}
				httpServletRequest.setAttribute("idServicoTipoEncontrada","true");				
			}else{
				form.setIdServicoTipo("");
				descricaoServicoTipo = "Tipo de Serviço inexistente";
			}
			form.getOrdemServico().setServicoTipo(servicoTipo);
		} 

		form.setIdRegistroAtendimento( (String) httpServletRequest.getParameter("idRegistroAtendimento") );
		form.setDescricaoServicoTipo(descricaoServicoTipo);
		form.setValorServicoOriginal(valorServicoOriginal);
		form.setIdPrioridadeServicoOriginal(idServicoTipoPrioridadeOriginal + "");
		form.setDescricaoPrioridadeServicoOriginal(descricaoServicoTipoPrioridadeOriginal);
		
		//Ordem de Serviço Referência
		
		Integer idOrdemServicoReferencia = Util.converterStringParaInteger(form.getIdOrdemServicoReferencia());
		
		if (Util.validarNumeroMaiorQueZERO(idOrdemServicoReferencia)) {
			
			OrdemServico os = fachada.pesquisarOrdemServico(idOrdemServicoReferencia);
			
			if(os == null){
				form.setIdOrdemServicoReferencia(null);
				form.setDescricaoOrdemServicoReferencia("Ordem de Serviço inexistente");			
			}else{
				
				form.setIdOrdemServicoReferencia(os.getId().toString());
				form.setDescricaoOrdemServicoReferencia(os.getServicoTipo().getDescricao());			
				form.getOrdemServico().setOsReferencia(os);
				
				httpServletRequest.setAttribute("osReferenciaEncontrada","true");
			}
			
		}
		
		//Serviço Tipo Referência
		
		Integer idServicoTipoReferencia = Util.converterStringParaInteger(form.getIdServicoTipoReferencia());
		
		if (Util.validarNumeroMaiorQueZERO(idServicoTipoReferencia)) {
			
			ServicoTipo st = fachada.pesquisarSevicoTipo(idServicoTipoReferencia);
			
			if(st != null){
				form.setIdServicoTipoReferencia(st.getId().toString());
				form.setDescricaoServicoTipoReferencia(st.getDescricao());
				form.getOrdemServico().setServicoTipoReferencia(st);
				httpServletRequest.setAttribute("servicoTipoReferenciaEncontrada","true");
			}else{
				form.setIdServicoTipoReferencia("");
				form.setDescricaoServicoTipoReferencia("Tipo de Serviço referência inexistente");
			}
			
		}
		
		//Coleção de Serviços Tipo
		
		Collection colecaoServicosTipo = new ArrayList();
		for (Iterator iter = ra.getSolicitacaoTipoEspecificacao().getEspecificacaoServicoTipos().iterator(); iter.hasNext();) {
			EspecificacaoServicoTipo est = (EspecificacaoServicoTipo) iter.next();
			colecaoServicosTipo.add(est.getServicoTipo());
		}
		
		sessao.setAttribute("colecaoServicosTipo", colecaoServicosTipo);
		
		UnidadeOrganizacional unidadeAtualRA = fachada.obterUnidadeAtualRA(idRA);
		
		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade = new FiltroServicoTipoPrioridade();
		filtroServicoTipoPrioridade.setCampoOrderBy(FiltroCreditoTipo.DESCRICAO);
		filtroServicoTipoPrioridade.adicionarParametro(new ParametroSimples(FiltroCreditoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoServicoTipoPrioridade = fachada.pesquisar(filtroServicoTipoPrioridade, ServicoTipoPrioridade.class.getName());
		
		sessao.setAttribute("colecaoServicoTipoPrioridade", colecaoServicoTipoPrioridade);		
		
		httpServletRequest.setAttribute("servicoTipo", servicoTipo);
		if (servicoTipo != null && servicoTipo.getServicoTipoReferencia() != null ) {
			httpServletRequest.setAttribute("servicoTipoReferencia", servicoTipo.getServicoTipoReferencia());
		}
		
		httpServletRequest.setAttribute("registroAtendimento", ra);
		httpServletRequest.setAttribute("registroAtendimentoHelper", registroAtendimentoHelper);
		httpServletRequest.setAttribute("unidadeAtualRA", unidadeAtualRA);
		
		sessao.setAttribute("ordemServico", form.getOrdemServico());
		
		return retorno;
		
	}
	
	private String getRealForward(String upper) {
		String forward = "";
		if ("exibirGerarOrdemServico".toUpperCase().equals(upper.toUpperCase()) ) {
			forward = "exibirGerarOrdemServico";
		} else if ("exibirGerarOrdemServicoPopup".toUpperCase().equals(upper.toUpperCase())) {
			forward = "exibirGerarOrdemServicoPopup";
		} else {
			throw new IllegalArgumentException();
		}
		return forward;
	}

}
