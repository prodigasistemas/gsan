package gcom.micromedicao.bean;

/**
 * Classe responsável por ajudar o caso de uso [UC0586] Gerar Resumo Hidrometro
 * 
 * @author Thiago Tenório, Ivan Sérgio
 * @date 29/04/2007, 08/08/2007
 * @alteracao: Dois campos adicionados a quebra: Motivo Baixa e Classe Metrologica;
 */
public class ResumoHidrometroHelper {

	private Integer idHidrometroMotivoBaixa;

	private Integer idLocalArmazenagem;

	private Integer idHidrometroTipo;

	private Integer idHidrometroSituacao;

	private short numeroAnoFabricacao;

	private Integer idHidrometroMarca;

	private Integer idHidrometroDiametro;

	private Integer idHidrometroCapacidade;

	private Short indicadorMacro;
	
	private Integer count;
	
	private Integer idMotivoBaixa;
	
	private Integer idClasseMetrologica;

	/**
	 * Construtor com a sequencia correta de quebras para o caso de uso UC[586] -
	 * Gerar resumo de Hidrometros
	 * 
	 * @param idLocalArmazenagem
	 * @param idHidrometroTipo
	 * @param idHidrometroSituacao
	 * @param numeroAnoFabricacao
	 * @param idHidrometroMarca
	 * @param idHidrometroDiametro
	 * @param idHidrometroCapacidade
	 * @param indicadorMacro
	 * @param
	 */
	public ResumoHidrometroHelper(Integer idHidrometroMotivoBaixa,
			Integer idLocalArmazenagem, Integer idHidrometroTipo,
			Integer idHidrometroSituacao, short numeroAnoFabricacao,
			Integer idHidrometroMarca, Integer idHidrometroDiametro,
			Integer idHidrometroCapacidade, Short indicadorMacro, Integer count) {
	    this.idHidrometroMotivoBaixa = idHidrometroMotivoBaixa;
		this.idLocalArmazenagem = idLocalArmazenagem;
		this.idHidrometroTipo = idHidrometroTipo;
		this.numeroAnoFabricacao = numeroAnoFabricacao;
		this.idHidrometroMarca = idHidrometroMarca;
		this.idHidrometroSituacao = idHidrometroSituacao;
		this.idHidrometroDiametro = idHidrometroDiametro;
		this.idHidrometroCapacidade = idHidrometroCapacidade;
		this.indicadorMacro = indicadorMacro;
		this.count = count;

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

	/**
	 * Compara dois objetos levando em consideracao apenas as propriedades que
	 * identificam o agrupamento
	 * 
	 * @param obj
	 *            Objeto a ser comparado com a instancia deste objeto
	 */
	public boolean equals(Object obj) {

		if (!(obj instanceof ResumoHidrometroHelper)) {
			return false;
		} else {
			ResumoHidrometroHelper resumoTemp = (ResumoHidrometroHelper) obj;

			// Verificamos se todas as propriedades que identificam o objeto sao
			// iguais
			return propriedadesIguais(this.idHidrometroMotivoBaixa,
					resumoTemp.idHidrometroMotivoBaixa)
			        && propriedadesIguais(this.idLocalArmazenagem,
					resumoTemp.idLocalArmazenagem)
					&& propriedadesIguais(this.idHidrometroTipo,
							resumoTemp.idHidrometroTipo)
					&& this.numeroAnoFabricacao == (resumoTemp.numeroAnoFabricacao)
					&& propriedadesIguais(this.idHidrometroMarca,
							resumoTemp.idHidrometroMarca)
					&& propriedadesIguais(this.idHidrometroSituacao,
							resumoTemp.idHidrometroSituacao)
					&& propriedadesIguais(this.idHidrometroDiametro,
							resumoTemp.idHidrometroDiametro)
					&& propriedadesIguais(this.idHidrometroCapacidade,
							resumoTemp.idHidrometroCapacidade)
					&& this.indicadorMacro.equals(resumoTemp.indicadorMacro)
			        && propriedadesIguais(this.idMotivoBaixa, resumoTemp.idMotivoBaixa)
			        && propriedadesIguais(this.idClasseMetrologica, resumoTemp.idClasseMetrologica)
			        && propriedadesIguais(this.count, resumoTemp.count);

		}
	}

	public Integer getIdHidrometroCapacidade() {
		return idHidrometroCapacidade;
	}

	public void setIdHidrometroCapacidade(Integer idHidrometroCapacidade) {
		this.idHidrometroCapacidade = idHidrometroCapacidade;
	}

	public Integer getIdHidrometroDiametro() {
		return idHidrometroDiametro;
	}

	public void setIdHidrometroDiametro(Integer idHidrometroDiametro) {
		this.idHidrometroDiametro = idHidrometroDiametro;
	}

	public Integer getIdHidrometroMarca() {
		return idHidrometroMarca;
	}

	public void setIdHidrometroMarca(Integer idHidrometroMarca) {
		this.idHidrometroMarca = idHidrometroMarca;
	}

	public Integer getIdHidrometroSituacao() {
		return idHidrometroSituacao;
	}

	public void setIdHidrometroSituacao(Integer idHidrometroSituacao) {
		this.idHidrometroSituacao = idHidrometroSituacao;
	}

	public Integer getIdHidrometroTipo() {
		return idHidrometroTipo;
	}

	public void setIdHidrometroTipo(Integer idHidrometroTipo) {
		this.idHidrometroTipo = idHidrometroTipo;
	}

	public Integer getIdLocalArmazenagem() {
		return idLocalArmazenagem;
	}

	public void setIdLocalArmazenagem(Integer idLocalArmazenagem) {
		this.idLocalArmazenagem = idLocalArmazenagem;
	}

	public Short getIndicadorMacro() {
		return indicadorMacro;
	}

	public void setIndicadorMacro(Short indicadorMacro) {
		this.indicadorMacro = indicadorMacro;
	}

	public short getNumeroAnoFabricacao() {
		return numeroAnoFabricacao;
	}

	public void setNumeroAnoFabricacao(short numeroAnoFabricacao) {
		this.numeroAnoFabricacao = numeroAnoFabricacao;
	}

	public Integer getIdHidrometroMotivoBaixa() {
		return idHidrometroMotivoBaixa;
	}

	public void setIdHidrometroMotivoBaixa(Integer idHidrometroMotivoBaixa) {
		this.idHidrometroMotivoBaixa = idHidrometroMotivoBaixa;
	}

	// public void setIdElo(Integer idElo) {
	// if (idElo == null) {
	// idElo = 0;
	//
	// } else {
	// this.idElo = idElo;
	// }
	// }

	public void verificaNulo(){
		if(idHidrometroMarca == null){
			idHidrometroMarca = new Integer(0);
		}
			//(resumoHidrometroHelper.)
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getIdClasseMetrologica() {
		return idClasseMetrologica;
	}

	public void setIdClasseMetrologica(Integer idClasseMetrologica) {
		this.idClasseMetrologica = idClasseMetrologica;
	}

	public Integer getIdMotivoBaixa() {
		return idMotivoBaixa;
	}

	public void setIdMotivoBaixa(Integer idMotivoBaixa) {
		this.idMotivoBaixa = idMotivoBaixa;
	}
	
	
}
