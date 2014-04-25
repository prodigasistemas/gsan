package gcom.gui.micromedicao;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Inserir Leiturista
 * 
 * @author Thiago Tenório
 * @date 22/07/2007
 */
public class ConsultarArquivoTextoLeituraActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	//private String codigo;
	
	private String empresaID;
	
	private String grupoFaturamentoID;
	
	private String mesAno;
	
	private String situaTransmLeitura;
	
	private String[] idsRegistros;
	
	private String leituristaID;
	
	private String idSituacaoLeitura;
	
	private String idLocalidade;
	
	private String nomeLocalidade;
	
	private String motivoFinalizacao;
	
	private String tipoMedicao;
	
	private String finalizar;
	
	//Atributo FK na tabela de arquivo_texto_roteiro_empresa que referencia um servico tipo celular na
	//tabela servico_tipo_celular
	private String servicoTipoCelular;
	
	private String contaImpressa;	
	
	public String getContaImpressa() {
		return contaImpressa;
	}

	public void setContaImpressa(String contaImpressa) {
		this.contaImpressa = contaImpressa;
	}

	public String getServicoTipoCelular() {
		return servicoTipoCelular;
	}
	
	public void setServicoTipoCelular(String servicoTipoCelular) {
		this.servicoTipoCelular = servicoTipoCelular;
	}
	
	public String getIdSituacaoLeitura() {
		return idSituacaoLeitura;
	}
	
	public void setIdSituacaoLeitura(String idSituacaoLeitura) {
		this.idSituacaoLeitura = idSituacaoLeitura;
	}
	
	public String getEmpresaID() {
		return empresaID;
	}
	
	public void setEmpresaID(String empresaID) {
		this.empresaID = empresaID;
	}
	
	public String getGrupoFaturamentoID() {
		return grupoFaturamentoID;
	}
	
	public void setGrupoFaturamentoID(String grupoFaturamentoID) {
		this.grupoFaturamentoID = grupoFaturamentoID;
	}
	
	public String getMesAno() {
		return mesAno;
	}
	
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	
	public String getSituaTransmLeitura() {
		return situaTransmLeitura;
	}
	
	public void setSituaTransmLeitura(String situaTransmLeitura) {
		this.situaTransmLeitura = situaTransmLeitura;
	}
	
	/**
	 * @return Returns the idsRegistros.
	 */
	public String[] getIdsRegistros() {
		return idsRegistros;
	}
	
	/**
	 * @param idsRegistros The idsRegistros to set.
	 */
	public void setIdsRegistros(String[] idsRegistros) {
		this.idsRegistros = idsRegistros;
	}
	
	/**
	 * @return Returns the leituristaID.
	 */
	public String getLeituristaID() {
		return leituristaID;
	}
	
	/**
	 * @param leituristaID The leituristaID to set.
	 */
	public void setLeituristaID(String leituristaID) {
		this.leituristaID = leituristaID;
	}
	
	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		super.reset(arg0, arg1);
		
		this.empresaID="";
		
		this.grupoFaturamentoID="";
		
		this.mesAno="";
		
		this.situaTransmLeitura="";
		
		this.idsRegistros=new String[0];
		
		this.leituristaID="";
		
		this.idSituacaoLeitura="";
		
	}

	public String getMotivoFinalizacao() {
		return motivoFinalizacao;
	}

	public void setMotivoFinalizacao(String motivoFinalizacao) {
		this.motivoFinalizacao = motivoFinalizacao;
	}

	public String getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	public String getFinalizar() {
		return finalizar;
	}

	public void setFinalizar(String finalizar) {
		this.finalizar = finalizar;
	}
}
