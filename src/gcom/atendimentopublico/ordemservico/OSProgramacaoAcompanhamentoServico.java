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

import gcom.cadastro.imovel.Imovel;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OSProgramacaoAcompanhamentoServico implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataProgramacao;

    /** persistent field */
    private int sequencialProgramacao;

    /** persistent field */
    private Imovel imovel;

    /** nullable persistent field */
    private String descricaoPontoReferencia;

    /** nullable persistent field */
    private String numeroImovel;

    /** persistent field */
    private String nomeSolicitante;

    /** nullable persistent field */
    private String numeroTelefone;

    /** persistent field */
    private String descricaoEndereco;

    /** nullable persistent field */
    private String descricaoLigacaoAguaSituacao;

    /** nullable persistent field */
    private String descricaoLigacaoEsgotoSituacao;

    /** nullable persistent field */
    private String inscricaoImovel;

    /** nullable persistent field */
    private String numeroHidrometro;

    /** nullable persistent field */
    private String descricaoHidrometroCapacidade;

    /** persistent field */
    private short indicadorAtualizacaoOS;

    /** persistent field */
    private short indicadorTrasmissaoOS;

    /** persistent field */
    private Date dataUltimaAlteracao;

    /** persistent field */
    private ServicoTipo servicoTipo;

    /** persistent field */
    private OrdemServicoSituacao ordemServicoSituacao;

    /** persistent field */
    private OrdemServico ordemServico;

    /** persistent field */
    private Equipe equipe;

    /** persistent field */
    private ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico;
    
    /** persistent field */
    private EquipamentosEspeciais equipamentosEspeciaisFaltante;

    /** persistent field */
    private OsProgramNaoEncerMotivo osProgramacaoNaoEncerramentoMotivo;
    
	/** persistent field */
	private short indicadorExcluido;
    
    /**
     * Description of the Field
     */
    public final static short INDICADOR_TRANSMISSAO_SIM = 1;
    
    /**
     * Description of the Field
     */
    public final static short INDICADOR_TRANSMISSAO_NAO = 2;

    /**
     * Description of the Field
     */
    public final static short INDICADOR_ATUALIZA_OS_SIM = 1;
    
    /**
     * Description of the Field
     */
    public final static short INDICADOR_ATUALIZA_OS_NAO = 2;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer osasId) {
        this.id = osasId;
    }

    public Date getDataProgramacao() {
        return this.dataProgramacao;
    }

    public void setDataProgramacao(Date osasDtprogramacao) {
        this.dataProgramacao = osasDtprogramacao;
    }

    public int getSequencialProgramacao() {
        return this.sequencialProgramacao;
    }

    public void setSequencialProgramacao(int osasNnseqprogramacao) {
        this.sequencialProgramacao = osasNnseqprogramacao;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public String getDescricaoPontoReferencia() {
        return this.descricaoPontoReferencia;
    }

    public void setDescricaoPontoReferencia(String osasDspontoreferencia) {
        this.descricaoPontoReferencia = osasDspontoreferencia;
    }

    public String getNumeroImovel() {
        return this.numeroImovel;
    }

    public void setNumeroImovel(String osasNnimovel) {
        this.numeroImovel = osasNnimovel;
    }

    public String getNomeSolicitante() {
        return this.nomeSolicitante;
    }

    public void setNomeSolicitante(String osasNmsolicitante) {
        this.nomeSolicitante = osasNmsolicitante;
    }

    public String getNumeroTelefone() {
        return this.numeroTelefone;
    }

    public void setNumeroTelefone(String osasNnfone) {
        this.numeroTelefone = osasNnfone;
    }

    public String getDescricaoEndereco() {
        return this.descricaoEndereco;
    }

    public void setDescricaoEndereco(String osasDsendereco) {
        this.descricaoEndereco = osasDsendereco;
    }

    public String getDescricaoLigacaoAguaSituacao() {
        return this.descricaoLigacaoAguaSituacao;
    }

    public void setDescricaoLigacaoAguaSituacao(String osasDsligaguasituacao) {
        this.descricaoLigacaoAguaSituacao = osasDsligaguasituacao;
    }

    public String getDescricaoLigacaoEsgotoSituacao() {
        return this.descricaoLigacaoEsgotoSituacao;
    }

    public void setDescricaoLigacaoEsgotoSituacao(String osasDsligesgotosituacao) {
        this.descricaoLigacaoEsgotoSituacao = osasDsligesgotosituacao;
    }

    public String getInscricaoImovel() {
        return this.inscricaoImovel;
    }

    public void setInscricaoImovel(String osasInscricaoimovel) {
        this.inscricaoImovel = osasInscricaoimovel;
    }

    public String getNumeroHidrometro() {
        return this.numeroHidrometro;
    }

    public void setNumeroHidrometro(String osasNnhidrometro) {
        this.numeroHidrometro = osasNnhidrometro;
    }

    public String getDescricaoHidrometroCapacidade() {
        return this.descricaoHidrometroCapacidade;
    }

    public void setDescricaoHidrometroCapacidade(String osasDshidrometrocapacidade) {
        this.descricaoHidrometroCapacidade = osasDshidrometrocapacidade;
    }

    public short getIndicadorAtualizacaoOS() {
        return this.indicadorAtualizacaoOS;
    }

    public void setIndicadorAtualizacaoOS(short osasIcatualizaos) {
        this.indicadorAtualizacaoOS = osasIcatualizaos;
    }

    public short getIndicadorTrasmissaoOS() {
        return this.indicadorTrasmissaoOS;
    }

    public void setIndicadorTrasmissaoOS(short osasIctransmissao) {
        this.indicadorTrasmissaoOS = osasIctransmissao;
    }

    public Date getDataUltimaAlteracao() {
        return this.dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date osasTmultimaalteracao) {
        this.dataUltimaAlteracao = osasTmultimaalteracao;
    }

    public ServicoTipo getServicoTipo() {
        return this.servicoTipo;
    }

    public void setServicoTipo(ServicoTipo servicoTipo) {
        this.servicoTipo = servicoTipo;
    }

    public OrdemServicoSituacao getOrdemServicoSituacao() {
        return this.ordemServicoSituacao;
    }

    public void setOrdemServicoSituacao(OrdemServicoSituacao ordemServicoSituacao) {
        this.ordemServicoSituacao = ordemServicoSituacao;
    }

    public OrdemServico getOrdemServico() {
        return this.ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Equipe getEquipe() {
        return this.equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public ArquivoTextoAcompanhamentoServico getArquivoTextoAcompanhamentoServico() {
        return this.arquivoTextoAcompanhamentoServico;
    }

    public void setArquivoTextoAcompanhamentoServico(ArquivoTextoAcompanhamentoServico arqTxtAcompServico) {
        this.arquivoTextoAcompanhamentoServico = arqTxtAcompServico;
    }

    public OsProgramNaoEncerMotivo getOsProgramacaoNaoEncerramentoMotivo() {
        return this.osProgramacaoNaoEncerramentoMotivo;
    }

    public void setOsProgramacaoNaoEncerramentoMotivo(OsProgramNaoEncerMotivo osProgNaoEncerMotivo) {
        this.osProgramacaoNaoEncerramentoMotivo = osProgNaoEncerMotivo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("osasId", getId())
            .toString();
    }

	public EquipamentosEspeciais getEquipamentosEspeciaisFaltante() {
		return equipamentosEspeciaisFaltante;
	}

	public void setEquipamentosEspeciaisFaltante(
			EquipamentosEspeciais equipamentosEspeciaisFaltante) {
		this.equipamentosEspeciaisFaltante = equipamentosEspeciaisFaltante;
	}

	public short getIndicadorExcluido() {
		return indicadorExcluido;
	}

	public void setIndicadorExcluido(short indicadorExcluido) {
		this.indicadorExcluido = indicadorExcluido;
	}
	
	

}
