package gcom.gerencial.cadastro;

/**
 * 
 *  
 *
 * @author Ivan Sérgio
 * @date 18/04/2007
 */
public class ResumoLigacaoEconomiaRegiao {	

	private java.lang.Integer id;
	private java.lang.Integer anoMesReferencia;
	private gcom.cadastro.geografico.Regiao regiao;
	private gcom.cadastro.geografico.Microrregiao microrregiao;
	private gcom.cadastro.geografico.Municipio municipio;
	private gcom.cadastro.geografico.Bairro bairro;
	private gcom.cadastro.imovel.ImovelPerfil perfilImovel;
	private gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao ligacaoAguaSituacao;
	private gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	private gcom.cadastro.imovel.Categoria categoria;
	private gcom.cadastro.imovel.Subcategoria subcategoria;
	private gcom.cadastro.cliente.EsferaPoder esferaPoder;
	private gcom.cadastro.cliente.ClienteTipo clienteTipo;
	private gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil;
	private gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil;
    private Short indicadorHidrometro;
    private Short indicadorHidrometroPoco;
    private Short indicadorVolumeMinimoAguaFixado;
    private Short indicadorVolumeMinimoEsgotoFixado;
    private Short indicadorPoco;
	private java.lang.Integer quantidadeLigacoes;
	private java.lang.Integer quantidadeEconomias;
	
	public ResumoLigacaoEconomiaRegiao(
			java.lang.Integer anoMesReferencia,
			gcom.cadastro.geografico.Regiao regiao,
			gcom.cadastro.geografico.Microrregiao microrregiao,
			gcom.cadastro.geografico.Municipio municipio,
			gcom.cadastro.geografico.Bairro bairro,
			gcom.cadastro.imovel.ImovelPerfil perfilImovel,
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao ligacaoAguaSituacao,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			gcom.cadastro.imovel.Categoria categoria,
			gcom.cadastro.imovel.Subcategoria subcategoria,
			gcom.cadastro.cliente.EsferaPoder esferaPoder,
			gcom.cadastro.cliente.ClienteTipo clienteTipo,
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil,
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil,
		    Short indicadorHidrometro,
		    Short indicadorHidrometroPoco,
		    Short indicadorVolumeMinimoAguaFixado,
		    Short indicadorVolumeMinimoEsgotoFixado,
		    Short indicadorPoco,
			java.lang.Integer quantidadeLigacoes,
			java.lang.Integer quantidadeEconomias){
		
		this.anoMesReferencia = anoMesReferencia;
		this.regiao = regiao;
		this.microrregiao = microrregiao;
		this.municipio = municipio;
		this.bairro = bairro;
		this.perfilImovel = perfilImovel;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.esferaPoder = esferaPoder;
		this.clienteTipo = clienteTipo;
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
		this.ligacaoEsgotoPerfil = ligacaoEsgotoPerfil;
		this.indicadorHidrometro = indicadorHidrometro;
		this.indicadorHidrometroPoco = indicadorHidrometroPoco;
		this.indicadorVolumeMinimoAguaFixado = indicadorVolumeMinimoAguaFixado;
		this.indicadorVolumeMinimoEsgotoFixado = indicadorVolumeMinimoEsgotoFixado;
		this.indicadorPoco = indicadorPoco;
		this.quantidadeLigacoes = quantidadeLigacoes;
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public java.lang.Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(java.lang.Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public gcom.cadastro.geografico.Bairro getBairro() {
		return bairro;
	}

	public void setBairro(gcom.cadastro.geografico.Bairro bairro) {
		this.bairro = bairro;
	}

	public gcom.cadastro.imovel.Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(gcom.cadastro.imovel.Categoria categoria) {
		this.categoria = categoria;
	}

	public gcom.cadastro.cliente.ClienteTipo getClienteTipo() {
		return clienteTipo;
	}

	public void setClienteTipo(gcom.cadastro.cliente.ClienteTipo clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	public gcom.cadastro.cliente.EsferaPoder getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(gcom.cadastro.cliente.EsferaPoder esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public Short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(Short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public Short getIndicadorHidrometroPoco() {
		return indicadorHidrometroPoco;
	}

	public void setIndicadorHidrometroPoco(Short indicadorHidrometroPoco) {
		this.indicadorHidrometroPoco = indicadorHidrometroPoco;
	}

	public Short getIndicadorPoco() {
		return indicadorPoco;
	}

	public void setIndicadorPoco(Short indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}

	public Short getIndicadorVolumeMinimoAguaFixado() {
		return indicadorVolumeMinimoAguaFixado;
	}

	public void setIndicadorVolumeMinimoAguaFixado(
			Short indicadorVolumeMinimoAguaFixado) {
		this.indicadorVolumeMinimoAguaFixado = indicadorVolumeMinimoAguaFixado;
	}

	public Short getIndicadorVolumeMinimoEsgotoFixado() {
		return indicadorVolumeMinimoEsgotoFixado;
	}

	public void setIndicadorVolumeMinimoEsgotoFixado(
			Short indicadorVolumeMinimoEsgotoFixado) {
		this.indicadorVolumeMinimoEsgotoFixado = indicadorVolumeMinimoEsgotoFixado;
	}

	public gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil getLigacaoAguaPerfil() {
		return ligacaoAguaPerfil;
	}

	public void setLigacaoAguaPerfil(
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil ligacaoAguaPerfil) {
		this.ligacaoAguaPerfil = ligacaoAguaPerfil;
	}

	public gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(
			gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil getLigacaoEsgotoPerfil() {
		return ligacaoEsgotoPerfil;
	}

	public void setLigacaoEsgotoPerfil(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil ligacaoEsgotoPerfil) {
		this.ligacaoEsgotoPerfil = ligacaoEsgotoPerfil;
	}

	public gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(
			gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public gcom.cadastro.geografico.Microrregiao getMicrorregiao() {
		return microrregiao;
	}

	public void setMicrorregiao(gcom.cadastro.geografico.Microrregiao microrregiao) {
		this.microrregiao = microrregiao;
	}

	public gcom.cadastro.geografico.Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(gcom.cadastro.geografico.Municipio municipio) {
		this.municipio = municipio;
	}

	public gcom.cadastro.imovel.ImovelPerfil getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(gcom.cadastro.imovel.ImovelPerfil perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public java.lang.Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(java.lang.Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public java.lang.Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	public void setQuantidadeLigacoes(java.lang.Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	public gcom.cadastro.geografico.Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(gcom.cadastro.geografico.Regiao regiao) {
		this.regiao = regiao;
	}

	public gcom.cadastro.imovel.Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(gcom.cadastro.imovel.Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
}
