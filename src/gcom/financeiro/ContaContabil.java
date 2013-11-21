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
package gcom.financeiro;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class ContaContabil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static int RECEITA_AGUA = 1;
	public static int RECEITA_ESGOTO = 2;
	public static int RECEITA_DIVIDA_ATIVA = 3;
	public static int RECEITA_CREDITO = 10;
	public static int RECEITA_IMPOSTOS = 11;
	public static int OUTRAS_RECEITAS = 12;
	public static int PAGAMENTO_NAO_CLASSIFICADO = 13;
	public static int HISTORICO_PAG_NAO_CLASSIFICADO = 14;
	public static int DEVOLUCAO_AVISO_BANCARIO = 15;
	public static int PAG_HISTORICO_SEM_CORRESPONDENTE = 16;
	public static int RECEITA_SERVICOS = 17;
		
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String prefixoContabil;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private String numeroDigito;
    
    /** nullable persistent field */
    private Short indicadorLinha;
    
    /** nullable persistent field */
    private String numeroTerceiros;
    
    /** nullable persistent field */
    private String numeroConta;
    
    /** nullable persistent field */
    private String nomeConta;
    
    /** nullable persistent field */
    private Short indicadorCentroCusto;


    /** full constructor */
    public ContaContabil(String prefixoContabil, Date ultimaAlteracao, String descricao) {
                
        this.prefixoContabil = prefixoContabil;
        this.ultimaAlteracao = ultimaAlteracao;

    }
    
    public ContaContabil(Integer id, String prefixoContabil, Date ultimaAlteracao, String descricao) {
        this.id = id;
        this.prefixoContabil = prefixoContabil;
        this.ultimaAlteracao = ultimaAlteracao;
   
    }

    /** default constructor */
    public ContaContabil() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrefixoContabil() {
		return prefixoContabil;
	}

	public void setPrefixoContabil(String prefixoContabil) {
		this.prefixoContabil = prefixoContabil;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getIndicadorLinha() {
		return indicadorLinha;
	}

	public void setIndicadorLinha(Short indicadorLinha) {
		this.indicadorLinha = indicadorLinha;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getNumeroDigito() {
		return numeroDigito;
	}

	public void setNumeroDigito(String numeroDigito) {
		this.numeroDigito = numeroDigito;
	}

	public String getNumeroTerceiros() {
		return numeroTerceiros;
	}

	public void setNumeroTerceiros(String numeroTerceiros) {
		this.numeroTerceiros = numeroTerceiros;
	}

	public Short getIndicadorCentroCusto() {
		return indicadorCentroCusto;
	}

	public void setIndicadorCentroCusto(Short indicadorCentroCusto) {
		this.indicadorCentroCusto = indicadorCentroCusto;
	}
	
}

