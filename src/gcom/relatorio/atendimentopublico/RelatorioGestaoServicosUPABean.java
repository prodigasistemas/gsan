package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

public class RelatorioGestaoServicosUPABean implements RelatorioBean {
	
	private String unidade;
	private String atividade;
	
	private Integer encerradoNoPrazoNivel0;
	private Integer encerradoNoPrazoNivel1;
	private Integer encerradoNoPrazoNivel2;
	private Integer encerradoNoPrazoNivel3;
	
	private Integer encerradoForaPrazoNivel0;
	private Integer encerradoForaPrazoNivel1;
	private Integer encerradoForaPrazoNivel2;
	private Integer encerradoForaPrazoNivel3;
	
	private Integer pendenteNoPrazoNivel0;
	private Integer pendenteNoPrazoNivel1;
	private Integer pendenteNoPrazoNivel2;
	private Integer pendenteNoPrazoNivel3;
	
	private Integer pendenteForaPrazoNivel0;
	private Integer pendenteForaPrazoNivel1;
	private Integer pendenteForaPrazoNivel2;
	private Integer pendenteForaPrazoNivel3;
	
	private Boolean superior;
	
	public RelatorioGestaoServicosUPABean() {
		this.encerradoNoPrazoNivel0 = 0;
		this.encerradoNoPrazoNivel1 = 0;
		this.encerradoNoPrazoNivel2 = 0;
		this.encerradoNoPrazoNivel3 = 0;
		this.encerradoForaPrazoNivel0 = 0;
		this.encerradoForaPrazoNivel1 = 0;
		this.encerradoForaPrazoNivel2 = 0;
		this.encerradoForaPrazoNivel3 = 0;
		this.pendenteNoPrazoNivel0 = 0;
		this.pendenteNoPrazoNivel1 = 0;
		this.pendenteNoPrazoNivel2 = 0;
		this.pendenteNoPrazoNivel3 = 0;
		this.pendenteForaPrazoNivel0 = 0;
		this.pendenteForaPrazoNivel1 = 0;
		this.pendenteForaPrazoNivel2 = 0;
		this.pendenteForaPrazoNivel3 = 0;
	}
	
	public RelatorioGestaoServicosUPABean(String unidade, String atividade, Integer encerradoNoPrazoNivel0, Integer encerradoNoPrazoNivel1, Integer encerradoNoPrazoNivel2, Integer encerradoNoPrazoNivel3, Integer encerradoForaPrazoNivel0, Integer encerradoForaPrazoNivel1, Integer encerradoForaPrazoNivel2, Integer encerradoForaPrazoNivel3, Integer pendenteNoPrazoNivel0, Integer pendenteNoPrazoNivel1, Integer pendenteNoPrazoNivel2, Integer pendenteNoPrazoNivel3, Integer pendenteForaPrazoNivel0, Integer pendenteForaPrazoNivel1, Integer pendenteForaPrazoNivel2, Integer pendenteForaPrazoNivel3, Boolean superior) {
		this.unidade = unidade;
		this.atividade = atividade;
		this.encerradoNoPrazoNivel0 = encerradoNoPrazoNivel0;
		this.encerradoNoPrazoNivel1 = encerradoNoPrazoNivel1;
		this.encerradoNoPrazoNivel2 = encerradoNoPrazoNivel2;
		this.encerradoNoPrazoNivel3 = encerradoNoPrazoNivel3;
		this.encerradoForaPrazoNivel0 = encerradoForaPrazoNivel0;
		this.encerradoForaPrazoNivel1 = encerradoForaPrazoNivel1;
		this.encerradoForaPrazoNivel2 = encerradoForaPrazoNivel2;
		this.encerradoForaPrazoNivel3 = encerradoForaPrazoNivel3;
		this.pendenteNoPrazoNivel0 = pendenteNoPrazoNivel0;
		this.pendenteNoPrazoNivel1 = pendenteNoPrazoNivel1;
		this.pendenteNoPrazoNivel2 = pendenteNoPrazoNivel2;
		this.pendenteNoPrazoNivel3 = pendenteNoPrazoNivel3;
		this.pendenteForaPrazoNivel0 = pendenteForaPrazoNivel0;
		this.pendenteForaPrazoNivel1 = pendenteForaPrazoNivel1;
		this.pendenteForaPrazoNivel2 = pendenteForaPrazoNivel2;
		this.pendenteForaPrazoNivel3 = pendenteForaPrazoNivel3;
		this.superior = superior;
	}
	
