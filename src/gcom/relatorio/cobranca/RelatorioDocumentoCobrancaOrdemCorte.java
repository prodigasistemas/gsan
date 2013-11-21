package gcom.relatorio.cobranca;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
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
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * 
 * @author Mariana Vcitor, Raimundo Martins
 * @date 17/01/2011, 03/08/2011
 * 
 */
public class RelatorioDocumentoCobrancaOrdemCorte extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	public RelatorioDocumentoCobrancaOrdemCorte(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DOCUMENTO_COBRANCA_ORDEM_CORTE);
	}
	public Object executar() throws TarefaException {

//		 ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Integer idCobrancaAcaoCronograma = (Integer) getParametro("idCobrancaAcaoCronograma");
		Integer idCobrancaAcaoComando = (Integer) getParametro("idCobrancaAcaoComando");
		CobrancaAcao cobrancaAcao = (CobrancaAcao) getParametro("cobrancaAcao");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		//quantidades de relatorio a serem impressos em cada página
		String quantidadeRelatorios = (String) getParametro("quantidadeRelatorios");

		//tamanho máximo de contas a aparecerem no relatório.
		int tamanhoMaximoDebito = 11;

		// valor de retorno
		byte[] retorno = null;


		Fachada fachada = Fachada.getInstancia();

		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
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
		
		List relatorioBeans = (List) fachada.gerarRelatorioDocumentoCobrancaOrdemCorte(idCobrancaAcaoCronograma,
				idCobrancaAcaoComando, tamanhoMaximoDebito, quantidadeRelatorios);
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);
		

		String nomeEmpresa = sistemaParametro.getNomeEmpresa();
		String nomeAbreviadoEmpresa = sistemaParametro.getNomeAbreviadoEmpresa();
		String enderecoEmpresa = sistemaParametro.getEnderecoFormatado();
		String cepEmpresa = sistemaParametro.getCep().getCepFormatado();
		String cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		String telefoneGeral = "Fone Geral: ";
		telefoneGeral += Util.formatarTelefone(sistemaParametro.getNumeroTelefone());
		String numeroAtendimento = "ATENDIMENTO \n";
		numeroAtendimento +=  sistemaParametro.getNumero0800Empresa();

		List<String> tipoCorte =  fachada.pesquisarTipoDeCorte();
		if (tipoCorte != null && !tipoCorte.isEmpty()) {
			Iterator iterator = tipoCorte.iterator();
			int i = 1;
			while (iterator.hasNext()) {
				parametros.put("tipoCorte0"+i, iterator.next());
				i++;
			}
		}
		
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("nomeAbreviadoEmpresa", nomeAbreviadoEmpresa);
		parametros.put("enderecoEmpresa", enderecoEmpresa);
		parametros.put("cepEmpresa", cepEmpresa);
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("telefoneGeral", telefoneGeral);
		parametros.put("numeroAtendimento", numeroAtendimento);
		parametros.put("dataCorrente", Util.formatarData(new Date()));
		
		if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
				SistemaParametro.EMPRESA_CAERN)){
			parametros.put("agenciaCodigoCedente", "3795-8/9121-9");
		}
		if(sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
				SistemaParametro.EMPRESA_CAERN)){
			parametros.put("telefoneGeral", "");
			parametros.put("numeroAtendimento", "");
		}
		
		if (cobrancaAcao != null) {
			parametros.put("acaoCobranca", cobrancaAcao
					.getDescricaoCobrancaAcao());
		}else{
			parametros.put("acaoCobranca", "");
		}
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_DOCUMENTO_COBRANCA_ORDEM_CORTE,
		parametros, ds, tipoFormatoRelatorio);


		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno,
					Relatorio.ANORMALIDADE_CONSUMO,
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
		AgendadorTarefas.agendarTarefa("RelatorioVisitaCobranca",
				this);
	}
}