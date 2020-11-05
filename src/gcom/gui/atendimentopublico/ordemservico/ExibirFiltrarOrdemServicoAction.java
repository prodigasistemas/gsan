package gcom.gui.atendimentopublico.ordemservico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.projeto.FiltroProjeto;
import gcom.cadastro.projeto.Projeto;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.FiltroDocumentoCobranca;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

/**
 * [UC0450] Pesquisar Ordem Servico - Exibir
 */
public class ExibirFiltrarOrdemServicoAction extends GcomAction {
	
	private HttpSession sessao;
	private HttpServletRequest request;
	private FiltrarOrdemServicoActionForm form;

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		this.sessao = request.getSession(false);
		this.request = request;
		this.form = (FiltrarOrdemServicoActionForm) actionForm;

		ActionForward retorno = actionMapping.findForward("filtrarOrdemServico");

		configurarQuantidadeDiasMesAtual();
		configurarPesquisas();
		configurarConsultasForm();
		pesquisarServicoTipo();
		pesquisarAtendimentoMotivoEncerramento();
		pesquisarSolicitacaoTipo();
		pesquisarPerfilImovel();
		pesquisarProjeto();

		configurarCamposEncontrados();

		if (request.getParameter("caminhoRetornoTelaPesquisaOrdemServico") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaOrdemServico", request.getParameter("caminhoRetornoTelaPesquisaOrdemServico"));
		}

		if (form.getSituacaoProgramacao() == null || form.getSituacaoProgramacao().equals("")) {
			form.setSituacaoProgramacao(ConstantesSistema.SET_ZERO.toString());
		}

