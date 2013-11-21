package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * @author Pamela Gatinho
 * @date 08/09/2011
 */
public class RelatorioLeiturasRealizadasBean implements RelatorioBean{
	
	private String anoMesReferencia;
	private String grupo;
	private String empresa;
	private String localidade;
	private String situacaoLeitura; 
	private Integer qtdRotasEmCampo;
	private Integer qtdRotasTransmitidas;
	private Integer qtdRotasFinalizadasPeloUsuario;
	private Integer qtdTotalLocal;
	private Integer qtdImoveisEmCampo;
	private Integer qtdImoveisTransmitidosDeRotaParcial;
	
	public RelatorioLeiturasRealizadasBean() {
		
	}
	
	public RelatorioLeiturasRealizadasBean(
			String anoMesReferencia, String grupo,
			String empresa, String localidade, String situacaoLeitura,
			Integer qtdRotasEmCampo, Integer qtdRotasTransmitidas,
			Integer qtdRotasFinalizadasPeloUsuario, Integer qtdTotalLocal,
			Integer qtdImoveisEmCampo, Integer qtdImoveisTransmitidosDeRotaParcial) {
		
		
		this.anoMesReferencia = anoMesReferencia;
		this.grupo = grupo;
		this.empresa = empresa;
		this.localidade = localidade;
		this.situacaoLeitura =situacaoLeitura;
		this.qtdRotasEmCampo = qtdRotasEmCampo;
		this.qtdRotasTransmitidas = qtdRotasTransmitidas;
		this.qtdRotasFinalizadasPeloUsuario = qtdRotasFinalizadasPeloUsuario;
		this.qtdTotalLocal = qtdTotalLocal;
		this.qtdImoveisEmCampo = qtdImoveisEmCampo;
		this.qtdImoveisTransmitidosDeRotaParcial = qtdImoveisTransmitidosDeRotaParcial;
		
	}
	
	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public Integer getQtdRotasEmCampo() {
		return qtdRotasEmCampo;
	}
	public void setQtdRotasEmCampo(Integer qtdRotasEmCampo) {
		this.qtdRotasEmCampo = qtdRotasEmCampo;
	}
	public Integer getQtdRotasTransmitidas() {
		return qtdRotasTransmitidas;
	}
	public void setQtdRotasTransmitidas(Integer qtdRotasTransmitidas) {
		this.qtdRotasTransmitidas = qtdRotasTransmitidas;
	}
	public Integer getQtdRotasFinalizadasPeloUsuario() {
		return qtdRotasFinalizadasPeloUsuario;
	}
	public void setQtdRotasFinalizadasPeloUsuario(
			Integer qtdRotasFinalizadasPeloUsuario) {
		this.qtdRotasFinalizadasPeloUsuario = qtdRotasFinalizadasPeloUsuario;
	}
	public Integer getQtdTotalLocal() {
		return qtdTotalLocal;
	}
	public void setQtdTotalLocal(Integer qtdTotalLocal) {
		this.qtdTotalLocal = qtdTotalLocal;
	}
	public Integer getQtdImoveisEmCampo() {
		return qtdImoveisEmCampo;
	}
	public void setQtdImoveisEmCampo(Integer qtdImoveisEmCampo) {
		this.qtdImoveisEmCampo = qtdImoveisEmCampo;
	}
	public Integer getQtdImoveisTransmitidosDeRotaParcial() {
		return qtdImoveisTransmitidosDeRotaParcial;
	}
	public void setQtdImoveisTransmitidosDeRotaParcial(
			Integer qtdImoveisTransmitidosDeRotaParcial) {
		this.qtdImoveisTransmitidosDeRotaParcial = qtdImoveisTransmitidosDeRotaParcial;
	}

	public String getSituacaoLeitura() {
		return situacaoLeitura;
	}

	public void setSituacaoLeitura(String situacaoLeitura) {
		this.situacaoLeitura = situacaoLeitura;
	}
	
	
	
}
