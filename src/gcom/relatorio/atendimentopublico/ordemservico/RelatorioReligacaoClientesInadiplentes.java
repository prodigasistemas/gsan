package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
 * 
 * @author Hugo Leonardo
 *
 * @date 25/01/2011
 */
public class RelatorioReligacaoClientesInadiplentes extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioReligacaoClientesInadiplentes(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES);
	}

	@Deprecated
	public RelatorioReligacaoClientesInadiplentes() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper = 
			(FiltrarRelatorioReligacaoClientesInadiplentesHelper) getParametro("filtrarRelatorioReligacaoClientesInadiplentesHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		String gerenciaRegional = (String) getParametro("gerenciaRegional");
		String unidadeNegocio = (String) getParametro("unidadeNegocio");
		String localidade = (String) getParametro("localidade");
		String setorComercial = (String) getParametro("setorComercial");
		String cliente = (String) getParametro("cliente");
		String nomeUsuario = (String) getParametro("nomeUsuario");
		String periodoEncerramento = (String) getParametro("periodoEncerramento");
		String periodoRecorrencia = (String) getParametro("periodoRecorrencia");
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioReligacaoClientesInadiplentesBean relatorioBean = null;

		Collection<RelatorioReligacaoClientesInadiplentesHelper> colecao = fachada.pesquisarRelatorioReligacaoClientesInadiplentes(relatorioHelper);

		// se a coleção de parâmetros da analise não for vazia
		if (colecao != null && !colecao.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (RelatorioReligacaoClientesInadiplentesHelper helper : colecao) {
			
				if(relatorioHelper.getEscolhaRelatorio() == 1){
					
					relatorioBean = new RelatorioReligacaoClientesInadiplentesBean(
							helper.getMatricula(),
							helper.getEndereco(),
							helper.getPerfil(),
							helper.getNumeroOS(),
							helper.getUsuarioAberturaOS(),
							helper.getNomeUsuarioAberturaOS(),
							helper.getDataEncerramento(),
							helper.getUsuarioEncerramentoOS(),
							helper.getNomeUsuarioEncerramentoOS(),
							helper.getValorDebito()
					);
				}
				else if(relatorioHelper.getEscolhaRelatorio() == 2){
					
					relatorioBean = new RelatorioReligacaoClientesInadiplentesBean(
							
							helper.getNomeCliente(),
							helper.getCpf(),
							helper.getTipoRelacao(),
							helper.getMatricula(),
							helper.getEndereco(),
							helper.getPerfil(),
							helper.getNumeroOS(),
							helper.getUsuarioAberturaOS(),
							helper.getNomeUsuarioAberturaOS(),
							helper.getDataEncerramento(),
							helper.getUsuarioEncerramentoOS(),
							helper.getNomeUsuarioEncerramentoOS(),
							helper.getValorDebito()
					);
				}
				else if(relatorioHelper.getEscolhaRelatorio() == 3 || 
						relatorioHelper.getEscolhaRelatorio() == 4 || 
						relatorioHelper.getEscolhaRelatorio() == 5 ){
					
					relatorioBean = new RelatorioReligacaoClientesInadiplentesBean(
							
							helper.getNomeCliente(),
							helper.getCpf(),
							helper.getTipoRelacao(),
							helper.getMatricula(),
							helper.getEndereco(),
							helper.getPerfil(),
							helper.getNumeroOS(),
							helper.getUsuarioAberturaOS(),
							helper.getNomeUsuarioAberturaOS(),
							helper.getDataEncerramento(),
							helper.getUsuarioEncerramentoOS(),
							helper.getNomeUsuarioEncerramentoOS(),
							helper.getValorDebito()
					);
				}
				
				relatorioBeans.add(relatorioBean);
			}
			
			if(relatorioHelper.getEscolhaRelatorio() == 2){
				
				Collections.sort((List) relatorioBeans,
					new Comparator() {
						public int compare(Object a, Object b) {
							String codigo1 = ((RelatorioReligacaoClientesInadiplentesBean) a)
									.getNomeCliente();
							String codigo2 = ((RelatorioReligacaoClientesInadiplentesBean) b)
								.getNomeCliente();
							if (codigo1 == null || codigo1.equals("")) {
								return -1;
							} else {
								return codigo1.compareTo(codigo2);
							}
						}
					});
			}
			
			if(relatorioHelper.getEscolhaRelatorio() == 3){
				
				Collections.sort((List) relatorioBeans,
					new Comparator() {
						public int compare(Object a, Object b) {
							String codigo1 = ((RelatorioReligacaoClientesInadiplentesBean) a)
									.getNomeUsuarioAberturaOS();
							String codigo2 = ((RelatorioReligacaoClientesInadiplentesBean) b)
								.getNomeUsuarioAberturaOS();
							if (codigo1 == null || codigo1.equals("")) {
								return -1;
							} else {
								return codigo2.compareTo(codigo1);
							}
						}
					});
			}
			else if(relatorioHelper.getEscolhaRelatorio() == 4 || 
					relatorioHelper.getEscolhaRelatorio() == 5 ){
				
				Collections.sort((List) relatorioBeans,
					new Comparator() {
						public int compare(Object a, Object b) {
							String codigo1 = ((RelatorioReligacaoClientesInadiplentesBean) a)
									.getNomeUsuarioEncerramentoOS();
							String codigo2 = ((RelatorioReligacaoClientesInadiplentesBean) b)
								.getNomeUsuarioEncerramentoOS();
							if (codigo1 == null || codigo1.equals("")) {
								return -1;
							} else {
								return codigo2.compareTo(codigo1);
							}
						}
					});
			}
		}
		
		else{
			
			relatorioBean = new RelatorioReligacaoClientesInadiplentesBean();
			relatorioBeans.add(relatorioBean);
		}
		
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());	
		
		parametros.put("gerenciaRegional", gerenciaRegional);
		parametros.put("unidadeNegocio", unidadeNegocio);
		parametros.put("localidade", localidade);
		parametros.put("setorComercial", setorComercial);
		parametros.put("cliente", cliente);
		parametros.put("nomeUsuario", nomeUsuario);
		parametros.put("periodoEncerramento", periodoEncerramento);
		parametros.put("periodoRecorrencia", periodoRecorrencia);
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		
		if(colecao != null && colecao.size() > 0){
			
			if(relatorioHelper.getEscolhaRelatorio() == 1){
				
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES,
						parametros, ds, tipoFormatoRelatorio);
			}
			else if(relatorioHelper.getEscolhaRelatorio() == 2){
				
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES_2,
						parametros, ds, tipoFormatoRelatorio);
			}
			else if(relatorioHelper.getEscolhaRelatorio() == 3){
				
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES_3,
						parametros, ds, tipoFormatoRelatorio);
			}
			else if(relatorioHelper.getEscolhaRelatorio() == 4){
				
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES_4,
						parametros, ds, tipoFormatoRelatorio);
			}
			else if(relatorioHelper.getEscolhaRelatorio() == 5){
				
				retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES_5,
						parametros, ds, tipoFormatoRelatorio);
			}
		}
		else{
			
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}
		
		
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES,
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
		
		int retorno = 101;
		
		/*
		FiltrarRelatorioReligacaoClientesInadiplentesHelper relatorioHelper = 
			(FiltrarRelatorioReligacaoClientesInadiplentesHelper) getParametro("filtrarRelatorioReligacaoClientesInadiplentesHelper");
		
		retorno = Fachada.getInstancia().countRelatorioReligacaoClientesInadiplentes(relatorioHelper);
		
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		else{
			
			if(relatorioHelper.getEscolhaRelatorio() == 1 && retorno > 100){
				retorno = 101;
			}
			else if(relatorioHelper.getEscolhaRelatorio() == 2 && retorno > 100){
				retorno = 101;
			}
			else if(relatorioHelper.getEscolhaRelatorio() == 3 && retorno > 25){
				retorno = 101;	
			}
			else if(relatorioHelper.getEscolhaRelatorio() == 4 && retorno > 25){
				retorno = 101;
			}
			else if(relatorioHelper.getEscolhaRelatorio() == 5 && retorno > 100){
				retorno = 101;
			}
		}
		*/
		
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioReligacaoClientesInadiplentes", this);
	}

}
