package gcom.gui.faturamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1082] Informar dados para Prescrever Débitos de Imóveis Públicos
 * 
 * @author Hugo Leonardo
 * @date 15/10/2010
 */
public class ExibirPrescreverImoveisPublicosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.
			findForward("exibirPrescreverImoveisPublicos");
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Form
		PrescreverImoveisPublicosActionForm form = (PrescreverImoveisPublicosActionForm) actionForm;
		
		// Primeira vez que carrega a pagina
		if ( httpServletRequest.getParameter("menu") != null && 
				httpServletRequest.getParameter("menu").equals("sim")) {
			
			form.setFormaPrescricao("0");
		}
		
		//Manual
		if(form.getFormaPrescricao().equals("0")){
			
			httpServletRequest.setAttribute("manual", true);
			
        // Automático	
		}else if(form.getFormaPrescricao().equals("1")){
			
			httpServletRequest.setAttribute("manual", false);
		}
		
		// Se selecionar imóvel ou cliente desabilitar o campo "Esfera Poder"
		if(!Util.verificarNaoVazio(form.getIdImovel()) && !Util.verificarNaoVazio(form.getIdCliente())){
			
			httpServletRequest.setAttribute("esfera", true);
		}else{
			
			httpServletRequest.setAttribute("esfera", false);
		}
		
		// Pesquisa a Esfera de Poder de Acordo com a forma de Prescrição.
		pesquisarEsferaPoder(sessao, form);
		
		if (Util.verificarNaoVazio( form.getIdCliente())) {
			
			this.pesquisarCliente( httpServletRequest, form);
		}
		
		if(Util.verificarNaoVazio( form.getIdImovel())){
			this.pesquisarImovel( httpServletRequest, form);
		}
		
		if(sessao.getAttribute("colecaoEsferaPoder") == null ){
			
			this.pesquisarEsferaPoder(sessao, form);
		}
		
		return retorno;
	}
	
	private void pesquisarEsferaPoder(HttpSession sessao, PrescreverImoveisPublicosActionForm form){
		
		Collection colTipoEsfera = new ArrayList();
		
		// Manual
		if(form.getFormaPrescricao().equals("0")){
			
			colTipoEsfera.add(EsferaPoder.MUNICIPAL);
			colTipoEsfera.add(EsferaPoder.ESTADUAL);
			colTipoEsfera.add(EsferaPoder.FEDERAL);
		}else if(form.getFormaPrescricao().equals("1")){
			
			colTipoEsfera.add(EsferaPoder.ESTADUAL);
			colTipoEsfera.add(EsferaPoder.FEDERAL);
		}
		
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
		
		filtroEsferaPoder.adicionarParametro(
				new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroEsferaPoder.adicionarParametro( new ParametroSimplesIn(FiltroEsferaPoder.ID, colTipoEsfera));
		
		filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
		
		Collection<EsferaPoder> colecaoEsferaPoder = 
			Fachada.getInstancia().pesquisar( filtroEsferaPoder, EsferaPoder.class.getName());
		
		if (colecaoEsferaPoder == null || colecaoEsferaPoder.isEmpty()) {
			
			throw new ActionServletException("atencao.naocadastrado", null, "Esfera Poder");
		}else{
			
			sessao.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
		}
	}
	
	private void pesquisarCliente(HttpServletRequest httpServletRequest, 
			PrescreverImoveisPublicosActionForm form) {

		// Pesquisa o Cliente na base
		FiltroCliente filtroCliente = new FiltroCliente();
		
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.ID, form.getIdCliente()));

		Collection<Cliente> collCliente = Fachada.getInstancia().pesquisar(
				filtroCliente, Cliente.class.getName());

		// Se nenhum cliente for encontrado a mensagem é enviada para a página
		if (!Util.isVazioOrNulo(collCliente)) {
			
			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(collCliente);
			
			form.setIdCliente("" + cliente.getId());
			form.setDescricaoCliente( cliente.getNome());

		} else {
			form.setIdCliente("");
			form.setDescricaoCliente("CLIENTE INEXISTENTE");
			httpServletRequest.setAttribute("clienteInexistente",true);
		}
	}
	
	private void pesquisarImovel(HttpServletRequest httpServletRequest, 
			PrescreverImoveisPublicosActionForm form) {

		// Pesquisa o Imovel na base
		FiltroImovel filtroImovel = new FiltroImovel();
		
		filtroImovel.adicionarParametro(new ParametroSimples(
				FiltroImovel.ID, form.getIdImovel()));
		
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
		
		Collection<Imovel> collImovel = Fachada.getInstancia().pesquisar(
				filtroImovel, Imovel.class.getName());

		// Se nenhum cliente for encontrado a mensagem é enviada para a página
		if (!Util.isVazioOrNulo(collImovel)) {
			
			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(collImovel);
			
			form.setIdImovel("" + imovel.getId());
			form.setDescricaoImovel( imovel.getInscricaoFormatada());

		} else {
			form.setIdImovel("");
			form.setDescricaoImovel("IMOVEL INEXISTENTE");
			httpServletRequest.setAttribute("imovelInexistente",true);
		}
	}
	
}
