package gcom.gui.cadastro.endereco;

import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.cadastro.endereco.FiltroOSProgramaCalibragem;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.bean.ManterLogradouroHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action para a pré-exibição da página de Manter Logradouro
 * 
 * @author Sávio Luiz
 * @date   28/06/2006 
 */
public class ExibirManterLogradouroAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("manterLogradouro");

		Fachada fachada = Fachada.getInstancia();

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

	
		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Verifica se o filtro foi informado pela página de filtragem de
		// logradouro

		String idMunicipio = (String) pesquisarActionForm.get("idMunicipioFiltro");
		String idBairro = (String) pesquisarActionForm.get("idBairro");
		String nomeLogradouro = (String) pesquisarActionForm
				.get("nomeLogradouro");
		String nomePopularLogradouro = (String) pesquisarActionForm
				.get("nomePopularLogradouro");
		String idLogradouro = (String) pesquisarActionForm.get("idLogradouro");
		String codigoCep = (String) pesquisarActionForm.get("cep");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");
		String tipoPesquisaPopular = (String) pesquisarActionForm.get("tipoPesquisaPopular");
		
		String idTipoLogradouro = null;
		String idTituloLogradouro = null;
		if (pesquisarActionForm.get("idTipo") != null){
			idTipoLogradouro =  (String) pesquisarActionForm.get("idTipo").toString();
		}
		 if (pesquisarActionForm.get("idTitulo") != null){
			 idTituloLogradouro = (String) pesquisarActionForm.get("idTitulo").toString();
		 }
		 
		 
		String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");

		String indicadorImportanciaLogradouro = (String) pesquisarActionForm.get("indicadorImportanciaLogradouro");
		
		if(indicadorImportanciaLogradouro!=null && indicadorImportanciaLogradouro.equals("1")){
			httpServletRequest.setAttribute("indicadorImportanciaLogradouro",
			"true");
		}
		
		
		if (httpServletRequest.getAttribute("filtroLogradouro") != null) {
		} else {
			// Caso o exibirManterLogradouro não tenha passado por algum esquema
			// de filtro,
			// a quantidade de registros é verificada para avaliar a necessidade
			// de filtragem

			if (fachada.registroMaximo(LogradouroCep.class) > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_MANUTENCAO) {
				// Se o limite de registros foi atingido, a página de filtragem
				// é chamada
				retorno = actionMapping.findForward("filtrarLogradouro");
				// limpa os parametros do form pesquisar
				sessao.removeAttribute("PesquisarActionForm");

			}
		}

		// A pesquisa de logradouros só será feita se o forward estiver
		// direcionado
		// para a página de manterLogradouro
		if (retorno.getName().equalsIgnoreCase("manterLogradouro")) {

			// 1º Passo - Pegar o total de registros através de um count da
			// consulta que aparecerá na tela
			
			Integer totalRegistros = fachada.pesquisarLogradouroCompletoCount(
					idMunicipio, idBairro, nomeLogradouro,
					nomePopularLogradouro, idTipoLogradouro,
					idTituloLogradouro, codigoCep, idLogradouro, indicadorUso, tipoPesquisa, tipoPesquisaPopular);
			if (totalRegistros.intValue() <= 0 || totalRegistros == null) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}

			// 2º Passo - Chamar a função de Paginação passando o total de
			// registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);

			// 3º Passo - Obter a coleção da consulta que aparecerá na tela
			// passando o numero de paginas
			// da pesquisa que está no request
			Collection<Logradouro> colecaoLogradouro = fachada.pesquisarLogradouroCompleto(idMunicipio, idBairro, nomeLogradouro,
					nomePopularLogradouro, idTipoLogradouro,
					idTituloLogradouro, codigoCep, idLogradouro, indicadorUso, tipoPesquisa, tipoPesquisaPopular,
					(Integer) httpServletRequest
							.getAttribute("numeroPaginasPesquisa"));
			
			Collection<ManterLogradouroHelper> colecaoParaExibicao = new ArrayList<ManterLogradouroHelper>();
			
			for (Logradouro logradouro : colecaoLogradouro){
				
				ManterLogradouroHelper helper = new ManterLogradouroHelper();
				
				helper.setLogradouro(logradouro);
				
				Collection<Bairro> colecaoBairros = fachada.obterBairrosPorLogradouro(logradouro);
				
				String bairros = "";
				
				if(colecaoBairros!=null && !colecaoBairros.isEmpty()){
					
					for (Bairro bairro : colecaoBairros) {
						
						if(bairros.length() > 40){
							bairros += "\n";
						}
						
						bairros += bairro.getNome()+",";
					}
					
					bairros = Util.removerUltimosCaracteres(bairros, 1);		
				}
				
				helper.setBairro(bairros);
				
				
				if(logradouro.getProgramaCalibragem() != null){
					FiltroOSProgramaCalibragem filtroOSProgramaCalibragem = new FiltroOSProgramaCalibragem();
					filtroOSProgramaCalibragem.adicionarParametro( new ParametroSimples(FiltroOSProgramaCalibragem.ID, logradouro.getProgramaCalibragem().getId()));
					Collection colecCalibragem = fachada.pesquisar(filtroOSProgramaCalibragem, OSProgramacaoCalibragem.class.getName());
					OSProgramacaoCalibragem programaCalibragem = (OSProgramacaoCalibragem) Util.retonarObjetoDeColecao(colecCalibragem);
					
					helper.setGrauImportancia(programaCalibragem.getGrauImportancia());
				}
			
				colecaoParaExibicao.add(helper);
			}


			sessao.setAttribute("logradouros", colecaoParaExibicao);
			

			
			LogradorouRelatorioHelper helperLogradouroRelatorio = new LogradorouRelatorioHelper();
			
			helperLogradouroRelatorio.setIdMunicipio(idMunicipio);
			helperLogradouroRelatorio.setIdBairro(idBairro);
			helperLogradouroRelatorio.setNomePopularLogradouro(nomePopularLogradouro);
			helperLogradouroRelatorio.setIdTipoLogradouro(idTipoLogradouro);
			helperLogradouroRelatorio.setIdTituloLogradouro(idTituloLogradouro);
			helperLogradouroRelatorio.setCodigoCep(codigoCep);
			helperLogradouroRelatorio.setIdLogradouro(idLogradouro);
			helperLogradouroRelatorio.setIndicadorUso(indicadorUso);
			helperLogradouroRelatorio.setTipoPesquisa(tipoPesquisa);
			helperLogradouroRelatorio.setTipoPesquisaPopular(tipoPesquisaPopular);
			helperLogradouroRelatorio.setNomeLogradouro( nomeLogradouro );
			
			pesquisarActionForm.set("helperLogradouroRelatorio",helperLogradouroRelatorio);
			
			String atualizar = (String) sessao.getAttribute("atualizar");

			if (atualizar != null && colecaoLogradouro != null
					&& colecaoLogradouro.size() == 1) {
				retorno = actionMapping.findForward("atualizarLogradouro");
				Logradouro logradouro = (Logradouro) colecaoLogradouro.iterator()
						.next();
				httpServletRequest.setAttribute("idRegistroAtualizacao",
						logradouro.getId());
			}
		}
		return retorno;
	}
}
