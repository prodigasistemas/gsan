package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.bean.OSFiltroHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
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

public class PesquisarOrdemServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirResultadoPesquisaOrdemServico");
		
		// Instacia a fachada
		//Fachada fachada = Fachada.getInstancia();
		
		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Form
		PesquisarOrdemServicoActionForm pesquisarOrdemServicoActionForm 
			= (PesquisarOrdemServicoActionForm) actionForm;
		
		//Definindo a tela de retorno que será utilizado caso o usuário queira consultar os dados da OS ou do RA
		if (sessao.getAttribute("caminhoRetornoResultadoPesquisaOS") == null){
			sessao.setAttribute("caminhoRetornoResultadoPesquisaOS", "pesquisarOrdemServicoAction.do");
		}
		
		boolean parametroInformado = false;
		
		
		// quando for informado numero do RA, documento de cobrança, matricula do imovel ou 
        // codigo do cliente não deve haver esta restrição
        // Arthur Carvalho Solicitado por Romulo
		if ((pesquisarOrdemServicoActionForm.getNumeroRA() == null || pesquisarOrdemServicoActionForm.getNumeroRA().equals(""))&&
            (pesquisarOrdemServicoActionForm.getMatriculaImovel() == null || pesquisarOrdemServicoActionForm.getMatriculaImovel().equals(""))&&
            (pesquisarOrdemServicoActionForm.getDocumentoCobranca() == null || pesquisarOrdemServicoActionForm.getDocumentoCobranca().equals(""))&&
            (pesquisarOrdemServicoActionForm.getCodigoCliente() == null || pesquisarOrdemServicoActionForm.getCodigoCliente().equals(""))) {

			String dtAtendimentoIni = pesquisarOrdemServicoActionForm.getPeriodoAtendimentoInicial();
			String dtAtendimentoFinal = pesquisarOrdemServicoActionForm.getPeriodoAtendimentoFinal();

			String dtGeracaoIni = pesquisarOrdemServicoActionForm.getPeriodoGeracaoInicial();
			String dtGeracaoFinal = pesquisarOrdemServicoActionForm.getPeriodoGeracaoFinal();

			String dtProgramacaoIni = pesquisarOrdemServicoActionForm.getPeriodoProgramacaoInicial();
			String dtProgramacaoFinal = pesquisarOrdemServicoActionForm.getPeriodoProgramacaoFinal();

			String dtEncerramentoIni = pesquisarOrdemServicoActionForm.getPeriodoEncerramentoInicial();
			String dtEncerramentoFinal = pesquisarOrdemServicoActionForm.getPeriodoEncerramentoFinal();
			
			if((dtAtendimentoIni==null || dtAtendimentoIni.equals("")) 
					&& (dtAtendimentoFinal==null || dtAtendimentoFinal.equals(""))){
				if((dtGeracaoIni==null || dtGeracaoIni.equals(""))
						&& (dtGeracaoFinal==null || dtGeracaoFinal.equals(""))){
					if((dtProgramacaoIni==null || dtProgramacaoIni.equals("")) 
							&& (dtProgramacaoFinal==null || dtProgramacaoFinal.equals(""))){
						if((dtEncerramentoIni==null || dtEncerramentoIni.equals("")) 
								&& (dtEncerramentoFinal==null || dtEncerramentoFinal.equals(""))){
							throw new ActionServletException("atencao.filtrar_intervalo_datas_obrigatorio");
						}
					}
				}
			}
					
			if(dtAtendimentoIni != null && !dtAtendimentoIni.equals("")){
				if(dtAtendimentoFinal==null || dtAtendimentoFinal.equals("")){
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"atendimento");
				}else{
					Date ini = Util.converteStringParaDate(dtAtendimentoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias-1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtAtendimentoFinal),numeroDias); 
					if(dataLimite.after(ini)){
						throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"atendimento");
					}
				}
			}

			if(dtGeracaoIni != null && !dtGeracaoIni.equals("")){
				if(dtGeracaoFinal==null || dtGeracaoFinal.equals("")){
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"geração");
				}else{
					Date ini = Util.converteStringParaDate(dtGeracaoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias-1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtGeracaoFinal),numeroDias); 
					if(dataLimite.after(ini)){
						throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"geração");
					}
				}
			}
			
			if(dtProgramacaoIni != null && !dtProgramacaoIni.equals("")){
				if(dtProgramacaoFinal==null || dtProgramacaoFinal.equals("")){
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"programação");
				}else{
					Date ini = Util.converteStringParaDate(dtProgramacaoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias-1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtProgramacaoFinal),numeroDias); 
					if(dataLimite.after(ini)){
						throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"tramitação");
					}
				}
			}
			
			if(dtEncerramentoIni != null && !dtEncerramentoIni.equals("")){
				if(dtEncerramentoFinal==null || dtEncerramentoFinal.equals("")){
					throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",null,"encerramento");
				}else{
					Date ini = Util.converteStringParaDate(dtEncerramentoIni);
					Calendar calendario = new GregorianCalendar();
					calendario.setTime(ini);
					Integer numeroDias = new Integer(Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
					numeroDias = new Integer(numeroDias-1);
					Date dataLimite = Util.subtrairNumeroDiasDeUmaData(Util.converteStringParaDate(dtEncerramentoFinal),numeroDias); 
					if(dataLimite.after(ini)){
						throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"encerramento");
					}
				}
			}
		}
		
		
		//Origem da OS
		String origemOS = null;
		if (pesquisarOrdemServicoActionForm.getOrigemOrdemServico() != null &&
			!pesquisarOrdemServicoActionForm.getOrigemOrdemServico().equals("")) {
			
			origemOS = pesquisarOrdemServicoActionForm.getOrigemOrdemServico();
		}
		
		// Numero RA
		Integer numeroRA = null;
		if (pesquisarOrdemServicoActionForm.getNumeroRA() != null &&
			!pesquisarOrdemServicoActionForm.getNumeroRA().equals("")) {
			
			numeroRA = new Integer(pesquisarOrdemServicoActionForm.getNumeroRA());
			parametroInformado = true;
		}
		
		// Documento Cobrança
		Integer idDocumentoCobranca = null;
		if (pesquisarOrdemServicoActionForm.getDocumentoCobranca() != null &&
			!pesquisarOrdemServicoActionForm.getDocumentoCobranca().equals("")) {
			
			idDocumentoCobranca = new Integer(pesquisarOrdemServicoActionForm.getDocumentoCobranca());
			parametroInformado = true;
		}
		
		// Situacao da Ordem de Servico
		short situacaoOrdemServico = ConstantesSistema.NUMERO_NAO_INFORMADO;
		
		if (pesquisarOrdemServicoActionForm.getSituacaoOrdemServico() != null &&
			!pesquisarOrdemServicoActionForm.getSituacaoOrdemServico().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			situacaoOrdemServico = 
				new Short(pesquisarOrdemServicoActionForm.getSituacaoOrdemServico()).shortValue();	

			parametroInformado = true;
		}

		// Situacao da Programação
		short situacaoProgramacao = ConstantesSistema.NUMERO_NAO_INFORMADO;
		if (pesquisarOrdemServicoActionForm.getSituacaoProgramacao() != null) {
			
			//Informou todos
			if(!pesquisarOrdemServicoActionForm.getSituacaoProgramacao().equals("0")){
				situacaoProgramacao = 
					new Short(pesquisarOrdemServicoActionForm.getSituacaoProgramacao()).shortValue();
			}
			
			parametroInformado = true;
		}
		
		//Tipo Servico
		Integer[] idsTipoServicoSelecionado = 
			(Integer[]) pesquisarOrdemServicoActionForm.getTipoServicoSelecionados();
		
		if(idsTipoServicoSelecionado.length > 0){
			if(idsTipoServicoSelecionado[0].intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
				idsTipoServicoSelecionado = null;
			}else{
				parametroInformado = true;
			}
		}
		
		// Imovel
		Integer matriculaImovel = null;
		if (pesquisarOrdemServicoActionForm.getMatriculaImovel() != null &&
			!pesquisarOrdemServicoActionForm.getMatriculaImovel().equals("")) {
			
			matriculaImovel = new Integer(pesquisarOrdemServicoActionForm.getMatriculaImovel());
			
			parametroInformado = true;
		}

		// Cliente
		Integer codigoCliente = null;
		if (pesquisarOrdemServicoActionForm.getCodigoCliente() != null &&
			!pesquisarOrdemServicoActionForm.getCodigoCliente().equals("")) {
			
			codigoCliente = new Integer(pesquisarOrdemServicoActionForm.getCodigoCliente());
			
			parametroInformado = true;
		}

		// Unidade de Geração
		Integer unidadeGeracao = null;
		
		if (pesquisarOrdemServicoActionForm.getUnidadeGeracao() != null &&
			!pesquisarOrdemServicoActionForm.getUnidadeGeracao().equals("")) {
			
			unidadeGeracao= new Integer(pesquisarOrdemServicoActionForm.getUnidadeGeracao());

			parametroInformado = true;
		}

		// Unidade Atual
		Integer unidadeAtual = null;

		if (pesquisarOrdemServicoActionForm.getUnidadeAtual() != null &&
			!pesquisarOrdemServicoActionForm.getUnidadeAtual().equals("")) {
			
			unidadeAtual = new Integer(pesquisarOrdemServicoActionForm.getUnidadeAtual());
			
			parametroInformado = true;
		}

		// Unidade Superior
		Integer unidadeSuperior = null;

		if (pesquisarOrdemServicoActionForm.getUnidadeSuperior() != null &&
			!pesquisarOrdemServicoActionForm.getUnidadeSuperior().equals("")) {

			unidadeSuperior = new Integer(pesquisarOrdemServicoActionForm.getUnidadeSuperior());
			
			parametroInformado = true;
		}

		// Data de Atendimento
		Date dataAtendimentoInicial = null;
		Date dataAtendimentoFinal = null;
		
		if (pesquisarOrdemServicoActionForm.getPeriodoAtendimentoInicial() != null &&
			!pesquisarOrdemServicoActionForm.getPeriodoAtendimentoInicial().equals("")) {
			
			dataAtendimentoInicial = 
				Util.converteStringParaDate(pesquisarOrdemServicoActionForm.getPeriodoAtendimentoInicial());
			
			dataAtendimentoFinal = null;
			if (pesquisarOrdemServicoActionForm.getPeriodoAtendimentoFinal() != null &&
				!pesquisarOrdemServicoActionForm.getPeriodoAtendimentoFinal().equals("")) {
				
				dataAtendimentoFinal = 
					Util.converteStringParaDate(pesquisarOrdemServicoActionForm.getPeriodoAtendimentoFinal());
			} else {
				dataAtendimentoFinal = new Date();
			}

			parametroInformado = true;
		}

		// Data de Geração
		Date dataGeracaoInicial = null;
		Date dataGeracaoFinal = null;
		
		if (pesquisarOrdemServicoActionForm.getPeriodoGeracaoInicial() != null &&
			!pesquisarOrdemServicoActionForm.getPeriodoGeracaoInicial().equals("")){
			
			dataGeracaoInicial = 
				Util.converteStringParaDate(pesquisarOrdemServicoActionForm.getPeriodoGeracaoInicial());
			
			dataGeracaoFinal = null;
			
			if (pesquisarOrdemServicoActionForm.getPeriodoGeracaoFinal() != null &&
				!pesquisarOrdemServicoActionForm.getPeriodoGeracaoFinal().equals("") ) {
				
				dataGeracaoFinal = 
					Util.converteStringParaDate(pesquisarOrdemServicoActionForm.getPeriodoGeracaoFinal());
				
			} else {
				dataGeracaoFinal = new Date();
			}

			parametroInformado = true;
		}

		// Data de Programação
		Date dataProgramacaoInicial = null;
		Date dataProgramacaoFinal = null;
		
		if (pesquisarOrdemServicoActionForm.getPeriodoProgramacaoInicial() != null &&
			!pesquisarOrdemServicoActionForm.getPeriodoProgramacaoInicial().equals("")){
			
			dataProgramacaoInicial = 
				Util.converteStringParaDate(pesquisarOrdemServicoActionForm.getPeriodoProgramacaoInicial());
			
			dataProgramacaoFinal = null;
			
			if (pesquisarOrdemServicoActionForm.getPeriodoProgramacaoFinal() != null &&
				!pesquisarOrdemServicoActionForm.getPeriodoProgramacaoFinal().equals("") ) {
				
				dataProgramacaoFinal = 
					Util.converteStringParaDate(pesquisarOrdemServicoActionForm.getPeriodoProgramacaoFinal());
				
			} else {
				dataProgramacaoFinal = new Date();
			}

			parametroInformado = true;
		}

		
		// Data de Encerramento
		Date dataEncerramentoInicial = null;
		Date dataEncerramentoFinal = null;
		
		if (pesquisarOrdemServicoActionForm.getPeriodoEncerramentoInicial() != null &&
			!pesquisarOrdemServicoActionForm.getPeriodoEncerramentoInicial().equals("")){
			
			dataEncerramentoInicial = 
				Util.converteStringParaDate(pesquisarOrdemServicoActionForm.getPeriodoEncerramentoInicial());
		
			dataEncerramentoFinal = null;
			
			if (pesquisarOrdemServicoActionForm.getPeriodoEncerramentoFinal() != null &&
				!pesquisarOrdemServicoActionForm.getPeriodoEncerramentoFinal().equals("") ) {
				
				dataEncerramentoFinal = 
					Util.converteStringParaDate(pesquisarOrdemServicoActionForm.getPeriodoEncerramentoFinal());
				
			} else {
				dataEncerramentoFinal = new Date();
			}

			parametroInformado = true;
		}

		// Município
		Integer idMunicipio = null;
		
		if (pesquisarOrdemServicoActionForm.getMunicipio() != null &&
			!pesquisarOrdemServicoActionForm.getMunicipio().equals("")) {
			
			idMunicipio = new Integer(pesquisarOrdemServicoActionForm.getMunicipio()); 
			
			parametroInformado = true;
		}

		// Bairro
		Integer idBairro = null;
		
		if (pesquisarOrdemServicoActionForm.getBairro() != null &&
				!pesquisarOrdemServicoActionForm.getBairro().equals("")) {
			
			if(pesquisarOrdemServicoActionForm.getIdBairro() != null &&
				!pesquisarOrdemServicoActionForm.getIdBairro().equals("")){
				
				idBairro = new Integer(pesquisarOrdemServicoActionForm.getIdBairro());	
			}else {
				Bairro bai = pesquisarBairro(pesquisarOrdemServicoActionForm);
				idBairro = bai.getId();
			}
			
			
			
			parametroInformado = true;
		}

		// Bairro Área
		Integer idAreaBairro = null;
		if (new Integer(pesquisarOrdemServicoActionForm.getAreaBairro()).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {

			idAreaBairro = new Integer(pesquisarOrdemServicoActionForm.getAreaBairro());
			
			parametroInformado = true;
		}
		
		// Logradouro
		Integer idLogradouro = null;
		
		if (pesquisarOrdemServicoActionForm.getLogradouro() != null &&
			!pesquisarOrdemServicoActionForm.getLogradouro().equals("")) {
		
			idLogradouro = new Integer(pesquisarOrdemServicoActionForm.getLogradouro());
			
			parametroInformado = true;
		}
		
		/*
		 * Colocado por Raphael Rossiter em 15/10/2009
		 * 
		 * Permitir efetuar a pesquisa das ordens de serviço pelo projeto
		 */
		Integer idProjeto = null;
		
		if (pesquisarOrdemServicoActionForm.getProjeto() != null &&
			!pesquisarOrdemServicoActionForm.getProjeto().equals("") &&
			!pesquisarOrdemServicoActionForm.getProjeto().equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			
			idProjeto = new Integer(pesquisarOrdemServicoActionForm.getProjeto());
				
			parametroInformado = true;
		}
		
		PesquisarOrdemServicoHelper pesquisarOrdemServicoHelper = 
			new PesquisarOrdemServicoHelper();
		
		pesquisarOrdemServicoHelper.setOrigemOrdemServico(origemOS);
		pesquisarOrdemServicoHelper.setNumeroRA(numeroRA);
		pesquisarOrdemServicoHelper.setDocumentoCobranca(idDocumentoCobranca);
		pesquisarOrdemServicoHelper.setSituacaoOrdemServico(situacaoOrdemServico);
		pesquisarOrdemServicoHelper.setSituacaoProgramacao(situacaoProgramacao);
		pesquisarOrdemServicoHelper.setTipoServicos(idsTipoServicoSelecionado);

		pesquisarOrdemServicoHelper.setMatriculaImovel(matriculaImovel);
		pesquisarOrdemServicoHelper.setCodigoCliente(codigoCliente);
		
		pesquisarOrdemServicoHelper.setUnidadeGeracao(unidadeGeracao);
		pesquisarOrdemServicoHelper.setUnidadeAtual(unidadeAtual);
		pesquisarOrdemServicoHelper.setUnidadeSuperior(unidadeSuperior);
		
		pesquisarOrdemServicoHelper.setDataAtendimentoInicial(dataAtendimentoInicial);
		pesquisarOrdemServicoHelper.setDataAtendimentoFinal(dataAtendimentoFinal);
		pesquisarOrdemServicoHelper.setDataGeracaoInicial(dataGeracaoInicial);
		pesquisarOrdemServicoHelper.setDataGeracaoFinal(dataGeracaoFinal);
		pesquisarOrdemServicoHelper.setDataProgramacaoInicial(dataProgramacaoInicial);
		pesquisarOrdemServicoHelper.setDataProgramacaoFinal(dataProgramacaoFinal);
		pesquisarOrdemServicoHelper.setDataEncerramentoInicial(dataEncerramentoInicial);
		pesquisarOrdemServicoHelper.setDataEncerramentoFinal(dataEncerramentoFinal);

		pesquisarOrdemServicoHelper.setMunicipio(idMunicipio);
		pesquisarOrdemServicoHelper.setBairro(idBairro);
		pesquisarOrdemServicoHelper.setAreaBairro(idAreaBairro);
		pesquisarOrdemServicoHelper.setLogradouro(idLogradouro);
		
		pesquisarOrdemServicoHelper.setIdProjeto(idProjeto);
		
		// Pesquisar Ordem Servico
		if(sessao.getAttribute("parametroInformado") != null){
			parametroInformado = ((Boolean) sessao.getAttribute("parametroInformado")).booleanValue();
		}
		
		if (parametroInformado) {

			int totalRegistros = ConstantesSistema.NUMERO_NAO_INFORMADO;

			Integer tamanho = 
				Fachada.getInstancia().pesquisarOrdemServicoTamanho(pesquisarOrdemServicoHelper);
			
			if (tamanho == null || tamanho == 0) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			} else {
				
				totalRegistros = tamanho.intValue();
				
				retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);				
				
				int numeroPaginasPesquisa = 
					((Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa")).intValue();
				
				pesquisarOrdemServicoHelper.setNumeroPaginasPesquisa(numeroPaginasPesquisa);
				
				Collection<OrdemServico> colecaoOrdemServico = 
					Fachada.getInstancia().pesquisarOrdemServico(pesquisarOrdemServicoHelper);
				
				Collection colecaoOSHelper = loadColecaoOSHelper(colecaoOrdemServico);
				sessao.setAttribute("colecaoOSHelper", colecaoOSHelper);
				sessao.setAttribute("parametroInformado", new Boolean(parametroInformado));
			}
		} else {
			throw new ActionServletException("atencao.filtrar_informar_um_filtro");
		}

		return retorno;
	}

	/**
	 * Carrega coleção de ordem de servico, situação da unidade atual no 
	 * objeto facilitador 
	 *
	 * @author Rafael Pinto
	 * @date 18/08/2006
	 *
	 * @param colecaoRegistroAtendimento
	 * @return
	 */
	private Collection loadColecaoOSHelper(Collection<OrdemServico> colecaoOrdemServico) {
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoOSHelper = new ArrayList();
		UnidadeOrganizacional unidadeAtual = null;
		ObterDescricaoSituacaoOSHelper situacao = null;
		Imovel imovel = null;
		OSFiltroHelper helper = null;
		
		for (Iterator iter = colecaoOrdemServico.iterator(); iter.hasNext();) {
			
			OrdemServico ordemServico = (OrdemServico) iter.next();
			
			situacao = fachada.obterDescricaoSituacaoOS(ordemServico.getId());
			
			if(ordemServico.getRegistroAtendimento() != null) {
				unidadeAtual = fachada.obterUnidadeAtualRA(ordemServico.getRegistroAtendimento().getId());
				imovel = ordemServico.getRegistroAtendimento().getImovel();
			}else if(ordemServico.getCobrancaDocumento() != null){
				imovel = ordemServico.getCobrancaDocumento().getImovel();
			}
			
			helper = new OSFiltroHelper();
			
			helper.setOrdemServico(ordemServico);
			helper.setImovel(imovel);
			helper.setUnidadeAtual(unidadeAtual);
			helper.setSituacao(situacao.getDescricaoSituacao());
			
			colecaoOSHelper.add(helper);
		}
		
		return colecaoOSHelper;
	}
	
	/**
	 * Pesquisa Bairro 
	 *
	 * @author Rafael Pinto
	 * @date 16/08/2006
	 */
	private Bairro pesquisarBairro(PesquisarOrdemServicoActionForm pesquisarOrdemServicoActionForm) {
		Bairro bairro = null;
		
		//[FS0013] - Verificar informação do munícipio
		String codigoMunicipio = pesquisarOrdemServicoActionForm.getMunicipio();
		if(codigoMunicipio == null || codigoMunicipio.equals("")){
			throw new ActionServletException("atencao.filtrar_informar_municipio");	
		}
		
		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.CODIGO, 
			new Integer(pesquisarOrdemServicoActionForm.getBairro())));

		filtroBairro.adicionarParametro(
				new ParametroSimples(FiltroBairro.MUNICIPIO_ID,new Integer(codigoMunicipio)));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoBairro = Fachada.getInstancia()
				.pesquisar(filtroBairro,Bairro.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoBairro != null && !colecaoBairro.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			bairro = 
				(Bairro) Util.retonarObjetoDeColecao(colecaoBairro);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"Bairro");
		}
		
		return bairro;
	}	
}
