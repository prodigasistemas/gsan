package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

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
 * Description of the Class
 * 
 * @author compesa
 * @created 29 de Junho de 2004
 */
public class InserirBairroAction extends GcomAction {
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

		BairroActionForm bairroActionForm = (BairroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		/*
		 * [UC0107] Registrar Transação
		 * 
		 */

//		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
//				Operacao.OPERACAO_BAIRRO_INSERIR,
//				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
//						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
//
//		Operacao operacao = new Operacao();
//		operacao.setId(Operacao.OPERACAO_BAIRRO_INSERIR);
//
//		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
//		operacaoEfetuada.setOperacao(operacao);

		// [UC0107] -Fim- Registrar Transação
		
		Collection colecaoBairroArea = null;
		
		if (sessao.getAttribute("colecaoBairroArea") != null){
			colecaoBairroArea = (Collection)sessao.getAttribute("colecaoBairroArea");
		}

		String idMunicipio = (String) bairroActionForm.getIdMunicipio();

		Municipio municipio = null;

		if (idMunicipio != null && !idMunicipio.equals("")) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));
			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipios = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipios != null && !municipios.isEmpty()) {
				// O municipio foi encontrado
				Iterator municipioIterator = municipios.iterator();

				municipio = (Municipio) municipioIterator.next();

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Município");
			}

		}

		int codigoBairro = 0;

		String codigoBairroPesquisar = (String) bairroActionForm
				.getCodigoBairro();

		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.CODIGO, codigoBairroPesquisar));

		filtroBairro.adicionarParametro(new ParametroSimples(
				FiltroBairro.MUNICIPIO_ID, idMunicipio));
		filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");

		Collection bairros = fachada.pesquisar(filtroBairro, Bairro.class
				.getName());
		if (bairros != null && !bairros.isEmpty()) {
			Bairro bairro = (Bairro) bairros.iterator().next();
			throw new ActionServletException(
					"atencao.pesquisa_bairro_ja_cadastrada",codigoBairroPesquisar, bairro.getMunicipio().getNome());
		} else {

			codigoBairro = Integer.parseInt(codigoBairroPesquisar);
		}

		Integer codigoBairroPrefeitura = null;
		if (bairroActionForm.getCodigoBairroPrefeitura() != null
				&& !bairroActionForm.getCodigoBairroPrefeitura()
						.equalsIgnoreCase("")) {
			codigoBairroPrefeitura = new Integer(bairroActionForm
					.getCodigoBairroPrefeitura());
		}

		Short indicadorDeUso = ConstantesSistema.INDICADOR_USO_ATIVO;

		// cria o objeto bairro para ser inserido
		Bairro bairro = new Bairro(codigoBairro, bairroActionForm
				.getNomeBairro(), codigoBairroPrefeitura, indicadorDeUso,
				new Date(), municipio);

		// verifica se o bairro ja existe no cadastro - Tiago Moreno
		FiltroBairro filtroBairroExistente = new FiltroBairro();
		filtroBairroExistente.adicionarParametro(new ParametroSimples(
				FiltroBairro.MUNICIPIO_ID, bairro.getMunicipio().getId()));
		filtroBairroExistente.adicionarParametro(new ParametroSimples(
				FiltroBairro.NOME, bairro.getNome()));
		filtroBairroExistente
				.adicionarCaminhoParaCarregamentoEntidade("municipio");
		Collection collectionBairro = (Collection) fachada.pesquisar(
				filtroBairroExistente, Bairro.class.getName());

		if (collectionBairro != null && !collectionBairro.isEmpty()) {
			throw new ActionServletException(
					"atencao.bairro_existente_municipio");
		}
				
		if (colecaoBairroArea == null || colecaoBairroArea.isEmpty()){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Áreas do Bairro");
		}
		

		Integer codigoBairroInserido = (Integer) fachada.inserirBairro(bairro,colecaoBairroArea,usuarioLogado);

		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest, "Bairro de código "
				+ bairro.getCodigo() + " do município "
				+ bairro.getMunicipio().getNome() + " inserido com sucesso.",
				"Inserir outro Bairro", "exibirInserirBairroAction.do?limparForm=ok",
				"exibirAtualizarBairroAction.do?idRegistroAtualizacao="
						+ codigoBairroInserido, "Atualizar Bairro Inserido");

		return retorno;
	}
}
