package gcom.faturamento.credito;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()
public class CreditoARealizar extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
		
	public static final int ATRIBUTOS_CREDITO_A_REALIZAR_INSERIR = 62; //Operacao.OPERACAO_CREDITO_A_REALIZAR_INSERIR
	public static final int ATRIBUTOS_CREDITO_A_REALIZAR_CANCELAR = 66; //Operacao.OPERACAO_CREDITO_A_REALIZAR_CANCELAR
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CREDITO_A_REALIZAR_INSERIR})
	private Integer id;
	private Date geracaoCredito;
	private Integer anoMesReferenciaContabil;
	private Integer anoMesCobrancaCredito;
	private BigDecimal valorResidualMesAnterior;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Short numeroLote;
	private Short numeroSubLote;
	private Date ultimaAlteracao;
	private Short numeroParcelaBonus;
	private BigDecimal valorResidualConcedidoMes;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CREDITO_A_REALIZAR_INSERIR,ATRIBUTOS_CREDITO_A_REALIZAR_CANCELAR})
	private Integer anoMesReferenciaCredito;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CREDITO_A_REALIZAR_INSERIR,ATRIBUTOS_CREDITO_A_REALIZAR_CANCELAR})
	private BigDecimal valorCredito;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CREDITO_A_REALIZAR_INSERIR,ATRIBUTOS_CREDITO_A_REALIZAR_CANCELAR})
	private Short numeroPrestacaoCredito;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CREDITO_A_REALIZAR_INSERIR})
	private Short numeroPrestacaoRealizada;

	@ControleAlteracao(value=FiltroCreditoARealizar.REGISTRO_ATENDIMENTO, funcionalidade={ATRIBUTOS_CREDITO_A_REALIZAR_INSERIR,ATRIBUTOS_CREDITO_A_REALIZAR_CANCELAR})
	private RegistroAtendimento registroAtendimento;

	private Imovel imovel;
	private Quadra quadra;
	private Localidade localidade;

	@ControleAlteracao(value=FiltroCreditoARealizar.ORDEM_SERVICO, funcionalidade={ATRIBUTOS_CREDITO_A_REALIZAR_INSERIR,ATRIBUTOS_CREDITO_A_REALIZAR_CANCELAR})
	private OrdemServico ordemServico;

	@ControleAlteracao(value=FiltroCreditoARealizar.CREDITO_TIPO, funcionalidade={ATRIBUTOS_CREDITO_A_REALIZAR_INSERIR,ATRIBUTOS_CREDITO_A_REALIZAR_CANCELAR})
	private gcom.faturamento.credito.CreditoTipo creditoTipo;

	private LancamentoItemContabil lancamentoItemContabil;

	@ControleAlteracao(value=FiltroCreditoARealizar.DEBITO_CREDITO_SITUACAO_ATUAL_, funcionalidade={ATRIBUTOS_CREDITO_A_REALIZAR_CANCELAR})
	private DebitoCreditoSituacao debitoCreditoSituacaoAtual;

	private DebitoCreditoSituacao debitoCreditoSituacaoAnterior;

	@ControleAlteracao(value=FiltroCreditoARealizar.CREDITO_ORIGEM, funcionalidade={ATRIBUTOS_CREDITO_A_REALIZAR_INSERIR,ATRIBUTOS_CREDITO_A_REALIZAR_CANCELAR})
	private gcom.faturamento.credito.CreditoOrigem creditoOrigem;

	private Parcelamento parcelamento;
	private DocumentoTipo documentoTipo;
	private Usuario usuario;
	
	@SuppressWarnings("rawtypes")
	private Set creditoARealizarCategoria;
	
	private CreditoARealizarGeral origem;
	private CreditoARealizarGeral creditoARealizarGeral;
	private Integer anoMesReferenciaPrestacao;
	private Integer numeroParcelasAntecipadas;
	
	@SuppressWarnings("rawtypes")
	public CreditoARealizar(Date geracaoCredito,
			Integer anoMesReferenciaCredito, Integer anoMesReferenciaContabil,
			Integer anoMesCobrancaCredito, BigDecimal valorResidualMesAnterior,
			BigDecimal valorCredito, Short numeroPrestacaoCredito,
			Short numeroPrestacaoRealizada, Integer codigoSetorComercial,
			Integer numeroQuadra, Short numeroLote, Short numeroSubLote,
			Date ultimaAlteracao, RegistroAtendimento registroAtendimento,
			Imovel imovel, OrdemServico ordemServico, Quadra quadra,
			Localidade localidade,
			gcom.faturamento.credito.CreditoTipo creditoTipo,
			LancamentoItemContabil lancamentoItemContabil,
			DebitoCreditoSituacao debitoCreditoSituacaoAtual,
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior,
			gcom.faturamento.credito.CreditoOrigem creditoOrigem,
			Parcelamento parcelamento, DocumentoTipo documentoTipo,
			Usuario usuario, Set creditoARealizarCategoria) {
		this.geracaoCredito = geracaoCredito;
		this.anoMesReferenciaCredito = anoMesReferenciaCredito;
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
		this.anoMesCobrancaCredito = anoMesCobrancaCredito;
		this.valorResidualMesAnterior = valorResidualMesAnterior;
		this.valorCredito = valorCredito;
		this.numeroPrestacaoCredito = numeroPrestacaoCredito;
		this.numeroPrestacaoRealizada = numeroPrestacaoRealizada;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.numeroLote = numeroLote;
		this.numeroSubLote = numeroSubLote;
		this.ultimaAlteracao = ultimaAlteracao;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.ordemServico = ordemServico;
		this.quadra = quadra;
		this.localidade = localidade;
		this.creditoTipo = creditoTipo;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
		this.creditoOrigem = creditoOrigem;
		this.parcelamento = parcelamento;
		this.documentoTipo = documentoTipo;
		this.usuario = usuario;
		this.creditoARealizarCategoria = creditoARealizarCategoria;
	}

	public CreditoARealizar() {
	}

	@SuppressWarnings("rawtypes")
	public CreditoARealizar(Date geracaoCredito,
			RegistroAtendimento registroAtendimento, Imovel imovel,
			OrdemServico ordemServico, Quadra quadra, Localidade localidade,
			LancamentoItemContabil lancamentoItemContabil,
			DebitoCreditoSituacao debitoCreditoSituacaoAtual,
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior,
			gcom.faturamento.credito.CreditoOrigem creditoOrigem,
			Parcelamento parcelamento, DocumentoTipo documentoTipo,
			Set creditoARealizarCategoria) {
		this.geracaoCredito = geracaoCredito;
		this.registroAtendimento = registroAtendimento;
		this.imovel = imovel;
		this.ordemServico = ordemServico;
		this.quadra = quadra;
		this.localidade = localidade;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
		this.creditoOrigem = creditoOrigem;
		this.parcelamento = parcelamento;
		this.documentoTipo = documentoTipo;
		this.creditoARealizarCategoria = creditoARealizarCategoria;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getGeracaoCredito() {
		return this.geracaoCredito;
	}

	public void setGeracaoCredito(Date geracaoCredito) {
		this.geracaoCredito = geracaoCredito;
	}

	public Integer getAnoMesReferenciaCredito() {
		return this.anoMesReferenciaCredito;
	}

	public void setAnoMesReferenciaCredito(Integer anoMesReferenciaCredito) {
		this.anoMesReferenciaCredito = anoMesReferenciaCredito;
	}

	public Integer getAnoMesReferenciaContabil() {
		return this.anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public Integer getAnoMesCobrancaCredito() {
		return this.anoMesCobrancaCredito;
	}

	public void setAnoMesCobrancaCredito(Integer anoMesCobrancaCredito) {
		this.anoMesCobrancaCredito = anoMesCobrancaCredito;
	}

	public BigDecimal getValorResidualMesAnterior() {
		return this.valorResidualMesAnterior;
	}

	public void setValorResidualMesAnterior(BigDecimal valorResidualMesAnterior) {
		this.valorResidualMesAnterior = valorResidualMesAnterior;
	}

	public BigDecimal getValorCredito() {
		return this.valorCredito;
	}

	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}

	public Short getNumeroPrestacaoCredito() {
		return this.numeroPrestacaoCredito;
	}

	public void setNumeroPrestacaoCredito(Short numeroPrestacaoCredito) {
		this.numeroPrestacaoCredito = numeroPrestacaoCredito;
	}

	public Short getNumeroPrestacaoRealizada() {
		return this.numeroPrestacaoRealizada;
	}

	public void setNumeroPrestacaoRealizada(Short numeroPrestacaoRealizada) {
		this.numeroPrestacaoRealizada = numeroPrestacaoRealizada;
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

	public Short getNumeroLote() {
		return this.numeroLote;
	}

	public void setNumeroLote(Short numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Short getNumeroSubLote() {
		return this.numeroSubLote;
	}

	public void setNumeroSubLote(Short numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
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

	public OrdemServico getOrdemServico() {
		return this.ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
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

	public gcom.faturamento.credito.CreditoTipo getCreditoTipo() {
		return this.creditoTipo;
	}

	public void setCreditoTipo(gcom.faturamento.credito.CreditoTipo creditoTipo) {
		this.creditoTipo = creditoTipo;
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return this.lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoAtual() {
		return this.debitoCreditoSituacaoAtual;
	}

	public void setDebitoCreditoSituacaoAtual(
			DebitoCreditoSituacao debitoCreditoSituacaoAtual) {
		this.debitoCreditoSituacaoAtual = debitoCreditoSituacaoAtual;
	}

	public DebitoCreditoSituacao getDebitoCreditoSituacaoAnterior() {
		return this.debitoCreditoSituacaoAnterior;
	}

	public void setDebitoCreditoSituacaoAnterior(
			DebitoCreditoSituacao debitoCreditoSituacaoAnterior) {
		this.debitoCreditoSituacaoAnterior = debitoCreditoSituacaoAnterior;
	}

	public gcom.faturamento.credito.CreditoOrigem getCreditoOrigem() {
		return this.creditoOrigem;
	}

	public void setCreditoOrigem(
			gcom.faturamento.credito.CreditoOrigem creditoOrigem) {
		this.creditoOrigem = creditoOrigem;
	}

	public Parcelamento getParcelamento() {
		return this.parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public DocumentoTipo getDocumentoTipo() {
		return this.documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	@SuppressWarnings("rawtypes")
	public Set getCreditoARealizarCategoria() {
		return this.creditoARealizarCategoria;
	}

	@SuppressWarnings("rawtypes")
	public void setCreditoARealizarCategoria(Set creditoARealizarCategoria) {
		this.creditoARealizarCategoria = creditoARealizarCategoria;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	
	public Integer getAnoMesReferenciaPrestacao() {
		return anoMesReferenciaPrestacao;
	}

	public void setAnoMesReferenciaPrestacao(Integer anoMesReferenciaPrestacao) {
		this.anoMesReferenciaPrestacao = anoMesReferenciaPrestacao;
	}

	public String getFormatarAnoMesCobrancaCredito() {

		String anoMesRecebido = "" + this.getAnoMesCobrancaCredito();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}
	public String getFormatarAnoMesReferenciaCredito() {

		String anoMesRecebido = "" + this.getAnoMesReferenciaCredito();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}
	public String getFormatarAnoMesReferenciaContabil() {

		String anoMesRecebido = "" + this.getAnoMesReferenciaContabil();
		String mes = anoMesRecebido.substring(4, 6);
		String ano = anoMesRecebido.substring(0, 4);
		String anoMesFormatado = mes + "/" + ano;

		return anoMesFormatado.toString();
	}
	
	public BigDecimal getValorTotal() {

		BigDecimal retornoDivisao = new BigDecimal("0.00");
		BigDecimal retornoMultiplicacao = new BigDecimal("0.00");
		BigDecimal retornoSubtracao = new BigDecimal("0.00");
		BigDecimal retorno = new BigDecimal("0.00");

		retornoDivisao = Util.dividirArredondando(this.valorCredito,new BigDecimal(numeroPrestacaoCredito));

		retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(
				numeroPrestacaoRealizada));

		retornoSubtracao = this.valorCredito.subtract(retornoMultiplicacao);
		if (valorResidualMesAnterior != null) {
			retorno = retornoSubtracao.add(valorResidualMesAnterior);
		}else{
            retorno = retornoSubtracao;
        }
		retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_UP);

		return retorno;
	}
	
 	public short getParcelasRestante(){
 		
 	   short retorno = Short.parseShort(""+
               (getNumeroPrestacaoCredito() -  getNumeroPrestacaoRealizada()));
       
 		return retorno;
 	}
 	
 	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroCreditoARealizar filtroCreditoARealizar = new FiltroCreditoARealizar();
		
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.REGISTRO_ATENDIMENTO);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.IMOVEL);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.ORDEM_SERVICO);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.QUADRA);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.LOCALIDADE);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.CREDITO_A_REALIZAR_CATEGORIA);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.CREDITO_TIPO);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.LANCAMENTO_ITEM_CONTABIL);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.DEBITO_CREDITO_SITUACAO_ATUAL_);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.DEBITO_CREDITO_SITUACAO_ANTERIOR_);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.CREDITO_ORIGEM);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.PARCELAMENTO);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.DOCUMENTO_TIPO);
		filtroCreditoARealizar.adicionarCaminhoParaCarregamentoEntidade(FiltroCreditoARealizar.USUARIO);
		
		filtroCreditoARealizar.adicionarParametro(
				new ParametroSimples(FiltroCreditoARealizar.ID, this.getId()));
		return filtroCreditoARealizar; 
	}

	public CreditoARealizarGeral getCreditoARealizarGeral() {
		return creditoARealizarGeral;
	}

	public void setCreditoARealizarGeral(CreditoARealizarGeral creditoARealizarGeral) {
		this.creditoARealizarGeral = creditoARealizarGeral;
	}

	public CreditoARealizarGeral getOrigem() {
		return origem;
	}

	public void setOrigem(CreditoARealizarGeral origem) {
		this.origem = origem;
	}

	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
		if (getCreditoTipo() != null){
			getCreditoTipo().initializeLazy();
		}
		if (getCreditoOrigem() != null){
			getCreditoOrigem().initializeLazy();
		}
		if (registroAtendimento != null){
			getRegistroAtendimento().initializeLazy();
		}
		if (ordemServico != null){
			getOrdemServico().initializeLazy();
		}
	}
	
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {
				"creditoTipo.descricao"
				, "valorCredito"
				};
			return atributos;		
	}
	
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []labels = {"Tipo Credito"
				, "Valor"
				};
			return labels;		
	}

	public Short getNumeroParcelaBonus() {
        return numeroParcelaBonus;
    }

    public void setNumeroParcelaBonus(Short numeroParcelaBonus) {
        this.numeroParcelaBonus = numeroParcelaBonus;
    }
   
    public short getParcelasRestanteComBonus(){
        
       short retorno = Short.parseShort(""+
               (getNumeroPrestacaoCredito() -  getNumeroPrestacaoRealizada()));
       
      if (getNumeroParcelaBonus() != null){
          retorno = Short.parseShort(""+ (retorno - getNumeroParcelaBonus().shortValue()));
      }
            
        return retorno;
    }
    
    public BigDecimal getValorTotalComBonus() {

        BigDecimal retornoDivisao = new BigDecimal("0.00");
        BigDecimal retornoMultiplicacao = new BigDecimal("0.00");
        BigDecimal retornoSubtracao = new BigDecimal("0.00");
        BigDecimal retorno = new BigDecimal("0.00");

        retornoDivisao = Util.dividirArredondando(this.valorCredito,new BigDecimal(numeroPrestacaoCredito));

        if (numeroParcelaBonus != null){
            retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(numeroPrestacaoRealizada + numeroParcelaBonus));
        }else{
            retornoMultiplicacao = retornoDivisao.multiply(new BigDecimal(numeroPrestacaoRealizada));
        }

        retornoSubtracao = this.valorCredito.subtract(retornoMultiplicacao);
        if (valorResidualMesAnterior != null) {
            retorno = retornoSubtracao.add(valorResidualMesAnterior);
        }else{
            retorno = retornoSubtracao;
        }
        retorno = retorno.setScale(2, BigDecimal.ROUND_HALF_UP);

        return retorno;
    }
    
    public short getNumeroPrestacaoCreditoMenosBonus() {
        short retorno = getNumeroPrestacaoCredito();
        
        if (getNumeroParcelaBonus() != null){
            retorno = Short.parseShort(""+ (retorno - getNumeroParcelaBonus().shortValue()));
        }
             
        return retorno;
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public BigDecimal getValorPrestacao(){
        
        //truncando o resultado com 2 casas decimais
        BigDecimal retornoDivisao = 
            this.getValorCredito().divide(new BigDecimal(this.getNumeroPrestacaoCredito()),2,BigDecimal.ROUND_DOWN);
       
        return retornoDivisao;
    }
	
	public BigDecimal getValorAntecipacaoParcela(int quantidadePrestacoes){
    	
    	return this.getValorPrestacao().multiply(BigDecimal.valueOf(quantidadePrestacoes));
    }

	public Integer getNumeroParcelasAntecipadas() {
		return numeroParcelasAntecipadas;
	}

	public void setNumeroParcelasAntecipadas(Integer numeroParcelasAntecipadas) {
		this.numeroParcelasAntecipadas = numeroParcelasAntecipadas;
	}

	public BigDecimal getValorResidualConcedidoMes() {
		return valorResidualConcedidoMes;
	}

	public void setValorResidualConcedidoMes(BigDecimal valorResidualConcedidoMes) {
		this.valorResidualConcedidoMes = valorResidualConcedidoMes;
	}

	public void reduzPrestacoesRealizadas() {
		numeroPrestacaoRealizada--;
	}

	public boolean possuiResiduo() {
		return valorResidualMesAnterior != null && valorResidualMesAnterior.compareTo(BigDecimal.ZERO) > 0;
	}

	public boolean isUltimaPrestacao() {
		return numeroPrestacaoRealizada() == numeroPrestacaoCredito() - numeroParcelaBonus();
	}

	public short numeroParcelaBonus() {
		return numeroParcelaBonus != null ? numeroParcelaBonus : 0;
	}
	
	private int numeroPrestacaoCredito() {
		return numeroPrestacaoCredito != null ? numeroPrestacaoCredito : 0;
	}
	
	private int numeroPrestacaoRealizada() {
		return numeroPrestacaoRealizada != null ? numeroPrestacaoRealizada : 0;
	}

	public BigDecimal calculaValorParcelaIntermediaria(boolean prefaturamento) {
		BigDecimal valorParcela = BigDecimal.ZERO;
		
		if (!isUltimaPrestacao()) {
			valorParcela = getValorCredito().divide(new BigDecimal(numeroPrestacaoCredito()), 2, BigDecimal.ROUND_DOWN);

			if (numeroPrestacaoRealizada() == numeroPrestacaoCredito() - numeroParcelaBonus() - 1 && prefaturamento) {
				BigDecimal valorMesVezesPrestacaoCredito = valorParcela.multiply(new BigDecimal(numeroPrestacaoCredito())).setScale(2);

				BigDecimal parte11 = valorParcela.add(getValorCredito());
				BigDecimal parte22 = parte11.subtract(valorMesVezesPrestacaoCredito);

				valorParcela = parte22;
			}
		}
		
		return valorParcela;
	}

	private BigDecimal calculaValorUltimaParcela() {
		BigDecimal valorParcela = getValorCredito().divide(new BigDecimal(numeroPrestacaoCredito()), 2, BigDecimal.ROUND_DOWN);
		
		BigDecimal valorMesVezesPrestacaoCredito = valorParcela.multiply(new BigDecimal(numeroPrestacaoCredito())).setScale(2);
		
		BigDecimal parte11 = valorParcela.add(getValorCredito());
		BigDecimal parte22 = parte11.subtract(valorMesVezesPrestacaoCredito);
		
		valorParcela = parte22;
		
		return valorParcela;
	}
	
	public void incrementaPrestacoesRealizadas(){
		if (!isUltimaPrestacao()){
			this.setNumeroPrestacaoRealizada((short) (numeroPrestacaoRealizada() + 1));			
		}
	}

	public void atualizaResiduo(BigDecimal valorCredito, BigDecimal valorTotalACobrar) {
		valorResidualMesAnterior = valorCredito.subtract(valorTotalACobrar);
		valorResidualMesAnterior = valorResidualMesAnterior.compareTo(BigDecimal.ZERO) >= 0 ? valorResidualMesAnterior : BigDecimal.ZERO;
	}

	public boolean concedidoNaReferenciaAtual(int referencia) {
		return anoMesReferenciaPrestacao != null && anoMesReferenciaPrestacao.intValue() == referencia;
	}
	
	public boolean nuncaFoiConcedido(){
		return anoMesReferenciaPrestacao == null;
	}

	public BigDecimal calculaCreditoOuResiduo() {
		return possuiResiduo() ? valorResidualMesAnterior : calculaValorUltimaParcela();
	}
	
	public BigDecimal getValorNaoConcedido() {
		BigDecimal valorConcedido =  getValorPrestacao().multiply(new BigDecimal(numeroPrestacaoRealizada()));
		
		return valorCredito.subtract(valorConcedido).add(valorResidualMesAnterior);
	}
	
	public boolean isCreditoBolsaAgua() {
		return this.getCreditoTipo().getId().equals(CreditoTipo.CREDITO_BOLSA_AGUA);
	}
}