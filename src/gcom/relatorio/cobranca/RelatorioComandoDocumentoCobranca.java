package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.RelatorioComandoDocumentoCobrancaHelper;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioComandoDocumentoCobranca extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;

	public RelatorioComandoDocumentoCobranca(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_NOTIFICACAO_DEBITO);
	}

	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Integer idCobrancaAcaoCronograma = (Integer) getParametro("idCobrancaAcaoCronograma");
		Integer idCobrancaAcaoComando = (Integer)  getParametro("idCobrancaAcaoComando");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		CobrancaAcao cobrancaAcao = (CobrancaAcao) getParametro("cobrancaAcao");
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		// RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		// Parâmetros do relatório
		Map parametros = new HashMap();

		RelatorioComandoDocumentoCobrancaBean relatorioComandoDocumentoCobrancaBean = null;
		Collection colecaoComandoDocumentoCobrancaHelper = fachada
				.gerarRelatorioComandoDocumentoCobranca(
						idCobrancaAcaoCronograma, idCobrancaAcaoComando);

		Iterator colecaoComandoDocumentoCobrancaHelperIterator = colecaoComandoDocumentoCobrancaHelper
				.iterator();

		boolean flagTerminou = false;

		int totalContas = colecaoComandoDocumentoCobrancaHelper.size();
		int quantidadeContas = 0;

		while (!flagTerminou) {

			RelatorioComandoDocumentoCobrancaHelper comandoDocumentoCobrancaHelperPrimeiraConta = null;
			RelatorioComandoDocumentoCobrancaHelper comandoDocumentoCobrancaHelperSegundaConta = null;

			while (colecaoComandoDocumentoCobrancaHelperIterator.hasNext()) {

				RelatorioComandoDocumentoCobrancaHelper comandoDocumentoCobrancaHelper = (RelatorioComandoDocumentoCobrancaHelper) colecaoComandoDocumentoCobrancaHelperIterator
						.next();

				if (comandoDocumentoCobrancaHelperPrimeiraConta == null) {
					comandoDocumentoCobrancaHelperPrimeiraConta = comandoDocumentoCobrancaHelper;
				} else {
					comandoDocumentoCobrancaHelperSegundaConta = comandoDocumentoCobrancaHelper;
				}

				if (comandoDocumentoCobrancaHelperPrimeiraConta != null
						&& comandoDocumentoCobrancaHelperSegundaConta != null) {

					relatorioComandoDocumentoCobrancaBean = new RelatorioComandoDocumentoCobrancaBean(
							comandoDocumentoCobrancaHelperPrimeiraConta,
							comandoDocumentoCobrancaHelperSegundaConta);

					relatorioBeans.add(relatorioComandoDocumentoCobrancaBean);

					comandoDocumentoCobrancaHelperPrimeiraConta = null;
					comandoDocumentoCobrancaHelperSegundaConta = null;
				}

				quantidadeContas++;

				if (quantidadeContas == totalContas) {
					break;
				}

			}

			// Caso tenha sobrado apenas uma conta
			if (comandoDocumentoCobrancaHelperPrimeiraConta != null) {

				comandoDocumentoCobrancaHelperSegundaConta = new RelatorioComandoDocumentoCobrancaHelper();
				comandoDocumentoCobrancaHelperSegundaConta.setInscricao(null);

				relatorioComandoDocumentoCobrancaBean = new RelatorioComandoDocumentoCobrancaBean(
						comandoDocumentoCobrancaHelperPrimeiraConta,
						comandoDocumentoCobrancaHelperSegundaConta);

				relatorioBeans.add(relatorioComandoDocumentoCobrancaBean);
			}

			if (quantidadeContas == totalContas) {
				flagTerminou = true;
			}

		}
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		if ( relatorioBeans.size() <= 0  ) {
		
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		
		
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		if (sistemaParametro.getNumeroTelefone() != null
				&& !sistemaParametro.getNumeroTelefone().equals("")) {
			parametros.put("telefoneGeral", Util
					.formatarTelefone(sistemaParametro.getNumeroTelefone()));

		} else {
			parametros.put("telefoneGeral", "");
		}
		
		if (sistemaParametro.getCep() != null
				&& sistemaParametro.getCep().getCodigo() != null) {
			parametros.put("cep", Util.formatarCEP(sistemaParametro.getCep()
					.getCodigo().toString()));
		} else {
			parametros.put("cep", "");
		}

		if (sistemaParametro.getCnpjEmpresa() != null
				&& !sistemaParametro.getCnpjEmpresa().equals("")) {

			parametros.put("cnpj", Util.formatarCnpj(sistemaParametro
					.getCnpjEmpresa()));
		} else {
			parametros.put("cnpj", "");
		}
		
		if(colecaoComandoDocumentoCobrancaHelper!=null && !colecaoComandoDocumentoCobrancaHelper.isEmpty()){

		String endereco = sistemaParametro.getEnderecoFormatado();
			parametros.put("endereco", endereco);
		}
		
		parametros.put("nomeEmpresa", sistemaParametro.getNomeEmpresa());

		if (sistemaParametro.getNomeAbreviadoEmpresa() != null) {
			parametros.put("nomeAbreviadoEmpresa", sistemaParametro
					.getNomeAbreviadoEmpresa());
		} else {
			parametros.put("nomeAbreviadoEmpresa", "");
		}

		if (sistemaParametro.getNumero0800Empresa() != null) {
			parametros.put("numeroAtendimento", sistemaParametro
					.getNumero0800Empresa());
		}else{
			parametros.put("numeroAtendimento", "");
		}
		if (cobrancaAcao != null) {
			parametros.put("acaoCobranca", cobrancaAcao
					.getDescricaoCobrancaAcao());
		}else{
			parametros.put("acaoCobranca", "");
		}
		
		String informacao = "";

		if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
				SistemaParametro.EMPRESA_SAAE)) {
			informacao = "	Nosso objetivo é fornecer o que há de melhor em atendimento e qualidade no "
					+ "fornecimento de água para seu imóvel, o que somente será alcançado com o pagamento em dia "
					+ "das contas de água. \n"
					+ "	Ocorre que, o atraso no pagamento de sua conta de água, bem como a falta "
					+ "de atendimento aos nossos comunicados quanto ao referido atraso, nos levaram "
					+ "a adotar a medida cabível no caso, ou seja o corte do fornecimento de água para seu imóvel. \n"
					+ "	Lembramos que a religação indevida do ramal predial é infração prevista no "
					+ "Regulamento de Serviço prestados pelo SAAE, passível de multa. "
					+ "Além disso a referida infração constitui crimes de furto e dano, "
					+ "previstos nos artigos 155 e 163 do Código Penal, o que os levará a "
					+ "providenciar a imediata lavraturado do Boletim de Ocorrência, com vistas a "
					+ "adoção das medidas cabíveis ao caso. "
					+ "	Face ao exposto, gentilmente, solicitamos, mais uma vez, o seu "
					+ "comparecimento nesta Empresa, a fim de firmarmos um acordo que lhe "
					+ "permita efetuar o pagamento dos débitos em atraso. "
					+ "Atenciosamente, ";
		} else if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
				SistemaParametro.EMPRESA_CAER)) {
			informacao = "   Nosso objetivo é fornecer o que há de melhor em atendimento e qualidade no fornecimento de água para seu imóvel, "
					+ "o que somente será alcançado com o pagamento em dia das contas de água. \n"
					+ "	Ocorre que, o atraso no pagamento de sua conta de água, bem como a falta de atendimento aos nossos comunicados quanto ao "
					+ "referido atraso, nos levaram a adotar a medida cabível no caso, ou seja o corte do fornecimento de água para seu imóvel. \n"
					+ "	Lembramos que a religação indevida do ramal predial é infração prevista no decreto n.º 4.784-E de 23 de maio de 2002 "
					+ "do Regulamento de Serviço prestados pela CAER, passível de multa equivalente a 12(doze) vezes a taxa mínima, hoje "
					+ "perfazendo o total de R$ 90,72(noventa reais e setenta e dois centavos). Além disso a referida infração constitui crimes "
					+ "de furto e dano, previstos nos artigos 155 e 163 do Código Penal, o que os levará a providenciar a imediata lavraturado do "
					+ "	Boletim de Ocorrência, com vistas a adoção das medidas cabíveis ao caso. \n"
					+ "	Face ao exposto, gentilmente, solicitamos, mais uma vez, o seu comparecimento "
					+ "nesta Empresa, a fim de firmarmos um acordo que lhe permita efetuar o pagamento dos débitos em atraso. "
					+ "Atenciosamente, ";
		}else if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
			SistemaParametro.EMPRESA_COSANPA)) {
			informacao = "  A violação do dispositivo de suspensão (corte) do ramal predial de água ou de esgoto constitui infração cujo "
				+ "valor multa é de R$ 86,40 e, em caso de reincidência, supressão imediata do ramal predial de abastecimento de "
				+ "água ou coleta de esgoto sanitário. \n"
				+ "	Lembramos que a religação indevida do ramal predial é infração prevista no "
				+ "Regulamento de Serviço prestados pela COSANPA. "
				+ "Além disso a referida infração constitui crimes de furto e dano, "
				+ "previstos nos artigos 155 e 163 do Código Penal, o que nos levará a "
				+ "providenciar a imediata lavratura do Boletim de Ocorrência Policial, com vistas a "
				+ "adoção das medidas cabíveis ao caso. \n"
				+ "	Face ao exposto, gentilmente, solicitamos, mais uma vez, o seu "
				+ "comparecimento nesta Empresa, a fim de firmarmos um acordo que lhe "
				+ "permita efetuar o pagamento dos débitos em atraso. \n"
				+ " Em caso de Execução de Corte em que houver a necessidade de intervir na calçada ou meio fio o custo de "
				+ "recomposição será cobrado do cliente. \n \n"
				+ "Atenciosamente, ";
	}

		parametros.put("informacao", informacao);

		
		if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_COSANPA)) {
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_COMANDO_DOCUMENTO_COBRANCA_COSANPA,
			parametros, ds, tipoFormatoRelatorio);
		}
		else{
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_COMANDO_DOCUMENTO_COBRANCA,
			parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.RELATORIO_COMANDO_DOCUMENTO_COBRANCA,
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
		int retorno = 1;

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioComandoDocumentoCobranca",
				this);
	}

}