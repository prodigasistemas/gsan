package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FaturamentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    private String idFaturamentoGrupoCronogramaMensal;

    private String idFaturamentoAtividadeCronograma;

    private String idGrupoFaturamento;

    private String mesAno;
    
    private String comandar;
    
    private String auxIDHidden;

    public String getComandar() {
		return comandar;
	}

	public void setComandar(String comandar) {
		this.comandar = comandar;
	}

	/**
     * Construtor da classe FaturamentoActionForm
     */
    public FaturamentoActionForm() {
    }

    /**
     * Retorna o valor de idFaturamentoAtividadeCronograma
     * 
     * @return O valor de idFaturamentoAtividadeCronograma
     */
    public String getIdFaturamentoAtividadeCronograma() {
        return idFaturamentoAtividadeCronograma;
    }

    /**
     * Seta o valor de idFaturamentoAtividadeCronograma
     * 
     * @param idFaturamentoAtividadeCronograma
     *            O novo valor de idFaturamentoAtividadeCronograma
     */
    public void setIdFaturamentoAtividadeCronograma(
            String idFaturamentoAtividadeCronograma) {
        this.idFaturamentoAtividadeCronograma = idFaturamentoAtividadeCronograma;
    }

    /**
     * Retorna o valor de idFaturamentoGrupoCronogramaMensal
     * 
     * @return O valor de idFaturamentoGrupoCronogramaMensal
     */
    public String getIdFaturamentoGrupoCronogramaMensal() {
        return idFaturamentoGrupoCronogramaMensal;
    }

    /**
     * Seta o valor de idFaturamentoGrupoCronogramaMensal
     * 
     * @param idFaturamentoGrupoCronogramaMensal
     *            O novo valor de idFaturamentoGrupoCronogramaMensal
     */
    public void setIdFaturamentoGrupoCronogramaMensal(
            String idFaturamentoGrupoCronogramaMensal) {
        this.idFaturamentoGrupoCronogramaMensal = idFaturamentoGrupoCronogramaMensal;
    }

    /**
     * Retorna o valor de idGrupoFaturamento
     * 
     * @return O valor de idGrupoFaturamento
     */
    public String getIdGrupoFaturamento() {
        return idGrupoFaturamento;
    }

    /**
     * Seta o valor de idGrupoFaturamento
     * 
     * @param idGrupoFaturamento
     *            O novo valor de idGrupoFaturamento
     */
    public void setIdGrupoFaturamento(String idGrupoFaturamento) {
        this.idGrupoFaturamento = idGrupoFaturamento;
    }

    /**
     * Retorna o valor de mesAno
     * 
     * @return O valor de mesAno
     */
    public String getMesAno() {
        return mesAno;
    }

    /**
     * Seta o valor de mesAno
     * 
     * @param mesAno
     *            O novo valor de mesAno
     */
    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

	/**
	 * @return Returns the auxIDHidden.
	 */
	public String getAuxIDHidden() {
		return auxIDHidden;
	}

	/**
	 * @param auxIDHidden The auxIDHidden to set.
	 */
	public void setAuxIDHidden(String auxIDHidden) {
		this.auxIDHidden = auxIDHidden;
	}
	
}
