package gcom.api.ordemServico.helper;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import gcom.api.ordemServico.DTO.DadosHidrometroInstalacaoDTO;
import gcom.api.ordemServico.DTO.DadosHidrometroSubstituicaoDTO;
import gcom.api.ordemServico.DTO.DadosLigacaoAguaDTO;

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
	
	@SerializedName("id_usuario")
	private Integer idUsuario;
	
	private Integer idServicoTipo;
	
	@SerializedName(value = "parecer_encerramento")
	private String parecerEncerramento;
	
	private Integer idServicoMotivoNaoCobranca ;
	private String  valorusuario ;
	private Integer valorPercentual ;
	private String  qtdParcelas ;
	private Integer idTipoDebito; 
	private String  valorDebito;
	private String  PercentualCobranca;
	private String  DataReligacao;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idusuario) {
		this.idUsuario = idusuario;
	}

	public Integer getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(Integer motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public DadosHidrometroSubstituicaoDTO getDados_hidrometro_substituicao() {
		return dados_hidrometro_substituicao;
	}

	public void setDados_hidrometro_substituicao(DadosHidrometroSubstituicaoDTO dados_hidrometro_substituicao) {
		this.dados_hidrometro_substituicao = dados_hidrometro_substituicao;
	}

	public DadosHidrometroInstalacaoDTO getDados_hidrometro_instalacao() {
		return dados_hidrometro_instalacao;
	}

	public void setDados_hidrometro_instalacao(DadosHidrometroInstalacaoDTO dados_hidrometro_instalacao) {
		this.dados_hidrometro_instalacao = dados_hidrometro_instalacao;
	}

	public DadosLigacaoAguaDTO getDados_ligacao_agua() {
		return dados_ligacao_agua;
	}

	public void setDados_ligacao_agua(DadosLigacaoAguaDTO dados_ligacao_agua) {
		this.dados_ligacao_agua = dados_ligacao_agua;
	}

	public String getValorusuario() {
		return valorusuario;
	}

	public void setValorusuario(String valorusuario) {
		this.valorusuario = valorusuario;
	}

	public String getParecerEncerramento() {
		return parecerEncerramento;
	}

	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

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
	
}
