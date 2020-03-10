package gcom.gui.cobranca;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.api.GsanApi;
import gcom.cadastro.endereco.EnderecoTipo;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.DocumentoTipo;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.AvisoCorteDTO;
import gcom.relatorio.cobranca.AvisoCorteHelper;
import gcom.relatorio.cobranca.OrdemSuspensaoFornecimentoDTO;
import gcom.relatorio.cobranca.OrdemSuspensaoFornecimentoHelper;
import gcom.relatorio.cobranca.RelatorioComandoDocumentoCobranca;
import gcom.relatorio.cobranca.RelatorioNotificacaoDebito;
import gcom.seguranca.SegurancaParametro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.filtro.Filtro;

public class GerarRelatorioComandoDocumentoCobrancaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		
		String tipoEndereco = request.getParameter("tipoEndRelatorio");
		String tipoRelatorio = request.getParameter("tipoRelatorio");

		Integer idAcaoCronograma = getAcaoCronograma(request);
		Integer idAcaoComando = getAcaoComando(request);

		CobrancaAcao cobrancaAcao = getFachada().pesquisarAcaoCobrancaParaRelatorio(idAcaoComando, idAcaoCronograma);
		DocumentoTipo documentoTipo = getFachada().pesquisarTipoAcaoCobrancaParaRelatorio(idAcaoComando,idAcaoCronograma);

		if (isAvisoEnderecoAlternativo(tipoEndereco, documentoTipo)) {
			gerarAvisoCorteEnderecoAlternativo(response, idAcaoCronograma, idAcaoComando);

			return null;
		} else {
			TarefaRelatorio relatorio = null;
			
			if (isAvisoOuCorteAdministrativo(documentoTipo)) {
				relatorio = new RelatorioNotificacaoDebito(getUsuario(request));

			} else if (isOrdemSuspensaoFornecimentoComDebitos(documentoTipo)) {
				gerarOrdemSuspensaoFornecimento(response, idAcaoCronograma, idAcaoComando);
				
				return null;
			} else {
				relatorio = new RelatorioComandoDocumentoCobranca(getUsuario(request));
			}

			relatorio.addParametro("tipoEndRelatorio", tipoEndereco);
			relatorio.addParametro("idCobrancaAcaoCronograma", idAcaoCronograma);
			relatorio.addParametro("idCobrancaAcaoComando", idAcaoComando);
			relatorio.addParametro("cobrancaAcao", cobrancaAcao);

			if (tipoRelatorio == null)
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			relatorio.addParametro("quantidadeRelatorios", "2");

			return processarExibicaoRelatorio(relatorio, tipoRelatorio, request, response, mapping);
		}
	}

	private boolean isOrdemSuspensaoFornecimentoComDebitos(DocumentoTipo documentoTipo) {
		return documentoTipo != null && documentoTipo.getId().intValue() == DocumentoTipo.CORTE_FISICO;
	}

	private Usuario getUsuario(HttpServletRequest request) {
		return (Usuario) (request.getSession(false)).getAttribute("usuarioLogado");
	}

	private boolean isAvisoEnderecoAlternativo(String tipoEndereco, DocumentoTipo documentoTipo) {
		return Integer.valueOf(tipoEndereco) == EnderecoTipo.ALTERNATIVO && 
			   documentoTipo != null && 
			   documentoTipo.getId() == DocumentoTipo.AVISO_CORTE;
	}

	private boolean isAvisoOuCorteAdministrativo(DocumentoTipo documentoTipo) {
		return documentoTipo != null && 
			   (documentoTipo.getId() == DocumentoTipo.AVISO_CORTE || 
			    documentoTipo.getId() == DocumentoTipo.CORTE_ADMINISTRATIVO);
	}
	
	@SuppressWarnings("rawtypes")
	private SistemaParametro getParametros() {
		Filtro filtro = new FiltroSistemaParametro();
		filtro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("cep");

		Collection colecao = getFachada().pesquisar(filtro, SistemaParametro.class.getName());

		return (SistemaParametro) colecao.iterator().next();
	}

	private void gerarAvisoCorteEnderecoAlternativo(HttpServletResponse response, Integer idAcaoCronograma, Integer idAcaoComando) {

		List<AvisoCorteDTO> avisos = getFachada().gerarAvisoCorteEnderecoAlternativo(idAcaoCronograma, idAcaoComando);

		if (avisos != null && !avisos.isEmpty()) {
			try {
				AvisoCorteHelper helper = new AvisoCorteHelper(getParametros(), avisos);

				String url = getFachada().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_AVISO_CORTE.toString());

				GsanApi api = new GsanApi(url);
				api.invoke(helper);
				api.download(helper.getNomeArquivo(), response);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ActionServletException("atencao.erro_baixar_aviso_corte");
			}
		} else {
			throw new ActionServletException("atencao.aviso_corte_sem_dados");
		}
	}

	private void gerarOrdemSuspensaoFornecimento(HttpServletResponse response, Integer idAcaoCronograma, Integer idAcaoComando) {

		List<OrdemSuspensaoFornecimentoDTO> ordens = getFachada().gerarOrdemSuspensaoFornecimento(idAcaoCronograma, idAcaoComando);

		if (ordens != null && !ordens.isEmpty()) {
			try {
				OrdemSuspensaoFornecimentoHelper helper = new OrdemSuspensaoFornecimentoHelper(getParametros(), ordens);

				String url = getFachada().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_ORDEM_SUSPENSAO_FORNECIMENTO.toString());

				GsanApi api = new GsanApi(url);
				api.invoke(helper);
				api.download(helper.getNomeArquivo(), response);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ActionServletException("atencao.erro_baixar_ordem_suspensao_fornecimento");
			}
		} else {
			throw new ActionServletException("atencao.ordem_suspensao_fornecimento_sem_dados");
		}
	}

	private Integer getAcaoComando(HttpServletRequest request) {
		String idAcaoAtividadeComando = request.getParameter("idCobrancaAcaoAtividadeComando");
		
		if (idAcaoAtividadeComando != null && !idAcaoAtividadeComando.trim().equals("")) {
			return new Integer(idAcaoAtividadeComando);
		} else {
			return null;
		}
	}

	private Integer getAcaoCronograma(HttpServletRequest request) {
		String idAcaoAtividadeCronograma = request.getParameter("idCobrancaAcaoAtividadeCronograma");
		
		if (idAcaoAtividadeCronograma != null && !idAcaoAtividadeCronograma.trim().equals("")) {
			return new Integer(idAcaoAtividadeCronograma);
		} else {
			return null;
		}
	}
}
