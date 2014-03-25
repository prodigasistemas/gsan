package gcom.atendimentopublico.registroatendimento;

import gcom.atualizacaocadastral.ICliente;
import gcom.atualizacaocadastral.IClienteImovel;
import gcom.cadastro.ContaBraile;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.imovel.IImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.faturamento.conta.Conta;
import gcom.gui.atendimentopublico.registroatendimento.InserirRegistroAtendimentoActionForm;
import gcom.gui.portal.InserirSolicitacaoServicosPortalActionForm;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class RABuilder {
	
	public static Integer UNIDADE_ATENDIMENTO_UNAM = new Integer(17);
	
	public static Integer SOLICITACAO_TIPO_MANUTENCAO_CADASTRAL = new Integer(25);
	public static Integer SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZACAO_CADASTRAL = new Integer(13);
	
	public static Integer SERVICO_TIPO_LOCALIZAR_IMOVEL = new Integer(51);
	
	public static Integer USUARIO_ADMIN = new Integer(1); 
	
	
	public static RADadosGeraisHelper buildRADadosGeraisAtualizacaoCadastral(IImovel imovelRetorno, Integer alteracaoTipo, String protocoloAtendimento) {
		RADadosGeraisHelper raDadosGerais = new RADadosGeraisHelper();
		
		Date dataAtual = new Date();
		
		raDadosGerais.indicadorAtendimentoOnline(new Short("1"))
					.dataAtendimento(Util.formatarData(dataAtual))
					.dataPrevista(Util.formatarData(dataAtual))
					.horaAtendimento(Util.formatarHoraSemSegundos(dataAtual))
					.idUnidadeAtendimento(UNIDADE_ATENDIMENTO_UNAM)
					.idMeioSolicitacao(MeioSolicitacao.INTERNO)
					.idSolicitacaoTipo(SOLICITACAO_TIPO_MANUTENCAO_CADASTRAL)
					.idSolicitacaoTipoEspecificacao(SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZACAO_CADASTRAL)
					.observacao(getObservacaoImovelAntigo(imovelRetorno, alteracaoTipo))
					.indicadorRaAtualizacaoCadastral(true)
					.idUsuarioLogado(USUARIO_ADMIN)
					.protocoloAtendimento(protocoloAtendimento);
		
		return raDadosGerais;
	}
	
	public static RADadosGeraisHelper buildRADadosGeraisAtualizacaoCadastral(IImovel imovelRetorno, ICliente clienteRetorno, 
											IClienteImovel clienteImovelRetorno, Integer alteracaoTipo, String protocoloAtendimento) {
		RADadosGeraisHelper raDadosGerais = new RADadosGeraisHelper();
		
		Date dataAtual = new Date();
		
		raDadosGerais.indicadorAtendimentoOnline(new Short("1"))
					.dataAtendimento(Util.formatarData(dataAtual))
					.dataPrevista(Util.formatarData(dataAtual))
					.horaAtendimento(Util.formatarHoraSemSegundos(dataAtual))
					.idUnidadeAtendimento(UNIDADE_ATENDIMENTO_UNAM)
					.idMeioSolicitacao(MeioSolicitacao.INTERNO)
					.idSolicitacaoTipo(SOLICITACAO_TIPO_MANUTENCAO_CADASTRAL)
					.idSolicitacaoTipoEspecificacao(SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZACAO_CADASTRAL)
					.observacao(getObservacaoCliente(clienteRetorno, imovelRetorno, clienteImovelRetorno, alteracaoTipo))
					.indicadorRaAtualizacaoCadastral(true)
					.idUsuarioLogado(USUARIO_ADMIN)
					.protocoloAtendimento(protocoloAtendimento);
		
		return raDadosGerais;
	}
	
	public static RADadosGeraisHelper buildRADadosGeraisAtualizacaoCadastralInclusaoImovel(IImovel imovelRetorno, 
										HashMap<ClienteRelacaoTipo, ICliente> mapClientesImovel, Integer alteracaoTipo,
										String protocoloAtendimento) {
		RADadosGeraisHelper raDadosGerais = new RADadosGeraisHelper();

		Date dataAtual = new Date();

		raDadosGerais
				.indicadorAtendimentoOnline(new Short("1"))
				.dataAtendimento(Util.formatarData(dataAtual))
				.dataPrevista(Util.formatarData(dataAtual))
				.horaAtendimento(Util.formatarHoraSemSegundos(dataAtual))
				.idUnidadeAtendimento(UNIDADE_ATENDIMENTO_UNAM)
				.idMeioSolicitacao(MeioSolicitacao.INTERNO)
				.idSolicitacaoTipo(SOLICITACAO_TIPO_MANUTENCAO_CADASTRAL)
				.idSolicitacaoTipoEspecificacao(SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZACAO_CADASTRAL)
				.observacao(getObservacaoImovelNovo(imovelRetorno, mapClientesImovel, alteracaoTipo))
				.indicadorRaAtualizacaoCadastral(true)
				.idUsuarioLogado(USUARIO_ADMIN)
				.protocoloAtendimento(protocoloAtendimento);

		return raDadosGerais;
	}
	
	@SuppressWarnings("rawtypes")
	public static RADadosGeraisHelper buildRADadosGerais(InserirRegistroAtendimentoActionForm form, Usuario usuario, Integer idRAJAGerado, 
																String protocoloAtendimento, Collection colecaoRegistroAtendimentoAnexo) {
		RADadosGeraisHelper raDadosGerais = new RADadosGeraisHelper();
		
		raDadosGerais.indicadorAtendimentoOnline(Short.parseShort(form.getTipo()))
					.dataAtendimento(form.getDataAtendimento())
					.horaAtendimento(form.getHora())
					.tempoEsperaInicial(form.getTempoEsperaInicial())
					.tempoEsperaFinal(form.getTempoEsperaFinal())
					.idMeioSolicitacao(Util.converterStringParaInteger(form.getMeioSolicitacao()))
					.idSolicitacaoTipoEspecificacao(Util.converterStringParaInteger(form.getEspecificacao()))
					.dataPrevista(form.getDataPrevista())
					.observacao(form.getObservacao())
					.idSolicitacaoTipo(Util.converterStringParaInteger(form.getTipoSolicitacao()))
					.idUnidadeAtendimento(Util.converterStringParaInteger(form.getUnidade()))
					.idUsuarioLogado(usuario.getId())
					.idFuncionario(Util.converterStringParaInteger(form.getIdFuncionarioResponsavel()))
					.numeroRAManual(form.getNumeroAtendimentoManual())
					.idRAJAGerado(idRAJAGerado)
					.protocoloAtendimento(protocoloAtendimento)
					.colecaoRegistroAtendimentoAnexo(colecaoRegistroAtendimentoAnexo);
	
		return raDadosGerais;
	}
	
	public static RADadosGeraisHelper buildRADadosGerais(Short indicadorAtendimentoOnline, Integer idMeioSolicitacao, 
																Integer idSolicitacaoTipoEspecificacao, String dataPrevista, 
																String observacao, Integer idSolicitacaoTipo,
																Integer idUnidadeAtendimento, Usuario usuarioLogado,
																String protocoloAtendimento) {
		RADadosGeraisHelper raDadosGerais = new RADadosGeraisHelper();

		Date dataAtual = new Date();
		
		raDadosGerais.indicadorAtendimentoOnline(indicadorAtendimentoOnline)
					.dataAtendimento(Util.formatarData(dataAtual))
					.horaAtendimento(Util.formatarHoraSemData(dataAtual))
					.idMeioSolicitacao(idMeioSolicitacao)
					.idSolicitacaoTipoEspecificacao(idSolicitacaoTipoEspecificacao)
					.dataPrevista(dataPrevista)
					.observacao(observacao)
					.idSolicitacaoTipo(idSolicitacaoTipo)
					.idUnidadeAtendimento(idUnidadeAtendimento)
					.idUsuarioLogado(usuarioLogado.getId())
					.protocoloAtendimento(protocoloAtendimento);
		
		return raDadosGerais;

	}
	
	@SuppressWarnings("rawtypes")
	public static RADadosGeraisHelper buildRADadosGerais(RegistroAtendimento registroAtendimento, Usuario usuario, 
																Integer idUnidadeAtendimento, Collection colecaoRegistroAtendimentoAnexo){
		RADadosGeraisHelper raDadosGerais = new RADadosGeraisHelper();
		
		raDadosGerais.idSolicitacaoTipoEspecificacao(registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipoEspecificacaoNovoRA().getId())
					.idSolicitacaoTipo(registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipoEspecificacaoNovoRA().getId())
					.indicadorAtendimentoOnline(registroAtendimento.getIndicadorAtendimentoOnline())
					.dataAtendimento(Util.formatarData(new Date()))
					.horaAtendimento(Util.formatarHoraSemData(new Date()))
					.idMeioSolicitacao(registroAtendimento.getMeioSolicitacao().getId())
					.idUnidadeAtendimento(idUnidadeAtendimento)
					.idUsuarioLogado(usuario.getId())
					.colecaoRegistroAtendimentoAnexo(colecaoRegistroAtendimentoAnexo);
		
		return raDadosGerais;
	}
	
	public static RADadosGeraisHelper buildRADadosGerais(Short indicadorAtendimentoOnline, Date dataAtual, Integer meioSolicitacao, 
																Integer idSolicitacaoTipoEspecificacao, String dataPrevista, Integer idSolicitacaoTipo,
																Usuario usuarioLogado){
		RADadosGeraisHelper raDadosGerais = new RADadosGeraisHelper();
		
		raDadosGerais.indicadorAtendimentoOnline(indicadorAtendimentoOnline.shortValue())
					.dataAtendimento(Util.formatarData(dataAtual))
					.horaAtendimento(Util.formatarHoraSemSegundos(dataAtual))
					.idMeioSolicitacao(meioSolicitacao)
					.idSolicitacaoTipoEspecificacao(idSolicitacaoTipoEspecificacao)
					.dataPrevista(dataPrevista)
					.idSolicitacaoTipo(idSolicitacaoTipo)
					.idUnidadeAtendimento(usuarioLogado.getUnidadeOrganizacional().getId())
					.idUsuarioLogado(usuarioLogado.getId());
		
		return raDadosGerais;
	}
	
	public static RADadosGeraisHelper buildRADadosGerais(Short indicadorAtendimentoOnline, Integer idMeioSolicitacao, Integer idSolicitacaoTipoEspecificacao,
															Date dataPrevista, Integer idSolicitacaoTipo, Integer idUnidadeAtendimento, 
															Integer idUsuarioLogado, String observacao) {

		RADadosGeraisHelper raDadosGerais = new RADadosGeraisHelper();
		
		Date dataAtual = new Date();

		raDadosGerais.indicadorAtendimentoOnline(indicadorAtendimentoOnline)
					.dataAtendimento(Util.formatarData(dataAtual))
					.horaAtendimento(Util.formatarHoraSemData(dataAtual))
					.idMeioSolicitacao(idMeioSolicitacao)
					.idSolicitacaoTipoEspecificacao(idSolicitacaoTipoEspecificacao)
					.dataPrevista(Util.formatarData(dataPrevista))
					.idSolicitacaoTipo(idSolicitacaoTipo)
					.idUnidadeAtendimento(idUnidadeAtendimento)
					.idUsuarioLogado(idUsuarioLogado)
					.observacao(observacao);
		
		return raDadosGerais;
					
	}
	
	public static RADadosGeraisHelper buildRADadosGerais(InserirSolicitacaoServicosPortalActionForm form, Short indicadorAtendimentoOnline, 
														Integer idMeioSolicitacao, Date dataPrevista, String observacao, Integer idUnidadeAtendimento, 
														Integer idUsuarioLogado, String protocoloAtendimento, String observacaoOS) {
		
		RADadosGeraisHelper raDadosGerais = new RADadosGeraisHelper();
		
		Date dataAtual = new Date();
		
		raDadosGerais.indicadorAtendimentoOnline(indicadorAtendimentoOnline)
					.dataAtendimento(Util.formatarData(dataAtual))
					.horaAtendimento(Util.formatarHoraSemData(dataAtual))
					.idMeioSolicitacao(idMeioSolicitacao)
					.idSolicitacaoTipoEspecificacao(new Integer(form.getEspecificacao()))
					.dataPrevista(Util.formatarData(dataPrevista))
					.observacao(observacao)
					.idSolicitacaoTipo(new Integer(form.getSolicitacaoTipo()))
					.idUnidadeAtendimento(idUnidadeAtendimento)
					.idUsuarioLogado(idUsuarioLogado)
					.protocoloAtendimento(protocoloAtendimento)
					.observacaoOS(observacaoOS);
		
		return raDadosGerais;
	}
	
	public static RALocalOcorrenciaHelper buildRALocalOcorrenciaAtualizacaoCadastral(IImovel imovel, Integer idSetorComercial, Integer alteracaoTipo){
		RALocalOcorrenciaHelper raLocalOcorrenciaHelper = new RALocalOcorrenciaHelper();
		
		raLocalOcorrenciaHelper.colecaoEndereco(null)
								.idLocalidade(imovel.getIdLocalidade())
								.idSetorComercial(idSetorComercial)
								.idQuadra(imovel.getNumeroQuadra())
								.idUnidadeDestino(UNIDADE_ATENDIMENTO_UNAM)
								.parecerUnidadeDestino("")
								.idImovel(imovel.getIdImovel());
								
		if(alteracaoTipo == AlteracaoTipo.INCLUSAO){
			raLocalOcorrenciaHelper.parecerUnidadeDestino("Inclusão - Origem: RECADASTRAMENTO");
		}
		
		if(alteracaoTipo == AlteracaoTipo.EXCLUSAO){
			raLocalOcorrenciaHelper.parecerUnidadeDestino("Exclusão - Origem: RECADASTRAMENTO");
		}
		
		return raLocalOcorrenciaHelper;
	}
	
	@SuppressWarnings("rawtypes")
	public static RALocalOcorrenciaHelper buildRALocalOcorrencia(InserirRegistroAtendimentoActionForm form, Collection colecaoEnderecoLocalOcorrencia,
																Short indicadorCoordenadaSemLogradouro, Collection<Conta> colecaoContas){
		RALocalOcorrenciaHelper raLocalOcorrencia = new RALocalOcorrenciaHelper();
		
		raLocalOcorrencia.idImovel(Util.converterStringParaInteger(form.getIdImovel()))
						.descricaoLocalOcorrencia(form.getDescricaoLocalOcorrencia())
						.colecaoEndereco(colecaoEnderecoLocalOcorrencia)
						.pontoReferenciaLocalOcorrencia(form.getPontoReferencia())
						.idBairroArea(Util.converterStringParaInteger(form.getIdBairroArea()))
						.idLocalidade(Util.converterStringParaInteger(form.getIdLocalidade()))
						.idSetorComercial(Util.converterStringParaInteger(form.getIdSetorComercial()))
						.idQuadra(Util.converterStringParaInteger(form.getIdQuadra()))
						.idDivisaoEsgoto(Util.converterStringParaInteger(form.getIdDivisaoEsgoto()))
						.idLocalOcorrencia(Util.converterStringParaInteger(form.getIdLocalOcorrencia()))
						.idPavimentoCalcada(Util.converterStringParaInteger(form.getIdPavimentoCalcada()))
						.idPavimentoRua(Util.converterStringParaInteger(form.getIdPavimentoRua()))
						.idUnidadeDestino(Util.converterStringParaInteger(form.getIdUnidadeDestino()))
						.parecerUnidadeDestino(form.getParecerUnidadeDestino())
						.nnCoordenadaLeste(getCoordenadaLeste(form))
						.nnCoordenadaNorte(getCoordenadaNorte(form))
						.indicadorCoordenadaSemLogradouro(indicadorCoordenadaSemLogradouro)
						.colecaoContas(colecaoContas)
						.nnDiametro(getDiametro(form));
		
		return raLocalOcorrencia;
	}
	
	@SuppressWarnings("rawtypes")
	public static RALocalOcorrenciaHelper buildRALocalOcorrencia(ContaBraile contaBraile, Collection colecaoEndereco , 
																Integer idUnidadeAtendimento, String parecer,
																Short indicadorCoordenadaSemLogradouro) {
		
		RALocalOcorrenciaHelper raLocalOcorrencia = new RALocalOcorrenciaHelper();

		raLocalOcorrencia.idImovel(contaBraile.getImovel().getId())
						.colecaoEndereco(colecaoEndereco)
						.idLocalidade(contaBraile.getImovel().getLocalidade().getId())
						.idSetorComercial(contaBraile.getImovel().getSetorComercial().getId())
						.idQuadra(contaBraile.getImovel().getQuadra().getId())
						.idPavimentoCalcada(contaBraile.getImovel().getPavimentoCalcada().getId())
						.idPavimentoRua(contaBraile.getImovel().getPavimentoRua().getId())
						.idUnidadeDestino(idUnidadeAtendimento)
						.parecerUnidadeDestino(parecer)
						.indicadorCoordenadaSemLogradouro(indicadorCoordenadaSemLogradouro);
		
		return raLocalOcorrencia;
	}
	
	@SuppressWarnings("rawtypes")
	public static RALocalOcorrenciaHelper buildRALocalOcorrencia(RegistroAtendimento registroAtendimento, Collection colecaoEnderecos, Integer idUnidadeDestino){
		RALocalOcorrenciaHelper raLocalOcorrencia = new RALocalOcorrenciaHelper();
		raLocalOcorrencia.nnDiametro(registroAtendimento.getNnDiametro())
						.idImovel(registroAtendimento.getImovel().getId())
						.colecaoEndereco(colecaoEnderecos)
						.idLocalidade(registroAtendimento.getLocalidade().getId())
						.idSetorComercial(registroAtendimento.getSetorComercial().getId())
						.idQuadra(registroAtendimento.getImovel().getQuadra().getId())
						.idPavimentoRua(registroAtendimento.getImovel().getPavimentoRua().getId())
						.idPavimentoCalcada(registroAtendimento.getImovel().getPavimentoCalcada().getId())
						.idUnidadeDestino(idUnidadeDestino);
		
		return raLocalOcorrencia;
	}
	
	@SuppressWarnings("rawtypes")
	public static RALocalOcorrenciaHelper buildRALocalOcorrencia(Imovel imovel, Collection colecaoEndereco, Integer idUnidadeDestino, 
																	String parecerUnidadeDestino, Short indicadorCoordenadaSemLogradouro){
		RALocalOcorrenciaHelper raLocalOcorrencia = new RALocalOcorrenciaHelper();
		
		raLocalOcorrencia.idImovel(imovel.getId())
						.colecaoEndereco(colecaoEndereco)
						.idLocalidade(imovel.getLocalidade().getId())
						.idSetorComercial(imovel.getSetorComercial().getId())
						.idQuadra(imovel.getQuadra().getId())
						.idPavimentoCalcada(imovel.getPavimentoCalcada().getId())
						.idPavimentoRua(imovel.getPavimentoRua().getId())
						.idUnidadeDestino(idUnidadeDestino)
						.parecerUnidadeDestino(parecerUnidadeDestino)
						.indicadorCoordenadaSemLogradouro(indicadorCoordenadaSemLogradouro.shortValue());
		
		return raLocalOcorrencia;
	}
	
	@SuppressWarnings("rawtypes")
	public static RALocalOcorrenciaHelper buildRALocalOcorrencia(Imovel imovel, Collection colecaoEndereco, Short indicadorCoordenadaSemLogradouro, 
																Integer idUnidadeDestino) {
		RALocalOcorrenciaHelper raLocalOcorrencia = new RALocalOcorrenciaHelper();
		
		raLocalOcorrencia.idImovel(imovel.getId())
						.colecaoEndereco(colecaoEndereco)
						.idLocalidade(imovel.getLocalidade().getId())
						.idSetorComercial(imovel.getSetorComercia().getId())
						.idQuadra(imovel.getQuadra().getId())
						.idPavimentoRua(imovel.getPavimentoRua().getId())
						.idPavimentoCalcada(imovel.getPavimentoCalcada().getId())
						.indicadorCoordenadaSemLogradouro(indicadorCoordenadaSemLogradouro)
						.idUnidadeDestino(idUnidadeDestino);
	
		return raLocalOcorrencia;
	}
	
	@SuppressWarnings("rawtypes")
	public static RALocalOcorrenciaHelper buildRALocalOcorrencia(Imovel imovel, Collection colecaoEndereco, Short indicadorCoordenadaSemLogradouro) {

		RALocalOcorrenciaHelper raLocalOcorrencia = new RALocalOcorrenciaHelper();
		
		raLocalOcorrencia.idImovel(imovel.getId())
						.colecaoEndereco(colecaoEndereco)
						.idLocalidade(imovel.getLocalidade().getId())
						.idSetorComercial(imovel.getSetorComercia().getId())
						.idQuadra(imovel.getQuadra().getId())
						.indicadorCoordenadaSemLogradouro(indicadorCoordenadaSemLogradouro);
	
		return raLocalOcorrencia;
	}
	
	public static RASolicitanteHelper buildRASolicitante(InserirRegistroAtendimentoActionForm form, HttpSession sessao,	boolean novoSolicitante){
		RASolicitanteHelper raSolicitante = new RASolicitanteHelper();
		
		raSolicitante.novoSolicitante(false)
					.idClente(Util.converterStringParaInteger(form.getIdCliente()))
					.pontoReferenciaSolicitante(form.getPontoReferenciaSolicitante())
					.nomeSolicitante(form.getNomeSolicitante())
					.idUnidadeSolicitante(Util.converterStringParaInteger(form.getIdUnidadeSolicitante()))
					.colecaoFone(getColecaoFone(form, sessao))
					.colecaoEnderecoSolicitante(getColecaoEnderecoSolicitante(form, sessao))
					.idServicoTipo(getIdServicoTipo(sessao))
					.habilitarCampoSatisfacaoEmail(getHabilitarCampoSatisfacaoEmail(sessao))
					.enviarEmailSatisfacao(form.getEnviarEmailSatisfacao())
					.enderecoEmail(form.getEnderecoEmail());
		
		return raSolicitante;
	}
	
	@SuppressWarnings("rawtypes")
	public static RASolicitanteHelper buildRASolicitante(String nomeSolicitante, boolean isNovoSolicitante, Collection colecaoEnderecoSolicitante) {
		
		RASolicitanteHelper raSolicitante = new RASolicitanteHelper();
		
		raSolicitante.nomeSolicitante(nomeSolicitante)
					.novoSolicitante(isNovoSolicitante)
					.colecaoEnderecoSolicitante(colecaoEnderecoSolicitante);
		
		return raSolicitante;
	}
	
	public static RASolicitanteHelper buildRASolicitante(RegistroAtendimento registroAtendimento, boolean novoSolicitante, Integer idUnidadeSolicitante){
		RASolicitanteHelper raSolicitante = new RASolicitanteHelper();
		
		Integer idServicoTipo = null;
		if(registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipoEspecificacaoNovoRA().getServicoTipo().getId() != null){
			idServicoTipo = registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipoEspecificacaoNovoRA().getServicoTipo().getId();
		}
		
		raSolicitante.idServicoTipo(idServicoTipo)
						.novoSolicitante(novoSolicitante) 
						.idUnidadeSolicitante(idUnidadeSolicitante);
		
		return raSolicitante;
	}
	
	public static RASolicitanteHelper buildRASolicitante(boolean novoSolicitante, Integer idClienteUsuario){
		RASolicitanteHelper raSolicitante = new RASolicitanteHelper();
		
		raSolicitante.novoSolicitante(novoSolicitante)
					.idClente(idClienteUsuario);
		
		return raSolicitante;
	}
	
	public static RASolicitanteHelper buildRASolicitante(boolean novoSolicitante, UnidadeOrganizacional unidadeSolicitante) {
		
		RASolicitanteHelper raSolicitante = new RASolicitanteHelper();
		
		raSolicitante.novoSolicitante(novoSolicitante)
					.idUnidadeSolicitante(unidadeSolicitante.getId());
		
		return raSolicitante;
	
	}
	
	public static RASolicitanteHelper buildRASolicitante(InserirSolicitacaoServicosPortalActionForm form, Integer idCliente, String pontoReferenciaSolicitante, 
														boolean novoSolicitante, Collection<ClienteFone> colecaoFone) {
		
		RASolicitanteHelper raSolicitante = new RASolicitanteHelper();
		
		raSolicitante.idClente(idCliente)
					.pontoReferenciaSolicitante(pontoReferenciaSolicitante)
					.nomeSolicitante(form.getNomeSolicitante())
					.novoSolicitante(novoSolicitante)
					.colecaoFone(colecaoFone)
					.enderecoEmail(form.getEmail());
		
		   return raSolicitante;
	}
	
	public static RASolicitanteHelper buildRASolicitanteAtualizacaoCadastral(){
		return new RASolicitanteHelper().nomeSolicitante("COSANPA - Recadastramento").idServicoTipo(SERVICO_TIPO_LOCALIZAR_IMOVEL);
	}
	
	@SuppressWarnings("rawtypes")
	public static Collection getColecaoEnderecoSolicitante(InserirRegistroAtendimentoActionForm form, HttpSession sessao) {
		Collection colecaoEnderecoSolicitante = null;
		
		if (sessao.getAttribute("colecaoEnderecosAbaSolicitante") != null){
			colecaoEnderecoSolicitante = (Collection) sessao.getAttribute("colecaoEnderecosAbaSolicitante");
		}
		
		return colecaoEnderecoSolicitante;
	}
	
	@SuppressWarnings("rawtypes")
	public static Collection getColecaoFone(InserirRegistroAtendimentoActionForm form, HttpSession sessao) {
		Collection colecaoFone = null;

		if (sessao.getAttribute("colecaoFonesAbaSolicitante") != null){
			colecaoFone = (Collection) sessao.getAttribute("colecaoFonesAbaSolicitante");
		}
		
		return colecaoFone;
	}
	
	private static BigDecimal getCoordenadaLeste(InserirRegistroAtendimentoActionForm form){
		BigDecimal coordenadaLeste = null;
		
		if(form.getNnCoordenadaLeste()!=null && !form.getNnCoordenadaLeste().equalsIgnoreCase("")){
			coordenadaLeste = new BigDecimal(form.getNnCoordenadaLeste().replace(",","."));			
		}
		
		return coordenadaLeste;
	}
	
	private static BigDecimal getCoordenadaNorte(InserirRegistroAtendimentoActionForm form){
		BigDecimal coordenadaNorte = null;
		
		if(form.getNnCoordenadaNorte()!=null && !form.getNnCoordenadaNorte().equalsIgnoreCase("")){
			coordenadaNorte = new BigDecimal (form.getNnCoordenadaNorte().replace(",","."));
		}

		return coordenadaNorte;
	}
	
	private static BigDecimal getDiametro(InserirRegistroAtendimentoActionForm form){
		BigDecimal diametro = null;
		
		if(form.getNnDiamentro()!=null && !form.getNnDiamentro().equals("")){
			diametro = new BigDecimal(form.getNnDiamentro());
		}
		
		return diametro;
	}
	
	private static Integer getIdServicoTipo(HttpSession sessao){
		Integer idServicoTipo = null;
		
		if (sessao.getAttribute("servicoTipo") != null){
			idServicoTipo = (Integer) sessao.getAttribute("servicoTipo");
		}
		
		return idServicoTipo;
	}
	
	private static String getHabilitarCampoSatisfacaoEmail(HttpSession sessao){
		String habilitarCampoSatisfacaoEmail = null;
		
		if (sessao.getAttribute("habilitarCampoSatisfacaoEmail") != null){
			habilitarCampoSatisfacaoEmail = sessao.getAttribute("habilitarCampoSatisfacaoEmail").toString();
		}
		
		return habilitarCampoSatisfacaoEmail;
	}
	
	private static String getObservacaoImovelNovo(IImovel imovelRetorno,  HashMap<ClienteRelacaoTipo, ICliente> mapClienteImovel, Integer alteracaoTipo) {
		StringBuilder observacao = new StringBuilder();
		
		observacao.append("INCLUSÃO DE IMÓVEL - RECADASTRAMENTO. ");
		observacao.append(getDescricaoEnderecoImovel(imovelRetorno));
		observacao.append(getObservacaoCliente(imovelRetorno, mapClienteImovel));

		return observacao.toString();
	}
	
	private static String getObservacaoImovelAntigo(IImovel imovelRetorno, Integer alteracaoTipo) {
		StringBuilder observacao = new StringBuilder();
		
		if (alteracaoTipo.equals(AlteracaoTipo.EXCLUSAO)) {
			observacao.append("EXCLUSÃO DE IMÓVEL - RECADASTRAMENTO. ");
			observacao.append(getDescricaoEnderecoImovel(imovelRetorno));
		}
		
		return observacao.toString();
	}
	
	private static String getObservacaoCliente(ICliente clienteRetorno, IImovel imovelRetorno, IClienteImovel clienteImovelRetorno, Integer alteracaoTipo) {
		StringBuilder observacao = new StringBuilder();
		
		if (alteracaoTipo.equals(AlteracaoTipo.ALTERACAO)) {
			observacao.append("NOVA RELAÇÃO DE CLIENTE - RECADASTRAMENTO. ");
		}

		if (alteracaoTipo.equals(AlteracaoTipo.INCLUSAO)) {
			observacao.append("INCLUSÃO DE CLIENTE - RECADASTRAMENTO. ");
		}
		
		if (alteracaoTipo.equals(AlteracaoTipo.EXCLUSAO)) {
			observacao.append("FIM DA RELAÇÃO DO CLIENTE - RECADASTRAMENTO. ");
		}
		
		observacao.append(getDescricaoInformacoesCliente(clienteRetorno)); 
		observacao.append(getInformacoesClienteImovel(clienteImovelRetorno.getClienteRelacaoTipo(), imovelRetorno));
		
		return observacao.toString();
	}
	
	private static String getObservacaoCliente(IImovel imovelRetorno, HashMap<ClienteRelacaoTipo, ICliente> mapClientesImovel) {
		StringBuilder observacao = new StringBuilder();
		
		observacao.append("CLIENTES: ");
		
		for (ClienteRelacaoTipo clienteRelacaoTipo : mapClientesImovel.keySet()) {
			ICliente cliente = mapClientesImovel.get(clienteRelacaoTipo);
			observacao.append(getDescricaoInformacoesCliente(cliente)); 
			observacao.append(getInformacoesClienteImovel(clienteRelacaoTipo, imovelRetorno));
		}
		
		
		return observacao.toString();
	}
	
	private static String getDescricaoEnderecoImovel(IImovel imovelRetorno) {
		StringBuilder descricaoEndereco = new StringBuilder();
		
		descricaoEndereco.append("Endereco: ");
		descricaoEndereco.append(imovelRetorno.getDescricaoLogradouro());
		descricaoEndereco.append(", ");
		descricaoEndereco.append("n ");
		descricaoEndereco.append(imovelRetorno.getNumeroImovel());
		descricaoEndereco.append(", ");
		descricaoEndereco.append(imovelRetorno.getComplementoEndereco());
		descricaoEndereco.append(", ");
		descricaoEndereco.append(imovelRetorno.getNomeBairro());
		descricaoEndereco.append(", ");
		descricaoEndereco.append(imovelRetorno.getNomeMunicipio());
		descricaoEndereco.append(", ");
		descricaoEndereco.append("CEP: ");
		descricaoEndereco.append(imovelRetorno.getCodigoCep());
		descricaoEndereco.append(".");
		
		return descricaoEndereco.toString().replaceAll("\\s,", "");
	}
	
	private static String getDescricaoInformacoesCliente(ICliente clienteRetorno) {
		StringBuilder descricaoEndereco = new StringBuilder();
		
		descricaoEndereco.append("Nome:");
		descricaoEndereco.append(clienteRetorno.getNome());
		descricaoEndereco.append(", ");
		descricaoEndereco.append(getCpfCnpjPorTipoPessoa(clienteRetorno));
		descricaoEndereco.append(".");
		
		return descricaoEndereco.toString().replaceAll("\\s,", "");
	}
	
	private static StringBuilder getInformacoesClienteImovel(ClienteRelacaoTipo clienteRelacaoTipo, IImovel imovelRetorno) {
		StringBuilder informacoes = new StringBuilder();
		
		informacoes.append("Tipo de relação do cliente:");
		informacoes.append(clienteRelacaoTipo.getDescricao());
		informacoes.append(". ");
		
		if (imovelRetorno.getTipoOperacao() != null) {
			if (imovelRetorno.getTipoOperacao().equals(AlteracaoTipo.ALTERACAO)){
				informacoes.append("Matrícula do imóvel:");
				informacoes.append(imovelRetorno.getIdImovel());
				informacoes.append(".");
			} else if (imovelRetorno.getTipoOperacao().equals(AlteracaoTipo.INCLUSAO)) {
				informacoes.append("Imóvel Novo.");
			}
		} else if (imovelRetorno.getIdImovel() != null){
			informacoes.append("Matrícula do imóvel:");
			informacoes.append(imovelRetorno.getIdImovel());
			informacoes.append(".");
		}
		
		return informacoes;
	}
	
	private static StringBuilder getCpfCnpjPorTipoPessoa(ICliente clienteRetorno) {
		StringBuilder informacoesTipoPessoa = new StringBuilder();
		if (clienteRetorno.getClienteTipo().getId().equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
			informacoesTipoPessoa.append("CPF:");
			informacoesTipoPessoa.append(clienteRetorno.getCpf());
			informacoesTipoPessoa.append(", ");
			informacoesTipoPessoa.append("RG:");
			informacoesTipoPessoa.append(clienteRetorno.getRg());
		} else if (clienteRetorno.getClienteTipo().getId().equals(ClienteTipo.INDICADOR_PESSOA_JURIDICA)) {
			informacoesTipoPessoa.append("CNPJ:");
			informacoesTipoPessoa.append(clienteRetorno.getCnpj());
		}
		
		return informacoesTipoPessoa;
	}
	
}
