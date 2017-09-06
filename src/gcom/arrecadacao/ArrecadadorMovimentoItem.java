package gcom.arrecadacao;

import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.Fatura;
import gcom.interceptor.ObjetoGcom;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


public class ArrecadadorMovimentoItem extends ObjetoGcom {

	private static final long serialVersionUID = 1L;

    private Integer id;
    private String conteudoRegistro;
    private Date ultimaAlteracao;
    private String descricaoOcorrencia;
    private Short indicadorAceitacao;
    private BigDecimal valorDocumento;

    private ArrecadadorMovimento arrecadadorMovimento;
    private RegistroCodigo registroCodigo;
    private Imovel imovel;

    private ContaGeral contaGeral;
    private GuiaPagamentoGeral guiaPagamentoGeral;
    private CobrancaDocumento cobrancaDocumento;
    private Fatura fatura;
    
    
    public final static Short INDICADOR_ACEITO = 1;
    public final static String DESCRICAO_INDICADOR_ACEITO = "ACEITO";
    
    public final static Short INDICADOR_NAO_ACEITO = 2;
    public final static String DESCRICAO_INDICADOR_NAO_ACEITO = "NÃO ACEITO";

    public ArrecadadorMovimentoItem(String conteudoRegistro, Date ultimaAlteracao, String descricaoOcorrencia, Short indicadorAceitacao, gcom.arrecadacao.ArrecadadorMovimento arrecadadorMovimento, gcom.arrecadacao.RegistroCodigo registroCodigo) {
        this.conteudoRegistro = conteudoRegistro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.descricaoOcorrencia = descricaoOcorrencia;
        this.indicadorAceitacao = indicadorAceitacao;
        this.arrecadadorMovimento = arrecadadorMovimento;
        this.registroCodigo = registroCodigo;
    }

    public ArrecadadorMovimentoItem() {
    }

    public ArrecadadorMovimentoItem(gcom.arrecadacao.ArrecadadorMovimento arrecadadorMovimento, gcom.arrecadacao.RegistroCodigo registroCodigo) {
        this.arrecadadorMovimento = arrecadadorMovimento;
        this.registroCodigo = registroCodigo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConteudoRegistro() {
        return this.conteudoRegistro;
    }

    public void setConteudoRegistro(String conteudoRegistro) {
        this.conteudoRegistro = conteudoRegistro;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String getDescricaoOcorrencia() {
        return this.descricaoOcorrencia;
    }

    public void setDescricaoOcorrencia(String descricaoOcorrencia) {
        this.descricaoOcorrencia = descricaoOcorrencia;
    }

    public Short getIndicadorAceitacao() {
        return this.indicadorAceitacao;
    }

    public void setIndicadorAceitacao(Short indicadorAceitacao) {
        this.indicadorAceitacao = indicadorAceitacao;
    }

    public gcom.arrecadacao.ArrecadadorMovimento getArrecadadorMovimento() {
        return this.arrecadadorMovimento;
    }

    public void setArrecadadorMovimento(gcom.arrecadacao.ArrecadadorMovimento arrecadadorMovimento) {
        this.arrecadadorMovimento = arrecadadorMovimento;
    }

    public gcom.arrecadacao.RegistroCodigo getRegistroCodigo() {
        return this.registroCodigo;
    }

    public void setRegistroCodigo(gcom.arrecadacao.RegistroCodigo registroCodigo) {
        this.registroCodigo = registroCodigo;
    }

    public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}

	public CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}

	public void setCobrancaDocumento(CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	
	public void preencherDocumento(Integer tipoDocumento, Integer idDocumento) {
		if (tipoDocumento != null) {
			if (tipoDocumento.intValue() == DocumentoTipo.CONTA) {
				this.contaGeral = new ContaGeral(idDocumento);
			} else if (tipoDocumento.intValue() == DocumentoTipo.GUIA_PAGAMENTO) {
				this.guiaPagamentoGeral = new GuiaPagamentoGeral(idDocumento);
			} else if (tipoDocumento.intValue() == DocumentoTipo.DOCUMENTO_COBRANCA) {
				this.cobrancaDocumento = new CobrancaDocumento(idDocumento);
			} else if (tipoDocumento.intValue() == DocumentoTipo.FATURA_CLIENTE) {
				this.fatura = new Fatura(idDocumento);
			}
		}
	}
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}
