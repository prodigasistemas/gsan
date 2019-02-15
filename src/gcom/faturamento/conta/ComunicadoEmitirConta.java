package gcom.faturamento.conta;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class ComunicadoEmitirConta extends ObjetoTransacao {

	private static final long serialVersionUID = -6870445042577196496L;

	public final static Integer ALTO_CONSUMO = new Integer(0);
	public final static Integer ALTERACAO_CADASTRAL = new Integer(1);
	
	private Integer id;
	private Imovel imovel;
	private Integer referencia;
	private Integer tipoComunicado;
	private Short indicadorEmitido;
	private Date dataGeracao;
	private Date ultimaAlteracao;
	
	public ComunicadoEmitirConta() {}
	
	public ComunicadoEmitirConta(Integer idImovel, Integer referencia, Integer tipoComunicado, Short indicadorEmitido, Date dataGeracao) {
		this.imovel = new Imovel(idImovel);
		this.referencia = referencia;
		this.tipoComunicado = tipoComunicado;
		this.ultimaAlteracao = new Date();
		this.indicadorEmitido = indicadorEmitido;
		this.dataGeracao = dataGeracao;
	}
	
	public ComunicadoEmitirConta(Integer idImovel, Integer referencia, Integer tipoComunicado) {
		this.imovel = new Imovel(idImovel);
		this.referencia = referencia;
		this.tipoComunicado = tipoComunicado;
		this.ultimaAlteracao = new Date();
		this.dataGeracao = new Date();
		this.indicadorEmitido = ConstantesSistema.NAO;
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
	
	public Integer getTipoComunicado() {
		return tipoComunicado;
	}

	public void setTipoComunicado(Integer tipoComunicado) {
		this.tipoComunicado = tipoComunicado;
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

	public Short getIndicadorEmitido() {
		return indicadorEmitido;
	}

	public void setIndicadorEmitido(Short indicadorEmitido) {
		this.indicadorEmitido = indicadorEmitido;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public void naoEmitir() {
		this.indicadorEmitido = ConstantesSistema.NAO;
	}
}
