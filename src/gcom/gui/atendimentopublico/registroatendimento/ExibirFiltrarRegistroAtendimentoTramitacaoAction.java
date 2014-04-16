package gcom.gui.atendimentopublico.registroatendimento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0503]Tramitar Conjunto de Registro de Atendimento
 * 
 * @author Ana Maria		
 * @date 08/01/2007
 */
public class ExibirFiltrarRegistroAtendimentoTramitacaoAction extends GcomAction {
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

		// Obtém o action form
		FiltrarRegistroAtendimentoTramitacaoActionForm form = (FiltrarRegistroAtendimentoTramitacaoActionForm) actionForm;

		// Seta a ação de retorno
		ActionForward retorno = actionMapping.findForward("filtrarRegistroAtendimentoTramitacao");

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a facahda
		Fachada fachada = Fachada.getInstancia();
		
		String menu = httpServletRequest.getParameter("menu");
		
		if (menu != null && !menu.equalsIgnoreCase("")){

			Integer qtdDias = 30;
			
			Date dataAtual = new Date();
			Date dataSugestao = Util.subtrairNumeroDiasDeUmaData(dataAtual, qtdDias);
			
			form.setPeriodoAtendimentoInicial(Util.formatarData(dataSugestao));
			form.setPeriodoAtendimentoFinal(Util.formatarData(dataAtual));
			
		}
		
		// Tipo Solicitação
		getSolicitacaoTipoCollection(sessao, fachada);
		
		// Especificação
		if (form.getTipoSolicitacao() != null &&
				form.getTipoSolicitacao().length > 0) {
			getSolicitacaoTipoEspecificacaoCollection(sessao, fachada, form);
		} else {
			form.setSelectedTipoSolicitacaoSize("0");
		}

	    // Unidade Atual
		if (form.getUnidadeAtualId() != null && !form.getUnidadeAtualId().equals("")) {
			getUnidade(form, httpServletRequest, 1);
		}
		// Unidade Superior
		if (form.getUnidadeSuperiorId() != null && !form.getUnidadeSuperiorId().equals("")) {
			getUnidade(form, httpServletRequest, 2);
		}

		// Município
		if (form.getMunicipioId() != null && !form.getMunicipioId().equals("")) {
			getMunicipio(form, httpServletRequest);
		}
		// Bairro & Área do Bairro
		if (form.getBairroId() != null && !form.getBairroId().equals("")) {
			// Verificar informação do município
			if (form.getMunicipioId() != null && !form.getMunicipioId().equals("")) {
				getBairro(form, httpServletRequest);
			} else {
				throw new ActionServletException("atencao.filtrar_informar_municipio");
			}
		}
		// Logradouro
		if (form.getLogradouroId() != null && !form.getLogradouroId().equals("")) {
			getLogradouro(form, httpServletRequest);
		}
		
		// Perfil Imovel
		getPerfilImovelCollection(sessao, fachada);
		
		
		Collection tramites = (Collection)sessao.getAttribute("tramites");
		
		if(tramites != null && !tramites.isEmpty()){
			sessao.removeAttribute("tramites");
		}		
		
		// Verifica Permissao Especial para Tramitar Acquagis
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		boolean permissaoTramitarAcquagis = Fachada.getInstancia().verificarPermissaoEspecial(
						PermissaoEspecial.TRAMITAR_RA_ACQUAGIS,
						usuarioLogado);
		
		httpServletRequest.setAttribute("permissaoTramitarAcquagis", permissaoTramitarAcquagis);
		
