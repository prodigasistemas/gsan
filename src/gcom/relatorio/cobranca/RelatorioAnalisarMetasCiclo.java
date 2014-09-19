package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CicloMeta;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.FiltroCicloMeta;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o Relatório de Débitos
 * 
 * @author Genival Barbosa
 * @date 21/07/2009
 * 
 */
public class RelatorioAnalisarMetasCiclo extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioAnalisarMetasCiclo(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANALISAR_METAS_CICLO);
	}

	@Deprecated
	public RelatorioAnalisarMetasCiclo() {
		super(null, "");
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		
		Object obj = null;
		Object[] dados = null;
		
		String idCicloMeta = (String) getParametro("idCicloMeta");
		String idAcaoCobranca = (String) getParametro("idAcaoCobranca");
		String anoMesCobranca = (String) getParametro("anoMesCobranca");
		
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples(FiltroCobrancaAcao.ID, 
				idAcaoCobranca));

		Collection colecaoCobrancaAcao = fachada.pesquisar(
				filtroCobrancaAcao, CobrancaAcao.class.getName());
		
		CobrancaAcao cobrancaAcao = (CobrancaAcao) Util.retonarObjetoDeColecao(colecaoCobrancaAcao);
		String acaoCobranca = cobrancaAcao.getDescricaoCobrancaAcao();
		
	
		
		FiltroCicloMeta filtroCiclo = new FiltroCicloMeta();
		if ((idCicloMeta == null || idCicloMeta.equals("")) && 
				anoMesCobranca != null || anoMesCobranca.equals("")){
			filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.COBRANCA_ACAO_ID, 
					idAcaoCobranca));
			filtroCiclo.adicionarParametro(new ParametroSimples(FiltroCicloMeta.ANO_MES_REFERENCIA, 
					Util.formatarMesAnoComBarraParaAnoMes(anoMesCobranca)));
			filtroCiclo.adicionarCaminhoParaCarregamentoEntidade(FiltroCicloMeta.COBRANCA_ACAO);

		}
		
		Collection colecaoCicloMetas = fachada.pesquisar(filtroCiclo, CicloMeta.class.getName());
		CicloMeta cicloMeta = new CicloMeta();
		
		
		cicloMeta = (CicloMeta) Util.retonarObjetoDeColecao(colecaoCicloMetas);
		//colocar auqi a parte referente ao ciclometa de exibirAnalisarMetasCicloAction
		byte[] retorno = null;
		if(cicloMeta != null) {
			
		
			List colecaoCicloMetaGrupo = fachada
					.consultarColecaoCicloMetaGrupoRelatorio(cicloMeta);
	
			
			Collection<RelatorioAnalisarMetasCicloBean> colecaoBean = new ArrayList<RelatorioAnalisarMetasCicloBean>();
			
	//		 pra cada objeto obter a categoria
			for (int i = 0; i < colecaoCicloMetaGrupo.size(); i++) {
				obj = colecaoCicloMetaGrupo.get(i);
	
				if (obj instanceof Object[]) {
					dados = (Object[]) obj;
					
					String idGerencia = dados[0].toString();
					String nomeGerencia = dados[1].toString();
					String idUnidade = dados[2].toString();
					String nomeUnidade = dados[3].toString();				
					String idLocalidade = dados[4].toString();
					String nomeLocalidade = dados[5].toString();
					String metaOriginal = dados[6].toString();
					String metaAtual = dados[7].toString();
					String qtdImoveisSituacao = "0";
					if(dados[8] != null){
						qtdImoveisSituacao = dados[8].toString();
					}
					String qtdTotalRealizada = "0";
					if(dados[9] != null){
						qtdTotalRealizada = dados[9].toString();
					}
					String valorTotalRealizada = "0";
					if(dados[10] != null){
						valorTotalRealizada = dados[10].toString();
					}
					String qtdTotalRestante = "0";
					if(dados[11] != null) {
						qtdTotalRestante = dados[11].toString();
					}				
					String valorTotalRestante = "0";
					if(dados[12] != null){
						valorTotalRestante = dados[12].toString();
					}
					
					RelatorioAnalisarMetasCicloBean relatorioAnalisarMetasCicloBean = 
						new RelatorioAnalisarMetasCicloBean(
							nomeGerencia, 
							idGerencia, 
							nomeUnidade, 
							idUnidade, 
							nomeLocalidade,
							idLocalidade , 
							metaOriginal,
							metaAtual, 
							qtdImoveisSituacao, 
							qtdTotalRealizada,
							valorTotalRealizada, 
							qtdTotalRestante, 
							valorTotalRestante,
							acaoCobranca, 
							anoMesCobranca);
	
					colecaoBean.add(relatorioAnalisarMetasCicloBean);
				}
				
			}
			
			int tipoFormatoRelatorio = (Integer) getParametro("tipoRelatorio");
			
			//Parâmetros do relatório
			Map parametros = new HashMap();
			
			
			//Recupera o AnoMesFaturamento de Sistema Parametro
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
	
			RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);
	
			// exporta o relatório em pdf e retorna o array de bytes
			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_ANALISAR_METAS_CICLO,
					parametros, ds, tipoFormatoRelatorio);
		}else {
			throw new ActionServletException(
            "atencao.pesquisa.nenhumresultado");
		}
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ANALISAR_METAS_CICLO, 0);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------
		// retorna o relatório gerado
		
		return retorno;

	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		

	}
}
