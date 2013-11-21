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
package gcom.relatorio.micromedicao.hidrometro;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * 
 * Title: GCOM
 * </p>
 * <p>
 * 
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * 
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * 
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @created 21 de Setembro de 2005
 * @version 1.0
 */

public class RelatorioManterHidrometroBean implements RelatorioBean {
    private String numero;

    private String dataAquisicao;

    private String anoFabricacao;

    private String finalidade;

    private String classeMetrologica;

    private String marca;

    private String diametro;

    private String capacidade;

    private String numeroDigitos;

    private String tipo;
    
    private String situacao;
    
    private String matricula;
    
    private String dataInstalacao;

    public String getDataInstalacao() {
		return dataInstalacao;
	}

	public void setDataInstalacao(String dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
     * Constructor for the RelatorioManterHidrometroBean object
     * 
     * @param numero
     *            Description of the Parameter
     * @param dataAquisicao
     *            Description of the Parameter
     * @param anoFabricacao
     *            Description of the Parameter
     * @param finalidade
     *            Description of the Parameter
     * @param classeMetrologica
     *            Description of the Parameter
     * @param marca
     *            Description of the Parameter
     * @param diametro
     *            Description of the Parameter
     * @param capacidade
     *            Description of the Parameter
     * @param numeroDigitos
     *            Description of the Parameter
     * @param tipo
     *            Description of the Parameter
     */
    public RelatorioManterHidrometroBean(String numero, String dataAquisicao,
            String anoFabricacao, String finalidade, String classeMetrologica,
            String marca, String diametro, String capacidade,
            String numeroDigitos, String tipo, String situacao, String matricula, 
            String dataInstalacao) {
        this.numero = numero;
        this.dataAquisicao = dataAquisicao;
        this.anoFabricacao = anoFabricacao;
        this.finalidade = finalidade;
        this.classeMetrologica = classeMetrologica;
        this.marca = marca;
        this.diametro = diametro;
        this.capacidade = capacidade;
        this.numeroDigitos = numeroDigitos;
        this.tipo = tipo;
        this.situacao = situacao;
        this.matricula = matricula;
        this.dataInstalacao = dataInstalacao;
    }

    /**
     * Gets the anoFabricacao attribute of the RelatorioManterHidrometroBean
     * object
     * 
     * @return The anoFabricacao value
     */
    public String getAnoFabricacao() {
        return anoFabricacao;
    }

    /**
     * Gets the capacidade attribute of the RelatorioManterHidrometroBean object
     * 
     * @return The capacidade value
     */
    public String getCapacidade() {
        return capacidade;
    }

    /**
     * Gets the classeMetrologica attribute of the RelatorioManterHidrometroBean
     * object
     * 
     * @return The classeMetrologica value
     */
    public String getClasseMetrologica() {
        return classeMetrologica;
    }

    /**
     * Gets the dataAquisicao attribute of the RelatorioManterHidrometroBean
     * object
     * 
     * @return The dataAquisicao value
     */
    public String getDataAquisicao() {
        return dataAquisicao;
    }

    /**
     * Gets the diametro attribute of the RelatorioManterHidrometroBean object
     * 
     * @return The diametro value
     */
    public String getDiametro() {
        return diametro;
    }

    /**
     * Gets the finalidade attribute of the RelatorioManterHidrometroBean object
     * 
     * @return The finalidade value
     */
    public String getFinalidade() {
        return finalidade;
    }

    /**
     * Gets the marca attribute of the RelatorioManterHidrometroBean object
     * 
     * @return The marca value
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Gets the numero attribute of the RelatorioManterHidrometroBean object
     * 
     * @return The numero value
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Gets the numeroDigitos attribute of the RelatorioManterHidrometroBean
     * object
     * 
     * @return The numeroDigitos value
     */
    public String getNumeroDigitos() {
        return numeroDigitos;
    }

    /**
     * Gets the tipo attribute of the RelatorioManterHidrometroBean object
     * 
     * @return The tipo value
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the tipo attribute of the RelatorioManterHidrometroBean object
     * 
     * @param tipo
     *            The new tipo value
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Sets the numeroDigitos attribute of the RelatorioManterHidrometroBean
     * object
     * 
     * @param numeroDigitos
     *            The new numeroDigitos value
     */
    public void setNumeroDigitos(String numeroDigitos) {
        this.numeroDigitos = numeroDigitos;
    }

    /**
     * Sets the numero attribute of the RelatorioManterHidrometroBean object
     * 
     * @param numero
     *            The new numero value
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Sets the marca attribute of the RelatorioManterHidrometroBean object
     * 
     * @param marca
     *            The new marca value
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Sets the finalidade attribute of the RelatorioManterHidrometroBean object
     * 
     * @param finalidade
     *            The new finalidade value
     */
    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    /**
     * Sets the diametro attribute of the RelatorioManterHidrometroBean object
     * 
     * @param diametro
     *            The new diametro value
     */
    public void setDiametro(String diametro) {
        this.diametro = diametro;
    }

    /**
     * Sets the dataAquisicao attribute of the RelatorioManterHidrometroBean
     * object
     * 
     * @param dataAquisicao
     *            The new dataAquisicao value
     */
    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    /**
     * Sets the classeMetrologica attribute of the RelatorioManterHidrometroBean
     * object
     * 
     * @param classeMetrologica
     *            The new classeMetrologica value
     */
    public void setClasseMetrologica(String classeMetrologica) {
        this.classeMetrologica = classeMetrologica;
    }

    /**
     * Sets the capacidade attribute of the RelatorioManterHidrometroBean object
     * 
     * @param capacidade
     *            The new capacidade value
     */
    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    /**
     * Sets the anoFabricacao attribute of the RelatorioManterHidrometroBean
     * object
     * 
     * @param anoFabricacao
     *            The new anoFabricacao value
     */
    public void setAnoFabricacao(String anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

    
    
}
