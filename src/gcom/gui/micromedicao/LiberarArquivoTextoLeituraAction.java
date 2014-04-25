package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ServicoTipoCelular;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Action para não Liberar o Arquivo Texto
 * 
 * @author Thiago Tenório , Thiago Nascimento e Yara T. Souza, Hugo Amorim
 * @date 19/12/2008 , 19/08/2010
 *  
 * 
 */
public class LiberarArquivoTextoLeituraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		Integer situacaoNova = null;	
		String descricaoSituacaoNova = "";

		HttpSession sessao = this.getSessao(httpServletRequest);     
		
		Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
		
		ConsultarArquivoTextoLeituraActionForm consultarArquivoTextoLeituraActionForm = (ConsultarArquivoTextoLeituraActionForm) actionForm;
		
		// Saber se vai liberar ou nao liberar
		String liberar = (String) httpServletRequest.getParameter("liberar");
		
		/**
		 * CRC 
		 * autor: Yara T. Souza
		 * data : 19/12/2008
		 * Acrescentado 2 novos botões para alterar a situação do arquivo texto.
		 */
		
		if(liberar == null){
			liberar = (String) sessao.getAttribute("liberar");
		}
		
		if (liberar.equals("0")) {
			situacaoNova = SituacaoTransmissaoLeitura.DISPONIVEL;
			descricaoSituacaoNova = "DISPONIVEL";
		} else if (liberar.equals("1")) {
			situacaoNova = SituacaoTransmissaoLeitura.LIBERADO;
			descricaoSituacaoNova = "LIBERADO";
		} else if (liberar.equals("2")) {
			situacaoNova = SituacaoTransmissaoLeitura.EM_CAMPO;
			descricaoSituacaoNova = "EM CAMPO";
		} else if (liberar.equals("3")) {
			situacaoNova = SituacaoTransmissaoLeitura.TRANSMITIDO;
			descricaoSituacaoNova = "FINALIZADO";
		} else if (liberar.equals("7")) {
			situacaoNova = SituacaoTransmissaoLeitura.FINALIZADO_USUARIO;
			descricaoSituacaoNova = "FINALIZADO PELO USUARIO";
		} else if ( liberar.equals( "9" ) ){
			situacaoNova = SituacaoTransmissaoLeitura.INFORMAR_MOTIVO_FINALIZACAO;
			descricaoSituacaoNova = "MOTIVO DE FINALIZAÇÃO INFORMADO";			
		}
		
		//Valida tamanho da descrição do motivo de finalição
		String motivoFinalizacao = null;
		if(consultarArquivoTextoLeituraActionForm.getMotivoFinalizacao()!=null
				&& !consultarArquivoTextoLeituraActionForm.getMotivoFinalizacao().equals("")){
			
			if(consultarArquivoTextoLeituraActionForm.getMotivoFinalizacao().length()>200){
				String[] msg = new String[2];
				msg[0]="Motivo Finalização";
				msg[1]="200";
				
				throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
			}
			
			motivoFinalizacao = consultarArquivoTextoLeituraActionForm.getMotivoFinalizacao();
		}
		
		if (consultarArquivoTextoLeituraActionForm.getIdsRegistros() != null) {
			Vector<Integer> v = new Vector<Integer>();
			for (int i = 0; i < consultarArquivoTextoLeituraActionForm
					.getIdsRegistros().length; i++) {
				v.add(new Integer(consultarArquivoTextoLeituraActionForm
						.getIdsRegistros()[i]));

			}
			
			Integer idServicoTipoCelular = ServicoTipoCelular.LEITURA;
			if(consultarArquivoTextoLeituraActionForm.getServicoTipoCelular() != null && 
					!consultarArquivoTextoLeituraActionForm.getServicoTipoCelular().equals("")){
				idServicoTipoCelular = Util.converterStringParaInteger(consultarArquivoTextoLeituraActionForm.getServicoTipoCelular());
			}
			
			String valorConfirmacao = httpServletRequest.getParameter("confirmado");
			
			// Alteracao solitcitada por Leo para deixar finalizar aquivos que sejam de impressao simultanea		
			//Permissao Especial FINALIZAR_ARQUIVO_TEXTO_DE_LEITURA

			boolean temPermissaoFinalizarArquivo = Fachada.getInstancia()
					.verificarPermissaoEspecial(
							PermissaoEspecial.FINALIZAR_ARQUIVO_TEXTO_DE_LEITURA,
							usuarioLogado);
			
			boolean realizouLeituras = Fachada.getInstancia().verificarLeiturasRealizadas(v,idServicoTipoCelular);
			
			if(temPermissaoFinalizarArquivo && (liberar.equals("3") || liberar.equals("7")) 
					&& idServicoTipoCelular == 2 && (valorConfirmacao == null) && !realizouLeituras) {
				
					sessao.setAttribute("liberar", liberar);
					
					// Se for para ir para a tela de confirmação
					httpServletRequest.setAttribute("caminhoActionConclusao",
	                        "/gsan/liberarArquivoLeituraAction.do");
	                httpServletRequest.setAttribute("cancelamento", "TRUE");
	                httpServletRequest.setAttribute("nomeBotao1", "Sim");
	                httpServletRequest.setAttribute("nomeBotao2", "Não");

	                return montarPaginaConfirmacao("atencao.confirmar.leituras.nao.recebidas",
	                        httpServletRequest, actionMapping);
			}  
			
			
				
				
			if(temPermissaoFinalizarArquivo && valorConfirmacao != null && valorConfirmacao.equals("cancelar")){
				return retorno = actionMapping.findForward("consultarArquivoTextoLeitura");
			}
				
			fachada.atualizarListaArquivoTexto(v, situacaoNova,
					idServicoTipoCelular,temPermissaoFinalizarArquivo,
					motivoFinalizacao);

		} else {
			
			throw new ActionServletException(
					"atencao.nenhum_arquivo_mudar_situacao", null,
					descricaoSituacaoNova);
			
		}
			
		montarPaginaSucesso(httpServletRequest,
				"Arquivo Texto para Leitura Alterado para " + descricaoSituacaoNova.toLowerCase() + " com sucesso.",
				"Realizar outra Manutenção de Arquivo Texto para Leitura",
				"consultarArquivoTextoLeituraAction.do");

		consultarArquivoTextoLeituraActionForm.setIdsRegistros(null);

		return retorno;

	}

}
