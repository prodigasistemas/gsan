package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.CategoriaTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 22 de Dezembro de 2005
 */
public class InserirCategoriaAction extends GcomAction {
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

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_CATEGORIA_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		String tipoCategoria = (String) categoriaActionForm.getTipoCategoria();
	
		// Tipo de Categoria
		CategoriaTipo categoriaTipo = new CategoriaTipo();
		categoriaTipo.setId(new Integer(tipoCategoria));

		/*
		 * Categoria categoria = new Categoria(descricao, descricaoAbreviada,
		 * consumoMinimo, consumoEstouro, vezesMediaEstouro, mediaBaixoConsumo,
		 * porcentagemMediaBaixoConsumo, consumoAlto, vezesMediaAltoConsumo,
		 * indicadorDeUso, new Date(), categoriaTipo);
		 * 
		 * 
		 * //------------ REGISTRAR TRANSAÇÃO ----------------
		 * categoria.setOperacaoEfetuada(operacaoEfetuada);
		 * categoria.adicionarUsuario(Usuario.USUARIO_TESTE,
		 * UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		 * registradorOperacao.registrarOperacao(categoria); //------------
		 * REGISTRAR TRANSAÇÃO ----------------
		 *  // Insere a Categoria - As validações estão no Controlador Integer
		 * idCategoriaInserida = (Integer)fachada.inserirCategoria(categoria);
		 * 
		 * montarPaginaSucesso(httpServletRequest, "Categoria de código " +
		 * idCategoriaInserida + " inserida com sucesso.", "Inserir outra
		 * Categoria", "exibirInserirCategoriaAction.do",
		 * "exibirAtualizarCategoriaAction.do?idRegistroAtualizacao=" +
		 * idCategoriaInserida, "Atualizar Categoria Inserida");
		 */
		return retorno;
	}
}
