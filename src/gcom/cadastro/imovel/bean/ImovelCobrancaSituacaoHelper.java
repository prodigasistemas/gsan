package gcom.cadastro.imovel.bean;

import gcom.util.Util;

import java.util.Date;


public class ImovelCobrancaSituacaoHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String descricaoSituacaoCobranca;
    private Integer anoMesReferenciaInicio = 0;
    private Integer anoMesReferenciaFinal = 0;
	private Date dataImplantacaoCobranca;
	private Date dataRetiradaCobranca;
	private Integer idClienteAlvo;
	private String escritorioCobranca; 
	private String advogadoResponsavelCobranca;
	
	//Relatorio de Dados Complementares do Imóvel
	
	public String getAdvogadoResponsavelCobranca() {
		return advogadoResponsavelCobranca;
	}
	public void setAdvogadoResponsavelCobranca(String advogadoResponsavelCobranca) {
		this.advogadoResponsavelCobranca = advogadoResponsavelCobranca;
	}
	public Integer getAnoMesReferenciaFinal() {
		return anoMesReferenciaFinal;
	}
	public void setAnoMesReferenciaFinal(Integer anoMesReferenciaFinal) {
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
	}
	public Integer getAnoMesReferenciaInicio() {
		return anoMesReferenciaInicio;
	}
	public void setAnoMesReferenciaInicio(Integer anoMesReferenciaInicio) {
		this.anoMesReferenciaInicio = anoMesReferenciaInicio;
	}
	public Date getDataImplantacaoCobranca() {
		return dataImplantacaoCobranca;
	}
	public void setDataImplantacaoCobranca(Date dataImplantacaoCobranca) {
		this.dataImplantacaoCobranca = dataImplantacaoCobranca;
	}
	public Date getDataRetiradaCobranca() {
		return dataRetiradaCobranca;
	}
	public void setDataRetiradaCobranca(Date dataRetiradaCobranca) {
		this.dataRetiradaCobranca = dataRetiradaCobranca;
	}
	public String getDescricaoSituacaoCobranca() {
		return descricaoSituacaoCobranca;
	}
	public void setDescricaoSituacaoCobranca(String descricaoSituacaoCobranca) {
		this.descricaoSituacaoCobranca = descricaoSituacaoCobranca;
	}
	public String getEscritorioCobranca() {
		return escritorioCobranca;
	}
	public void setEscritorioCobranca(String escritorioCobranca) {
		this.escritorioCobranca = escritorioCobranca;
	}
	public Integer getIdClienteAlvo() {
		return idClienteAlvo;
	}
	public void setIdClienteAlvo(Integer idClienteAlvo) {
		this.idClienteAlvo = idClienteAlvo;
	}
	public String getAnoMesReferencia() {
		String retorno = "";
		
		if(this.anoMesReferenciaInicio != null && !this.anoMesReferenciaInicio.equals(new Integer(0))
				&& this.anoMesReferenciaFinal != null && !this.anoMesReferenciaFinal.equals(new Integer(0))){
			
			retorno = this.anoMesReferenciaInicio + " a " + this.anoMesReferenciaFinal;
		}
		
		return retorno;
	}
	public String getDataImplantacaoCobrancaString() {
		String retorno = "";
		if(this.dataImplantacaoCobranca != null){
			retorno = Util.formatarData(this.dataImplantacaoCobranca);
		}
		return retorno;
	}
	public String getDataRetiradaCobrancaString() {
		String retorno = "";
		if(this.dataRetiradaCobranca != null){
			retorno = Util.formatarData(this.dataRetiradaCobranca);
		}
		return retorno;
	}
	public String getIdClienteAlvoString() {
		String retorno = "";
		if(this.idClienteAlvo != null){
			retorno = this.idClienteAlvo.toString();
		}
		return retorno;
	}
	

}
