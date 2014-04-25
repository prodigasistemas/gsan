package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela inserção da subcategoria
 * 
 * [UC0058] Inserir Subcategoria
 * 
 * @author Fernanda Paiva
 * @date 28/12/2005
 */
public class InserirSubcategoriaAction extends GcomAction {
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

		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirSubcategoriaActionForm inserirSubcategoriaActionForm = (InserirSubcategoriaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		//------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SUBCATEGORIA_INSERIR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SUBCATEGORIA_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		//------------ REGISTRAR TRANSAÇÃO ----------------

		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		//Obtém a sessão
		//HttpSession sessao = httpServletRequest.getSession(false);

		String categoria = inserirSubcategoriaActionForm.getCategoria();
		String descricaoSubcategoria = (String) inserirSubcategoriaActionForm
				.getDescricaoSubcategoria();
		Integer codigoSubcategoria = new Integer(inserirSubcategoriaActionForm
				.getCodigoSubcategoria());
		String descricaoAbreviada = inserirSubcategoriaActionForm
				.getDescricaoAbreviada();
		String codigoTarifaSocial = inserirSubcategoriaActionForm
				.getCodigoTarifaSocial();
		Integer codigoGrupoSubcategoria = null;

		if (inserirSubcategoriaActionForm.getCodigoGrupoSubcategoria() != null && !inserirSubcategoriaActionForm.getCodigoGrupoSubcategoria().equalsIgnoreCase("")) {

			codigoGrupoSubcategoria = new Integer(inserirSubcategoriaActionForm
					.getCodigoGrupoSubcategoria());
		}
		String numeroFatorFiscalizacao = inserirSubcategoriaActionForm
				.getNumeroFatorFiscalizacao();

		Short indicadorTarifaConsumo = null;

		if (inserirSubcategoriaActionForm.getIndicadorTarifaConsumo() != null  && !inserirSubcategoriaActionForm.getIndicadorTarifaConsumo().equalsIgnoreCase("")) {

			indicadorTarifaConsumo = new Integer(inserirSubcategoriaActionForm
					.getIndicadorTarifaConsumo()).shortValue();
		}

		String indicadorSazonalidade = inserirSubcategoriaActionForm
				.getIndicadorSazonalidade();

		Categoria categoriaSelecionada = null;
		Subcategoria subcategoriaSelecionada = null;

		//Verifica a descrição da categoria 
		if (categoria != null && !categoria.equals("")) {
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.CODIGO, categoria));

			Collection<Categoria> categorias = fachada.pesquisar(
					filtroCategoria, Categoria.class.getName());

			if (categorias != null && categorias.isEmpty()) {
				throw new ActionServletException(
						"atencao.categoria_inexistente", null, "codigo");
			}

			categoriaSelecionada = categorias.iterator().next();
		}
		if (codigoSubcategoria != null) {
			FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();

			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CODIGO, codigoSubcategoria));

			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CATEGORIA_ID, categoria));

			filtroSubcategoria
					.adicionarCaminhoParaCarregamentoEntidade("categoria");

			Collection<Subcategoria> subcategorias = fachada.pesquisar(
					filtroSubcategoria, Subcategoria.class.getName());

			if (subcategorias != null && !subcategorias.isEmpty()) {
				subcategoriaSelecionada = subcategorias.iterator().next();
				throw new ActionServletException(
						"atencao.subcategoria_ja_existente",
						codigoSubcategoria.toString(), subcategoriaSelecionada
								.getCategoria().getDescricao());
			}
		}

		Short indicadorDeUso = ConstantesSistema.INDICADOR_USO_ATIVO;

		//cria o objeto subcategoria para ser inserido
		
		Subcategoria subcategoria = new Subcategoria(codigoSubcategoria,
				descricaoSubcategoria, indicadorDeUso, new Short(
						indicadorSazonalidade), descricaoAbreviada,
				codigoTarifaSocial, codigoGrupoSubcategoria,
				new Short(numeroFatorFiscalizacao), 
						indicadorTarifaConsumo, new Date(),
				categoriaSelecionada);

		//------------ REGISTRAR TRANSAÇÃO ----------------
		subcategoria.setOperacaoEfetuada(operacaoEfetuada);
		subcategoria.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(subcategoria);
		//------------ REGISTRAR TRANSAÇÃO ----------------

		fachada.inserir(subcategoria);

		montarPaginaSucesso(httpServletRequest, "Subcategoria de código "
				+ subcategoria.getCodigo() + " da categoria "
				+ subcategoria.getCategoria().getDescricao()
				+ " inserida com sucesso.", "Inserir outra Subcategoria",
				"exibirInserirSubcategoriaAction.do?menu=sim",
				"exibirAtualizarSubcategoriaAction.do?idRegistroAtualizacao="
						+ subcategoria.getId(),
				"Atualizar Subcategoria Inserida");

		return retorno;
	}
}
