package gcom.relatorio.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.ExtratoQuitacao;
import gcom.faturamento.ExtratoQuitacaoItem;
import gcom.faturamento.FiltroExtratoQuitacao;
import gcom.gui.ActionServletException;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioDeclaracaoAnualQuitacaoDebitos extends TarefaRelatorio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RelatorioDeclaracaoAnualQuitacaoDebitos(Usuario usuario,String nomeRelatorio) {
		
		super(usuario, nomeRelatorio);
	}

	@Deprecated
	public RelatorioDeclaracaoAnualQuitacaoDebitos() {
		super(null, "");
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		
		
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDeclaracaoAnualQuitacaoDebitos", this);

	}

	@Override
	public Object executar() throws TarefaException {


		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String ano = (String) getParametro("ano");
		String matricula = (String) getParametro("matricula");

		// valor de retorno
		byte[] retorno = null;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		FiltroExtratoQuitacao filtro = new FiltroExtratoQuitacao();
		
		filtro.adicionarParametro(new ParametroSimples(
				FiltroExtratoQuitacao.ID_IMOVEL,matricula));
		
		filtro.adicionarParametro(new ParametroSimples(
				FiltroExtratoQuitacao.ANO_REFERENCIA,ano));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroExtratoQuitacao.IMOVEL);
		
		Collection colecaoExtratoQuitacao =
			fachada.pesquisar(filtro, ExtratoQuitacao.class.getName());
		
		ExtratoQuitacao extrato = (ExtratoQuitacao) Util.retonarObjetoDeColecao(colecaoExtratoQuitacao);
		
		if(extrato == null){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		String cliente = fachada.consultarClienteUsuarioImovel(extrato.getImovel().getId());
		String endereco = fachada.pesquisarEndereco(extrato.getImovel().getId());
		String matriculaFormatada = Util.retornaMatriculaImovelFormatada(extrato.getImovel().getId());
		
		
		
		Collection<ExtratoQuitacaoItem> colecaoExtratosItens 
			= fachada.pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(extrato.getId());
		
		RelatorioDeclaracaoAnualQuitacaoDebitosBean bean = null;
		BigDecimal valorTotal = new BigDecimal("0.0");
		for (ExtratoQuitacaoItem extratoQuitacaoItem : colecaoExtratosItens) {
			bean = new RelatorioDeclaracaoAnualQuitacaoDebitosBean(
					extrato.getAnoReferencia().toString(), 
					Util.formatarAnoMesParaMesAno(extratoQuitacaoItem.getAnoMesReferenciaConta()),
					extratoQuitacaoItem.getDescricaoSituacao(), 
					Util.formatarData(extratoQuitacaoItem.getDataSituacao()),
					Util.formatarMoedaReal(extratoQuitacaoItem.getValorConta()));
			
			valorTotal = valorTotal.add(extratoQuitacaoItem.getValorConta());
			
			relatorioBeans.add(bean);
			
		}
		
		bean = new RelatorioDeclaracaoAnualQuitacaoDebitosBean();
		
		bean.setAnoMes("TOTAL");
		bean.setValor(Util.formatarMoedaReal(valorTotal));
		relatorioBeans.add(bean);
		
		// Parâmetros do relatório
		Map parametros = new HashMap();

		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String nomeEmpresa = sistemaParametro.getNomeAbreviadoEmpresa();
		
		String sequencial = "";
		
		/*
		 *
		 * Criação das variáveis localidade, setor, rota e sequencialRota
		 */
		
		String localidade = "";
		String setor = "";
		String rota = "";
		String sequencialRota = "";
		
		/*
		 *
		 * Adição da verificação OU para nomeEmpresa.equalsIgnoreCase("COSANPA") no IF abaixo
		 */
		
		if(nomeEmpresa.equalsIgnoreCase("CAERN") || nomeEmpresa.equalsIgnoreCase("COSANPA")){
			
			Object[] idLocalidadeCodigoSetor =
				fachada.pesquisarLocalidadeCodigoSetorImovel(new Integer(matricula));
			
			localidade = idLocalidadeCodigoSetor[0].toString();
			setor = idLocalidadeCodigoSetor[1].toString();
			
			Object[] rotaESequencialRotaDoImovel =
				fachada.obterRotaESequencialRotaDoImovelSeparados(new Integer(matricula));
			
			rota = rotaESequencialRotaDoImovel[0].toString();
			sequencialRota = rotaESequencialRotaDoImovel[1].toString();
			
			sequencial = localidade+"/"+setor+"/"+rota+"/"+sequencialRota;
			
		}else if(nomeEmpresa.equalsIgnoreCase("CAEMA")){
			
			String cnpjInscricao = "CNPJ: ";
			
			cnpjInscricao += Util.formatarCnpj(sistemaParametro.getCnpjEmpresa())+" Inscrição Estadual: ";
			cnpjInscricao += sistemaParametro.getInscricaoEstadual();
			
			parametros.put("cnpjInscricao", cnpjInscricao);
			
			parametros.put("nomeCompleto", sistemaParametro.getNomeEmpresa());
			
			parametros.put("nomeAbreviado", sistemaParametro.getNomeAbreviadoEmpresa());
			
			parametros.put("dataEmissao", Util.formatarData(new Date()));
				
			sequencial = extrato.getId().toString();
			
		}else{
			
			sequencial = extrato.getId().toString();

		}

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("matricula", matriculaFormatada);
		parametros.put("endereco", endereco);
		parametros.put("nome", cliente);
		parametros.put("ano", extrato.getAnoReferencia().toString());
		parametros.put("sequencial", sequencial);

		// cria uma instância do dataSource do relatório

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(this.getNomeRelatorio(), parametros, ds,tipoFormatoRelatorio);
		
		// retorna o relatório gerado
		return retorno;
	}
}
