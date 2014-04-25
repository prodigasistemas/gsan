package gcom.faturamento;

import gcom.cobranca.FiltroDocumentoCobranca;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DocumentoNaoEntregue extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataTentativaEntrega;

    /** persistent field */
    private short numeroTentativa;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cadastro.cliente.Cliente cliente;

    /** persistent field */
    private gcom.faturamento.conta.Fatura fatura;

    /** persistent field */
    private gcom.cobranca.CobrancaDocumento cobrancaDocumento;

    /** persistent field */
    private gcom.cobranca.DocumentoTipo documentoTipo;

    /** persistent field */
    private gcom.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento;

    /** persistent field */
    private gcom.faturamento.GuiaPagamentoGeral guiaPagamentoGeral;

    /** persistent field */
    private gcom.faturamento.conta.ContaGeral contaGeral;

    /** full constructor */
    public DocumentoNaoEntregue(Integer id, Date dataTentativaEntrega, short numeroTentativa, Date ultimaAlteracao, gcom.cadastro.cliente.Cliente cliente, gcom.faturamento.conta.Fatura fatura, gcom.cobranca.CobrancaDocumento cobrancaDocumento, gcom.cobranca.DocumentoTipo documentoTipo, gcom.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento, gcom.faturamento.GuiaPagamentoGeral guiaPagamentoGeral, gcom.faturamento.conta.ContaGeral contaGeral) {
        this.id = id;
        this.dataTentativaEntrega = dataTentativaEntrega;
        this.numeroTentativa = numeroTentativa;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cliente = cliente;
        this.fatura = fatura;
        this.cobrancaDocumento = cobrancaDocumento;
        this.documentoTipo = documentoTipo;
        this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
        this.contaGeral = contaGeral;
    }

    /** default constructor */
    public DocumentoNaoEntregue() {
    }

	public gcom.cadastro.cliente.Cliente getCliente() {
		return cliente;
	}

	public void setCliente(gcom.cadastro.cliente.Cliente cliente) {
		this.cliente = cliente;
	}

	public gcom.cobranca.CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}

	public void setCobrancaDocumento(
			gcom.cobranca.CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public gcom.faturamento.conta.ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(gcom.faturamento.conta.ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public Date getDataTentativaEntrega() {
		return dataTentativaEntrega;
	}

	public void setDataTentativaEntrega(Date dataTentativaEntrega) {
		this.dataTentativaEntrega = dataTentativaEntrega;
	}

	public gcom.cobranca.DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(gcom.cobranca.DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public gcom.faturamento.conta.Fatura getFatura() {
		return fatura;
	}

	public void setFatura(gcom.faturamento.conta.Fatura fatura) {
		this.fatura = fatura;
	}

	public gcom.faturamento.GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(
			gcom.faturamento.GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public gcom.faturamento.conta.MotivoNaoEntregaDocumento getMotivoNaoEntregaDocumento() {
		return motivoNaoEntregaDocumento;
	}

	public void setMotivoNaoEntregaDocumento(
			gcom.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento) {
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
	}

	public short getNumeroTentativa() {
		return numeroTentativa;
	}

	public void setNumeroTentativa(short numeroTentativa) {
		this.numeroTentativa = numeroTentativa;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	   public String toString() {	        return new ToStringBuilder(this)	            .append("id", getId())	            .toString();	    }	    		public String[] retornaCamposChavePrimaria(){			String[] retorno = new String[1];			retorno[0] = "id";			return retorno;		}			    public Filtro retornaFiltro(){	    	FiltroDocumentoCobranca filtroDocumentoNaoEntregue = new FiltroDocumentoCobranca();	    	filtroDocumentoNaoEntregue.adicionarParametro(new ParametroSimples(FiltroDocumentoCobranca.ID,this.getId()));	    	filtroDocumentoNaoEntregue.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");						return filtroDocumentoNaoEntregue;		}

}
