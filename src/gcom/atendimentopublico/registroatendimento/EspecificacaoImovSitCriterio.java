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
package gcom.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EspecificacaoImovSitCriterio extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Short indicadorHidrometroLigacaoAgua;
    
    /** nullable persistent field */
    private Short indicadorHidrometroPoco;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao especificacaoImovelSituacao;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** full constructor */
    public EspecificacaoImovSitCriterio(Short indicadorHidrometroLigacaoAgua, Short indicadorHidrometroPoco, Date ultimaAlteracao, gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao especificacaoImovelSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.indicadorHidrometroLigacaoAgua = indicadorHidrometroLigacaoAgua;
        this.indicadorHidrometroPoco = indicadorHidrometroPoco;
        this.ultimaAlteracao = ultimaAlteracao;
        this.especificacaoImovelSituacao = especificacaoImovelSituacao;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    /** default constructor */
    public EspecificacaoImovSitCriterio() {
    }

    /** minimal constructor */
    public EspecificacaoImovSitCriterio(Date ultimaAlteracao, gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao especificacaoImovelSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.ultimaAlteracao = ultimaAlteracao;
        this.especificacaoImovelSituacao = especificacaoImovelSituacao;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Short getIndicadorHidrometroLigacaoAgua() {
		return indicadorHidrometroLigacaoAgua;
	}

	public void setIndicadorHidrometroLigacaoAgua(
			Short indicadorHidrometroLigacaoAgua) {
		this.indicadorHidrometroLigacaoAgua = indicadorHidrometroLigacaoAgua;
	}

	public Short getIndicadorHidrometroPoco() {
		return indicadorHidrometroPoco;
	}

	public void setIndicadorHidrometroPoco(Short indicadorHidrometroPoco) {
		this.indicadorHidrometroPoco = indicadorHidrometroPoco;
	}

	public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao getEspecificacaoImovelSituacao() {
        return this.especificacaoImovelSituacao;
    }

    public void setEspecificacaoImovelSituacao(gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao especificacaoImovelSituacao) {
        this.especificacaoImovelSituacao = especificacaoImovelSituacao;
    }

    public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
        return this.ligacaoEsgotoSituacao;
    }

    public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
    }

    public LigacaoAguaSituacao getLigacaoAguaSituacao() {
        return this.ligacaoAguaSituacao;
    }

    public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	/**
	 * @param other Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean equals(Object other) {
		
		boolean equals = false;
		
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof EspecificacaoImovSitCriterio)) {
			return false;
		}
		
		EspecificacaoImovSitCriterio castOther = (EspecificacaoImovSitCriterio) other;

		if(castOther.getLigacaoAguaSituacao() != null && this.getLigacaoAguaSituacao() != null){
			
			equals = (castOther.getLigacaoAguaSituacao().getId().intValue() == 
				this.getLigacaoAguaSituacao().getId().intValue());
			
		}else if(castOther.getLigacaoAguaSituacao() == null && this.getLigacaoAguaSituacao() == null){
			equals = true;
		}

		if(!equals){
			return equals;
		}

		if(castOther.getLigacaoEsgotoSituacao() != null && this.getLigacaoEsgotoSituacao() != null){
			
			equals = equals && 
				(castOther.getLigacaoEsgotoSituacao().getId().intValue() == 
					this.getLigacaoEsgotoSituacao().getId().intValue());
			
		}else if(castOther.getLigacaoEsgotoSituacao() == null && this.getLigacaoEsgotoSituacao() == null){
			equals = equals && true;
		}else if(castOther.getLigacaoEsgotoSituacao() != null || this.getLigacaoEsgotoSituacao() != null){
			equals = false;
		}

		if(!equals){
			return equals;
		}

		if(castOther.getIndicadorHidrometroLigacaoAgua() != null && this.getIndicadorHidrometroLigacaoAgua() != null){
			
			equals = equals && 
				(castOther.getIndicadorHidrometroLigacaoAgua().shortValue() == 
					this.getIndicadorHidrometroLigacaoAgua().shortValue());
			
		}else if(castOther.getIndicadorHidrometroLigacaoAgua() == null && this.getIndicadorHidrometroLigacaoAgua() == null){
			equals = equals && true;
		}else if(castOther.getIndicadorHidrometroLigacaoAgua() != null || this.getIndicadorHidrometroLigacaoAgua() != null){
			equals = false;
		}

		if(!equals){
			return equals;
		}
		
		if(castOther.getIndicadorHidrometroPoco() != null && this.getIndicadorHidrometroPoco() != null){
			
			equals = equals && 
				(castOther.getIndicadorHidrometroPoco().shortValue() == 
					this.getIndicadorHidrometroPoco().shortValue());
			
		}else if(castOther.getIndicadorHidrometroPoco() == null && this.getIndicadorHidrometroPoco() == null){
			equals = equals && true;
		}else if(castOther.getIndicadorHidrometroPoco() != null || this.getIndicadorHidrometroPoco() != null){
			equals = false;
		}
		
		
		return equals;
	}
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroEspecificacaoImovelSituacaoCriterio filtro = new FiltroEspecificacaoImovelSituacaoCriterio();

		filtro.adicionarCaminhoParaCarregamentoEntidade("especificacaoImovelSituacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroEspecificacaoImovelSituacaoCriterio.ID, this.getId()));
		
		return filtro; 
	}	

}
