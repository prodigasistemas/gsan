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
import gcom.cobranca.CobrancaSituacao;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ImovelCobrancaSituacao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataImplantacaoCobranca;

    /** nullable persistent field */
    private Date dataRetiradaCobranca;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cadastro.imovel.Imovel imovel;

    /** persistent field */
    private CobrancaSituacao cobrancaSituacao;

    private Integer anoMesReferenciaInicio;
    
    private Integer anoMesReferenciaFinal;
    
    private Cliente cliente;
    
    private ContaMotivoRevisao contaMotivoRevisao;
    
    private Cliente escritorio;
    
    private Cliente advogado;
    
    /** full constructor */
    public ImovelCobrancaSituacao(Date dataImplantacaoCobranca,
            Date dataRetiradaCobranca, Integer cpfCobranca,
            Integer cnpjCobranca, Date ultimaAlteracao,
            gcom.cadastro.imovel.Imovel imovel,
            CobrancaSituacao cobrancaSituacao,
            Integer anoMesReferenciaInicio,
            Integer anoMesReferenciaFinal,
            Cliente cliente,ContaMotivoRevisao contaMotivoRevisao) {
        this.dataImplantacaoCobranca = dataImplantacaoCobranca;
        this.dataRetiradaCobranca = dataRetiradaCobranca;
        this.ultimaAlteracao = ultimaAlteracao;
        this.imovel = imovel;
        this.cobrancaSituacao = cobrancaSituacao;
        this.anoMesReferenciaInicio = anoMesReferenciaInicio;
        this.anoMesReferenciaFinal = anoMesReferenciaFinal;
        
    }

    /** default constructor */
    public ImovelCobrancaSituacao() {
    }

    /** minimal constructor */
    public ImovelCobrancaSituacao(Date dataImplantacaoCobranca,
            gcom.cadastro.imovel.Imovel imovel,
            CobrancaSituacao cobrancaSituacao) {
        this.dataImplantacaoCobranca = dataImplantacaoCobranca;
        this.imovel = imovel;
        this.cobrancaSituacao = cobrancaSituacao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataImplantacaoCobranca() {
        return this.dataImplantacaoCobranca;
    }

    public void setDataImplantacaoCobranca(Date dataImplantacaoCobranca) {
        this.dataImplantacaoCobranca = dataImplantacaoCobranca;
    }

    public Date getDataRetiradaCobranca() {
        return this.dataRetiradaCobranca;
    }

    public void setDataRetiradaCobranca(Date dataRetiradaCobranca) {
        this.dataRetiradaCobranca = dataRetiradaCobranca;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cadastro.imovel.Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
        this.imovel = imovel;
    }

    public CobrancaSituacao getCobrancaSituacao() {
        return this.cobrancaSituacao;
    }

    public void setCobrancaSituacao(CobrancaSituacao cobrancaSituacao) {
        this.cobrancaSituacao = cobrancaSituacao;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

	public Integer getAnoMesReferenciaFinal() {
		return anoMesReferenciaFinal;
	}

	public void setAnoMesReferenciaFinal(Integer anoMesReferenciaFinal) {
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
	}

	public Integer getAnoMesReferenciaInicio() {
		return anoMesReferenciaInicio;
	}

	public void setAnoMesReferenciaInicio(Integer anoMesReferenciaInicio) {
		this.anoMesReferenciaInicio = anoMesReferenciaInicio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ContaMotivoRevisao getContaMotivoRevisao() {
		return contaMotivoRevisao;
	}

	public void setContaMotivoRevisao(ContaMotivoRevisao contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}
	
	public Filtro retornaFiltro() {
		
		FiltroImovelCobrancaSituacao filtroImovelCobrancaSituacao = new FiltroImovelCobrancaSituacao();
		filtroImovelCobrancaSituacao.adicionarParametro(new ParametroSimples(FiltroImovelCobrancaSituacao.ID,this.getId()));
		filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
		filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroImovelCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao");
		
		return filtroImovelCobrancaSituacao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public Cliente getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Cliente advogado) {
		this.advogado = advogado;
	}

	public Cliente getEscritorio() {
		return escritorio;
	}

	public void setEscritorio(Cliente escritorio) {
		this.escritorio = escritorio;
	}

    
}
