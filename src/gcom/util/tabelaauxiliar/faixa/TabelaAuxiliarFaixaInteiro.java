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
package gcom.util.tabelaauxiliar.faixa;

import gcom.util.tabelaauxiliar.TabelaAuxiliarAbstrata;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Rômulo Aurélio
 *
 */
public class TabelaAuxiliarFaixaInteiro extends TabelaAuxiliarAbstrata {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer menorFaixa;

    private Integer maiorFaixa;
    
    private String faixaCompleta;
    
    private String faixaCompletaComId;
    /**
     * full constructor
     * 
     * @param id
     *            Descrição do parâmetro
     * @param faixaInical
     *            Descrição do parâmetro
     * @param faixaFinal
     *            Descrição do parâmetro
     */
    public TabelaAuxiliarFaixaInteiro(Integer id, Date ultimaAlteracao,
    		Integer menorFaixa, Integer maiorFaixa, String faixaCompleta) {
        super.setId(id);
        super.setUltimaAlteracao(ultimaAlteracao);
        this.menorFaixa = menorFaixa;
        this.maiorFaixa = maiorFaixa;
        this.faixaCompleta = faixaCompleta;
    }

    /**
     * default constructor
     */
    public TabelaAuxiliarFaixaInteiro() {
    }

	

	/**
	 * @return Returns the maiorFaixa.
	 */
	public Integer getMaiorFaixa() {
		return maiorFaixa;
	}

	/**
	 * @param maiorFaixa The maiorFaixa to set.
	 */
	public void setMaiorFaixa(Integer maiorFaixa) {
		this.maiorFaixa = maiorFaixa;
	}

	/**
	 * @return Returns the menorFaixa.
	 */
	public Integer getMenorFaixa() {
		return menorFaixa;
	}

	/**
	 * @param menorFaixa The menorFaixa to set.
	 */
	public void setMenorFaixa(Integer menorFaixa) {
		this.menorFaixa = menorFaixa;
	}

//	@Override
//	public Filtro retornaFiltro() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	/**
    * Retorna o valor de faixaCompleta
    * 
    * @return O valor de faixaCompleta
    */
   public String getFaixaCompleta() {
       faixaCompleta = this.getMenorFaixa() + " a " + this.getMaiorFaixa()
               + "m2";
       return faixaCompleta;
   }

	/**
	 * @return Returns the faixaCompleta.
	 */
	
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	/**
	 * @param faixaCompleta The faixaCompleta to set.
	 */
	public void setFaixaCompleta(String faixaCompleta) {
		this.faixaCompleta = faixaCompleta;
	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 26/09/2007
	 *
	 * @return
	 */
	public String getFaixaCompletaComId() {
		
		if(this.getId().compareTo(10) == -1){
			faixaCompletaComId = "0" + getId() + " - " + this.getMenorFaixa() + " a " + this.getMaiorFaixa() + "m2";
		}else{
			faixaCompletaComId = getId() + " - " + this.getMenorFaixa() + " a " + this.getMaiorFaixa() + "m2";
		}
	       return faixaCompletaComId;
	   }
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		if(this.getMenorFaixa() != null && this.getMaiorFaixa() != null) {
			return getFaixaCompleta();	
		} 
		return null;
	}
}