		return retorno;
	}
	
	/**
	 * Carrega coleção de solicitação tipo
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
	 * @param sessao
	 * @param fachada
	 */
	private void getSolicitacaoTipoEspecificacaoCollection(HttpSession sessao, Fachada fachada, FiltrarRegistroAtendimentoTramitacaoActionForm form) {
		String[] solicitacaoTipo = form.getTipoSolicitacao();
		form.setSelectedTipoSolicitacaoSize(solicitacaoTipo.length+"");
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
	 * Verificar existência de unidades subordinadas
	 * 
	 * @param form
	 * @param fachada
	 * @param tipo
	 */
	private void getUnidade(FiltrarRegistroAtendimentoTramitacaoActionForm form, HttpServletRequest httpServletRequest, int tipo) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0006] Valida Unidade Atendimento
		//if (isValidateObject(filtrarRegistroAtendimentoActionForm)) {
			String unidadeId = "";
			switch (tipo) {
			case 1:
				unidadeId = form.getUnidadeAtualId();
				form.setUnidadeAtualDescricao(getUnidadeDescricao(fachada, unidadeId));				
				if (form.getUnidadeAtualDescricao().equalsIgnoreCase("Unidade Inexistente")) {
					sessao.removeAttribute("unidadeAtualEncontrada");
					form.setUnidadeAtualId("");
				} else {
					sessao.setAttribute("unidadeAtualEncontrada","true");
					form.setValidaUnidadeAtual("false");
				}
				break;
			case 2:
				unidadeId = form.getUnidadeSuperiorId();
				UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
				unidadeOrganizacional.setId(new Integer(unidadeId));
				form.setUnidadeSuperiorDescricao(getUnidadeDescricao(fachada, unidadeId));
				if (form.getUnidadeSuperiorDescricao().equalsIgnoreCase("Unidade Inexistente")) {
					sessao.removeAttribute("unidadeSuperiorEncontrada");
					form.setUnidadeSuperiorId("");
				} else {
					// [FS007] Verificar existência de unidades subordinadas
					fachada.verificarExistenciaUnidadesSubordinadas(unidadeOrganizacional);
					sessao.setAttribute("unidadeSuperiorEncontrada","true");
					form.setValidaUnidadeSuperior("false");
				}
				break;
			default:
				break;
			}
	}
	
	/**
	 * Recupera a Unidade Organizacional (Atendimento, Atual e Superior)
	 *
	 * Valida Unidade
	 *
	 * @param fachada
	 * @param unidadeId
	 * @return Descrição da Unidade Filtrada
	 */
	private String getUnidadeDescricao(Fachada fachada, String unidadeId) {
		// Filtra Unidade
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, unidadeId));
		// Recupera Unidade Organizacional
		
		Collection<UnidadeOrganizacional> colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
		
		if (colecaoUnidadeOrganizacional != null && !colecaoUnidadeOrganizacional.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = colecaoUnidadeOrganizacional.iterator().next();
			return unidadeOrganizacional.getDescricao();
		}
		
		return "Unidade Inexistente";
	}
	
	/**
	 * Recupera Município
	 *
	 * @param form
	 * @param fachada
	 */
	private void getMunicipio(FiltrarRegistroAtendimentoTramitacaoActionForm form, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0008] Valida Município
		//if (isValidateObject(filtrarRegistroAtendimentoActionForm)) {
			// Filtra Imovel
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, form.getMunicipioId()));
			// Recupera Município
			
			Collection<Municipio> colecaoMunicipio = fachada.pesquisar(filtroMunicipio, Municipio.class.getName());
			
			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				sessao.setAttribute("municipioEncontrada", "true");
				Municipio municipio = colecaoMunicipio.iterator().next();
				form.setMunicipioDescricao(municipio.getNome());
			} else {
				sessao.removeAttribute("municipioEncontrada");
				form.setMunicipioId("");
				form.setMunicipioDescricao("Município inexistente");
			}
			form.setValidaMunicipio("false");
		//}
	}

	/**
	 * Recupera Bairro
	 *
	 * @param form
	 * @param fachada
	 * @param sessao
	 */
	private void getBairro(FiltrarRegistroAtendimentoTramitacaoActionForm form, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0010] Valida Bairro
		//if (isValidateObject(filtrarRegistroAtendimentoActionForm)) {
			// Filtra Bairro
			FiltroBairro filtroBairro = new FiltroBairro();
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.CODIGO, form.getBairroId()));
			filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.MUNICIPIO_ID, 
					form.getMunicipioId()));

			Collection<Bairro> colecaoBairro = fachada.pesquisar(filtroBairro, Bairro.class.getName());
			
			if (colecaoBairro != null && !colecaoBairro.isEmpty()) {
				sessao.setAttribute("bairroEncontrada", "true");
				Bairro bairro = colecaoBairro.iterator().next();
				form.setBairroDescricao(bairro.getNome());
				carregaBairroArea(bairro.getId(), fachada, sessao);
			} else {
				sessao.removeAttribute("bairroEncontrada");
				form.setBairroId("");
				form.setBairroDescricao("Bairro inexistente");
			}
			form.setValidaBairro("false");
		//}
	}

	/**
	 * Recupera Logradouro
	 *
	 * @param form
	 * @param fachada
	 */
	private void getLogradouro(FiltrarRegistroAtendimentoTramitacaoActionForm form, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// [F0011] Valida Logradouro
		//if (isValidateObject(filtrarRegistroAtendimentoActionForm)) {
			// Filtra Logradouro
			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
			filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, 
					form.getLogradouroId()));
			
			// Recupera Logradouro
			Collection<Logradouro> colecaoLogradouro = fachada.pesquisar(filtroLogradouro, Logradouro.class.getName());
			
			if (colecaoLogradouro != null && !colecaoLogradouro.isEmpty()) {
				sessao.setAttribute("logradouroEncontrada", "true");
				Logradouro logradouro = colecaoLogradouro.iterator().next();
				form.setLogradouroDescricao(logradouro.getNome());
			} else {
				sessao.removeAttribute("logradouroEncontrada");
				form.setLogradouroId("");
				form.setLogradouroDescricao("Logradouro inexistente");
			}
			form.setValidaLogradouro("false");
		//}
	}
	/**
	 * Carrega Área do Bairro de acordo com o bairro informado 
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
	 * Carrega coleção de Perfil do Imóvel
	 *
	 * @author Daniel Alves
	 * @date 09/07/2010
	 *
	 * @param sessao
	 * @param fachada
	 */
	private void getPerfilImovelCollection(HttpSession sessao, Fachada fachada) {
		// Filtra Solicitação Tipo
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

		Collection<ImovelPerfil> colecaoPerfilImovel= fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

		if (colecaoPerfilImovel != null && !colecaoPerfilImovel.isEmpty()) {
			sessao.setAttribute("colecaoPerfilImovel",	colecaoPerfilImovel);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Perfil do Imovel");
		}
	}
	
}
