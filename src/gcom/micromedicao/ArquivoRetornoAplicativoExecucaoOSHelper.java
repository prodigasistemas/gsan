package gcom.micromedicao;

import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;

public class ArquivoRetornoAplicativoExecucaoOSHelper implements Serializable{

	private static final long serialVersionUID = 1448570895806137609L;
	
	private Integer idOrdemServico;
	private Integer idServicoTipo;
	private Usuario usuario ;
	
	private Integer idServicoMotivoNaoCobranca ;
	private String  valorPercentual ;
	private String  qtdParcelas ;
	private Integer idTipoDebito; 
	private String  valorDebito;
	private String  PercentualCobranca;
	private String  DataReligacao;

	private Integer indcFinalizacao;
	private Integer codRota;
	private Integer setorComercial;
	private Integer localidade;
	private Integer numeroSequenciaArquivo;
	private Integer idRota;
	
	public ArquivoRetornoAplicativoExecucaoOSHelper(){}
	
	public ArquivoRetornoAplicativoExecucaoOSHelper(String registro0, Integer idRota){
		this.indcFinalizacao = Integer.parseInt(registro0.substring(1,2));
		this.codRota = Integer.parseInt(registro0.substring(8,15));
		this.setorComercial = Integer.parseInt(registro0.substring(5,8));
		this.localidade = Integer.parseInt(registro0.substring(2,5));
		this.idRota = idRota;
		
		if (registro0.length() == 17) {
			this.numeroSequenciaArquivo = Integer.parseInt(registro0.substring(15, 17));
		} else {
			this.numeroSequenciaArquivo = Integer.parseInt(registro0.substring(19, 21));
		}
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

	public String getValorPercentual() {
		return valorPercentual;
	}

	public void setValorPercentual(String valorPercentual) {
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
	public Integer getCodRota() {
		return codRota;
	}
	public void setCodRota(Integer codRota) {
		this.codRota = codRota;
	}
	public Integer getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(Integer setorComercial) {
		this.setorComercial = setorComercial;
	}
	public Integer getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}

	public Integer getNumeroSequenciaArquivo() {
		return numeroSequenciaArquivo;
	}

	public void setNumeroSequenciaArquivo(Integer numeroSequenciaArquivo) {
		this.numeroSequenciaArquivo = numeroSequenciaArquivo;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}
	
	public void atualizarDados(Imovel imovel, Integer idRota) {
		this.localidade = imovel.getLocalidade().getId();
		this.setorComercial = imovel.getRotaAlternativa().getSetorComercial().getCodigo();
		this.codRota = imovel.getRotaAlternativa().getCodigo().intValue();
		this.idRota = idRota;
	}

	

	
	
}
