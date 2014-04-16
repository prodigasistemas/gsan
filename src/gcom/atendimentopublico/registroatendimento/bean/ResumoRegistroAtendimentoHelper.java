package gcom.atendimentopublico.registroatendimento.bean;

/**
 * Classe responsável por ajudar o caso de uso [UC0567] Gerar Resumo Registro de
 * Atendimento
 * 
 * @author Thiago Tenório, Ivan Sérgio
 * @date 29/04/2007, 16/08/2007, 14/01/2009
 * @alteracao 16/08/2007 - Duas novas quebras: Unidade de Solicitacao, Unidade de Encerramento;
 * 			  14/01/2009 - CRC811 - Adicionado o campo amen_id(Motivo Encerramento) a consulta;
 */
public class ResumoRegistroAtendimentoHelper {

	private Integer idGerenciaRegional;

	private Integer idUnidadeNegocio;

	private Integer idLocalidade;

	private Integer idElo;

	private Integer idSolicitacaoTipo;

	private Integer idSetorComercial;

	private Integer idRota;

	private Integer idsolicitacaoTipoEspecificacao;

	private Integer idQuadra;

	private Integer idMeioSolicitacao;

	private Short indicadorAtendimentoOnline;

	private Integer idImovel;

	private Integer codigoSetorComercial;

	private Integer numeroQuadra;

	private Integer idPerfilImovel;

	private Integer idEsfera;

	private Integer idTipoClienteResponsavel;

	private Integer idSituacaoLigacaoAgua;

	private Integer idSituacaoLigacaoEsgoto;

	private Integer idCategoria;

	private Integer idSubCategoria;

	private Integer idPerfilLigacaoAgua;

	private Integer idPerfilLigacaoEsgoto;

	private Integer quantidadePendentesNoPrazo = 0;
	private Integer quantidadePendentesForaPrazo = 0;
	
	private Integer quantidadeBloqueada = 0;
	
	private Integer quantidadeEncerradasNoPrazo = 0;
	private Integer quantidadeEncerradasForaPrazo = 0;
	
	private Integer quantidadeGerada = 0;
	
	private Integer quantidadeGeradasNoMesPrazo = 0;
	private Integer quantidadeGeradasNoMesForaPrazo = 0;
	
	private Integer idUnidadeSolicitacao;
	
	private Integer idUnidadeEncerramento;
	
	private Short codigoRota;
	
	private Integer idMotivoEncerramento;

	public Integer getQuantidadeBloqueada() {
		return quantidadeBloqueada;
	}

