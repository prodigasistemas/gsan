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
package gcom.faturamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FaturamentoGrupoCronogramaMensal extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.faturamento.FaturamentoGrupo faturamentoGrupo;
    
    /** persistent field */
    private Usuario usuario;

    /** persistent field */
    private Set faturamentoAtividadeCronogramas;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroFaturamentoGrupoCronogramaMensal filtroFaturamentoGrupoCronogramaMensal = new FiltroFaturamentoGrupoCronogramaMensal();
		filtroFaturamentoGrupoCronogramaMensal. adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupo");
		filtroFaturamentoGrupoCronogramaMensal. adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtroFaturamentoGrupoCronogramaMensal. adicionarParametro(
		new ParametroSimples(FiltroFaturamentoGrupoCronogramaMensal.ID, this.getId()));
		
		return filtroFaturamentoGrupoCronogramaMensal; 
	}
    
    /** full constructor */
    public FaturamentoGrupoCronogramaMensal(Integer anoMesReferencia, Date ultimaAlteracao, gcom.faturamento.FaturamentoGrupo faturamentoGrupo, Usuario usuario) {
        this.anoMesReferencia = anoMesReferencia;
        this.ultimaAlteracao = ultimaAlteracao;
        this.faturamentoGrupo = faturamentoGrupo;
        this.usuario = usuario;
    }

    /** default constructor */
    public FaturamentoGrupoCronogramaMensal() {
    }

    /** minimal constructor */
    public FaturamentoGrupoCronogramaMensal(Integer anoMesReferencia, gcom.faturamento.FaturamentoGrupo faturamentoGrupo) {
        this.anoMesReferencia = anoMesReferencia;
        this.faturamentoGrupo = faturamentoGrupo;
    }
 
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.faturamento.FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(gcom.faturamento.FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }
    
    /**
	 * @return Retorna o campo faturamentoAtividadeCronogramas.
	 */
	public Set getFaturamentoAtividadeCronogramas() {
		return faturamentoAtividadeCronogramas;
	}

	/**
	 * @param faturamentoAtividadeCronogramas O faturamentoAtividadeCronogramas a ser setado.
	 */
	public void setFaturamentoAtividadeCronogramas(
			Set faturamentoAtividadeCronogramas) {
		if (faturamentoAtividadeCronogramas != null && !faturamentoAtividadeCronogramas.isEmpty()) {
			Iterator it = faturamentoAtividadeCronogramas.iterator();
			while (it.hasNext()) {
				FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) it.next();
				if ( faturamentoAtividadeCronograma.getFaturamentoAtividade() != null) 
					 faturamentoAtividadeCronograma.getFaturamentoAtividade().getFaturamentoAtividadePrecedente();
			}
		}
		this.faturamentoAtividadeCronogramas = faturamentoAtividadeCronogramas;
	}

	/**
     * Retorna o valor de mesAno
     * 
     * @return O valor de mesAno
     */
    public String getMesAno() {
        //o metodo serve para transformar o AnoMesReferencia do banco
        //em mes/Ano para demonstraçao para o usuario.
        //Ex.: 200508 para 08/2005
        String mesAno = null;

        String mes = null;
        String ano = null;

        if (this.anoMesReferencia != 0) {
            String anoMes = this.anoMesReferencia + "";

            mes = anoMes.substring(4, 6);
            ano = anoMes.substring(0, 4);
            mesAno = mes + "/" + ano;
        }
        return mesAno;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @param anoMesReferencia O anoMesReferencia a ser setado.
	 */
	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

}
