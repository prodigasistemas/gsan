package gcom.gui.micromedicao;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Estabelecer Vinculo
 * 
 * @author Rafael Santos, Magno Gouveia
 * @since 11/01/2006, 20/08/2011
 */
public class ExibirDesfazerVinculoPopupAction extends GcomAction {
	/**
	 * Description of the Method
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

		ActionForward retorno = actionMapping.findForward("desfazerVinculoPopup");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String matriculaImovel = httpServletRequest.getParameter("MatriculaImovel").trim();

		FiltroImovel filtroImovel = new FiltroImovel();

		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, matriculaImovel));

		// Procura Imovel na base
		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroImovel, Imovel.class.getName()));

		// [FS0009] - Verificar se o imóvel é um condomínio
		if (imovel.getIndicadorImovelCondominio() == null 
				|| !imovel.getIndicadorImovelCondominio().equals(Imovel.IMOVEL_EXCLUIDO)) {
			throw new ActionServletException("atencao.imovel.nao_condominio");
		}
		

		/*
		 * [SB0003] Desfazer Vínculo
		 * a lista deverá ser ordenada pelo indicador de imóvel área comum
		 * (imov_icimovelareaccomum/imov_id, 
		 * Caso o referido indicador seja igual a 1  a fonte dos dados exibidos será apresentada em vermelho
		 */
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel(FiltroClienteImovel.IMOVEL_INDICADOR_IMOVEL_AREA_COMUM);

		// Objetos que serão retornados pelo Hibernate através do clienteImovel
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.BAIRRO);
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CEP);
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.LOCALIDADE);
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio.unidadeFederacao");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra.rota");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial");
    	filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTipo");
    	filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroInicial.logradouroTitulo");
    	filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal");
    	filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTipo");
    	filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("imovel.perimetroFinal.logradouroTitulo");
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
		filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_CONDOMINIO_ID, matriculaImovel));

		Collection<Imovel> imovelPesquisadoVinculados = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

		sessao.setAttribute("colecaoImoveisVinculados", imovelPesquisadoVinculados);
		sessao.setAttribute("imovelCondominio", imovel);

		return retorno;
	}
	
}
