package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.ContaBraile;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.conta.Conta;
import gcom.gui.atendimentopublico.registroatendimento.InserirRegistroAtendimentoActionForm;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpSession;

public class RABuilder {
	
	@SuppressWarnings("rawtypes")
	public static RADadosGeraisHelper buildRADadosGeraisHelper(InserirRegistroAtendimentoActionForm form, Usuario usuario, Integer idRAJAGerado, 
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
	
	public static RADadosGeraisHelper buildRADadosGeraisHelper(Short indicadorAtendimentoOnline, Integer idMeioSolicitacao, 
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
	public static RADadosGeraisHelper buildRADadosGeraisHelper(RegistroAtendimento registroAtendimento, Usuario usuario, 
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
	
	public static RADadosGeraisHelper buildRADadosGeraisHelper(Short indicadorAtendimentoOnline, Date dataAtual, Integer meioSolicitacao, 
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
	
	public static RASolicitanteHelper buildRASolicitante(InserirRegistroAtendimentoActionForm form, HttpSession sessao, Boolean habilitarCampoSatisfacaoEmail,
															boolean novoSolicitante){
		RASolicitanteHelper raSolicitante = new RASolicitanteHelper();
		
		raSolicitante.novoSolicitante(false)
					.idClente(Util.converterStringParaInteger(form.getIdCliente()))
					.pontoReferenciaSolicitante(form.getPontoReferenciaSolicitante())
					.nomeSolicitante(form.getNomeSolicitante())
					.idUnidadeSolicitante(Util.converterStringParaInteger(form.getIdUnidadeSolicitante()))
					.colecaoFone(getColecaoFone(form, sessao))
					.colecaoEnderecoSolicitante(getColecaoEnderecoSolicitante(form, sessao))
					.idServicoTipo(getIdServicoTipo(sessao))
					.habilitarCampoSatisfacaoEmail(habilitarCampoSatisfacaoEmail.toString())
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
	
}
