package gcom.spcserasa.bean;

import gcom.util.ConstantesSistema;

import java.util.Date;

/**
 * @author Vivianne Sousa
 * @date 03/12/2010
 */
public class DadosNegativacaoRetornoHelper {

	private Date dataRetorno;
	private String descricaoRetornocodigo;
	private Short indicadorCorrecao;
	private String nomeCliente;
	
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public String getDescricaoRetornocodigo() {
		return descricaoRetornocodigo;
	}
	public void setDescricaoRetornocodigo(String descricaoRetornocodigo) {
		this.descricaoRetornocodigo = descricaoRetornocodigo;
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
	public String getIndicadorCorrecaoFormatado() {
		String retorno = "";
		
		if(indicadorCorrecao != null){
			
			if(indicadorCorrecao.equals(ConstantesSistema.SIM)){
				retorno = "Corrigido";
			}else if(indicadorCorrecao.equals(ConstantesSistema.NAO)){
				retorno = "Não Corrigido";
			}
		}
		return retorno;
	}
	
}
