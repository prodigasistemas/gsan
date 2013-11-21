package gcom.relatorio.cobranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeCronograma;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * Descrição da classe
 * Classe responsável pelo processamento dos
 * parametros informados e consequente 
 * montagem dos registros exibidos posteriormente
 * pelo relatório
 * 
 * @author Anderson Italo
 * @date 09/10/2009
 */
public class RelatorioComandosAcaoCobrancaCronograma extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioComandosAcaoCobrancaCronograma(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_COMANDOS_ACAO_COBRANCA_CRONOGRAMA);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		 
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		
		List<RelatorioComandosAcaoCobrancaCronogramaBean> beans = new ArrayList<RelatorioComandosAcaoCobrancaCronogramaBean>();
		
		FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = (FiltroCobrancaAcaoAtividadeCronograma) getParametro("filtroCobrancaAcaoAtividadeCronograma");
		filtroCobrancaAcaoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ACAO_CRONOGRAMA);
		filtroCobrancaAcaoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_GRUPO_CRONOGRAMA_MES);
		filtroCobrancaAcaoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_GRUPO);
		
		Collection colecaoCobrancaAcaoAtividade = fachada.pesquisar(filtroCobrancaAcaoAtividadeCronograma, CobrancaAcaoAtividadeCronograma.class.getName());
		
		
		if (colecaoCobrancaAcaoAtividade != null){
			for (Iterator colecaoCobrancaAcaoAtividadeIterator = colecaoCobrancaAcaoAtividade.iterator(); colecaoCobrancaAcaoAtividadeIterator
					.hasNext();) {
				CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) colecaoCobrancaAcaoAtividadeIterator.next();
				RelatorioComandosAcaoCobrancaCronogramaBean bean = new RelatorioComandosAcaoCobrancaCronogramaBean();
				
				bean.setGrupo(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getCobrancaGrupo().getDescricao());
				bean.setReferencia(Util.formatarAnoMesParaMesAno(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaGrupoCronogramaMes().getAnoMesReferencia()));
				bean.setAcao(cobrancaAcaoAtividadeCronograma.getCobrancaAcaoCronograma().getCobrancaAcao().getDescricaoCobrancaAcao());
				bean.setAtividade(cobrancaAcaoAtividadeCronograma.getCobrancaAtividade().getDescricaoCobrancaAtividade());
				
				if (cobrancaAcaoAtividadeCronograma.getComando() != null){
					bean.setDataComando(Util.formatarDataComHora(cobrancaAcaoAtividadeCronograma.getComando()));
				}
				
				if (cobrancaAcaoAtividadeCronograma.getDataPrevista() != null){
					bean.setDataPrevista(Util.formatarDataComHora(cobrancaAcaoAtividadeCronograma.getDataPrevista()));
				}
				
				if (cobrancaAcaoAtividadeCronograma.getRealizacao() != null){
					bean.setDataRealizacao(Util.formatarDataComHora(cobrancaAcaoAtividadeCronograma.getRealizacao()));
				}
				
				if (cobrancaAcaoAtividadeCronograma.getQuantidadeDocumentos() != null 
						&& !cobrancaAcaoAtividadeCronograma.getQuantidadeDocumentos().equals(0)){
					bean.setQtdDocs(cobrancaAcaoAtividadeCronograma.getQuantidadeDocumentos().toString());
				}
				
				if (cobrancaAcaoAtividadeCronograma.getValorDocumentos() != null 
						&& !cobrancaAcaoAtividadeCronograma.getValorDocumentos().toEngineeringString().equals("0.00") ){
					bean.setValor(Util.formataBigDecimal(cobrancaAcaoAtividadeCronograma.getValorDocumentos(),2,true));
				}
				
				if (cobrancaAcaoAtividadeCronograma.getQuantidadeItensCobrados() != null
						&& !cobrancaAcaoAtividadeCronograma.getQuantidadeItensCobrados().equals(0)){
					bean.setQtdItens(cobrancaAcaoAtividadeCronograma.getQuantidadeItensCobrados().toString());
				}
				
				beans.add(bean);
				
			}
		}
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_COMANDOS_ACAO_COBRANCA_CRONOGRAMA,
				parametros, new RelatorioDataSource(beans), tipoRelatorio);
		
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;
		
		FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = (FiltroCobrancaAcaoAtividadeCronograma) getParametro("filtroCobrancaAcaoAtividadeCronograma");
		
		Fachada fachada = Fachada.getInstancia(); 
		
		Collection colecaoCobrancaAcaoAtividade = fachada.pesquisar(filtroCobrancaAcaoAtividadeCronograma, CobrancaAcaoAtividadeCronograma.class.getName());
		
		if (colecaoCobrancaAcaoAtividade != null){
			retorno = colecaoCobrancaAcaoAtividade.size();
		}

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioComandosAcaoCobrancaCronograma", this);
	}

}
