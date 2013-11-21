package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		sessao.removeAttribute("imoveisFiltrados");

		ImovelParams imovelParams = new ImovelParams(sessao);

		int totalRegistros = getTotalRegistros(fachada, imovelParams);

		ActionForward retorno = actionMapping.findForward("manterImovel");
		retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);

		Collection<ClienteImovel> imoveis = getImoveis(httpServletRequest, fachada, imovelParams);

		if (imoveis != null && !imoveis.isEmpty()) {
			Collection<ClienteImovel> imoveisLista = carregaListaImoveis(imoveis);

			if (httpServletRequest.getAttribute("atualizar") != null && imoveisLista.size() == 1) {
				retorno = configuraRetornoImovelUnico(actionMapping, httpServletRequest, imoveisLista);
			} else {
				sessao.setAttribute("imoveisFiltrados", imoveisLista);
			}

		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "Imóvel");
		}

		return retorno;
	}

	private ActionForward configuraRetornoImovelUnico(ActionMapping actionMapping, HttpServletRequest httpServletRequest, Collection<ClienteImovel> imoveisLista) {
		ActionForward retorno = actionMapping.findForward("atualizarImovel");
		ClienteImovel imovelUnico = (ClienteImovel) imoveisLista.iterator().next();

		httpServletRequest.setAttribute("idRegistroAtualizacao", imovelUnico.getImovel().getId().toString());
		httpServletRequest.setAttribute("atualizar","atualizar");

		return retorno;
	}

	private Collection<ClienteImovel> carregaListaImoveis(Collection<ClienteImovel> imoveis) {
		Iterator<ClienteImovel> iteratorImoveis = imoveis.iterator();
		Collection<ClienteImovel> imoveisLista = new ArrayList<ClienteImovel>();

		while (iteratorImoveis.hasNext()) {
			imoveisLista.add(iteratorImoveis.next());
		}

		return imoveisLista;
	}

	private Collection<ClienteImovel> getImoveis(HttpServletRequest httpServletRequest, Fachada fachada, ImovelParams imovelParams) {
		@SuppressWarnings("unchecked")
		Collection<ClienteImovel> imoveis = fachada.pesquisarImovel(imovelParams.idImovel,
				imovelParams.idLocalidade,
				imovelParams.codigoSetorComercial,
				imovelParams.numeroQuadra,
				imovelParams.lote,
				imovelParams.subLote,
				imovelParams.codigoCliente,
				imovelParams.idMunicipio,
				imovelParams.cep,
				imovelParams.idBairro,
				imovelParams.idLogradouro, 
				imovelParams.numeroImovelInicial, 
				imovelParams.numeroImovelFinal, 
				false,
				false, 
				((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")));
		return imoveis;
	}

	private int getTotalRegistros(Fachada fachada, ImovelParams imovelParams) {
		int totalRegistros = fachada.pesquisarQuantidadeImovel(imovelParams.idImovel,
				imovelParams.idLocalidade,
				imovelParams.codigoSetorComercial,
				imovelParams.numeroQuadra,
				imovelParams.lote,
				imovelParams.subLote,
				imovelParams.codigoCliente,
				imovelParams.idMunicipio,
				imovelParams.cep,
				imovelParams.idBairro,
				imovelParams.idLogradouro, 
				imovelParams.numeroImovelInicial, 
				imovelParams.numeroImovelFinal, 
				false,
				false).intValue();
		return totalRegistros;
	}

	private class ImovelParams{
		public String idLocalidade;
		public String codigoSetorComercial;
		public String numeroQuadra;
		public String lote;
		public String subLote;
		public String codigoCliente;
		public String idMunicipio;
		public String cep;
		public String idBairro;
		public String idLogradouro;
		public String idImovel;
		public String numeroImovelInicial;
		public String numeroImovelFinal;

		public ImovelParams(HttpSession sessao){
			idLocalidade = (String) sessao.getAttribute("idLocalidade");
			codigoSetorComercial = (String) sessao.getAttribute("idSetorComercial");
			numeroQuadra = (String) sessao.getAttribute("idQuadra");
			lote = (String) sessao.getAttribute("lote");
			subLote = (String) sessao.getAttribute("subLote");
			codigoCliente = (String) sessao.getAttribute("codigoCliente");
			idMunicipio = (String) sessao.getAttribute("idMunicipio");
			cep = (String) sessao.getAttribute("cep");
			idBairro = (String) sessao.getAttribute("idBairro");
			idLogradouro = (String) sessao.getAttribute("idLogradouro");
			idImovel = (String) sessao.getAttribute("idImovel");
			numeroImovelInicial = (String) sessao.getAttribute("numeroImovelInicial");
			numeroImovelFinal = (String) sessao.getAttribute("numeroImovelFinal");
		}
	}

}