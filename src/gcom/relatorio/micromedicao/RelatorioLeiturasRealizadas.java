package gcom.relatorio.micromedicao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioAnaliticoFaturamentoBean;
import gcom.relatorio.faturamento.RelatorioAnaliticoFaturamentoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class RelatorioLeiturasRealizadas extends TarefaRelatorio {

	public RelatorioLeiturasRealizadas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_LEITURAS_REALIZADAS);
		
	}

	@Override
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		String mesAno = (String) getParametro("mesAno");
		String idGrupoFaturamento = (String) getParametro("idGrupoFaturamento");
		Collection colecaoLeiturasRealizadas = (Collection) getParametro("colecaoLeiturasRealizadas");
				
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("mesAno",mesAno);
		parametros.put("idGrupoFaturamento",idGrupoFaturamento);
		parametros.put("tipoFormatoRelatorio", "R0001-C");
		
		Collection dadosRelatorio = colecaoLeiturasRealizadas;

		Collection<RelatorioLeiturasRealizadasBean> colecaoBean = this
						.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_LEITURAS_REALIZADAS, 
				parametros, ds,	tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_LEITURAS_REALIZADAS,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	
	}

	private Collection<RelatorioLeiturasRealizadasBean> inicializarBeanRelatorio(
			Collection colecaoLeiturasRealizadas) {
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<RelatorioLeiturasRealizadasBean> retorno = new ArrayList<RelatorioLeiturasRealizadasBean>();

		String anoMesReferencia;
		String grupo;
		String empresa;
		String localidade;
		String situacaoLeitura;
		Integer qtdRotasEmCampo;
		Integer qtdRotasTransmitidas;
		Integer qtdRotasFinalizadasPeloUsuario;
		Integer qtdTotalLocal;
		Integer qtdImoveisEmCampo;
		Integer qtdImoveisTransmitidosDeRotaParcial;
		Iterator iter = colecaoLeiturasRealizadas.iterator();
		
		while (iter.hasNext()) {
			
			RelatorioLeiturasRealizadasHelper rel = (RelatorioLeiturasRealizadasHelper) iter.next();

			grupo = rel.getGrupo();
			empresa = rel.getEmpresa();
			localidade = rel.getLocalidade();
			qtdRotasEmCampo = rel.getQtdRotasEmCampo();
			qtdRotasFinalizadasPeloUsuario = rel.getQtdRotasFinalizadasPeloUsuario();
			qtdRotasTransmitidas = rel.getQtdRotasTransmitidas();
			qtdTotalLocal = rel.getQtdTotalLocal();
			qtdImoveisEmCampo = rel.getQtdImoveisEmCampo();
			qtdImoveisTransmitidosDeRotaParcial = rel.getQtdImoveisTransmitidosDeRotaParcial();
			
			Integer qtdRotasFinalizadas = qtdRotasFinalizadasPeloUsuario + qtdRotasTransmitidas;
			if (qtdRotasFinalizadas.equals(qtdTotalLocal)) {
				situacaoLeitura = "Concluído";
			} else if (qtdRotasEmCampo > 0) {
				situacaoLeitura = "Iniciado";
			} else {
				situacaoLeitura = "Não Iniciado";
			}
			
			RelatorioLeiturasRealizadasBean relatorioLeiturasRealizadasBean = new RelatorioLeiturasRealizadasBean();
			
			relatorioLeiturasRealizadasBean.setAnoMesReferencia(rel.getAnoMesReferencia());
			relatorioLeiturasRealizadasBean.setGrupo(rel.getGrupo());
			relatorioLeiturasRealizadasBean.setEmpresa(rel.getEmpresa());
			relatorioLeiturasRealizadasBean.setLocalidade(rel.getLocalidade());
			relatorioLeiturasRealizadasBean.setSituacaoLeitura(situacaoLeitura);
			relatorioLeiturasRealizadasBean.setQtdRotasEmCampo(rel.getQtdRotasEmCampo() != null ? new Integer(rel.getQtdRotasEmCampo()) : new Integer(0));
			relatorioLeiturasRealizadasBean.setQtdRotasTransmitidas(rel.getQtdRotasTransmitidas() != null ? new Integer(rel.getQtdRotasTransmitidas()) : new Integer(0));
			relatorioLeiturasRealizadasBean.setQtdRotasFinalizadasPeloUsuario(rel.getQtdRotasFinalizadasPeloUsuario() != null ? new Integer(rel.getQtdRotasFinalizadasPeloUsuario()) : new Integer(0));
			relatorioLeiturasRealizadasBean.setQtdTotalLocal(rel.getQtdTotalLocal() != null ? new Integer(rel.getQtdTotalLocal()) : new Integer(0));
			relatorioLeiturasRealizadasBean.setQtdImoveisEmCampo(rel.getQtdImoveisEmCampo() != null ? new Integer(rel.getQtdImoveisEmCampo()) : new Integer(0));
			relatorioLeiturasRealizadasBean.setQtdImoveisTransmitidosDeRotaParcial(rel.getQtdImoveisTransmitidosDeRotaParcial() != null ? new Integer(rel.getQtdImoveisTransmitidosDeRotaParcial()) : new Integer(0));
			
				
			retorno.add(relatorioLeiturasRealizadasBean);
			
		}

		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		return 1;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioLeiturasRealizadas", this);
	}

}
