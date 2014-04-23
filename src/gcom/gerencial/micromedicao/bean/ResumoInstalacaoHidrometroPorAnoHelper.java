package gcom.gerencial.micromedicao.bean;

import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * Descrição da classe 
 *
 * @author Fernando Fontelles
 * @date 17/06/2010
 */
public class ResumoInstalacaoHidrometroPorAnoHelper {

//	private Integer idQuadra;
	private Integer idPrincipalCategoria;
	private Integer idPrincipalSubCategoria;
	private Integer idEsferaPoder;
	private Integer idTipoClienteResponsavel;
	private Integer idPerfilLigacaoAgua;
	private Integer idPerfilLigacaoEsgoto;
	private Integer idLigacaoAguaSituacao;
	private Integer idLigacaoEsgotoSituacao;
	private Integer idPerfilImovel;
//	private Integer numeroQuadra;
//	private Integer idRota;
//	private Short   codigoRota;
	
	private Integer idConsumoTarifa;
	private Integer idMarcaHidrometro;
	private Integer idTipoHidrometro;
	private Integer idCapacidadeHidrometro;
	private Integer idDiametroHidrometro;
	private Integer idClasseHidrometro;

	/* acumuladores */
	private Integer qtdHidrometroInstaladoPoco = new Integer(0);
	private Integer qtdHidrometroInstaladoRamal = new Integer(0);
	private Integer qtdHidrometroRetiradoPoco = new Integer(0);
	private Integer qtdHidrometroRetiradoRamal = new Integer(0);
	private Integer qtdHidrometroSubstituidoPoco = new Integer(0);
	private Integer qtdHidrometroSubstituidoRamal = new Integer(0);
	private Integer qtdHidrometroRemanejadosPoco = new Integer(0);
	private Integer qtdHidrometroRemanejadosRamal = new Integer(0);
	private Integer qtdHidrometroAtualmenteInstaladosRamal = new Integer(0);
	private Integer qtdHidrometroAtualmenteInstaladosPoco = new Integer(0);

	/* fim acumuladores*/
	
	

	public ResumoInstalacaoHidrometroPorAnoHelper(
//			Integer idQuadra,
			Integer idPrincipalCategoria, 
			Integer idPrincipalSubCategoria, 
			Integer idEsferaPoder, 
			Integer idTipoClienteResponsavel, 
			Integer idPerfilLigacaoAgua, 
			Integer idPerfilLigacaoEsgoto, 
			Integer qtdHidrometroInstaladoPoco, 
			Integer qtdHidrometroInstaladoRamal, 
			Integer qtdHidrometroRetiradoPoco, 
			Integer qtdHidrometroRetiradoRamal, 
			Integer qtdHidrometroSubstituidoPoco, 
			Integer qtdHidrometroSubstituidoRamal,
			Integer qtdHidrometroRemanejadoPoco, 
			Integer qtdHidrometroRemanejadoRamal,
			Integer idLigacaoAguaSituacao,
			Integer idLigacaoEsgotoSituacao,
			Integer idPerfilImovel,
			Integer qtdHidrometroAtualmenteInstaladosRamal,
			Integer qtdHidrometroAtualmenteInstaladosPoco
//			Integer numeroQuadra,
//			Integer idRota,
//			Short codigoRota
			) {
		
//		this.idQuadra = idQuadra;
		this.idPrincipalCategoria = idPrincipalCategoria;
		this.idPrincipalSubCategoria = idPrincipalSubCategoria;
		this.idEsferaPoder = idEsferaPoder;
		this.idTipoClienteResponsavel = idTipoClienteResponsavel;
		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;
		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;
		this.qtdHidrometroInstaladoPoco = qtdHidrometroInstaladoPoco;
		this.qtdHidrometroInstaladoRamal = qtdHidrometroInstaladoRamal;
		this.qtdHidrometroRetiradoPoco = qtdHidrometroRetiradoPoco;
		this.qtdHidrometroRetiradoRamal = qtdHidrometroRetiradoRamal;
		this.qtdHidrometroSubstituidoPoco = qtdHidrometroSubstituidoPoco;
		this.qtdHidrometroSubstituidoRamal = qtdHidrometroSubstituidoRamal;
		this.qtdHidrometroRemanejadosPoco = qtdHidrometroRemanejadoPoco;
		this.qtdHidrometroRemanejadosRamal = qtdHidrometroRemanejadoRamal;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.idPerfilImovel = idPerfilImovel;
		this.qtdHidrometroAtualmenteInstaladosRamal = qtdHidrometroAtualmenteInstaladosRamal;
		this.qtdHidrometroAtualmenteInstaladosPoco = qtdHidrometroAtualmenteInstaladosPoco;
//		this.numeroQuadra = numeroQuadra;
//		this.idRota = idRota;
//		this.codigoRota = codigoRota;
	}

	
    
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof ResumoInstalacaoHidrometroPorAnoHelper)) {
            return false;
        }
        ResumoInstalacaoHidrometroPorAnoHelper castOther = (ResumoInstalacaoHidrometroPorAnoHelper) other;

        return new EqualsBuilder()
