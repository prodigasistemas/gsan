package gcom.gerencial.faturamento.bean;

import java.math.BigDecimal;

/**
 * Classe responsável por ajudar o caso de uso [UC0572] Gerar Resumo do ReFaturamento 
 *
 * @author Bruno Barros
 * @date 24/11/2008
 */
public class ResumoReFaturamentoOlapHelper {
    
    private Integer anoMesFaturamento;
    private Integer idGerenciaRegional;
    private Integer idUnidadeNegocio;
    private Integer cdElo;
    private Integer idLocalidade;
    private Integer idSetorComercial;
    private Integer cdSetorComercial;
    private Integer idRota;
    private Short   cdRota;
    private Integer idQuadra;
    private Integer nmQuadra;
    private Integer idPerfilImovel;
    private Integer situacaoLigacaoAgua;
    private Integer situacaoLigacaoEsgoto;
    private Integer idCategoria;
    private Integer idSubcategoria;
    private Integer idEsferaPoder;
    private Integer idTipoCliente;
    private Integer idPerfilLigacaoAgua;
    private Integer idPerfilLigacaoEsgoto;
    private Integer idTarifaConsumo;
    private Integer idGrupoFaturamento;
    private Integer indHidrometro;
    
    private BigDecimal valorAguaRetificado = new BigDecimal( 0 );
    private BigDecimal valorEsgotoRetificado = new BigDecimal( 0 );    
    private BigDecimal valorDebitoRetificado = new BigDecimal( 0 );    
    private BigDecimal valorCreditoRetificado = new BigDecimal( 0 );    
    private BigDecimal valorImpostoRetificado = new BigDecimal( 0 );    
    private BigDecimal consumoAguaRetificado = new BigDecimal( 0 );    
    private BigDecimal consumoEsgotoRetificado = new BigDecimal( 0 );
    
    private Integer quantidadeContasRetificadas;
    
    
    public Integer getQuantidadeContasRetificadas() {
        return quantidadeContasRetificadas;
    }



    
    public void setQuantidadeContasRetificadas(Integer quantidadeContasRetificadas) {
        this.quantidadeContasRetificadas = quantidadeContasRetificadas;
    }



    public BigDecimal getConsumoAguaRetificado() {
        return consumoAguaRetificado;
    }


    
    public void setConsumoAguaRetificado(BigDecimal consumoAguaRetificado) {
        this.consumoAguaRetificado = consumoAguaRetificado;
    }


    
    public BigDecimal getConsumoEsgotoRetificado() {
        return consumoEsgotoRetificado;
    }


    
    public void setConsumoEsgotoRetificado(BigDecimal consumoEsgotoRetificado) {
        this.consumoEsgotoRetificado = consumoEsgotoRetificado;
    }


    
    public BigDecimal getValorAguaRetificado() {
        return valorAguaRetificado;
    }


    
    public void setValorAguaRetificado(BigDecimal valorAguaRetificado) {
        this.valorAguaRetificado = valorAguaRetificado;
    }


    
    public BigDecimal getValorCreditoRetificado() {
        return valorCreditoRetificado;
    }


    
    public void setValorCreditoRetificado(BigDecimal valorCreditoRetificado) {
        this.valorCreditoRetificado = valorCreditoRetificado;
    }


    
    public BigDecimal getValorDebitoRetificado() {
        return valorDebitoRetificado;
    }


    
    public void setValorDebitoRetificado(BigDecimal valorDebitoRetificado) {
        this.valorDebitoRetificado = valorDebitoRetificado;
    }


    
    public BigDecimal getValorEsgotoRetificado() {
        return valorEsgotoRetificado;
    }


    
    public void setValorEsgotoRetificado(BigDecimal valorEsgotoRetificado) {
        this.valorEsgotoRetificado = valorEsgotoRetificado;
    }


    
    public BigDecimal getValorImpostoRetificado() {
        return valorImpostoRetificado;
    }


    
    public void setValorImpostoRetificado(BigDecimal valorImpostoRetificado) {
        this.valorImpostoRetificado = valorImpostoRetificado;
    }

