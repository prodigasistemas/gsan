package gcom.gui.relatorio.cadastro.endereco;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1063] Gerar Relatório de Logradouros por Municipio
 * 
 * @author Wallace Thierre
 * @date 06/09/2010
 */
public class ExibirGerarRelatorioLogradourosPorMunicipioAction extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
		.findForward("exibirGerarRelatorioLogradourosPorMunicipio");	

		Fachada fachada = Fachada.getInstancia();

		//Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		GerarRelatorioLogradouroPorMunicipioActionForm gerarLogradouroActionForm = (GerarRelatorioLogradouroPorMunicipioActionForm) actionForm;


		/*
		 * Removendo o bairro selecionado da sessão
		 */
		if(httpServletRequest.getParameter("remover") != null 
				&& httpServletRequest.getParameter("remover").equals("s")){

			String idBairro = httpServletRequest.getParameter("idBairro");

			if (idBairro != null && !idBairro.equals("") &&
					sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null){

				Collection colecaoBairrosSelecionadosUsuario = (Collection) sessao
				.getAttribute("colecaoBairrosSelecionadosUsuario");

				Iterator colecaoBairrosSelecionadosUsuarioIterator;

				Bairro bairroInserir;

				colecaoBairrosSelecionadosUsuarioIterator = colecaoBairrosSelecionadosUsuario
				.iterator();

				while (colecaoBairrosSelecionadosUsuarioIterator.hasNext()) {

					bairroInserir = (Bairro) colecaoBairrosSelecionadosUsuarioIterator
					.next();

					if (bairroInserir.getId().equals(new Integer(idBairro))) {

						colecaoBairrosSelecionadosUsuario.remove(bairroInserir);
						break;
					}
				}

				if (colecaoBairrosSelecionadosUsuario.isEmpty()){
					gerarLogradouroActionForm.setColecaoBairro("");
				}
			}

			gerarLogradouroActionForm.setIdBairro("");
			gerarLogradouroActionForm.setNomeBairro("");

			return retorno;
		}



		gerarLogradouroActionForm.setColecaoBairro("");   

		if (sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null){

			Collection colecaoBairros = (List) sessao
			.getAttribute("colecaoBairrosSelecionadosUsuario");

			if (!colecaoBairros.isEmpty()){
				gerarLogradouroActionForm.setColecaoBairro("CARREGADO");
			}
			else{
				gerarLogradouroActionForm.setColecaoBairro("");
			}

		}

		//-------Parte que trata do código quando o usuário tecla enter
		//caso seja o id do municipio
		String idDigitadoEnterMunicipio = (String) gerarLogradouroActionForm
		.getIdMunicipio();
		String codigoDigitadoEnterBairro = (String) gerarLogradouroActionForm
		.getIdBairro();


		/*
		 * Removendo toda a coleção de bairro da sessão
		 */
		String removerColecaoBairro = httpServletRequest.getParameter("removerColecaoBairro");

		if (removerColecaoBairro != null && !removerColecaoBairro.equals("")){

			sessao.removeAttribute("colecaoBairrosSelecionadosUsuario");
			gerarLogradouroActionForm.setColecaoBairro("");
		}

		//Verifica se o código foi digitado
		if (idDigitadoEnterMunicipio != null
				&& !idDigitadoEnterMunicipio.trim().equals("")
				&& !Util.validarValorNaoNumerico(idDigitadoEnterMunicipio)) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idDigitadoEnterMunicipio));
			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				//O municipio foi encontrado
				gerarLogradouroActionForm
				.setIdMunicipio(((Municipio) ((List) municipioEncontrado)
						.get(0)).getId().toString());
				gerarLogradouroActionForm
				.setNomeMunicipio(((Municipio) ((List) municipioEncontrado)
						.get(0)).getNome());
			}else{		
				httpServletRequest.setAttribute("nomeCampo",
				"codigoBairro");

				httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
				"true");

				gerarLogradouroActionForm.setIdMunicipio("");
				httpServletRequest.setAttribute("nomeCampo",
				"idMunicipio");
				httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
				"exception");
				gerarLogradouroActionForm.setNomeMunicipio("Município inexistente");
			}
		}

		//Verifica se o código foi digitado
		if (codigoDigitadoEnterBairro != null
				&& !codigoDigitadoEnterBairro.trim().equals("")
				&& !Util.validarValorNaoNumerico(codigoDigitadoEnterBairro)) {
			FiltroBairro filtroBairro = new FiltroBairro();

			filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");

			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.CODIGO, codigoDigitadoEnterBairro));
			filtroBairro.adicionarParametro(new ParametroSimples(
					FiltroBairro.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			// verifica se o bairro pesquisado é de um municipio existente
			if (idDigitadoEnterMunicipio != null
					&& !idDigitadoEnterMunicipio.trim().equals("")
					&& Integer.parseInt(idDigitadoEnterMunicipio) > 0) {

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.MUNICIPIO_ID, idDigitadoEnterMunicipio));
			}

			Collection bairroEncontrado = fachada.pesquisar(filtroBairro,
					Bairro.class.getName());

			if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {

				//O bairro foi encontrado
				Bairro objetoBairroEncontrado = (Bairro) Util.retonarObjetoDeColecao(bairroEncontrado);

				gerarLogradouroActionForm.setIdBairro(String.valueOf(objetoBairroEncontrado.getCodigo()));
				gerarLogradouroActionForm.setNomeBairro(objetoBairroEncontrado.getNome());

				httpServletRequest.setAttribute("nomeCampo", "botaoAdicionarBairro");

				httpServletRequest.setAttribute("idBairroNaoEncontrado", "true");

				/*
				 * Adicionado o novo BAIRRO na coleção
				 */
				String adicionarBairroColecao = httpServletRequest.getParameter("adicionarBairroColecao");

				if (adicionarBairroColecao != null && !adicionarBairroColecao.equals("")){

					gerarLogradouroActionForm.setIdBairro("");
					gerarLogradouroActionForm.setNomeBairro("");

					List colecaoBairrosSelecionadosUsuario = new ArrayList();                	
					if (sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null){

						colecaoBairrosSelecionadosUsuario = (List) sessao
						.getAttribute("colecaoBairrosSelecionadosUsuario");

						if (!colecaoBairrosSelecionadosUsuario.contains((Bairro) ((List) bairroEncontrado).get(0))){
							colecaoBairrosSelecionadosUsuario.add((Bairro) ((List) bairroEncontrado).get(0));
							gerarLogradouroActionForm.setColecaoBairro("CARREGADO");
						}
						else{
							throw new ActionServletException(
									"atencao.objeto_ja_selecionado", null, "Bairro");
						}
					}
					else{
						colecaoBairrosSelecionadosUsuario.add((Bairro) ((List) bairroEncontrado).get(0));
						sessao.setAttribute("colecaoBairrosSelecionadosUsuario", colecaoBairrosSelecionadosUsuario);
						gerarLogradouroActionForm.setColecaoBairro("CARREGADO");
					}
				}

			} else {
				gerarLogradouroActionForm.setIdBairro("");
				gerarLogradouroActionForm.setNomeBairro("");
				httpServletRequest.setAttribute("nomeCampo",
				"codigoBairro");
				httpServletRequest.setAttribute("idBairroNaoEncontrado",
				"exception");
				gerarLogradouroActionForm.setNomeBairro("Bairro inexistente");

			}

		}

		//fim da parte da pesquisa do enter        



		return retorno;
	}

}
