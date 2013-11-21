package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.bean.PesquisarAvisoBancarioPorContaCorrenteHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC0829] Gerar Relatório Avisos Bancarios Por Conta Corrente
 * 
 * @author Victor Cisneiros
 * @date 21/08/2008
 */
public class RelatorioAvisoBancarioPorContaCorrente extends TarefaRelatorio {

	public RelatorioAvisoBancarioPorContaCorrente(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_AVISO_BANCARIO_POR_CONTA_CORRENTE);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoRelatorio", "RF0829");
		 
		Integer mesAno = (Integer) getParametro("mesAno");
		Integer idBanco = (Integer) getParametro("idBanco");
		Integer idContaBancaria = (Integer) getParametro("idContaBancaria");
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		
		parametros.put("mesAno", mesAno.toString().substring(4) + "/" + mesAno.toString().substring(0, 4));
		
		PesquisarAvisoBancarioPorContaCorrenteHelper filtro = new PesquisarAvisoBancarioPorContaCorrenteHelper();
		filtro.setMesAno(mesAno);
		filtro.setIdBanco(idBanco);
		filtro.setIdContaBancaria(idContaBancaria);
		
		Collection<RelatorioAvisoBancarioPorContaCorrenteBean> pesquisa = fachada.pesquisarAvisoBancarioPorContaCorrente(filtro);
		if (pesquisa.isEmpty()) {
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		Integer idAntigoBanco = 0;
		Integer idAntigoConta = 0;
		long dataAntigaAviso = 0;
		
		for (RelatorioAvisoBancarioPorContaCorrenteBean bean : pesquisa) {
			
			if (bean.getIdAviso() == null || bean.getIdAviso().equals(0) || bean.getDataRealizada() == null) continue;
			
			Integer idAtualBanco = bean.getIdBanco();
			Integer idAtualConta = bean.getIdConta();
			
			/*
			 * Se a data for igual à data da linha anterior para um mesmo banco 
			 * e conta, então não mostrar a data da linha atual no relatorio
			 */ 
			
			if (!idAntigoBanco.equals(idAtualBanco) || 
				!idAntigoConta.equals(idAtualConta)) {
				idAntigoBanco = idAtualBanco;
				idAntigoConta = idAtualConta;
				dataAntigaAviso = 0;
			}
			
			long dataAtualAviso = bean.getDataRealizada().getTime();
			if (dataAntigaAviso == dataAtualAviso) {
				bean.setDataRealizada(null);
			} else {
				dataAntigaAviso = dataAtualAviso;
			}
			
		}
		
		List<RelatorioAvisoBancarioPorContaCorrenteBean> beans = new ArrayList<RelatorioAvisoBancarioPorContaCorrenteBean>();
		beans.addAll(pesquisa);
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_AVISO_BANCARIO_POR_CONTA_CORRENTE,
				parametros, new RelatorioDataSource(beans), tipoRelatorio);
		
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatotorioAvisoBancarioPorContaCorrente", this);
	}

}
