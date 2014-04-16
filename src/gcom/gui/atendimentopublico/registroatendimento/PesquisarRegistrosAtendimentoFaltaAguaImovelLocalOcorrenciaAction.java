package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ExibirRAFaltaAguaImovelHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAFaltaAguaPendenteHelper;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela com os registros
 * de atendimento de falta de água no imóvel da mesma área do bairro (Aba nº 02 -
 * Dados do local de ocorrência)
 * 
 * @author Sávio Luiz
 * @date 17/07/2006
 */
public class PesquisarRegistrosAtendimentoFaltaAguaImovelLocalOcorrenciaAction
		extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("pesquisarRAsFaltaAguaOcorrencia");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//PesquisarRegistrosAtendimentoFaltaAguaImovelLocalOcorrenciaActionForm form = 
		//(PesquisarRegistrosAtendimentoFaltaAguaImovelLocalOcorrenciaActionForm) actionForm;
		
		Integer idRegistroAtendimento = Util.converterStringParaInteger(httpServletRequest.getParameter("idRA"));
		Integer idBairroArea = Util.converterStringParaInteger(httpServletRequest.getParameter("idBairroArea"));
		Integer idEspecificacao = Util.converterStringParaInteger(httpServletRequest.getParameter("idEspecificacao"));

		RAFaltaAguaPendenteHelper rAFaltaAguaPendenteHelper = fachada
		.carregarObjetoRAFaltaAguaPendente(idRegistroAtendimento,
		idBairroArea, idEspecificacao);
		
		//Caso a chamada tenha sido feita pelo [UC0366] - Inserir Registro de Atendimento
		if (idRegistroAtendimento == null){
			
			//SolicitacaoTipo e SolicitacaoTipoEspecificacao
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new 
			FiltroSolicitacaoTipoEspecificacao();
			
			filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipo");
			
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
			FiltroSolicitacaoTipoEspecificacao.ID, idEspecificacao));
			
			Collection colecaoEspecificacao = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao,
			SolicitacaoTipoEspecificacao.class.getName());
			
			SolicitacaoTipoEspecificacao especificacao = (SolicitacaoTipoEspecificacao) 
			Util.retonarObjetoDeColecao(colecaoEspecificacao);
			
			rAFaltaAguaPendenteHelper.setIdSolicitacaoTipo(especificacao.getSolicitacaoTipo().getId());
			rAFaltaAguaPendenteHelper.setDescricaoSolicitacaoTipo(especificacao.getSolicitacaoTipo().getDescricao());
			rAFaltaAguaPendenteHelper.setIdSolicitacaoTipoEspecificacao(especificacao.getId());
			rAFaltaAguaPendenteHelper.setDescricaoSolicitacaoTipoEspecificacao(especificacao.getDescricao());
			
			//Bairro e BairroArea
			FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
			
			filtroBairroArea.adicionarCaminhoParaCarregamentoEntidade("bairro");
			
			filtroBairroArea.adicionarParametro(new ParametroSimples(
			FiltroBairroArea.ID, idBairroArea));
			
			Collection colecaoBairroArea = fachada.pesquisar(filtroBairroArea,
			BairroArea.class.getName());
			
			BairroArea bairroArea = (BairroArea) Util.retonarObjetoDeColecao(colecaoBairroArea);
		
			rAFaltaAguaPendenteHelper.setCodigoBairro(bairroArea.getBairro().getCodigo());
			rAFaltaAguaPendenteHelper.setNomeBairro(bairroArea.getBairro().getNome());
			rAFaltaAguaPendenteHelper.setIdBairroArea(bairroArea.getId());
			rAFaltaAguaPendenteHelper.setNomeBairroArea(bairroArea.getNome());
		}
		
		
		if (rAFaltaAguaPendenteHelper.getColecaoExibirRAFaltaAguaHelper() != null
				&& !rAFaltaAguaPendenteHelper
						.getColecaoExibirRAFaltaAguaHelper().isEmpty()) {
			Iterator iter = rAFaltaAguaPendenteHelper
					.getColecaoExibirRAFaltaAguaHelper().iterator();
			while (iter.hasNext()) {
				ExibirRAFaltaAguaImovelHelper exibirRAFaltaAguaImovelHelper = (ExibirRAFaltaAguaImovelHelper) iter
						.next();
				String enderecoOcorrencia = fachada
						.obterEnderecoOcorrenciaRA(exibirRAFaltaAguaImovelHelper
								.getIdRA());
				exibirRAFaltaAguaImovelHelper
						.setEnderecoOcorrencia(enderecoOcorrencia);
			}
		}
		
		

		sessao.setAttribute("rAFaltaAguaPendenteHelper",
				rAFaltaAguaPendenteHelper);

		return retorno;
	}

}
