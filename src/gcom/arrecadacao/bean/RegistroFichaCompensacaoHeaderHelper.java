package gcom.arrecadacao.bean;

import java.util.Date;

import gcom.arrecadacao.ArrecadadorContrato;
import gcom.util.ControladorException;
import gcom.util.Util;

public class RegistroFichaCompensacaoHeaderHelper {

	private String id;
	private String tipoOperacao;
	private String idTipoOperacao;
	private String idTipoServico;
	private String idExtensoTipoServico;
	private String prefixoAgencia;
	private String digitoVerificadorPrefixoAgencia;
	private String numeroContaCorrente;
	private String digitoVerificadorContaCorrenteCedente;
	private String nomeCedente;
	private String codigoBanco;
	private String dataGravacao;
	private String sequencialRetorno;
	private String numeroConvenio;
	private String sequencialRegistro;
	private String idCodigoBanco;
	private String nomeBanco;
	
	public RegistroFichaCompensacaoHeaderHelper(String linha, ArrecadadorContrato arrecadadorContrato) throws ControladorException{
		buildId(linha);
		buildTipoOperacao(linha);
		buildIdTipoOperacao(linha);
		buildIdTipoServico(linha);
		buildIdExtensoTipoServico(linha);
		buildPrefixoAgencia(linha);
		buildDigitoVerificadorPrefixoAgencia(linha);
		buildNumeroContaCorrente(linha);
		buildDigitoVerificadorContaCorrenteCedente(linha);
		buildNomeCedente(linha);
		buildCodigoBanco(linha);
		buildDataGravacao(linha);
		buildSequencialRetorno(linha, arrecadadorContrato);
		buildNumeroConvenio(linha);
		buildSequencialRegistro(linha);
	}

	public String getId() {
		return id;
	}
	public String getTipoOperacao() {
		return tipoOperacao;
	}
	public String getIdTipoOperacao() {
		return idTipoOperacao;
	}
	public String getIdTipoServico() {
		return idTipoServico;
	}
	public String getIdExtensoTipoServico() {
		return idExtensoTipoServico;
	}
	public String getPrefixoAgencia() {
		return prefixoAgencia;
	}
	public String getDigitoVerificadorPrefixoAgencia() {
		return digitoVerificadorPrefixoAgencia;
	}
	public String getNumeroContaCorrente() {
		return numeroContaCorrente;
	}
	public String getDigitoVerificadorContaCorrenteCedente() {
		return digitoVerificadorContaCorrenteCedente;
	}
	public String getNomeCedente() {
		return nomeCedente;
	}
	public String getCodigoBanco() {
		return codigoBanco;
	}
	public String getDataGravacao() {
		return dataGravacao;
	}
	public String getSequencialRetorno() {
		return sequencialRetorno;
	}
	public String getNumeroConvenio() {
		return numeroConvenio;
	}
	public String getSequencialRegistro() {
		return sequencialRegistro;
	}
	
	public String getIdCodigoBanco() {
		return idCodigoBanco;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}
	
	public Date getDataGravacaoFormatado() {
		return Util.converteStringSemBarraParaDateAnoSimples(dataGravacao);
	}

	private void buildSequencialRegistro(String linha) {
		this.sequencialRegistro = linha.substring(394,400);
	}
	
	private void buildNumeroConvenio(String linha) {
		this.numeroConvenio = linha.substring(149,156);
	}
	
	private void buildSequencialRetorno(String linha, ArrecadadorContrato arrecadadorContrato) throws ControladorException {
		this.sequencialRetorno = linha.substring(100,107);
		Integer numeroSequecialArquivoRetornoFichaComp = arrecadadorContrato.getNumeroSequencialArquivoRetornoFichaCompensacao();
	    numeroSequecialArquivoRetornoFichaComp += 1;
        if (!Integer.valueOf(sequencialRetorno).equals(numeroSequecialArquivoRetornoFichaComp)) {
            throw new ControladorException("atencao.arquivo.movimento.fora.sequencia");
        }
	}
	
	private void buildDataGravacao(String linha) {
		this.dataGravacao = linha.substring(94,100);
	}
	
	private void buildCodigoBanco(String linha) throws ControladorException {
		this.codigoBanco = linha.substring(76,94);
		this.idCodigoBanco = linha.substring(76, 79);
		this.nomeBanco = linha.substring(79, 94);
		if(!this.codigoBanco.equals("001BANCO DO BRASIL")){
			throw new ControladorException("Código do banco inválido.");
		}
	}
	
	private void buildNomeCedente(String linha) {
		this.nomeCedente = linha.substring(46,76);
	}
	
	private void buildDigitoVerificadorContaCorrenteCedente(String linha) {
		this.digitoVerificadorContaCorrenteCedente = linha.substring(39,40);
	}
	
	private void buildNumeroContaCorrente(String linha) {
		this.numeroContaCorrente = linha.substring(31,39);
	}
	
	private void buildDigitoVerificadorPrefixoAgencia(String linha) {
		this.digitoVerificadorPrefixoAgencia = linha.substring(30,31);
	}
	
	private void buildPrefixoAgencia(String linha) {
		this.prefixoAgencia = linha.substring(26,30);
	}
	
	private void buildIdExtensoTipoServico(String linha) throws ControladorException {
		this.idExtensoTipoServico = linha.substring(11,19);
		if(!this.idExtensoTipoServico.equals("COBRANCA")){
			throw new ControladorException("Tipo serviço por extenso inválido.");
		}
	}
	
	private void buildIdTipoServico(String linha) throws ControladorException {
		this.idTipoServico = linha.substring(9,11);
		if(!this.idTipoServico.equals("01")){
			throw new ControladorException("Tipo serviço inválido.");
		}
	}
	
	private void buildIdTipoOperacao(String linha) throws ControladorException {
		this.idTipoOperacao = linha.substring(2,9);
		if(!this.idTipoOperacao.equals("RETORNO")){
			throw new ControladorException("Tipo operação inválido.");
		}
	}
	
	private void buildTipoOperacao(String linha) throws ControladorException {
		this.tipoOperacao = linha.substring(1,2);
		if(!this.tipoOperacao.equals("2")){
			throw new ControladorException("atencao.codigo.remessa.invalido");
		}
	}
	
	private void buildId(String linha) throws ControladorException {
		this.id = linha.substring(0,1);
		if(!this.id.equals("0")){
			throw new ControladorException("atencao.arquivo.movimento.sem.header");
		}
	}
}
