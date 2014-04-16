package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.gerencial.bean.CobrancaAcaoDebitoHelper;
import gcom.gerencial.bean.CobrancaAcaoHelper;
import gcom.gerencial.bean.CobrancaAcaoSituacaoHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioResumoAcoesCobrancaEventuais extends TarefaRelatorio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioResumoAcoesCobrancaEventuais(Usuario usuario,
			String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		// TODO Auto-generated method stub		
	}

	@Override
	public Object executar() throws TarefaException {
		byte[] retorno = null;
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
						
		Fachada fachada = Fachada.getInstancia();
		
		//coleção de beans do relatório
		List relatorioBeans = new ArrayList();		
		
		//colecao que vai alimentar os beans do relatorio		
		Collection colecaoResumoAcaoCobranca = (Collection) getParametro("colecaoResumoAcaoCobranca");
		int tipoAcaoCobranca = Integer.parseInt((String) getParametro("tipoAcaoCobranca"));		
		int tipoRelatorio =  Integer.parseInt((String) getParametro("tipoRelatorio"));
		
		// coloca a coleção de parâmetros da analise no iterator
		Iterator colecaoResumoAcaoCobrancaIterator = colecaoResumoAcaoCobranca.iterator();
		
		// laço para criar a coleção de parâmetros da analise
		while (colecaoResumoAcaoCobrancaIterator.hasNext()) {
						
			CobrancaAcaoHelper resumoAcaoCobranca =	
				(CobrancaAcaoHelper) colecaoResumoAcaoCobrancaIterator.next();
			
			//filtro para adicionar apenas a acao de cobranca selecionada 
			if (resumoAcaoCobranca.getId() == tipoAcaoCobranca) {
				RelatorioResumoAcoesCobrancaEventuaisBean relatorioBean = new RelatorioResumoAcoesCobrancaEventuaisBean();				
				// Cabeçalho Ação Cobrança
				relatorioBean.setDescricaoResumoAcaoCobranca(resumoAcaoCobranca.getDescricao());
				relatorioBean.setDataRealizacaoEmitir(resumoAcaoCobranca.getRealizacaoEmitir());
				relatorioBean.setDataRealizacaoEncerrar(resumoAcaoCobranca.getRealizacaoEncerrar());
				relatorioBean.setIndicadorDefinitivo(resumoAcaoCobranca.getIndicadorDefinitivo());

				if (resumoAcaoCobranca.getColecaoCobrancaAcaoSituacao() != null
						&& resumoAcaoCobranca.getColecaoCobrancaAcaoSituacao().size() > 0) {

					if (resumoAcaoCobranca.getId() != CobrancaAcao.AVISO_CORTE) {
						relatorioBean.setSomatorioQuantidadesDocumentos(resumoAcaoCobranca.getSomatorioQuantidadesDocumentos());
						relatorioBean.setSomatorioValoresDocumentos(Util.formatarMoedaReal(resumoAcaoCobranca.getSomatorioValoresDocumentos()));
					}

						Collection colecaoCobrancaAcaoSituacao = resumoAcaoCobranca.getColecaoCobrancaAcaoSituacao();

						// coloca a coleção de parâmetros da analise no iterator
						Iterator colecaoCobrancaAcaoSituacaoIterator = colecaoCobrancaAcaoSituacao.iterator();

						// laço para criar a coleção de parâmetros da analise
						while (colecaoCobrancaAcaoSituacaoIterator.hasNext()) {

							CobrancaAcaoSituacaoHelper cobrancaAcaoSituacao = (CobrancaAcaoSituacaoHelper) colecaoCobrancaAcaoSituacaoIterator.next();
							// Situação Ação
							relatorioBean.setDescricaoCobrancaAcaoSituacao(cobrancaAcaoSituacao.getDescricao());

							relatorioBean.setQuantidadeDocumentoAcao(cobrancaAcaoSituacao.getQuantidadeDocumento());
							relatorioBean.setPorcentagemQuantidadeAcao(String.valueOf(cobrancaAcaoSituacao.getPorcentagemQuantidade()));
							relatorioBean.setValorDocumentoAcao(Util.formatarMoedaReal(cobrancaAcaoSituacao.getValorDoumento()));
							relatorioBean.setPorcentagemValorAcao(String.valueOf(cobrancaAcaoSituacao.getPorcentagemValor()));

							if (cobrancaAcaoSituacao.getColecaoCobrancaAcaoDebito() != null
									&& cobrancaAcaoSituacao.getColecaoCobrancaAcaoDebito().size() > 0) {

								Collection colecaoCobrancaAcaoDebito = cobrancaAcaoSituacao.getColecaoCobrancaAcaoDebito();

								// coloca a coleção de parâmetros da analise no iterator
								Iterator colecaoCobrancaAcaoDebitoIterator = colecaoCobrancaAcaoDebito.iterator();

								// laço para criar a coleção de parâmetros da analise
								while (colecaoCobrancaAcaoDebitoIterator.hasNext()) {

									CobrancaAcaoDebitoHelper cobrancaAcaoDebito = (CobrancaAcaoDebitoHelper) colecaoCobrancaAcaoDebitoIterator.next();
									// Situação Débito
									relatorioBean.setDescricaoIndicadorAcaoDebito(cobrancaAcaoDebito.getDescricaoIndicador());

									relatorioBean.setQuantidadeDocumentoDebito(cobrancaAcaoDebito.getQuantidadeDocumento());
									relatorioBean.setPorcentagemQuantidadeDebito(cobrancaAcaoDebito.getPercentualQuantidade(String.valueOf(cobrancaAcaoSituacao.getQuantidadeDocumento())));
									relatorioBean.setValorDocumentoDebito(Util.formatarMoedaReal(cobrancaAcaoDebito.getValorDocumento()));
									relatorioBean.setPorcentagemValorDebito(cobrancaAcaoDebito.getPercentualValor(String.valueOf(cobrancaAcaoSituacao.getValorDoumento())));

									relatorioBeans.add(relatorioBean);

									if (colecaoCobrancaAcaoSituacaoIterator.hasNext() || colecaoCobrancaAcaoDebitoIterator.hasNext()) {
										relatorioBean = new RelatorioResumoAcoesCobrancaEventuaisBean();
									}

									if (colecaoCobrancaAcaoDebitoIterator.hasNext()) {
										relatorioBean.setDescricaoCobrancaAcaoSituacao(cobrancaAcaoSituacao.getDescricao());
										
										relatorioBean.setQuantidadeDocumentoAcao(cobrancaAcaoSituacao.getQuantidadeDocumento());
										relatorioBean.setPorcentagemQuantidadeAcao(String.valueOf(cobrancaAcaoSituacao.getPorcentagemQuantidade()));
										relatorioBean.setValorDocumentoAcao(String.valueOf(cobrancaAcaoSituacao.getValorDoumento()));
										relatorioBean.setPorcentagemValorAcao(String.valueOf(cobrancaAcaoSituacao.getPorcentagemValor()));
									}

								}

							}
						}										
				}
			}
		}
		
		// __________________________________________________________________
		
		Map parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("tipoFormatoRelatorio", "R0157");
		

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_RESUMO_ACOES_COBRANCA_EVENTUAIS,
				parametros, ds, tipoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_RESUMO_ACOES_COBRANCA_EVENTUAIS,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		
		return retorno;
	}

}
