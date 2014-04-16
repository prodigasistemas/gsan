package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0443] Pesquisar Registro Atendimento - Exibir
 * 
 * @author Leonardo Regis 
 * @date 12/08/2006
 */
public class ExibirPesquisarRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("registroAtendimentoPesquisar");
		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		// Form
		PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm = (PesquisarRegistroAtendimentoActionForm) actionForm;
		// Reseta Pesquisa
		if (pesquisarRegistroAtendimentoActionForm.getResetarPesquisaRA().equalsIgnoreCase("true")) {
			pesquisarRegistroAtendimentoActionForm.resetRA();
		}
		//set o período de atendimento
		if(httpServletRequest.getParameter("validaImovel") == null && 
				httpServletRequest.getParameter("validaUnidadeAtendimento") == null &&
				httpServletRequest.getParameter("validaUnidadeAtual") == null &&
				httpServletRequest.getParameter("validaUnidadeSuperior") == null &&
				httpServletRequest.getParameter("validaMunicipio") == null &&
				httpServletRequest.getParameter("validaBairro") == null &&
				httpServletRequest.getParameter("validaLogradouro") == null &&
				httpServletRequest.getParameter("validaEspecificacao") == null){
			//Sugerindo um período para realização da filtragem de um RA
			Integer qtdDias = 30;
			
			Date dataAtual = new Date();
			Date dataSugestao = Util.subtrairNumeroDiasDeUmaData(dataAtual, qtdDias);
			
			pesquisarRegistroAtendimentoActionForm.setPeriodoAtendimentoInicial(Util.formatarData(dataSugestao));
			pesquisarRegistroAtendimentoActionForm.setPeriodoAtendimentoFinal(Util.formatarData(dataAtual));			
		}
		// Matrícula
		if (pesquisarRegistroAtendimentoActionForm.getMatriculaImovel() != null &&
				!pesquisarRegistroAtendimentoActionForm.getMatriculaImovel().equals("")) {
			getImovel(pesquisarRegistroAtendimentoActionForm, httpServletRequest);
		}
		
		//Usuário
		if (pesquisarRegistroAtendimentoActionForm.getLoginUsuario() != null &&
			!pesquisarRegistroAtendimentoActionForm.getLoginUsuario().equals("")) {
			
			getUsuario(pesquisarRegistroAtendimentoActionForm, fachada,
					pesquisarRegistroAtendimentoActionForm.getLoginUsuario(), sessao);
		}		
		
		// Tipo Solicitação
		getSolicitacaoTipoCollection(sessao, fachada);
		// Especificação
		if (pesquisarRegistroAtendimentoActionForm.getTipoSolicitacao() != null &&
				pesquisarRegistroAtendimentoActionForm.getTipoSolicitacao().length > 0) {
			getSolicitacaoTipoEspecificacaoCollection(sessao, fachada, pesquisarRegistroAtendimentoActionForm);
		} else {
			pesquisarRegistroAtendimentoActionForm.setSelectedTipoSolicitacaoSize("0");
		}
		// Unidade de Atendimento
	    if (pesquisarRegistroAtendimentoActionForm.getUnidadeAtendimentoId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getUnidadeAtendimentoId().equals("")) {
			getUnidade(pesquisarRegistroAtendimentoActionForm, httpServletRequest, 1);
		}
	    // Unidade Atual
		if (pesquisarRegistroAtendimentoActionForm.getUnidadeAtualId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getUnidadeAtualId().equals("")) {
			getUnidade(pesquisarRegistroAtendimentoActionForm, httpServletRequest, 2);
		}
		// Unidade Superior
		if (pesquisarRegistroAtendimentoActionForm.getUnidadeSuperiorId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getUnidadeSuperiorId().equals("")) {
			getUnidade(pesquisarRegistroAtendimentoActionForm, httpServletRequest, 3);
		}
		// Município
		if (pesquisarRegistroAtendimentoActionForm.getMunicipioId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getMunicipioId().equals("")) {
			getMunicipio(pesquisarRegistroAtendimentoActionForm, httpServletRequest);
		}
		// Bairro & Área do Bairro
		if (pesquisarRegistroAtendimentoActionForm.getBairroId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getBairroId().equals("")) {
			// [FS009] Verificar informação do município
			if (pesquisarRegistroAtendimentoActionForm.getMunicipioId() != null &&
					!pesquisarRegistroAtendimentoActionForm.getMunicipioId().equals("")) {
				getBairro(pesquisarRegistroAtendimentoActionForm, httpServletRequest);
			} else {
				throw new ActionServletException("atencao.filtrar_informar_municipio");
			}
		}
		// Logradouro
		if (pesquisarRegistroAtendimentoActionForm.getLogradouroId() != null &&
				!pesquisarRegistroAtendimentoActionForm.getLogradouroId().equals("")) {
			getLogradouro(pesquisarRegistroAtendimentoActionForm, httpServletRequest);
		}
		
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaRegistroAtendimento") != null) {
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaRegistroAtendimento",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaRegistroAtendimento"));
			
		}
		
		
		//Pesquisar PopUps
		
		//Imovel
		if (httpServletRequest.getParameter("pesquisarImovel") != null){
			retorno = actionMapping.findForward("pesquisarImovel");
		}
		
		//Unidade Atendimento
		if (httpServletRequest.getParameter("pesquisarUnidadeAtendimento") != null){
			retorno = actionMapping.findForward("pesquisarUnidadeAtendimento");
		}
		
		//Unidade Atual
		if (httpServletRequest.getParameter("pesquisarUnidadeAtual") != null){
			retorno = actionMapping.findForward("pesquisarUnidadeAtual");
		}
		
		//Municipio
		if (httpServletRequest.getParameter("pesquisarMunicipio") != null){
			retorno = actionMapping.findForward("pesquisarMunicipio");
		}
		
		
		//Retorno de PopUps
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null &&
			httpServletRequest.getParameter("descricaoCampoEnviarDados") != null &&
			httpServletRequest.getParameter("tipoConsulta") != null){
			
			String idCampoEnviarDados = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricaoCampoEnviarDados = httpServletRequest.getParameter("descricaoCampoEnviarDados");
			String tipoConsulta = httpServletRequest.getParameter("tipoConsulta");
			
			if (tipoConsulta.equalsIgnoreCase("Municipio")){
				
				pesquisarRegistroAtendimentoActionForm.setMunicipioId(idCampoEnviarDados);
				pesquisarRegistroAtendimentoActionForm.setMunicipioDescricao(descricaoCampoEnviarDados);
				
				sessao.setAttribute("municipioEncontrada", "true");
			}
			
			if (tipoConsulta.equalsIgnoreCase("Bairro")){
				
				pesquisarRegistroAtendimentoActionForm.setBairroCodigo(idCampoEnviarDados);
				getBairro(pesquisarRegistroAtendimentoActionForm, httpServletRequest);
				
			}
			
			if (tipoConsulta.equalsIgnoreCase("Logradouro")){
				
				pesquisarRegistroAtendimentoActionForm.setLogradouroId(idCampoEnviarDados);
				pesquisarRegistroAtendimentoActionForm.setLogradouroDescricao(descricaoCampoEnviarDados);
				
				sessao.setAttribute("logradouroEncontrada", "true");
				
			}
			
			if (tipoConsulta.equalsIgnoreCase("Imovel")){
				
				pesquisarRegistroAtendimentoActionForm.setMatriculaImovel(idCampoEnviarDados);
				pesquisarRegistroAtendimentoActionForm.setInscricaoImovel(descricaoCampoEnviarDados);
				
				sessao.setAttribute("inscricaoImovelEncontrada", "true");
				
			}
			
			if (tipoConsulta.equalsIgnoreCase("unidadeAtendimento")){
				
				pesquisarRegistroAtendimentoActionForm.setUnidadeAtendimentoId(idCampoEnviarDados);
				pesquisarRegistroAtendimentoActionForm.setUnidadeAtendimentoDescricao(descricaoCampoEnviarDados);
				
				sessao.setAttribute("unidadeAtendimentoEncontrada", "true");
				
			}
			
			if (tipoConsulta.equalsIgnoreCase("unidadeAtual")){
				
				pesquisarRegistroAtendimentoActionForm.setUnidadeAtualId(idCampoEnviarDados);
				pesquisarRegistroAtendimentoActionForm.setUnidadeAtualDescricao(descricaoCampoEnviarDados);
				
				sessao.setAttribute("unidadeAtualEncontrada", "true");
				
			}
			
		}

		return retorno;
	}
	
	/**
	 * Recupera Imóvel 
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param pesquisarRegistroAtendimentoActionForm
	 * @param fachada
	 */
	private void getImovel(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0001] Valida Imovel
		//if (isValidateObject(filtrarRegistroAtendimentoActionForm)) {
			// Filtra Imovel
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, 
					pesquisarRegistroAtendimentoActionForm.getMatriculaImovel()));
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			// Recupera Imóvel
			Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
				sessao.setAttribute("inscricaoImovelEncontrada", "true");
				Imovel imovel = colecaoImovel.iterator().next();
				pesquisarRegistroAtendimentoActionForm.setInscricaoImovel(imovel.getInscricaoFormatada());
			} else {
				sessao.removeAttribute("inscricaoImovelEncontrada");
				pesquisarRegistroAtendimentoActionForm.setMatriculaImovel("");
				pesquisarRegistroAtendimentoActionForm.setInscricaoImovel("Matrícula inexistente");
			}
			pesquisarRegistroAtendimentoActionForm.setValidaImovel("false");
		//}
	}
	/**
	 * Carrega coleção de solicitação tipo
	 *
	 * @author Leonardo Regis
	 * @date 03/08/2006
	 *
	 * @param sessao
	 * @param fachada
	 */
	private void getSolicitacaoTipoCollection(HttpSession sessao, Fachada fachada) {
		// Filtra Solicitação Tipo
		FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
		filtroSolicitacaoTipo.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);

		Collection<SolicitacaoTipo> colecaoSolicitacaoTipo = fachada.pesquisar(filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
		if (colecaoSolicitacaoTipo != null && !colecaoSolicitacaoTipo.isEmpty()) {
			sessao.setAttribute("colecaoTipoSolicitacao",	colecaoSolicitacaoTipo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Especificação");
		}
	}	
	/**
	 * Carrega coleção de solicitação tipo especificação.
	 *
	 * @author Leonardo Regis
	 * @date 03/08/2006
	 *
	 * @param sessao
	 * @param fachada
	 */
	private void getSolicitacaoTipoEspecificacaoCollection(HttpSession sessao, Fachada fachada, PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm) {
		String[] solicitacaoTipo = pesquisarRegistroAtendimentoActionForm.getTipoSolicitacao();
		pesquisarRegistroAtendimentoActionForm.setSelectedTipoSolicitacaoSize(solicitacaoTipo.length+"");
		if (solicitacaoTipo.length == 1) {
			// Filtra Solicitação Tipo Especificação
			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID, solicitacaoTipo[0]));
			filtroSolicitacaoTipoEspecificacao.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);

			Collection<SolicitacaoTipoEspecificacao> colecaoSolicitacaoTipoEspecificacao = fachada.pesquisar( filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			if (colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
				sessao.setAttribute("colecaoEspecificacao",	colecaoSolicitacaoTipoEspecificacao);
			} else {
				sessao.setAttribute("colecaoEspecificacao",	new ArrayList<SolicitacaoTipoEspecificacao>());
			}
			
		} else {
			sessao.setAttribute("colecaoEspecificacao",	new ArrayList<SolicitacaoTipoEspecificacao>());
		}
	}	
	/**
	 * Recupera Unidade
	 *
	 * [FS007] Verificar existência de unidades subordinadas
	 * 
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param pesquisarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param tipo
	 */
	private void getUnidade(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest, int tipo) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0006] Valida Unidade Atendimento
		//if (isValidateObject(filtrarRegistroAtendimentoActionForm)) {
			String unidadeId = "";
			switch (tipo) {
			case 1:
				unidadeId = pesquisarRegistroAtendimentoActionForm.getUnidadeAtendimentoId();
				pesquisarRegistroAtendimentoActionForm.setUnidadeAtendimentoDescricao(getUnidadeDescricao(pesquisarRegistroAtendimentoActionForm, fachada, unidadeId));
				if(pesquisarRegistroAtendimentoActionForm.getUnidadeAtendimentoDescricao().equalsIgnoreCase("Unidade Inexistente")) {
					sessao.removeAttribute("unidadeAtendimentoEncontrada");
					pesquisarRegistroAtendimentoActionForm.setUnidadeAtendimentoId("");
				} else {
					sessao.setAttribute("unidadeAtendimentoEncontrada","true");
					pesquisarRegistroAtendimentoActionForm.setValidaUnidadeAtendimento("false");
				}
				break;
			case 2:
				unidadeId = pesquisarRegistroAtendimentoActionForm.getUnidadeAtualId();
				pesquisarRegistroAtendimentoActionForm.setUnidadeAtualDescricao(getUnidadeDescricao(pesquisarRegistroAtendimentoActionForm, fachada, unidadeId));				
				if (pesquisarRegistroAtendimentoActionForm.getUnidadeAtualDescricao().equalsIgnoreCase("Unidade Inexistente")) {
					sessao.removeAttribute("unidadeAtualEncontrada");
					pesquisarRegistroAtendimentoActionForm.setUnidadeAtualId("");
				} else {
					sessao.setAttribute("unidadeAtualEncontrada","true");
					pesquisarRegistroAtendimentoActionForm.setValidaUnidadeAtual("false");
				}
				break;
			case 3:
				unidadeId = pesquisarRegistroAtendimentoActionForm.getUnidadeSuperiorId();
				UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
				unidadeOrganizacional.setId(new Integer(unidadeId));
				pesquisarRegistroAtendimentoActionForm.setUnidadeSuperiorDescricao(getUnidadeDescricao(pesquisarRegistroAtendimentoActionForm, fachada, unidadeId));
				if (pesquisarRegistroAtendimentoActionForm.getUnidadeSuperiorDescricao().equalsIgnoreCase("Unidade Inexistente")) {
					sessao.removeAttribute("unidadeSuperiorEncontrada");
					pesquisarRegistroAtendimentoActionForm.setUnidadeSuperiorId("");
				} else {
					// [FS007] Verificar existência de unidades subordinadas
					fachada.verificarExistenciaUnidadesSubordinadas(unidadeOrganizacional);
					sessao.setAttribute("unidadeSuperiorEncontrada","true");
					pesquisarRegistroAtendimentoActionForm.setValidaUnidadeSuperior("false");
				}
				break;
			default:
				break;
			}
		//}
	}
	/**
	 * Recupera a Unidade Organizacional (Atendimento, Atual e Superior)
	 *
	 * [F0006] Valida Unidade
	 * 
	 * @author Leonardo Regis
	 * @date 04/08/2006
	 *
	 * @param pesquisarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param unidadeId
	 * @return Descrição da Unidade Filtrada
	 */
	private String getUnidadeDescricao(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, Fachada fachada, String unidadeId) {
		// Filtra Unidade
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeId));
		// Recupera Unidade Organizacional
		Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
			return colecaoUnidadeOrganizacional.iterator().next().getDescricao();
		}
		return "Unidade Inexistente";
	}
	/**
	 * Recupera Município
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param pesquisarRegistroAtendimentoActionForm
	 * @param fachada
	 */
	private void getMunicipio(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0008] Valida Município
		//if (isValidateObject(pesquisarRegistroAtendimentoActionForm)) {
			// Filtra Imovel
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, 
															pesquisarRegistroAtendimentoActionForm.getMunicipioId()));
			//filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("nome");
			// Recupera Município
			Collection<Municipio> colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				sessao.setAttribute("municipioEncontrada", "true");
				Municipio municipio = colecaoMunicipio.iterator().next();
				pesquisarRegistroAtendimentoActionForm.setMunicipioDescricao(municipio.getNome());
			} else {
				sessao.removeAttribute("municipioEncontrada");
				pesquisarRegistroAtendimentoActionForm.setMunicipioId("");
				pesquisarRegistroAtendimentoActionForm.setMunicipioDescricao("Município inexistente");
			}
			pesquisarRegistroAtendimentoActionForm.setValidaMunicipio("false");
		//}
	}

	/**
	 * Recupera Bairro
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param pesquisarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param sessao
	 */
	private void getBairro(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0010] Valida Bairro
		//if (isValidateObject(pesquisarRegistroAtendimentoActionForm)) {
			// Filtra Bairro
			FiltroBairro filtroBairro = new FiltroBairro();
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.CODIGO, 
															pesquisarRegistroAtendimentoActionForm.getBairroCodigo()));
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.MUNICIPIO_ID, 
															pesquisarRegistroAtendimentoActionForm.getMunicipioId()));
			//filtroBairro.adicionarCaminhoParaCarregamentoEntidade("nome");
			// Recupera Município
			Collection<Bairro> colecaoBairro = fachada.pesquisar(filtroBairro, Bairro.class.getName());
			if (colecaoBairro != null && !colecaoBairro.isEmpty()) {
				sessao.setAttribute("bairroEncontrada", "true");
				Bairro bairro = colecaoBairro.iterator().next();
				pesquisarRegistroAtendimentoActionForm.setBairroId(bairro.getId().toString());
				pesquisarRegistroAtendimentoActionForm.setBairroDescricao(bairro.getNome());
				carregaBairroArea(bairro.getId(), fachada, sessao);
			} else {
				sessao.removeAttribute("bairroEncontrada");
				pesquisarRegistroAtendimentoActionForm.setBairroId("");
				pesquisarRegistroAtendimentoActionForm.setBairroCodigo("");
				pesquisarRegistroAtendimentoActionForm.setBairroDescricao("Bairro inexistente");
			}
			pesquisarRegistroAtendimentoActionForm.setValidaBairro("false");
		//}
	}

	/**
	 * Recupera Logradouro
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @param pesquisarRegistroAtendimentoActionForm
	 * @param fachada
	 */
	private void getLogradouro(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0011] Valida Logradouro
		//if (isValidateObject(pesquisarRegistroAtendimentoActionForm)) {
			// Filtra Logradouro
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
			filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, 
															pesquisarRegistroAtendimentoActionForm.getLogradouroId()));
			
			//filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade("nome");
			// Recupera Logradouro
			Collection<Logradouro> colecaoLogradouro = fachada.pesquisar(filtroLogradouro, Logradouro.class.getName());
			if (colecaoLogradouro != null && !colecaoLogradouro.isEmpty()) {
				sessao.setAttribute("logradouroEncontrada", "true");
				Logradouro logradouro = colecaoLogradouro.iterator().next();
				pesquisarRegistroAtendimentoActionForm.setLogradouroDescricao(logradouro.getNome());
			} else {
				sessao.removeAttribute("logradouroEncontrada");
				pesquisarRegistroAtendimentoActionForm.setLogradouroId("");
				pesquisarRegistroAtendimentoActionForm.setLogradouroDescricao("Logradouro inexistente");
			}
			pesquisarRegistroAtendimentoActionForm.setValidaLogradouro("false");
		//}
	}
	/**
	 * Carrega Área do Bairro de acordo com o bairro informado 
	 *
	 * @author Leonardo Regis
	 * @date 04/08/2006
	 *
	 * @param bairroId
	 * @param fachada
	 * @param sessao
	 */
	private void carregaBairroArea(int bairroId, Fachada fachada, HttpSession sessao) {
		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();
		filtroBairroArea.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID_BAIRRO, bairroId));
		// Recupera Área do Bairro
		Collection<BairroArea> colecaoBairroArea = fachada.pesquisar(filtroBairroArea, BairroArea.class.getName());
		if (colecaoBairroArea != null && !colecaoBairroArea.isEmpty()) {
			sessao.setAttribute("colecaoBairroArea", colecaoBairroArea);
		} else {
			sessao.removeAttribute("colecaoBairroArea");
		}
	}
	/**
	 * Valida Objeto com <Enter> da tela
	 *
	 * @author Leonardo Regis
	 * @date 02/08/2006
	 *
	 * @return está validando algum objeto através do enter?
	 */
	/*private boolean isValidateObject(PesquisarRegistroAtendimentoActionForm form) {
		boolean toReturn = false;
		if (form.getValidaImovel().equalsIgnoreCase("true") || 
				form.getValidaUnidadeAtendimento().equalsIgnoreCase("true") || 
				form.getValidaUnidadeAtual().equalsIgnoreCase("true") || 
				form.getValidaUnidadeSuperior().equalsIgnoreCase("true") || 
				form.getValidaMunicipio().equalsIgnoreCase("true") || 
				form.getValidaBairro().equalsIgnoreCase("true") || 
				form.getValidaLogradouro().equalsIgnoreCase("true")) {
			toReturn = true;
		}
		return toReturn;		
	}*/
	
	
	/**
	 * Recupera o Usuário
	 *
	 * @author Raphael Rossiter
	 * @date 11/12/2006
	 *
	 * @param filtrarRegistroAtendimentoActionForm
	 * @param fachada
	 * @param idUsuario
	 * @return Descrição da Unidade Filtrada
	 */
	private void getUsuario(PesquisarRegistroAtendimentoActionForm pesquisarRegistroAtendimentoActionForm, 
			Fachada fachada, String idUsuario, HttpSession sessao) {
		
		// Filtra Usuario
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, idUsuario));
		
		
		// Recupera Usuário
		Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			Usuario usuario = colecaoUsuario.iterator().next();
			
			sessao.setAttribute("usuarioEncontrado","true");
			pesquisarRegistroAtendimentoActionForm.setNomeUsuario(usuario.getNomeUsuario());
		} else {
			
			sessao.removeAttribute("usuarioEncontrado");
			pesquisarRegistroAtendimentoActionForm.setLoginUsuario("");
			pesquisarRegistroAtendimentoActionForm.setNomeUsuario("Usuário Inexistente");
		}
	}
}
