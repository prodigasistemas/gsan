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

package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.localidade.Localidade;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class BoletimOsConcluida implements Serializable {
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferenciaBoletim;

    /** persistent field */
    private short codigoFiscalizacao;

    /** nullable persistent field */
    private Date dataFiscalizacao;

    /** nullable persistent field */
    private Date dataEncerramentoBoletim;

    /** nullable persistent field */
    private Short indicadorTrocaProtecaoHidrometro;

    /** nullable persistent field */
    private Short indicadorTrocaRegistroHidrometro;
    
    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private HidrometroLocalInstalacao hidrometroLocalInstalacao;

    /** nullable persistent field */
    private OrdemServico ordemServico;
    
    private Set dataFiscalizacaoOsSeletivas;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private Usuario usuario;

    /** full constructor */
    public BoletimOsConcluida(Integer id, int anoMesReferenciaBoletim, short codigoFiscalizacao, Date dataFiscalizacao, Date dataEncerramentoBoletim, Short indicadorTrocaProtecaoHidrometro, Short indicadorTrocaRegistroHidrometro, HidrometroLocalInstalacao hidrometroLocalInstalacao, OrdemServico ordemServico, Localidade localidade, Usuario usuario) {
        this.id = id;
        this.anoMesReferenciaBoletim = anoMesReferenciaBoletim;
        this.codigoFiscalizacao = codigoFiscalizacao;
        this.dataFiscalizacao = dataFiscalizacao;
        this.dataEncerramentoBoletim = dataEncerramentoBoletim;
        this.indicadorTrocaProtecaoHidrometro = indicadorTrocaProtecaoHidrometro;
        this.indicadorTrocaRegistroHidrometro = indicadorTrocaRegistroHidrometro;
        this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
        this.ordemServico = ordemServico;
        this.localidade = localidade;
        this.usuario = usuario;
    }

    /** default constructor */
    public BoletimOsConcluida() {
    }

    /** minimal constructor */
    public BoletimOsConcluida(Integer id, int anoMesReferenciaBoletim, short codigoFiscalizacao, Localidade localidade, Usuario usuario) {
        this.id = id;
        this.anoMesReferenciaBoletim = anoMesReferenciaBoletim;
        this.codigoFiscalizacao = codigoFiscalizacao;
        this.localidade = localidade;
        this.usuario = usuario;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public int getAnoMesReferenciaBoletim() {
		return anoMesReferenciaBoletim;
	}

	public void setAnoMesReferenciaBoletim(int anoMesReferenciaBoletim) {
		this.anoMesReferenciaBoletim = anoMesReferenciaBoletim;
	}

	public short getCodigoFiscalizacao() {
		return codigoFiscalizacao;
	}

	public void setCodigoFiscalizacao(short codigoFiscalizacao) {
		this.codigoFiscalizacao = codigoFiscalizacao;
	}

	public Date getDataEncerramentoBoletim() {
		return dataEncerramentoBoletim;
	}

	public void setDataEncerramentoBoletim(Date dataEncerramentoBoletim) {
		this.dataEncerramentoBoletim = dataEncerramentoBoletim;
	}

	public Date getDataFiscalizacao() {
		return dataFiscalizacao;
	}

	public void setDataFiscalizacao(Date dataFiscalizacao) {
		this.dataFiscalizacao = dataFiscalizacao;
	}

	public Set getDataFiscalizacaoOsSeletivas() {
		return dataFiscalizacaoOsSeletivas;
	}

	public void setDataFiscalizacaoOsSeletivas(Set dataFiscalizacaoOsSeletivas) {
		this.dataFiscalizacaoOsSeletivas = dataFiscalizacaoOsSeletivas;
	}

	public HidrometroLocalInstalacao getHidrometroLocalInstalacao() {
		return hidrometroLocalInstalacao;
	}

	public void setHidrometroLocalInstalacao(
			HidrometroLocalInstalacao hidrometroLocalInstalacao) {
		this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorTrocaProtecaoHidrometro() {
		return indicadorTrocaProtecaoHidrometro;
	}

	public void setIndicadorTrocaProtecaoHidrometro(
			Short indicadorTrocaProtecaoHidrometro) {
		this.indicadorTrocaProtecaoHidrometro = indicadorTrocaProtecaoHidrometro;
	}

	public Short getIndicadorTrocaRegistroHidrometro() {
		return indicadorTrocaRegistroHidrometro;
	}

	public void setIndicadorTrocaRegistroHidrometro(
			Short indicadorTrocaRegistroHidrometro) {
		this.indicadorTrocaRegistroHidrometro = indicadorTrocaRegistroHidrometro;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
