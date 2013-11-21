/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
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