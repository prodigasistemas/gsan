package gcom.cobranca;

import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class CobrancaDocumentoFisc implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;

    private Date ultimaAlteracao;

    private FiscalizacaoSituacao fiscalizacaoSituacao;
    
    private OrdemServico ordemServico;
    
    private CobrancaDocumento cobrancaDocumento;
    
	public CobrancaDocumentoFisc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CobrancaDocumentoFisc(Integer id, Date ultimaAlteracao, 
			FiscalizacaoSituacao fiscalizacaoSituacao, 
			OrdemServico ordemServico,
			CobrancaDocumento cobrancaDocumento) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
		this.ordemServico = ordemServico;
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}

	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}

	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}
    
   
}
