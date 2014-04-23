package gcom.relatorio.arrecadacao;

import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Vinicius Medeiros
 * @version 1.0
 */

public class RelatorioManterMovimentoArrecadadoresItens extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterMovimentoArrecadadoresItens(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_MANTER_MOVIMENTO_ARRECADADORES_ITENS);
	}
	
	@Deprecated
	public RelatorioManterMovimentoArrecadadoresItens() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param atividades
	 *            Description of the Parameter
	 * @param atividadeParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// valor de retorno
		byte[] retorno = null;
		
		//Obtem a instancia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioManterMovimentoArrecadadoresItensBean relatorioBean = null;
		
		String indicadorAceitacao = (String) getParametro("indicadorAceitacao");
		String descricaoOcorrencia = (String) getParametro("descricaoOcorrencia");
		String indicadorDiferencaValorMovimentoValorPagamento = 
			(String) getParametro("indicadorDiferencaValorMovimentoValorPagamento");
		String idImovel = (String) getParametro("idImovel");
		String descricaoArrecadacaoForma = (String) getParametro("descricaoArrecadacaoForma");
		String valorDadosMovimento = (String) getParametro("valorDadosMovimento");
		String valorDadosPagamento = (String) getParametro("valorDadosPagamento");
		String nomeArrecadador = (String) getParametro("nomeArrecadador");
		
		if(descricaoOcorrencia.equals("" + ConstantesSistema.COM_ITENS)){
			descricaoOcorrencia = "Com Ocorrência";
		} else if(descricaoOcorrencia.equals("" + ConstantesSistema.SEM_ITENS)){
			descricaoOcorrencia = "Sem Ocorrência";
		} else {
			descricaoOcorrencia = "Todos";
		}
		
		if(indicadorAceitacao != null && !indicadorAceitacao.equals("")){
			if(indicadorAceitacao.equals("" + ConstantesSistema.INDICADOR_REGISTRO_ACEITO)){
				indicadorAceitacao = "Aceito";
			}else if(indicadorAceitacao.equals("" + ConstantesSistema.INDICADOR_REGISTRO_NAO_ACEITO)){
				indicadorAceitacao = "Não Aceito";
			}else{
				indicadorAceitacao = "Todos";
			}
		}else if(indicadorAceitacao == null){
			indicadorAceitacao ="";
		}
		
		if(indicadorDiferencaValorMovimentoValorPagamento != null 
				&& !indicadorDiferencaValorMovimentoValorPagamento.equals("")){
			if(indicadorDiferencaValorMovimentoValorPagamento.equals("" + ConstantesSistema.SEM_DIFERENCA)){
				indicadorDiferencaValorMovimentoValorPagamento = "Sem Diferença";
			} else if(indicadorDiferencaValorMovimentoValorPagamento.equals("" + ConstantesSistema.COM_DIFERENCA)){
				indicadorDiferencaValorMovimentoValorPagamento = "Com Diferença";
			} else{
				indicadorDiferencaValorMovimentoValorPagamento = "Todos";
			}
		}
		
		Collection<ArrecadadorMovimentoItemHelper> colecaoArrecadadorMovimentoItem = 
			(Collection) getParametro("colecaoArrecadadorMovimentoItemHelper"); 
		// se a coleção de parâmetros da analise não for vazia
		if (colecaoArrecadadorMovimentoItem != null && !colecaoArrecadadorMovimentoItem.isEmpty()) {

			// laço para criar a coleção de parâmetros da analise
			for (ArrecadadorMovimentoItemHelper arrecadadorMovimentoItemHelper : colecaoArrecadadorMovimentoItem) {
				
				// Caso não possuam valores, setar branco
				String identificacao = arrecadadorMovimentoItemHelper.getIdentificacao();
				
				if(identificacao == null || identificacao.equals("")){
					identificacao = "";
				} else {
					identificacao = arrecadadorMovimentoItemHelper.getIdentificacao();
				}
				
				// Caso não possuam valores, setar branco
				String valorMovimento = arrecadadorMovimentoItemHelper.getVlMovimento();
				
				if(valorMovimento == null || valorMovimento.equals("")){
					valorMovimento = "";
				} else {
					valorMovimento = arrecadadorMovimentoItemHelper.getVlMovimento();
				}

				// Caso não possuam valores, setar branco
				String valorPagamento = arrecadadorMovimentoItemHelper.getVlPagamento();
				
				if(valorPagamento == null || valorPagamento.equals("")){
					valorPagamento = "";
				} else {
					valorPagamento = arrecadadorMovimentoItemHelper.getVlPagamento();
				}

				// Caso não possuam valores, setar branco
				String matriculaImovel = arrecadadorMovimentoItemHelper.getMatriculaImovel();
				
				if(matriculaImovel == null){
					matriculaImovel = "";
				} else {
					matriculaImovel = arrecadadorMovimentoItemHelper.getMatriculaImovel();
				}
				
				
				relatorioBean = new RelatorioManterMovimentoArrecadadoresItensBean(
						
						// Codigo Registro
						arrecadadorMovimentoItemHelper.getCodigoRegistro().toString(),
						
						// Descricao Ocorrencia
						arrecadadorMovimentoItemHelper.getOcorrencia(), 
						
						//	Indicador Aceitacao
						arrecadadorMovimentoItemHelper.getDescricaoIndicadorAceitacao(),
						
						// Identificacao
						identificacao,
						
						// Tipo Pagamento
						arrecadadorMovimentoItemHelper.getTipoPagamento(),
						
						//	Valor Movimento 
						valorMovimento,
						
						// Valor Pagamento
						valorPagamento,
						
						//Matricula Imovel
						matriculaImovel);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
				
			}
			
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("ocorrencia", descricaoOcorrencia);
		parametros.put("indicadorAceitacao", indicadorAceitacao);
		parametros.put("indicadorDiferencaValorMovimentoValorPagamento", indicadorDiferencaValorMovimentoValorPagamento);
		parametros.put("idImovel", idImovel);
		parametros.put("descricaoArrecadacaoForma", descricaoArrecadacaoForma);
		parametros.put("valorDadosMovimento", valorDadosMovimento);
		parametros.put("valorDadosPagamento", valorDadosPagamento);
		parametros.put("tipoFormatoRelatorio", "R0604");
		parametros.put("nomeArrecadador", nomeArrecadador);
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_MANTER_MOVIMENTO_ARRECADADORES_ITENS,
				parametros, ds, tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterMovimentoArrecadadoresItens", this);
	}

}
