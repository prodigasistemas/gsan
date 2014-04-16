package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 30 de Dezembro de 2005
 */
public class AtualizarCategoriaAction extends GcomAction {
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
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		CategoriaActionForm categoriaActionForm = (CategoriaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_CATEGORIA_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_CATEGORIA_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança

		Categoria categoria = (Categoria) sessao.getAttribute("categoria");

		// Pegando os dados do Formulário
		String descricao = (String) categoriaActionForm.getDescricao();
		String descricaoAbreviada = (String) categoriaActionForm
				.getDescricaoAbreviada();
		Integer codigoCategoria = new Integer(categoriaActionForm
				.getIdCategoria());
		Integer consumoMinimo = new Integer(categoriaActionForm
				.getConsumoMinimo());
		Integer consumoEstouro = new Integer(categoriaActionForm
				.getConsumoEstouro());
		BigDecimal vezesMediaEstouro = new BigDecimal(categoriaActionForm
				.getVezesMediaEstouro().replace(",", "."));
		Integer mediaBaixoConsumo = new Integer(categoriaActionForm
				.getMediaBaixoConsumo());
		BigDecimal porcentagemMediaBaixoConsumo = new BigDecimal(
				categoriaActionForm.getPorcentagemMediaBaixoConsumo().replace(
						",", "."));
		Integer consumoAlto = new Integer(categoriaActionForm.getConsumoAlto());
		BigDecimal vezesMediaAltoConsumo = new BigDecimal(categoriaActionForm
				.getVezesMediaAltoConsumo().replace(",", "."));
		Short indicadorDeUso = new Short(categoriaActionForm.getIndicadorUso());

		// Seta os campos para serem atualizados
		categoria.setId(codigoCategoria);
		categoria.setDescricao(descricao);
		categoria.setDescricaoAbreviada(descricaoAbreviada);
		categoria.setConsumoMinimo(consumoMinimo);
		categoria.setConsumoEstouro(consumoEstouro);
		categoria.setVezesMediaEstouro(vezesMediaEstouro);
		categoria.setMediaBaixoConsumo(mediaBaixoConsumo);
		categoria.setPorcentagemMediaBaixoConsumo(porcentagemMediaBaixoConsumo);
		categoria.setVezesMediaAltoConsumo(vezesMediaAltoConsumo);
		categoria.setConsumoAlto(consumoAlto);
		categoria.setIndicadorUso(indicadorDeUso);

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		categoria.setOperacaoEfetuada(operacaoEfetuada);
		categoria.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(categoria);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		// Atualiza a Categoria - As validações estão no Controlador
		fachada.atualizarCategoria(categoria);

		montarPaginaSucesso(httpServletRequest, "Categoria de código "
				+ categoria.getId() + " atualizada com sucesso.",
				"Realizar outra Manutenção de Categoria",
				"exibirManterCategoriaAction.do?menu=sim");
		return retorno;
	}
}