    /**
     * Compara duas properiedades inteiras, comparando
     * seus valores para descobrirmos se sao iguais
     * @param pro1 Primeira propriedade
     * @param pro2 Segunda propriedade
     * @return Se iguais, retorna true
     */
    private boolean propriedadesIguais( Integer pro1, Integer pro2 ){
      if ( pro2 != null ){
          if ( !pro2.equals( pro1 ) ){
              return false;
          }
      } else if ( pro1 != null ){
          return false;
      }
      
      // Se chegou ate aqui quer dizer que as propriedades sao iguais
      return true;
    }
    
    /**
     * Compara duas properiedades inteiras, comparando
     * seus valores para descobrirmos se sao iguais
     * @param pro1 Primeira propriedade
     * @param pro2 Segunda propriedade
     * @return Se iguais, retorna true
     */
    private boolean propriedadesIguais( Short pro1, Short pro2 ){
      if ( pro2 != null ){
          if ( !pro2.equals( pro1 ) ){
              return false;
          }
      } else if ( pro1 != null ){
          return false;
      }
      
      // Se chegou ate aqui quer dizer que as propriedades sao iguais
      return true;
    }        

    public boolean equals( Object obj ){
        
        ResumoReFaturamentoOlapHelper objeto = ( ResumoReFaturamentoOlapHelper ) obj;
        return
            propriedadesIguais( this.getAnoMesFaturamento(), objeto.getAnoMesFaturamento() ) &&
            propriedadesIguais( this.getIdGerenciaRegional(), objeto.getIdGerenciaRegional() ) &&
            propriedadesIguais( this.getIdUnidadeNegocio(), objeto.getIdUnidadeNegocio() ) &&
            propriedadesIguais( this.getCdElo(), objeto.getCdElo() ) &&
            propriedadesIguais( this.getIdLocalidade(), objeto.getIdLocalidade() ) &&
            propriedadesIguais( this.getIdSetorComercial(), objeto.getIdSetorComercial() ) &&
            propriedadesIguais( this.getCdSetorComercial(), objeto.getCdSetorComercial() ) &&
            propriedadesIguais( this.getIdRota(), objeto.getIdRota() ) &&
            propriedadesIguais( this.getCdRota(), objeto.getCdRota() ) &&
            propriedadesIguais( this.getIdQuadra(), objeto.getIdQuadra() ) &&
            propriedadesIguais( this.getNmQuadra(), objeto.getNmQuadra() ) &&
            propriedadesIguais( this.getIdPerfilImovel(), objeto.getIdPerfilImovel() ) &&
            propriedadesIguais( this.getSituacaoLigacaoAgua(), objeto.getSituacaoLigacaoAgua() ) &&
            propriedadesIguais( this.getSituacaoLigacaoEsgoto(), objeto.getSituacaoLigacaoEsgoto() ) &&
            propriedadesIguais( this.getIdCategoria(), objeto.getIdCategoria() ) &&
            propriedadesIguais( this.getIdSubcategoria(), objeto.getIdSubcategoria() ) &&
            propriedadesIguais( this.getIdEsferaPoder(), objeto.getIdEsferaPoder() ) &&
            propriedadesIguais( this.getIdTipoCliente(), objeto.getIdTipoCliente() ) &&
            propriedadesIguais( this.getIdPerfilLigacaoAgua(), objeto.getIdPerfilLigacaoAgua() ) &&
            propriedadesIguais( this.getIdPerfilLigacaoEsgoto(), objeto.getIdPerfilLigacaoEsgoto() ) &&
            propriedadesIguais( this.getIdTarifaConsumo(), objeto.getIdTarifaConsumo() ) &&
            propriedadesIguais( this.getIdGrupoFaturamento(), objeto.getIdGrupoFaturamento() ) &&
            propriedadesIguais( this.getIndHidrometro(), objeto.getIndHidrometro() ); 
    }

    public Integer getCdElo() {
        return cdElo;
    }
    
