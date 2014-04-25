package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;

import java.math.BigDecimal;
import java.util.Date;


/**
 * [UC0639] Filtrar Ordens de Processo de Repavimentação
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Yara Taciane
 * @date 02/06/2008
 */
public class OSPavimentoHelper {

    private Integer id;

    private BigDecimal areaPavimentoRua;

    private BigDecimal areaPavimentoCalcada;

    private BigDecimal areaPavimentoRuaRetorno;

    private BigDecimal areaPavimentoCalcadaRetorno;

    private Date dataGeracao;
    
    private OrdemServico ordemServico; 

    private PavimentoRua pavimentoRua;
    
    private PavimentoCalcada pavimentoCalcada;
    
    private PavimentoRua pavimentoRuaRetorno;
    
    private PavimentoCalcada pavimentoCalcadaRetorno;
    
    private Integer idUnidadeResponsavel;
    
    private Integer situacaoRetorno;
    
    private String periodoEncerramentoOsInicial;
    
	private String periodoEncerramentoOsFinal;
	
	private String periodoRetornoServicoInicial;
	
	private String periodoRetornoServicoFinal;
	
	private String endereco;
	
	private String periodoRejeicaoInicial;
	
	private String periodoRejeicaoFinal;
    
	private Integer idMunicipio;
	
	private Integer idBairro;
	
	private Integer idLogradouro;
	
	private Short indicadorOsObservacaoRetorno;
	
	private Integer numeroOS;

	/**
	 * @return Retorna o campo areaPavimentoCalcada.
	 */
	public BigDecimal getAreaPavimentoCalcada() {
		return areaPavimentoCalcada;
	}

	/**
	 * @param areaPavimentoCalcada O areaPavimentoCalcada a ser setado.
	 */
	public void setAreaPavimentoCalcada(BigDecimal areaPavimentoCalcada) {
		this.areaPavimentoCalcada = areaPavimentoCalcada;
	}

	/**
	 * @return Retorna o campo areaPavimentoCalcadaRetorno.
	 */
	public BigDecimal getAreaPavimentoCalcadaRetorno() {
		return areaPavimentoCalcadaRetorno;
	}

	/**
	 * @param areaPavimentoCalcadaRetorno O areaPavimentoCalcadaRetorno a ser setado.
	 */
	public void setAreaPavimentoCalcadaRetorno(
			BigDecimal areaPavimentoCalcadaRetorno) {
		this.areaPavimentoCalcadaRetorno = areaPavimentoCalcadaRetorno;
	}

	/**
	 * @return Retorna o campo areaPavimentoRua.
	 */
	public BigDecimal getAreaPavimentoRua() {
		return areaPavimentoRua;
	}

	/**
	 * @param areaPavimentoRua O areaPavimentoRua a ser setado.
	 */
	public void setAreaPavimentoRua(BigDecimal areaPavimentoRua) {
		this.areaPavimentoRua = areaPavimentoRua;
	}

	/**
	 * @return Retorna o campo areaPavimentoRuaRetorno.
	 */
	public BigDecimal getAreaPavimentoRuaRetorno() {
		return areaPavimentoRuaRetorno;
	}

	/**
	 * @param areaPavimentoRuaRetorno O areaPavimentoRuaRetorno a ser setado.
	 */
	public void setAreaPavimentoRuaRetorno(BigDecimal areaPavimentoRuaRetorno) {
		this.areaPavimentoRuaRetorno = areaPavimentoRuaRetorno;
	}

	/**
	 * @return Retorna o campo dataGeracao.
	 */
	public Date getDataGeracao() {
		return dataGeracao;
	}

