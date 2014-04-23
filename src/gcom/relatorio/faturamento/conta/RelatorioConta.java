package gcom.relatorio.faturamento.conta;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de contas
 * 
 * @author Rafael Corrêa
 * @created 27/07/2009
 */
public class RelatorioConta extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioConta(Usuario usuario) {
		super(usuario,
				ConstantesRelatorios.RELATORIO_CONTAS);
	}

	@Deprecated
	public RelatorioConta() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		Integer mesAno = (Integer) getParametro("mesAno");
		Integer idGrupo = (Integer) getParametro("idGrupo");
		Integer idLocalidadeInicial = (Integer) getParametro("idLocalidadeInicial");
		Integer idLocalidadeFinal = (Integer) getParametro("idLocalidadeFinal");
		Integer codigoSetorComercialInicial = (Integer) getParametro("codigoSetorComercialInicial");
		Integer codigoSetorComercialFinal = (Integer) getParametro("codigoSetorComercialFinal");
		Short codigoRotaInicial = (Short) getParametro("codigoRotaInicial");
		Short codigoRotaFinal = (Short) getParametro("codigoRotaFinal");
		Short sequencialRotaInicial = (Short) getParametro("sequencialRotaInicial");
		Short sequencialRotaFinal = (Short) getParametro("sequencialRotaFinal");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String indicadorEmissao = (String) getParametro("indicadorEmissao");
		String indicadorOrdenacao = (String) getParametro("indicadorOrdenacao");
		
		// valor de retorno
		byte[] retorno = null;


		Fachada fachada = Fachada.getInstancia();

		// coleção de beans do relatório
		List relatorioBeans = (List) fachada.pesquisarDadosContaRelatorio(
				mesAno, idGrupo, idLocalidadeInicial, idLocalidadeFinal,
				codigoSetorComercialInicial, codigoSetorComercialFinal,
				codigoRotaInicial, codigoRotaFinal,
				sequencialRotaInicial, sequencialRotaFinal, indicadorEmissao, indicadorOrdenacao);
		
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

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

		String nomeEmpresa = sistemaParametro.getNomeEmpresa();
		String enderecoEmpresaParte01 = sistemaParametro.getEnderecoFormatadoParte01();
		String enderecoEmpresaParte02 = sistemaParametro.getEnderecoFormatadoParte02();
		String enderecoEmpresaSemComplemento = sistemaParametro.getEnderecoFormatadoSemComplemento();
		String cepEmpresa = sistemaParametro.getCep().getCepFormatado();
		String cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		
		
		String telefoneGeral = "";
		if (sistemaParametro.getLogradouroBairro().getBairro().getMunicipio().getDdd() != null) {
			telefoneGeral += "(" + sistemaParametro.getLogradouroBairro().getBairro().getMunicipio().getDdd() + ") ";
		}
		telefoneGeral += Util.formatarTelefone(sistemaParametro.getNumeroTelefone());
		String fax = "";
		if (sistemaParametro.getLogradouroBairro().getBairro().getMunicipio().getDdd() != null) {
			fax += "(" + sistemaParametro.getLogradouroBairro().getBairro().getMunicipio().getDdd() + ") ";
		}
		fax += Util.formatarTelefone(sistemaParametro.getNumeroFax());
		String cidadeEstado =  sistemaParametro.getNomeEstado();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("enderecoEmpresaParte01", enderecoEmpresaParte01);
		parametros.put("enderecoEmpresaParte02", enderecoEmpresaParte02);
		parametros.put("enderecoEmpresaSemComplemento", enderecoEmpresaSemComplemento);
		parametros.put("cepEmpresa", cepEmpresa);
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("telefoneGeral", telefoneGeral);
		parametros.put("fax", fax);
		parametros.put("cidadeEstado", cidadeEstado);

		retorno = gerarRelatorio(
				ConstantesRelatorios.RELATORIO_CONTAS,
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
		AgendadorTarefas.agendarTarefa("RelatorioContas",
				this);
	}
}
