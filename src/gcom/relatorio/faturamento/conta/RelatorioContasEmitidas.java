package gcom.relatorio.faturamento.conta;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
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
 * @author Vivianne Sousa
 * @date 27/01/2007
 */

public class RelatorioContasEmitidas extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioContasEmitidas(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_CONTAS_EMITIDAS);
	}
	
	private Collection<RelatorioContasEmitidasBean> inicializarBeanRelatorio(
			Collection colecaoContasEmitidasRelatorioHelper) {
		
		Collection<RelatorioContasEmitidasBean> retorno = new ArrayList<RelatorioContasEmitidasBean>();
		Integer idEsferaPoderAnterior = 0;
		Integer numeroContasPorEsfera = 0;
		BigDecimal valorTotalPorEsfera = BigDecimal.ZERO;
		Iterator iter = colecaoContasEmitidasRelatorioHelper.iterator();
		while (iter.hasNext()) {
			
			ContasEmitidasRelatorioHelper contasEmitidasRelatorioHelper = (ContasEmitidasRelatorioHelper) iter.next();
				
			Integer idEsferaPoderAtual = new Integer(contasEmitidasRelatorioHelper.getIdEsferaPoder());
			if (!idEsferaPoderAtual.equals(idEsferaPoderAnterior)){
				idEsferaPoderAnterior = idEsferaPoderAtual;
				numeroContasPorEsfera = 1;
				valorTotalPorEsfera = contasEmitidasRelatorioHelper.getValorTotal();
			}else{
				numeroContasPorEsfera ++;
				valorTotalPorEsfera = valorTotalPorEsfera.add(contasEmitidasRelatorioHelper.getValorTotal());
			}
			
			
			RelatorioContasEmitidasBean relatorioContasEmitidasBean = 
					new RelatorioContasEmitidasBean(
							contasEmitidasRelatorioHelper.getIdClienteResponsavel(),
							contasEmitidasRelatorioHelper.getDataVencimentoConta(),
							contasEmitidasRelatorioHelper.getEndereco(),
							contasEmitidasRelatorioHelper.getDescEsferaPoder(),
							contasEmitidasRelatorioHelper.getIdEsferaPoder(),
							contasEmitidasRelatorioHelper.getIdGrupoFaturamento(),
							contasEmitidasRelatorioHelper.getInscricaoFormatada(),
							contasEmitidasRelatorioHelper.getDescLocalidade(),
							contasEmitidasRelatorioHelper.getIdImovel(),
							contasEmitidasRelatorioHelper.getMesAnoReferencia(),
							contasEmitidasRelatorioHelper.getNomeClienteResponsavel(),
							contasEmitidasRelatorioHelper.getNomeUsuario(),
							Util.formatarMoedaReal(contasEmitidasRelatorioHelper.getValorTotal()),
							numeroContasPorEsfera.toString(),Util.formatarMoedaReal(valorTotalPorEsfera));
				
			retorno.add(relatorioContasEmitidasBean);
		
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

		Integer mesAnoInteger = (Integer) getParametro("mesAnoInteger");
		Integer idGrupoFaturamento = (Integer) getParametro("idGrupoFaturamento");
		Collection idEsferaPoder = (Collection) getParametro("colecaoIdEsferaPoder");
		String tipoImpressao = (String) getParametro("tipoImpressao");
		
		Collection colecaoContasEmitidasHelper = null;
				
		colecaoContasEmitidasHelper = 
			fachada.consultarContasEmitidasRelatorio(mesAnoInteger.intValue(),
				idGrupoFaturamento,idEsferaPoder,tipoImpressao);		

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());

		Collection dadosRelatorio = colecaoContasEmitidasHelper;

		Collection<RelatorioContasEmitidasBean> colecaoBean = this
						.inicializarBeanRelatorio(dadosRelatorio);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new ActionServletException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_CONTAS_EMITIDAS, 
				parametros, ds,	tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.CONTAS_EMITIDAS,
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
		
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioContasEmitidas", this);
	}
	
}
