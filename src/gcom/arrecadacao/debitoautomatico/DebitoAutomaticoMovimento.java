package gcom.arrecadacao.debitoautomatico;

import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.conta.ContaGeral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DebitoAutomaticoMovimento implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date dataVencimento;

    /** nullable persistent field */
    private BigDecimal valorDebito;

    /** nullable persistent field */
    private Date processamento;

    /** nullable persistent field */
    private Date envioBanco;

    /** nullable persistent field */
    private Date retornoBanco;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Integer numeroSequenciaArquivoEnviado;

    /** nullable persistent field */
    private Integer numeroSequenciaArquivoRecebido;

    /** persistent field */
    private gcom.arrecadacao.debitoautomatico.DebitoAutomatico debitoAutomatico;

    /** persistent field */
    private FaturamentoGrupo faturamentoGrupo;

    /** persistent field */
    private gcom.arrecadacao.debitoautomatico.DebitoAutomaticoRetornoCodigo debitoAutomaticoRetornoCodigo;

    /** persistent field */
    private ContaGeral contaGeral;

    /** full constructor */
    public DebitoAutomaticoMovimento(Date dataVencimento, BigDecimal valorDebito, Date processamento, 
    		Date envioBanco, Date retornoBanco, Date ultimaAlteracao, Integer numeroSequenciaArquivoEnviado, 
    		Integer numeroSequenciaArquivoRecebido, 
    		gcom.arrecadacao.debitoautomatico.DebitoAutomatico debitoAutomatico, 
    		FaturamentoGrupo faturamentoGrupo, 
    		gcom.arrecadacao.debitoautomatico.DebitoAutomaticoRetornoCodigo debitoAutomaticoRetornoCodigo, 
    		ContaGeral contaGeral) {
    	
        this.dataVencimento = dataVencimento;
        this.valorDebito = valorDebito;
        this.processamento = processamento;
        this.envioBanco = envioBanco;
        this.retornoBanco = retornoBanco;
        this.ultimaAlteracao = ultimaAlteracao;
        this.numeroSequenciaArquivoEnviado = numeroSequenciaArquivoEnviado;
        this.numeroSequenciaArquivoRecebido = numeroSequenciaArquivoRecebido;
        this.debitoAutomatico = debitoAutomatico;
        this.faturamentoGrupo = faturamentoGrupo;
        this.debitoAutomaticoRetornoCodigo = debitoAutomaticoRetornoCodigo;
        this.contaGeral = contaGeral;
    }

    /** default constructor */
    public DebitoAutomaticoMovimento() {
    }

    /** minimal constructor */
    public DebitoAutomaticoMovimento(gcom.arrecadacao.debitoautomatico.DebitoAutomatico debitoAutomatico, 
    		FaturamentoGrupo faturamentoGrupo, 
    		gcom.arrecadacao.debitoautomatico.DebitoAutomaticoRetornoCodigo debitoAutomaticoRetornoCodigo,
    		ContaGeral contaGeral) {
    	
        this.debitoAutomatico = debitoAutomatico;
        this.faturamentoGrupo = faturamentoGrupo;
        this.debitoAutomaticoRetornoCodigo = debitoAutomaticoRetornoCodigo;
        this.contaGeral = contaGeral;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataVencimento() {
        return this.dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValorDebito() {
        return this.valorDebito;
    }

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }

    public Date getProcessamento() {
        return this.processamento;
    }

    public void setProcessamento(Date processamento) {
        this.processamento = processamento;
    }

    public Date getEnvioBanco() {
        return this.envioBanco;
    }

    public void setEnvioBanco(Date envioBanco) {
        this.envioBanco = envioBanco;
    }

    public Date getRetornoBanco() {
        return this.retornoBanco;
    }

    public void setRetornoBanco(Date retornoBanco) {
        this.retornoBanco = retornoBanco;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getNumeroSequenciaArquivoEnviado() {
        return this.numeroSequenciaArquivoEnviado;
    }

    public void setNumeroSequenciaArquivoEnviado(Integer numeroSequenciaArquivoEnviado) {
        this.numeroSequenciaArquivoEnviado = numeroSequenciaArquivoEnviado;
    }

    public Integer getNumeroSequenciaArquivoRecebido() {
        return this.numeroSequenciaArquivoRecebido;
    }

    public void setNumeroSequenciaArquivoRecebido(Integer numeroSequenciaArquivoRecebido) {
        this.numeroSequenciaArquivoRecebido = numeroSequenciaArquivoRecebido;
    }

    public gcom.arrecadacao.debitoautomatico.DebitoAutomatico getDebitoAutomatico() {
        return this.debitoAutomatico;
    }

    public void setDebitoAutomatico(gcom.arrecadacao.debitoautomatico.DebitoAutomatico debitoAutomatico) {
        this.debitoAutomatico = debitoAutomatico;
    }

    public FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public gcom.arrecadacao.debitoautomatico.DebitoAutomaticoRetornoCodigo getDebitoAutomaticoRetornoCodigo() {
        return this.debitoAutomaticoRetornoCodigo;
    }

    public void setDebitoAutomaticoRetornoCodigo(gcom.arrecadacao.debitoautomatico.DebitoAutomaticoRetornoCodigo debitoAutomaticoRetornoCodigo) {
        this.debitoAutomaticoRetornoCodigo = debitoAutomaticoRetornoCodigo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

}
