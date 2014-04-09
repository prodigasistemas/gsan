package gcom.gui.relatorio.cadastro.endereco;

import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.endereco.RelatorioLogradouroPorMunicipio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Wallace Thierre
 * @version 1.0
 */

public class GerarRelatorioLogradouroPorMunicipioAction extends
ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();

		GerarRelatorioLogradouroPorMunicipioActionForm gerarLogradouroPorMunicipioActionForm = (GerarRelatorioLogradouroPorMunicipioActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Recupera a variável para indicar se o usuário apertou o botão de
		// confirmar da tela de
		// confirmação do wizard	

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		TarefaRelatorio relatorio = null;
		// Inicio da parte que vai mandar os parametros para o relatório	

		String idMunicipio = (String) gerarLogradouroPorMunicipioActionForm.getIdMunicipio();

		Municipio municipio = null;

		if (idMunicipio != null && !idMunicipio.trim().equals("")
				&& Integer.parseInt(idMunicipio) > 0) {
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
						"atencao.pesquisa.nenhumresultado", null, "municipio");
			}

		} 
		else{			
			throw new ActionServletException("atencao.required", null, "Município");

		}

		Collection colecaoBairros = (Collection) sessao
		.getAttribute("colecaoBairrosSelecionadosUsuario");
		
		Collection colecaoLogradouroBairroFinal = new ArrayList();

		if (colecaoBairros == null || colecaoBairros.isEmpty()) {

			throw new ActionServletException("atencao.required", null, "Bairro(s)");

		}else{

			Iterator colecaoBairroIterator = colecaoBairros.iterator();

			while(colecaoBairroIterator.hasNext()){

				Bairro bairro = (Bairro) colecaoBairroIterator.next();

				FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();

				filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_BAIRRO, bairro.getId()));

				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.BAIRRO);

				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO);
				
				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO_TIPO);
				
				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouroBairro.LOGRADOURO_TITULO);

				filtroLogradouroBairro.setCampoOrderBy(FiltroLogradouroBairro.NOME_BAIRRO);
				
				filtroLogradouroBairro.setCampoOrderBy(FiltroLogradouroBairro.NOME_LOGRADOURO);
				
				filtroLogradouroBairro.setCampoOrderBy(FiltroLogradouroBairro.LOGRADOUROTIPO_DESCRICAO);

				filtroLogradouroBairro.setCampoOrderBy(FiltroLogradouroBairro.LOGRADOUROTITULO_DESCRICAO);
			
				
				Collection colecaoLogradouroBairro = 
					fachada.pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());

				if ( colecaoLogradouroBairro != null && !colecaoLogradouroBairro.isEmpty() ){

					Iterator colecaoLogradouroBairroIte = colecaoLogradouroBairro.iterator();

					while ( colecaoLogradouroBairroIte.hasNext() ){

						LogradouroBairro logradouroBairro = (LogradouroBairro) colecaoLogradouroBairroIte.next();						

						colecaoLogradouroBairroFinal.add(logradouroBairro);						
					}			

				}			

			}

		}

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		Collections.sort((java.util.List<LogradouroBairro>) colecaoLogradouroBairroFinal, new Comparator() {
			public int compare(Object a, Object b) {
				String nomeBairro1 = ((LogradouroBairro) a)
						.getBairro().getNome();
				String nomeBairro2 = ((LogradouroBairro) b)
						.getBairro().getNome();

				return nomeBairro1.compareTo(nomeBairro2);
			}
		});

		relatorio = new RelatorioLogradouroPorMunicipio( (Usuario)( httpServletRequest.getSession(false)).getAttribute("usuarioLogado") );

		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt( tipoRelatorio ) );
		relatorio.addParametro("colecaoBairrosSelecionadosUsuario",  colecaoBairros );
		relatorio.addParametro("colecaoLogradourosBairro", colecaoLogradouroBairroFinal );
		relatorio.addParametro("nomeMunicipio", municipio.getNome());


		try {	
			retorno = 
				processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.nao.existe.dados_relatorio_anomesreferencia", null, "");
		}

		return retorno;        
	}

}
