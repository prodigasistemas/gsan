package gcom.gui.cobranca;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cobranca.FiltroMotivoNaoAceitacaoEncerramentoOS;
import gcom.cobranca.MotivoNaoAceitacaoEncerramentoOS;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1098] Informar Não Aceitação de Motivo de Encerramento Ordem de Serviço
 * @author Mariana Victor
 * @since 13/12/2010
 */
public class ExibirFiltrarComandosAcaoCobrancaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarComandosAcaoCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		ComandosAcaoCobrancaFiltrarActionForm comandosAcaoCobrancaFiltrarActionForm = (ComandosAcaoCobrancaFiltrarActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		String carregando = httpServletRequest.getParameter("carregando");
		
		if(carregando != null && !carregando.equals("")
				&& sessao.getAttribute("comandosAcaoCobrancaFiltrarActionForm") != null){
			
			ComandosAcaoCobrancaFiltrarActionForm comandosAcaoCobrancaFiltrarActionFormRecarregar = (ComandosAcaoCobrancaFiltrarActionForm)
						sessao.getAttribute("comandosAcaoCobrancaFiltrarActionForm");
			comandosAcaoCobrancaFiltrarActionForm.setPeriodoReferenciaContaInicial(comandosAcaoCobrancaFiltrarActionFormRecarregar.getPeriodoReferenciaContaInicial());
			comandosAcaoCobrancaFiltrarActionForm.setPeriodoReferenciaContaFinal(comandosAcaoCobrancaFiltrarActionFormRecarregar.getPeriodoReferenciaContaFinal());
			comandosAcaoCobrancaFiltrarActionForm.setIdAcaoCobranca(comandosAcaoCobrancaFiltrarActionFormRecarregar.getIdAcaoCobranca());
			comandosAcaoCobrancaFiltrarActionForm.setIdGrupoCobranca(comandosAcaoCobrancaFiltrarActionFormRecarregar.getIdGrupoCobranca());
		}
		
		// CARREGAR AS COBRANÇAS GRUPO
		if (sessao.getAttribute("colecaoGrupoCobranca") == null) {
			sessao.setAttribute("colecaoGrupoCobranca", fachada
					.obterColecaoCobrancaGrupo());
		}

		// CARREGAR AS COBRANÇAS ACAO
		if (sessao.getAttribute("colecaoAcaoCobranca") == null) {
			sessao.setAttribute("colecaoAcaoCobranca", fachada
					.obterColecaoCobrancaAcao());
		}
		
		// PESQUISAR A ORDEM DE SERVIÇO
		if (comandosAcaoCobrancaFiltrarActionForm.getIdOrdemServicoConsulta() != null
				&& !comandosAcaoCobrancaFiltrarActionForm.getIdOrdemServicoConsulta().equals("")) {
			OrdemServico ordemServico = fachada.pesquisarOrdemServico(new Integer(comandosAcaoCobrancaFiltrarActionForm.getIdOrdemServicoConsulta()));
			if (ordemServico != null) {
				comandosAcaoCobrancaFiltrarActionForm.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
				sessao.setAttribute("ordemServicoInexistente",Boolean.FALSE);
			} else {
				comandosAcaoCobrancaFiltrarActionForm.setNomeOrdemServico(ConstantesSistema.ORDEM_SERVICO_INEXISTENTE);
				sessao.setAttribute("ordemServicoInexistente",Boolean.TRUE);
			}
		}
		
		// PESQUISAR MOTIVOS DE NÃO ACEITAÇÃO
		FiltroMotivoNaoAceitacaoEncerramentoOS filtro = new FiltroMotivoNaoAceitacaoEncerramentoOS();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroMotivoNaoAceitacaoEncerramentoOS.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoMotivosNaoAceitacao = fachada.pesquisar(filtro, MotivoNaoAceitacaoEncerramentoOS.class.getName());

		if (colecaoMotivosNaoAceitacao != null && colecaoMotivosNaoAceitacao.size() > 0) {
			sessao.setAttribute("colecaoMotivosNaoAceitacao", colecaoMotivosNaoAceitacao);
		} else {
			throw new ActionServletException("atencao.motivo_nao_aceitacao_encerramento_os", null, "Motivo de Não Aceitação");
		}
		
		return retorno;
	}

}
