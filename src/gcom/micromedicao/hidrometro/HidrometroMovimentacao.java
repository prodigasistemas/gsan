package gcom.micromedicao.hidrometro;

import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class HidrometroMovimentacao implements Serializable {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Date data; 

	/** nullable persistent field */
	private Date hora;

	/** nullable persistent field */
	private String parecer;

	/** nullable persistent field */
	private String quantidade;

	/** nullable persistent field */
	private String fixo;

	/** nullable persistent field */
	private String faixaInicial;

	/** nullable persistent field */
	private String faixaFinal;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao hidrometroMotivoMovimentacao;

	/** persistent field */
	private gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemDestino;

	/** persistent field */
	private gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemOrigem;

	/** persistent field */
	private Set hidrometroMovimentados;

	/** persistent field */
	private Usuario usuario;
	
	/** persistent field */
	private String dataMovimentacaoInicial;
	
	/** persistent field */
	private String dataMovimentacaoFinal;
	
	/** persistent field */
	private String horaMovimentacaoInicial;

	/** persistent field */
	private String horaMovimentacaoFinal;
	
	
	/** full constructor */
	public HidrometroMovimentacao(
			Date data,
			Date hora,
			String parecer,
			Date ultimaAlteracao,
			gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao hidrometroMotivoMovimentacao,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemDestino,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemOrigem,
			Usuario usuario) {
		this.data = data;
		this.hora = hora;
		this.parecer = parecer;
		this.ultimaAlteracao = ultimaAlteracao;
		this.hidrometroMotivoMovimentacao = hidrometroMotivoMovimentacao;
		this.hidrometroLocalArmazenagemDestino = hidrometroLocalArmazenagemDestino;
		this.hidrometroLocalArmazenagemOrigem = hidrometroLocalArmazenagemOrigem;
		this.usuario = usuario;
	}

	/** default constructor */
	public HidrometroMovimentacao() {
	}

	/** minimal constructor */
	public HidrometroMovimentacao(
			gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao hidrometroMotivoMovimentacao,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemDestino,
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemOrigem,
			Usuario usuario) {

		this.hidrometroMotivoMovimentacao = hidrometroMotivoMovimentacao;
		this.hidrometroLocalArmazenagemDestino = hidrometroLocalArmazenagemDestino;
		this.hidrometroLocalArmazenagemOrigem = hidrometroLocalArmazenagemOrigem;
		this.usuario = usuario;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getParecer() {
		return this.parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao getHidrometroMotivoMovimentacao() {
		return this.hidrometroMotivoMovimentacao;
	}

	public Set getHidrometroMovimentados() {
		return hidrometroMovimentados;
	}

	public void setHidrometroMovimentados(Set hidrometroMovimentados) {
		this.hidrometroMovimentados = hidrometroMovimentados;
	}

	public void setHidrometroMotivoMovimentacao(
			gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao hidrometroMotivoMovimentacao) {
		this.hidrometroMotivoMovimentacao = hidrometroMotivoMovimentacao;
	}

	public gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem getHidrometroLocalArmazenagemDestino() {
		return this.hidrometroLocalArmazenagemDestino;
	}

	public void setHidrometroLocalArmazenagemDestino(
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemDestino) {
		this.hidrometroLocalArmazenagemDestino = hidrometroLocalArmazenagemDestino;
	}

	public gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem getHidrometroLocalArmazenagemOrigem() {
		return this.hidrometroLocalArmazenagemOrigem;
	}

	public void setHidrometroLocalArmazenagemOrigem(
			gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem hidrometroLocalArmazenagemOrigem) {
		this.hidrometroLocalArmazenagemOrigem = hidrometroLocalArmazenagemOrigem;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getFaixaFinal() {
		return faixaFinal;
	}

	public void setFaixaFinal(String faixaFinal) {
		this.faixaFinal = faixaFinal;
	}

	public String getFaixaInicial() {
		return faixaInicial;
	}

	public void setFaixaInicial(String faixaInicial) {
		this.faixaInicial = faixaInicial;
	}

	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof HidrometroMovimentacao))
			return false;
		HidrometroMovimentacao castOther = (HidrometroMovimentacao) other;
		return new EqualsBuilder()
			.append(this.getId(),castOther.getId())
			.append(this.getData(),castOther.getData())
			.append(this.getHora(),castOther.getHora())
			.append(this.getParecer(),castOther.getParecer())
			.append(this.getHidrometroMotivoMovimentacao().getId(),castOther.getHidrometroMotivoMovimentacao().getId())
			.append(this.getHidrometroLocalArmazenagemDestino().getId(),castOther.getHidrometroLocalArmazenagemDestino().getId())
			.append(this.getHidrometroLocalArmazenagemOrigem().getId(),castOther.getHidrometroLocalArmazenagemOrigem().getId())
			.isEquals();
	}

	public String getDataMovimentacaoFinal() {
		return dataMovimentacaoFinal;
	}

	public void setDataMovimentacaoFinal(String dataMovimentacaoFinal) {
		this.dataMovimentacaoFinal = dataMovimentacaoFinal;
	}

	public String getDataMovimentacaoInicial() {
		return dataMovimentacaoInicial;
	}

	public void setDataMovimentacaoInicial(String dataMovimentacaoInicial) {
		this.dataMovimentacaoInicial = dataMovimentacaoInicial;
	}

	public String getHoraMovimentacaoFinal() {
		return horaMovimentacaoFinal;
	}

	public void setHoraMovimentacaoFinal(String horaMovimentacaoFinal) {
		this.horaMovimentacaoFinal = horaMovimentacaoFinal;
	}

	public String getHoraMovimentacaoInicial() {
		return horaMovimentacaoInicial;
	}

	public void setHoraMovimentacaoInicial(String horaMovimentacaoInicial) {
		this.horaMovimentacaoInicial = horaMovimentacaoInicial;
	}


}
