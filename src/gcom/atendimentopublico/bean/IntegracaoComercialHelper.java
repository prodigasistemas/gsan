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
package gcom.atendimentopublico.bean;

import gcom.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class IntegracaoComercialHelper implements Serializable {
	private static final long serialVersionUID = 1L;

    private gcom.cadastro.imovel.Imovel imovel;
    
    private gcom.atendimentopublico.ligacaoagua.LigacaoAgua ligacaoAgua;
    
    private gcom.atendimentopublico.ordemservico.OrdemServico ordemServico;
    
    private LigacaoEsgoto ligacaoEsgoto;
    
    private HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico;
    
    private HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico;
    
    private DadosEfetuacaoCorteLigacaoAguaHelper dadosEfetuacaoCorteLigacaoAguaHelper;
    
    private boolean veioEncerrarOS;
    
    private String qtdParcelas;
    
    private Integer localArmazenagemHidrometro;
    
    private String matriculaImovel;
    
    private String situacaoHidrometroSubstituido;   
    
    private Usuario usuarioLogado;
    
	public IntegracaoComercialHelper(){}

	public gcom.cadastro.imovel.Imovel getImovel() {
		return imovel;
	}

	public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
		this.imovel = imovel;
	}

	public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof IntegracaoComercialHelper)) {
            return false;
        }
        IntegracaoComercialHelper castOther = (IntegracaoComercialHelper) other;

        return (this.getImovel().getId().equals(castOther.getImovel().getId()));
    }

	public gcom.atendimentopublico.ligacaoagua.LigacaoAgua getLigacaoAgua() {
		return ligacaoAgua;
	}

	public void setLigacaoAgua(
			gcom.atendimentopublico.ligacaoagua.LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	public gcom.atendimentopublico.ordemservico.OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(
			gcom.atendimentopublico.ordemservico.OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public String getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public LigacaoEsgoto getLigacaoEsgoto() {
		return ligacaoEsgoto;
	}

	public void setLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}

	public boolean isVeioEncerrarOS() {
		return veioEncerrarOS;
	}

	public void setVeioEncerrarOS(boolean veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}

	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico() {
		return hidrometroInstalacaoHistorico;
	}

	public void setHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
	}

	public HidrometroInstalacaoHistorico getHidrometroSubstituicaoHistorico() {
		return hidrometroSubstituicaoHistorico;
	}

	public void setHidrometroSubstituicaoHistorico(
			HidrometroInstalacaoHistorico hidrometroSubstituicaoHistorico) {
		this.hidrometroSubstituicaoHistorico = hidrometroSubstituicaoHistorico;
	}

	public Integer getLocalArmazenagemHidrometro() {
		return localArmazenagemHidrometro;
	}

	public void setLocalArmazenagemHidrometro(Integer localArmazenagemHidrometro) {
		this.localArmazenagemHidrometro = localArmazenagemHidrometro;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getSituacaoHidrometroSubstituido() {
		return situacaoHidrometroSubstituido;
	}

	public void setSituacaoHidrometroSubstituido(
			String situacaoHidrometroSubstituido) {
		this.situacaoHidrometroSubstituido = situacaoHidrometroSubstituido;
	}

	public DadosEfetuacaoCorteLigacaoAguaHelper getDadosEfetuacaoCorteLigacaoAguaHelper() {
		return dadosEfetuacaoCorteLigacaoAguaHelper;
	}

	public void setDadosEfetuacaoCorteLigacaoAguaHelper(
			DadosEfetuacaoCorteLigacaoAguaHelper dadosEfetuacaoCorteLigacaoAguaHelper) {
		this.dadosEfetuacaoCorteLigacaoAguaHelper = dadosEfetuacaoCorteLigacaoAguaHelper;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
