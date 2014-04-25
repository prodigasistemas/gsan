package gcom.arrecadacao;

import gcom.arrecadacao.banco.ContaBancaria;
import gcom.cadastro.cliente.Cliente;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ArrecadadorContrato implements Serializable {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private String numeroContrato;

    /** nullable persistent field */
    private Date dataContratoInicio;

    /** nullable persistent field */
    private String codigoConvenio;

    /** nullable persistent field */
    private Date dataContratoFim;

    /** nullable persistent field */
    private Date dataContratoEncerramento;

    /** nullable persistent field */
    private Short indicadorCobrancaIss;

    /** nullable persistent field */
    private Integer numeroSequecialArquivoRetornoCodigoBarras;

    /** nullable persistent field */
    private Integer numeroSequencialArquivoRetornoDebitoAutomatico;

    /** nullable persistent field */
    private Integer numeroSequencialArquivoEnvioDebitoAutomatico;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private ContaBancaria contaBancariaDepositoTarifa;

    /** persistent field */
    private ContaBancaria contaBancariaDepositoArrecadacao;

    /** persistent field */
    private gcom.arrecadacao.Arrecadador arrecadador;

    /** persistent field */
    private gcom.arrecadacao.ContratoMotivoCancelamento contratoMotivoCancelamento;

    /** persistent field */
    private Cliente cliente;
    
    private String descricaoEmail;
    
    /** nullable persistent field */
    private Integer numeroSequencialArquivoRetornoFichaCompensacao;
    
    private Short tamanhoMaximoIdentificacaoImovel;

    public Short getTamanhoMaximoIdentificacaoImovel() {
		return tamanhoMaximoIdentificacaoImovel;
	}

	public void setTamanhoMaximoIdentificacaoImovel(
			Short tamanhoMaximoIdentificacaoImovel) {
		this.tamanhoMaximoIdentificacaoImovel = tamanhoMaximoIdentificacaoImovel;
	}

	/** full constructor */
    public ArrecadadorContrato(String numeroContrato, Date dataContratoInicio, String codigoConvenio, Date dataContratoFim, Date dataContratoEncerramento, Short indicadorCobrancaIss, Integer numeroSequecialArquivoRetornoCodigoBarras, Integer numeroSequencialArquivoRetornoDebitoAutomatico, Integer numeroSequencialArquivoEnvioDebitoAutomatico, Date ultimaAlteracao, ContaBancaria contaBancariaDepositoTarifa, ContaBancaria contaBancariaDepositoArrecadacao, gcom.arrecadacao.Arrecadador arrecadador, gcom.arrecadacao.ContratoMotivoCancelamento contratoMotivoCancelamento, Cliente cliente) {
        this.numeroContrato = numeroContrato;
        this.dataContratoInicio = dataContratoInicio;
        this.codigoConvenio = codigoConvenio;
        this.dataContratoFim = dataContratoFim;
        this.dataContratoEncerramento = dataContratoEncerramento;
        this.indicadorCobrancaIss = indicadorCobrancaIss;
        this.numeroSequecialArquivoRetornoCodigoBarras = numeroSequecialArquivoRetornoCodigoBarras;
        this.numeroSequencialArquivoRetornoDebitoAutomatico = numeroSequencialArquivoRetornoDebitoAutomatico;
        this.numeroSequencialArquivoEnvioDebitoAutomatico = numeroSequencialArquivoEnvioDebitoAutomatico;
        this.ultimaAlteracao = ultimaAlteracao;
        this.contaBancariaDepositoTarifa = contaBancariaDepositoTarifa;
        this.contaBancariaDepositoArrecadacao = contaBancariaDepositoArrecadacao;
        this.arrecadador = arrecadador;
        this.contratoMotivoCancelamento = contratoMotivoCancelamento;
        this.cliente = cliente;
    }

    /** default constructor */
    public ArrecadadorContrato() {
    }

    /** minimal constructor */
    public ArrecadadorContrato(String numeroContrato, ContaBancaria contaBancariaDepositoTarifa, ContaBancaria contaBancariaDepositoArrecadacao, gcom.arrecadacao.Arrecadador arrecadador, gcom.arrecadacao.ContratoMotivoCancelamento contratoMotivoCancelamento, Cliente cliente) {
        this.numeroContrato = numeroContrato;
        this.contaBancariaDepositoTarifa = contaBancariaDepositoTarifa;
        this.contaBancariaDepositoArrecadacao = contaBancariaDepositoArrecadacao;
        this.arrecadador = arrecadador;
        this.contratoMotivoCancelamento = contratoMotivoCancelamento;
        this.cliente = cliente;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroContrato() {
        return this.numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Date getDataContratoInicio() {
        return this.dataContratoInicio;
    }

    public void setDataContratoInicio(Date dataContratoInicio) {
        this.dataContratoInicio = dataContratoInicio;
    }

    public String getCodigoConvenio() {
        return this.codigoConvenio;
    }

    public void setCodigoConvenio(String codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }

    public Date getDataContratoFim() {
        return this.dataContratoFim;
    }

    public void setDataContratoFim(Date dataContratoFim) {
        this.dataContratoFim = dataContratoFim;
    }

    public Date getDataContratoEncerramento() {
        return this.dataContratoEncerramento;
    }

    public void setDataContratoEncerramento(Date dataContratoEncerramento) {
        this.dataContratoEncerramento = dataContratoEncerramento;
    }

    public Short getIndicadorCobrancaIss() {
        return this.indicadorCobrancaIss;
    }

    public void setIndicadorCobrancaIss(Short indicadorCobrancaIss) {
        this.indicadorCobrancaIss = indicadorCobrancaIss;
    }

    public Integer getNumeroSequecialArquivoRetornoCodigoBarras() {
        return this.numeroSequecialArquivoRetornoCodigoBarras;
    }

    public void setNumeroSequecialArquivoRetornoCodigoBarras(Integer numeroSequecialArquivoRetornoCodigoBarras) {
        this.numeroSequecialArquivoRetornoCodigoBarras = numeroSequecialArquivoRetornoCodigoBarras;
    }

    public Integer getNumeroSequencialArquivoRetornoDebitoAutomatico() {
        return this.numeroSequencialArquivoRetornoDebitoAutomatico;
    }

    public void setNumeroSequencialArquivoRetornoDebitoAutomatico(Integer numeroSequencialArquivoRetornoDebitoAutomatico) {
        this.numeroSequencialArquivoRetornoDebitoAutomatico = numeroSequencialArquivoRetornoDebitoAutomatico;
    }

    public Integer getNumeroSequencialArquivoEnvioDebitoAutomatico() {
        return this.numeroSequencialArquivoEnvioDebitoAutomatico;
    }

    public void setNumeroSequencialArquivoEnvioDebitoAutomatico(Integer numeroSequencialArquivoEnvioDebitoAutomatico) {
        this.numeroSequencialArquivoEnvioDebitoAutomatico = numeroSequencialArquivoEnvioDebitoAutomatico;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public ContaBancaria getContaBancariaDepositoTarifa() {
        return this.contaBancariaDepositoTarifa;
    }

    public void setContaBancariaDepositoTarifa(ContaBancaria contaBancariaDepositoTarifa) {
        this.contaBancariaDepositoTarifa = contaBancariaDepositoTarifa;
    }

    public ContaBancaria getContaBancariaDepositoArrecadacao() {
        return this.contaBancariaDepositoArrecadacao;
    }

    public void setContaBancariaDepositoArrecadacao(ContaBancaria contaBancariaDepositoArrecadacao) {
        this.contaBancariaDepositoArrecadacao = contaBancariaDepositoArrecadacao;
    }

    public gcom.arrecadacao.Arrecadador getArrecadador() {
        return this.arrecadador;
    }

    public void setArrecadador(gcom.arrecadacao.Arrecadador arrecadador) {
        this.arrecadador = arrecadador;
    }

    public gcom.arrecadacao.ContratoMotivoCancelamento getContratoMotivoCancelamento() {
        return this.contratoMotivoCancelamento;
    }

    public void setContratoMotivoCancelamento(gcom.arrecadacao.ContratoMotivoCancelamento contratoMotivoCancelamento) {
        this.contratoMotivoCancelamento = contratoMotivoCancelamento;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    

    public String getDescricaoEmail() {
		return descricaoEmail;
	}

	public void setDescricaoEmail(String descricaoEmail) {
		this.descricaoEmail = descricaoEmail;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Integer getNumeroSequencialArquivoRetornoFichaCompensacao() {
		return numeroSequencialArquivoRetornoFichaCompensacao;
	}

	public void setNumeroSequencialArquivoRetornoFichaCompensacao(
			Integer numeroSequencialArquivoRetornoFichaCompensacao) {
		this.numeroSequencialArquivoRetornoFichaCompensacao = numeroSequencialArquivoRetornoFichaCompensacao;
	}

}
