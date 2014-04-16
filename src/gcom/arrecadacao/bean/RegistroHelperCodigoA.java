package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * @author Sávio Luiz
 * @created 30 de Janeiro de 2006 [UC0262] - Distribuir Dados do Registro de
 *          Movimento do Arrecadador
 */
public class RegistroHelperCodigoA implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigoA() {
	}

	private String codigoRegistro;

	private String codigoRemessa;

	private String codigoConvenio;

	private String nomeEmpresa;

	private String codigoBanco;

	private String nomeBanco;

	private String dataGeracaoArquivo;

	private String numeroSequencialArquivo;

	private String versaoLayout;

	private String tipoMovimento;

	private String reservadoFuturo;
	
	//Alterado por Sávio Luiz Data:24/04/09
	// Caso um banco tenha mais de 1 contrato e os ids dos bancos sejam diferentes para cada contrato
	//Ex.: Na CAEMA o Banco do Brasil tem 2 contratos, só que no sistema temos cadastrados 2 bancos diferentes, 
	//mas no arquivo o id do banco vem sempre 1 independete de contrato.
	// Este campo será usado para os arquivos do tipo "B" para inserção e deleção de débito automático.
	private String idArrecadadorBanco;

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getCodigoConvenio() {
		return codigoConvenio;
	}

	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

	public String getCodigoRegistro() {
		return codigoRegistro;
	}

	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	public String getCodigoRemessa() {
		return codigoRemessa;
	}

	public void setCodigoRemessa(String codigoRemessa) {
		this.codigoRemessa = codigoRemessa;
	}

	public String getDataGeracaoArquivo() {
		return dataGeracaoArquivo;
	}

	public void setDataGeracaoArquivo(String dataGeracaoArquivo) {
		this.dataGeracaoArquivo = dataGeracaoArquivo;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNumeroSequencialArquivo() {
		return numeroSequencialArquivo;
	}

	public void setNumeroSequencialArquivo(String numeroSequencialArquivo) {
		this.numeroSequencialArquivo = numeroSequencialArquivo;
	}

	public String getReservadoFuturo() {
		return reservadoFuturo;
	}

	public void setReservadoFuturo(String reservadoFuturo) {
		this.reservadoFuturo = reservadoFuturo;
	}

	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public String getVersaoLayout() {
		return versaoLayout;
	}

	public void setVersaoLayout(String versaoLayout) {
		this.versaoLayout = versaoLayout;
	}

	public String getIdArrecadadorBanco() {
		return idArrecadadorBanco;
	}

	public void setIdArrecadadorBanco(String idArrecadadorBanco) {
		this.idArrecadadorBanco = idArrecadadorBanco;
	}

	
}