//        .append(this.getIdQuadra(),castOther.getIdQuadra())
        .append(this.getIdPrincipalCategoria(),castOther.getIdPrincipalCategoria())
        .append(this.getIdPrincipalSubCategoria(), castOther.getIdPrincipalSubCategoria())
        .append(this.getIdEsferaPoder(), castOther.getIdEsferaPoder())
        .append(this.getIdTipoClienteResponsavel(), castOther.getIdTipoClienteResponsavel())
        .append(this.getIdPerfilLigacaoAgua(), castOther.getIdPerfilLigacaoAgua())
        .append(this.getIdLigacaoAguaSituacao(), castOther.getIdLigacaoAguaSituacao())                
        .append(this.getIdLigacaoEsgotoSituacao(),castOther.getIdLigacaoEsgotoSituacao())
        .append(this.getIdPerfilLigacaoEsgoto(), castOther.getIdPerfilLigacaoEsgoto())
        .append(this.getIdPerfilImovel(), castOther.getIdPerfilImovel())
        .isEquals();
    }
    
//	public Integer getIdQuadra() {
//		return idQuadra;
//	}
//
//
//
//	public void setIdQuadra(Integer idQuadra) {
//		this.idQuadra = idQuadra;
//	}
//
//
//
//	public Short getCodigoRota() {
//		return codigoRota;
//	}



//	public void setCodigoRota(Short codigoRota) {
//		this.codigoRota = codigoRota;
//	}



	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}

	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	public Integer getIdPerfilLigacaoAgua() {
		return idPerfilLigacaoAgua;
	}

	public void setIdPerfilLigacaoAgua(Integer idPerfilLigacaoAgua) {
		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;
	}

	public Integer getIdPerfilLigacaoEsgoto() {
		return idPerfilLigacaoEsgoto;
	}

	public void setIdPerfilLigacaoEsgoto(Integer idPerfilLigacaoEsgoto) {
		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;
	}

	public Integer getIdPrincipalCategoria() {
		return idPrincipalCategoria;
	}

	public void setIdPrincipalCategoria(Integer idPrincipalCategoria) {
		this.idPrincipalCategoria = idPrincipalCategoria;
	}

	public Integer getIdPrincipalSubCategoria() {
		return idPrincipalSubCategoria;
	}

	public void setIdPrincipalSubCategoria(Integer idPrincipalSubCategoria) {
		this.idPrincipalSubCategoria = idPrincipalSubCategoria;
	}

	public Integer getIdTipoClienteResponsavel() {
		return idTipoClienteResponsavel;
	}

	public void setIdTipoClienteResponsavel(Integer idTipoClienteResponsavel) {
		this.idTipoClienteResponsavel = idTipoClienteResponsavel;
	}

	public Integer getQtdHidrometroInstaladoPoco() {
		return qtdHidrometroInstaladoPoco;
	}

	public void setQtdHidrometroInstaladoPoco(Integer qtdHidrometroInstaladoPoco) {
		this.qtdHidrometroInstaladoPoco = qtdHidrometroInstaladoPoco;
	}

	public Integer getQtdHidrometroInstaladoRamal() {
		return qtdHidrometroInstaladoRamal;
	}

	public void setQtdHidrometroInstaladoRamal(Integer qtdHidrometroInstaladoRamal) {
		this.qtdHidrometroInstaladoRamal = qtdHidrometroInstaladoRamal;
	}

	public Integer getQtdHidrometroRetiradoPoco() {
		return qtdHidrometroRetiradoPoco;
	}

	public void setQtdHidrometroRetiradoPoco(Integer qtdHidrometroRetiradoPoco) {
		this.qtdHidrometroRetiradoPoco = qtdHidrometroRetiradoPoco;
	}

	public Integer getQtdHidrometroRetiradoRamal() {
		return qtdHidrometroRetiradoRamal;
	}

	public void setQtdHidrometroRetiradoRamal(Integer qtdHidrometroRetiradoRamal) {
		this.qtdHidrometroRetiradoRamal = qtdHidrometroRetiradoRamal;
	}

	public Integer getQtdHidrometroSubstituidoPoco() {
		return qtdHidrometroSubstituidoPoco;
	}

	public void setQtdHidrometroSubstituidoPoco(Integer qtdHidrometroSubstituidoPoco) {
		this.qtdHidrometroSubstituidoPoco = qtdHidrometroSubstituidoPoco;
	}

	public Integer getQtdHidrometroSubstituidoRamal() {
		return qtdHidrometroSubstituidoRamal;
	}

	public void setQtdHidrometroSubstituidoRamal(
			Integer qtdHidrometroSubstituidoRamal) {
		this.qtdHidrometroSubstituidoRamal = qtdHidrometroSubstituidoRamal;
	}

	public Integer getQtdHidrometroRemanejadosPoco() {
		return qtdHidrometroRemanejadosPoco;
	}

	public void setQtdHidrometroRemanejadosPoco(Integer qtdHidrometroRemanejadosPoco) {
		this.qtdHidrometroRemanejadosPoco = qtdHidrometroRemanejadosPoco;
	}

	public Integer getQtdHidrometroRemanejadosRamal() {
		return qtdHidrometroRemanejadosRamal;
	}

	public void setQtdHidrometroRemanejadosRamal(
			Integer qtdHidrometroRemanejadosRamal) {
		this.qtdHidrometroRemanejadosRamal = qtdHidrometroRemanejadosRamal;
	}

	public Integer getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	public Integer getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}

	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}

	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}

	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	public Integer getQtdHidrometroAtualmenteInstaladosPoco() {
		return qtdHidrometroAtualmenteInstaladosPoco;
	}

	public void setQtdHidrometroAtualmenteInstaladosPoco(
			Integer qtdHidrometroAtualmenteInstaladosPoco) {
		this.qtdHidrometroAtualmenteInstaladosPoco = qtdHidrometroAtualmenteInstaladosPoco;
	}

	public Integer getQtdHidrometroAtualmenteInstaladosRamal() {
		return qtdHidrometroAtualmenteInstaladosRamal;
	}

	public void setQtdHidrometroAtualmenteInstaladosRamal(
			Integer qtdHidrometroAtualmenteInstaladosRamal) {
		this.qtdHidrometroAtualmenteInstaladosRamal = qtdHidrometroAtualmenteInstaladosRamal;
	}



