package gcom.gui.cadastro;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.CepTipo;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroCepTipo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * [UC0883] Inserir Cep
 * 
 * @author Vinícius Medeiros
 * @date 10/02/2009
 */

public class InserirCepAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um Cep
	 * 
	 * [UC0883] Inserir Cep
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 10/02/2009
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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirCepActionForm inserirCepActionForm = (InserirCepActionForm) actionForm;

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		String codigo = inserirCepActionForm.getCodigo();
		String cepTipo = inserirCepActionForm.getCepTipo();
		String municipioId = inserirCepActionForm.getMunicipioId();
		String bairro = inserirCepActionForm.getBairro();
		String bairroId = inserirCepActionForm.getBairroId();
		String logradouro = inserirCepActionForm.getLogradouro();
		
		Cep cep = new Cep();
		Collection colecaoPesquisa = null;

		// Verifica se o Tipo de CEP foi passado
		if (cepTipo != null
				&& !cepTipo.equalsIgnoreCase("")) {

			FiltroCepTipo filtroCepTipo = new FiltroCepTipo();

			filtroCepTipo.adicionarParametro(new ParametroSimples(
					FiltroCepTipo.ID, cepTipo));
			filtroCepTipo.adicionarParametro(new ParametroSimples(
					FiltroCepTipo.INDICADOR_USO, 
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Tipo de CEP
			colecaoPesquisa = fachada.pesquisar(filtroCepTipo,
					CepTipo.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException(
						"atencao.cep_tipo_inexistente");
			} else {
				CepTipo objetoCepTipo = (CepTipo) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				cep.setCepTipo(objetoCepTipo);
			}
		}
		
		// Verifica se o código foi passado
		if (!"".equals(codigo)) {
			Integer codigoFormatado = new Integer(Util.retirarFormatacaoCEP(inserirCepActionForm.getCodigo()));
			cep.setCodigo(new Integer(codigoFormatado));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Código");
		}
		
		// Verifica se o municipio foi passado
		if (municipioId != null
				&& !municipioId.equalsIgnoreCase("")) {

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, municipioId));
			
			filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Municipio
			colecaoPesquisa = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.municipio_inexistente");
			} else {
				Municipio objetoMunicipio = (Municipio) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				cep.setMunicipio(objetoMunicipio.getNome());
				
				cep.setSigla(objetoMunicipio.getUnidadeFederacao().getSigla());
	        }
		}
		
		// Verifica se o bairro foi passado
		if (bairroId != null
				&& !bairroId.equalsIgnoreCase("")) {

			FiltroBairro filtroBairro = new FiltroBairro();

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, bairroId));
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.NOME,bairro));

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Bairro
			colecaoPesquisa = fachada.pesquisar(filtroBairro,
					Bairro.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.bairro_inexistente");
			} else {
				Bairro objetoBairro = (Bairro) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				cep.setBairro(objetoBairro.getNome());
			}
		}
		
		if(!"".equals(inserirCepActionForm.getLogradouro())) {
			cep.setDescricaoTipoLogradouro(inserirCepActionForm.getLogradouroTipo());
		}
		
		cep.setUltimaAlteracao(new Date());

		cep.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		if(!"".equals(inserirCepActionForm.getLogradouro())) {
			cep.setLogradouro(logradouro);
		}

		FiltroCep filtroCep = new FiltroCep();

		filtroCep.adicionarParametro(
			new ParametroSimples(
				FiltroCep.CODIGO, 
				cep.getCodigo()));
		
		colecaoPesquisa = 
			(Collection) this.getFachada().pesquisar(
				filtroCep, Cep.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			
			// Caso já haja um CEP com o código passado
			throw new ActionServletException("atencao.cep_ja_cadastrado", 
				null,cep.getCodigo().toString());
		} else {

			Integer cepId = (Integer) this.getFachada().inserir(cep);

			montarPaginaSucesso(httpServletRequest,
				"CEP " + codigo + " inserido com sucesso.",
				"Inserir outro CEP",
				"exibirInserirCepAction.do?menu=sim",
				"exibirAtualizarCepAction.do?idRegistroAtualizacao="+ cepId,
				"Atualizar CEP Inserido");

			sessao.removeAttribute("InserirCepActionForm");
			sessao.removeAttribute("colecaoCepTipo");
			sessao.removeAttribute("colecaoLogradouroTipo");
			sessao.removeAttribute("colecaoUnidadeFederacao");
			sessao.removeAttribute("colecaoCep");
			sessao.removeAttribute("colecaoCep2");
			
			
			return retorno;
		}

	}
}
