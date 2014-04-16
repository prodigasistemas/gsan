package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
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
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela atualização da subcategoria
 * 
 * [UC0059] Atualizar Subcategoria
 * 
 * @author Fernanda Paiva
 * @date 4/01/2006
 */
public class AtualizarSubcategoriaAction extends GcomAction {
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

		FiltrarSubcategoriaActionForm filtrarSubcategoriaActionForm = (FiltrarSubcategoriaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_SUBCATEGORIA_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_SUBCATEGORIA_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		Fachada fachada = Fachada.getInstancia();

		Subcategoria subcategoria = (Subcategoria) sessao
				.getAttribute("subcategoria");

		String codigoSubcategoria = filtrarSubcategoriaActionForm
				.getCodigoSubcategoria();

		String idCategoria = (String) filtrarSubcategoriaActionForm
				.getIdCategoria();

		Short indicadorDeUso = new Short(filtrarSubcategoriaActionForm
				.getIndicadorUso());
		
		String descricaoAbreviada = filtrarSubcategoriaActionForm.getDescricaoAbreviada();
        String  numeroFatorFiscalizacao =  filtrarSubcategoriaActionForm.getNumeroFatorFiscalizacao() ;
        String indicadorSazonalidade = filtrarSubcategoriaActionForm.getIndicadorSazonalidade();
        
        Short  indicadorTarifaConsumo = null;
        
        if(filtrarSubcategoriaActionForm.getIndicadorTarifaConsumo() != null && !filtrarSubcategoriaActionForm.getIndicadorTarifaConsumo().equalsIgnoreCase("")){

        	indicadorTarifaConsumo =  new Short(filtrarSubcategoriaActionForm.getIndicadorTarifaConsumo()) ;
        }
        
        String codigoTarifaSocial = null;
		
        if ( filtrarSubcategoriaActionForm.getCodigoTarifaSocial() != null && !filtrarSubcategoriaActionForm.getCodigoTarifaSocial().equalsIgnoreCase("")){
        
			codigoTarifaSocial = filtrarSubcategoriaActionForm.getCodigoTarifaSocial();
		}
        Integer codigoGrupoSubcategoria = null; 
        
        if(filtrarSubcategoriaActionForm.getCodigoGrupoSubcategoria() != null && !filtrarSubcategoriaActionForm.getCodigoGrupoSubcategoria().equalsIgnoreCase("")){
        
        	codigoGrupoSubcategoria = new Integer(filtrarSubcategoriaActionForm.getCodigoGrupoSubcategoria());
        }

		Categoria categoria = null;

		if (idCategoria != null && !idCategoria.equals("")) {
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.CODIGO, idCategoria));
			filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection categorias = fachada.pesquisar(filtroCategoria,
					Categoria.class.getName());

			if (categorias != null && !categorias.isEmpty()) {
				// A categoria foi encontrada
				Iterator categoriaIterator = categorias.iterator();

				categoria = (Categoria) categoriaIterator.next();

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Categoria");
			}

		}
		// seta os campos para serem atualizados
		subcategoria.setId(new Integer(filtrarSubcategoriaActionForm
				.getIdSubcategoria()));
		subcategoria.setCategoria(categoria);
		subcategoria.setCodigo(Integer.parseInt(codigoSubcategoria));
		
		subcategoria.setDescricao(filtrarSubcategoriaActionForm
				.getDescricaoSubcategoria());
		subcategoria.setIndicadorUso(indicadorDeUso);
		subcategoria.setDescricaoAbreviada(descricaoAbreviada);
		subcategoria.setCodigoTarifaSocial(codigoTarifaSocial);
		subcategoria.setCodigoGrupoSubcategoria(codigoGrupoSubcategoria);
		subcategoria.setNumeroFatorFiscalizacao(new Short(numeroFatorFiscalizacao));
		subcategoria.setIndicadorTarifaConsumo(indicadorTarifaConsumo);
		subcategoria.setIndicadorSazonalidade(new Short(indicadorSazonalidade));
		
		
		// ------------ REGISTRAR TRANSAÇÃO ----------------
		subcategoria.setOperacaoEfetuada(operacaoEfetuada);
		subcategoria.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(subcategoria);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		Subcategoria subcategoriaVelha = (Subcategoria) sessao
				.getAttribute("subcategoria");

		fachada.atualizarSubcategoria(subcategoria, subcategoriaVelha);

		montarPaginaSucesso(httpServletRequest, "Subcategoria de código "
				+ subcategoria.getCodigo() + " da categoria "
				+ subcategoria.getCategoria().getDescricao()
				+ " atualizada com sucesso.",
				"Realizar outra Manutenção de Subcategoria",
				"exibirManterSubcategoriaAction.do?menu=sim");

		return retorno;
	}

}
