package gcom.micromedicao.is.retorno;

public class ArquivoRetornoHelper {

	private Integer anoMesReferencia;
	private int tipoFinalizacao;
	
	private CabecalhoArquivoHelper cabecalho;

	public ArquivoRetornoHelper() {}
	
	public ArquivoRetornoHelper(CabecalhoArquivoHelper cabecalho, Integer anoMesReferencia, int tipoFinalizacao) {
		this.cabecalho = cabecalho;
		this.anoMesReferencia = anoMesReferencia;
		this.tipoFinalizacao = tipoFinalizacao;
	}
	
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public int getTipoFinalizacao() {
		return tipoFinalizacao;
	}

	public void setTipoFinalizacao(int tipoFinalizacao) {
		this.tipoFinalizacao = tipoFinalizacao;
	}

	public CabecalhoArquivoHelper getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(CabecalhoArquivoHelper cabecalho) {
		this.cabecalho = cabecalho;
	}
	
	
}
