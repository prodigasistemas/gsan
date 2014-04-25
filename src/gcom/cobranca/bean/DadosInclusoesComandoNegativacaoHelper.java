package gcom.cobranca.bean;

import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 31/10/2007
 */

public class DadosInclusoesComandoNegativacaoHelper {
	
	private static final long serialVersionUID = 1L;
	
    private String nomeCliente;
    private Integer quantidadeInclusoes; 
    private BigDecimal valorTotalDebito;
    private Integer quantidadeItensIncluidos;
    private Integer idImovel;
    private String numeroCpf;
    private String numeroCnpj;
    private BigDecimal valorDebito;
    private String descricaoCobrancaSituacao;
    private Date dataSituacaoDebito;
    private Short indicadorAceito;
    private Short indicadorCorrecao;
    private Integer codigoExclusaoTipo;
    private String nomeUsuario;
	
	
	public DadosInclusoesComandoNegativacaoHelper() {
	}

	public DadosInclusoesComandoNegativacaoHelper(
            String nomeCliente,
            Integer quantidadeInclusoes, 
            BigDecimal valorTotalDebito,
            Integer quantidadeItensIncluidos,
            Integer idImovel,
            String numeroCpf,
            String numeroCnpj,
            BigDecimal valorDebito,
            String descricaoCobrancaSituacao,
            Date dataSituacaoDebito,
            Short indicadorAceito,
            Short indicadorCorrecao,
            Integer codigoExclusaoTipo,
            String nomeUsuario
			) {
		
            this.nomeCliente = nomeCliente;
            this.quantidadeInclusoes =  quantidadeInclusoes;
            this.valorTotalDebito = valorTotalDebito;
            this.quantidadeItensIncluidos = quantidadeItensIncluidos;
            this.idImovel = idImovel;
            this.numeroCpf = numeroCpf;
            this.numeroCnpj = numeroCnpj;
            this.valorDebito = valorDebito;
            this.descricaoCobrancaSituacao = descricaoCobrancaSituacao;
            this.dataSituacaoDebito = dataSituacaoDebito;
            this.indicadorAceito = indicadorAceito;
            this.indicadorCorrecao = indicadorCorrecao;
            this.codigoExclusaoTipo = codigoExclusaoTipo;
            this.nomeUsuario = nomeUsuario;
		
		
	}

    public Integer getCodigoExclusaoTipo() {
        return codigoExclusaoTipo;
    }

    public void setCodigoExclusaoTipo(Integer codigoExclusaoTipo) {
        this.codigoExclusaoTipo = codigoExclusaoTipo;
    }

    public Date getDataSituacaoDebito() {
        return dataSituacaoDebito;
    }

    public void setDataSituacaoDebito(Date dataSituacaoDebito) {
        this.dataSituacaoDebito = dataSituacaoDebito;
    }

    public String getDescricaoCobrancaSituacao() {
        return descricaoCobrancaSituacao;
    }

    public void setDescricaoCobrancaSituacao(String descricaoCobrancaSituacao) {
        this.descricaoCobrancaSituacao = descricaoCobrancaSituacao;
    }

    public Integer getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(Integer idImovel) {
        this.idImovel = idImovel;
    }

    public Short getIndicadorAceito() {
        return indicadorAceito;
    }

    public void setIndicadorAceito(Short indicadorAceito) {
        this.indicadorAceito = indicadorAceito;
    }

    public Short getIndicadorCorrecao() {
        return indicadorCorrecao;
    }

    public void setIndicadorCorrecao(Short indicadorCorrecao) {
        this.indicadorCorrecao = indicadorCorrecao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNumeroCnpj() {
        return numeroCnpj;
    }

    public void setNumeroCnpj(String numeroCnpj) {
        this.numeroCnpj = numeroCnpj;
    }

    public String getNumeroCpf() {
        return numeroCpf;
    }

    public void setNumeroCpf(String numeroCpf) {
        this.numeroCpf = numeroCpf;
    }

    public Integer getQuantidadeInclusoes() {
        return quantidadeInclusoes;
    }

    public void setQuantidadeInclusoes(Integer quantidadeInclusoes) {
        this.quantidadeInclusoes = quantidadeInclusoes;
    }

    public Integer getQuantidadeItensIncluidos() {
        return quantidadeItensIncluidos;
    }

    public void setQuantidadeItensIncluidos(Integer quantidadeItensIncluidos) {
        this.quantidadeItensIncluidos = quantidadeItensIncluidos;
    }

    public BigDecimal getValorDebito() {
        return valorDebito;
    }

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }

    public BigDecimal getValorTotalDebito() {
        return valorTotalDebito;
    }

    public void setValorTotalDebito(BigDecimal valorTotalDebito) {
        this.valorTotalDebito = valorTotalDebito;
    }

    public String getInclusaoAceita() {
        String inclusaoAceita = "NÃO";
        
        if(getIndicadorAceito()!= null && getIndicadorAceito().equals(ConstantesSistema.SIM)){
            inclusaoAceita = "SIM";
        }
        
        return inclusaoAceita;
    }
	
    
    public String getInclusaoCorrigida() {
        String inclusaoCorrigida = "";
        
        if(getIndicadorAceito()!= null && getIndicadorAceito().equals(ConstantesSistema.NAO)){
            if(getIndicadorCorrecao()!= null && getIndicadorCorrecao().equals(ConstantesSistema.SIM)){
                inclusaoCorrigida = "SIM";
            }else{
                inclusaoCorrigida = "NÃO";
            }
        }
        
        return inclusaoCorrigida;
    }
    
    public String getSituacaoInclusao() {
        String situacaoInclusao = "";
        
        if(getIndicadorAceito()!= null && getIndicadorAceito().equals(ConstantesSistema.NAO)){
        	 situacaoInclusao = "Rejeitada";       	
        }else if (getIndicadorAceito()!= null && getIndicadorAceito().equals(ConstantesSistema.SIM)){ 
          if(getCodigoExclusaoTipo() == null){
              situacaoInclusao = "Aceita";
          }else if(getCodigoExclusaoTipo().equals(1)){
              situacaoInclusao = "Excluída";
          }else if(getCodigoExclusaoTipo().equals(2)){
              situacaoInclusao = "Excluída-On Line";
          }   
        }
        
        return situacaoInclusao;
    }
    
    public String getCpfCnpj() {
        String cpfCnpj = "";
        if(getNumeroCpf() != null){
        	cpfCnpj = Util.formatarCPFApresentacao(getNumeroCpf());
        }else if(getNumeroCnpj() != null){
        	cpfCnpj = getNumeroCnpj();
        }
        return cpfCnpj;
    }
    
    
}
