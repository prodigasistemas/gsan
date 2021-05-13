package gcom.api.ordemservico.bo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import gcom.api.ordemservico.dto.HidrometroInstalacaoDTO;
import gcom.api.ordemservico.dto.LigacaoAguaDTO;
import gcom.api.ordemservico.dto.OrdemServicoDTO;
import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.fachada.Fachada;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.FachadaException;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class RequisicaoEncerrarOrdemServico {

	private Fachada fachada = Fachada.getInstancia();
	
	private OrdemServicoDTO dto = null;
	private OrdemServico ordemServico = null;
	private Usuario usuario = null;
	private Imovel imovel = null;
	private IntegracaoComercialHelper helper = null;
	private Map<String, String> resposta;
	
	public RequisicaoEncerrarOrdemServico(OrdemServicoDTO dto) {
		super();
		this.dto = dto;
		setarDadosEncerramento();
	}

	public Map<String, String> processar() {
		Integer idOperacao = dto.getOperacao();
		
		if (idOperacao != null && !resposta.containsKey("msg")) {

			switch (idOperacao) {

			case (Operacao.OPERACAO_RELIGACAO_AGUA_EFETUAR_INT):
				executarEncerramentoReligacaoAgua();
				break;

			case (Operacao.OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR_INT):
				executarEncerramentoInstalacaoHidrometro();
				break;

			case (Operacao.OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR_INT):
				executarEncerramentoSubstituicaoHidrometro();
				break;

			case (Operacao.OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_EFETUAR_INT):
				executarEncerramento();
				break;

			case (Operacao.OPERACAO_LIGACAO_AGUA_EFETUAR_INT):
				executarEncerramentoLigacaoAgua();
				break;

			case (Operacao.OPERACAO_EFETUAR_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT):
				executarEncerramentoLigacaoAguaComInstalacaoHidrometro();
			break;
			
			case (Operacao.OPERACAO_EFETUAR_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT):
				executarEncerramentoReligacaoAguaComInstalacaoHidrometro();
				break;
			
			case (Operacao.OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT):
				executarEncerramentoRestabelecimentoLigacaoAguaComInstalacaoHidrometro();
				break;
				
			case (Operacao.OPERACAO_EFETUAR_LIGACAO_AGUA_COM_SUBSTITUICAO_HIDROMETRO):
				executarEncerramentoLigacaoAguaComSubstituicaoHidrometro();
				break;
			
			case (Operacao.OPERACAO_EFETUAR_RELIGACAO_AGUA_COM_SUBSTITUICAO_HIDROMETRO):
				executarEncerramentoReligacaoAguaComSubstituicaoHidrometro();
				break;
			
			case (Operacao.OPERACAO_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_COM_SUBSTITUICAO_HIDROMETRO):
				executarEncerramentoRestabelecimentoLigacaoAguaComSubstituicaoHidrometro();
				break;
			}
		}

		return resposta;
	}
	
	/*
	 * INÍCIO DOS MÉTODOS DE ENCERRAMENTO
	 */
	private void executarEncerramento() {
		try {
			encerrarOrdemServico();

			if (existeRegistroAtendimento()) {
				encerrarRegistroAtendimento();
			}

		} catch (FachadaException e) {
			resposta.put("msg", e.getMessage());
		}
	}
	
	private void encerrarOrdemServico() {
		try {
			fachada.encerrarOSComExecucaoSemReferencia(
					ordemServico.getId(),
					ordemServico.getDataEncerramento(),
					usuario,
					dto.getMotivoEncerramento().toString(),
					new Date(),
					dto.getParecerEncerramento(),
					"",
					"",
					null,
					helper,
					ordemServico.getServicoTipo().getId().toString(),
					null,
					null,
					"",
					null,
					null,
					ConstantesSistema.ACEITO);
			
		} catch (NumberFormatException e) {
			throw new NumberFormatException(e.getMessage());
			
		} catch (FachadaException e) {
			throw new FachadaException(e.getMessage());
		}
	}
	
	private void encerrarRegistroAtendimento() {
		RegistroAtendimento registroAtendimento = montarRegistroAtendimento();
		RegistroAtendimentoUnidade unidade = montarRegistroAtendimentoUnidade(registroAtendimento);

		ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = fachada.obterDadosRegistroAtendimento(registroAtendimento.getId());

		Integer idDebitoTipo = null;
		BigDecimal valorAtual = null;
		Integer quantidadeParcelas = null;
		String percentualCobranca = null;

		SolicitacaoTipoEspecificacao especificacao = registroAtendimentoHelper.getRegistroAtendimento().getSolicitacaoTipoEspecificacao();
		if (especificacao != null && especificacao.getDebitoTipo() != null) {
			idDebitoTipo = especificacao.getDebitoTipo().getId();
			valorAtual = ordemServico.getValorAtual();
			quantidadeParcelas = 1;
			percentualCobranca = "100";
		}

		try {
			
			fachada.encerrarRegistroAtendimento(
					registroAtendimento, 
					unidade, 
					usuario, 
					idDebitoTipo, 
					valorAtual, 
					quantidadeParcelas, 
					percentualCobranca,
					false, 
					null, 
					false);
			
		} catch (FachadaException e) {
			throw new FachadaException(e.getMessage());
		}
	}
	
	/*
	 * INÍCIO DOS MÉTODOS DE OPERAÇÕES
	 */
	private void executarEncerramentoReligacaoAgua() {
		try {
			LigacaoAgua ligacaoAgua = obterLigacaoAgua();
			ligacaoAgua.setDataReligacao(ordemServico.getDataEncerramento());
			helper.setLigacaoAgua(ligacaoAgua);

			executarEncerramento();
		} catch (FachadaException e) {
			resposta.put("msg", e.getMessage());
		}

	}
	
	private void executarEncerramentoInstalacaoHidrometro() {
		try {
			HidrometroInstalacaoHistorico instalacao = montarDadosHidrometroInstalacao();
			helper.setHidrometroInstalacaoHistorico(instalacao); 
		
			executarEncerramento();
		} catch (FachadaException e) {
			resposta.put("msg", e.getMessage());
		}
	}
	
	private void executarEncerramentoSubstituicaoHidrometro() {
		try {
			HidrometroInstalacaoHistorico instalacao = montarDadosHidrometroInstalacao();
			HidrometroInstalacaoHistorico substituicao = montarDadosHidrometroSubstituicao();

			helper.setHidrometroInstalacaoHistorico(instalacao);
			helper.setHidrometroSubstituicaoHistorico(substituicao);
			helper.setSituacaoHidrometroSubstituido(dto.getHidrometro().getSituacao().toString());

			if (hidrometroNaoExtraviado()) {
				helper.setLocalArmazenagemHidrometro(dto.getHidrometro().getLocalArmazenagem());
			}

			executarEncerramento();
		} catch (FachadaException e) {
			resposta.put("msg", e.getMessage());
		}
	}
	
	private void executarEncerramentoLigacaoAgua() {
		try {
			LigacaoAguaDTO ligacaoAguaDTO = dto.getLigacaoAgua();

			LigacaoAgua ligacaoAgua = new LigacaoAgua();
			ligacaoAgua.setImovel(imovel);
			ligacaoAgua.setDataLigacao(ordemServico.getDataEncerramento());
			ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDTO.getDiametro());
			ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaDTO.getMaterial());
			ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaDTO.getPerfil());
			ligacaoAgua.setRamalLocalInstalacao(ligacaoAguaDTO.getLocalInstalacaoRamal());
			ligacaoAgua.setProfundidadeRamal(ligacaoAguaDTO.getProfundidadeRamal());
			ligacaoAgua.setDistanciaInstalacaoRamal(ligacaoAguaDTO.getDistanciaInstalacaoRamal());
			ligacaoAgua.setPavimentoRua(ligacaoAguaDTO.getPavimentoRua());
			ligacaoAgua.setPavimentoCalcada(ligacaoAguaDTO.getPavimentoCalcada());
			ligacaoAgua.setLigacaoOrigem(ligacaoAguaDTO.getOrigem());
			ligacaoAgua.setNumeroLacre(ligacaoAguaDTO.getLacre());
			
			if (possuiHidrometroInstalado()) {				
				ligacaoAgua.setHidrometroInstalacaoHistorico(imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico());
			}

			helper.setLigacaoAgua(ligacaoAgua);

			executarEncerramento();
		} catch (FachadaException e) {
			resposta.put("msg", e.getMessage());
		}
	}

	private void executarEncerramentoLigacaoAguaComInstalacaoHidrometro() {
		try {
			LigacaoAgua ligacaoAgua = montarDadosLigacaoAgua();
			HidrometroInstalacaoHistorico instalacao = montarDadosHidrometroInstalacao();
			instalacao.setLigacaoAgua(ligacaoAgua);

			helper.setLigacaoAgua(ligacaoAgua);
			helper.setHidrometroInstalacaoHistorico(instalacao);

			executarEncerramento();
		} catch (Exception e) {
			resposta.put("msg", e.getMessage());
		}
	}
	
	private void executarEncerramentoReligacaoAguaComInstalacaoHidrometro() {
		try {
			LigacaoAgua ligacaoAgua = obterLigacaoAgua();
			ligacaoAgua.setDataReligacao(ordemServico.getDataEncerramento());
			
			HidrometroInstalacaoHistorico instalacao = montarDadosHidrometroInstalacao();
			instalacao.setLigacaoAgua(ligacaoAgua);

			helper.setLigacaoAgua(ligacaoAgua);
			helper.setHidrometroInstalacaoHistorico(instalacao);

			executarEncerramento();
		} catch (Exception e) {
			resposta.put("msg", e.getMessage());
		}
	}

	private void executarEncerramentoRestabelecimentoLigacaoAguaComInstalacaoHidrometro() {
		try {
			validarRestabelecimentoLigacaoAguaComInstalacaoHidrometro();

			LigacaoAgua ligacaoAgua = obterLigacaoAgua();
			ligacaoAgua.setDataRestabelecimentoAgua(ordemServico.getDataEncerramento());
			
			HidrometroInstalacaoHistorico instalacao = montarDadosHidrometroInstalacao();
			instalacao.setLigacaoAgua(ligacaoAgua);

			helper.setLigacaoAgua(ligacaoAgua);
			helper.setHidrometroInstalacaoHistorico(instalacao);

			executarEncerramento();
		} catch (Exception e) {
			resposta.put("msg", e.getMessage());
		}
	}
	
	private void executarEncerramentoLigacaoAguaComSubstituicaoHidrometro() {
		try {
			LigacaoAgua ligacaoAgua = montarDadosLigacaoAgua();

			HidrometroInstalacaoHistorico instalacao = montarDadosHidrometroInstalacao();
			instalacao.setLigacaoAgua(ligacaoAgua);

			HidrometroInstalacaoHistorico substituicao = montarDadosHidrometroSubstituicao();

			if (hidrometroNaoExtraviado()) {
				helper.setLocalArmazenagemHidrometro(dto.getHidrometro().getLocalArmazenagem());
			}

			helper.setLigacaoAgua(ligacaoAgua);
			helper.setHidrometroInstalacaoHistorico(instalacao);
			helper.setHidrometroSubstituicaoHistorico(substituicao);
			helper.setSituacaoHidrometroSubstituido(dto.getHidrometro().getSituacao().toString());

			executarEncerramento();
		} catch (Exception e) {
			resposta.put("msg", e.getMessage());
		}
	}
	
	private void executarEncerramentoReligacaoAguaComSubstituicaoHidrometro() {
		try {
			LigacaoAgua ligacaoAgua = obterLigacaoAgua();
			ligacaoAgua.setDataReligacao(ordemServico.getDataEncerramento());

			HidrometroInstalacaoHistorico instalacao = montarDadosHidrometroInstalacao();
			instalacao.setLigacaoAgua(ligacaoAgua);

			HidrometroInstalacaoHistorico substituicao = montarDadosHidrometroSubstituicao();

			if (hidrometroNaoExtraviado()) {
				helper.setLocalArmazenagemHidrometro(dto.getHidrometro().getLocalArmazenagem());
			}

			helper.setLigacaoAgua(ligacaoAgua);
			helper.setHidrometroInstalacaoHistorico(instalacao);
			helper.setHidrometroSubstituicaoHistorico(substituicao);
			helper.setSituacaoHidrometroSubstituido(dto.getHidrometro().getSituacao().toString());

			executarEncerramento();
		} catch (Exception e) {
			resposta.put("msg", e.getMessage());
		}
	}
	
	private void executarEncerramentoRestabelecimentoLigacaoAguaComSubstituicaoHidrometro() {
		try {
			validarRestabelecimentoLigacaoAguaComInstalacaoHidrometro();

			LigacaoAgua ligacaoAgua = obterLigacaoAgua();
			ligacaoAgua.setDataRestabelecimentoAgua(ordemServico.getDataEncerramento());

			HidrometroInstalacaoHistorico instalacao = montarDadosHidrometroInstalacao();
			instalacao.setLigacaoAgua(ligacaoAgua);

			HidrometroInstalacaoHistorico substituicao = montarDadosHidrometroSubstituicao();

			if (hidrometroNaoExtraviado()) {
				helper.setLocalArmazenagemHidrometro(dto.getHidrometro().getLocalArmazenagem());
			}

			helper.setLigacaoAgua(ligacaoAgua);
			helper.setHidrometroInstalacaoHistorico(instalacao);
			helper.setHidrometroSubstituicaoHistorico(substituicao);
			helper.setSituacaoHidrometroSubstituido(dto.getHidrometro().getSituacao().toString());

			executarEncerramento();
		} catch (Exception e) {
			resposta.put("msg", e.getMessage());
		}
	}
	
	/*
	 * INÍCIO DOS MÉTODOS AUXILIARES
	 */
	private void setarDadosEncerramento() {
		
		resposta = new HashMap<String, String>();
		
		try {
			ordemServico = fachada.recuperaOSPorId(dto.getId());
			if (ordemServico == null) {
				resposta.put("msg", "Ordem de Serviço não encontrada");
				return;
			}
			ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.SIM);
			ordemServico.setValorAtual(dto.getServicoTipoValor());
			ordemServico.setPercentualCobranca(new BigDecimal(100));
			ordemServico.setDataEncerramento(Util.converterStringParaDateComDataETempo(dto.getDataEncerramento()));
			ordemServico.setUltimaAlteracao(new Date());
	
			usuario = fachada.pesquisarUsuario(dto.getIdUsuarioEncerramento());
	
			imovel = ordemServico.getImovel();
			imovel.setUltimaAlteracao(new Date());
	
			helper = new IntegracaoComercialHelper();
			helper.setImovel(imovel);
			helper.setMatriculaImovel(imovel.getId().toString());
			helper.setOrdemServico(ordemServico);
			helper.setQtdParcelas(1);
			helper.setUsuarioLogado(usuario);
			helper.setVeioEncerrarOS(true);
			
		} catch (Exception e) {
			resposta.put("msg", e.getMessage());
			return;
		}
	}
	
	@SuppressWarnings("unchecked")
	private RegistroAtendimento montarRegistroAtendimento() {
		Filtro filtro = new FiltroRegistroAtendimento();
		filtro.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, ordemServico.getRegistroAtendimento().getId()));

		Collection<RegistroAtendimento> colecao = fachada.pesquisar(filtro, RegistroAtendimento.class.getName());

		RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecao);

		registroAtendimento.setAtendimentoMotivoEncerramento(getMotivo());
		registroAtendimento.setCodigoSituacao(RegistroAtendimento.SITUACAO_ENCERRADO);
		registroAtendimento.setDataEncerramento(ordemServico.getDataEncerramento());
		registroAtendimento.setObservacao("ENCERRADO VIA APLICATICO");
		registroAtendimento.setUltimaAlteracao(new Date());

		return registroAtendimento;
	}

	private AtendimentoMotivoEncerramento getMotivo() {
		AtendimentoMotivoEncerramento motivo = new AtendimentoMotivoEncerramento();
		motivo.setId(dto.getMotivoEncerramento());
		motivo.setIndicadorExecucao(AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM);
		return motivo;
	}
	
	private RegistroAtendimentoUnidade montarRegistroAtendimentoUnidade(RegistroAtendimento registroAtendimento) {
		RegistroAtendimentoUnidade unidade = new RegistroAtendimentoUnidade();
		unidade.setRegistroAtendimento(registroAtendimento);
		unidade.setUsuario(usuario);

		if (usuario.getUnidadeOrganizacional() != null) {
			unidade.setUnidadeOrganizacional(usuario.getUnidadeOrganizacional());
		}

		unidade.setAtendimentoRelacaoTipo(new AtendimentoRelacaoTipo(AtendimentoRelacaoTipo.ENCERRAR));
		unidade.setUltimaAlteracao(new Date());
		
		return unidade;
	}

	@SuppressWarnings("unchecked")
	private LigacaoAgua obterLigacaoAgua() {
		Filtro filtro = new FiltroLigacaoAgua();
		filtro.adicionarParametro(new ParametroSimples(FiltroLigacaoAgua.ID, imovel.getId()));

		Collection<LigacaoAgua> colecao = fachada.pesquisar(filtro, LigacaoAgua.class.getName());

		LigacaoAgua ligacaoAgua = (LigacaoAgua) Util.retonarObjetoDeColecao(colecao);
		ligacaoAgua.setId(imovel.getId());

		return ligacaoAgua;
	}

	private boolean hidrometroNaoExtraviado() {
		Integer situacao = dto.getHidrometro().getSituacao();
		return situacao != null && situacao != HidrometroSituacao.EXTRAVIADO;
	}

	private HidrometroInstalacaoHistorico montarDadosHidrometroSubstituicao() {
		HidrometroInstalacaoHistorico substituicao = obterDadosHidrometroSubstituicao();
		substituicao.setUltimaAlteracao(new Date());
		substituicao.setDataRetirada(ordemServico.getDataEncerramento());
		substituicao.setUsuarioRetirada(helper.getUsuarioLogado());

		if (dto.getHidrometro().getLeituraRetirada() != null) {
			substituicao.setNumeroLeituraRetirada(dto.getHidrometro().getLeituraRetirada());
		}
		return substituicao;
	}

	private HidrometroInstalacaoHistorico obterDadosHidrometroSubstituicao() {
		int tipoMedicao = dto.getHidrometro().getTipoMedicao().intValue();
		if (tipoMedicao == MedicaoTipo.LIGACAO_AGUA.intValue()) {
			return imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico();
		} else {
			return imovel.getHidrometroInstalacaoHistorico();
		}
	}

	private HidrometroInstalacaoHistorico montarDadosHidrometroInstalacao() {
		try {
			HidrometroInstalacaoHistorico instalacao = new HidrometroInstalacaoHistorico();
	
			Hidrometro hidrometro = validarHidrometroInstalacao(dto.getHidrometroInstalacao().getNumero());
			instalacao.setHidrometro(hidrometro);
	
			verificarLocalArmazenagemHidrometroInstalacao(hidrometro);
			
			return setarCamposHidrometroInstalacao(instalacao);
			
		} catch (Exception e) {
			throw new FachadaException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private void verificarLocalArmazenagemHidrometroInstalacao(Hidrometro hidrometro) {
		Filtro filtro = new FiltroImovel();
		filtro.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
		filtro.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));

		Collection<Imovel> colecao = fachada.pesquisar(filtro, Imovel.class.getName());

		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecao);

		if (imovel != null && 
			imovel.getLocalidade().getHidrometroLocalArmazenagem() != null &&
			hidrometro.getHidrometroLocalArmazenagem() != null &&
			!hidrometro.getHidrometroLocalArmazenagem().getId().equals(imovel.getLocalidade().getHidrometroLocalArmazenagem().getId())) {
			
			throw new FachadaException("Local de armazenagem incompativel");
		}
	}

	private Hidrometro validarHidrometroInstalacao(String numero) {
		Hidrometro hidrometro = fachada.pesquisarHidrometroNumeroSituacao(numero, HidrometroSituacao.DISPONIVEL);

		if (hidrometro == null) {
			throw new FachadaException("Hidrometro inexistente ou indisponivel");
		}

		return hidrometro;
	}

	private boolean existeRegistroAtendimento() {
		return ordemServico.getRegistroAtendimento() != null && 
			   ordemServico.getRegistroAtendimento().getId() != null;
	}
	
	private HidrometroInstalacaoHistorico setarCamposHidrometroInstalacao(HidrometroInstalacaoHistorico instalacao) {
		try {
			HidrometroInstalacaoDTO instalacaoDTO = dto.getHidrometroInstalacao();
	
			instalacao.setDataInstalacao(ordemServico.getDataEncerramento());
			instalacao.setUsuarioInstalacao(helper.getUsuarioLogado());
	
			Integer tipoMedicao = instalacaoDTO.getTipoMedicao().intValue();
			if (tipoMedicao.equals(MedicaoTipo.POCO)) {
				instalacao.setImovel(ordemServico.getImovel());
				instalacao.setLigacaoAgua(null);
	
			} else if (tipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA)) {
				LigacaoAgua ligacaoAgua = new LigacaoAgua();
				ligacaoAgua.setId(ordemServico.getImovel().getId());
				instalacao.setLigacaoAgua(ligacaoAgua);
				instalacao.setImovel(null);
			}
	
			instalacao.setMedicaoTipo(new MedicaoTipo(tipoMedicao));
			instalacao.setHidrometroLocalInstalacao(new HidrometroLocalInstalacao(instalacaoDTO.getLocal()));
			instalacao.setHidrometroProtecao(new HidrometroProtecao(instalacaoDTO.getProtecao()));
			instalacao.setNumeroLeituraInstalacao(instalacaoDTO.getLeitura());
			instalacao.setIndicadorExistenciaCavalete(instalacaoDTO.getCavalete().shortValue());
			instalacao.setNumeroSelo(instalacaoDTO.getSelo());
			instalacao.setNumeroLacre(instalacaoDTO.getLacre());
			instalacao.setDataImplantacaoSistema(new Date());
			instalacao.setIndicadorInstalcaoSubstituicao(new Short("1"));
			instalacao.setUltimaAlteracao(new Date());
			instalacao.setIndicadorTrocaProtecao(instalacaoDTO.getTrocaProtecao().shortValue());
			instalacao.setIndicadorTrocaRegistro(instalacaoDTO.getTrocaRegistro().shortValue());
		} catch (Exception e) {
			throw new FachadaException("Erro ao setar dados de instalacao do Hidrometro");
		}
		return instalacao;
	}
	
	private LigacaoAgua montarDadosLigacaoAgua() {
		LigacaoAguaDTO ligacaoAguaDTO = dto.getLigacaoAgua();

		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		ligacaoAgua.setImovel(imovel);
		ligacaoAgua.setDataLigacao(ordemServico.getDataEncerramento());
		ligacaoAgua.setLigacaoAguaDiametro(ligacaoAguaDTO.getDiametro());
		ligacaoAgua.setLigacaoAguaMaterial(ligacaoAguaDTO.getMaterial());
		ligacaoAgua.setLigacaoAguaPerfil(ligacaoAguaDTO.getPerfil());
		ligacaoAgua.setRamalLocalInstalacao(ligacaoAguaDTO.getLocalInstalacaoRamal());
		ligacaoAgua.setProfundidadeRamal(ligacaoAguaDTO.getProfundidadeRamal());
		ligacaoAgua.setDistanciaInstalacaoRamal(ligacaoAguaDTO.getDistanciaInstalacaoRamal());
		ligacaoAgua.setPavimentoRua(ligacaoAguaDTO.getPavimentoRua());
		ligacaoAgua.setPavimentoCalcada(ligacaoAguaDTO.getPavimentoCalcada());
		ligacaoAgua.setLigacaoOrigem(ligacaoAguaDTO.getOrigem());
		ligacaoAgua.setNumeroLacre(ligacaoAguaDTO.getLacre());
		
		return ligacaoAgua;
	}
	
	private void validarRestabelecimentoLigacaoAguaComInstalacaoHidrometro() {
		if (imovelNaoSuprimido()) {
			throw new FachadaException("A situação de Ligação de Água do imóvel é inválida");
		}

		if (naoPossuiRedeDeAgua()) {
			throw new FachadaException("Não existe rede de água na quadra do imóvel");
		}

		if (imovelInativo()) {
			throw new FachadaException("O imóvel não está ativo");
		}

		if (naoPossuiSubstituicao() && possuiHidrometroInstalado()) {
			throw new FachadaException("O imóvel já possui hidrômetro instalado para o tipo de médição LIGAÇÃO DE ÁGUA");
		}
	}

	private boolean imovelNaoSuprimido() {
		int situacao = imovel.getLigacaoAguaSituacao().getId().intValue();

		return situacao != LigacaoAguaSituacao.SUPRIMIDO.intValue() &&
			   situacao != LigacaoAguaSituacao.SUPR_PARC_PEDIDO.intValue();
	}

	private boolean naoPossuiRedeDeAgua() {
		IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());

		return integracao.getIndicadorRedeAgua().shortValue() == Quadra.SEM_REDE.shortValue();
	}

	private boolean imovelInativo() {
		return imovel.getIndicadorExclusao().intValue() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO;
	}

	private boolean naoPossuiSubstituicao() {
		return dto.getOperacao().intValue() != Operacao.OPERACAO_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_COM_SUBSTITUICAO_HIDROMETRO;
	}
	
	private boolean possuiHidrometroInstalado() {
		return imovel != null &&
			   imovel.getLigacaoAgua() != null &&
			   imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null;
	}
}