	public void setNull() {
		this.encerradoNoPrazoNivel0 = null;
		this.encerradoNoPrazoNivel1 = null;
		this.encerradoNoPrazoNivel2 = null;
		this.encerradoNoPrazoNivel3 = null;
		this.encerradoForaPrazoNivel0 = null;
		this.encerradoForaPrazoNivel1 = null;
		this.encerradoForaPrazoNivel2 = null;
		this.encerradoForaPrazoNivel3 = null;
		this.pendenteNoPrazoNivel0 = null;
		this.pendenteNoPrazoNivel1 = null;
		this.pendenteNoPrazoNivel2 = null;
		this.pendenteNoPrazoNivel3 = null;
		this.pendenteForaPrazoNivel0 = null;
		this.pendenteForaPrazoNivel1 = null;
		this.pendenteForaPrazoNivel2 = null;
		this.pendenteForaPrazoNivel3 = null;
	}
	
	public boolean isEmpty() {
		boolean empty = true;
		if (this.encerradoNoPrazoNivel0 != 0) empty = false;
		if (this.encerradoNoPrazoNivel1 != 0) empty = false;
		if (this.encerradoNoPrazoNivel2 != 0) empty = false;
		if (this.encerradoNoPrazoNivel3 != 0) empty = false;
		if (this.encerradoForaPrazoNivel0 != 0) empty = false;
		if (this.encerradoForaPrazoNivel1 != 0) empty = false;
		if (this.encerradoForaPrazoNivel2 != 0) empty = false;
		if (this.encerradoForaPrazoNivel3 != 0) empty = false;
		if (this.pendenteNoPrazoNivel0 != 0) empty = false;
		if (this.pendenteNoPrazoNivel1 != 0) empty = false;
		if (this.pendenteNoPrazoNivel2 != 0) empty = false;
		if (this.pendenteNoPrazoNivel3 != 0) empty = false;
		if (this.pendenteForaPrazoNivel0 != 0) empty = false;
		if (this.pendenteForaPrazoNivel1 != 0) empty = false;
		if (this.pendenteForaPrazoNivel2 != 0) empty = false;
		if (this.pendenteForaPrazoNivel3 != 0) empty = false;
		return empty;
	}
	
	public void sum(RelatorioGestaoServicosUPABean other) {
		this.encerradoNoPrazoNivel0 += other.encerradoNoPrazoNivel0;
		this.encerradoNoPrazoNivel1 += other.encerradoNoPrazoNivel1;
		this.encerradoNoPrazoNivel2 += other.encerradoNoPrazoNivel2;
		this.encerradoNoPrazoNivel3 += other.encerradoNoPrazoNivel3;
		
		this.encerradoForaPrazoNivel0 += other.encerradoForaPrazoNivel0;
		this.encerradoForaPrazoNivel1 += other.encerradoForaPrazoNivel1;
		this.encerradoForaPrazoNivel2 += other.encerradoForaPrazoNivel2;
		this.encerradoForaPrazoNivel3 += other.encerradoForaPrazoNivel3;
		
		this.pendenteNoPrazoNivel0 += other.pendenteNoPrazoNivel0;
		this.pendenteNoPrazoNivel1 += other.pendenteNoPrazoNivel1;
		this.pendenteNoPrazoNivel2 += other.pendenteNoPrazoNivel2;
		this.pendenteNoPrazoNivel3 += other.pendenteNoPrazoNivel3;
		
		this.pendenteForaPrazoNivel0 += other.pendenteForaPrazoNivel0;
		this.pendenteForaPrazoNivel1 += other.pendenteForaPrazoNivel1;
		this.pendenteForaPrazoNivel2 += other.pendenteForaPrazoNivel2;
		this.pendenteForaPrazoNivel3 += other.pendenteForaPrazoNivel3;
	}
	
	public RelatorioGestaoServicosUPABean copy() {
		RelatorioGestaoServicosUPABean result = new RelatorioGestaoServicosUPABean();
		result.setUnidade(this.getUnidade());
		result.setAtividade(this.getAtividade());
		
		result.setEncerradoNoPrazoNivel0(this.getEncerradoNoPrazoNivel0());
		result.setEncerradoNoPrazoNivel1(this.getEncerradoNoPrazoNivel1());
		result.setEncerradoNoPrazoNivel2(this.getEncerradoNoPrazoNivel2());
		result.setEncerradoNoPrazoNivel3(this.getEncerradoNoPrazoNivel3());
		
		result.setEncerradoForaPrazoNivel0(this.getEncerradoForaPrazoNivel0());
		result.setEncerradoForaPrazoNivel1(this.getEncerradoForaPrazoNivel1());
		result.setEncerradoForaPrazoNivel2(this.getEncerradoForaPrazoNivel2());
		result.setEncerradoForaPrazoNivel3(this.getEncerradoForaPrazoNivel3());
		
		result.setPendenteNoPrazoNivel0(this.getPendenteNoPrazoNivel0());
		result.setPendenteNoPrazoNivel1(this.getPendenteNoPrazoNivel1());
		result.setPendenteNoPrazoNivel2(this.getPendenteNoPrazoNivel2());
		result.setPendenteNoPrazoNivel3(this.getPendenteNoPrazoNivel3());
		
		result.setPendenteForaPrazoNivel0(this.getPendenteForaPrazoNivel0());
		result.setPendenteForaPrazoNivel1(this.getPendenteForaPrazoNivel1());
		result.setPendenteForaPrazoNivel2(this.getPendenteForaPrazoNivel2());
		result.setPendenteForaPrazoNivel3(this.getPendenteForaPrazoNivel3());
		
		result.setSuperior(this.getSuperior());
		
		return result;
	}
	