	/**
	 * @param dataGeracao O dataGeracao a ser setado.
	 */
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo ordemServico.
	 */
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	/**
	 * @param ordemServico O ordemServico a ser setado.
	 */
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	/**
	 * @return Retorna o campo pavimentoCalcada.
	 */
	public PavimentoCalcada getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	/**
	 * @param pavimentoCalcada O pavimentoCalcada a ser setado.
	 */
	public void setPavimentoCalcada(PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	/**
	 * @return Retorna o campo pavimentoCalcadaRetorno.
	 */
	public PavimentoCalcada getPavimentoCalcadaRetorno() {
		return pavimentoCalcadaRetorno;
	}

	/**
	 * @param pavimentoCalcadaRetorno O pavimentoCalcadaRetorno a ser setado.
	 */
	public void setPavimentoCalcadaRetorno(PavimentoCalcada pavimentoCalcadaRetorno) {
		this.pavimentoCalcadaRetorno = pavimentoCalcadaRetorno;
	}

	/**
	 * @return Retorna o campo pavimentoRua.
	 */
	public PavimentoRua getPavimentoRua() {
		return pavimentoRua;
	}

	/**
	 * @param pavimentoRua O pavimentoRua a ser setado.
	 */
	public void setPavimentoRua(PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	/**
	 * @return Retorna o campo pavimentoRuaRetorno.
	 */
	public PavimentoRua getPavimentoRuaRetorno() {
		return pavimentoRuaRetorno;
	}

	/**
	 * @param pavimentoRuaRetorno O pavimentoRuaRetorno a ser setado.
	 */
	public void setPavimentoRuaRetorno(PavimentoRua pavimentoRuaRetorno) {
		this.pavimentoRuaRetorno = pavimentoRuaRetorno;
	}

	/**
	 * @return Retorna o campo idUnidadeResponsavel.
	 */
	public Integer getIdUnidadeResponsavel() {
		return idUnidadeResponsavel;
	}

	/**
	 * @param idUnidadeResponsavel O idUnidadeResponsavel a ser setado.
	 */
	public void setIdUnidadeResponsavel(Integer idUnidadeResponsavel) {
		this.idUnidadeResponsavel = idUnidadeResponsavel;
	}

	/**
	 * @return Retorna o campo periodoEncerramentoOsFinal.
	 */
	public String getPeriodoEncerramentoOsFinal() {
		return periodoEncerramentoOsFinal;
	}

	/**
	 * @param periodoEncerramentoOsFinal O periodoEncerramentoOsFinal a ser setado.
	 */
	public void setPeriodoEncerramentoOsFinal(String periodoEncerramentoOsFinal) {
		this.periodoEncerramentoOsFinal = periodoEncerramentoOsFinal;
	}

	/**
	 * @return Retorna o campo periodoEncerramentoOsInicial.
	 */
	public String getPeriodoEncerramentoOsInicial() {
		return periodoEncerramentoOsInicial;
	}

	/**
	 * @param periodoEncerramentoOsInicial O periodoEncerramentoOsInicial a ser setado.
	 */
	public void setPeriodoEncerramentoOsInicial(String periodoEncerramentoOsInicial) {
		this.periodoEncerramentoOsInicial = periodoEncerramentoOsInicial;
	}

	/**
	 * @return Retorna o campo periodoRetornoServicoFinal.
	 */
	public String getPeriodoRetornoServicoFinal() {
		return periodoRetornoServicoFinal;
	}

	/**
	 * @param periodoRetornoServicoFinal O periodoRetornoServicoFinal a ser setado.
	 */
	public void setPeriodoRetornoServicoFinal(String periodoRetornoServicoFinal) {
		this.periodoRetornoServicoFinal = periodoRetornoServicoFinal;
	}

	/**
	 * @return Retorna o campo periodoRetornoServicoInicial.
	 */
	public String getPeriodoRetornoServicoInicial() {
		return periodoRetornoServicoInicial;
	}

	/**
	 * @param periodoRetornoServicoInicial O periodoRetornoServicoInicial a ser setado.
	 */
	public void setPeriodoRetornoServicoInicial(String periodoRetornoServicoInicial) {
		this.periodoRetornoServicoInicial = periodoRetornoServicoInicial;
	}

	/**
	 * @return Retorna o campo situacaoRetorno.
	 */
	public Integer getSituacaoRetorno() {
		return situacaoRetorno;
	}

	/**
	 * @param situacaoRetorno O situacaoRetorno a ser setado.
	 */
	public void setSituacaoRetorno(Integer situacaoRetorno) {
		this.situacaoRetorno = situacaoRetorno;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

    public String getPeriodoRejeicaoFinal() {
		return periodoRejeicaoFinal;
	}

	public void setPeriodoRejeicaoFinal(String periodoRejeicaoFinal) {
		this.periodoRejeicaoFinal = periodoRejeicaoFinal;
	}

	public String getPeriodoRejeicaoInicial() {
		return periodoRejeicaoInicial;
	}

	public void setPeriodoRejeicaoInicial(String periodoRejeicaoInicial) {
		this.periodoRejeicaoInicial = periodoRejeicaoInicial;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Integer getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(Integer idBairro) {
		this.idBairro = idBairro;
	}
	
	public Integer getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(Integer idLogradouro) {
		this.idLogradouro = idLogradouro;
	}
	
	public Short getIndicadorOsObservacaoRetorno() {
		return indicadorOsObservacaoRetorno;
	}

	public void setIndicadorOsObservacaoRetorno(Short indicadorOsObservacaoRetorno) {
		this.indicadorOsObservacaoRetorno = indicadorOsObservacaoRetorno;
	}
	
	public Integer getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(Integer numeroOS) {
		this.numeroOS = numeroOS;
	}


	public OSPavimentoHelper getOsPavimentoHelper(OrdemServicoPavimento ordemServicoPavimento){
    	
    	OSPavimentoHelper osPavimentoHelper = new OSPavimentoHelper();
    	
    	osPavimentoHelper.setAreaPavimentoCalcada(ordemServicoPavimento.getAreaPavimentoCalcada());
    	osPavimentoHelper.setAreaPavimentoCalcadaRetorno(ordemServicoPavimento.getAreaPavimentoCalcadaRetorno());
    	osPavimentoHelper.setAreaPavimentoRua(ordemServicoPavimento.getAreaPavimentoRua());
    	osPavimentoHelper.setAreaPavimentoRuaRetorno(ordemServicoPavimento.getAreaPavimentoRuaRetorno());
    	osPavimentoHelper.setDataGeracao(ordemServicoPavimento.getDataGeracao());
    	osPavimentoHelper.setId(ordemServicoPavimento.getId());    
    	osPavimentoHelper.setOrdemServico(ordemServicoPavimento.getOrdemServico());
    	osPavimentoHelper.setPavimentoCalcada(ordemServicoPavimento.getPavimentoCalcada());
    	osPavimentoHelper.setPavimentoCalcadaRetorno(ordemServicoPavimento.getPavimentoCalcadaRetorno());
    	osPavimentoHelper.setPavimentoRua(ordemServicoPavimento.getPavimentoRua());
    	osPavimentoHelper.setPavimentoRuaRetorno(ordemServicoPavimento.getPavimentoRuaRetorno());
//    	osPavimentoHelper.setPeriodoEncerramentoOsInicial(ordemServicoPavimento.getPeriodoEncerramentoOsInicial());
//    	osPavimentoHelper.setPeriodoEncerramentoOsFinal(ordemServicoPavimento.getPeriodoEncerramentoOsFinal());
//    	osPavimentoHelper.setPeriodoRetornoServicoInicial(ordemServicoPavimento.getPeriodoRetornoServicoInicial());
//    	osPavimentoHelper.setPeriodoRetornoServicoFinal(ordemServicoPavimento.getPeriodoRetornoServicoFinal());
    	
    	
    	
    	return osPavimentoHelper;
    }

    
    

}
