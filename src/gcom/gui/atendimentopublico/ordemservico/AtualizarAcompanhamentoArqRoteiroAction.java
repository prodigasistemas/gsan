package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.FiltroAcompanhamentoArquivoRoteiro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Action para Atualizar o Arquivo Texto do Acompanhamento de Arquivos de Roteiro
 * 
 * @author Thúlio Araújo
 * @date 28/07/2011
 *  
 */
public class AtualizarAcompanhamentoArqRoteiroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = this.getSessao(httpServletRequest);     
		
		Integer situacaoNova = null;	
		String descricaoSituacaoNova = "";
		
		AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanhamentoArquivosRoteiroActionForm = 
				(AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		
		// Saber se vai liberar ou nao liberar
		String atualizar = (String) httpServletRequest.getParameter("atualizar");
		
		if(atualizar == null){
			atualizar = (String) sessao.getAttribute("atualizar");
		}
		
		if (atualizar.equals("2")) {
			situacaoNova = SituacaoTransmissaoLeitura.LIBERADO;
			descricaoSituacaoNova = "LIBERADO";
		} else if (atualizar.equals("3")) {
			situacaoNova = SituacaoTransmissaoLeitura.EM_CAMPO;
			descricaoSituacaoNova = "EM CAMPO";
		} else if (atualizar.equals("4")) {
			situacaoNova = SituacaoTransmissaoLeitura.TRANSMITIDO;
			descricaoSituacaoNova = "FINALIZADO";
		}
		
		if (acompanhamentoArquivosRoteiroActionForm.getIdsRegistros() != null) {
			Vector<Integer> vetorIdsRegistros = new Vector<Integer>();
			for (int i = 0; i < acompanhamentoArquivosRoteiroActionForm
					.getIdsRegistros().length; i++) {
				
				FiltroAcompanhamentoArquivoRoteiro filtroAcompanhamentoArquivoRoteiro = new FiltroAcompanhamentoArquivoRoteiro();
				filtroAcompanhamentoArquivoRoteiro.adicionarParametro(new ParametroSimples(FiltroAcompanhamentoArquivoRoteiro.ID, 
						acompanhamentoArquivosRoteiroActionForm.getIdsRegistros()[i]));
				
				Collection<?> colecao = fachada.pesquisar(filtroAcompanhamentoArquivoRoteiro, ArquivoTextoAcompanhamentoServico.class.getName());
				if (colecao != null && !colecao.isEmpty()){
					Iterator<?> colecaoIt = colecao.iterator();
					while (colecaoIt.hasNext()){
						ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico =  (ArquivoTextoAcompanhamentoServico) colecaoIt.next();
						if (descricaoSituacaoNova == "LIBERADO" && (arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.LIBERADO) || 
							arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.TRANSMITIDO))){
							throw new ActionServletException(
									"atencao.nao_possivel.liberar_arquivo", null,
									descricaoSituacaoNova);
						}else if (descricaoSituacaoNova == "EM CAMPO" && arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.EM_CAMPO)){
							throw new ActionServletException(
									"atencao.nao_possivel.em_campo_arquivo", null,
									descricaoSituacaoNova);
						}else if (descricaoSituacaoNova == "FINALIZADO" && (arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.LIBERADO) || 
							arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.TRANSMITIDO))){
							throw new ActionServletException(
									"atencao.nao_possivel.finalizar_arquivo", null,
									descricaoSituacaoNova);
						}
					}
				}
				
				if (situacaoNova != Util.converterStringParaInteger(acompanhamentoArquivosRoteiroActionForm.getIdsRegistros()[i])){
					vetorIdsRegistros.add(new Integer(acompanhamentoArquivosRoteiroActionForm
						.getIdsRegistros()[i]));
				}
			}
			
			fachada.atualizarListaArquivoTextoAcompArqRoteiro(vetorIdsRegistros, situacaoNova);

		} else {
			throw new ActionServletException(
					"atencao.nenhum_arquivo_mudar_situacao", null,
					descricaoSituacaoNova);
		}
			
		montarPaginaSucesso(httpServletRequest,
				"Situação do arquivo alterada para " + descricaoSituacaoNova.toLowerCase() + " com sucesso.",
				"Realizar um novo acompanhamento de arquivos de roteiro",
				"selecionarAcompanhamentoArquivosRoteiroAction.do");

		return retorno;
	}
}
