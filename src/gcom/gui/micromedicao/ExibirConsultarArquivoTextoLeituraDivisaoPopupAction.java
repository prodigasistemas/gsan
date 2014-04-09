package gcom.gui.micromedicao;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.FiltroArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
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
 * UC1027 - Exibir Consultar Arquivo Texto Leitura Divisão.
 *  
 * @author Hugo Leonardo
 * @created 04/06/2010
 * 
 */
public class ExibirConsultarArquivoTextoLeituraDivisaoPopupAction extends GcomAction {

	/**
	 * 
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarArquivoTextoLeituraDivisaoPopupAction");

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarArquivoTextoLeituraDivisaoPopupActionForm form = 
			(ConsultarArquivoTextoLeituraDivisaoPopupActionForm) actionForm;

		FiltroArquivoTextoRoteiroEmpresaDivisao filtroArquivoTextoRoteiroEmpresaDivisao = null;
			
		String idArqRoteiroEmpresa = null;
		if (httpServletRequest.getParameter("idArquivoTextoRoteiroEmpresa") != null
				|| sessao.getAttribute("idArquivoTextoRoteiroEmpresaDivisao") != null) {
			
			if(httpServletRequest.getParameter("idArquivoTextoRoteiroEmpresa") != null){
				
				idArqRoteiroEmpresa = (String) httpServletRequest.getParameter("idArquivoTextoRoteiroEmpresa");
				sessao.setAttribute("idArquivoTextoRoteiroEmpresaDivisao", idArqRoteiroEmpresa);
			}else{
				
				idArqRoteiroEmpresa = (String) sessao.getAttribute("idArquivoTextoRoteiroEmpresaDivisao");
			}
			
			
			
			// pesquisa Arquivo Texto Roteiro Empresa Divisão
			filtroArquivoTextoRoteiroEmpresaDivisao = new FiltroArquivoTextoRoteiroEmpresaDivisao();
			
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarParametro(new ParametroSimples(
					FiltroArquivoTextoRoteiroEmpresaDivisao.ARQUIVO_TEXTO_ROTEIRO_EMPRESA_ID,
					idArqRoteiroEmpresa));
			
			String[] orderby = new String[] {
					FiltroArquivoTextoRoteiroEmpresaDivisao.NUMERO_SEQUENCIA_LEITURA, 
					FiltroArquivoTextoRoteiroEmpresaDivisao.SITUACAO_TRANS_LEITURA};
			
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
					FiltroArquivoTextoRoteiroEmpresaDivisao.ARQUIVO_TEXTO_ROTEIRO_EMPRESA);
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
					FiltroArquivoTextoRoteiroEmpresa.SITUACAO_TRANS_LEITURA);
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
					"arquivoTextoRoteiroEmpresa.localidade");
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
					"arquivoTextoRoteiroEmpresa.rota");
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
			"leiturista");
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
			"leiturista.cliente");
			filtroArquivoTextoRoteiroEmpresaDivisao.adicionarCaminhoParaCarregamentoEntidade(
			"leiturista.funcionario");
			
			filtroArquivoTextoRoteiroEmpresaDivisao.setCampoOrderBy(orderby);
			
			Collection colecaoArquivoTextoRoteiroEmpresaDivisao = Fachada.getInstancia()
				.pesquisar(filtroArquivoTextoRoteiroEmpresaDivisao, ArquivoTextoRoteiroEmpresaDivisao.class.getName());
			
			if(colecaoArquivoTextoRoteiroEmpresaDivisao != null && !colecaoArquivoTextoRoteiroEmpresaDivisao.isEmpty()){
				
				ArquivoTextoRoteiroEmpresaDivisao arquivoTextoRoteiroEmpresaDivisao = 
					(ArquivoTextoRoteiroEmpresaDivisao) Util.retonarObjetoDeColecao(colecaoArquivoTextoRoteiroEmpresaDivisao);
				//Caso a situação de transmissão leitura do arquivo texto roteiro empresa não esteja com a situação de "Liberado"
				//então exibe uma mensagem para liberar o arquivo.
				if(arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa() != null && arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa() != null &&
						!arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa().getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.EM_CAMPO)){
					throw new ActionServletException("atencao.arquivo_texto_roteiro_empresa_nao_em_campo");
				}
				
				String idLocalidade = arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa().getLocalidade().getId().toString();
				String nomeLocalidade = arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa().getLocalidade().getDescricao();
				String idSetorComercial = arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa().getCodigoSetorComercial1().toString();
				String codigoRota = arquivoTextoRoteiroEmpresaDivisao.getArquivoTextoRoteiroEmpresa().getRota().getCodigo().toString();
				
				// localidade
				form.setLocalidadeID(""+ idLocalidade);
				form.setLocalidadeNome(""+ nomeLocalidade);

				// setor comercial
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID, idSetorComercial));

				Collection<SetorComercial> setorComercialPesquisada = Fachada.getInstancia().pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				if (setorComercialPesquisada != null && !setorComercialPesquisada.isEmpty()) {
					SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(setorComercialPesquisada);
					
					form.setCodigoSetorComercial(""+ setorComercial.getCodigo());
				}
				
				// rota
				form.setCodigoRota(""+ codigoRota);
				
				sessao.setAttribute("colecaoArquivoTextoRoteiroEmpresaDivisao",
						colecaoArquivoTextoRoteiroEmpresaDivisao);
				
			}else{
				throw new ActionServletException("atencao.arquivo_texto_roteiro_empresa_divisao");
			}
		}

		sessao.setAttribute("achou","1");
		form.setIdsRegistros(new String[0]);

		return retorno;
	}

}
