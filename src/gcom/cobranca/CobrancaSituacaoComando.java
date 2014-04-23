package gcom.cobranca;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

public class CobrancaSituacaoComando implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;
    
    private Integer codigoSetorComercialInicial;
    
    private Integer codigoSetorComercialFinal;
    
    private Integer numeroQuadraInicial;
    
    private Integer numeroQuadraFinal;
    
    private Integer numeroLoteInicial;
    
    private Integer numeroLoteFinal;
    
    private Integer numeroSubLoteInicial;
    
    private Integer numeroSubLoteFinal;
    
    private Integer codigoRotaInicial;
    
    private Integer codigoRotaFinal;
    private Integer sequencialRotaInicial;
    
    private Integer sequencialRotaFinal;
    
    private Integer quantidadeImoveis;
    
    private Short indicadorComando;
    
    private Integer anoMesInicialSituacaoCobranca;
    
    private Integer anoMesFinalSituacaoCobranca;
    
    private String observacao;
    
    private Date ultimaAlteracao;
    
    private Imovel imovel;
    
    private Localidade localidadeInicial;
    
    private Localidade localidadeFinal;
    
    private Categoria categoria1;
    
    private Categoria categoria2;
    
    private Categoria categoria3;
    
    private Categoria categoria4;
    
    private Usuario usuario;
    
  
    public static Integer COMANDO_INSERIR = 1;
    public static Integer COMANDO_RETIRAR = 2;
    
    /** persistent field */
    private gcom.cobranca.CobrancaSituacaoTipo cobrancaSituacaoTipo;
    
    private gcom.cobranca.CobrancaSituacaoMotivo cobrancaSituacaoMotivo;

	public Integer getAnoMesFinalSituacaoCobranca() {
		return anoMesFinalSituacaoCobranca;
	}

	public void setAnoMesFinalSituacaoCobranca(Integer anoMesFinalSituacaoCobranca) {
		this.anoMesFinalSituacaoCobranca = anoMesFinalSituacaoCobranca;
	}

	public Integer getAnoMesInicialSituacaoCobranca() {
		return anoMesInicialSituacaoCobranca;
	}

	public void setAnoMesInicialSituacaoCobranca(
			Integer anoMesInicialSituacaoCobranca) {
		this.anoMesInicialSituacaoCobranca = anoMesInicialSituacaoCobranca;
	}

	public Categoria getCategoria1() {
		return categoria1;
	}

	public void setCategoria1(Categoria categoria1) {
		this.categoria1 = categoria1;
	}

	public Categoria getCategoria2() {
		return categoria2;
	}

	public void setCategoria2(Categoria categoria2) {
		this.categoria2 = categoria2;
	}

	public Categoria getCategoria3() {
		return categoria3;
	}

	public void setCategoria3(Categoria categoria3) {
		this.categoria3 = categoria3;
	}

	public Categoria getCategoria4() {
		return categoria4;
	}

	public void setCategoria4(Categoria categoria4) {
		this.categoria4 = categoria4;
	}

	public Integer getCodigoRotaFinal() {
		return codigoRotaFinal;
	}

	public void setCodigoRotaFinal(Integer codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}

	public Integer getCodigoRotaInicial() {
		return codigoRotaInicial;
	}

	public void setCodigoRotaInicial(Integer codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}

	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
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

	public Short getIndicadorComando() {
		return indicadorComando;
	}

	public void setIndicadorComando(Short indicadorComando) {
		this.indicadorComando = indicadorComando;
	}

	

	public Localidade getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Localidade localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Localidade getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Localidade localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Integer getNumeroLoteFinal() {
		return numeroLoteFinal;
	}

	public void setNumeroLoteFinal(Integer numeroLoteFinal) {
		this.numeroLoteFinal = numeroLoteFinal;
	}

	public Integer getNumeroLoteInicial() {
		return numeroLoteInicial;
	}

	public void setNumeroLoteInicial(Integer numeroLoteInicial) {
		this.numeroLoteInicial = numeroLoteInicial;
	}

	public Integer getNumeroQuadraFinal() {
		return numeroQuadraFinal;
	}

	public void setNumeroQuadraFinal(Integer numeroQuadraFinal) {
		this.numeroQuadraFinal = numeroQuadraFinal;
	}

	public Integer getNumeroQuadraInicial() {
		return numeroQuadraInicial;
	}

	public void setNumeroQuadraInicial(Integer numeroQuadraInicial) {
		this.numeroQuadraInicial = numeroQuadraInicial;
	}

	public Integer getNumeroSubLoteFinal() {
		return numeroSubLoteFinal;
	}

	public void setNumeroSubLoteFinal(Integer numeroSubLoteFinal) {
		this.numeroSubLoteFinal = numeroSubLoteFinal;
	}

	public Integer getNumeroSubLoteInicial() {
		return numeroSubLoteInicial;
	}

	public void setNumeroSubLoteInicial(Integer numeroSubLoteInicial) {
		this.numeroSubLoteInicial = numeroSubLoteInicial;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getQuantidadeImoveis() {
		return quantidadeImoveis;
	}

	public void setQuantidadeImoveis(Integer quantidadeImoveis) {
		this.quantidadeImoveis = quantidadeImoveis;
	}

	public Integer getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(Integer sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public Integer getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(Integer sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
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

	public gcom.cobranca.CobrancaSituacaoMotivo getCobrancaSituacaoMotivo() {
		return cobrancaSituacaoMotivo;
	}

	public void setCobrancaSituacaoMotivo(
			gcom.cobranca.CobrancaSituacaoMotivo cobrancaSituacaoMotivo) {
		this.cobrancaSituacaoMotivo = cobrancaSituacaoMotivo;
	}

	public gcom.cobranca.CobrancaSituacaoTipo getCobrancaSituacaoTipo() {
		return cobrancaSituacaoTipo;
	}

	public void setCobrancaSituacaoTipo(
			gcom.cobranca.CobrancaSituacaoTipo cobrancaSituacaoTipo) {
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
	}
    

}
