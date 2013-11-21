package gcom.relatorio.atendimentopublico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**[UC1177] Gerar Relatório de Ordens de Serviço por Situação
 * 
 * @author Diogo Peixoto
 * @since 03/06/2011
 *
 */

public class RelatorioOSSituacao extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioOSSituacao(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public Object executar() throws TarefaException {
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		RelatorioOSSituacaoHelper helper = (RelatorioOSSituacaoHelper)getParametro("dadosRelatorio");

		if(helper == null || Util.isVazioOrNulo(helper.getBeans())){
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		Collection<RelatorioOSSituacaoBean> beans = helper.getBeans();
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String dataReferencia = (String) getParametro("dataReferencia");
		String tipoRelatorio = getParametro("tipoRelatorio").toString();
		Integer situacaoOS = Integer.valueOf((String)getParametro("situacaoOS"));
		String descricaoEmpresa = (String) getParametro("descricaoEmpresa");
		String numeroContrato = (String) getParametro("numeroContrato");
		String cobrancaGrupo = (String) getParametro("cobrancaGrupo");
		
		String strSituacaoOS = null;
		switch (situacaoOS) {
		case 1:
			strSituacaoOS = "Descontadas";
			break;
		case 2:
			strSituacaoOS = "Encerradas";
			break;
		case 3:
			strSituacaoOS = "Executadas";
			break;
		case 4:
			strSituacaoOS = "Fiscalizadas";
			break;
		case 5:
			strSituacaoOS = "Justificadas";
			break;
		case 6:
			strSituacaoOS = "Penalizadas por Fiscalização";
			break;
		case 7:
			strSituacaoOS = "Penalizadas por Decurso de Prazo";
			break;
		case 8:
			strSituacaoOS = "Todas";
			break;
		case 9:
			strSituacaoOS = "Encerradas com Execução";
			break;
		case 10:
			strSituacaoOS = "Encerradas por Decurso de Prazo";
			break;
		case 11:
			strSituacaoOS = "Pendentes";
			break;
		case 12:
			strSituacaoOS = "Fiscalizadas";
			break; 
		case 13:
			strSituacaoOS = "Todas";
			break;
		default:
			strSituacaoOS = "";
			break;
		}
		
		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap<String, String>();
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("numeroRelatorio", "R1177");
		parametros.put("periodoReferencia", dataReferencia);
		parametros.put("tipoRelatorio", tipoRelatorio);
		parametros.put("situacaoOS", strSituacaoOS);
		parametros.put("descricaoEmpresa", descricaoEmpresa);
		parametros.put("numeroContrato", numeroContrato);

		parametros.put("gerenciaRegional", helper.getGerenciaRegional());
		parametros.put("setorComercial", helper.getSetorComercial());
		parametros.put("quadra", helper.getQuadra());
		parametros.put("unidadeNegocio", helper.getUnidadeNegocio());
		parametros.put("grupoCobranca", cobrancaGrupo);
		parametros.put("localidadeEloPolo", helper.getLocalidadeEloPolo());
		parametros.put("localidade", helper.getLocalidade());
		parametros.put("servicoTipo", helper.getTipoServico());
		
		byte[] retorno = null;

		RelatorioDataSource ds = new RelatorioDataSource((List<RelatorioOSSituacaoBean>) beans);
		try {
			if(tipoRelatorio.equalsIgnoreCase("Analítico")){
				retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_OS_SITUACAO_ANALITICO, parametros, ds, tipoFormatoRelatorio);
				persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_OS_SITUACAO_ANALITICO, idFuncionalidadeIniciada);
			}else if(tipoRelatorio.equalsIgnoreCase("Sintético")){
				retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_OS_SITUACAO_SINTETICO, parametros, ds, tipoFormatoRelatorio);
				persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_OS_SITUACAO_SINTETICO, idFuncionalidadeIniciada);
			}
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioOSSituacao", this);
	}
}