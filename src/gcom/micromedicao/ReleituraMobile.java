package gcom.micromedicao;

import gcom.cadastro.imovel.Imovel;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class ReleituraMobile implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer indicadorReleitura;
	
	private Integer anoMesReferencia;
	
	private Integer indicadorMensagemRecebida;
	
	private Date ultimaAlteracao;
	
	private Usuario usuario;
	
	private Imovel imovel;
	
	private Integer leituraAtualAgua;	
	private Integer leituraAnteriorAgua;
	private Integer leituraAtualPoco;	
	private Integer leituraAnteriorPoco;	
	
	private LeituraAnormalidade leituraAnormalidadeAnteriorAgua;	
	private LeituraAnormalidade leituraAnormalidadeAtualAgua;
	private LeituraAnormalidade leituraAnormalidadeAnteriorPoco;	
	private LeituraAnormalidade leituraAnormalidadeAtualPoco;
	

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Integer getIndicadorMensagemRecebida() {
		return indicadorMensagemRecebida;
	}

	public void setIndicadorMensagemRecebida(Integer indicadorMensagemRecebida) {
		this.indicadorMensagemRecebida = indicadorMensagemRecebida;
	}

	public Integer getIndicadorReleitura() {
		return indicadorReleitura;
	}

	public void setIndicadorReleitura(Integer indicadorReleitura) {
		this.indicadorReleitura = indicadorReleitura;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LeituraAnormalidade getLeituraAnormalidadeAnteriorAgua() {
		return leituraAnormalidadeAnteriorAgua;
	}

	public void setLeituraAnormalidadeAnteriorAgua(
			LeituraAnormalidade leituraAnormalidadeAnteriorAgua) {
		this.leituraAnormalidadeAnteriorAgua = leituraAnormalidadeAnteriorAgua;
	}

	public LeituraAnormalidade getLeituraAnormalidadeAnteriorPoco() {
		return leituraAnormalidadeAnteriorPoco;
	}

	public void setLeituraAnormalidadeAnteriorPoco(
			LeituraAnormalidade leituraAnormalidadeAnteriorPoco) {
		this.leituraAnormalidadeAnteriorPoco = leituraAnormalidadeAnteriorPoco;
	}

	public LeituraAnormalidade getLeituraAnormalidadeAtualAgua() {
		return leituraAnormalidadeAtualAgua;
	}

	public void setLeituraAnormalidadeAtualAgua(
			LeituraAnormalidade leituraAnormalidadeAtualAgua) {
		this.leituraAnormalidadeAtualAgua = leituraAnormalidadeAtualAgua;
	}

	public LeituraAnormalidade getLeituraAnormalidadeAtualPoco() {
		return leituraAnormalidadeAtualPoco;
	}

	public void setLeituraAnormalidadeAtualPoco(
			LeituraAnormalidade leituraAnormalidadeAtualPoco) {
		this.leituraAnormalidadeAtualPoco = leituraAnormalidadeAtualPoco;
	}

	public Integer getLeituraAnteriorAgua() {
		return leituraAnteriorAgua;
	}

	public void setLeituraAnteriorAgua(Integer leituraAnteriorAgua) {
		this.leituraAnteriorAgua = leituraAnteriorAgua;
	}

	public Integer getLeituraAnteriorPoco() {
		return leituraAnteriorPoco;
	}

	public void setLeituraAnteriorPoco(Integer leituraAnteriorPoco) {
		this.leituraAnteriorPoco = leituraAnteriorPoco;
	}

	public Integer getLeituraAtualAgua() {
		return leituraAtualAgua;
	}

	public void setLeituraAtualAgua(Integer leituraAtualAgua) {
		this.leituraAtualAgua = leituraAtualAgua;
	}

	public Integer getLeituraAtualPoco() {
		return leituraAtualPoco;
	}

	public void setLeituraAtualPoco(Integer leituraAtualPoco) {
		this.leituraAtualPoco = leituraAtualPoco;
	}	
}
