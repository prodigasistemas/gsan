package gcom.faturamento.bean;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Encapsula as informações necessárias para retificar contas de um conjunto de imóveis
 *
 * @author Raphael Rossiter
 * 
 * @date 02/07/2009
 */
public class RetificarConjuntoContaConsumosHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Collection colecaoImovel;
	
	private Integer idGrupoFaturamento;
	
	private Integer codigoCliente;
	
	private Integer codigoClienteSuperior;
	
	private Integer anoMes; 
	
	private Integer anoMesFim;
	
	private ContaMotivoRetificacao contaMotivoRetificacao;
	
	private Usuario usuarioLogado;
	
	private Date dataVencimentoContaInicio; 
	
	private Date dataVencimentoContaFim;
	
	private String indicadorContaPaga;
	
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	
	private Integer consumoAgua;
	
	private Integer volumeEsgoto;
	
	private Date dataVencimentoContaRetificacao;
	
	private Short indicadorCategoriaEconomiaConta;
	
	public RetificarConjuntoContaConsumosHelper(){}

	public Integer getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(Integer anoMes) {
		this.anoMes = anoMes;
	}

	public Integer getAnoMesFim() {
		return anoMesFim;
	}

	public void setAnoMesFim(Integer anoMesFim) {
		this.anoMesFim = anoMesFim;
	}

	public Collection getColecaoImovel() {
		return colecaoImovel;
	}

	public void setColecaoImovel(Collection colecaoImovel) {
		this.colecaoImovel = colecaoImovel;
	}

	public ContaMotivoRetificacao getContaMotivoRetificacao() {
		return contaMotivoRetificacao;
	}

	public void setContaMotivoRetificacao(
			ContaMotivoRetificacao contaMotivoRetificacao) {
		this.contaMotivoRetificacao = contaMotivoRetificacao;
	}

	public Date getDataVencimentoContaFim() {
		return dataVencimentoContaFim;
	}

	public void setDataVencimentoContaFim(Date dataVencimentoContaFim) {
		this.dataVencimentoContaFim = dataVencimentoContaFim;
	}

	public Date getDataVencimentoContaInicio() {
		return dataVencimentoContaInicio;
	}

	public void setDataVencimentoContaInicio(Date dataVencimentoContaInicio) {
		this.dataVencimentoContaInicio = dataVencimentoContaInicio;
	}

	public String getIndicadorContaPaga() {
		return indicadorContaPaga;
	}

	public void setIndicadorContaPaga(String indicadorContaPaga) {
		this.indicadorContaPaga = indicadorContaPaga;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public Integer getVolumeEsgoto() {
		return volumeEsgoto;
	}

	public void setVolumeEsgoto(Integer volumeEsgoto) {
		this.volumeEsgoto = volumeEsgoto;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public Date getDataVencimentoContaRetificacao() {
		return dataVencimentoContaRetificacao;
	}

	public void setDataVencimentoContaRetificacao(
			Date dataVencimentoContaRetificacao) {
		this.dataVencimentoContaRetificacao = dataVencimentoContaRetificacao;
	}

	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Integer getCodigoClienteSuperior() {
		return codigoClienteSuperior;
	}

	public void setCodigoClienteSuperior(Integer codigoClienteSuperior) {
		this.codigoClienteSuperior = codigoClienteSuperior;
	}

	public Short getIndicadorCategoriaEconomiaConta() {
		return indicadorCategoriaEconomiaConta;
	}

	public void setIndicadorCategoriaEconomiaConta(
			Short indicadorCategoriaEconomiaConta) {
		this.indicadorCategoriaEconomiaConta = indicadorCategoriaEconomiaConta;
	}
}
