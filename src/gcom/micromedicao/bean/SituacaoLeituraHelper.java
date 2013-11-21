package gcom.micromedicao.bean;

import java.io.Serializable;

public class SituacaoLeituraHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String medidosEnviados;
	private String medidosRecebidos;
	private String diferencaMedidosEnvRec;
	private String naoMedidosEnviados;
	private String naoMedidosRecebidos;
	private String diferencanaoMedidosEnvRec;
	private String medidosImpressos;
	private String medidosNaoImpressos;
	private String naoMedidosImpressos;
	private String naoMedidosNaoImpressos;
	private String motivoFinalizacao;
	private String anormalidades;
	
	public SituacaoLeituraHelper(String medidosEnviados,
			String medidosRecebidos, String diferencaMedidosEnvRec,
			String naoMedidosEnviados, String naoMedidosRecebidos,
			String diferencanaoMedidosEnvRec, String medidosImpressos,
			String medidosNaoImpressos, String naoMedidosImpressos,
			String naoMedidosNaoImpressos,  String motivoFinalizacao,
			String anormalidades ) {
		this.medidosEnviados = medidosEnviados;
		this.medidosRecebidos = medidosRecebidos;
		this.diferencaMedidosEnvRec = diferencaMedidosEnvRec;
		this.naoMedidosEnviados = naoMedidosEnviados;
		this.naoMedidosRecebidos = naoMedidosRecebidos;
		this.diferencanaoMedidosEnvRec = diferencanaoMedidosEnvRec;
		this.medidosImpressos = medidosImpressos;
		this.medidosNaoImpressos = medidosNaoImpressos;
		this.naoMedidosImpressos = naoMedidosImpressos;
		this.naoMedidosNaoImpressos = naoMedidosNaoImpressos;
		this.motivoFinalizacao = motivoFinalizacao;
		this.anormalidades = anormalidades; 
	}
	public String getMedidosEnviados() {
		return medidosEnviados;
	}
	public void setMedidosEnviados(String medidosEnviados) {
		this.medidosEnviados = medidosEnviados;
	}
	public String getMedidosRecebidos() {
		return medidosRecebidos;
	}
	public void setMedidosRecebidos(String medidosRecebidos) {
		this.medidosRecebidos = medidosRecebidos;
	}
	public String getDiferencaMedidosEnvRec() {
		return diferencaMedidosEnvRec;
	}
	public void setDiferencaMedidosEnvRec(String diferencaMedidosEnvRec) {
		this.diferencaMedidosEnvRec = diferencaMedidosEnvRec;
	}
	public String getNaoMedidosEnviados() {
		return naoMedidosEnviados;
	}
	public void setNaoMedidosEnviados(String naoMedidosEnviados) {
		this.naoMedidosEnviados = naoMedidosEnviados;
	}
	public String getNaoMedidosRecebidos() {
		return naoMedidosRecebidos;
	}
	public void setNaoMedidosRecebidos(String naoMeidosRecebidos) {
		this.naoMedidosRecebidos = naoMeidosRecebidos;
	}
	public String getDiferencanaoMedidosEnvRec() {
		return diferencanaoMedidosEnvRec;
	}
	public void setDiferencanaoMedidosEnvRec(String diferencanaoMedidosEnvRec) {
		this.diferencanaoMedidosEnvRec = diferencanaoMedidosEnvRec;
	}
	public String getMedidosImpressos() {
		return medidosImpressos;
	}
	public void setMedidosImpressos(String medidosImpressos) {
		this.medidosImpressos = medidosImpressos;
	}
	public String getMedidosNaoImpressos() {
		return medidosNaoImpressos;
	}
	public void setMedidosNaoImpressos(String medidosNaoImpressos) {
		this.medidosNaoImpressos = medidosNaoImpressos;
	}
	public String getNaoMedidosImpressos() {
		return naoMedidosImpressos;
	}
	public void setNaoMedidosImpressos(String naoMedidosImpressos) {
		this.naoMedidosImpressos = naoMedidosImpressos;
	}
	public String getNaoMedidosNaoImpressos() {
		return naoMedidosNaoImpressos;
	}
	public void setNaoMedidosNaoImpressos(String naoMedidosNaoImpressos) {
		this.naoMedidosNaoImpressos = naoMedidosNaoImpressos;
	}
	public String getMotivoFinalizacao() {
		return motivoFinalizacao;
	}
	public void setMotivoFinalizacao(String motivoFinalizacao) {
		this.motivoFinalizacao = motivoFinalizacao;
	}
	public String getAnormalidades() {
		return anormalidades;
	}
	public void setAnormalidades(String anormalidades) {
		this.anormalidades = anormalidades;
	}
	
	

}
