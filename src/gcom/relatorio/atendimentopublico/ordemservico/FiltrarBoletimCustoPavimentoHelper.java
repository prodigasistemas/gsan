package gcom.relatorio.atendimentopublico.ordemservico;

import java.io.Serializable;


/**
 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento.
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de Boletim de Custo de Repavimentação
 * 
 * @author Hugo Leonardo
 * @date 03/01/2011
 */
public class FiltrarBoletimCustoPavimentoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferenciaGeracao;
	private String idUnidadeRepavimentadora;
	private String indicadorTipoBoletim;
	
	public String getIdUnidadeRepavimentadora() {
		return idUnidadeRepavimentadora;
	}
	
	public void setIdUnidadeRepavimentadora(String idUnidadeRepavimentadora) {
		this.idUnidadeRepavimentadora = idUnidadeRepavimentadora;
	}
	
	public String getIndicadorTipoBoletim() {
		return indicadorTipoBoletim;
	}
	
	public void setIndicadorTipoBoletim(String indicadorTipoBoletim) {
		this.indicadorTipoBoletim = indicadorTipoBoletim;
	}
	
	public String getMesAnoReferenciaGeracao() {
		return mesAnoReferenciaGeracao;
	}
	
	public void setMesAnoReferenciaGeracao(String mesAnoReferenciaGeracao) {
		this.mesAnoReferenciaGeracao = mesAnoReferenciaGeracao;
	}

}
