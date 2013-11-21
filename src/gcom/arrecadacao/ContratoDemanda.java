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
package gcom.arrecadacao;

import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.FiltroContratoDemanda;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContratoDemanda extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataContratoInicio;
    
    /** persistent field */
    private Date dataContratoEncerrado;

    /** persistent field */
    private Date dataContratoFim;

    /** persistent field */
    private String numeroContrato;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Imovel imovel;
    
    /** persistent field */
    private ContratoMotivoCancelamento contratoMotivoCancelamento;

    /** full constructor */
    public ContratoDemanda(Integer id, Date dataContratoInicio, Date dataContratoEncerrado, Date dataContratoFim, String numeroContrato, Date ultimaAlteracao, Imovel imovel) {
        this.id = id;
        this.dataContratoInicio = dataContratoInicio;
        this.dataContratoEncerrado = dataContratoEncerrado;
        this.dataContratoFim = dataContratoFim;
        this.numeroContrato = numeroContrato;
        this.ultimaAlteracao = ultimaAlteracao;
        this.imovel = imovel;
    }

    /** default constructor */
    public ContratoDemanda() {
    }

    

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Date getDataContratoFim() {
		return dataContratoFim;
	}

	public void setDataContratoFim(Date dataContratoFim) {
		this.dataContratoFim = dataContratoFim;
	}
	
	public Date getDataContratoEncerrado() {
		return dataContratoEncerrado;
	}

	public void setDataContratoEncerrado(Date dataContratoEncerrado) {
		this.dataContratoEncerrado = dataContratoEncerrado;
	}

	public Date getDataContratoInicio() {
		return dataContratoInicio;
	}

	public void setDataContratoInicio(Date dataContratoInicio) {
		this.dataContratoInicio = dataContratoInicio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ContratoMotivoCancelamento getContratoMotivoCancelamento() {
		return contratoMotivoCancelamento;
	}

	public void setContratoMotivoCancelamento(
			ContratoMotivoCancelamento contratoMotivoCancelamento) {
		this.contratoMotivoCancelamento = contratoMotivoCancelamento;
	}
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
		
		filtroContratoDemanda. adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroContratoDemanda. adicionarParametro(
				new ParametroSimples(FiltroContratoDemanda.ID, this.getId()));
		return filtroContratoDemanda; 
	} 

}
