package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * Classe de bean que representa o relatório jasper report cujo
 * nome é igual ao nome da classe
 * 
 * Todos os atributos contidos nessa classe são manipulados pelo
 * Jasper Reports
 *
 * @author José Guilherme Macedo Vieira
 * @date 09/06/2009
 */
public class RelatorioLeituraConsultarArquivosTextosBean implements RelatorioBean{
	
	//Atributos que equivalem ao cabeçalho do relatório
	private String numeroSequenciaLeitura;	
	//fim dos atributos que equivalem ao cabeçalho do relatório
	
	//Nome da localidade de onde os dados do arquivo são
	private String codigoLocalidade;
	
	//Nome do setor comercial ao qual o arquivo pertence
	private String codigoSetorComercial;
	
	//Codigo da rota do arquivo
	private String codigoRota;
	
	private String quantidade;
	
	//Nome do leiturista que fez a leitura
	private String nomeLeiturista;
	
	//Situação do arquivo para leitura
	private String situacaoArquivo;
	
	//Data da liberação do arquivo
	private String dataLiberacao;
	
	private String servicoTipoCelular;
	
	private String jusficativa;
	
	private String medidosEnviados;
	private String medidosRecebidosImpressos;
	private String medidosRecebidosNaoImpressos;
	private String naoMedidosEnviados;
	private String naoMedidosRecebidosImpressos;
	private String naoMedidosRecebidosNaoImpressos;
	
	private String qtdRecebidos;
	
	public RelatorioLeituraConsultarArquivosTextosBean(
			String numeroSequenciaLeitura, String codigoLocalidade,
			String codigoSetorComercial, String codigoRota, String quantidade,
			String nomeLeiturista, String situacaoArquivo,
			String dataLiberacao, String servicoTipoCelular,
			String jusficativa, String medidosEnviados,
			String medidosRecebidosImpressos,
			String medidosRecebidosNaoImpressos, String naoMedidosEnviados,
			String naoMedidosRecebidosImpressos,
			String naoMedidosRecebidosNaoImpressos, String qtdRecebidos) {
		this.numeroSequenciaLeitura = numeroSequenciaLeitura;
		this.codigoLocalidade = codigoLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.codigoRota = codigoRota;
		this.quantidade = quantidade;
		this.nomeLeiturista = nomeLeiturista;
		this.situacaoArquivo = situacaoArquivo;
		this.dataLiberacao = dataLiberacao;
		this.servicoTipoCelular = servicoTipoCelular;
		this.jusficativa = jusficativa;
		this.medidosEnviados = medidosEnviados;
		this.medidosRecebidosImpressos = medidosRecebidosImpressos;
		this.medidosRecebidosNaoImpressos = medidosRecebidosNaoImpressos;
		this.naoMedidosEnviados = naoMedidosEnviados;
		this.naoMedidosRecebidosImpressos = naoMedidosRecebidosImpressos;
		this.naoMedidosRecebidosNaoImpressos = naoMedidosRecebidosNaoImpressos;
		this.qtdRecebidos = qtdRecebidos;
	}

	public String getServicoTipoCelular() {
		return servicoTipoCelular;
	}
	
	public void setServicoTipoCelular(String servicoTipoCelular) {
		this.servicoTipoCelular = servicoTipoCelular;
	}
	
	public String getCodigoRota() {
		return codigoRota;
	}
	
	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}
	
	public String getDataLiberacao() {
		return dataLiberacao;
	}
	
	public void setDataLiberacao(String dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}
	
	public String getNomeLeiturista() {
		return nomeLeiturista;
	}
	
	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}
	
	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}
	
	public void setCodigoLocalidade(String nomeLocalidade) {
		this.codigoLocalidade = nomeLocalidade;
	}
	
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	
	public void setCodigoSetorComercial(String nomeSetorComercial) {
		this.codigoSetorComercial = nomeSetorComercial;
	}
	
	public String getNumeroSequenciaLeitura() {
		return numeroSequenciaLeitura;
	}
	
	public void setNumeroSequenciaLeitura(String numeroSequenciaLeitura) {
		this.numeroSequenciaLeitura = numeroSequenciaLeitura;
	}
	
	public String getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getSituacaoArquivo() {
		return situacaoArquivo;
	}
	
	public void setSituacaoArquivo(String situacaoArquivo) {
		this.situacaoArquivo = situacaoArquivo;
	}

	public String getJusficativa() {
		return jusficativa;
	}

	public void setJusficativa(String jusficativa) {
		this.jusficativa = jusficativa;
	}

	public String getMedidosEnviados() {
		return medidosEnviados;
	}

	public void setMedidosEnviados(String medidosEnviados) {
		this.medidosEnviados = medidosEnviados;
	}

	public String getMedidosRecebidosImpressos() {
		return medidosRecebidosImpressos;
	}

	public void setMedidosRecebidosImpressos(String medidosRecebidosImpressos) {
		this.medidosRecebidosImpressos = medidosRecebidosImpressos;
	}

	public String getMedidosRecebidosNaoImpressos() {
		return medidosRecebidosNaoImpressos;
	}

	public void setMedidosRecebidosNaoImpressos(String medidosRecebidosNaoImpressos) {
		this.medidosRecebidosNaoImpressos = medidosRecebidosNaoImpressos;
	}

	public String getNaoMedidosEnviados() {
		return naoMedidosEnviados;
	}

	public void setNaoMedidosEnviados(String naoMedidosEnviados) {
		this.naoMedidosEnviados = naoMedidosEnviados;
	}

	public String getNaoMedidosRecebidosImpressos() {
		return naoMedidosRecebidosImpressos;
	}

	public void setNaoMedidosRecebidosImpressos(String naoMedidosRecebidosImpressos) {
		this.naoMedidosRecebidosImpressos = naoMedidosRecebidosImpressos;
	}

	public String getNaoMedidosRecebidosNaoImpressos() {
		return naoMedidosRecebidosNaoImpressos;
	}

	public void setNaoMedidosRecebidosNaoImpressos(
			String naoMedidosRecebidosNaoImpressos) {
		this.naoMedidosRecebidosNaoImpressos = naoMedidosRecebidosNaoImpressos;
	}

	public String getQtdRecebidos() {
		return qtdRecebidos;
	}

	public void setQtdRecebidos(String qtdRecebidos) {
		this.qtdRecebidos = qtdRecebidos;
	}
	
}
