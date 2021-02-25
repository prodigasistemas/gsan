package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.bean.OSFiltroHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarOrdemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("resultadoPesquisa");

		HttpSession sessao = request.getSession(false);

		FiltrarOrdemServicoActionForm form = (FiltrarOrdemServicoActionForm) actionForm;

		boolean parametroInformado = false;

		Integer[] tiposServicosSelecionados = form.getTipoServicoSelecionados();

		if (form.getNumeroOS().equals("")&& form.getSituacaoOrdemServico().equals("-1")	&& tiposServicosSelecionados[0].equals(-1)) {
			throw new ActionServletException("atencao.numero_os_situacao_ordem_servico_e_tipo_servico_obrigatorios");
			
		} else if (form.getNumeroOS().equals("") && form.getSituacaoOrdemServico().equals("-1")) {
			throw new ActionServletException("atencao.servico_ordem_servico_obrigatorio");
			
		} else if (form.getNumeroOS().equals("") && tiposServicosSelecionados[0].equals(-1)) {
			throw new ActionServletException("atencao.tipo_servico_obrigatorio");
		}

		if (request.getParameter("idRa") != null) {
			form.setNumeroRA(request.getParameter("idRa").toString());

			Integer[] tipoServicoSelecionados = new Integer[1];
			tipoServicoSelecionados[0] = ConstantesSistema.NUMERO_NAO_INFORMADO;
			form.setTipoServicoSelecionados(tipoServicoSelecionados);

			form.setOrigemOrdemServico(OrdemServico.TODAS);
		}
		
		
		recuperaPeriodos(form);

		String origemOS = null;
		if (form.getOrigemOrdemServico() != null
				&& !form.getOrigemOrdemServico().equals("")) {

			origemOS = form.getOrigemOrdemServico();
			// parametroInformado = true;
		}

		Integer numeroOS = null;
		if (form.getNumeroOS() != null
				&& !form.getNumeroOS().equals("")) {

			numeroOS = new Integer(form.getNumeroOS());
			parametroInformado = true;
		}

		Integer numeroRA = null;
		if (form.getNumeroRA() != null
				&& !form.getNumeroRA().equals("")) {

			numeroRA = new Integer(form.getNumeroRA());
			parametroInformado = true;
		}

		Integer idDocumentoCobranca = null;
		if (form.getDocumentoCobranca() != null
				&& !form.getDocumentoCobranca().equals("")) {

			idDocumentoCobranca = new Integer(form.getDocumentoCobranca());
			parametroInformado = true;
		}

		short situacaoOrdemServico = ConstantesSistema.NUMERO_NAO_INFORMADO;

		if (form.getSituacaoOrdemServico() != null
				&& !form.getSituacaoOrdemServico().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			situacaoOrdemServico = new Short(form.getSituacaoOrdemServico()).shortValue();

			parametroInformado = true;
		}

		short situacaoProgramacao = ConstantesSistema.NUMERO_NAO_INFORMADO;
		if (form.getSituacaoProgramacao() != null) {

			if (!form.getSituacaoProgramacao().equals("0")) {
				situacaoProgramacao = new Short(form.getSituacaoProgramacao()).shortValue();
			}

		}

		Integer[] idsTipoServicoSelecionado = (Integer[]) form.getTipoServicoSelecionados();

		if (idsTipoServicoSelecionado.length > 0) {
			if (idsTipoServicoSelecionado[0].intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO) {
				idsTipoServicoSelecionado = null;
			} else {
				parametroInformado = true;
			}
		}

		Collection<Integer> colecaoPerfilImovel = null;
		if (form.getColecaoPerfilImovel() != null) {
			colecaoPerfilImovel = new ArrayList<Integer>();

			for (String id : form.getColecaoPerfilImovel()) {
				if (!id.equals("-1")) {
					parametroInformado = true;
					colecaoPerfilImovel.add(new Integer(id));
				}
			}
			if (colecaoPerfilImovel.size() == 0)
				colecaoPerfilImovel = null;
		}

		Integer matriculaImovel = null;
		if (form.getMatriculaImovel() != null
				&& !form.getMatriculaImovel().equals("")) {

			matriculaImovel = new Integer(form.getMatriculaImovel());

			parametroInformado = true;
		}

		Integer codigoCliente = null;
		if (form.getCodigoCliente() != null
				&& !form.getCodigoCliente().equals("")) {

			codigoCliente = new Integer(form.getCodigoCliente());

			parametroInformado = true;
		}

		Integer unidadeGeracao = null;

		if (form.getUnidadeGeracao() != null
				&& !form.getUnidadeGeracao().equals("")) {

			unidadeGeracao = new Integer(form.getUnidadeGeracao());

			parametroInformado = true;
		}

		Integer unidadeAtual = null;

		if (form.getUnidadeAtual() != null
				&& !form.getUnidadeAtual().equals("")) {

			unidadeAtual = new Integer(form.getUnidadeAtual());

			parametroInformado = true;
		}

		Integer unidadeSuperior = null;

		if (form.getUnidadeSuperior() != null
				&& !form.getUnidadeSuperior().equals("")) {

			unidadeSuperior = new Integer(form.getUnidadeSuperior());

			parametroInformado = true;
		}

		Date dataAtendimentoInicial = null;
		Date dataAtendimentoFinal = null;

		if (form.getPeriodoAtendimentoInicial() != null
				&& !form.getPeriodoAtendimentoInicial().equals("")) {

			dataAtendimentoInicial = Util.converteStringParaDate(form
					.getPeriodoAtendimentoInicial());

			dataAtendimentoFinal = null;
			if (form.getPeriodoAtendimentoFinal() != null
					&& !form.getPeriodoAtendimentoFinal().equals("")) {

				dataAtendimentoFinal = Util.converteStringParaDate(form
						.getPeriodoAtendimentoFinal());
			} else {
				dataAtendimentoFinal = new Date();
			}

			parametroInformado = true;
		}

		Collection<Integer> colecaoAtendimentoMotivoEncerramento = null;
		if (form.getColecaoAtendimentoMotivoEncerramento() != null) {
			colecaoAtendimentoMotivoEncerramento = new ArrayList<Integer>();

			for (String id : form.getColecaoAtendimentoMotivoEncerramento()) {
				if (!id.equals("-1")) {
					parametroInformado = true;
					colecaoAtendimentoMotivoEncerramento.add(new Integer(id));
				}
			}
			if (colecaoAtendimentoMotivoEncerramento.size() == 0)
				colecaoAtendimentoMotivoEncerramento = null;
		}

		Date dataGeracaoInicial = null;
		Date dataGeracaoFinal = null;

		if (form.getPeriodoGeracaoInicial() != null
				&& !form.getPeriodoGeracaoInicial().equals("")) {

			dataGeracaoInicial = Util.converteStringParaDate(form.getPeriodoGeracaoInicial());

			dataGeracaoFinal = null;

			if (form.getPeriodoGeracaoFinal() != null
					&& !form.getPeriodoGeracaoFinal().equals("")) {

				dataGeracaoFinal = Util.converteStringParaDate(form.getPeriodoGeracaoFinal());

			} else {
				dataGeracaoFinal = new Date();
			}

			parametroInformado = true;
		}

		Date dataProgramacaoInicial = null;
		Date dataProgramacaoFinal = null;

		if (form.getPeriodoProgramacaoInicial() != null
				&& !form.getPeriodoProgramacaoInicial().equals("")) {

			dataProgramacaoInicial = Util.converteStringParaDate(form
					.getPeriodoProgramacaoInicial());

			dataProgramacaoFinal = null;

			if (form.getPeriodoProgramacaoFinal() != null
					&& !form.getPeriodoProgramacaoFinal().equals("")) {

				dataProgramacaoFinal = Util.converteStringParaDate(form
						.getPeriodoProgramacaoFinal());

			} else {
				dataProgramacaoFinal = new Date();
			}

			parametroInformado = true;
		}

		Date dataEncerramentoInicial = null;
		Date dataEncerramentoFinal = null;

		if (form.getPeriodoEncerramentoInicial() != null
				&& !form.getPeriodoEncerramentoInicial().equals("")) {

			dataEncerramentoInicial = Util.converteStringParaDate(form
					.getPeriodoEncerramentoInicial());

			dataEncerramentoFinal = null;

			if (form.getPeriodoEncerramentoFinal() != null
					&& !form.getPeriodoEncerramentoFinal().equals("")) {

				dataEncerramentoFinal = Util.converteStringParaDate(form
						.getPeriodoEncerramentoFinal());

			} else {
				dataEncerramentoFinal = new Date();
			}

			parametroInformado = true;
		}

		Integer idMunicipio = null;

		if (form.getMunicipio() != null
				&& !form.getMunicipio().equals("")) {

			idMunicipio = new Integer(form.getMunicipio());

			parametroInformado = true;
		}

		Integer idBairro = null;

		if (form.getCodigoBairro() != null
				&& !form.getCodigoBairro().equals("")) {

			idBairro = this.pesquisarBairro(form);

			parametroInformado = true;
		}

		Integer idAreaBairro = null;
		if (form.getAreaBairro() != null
				&& new Integer(form.getAreaBairro()).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

			idAreaBairro = new Integer(form.getAreaBairro());

			parametroInformado = true;
		}

		Integer idLogradouro = null;

		if (form.getLogradouro() != null
				&& !form.getLogradouro().equals("")) {

			idLogradouro = new Integer(form.getLogradouro());

			parametroInformado = true;
		}

		Integer idProjeto = null;

		if (form.getProjeto() != null
				&& !form.getProjeto().equals("")
				&& !form.getProjeto().equals(
						String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

			idProjeto = new Integer(form.getProjeto());

			parametroInformado = true;
		}

		PesquisarOrdemServicoHelper helper = new PesquisarOrdemServicoHelper();
		helper.setOrigemOrdemServico(origemOS);
		helper.setNumeroOS(numeroOS);
		helper.setNumeroRA(numeroRA);
		helper.setDocumentoCobranca(idDocumentoCobranca);
		helper.setSituacaoOrdemServico(situacaoOrdemServico);
		helper.setSituacaoProgramacao(situacaoProgramacao);
		helper.setTipoServicos(idsTipoServicoSelecionado);

		helper.setMatriculaImovel(matriculaImovel);
		helper.setCodigoCliente(codigoCliente);

		helper.setUnidadeGeracao(unidadeGeracao);
		helper.setUnidadeAtual(unidadeAtual);
		helper.setUnidadeSuperior(unidadeSuperior);

		helper.setColecaoAtendimentoMotivoEncerramento(colecaoAtendimentoMotivoEncerramento);

		helper.setDataAtendimentoInicial(dataAtendimentoInicial);
		helper.setDataAtendimentoFinal(dataAtendimentoFinal);
		helper.setDataGeracaoInicial(dataGeracaoInicial);
		helper.setDataGeracaoFinal(dataGeracaoFinal);
		helper.setDataProgramacaoInicial(dataProgramacaoInicial);
		helper.setDataProgramacaoFinal(dataProgramacaoFinal);
		helper.setDataEncerramentoInicial(dataEncerramentoInicial);
		helper.setDataEncerramentoFinal(dataEncerramentoFinal);

		helper.setMunicipio(idMunicipio);
		helper.setBairro(idBairro);
		helper.setAreaBairro(idAreaBairro);
		helper.setLogradouro(idLogradouro);

		helper.setColecaoPerfilImovel(colecaoPerfilImovel);

		helper.setIdProjeto(idProjeto);

		sessao.setAttribute("pesquisarOrdemServicoHelper", helper);

		String indicadorTipoServico = form.getIndicadorTipoServico();

		if (indicadorTipoServico != null && !indicadorTipoServico.equals("")) {
			parametroInformado = true;
			if (indicadorTipoServico.equals("terceirizado")) {
				helper.setIndicadorTerceirizado(ConstantesSistema.SIM);
			} else if (indicadorTipoServico.equals("pavimento")) {
				helper.setIndicadorPavimento(ConstantesSistema.SIM);
			} else if (indicadorTipoServico.equals("vistoria")) {
				helper.setIndicadorVistoria(ConstantesSistema.SIM);
			} else if (indicadorTipoServico.equals("fiscalizacao")) {
				helper.setIndicadorFiscalizacao(ConstantesSistema.SIM);
			}
		}

		if (sessao.getAttribute("parametroInformado") != null) {
			parametroInformado = ((Boolean) sessao.getAttribute("parametroInformado")).booleanValue();
		}

		if (request.getParameter("voltar") != null) {

			if (helper.getNumeroOS() != null) {
				helper.setNumeroOS(null);
				helper.setNumeroRA(null);

			}

		}

		if (parametroInformado) {

			int totalRegistros = ConstantesSistema.NUMERO_NAO_INFORMADO;

			Integer tamanho = null;

			if (request.getParameter("page.offset") != null) {
				tamanho = (Integer) sessao.getAttribute("totalRegistros");
			} else {
				tamanho = Fachada.getInstancia().pesquisarOrdemServicoTamanho(helper);
			}

			if (tamanho == null || tamanho == 0) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");

			} else if (tamanho.intValue() == 1) {

				Collection<OrdemServico> colecaoOrdemServico = Fachada.getInstancia().pesquisarOrdemServico(helper);

				OrdemServico os = (OrdemServico) Util.retonarObjetoDeColecao(colecaoOrdemServico);

				request.setAttribute("numeroOS", os.getId());
				retorno = actionMapping.findForward("manterOrdemServico");

				// sessao.removeAttribute("caminhoRetornoOS");
				sessao.removeAttribute("manterOs");
			} else {
				sessao.setAttribute("manterOs", "Não");
				// sessao.setAttribute( "caminhoRetornoOS",
				// "filtrarOrdemServicoAction.do" );

				totalRegistros = tamanho.intValue();

				retorno = this.controlarPaginacao(request, retorno, totalRegistros);

				int numeroPaginasPesquisa = ((Integer) request.getAttribute("numeroPaginasPesquisa"))
						.intValue();

				helper.setNumeroPaginasPesquisa(numeroPaginasPesquisa);

				Collection<OrdemServico> colecaoOrdemServico = Fachada.getInstancia().pesquisarOrdemServico(helper);

				@SuppressWarnings("unchecked")
				Collection<OSFiltroHelper> colecaoOSHelper = loadColecaoOSHelper(colecaoOrdemServico);
				sessao.setAttribute("numeroPaginasPesquisa", numeroPaginasPesquisa);
				sessao.setAttribute("page.offset", request.getAttribute("page.offset"));
				sessao.setAttribute("colecaoOSHelper", colecaoOSHelper);
				sessao.setAttribute("parametroInformado", new Boolean(parametroInformado));

			}
		} else {
			throw new ActionServletException("atencao.filtrar_informar_um_filtro");
		}

		if (sessao.getAttribute("importarMovimentoACQUAGIS") != null
				&& sessao.getAttribute("importarMovimentoACQUAGIS").equals("sim")) {
			sessao.setAttribute("caminhoRetornoOS",
					"filtrarRegistroAtendimentoTramitacaoAction.do?importarMovimentoACQUAGIS=sim");
		}

		return retorno;
	}

	private void recuperaPeriodos(FiltrarOrdemServicoActionForm form) {
		if ((form.getNumeroOS() == null || 
				form.getNumeroOS().equals("")) && (form.getNumeroRA() == null || 
				form.getNumeroRA().equals(""))	&& (form.getMatriculaImovel() == null || 
				form.getMatriculaImovel().equals(""))	&& (form.getDocumentoCobranca() == null || 
				form.getDocumentoCobranca().equals(""))	&& (form.getCodigoCliente() == null || 
				form.getCodigoCliente().equals(""))) {

			String dtAtendimentoIni = form.getPeriodoAtendimentoInicial();
			String dtAtendimentoFinal = form.getPeriodoAtendimentoFinal();

			String dtGeracaoIni = form.getPeriodoGeracaoInicial();
			String dtGeracaoFinal = form.getPeriodoGeracaoFinal();

			String dtProgramacaoIni = form.getPeriodoProgramacaoInicial();
			String dtProgramacaoFinal = form.getPeriodoProgramacaoFinal();

			String dtEncerramentoIni = form.getPeriodoEncerramentoInicial();
			String dtEncerramentoFinal = form.getPeriodoEncerramentoFinal();

			if ((dtAtendimentoIni == null || dtAtendimentoIni.equals(""))
					&& (dtAtendimentoFinal == null || dtAtendimentoFinal.equals(""))) {
				if ((dtGeracaoIni == null || dtGeracaoIni.equals(""))
						&& (dtGeracaoFinal == null || dtGeracaoFinal.equals(""))) {
					if ((dtProgramacaoIni == null || dtProgramacaoIni.equals(""))
							&& (dtProgramacaoFinal == null || dtProgramacaoFinal.equals(""))) {
						if ((dtEncerramentoIni == null || dtEncerramentoIni.equals(""))
								&& (dtEncerramentoFinal == null || dtEncerramentoFinal.equals(""))) {
							throw new ActionServletException("atencao.filtrar_intervalo_datas_obrigatorio");
						}
					}
				}
			}

			if (dtAtendimentoIni != null && !dtAtendimentoIni.equals("")) {
				if (dtAtendimentoFinal == null || dtAtendimentoFinal.equals("")) {
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial", null,
							"atendimento");
				} else {
					Date ini = Util.converteStringParaDate(dtAtendimentoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH) + 1,
							calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias - 1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtAtendimentoFinal),
							numeroDias);
					if (dataLimite.after(ini)) {
						throw new ActionServletException("atencao.filtrar_intervalo_limite", null, "atendimento");
					}
				}
			}

			if (dtGeracaoIni != null && !dtGeracaoIni.equals("")) {
				if (dtGeracaoFinal == null || dtGeracaoFinal.equals("")) {
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial", null,
							"geração");
				} else {
					Date ini = Util.converteStringParaDate(dtGeracaoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH) + 1,
							calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias - 1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtGeracaoFinal),
							numeroDias);
					if (dataLimite.after(ini)) {
						throw new ActionServletException("atencao.filtrar_intervalo_limite", null, "geração");
					}
				}
			}

			if (dtProgramacaoIni != null && !dtProgramacaoIni.equals("")) {
				if (dtProgramacaoFinal == null || dtProgramacaoFinal.equals("")) {
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial", null,
							"programação");
				} else {
					Date ini = Util.converteStringParaDate(dtProgramacaoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH) + 1,
							calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias - 1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtProgramacaoFinal),
							numeroDias);
					if (dataLimite.after(ini)) {
						throw new ActionServletException("atencao.filtrar_intervalo_limite", null, "tramitação");
					}
				}
			}

			if (dtEncerramentoIni != null && !dtEncerramentoIni.equals("")) {
				if (dtEncerramentoFinal == null || dtEncerramentoFinal.equals("")) {
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial", null,
							"encerramento");
				} else {
					Date ini = Util.converteStringParaDate(dtEncerramentoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH) + 1,
							calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias - 1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(
							Util.converteStringParaDate(dtEncerramentoFinal), numeroDias);
					if (dataLimite.after(ini)) {
						throw new ActionServletException("atencao.filtrar_intervalo_limite", null, "encerramento");
					}
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Collection loadColecaoOSHelper(Collection<OrdemServico> colecaoOrdemServico) {

		Fachada fachada = Fachada.getInstancia();

		Collection<OSFiltroHelper> colecaoOSHelper = new ArrayList();
		UnidadeOrganizacional unidadeAtual = null;
		ObterDescricaoSituacaoOSHelper situacao = null;
		Imovel imovel = null;
		OSFiltroHelper helper = null;

		for (Iterator iter = colecaoOrdemServico.iterator(); iter.hasNext();) {

			OrdemServico ordemServico = (OrdemServico) iter.next();

			situacao = fachada.obterDescricaoSituacaoOS(ordemServico.getId());

			if (ordemServico.getRegistroAtendimento() != null) {
				unidadeAtual = fachada.obterUnidadeAtualRA(ordemServico.getRegistroAtendimento().getId());
				imovel = ordemServico.getRegistroAtendimento().getImovel();
			} else if (ordemServico.getCobrancaDocumento() != null) {
				imovel = ordemServico.getCobrancaDocumento().getImovel();
			}

			helper = new OSFiltroHelper();

			helper.setUnidadeAtual(ordemServico.getUnidadeAtual());
			helper.setOrdemServico(ordemServico);
			helper.setImovel(imovel);
			helper.setUnidadeAtual(unidadeAtual);
			helper.setSituacao(situacao.getDescricaoAbreviadaSituacao());

			if (ordemServico.getImovel() != null) {
				helper.setPerfilImovel(ordemServico.getImovel().getImovelPerfil());
			}

			// Caso o RegistroAtendimento for urgente, indicadorUrgencia = 1,
			// senao = 2.
			// if( ordemServico.getRegistroAtendimento() != null &&
			// this.getFachada().verificarRegistroAtendimentoUrgencia(ordemServico.getRegistroAtendimento().getId())
			// > 0){
			// helper.setIndicadorUrgencia(1);
			// }else{
			// helper.setIndicadorUrgencia(2);
			// }
			verificarUrgencia(helper, ordemServico.getRegistroAtendimento());

			colecaoOSHelper.add(helper);
		}

		return colecaoOSHelper;
	}


	@SuppressWarnings("unchecked")
	private Integer pesquisarBairro(FiltrarOrdemServicoActionForm form) {

		// [FS0013] - Verificar informação do munícipio
		String codigoMunicipio = form.getMunicipio();
		if (codigoMunicipio == null || codigoMunicipio.equals("")) {
			throw new ActionServletException("atencao.filtrar_informar_municipio");
		}

		Integer retorno = null;

		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.CODIGO, new Integer(form.getCodigoBairro())));

		filtroBairro.adicionarParametro(new ParametroSimples(FiltroBairro.MUNICIPIO_ID, new Integer(codigoMunicipio)));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection<Bairro> colecaoBairro = Fachada.getInstancia().pesquisar(filtroBairro, Bairro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Bairro bairro = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);

			retorno = bairro.getId();
		} else {
			throw new ActionServletException("atencao.bairro.inexistente");
		}

		return retorno;
	}

	private void verificarUrgencia(OSFiltroHelper helper, RegistroAtendimento registroAtendimento) {

		Integer indicadorUrgencia = 2;

		if (registroAtendimento != null) {

			Integer qtdeRAUrgencia = this.getFachada()
					.verificarRegistroAtendimentoUrgencia(registroAtendimento.getId());
			Short qtdeReiteracoes = this.getFachada().pesquisarQtdeReiteracaoRA(registroAtendimento.getId());

			StringBuilder hint1 = new StringBuilder();

			if (qtdeRAUrgencia.intValue() > 0 && qtdeReiteracoes != null && qtdeReiteracoes.intValue() > 0) {
				indicadorUrgencia = 1;
				hint1.append("Registro de Atendimento em caráter de urgência");
				hint1.append(" <br> Registro de Atendimento reiterado");
			} else if (qtdeRAUrgencia.intValue() > 0) {
				indicadorUrgencia = 1;
				hint1.append("Registro de Atendimento em caráter de urgência");
			} else if (qtdeReiteracoes != null && qtdeReiteracoes.intValue() > 0) {
				indicadorUrgencia = 1;
				hint1.append("Registro de Atendimento reiterado");
			}
			helper.setHint1(hint1.toString());
		}
		helper.setIndicadorUrgencia(indicadorUrgencia);
	}
}
