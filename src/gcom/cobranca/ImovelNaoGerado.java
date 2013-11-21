package gcom.cobranca;

import gcom.cadastro.imovel.Imovel;

import java.util.Date;

public class ImovelNaoGerado {

	private Integer id;
	
	
	
	private Date ultimaAlteracao;
	
	private Imovel imovel;
	
	private CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma;

	private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;
	
	private MotivoNaoGeracaoDocCobranca motivoNaoGeracaoDocCobranca;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public CobrancaAcaoAtividadeCronograma getCobrancaAcaoAtividadeCronograma() {
		return cobrancaAcaoAtividadeCronograma;
	}

	public void setCobrancaAcaoAtividadeCronograma(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma) {
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
	}

	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}

	public void setCobrancaAcaoAtividadeComando(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}

	public MotivoNaoGeracaoDocCobranca getMotivoNaoGeracaoDocCobranca() {
		return motivoNaoGeracaoDocCobranca;
	}

	public void setMotivoNaoGeracaoDocCobranca(
			MotivoNaoGeracaoDocCobranca motivoNaoGeracaoDocCobranca) {
		this.motivoNaoGeracaoDocCobranca = motivoNaoGeracaoDocCobranca;
	}	
}
