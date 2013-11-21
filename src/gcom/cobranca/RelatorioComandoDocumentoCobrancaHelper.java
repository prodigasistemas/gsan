package gcom.cobranca;

import java.io.Serializable;

public class RelatorioComandoDocumentoCobrancaHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String numero;
	private String nomeCliente;
	private String endereco;
	private String enderecoLinha2;
	private String enderecoLinha3;
	private String rotaGrupo;
	private String matricula;
	private String inscricao;
	private String dataEmissao;
	private String mesAno;
	private String situacao;
	private String executor;
	private String dataCorte;
	private String horaCorte;
	private String numeroHidrometro;
	private String leituraHidrometro;
	private String localizacaoHidrometro;
	private String dataVencimento;
	private String valor;
	private String representacaoNumericaCodBarraFormatada;
	private String representacaoNumericaCodBarraSemDigito;
	private String categoriaPrincipal;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String codigoRota;
	private String sequencialRota;
	private String idDocumentoCobranca;
	private String idFaturamentoGrupo;
	private String numeroDocumento;

	
	public RelatorioComandoDocumentoCobrancaHelper() {
		super();
	}


	public RelatorioComandoDocumentoCobrancaHelper(
			String numero, String nomeCliente, String endereco,
			String enderecoLinha2, String enderecoLinha3, String rotaGrupo,
			String matricula, String inscricao, String dataEmissao,
			String mesAno, String situacao, String executor,
			String dataCorte, String horaCorte, String numeroHidrometro,
			String leituraHidrometro, String localizacaoHidrometro,
			String dataVencimento, String valor,
			String representacaoNumericaCodBarraFormatada,
			String representacaoNumericaCodBarraSemDigito,
			String categoriaPrincipal,
			String situacaoLigacaoAgua,
			String situacaoLigacaoEsgoto, 
			String codigoRota,
			String sequencialRota,
			String idDocumentoCobranca,
			String idFaturamentoGrupo) {

		this.numero = numero;
		this.nomeCliente = nomeCliente;
		this.endereco = endereco;
		this.enderecoLinha2 = enderecoLinha2;
		this.enderecoLinha3 = enderecoLinha3;
		this.rotaGrupo = rotaGrupo;
		this.matricula = matricula;
		this.inscricao = inscricao;
		this.dataEmissao = dataEmissao;
		this.mesAno = mesAno;
		this.situacao = situacao;
		this.executor = executor;
		this.dataCorte = dataCorte;
		this.horaCorte = horaCorte;
		this.numeroHidrometro = numeroHidrometro;
		this.leituraHidrometro = leituraHidrometro;
		this.localizacaoHidrometro = localizacaoHidrometro;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
		this.codigoRota = codigoRota;
		this.sequencialRota = sequencialRota;
		this.idDocumentoCobranca = idDocumentoCobranca;
		this.idFaturamentoGrupo = idFaturamentoGrupo;
		
	}


	public String getCategoriaPrincipal() {
		return categoriaPrincipal;
	}


	public void setCategoriaPrincipal(String categoriaPrincipal) {
		this.categoriaPrincipal = categoriaPrincipal;
	}


	public String getDataCorte() {
		return dataCorte;
	}


	public void setDataCorte(String dataCorte) {
		this.dataCorte = dataCorte;
	}


	public String getDataEmissao() {
		return dataEmissao;
	}


	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}


	public String getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getEnderecoLinha2() {
		return enderecoLinha2;
	}


	public void setEnderecoLinha2(String enderecoLinha2) {
		this.enderecoLinha2 = enderecoLinha2;
	}


	public String getEnderecoLinha3() {
		return enderecoLinha3;
	}


	public void setEnderecoLinha3(String enderecoLinha3) {
		this.enderecoLinha3 = enderecoLinha3;
	}


	public String getExecutor() {
		return executor;
	}


	public void setExecutor(String executor) {
		this.executor = executor;
	}


	public String getHoraCorte() {
		return horaCorte;
	}


	public void setHoraCorte(String horaCorte) {
		this.horaCorte = horaCorte;
	}


	public String getInscricao() {
		return inscricao;
	}


	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}


	public String getLeituraHidrometro() {
		return leituraHidrometro;
	}


	public void setLeituraHidrometro(String leituraHidrometro) {
		this.leituraHidrometro = leituraHidrometro;
	}


	public String getLocalizacaoHidrometro() {
		return localizacaoHidrometro;
	}


	public void setLocalizacaoHidrometro(String localizacaoHidrometro) {
		this.localizacaoHidrometro = localizacaoHidrometro;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getMesAno() {
		return mesAno;
	}


	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}


	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}


	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}


	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}


	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}


	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}


	public String getRotaGrupo() {
		return rotaGrupo;
	}


	public void setRotaGrupo(String rotaGrupo) {
		this.rotaGrupo = rotaGrupo;
	}


	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}


	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}


	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}


	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}


	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}


	public String getCodigoRota() {
		return codigoRota;
	}


	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}


	public String getSequencialRota() {
		return sequencialRota;
	}


	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}


	public String getIdDocumentoCobranca() {
		return idDocumentoCobranca;
	}


	public void setIdDocumentoCobranca(String idDocumentoCobranca) {
		this.idDocumentoCobranca = idDocumentoCobranca;
	}


	public String getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}


	public void setIdFaturamentoGrupo(String idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}


	public String getNumeroDocumento() {
		return numeroDocumento;
	}


	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}
