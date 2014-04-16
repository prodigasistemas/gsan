package gcom.relatorio.cobranca;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Ana Maria
 * @date 15/05/2007
 */
public class RelatorioEmitirProtocoloDocumentoCobrancaBean implements RelatorioBean {
	
	private String empresa;

	private String idLocalidade;
	
	private String localidade;
	
	private String setor;

	private String quantidadeDocumento;
	
	private BigDecimal valorDocumento;
	
	private Integer seqInicial;
	
	private Integer seqFinal;
	
	private String idGerencia;
	
	private String gerencia;
	
	private String idUnidadeNegocio;
	
	private String nomeUnidadeNegocio;

	public RelatorioEmitirProtocoloDocumentoCobrancaBean(String empresa, String idLocalidade, String localidade,
		 String setor, String quantidadeDocumento, BigDecimal valorDocumento, Integer seqInicial, Integer seqFinal,  
		 String idGerencia, String gerencia) {
		this.empresa = empresa;
		this.idLocalidade = idLocalidade;
		this.localidade = localidade;
		this.setor = setor;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorDocumento = valorDocumento;
		this.seqInicial = seqInicial;
		this.seqFinal = seqFinal;
		this.idGerencia = idGerencia;		
		this.gerencia = gerencia;
	}
	
	public RelatorioEmitirProtocoloDocumentoCobrancaBean(String empresa, String idLocalidade, String localidade,
			 String setor, String quantidadeDocumento, BigDecimal valorDocumento, Integer seqInicial, Integer seqFinal,  
			 String idGerencia, String gerencia, String idUnidadeNegocio, String nomeUnidadeNegocio) {
			this.empresa = empresa;
			this.idLocalidade = idLocalidade;
			this.localidade = localidade;
			this.setor = setor;
			this.quantidadeDocumento = quantidadeDocumento;
			this.valorDocumento = valorDocumento;
			this.seqInicial = seqInicial;
			this.seqFinal = seqFinal;
			this.idGerencia = idGerencia;		
			this.gerencia = gerencia;
			this.idUnidadeNegocio = idUnidadeNegocio;
			this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo quantidadeDocumento.
	 */
	public String getQuantidadeDocumento() {
		return quantidadeDocumento;
	}

	/**
	 * @param quantidadeDocumento O quantidadeDocumento a ser setado.
	 */
	public void setQuantidadeDocumento(String quantidadeDocumento) {
		this.quantidadeDocumento = quantidadeDocumento;
	}

	/**
	 * @return Retorna o campo valorDocumento.
	 */
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	/**
	 * @param valorDocumento O valorDocumento a ser setado.
	 */
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo setor.
	 */
	public String getSetor() {
		return setor;
	}

	/**
	 * @param setor O setor a ser setado.
	 */
	public void setSetor(String setor) {
		this.setor = setor;
	}

	/**
	 * @return Retorna o campo empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa O empresa a ser setado.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Retorna o campo seqFinal.
	 */
	public Integer getSeqFinal() {
		return seqFinal;
	}

	/**
	 * @param seqFinal O seqFinal a ser setado.
	 */
	public void setSeqFinal(Integer seqFinal) {
		this.seqFinal = seqFinal;
	}

	/**
	 * @return Retorna o campo seqInicial.
	 */
	public Integer getSeqInicial() {
		return seqInicial;
	}

	/**
	 * @param seqInicial O seqInicial a ser setado.
	 */
	public void setSeqInicial(Integer seqInicial) {
		this.seqInicial = seqInicial;
	}

	/**
	 * @return Retorna o campo gerencia.
	 */
	public String getGerencia() {
		return gerencia;
	}

	/**
	 * @param gerencia O gerencia a ser setado.
	 */
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	/**
	 * @return Retorna o campo idGerencia.
	 */
	public String getIdGerencia() {
		return idGerencia;
	}

	/**
	 * @param idGerencia O idGerencia a ser setado.
	 */
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

}
