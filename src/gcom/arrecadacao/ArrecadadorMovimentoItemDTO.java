package gcom.arrecadacao;

import java.math.BigDecimal;

public class ArrecadadorMovimentoItemDTO {

	private Integer idImovel;
	private Integer idLocalidade;
	
	private BigDecimal valorDocumento;
	
	private Integer idConta;
	private Integer idGuia;
	private Integer idDocumentoCobranca;
	private Integer idFatura;

	public ArrecadadorMovimentoItemDTO() {
		
	}
	
	public ArrecadadorMovimentoItemDTO(Integer idImovel, Integer idLocalidade, BigDecimal valorDocumento, Integer idConta, Integer idGuia, 
			Integer idDocumentoCobranca, Integer idFatura) {
		super();
		this.idImovel = idImovel;
		this.idLocalidade = idLocalidade;
		this.valorDocumento = valorDocumento;
		this.idConta = idConta;
		this.idGuia = idGuia;
		this.idDocumentoCobranca = idDocumentoCobranca;
		this.idFatura = idFatura;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public Integer getIdGuia() {
		return idGuia;
	}

	public void setIdGuia(Integer idGuia) {
		this.idGuia = idGuia;
	}

	public Integer getIdDocumentoCobranca() {
		return idDocumentoCobranca;
	}

	public void setIdDocumentoCobranca(Integer idDocumentoCobranca) {
		this.idDocumentoCobranca = idDocumentoCobranca;
	}

	public Integer getIdFatura() {
		return idFatura;
	}

	public void setIdFatura(Integer idFatura) {
		this.idFatura = idFatura;
	}

}
