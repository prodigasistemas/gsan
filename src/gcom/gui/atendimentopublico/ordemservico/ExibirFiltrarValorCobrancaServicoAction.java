package gcom.gui.atendimentopublico.ordemservico;

import java.util.Collection;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 27/10/2006
 */
public class ExibirFiltrarValorCobrancaServicoAction extends GcomAction {

	/**
	 * [UC0392] Filtrar Valor de Cobrança do Serviço
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa do Valor de
	 * Cobrança de Serviço
	 * 
	 * @author Rômulo Aurélio
	 * @date 27/10/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);
		ActionForward retorno = actionMapping
				.findForward("filtrarValorCobrancaServico");

		Fachada fachada = Fachada.getInstancia();
		FiltrarValorCobrancaServicoActionForm form = (FiltrarValorCobrancaServicoActionForm) actionForm;

		if (httpServletRequest.getParameter("menu") != null) {
			form.setIndicadorMedido("3");
			form.setIndicativoTipoSevicoEconomias("3");
			form.setIndicadorGeracaoDebito("3");
			form.setAtualizar("1");
		}

		// Pesquisar Categoria
		this.pesquisarCategoria(httpServletRequest, form);
		
		// pesquisar SubCategoria
		this.pesquisarSubCategoria(httpServletRequest, form);
		
		this.getPerfilImovelCollection(sessao);
		
		this.getHidrometroCapacidadeCollection(sessao, fachada);
		
		
		if (form.getTipoServico() != null && !form.getTipoServico().equals("")) {
			this.getServicoTipo(form, httpServletRequest);
		}

		if(form.getIndicativoTipoSevicoEconomias() == null){
			form.setIndicativoTipoSevicoEconomias("3");
		}
		
		return retorno;
	}

	/**
	 * Recupera Tipo de Serviço
	 * 
	 * [FS0001] - Verificar Serviço Geral Débito
	 * 
	 * @author Rômulo Aurélio
	 * @date 29/10/2006
	 * 
	 * @param filtrarRegistroAtendimentoActionForm
	 * @param fachada
	 */
	private void getServicoTipo(FiltrarValorCobrancaServicoActionForm form,
			HttpServletRequest httpServletRequest) {
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Filtra Tipo de Serviço
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		filtroServicoTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.ID, form.getTipoServico()));
		
		filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		
		// Recupera Tipo de Serviço
		Collection colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo,
				ServicoTipo.class.getName());
		
		if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
			sessao.setAttribute("servicoTipoEncontrada", "true");
			ServicoTipo servicoTipo = (ServicoTipo) colecaoServicoTipo
					.iterator().next();
			form.setNomeTipoServico(servicoTipo.getDescricao());
//			if (servicoTipo.getDebitoTipo() == null) {
//				throw new ActionServletException(
//						"atencao.valor_cobranca_tipo_servico_sem_debito", null,
//						servicoTipo.getDescricao());
//			}
		} else {
			sessao.removeAttribute("servicoTipoEncontrada");
			form.setTipoServico("");
			form.setNomeTipoServico("Tipo de Serviço inexistente");
		}
	}

	/**
	 * Carrega a coleção de capacidade de hidrômetro
	 * 
	 * @author Rômulo Aurélio
	 * @date 29/10/2006
	 * 
	 * @param sessao
	 */
	private void getHidrometroCapacidadeCollection(HttpSession sessao,
			Fachada fachada) {
		
		// Filtro para o campo Capacidade do Hidrômetro
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroHidrometroCapacidade.setCampoOrderBy(FiltroHidrometroCapacidade.DESCRICAO);

		Collection colecaoHidrometroCapacidade = fachada.pesquisar(
				filtroHidrometroCapacidade, HidrometroCapacidade.class.getName());
		
		if (colecaoHidrometroCapacidade != null
				&& !colecaoHidrometroCapacidade.isEmpty()) {
			sessao.setAttribute("colecaoHidrometroCapacidade",
					colecaoHidrometroCapacidade);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Capacidade Hidrômetro");
		}
	}

	/**
	 * Carrega a coleção de perfil de imóvel
	 * 
	 * @author Rômulo Aurélio
	 * @date 29/10/2006
	 * 
	 * @param sessao
	 */
	private void getPerfilImovelCollection(HttpSession sessao) {
		
		Fachada fachada = Fachada.getInstancia();
		
		// Filtro para o campo Perfil do Imóvel
		FiltroImovelPerfil filtroPerfilImovel = new FiltroImovelPerfil();
		
		filtroPerfilImovel.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroPerfilImovel.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

		Collection colecaoImovelPerfil = fachada.pesquisar(filtroPerfilImovel,
				ImovelPerfil.class.getName());
		
		if (colecaoImovelPerfil != null && !colecaoImovelPerfil.isEmpty()) {
			
			sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Perfil do Imóvel");
		}
	}
	
	// Verificar existência de dados (CATEGORIA)
	private void pesquisarCategoria(HttpServletRequest httpServletRequest, 
			FiltrarValorCobrancaServicoActionForm form) {
	
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.setConsultaSemLimites(true);
		
		filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());
		
		if(colecaoCategoria == null || colecaoCategoria.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null, "Categorias");
		}else{
			httpServletRequest.setAttribute("colecaoCategoria", colecaoCategoria);
		}
	}
	
    // Verificar existência de dados (SUBCATEGORIA)
	private void pesquisarSubCategoria(HttpServletRequest httpServletRequest, 
			FiltrarValorCobrancaServicoActionForm form) {
	
		String idCategoria = form.getIdCategoria();
		Collection colecaoSubCategoria  = null;
		
		if(idCategoria != null && !idCategoria.equalsIgnoreCase("-1")){
			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria(FiltroSubCategoria.DESCRICAO);
			
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, idCategoria));
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoSubCategoria = this.getFachada().pesquisar(filtroSubCategoria, Subcategoria.class.getName());
			
			if(colecaoSubCategoria == null || colecaoSubCategoria.isEmpty()){
				throw new ActionServletException("atencao.naocadastrado", null, "SubCategorias");
			}else{
				httpServletRequest.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
			}
		}else{
			httpServletRequest.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
		}
	}
	
}
