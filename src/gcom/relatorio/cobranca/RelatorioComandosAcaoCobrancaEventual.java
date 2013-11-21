package gcom.relatorio.cobranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
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
public class RelatorioComandosAcaoCobrancaEventual extends TarefaRelatorio{
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioComandosAcaoCobrancaEventual(Usuario usuario){
		super(usuario, ConstantesRelatorios.RELATORIO_COMANDOS_ACAO_COBRANCA_EVENTUAL);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		 
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		
		List<RelatorioComandosAcaoCobrancaEventualBean> beans = new ArrayList<RelatorioComandosAcaoCobrancaEventualBean>();
		
		FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = (FiltroCobrancaAcaoAtividadeComando) getParametro("filtroCobrancaAcaoAtividadeComando");
		filtroCobrancaAcaoAtividadeComando.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeComando.COBRANCA_ACAO);
		
		Collection colecaoCobrancaAcaoAtividade = fachada.pesquisar(filtroCobrancaAcaoAtividadeComando, CobrancaAcaoAtividadeComando.class.getName());
		
		
		if (colecaoCobrancaAcaoAtividade != null){
			for (Iterator colecaoCobrancaAcaoAtividadeIterator = colecaoCobrancaAcaoAtividade.iterator(); colecaoCobrancaAcaoAtividadeIterator
					.hasNext();) {
				CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando) colecaoCobrancaAcaoAtividadeIterator.next();
				RelatorioComandosAcaoCobrancaEventualBean bean = new RelatorioComandosAcaoCobrancaEventualBean();
				
				bean.setAcao(cobrancaAcaoAtividadeComando.getCobrancaAcao().getDescricaoCobrancaAcao());
				bean.setAtividade(cobrancaAcaoAtividadeComando.getCobrancaAtividade().getDescricaoCobrancaAtividade());
				
				if (cobrancaAcaoAtividadeComando.getDescricaoTitulo() != null){
					bean.setTitulo(cobrancaAcaoAtividadeComando.getDescricaoTitulo());
				}
				
				if (cobrancaAcaoAtividadeComando.getComando() != null){
					bean.setDataComando(Util.formatarDataComHora(cobrancaAcaoAtividadeComando.getComando()));
				}
				
				if (cobrancaAcaoAtividadeComando.getDataEncerramentoPrevista() != null){
					bean.setDataPrevista(Util.formatarDataComHora(cobrancaAcaoAtividadeComando.getDataEncerramentoPrevista()));
				}
				
				if (cobrancaAcaoAtividadeComando.getRealizacao() != null){
					bean.setDataRealizacao(Util.formatarDataComHora(cobrancaAcaoAtividadeComando.getRealizacao()));
				}
				
				if (cobrancaAcaoAtividadeComando.getQuantidadeDocumentos() != null 
						&& !cobrancaAcaoAtividadeComando.getQuantidadeDocumentos().equals(0)){
					bean.setQtdDocs(cobrancaAcaoAtividadeComando.getQuantidadeDocumentos().toString());
				}
				
				if (cobrancaAcaoAtividadeComando.getValorDocumentos() != null 
						&& !cobrancaAcaoAtividadeComando.getValorDocumentos().toEngineeringString().equals("0.00") ){
					bean.setValor(Util.formataBigDecimal(cobrancaAcaoAtividadeComando.getValorDocumentos(),2,true));
				}
				
				if (cobrancaAcaoAtividadeComando.getQuantidadeItensCobrados() != null
						&& !cobrancaAcaoAtividadeComando.getQuantidadeItensCobrados().equals(0)){
					bean.setQtdItens(cobrancaAcaoAtividadeComando.getQuantidadeItensCobrados().toString());
				}
				
				if (cobrancaAcaoAtividadeComando.getDataEncerramentoRealizada() != null){
					bean.setDataEncerramentoRealizada(Util.formatarDataComHora(cobrancaAcaoAtividadeComando.getDataEncerramentoRealizada()));
				}
				
				// CRC2914
				if (cobrancaAcaoAtividadeComando.getLocalidadeInicial() != null){
					
					bean.setNomeLocalidadeInicial(cobrancaAcaoAtividadeComando.getLocalidadeInicial().getDescricao());
					bean.setIdLocalidadeInicial(cobrancaAcaoAtividadeComando.getLocalidadeInicial().getId().toString());
				}
				
				if (cobrancaAcaoAtividadeComando.getLocalidadeFinal() != null){
					
					bean.setNomeLocalidadeFinal(cobrancaAcaoAtividadeComando.getLocalidadeFinal().getDescricao());
					bean.setIdLocalidadeFinal(cobrancaAcaoAtividadeComando.getLocalidadeFinal().getId().toString());
				}
				
				if (cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial() != null){
					
					bean.setIdSetorComercialInicial(cobrancaAcaoAtividadeComando.getCodigoSetorComercialInicial().toString());
				}
				if (cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal() != null) {
					
					bean.setIdSetorComercialFinal(cobrancaAcaoAtividadeComando.getCodigoSetorComercialFinal().toString());
				}
				
				beans.add(bean);
				
			}
		}
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_COMANDOS_ACAO_COBRANCA_EVENTUAL,
				parametros, new RelatorioDataSource(beans), tipoRelatorio);
		
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;
		
		FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando = (FiltroCobrancaAcaoAtividadeComando) getParametro("filtroCobrancaAcaoAtividadeComando");
		
		Fachada fachada = Fachada.getInstancia(); 
		
		Collection colecaoCobrancaAcaoAtividade = fachada.pesquisar(filtroCobrancaAcaoAtividadeComando, CobrancaAcaoAtividadeComando.class.getName());
		
		
		if (colecaoCobrancaAcaoAtividade != null){
			retorno = colecaoCobrancaAcaoAtividade.size();
		}

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioComandosAcaoCobrancaEventual", this);
	}

}
