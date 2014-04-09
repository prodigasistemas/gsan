package gcom.spcserasa.bean;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Classe responsável por ajudar o caso de uso [UC0671] Gerar Movimento de Inclusão Negativação 
 *
 * @author Marcio Roberto
 * @date 21/11/2007
 */
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

	/**
	 * Construtor  
	 * @param idCliente
	 * @param cpfCliente
	 * @param cnpjCliente
	 */
	public DadosNegativacaoPorImovelHelper( 
			Integer idCliente,
			String cpfCliente,
			String cnpjCliente){
		this.idCliente = idCliente;
		this.cpfCliente = cpfCliente;
		this.cnpjCliente = cnpjCliente;
	}
	/**
	 * Construtor  
	 * @param idCliente
	 * @param cpfCliente
	 * @param cnpjCliente
	 */
	public DadosNegativacaoPorImovelHelper(){ 
	}
	
	
    /**
     *  Descrição do método>>
     * 
     * @param other
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
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

   
   /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     */
    public int hashCode() {
        return new HashCodeBuilder().append(getIdImovel())
                .toHashCode();
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
	/**
	 * @return Retorna o campo colecaoConta.
	 */
	public List getColecaoConta() {
		return colecaoConta;
	}
	/**
	 * @param colecaoConta O colecaoConta a ser setado.
	 */
	public void setColecaoConta(List colecaoConta) {
		this.colecaoConta = colecaoConta;
	}
	/**
	 * @return Retorna o campo colecaoGuias.
	 */
	public List getColecaoGuias() {
		return colecaoGuias;
	}
	/**
	 * @param colecaoGuias O colecaoGuias a ser setado.
	 */
	public void setColecaoGuias(List colecaoGuias) {
		this.colecaoGuias = colecaoGuias;
	}
	public Integer getIdClienteRelacaoTipo() {
		return idClienteRelacaoTipo;
	}
	public void setIdClienteRelacaoTipo(Integer idClienteRelacaoTipo) {
		this.idClienteRelacaoTipo = idClienteRelacaoTipo;
	}
	
}
