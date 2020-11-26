package gcom.micromedicao;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ArquivoRetornoAplicativoExecucaoOSHelper implements Serializable{

	private static final long serialVersionUID = 1448570895806137609L;
	
	@SerializedName("id") 
	private Integer idOrdemServico;
	
	@SerializedName("data_encerramento") 
	private String  dataEncerramento;
	
	@SerializedName("motivo_encerramento")
	private Integer motivoEncerramento;

	@SerializedName("parecer_encerramento")
	private String  parecer;
		
	private DadosHidrometroSubstituicaoDTO dados_hidrometro_substituicao;
	
	private DadosHidrometroInstalacaoDTO dados_hidrometro_instalacao;
	
	private DadosLigacaoAguaDTO dados_ligacao_agua;
	
	private Integer idServicoTipo;
	
	private Integer idServicoMotivoNaoCobranca ;
	private String  valorusuario ;
	private Integer valorPercentual ;
	private String  qtdParcelas ;
	private Integer idTipoDebito; 
	private String  valorDebito;
	private String  PercentualCobranca;
	private String  DataReligacao;
	
    //@SerializedName("") 
	//private Integer indcFinalizacao;

	//private Usuario usuario;
  
	
	public ArquivoRetornoAplicativoExecucaoOSHelper(){
		
	}
	
	
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

	/*public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}*/

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

	/*public Integer getIndcFinalizacao() {
		return indcFinalizacao;
	}
	public void setIndcFinalizacao(Integer indcFinalizacao) {
		this.indcFinalizacao = indcFinalizacao;
	}*/


	
}
