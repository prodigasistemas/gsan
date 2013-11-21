package gcom.relatorio.arrecadacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.arrecadacao.FiltrarDadosDiariosArrecadacaoActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC0339] Consultar Dados Diários da Arrecadação
 * 
 * Gerar Relatório Dados Diários da Arrecadação por Gerência
 * 
 * @author Mariana Victor
 * @date 01/02/2011
 */
public class RelatorioDadosDiariosArrecadacaoParametros extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	
	public RelatorioDadosDiariosArrecadacaoParametros(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_DOCUMENTO_PARAMETROS);
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		
		// valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Collection<ImovelPerfil> colecaoImovelPerfil = (Collection<ImovelPerfil>) getParametro("colecaoImovelPerfil");
		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = (Collection<LigacaoAguaSituacao>) getParametro("colecaoLigacaoAguaSituacao");
		Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = (Collection<LigacaoEsgotoSituacao>) getParametro("colecaoLigacaoEsgotoSituacao");
		Collection<Categoria> colecaoCategoria = (Collection<Categoria>) getParametro("colecaoCategoria");
		Collection<EsferaPoder> colecaoEsferaPoder = (Collection<EsferaPoder>) getParametro("colecaoEsferaPoder");
		Collection colecaoDocumentoTipo = (Collection) getParametro("colecaoDocumentoTipo");
		FiltrarDadosDiariosArrecadacaoActionForm form = (FiltrarDadosDiariosArrecadacaoActionForm) getParametro("filtrarDadosDiariosArrecadacaoActionForm");

		// Parâmetros do relatório
		Map parametros = new HashMap();

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
		
		String cnpjEmpresa = "";
		if (sistemaParametro.getCnpjEmpresa() != null) {
			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		}
		
		// Usuario que emite o relatorio
		Usuario usuario = this.getUsuario();

		String nomeUsuario = usuario.getNomeUsuario();
		
		parametros.put("nomeUsuario", nomeUsuario);
		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("enderecoEmpresa", sistemaParametro.getEnderecoFormatado());
		parametros.put("cepEmpresa", sistemaParametro.getCep().getCepFormatado());
		parametros.put("telefoneGeral", sistemaParametro.getNumeroTelefone());
		
		if (form.getPeriodoArrecadacaoFim() != null) {
			parametros.put("periodoArrecadacaoFim",form.getPeriodoArrecadacaoFim());
		} else {
			parametros.put("periodoArrecadacaoFim","");
		}
		
		if (form.getPeriodoArrecadacaoInicio() != null) {
			parametros.put("periodoArrecadacaoInicio",form.getPeriodoArrecadacaoInicio());
		} else {
			parametros.put("periodoArrecadacaoInicio","");
		}

		if (form.getNomeGerenciaRegional() != null) {
			parametros.put("nomeGerenciaRegional",form.getNomeGerenciaRegional());
		} else {
			parametros.put("nomeGerenciaRegional","");
		}

		if (form.getNomeElo() != null) {
			parametros.put("nomeElo",form.getNomeElo());
		} else {
			parametros.put("nomeElo","");
		}

		if (form.getDescricaoLocalidade() != null) {
			parametros.put("descricaoLocalidade",form.getDescricaoLocalidade());
		} else {
			parametros.put("descricaoLocalidade","");
		}

		if (form.getNomeArrecadador() != null) {
			parametros.put("nomeArrecadador",form.getNomeArrecadador());
		} else {
			parametros.put("nomeArrecadador","");
		}
		
		
		Collection<RelatorioDadosDiariosArrecadacaoParametrosBean> colecaoBean = this
			.inicializarBeanRelatorio(colecaoImovelPerfil, colecaoLigacaoAguaSituacao,
					colecaoLigacaoEsgotoSituacao, colecaoCategoria, colecaoEsferaPoder,
					colecaoDocumentoTipo);

		RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);

		retorno = this
				.gerarRelatorio(
						ConstantesRelatorios.RELATORIO_RELATORIO_DADOS_DIARIOS_DOCUMENTO_PARAMETROS,
						parametros, ds, tipoFormatoRelatorio);

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDadosDiariosArrecadacaoParametros", this);
	}
	
	private Collection<RelatorioDadosDiariosArrecadacaoParametrosBean> inicializarBeanRelatorio(
			Collection<ImovelPerfil> colecaoImovelPerfil, Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao,
			Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao, Collection<Categoria> colecaoCategoria,
			Collection<EsferaPoder> colecaoEsferaPoder, Collection colecaoDocumentoTipo){
		
		Collection<RelatorioDadosDiariosArrecadacaoParametrosBean> colecaoBean = new ArrayList<RelatorioDadosDiariosArrecadacaoParametrosBean>();

		RelatorioDadosDiariosArrecadacaoParametrosBean bean = new RelatorioDadosDiariosArrecadacaoParametrosBean();

		// Coleção Perfil do Imóvel
		Iterator iteratorImovelPerfil = colecaoImovelPerfil.iterator();
		while (iteratorImovelPerfil.hasNext()) {
			ImovelPerfil imovelPerfil = (ImovelPerfil) iteratorImovelPerfil.next();
			
			RelatorioDadosDiariosArrecadacaoParametrosBean grupoBean = new RelatorioDadosDiariosArrecadacaoParametrosBean();
			grupoBean.setImovelPerfilDescricao(imovelPerfil.getDescricao());
			colecaoBean.add(grupoBean);
		}
		
		// Colecao de Ligação de Água
		Iterator iterator = colecaoLigacaoAguaSituacao.iterator();
		while (iterator.hasNext()) {
			LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
				
			RelatorioDadosDiariosArrecadacaoParametrosBean grupoBean = new RelatorioDadosDiariosArrecadacaoParametrosBean();
			grupoBean.setLigacaoAguaSituacaoDescricao(ligacaoAguaSituacao.getDescricao());
			colecaoBean.add(grupoBean);
		}
		
		// Coleção Situação da Ligação de Esgoto
		Iterator iteratorLigacaoEsgotoSituacao = colecaoLigacaoEsgotoSituacao.iterator();
		while (iteratorLigacaoEsgotoSituacao.hasNext()) {
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iteratorLigacaoEsgotoSituacao.next();
				
			RelatorioDadosDiariosArrecadacaoParametrosBean grupoBean = new RelatorioDadosDiariosArrecadacaoParametrosBean();
			grupoBean.setLigacaoEsgotoSituacaoDescricao(ligacaoEsgotoSituacao.getDescricao());
			colecaoBean.add(grupoBean);
		}
		
		// Coleção Situação da Categoria
		Iterator iteratorCategoria = colecaoCategoria.iterator();
		while (iteratorCategoria.hasNext()) {
			Categoria categoria = (Categoria) iteratorCategoria.next();
					
			RelatorioDadosDiariosArrecadacaoParametrosBean grupoBean = new RelatorioDadosDiariosArrecadacaoParametrosBean();
			grupoBean.setCategoriaDescricao(categoria.getDescricao());
			colecaoBean.add(grupoBean);
		}
		
		// Coleção Situação da Esfera de Poder
		Iterator iteratorEsferaPoder = colecaoEsferaPoder.iterator();
		while (iteratorEsferaPoder.hasNext()) {
			EsferaPoder esferaPoder = (EsferaPoder) iteratorEsferaPoder.next();
						
			RelatorioDadosDiariosArrecadacaoParametrosBean grupoBean = new RelatorioDadosDiariosArrecadacaoParametrosBean();
			grupoBean.setEsferaPoderDescricao(esferaPoder.getDescricao());
			colecaoBean.add(grupoBean);
		}
		
		// Coleção Situação da Esfera de Poder
		Iterator iteratorDocumentoTipo = colecaoDocumentoTipo.iterator();
		while (iteratorDocumentoTipo.hasNext()) {
			DocumentoTipo documentoTipo = (DocumentoTipo) iteratorDocumentoTipo.next();
						
			RelatorioDadosDiariosArrecadacaoParametrosBean grupoBean = new RelatorioDadosDiariosArrecadacaoParametrosBean();
			grupoBean.setDocumentoTipoDescricao(documentoTipo.getDescricaoDocumentoTipo());
			colecaoBean.add(grupoBean);
		}
		
		/*
		//Coleção Perfil do Imóvel
		List<RelatorioDadosDiariosArrecadacaoParametrosSubBean> listaImovelPerfil= new ArrayList();
		Iterator iteratorImovelPerfil = colecaoImovelPerfil.iterator();
		while (iteratorImovelPerfil.hasNext()) {
			ImovelPerfil imovelPerfil = (ImovelPerfil) iteratorImovelPerfil.next();
			
			RelatorioDadosDiariosArrecadacaoParametrosSubBean subBean = new RelatorioDadosDiariosArrecadacaoParametrosSubBean();
			subBean.setDescricao(imovelPerfil.getDescricao());
			
			listaImovelPerfil.add(subBean);
		}
		
		bean.setColecaoImovelPerfil(new RelatorioDataSource(listaImovelPerfil));
		
		
		// Colecao de Ligação de Água
		List<RelatorioDadosDiariosArrecadacaoParametrosSubBean> listaLigacaoAgua = new ArrayList();
		Iterator iterator = colecaoLigacaoAguaSituacao.iterator();
		while (iterator.hasNext()) {
			LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) iterator.next();
			
			RelatorioDadosDiariosArrecadacaoParametrosSubBean subBean = new RelatorioDadosDiariosArrecadacaoParametrosSubBean();
			subBean.setDescricao(ligacaoAguaSituacao.getDescricao());
			
			listaLigacaoAgua.add(subBean);
		}

		bean.setColecaoLigacaoAguaSituacao(new RelatorioDataSource(listaLigacaoAgua));
		
		
		//Coleção Situação da Ligação de Esgoto
		List<RelatorioDadosDiariosArrecadacaoParametrosSubBean> listaLigacaoEsgotoSituacao = new ArrayList();
		Iterator iteratorLigacaoEsgotoSituacao = colecaoLigacaoEsgotoSituacao.iterator();
		while (iteratorLigacaoEsgotoSituacao.hasNext()) {
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iteratorLigacaoEsgotoSituacao.next();
			
			RelatorioDadosDiariosArrecadacaoParametrosSubBean subBean = new RelatorioDadosDiariosArrecadacaoParametrosSubBean();
			subBean.setDescricao(ligacaoEsgotoSituacao.getDescricao());
			
			listaLigacaoAgua.add(subBean);
		}

		bean.setColecaoLigacaoEsgotoSituacao(new RelatorioDataSource(listaLigacaoEsgotoSituacao));
		

		//Coleção Situação da Categoria
		List<RelatorioDadosDiariosArrecadacaoParametrosSubBean> listaCategoria = new ArrayList();
		Iterator iteratorCategoria = colecaoCategoria.iterator();
		while (iteratorCategoria.hasNext()) {
			Categoria categoria = (Categoria) iteratorCategoria.next();
			
			RelatorioDadosDiariosArrecadacaoParametrosSubBean subBean = new RelatorioDadosDiariosArrecadacaoParametrosSubBean();
			subBean.setDescricao(categoria.getDescricao());
			
			listaCategoria.add(subBean);
		}

		bean.setColecaoCategoria(new RelatorioDataSource(listaCategoria));
		

		//Coleção Situação da Esfera de Poder
		List<RelatorioDadosDiariosArrecadacaoParametrosSubBean> listaEsferaPoder = new ArrayList();
		Iterator iteratorEsferaPoder = colecaoEsferaPoder.iterator();
		while (iteratorEsferaPoder.hasNext()) {
			EsferaPoder esferaPoder = (EsferaPoder) iteratorEsferaPoder.next();
			
			RelatorioDadosDiariosArrecadacaoParametrosSubBean subBean = new RelatorioDadosDiariosArrecadacaoParametrosSubBean();
			subBean.setDescricao(esferaPoder.getDescricao());
			
			listaEsferaPoder.add(subBean);
		}

		bean.setColecaoEsferaPoder(new RelatorioDataSource(listaEsferaPoder));


		//Coleção Situação da Esfera de Poder
		List<RelatorioDadosDiariosArrecadacaoParametrosSubBean> listaDocumentoTipo = new ArrayList();
		Iterator iteratorDocumentoTipo = colecaoDocumentoTipo.iterator();
		while (iteratorDocumentoTipo.hasNext()) {
			DocumentoTipo documentoTipo = (DocumentoTipo) iteratorDocumentoTipo.next();
			
			RelatorioDadosDiariosArrecadacaoParametrosSubBean subBean = new RelatorioDadosDiariosArrecadacaoParametrosSubBean();
			subBean.setDescricao(documentoTipo.getDescricaoDocumentoTipo());
			
			listaDocumentoTipo.add(subBean);
		}

		bean.setColecaoDocumentoTipo(new RelatorioDataSource(listaDocumentoTipo));
		*/
		
		colecaoBean.add(bean);
		
		return colecaoBean;
	}
}
