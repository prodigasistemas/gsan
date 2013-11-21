package gcom.gui.cadastro.imovel;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Bairro;
import gcom.gui.GcomAction;
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
 * [UC1202]Exibir a Página de transferir imóveis para outro Logradouro.
 * 
 * @author Davi Menezes
 * @date 03/08/2011
 */
public class ExibirTransferirImovelLogradouroAction extends GcomAction {
	
	private static final int LOGRADOURO_ORIGEM = 1;
	
	private static final int LOGRADOURO_DESTINO = 2;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("transferirImovel");
		
		boolean idLogradouroNaoEncontrado = true;
		
		HttpSession session = httpServletRequest.getSession(false);
		
		Collection<Bairro> colecaoBairros = null;
		
		Collection<Cep> colecaoCep = null;
		
		int qtdBairros = 0, qtdCep = 0;
		
		TransferirImovelLogradouroActionForm form = (TransferirImovelLogradouroActionForm) actionForm;
	
		if(Util.verificarNaoVazio(form.getLogradouroImovelOrigemFiltro())){
			idLogradouroNaoEncontrado = this.pesquisarLogradouro(LOGRADOURO_ORIGEM, form, httpServletRequest);
		}
		
		//Pesquisar o Logradouro Destino e trazer as coleções de bairro e cep
		if(Util.verificarNaoVazio(form.getLogradouroImovelDestinoFiltro())){
			idLogradouroNaoEncontrado = this.pesquisarLogradouro(LOGRADOURO_DESTINO, form, httpServletRequest);
			
			String idLogradouro = form.getLogradouroImovelDestinoFiltro();
			
			if(!idLogradouroNaoEncontrado){
				
				FiltroLogradouroBairro filtroBairro = new FiltroLogradouroBairro();
				filtroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.BAIRRO);
				filtroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO, idLogradouro));
				
				FiltroLogradouroCep filtroCep = new FiltroLogradouroCep();
				filtroCep.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroCep.CEP);
				filtroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO, idLogradouro));
				
				Collection colecaoLogradouroBairro = getFachada().pesquisar(filtroBairro,LogradouroBairro.class.getName());
				Collection colecaoLogradouroCep = getFachada().pesquisar(filtroCep,LogradouroCep.class.getName());
				
				Iterator iteratorBairro = colecaoLogradouroBairro.iterator();
				Iterator iteratorCep = colecaoLogradouroCep.iterator();
				
				colecaoBairros = new ArrayList();
				colecaoCep = new ArrayList();
				
				while(iteratorBairro.hasNext()) {
					LogradouroBairro logradouroBairro = (LogradouroBairro) iteratorBairro.next();
					colecaoBairros.add(logradouroBairro.getBairro());
				}
				
				while(iteratorCep.hasNext()) {
					LogradouroCep logradouroCep = (LogradouroCep) iteratorCep.next();
					colecaoCep.add(logradouroCep.getCep());
				}
				
				qtdBairros = colecaoBairros.size();
				qtdCep = colecaoCep.size();
				
			}
		}
		
		session.setAttribute("quantidadeBairros", qtdBairros);
		
		session.setAttribute("quantidadeCep", qtdCep);
		
		session.setAttribute("colecaoBairros", colecaoBairros);
		
		session.setAttribute("colecaoCep", colecaoCep);
		
		return retorno;
	}
	
	/**
	 * Pesquisar pelo logradouro
	 * @param Logradouro origem ou destino
	 * @param TransferirImovelLogradouroActionForm
	 * @param httpServletRequest
	 */
	public boolean pesquisarLogradouro(Integer atributoToSet, TransferirImovelLogradouroActionForm form, HttpServletRequest httpServletRequest){
		
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTIPO);
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTITULO);
		
		if(atributoToSet == LOGRADOURO_ORIGEM) {
			filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, Integer.parseInt(form.getLogradouroImovelOrigemFiltro())));
		} else if(atributoToSet == LOGRADOURO_DESTINO){
			filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, Integer.parseInt(form.getLogradouroImovelDestinoFiltro())));
		}
		// filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

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
			
			
			if(atributoToSet == LOGRADOURO_ORIGEM) {
				form.setLogradouroImovelOrigemFiltro(String.valueOf(logradouro.getId()));
				form.setDescricaoLogradouroImovelOrigemFiltro(logradouroFormatado);
			} else if(atributoToSet == LOGRADOURO_DESTINO) {
				form.setLogradouroImovelDestinoFiltro(String.valueOf(logradouro.getId()));
				form.setDescricaoLogradouroImovelDestinoFiltro(logradouroFormatado);
			}
			
			httpServletRequest.setAttribute("idLogradouroNaoEncontrado", "false");
			return false;
		} else {
			
			if(atributoToSet == LOGRADOURO_ORIGEM) {
				form.setLogradouroImovelOrigemFiltro("");
				form.setDescricaoLogradouroImovelOrigemFiltro("Logradouro Inexistente");
			} else if(atributoToSet == LOGRADOURO_DESTINO) {
				form.setLogradouroImovelDestinoFiltro("");
				form.setDescricaoLogradouroImovelDestinoFiltro("Logradouro Inexistente");
			}
			
			// exception
			httpServletRequest.setAttribute("idLogradouroNaoEncontrado", "true");
			return true;
		}
	}
}
