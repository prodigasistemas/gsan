package gcom.spcserasa.bean;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class DadosNegativacaoPorImovelHelper {

	private Integer idCliente;
	private Integer idImovel;
	private String cpfCliente;
	private String cnpjCliente;
	private List colecaoConta;
	private List colecaoGuias;
	private Integer qtdItensDebitoImovel;
	private BigDecimal totalDebitosImovel;
	private Integer idClienteRelacaoTipo;
	private Integer anoMesReferenciaInicioDebito;
	private Integer anoMesReferenciaFinalDebito;

	public DadosNegativacaoPorImovelHelper( 
			Integer idCliente,
			String cpfCliente,
			String cnpjCliente){
		this.idCliente = idCliente;
		this.cpfCliente = cpfCliente;
		this.cnpjCliente = cnpjCliente;
	}

	public DadosNegativacaoPorImovelHelper(){}

	public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof DadosNegativacaoPorImovelHelper)) {
            return false;
        }
        DadosNegativacaoPorImovelHelper castOther = (DadosNegativacaoPorImovelHelper) other;

        return new EqualsBuilder().append(this.getIdImovel(), castOther.getIdImovel())
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getIdImovel()).toHashCode();
    }

	public String getCnpjCliente() {
		return cnpjCliente;
	}
	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getQtdItensDebitoImovel() {
		return qtdItensDebitoImovel;
	}
	public void setQtdItensDebitoImovel(Integer qtdItensDebitoImovel) {
		this.qtdItensDebitoImovel = qtdItensDebitoImovel;
	}
	public BigDecimal getTotalDebitosImovel() {
		return totalDebitosImovel;
	}
	public void setTotalDebitosImovel(BigDecimal totalDebitosImovel) {
		this.totalDebitosImovel = totalDebitosImovel;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public List getColecaoConta() {
		return colecaoConta;
	}

	public void setColecaoConta(List colecaoConta) {
		this.colecaoConta = colecaoConta;
	}

	public List getColecaoGuias() {
		return colecaoGuias;
	}

	public void setColecaoGuias(List colecaoGuias) {
		this.colecaoGuias = colecaoGuias;
	}
	public Integer getIdClienteRelacaoTipo() {
		return idClienteRelacaoTipo;
	}
	public void setIdClienteRelacaoTipo(Integer idClienteRelacaoTipo) {
		this.idClienteRelacaoTipo = idClienteRelacaoTipo;
	}

	public Integer getAnoMesReferenciaInicioDebito() {
		return anoMesReferenciaInicioDebito;
	}

	public void setAnoMesReferenciaInicioDebito(Integer anoMesReferenciaInicioDebito) {
		this.anoMesReferenciaInicioDebito = anoMesReferenciaInicioDebito;
	}

	public Integer getAnoMesReferenciaFinalDebito() {
		return anoMesReferenciaFinalDebito;
	}

	public void setAnoMesReferenciaFinalDebito(Integer anoMesReferenciaFinalDebito) {
		this.anoMesReferenciaFinalDebito = anoMesReferenciaFinalDebito;
	}
}
