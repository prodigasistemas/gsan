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
package gcom.cobranca;

import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CobrancaSituacao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Short indicadorExigenciaAdvogado;
    
    private Short indicadorBloqueioParcelamento;
    
    private Short indicadorBloqueioInclusao;
    
    private Short indicadorBloqueioRetirada;
    
    private ContaMotivoRevisao contaMotivoRevisao;
    
    private Profissao profissao;
    
    private RamoAtividade ramoAtividade;
    
    private Short indicadorSelecaoApenasComPermissao;
    
    private Integer indicadorPrescricaoImoveisParticulares;
    
    private Integer indicadorNaoIncluirImoveisEmCobrancaResultado;
    
    /**
     * Em Cobrança Adminitrativa
     */
    public final static Integer COBRANCA_ADMINISTRATIVA = new Integer(4);
    
    /**
     * Cheque Devolvido
     */
    public final static Integer CHEQUE_DEVOLVIDO = new Integer(2);


    /**
     * Cheque Devolvido
     */
    public final static Integer NEGATIVADO_AUTOMATICAMENTE_NO_SPC = new Integer(11);

    /**
     * Cheque Devolvido
     */
    public final static Integer NEGATIVADO_AUTOMATICAMENTE_NA_SERASA = new Integer(12);

    /**
     * Cheque Devolvido
     */
    public final static Integer CARTA_ENVIADA_AO_SPC = new Integer(13);

    /**
     * Cheque Devolvido
     */
    public final static Integer CARTA_ENVIADA_A_SERASA = new Integer(14);
    
    
   
    public final static Integer EM_ANALISE_PARA_NEGATIVACAO	 = new Integer(15);
    
    
    //Indicador usado para a empresa Terceirizada da CAEMA
    public final static Integer COBRANCA_EMPRESA_TERCEIRIZADA = new Integer(1);
    
    //Indicador usado para a empresa Compesa
    public final static Integer EM_COBRANCA_JUDICIAL = new Integer(5);
    

    public final static Integer EM_ANALISE_PARA_NEGATIVACAO_SPC	 = new Integer(16);
    public final static Integer EM_ANALISE_PARA_NEGATIVACAO_SERASA	 = new Integer(17);

    /** full constructor */
    public CobrancaSituacao(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public CobrancaSituacao() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public ContaMotivoRevisao getContaMotivoRevisao() {
		return contaMotivoRevisao;
	}

	public void setContaMotivoRevisao(ContaMotivoRevisao contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public Short getIndicadorExigenciaAdvogado() {
		return indicadorExigenciaAdvogado;
	}

	public void setIndicadorExigenciaAdvogado(Short indicadorExigenciaAdvogado) {
		this.indicadorExigenciaAdvogado = indicadorExigenciaAdvogado;
	}
	
	public Filtro retornaFiltro(){
		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();

		filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacao.ID,this.getId()));
		filtroCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("ContaMotivoRevisao");
		
		
		return filtroCobrancaSituacao;
	}

    public Short getIndicadorBloqueioParcelamento() {
        return indicadorBloqueioParcelamento;
    }

    public void setIndicadorBloqueioParcelamento(Short indicadorBloqueioParcelamento) {
        this.indicadorBloqueioParcelamento = indicadorBloqueioParcelamento;
    }

	/**
	 * @return Retorna o campo indicadorBloqueioInclusao.
	 */
	public Short getIndicadorBloqueioInclusao() {
		return indicadorBloqueioInclusao;
	}

	/**
	 * @param indicadorBloqueioInclusao O indicadorBloqueioInclusao a ser setado.
	 */
	public void setIndicadorBloqueioInclusao(Short indicadorBloqueioInclusao) {
		this.indicadorBloqueioInclusao = indicadorBloqueioInclusao;
	}

	/**
	 * @return Retorna o campo indicadorBloqueioRetirada.
	 */
	public Short getIndicadorBloqueioRetirada() {
		return indicadorBloqueioRetirada;
	}

	/**
	 * @param indicadorBloqueioRetirada O indicadorBloqueioRetirada a ser setado.
	 */
	public void setIndicadorBloqueioRetirada(Short indicadorBloqueioRetirada) {
		this.indicadorBloqueioRetirada = indicadorBloqueioRetirada;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	public RamoAtividade getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	
	public Short getIndicadorSelecaoApenasComPermissao() {
		return indicadorSelecaoApenasComPermissao;
	}

	public void setIndicadorSelecaoApenasComPermissao(
			Short indicadorSelecaoApenasComPermissao) {
		this.indicadorSelecaoApenasComPermissao = indicadorSelecaoApenasComPermissao;
	}

	public Integer getIndicadorPrescricaoImoveisParticulares() {
		return indicadorPrescricaoImoveisParticulares;
	}

	public void setIndicadorPrescricaoImoveisParticulares(
			Integer indicadorPrescricaoImoveisParticulares) {
		this.indicadorPrescricaoImoveisParticulares = indicadorPrescricaoImoveisParticulares;
	}

	public Integer getIndicadorNaoIncluirImoveisEmCobrancaResultado() {
		return indicadorNaoIncluirImoveisEmCobrancaResultado;
	}

	public void setIndicadorNaoIncluirImoveisEmCobrancaResultado(
			Integer indicadorNaoIncluirImoveisEmCobrancaResultado) {
		this.indicadorNaoIncluirImoveisEmCobrancaResultado = indicadorNaoIncluirImoveisEmCobrancaResultado;
	}

}
