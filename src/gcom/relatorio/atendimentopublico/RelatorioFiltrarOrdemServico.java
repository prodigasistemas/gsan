package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioFiltrarOrdemServico extends TarefaRelatorio {

	private static final long serialVersionUID = 1L;
	
	public RelatorioFiltrarOrdemServico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FILTRAR_ORDEM_SERVICO);
	}

	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		PesquisarOrdemServicoHelper filtroOS = (PesquisarOrdemServicoHelper) getParametro("pesquisarOrdemServicoHelper");
		filtroOS.setNumeroPaginasPesquisa(ConstantesSistema.NUMERO_NAO_INFORMADO);
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		
		// Parametros
		String numeroOS = "";
		if (filtroOS.getNumeroOS() != null) {
			numeroOS = filtroOS.getNumeroOS().toString();
		}
		
		String numeroRA = "";
		if (filtroOS.getNumeroRA() != null) {
			numeroRA = filtroOS.getNumeroRA().toString();
		}
		
		String documentoCobranca = "";
		if (filtroOS.getDocumentoCobranca() != null) {
			documentoCobranca = filtroOS.getDocumentoCobranca().toString();
		}
		
		Short codigoSituacao = filtroOS.getSituacaoOrdemServico();
		String situacao = "TODAS";
		if (codigoSituacao != null) {
			if (codigoSituacao == 1) situacao = "PENDENTES";
			if (codigoSituacao == 2) situacao = "ENCERRADAS";
			if (codigoSituacao == 3) situacao = "EXECUÇÃO EM ANDAMENTO";
			if (codigoSituacao == 4) situacao = "AGUARDANDO LIBERAÇÃO PARA EXECUÇÃO";
		}
		
		String indicadorTipoServico = "";
		if (filtroOS.getIndicadorTerceirizado() != null) indicadorTipoServico = "TERCEIRIZADO";
		if (filtroOS.getIndicadorPavimento() != null) indicadorTipoServico = "PAVIMENTO";
		if (filtroOS.getIndicadorVistoria() != null) indicadorTipoServico = "VISTORIA";
		
		String matriculaImovel = "";
		if (filtroOS.getMatriculaImovel() != null) {
			matriculaImovel = filtroOS.getMatriculaImovel().toString();
		}
		
		String codigoCliente = "";
		if (filtroOS.getCodigoCliente() != null) {
			codigoCliente = filtroOS.getCodigoCliente().toString();
		}
		
		String unidadeGeracao = "";
		if (filtroOS.getUnidadeGeracao() != null) {
			FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, filtroOS.getUnidadeGeracao()));
			
			Collection pesquisa = fachada.pesquisar(filtro, UnidadeOrganizacional.class.getName());
			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(pesquisa);
			
			unidadeGeracao = unidade.getDescricao();
		}
		
		String unidadeAtual = "";
		if (filtroOS.getUnidadeAtual() != null) {
			FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, filtroOS.getUnidadeAtual()));
			
			Collection pesquisa = fachada.pesquisar(filtro, UnidadeOrganizacional.class.getName());
			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(pesquisa);
			
			unidadeAtual = unidade.getDescricao();
		}
		
		String unidadeSuperior = "";
		if (filtroOS.getUnidadeSuperior() != null) {
			FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, filtroOS.getUnidadeSuperior()));
			
			Collection pesquisa = fachada.pesquisar(filtro, UnidadeOrganizacional.class.getName());
			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(pesquisa);
			
			unidadeAtual = unidade.getDescricao();
		}
		
		String dataAtendimento = "";
		if (filtroOS.getDataAtendimentoInicial() != null && filtroOS.getDataAtendimentoFinal() != null) {
			dataAtendimento = f.format(filtroOS.getDataAtendimentoInicial()) + " à " + f.format(filtroOS.getDataAtendimentoFinal());
		}
		
		String dataGeracao = "";
		if (filtroOS.getDataGeracaoInicial() != null && filtroOS.getDataGeracaoFinal() != null) {
			dataGeracao = f.format(filtroOS.getDataGeracaoInicial()) + " à " + f.format(filtroOS.getDataGeracaoFinal());
		}
		
		String dataProgramacao = "";
		if (filtroOS.getDataProgramacaoInicial() != null && filtroOS.getDataProgramacaoFinal() != null) {
			dataProgramacao = f.format(filtroOS.getDataProgramacaoInicial()) + " à " + f.format(filtroOS.getDataProgramacaoFinal());
		}
		
		String dataEncerramento = "";
		if (filtroOS.getDataEncerramentoInicial() != null && filtroOS.getDataEncerramentoFinal() != null) {
			dataEncerramento = f.format(filtroOS.getDataEncerramentoInicial()) + " à " + f.format(filtroOS.getDataEncerramentoFinal());
		}
		
		String municipio = "";
		if (filtroOS.getMunicipio() != null) {
			FiltroMunicipio filtro = new FiltroMunicipio();
			filtro.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, filtroOS.getMunicipio()));
			
			Collection pesquisa = fachada.pesquisar(filtro, Municipio.class.getName());
			Municipio objetoMunicipio = (Municipio) Util.retonarObjetoDeColecao(pesquisa);
			
			municipio = objetoMunicipio.getNome();
		}
		
		String bairro = "";
		if (filtroOS.getBairro() != null && filtroOS.getMunicipio() != null) {
			FiltroBairro filtro = new FiltroBairro();
			filtro.adicionarParametro(new ParametroSimples(FiltroBairro.ID, filtroOS.getBairro()));
			
			Collection pesquisa = fachada.pesquisar(filtro, Bairro.class.getName());
			Bairro objetoBairro = (Bairro) Util.retonarObjetoDeColecao(pesquisa);
			
			bairro = objetoBairro.getNome();
		}
		
		String logradouro = "";
		if (filtroOS.getLogradouro() != null) {
			FiltroLogradouro filtro = new FiltroLogradouro();
			filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, filtroOS.getLogradouro()));
			
			Collection pesquisa = fachada.pesquisar(filtro, Logradouro.class.getName());
			Logradouro objetoLogradouro = (Logradouro) Util.retonarObjetoDeColecao(pesquisa);
			
			logradouro = objetoLogradouro.toString();
		}
		
		String parametroServicos = "TODOS";
		if (filtroOS.getTipoServicos() != null && filtroOS.getTipoServicos().length != 0) {
			Collection<Integer> tiposServicos = new ArrayList<Integer>();
			for (Integer servico : filtroOS.getTipoServicos()) {
				tiposServicos.add(servico);
			}
			
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
			Collection<ServicoTipo> pesquisa = fachada.pesquisar(tiposServicos, filtroServicoTipo, ServicoTipo.class.getName());
			
			for (ServicoTipo servicoTipo : pesquisa) {
				parametroServicos += servicoTipo.getDescricao() + "; ";
			} 
		}
		
		String motivoEncerramento = "Motivos de Encerramento: TODOS ";
		if (filtroOS.getColecaoAtendimentoMotivoEncerramento() != null && filtroOS.getColecaoAtendimentoMotivoEncerramento().size() > 0) {
			motivoEncerramento = "Motivos de Encerramento: ";
			
			FiltroAtendimentoMotivoEncerramento filtro = new FiltroAtendimentoMotivoEncerramento();
			filtro.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
			Collection<AtendimentoMotivoEncerramento> pesquisa = fachada.pesquisar(filtroOS.getColecaoAtendimentoMotivoEncerramento(), filtro, AtendimentoMotivoEncerramento.class.getName());
			
			for (AtendimentoMotivoEncerramento motivo : pesquisa) {
				motivoEncerramento += motivo.getDescricao() + "; ";
			}
		}
		
		parametros.put("numeroOS", numeroOS);
		parametros.put("numeroRA", numeroRA);
		parametros.put("documentoCobranca", documentoCobranca);
		parametros.put("situacao", situacao);
		parametros.put("indicadorTipoServico", indicadorTipoServico);
		parametros.put("matriculaImovel", matriculaImovel);
		parametros.put("codigoCliente", codigoCliente);
		parametros.put("unidadeGeracao", unidadeGeracao);
		parametros.put("unidadeAtual", unidadeAtual);
		parametros.put("unidadeSuperior", unidadeSuperior);
		parametros.put("motivoEncerramento", motivoEncerramento);
		parametros.put("dataAtendimento", dataAtendimento);
		parametros.put("dataGeracao", dataGeracao);
		parametros.put("dataEncerramento", dataEncerramento);
		parametros.put("dataProgramacao", dataProgramacao);
		parametros.put("municipio", municipio);
		parametros.put("bairro", bairro);
		parametros.put("logradouro", logradouro);
		parametros.put("servicos", parametroServicos);
		
		//trecho de código para colocar os Perfis de Imovel que foram selecionados no cabeçalho.
		if(filtroOS.getColecaoPerfilImovel() != null && !filtroOS.getColecaoPerfilImovel().isEmpty()){
			
			Collection<Integer> colecaoPerfilImovel =(Collection<Integer>)filtroOS.getColecaoPerfilImovel();			
			ImovelPerfil imovelPerfil = new ImovelPerfil();
			
			String filtroImovelPerfil = "";
			
			for(Integer codImovelPerfil : colecaoPerfilImovel){
				if(codImovelPerfil != null){
					
					imovelPerfil = fachada.pesquisarImovelPerfil(codImovelPerfil);
					if(imovelPerfil != null){
						filtroImovelPerfil += imovelPerfil.getDescricao() + ", ";						
					}					
				}				
			}
			
			if(filtroImovelPerfil != null && !filtroImovelPerfil.equals("")){
				filtroImovelPerfil = Util.removerUltimosCaracteres(filtroImovelPerfil, 2);
				parametros.put("filtroPerfilImovel", filtroImovelPerfil);
			}
		}
		
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		
		Collection<OrdemServico> pesquisa = fachada.pesquisarOrdemServico(filtroOS);
		if (pesquisa == null || pesquisa.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		Collection<Integer> idsUnidadesAtuais = new ArrayList<Integer>();
		for (OrdemServico ordem : pesquisa) {
			if (ordem.getUnidadeAtual() != null) idsUnidadesAtuais.add(ordem.getUnidadeAtual().getId());
		}
		
		Map<Integer, String> descricaoUnidade = new HashMap<Integer, String>();
		
		if (idsUnidadesAtuais.size() > 0) {
			FiltroUnidadeOrganizacional filtroUnidadesAtuais = new FiltroUnidadeOrganizacional();
			Collection<UnidadeOrganizacional> unidadesAtuais = fachada.pesquisar(
					idsUnidadesAtuais, filtroUnidadesAtuais, UnidadeOrganizacional.class.getName());
			
			for (UnidadeOrganizacional unidAtual : unidadesAtuais) {
				descricaoUnidade.put(unidAtual.getId(), unidAtual.getDescricao());
			} 
		}
		
		List<RelatorioFiltrarOrdemServicoBean> beans = new ArrayList<RelatorioFiltrarOrdemServicoBean>();
		for (OrdemServico ordem : pesquisa) {
			RelatorioFiltrarOrdemServicoBean bean = new RelatorioFiltrarOrdemServicoBean();
			bean.setNumeroOS(ordem.getId());
			if (ordem.getRegistroAtendimento() != null) {
				bean.setNumeroRA(ordem.getRegistroAtendimento().getId());
			}
			bean.setTipoServico(ordem.getServicoTipo().getDescricao());
			if (ordem.getRegistroAtendimento() != null) {
				bean.setMatriculaImovel(ordem.getRegistroAtendimento().getImovel().getId());
			} else if (ordem.getCobrancaDocumento() != null) {
				bean.setMatriculaImovel(ordem.getCobrancaDocumento().getImovel().getId());
			}
			bean.setSituacao(fachada.obterDescricaoSituacaoOS(ordem.getId()).getDescricaoSituacao());
			bean.setDataGeracao(ordem.getDataGeracao());
			bean.setDataEmissao(ordem.getDataEmissao());
			
			if (descricaoUnidade.containsKey(ordem.getUnidadeAtual().getId())) {
				bean.setUnidadeAtual(descricaoUnidade.get(ordem.getUnidadeAtual().getId()));
			}
			
			if(ordem.getImovel() != null && ordem.getImovel().getImovelPerfil() != null){
				bean.setImovelPerfil(ordem.getImovel().getImovelPerfil().getDescricao());
			}
			
			beans.add(bean);
		}
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_FILTRAR_ORDEM_SERVICO,
				parametros, new RelatorioDataSource(beans), tipoRelatorio);
		
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioFiltrarOrdemServico", this);
	}

}
