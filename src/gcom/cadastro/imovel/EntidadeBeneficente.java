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
package gcom.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.faturamento.debito.DebitoTipo;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EntidadeBeneficente implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Cliente cliente;

    /** persistent field */
    private DebitoTipo debitoTipo;

    /** persistent field */
    private Set imovelDoacoes;    
    
    /** persistent field */
    private Empresa empresa;
    
    /** persistent field */
    private Integer inicioMesAnoAdesao;
    
    /** persistent field */
    private Integer fimMesAnoAdesao;
    
    /** persistent field */
    private String inicioMesAnoAdesao2;
    
    /** persistent field */
    private String fimMesAnoAdesao2;
    
    
    
    /** persistent field */
    private Integer anoMesContratoInicial;
    
    /** persistent field */
    private Integer anoMesContratoFinal;



	/** full constructor */
    public EntidadeBeneficente(short indicadorUso, Date ultimaAlteracao, Cliente cliente, DebitoTipo debitoTipo, Set imovelDoacoes,Empresa empresa) {
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cliente = cliente;
        this.debitoTipo = debitoTipo;
        this.imovelDoacoes = imovelDoacoes;
        this.empresa = empresa;
    }

    /** default constructor */
    public EntidadeBeneficente() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public DebitoTipo getDebitoTipo() {
        return this.debitoTipo;
    }

    public void setDebitoTipo(DebitoTipo debitoTipo) {
        this.debitoTipo = debitoTipo;
    }

    public Set getImovelDoacoes() {
        return this.imovelDoacoes;
    }

    public void setImovelDoacoes(Set imovelDoacoes) {
        this.imovelDoacoes = imovelDoacoes;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String[] retornaCamposChavePrimaria(){
 		String[] retorno = new String[1];
 		retorno[0] = "id";
 		return retorno;
 	}
    
 	public Filtro retornaFiltro(){
 		FiltroEntidadeBeneficente filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();
 		
 		filtroEntidadeBeneficente.adicionarParametro(new ParametroSimples(FiltroEntidadeBeneficente.ID, this.getId()));
 		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("imovel");
 		
 		return filtroEntidadeBeneficente; 
 	}

	/**
	 * @return Retorna o campo empresa.
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa O empresa a ser setado.
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
    /**
	 * @return Returns the fimMesAnoAdesao.
	 */
	public Integer getFimMesAnoAdesao() {
		return fimMesAnoAdesao;
	}

	/**
	 * @param fimMesAnoAdesao The fimMesAnoAdesao to set.
	 */
	public void setFimMesAnoAdesao(Integer fimMesAnoAdesao) {
		this.fimMesAnoAdesao = fimMesAnoAdesao;
	}

	/**
	 * @return Returns the inicioMesAnoAdesao.
	 */
	public Integer getInicioMesAnoAdesao() {
		return inicioMesAnoAdesao;
	}

	/**
	 * @param inicioMesAnoAdesao The inicioMesAnoAdesao to set.
	 */
	public void setInicioMesAnoAdesao(Integer inicioMesAnoAdesao) {
		this.inicioMesAnoAdesao = inicioMesAnoAdesao;
	}

	/**
	 * @return Returns the fimMesAnoAdesao2.
	 */
	public String getFimMesAnoAdesao2() {
		return fimMesAnoAdesao2;
	}

	/**
	 * @param fimMesAnoAdesao2 The fimMesAnoAdesao2 to set.
	 */
	public void setFimMesAnoAdesao2(String fimMesAnoAdesao2) {
		this.fimMesAnoAdesao2 = fimMesAnoAdesao2;
	}

	/**
	 * @return Returns the inicioMesAnoAdesao2.
	 */
	public String getInicioMesAnoAdesao2() {
		return inicioMesAnoAdesao2;
	}

	/**
	 * @param inicioMesAnoAdesao2 The inicioMesAnoAdesao2 to set.
	 */
	public void setInicioMesAnoAdesao2(String inicioMesAnoAdesao2) {
		this.inicioMesAnoAdesao2 = inicioMesAnoAdesao2;
	}

	public Integer getAnoMesContratoFinal() {
		return anoMesContratoFinal;
	}

	public void setAnoMesContratoFinal(Integer anoMesContratoFinal) {
		this.anoMesContratoFinal = anoMesContratoFinal;
	}

	public Integer getAnoMesContratoInicial() {
		return anoMesContratoInicial;
	}

	public void setAnoMesContratoInicial(Integer anoMesContratoInicial) {
		this.anoMesContratoInicial = anoMesContratoInicial;
	}



}
