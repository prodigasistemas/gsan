package gcom.gui.cadastro.imovel;

import gcom.batch.Processo;
import gcom.cadastro.imovel.bean.GerarArquivoTextoAtualizacaoCadastralHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarArquivoTextoAtualizacaoCadastralDispositivoMovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
        GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form = (GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm) actionForm;
        
        Collection colecaoImovelFiltrado = (Collection)sessao.getAttribute("colecaoImovelFiltrado");
        
        GerarArquivoTextoAtualizacaoCadastralHelper helper = new GerarArquivoTextoAtualizacaoCadastralHelper();
        
        helper.setDescricao(form.getDescricaoArquivo());
        helper.setIdLeiturista(new Integer(form.getIdLeiturista()));
        if(form.getLocalidade()!= null && !form.getLocalidade().equals("")){
          helper.setIdLocalidade(new Integer(form.getLocalidade()));
          if(form.getSetorComercialCD()!= null && !form.getSetorComercialCD().equals("")){
	          helper.setSetorComercialCD(new Integer(form.getSetorComercialCD()));
	          if(form.getQuadraInicial() != null && !form.getQuadraInicial().equals("")){
	        	 helper.setNumeroQuadraInicial(new Integer(form.getQuadraInicial()));
	        	 helper.setNumeroQuadraFinal(new Integer(form.getQuadraFinal()));
	        	 // Alterado por Tiago Moreno - 23/07/2009 - CRC2374
	        	 if (form.getCodigoRota() != null && !form.getCodigoRota().equals("")){
	        		 helper.setRotaCD(new Integer(form.getCodigoRota()));
	        	 }
	          }else{
	        	  // Alterado por Tiago Moreno - 23/07/2009 - CRC2374
	        	 if (form.getCodigoRota() != null && !form.getCodigoRota().equals("")){
		        	 helper.setRotaCD(new Integer(form.getCodigoRota()));
		         } 
	          }
           }
        }
        helper.setSituacao(SituacaoTransmissaoLeitura.LIBERADO);
        helper.setQtdImovel(form.getTamanhoColecaoImovel());
        
        if ( colecaoImovelFiltrado != null && !colecaoImovelFiltrado.isEmpty() ) {
        	helper.setColecaoImovel(colecaoImovelFiltrado);
        }
        
		Map parametros = new HashMap();
		parametros.put("gerarArquivoTextoAtualizacaoCadastralHelper",helper);

		Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
		             		Processo.GERAR_ARQUIVO_TEXTO_ATU_CADASTRAL ,
		             		this.getUsuarioLogado(httpServletRequest));
		
		montarPaginaSucesso(httpServletRequest, "Geração do arquivo texto encaminhada para Batch.", "", "");

		return retorno;
	}
	

	
}