	public void setQuantidadeBloqueada(Integer quantidadeBloqueada) {
		this.quantidadeBloqueada = quantidadeBloqueada;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	/**
	 * Construtor com a sequencia correta de quebras para o caso de uso UC[567] -
	 * Gerar resumo dos Registro de Atendimentos
	 * 
	 * OBS - Duas quebras adicionais nao foram passadas neste contrutor, a
	 * saber, idCategoria e idSubCatergoria, pois no momento da criacao deste
	 * objeto essas informacoes nao estao disponiveis.
	 * 
	 * @param idGerenciaRegional
	 * @param idUnidadeNegocio
	 * @param idLocalidade
	 * @param idElo
	 * @param idSetorComercial
	 * @param idRota
	 * @param idQuadra
	 * @param codigoSetorComercial
	 * @param numeroQuadra
	 * @param idPerfilImovel    
	 * @param idSituacaoLigacaoAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param idPerfilLigacaoAgua
	 * @param idPerfilLigacaoEsgoto 
	 * @param indicadorAtendimentoOnline  
	 * @param idSolicitacaoTipo
	 * @param idTipoEspecificacao
	 * @param idMeioSolicitacao
	 * @param qtPendentes
	 * @param qtEncerradas
	 * @param qtBloqueadas
	 */
	public ResumoRegistroAtendimentoHelper(
			Integer idGerenciaRegional,
			Integer idUnidadeNegocio,
			Integer idLocalidade,			
			Integer idElo,
			Integer idSetorComercial,
			Integer idRota,
			Integer idQuadra,
			Integer codigoSetorComercial,
			Integer numeroQuadra,
			Integer perfilImovel,
			Integer idSituacaoLigacaoAgua,
			Integer idSituacaoLigacaoEsgoto,
			Integer idPerfilLigacaiAgua,	
			Integer idPerfilLigacaoEsgoto,
			Short   indicadorAtendimentoOnline,
			Integer idTipoSolicitacao,
			Integer idTipoEspecificacao,
			Integer idMeioSolicitacao,
			Integer qtGerada,
			Integer qtPendentesNoPrazo,
			Integer qtPendentesForaPrazo,
			Integer qtEncerradasNoPrazo,
			Integer qtEncerradasForaPrazo,
			Integer qtBloqueadas,
			Integer qtGeradaNoMesPrazo,
			Integer qtGeradaNoMesForaPrazo,
			Short codigoRota) {
		
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idLocalidade = idLocalidade;
		this.idElo = idElo;
		this.idSetorComercial = idSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.idPerfilImovel = perfilImovel;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idPerfilLigacaoAgua = idPerfilLigacaiAgua;
		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;		
		this.indicadorAtendimentoOnline = indicadorAtendimentoOnline;		
		this.idSolicitacaoTipo = idTipoSolicitacao;				
		this.idsolicitacaoTipoEspecificacao = idTipoEspecificacao;
		this.idMeioSolicitacao = idMeioSolicitacao;
		this.quantidadeGerada = qtGerada;
		this.quantidadePendentesNoPrazo = qtPendentesNoPrazo;
		this.quantidadePendentesForaPrazo = qtPendentesForaPrazo;
		this.quantidadeEncerradasNoPrazo = qtEncerradasNoPrazo;
		this.quantidadeEncerradasForaPrazo = qtEncerradasForaPrazo;
		this.quantidadeBloqueada = qtBloqueadas;
		this.quantidadeGeradasNoMesPrazo = qtGeradaNoMesPrazo;
		this.quantidadeGeradasNoMesForaPrazo = qtGeradaNoMesForaPrazo;		
		this.codigoRota = codigoRota;
	}

	/**
	 * Compara duas properiedades inteiras, comparando seus valores para
	 * descobrirmos se sao iguais
	 * 
	 * @param pro1
	 *            Primeira propriedade
	 * @param pro2
	 *            Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais(Integer pro1, Integer pro2) {
		if (pro2 != null) {
			if (!pro2.equals(pro1)) {
				return false;
			}
		} else if (pro1 != null) {
			return false;
		}

		// Se chegou ate aqui quer dizer que as propriedades sao iguais
		return true;
	}
	
	private boolean propriedadesIguais(Short pro1, Short pro2) {
		if (pro2 != null) {
			if (!pro2.equals(pro1)) {
				return false;
			}
		} else if (pro1 != null) {
			return false;
		}

		// Se chegou ate aqui quer dizer que as propriedades sao iguais
		return true;
	}	

	/**
	 * Compara dois objetos levando em consideracao apenas as propriedades que
	 * identificam o agrupamento
	 * 
	 * @param obj
	 *            Objeto a ser comparado com a instancia deste objeto
	 */
	public boolean equals(Object obj) {

		if (!(obj instanceof ResumoRegistroAtendimentoHelper)) {
			return false;
		} else {
			ResumoRegistroAtendimentoHelper resumoTemp = (ResumoRegistroAtendimentoHelper) obj;

			// Verificamos se todas as propriedades que identificam o objeto sao
			// iguais
			return 
			propriedadesIguais( this.idGerenciaRegional , resumoTemp.idGerenciaRegional ) &&
			propriedadesIguais( this.idUnidadeNegocio , resumoTemp.idUnidadeNegocio ) &&
			propriedadesIguais( this.idLocalidade , resumoTemp.idLocalidade ) &&
			propriedadesIguais( this.idElo , resumoTemp.idElo ) &&
			propriedadesIguais( this.idSetorComercial , resumoTemp.idSetorComercial ) &&
			propriedadesIguais( this.idRota , resumoTemp.idRota ) &&
			propriedadesIguais( this.idQuadra , resumoTemp.idQuadra ) &&
			propriedadesIguais( this.codigoSetorComercial , resumoTemp.codigoSetorComercial ) &&
			propriedadesIguais( this.numeroQuadra , resumoTemp.numeroQuadra ) &&
			propriedadesIguais( this.idPerfilImovel , resumoTemp.idPerfilImovel ) &&
			propriedadesIguais( this.idEsfera , resumoTemp.idEsfera ) &&
			propriedadesIguais( this.idTipoClienteResponsavel , resumoTemp.idTipoClienteResponsavel ) &&
			propriedadesIguais( this.idSituacaoLigacaoAgua , resumoTemp.idSituacaoLigacaoAgua ) &&
			propriedadesIguais( this.idSituacaoLigacaoEsgoto , resumoTemp.idSituacaoLigacaoEsgoto ) &&
			propriedadesIguais( this.idCategoria , resumoTemp.idCategoria ) &&
			propriedadesIguais( this.idSubCategoria , resumoTemp.idSubCategoria ) &&
			propriedadesIguais( this.idPerfilLigacaoAgua , resumoTemp.idPerfilLigacaoAgua ) &&
			propriedadesIguais( this.idPerfilLigacaoEsgoto , resumoTemp.idPerfilLigacaoEsgoto ) &&
			propriedadesIguais( this.indicadorAtendimentoOnline , resumoTemp.indicadorAtendimentoOnline ) &&		
			propriedadesIguais( this.idSolicitacaoTipo , resumoTemp.idSolicitacaoTipo ) &&				
			propriedadesIguais( this.idsolicitacaoTipoEspecificacao , resumoTemp.idsolicitacaoTipoEspecificacao ) &&
			propriedadesIguais( this.idMeioSolicitacao , resumoTemp.idMeioSolicitacao ) &&
			propriedadesIguais( this.idUnidadeSolicitacao , resumoTemp.idUnidadeSolicitacao ) &&
			propriedadesIguais( this.idUnidadeEncerramento , resumoTemp.idUnidadeEncerramento );
			
		}
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}

	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	public Integer getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(Integer idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public Integer getIdEsfera() {
		return idEsfera;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public Integer getIdPerfilLigacaoAgua() {
		return idPerfilLigacaoAgua;
	}

	public Integer getIdPerfilLigacaoEsgoto() {
		return idPerfilLigacaoEsgoto;
	}

	public Integer getIdQuadra() {
		return idQuadra;
	}

	public Integer getIdMeioSolicitacao() {
		return idMeioSolicitacao;
	}

	public Short getIndicadorAtendimentoOnline() {
		return indicadorAtendimentoOnline;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public Integer getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}

	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}

	public Integer getIdTipoClienteResponsavel() {
		return idTipoClienteResponsavel;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public Integer getIdElo() {
		return idElo;
	}

	public Integer getIdSolicitacaoTipo() {
		return idSolicitacaoTipo;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdEsfera(Integer idEsfera) {
		this.idEsfera = idEsfera;
	}

	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}

	public void setIdTipoClienteResponsavel(Integer idTipoClienteResponsavel) {
		this.idTipoClienteResponsavel = idTipoClienteResponsavel;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public void setIdElo(Integer idElo) {
		if (idElo == null) {
			idElo = 0;

		} else {
			this.idElo = idElo;
		}
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public void setIdMeioSolicitacao(Integer idMeioSolicitacao) {
		this.idMeioSolicitacao = idMeioSolicitacao;
	}

	public void setIdPerfilLigacaoAgua(Integer idPerfilLigacaoAgua) {
		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;
	}

	public void setIdPerfilLigacaoEsgoto(Integer idPerfilLigacaoEsgoto) {
		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;
	}

	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public void setIdSituacaoLigacaoAgua(Integer idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}

	public void setIdSolicitacaoTipo(Integer idSolicitacaoTipo) {
		this.idSolicitacaoTipo = idSolicitacaoTipo;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public void setIndicadorAtendimentoOnline(Short indicadorAtendimentoOnline) {
		this.indicadorAtendimentoOnline = indicadorAtendimentoOnline;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getIdsolicitacaoTipoEspecificacao() {
		return idsolicitacaoTipoEspecificacao;
	}

	public void setIdsolicitacaoTipoEspecificacao(
			Integer idsolicitacaoTipoEspecificacao) {
		this.idsolicitacaoTipoEspecificacao = idsolicitacaoTipoEspecificacao;
	}

	public Integer getQuantidadeGerada() {
		return quantidadeGerada;
	}

	public void setQuantidadeGerada(Integer quantidadeGerada) {
		this.quantidadeGerada = quantidadeGerada;
	}

	public Integer getIdUnidadeEncerramento() {
		return idUnidadeEncerramento;
	}

	public void setIdUnidadeEncerramento(Integer idUnidadeEncerramento) {
		this.idUnidadeEncerramento = idUnidadeEncerramento;
	}

	public Integer getIdUnidadeSolicitacao() {
		return idUnidadeSolicitacao;
	}

	public void setIdUnidadeSolicitacao(Integer idUnidadeSolicitacao) {
		this.idUnidadeSolicitacao = idUnidadeSolicitacao;
	}

	public Integer getQuantidadeEncerradasForaPrazo() {
		return quantidadeEncerradasForaPrazo;
	}

	public void setQuantidadeEncerradasForaPrazo(
			Integer quantidadeEncerradasForaPrazo) {
		this.quantidadeEncerradasForaPrazo = quantidadeEncerradasForaPrazo;
	}

	public Integer getQuantidadeEncerradasNoPrazo() {
		return quantidadeEncerradasNoPrazo;
	}

	public void setQuantidadeEncerradasNoPrazo(Integer quantidadeEncerradasNoPrazo) {
		this.quantidadeEncerradasNoPrazo = quantidadeEncerradasNoPrazo;
	}

	public Integer getQuantidadePendentesForaPrazo() {
		return quantidadePendentesForaPrazo;
	}

	public void setQuantidadePendentesForaPrazo(Integer quantidadePendentesForaPrazo) {
		this.quantidadePendentesForaPrazo = quantidadePendentesForaPrazo;
	}

	public Integer getQuantidadePendentesNoPrazo() {
		return quantidadePendentesNoPrazo;
	}

	public void setQuantidadePendentesNoPrazo(Integer quantidadePendentesNoPrazo) {
		this.quantidadePendentesNoPrazo = quantidadePendentesNoPrazo;
	}

	/**
	 * @return Retorna o campo idMotivoEncerramento.
	 */
	public Integer getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	/**
	 * @param idMotivoEncerramento O idMotivoEncerramento a ser setado.
	 */
	public void setIdMotivoEncerramento(Integer idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public Integer getQuantidadeGeradasNoMesPrazo() {
		return quantidadeGeradasNoMesPrazo;
	}

	public void setQuantidadeGeradasNoMesPrazo(Integer quantidadeGeradasNoMesPrazo) {
		this.quantidadeGeradasNoMesPrazo = quantidadeGeradasNoMesPrazo;
	}

	public Integer getQuantidadeGeradasNoMesForaPrazo() {
		return quantidadeGeradasNoMesForaPrazo;
	}

	public void setQuantidadeGeradasNoMesForaPrazo(
			Integer quantidadeGeradasNoMesForaPrazo) {
		this.quantidadeGeradasNoMesForaPrazo = quantidadeGeradasNoMesForaPrazo;
	}	
	
}