//	public Integer getIdRota() {
//		return idRota;
//	}
//
//
//
//	public void setIdRota(Integer idRota) {
//		this.idRota = idRota;
//	}
//
//
//
//	public Integer getNumeroQuadra() {
//		return numeroQuadra;
//	}
//
//
//
//	public void setNumeroQuadra(Integer numeroQuadra) {
//		this.numeroQuadra = numeroQuadra;
//	}



	/**
	 * @return Retorna o campo idCapacidadeHidrometro.
	 */
	public Integer getIdCapacidadeHidrometro() {
		return idCapacidadeHidrometro;
	}



	/**
	 * @param idCapacidadeHidrometro O idCapacidadeHidrometro a ser setado.
	 */
	public void setIdCapacidadeHidrometro(Integer idCapacidadeHidrometro) {
		this.idCapacidadeHidrometro = idCapacidadeHidrometro;
	}



	/**
	 * @return Retorna o campo idClasseHidrometro.
	 */
	public Integer getIdClasseHidrometro() {
		return idClasseHidrometro;
	}



	/**
	 * @param idClasseHidrometro O idClasseHidrometro a ser setado.
	 */
	public void setIdClasseHidrometro(Integer idClasseHidrometro) {
		this.idClasseHidrometro = idClasseHidrometro;
	}



	/**
	 * @return Retorna o campo idConsumoTarifa.
	 */
	public Integer getIdConsumoTarifa() {
		return idConsumoTarifa;
	}



	/**
	 * @param idConsumoTarifa O idConsumoTarifa a ser setado.
	 */
	public void setIdConsumoTarifa(Integer idConsumoTarifa) {
		this.idConsumoTarifa = idConsumoTarifa;
	}



	/**
	 * @return Retorna o campo idDiametroHidrometro.
	 */
	public Integer getIdDiametroHidrometro() {
		return idDiametroHidrometro;
	}



	/**
	 * @param idDiametroHidrometro O idDiametroHidrometro a ser setado.
	 */
	public void setIdDiametroHidrometro(Integer idDiametroHidrometro) {
		this.idDiametroHidrometro = idDiametroHidrometro;
	}



	/**
	 * @return Retorna o campo idMarcaHidrometro.
	 */
	public Integer getIdMarcaHidrometro() {
		return idMarcaHidrometro;
	}



	/**
	 * @param idMarcaHidrometro O idMarcaHidrometro a ser setado.
	 */
	public void setIdMarcaHidrometro(Integer idMarcaHidrometro) {
		this.idMarcaHidrometro = idMarcaHidrometro;
	}



	/**
	 * @return Retorna o campo idTipoHidrometro.
	 */
	public Integer getIdTipoHidrometro() {
		return idTipoHidrometro;
	}



	/**
	 * @param idTipoHidrometro O idTipoHidrometro a ser setado.
	 */
	public void setIdTipoHidrometro(Integer idTipoHidrometro) {
		this.idTipoHidrometro = idTipoHidrometro;
	}

	
}
