package gcom.cobranca;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class CobrancaDocumentoImpressao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;

    private String linhaTxt;

    private Date ultimaAlteracao;

    private Integer sequencialImpressao;
    
    private gcom.cobranca.CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;

    private gcom.cobranca.CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma;

    private String conteudoFichaCompensacao;

	public CobrancaDocumentoImpressao(Integer id, String linhaTxt, Date ultimaAlteracao, Integer sequencialImpressao, CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.linhaTxt = linhaTxt;
		this.ultimaAlteracao = ultimaAlteracao;
		this.sequencialImpressao = sequencialImpressao;
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
	}

	public gcom.cobranca.CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}

	public void setCobrancaAcaoAtividadeComando(
			gcom.cobranca.CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}

	public gcom.cobranca.CobrancaAcaoAtividadeCronograma getCobrancaAcaoAtividadeCronograma() {
		return cobrancaAcaoAtividadeCronograma;
	}

	public void setCobrancaAcaoAtividadeCronograma(
			gcom.cobranca.CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma) {
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLinhaTxt() {
		return linhaTxt;
	}

	public void setLinhaTxt(String linhaTxt) {
		this.linhaTxt = linhaTxt;
	}

	public Integer getSequencialImpressao() {
		return sequencialImpressao;
	}

	public void setSequencialImpressao(Integer sequencialImpressao) {
		this.sequencialImpressao = sequencialImpressao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public CobrancaDocumentoImpressao(){
		super();
	}

	public String getConteudoFichaCompensacao() {
		return conteudoFichaCompensacao;
	}

	public void setConteudoFichaCompensacao(String conteudoFichaCompensacao) {
		this.conteudoFichaCompensacao = conteudoFichaCompensacao;
	}

    
}
