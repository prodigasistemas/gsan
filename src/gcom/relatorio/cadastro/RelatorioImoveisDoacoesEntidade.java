package gcom.relatorio.cadastro;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  [UC1174] Gerar Relatório Imoveis Com Doacoes.
 * 
 * @author Erivan Sousa
 * @date 13/06/2011, 16/06/2011
 */

public class RelatorioImoveisDoacoesEntidade extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
		
	public RelatorioImoveisDoacoesEntidade(Usuario usuario) {		
		super(usuario, ConstantesRelatorios.RELATORIO_IMOVEIS_DOACOES_ENTIDADE);		
	}
	
	@Deprecated
	public RelatorioImoveisDoacoesEntidade() {
		super(null, "");
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

		String filtroPeriodoAdesaoInicial = (String) getParametro("filtroPeriodoAdesaoInicial");
		String filtroPeriodoAdesaoFinal = (String) getParametro("filtroPeriodoAdesaoFinal");
		String filtroPeriodoCancelamentoInicial = (String) getParametro("filtroPeriodoCancelamentoInicial");
		String filtroPeriodoCancelamentoFinal = (String) getParametro("filtroPeriodoCancelamentoFinal");
		String filtroRefInicioDoacaoInicial = (String) getParametro("filtroRefInicioDoacaoInicial");
		String filtroRefInicioDoacaoFinal = (String) getParametro("filtroRefInicioDoacaoFinal");
		String filtroRefFimDoacaoInicial = (String) getParametro("filtroRefFimDoacaoInicial");
		String filtroRefFimDoacaoFinal= (String) getParametro("filtroRefFimDoacaoFinal");
//		String filtroUsuarioAdesao = (String)getParametro("filtroUsuarioAdesao");
//		String filtroUsuarioCancelamento = (String)getParametro("filtroUsuarioCancelamento");
//		Integer filtroEntidadeBeneficente = Integer.parseInt((String) getParametro("filtroEntidadeBeneficente"));

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		Collection colecaoImoveisDoacoes = null;
		
		colecaoImoveisDoacoes = pesquisarDoacoes();
	
		RelatorioImoveisDoacoesEntidadeBean relatorioBean = null;
		
		Iterator colec = colecaoImoveisDoacoes.iterator();
			while(colec.hasNext()){
				Object[] objetos = (Object[])colec.next();
				
			relatorioBean = new RelatorioImoveisDoacoesEntidadeBean();
			
			Integer matriculaImovel = (Integer)objetos[1];
			
			relatorioBean.setEntidade((String)objetos[0]);
			
			if( objetos[9] != null){
				relatorioBean.setCliente((String)objetos[9]);
			}else{
				relatorioBean.setCliente("");
			}
			
//			Cliente clienteUsuario = fachada.pesquisarClienteUsuarioImovel(matriculaImovel);
//			String nomeUsuario = "";
//			if(clienteUsuario != null){
//				nomeUsuario = clienteUsuario.getNome();
//			}
//			relatorioBean.setCliente(nomeUsuario);
			relatorioBean.setImovel(Util.retornaMatriculaImovelFormatada(matriculaImovel));
			
			if( objetos[2] != null){
				relatorioBean.setDataAdesao(Util.formatarData((Date) objetos[2]));
			}else{
				relatorioBean.setDataAdesao(" ");
			}
			
			if( objetos[3] != null){
				relatorioBean.setDataCancelamento(Util.formatarData((Date) objetos[3]));
			}else{
				relatorioBean.setDataCancelamento(" ");
			}
			
			relatorioBean.setUsuarioAdesao(((String)objetos[4]).toUpperCase());
			
			if((String)objetos[5] != null && !((String)objetos[5]).equals("")){
				relatorioBean.setUsuarioCancelamento(((String)objetos[5]).toUpperCase());
			}else{
				relatorioBean.setUsuarioCancelamento("");
			}

			if( objetos[6] != null){
				relatorioBean.setMesAnoInicio(Util.formatarAnoMesParaMesAno((Integer)objetos[6]));
			}else{
				relatorioBean.setMesAnoInicio(" ");
			}
			
			if( objetos[7] != null){
				relatorioBean.setMesAnoFinal(Util.formatarAnoMesParaMesAno((Integer)objetos[7]));
			}else{
				relatorioBean.setMesAnoFinal(" ");
			}
			
			relatorioBean.setValor((BigDecimal)objetos[8]);

			relatorioBeans.add(relatorioBean);
		}
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		//Formata  Período de adesão
		String periodoAdesao = null;
		if(filtroPeriodoAdesaoInicial != null && !filtroPeriodoAdesaoInicial.equals("")&&
				filtroPeriodoAdesaoFinal != null && !filtroPeriodoAdesaoFinal.equals("")){
			periodoAdesao =  filtroPeriodoAdesaoInicial + " a " + filtroPeriodoAdesaoFinal;
		}else{
			periodoAdesao = " ";
		}
		parametros.put("filtroPeriodoAdesao", periodoAdesao);
		
		//Formata o Período de Cancelamento
		String periodoCancelamento = null;
		if(filtroPeriodoCancelamentoInicial != null && !filtroPeriodoCancelamentoInicial.equals("")&&
				filtroPeriodoCancelamentoFinal != null && !filtroPeriodoCancelamentoFinal.equals("")){
			periodoCancelamento =  filtroPeriodoCancelamentoInicial + " a " + filtroPeriodoCancelamentoFinal;
		}else{
			periodoCancelamento = " ";
		}
		parametros.put("filtroPeriodoCancelamento", periodoCancelamento);
		
		//Formata Mês Ano Inicio
		String mesAnoInicio = null;
		if(filtroRefInicioDoacaoInicial != null && !filtroRefInicioDoacaoInicial.equals("")&&
				filtroRefInicioDoacaoFinal != null && !filtroRefInicioDoacaoFinal.equals("")){
			mesAnoInicio =  filtroRefInicioDoacaoInicial + " a " + filtroRefInicioDoacaoFinal;
		}else{
			mesAnoInicio = " ";
		}
		parametros.put("filtroMesAnoInicio", mesAnoInicio);
		
		//Formata Mês Ano Final
		String mesAnoFinal = null;
		if(filtroRefFimDoacaoInicial != null && !filtroRefFimDoacaoInicial.equals("")&&
				filtroRefFimDoacaoFinal != null && !filtroRefFimDoacaoFinal.equals("")){
			mesAnoInicio =  filtroRefFimDoacaoInicial + " a " + filtroRefFimDoacaoFinal;
		}else{
			mesAnoInicio = " ";
		}
		parametros.put("filtroMesAnoFinal", mesAnoFinal);						
		
		parametros.put("tipoRelatorio", "R1174");

		
		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) relatorioBeans );

		
			retorno = this.gerarRelatorio(
					ConstantesRelatorios.RELATORIO_IMOVEIS_DOACOES_ENTIDADE, parametros, ds,
					tipoFormatoRelatorio);
		

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_IMOVEIS_DOACOES_ENTIDADE,
						idFuncionalidadeIniciada);

		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	
}
	private Collection pesquisarDoacoes(){
		Collection colecao = null;
		
		colecao = Fachada.getInstancia().pesquisarImoveisDoacoes(montarFiltro());
		
		return colecao;
	}
	@Override
	public int calcularTotalRegistrosRelatorio() {		
		
		Integer retorno = new Integer(0);
		
		retorno = Fachada.getInstancia().countImoveisDoacao(montarFiltro());
		
		if(retorno.intValue() == 0 ){			
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		return retorno;
	}
	
	private GerarRelatorioImoveisDoacoesHelper montarFiltro(){
		String filtroPeriodoAdesaoInicial = (String) getParametro("filtroPeriodoAdesaoInicial");
		String filtroPeriodoAdesaoFinal = (String) getParametro("filtroPeriodoAdesaoFinal");
		String filtroPeriodoCancelamentoInicial = (String) getParametro("filtroPeriodoCancelamentoInicial");
		String filtroPeriodoCancelamentoFinal = (String) getParametro("filtroPeriodoCancelamentoFinal");
		String filtroRefInicioDoacaoInicial = (String) getParametro("filtroRefInicioDoacaoInicial");
		String filtroRefInicioDoacaoFinal = (String) getParametro("filtroRefInicioDoacaoFinal");
		String filtroRefFimDoacaoInicial = (String) getParametro("filtroRefFimDoacaoInicial");
		String filtroRefFimDoacaoFinal= (String) getParametro("filtroRefFimDoacaoFinal");
		String filtroUsuarioAdesao = (String)getParametro("filtroUsuarioAdesao");
		String filtroUsuarioCancelamento = (String)getParametro("filtroUsuarioCancelamento");
		Integer filtroEntidadeBeneficente = Integer.parseInt((String) getParametro("filtroEntidadeBeneficente"));
		
		GerarRelatorioImoveisDoacoesHelper filtro = new GerarRelatorioImoveisDoacoesHelper();
		
		filtro.setIdEntidade(filtroEntidadeBeneficente);
		if(filtroPeriodoAdesaoInicial != null && !filtroPeriodoAdesaoInicial.equals("")){
			filtro.setDataAdesaoInicio(Util.converteStringParaDate(filtroPeriodoAdesaoInicial));
		}
		if(filtroPeriodoAdesaoFinal != null && !filtroPeriodoAdesaoFinal.equals("")){
			filtro.setDataAdesaoFinal(Util.converteStringParaDate(filtroPeriodoAdesaoFinal));
		}
		if(filtroPeriodoCancelamentoInicial != null && !filtroPeriodoCancelamentoInicial.equals("")){
			filtro.setDataCancelamentoInicio(Util.converteStringParaDate(filtroPeriodoCancelamentoInicial));
		}
		if(filtroPeriodoCancelamentoFinal != null && !filtroPeriodoCancelamentoFinal.equals("")){
			filtro.setDataCancelamentoFinal(Util.converteStringParaDate(filtroPeriodoCancelamentoFinal));
		}
		if(filtroUsuarioAdesao != null && !filtroUsuarioAdesao.equals("")){
			filtro.setLoginUsuarioAdesao(filtroUsuarioAdesao);
		}
		if(filtroUsuarioCancelamento != null && !filtroUsuarioCancelamento.equals("")){
			filtro.setLoginUsuarioCancelamento(filtroUsuarioCancelamento);
		}
		if(filtroRefFimDoacaoFinal != null && !filtroRefFimDoacaoFinal.equals("")){
			filtro.setRefFimDoacaoFinal(Util.formatarMesAnoComBarraParaAnoMes(filtroRefFimDoacaoFinal));
		}
		if(filtroRefFimDoacaoInicial != null && !filtroRefFimDoacaoInicial.equals("")){
			filtro.setRefFimDoacaoInicio(Util.formatarMesAnoComBarraParaAnoMes(filtroRefFimDoacaoInicial));
		}
		if(filtroRefInicioDoacaoInicial != null && !filtroRefInicioDoacaoInicial.equals("")){
			filtro.setRefInicioDoacaoInicio(Util.formatarMesAnoComBarraParaAnoMes(filtroRefInicioDoacaoInicial));
		}
		if(filtroRefInicioDoacaoFinal != null && !filtroRefInicioDoacaoFinal.equals("")){
			filtro.setRefInicioDoacaoFinal(Util.formatarMesAnoComBarraParaAnoMes(filtroRefInicioDoacaoFinal));
		}
		
		return filtro;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioImoveisDoacoes", this);
	}
	
}
