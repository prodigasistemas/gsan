package gcom.relatorio.arrecadacao;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class RelatorioAvisoBancarioPorContaCorrenteBean implements RelatorioBean {
	
	public static final int SITUACAO_NORMAL = 1;
	public static final int SITUACAO_MES_ANTERIOR = 2;
	public static final int SITUACAO_EM_TRANSITO_MES_ANTERIOR = 3;
	public static final int SITUACAO_EM_TRANSITO = 4;
	public static final int SITUACAO_ACERTO = 5;
	
	private Integer idAviso;
	private Date dataRealizada;
	
	private Integer idArrecadador;
	private String descricaoArrecadador;
	private Integer idArrecadacaoForma;
	private String descricaoArrecadacaoForma;
	private Integer idBanco;
	private String descricaoBanco;
	private Integer idConta;
	private String numeroConta;
	private Integer idAgencia;
	private String codigoAgencia; 
	
	private String descricaoAgencia;
	private Integer numeroContaContabil;
	private String numeroFone;
	private String numeroRamal;
	
	private String enderecoFormatado;
	
	private Date dataLancamento;
	private Integer sequencialAviso;
	private Integer numeroDocumento;
	private BigDecimal credito;
	private BigDecimal debito;
	private Date dataPagamento;
	private BigDecimal valorPagamentos;
	
	private Integer situacao;
	private Integer anoMesArrecadacao;
	private Integer anoMesArrecadacaoInformado;
	private Integer anoMesArrecadacaoAtual;

	public RelatorioAvisoBancarioPorContaCorrenteBean() { 
		GregorianCalendar atual = new GregorianCalendar();
		anoMesArrecadacaoAtual = Integer.parseInt(
				String.valueOf(atual.get(GregorianCalendar.YEAR)) + String.valueOf(atual.get(GregorianCalendar.MONTH)));
	}
	
	public String toString() {
		return idAviso + " :: " + dataPagamento + " , " + valorPagamentos;
	}
	
	public Integer getAnoMesDataCredito() {
		return (dataRealizada != null) ? Util.formataAnoMes(dataRealizada) : null;
	}
	
	public String getSituacaoAviso() {
		int situacao = getSituacao();
		
		if (situacao == SITUACAO_ACERTO) {
			return "Acerto do Aviso\n" + idArrecadador+"-"+descricaoArrecadador + "\n" + 
				" SEQ: " + sequencialAviso + " NUM: " + numeroDocumento + "\n" + 
				"de " + new SimpleDateFormat("dd/MM/yyyy").format(dataRealizada);
		}
		else if (situacao == SITUACAO_MES_ANTERIOR) {
			return "Mês anterior";
		}
		else if (situacao == SITUACAO_EM_TRANSITO_MES_ANTERIOR) {
			return "Em trânsito mês ant";
		}
		else if (situacao == SITUACAO_EM_TRANSITO) {
			return "Em trânsito";
		}
		else {
			return "";
		}
	}
	
	public void setSituacao() {
		if (idAviso == null || idAviso.equals(new Integer(0))) {
			situacao = SITUACAO_ACERTO;
		}
		else {
			Integer anoMesDataCredito = getAnoMesDataCredito();
			if (anoMesDataCredito == null) {
				situacao = SITUACAO_NORMAL;
			} 
			else if (anoMesDataCredito < anoMesArrecadacaoInformado) {
				situacao = SITUACAO_MES_ANTERIOR;
			} 
			else if (!anoMesArrecadacaoInformado.equals(anoMesArrecadacao) && 
					 anoMesDataCredito.equals(anoMesArrecadacaoInformado)) {
				situacao =  SITUACAO_EM_TRANSITO_MES_ANTERIOR;
			}
			else if (anoMesArrecadacao.equals(anoMesArrecadacaoInformado) && 
					anoMesDataCredito > anoMesArrecadacaoInformado) {
				situacao =  SITUACAO_EM_TRANSITO;
			}
			else {
				situacao =  SITUACAO_NORMAL;
			}
		}
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataRealizada() {
		return dataRealizada;
	}

	public void setDataRealizada(Date dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	public String getDescricaoArrecadacaoForma() {
		return descricaoArrecadacaoForma;
	}

	public void setDescricaoArrecadacaoForma(String descricaoArrecadacaoForma) {
		this.descricaoArrecadacaoForma = descricaoArrecadacaoForma;
	}

	public String getDescricaoArrecadador() {
		return descricaoArrecadador;
	}

	public void setDescricaoArrecadador(String descricaoArrecadador) {
		this.descricaoArrecadador = descricaoArrecadador;
	}

	public Integer getIdArrecadacaoForma() {
		return idArrecadacaoForma;
	}

	public void setIdArrecadacaoForma(Integer idArrecadacaoForma) {
		this.idArrecadacaoForma = idArrecadacaoForma;
	}

	public Integer getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(Integer idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public Integer getIdAviso() {
		return idAviso;
	}

	public void setIdAviso(Integer idAviso) {
		this.idAviso = idAviso;
	}

	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Integer getSequencialAviso() {
		return sequencialAviso;
	}

	public void setSequencialAviso(Integer sequencialAviso) {
		this.sequencialAviso = sequencialAviso;
	}
	
	public BigDecimal getValorPagamentos() {
		return valorPagamentos;
	}

	public void setValorPagamentos(BigDecimal valor) {
		this.valorPagamentos = valor;
	}

	public BigDecimal getCredito() {
		return credito;
	}

	public void setCredito(BigDecimal credito) {
		this.credito = credito;
	}

	public BigDecimal getDebito() {
		return debito;
	}

	public void setDebito(BigDecimal debito) {
		this.debito = debito;
	}
	
	public Integer getAnoMesArrecadacaoAtual() {
		return anoMesArrecadacaoAtual;
	}

	public void setAnoMesArrecadacaoAtual(Integer anoMesArrecadacaoAtual) {
		this.anoMesArrecadacaoAtual = anoMesArrecadacaoAtual;
	}

	public Integer getAnoMesArrecadacaoInformado() {
		return anoMesArrecadacaoInformado;
	}

	public void setAnoMesArrecadacaoInformado(Integer anoMesArrecadacaoInformado) {
		this.anoMesArrecadacaoInformado = anoMesArrecadacaoInformado;
	}
	
	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public String getDescricaoBanco() {
		return descricaoBanco;
	}

	public void setDescricaoBanco(String descricaoBanco) {
		this.descricaoBanco = descricaoBanco;
	}

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	public String getDescricaoAgencia() {
		return descricaoAgencia;
	}

	public void setDescricaoAgencia(String descricaoAgencia) {
		this.descricaoAgencia = descricaoAgencia;
	}

	public Integer getNumeroContaContabil() {
		return numeroContaContabil;
	}

	public void setNumeroContaContabil(Integer numeroContaContabil) {
		this.numeroContaContabil = numeroContaContabil;
	}

	public String getNumeroFone() {
		return numeroFone;
	}

	public void setNumeroFone(String numeroFone) {
		this.numeroFone = numeroFone;
	}

	public String getNumeroRamal() {
		return numeroRamal;
	}

	public void setNumeroRamal(String numeroRamal) {
		this.numeroRamal = numeroRamal;
	}

	public String getEnderecoFormatado() {
		return enderecoFormatado;
	}

	public void setEnderecoFormatado(String enderecoFormatado) {
		this.enderecoFormatado = enderecoFormatado;
	}

	public Integer getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Integer idAgencia) {
		this.idAgencia = idAgencia;
	}

	public Integer getAnoMesArrecadacao() {
		return anoMesArrecadacao;
	}

	public void setAnoMesArrecadacao(Integer anoMesArrecadacao) {
		this.anoMesArrecadacao = anoMesArrecadacao;
	}

}