    public void setCdElo(Integer cdElo) {
        this.cdElo = cdElo;
    }
    
    public Integer getCdSetorComercial() {
        return cdSetorComercial;
    }
    
    public void setCdSetorComercial(Integer cdSetorComercial) {
        this.cdSetorComercial = cdSetorComercial;
    }
    
    public Integer getIdCategoria() {
        return idCategoria;
    }
    
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    public Integer getIdEsferaPoder() {
        return idEsferaPoder;
    }
    
    public void setIdEsferaPoder(Integer idEsferaPoder) {
        this.idEsferaPoder = idEsferaPoder;
    }
    
    public Integer getIdGerenciaRegional() {
        return idGerenciaRegional;
    }
    
    public void setIdGerenciaRegional(Integer idGerenciaRegional) {
        this.idGerenciaRegional = idGerenciaRegional;
    }
    
    public Integer getIdGrupoFaturamento() {
        return idGrupoFaturamento;
    }
    
    public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
        this.idGrupoFaturamento = idGrupoFaturamento;
    }
    
    public Integer getIdLocalidade() {
        return idLocalidade;
    }
    
    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }
    
    public Integer getIdPerfilImovel() {
        return idPerfilImovel;
    }
    
    public void setIdPerfilImovel(Integer idPerfilImovel) {
        this.idPerfilImovel = idPerfilImovel;
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
    
    public void setIdPerfilLigacaoEsgoto(
            Integer idPerfilLigacaoEsgoto) {
        this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;
    }
    
    public Integer getIdQuadra() {
        return idQuadra;
    }
    
    public void setIdQuadra(Integer idQuadra) {
        this.idQuadra = idQuadra;
    }
    
    public Integer getIdRota() {
        return idRota;
    }
    
    public void setIdRota(Integer idRota) {
        this.idRota = idRota;
    }
    
    public Integer getIdSetorComercial() {
        return idSetorComercial;
    }
    
    public void setIdSetorComercial(Integer idSetorComercial) {
        this.idSetorComercial = idSetorComercial;
    }
    
    public Integer getIdSubcategoria() {
        return idSubcategoria;
    }
    
    public void setIdSubcategoria(Integer idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }
    
    public Integer getIdTarifaConsumo() {
        return idTarifaConsumo;
    }
    
    public void setIdTarifaConsumo(Integer idTarifaConsumo) {
        this.idTarifaConsumo = idTarifaConsumo;
    }
    
    public Integer getIdTipoCliente() {
        return idTipoCliente;
    }
    
    public void setIdTipoCliente(Integer idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }
    
    public Integer getIdUnidadeNegocio() {
        return idUnidadeNegocio;
    }
    
    public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
        this.idUnidadeNegocio = idUnidadeNegocio;
    }
    
    public Integer getIndHidrometro() {
        return indHidrometro;
    }
    
    public void setIndHidrometro(Integer indHidrometro) {
        this.indHidrometro = indHidrometro;
    }
    
    public Integer getNmQuadra() {
        return nmQuadra;
    }
    
    public void setNmQuadra(Integer nmQuadra) {
        this.nmQuadra = nmQuadra;
    }
    
    public Integer getSituacaoLigacaoAgua() {
        return situacaoLigacaoAgua;
    }
    
    public void setSituacaoLigacaoAgua(Integer situacaoLigacaoAgua) {
        this.situacaoLigacaoAgua = situacaoLigacaoAgua;
    }
    
    public Integer getSituacaoLigacaoEsgoto() {
        return situacaoLigacaoEsgoto;
    }
    
    public void setSituacaoLigacaoEsgoto(Integer situacaoLigacaoEsgoto) {
        this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
    }


    
    public Integer getAnoMesFaturamento() {
        return anoMesFaturamento;
    }


    
    public void setAnoMesFaturamento(Integer anoMesFaturamento) {
        this.anoMesFaturamento = anoMesFaturamento;
    }


    
    public Short getCdRota() {
        return cdRota;
    }


    
    public void setCdRota(Short cdRota) {
        this.cdRota = cdRota;
    }
}