	/**
	 * @return Retorna o campo atividade.
	 */
	public String getAtividade() {
		return atividade;
	}
	/**
	 * @param atividade O atividade a ser setado.
	 */
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	/**
	 * @return Retorna o campo encerradoForaPrazoNivel0.
	 */
	public Integer getEncerradoForaPrazoNivel0() {
		return encerradoForaPrazoNivel0;
	}
	/**
	 * @param encerradoForaPrazoNivel0 O encerradoForaPrazoNivel0 a ser setado.
	 */
	public void setEncerradoForaPrazoNivel0(Integer encerradoForaPrazoNivel0) {
		this.encerradoForaPrazoNivel0 = encerradoForaPrazoNivel0;
	}
	/**
	 * @return Retorna o campo encerradoForaPrazoNivel1.
	 */
	public Integer getEncerradoForaPrazoNivel1() {
		return encerradoForaPrazoNivel1;
	}
	/**
	 * @param encerradoForaPrazoNivel1 O encerradoForaPrazoNivel1 a ser setado.
	 */
	public void setEncerradoForaPrazoNivel1(Integer encerradoForaPrazoNivel1) {
		this.encerradoForaPrazoNivel1 = encerradoForaPrazoNivel1;
	}
	/**
	 * @return Retorna o campo encerradoForaPrazoNivel2.
	 */
	public Integer getEncerradoForaPrazoNivel2() {
		return encerradoForaPrazoNivel2;
	}
	/**
	 * @param encerradoForaPrazoNivel2 O encerradoForaPrazoNivel2 a ser setado.
	 */
	public void setEncerradoForaPrazoNivel2(Integer encerradoForaPrazoNivel2) {
		this.encerradoForaPrazoNivel2 = encerradoForaPrazoNivel2;
	}
	/**
	 * @return Retorna o campo encerradoForaPrazoNivel3.
	 */
	public Integer getEncerradoForaPrazoNivel3() {
		return encerradoForaPrazoNivel3;
	}
	/**
	 * @param encerradoForaPrazoNivel3 O encerradoForaPrazoNivel3 a ser setado.
	 */
	public void setEncerradoForaPrazoNivel3(Integer encerradoForaPrazoNivel3) {
		this.encerradoForaPrazoNivel3 = encerradoForaPrazoNivel3;
	}
	/**
	 * @return Retorna o campo encerradoNoPrazoNivel0.
	 */
	public Integer getEncerradoNoPrazoNivel0() {
		return encerradoNoPrazoNivel0;
	}
	/**
	 * @param encerradoNoPrazoNivel0 O encerradoNoPrazoNivel0 a ser setado.
	 */
	public void setEncerradoNoPrazoNivel0(Integer encerradoNoPrazoNivel0) {
		this.encerradoNoPrazoNivel0 = encerradoNoPrazoNivel0;
	}
	/**
	 * @return Retorna o campo encerradoNoPrazoNivel1.
	 */
	public Integer getEncerradoNoPrazoNivel1() {
		return encerradoNoPrazoNivel1;
	}
	/**
	 * @param encerradoNoPrazoNivel1 O encerradoNoPrazoNivel1 a ser setado.
	 */
	public void setEncerradoNoPrazoNivel1(Integer encerradoNoPrazoNivel1) {
		this.encerradoNoPrazoNivel1 = encerradoNoPrazoNivel1;
	}
	/**
	 * @return Retorna o campo encerradoNoPrazoNivel2.
	 */
	public Integer getEncerradoNoPrazoNivel2() {
		return encerradoNoPrazoNivel2;
	}
	/**
	 * @param encerradoNoPrazoNivel2 O encerradoNoPrazoNivel2 a ser setado.
	 */
	public void setEncerradoNoPrazoNivel2(Integer encerradoNoPrazoNivel2) {
		this.encerradoNoPrazoNivel2 = encerradoNoPrazoNivel2;
	}
	/**
	 * @return Retorna o campo encerradoNoPrazoNivel3.
	 */
	public Integer getEncerradoNoPrazoNivel3() {
		return encerradoNoPrazoNivel3;
	}
	/**
	 * @param encerradoNoPrazoNivel3 O encerradoNoPrazoNivel3 a ser setado.
	 */
	public void setEncerradoNoPrazoNivel3(Integer encerradoNoPrazoNivel3) {
		this.encerradoNoPrazoNivel3 = encerradoNoPrazoNivel3;
	}
	/**
	 * @return Retorna o campo pendenteForaPrazoNivel0.
	 */
	public Integer getPendenteForaPrazoNivel0() {
		return pendenteForaPrazoNivel0;
	}
	/**
	 * @param pendenteForaPrazoNivel0 O pendenteForaPrazoNivel0 a ser setado.
	 */
	public void setPendenteForaPrazoNivel0(Integer pendenteForaPrazoNivel0) {
		this.pendenteForaPrazoNivel0 = pendenteForaPrazoNivel0;
	}
	/**
	 * @return Retorna o campo pendenteForaPrazoNivel1.
	 */
	public Integer getPendenteForaPrazoNivel1() {
		return pendenteForaPrazoNivel1;
	}
	/**
	 * @param pendenteForaPrazoNivel1 O pendenteForaPrazoNivel1 a ser setado.
	 */
	public void setPendenteForaPrazoNivel1(Integer pendenteForaPrazoNivel1) {
		this.pendenteForaPrazoNivel1 = pendenteForaPrazoNivel1;
	}
	/**
	 * @return Retorna o campo pendenteForaPrazoNivel2.
	 */
	public Integer getPendenteForaPrazoNivel2() {
		return pendenteForaPrazoNivel2;
	}
	/**
	 * @param pendenteForaPrazoNivel2 O pendenteForaPrazoNivel2 a ser setado.
	 */
	public void setPendenteForaPrazoNivel2(Integer pendenteForaPrazoNivel2) {
		this.pendenteForaPrazoNivel2 = pendenteForaPrazoNivel2;
	}
	/**
	 * @return Retorna o campo pendenteForaPrazoNivel3.
	 */
	public Integer getPendenteForaPrazoNivel3() {
		return pendenteForaPrazoNivel3;
	}
	/**
	 * @param pendenteForaPrazoNivel3 O pendenteForaPrazoNivel3 a ser setado.
	 */
	public void setPendenteForaPrazoNivel3(Integer pendenteForaPrazoNivel3) {
		this.pendenteForaPrazoNivel3 = pendenteForaPrazoNivel3;
	}
	/**
	 * @return Retorna o campo pendenteNoPrazoNivel0.
	 */
	public Integer getPendenteNoPrazoNivel0() {
		return pendenteNoPrazoNivel0;
	}
	/**
	 * @param pendenteNoPrazoNivel0 O pendenteNoPrazoNivel0 a ser setado.
	 */
	public void setPendenteNoPrazoNivel0(Integer pendenteNoPrazoNivel0) {
		this.pendenteNoPrazoNivel0 = pendenteNoPrazoNivel0;
	}
	/**
	 * @return Retorna o campo pendenteNoPrazoNivel1.
	 */
	public Integer getPendenteNoPrazoNivel1() {
		return pendenteNoPrazoNivel1;
	}
	/**
	 * @param pendenteNoPrazoNivel1 O pendenteNoPrazoNivel1 a ser setado.
	 */
	public void setPendenteNoPrazoNivel1(Integer pendenteNoPrazoNivel1) {
		this.pendenteNoPrazoNivel1 = pendenteNoPrazoNivel1;
	}
	/**
	 * @return Retorna o campo pendenteNoPrazoNivel2.
	 */
	public Integer getPendenteNoPrazoNivel2() {
		return pendenteNoPrazoNivel2;
	}
	/**
	 * @param pendenteNoPrazoNivel2 O pendenteNoPrazoNivel2 a ser setado.
	 */
	public void setPendenteNoPrazoNivel2(Integer pendenteNoPrazoNivel2) {
		this.pendenteNoPrazoNivel2 = pendenteNoPrazoNivel2;
	}
	/**
	 * @return Retorna o campo pendenteNoPrazoNivel3.
	 */
	public Integer getPendenteNoPrazoNivel3() {
		return pendenteNoPrazoNivel3;
	}
	/**
	 * @param pendenteNoPrazoNivel3 O pendenteNoPrazoNivel3 a ser setado.
	 */
	public void setPendenteNoPrazoNivel3(Integer pendenteNoPrazoNivel3) {
		this.pendenteNoPrazoNivel3 = pendenteNoPrazoNivel3;
	}
	/**
	 * @return Retorna o campo unidade.
	 */
	public String getUnidade() {
		return unidade;
	}
	/**
	 * @param unidade O unidade a ser setado.
	 */
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	/**
	 * @return Retorna o campo superior.
	 */
	public Boolean getSuperior() {
		return superior;
	}
	/**
	 * @param superior O superior a ser setado.
	 */
	public void setSuperior(Boolean superior) {
		this.superior = superior;
	}

}
