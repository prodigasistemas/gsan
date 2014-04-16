package gcom.gui.faturamento;




/**
 * [UC0991] Filtrar Faturamento Imediato Ajuste
 * 
 * @author Hugo Leonardo
 *
 * @date 26/02/2010
 */

public class FaturamentoImediatoAjusteHelper {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private String mesAnoReferencia;

    /** nullable persistent field */
    private String faturamentoGrupo;

    /** nullable persistent field */
    private String imovelId;
    
    /** nullable persistent field */
    private String rotaId;
    
    private String inscricao;
    
    private String rota;
    
    private String difValorAgua;
    
    private String difConsumoAgua;
    
    private String difValorEsgoto;
    
    private String difConsumoEsgoto;

    /** default constructor */
    public FaturamentoImediatoAjusteHelper() {
    }

	/**
	 * @return Returns the faturamentoGrupo.
	 */
	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	/**
	 * @param faturamentoGrupo The faturamentoGrupo to set.
	 */
	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	/**
	 * @return Returns the mesAnoReferencia.
	 */
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	/**
	 * @param mesAnoReferencia The mesAnoReferencia to set.
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	/**
	 * @return Returns the imovelId.
	 */
	public String getImovelId() {
		return imovelId;
	}

	/**
	 * @param imovelId The imovelId to set.
	 */
	public void setImovelId(String imovelId) {
		this.imovelId = imovelId;
	}

	/**
	 * @return Returns the rotaId.
	 */
	public String getRotaId() {
		return rotaId;
	}

	/**
	 * @param rotaId The rotaId to set.
	 */
	public void setRotaId(String rotaId) {
		this.rotaId = rotaId;
	}

	/**
	 * @return Returns the difConsumoAgua.
	 */
	public String getDifConsumoAgua() {
		return difConsumoAgua;
	}

	/**
	 * @param difConsumoAgua The difConsumoAgua to set.
	 */
	public void setDifConsumoAgua(String difConsumoAgua) {
		this.difConsumoAgua = difConsumoAgua;
	}

	/**
	 * @return Returns the difConsumoEsgoto.
	 */
	public String getDifConsumoEsgoto() {
		return difConsumoEsgoto;
	}

	/**
	 * @param difConsumoEsgoto The difConsumoEsgoto to set.
	 */
	public void setDifConsumoEsgoto(String difConsumoEsgoto) {
		this.difConsumoEsgoto = difConsumoEsgoto;
	}

	/**
	 * @return Returns the difValorAgua.
	 */
	public String getDifValorAgua() {
		return difValorAgua;
	}

	/**
	 * @param difValorAgua The difValorAgua to set.
	 */
	public void setDifValorAgua(String difValorAgua) {
		this.difValorAgua = difValorAgua;
	}

	/**
	 * @return Returns the difValorEsgoto.
	 */
	public String getDifValorEsgoto() {
		return difValorEsgoto;
	}

	/**
	 * @param difValorEsgoto The difValorEsgoto to set.
	 */
	public void setDifValorEsgoto(String difValorEsgoto) {
		this.difValorEsgoto = difValorEsgoto;
	}

	/**
	 * @return Returns the inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao The inscricao to set.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	/**
	 * @return Returns the rota.
	 */
	public String getRota() {
		return rota;
	}

	/**
	 * @param rota The rota to set.
	 */
	public void setRota(String rota) {
		this.rota = rota;
	}
}
