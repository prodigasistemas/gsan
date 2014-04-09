package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0774]FILTRAR Empresa
 * 
 * @author Arthur Carvalho
 * @date 14/05/2008
 */

public class FiltrarFaturaClienteResponsavelActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;

	private String descricao;
	
	private String clienteId;
	
	private String clienteNome;
	
	private String IndicadorAtualizar;
	
	private String mesAno;
	
	private String imovelId;
	
	private String inscricao;
	
	private String valorFatura;
	
	private String dataVencimentoFatura;
	
	private String[] idRegistrosRemocao;

	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}

	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getImovelId() {
		return imovelId;
	}

	public void setImovelId(String imovelId) {
		this.imovelId = imovelId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getIndicadorAtualizar() {
		return IndicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		IndicadorAtualizar = indicadorAtualizar;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getDataVencimentoFatura() {
		return dataVencimentoFatura;
	}

	public void setDataVencimentoFatura(String dataVencimentoFatura) {
		this.dataVencimentoFatura = dataVencimentoFatura;
	}

	public String getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(String valorFatura) {
		this.valorFatura = valorFatura;
	}
	
}
