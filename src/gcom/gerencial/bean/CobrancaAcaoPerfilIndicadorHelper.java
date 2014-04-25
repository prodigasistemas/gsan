package gcom.gerencial.bean;

import gcom.cobranca.ResumoCobrancaAcao;
import gcom.util.Util;

import java.math.BigDecimal;

public class CobrancaAcaoPerfilIndicadorHelper {

	private Integer id;
	private String descricao;
	private Integer quantidadeDocumento;
	private BigDecimal valorDocumento;
	private Short indicadorLimite;
	
	public CobrancaAcaoPerfilIndicadorHelper(Integer id, String descricao, Short indicadorLimite,
			Integer quantidadeDocumento, BigDecimal valorDocumento) {
		this.id = id;
		this.descricao = descricao;
		this.indicadorLimite = indicadorLimite;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorDocumento = valorDocumento;
		
	}

	public String getPercentualValor(String valorTotal) {
		String valorPercentual = Util.calcularPercentual(getValorDocumento().toString() , valorTotal);
		valorPercentual = Util.formatarMoedaReal(new BigDecimal(valorPercentual));
		return valorPercentual;
	}
	
	public String getPercentualQuantidade(String quantidadeTotal) {
		String quantidadePercentual = Util.calcularPercentual(getQuantidadeDocumento().toString(), quantidadeTotal);
		quantidadePercentual = Util.formatarMoedaReal(new BigDecimal(quantidadePercentual));
		return quantidadePercentual;
	}
	
	/**
	 * @return Retorna o campo indicadorLimite.
	 */
	public Short getIndicadorLimite() {
		return indicadorLimite;
	}
	/**
	 * @param indicadorLimite O indicadorLimite a ser setado.
	 */
	public void setIndicadorLimite(Short indicadorLimite) {
		this.indicadorLimite = indicadorLimite;
	}
	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return Retorna o campo quantidadeDocumento.
	 */
	public Integer getQuantidadeDocumento() {
		return quantidadeDocumento;
	}
	/**
	 * @param quantidadeDocumento O quantidadeDocumento a ser setado.
	 */
	public void setQuantidadeDocumento(Integer quantidadeDocumento) {
		this.quantidadeDocumento = quantidadeDocumento;
	}
	/**
	 * @return Retorna o campo valorDocumento.
	 */
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}
	/**
	 * @param valorDocumento O valorDocumento a ser setado.
	 */
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	
	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricaoIndicador(){
		String	descricao = "";
		if(getIndicadorLimite() != null && !getIndicadorLimite().equals("")){
			if(getIndicadorLimite() == ResumoCobrancaAcao.INDICADOR_ACIMA){
			  descricao = "ACIMA";  	
			}else if(getIndicadorLimite() == ResumoCobrancaAcao.INDICADOR_ABAIXO){
			  descricao = "ABAIXO";	
			}
		}
		return descricao;
	}
}
