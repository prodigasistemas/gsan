package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovelEconomia;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action ConsultarRelacaoClienteImovelAction
 *
 * @author thiago toscano
 * @date 10/03/2006
 */
public class ExibirImovelRelacaoClienteImovelAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse httpServletResponse) {

        ConsultarRelacaoClienteImovelActionForm form = (ConsultarRelacaoClienteImovelActionForm) actionForm;

        if ( !Util.verificarNaoVazio(form.getIdImovel())) {
	    	throw new ActionServletException("erro.parametro.nao.informado", null, "idImovel");
	    }

        HttpSession sessao = request.getSession(false);

        obterImovelInformadoESetarSessao(form, sessao);
    	
    	setarColecaoClienteImovelSessao(form, sessao);

    	setarColecaoImovelSubcategoriaHelperSessao(form, sessao);

        return actionMapping.findForward("exibir");
    }

	/**
	 * Consulta o Imovel informado pelo usuario e então seta-o na sessão.
	 *
	 *@since 14/09/2009
	 *@author Marlon Patrick
	 */
	private void obterImovelInformadoESetarSessao(ConsultarRelacaoClienteImovelActionForm form,
			HttpSession sessao) {
		FiltroImovel filtroImovel = criarFiltroConsultarImovelInformadoUsuario(form);
        Collection<Imovel> colecaoImoveis = Fachada.getInstancia().pesquisar(filtroImovel,Imovel.class.getSimpleName());
        
        if (Util.isVazioOrNulo(colecaoImoveis)) {
        	throw new ActionServletException("atencao.pesquisa.nenhumresultado");        	
        }
        
    	sessao.setAttribute("imovel",colecaoImoveis.iterator().next());
	}

	/**
	 * Cria uma colecão de ImovelSubcategoriaHelper e seta-a na sessão.
	 *
	 *@since 14/09/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoImovelSubcategoriaHelperSessao(ConsultarRelacaoClienteImovelActionForm form,
			HttpSession sessao) {
		FiltroImovelSubCategoria filtroImovelSubCategoria = criarFiltroImovelSubCategoria(form);
		Collection<ImovelSubcategoria> colecaoImovelSubCategoria = Fachada.getInstancia().pesquisar(filtroImovelSubCategoria,ImovelSubcategoria.class.getSimpleName());
		
		Collection<ImovelSubcategoriaHelper> colecaoImovelSubCategoriaHelper = criarColecaoImovelSubcategoriaHelper(
				form, colecaoImovelSubCategoria);

		sessao.setAttribute("collImovelSubCategoriaHelper",colecaoImovelSubCategoriaHelper);
	}

	/**
	 *[UC ] - 
	 *[SB ] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 14/09/2009
	 *@author Marlon Patrick
	 */
	private Collection<ImovelSubcategoriaHelper> criarColecaoImovelSubcategoriaHelper(
			ConsultarRelacaoClienteImovelActionForm form,
			Collection<ImovelSubcategoria> colecaoImovelSubCategoria) {
		
		Collection<ImovelSubcategoriaHelper> colecaoImovelSubCategoriaHelper = new ArrayList<ImovelSubcategoriaHelper>();

		if ( !Util.isVazioOrNulo(colecaoImovelSubCategoria)) {
			Iterator<ImovelSubcategoria> it = colecaoImovelSubCategoria.iterator();

			while (it.hasNext()) {
				ImovelSubcategoria imovelSubcategoria = it.next();

		    	FiltroClienteImovelEconomia filtroClienteImovelEconomia = criarFiltroClienteImovelEconomia(
						form, imovelSubcategoria);
		    	
		    	Collection<ClienteImovelEconomia> colecaoClienteImovelEconomia = 
		    		Fachada.getInstancia().pesquisar(filtroClienteImovelEconomia, ClienteImovelEconomia.class.getSimpleName());

				ImovelSubcategoriaHelper imovelSubcategoriaHelper = new ImovelSubcategoriaHelper();
				imovelSubcategoriaHelper.setCategoria(imovelSubcategoria.getComp_id().getSubcategoria().getCategoria().getDescricao());
				imovelSubcategoriaHelper.setSubcategoria(imovelSubcategoria.getComp_id().getSubcategoria().getDescricao());
				imovelSubcategoriaHelper.setQuantidadeEconomias(imovelSubcategoria.getQuantidadeEconomias());
				imovelSubcategoriaHelper.setColecaoImovelEconomia(colecaoClienteImovelEconomia);
				colecaoImovelSubCategoriaHelper.add(imovelSubcategoriaHelper);
			}
		}
		return colecaoImovelSubCategoriaHelper;
	}

	/**
	 *[UC ] - 
	 *[SB ] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 14/09/2009
	 *@author Marlon Patrick
	 */
	private FiltroClienteImovelEconomia criarFiltroClienteImovelEconomia(
			ConsultarRelacaoClienteImovelActionForm form,
			ImovelSubcategoria imovelSubcategoria) {
		FiltroClienteImovelEconomia filtroClienteImovelEconomia = new FiltroClienteImovelEconomia();
		filtroClienteImovelEconomia.setConsultaSemLimites(true);
		filtroClienteImovelEconomia.adicionarParametro( new ParametroSimples (FiltroClienteImovelEconomia.IMOVEL_ID,form.getIdImovel()));
		filtroClienteImovelEconomia.adicionarParametro( new ParametroSimples (FiltroClienteImovelEconomia.SUBCATEGORIA_ID,imovelSubcategoria.getComp_id().getSubcategoria().getId()));
		filtroClienteImovelEconomia.adicionarParametro(new ParametroSimples(FiltroClienteImovelEconomia.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
		filtroClienteImovelEconomia.adicionarParametro(new ParametroNulo(FiltroClienteImovelEconomia.FIM_RELACAO_MOTIVO));
		filtroClienteImovelEconomia.adicionarParametro(new ParametroNulo(FiltroClienteImovelEconomia.DATA_FIM_RELACAO));
		filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIA);
		filtroClienteImovelEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovelEconomia.IMOVEL_ECONOMIEA_AREA_CONSTRUIDA_FAIXA);
		return filtroClienteImovelEconomia;
	}

	/**
	 *[UC ] - 
	 *[SB ] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 14/09/2009
	 *@author Marlon Patrick
	 */
	private FiltroImovelSubCategoria criarFiltroImovelSubCategoria(
			ConsultarRelacaoClienteImovelActionForm form) {
		FiltroImovelSubCategoria filtroImovelSubCategoria = new FiltroImovelSubCategoria();
		filtroImovelSubCategoria.setConsultaSemLimites(true);
		filtroImovelSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelSubCategoria.SUBCATEGORIA);
		filtroImovelSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelSubCategoria.SUBCATEGORIA_CATEGORIA);
		filtroImovelSubCategoria.adicionarParametro(new ParametroSimples(FiltroImovelSubCategoria.IMOVEL_ID,form.getIdImovel()));
		return filtroImovelSubCategoria;
	}

	/**
	 *[UC ] - 
	 *[SB ] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 14/09/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoClienteImovelSessao(
			ConsultarRelacaoClienteImovelActionForm form, HttpSession sessao) {
		FiltroClienteImovel filtroClienteImovel = criarFiltroClienteImovel(form);

		Collection<ClienteImovel> colecaoClienteImoveis = Fachada.getInstancia().pesquisar(filtroClienteImovel,ClienteImovel.class.getSimpleName());
		sessao.setAttribute("collClienteImovel",colecaoClienteImoveis);
	}

	/**
	 *[UC ] - 
	 *[SB ] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 14/09/2009
	 *@author Marlon Patrick
	 */
	private FiltroClienteImovel criarFiltroClienteImovel(
			ConsultarRelacaoClienteImovelActionForm form) {
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
		filtroClienteImovel.setConsultaSemLimites(true);
		filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID,form.getIdImovel()));
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE);
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");
		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteImovelFimRelacaoMotivo");

		if (form.getIdClienteRelacaoTipo() != null && !"".equals(form.getIdClienteRelacaoTipo())) {
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, form.getIdClienteRelacaoTipo()));
		}
		if (form.getIdClienteImovelFimRelacaoMotivo() != null && !"".equals(form.getIdClienteImovelFimRelacaoMotivo())) {
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.FIM_RELACAO_MOTIVO, form.getIdClienteImovelFimRelacaoMotivo()));
		}
		if (form.getPeriodoInicialDataInicioRelacao() != null && !"".equals(form.getPeriodoInicialDataInicioRelacao())) {        		
			Date periodoInicialDataInicioRelacao = Util.converteStringParaDate(form.getPeriodoInicialDataInicioRelacao());

			Date periodoFinalDataInicioRelacao = null;
			
			if (form.getPeriodoFinalDataInicioRelacao() == null || form.getPeriodoFinalDataInicioRelacao().equals("")) {
				periodoFinalDataInicioRelacao = periodoInicialDataInicioRelacao;
			} else {
				periodoFinalDataInicioRelacao = Util.converteStringParaDate(form.getPeriodoFinalDataInicioRelacao());
			}
			Calendar diInicio = Calendar.getInstance();
			diInicio.setTime(periodoInicialDataInicioRelacao);
			diInicio.set(Calendar.HOUR, 0);
			diInicio.set(Calendar.MINUTE, 0);
			diInicio.set(Calendar.SECOND, 0);
			
			Calendar diFim = Calendar.getInstance();
			diFim.setTime(periodoFinalDataInicioRelacao);
			diFim.set(Calendar.HOUR, 0);
			diFim.set(Calendar.MINUTE, 0);
			diFim.set(Calendar.SECOND, 0);
			
			filtroClienteImovel.adicionarParametro(new Intervalo(FiltroClienteImovel.DATA_INICIO_RELACAO, diInicio.getTime(), diFim.getTime()));
		}
		if (form.getPeriodoInicialDataFimRelacao() != null && !"".equals(form.getPeriodoInicialDataFimRelacao())) {        		
			Date periodoInicialDataFimRelacao = Util.converteStringParaDate(form.getPeriodoInicialDataFimRelacao());

			Date periodoFinalDataFimRelacao = null;
			
			if (form.getPeriodoFinalDataFimRelacao() == null || form.getPeriodoFinalDataFimRelacao().equals("")) {
				periodoFinalDataFimRelacao = periodoInicialDataFimRelacao;
			} else {
				periodoFinalDataFimRelacao = Util.converteStringParaDate(form.getPeriodoFinalDataFimRelacao());
			}
			Calendar diInicio = Calendar.getInstance();
			diInicio.setTime(periodoInicialDataFimRelacao);
			diInicio.set(Calendar.HOUR, 0);
			diInicio.set(Calendar.MINUTE, 0);
			diInicio.set(Calendar.SECOND, 0);
			
			Calendar diFim = Calendar.getInstance();
			diFim.setTime(periodoFinalDataFimRelacao);
			diFim.set(Calendar.HOUR, 0);
			diFim.set(Calendar.MINUTE, 0);
			diFim.set(Calendar.SECOND, 0);
			
			filtroClienteImovel.adicionarParametro(new Intervalo(FiltroClienteImovel.DATA_FIM_RELACAO, diInicio.getTime(), diFim.getTime()));
		}
		
		if (form.getSituacaoRelacao() != null && form.getSituacaoRelacao().equalsIgnoreCase("1")) {        		
			filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		} else if (form.getSituacaoRelacao() != null && form.getSituacaoRelacao().equalsIgnoreCase("2")) {
			filtroClienteImovel.adicionarParametro(new ParametroNaoNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		} else {
			filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO, FiltroParametro.CONECTOR_OR, 2));
			filtroClienteImovel.adicionarParametro(new ParametroNaoNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		}
		return filtroClienteImovel;
	}

	/**
	 *[UC ] - 
	 *[SB ] -
	 *[FS ] -
	 *
	 * comment
	 *
	 *@since 14/09/2009
	 *@author Marlon Patrick
	 */
	private FiltroImovel criarFiltroConsultarImovelInformadoUsuario(
			ConsultarRelacaoClienteImovelActionForm form) {
		FiltroImovel filtroImovel = new FiltroImovel();
        filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getIdImovel()));
        
        filtroImovel.adicionarParametro(new ParametroNulo(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO,
    	ConectorOr.CONECTOR_OR, 2));
        filtroImovel.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroImovel.INDICADOR_IMOVEL_EXCLUIDO, 
        ConstantesSistema.SIM));
        
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOCALIDADE);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.QUADRA);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TIPO);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TITULO);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.BAIRRO);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL_MUNICIPIO);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.SETOR_COMERCIAL_MUNICIPIO_UF);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.MUNICIPIO);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CEP);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.UNIDADE_FEDERACAO);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.ENDERECO_REFERENCIA);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TIPO_PERIMETRO_INICIAL);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TITULO_PERIMETRO_INICIAL);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TIPO_PERIMETRO_FINAL);
        filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LOGRADOURO_TITULO_PERIMETRO_FINAL);
		return filtroImovel;
	}
}
