package gcom.relatorio.faturamento.conta;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.MapaControleContaRelatorioHelper;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [UC] 
 * @author Flavio Cordeiro
 * @date 14/02/2007
 */

public class RelatorioMapaControleConta extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioMapaControleConta(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MAPA_CONTROLE_CONTA);
	}
	
	@Deprecated
	public RelatorioMapaControleConta() {
		super(null, "");
	}
	
	private Collection<RelatorioMapaControleContaBean> inicializarBeanRelatorio(
			Collection colecaoMapaControleConta) {
		
		Collection<RelatorioMapaControleContaBean> retorno = new ArrayList<RelatorioMapaControleContaBean>();

		BigDecimal valorTotalPorEmpresa = BigDecimal.ZERO;
		BigDecimal valorTotalPorEmpresaNull = BigDecimal.ZERO;
		Iterator iter = colecaoMapaControleConta.iterator();
		Integer idEmpresaAnterior = 0;
		
		while (iter.hasNext()) {
			
			MapaControleContaRelatorioHelper mapaControleContaRelatorioHelper = (MapaControleContaRelatorioHelper) iter.next();
			
			if(mapaControleContaRelatorioHelper.getIdEmpresa() != null){
				Integer idEmpresaAtual = new Integer(mapaControleContaRelatorioHelper.getIdEmpresa());
				if (!idEmpresaAtual.equals(idEmpresaAnterior)){
					idEmpresaAnterior = idEmpresaAtual;
					valorTotalPorEmpresa = mapaControleContaRelatorioHelper.getValor();
				}else{
					valorTotalPorEmpresa = valorTotalPorEmpresa.add(mapaControleContaRelatorioHelper.getValor());
				}
				
				RelatorioMapaControleContaBean relatorioMapaControleContaBean = 
					new RelatorioMapaControleContaBean(
							"" + mapaControleContaRelatorioHelper.getIdEmpresa(),
							mapaControleContaRelatorioHelper.getNomeEmpresa(),
							"" + mapaControleContaRelatorioHelper.getIdLocalidade(),
							"" + mapaControleContaRelatorioHelper.getCodigoSetor(),
							mapaControleContaRelatorioHelper.getDescricaoTipoConta(),
							mapaControleContaRelatorioHelper.getSequencialInicial() == null? "": "" + mapaControleContaRelatorioHelper.getSequencialInicial(),
									mapaControleContaRelatorioHelper.getSequencialFinal() == null? "": "" + mapaControleContaRelatorioHelper.getSequencialFinal(),
							"" + mapaControleContaRelatorioHelper.getQtdContas(),
							Util.formatarMoedaReal(mapaControleContaRelatorioHelper.getValor()),
							Util.formatarMoedaReal(valorTotalPorEmpresa));
				
				retorno.add(relatorioMapaControleContaBean);
			}else{
				valorTotalPorEmpresaNull = valorTotalPorEmpresaNull.add(mapaControleContaRelatorioHelper.getValor());
				
				RelatorioMapaControleContaBean relatorioMapaControleContaBean = 
					new RelatorioMapaControleContaBean(
							"" + mapaControleContaRelatorioHelper.getIdEmpresa(),
							"",
							"" + mapaControleContaRelatorioHelper.getIdLocalidade(),
							"" + mapaControleContaRelatorioHelper.getCodigoSetor(),
							mapaControleContaRelatorioHelper.getDescricaoTipoConta(),
							mapaControleContaRelatorioHelper.getSequencialInicial() == null? "": "" + mapaControleContaRelatorioHelper.getSequencialInicial(),
							mapaControleContaRelatorioHelper.getSequencialFinal() == null? "": "" + mapaControleContaRelatorioHelper.getSequencialFinal(),
							"" + mapaControleContaRelatorioHelper.getQtdContas(),
							Util.formatarMoedaReal(mapaControleContaRelatorioHelper.getValor()),
							Util.formatarMoedaReal(valorTotalPorEmpresaNull));
				
				retorno.add(relatorioMapaControleContaBean);
			}
		}

		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		String mesAno = (String) getParametro("mesAno");
		String idGrupoFaturamento = (String) getParametro("idGrupoFaturamento");
		String vencimento = (String) getParametro("vencimento");
		Collection colecaoMapaControleConta = (Collection) getParametro("colecaoMapaControleConta");
		String tipoFichaCompensacao = (String) getParametro("tipoFichaCompensacao");
				
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("mesAno",mesAno);
		parametros.put("vencimento", vencimento);
		parametros.put("idGrupoFaturamento",idGrupoFaturamento);
		parametros.put("tipoFichaCompensacao", tipoFichaCompensacao);
		
		Collection dadosRelatorio = colecaoMapaControleConta;

		Collection<RelatorioMapaControleContaBean> colecaoBean = this
						.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_MAPA_CONTROLE_CONTA, 
				parametros, ds,	tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.MAPA_CONTROLE_CONTA,
					idFuncionalidadeIniciada);
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
//		Integer mesAnoInteger = (Integer) getParametro("mesAnoInteger");
//		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");
//		Collection idEsferaPoder = (Collection) getParametro("colecaoIdEsferaPoder");
		
		
		return 1;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMapaControleConta", this);
	}
	
}
