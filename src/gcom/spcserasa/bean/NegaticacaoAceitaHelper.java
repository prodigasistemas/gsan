package gcom.spcserasa.bean;



public class NegaticacaoAceitaHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeNegativador;
    private Integer inclusoesNaoConfirmadas = 0;
	private Integer inclusoesConfirmadas = 0;
	
	
	public Integer getInclusoesConfirmadas() {
		return inclusoesConfirmadas;
	}
	public void setInclusoesConfirmadas(Integer inclusoesConfirmadas) {
		this.inclusoesConfirmadas = inclusoesConfirmadas;
	}
	public Integer getInclusoesNaoConfirmadas() {
		return inclusoesNaoConfirmadas;
	}
	public void setInclusoesNaoConfirmadas(Integer inclusoesNaoConfirmadas) {
		this.inclusoesNaoConfirmadas = inclusoesNaoConfirmadas;
	}
	public String getNomeNegativador() {
		return nomeNegativador;
	}
	public void setNomeNegativador(String nomeNegativador) {
		this.nomeNegativador = nomeNegativador;
	}
	public Integer getTotalInclusoes() {
		
		int inclusoesConfirmadas = 0;
		int inclusoesNaoConfirmadas = 0;
    	
    	if (getInclusoesConfirmadas() != null){
    		inclusoesConfirmadas = getInclusoesConfirmadas();
    	}
    	
    	if (getInclusoesNaoConfirmadas() != null){
    		inclusoesNaoConfirmadas = getInclusoesNaoConfirmadas();
    	}
    	
		 return inclusoesConfirmadas + inclusoesNaoConfirmadas;
	}
	
	
	
	
	
	
	

}
