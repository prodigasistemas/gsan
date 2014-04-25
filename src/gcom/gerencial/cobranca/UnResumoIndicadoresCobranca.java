package gcom.gerencial.cobranca;

import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.faturamento.credito.GCreditoOrigem;
import gcom.gerencial.faturamento.credito.GCreditoTipo;
import gcom.gerencial.faturamento.debito.GDebitoTipo;
import gcom.gerencial.financeiro.GFinanciamentoTipo;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import gcom.gerencial.micromedicao.GRota;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoIndicadoresCobranca implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private String anoReferencia;

    /** persistent field */
    private String mesReferencia;

    /** persistent field */
    private int codigoSetorComercial;

    /** persistent field */
    private int numeroQuadra;

    /** persistent field */
    private int valorArrecadadoAcumuladoAte3Meses;

    /** persistent field */
    private int quantidadesContasEGuias;

    /** persistent field */
    private int valorFaturamentoLiquidoMesAno;

    /** persistent field */
    private int quantidadeContasFaturamentoLiquidoMesAno;

    /** persistent field */
    private int quantidadeContasPendentesMesAno;

    /** persistent field */
    private int valorPendenteContaMesAno;

    /** persistent field */
    private int quantidadeLigacoes;

    /** persistent field */
    private int quantidadeLigacoesAtivas;

    /** persistent field */
    private int quantidadeDocumentos;

    /** persistent field */
    private int quantidadeContasPendentes;

    /** persistent field */
    private int valorPendenteTotal;

    /** persistent field */
    private BigDecimal valorPendenteConta;

    /** persistent field */
    private BigDecimal valorPendenteServicos;

    /** persistent field */
    private BigDecimal valorPendenteParcelamento;

    /** persistent field */
    private BigDecimal quantidadeLigacoesAtivasAgua;

    /** persistent field */
    private BigDecimal quantidadeLigacoesInativasAgua;

    /** persistent field */
    private BigDecimal quantidadeLigacoesTotaisAgua;

    /** persistent field */
    private BigDecimal quantidadeContasRecebidas;

    /** persistent field */
    private BigDecimal valorArrecadado;

    /** persistent field */
    private BigDecimal valorArrecadadoMesAteVencimento;

    /** persistent field */
    private BigDecimal valorArrecadadoMesAposVencimento;

    /** persistent field */
    private BigDecimal valorArrecadadoMes2;

    /** persistent field */
    private BigDecimal valorArrecadadoMes3;

    /** persistent field */
    private BigDecimal valorArrecadadoParcelamento;

    /** persistent field */
    private int quantidadeContasFaturamentoLiquido;

    /** persistent field */
    private int valorFaturamentoLiquido;

    /** persistent field */
    private int quantidadeParcelmantos;

    /** persistent field */
    private int valorNegociado;

    /** persistent field */
    private int valorFinanciado;

    /** persistent field */
    private int valorParcelado;

    /** persistent field */
    private int quantidadeCortes;

    /** persistent field */
    private int quantidadeSupressoes;

    /** persistent field */
    private int quantidadeReligacoes;

    /** persistent field */
    private int quantidadeReestabelecimentos;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GQuadra gerQuadra;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GCreditoTipo gerCreditoTipo;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GRota gerRota;

    /** full constructor */
    public UnResumoIndicadoresCobranca(Integer id, int anoMesReferencia, String anoReferencia, String mesReferencia,
    		int codigoSetorComercial, int numeroQuadra, int valorArrecadadoAcumuladoAte3Meses, int quantidadesContasEGuias, 
    		int valorFaturamentoLiquidoMesAno, int quantidadeContasFaturamentoLiquidoMesAno, int quantidadeContasPendentesMesAno, 
    		int valorPendenteContaMesAno, int quantidadeLigacoes, int quantidadeLigacoesAtivas, int quantidadeDocumentos, 
    		int quantidadeContasPendentes, int valorPendenteTotal, BigDecimal valorPendenteConta, 
    		BigDecimal valorPendenteServicos, BigDecimal valorPendenteParcelamento, BigDecimal quantidadeLigacoesAtivasAgua,
			BigDecimal quantidadeLigacoesInativasAgua, BigDecimal quantidadeLigacoesTotaisAgua, BigDecimal quantidadeContasRecebidas, 
			BigDecimal valorArrecadado, BigDecimal valorArrecadadoMesAteVencimento, BigDecimal valorArrecadadoMesAposVencimento, 
			BigDecimal valorArrecadadoMes2, BigDecimal valorArrecadadoMes3, BigDecimal valorArrecadadoParcelamento, 
			int quantidadeContasFaturamentoLiquido, int valorFaturamentoLiquido, int quantidadeParcelmantos, 
			int valorNegociado, int valorFinanciado, int valorParcelado, 
			int quantidadeCortes, int quantidadeSupressoes, int quantidadeReligacoes, int quantidadeReestabelecimentos,
			int volumeIncluidoEsgotoMesAno, BigDecimal valorFaturadoAguaMesAno, BigDecimal valorCanceladoAguaMesAno, 
			BigDecimal valorIncluidoAguaMesAno, BigDecimal valorFaturadoEsgotoMesAno, BigDecimal valorCanceladoEsgotoMesAno,
			BigDecimal valorIncluidoEsgotoMesAno, BigDecimal valorDocumentosFaturadosCreditoMesAno, BigDecimal valorCanceladoCreditoMesAno,
			BigDecimal valorIncluidoCreditoMesAno, BigDecimal valorDocumentosFaturadosOutrosMesAno, BigDecimal valorCanceladoOutrosMesAno,
			BigDecimal valorIncluidoOutrosMesAno, BigDecimal valorArrastos, BigDecimal valorParcelamento, int quantidadeGuiasCanceladas, 
			Date ultimaAlteracao, GSubcategoria gerSubcategoria, GClienteTipo gerClienteTipo, GLigacaoAguaSituacao gerLigacaoAguaSituacao, 
			GCreditoOrigem gerCreditoOrigem, GQuadra gerQuadra, GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, GGerenciaRegional gerGerenciaRegional, 
			GSetorComercial gerSetorComercial, GLigacaoAguaPerfil gerLigacaoAguaPerfil, GFinanciamentoTipo gerFinanciamentoTipo, 
			GImovelPerfil gerImovelPerfil, GLancamentoItemContabil gerLancamentoItemContabil, GUnidadeNegocio gerUnidadeNegocio,
			GCreditoTipo gerCreditoTipo, GLocalidade gerLocalidade, GLocalidade gerLocalidadeElo, GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil, 
			GDocumentoTipo gerDocumentoTipo, GEsferaPoder gerEsferaPoder, GDebitoTipo gerDebitoTipo, GCategoria gerCategoria, GRota gerRota) {

    	this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.anoReferencia = anoReferencia;
        this.mesReferencia = mesReferencia;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.valorArrecadadoAcumuladoAte3Meses = valorArrecadadoAcumuladoAte3Meses;
        this.quantidadesContasEGuias = quantidadesContasEGuias;
        this.valorFaturamentoLiquidoMesAno = valorFaturamentoLiquidoMesAno;
        this.quantidadeContasFaturamentoLiquidoMesAno = quantidadeContasFaturamentoLiquidoMesAno;
        this.quantidadeContasPendentesMesAno = quantidadeContasPendentesMesAno;
        this.valorPendenteContaMesAno = valorPendenteContaMesAno;
        this.quantidadeLigacoes = quantidadeLigacoes;
        this.quantidadeLigacoesAtivas = quantidadeLigacoesAtivas;
        this.quantidadeDocumentos = quantidadeDocumentos;
        this.quantidadeContasPendentes = quantidadeContasPendentes;
        this.valorPendenteTotal = valorPendenteTotal;
        this.valorPendenteConta = valorPendenteConta;
        this.valorPendenteServicos = valorPendenteServicos;
        this.valorPendenteParcelamento = valorPendenteParcelamento;
        this.quantidadeLigacoesAtivasAgua = quantidadeLigacoesAtivasAgua;
        this.quantidadeLigacoesInativasAgua = quantidadeLigacoesInativasAgua;
        this.quantidadeLigacoesTotaisAgua = quantidadeLigacoesTotaisAgua;
        this.quantidadeContasRecebidas = quantidadeContasRecebidas;
        this.valorArrecadado = valorArrecadado;
        this.valorArrecadadoMesAteVencimento = valorArrecadadoMesAteVencimento;
        this.valorArrecadadoMesAposVencimento = valorArrecadadoMesAposVencimento;
        this.valorArrecadadoMes2 = valorArrecadadoMes2;
        this.valorArrecadadoMes3 = valorArrecadadoMes3;
        this.valorArrecadadoParcelamento = valorArrecadadoParcelamento;
        this.quantidadeContasFaturamentoLiquido = quantidadeContasFaturamentoLiquido;
        this.valorFaturamentoLiquido = valorFaturamentoLiquido;
        this.quantidadeParcelmantos = quantidadeParcelmantos;
        this.valorNegociado = valorNegociado;
        this.valorFinanciado = valorFinanciado;
        this.valorParcelado = valorParcelado;
        this.quantidadeCortes = quantidadeCortes;
        this.quantidadeSupressoes = quantidadeSupressoes;
        this.quantidadeReligacoes = quantidadeReligacoes;
        this.quantidadeReestabelecimentos = quantidadeReestabelecimentos;
        this.gerSubcategoria = gerSubcategoria;
        this.gerClienteTipo = gerClienteTipo;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerQuadra = gerQuadra;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSetorComercial = gerSetorComercial;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerCreditoTipo = gerCreditoTipo;
        this.gerLocalidade = gerLocalidade;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerCategoria = gerCategoria;
        this.gerRota = gerRota;
    }

    /** default constructor */
    public UnResumoIndicadoresCobranca() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(int anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public String getAnoReferencia() {
        return this.anoReferencia;
    }

    public void setAnoReferencia(String anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public String getMesReferencia() {
        return this.mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public int getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(int codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public int getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(int numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public int getValorArrecadadoAcumuladoAte3Meses() {
        return this.valorArrecadadoAcumuladoAte3Meses;
    }

    public void setValorArrecadadoAcumuladoAte3Meses(int valorArrecadadoAcumuladoAte3Meses) {
        this.valorArrecadadoAcumuladoAte3Meses = valorArrecadadoAcumuladoAte3Meses;
    }

    public int getQuantidadesContasEGuias() {
        return this.quantidadesContasEGuias;
    }

    public void setQuantidadesContasEGuias(int quantidadesContasEGuias) {
        this.quantidadesContasEGuias = quantidadesContasEGuias;
    }

    public int getValorFaturamentoLiquidoMesAno() {
        return this.valorFaturamentoLiquidoMesAno;
    }

    public void setValorFaturamentoLiquidoMesAno(int quantidadeContasCanceladas) {
        this.valorFaturamentoLiquidoMesAno = quantidadeContasCanceladas;
    }

    public int getQuantidadeContasFaturamentoLiquidoMesAno() {
        return this.quantidadeContasFaturamentoLiquidoMesAno;
    }

    public void setQuantidadeContasFaturamentoLiquidoMesAno(int quantidadeContasIncluidas) {
        this.quantidadeContasFaturamentoLiquidoMesAno = quantidadeContasIncluidas;
    }

    public int getQuantidadeContasPendentesMesAno() {
        return this.quantidadeContasPendentesMesAno;
    }

    public void setQuantidadeContasPendentesMesAno(int quantidadeEconomiasFaturadas) {
        this.quantidadeContasPendentesMesAno = quantidadeEconomiasFaturadas;
    }

    public int getValorPendenteContaMesAno() {
        return this.valorPendenteContaMesAno;
    }

    public void setValorPendenteContaMesAno(int volumeFaturadoAgua) {
        this.valorPendenteContaMesAno = volumeFaturadoAgua;
    }

    public int getQuantidadeLigacoes() {
        return this.quantidadeLigacoes;
    }

    public void setQuantidadeLigacoes(int volumeCanceladoAgua) {
        this.quantidadeLigacoes = volumeCanceladoAgua;
    }

    public int getQuantidadeLigacoesAtivas() {
        return this.quantidadeLigacoesAtivas;
    }

    public void setQuantidadeLigacoesAtivas(int volumeIncluidoAgua) {
        this.quantidadeLigacoesAtivas = volumeIncluidoAgua;
    }

    public int getQuantidadeDocumentos() {
        return this.quantidadeDocumentos;
    }

    public void setQuantidadeDocumentos(int volumeFaturadoEsgoto) {
        this.quantidadeDocumentos = volumeFaturadoEsgoto;
    }

    public int getQuantidadeContasPendentes() {
        return this.quantidadeContasPendentes;
    }

    public void setQuantidadeContasPendentes(int volumeCanceladoEsgoto) {
        this.quantidadeContasPendentes = volumeCanceladoEsgoto;
    }

    public int getValorPendenteTotal() {
        return this.valorPendenteTotal;
    }

    public void setValorPendenteTotal(int volumeIncluidoEsgoto) {
        this.valorPendenteTotal = volumeIncluidoEsgoto;
    }

    public BigDecimal getValorPendenteConta() {
        return this.valorPendenteConta;
    }

    public void setValorPendenteConta(BigDecimal valorFaturadoAgua) {
        this.valorPendenteConta = valorFaturadoAgua;
    }

    public BigDecimal getValorPendenteServicos() {
        return this.valorPendenteServicos;
    }

    public void setValorPendenteServicos(BigDecimal valorCanceladoAgua) {
        this.valorPendenteServicos = valorCanceladoAgua;
    }

    public BigDecimal getValorPendenteParcelamento() {
        return this.valorPendenteParcelamento;
    }

    public void setValorPendenteParcelamento(BigDecimal valorIncluidoAgua) {
        this.valorPendenteParcelamento = valorIncluidoAgua;
    }

    public BigDecimal getQuantidadeLigacoesAtivasAgua() {
        return this.quantidadeLigacoesAtivasAgua;
    }

    public void setQuantidadeLigacoesAtivasAgua(BigDecimal valorFaturadoEsgoto) {
        this.quantidadeLigacoesAtivasAgua = valorFaturadoEsgoto;
    }

    public BigDecimal getQuantidadeLigacoesInativasAgua() {
        return this.quantidadeLigacoesInativasAgua;
    }

    public void setQuantidadeLigacoesInativasAgua(BigDecimal valorCanceladoEsgoto) {
        this.quantidadeLigacoesInativasAgua = valorCanceladoEsgoto;
    }

    public BigDecimal getQuantidadeLigacoesTotaisAgua() {
        return this.quantidadeLigacoesTotaisAgua;
    }

    public void setQuantidadeLigacoesTotaisAgua(BigDecimal valorIncluidoEsgoto) {
        this.quantidadeLigacoesTotaisAgua = valorIncluidoEsgoto;
    }

    public BigDecimal getQuantidadeContasRecebidas() {
        return this.quantidadeContasRecebidas;
    }

    public void setQuantidadeContasRecebidas(BigDecimal valorDocumentosFaturadosCredito) {
        this.quantidadeContasRecebidas = valorDocumentosFaturadosCredito;
    }

    public BigDecimal getValorArrecadado() {
        return this.valorArrecadado;
    }

    public void setValorArrecadado(BigDecimal valorCanceladoCredito) {
        this.valorArrecadado = valorCanceladoCredito;
    }

    public BigDecimal getValorArrecadadoMesAteVencimento() {
        return this.valorArrecadadoMesAteVencimento;
    }

    public void setValorArrecadadoMesAteVencimento(BigDecimal valorIncluidoCredito) {
        this.valorArrecadadoMesAteVencimento = valorIncluidoCredito;
    }

    public BigDecimal getValorArrecadadoMesAposVencimento() {
        return this.valorArrecadadoMesAposVencimento;
    }

    public void setValorArrecadadoMesAposVencimento(BigDecimal valorDocumentosFaturadosOutros) {
        this.valorArrecadadoMesAposVencimento = valorDocumentosFaturadosOutros;
    }

    public BigDecimal getValorArrecadadoMes2() {
        return this.valorArrecadadoMes2;
    }

    public void setValorArrecadadoMes2(BigDecimal valorCanceladoOutros) {
        this.valorArrecadadoMes2 = valorCanceladoOutros;
    }

    public BigDecimal getValorArrecadadoMes3() {
        return this.valorArrecadadoMes3;
    }

    public void setValorArrecadadoMes3(BigDecimal valorIncluidoOutros) {
        this.valorArrecadadoMes3 = valorIncluidoOutros;
    }

    public BigDecimal getValorArrecadadoParcelamento() {
        return this.valorArrecadadoParcelamento;
    }

    public void setValorArrecadadoParcelamento(BigDecimal valorAcrescimoImpontualidade) {
        this.valorArrecadadoParcelamento = valorAcrescimoImpontualidade;
    }

    public int getQuantidadeContasFaturamentoLiquido() {
        return this.quantidadeContasFaturamentoLiquido;
    }

    public void setQuantidadeContasFaturamentoLiquido(int quantidadeContasEmitidasMesAno) {
        this.quantidadeContasFaturamentoLiquido = quantidadeContasEmitidasMesAno;
    }

    public int getValorFaturamentoLiquido() {
        return this.valorFaturamentoLiquido;
    }

    public void setValorFaturamentoLiquido(int quantidadeContasRetificadasMesAno) {
        this.valorFaturamentoLiquido = quantidadeContasRetificadasMesAno;
    }

    public int getQuantidadeParcelmantos() {
        return this.quantidadeParcelmantos;
    }

    public void setQuantidadeParcelmantos(int quantidadeContasCanceladasMesAno) {
        this.quantidadeParcelmantos = quantidadeContasCanceladasMesAno;
    }

    public int getValorNegociado() {
        return this.valorNegociado;
    }

    public void setValorNegociado(int quantidadeContasIncluidasMesAno) {
        this.valorNegociado = quantidadeContasIncluidasMesAno;
    }

    public int getValorFinanciado() {
        return this.valorFinanciado;
    }

    public void setValorFinanciado(int quantidadeEconomiasFaturadasMesAno) {
        this.valorFinanciado = quantidadeEconomiasFaturadasMesAno;
    }

    public int getValorParcelado() {
        return this.valorParcelado;
    }

    public void setValorParcelado(int volumeFaturadoAguaMesAno) {
        this.valorParcelado = volumeFaturadoAguaMesAno;
    }

    public int getQuantidadeCortes() {
        return this.quantidadeCortes;
    }

    public void setQuantidadeCortes(int volumeCanceladoAguaMesAno) {
        this.quantidadeCortes = volumeCanceladoAguaMesAno;
    }

    public int getQuantidadeSupressoes() {
        return this.quantidadeSupressoes;
    }

    public void setQuantidadeSupressoes(int volumeIncluidoAguaMesAno) {
        this.quantidadeSupressoes = volumeIncluidoAguaMesAno;
    }

    public int getQuantidadeReligacoes() {
        return this.quantidadeReligacoes;
    }

    public void setQuantidadeReligacoes(int volumeFaturadoEsgotoMesAno) {
        this.quantidadeReligacoes = volumeFaturadoEsgotoMesAno;
    }

    public int getQuantidadeReestabelecimentos() {
        return this.quantidadeReestabelecimentos;
    }

    public void setQuantidadeReestabelecimentos(int volumeCanceladoEsgotoMesAno) {
        this.quantidadeReestabelecimentos = volumeCanceladoEsgotoMesAno;
    }

    public GSubcategoria getGerSubcategoria() {
        return this.gerSubcategoria;
    }

    public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
        this.gerSubcategoria = gerSubcategoria;
    }

    public GClienteTipo getGerClienteTipo() {
        return this.gerClienteTipo;
    }

    public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
        this.gerClienteTipo = gerClienteTipo;
    }

    public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
        return this.gerLigacaoAguaSituacao;
    }

    public void setGerLigacaoAguaSituacao(GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
    }

    public GQuadra getGerQuadra() {
        return this.gerQuadra;
    }

    public void setGerQuadra(GQuadra gerQuadra) {
        this.gerQuadra = gerQuadra;
    }

    public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
        return this.gerLigacaoEsgotoSituacao;
    }

    public void setGerLigacaoEsgotoSituacao(GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
    }

    public GGerenciaRegional getGerGerenciaRegional() {
        return this.gerGerenciaRegional;
    }

    public void setGerGerenciaRegional(GGerenciaRegional gerGerenciaRegional) {
        this.gerGerenciaRegional = gerGerenciaRegional;
    }

    public GSetorComercial getGerSetorComercial() {
        return this.gerSetorComercial;
    }

    public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
        this.gerSetorComercial = gerSetorComercial;
    }

    public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
        return this.gerLigacaoAguaPerfil;
    }

    public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
    }

    public GImovelPerfil getGerImovelPerfil() {
        return this.gerImovelPerfil;
    }

    public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
        this.gerImovelPerfil = gerImovelPerfil;
    }

    public GUnidadeNegocio getGerUnidadeNegocio() {
        return this.gerUnidadeNegocio;
    }

    public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
        this.gerUnidadeNegocio = gerUnidadeNegocio;
    }

    public GCreditoTipo getGerCreditoTipo() {
        return this.gerCreditoTipo;
    }

    public void setGerCreditoTipo(GCreditoTipo gerCreditoTipo) {
        this.gerCreditoTipo = gerCreditoTipo;
    }

    public GLocalidade getGerLocalidade() {
        return this.gerLocalidade;
    }

    public void setGerLocalidade(GLocalidade gerLocalidade) {
        this.gerLocalidade = gerLocalidade;
    }

    public GLocalidade getGerLocalidadeElo() {
        return this.gerLocalidadeElo;
    }

    public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
        this.gerLocalidadeElo = gerLocalidadeElo;
    }

    public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
        return this.gerLigacaoEsgotoPerfil;
    }

    public void setGerLigacaoEsgotoPerfil(GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    public GEsferaPoder getGerEsferaPoder() {
        return this.gerEsferaPoder;
    }

    public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
        this.gerEsferaPoder = gerEsferaPoder;
    }

    public GCategoria getGerCategoria() {
        return this.gerCategoria;
    }

    public void setGerCategoria(GCategoria gerCategoria) {
        this.gerCategoria = gerCategoria;
    }

    public GRota getGerRota() {
        return this.gerRota;
    }

    public void setGerRota(GRota gerRota) {
        this.gerRota = gerRota;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
