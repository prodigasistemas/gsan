package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.ordemservico.GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioRelacaoOrdensServicoConcluidas;
import gcom.relatorio.atendimentopublico.RelatorioRelacaoOrdensServicoEncerradasPendentes;
import gcom.relatorio.atendimentopublico.RelatorioResumoOrdensServicoEncerradasPendentes;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Ivan Sérgio
 * @created 11/12/2007, 27/03/2008, 04/04/2008
 * @alteracao: 27/03/2008 - Adicionado Motivo Encerramento; Setor Comercial;
 * 			   04/04/2008 - Adicionado um novo relatorio quebrando por Local de Instalação; 
 */
public class GerarRelatorioAcompanhamentoOrdemServicoHidrometroAction extends
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

		GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm form = 
			(GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm) actionForm;

		// Recupera os valores do form para serem passados como parâmetros para
		// o RelatorioAcompanhamentoOrdemServicoHidrometro
		String tipoOrdem = form.getTipoOrdem();
		String situacaoOrdemServico = form.getSituacaoOrdemServico();
		String firma = form.getFirma();
		String nomeFirma = form.getNomeFirma();
		String idLocalidadeInicial = form.getLocalidadeInicial();
		String nomeLocalidadeInicial = form.getNomeLocalidadeInicial();
		String idLocalidadeFinal = form.getLocalidadeFinal();
		String nomeLocalidadeFinal = form.getNomeLocalidadeFinal();
		String dataEncerramentoInicial = form.getDataEncerramentoInicial();
		String dataEncerramentoFinal = form.getDataEncerramentoFinal();
		
		if (dataEncerramentoInicial != null && !dataEncerramentoInicial.trim().equals("")) {
			dataEncerramentoInicial += " 00:00:00";
		}
		
		if (dataEncerramentoFinal != null && !dataEncerramentoFinal.trim().equals("")) {
			dataEncerramentoFinal += " 23:59:59";
		}
		
		String tipoRelatorioAcompanhamento = form.gettipoRelatorioAcompanhamento();
		
		String idMotivoEncerramento = form.getIdMotivoEncerramento();
		String descricaoMotivoEncerramento = form.getDescricaoMotivoEncerramento();
		String idSetorComercialInicial = form.getSetorComercialInicial();
		String idSetorComercialFinal = form.getSetorComercialFinal();
		String codigoSetorComercialInicial = form.getCodigoSetorComercialInicial();		
		String codigoSetorComercialFinal = form.getCodigoSetorComercialFinal();
		String nomeSetorComercialInicial = form.getNomeSetorComercialInicial();
		String nomeSetorComercialFinal = form.getNomeSetorComercialFinal();
		String codigoQuadraInicial = form.getQuadraInicial();
		String codigoQuadraFinal = form.getQuadraFinal();
		String codigoRotaInicial = form.getRotaInicial();
		String codigoRotaFinal = form.getRotaFinal();
		String sequenciaRotaInicial = form.getRotaSequenciaInicial();
		String sequenciaRotaFinal = form.getRotaSequenciaFinal();
		
		// Valida a Localidade Informada
		if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("") &&
				idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {
			Localidade localidadeInicial = validarLocalidade(idLocalidadeInicial);
			Localidade localidadeFinal = validarLocalidade(idLocalidadeFinal);
			
			if (nomeLocalidadeInicial == null || nomeLocalidadeInicial.equals("")) {
				nomeLocalidadeInicial = localidadeInicial.getDescricao();
			}
			
			if (nomeLocalidadeFinal == null || nomeLocalidadeFinal.equals("")) {
				nomeLocalidadeFinal = localidadeFinal.getDescricao();
			}
		}
		
		// Valida o Setor Informado
		if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.equals("")&&
				codigoSetorComercialFinal != null && !codigoSetorComercialFinal.equals("")) {
			SetorComercial setorComercialInicial = validarSetorComercial(idLocalidadeInicial, codigoSetorComercialInicial);
			SetorComercial setorComercialFinal = validarSetorComercial(idLocalidadeFinal, codigoSetorComercialFinal);
			//inicial
			if (idSetorComercialInicial == null || idSetorComercialInicial.equals("")) {
				idSetorComercialInicial = setorComercialInicial.getId().toString();
			}
			if (nomeSetorComercialInicial == null || nomeSetorComercialInicial.equals("")) {
				nomeSetorComercialInicial = setorComercialInicial.getDescricao();
			}
			
			//final
			if (idSetorComercialFinal == null || idSetorComercialFinal.equals("")) {
				idSetorComercialFinal = setorComercialFinal.getId().toString();
			}
			if (nomeSetorComercialFinal == null || nomeSetorComercialFinal.equals("")) {
				nomeSetorComercialFinal = setorComercialFinal.getDescricao();
			}			
		}
		
		String tipoGeracaoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		// Verifica o Tipo do Relatorio Escolhido pelo usuario
		// 1 - Analitico (RelatorioRelacaoOrdensServicoEncerradasPendentes)
		// 2 - Sintetico (RelatorioResumoOrdensServicoEncerradasPendentes)
		// 3 - Por Local de Instalacao (RelatorioRelacaoOrdensServicoConcluidas)
		// 4 - Por Motivo de Encerramento (RelatorioRelacaoOrdensServicoConcluidas)
		if (Util.converterStringParaInteger(tipoRelatorioAcompanhamento).equals(
				GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_RELATORIO_ANALITICO)) {
			
			RelatorioRelacaoOrdensServicoEncerradasPendentes relatorio = 
				new RelatorioRelacaoOrdensServicoEncerradasPendentes(
					(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorio.addParametro("tipoOrdem", tipoOrdem);
			relatorio.addParametro("situacaoOrdemServico", situacaoOrdemServico);
			relatorio.addParametro("idMotivoEncerramento", idMotivoEncerramento);
			relatorio.addParametro("descricaoMotivoEncerramento", descricaoMotivoEncerramento);
			relatorio.addParametro("firma", firma);
			relatorio.addParametro("nomeFirma", nomeFirma);
			relatorio.addParametro("idLocalidadeInicial", idLocalidadeInicial);
			relatorio.addParametro("idLocalidadeFinal", idLocalidadeFinal);		
			relatorio.addParametro("idSetorComercialInicial", idSetorComercialInicial);
			relatorio.addParametro("idSetorComercialFinal", idSetorComercialFinal);
			relatorio.addParametro("codigoSetorComercialInicial", codigoSetorComercialInicial);
			relatorio.addParametro("codigoSetorComercialFinal", codigoSetorComercialFinal);			
			relatorio.addParametro("dataEncerramentoInicial", dataEncerramentoInicial);
			relatorio.addParametro("dataEncerramentoFinal", dataEncerramentoFinal);
			relatorio.addParametro("tipoRelatorioAcompanhamento", tipoRelatorioAcompanhamento);
			
			relatorio.addParametro("codigoQuadraInicial", codigoQuadraInicial);
			relatorio.addParametro("codigoQuadraFinal", codigoQuadraFinal);
			relatorio.addParametro("codigoRotaInicial", codigoRotaInicial);
			relatorio.addParametro("codigoRotaFinal", codigoRotaFinal);
			relatorio.addParametro("sequenciaRotaInicial", sequenciaRotaInicial);
			relatorio.addParametro("sequenciaRotaFinal", sequenciaRotaFinal);
			
			if (tipoGeracaoRelatorio == null) {
				tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));
			
			retorno = processarExibicaoRelatorio(relatorio,
					tipoGeracaoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		}else if (Util.converterStringParaInteger(tipoRelatorioAcompanhamento).equals(
				GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_RELATORIO_SINTETICO)) {
			
			RelatorioResumoOrdensServicoEncerradasPendentes relatorio = 
				new RelatorioResumoOrdensServicoEncerradasPendentes(
					(Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

			relatorio.addParametro("tipoOrdem", tipoOrdem);
			relatorio.addParametro("situacaoOrdemServico", situacaoOrdemServico);
			relatorio.addParametro("idMotivoEncerramento", idMotivoEncerramento);
			relatorio.addParametro("descricaoMotivoEncerramento", descricaoMotivoEncerramento);
			relatorio.addParametro("firma", firma);
			relatorio.addParametro("nomeFirma", nomeFirma);
			relatorio.addParametro("idLocalidadeInicial", idLocalidadeInicial);
			relatorio.addParametro("idLocalidadeFinal", idLocalidadeFinal);			
			relatorio.addParametro("idSetorComercialInicial", idSetorComercialInicial);
			relatorio.addParametro("idSetorComercialFinal", idSetorComercialFinal);
			relatorio.addParametro("codigoSetorComercialInicial", codigoSetorComercialInicial);
			relatorio.addParametro("codigoSetorComercialFinal", codigoSetorComercialFinal);			
			relatorio.addParametro("dataEncerramentoInicial", dataEncerramentoInicial);
			relatorio.addParametro("dataEncerramentoFinal", dataEncerramentoFinal);
			relatorio.addParametro("tipoRelatorioAcompanhamento", tipoRelatorioAcompanhamento);

			relatorio.addParametro("codigoQuadraInicial", codigoQuadraInicial);
			relatorio.addParametro("codigoQuadraFinal", codigoQuadraFinal);
			relatorio.addParametro("codigoRotaInicial", codigoRotaInicial);
			relatorio.addParametro("codigoRotaFinal", codigoRotaFinal);
			relatorio.addParametro("sequenciaRotaInicial", sequenciaRotaInicial);
			relatorio.addParametro("sequenciaRotaFinal", sequenciaRotaFinal);
			
			if (tipoGeracaoRelatorio == null) {
				tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));
			
			retorno = processarExibicaoRelatorio(relatorio,
					tipoGeracaoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
		}else if (Util.converterStringParaInteger(tipoRelatorioAcompanhamento).equals(
				GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_RELATORIO_POR_LOCAL_INSTALACAO)) {
			
			RelatorioRelacaoOrdensServicoConcluidas relatorio = 
				new RelatorioRelacaoOrdensServicoConcluidas((Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"),
						ConstantesRelatorios.RELATORIO_RELACAO_ORDENS_SERVICO_ENCERRADAS_PENDENTES);
			
			relatorio.addParametro("tipoOrdem", tipoOrdem);
			relatorio.addParametro("situacaoOrdemServico", "ENCERRADAS");
			relatorio.addParametro("idMotivoEncerramento", AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO);
			relatorio.addParametro("descricaoMotivoEncerramento", descricaoMotivoEncerramento);
			relatorio.addParametro("firma", firma);
			relatorio.addParametro("nomeFirma", nomeFirma);
			relatorio.addParametro("idLocalidadeInicial", idLocalidadeInicial);
			relatorio.addParametro("idLocalidadeFinal", idLocalidadeFinal);			
			relatorio.addParametro("idSetorComercialInicial", idSetorComercialInicial);
			relatorio.addParametro("idSetorComercialFinal", idSetorComercialFinal);
			relatorio.addParametro("codigoSetorComercialInicial", codigoSetorComercialInicial);
			relatorio.addParametro("codigoSetorComercialFinal", codigoSetorComercialFinal);			
			relatorio.addParametro("dataEncerramentoInicial", dataEncerramentoInicial);
			relatorio.addParametro("dataEncerramentoFinal", dataEncerramentoFinal);
			relatorio.addParametro("tipoRelatorioAcompanhamento", tipoRelatorioAcompanhamento);
			
			relatorio.addParametro("codigoQuadraInicial", codigoQuadraInicial);
			relatorio.addParametro("codigoQuadraFinal", codigoQuadraFinal);
			relatorio.addParametro("codigoRotaInicial", codigoRotaInicial);
			relatorio.addParametro("codigoRotaFinal", codigoRotaFinal);
			relatorio.addParametro("sequenciaRotaInicial", sequenciaRotaInicial);
			relatorio.addParametro("sequenciaRotaFinal", sequenciaRotaFinal);
			
			if (tipoGeracaoRelatorio == null) {
				tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));
			
			retorno = processarExibicaoRelatorio(relatorio,
					tipoGeracaoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
						
		}else if (Util.converterStringParaInteger(tipoRelatorioAcompanhamento).equals(
				GerarRelatorioAcompanhamentoOrdemServicoHidrometroActionForm.TIPO_RELATORIO_POR_MOTIVO_ENCERRAMENTO)) {
			
			RelatorioRelacaoOrdensServicoConcluidas relatorio = 
				new RelatorioRelacaoOrdensServicoConcluidas((Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"),
						ConstantesRelatorios.RELATORIO_RELACAO_ORDENS_SERVICO_ENCERRADAS_MOTIVO_ENCERRAMENTO);
			
			relatorio.addParametro("tipoOrdem", tipoOrdem);
			relatorio.addParametro("situacaoOrdemServico", "ENCERRADAS");
			relatorio.addParametro("idMotivoEncerramento", idMotivoEncerramento);
			relatorio.addParametro("descricaoMotivoEncerramento", descricaoMotivoEncerramento);
			relatorio.addParametro("firma", firma);
			relatorio.addParametro("nomeFirma", nomeFirma);
			relatorio.addParametro("idLocalidadeInicial", idLocalidadeInicial);
			relatorio.addParametro("idLocalidadeFinal", idLocalidadeFinal);			
			relatorio.addParametro("idSetorComercialInicial", idSetorComercialInicial);
			relatorio.addParametro("idSetorComercialFinal", idSetorComercialFinal);
			relatorio.addParametro("codigoSetorComercialInicial", codigoSetorComercialInicial);
			relatorio.addParametro("codigoSetorComercialFinal", codigoSetorComercialFinal);			
			relatorio.addParametro("dataEncerramentoInicial", dataEncerramentoInicial);
			relatorio.addParametro("dataEncerramentoFinal", dataEncerramentoFinal);
			relatorio.addParametro("tipoRelatorioAcompanhamento", tipoRelatorioAcompanhamento);
			
			relatorio.addParametro("codigoQuadraInicial", codigoQuadraInicial);
			relatorio.addParametro("codigoQuadraFinal", codigoQuadraFinal);
			relatorio.addParametro("codigoRotaInicial", codigoRotaInicial);
			relatorio.addParametro("codigoRotaFinal", codigoRotaFinal);
			relatorio.addParametro("sequenciaRotaInicial", sequenciaRotaInicial);
			relatorio.addParametro("sequenciaRotaFinal", sequenciaRotaFinal);
			
			if (tipoGeracaoRelatorio == null) {
				tipoGeracaoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoGeracaoRelatorio));
			
			retorno = processarExibicaoRelatorio(relatorio,
					tipoGeracaoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
						
		}
		return retorno;
	}

	private Localidade validarLocalidade(String localidade) {
		Localidade retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		FiltroLocalidade filtroLocalidade = null;
		Collection<Localidade> colecaoLocalidade = null;
		
		// Verifica se a Localidade existe
		if (localidade != null && !localidade.equals("")) {
			filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidade));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Iterator<Localidade> iColecaoLocalidade = colecaoLocalidade.iterator();
				retorno = iColecaoLocalidade.next();
			}else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade");
			}
		}
		
		return retorno;
	}
	
	private SetorComercial validarSetorComercial(String localidade, String codigoSetorComercial) {
		SetorComercial retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		FiltroSetorComercial filtroSetorComercial = null;
		Collection<SetorComercial> colecaoSetorComercial = null;
		
		// Verifica se o Setor existe
		if (codigoSetorComercial != null && !codigoSetorComercial.equals("")) {
			filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, localidade));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
				Iterator<SetorComercial> iColecaoSetorComercial = colecaoSetorComercial.iterator();
				retorno = iColecaoSetorComercial.next();
			}else {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
			}
		}
		return retorno;
	}
}