		return retorno;
	}

	private void configurarConsultasForm() {
		if (request.getParameter("tipoConsulta") != null && !request.getParameter("tipoConsulta").equals("")) {

			String id = request.getParameter("idCampoEnviarDados");
			String descricao = request.getParameter("descricaoCampoEnviarDados");

			if (request.getParameter("tipoConsulta").equals("registroAtendimento")) {
				form.setNumeroRA(id);
				form.setDescricaoRA(descricao);
				
			} else if (request.getParameter("tipoConsulta").equals("documentoCobranca")) {
				form.setDocumentoCobranca(id);
				form.setDescricaoDocumentoCobranca(descricao);
				
			} else if (request.getParameter("tipoConsulta").equals("imovel")) {
				form.setMatriculaImovel(id);
				form.setInscricaoImovel(descricao);
				
			} else if (request.getParameter("tipoConsulta").equals("cliente")) {
				form.setCodigoCliente(id);
				form.setNomeCliente(descricao);
				
			} else if (request.getParameter("tipoConsulta").equals("unidadeOrganizacional")) {
				
				if (sessao.getAttribute("tipoUnidade").equals("unidadeGeracao")) {
					form.setUnidadeGeracao(id);
					form.setDescricaoUnidadeGeracao(descricao);
					
				} else if (sessao.getAttribute("tipoUnidade").equals("unidadeAtual")) {
					form.setUnidadeAtual(id);
					form.setDescricaoUnidadeAtual(descricao);
					
				} else {
					form.setUnidadeSuperior(id);
					form.setDescricaoUnidadeSuperior(descricao);
				}
			} else if (request.getParameter("tipoConsulta").equals("municipio")) {
				form.setMunicipio(id);
				form.setDescricaoMunicipio(descricao);
				
			} else if (request.getParameter("tipoConsulta").equals("bairro")) {
				form.setCodigoBairro(id);
				form.setDescricaoBairro(descricao);
				
			} else if (request.getParameter("tipoConsulta").equals("logradouro")) {
				form.setLogradouro(id);
				form.setDescricaoLogradouro(descricao);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarProjeto() {
		if (sessao.getAttribute("colecaoProjeto") == null) {
			Filtro filtro = new FiltroProjeto();

			Collection<Projeto> colecao = getFachada().pesquisar(filtro, Projeto.class.getName());
			if (colecao != null && !colecao.isEmpty()) {
				sessao.setAttribute("colecaoProjeto", colecao);
			}
		}
	}

	private void configurarPesquisas() {
		String objetoConsulta = request.getParameter("objetoConsulta");
		
		// [UC0443] - Pesquisar Registro Atendimento
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("1")) {
			pesquisarRegistroAtendimento();
		}

		// [UC9999] - Pesquisar Documento de Cobrança
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("2")) {
			pesquisarDocumentoCobranca();
		}

		// [UC0013] - Pesquisar Imovel
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("3")) {
			pesquisarImovel();
		}

		// [UC0012] - Pesquisar Cliente
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("4")) {
			pesquisarCliente();
		}

		// [UC0376 - Pesquisar Unidade
		if ((objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("5"))
				|| (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("6"))
				|| (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("7"))) {

			pesquisarUnidadeOrganizacional(objetoConsulta);
		}

		// [UC0075] - Pesquisar Municipio
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("8")) {
			pesquisarMunicipio();
		}

		// [UC0141] - Pesquisar Bairro
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("9")) {
			pesquisarBairro();
		}

		// [UC0004] - Pesquisar Logradouro
		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("10")) {
			pesquisarLogradouro();
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarAtendimentoMotivoEncerramento() {
		Collection<AtendimentoMotivoEncerramento> colecao = (Collection<AtendimentoMotivoEncerramento>) sessao.getAttribute("colecaoAtendimentoMotivoEncerramento");
		
		if (colecao == null) {
			Filtro filtro = new FiltroAtendimentoMotivoEncerramento();
			filtro.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
			
			colecao = getFachada().pesquisar(filtro, AtendimentoMotivoEncerramento.class.getName());
			sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", colecao);
		}
	}

	private void configurarQuantidadeDiasMesAtual() {
		String menu = request.getParameter("menu");
		if (menu != null && !menu.equalsIgnoreCase("")) {
			Date dataAtual = new Date();
			Calendar calendario = new GregorianCalendar();
			calendario.setTime(dataAtual);

			Integer quantidadeDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH), calendario.get(Calendar.YEAR)));
			Date dataSugestao = Util.subtrairNumeroDiasDeUmaData(dataAtual, quantidadeDias - 1);
			
			form.setPeriodoGeracaoInicial(Util.formatarData(dataSugestao));
			form.setPeriodoGeracaoFinal(Util.formatarData(dataAtual));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarImovel() {
		Filtro filtro = new FiltroImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getMatriculaImovel()));
		filtro.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtro.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtro.adicionarCaminhoParaCarregamentoEntidade("quadra");

		Collection<Imovel> colecao = getFachada().pesquisar(filtro, Imovel.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecao);
			form.setMatriculaImovel(imovel.getId().toString());
			form.setInscricaoImovel(imovel.getInscricaoFormatada());

		} else {
			form.setMatriculaImovel("");
			form.setInscricaoImovel("Matrícula inexistente");
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarRegistroAtendimento() {
		Filtro filtro = new FiltroRegistroAtendimento();
		filtro.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, new Integer(form.getNumeroRA())));
		filtro.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao");

		Collection<RegistroAtendimento> colecao = getFachada().pesquisar(filtro, RegistroAtendimento.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecao);
			form.setNumeroRA(registroAtendimento.getId().toString());
			form.setDescricaoRA(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
		} else {
			form.setDescricaoRA("Registro Atendimento inexistente");
			form.setNumeroRA("");
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarDocumentoCobranca() {
		Filtro filtro = new FiltroDocumentoCobranca();
		filtro.adicionarParametro(new ParametroSimples(FiltroDocumentoCobranca.ID, new Integer(form.getDocumentoCobranca())));
		filtro.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");

		Collection<CobrancaDocumento> colecao = getFachada().pesquisar(filtro,CobrancaDocumento.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			CobrancaDocumento cobrancaDocumento = (CobrancaDocumento) Util.retonarObjetoDeColecao(colecao);
			form.setDocumentoCobranca(cobrancaDocumento.getId().toString());
			form.setDescricaoDocumentoCobranca(cobrancaDocumento.getDocumentoTipo().getDescricaoDocumentoTipo());
		} else {
			form.setDocumentoCobranca("");
			form.setDescricaoDocumentoCobranca("Documento Cobrança inexistente");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarCliente() {
		Filtro filtro = new FiltroCliente();
		filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, new Integer(form.getCodigoCliente())));
		Collection<Cliente> colecao = getFachada().pesquisar(filtro, Cliente.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecao);
			form.setCodigoCliente(cliente.getId().toString());
			form.setNomeCliente(cliente.getNome());
		} else {
			form.setCodigoCliente("");
			form.setNomeCliente("Cliente inexistente");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarUnidadeOrganizacional(String objetoConsulta) {
		Filtro filtro = new FiltroUnidadeOrganizacional();

		Integer idUnidade = null;
		if (objetoConsulta.equals("5")) {
			idUnidade = new Integer(form.getUnidadeGeracao());
		} else if (objetoConsulta.equals("6")) {
			idUnidade = new Integer(form.getUnidadeAtual());
		} else {
			idUnidade = new Integer(form.getUnidadeSuperior());
		}

		filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidade));

		Collection<UnidadeOrganizacional> colecao = getFachada().pesquisar(filtro,UnidadeOrganizacional.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecao);

			if (objetoConsulta.equals("5")) {
				form.setUnidadeGeracao(unidadeOrganizacional.getId().toString());
				form.setDescricaoUnidadeGeracao(unidadeOrganizacional.getDescricao());

			} else if (objetoConsulta.equals("6")) {
				form.setUnidadeAtual(unidadeOrganizacional.getId().toString());
				form.setDescricaoUnidadeAtual(unidadeOrganizacional.getDescricao());
			} else {
				// [FS0009] - Verificar existência de unidades subordinadas
				filtro = new FiltroUnidadeOrganizacional();
				filtro.adicionarParametro(new ParametroSimples(	FiltroUnidadeOrganizacional.ID_UNIDADE_SUPERIOR, idUnidade));

				colecao = getFachada().pesquisar(filtro,UnidadeOrganizacional.class.getName());

				if (colecao != null && !colecao.isEmpty()) {
					form.setUnidadeSuperior(unidadeOrganizacional.getId().toString());
					form.setDescricaoUnidadeSuperior(unidadeOrganizacional.getDescricao());
				} else {
					throw new ActionServletException("atencao.filtrar_ra_sem_unidades_subordinadas");
				}
			}
		} else {
			if (objetoConsulta.equals("5")) {
				form.setUnidadeGeracao("");
				form.setDescricaoUnidadeGeracao("Unidade de Geração inexistente");

			} else if (objetoConsulta.equals("6")) {
				form.setUnidadeAtual("");
				form.setDescricaoUnidadeAtual("Unidade Atual inexistente");

			} else {
				form.setUnidadeSuperior("");
				form.setDescricaoUnidadeSuperior("Unidade Superior inexistente");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarMunicipio() {
		Filtro filtro = new FiltroMunicipio();
		filtro.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, new Integer(form.getMunicipio())));

		Collection<Municipio> colecao = getFachada().pesquisar(filtro, Municipio.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			Municipio municipio = (Municipio) Util.retonarObjetoDeColecao(colecao);

			form.setMunicipio(municipio.getId().toString());
			form.setDescricaoMunicipio(municipio.getNome());
		} else {
			form.setMunicipio("");
			form.setDescricaoMunicipio("Município inexistente");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarBairro() {
		String codigoMunicipio = form.getMunicipio();
		if (codigoMunicipio == null || codigoMunicipio.equals("")) {
			throw new ActionServletException("atencao.filtrar_informar_municipio");
		}

		Filtro filtro = new FiltroBairro();
		filtro.adicionarParametro(new ParametroSimples(FiltroBairro.CODIGO, new Integer(form.getCodigoBairro())));
		filtro.adicionarParametro(new ParametroSimples(FiltroBairro.MUNICIPIO_ID, new Integer(codigoMunicipio)));

		Collection<Bairro> colecao = getFachada().pesquisar(filtro, Bairro.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecao);

			montarAreaBairroPorId(bairro.getId());

			form.setCodigoBairro("" + bairro.getCodigo());
			form.setIdBairro("" + bairro.getId());
			form.setDescricaoBairro(bairro.getNome());
		} else {
			form.setCodigoBairro(null);
			form.setDescricaoBairro("Bairro inexistente");
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarLogradouro() {
		FiltroLogradouro filtro = new FiltroLogradouro();
		filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, new Integer(form.getLogradouro())));

		Collection<Logradouro> colecao = getFachada().pesquisar(filtro, Logradouro.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			Logradouro logradouro = (Logradouro) Util.retonarObjetoDeColecao(colecao);

			form.setLogradouro(logradouro.getId().toString());
			form.setDescricaoLogradouro(logradouro.getNome());
		} else {
			form.setLogradouro("");
			form.setDescricaoLogradouro("Logradouro inexistente");
		}
	}

	@SuppressWarnings("unchecked")
	private void montarAreaBairroPorId(Integer id) {
		Filtro filtro = new FiltroBairroArea();
		filtro.adicionarParametro(new ParametroSimples(FiltroBairroArea.ID_BAIRRO, id));

		Collection<BairroArea> colecao = getFachada().pesquisar(filtro,BairroArea.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			request.setAttribute("colecaoAreaBairro", colecao);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Área do Bairro");
		}
	}

	private void pesquisarServicoTipo() {
		Filtro filtro = new FiltroServicoTipo();
		filtro.setConsultaSemLimites(true);
		filtro.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
		filtro.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		List<Integer> idsServicoTipo = null;
		
		String pesquisarEspecificacao = request.getParameter("pesquisarEspecificacao");
		if (pesquisarEspecificacao != null && !pesquisarEspecificacao.equalsIgnoreCase("")
				|| !(form.getTipoSolicitacao() == null ||  form.getTipoSolicitacao().equals("-1"))) {
			idsServicoTipo = obterIdsServicoTipoPorEspecificacao();
			
			if (idsServicoTipo != null) {
				filtro.adicionarParametro(new ParametroSimplesIn(FiltroServicoTipo.ID, idsServicoTipo));
				
			}
			
			if (idsServicoTipo == null || idsServicoTipo.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null, "Tipo de Serviço");
					
			}
			
		}
		
		Collection<ServicoTipo>  colecao = getFachada().pesquisar(filtro, ServicoTipo.class.getName());
		request.setAttribute("colecaoTipoServico", colecao);


	}

	private boolean nenhumTipoServicoSelecionado() {
		return form.getTipoServicoSelecionados() == null || form.getTipoServicoSelecionados().length <= 0;
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarSolicitacaoTipo() {
		Collection<SolicitacaoTipo> colecao = (Collection<SolicitacaoTipo>) sessao.getAttribute("colecaoSolicitacaoTipo");

		if (colecao == null) {
			
			List<Integer> idsEspecificacoes = new ArrayList<Integer>();
			idsEspecificacoes.addAll(obterIdsServicoTipoPorEspecificacao());
			
			
			Filtro filtro = new FiltroSolicitacaoTipo(FiltroSolicitacaoTipo.DESCRICAO);
			filtro.setConsultaSemLimites(true);
			filtro.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA, SolicitacaoTipo.INDICADOR_USO_SISTEMA_NAO));

			colecao = getFachada().pesquisar(filtro, SolicitacaoTipo.class.getName());
			
			if (colecao == null || colecao.isEmpty()) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "SOLICITACAO_TIPO");
			} else {
				sessao.setAttribute("colecaoSolicitacaoTipo", colecao);
			}
		}
	}
	
	private List<Integer> obterIdsServicoTipoPorEspecificacao() {
		List<SolicitacaoTipoEspecificacao> colecaoEspecificacao = pesquisarSolicitacaoTipoEspecificacao();
		
		List<Integer> ids = new ArrayList<Integer>();
		for (SolicitacaoTipoEspecificacao especificacao : colecaoEspecificacao) {
			
			if (especificacao.getServicoTipo() != null)
				ids.add(especificacao.getServicoTipo().getId());
		}
		
		return ids;
	}

	@SuppressWarnings("unchecked")
	private List<SolicitacaoTipoEspecificacao> pesquisarSolicitacaoTipoEspecificacao() {
		Filtro filtro = new FiltroSolicitacaoTipoEspecificacao(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
		if (form.getTipoSolicitacao() != null)
			filtro.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID, new Integer(form.getTipoSolicitacao())));
		
		filtro.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
		filtro.setInitializeLazy(false);
		filtro.setConsultaSemLimites(true);

		return (List<SolicitacaoTipoEspecificacao>) getFachada().pesquisar(filtro, SolicitacaoTipoEspecificacao.class.getName());
	}

	private void configurarCamposEncontrados() {

		if (form.getMatriculaImovel() != null && !form.getMatriculaImovel().equals("") && 
				form.getInscricaoImovel() != null && !form.getInscricaoImovel().equals("")) {

			request.setAttribute("matriculaImovelEncontrada", "true");
		}

		if (form.getNumeroRA() != null && !form.getNumeroRA().equals("") && form.getDescricaoRA() != null && !form.getDescricaoRA().equals("")) {
			request.setAttribute("numeroRAEncontrada", "true");
		}

		if (form.getDocumentoCobranca() != null && !form.getDocumentoCobranca().equals("") && 
				form.getDescricaoDocumentoCobranca() != null && !form.getDescricaoDocumentoCobranca().equals("")) {

			request.setAttribute("documentoCobrancaEncontrada", "true");
		}

		if (form.getCodigoCliente() != null && !form.getCodigoCliente().equals("") && 
				form.getNomeCliente() != null && !form.getNomeCliente().equals("")) {

			request.setAttribute("codigoClienteEncontrada", "true");
		}

		if (form.getUnidadeGeracao() != null && !form.getUnidadeGeracao().equals("") && 
				form.getDescricaoUnidadeGeracao() != null && !form.getDescricaoUnidadeGeracao().equals("")) {

			request.setAttribute("unidadeGeracaoEncontrada", "true");
		}

		if (form.getUnidadeAtual() != null && !form.getUnidadeAtual().equals("") && 
				form.getDescricaoUnidadeAtual() != null && !form.getDescricaoUnidadeAtual().equals("")) {

			request.setAttribute("unidadeAtualEncontrada", "true");
		}

		if (form.getUnidadeSuperior() != null && !form.getUnidadeSuperior().equals("") && 
				form.getDescricaoUnidadeSuperior() != null && !form.getDescricaoUnidadeSuperior().equals("")) {

			request.setAttribute("unidadeSuperiorEncontrada", "true");
		}

		if (form.getMunicipio() != null && !form.getMunicipio().equals("") && 
				form.getDescricaoMunicipio() != null && !form.getDescricaoMunicipio().equals("")) {

			request.setAttribute("municipioEncontrada", "true");
		}

		if (form.getCodigoBairro() != null && !form.getCodigoBairro().equals("") && 
				form.getDescricaoBairro() != null && !form.getDescricaoBairro().equals("")) {

			request.setAttribute("bairroEncontrada", "true");
		}

		if (form.getLogradouro() != null && !form.getLogradouro().equals("") && 
				form.getDescricaoLogradouro() != null && !form.getDescricaoLogradouro().equals("")) {

			request.setAttribute("logradouroEncontrado", "true");
		}
		
		
		Collection colecaoTipoServicoSelecionados = null;
		
		if(form.getTipoServicoSelecionados() != null){
			
			Integer[] aux = form.getTipoServicoSelecionados();
			
			List aux1 = Arrays.asList(aux);
			colecaoTipoServicoSelecionados = aux1;
			
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			
			filtroServicoTipo.adicionarParametro(new ParametroSimplesIn(FiltroServicoTipo.ID, colecaoTipoServicoSelecionados));
			
			filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
			// Pesquisa de acordo com os parâmetros informados no filtro
			colecaoTipoServicoSelecionados = Fachada.getInstancia().pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());
			
			
			// Verifica se a pesquisa retornou algum objeto para a coleção
			if (colecaoTipoServicoSelecionados != null && !colecaoTipoServicoSelecionados.isEmpty()) {
				this.request.setAttribute("colecaoTipoServicoSelecionados", colecaoTipoServicoSelecionados);
			}
		}
		
		// Monta a colecao de tipos Servicos
		// pesquisarServicoTipo(colecaoTipoServicoSelecionados);
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarPerfilImovel() {
		Filtro filtro = new FiltroImovelPerfil();
		filtro.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

		Collection<ImovelPerfil> colecao = getFachada().pesquisar(filtro, ImovelPerfil.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			sessao.setAttribute("colecaoPerfilImovel", colecao);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null, "Perfil do Imovel");
		}
	}
}
