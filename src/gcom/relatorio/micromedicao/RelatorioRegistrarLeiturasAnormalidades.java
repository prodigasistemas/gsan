package gcom.relatorio.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
 * @author Sávio Luiz
 * @version 1.0
 */

public class RelatorioRegistrarLeiturasAnormalidades extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioRegistrarLeiturasAnormalidades(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_REGISTRAR_LEITURAS_ANORMALIDADES);
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param localidades
	 *            Description of the Parameter
	 * @param localidadeParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */

	public Object executar() throws TarefaException {

		// public byte[] gerarRelatorioRegistrarLeiturasAnormalidades(
		// Collection<MedicaoHistorico> colecaoMedicaoHistoricoRelatorio,
		// String idFaturamento, String anoMesReferencia)
		// throws RelatorioVazioException {

		Collection colecaoMedicaoHistoricoRelatorio = (Collection) getParametro("colecaoMedicaoHistoricoRelatorio");
		Integer idFaturamento = (Integer) getParametro("idFaturamento");
		String anoMesReferencia = (String) getParametro("anoMesLeitura");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String nomeArquivo = (String) getParametro("nomeArquivo");

		Fachada fachada = Fachada.getInstancia();
		
		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		RelatorioRegistrarLeiturasAnormalidadesBean relatorioBean = null;

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoMedicaoHistoricoRelatorio != null
				&& !colecaoMedicaoHistoricoRelatorio.isEmpty()) {
			// coloca a coleção de parâmetros da analise no iterator
			Iterator medicaoHistoricoRelatorioIterator = colecaoMedicaoHistoricoRelatorio
					.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (medicaoHistoricoRelatorioIterator.hasNext()) {

				MedicaoHistorico medicaoHistrico = (MedicaoHistorico) medicaoHistoricoRelatorioIterator
						.next();

				String matriculaFuncionario = ""
						+ medicaoHistrico.getFuncionario().getId();
				String matriculaImovel = ""
						+ medicaoHistrico.getImovel().getId();
				String inscricaoImovel = ""
						+ medicaoHistrico.getImovel().getInscricaoFormatada();
				String tipoMedicao = ""
						+ medicaoHistrico.getMedicaoTipo().getId();
				String dataLeitura = medicaoHistrico
						.getDataLeituraParaRegistrar();
				String dataLeituraFormatada = dataLeitura.substring(0, 2) + "/"
						+ dataLeitura.substring(2, 4) + "/"
						+ dataLeitura.substring(4, 8);
				String codigoAnormalidade = "";
				if (medicaoHistrico.getLeituraAnormalidadeInformada() != null
						&& !medicaoHistrico.getLeituraAnormalidadeInformada()
								.equals("")) {
					codigoAnormalidade = ""
							+ medicaoHistrico.getLeituraAnormalidadeInformada()
									.getId();
				}else{
					codigoAnormalidade = "0";
				}
				// indicador confirmação leitura
				String indicadorConfirmacaoLeitura = medicaoHistrico
						.getIndicadorConfirmacaoLeitura();
				String errosTxt = medicaoHistrico.getGerarRelatorio();
				String leituraHidrometro = ""
						+ medicaoHistrico.getLeituraAtualInformada();

				relatorioBean = new RelatorioRegistrarLeiturasAnormalidadesBean(
						matriculaFuncionario, matriculaImovel, inscricaoImovel,
						tipoMedicao, dataLeituraFormatada, codigoAnormalidade,
						indicadorConfirmacaoLeitura, errosTxt,
						leituraHidrometro);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("idFaturamentoGrupo", ""+idFaturamento);

		String formatarAnoMesReferencia = anoMesReferencia.substring(4, 6)
				+ "/" + anoMesReferencia.substring(0, 4);

		parametros.put("mesAnoReferencia", formatarAnoMesReferencia);
		
		parametros.put("nomeArquivo", nomeArquivo);

		//cria uma instância do dataSource do relatório
		if ( relatorioBeans.size() > 0) {
			RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_REGISTRAR_LEITURAS_ANORMALIDADES,
					parametros, ds, tipoFormatoRelatorio);
		} else {

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
	}

}
