package gcom.relatorio.atendimentopublico;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRegistroAtendimentoHelper;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
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
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouro extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouro(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FILTRAR_REGISTRO_ATENDIMENTO_COORDENADAS_SEM_LOGRADOURO);
	}

	@Override
	public Object executar() throws TarefaException {
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		 
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		FiltrarRegistroAtendimentoHelper filtroRA = (FiltrarRegistroAtendimentoHelper) getParametro("filtroRA");
		filtroRA.setNumeroPagina(-1);
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		
		// Parametros
		String numeroDoRA = "";
		if (filtroRA.getRegistroAtendimento().getId() != null) {
			numeroDoRA = String.valueOf(filtroRA.getRegistroAtendimento().getId());
		}
		
		String numeroManual = "";
		if (filtroRA.getRegistroAtendimento().getManual() != null) {
			numeroManual = String.valueOf(filtroRA.getRegistroAtendimento().getManual());
		}
		
		String quantidadeRAReiteradas = "";
		if (filtroRA.getQuantidadeRAReiteradasIncial() != null && filtroRA.getQuantidadeRAReiteradasFinal() != null) {
			quantidadeRAReiteradas = filtroRA.getQuantidadeRAReiteradasIncial() + " à " + filtroRA.getQuantidadeRAReiteradasFinal();
		}
		
		String matriculaImovel = "";
		if (filtroRA.getRegistroAtendimento().getImovel() != null) {
			matriculaImovel = String.valueOf(filtroRA.getRegistroAtendimento().getImovel().getId());
		}
		
		Short codigoSituacao = filtroRA.getRegistroAtendimento().getCodigoSituacao();
		String situacao = "TODOS";
		if (codigoSituacao != null) {
			if (codigoSituacao == 1) situacao = "PENDENTES";
			if (codigoSituacao == 2) situacao = "ENCERRADOS";
			if (codigoSituacao == 3) situacao = "SEM LOCAL DE OCORRÊNCIA";
		}
		
		String loginUsuario = "";
		if (filtroRA.getRegistroAtendimentoUnidade() != null && filtroRA.getRegistroAtendimentoUnidade().getUsuario() != null) {
			loginUsuario = filtroRA.getRegistroAtendimentoUnidade().getUsuario().getLogin();
		}
		
		String dataAtendimento = "";
		if (filtroRA.getDataAtendimentoInicial() != null && filtroRA.getDataAtendimentoFinal() != null) {
			dataAtendimento = f.format(filtroRA.getDataAtendimentoInicial()) + " à " + f.format(filtroRA.getDataAtendimentoFinal());
		}
		
		String dataEncerramento = "";
		if (filtroRA.getDataEncerramentoInicial() != null && filtroRA.getDataEncerramentoFinal() != null) {
			dataEncerramento = f.format(filtroRA.getDataEncerramentoInicial()) + " à " + f.format(filtroRA.getDataEncerramentoFinal());
		}
		
		String dataTramitacao = "";
		if (filtroRA.getDataTramitacaoInicial() != null && filtroRA.getDataTramitacaoFinal() != null) {
			dataTramitacao = f.format(filtroRA.getDataTramitacaoInicial()) + " à " + f.format(filtroRA.getDataTramitacaoFinal());
		}
			
		String unidadeAtendimento = "";
		if (filtroRA.getUnidadeAtendimento() != null) {
			FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, filtroRA.getUnidadeAtendimento().getId()));
			
			Collection pesquisa = fachada.pesquisar(filtro, UnidadeOrganizacional.class.getName());
			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(pesquisa);
			
			unidadeAtendimento = unidade.getDescricao();
		}
		
		String unidadeAtual = "";
		if (filtroRA.getUnidadeAtual() != null) {
			FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, filtroRA.getUnidadeAtual().getId()));
			
			Collection pesquisa = fachada.pesquisar(filtro, UnidadeOrganizacional.class.getName());
			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(pesquisa);
			
			unidadeAtual = unidade.getDescricao();
		}
		
		String unidadeSuperior = "";
		if (filtroRA.getUnidadeSuperior() != null) {
			FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, filtroRA.getUnidadeSuperior().getId()));
			
			Collection pesquisa = fachada.pesquisar(filtro, UnidadeOrganizacional.class.getName());
			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(pesquisa);
			
			unidadeSuperior = unidade.getDescricao();
		}
		
		String municipio = "";
		if (filtroRA.getMunicipioId() != null) {
			FiltroMunicipio filtro = new FiltroMunicipio();
			filtro.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, filtroRA.getMunicipioId()));
			
			Collection pesquisa = fachada.pesquisar(filtro, Municipio.class.getName());
			Municipio objetoMunicipio = (Municipio) Util.retonarObjetoDeColecao(pesquisa);
			
			municipio = String.valueOf(objetoMunicipio.getNome());
		}
		
		String bairro = "";
		if (filtroRA.getBairroId() != null) {
			FiltroBairro filtro = new FiltroBairro();
			filtro.adicionarParametro(new ParametroSimples(FiltroBairro.ID, filtroRA.getBairroId()));
			
			Collection pesquisa = fachada.pesquisar(filtro, Bairro.class.getName());
			Bairro objetoBairro = (Bairro) Util.retonarObjetoDeColecao(pesquisa);
			
			bairro = String.valueOf(objetoBairro.getNome());
		}
		
		String logradouro = "";
		if (filtroRA.getLogradouroId() != null) {
			FiltroLogradouro filtro = new FiltroLogradouro();
			filtro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, filtroRA.getLogradouroId()));
			
			Collection pesquisa = fachada.pesquisar(filtro, Logradouro.class.getName());
			Logradouro objetoLogradouro = (Logradouro) Util.retonarObjetoDeColecao(pesquisa);
			
			logradouro = String.valueOf(objetoLogradouro.getNome());
		}
		
		String especificacoes = "Solicitações: TODAS ";
		
		if (filtroRA.getColecaoTipoSolicitacaoEspecificacao() != null && !filtroRA.getColecaoTipoSolicitacaoEspecificacao().isEmpty()) {
				especificacoes = "Especificações: ";
				
				FiltroSolicitacaoTipoEspecificacao filtro = new FiltroSolicitacaoTipoEspecificacao();
				filtro.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
				Collection<SolicitacaoTipoEspecificacao> pesquisa = fachada.pesquisar(filtroRA.getColecaoTipoSolicitacaoEspecificacao(), filtro, SolicitacaoTipoEspecificacao.class.getName());
				
				for (SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao : pesquisa) {
					especificacoes += solicitacaoTipoEspecificacao.getDescricao() + "; ";
				}
		}
		else if (filtroRA.getColecaoTipoSolicitacao() != null && !filtroRA.getColecaoTipoSolicitacao().isEmpty()) {
			especificacoes = "Solicitações: ";
			
			FiltroSolicitacaoTipo filtro = new FiltroSolicitacaoTipo();
			filtro.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);
			Collection<SolicitacaoTipo> pesquisa = fachada.pesquisar(filtroRA.getColecaoTipoSolicitacao(), filtro, SolicitacaoTipo.class.getName());
			
			for (SolicitacaoTipo solicitacaoTipo : pesquisa) {
				especificacoes += solicitacaoTipo.getDescricao() + "; ";
			}
		}
		
		String motivoEncerramento = "Motivos de Encerramento: TODOS ";
		if (filtroRA.getColecaoAtendimentoMotivoEncerramento() != null && filtroRA.getColecaoAtendimentoMotivoEncerramento().size() > 0) {
			motivoEncerramento = "Motivos de Encerramento: ";
			
			FiltroAtendimentoMotivoEncerramento filtro = new FiltroAtendimentoMotivoEncerramento();
			filtro.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
			Collection<AtendimentoMotivoEncerramento> pesquisa = fachada.pesquisar(filtroRA.getColecaoAtendimentoMotivoEncerramento(), filtro, AtendimentoMotivoEncerramento.class.getName());
			
			for (AtendimentoMotivoEncerramento motivo : pesquisa) {
				motivoEncerramento += motivo.getDescricao() + "; ";
			}
		}
		
		parametros.put("numeroDoRA", numeroDoRA);
		parametros.put("numeroManual", numeroManual);
		parametros.put("quantidadeRAReiteradas", quantidadeRAReiteradas); 
		parametros.put("matriculaImovel", matriculaImovel);
		parametros.put("loginUsuario", loginUsuario);
		parametros.put("situacao", situacao);
		parametros.put("dataAtendimento", dataAtendimento);
		parametros.put("dataEncerramento", dataEncerramento);
		parametros.put("dataTramitacao", dataTramitacao);
		parametros.put("unidadeAtendimento", unidadeAtendimento);
		parametros.put("unidadeAtual", unidadeAtual);
		parametros.put("unidadeSuperior", unidadeSuperior);
		parametros.put("municipio", municipio);
		parametros.put("bairro", bairro);
		parametros.put("logradouro", logradouro); 
		parametros.put("especificacoes", especificacoes); 
		parametros.put("motivoEncerramento", motivoEncerramento);
		
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		
		Collection<RegistroAtendimento> pesquisa = fachada.filtrarRegistroAtendimento(filtroRA);
		if (pesquisa == null || pesquisa.isEmpty()) {
			throw new ActionServletException("atencao.dados.inexistente.parametros.informados");
		}
		
		Collection<Integer> idsUnidadesAtuais = new ArrayList<Integer>();
		for (RegistroAtendimento registro : pesquisa) {
			if (registro.getUnidadeAtual() != null){
				idsUnidadesAtuais.add(registro.getUnidadeAtual().getId());
			}
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
		
		List<RelatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouroBean> beans = new ArrayList<RelatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouroBean>();
		for (RegistroAtendimento registro : pesquisa) {
			String endereco = "";
			
//			try {
//				if (registro.getImovel() != null){
//					if(registro.getImovel().getId() != null)
//						endereco = fachada.pesquisarEnderecoFormatado(registro.getImovel().getId());
//				} else if (registro.getLogradouroBairro()!= null) {
//					Integer idLogradouroBairro = null;
//					Integer idLogradouroCep = null;
//					idLogradouroBairro = registro.getLogradouroBairro()
//								.getId();
//					idLogradouroCep = registro.getLogradouroCep().getId();
//			
			 endereco = fachada.obterEnderecoOcorrenciaRA(registro.getId());
		
//				}			
				
//			} catch (ControladorException e) {
//				e.printStackTrace();
//			}
			
			
			RelatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouroBean bean = new RelatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouroBean();
			bean.setNumeroRA(registro.getId());
			bean.setEspecificacao(registro.getSolicitacaoTipoEspecificacao().getDescricao());
			bean.setDataAtendimento(registro.getRegistroAtendimento());
			bean.setDataEncerramento(registro.getDataEncerramento());
			bean.setSituacao(registro.getDescricaoSituacao());
			bean.setEndereco(endereco);
			bean.setCoordenadaNorte(registro.getNnCoordenadaNorte());
			bean.setCoordenadaLeste(registro.getNnCoordenadaLeste());

			if (descricaoUnidade.containsKey(registro.getUnidadeAtual().getId())) {
				bean.setUnidadeAtual(descricaoUnidade.get(registro.getUnidadeAtual().getId()));
			}
			
			beans.add(bean);
		}
		
		byte[] retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_FILTRAR_REGISTRO_ATENDIMENTO_COORDENADAS_SEM_LOGRADOURO,
				parametros, new RelatorioDataSource(beans), tipoRelatorio);
		
		return retorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		return 1;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioFiltrarRegistroAtendimentoCoordenadasSemLogradouro", this);
	}

}
