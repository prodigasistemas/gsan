package gcom.relatorio.atendimentopublico.bean;


public class RelatorioOSFiscalizacaoHelper {
	
	private String sequencial;
	private String inscricao;
	private String nomeClienteUsuario;
	private String grupo;
	private String matricula;
	private String dataEmissao;
	private String categoriaRES;
	private String categoriaPUB;
	private String categoriaIND;
	private String categoriaCOM;
	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	private String ordemServico;
	private String dataValidade;
	private String rota;
	private String sequencialRota;
	private String mesAno;
	private String enderecoImovel;
	private String numeroOrdemExecutada;
	private String tipoServico;
	private String dataExecucao;
	private String motivoEncerramento;
	private String tipoPavimento;
	private String qtdeRecomposta;
	private String numeroHidrometro;
	private String leituraHidrometro;
	
	public String getCategoriaCOM() {
		return categoriaCOM;
	}
	public void setCategoriaCOM(String categoriaCOM) {
		this.categoriaCOM = categoriaCOM;
	}
	public String getCategoriaIND() {
		return categoriaIND;
	}
	public void setCategoriaIND(String categoriaIND) {
		this.categoriaIND = categoriaIND;
	}
	public String getCategoriaPUB() {
		return categoriaPUB;
	}
	public void setCategoriaPUB(String categoriaPUB) {
		this.categoriaPUB = categoriaPUB;
	}
	public String getCategoriaRES() {
		return categoriaRES;
	}
	public void setCategoriaRES(String categoriaRES) {
		this.categoriaRES = categoriaRES;
	}
	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getDataExecucao() {
		return dataExecucao;
	}
	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	public String getEnderecoImovel() {
		return enderecoImovel;
	}
	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
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
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	public String getNumeroOrdemExecutada() {
		return numeroOrdemExecutada;
	}
	public void setNumeroOrdemExecutada(String numeroOrdemExecutada) {
		this.numeroOrdemExecutada = numeroOrdemExecutada;
	}
	public String getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(String ordemServico) {
		this.ordemServico = ordemServico;
	}
	public String getQtdeRecomposta() {
		String retorno = "";
		if(!qtdeRecomposta.equals("0,00")){
			retorno = qtdeRecomposta;
		}
		return retorno;
	}
	public void setQtdeRecomposta(String qtdeRecomposta) {
		this.qtdeRecomposta = qtdeRecomposta;
	}
	public String getRota() {
		return rota;
	}
	public void setRota(String rota) {
		this.rota = rota;
	}
	public String getSequencial() {
		return sequencial;
	}
	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}
	public String getSequencialRota() {
		return sequencialRota;
	}
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
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
	public String getTipoPavimento() {
		return tipoPavimento;
	}
	public void setTipoPavimento(String tipoPavimento) {
		this.tipoPavimento = tipoPavimento;
	}
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	
	

}
