package gcom.micromedicao;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import gcom.seguranca.acesso.usuario.Usuario;

public class ArquivoRetornoAplicativoExecucaoOSHelper implements Serializable{

	private static final long serialVersionUID = 1448570895806137609L;
	
	@SerializedName("id") 
	private Integer idOrdemServico;
	
	@SerializedName("data_encerramento") 
	private String  dataEncerramento;
	
	@SerializedName("motivo_encerramento")
	private Integer dotivoEncerramento;

	@SerializedName("parecer_encerramento")
	private String  darecer;
	
	//dados_hidrometro_substituicao
	@SerializedName("numero")
	private Integer  idSubstituicao;
	
	@SerializedName("dataSubstituicao") 
	private String  dataSubstituicao;
	
	@SerializedName("leituraSubstituicao")
	private Integer  leituraSubstituicao;
	
	@SerializedName("situacaoHidrometro") 
	private Integer  situacaoHidrometro;
	
	@SerializedName("localArmazenagem") 
	private Integer localArmazenagem;
	
	
	//dados_hidrometro_instalacao
	@SerializedName("numero") 
	private Integer idInstalacao;
	
	@SerializedName("dataInstalacao") 
	private String dataInstalacao;
	
	@SerializedName("localInstalacao") 
	private Integer localInstalacao;
	
	@SerializedName("protecao")
	private Integer protecao;
	
	@SerializedName("trocaProtecao")
	private Integer trocaProtecao;
	
	@SerializedName("trocaRegistro")
	private Integer trocaRegistro;
	
	@SerializedName("leituraInstalacao")
	private Integer leituraInstalacao;
	
	@SerializedName("selo")
	private Integer selo;
	
	@SerializedName("cavalete")
	private Integer cavalete;
	
	@SerializedName("lacre")
	private Integer lacreHidrometro;
	
	//dados_ligacao_agua
	@SerializedName("dataLigacao")
	private String dataLigacao;
	
	@SerializedName("diametro")
	private Integer diametro;
	
	@SerializedName("material")
	private Integer material;
	
	@SerializedName("perfil")
	private Integer perfil;
	
	@SerializedName("localInstalacaoRamal") 
	private Integer localInstalacaoRamal;
	
	@SerializedName("profundidade")
	private Integer profundidade;
	
	@SerializedName("distanciaInstalacaoRamal") 
	private Integer distanciaInstalacaoRamal;
	
	@SerializedName("pavimentoRua") 
	private Integer pavimentoRua;
	
	@SerializedName("pavimentoCalcada") 
	private Integer pavimentoCalcada;
	
	@SerializedName("origem") 
	private Integer origem;
	
	@SerializedName("lacre")
	private Integer lacreAgua;
	
	private Integer idServicoTipo;
	
	private Integer idServicoMotivoNaoCobranca ;
	private String  valorusuario ;
	private Integer valorPercentual ;
	private String  qtdParcelas ;
	private Integer idTipoDebito; 
	private String  valorDebito;
	private String  PercentualCobranca;
	private String  DataReligacao;
	
    @SerializedName("") 
	private Integer indcFinalizacao;

	private Usuario usuario;
    

	
	public ArquivoRetornoAplicativoExecucaoOSHelper(){}
	
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	
	public Integer getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(Integer idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getIdServicoMotivoNaoCobranca() {
		return idServicoMotivoNaoCobranca;
	}

	public void setIdServicoMotivoNaoCobranca(Integer idServicoMotivoNaoCobranca) {
		this.idServicoMotivoNaoCobranca = idServicoMotivoNaoCobranca;
	}


	public Integer getValorPercentual() {
		return valorPercentual;
	}

	public void setValorPercentual(Integer valorPercentual) {
		this.valorPercentual = valorPercentual;
	}

	public String getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public Integer getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(Integer idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	public String getPercentualCobranca() {
		return PercentualCobranca;
	}

	public void setPercentualCobranca(String percentualCobranca) {
		PercentualCobranca = percentualCobranca;
	}

	public String getDataReligacao() {
		return DataReligacao;
	}

	public void setDataReligacao(String dataReligacao) {
		DataReligacao = dataReligacao;
	}

	public Integer getIndcFinalizacao() {
		return indcFinalizacao;
	}
	public void setIndcFinalizacao(Integer indcFinalizacao) {
		this.indcFinalizacao = indcFinalizacao;
	}


	
}
