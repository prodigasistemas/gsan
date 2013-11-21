package gcom.gui.cadastro.imovel;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * Pesquisar Imóveis de acordo com o logradouro informado pelo usuário.
 * 
 * @author Davi Menezes
 * @date 03/08/2011
 */

public class PesquisarImovelLogradouroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		ActionForward retorno = actionMapping.findForward("transferirImovel");
		
		HttpSession session = httpServletRequest.getSession(false);
		
		TransferirImovelLogradouroActionForm form = (TransferirImovelLogradouroActionForm) actionForm;
		
		Collection imoveis = null;
		
		String idLogradouro = (String) form.getLogradouroImovelOrigemFiltro();
		
		if(form.getLogradouroImovelDestinoFiltro() == null || form.getLogradouroImovelDestinoFiltro().equals("")){
			session.removeAttribute("quantidadeBairros");
			session.removeAttribute("quantidadeCep");
			session.removeAttribute("colecaoBairros");
			session.removeAttribute("colecaoCep");
		}
		
		if(idLogradouro == null || idLogradouro.equals("")){
			session.removeAttribute("collImoveis");
			return retorno;
		}
		
		if(form.getDescricaoLogradouroImovelOrigemFiltro() == null || form.getDescricaoLogradouroImovelOrigemFiltro().equals("")){
			this.exibirLogradouroOrigem(form, httpServletRequest);
		}
		
		FiltroImovel filtro = new FiltroImovel(FiltroImovel.ID);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.UNIDADE_FEDERACAO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_BAIRRO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_CEP);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TIPO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TITULO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.ENDERECO_REFERENCIA);
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.LOGRADOURO_ID, idLogradouro));

		imoveis = this.getFachada().pesquisar(filtro, Imovel.class.getName()); 
		
		if (!Util.isVazioOrNulo(imoveis)) {
        	session.setAttribute("collImoveis", imoveis);
        }else {
        	if(!form.getDescricaoLogradouroImovelOrigemFiltro().equalsIgnoreCase("Logradouro Inexistente")){
        		throw new ActionServletException("atencao.pesquisa.nenhumresultado");
        	}
        }
        
		return retorno;
	}

	private void exibirLogradouroOrigem(TransferirImovelLogradouroActionForm form, HttpServletRequest httpServletRequest) {
		
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTIPO);
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTITULO);
		filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, Integer.parseInt(form.getLogradouroImovelOrigemFiltro())));
		
		Collection logradouroEncontrado = this.getFachada().pesquisar(filtroLogradouro, Logradouro.class.getName());

		Logradouro logradouro = (Logradouro) Util.retonarObjetoDeColecao(logradouroEncontrado);
		
		httpServletRequest.removeAttribute("idLogradouroNaoEncontrado");
		
		if(logradouro != null){
			String logradouroFormatado = "";

			if(logradouro.getLogradouroTipo() != null){
				logradouroFormatado = logradouro.getLogradouroTipo().getDescricaoAbreviada();	
			}
			
			if(logradouro.getLogradouroTitulo() != null){
				logradouroFormatado = logradouroFormatado + " " + logradouro.getLogradouroTitulo().getDescricaoAbreviada(); 
			}
				
			logradouroFormatado = logradouroFormatado + " " + logradouro.getNome();
			
			form.setLogradouroImovelOrigemFiltro(String.valueOf(logradouro.getId()));
			form.setDescricaoLogradouroImovelOrigemFiltro(logradouroFormatado);
			
			httpServletRequest.setAttribute("idLogradouroNaoEncontrado", "false");
		
		} else {
			form.setLogradouroImovelOrigemFiltro("");
			form.setDescricaoLogradouroImovelOrigemFiltro("Logradouro Inexistente");
			httpServletRequest.setAttribute("idLogradouroNaoEncontrado", "true");
		}
	}
}
