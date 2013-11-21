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
* Rômulo Aurélio de Melo Souza Filho
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
* Anderson Italo felinto de Lima
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
package gcom.cadastro.unidade;

import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Descrição da classe Unidade Repavimentadora Custo Pavimento Calçada
 * 
 * @author Hugo Leonardo
 * @date 20/12/2010
 */
public class UnidadeRepavimentadoraCustoPavimentoCalcada implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;
	
	/** identifier field */
	private UnidadeOrganizacional unidadeRepavimentadora;

	/** persistent field */
	private PavimentoCalcada pavimentoCalcada;
	
	/** persistent field */
	private BigDecimal valorPavimento;
	
	/** persistent field */
	private Date dataVigenciaInicial;
	
	/** persistent field */
	private Date dataVigenciaFinal;
	
	/** persistent field */
	private Date ultimaAlteracao;

	/** default constructor */
	public UnidadeRepavimentadoraCustoPavimentoCalcada() {
		
	}
	
    /** minimal constructor */
    public UnidadeRepavimentadoraCustoPavimentoCalcada(UnidadeOrganizacional unidadeRepavimentadora, PavimentoCalcada pavimentoCalcada, 
    		BigDecimal valorPavimento, Date dataVigenciaInicial, Date ultimaAlteracao) {
    	
        this.unidadeRepavimentadora = unidadeRepavimentadora;
        this.pavimentoCalcada = pavimentoCalcada;
        this.valorPavimento = valorPavimento;
        this.dataVigenciaInicial = dataVigenciaInicial;
        this.ultimaAlteracao = ultimaAlteracao;
    }

	public Date getDataVigenciaFinal() {
		return dataVigenciaFinal;
	}

	public void setDataVigenciaFinal(Date dataVigenciaFinal) {
		this.dataVigenciaFinal = dataVigenciaFinal;
	}

	public Date getDataVigenciaInicial() {
		return dataVigenciaInicial;
	}

	public void setDataVigenciaInicial(Date dataVigenciaInicial) {
		this.dataVigenciaInicial = dataVigenciaInicial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PavimentoCalcada getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	public void setPavimentoCalcada(PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeOrganizacional getUnidadeRepavimentadora() {
		return unidadeRepavimentadora;
	}

	public void setUnidadeRepavimentadora(
			UnidadeOrganizacional unidadeRepavimentadora) {
		this.unidadeRepavimentadora = unidadeRepavimentadora;
	}

	public BigDecimal getValorPavimento() {
		return valorPavimento;
	}

	public void setValorPavimento(BigDecimal valorPavimento) {
		this.valorPavimento = valorPavimento;
	}
	
	public boolean isPodeAtualizar(){
		boolean retorno = true;
		
		if(this.getDataVigenciaFinal() != null 
				&& Util.compararData(this.getDataVigenciaFinal(), new Date()) == -1){
			
			retorno = false;
		}
		return retorno;
	}
    
}
