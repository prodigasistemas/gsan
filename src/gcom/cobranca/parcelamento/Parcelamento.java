package gcom.cobranca.parcelamento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Parcelamento extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	public final static int CASAS_DECIMAIS = 2;
	public final static int TIPO_ARREDONDAMENTO = BigDecimal.ROUND_HALF_DOWN;

	private Integer id;

	private Date parcelamento;

	private Integer anoMesReferenciaFaturamento;

	private BigDecimal valorConta;

	private BigDecimal valorServicosACobrar;

	private BigDecimal valorAtualizacaoMonetaria;

	private BigDecimal valorJurosMora;

	private BigDecimal valorMulta;

	private BigDecimal valorDebitoAtualizado;

	private BigDecimal valorDescontoFaixaReferenciaConta;

	private BigDecimal valorDescontoAcrescimos;

	private BigDecimal valorEntrada;

	private BigDecimal valorJurosParcelamento;

	private Short numeroPrestacoes;

	private BigDecimal valorPrestacao;

	private BigDecimal valorDescontoAntiguidade;

	private Short indicadorDebitoACobrar;

	private BigDecimal valorDescontoInatividade;

	private BigDecimal percentualDescontoAcrescimos;

	private Short indicadorAcrescimosImpontualdade;

	private BigDecimal valorGuiaPapagamento;

	private BigDecimal percentualDescontoAntiguidade;

	private BigDecimal percentualDescontoInatividade;

	private Integer codigoSetorComercial;

	private Integer numeroQuadra;

	private Date ultimaAlteracao;

	private BigDecimal valorCreditoARealizar;

	private BigDecimal valorParcelamentosACobrar;

	private Short indicadorRestabelecimento;

	private Short indicadorContasRevisao;

	private Short indicadorGuiasPagamento;

	private Short indicadorCreditoARealizar;

	private BigDecimal taxaJuros;

	private BigDecimal valorDescontoSancao;

	private Short numeroParcelasPagasConsecutivas;

	private ParcelamentoTipo parcelamentoTipo;

	private ParcelamentoSituacao parcelamentoSituacao;

	private RegistroAtendimento registroAtendimento;

	private Imovel imovel;

	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

	private ParcelamentoPerfil parcelamentoPerfil;

	private ImovelPerfil imovelPerfil;

	private CobrancaForma cobrancaForma;

	private Quadra quadra;

	private Localidade localidade;

	private ParcelamentoMotivoDesfazer parcelamentoMotivoDesfazer;

	private LigacaoAguaSituacao ligacaoAguaSituacao;

	private Funcionario funcionario;

	private Short indicadorConfirmacaoParcelamento;

	private Cliente cliente;

	private Usuario usuario;

	private Usuario usuarioDesfez;

	private ResolucaoDiretoria resolucaoDiretoria;

	private BigDecimal valorDescontoTarifaSocial;
	
	private Usuario usuarioCancelamento;
	
	private Date dataCancelamento;
	
	private ParcelamentoMotivoCancelamento motivoCancelamento;

	public Parcelamento(Date parcelamento, Integer anoMesReferenciaFaturamento, BigDecimal valorConta, BigDecimal valorServicosACobrar, BigDecimal valorAtualizacaoMonetaria,
			BigDecimal valorJurosMora, BigDecimal valorMulta, BigDecimal valorDebitoAtualizado, BigDecimal valorDescontoFaixaReferenciaConta, BigDecimal valorDescontoAcrescimos,
			BigDecimal valorEntrada, BigDecimal valorJurosParcelamento, Short numeroPrestacoes, BigDecimal valorPrestacao, BigDecimal valorDescontoAntiguidade, Short indicadorDebitoACobrar,
			BigDecimal valorDescontoInatividade, BigDecimal percentualDescontoAcrescimos, Short indicadorAcrescimosImpontualdade, BigDecimal valorGuiaPapagamento,
			BigDecimal percentualDescontoAntiguidade, BigDecimal percentualDescontoInatividade, Integer codigoSetorComercial, Integer numeroQuadra, Date ultimaAlteracao,
			BigDecimal valorCreditoARealizar, BigDecimal valorParcelamentosACobrar, Short indicadorRestabelecimento, Short indicadorContasRevisao, Short indicadorGuiasPagamento,
			Short indicadorCreditoARealizar, BigDecimal taxaJuros, ParcelamentoTipo parcelamentoTipo, ParcelamentoSituacao parcelamentoSituacao,
			RegistroAtendimento registroAtendimento, Imovel imovel, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ParcelamentoPerfil parcelamentoPerfil,
			ImovelPerfil imovelPerfil, CobrancaForma cobrancaForma, Quadra quadra, Localidade localidade, ParcelamentoMotivoDesfazer parcelamentoMotivoDesfazer,
			LigacaoAguaSituacao ligacaoAguaSituacao, Funcionario funcionario, Short indicadorConfirmacaoParcelamento, Cliente cliente, Usuario usuario, ResolucaoDiretoria resolucaoDiretoria,
			BigDecimal valorDescontoSancao) {
		this.parcelamento = parcelamento;
		this.anoMesReferenciaFaturamento = anoMesReferenciaFaturamento;
		this.valorConta = valorConta;
		this.valorServicosACobrar = valorServicosACobrar;
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
		this.valorJurosMora = valorJurosMora;
		this.valorMulta = valorMulta;
		this.valorDebitoAtualizado = valorDebitoAtualizado;
		this.valorDescontoFaixaReferenciaConta = valorDescontoFaixaReferenciaConta;
		this.valorDescontoAcrescimos = valorDescontoAcrescimos;
		this.valorEntrada = valorEntrada;
		this.valorJurosParcelamento = valorJurosParcelamento;
		this.numeroPrestacoes = numeroPrestacoes;
		this.valorPrestacao = valorPrestacao;
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
		this.indicadorDebitoACobrar = indicadorDebitoACobrar;
		this.valorDescontoInatividade = valorDescontoInatividade;
		this.percentualDescontoAcrescimos = percentualDescontoAcrescimos;
		this.indicadorAcrescimosImpontualdade = indicadorAcrescimosImpontualdade;
		this.valorGuiaPapagamento = valorGuiaPapagamento;
		this.percentualDescontoAntiguidade = percentualDescontoAntiguidade;
		this.percentualDescontoInatividade = percentualDescontoInatividade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.ultimaAlteracao = ultimaAlteracao;
		this.valorCreditoARealizar = valorCreditoARealizar;
		this.valorParcelamentosACobrar = valorParcelamentosACobrar;
		this.indicadorRestabelecimento = indicadorRestabelecimento;
		this.indicadorContasRevisao = indicadorContasRevisao;
		this.indicadorGuiasPagamento = indicadorGuiasPagamento;
		this.indicadorCreditoARealizar = indicadorCreditoARealizar;
		this.taxaJuros = taxaJuros;
		this.parcelamentoTipo = parcelamentoTipo;
		this.parcelamentoSituacao = parcelamentoSituacao;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.parcelamentoPerfil = parcelamentoPerfil;
		this.imovelPerfil = imovelPerfil;
		this.cobrancaForma = cobrancaForma;
		this.quadra = quadra;
		this.localidade = localidade;
		this.parcelamentoMotivoDesfazer = parcelamentoMotivoDesfazer;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.funcionario = funcionario;
		this.indicadorConfirmacaoParcelamento = indicadorConfirmacaoParcelamento;
		this.cliente = cliente;
		this.usuario = usuario;
		this.resolucaoDiretoria = resolucaoDiretoria;
		this.valorDescontoSancao = valorDescontoSancao;
	}

	public Parcelamento() {
	}

	public Parcelamento(ParcelamentoTipo parcelamentoTipo, ParcelamentoSituacao parcelamentoSituacao, RegistroAtendimento registroAtendimento,
			Imovel imovel, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ParcelamentoPerfil parcelamentoPerfil, ImovelPerfil imovelPerfil, CobrancaForma cobrancaForma,
			Quadra quadra, Localidade localidade, ParcelamentoMotivoDesfazer parcelamentoMotivoDesfazer, LigacaoAguaSituacao ligacaoAguaSituacao, Funcionario funcionario,
			Short indicadorConfirmacaoParcelamento) {
		this.parcelamentoTipo = parcelamentoTipo;
		this.parcelamentoSituacao = parcelamentoSituacao;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.parcelamentoPerfil = parcelamentoPerfil;
		this.imovelPerfil = imovelPerfil;
		this.cobrancaForma = cobrancaForma;
		this.quadra = quadra;
		this.localidade = localidade;
		this.parcelamentoMotivoDesfazer = parcelamentoMotivoDesfazer;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.indicadorConfirmacaoParcelamento = indicadorConfirmacaoParcelamento;
	}

	public Parcelamento(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getParcelamento() {
		return this.parcelamento;
	}

	public void setParcelamento(Date parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Integer getAnoMesReferenciaFaturamento() {
		return this.anoMesReferenciaFaturamento;
	}

	public void setAnoMesReferenciaFaturamento(Integer anoMesReferenciaFaturamento) {
		this.anoMesReferenciaFaturamento = anoMesReferenciaFaturamento;
	}

	public BigDecimal getValorConta() {
		return this.valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public BigDecimal getValorServicosACobrar() {
		return this.valorServicosACobrar;
	}

	public void setValorServicosACobrar(BigDecimal valorServicosACobrar) {
		this.valorServicosACobrar = valorServicosACobrar;
	}

	public BigDecimal getValorAtualizacaoMonetaria() {
		return this.valorAtualizacaoMonetaria;
	}

	public void setValorAtualizacaoMonetaria(BigDecimal valorAtualizacaoMonetaria) {
		this.valorAtualizacaoMonetaria = valorAtualizacaoMonetaria;
	}

	public BigDecimal getValorJurosMora() {
		return this.valorJurosMora;
	}

	public void setValorJurosMora(BigDecimal valorJurosMora) {
		this.valorJurosMora = valorJurosMora;
	}

	public BigDecimal getValorMulta() {
		return this.valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public BigDecimal getValorDebitoAtualizado() {
		return this.valorDebitoAtualizado;
	}

	public void setValorDebitoAtualizado(BigDecimal valorDebitoAtualizado) {
		this.valorDebitoAtualizado = valorDebitoAtualizado;
	}

	public BigDecimal getValorDescontoFaixaReferenciaConta() {
		return valorDescontoFaixaReferenciaConta;
	}

	public void setValorDescontoFaixaReferenciaConta(BigDecimal valorDescontoFaixaReferenciaConta) {
		this.valorDescontoFaixaReferenciaConta = valorDescontoFaixaReferenciaConta;
	}

	public BigDecimal getValorDescontoAcrescimos() {
		return this.valorDescontoAcrescimos;
	}

	public void setValorDescontoAcrescimos(BigDecimal valorDescontoAcrescimos) {
		this.valorDescontoAcrescimos = valorDescontoAcrescimos;
	}

	public BigDecimal getValorEntrada() {
		return this.valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorJurosParcelamento() {
		return this.valorJurosParcelamento;
	}

	public void setValorJurosParcelamento(BigDecimal valorJurosParcelamento) {
		this.valorJurosParcelamento = valorJurosParcelamento;
	}

	public Short getNumeroPrestacoes() {
		return this.numeroPrestacoes;
	}

	public void setNumeroPrestacoes(Short numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}

	public BigDecimal getValorPrestacao() {
		return this.valorPrestacao;
	}

	public void setValorPrestacao(BigDecimal valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}

	public BigDecimal getValorDescontoAntiguidade() {
		return this.valorDescontoAntiguidade;
	}

	public void setValorDescontoAntiguidade(BigDecimal valorDescontoAntiguidade) {
		this.valorDescontoAntiguidade = valorDescontoAntiguidade;
	}

	public Short getIndicadorDebitoACobrar() {
		return this.indicadorDebitoACobrar;
	}

	public void setIndicadorDebitoACobrar(Short indicadorDebitoACobrar) {
		this.indicadorDebitoACobrar = indicadorDebitoACobrar;
	}

	public BigDecimal getValorDescontoInatividade() {
		return this.valorDescontoInatividade;
	}

	public void setValorDescontoInatividade(BigDecimal valorDescontoInatividade) {
		this.valorDescontoInatividade = valorDescontoInatividade;
	}

	public BigDecimal getPercentualDescontoAcrescimos() {
		return this.percentualDescontoAcrescimos;
	}

	public void setPercentualDescontoAcrescimos(BigDecimal percentualDescontoAcrescimos) {
		this.percentualDescontoAcrescimos = percentualDescontoAcrescimos;
	}

	public Short getIndicadorAcrescimosImpontualdade() {
		return this.indicadorAcrescimosImpontualdade;
	}

	public void setIndicadorAcrescimosImpontualdade(Short indicadorAcrescimosImpontualdade) {
		this.indicadorAcrescimosImpontualdade = indicadorAcrescimosImpontualdade;
	}

	public BigDecimal getValorGuiaPapagamento() {
		return this.valorGuiaPapagamento;
	}

	public void setValorGuiaPapagamento(BigDecimal valorGuiaPapagamento) {
		this.valorGuiaPapagamento = valorGuiaPapagamento;
	}

	public BigDecimal getPercentualDescontoAntiguidade() {
		return this.percentualDescontoAntiguidade;
	}

	public void setPercentualDescontoAntiguidade(BigDecimal percentualDescontoAntiguidade) {
		this.percentualDescontoAntiguidade = percentualDescontoAntiguidade;
	}

	public BigDecimal getPercentualDescontoInatividade() {
		return this.percentualDescontoInatividade;
	}

	public void setPercentualDescontoInatividade(BigDecimal percentualDescontoInatividade) {
		this.percentualDescontoInatividade = percentualDescontoInatividade;
	}

	public Integer getCodigoSetorComercial() {
		return this.codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getNumeroQuadra() {
		return this.numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorCreditoARealizar() {
		return this.valorCreditoARealizar;
	}

	public void setValorCreditoARealizar(BigDecimal valorCreditoARealizar) {
		this.valorCreditoARealizar = valorCreditoARealizar;
	}

	public BigDecimal getValorParcelamentosACobrar() {
		return this.valorParcelamentosACobrar;
	}

	public void setValorParcelamentosACobrar(BigDecimal valorParcelamentosACobrar) {
		this.valorParcelamentosACobrar = valorParcelamentosACobrar;
	}

	public Short getIndicadorRestabelecimento() {
		return this.indicadorRestabelecimento;
	}

	public void setIndicadorRestabelecimento(Short indicadorRestabelecimento) {
		this.indicadorRestabelecimento = indicadorRestabelecimento;
	}

	public Short getIndicadorContasRevisao() {
		return this.indicadorContasRevisao;
	}

	public void setIndicadorContasRevisao(Short indicadorContasRevisao) {
		this.indicadorContasRevisao = indicadorContasRevisao;
	}

	public Short getIndicadorGuiasPagamento() {
		return this.indicadorGuiasPagamento;
	}

	public void setIndicadorGuiasPagamento(Short indicadorGuiasPagamento) {
		this.indicadorGuiasPagamento = indicadorGuiasPagamento;
	}

	public Short getIndicadorCreditoARealizar() {
		return this.indicadorCreditoARealizar;
	}

	public void setIndicadorCreditoARealizar(Short indicadorCreditoARealizar) {
		this.indicadorCreditoARealizar = indicadorCreditoARealizar;
	}

	public BigDecimal getTaxaJuros() {
		return this.taxaJuros;
	}

	public void setTaxaJuros(BigDecimal taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public ParcelamentoTipo getParcelamentoTipo() {
		return this.parcelamentoTipo;
	}

	public void setParcelamentoTipo(ParcelamentoTipo parcelamentoTipo) {
		this.parcelamentoTipo = parcelamentoTipo;
	}

	public ParcelamentoSituacao getParcelamentoSituacao() {
		return this.parcelamentoSituacao;
	}

	public void setParcelamentoSituacao(ParcelamentoSituacao parcelamentoSituacao) {
		this.parcelamentoSituacao = parcelamentoSituacao;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return this.registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return this.ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public ParcelamentoPerfil getParcelamentoPerfil() {
		return this.parcelamentoPerfil;
	}

	public void setParcelamentoPerfil(ParcelamentoPerfil parcelamentoPerfil) {
		this.parcelamentoPerfil = parcelamentoPerfil;
	}

	public ImovelPerfil getImovelPerfil() {
		return this.imovelPerfil;
	}

	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public CobrancaForma getCobrancaForma() {
		return this.cobrancaForma;
	}

	public void setCobrancaForma(CobrancaForma cobrancaForma) {
		this.cobrancaForma = cobrancaForma;
	}

	public Quadra getQuadra() {
		return this.quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public ParcelamentoMotivoDesfazer getParcelamentoMotivoDesfazer() {
		return this.parcelamentoMotivoDesfazer;
	}

	public void setParcelamentoMotivoDesfazer(ParcelamentoMotivoDesfazer parcelamentoMotivoDesfazer) {
		this.parcelamentoMotivoDesfazer = parcelamentoMotivoDesfazer;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return this.ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public BigDecimal getValorDesconto() {
		BigDecimal retorno = valorDescontoAcrescimos.add(valorDescontoAntiguidade).add(valorDescontoInatividade);

		if (valorDescontoSancao != null) {
			retorno = valorDescontoSancao.add(retorno);
		}

		if (valorDescontoTarifaSocial != null) {
			retorno = valorDescontoTarifaSocial.add(retorno);
		}

		if (valorDescontoFaixaReferenciaConta != null) {
			retorno = valorDescontoFaixaReferenciaConta.add(retorno);
		}

		return retorno.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getValorAcrescimoImpontualidade() {
		BigDecimal retorno = valorMulta.add(valorJurosMora).add(valorAtualizacaoMonetaria);

		return retorno.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getValorNegociado() {
		BigDecimal retorno = valorDebitoAtualizado.subtract(getValorDesconto());

		return retorno.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getValorParcelado() {
		BigDecimal retorno = getValorNegociado().subtract(valorEntrada).add(valorJurosParcelamento);

		return retorno.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Short getIndicadorConfirmacaoParcelamento() {
		return indicadorConfirmacaoParcelamento;
	}

	public void setIndicadorConfirmacaoParcelamento(Short indicadorConfirmacaoParcelamento) {
		this.indicadorConfirmacaoParcelamento = indicadorConfirmacaoParcelamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ResolucaoDiretoria getResolucaoDiretoria() {
		return resolucaoDiretoria;
	}

	public void setResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria) {
		this.resolucaoDiretoria = resolucaoDiretoria;
	}

	public BigDecimal getValorDescontoSancao() {
		return valorDescontoSancao;
	}

	public void setValorDescontoSancao(BigDecimal valorDescontoSancao) {
		this.valorDescontoSancao = valorDescontoSancao;
	}

	public BigDecimal getValorDescontoTarifaSocial() {
		return valorDescontoTarifaSocial;
	}

	public void setValorDescontoTarifaSocial(BigDecimal valorDescontoTarifaSocial) {
		this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
	}

	public Short getNumeroParcelasPagasConsecutivas() {
		return numeroParcelasPagasConsecutivas;
	}

	public void setNumeroParcelasPagasConsecutivas(Short numeroParcelasPagasConsecutivas) {
		this.numeroParcelasPagasConsecutivas = numeroParcelasPagasConsecutivas;
	}

	public Usuario getUsuarioDesfez() {
		return usuarioDesfez;
	}

	public void setUsuarioDesfez(Usuario usuarioDesfez) {
		this.usuarioDesfez = usuarioDesfez;
	}

	public Usuario getUsuarioCancelamento() {
		return usuarioCancelamento;
	}

	public void setUsuarioCancelamento(Usuario usuarioCancelamento) {
		this.usuarioCancelamento = usuarioCancelamento;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public ParcelamentoMotivoCancelamento getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(ParcelamentoMotivoCancelamento motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroParcelamento filtro = new FiltroParcelamento();

		filtro.adicionarParametro(new ParametroSimples(FiltroParcelamento.ID, this.getId()));

		return filtro;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}

}
