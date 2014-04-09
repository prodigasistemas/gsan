package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.bean.RelacaoOrdensServicoEncerradasPendentesHelper;
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
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * [UC0432] - Gerar Relatorio Acompanhamento Ordem de Servico de Hidrometro
 * 
 * Tipo 2 - Sintetico - Relatorio Resumo das Ordens de Servico Encerradas/Pendentes
 * 
 * @author Ivan Sérgio
 * @created 12/12/2007, 31/03/2008
 * @alteracao: Adicionado Motivo Encerramento; Setor Comercial;
 */
public class RelatorioResumoOrdensServicoEncerradasPendentes extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioResumoOrdensServicoEncerradasPendentes(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_RESUMO_ORDENS_SERVICO_ENCERRADAS_PENDENTES);
	}

	@Deprecated
	public RelatorioResumoOrdensServicoEncerradasPendentes() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		String tipoOrdem = (String) getParametro("tipoOrdem");
		String situacaoOrdemServico = (String) getParametro("situacaoOrdemServico");
		String firma = (String) getParametro("firma");
		String nomeFirma = (String) getParametro("nomeFirma");
		String idLocalidadeInicial = (String) getParametro("idLocalidadeInicial");
		String idLocalidadeFinal = (String) getParametro("idLocalidadeFinal");
		String dataEncerramentoInicial = (String) getParametro("dataEncerramentoInicial");
		String dataEncerramentoFinal = (String) getParametro("dataEncerramentoFinal");
		
		String idMotivoEncerramento = (String) getParametro("idMotivoEncerramento");
		String idSetorComercialInicial = (String) getParametro("idSetorComercialInicial");
		String idSetorComercialFinal = (String) getParametro("idSetorComercialFinal");
		String codigoQuadraInicial = (String) getParametro("codigoQuadraInicial");
		String codigoQuadraFinal = (String) getParametro("codigoQuadraFinal");
		String codigoRotaInicial = (String) getParametro("codigoRotaInicial");
		String codigoRotaFinal = (String) getParametro("codigoRotaFinal");
		String sequenciaRotaInicial = (String) getParametro("sequenciaRotaInicial");
		String sequenciaRotaFinal = (String) getParametro("sequenciaRotaFinal");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;
		
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection colecaoDadosHelper = fachada
				.pesquisarOrdemServicoGerarRelatorioAcompanhamentoHidrometro(
						firma, tipoOrdem, situacaoOrdemServico, idLocalidadeInicial,
						idLocalidadeFinal, dataEncerramentoInicial, dataEncerramentoFinal,
						idMotivoEncerramento, idSetorComercialInicial, idSetorComercialFinal,
						codigoQuadraInicial, codigoQuadraFinal, codigoRotaInicial, codigoRotaFinal,
						sequenciaRotaInicial, sequenciaRotaFinal);
		
		if (colecaoDadosHelper != null && !colecaoDadosHelper.isEmpty()) {
			// Usado para fazer o controle das quebras
			Integer setorComercial = 0;
			
			Integer motivo = 0;
			
			Iterator iColecaoDados = colecaoDadosHelper.iterator();
			
			
			while (iColecaoDados.hasNext()) {
				RelacaoOrdensServicoEncerradasPendentesHelper helper = 
					(RelacaoOrdensServicoEncerradasPendentesHelper) iColecaoDados.next();
				
				// Setor Comercial
				setorComercial = (Integer) helper.getIdSetorComercial();
				// Motivo Encerramento
				motivo = (Integer) helper.getIdMotivoEncerramento();
				
				// Faz a quebra por Setor Comercial
//				if (setorComercial.equals(setorComercialAnt) || setorComercialAnt.equals(0)) {
					
					
					// Faz a quebra por Motivo de Encerramento
					if (situacaoOrdemServico.equals("ENCERRADAS")) {
//						if (motivo.equals(motivoAnt) || motivoAnt.equals(0)) {
//							quantidadeMotivo++;
//							motivoAnt = motivo;
//							descricaoMotivoAnt = helper.getMotivoEncerramento();
//						}
					
							RelatorioResumoOrdensServicoEncerradasPendentesBean relatorioBean =
								new RelatorioResumoOrdensServicoEncerradasPendentesBean();
							
							// Localidade
							relatorioBean.setIdLocalidade(String.valueOf(helper.getIdLocalidade()));
							// Situacao selecionada
							relatorioBean.setSituacao(situacaoOrdemServico);
							// Tipo do Servico
							relatorioBean.setTipoServico(tipoOrdem);
							// Periodo encerramento
							if (dataEncerramentoInicial != null && !dataEncerramentoInicial.equals("") &&
									dataEncerramentoFinal != null && !dataEncerramentoFinal.equals("")) {
								relatorioBean.setPeriodoEncerramento(dataEncerramentoInicial + " à " + dataEncerramentoFinal);
							}else {
								relatorioBean.setPeriodoEncerramento("");
							}
							// Nome Localidade
							relatorioBean.setNomeLocalidade(helper.getNomeLocalidade());
							// Firma
							relatorioBean.setFirma(firma);
							// Nome Firma
							relatorioBean.setNomeFirma(nomeFirma);
							// Total de Ordens
							relatorioBean.setTotalOrdensServico(""+colecaoDadosHelper.size());
							
							// Setor Comercial
							setorComercial = helper.getIdSetorComercial();
							relatorioBean.setIdSetorComercial(setorComercial.toString());
							// Codigo Setor Comercial
							relatorioBean.setCodigoSetorComercial(helper.getCodigoSetorComercial().toString());
							
							// Motivo Encerramento
							if (situacaoOrdemServico.equals("ENCERRADAS")) {
								motivo = helper.getIdMotivoEncerramento();
								relatorioBean.setIdMotivoEncerramento(motivo + "");
								relatorioBean.setDescricaoMotivoEncerramento(helper.getMotivoEncerramento());
								relatorioBean.setQuantidadeMotivo(1 + "");
							}
							
							if ( relatorioBeans.contains(relatorioBean) ) {
								
								int index = relatorioBeans.indexOf(relatorioBean);
								
								relatorioBean = (RelatorioResumoOrdensServicoEncerradasPendentesBean) relatorioBeans.get(index);
								relatorioBean.setQuantidadeMotivo( (new Integer(relatorioBean.getQuantidadeMotivo()) + 1) + "");
								relatorioBeans.set(index , relatorioBean);
								
								
							} else {
								// adiciona o bean a coleção
								relatorioBeans.add(relatorioBean);
									
							}
							
							
							// Contador dos Motivos = 1 para o novo Motivo
//							quantidadeMotivo = 1;
//							// Faz o controle dos Motivos
//							motivoAnt = motivo;
					//	}
					}
//					else {
//						quantidadeMotivo++;
//					}
//				}else {
//					RelatorioResumoOrdensServicoEncerradasPendentesBean relatorioBean =
//						new RelatorioResumoOrdensServicoEncerradasPendentesBean();
//					
//					// Localidade
//					relatorioBean.setIdLocalidade(String.valueOf(helperAnt.getIdLocalidade()));
//					// Situacao selecionada
//					relatorioBean.setSituacao(situacaoOrdemServico);
//					// Tipo do Servico
//					relatorioBean.setTipoServico(tipoOrdem);
//					// Periodo encerramento
//					if (dataEncerramentoInicial != null && !dataEncerramentoInicial.equals("") &&
//							dataEncerramentoFinal != null && !dataEncerramentoFinal.equals("")) {
//						relatorioBean.setPeriodoEncerramento(dataEncerramentoInicial + " à " + dataEncerramentoFinal);
//					}else {
//						relatorioBean.setPeriodoEncerramento("");
//					}
//					// Nome Localidade
//					relatorioBean.setNomeLocalidade(helperAnt.getNomeLocalidade());
//					// Firma
//					relatorioBean.setFirma(firma);
//					// Nome Firma
//					relatorioBean.setNomeFirma(nomeFirma);
//					// Total de Ordens
//					relatorioBean.setTotalOrdensServico(""+colecaoDadosHelper.size());
//					
//					// Setor Comercial
//					setorComercial = helper.getIdSetorComercial();
//					relatorioBean.setIdSetorComercial(setorComercialAnt.toString());
//					// Codigo Setor Comercial
//					relatorioBean.setCodigoSetorComercial(codigoSetorComercialAnt.toString());
//					
//					// Motivo Encerramento
//					if (situacaoOrdemServico.equals("ENCERRADAS")) {
//						motivo = helper.getIdMotivoEncerramento();
//						relatorioBean.setIdMotivoEncerramento(motivoAnt.toString());
//						relatorioBean.setDescricaoMotivoEncerramento(descricaoMotivoAnt);
//						relatorioBean.setQuantidadeMotivo(quantidadeMotivo.toString());
//					}else {
//						relatorioBean.setIdMotivoEncerramento("--");
//						relatorioBean.setDescricaoMotivoEncerramento("--");
//						relatorioBean.setQuantidadeMotivo(quantidadeMotivo.toString());
//					}
//					
//					// adiciona o bean a coleção
//					relatorioBeans.add(relatorioBean);
//					
//					// Contador dos Motivos = 1 para o novo Motivo
//					quantidadeMotivo = 1;
//					// Faz o controle dos Setores
//					setorComercialAnt = setorComercial;
//					// Faz o controle dos Motivos
//					motivoAnt = motivo;
//				}
				
				// Adiciona o Ultimo da colecao
//				if (!iColecaoDados.hasNext()) {
//					RelatorioResumoOrdensServicoEncerradasPendentesBean relatorioBean =
//						new RelatorioResumoOrdensServicoEncerradasPendentesBean();
//					
//					// Localidade
//					relatorioBean.setIdLocalidade(String.valueOf(helper.getIdLocalidade()));
//					// Situacao selecionada
//					relatorioBean.setSituacao(situacaoOrdemServico);
//					// Tipo do Servico
//					relatorioBean.setTipoServico(tipoOrdem);
//					// Periodo encerramento
//					if (dataEncerramentoInicial != null && !dataEncerramentoInicial.equals("") &&
//							dataEncerramentoFinal != null && !dataEncerramentoFinal.equals("")) {
//						relatorioBean.setPeriodoEncerramento(dataEncerramentoInicial + " à " + dataEncerramentoFinal);
//					}else {
//						relatorioBean.setPeriodoEncerramento("");
//					}
//					// Nome Localidade
//					relatorioBean.setNomeLocalidade(helper.getNomeLocalidade());
//					// Firma
//					relatorioBean.setFirma(firma);
//					// Nome Firma
//					relatorioBean.setNomeFirma(nomeFirma);
//					// Total de Ordens
//					relatorioBean.setTotalOrdensServico(""+colecaoDadosHelper.size());
//					
//					// Setor Comercial
//					//setorComercial = (Integer) dados[10];
//					relatorioBean.setIdSetorComercial(setorComercial.toString());
//					// Codigo Setor Comercial
//					relatorioBean.setCodigoSetorComercial(codigoSetorComercialAnt.toString());
//					
//					// Motivo Encerramento
//					//motivo = (Integer) dados[11];
//					if (situacaoOrdemServico.equals("ENCERRADAS")) {
//						relatorioBean.setIdMotivoEncerramento(motivo.toString());
//						relatorioBean.setDescricaoMotivoEncerramento(descricaoMotivoAnt);
//						relatorioBean.setQuantidadeMotivo(quantidadeMotivo.toString());
//					}else {
//						relatorioBean.setIdMotivoEncerramento("--");
//						relatorioBean.setDescricaoMotivoEncerramento("--");
//						relatorioBean.setQuantidadeMotivo(quantidadeMotivo.toString());
//					}
//					
//					// adiciona o bean a coleção
//					relatorioBeans.add(relatorioBean);
//				}
			}

			
			// Parâmetros do relatório
			Map parametros = new HashMap();
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

			parametros.put("imagem", sistemaParametro.getImagemRelatorio());

			// cria uma instância do dataSource do relatório
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_RESUMO_ORDENS_SERVICO_ENCERRADAS_PENDENTES,
					parametros, ds, tipoFormatoRelatorio);

			// Grava o relatório no sistema
			try {
				persistirRelatorioConcluido(retorno,
						Relatorio.RELATORIO_RESUMO_OS_ENCERRADAS_PENDENTES,
						idFuncionalidadeIniciada);
			} catch (ControladorException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gravar relatório no sistema", e);
			}
			// ------------------------------------
		}else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		// retorna o relatório gerado
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioResumoOrdensServicoEncerradasPendentes",
				this);
	}
}
