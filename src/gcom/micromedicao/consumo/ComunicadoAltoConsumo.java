package gcom.micromedicao.consumo;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class ComunicadoAltoConsumo extends ObjetoTransacao {

	private static final long serialVersionUID = -6870445042577196496L;

	private Integer id;
	private Imovel imovel;
	private Integer referencia;
	private Date ultimaAlteracao;
	
	public ComunicadoAltoConsumo() {}
	
	public ComunicadoAltoConsumo(Integer idImovel, Integer referencia) {
		this.imovel = new Imovel(idImovel);
		this.referencia = referencia;
		this.ultimaAlteracao = new Date();
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

	public Integer getReferencia() {
		return referencia;
	}
	
	public String getReferenciaFormatada() {
		return Util.formatarAnoMesParaMesAno(referencia.toString());
	}

	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}

	@Override
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}
	
	public String getGeracao() {
		return Util.formatarData(this.ultimaAlteracao);
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		return null;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}

}
