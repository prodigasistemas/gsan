package gcom.gui.cobranca;

import gcom.api.GsanApi;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.DocumentoTipo;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.AvisoCorteDTO;
import gcom.relatorio.cobranca.AvisoCorteHelper;
import gcom.relatorio.cobranca.RelatorioComandoDocumentoCobranca;
import gcom.relatorio.cobranca.RelatorioNotificacaoDebito;
import gcom.seguranca.SegurancaParametro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.filtro.Filtro;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioComandoDocumentoCobrancaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		String tipoEndereco = request.getParameter("tipoEndRelatorio");
		String tipoRelatorio = request.getParameter("tipoRelatorio");

		Integer idAcaoCronograma = getAcaoCronograma(request);
		Integer idAcaoComando = getAcaoComando(request);
		
		CobrancaAcao cobrancaAcao = getFachada().pesquisarAcaoCobrancaParaRelatorio(idAcaoComando, idAcaoCronograma);
		DocumentoTipo documentoTipo = getFachada().pesquisarTipoAcaoCobrancaParaRelatorio(idAcaoComando, idAcaoCronograma);

		if (tipoEndereco.equals("3") && documentoTipo != null && (documentoTipo.getId().intValue() == DocumentoTipo.AVISO_CORTE)) {
			gerarAvisoCorteEnderecoAlternativo(response, idAcaoCronograma, idAcaoComando);
			
			return null;
		} else {
			TarefaRelatorio relatorio = null;
			if (documentoTipo != null && (documentoTipo.getId().intValue() == DocumentoTipo.AVISO_CORTE || documentoTipo.getId().intValue() == DocumentoTipo.CORTE_ADMINISTRATIVO)) {
				relatorio = new RelatorioNotificacaoDebito((Usuario) (request.getSession(false)).getAttribute("usuarioLogado"));
			} else {
				relatorio = new RelatorioComandoDocumentoCobranca((Usuario) (request.getSession(false)).getAttribute("usuarioLogado"));
			}
			
			relatorio.addParametro("tipoEndRelatorio", tipoEndereco);
			relatorio.addParametro("idCobrancaAcaoCronograma", idAcaoCronograma);
			relatorio.addParametro("idCobrancaAcaoComando", idAcaoComando);
			relatorio.addParametro("cobrancaAcao", cobrancaAcao);
			
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			
			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			relatorio.addParametro("quantidadeRelatorios", "2");
			
			return processarExibicaoRelatorio(relatorio, tipoRelatorio, request, response, mapping);
		}
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
		
		Collection colecao = getFachada().pesquisar(filtro,SistemaParametro.class.getName());
		
		return (SistemaParametro) colecao.iterator().next();
	}

	private void gerarAvisoCorteEnderecoAlternativo(HttpServletResponse response, Integer idAcaoCronograma, Integer idAcaoComando) {
		
		List<AvisoCorteDTO> avisos = getFachada().gerarAvisoCorteEnderecoAlternativo(idAcaoCronograma, idAcaoComando);
		
		try {
			if (avisos != null && !avisos.isEmpty()) {
				SistemaParametro sistemaParametro = getParametros();
				AvisoCorteHelper helper = new AvisoCorteHelper(sistemaParametro, avisos);

				String url = getFachada().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_AVISO_CORTE.toString());

				GsanApi api = new GsanApi(url);
				api.invoke(helper);
				api.download(helper.getNomeArquivo(), response);
			} else {
				throw new ActionServletException("atencao.conta_segunda_via_sem_dados");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_conta_segunda_via");
		}
	}

	private Integer getAcaoComando(HttpServletRequest request) {
		String idAcaoAtividadeComando = request.getParameter("idCobrancaAcaoAtividadeComando");
		Integer idAcaoComando = null;
		if (idAcaoAtividadeComando != null && !idAcaoAtividadeComando.trim().equals("")) {
			idAcaoComando = new Integer(idAcaoAtividadeComando);
		}
		return idAcaoComando;
	}

	private Integer getAcaoCronograma(HttpServletRequest request) {
		Integer idAcaoCronograma = null;
		String idAcaoAtividadeCronograma = request.getParameter("idCobrancaAcaoAtividadeCronograma");
		if (idAcaoAtividadeCronograma != null && !idAcaoAtividadeCronograma.trim().equals("")) {
			idAcaoCronograma = new Integer(idAcaoAtividadeCronograma);
		}
		return idAcaoCronograma;
	}
}
