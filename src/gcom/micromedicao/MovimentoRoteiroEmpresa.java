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
package gcom.micromedicao;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.Util;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class MovimentoRoteiroEmpresa implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Integer anoMesMovimento;

    /** persistent field */
    private Integer codigoSetorComercial;

    /** nullable persistent field */
    private String nomeCliente;

    /** persistent field */
    private Integer numeroQuadra;

    /** persistent field */
    private String numeroLoteImovel;

    /** persistent field */
    private String numeroSubloteImovel;

    /** nullable persistent field */
    private String enderecoImovel;

    /** nullable persistent field */
    private String descricaoAbreviadaCategoriaImovel;

    /** nullable persistent field */
    private String numeroHidrometro;

    /** nullable persistent field */
    private Short quantidadeEconomias;

    /** nullable persistent field */
    private Integer numeroLeituraAnterior;

    /** nullable persistent field */
    private Integer numeroFaixaLeituraEsperadaInicial;

    /** nullable persistent field */
    private Integer numeroFaixaLeituraEsperadaFinal;

    /** nullable persistent field */
    private Integer numeroLeituraHidrometro;

    /** nullable persistent field */
    private Short indicadorConfirmacaoLeitura;

    /** nullable persistent field */
    private Date tempoLeitura;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Date dataInstalacaoHidrometro;

    /** persistent field */
    private LeituraAnormalidade leituraAnormalidade;

    /** persistent field */
    private Localidade localidade;

    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    private ImovelPerfil imovelPerfil;

    /** persistent field */
    private FaturamentoGrupo faturamentoGrupo;

    /** persistent field */
    private HidrometroProtecao hidrometroProtecao;

    /** persistent field */
    private HidrometroMarca hidrometroMarca;

    /** persistent field */
    private HidrometroLocalInstalacao hidrometroLocalInstalacao;

    /** persistent field */
    private gcom.micromedicao.RoteiroEmpresa roteiroEmpresa;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** persistent field */
    private Empresa empresa;

    /** persistent field */
    private HidrometroCapacidade hidrometroCapacidade;

    /** persistent field */
    private MedicaoTipo medicaoTipo;
    
    private Rota rota;
    
    private Date dataHoraProcessamento;
    
    /** nullable persistent field */
    private Integer indicadorAtualizacaoLeitura;
    
    private Integer codigoSubcategoria1;
    
   // private String descricaoAbreviadaCategoriaImovel2;
    
    //private Integer idCategoriaImovel2;
    
    private Categoria categoria2;
    
    private Short quantidadeEconomias2;
    
    private Integer codigoSubcategoria2;
    
    private Integer numeroConsumoFaturadoMenos1Mes;
    
    private Integer numeroConsumoFaturadoMenos2Meses;
    
    private Integer numeroConsumoFaturadoMenos3Meses;
    
    private String descricaoConsumoTipo;
    
    private String numeroLacreLigacaoAgua;
    
    private Integer numeroSequencialRota;
    
    private Integer numeroConsumoFaturadoMenos4Meses;
    
    private Integer numeroConsumoFaturadoMenos5Meses;
    
    private Integer numeroConsumoFaturadoMenos6Meses;
    
    private Integer numeroConsumoMedio;
    
    private Short codigoRota;
    
    private Logradouro logradouro;
    
    private String nomeLogradouro;
    
    private String numeroImovel;
     
    private String complementoEndereco;
    
    private String inscricaoImovel;
    
    private String nomeLeiturista;
    
    private GerenciaRegional gerenciaRegional;
    
    private String nomeBairro;
    
    private Categoria categoriaPrincipal;
    
    private LeituraTipo leituraTipo;
    
    private String descricaoAbreviadaLogradouroTitulo;
    
    private String descricaoAbreviadaLogradouroTipo;
    
    private Integer codigoQuadraFace;
    
    private String descricaoLigacaoAguaSituacao;
    
    private Integer numeroMoradores;
    
    private Integer codigoAnormalidadeAnterior;
    
    private String descricaoAnormalidadeAnterior;
    
    private String nomeLocalidade;
   
	private String descricaoHidrometroMarca;
	
	private Leiturista leiturista;
	
	private Date dataLeituraAnterior;

	public String getDescricaoAbreviadaLogradouroTipo() {
		return descricaoAbreviadaLogradouroTipo;
	}

	public void setDescricaoAbreviadaLogradouroTipo(
			String descricaoAbreviadaLogradouroTipo) {
		this.descricaoAbreviadaLogradouroTipo = descricaoAbreviadaLogradouroTipo;
	}

	public String getDescricaoAbreviadaLogradouroTitulo() {
		return descricaoAbreviadaLogradouroTitulo;
	}

	public void setDescricaoAbreviadaLogradouroTitulo(
			String descricaoAbreviadaLogradouroTitulo) {
		this.descricaoAbreviadaLogradouroTitulo = descricaoAbreviadaLogradouroTitulo;
	}

	public LeituraTipo getLeituraTipo() {
		return leituraTipo;
	}

	public void setLeituraTipo(LeituraTipo leituraTipo) {
		this.leituraTipo = leituraTipo;
	}

	public Categoria getCategoriaPrincipal() {
		return categoriaPrincipal;
	}

	public void setCategoriaPrincipal(Categoria categoriaPrincipal) {
		this.categoriaPrincipal = categoriaPrincipal;
	}

	public Date getDataHoraProcessamento() {
		return dataHoraProcessamento;
	}

	public void setDataHoraProcessamento(Date dataHoraProcessamento) {
		this.dataHoraProcessamento = dataHoraProcessamento;
	}

	/** full constructor */
    public MovimentoRoteiroEmpresa(Integer id, Integer anoMesMovimento, Integer codigoSetorComercial, 
    		String nomeCliente, Integer numeroQuadra, String numeroLoteImovel, String numeroSubloteImovel, 
    		String enderecoImovel, String descricaoAbreviadaCategoriaImovel, String numeroHidrometro, 
    		Short quantidadeEconomias, Integer numeroLeituraAnterior, Integer numeroFaixaLeituraEsperadaInicial, 
    		Integer numeroFaixaLeituraEsperadaFinal, Integer numeroLeituraHidrometro, 
    		Short indicadorConfirmacaoLeitura, Date tempoLeitura, Date ultimaAlteracao, 
    		Date dataInstalacaoHidrometro, LeituraAnormalidade leituraAnormalidade, 
    		Localidade localidade, Imovel imovel, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, 
    		ImovelPerfil imovelPerfil, FaturamentoGrupo faturamentoGrupo, HidrometroProtecao hidrometroProtecao, 
    		HidrometroMarca hidrometroMarca, HidrometroLocalInstalacao hidrometroLocalInstalacao, 
    		gcom.micromedicao.RoteiroEmpresa roteiroEmpresa, LigacaoAguaSituacao ligacaoAguaSituacao, 
    		Empresa empresa, HidrometroCapacidade hidrometroCapacidade, MedicaoTipo medicaoTipo) {
    	
        this.id = id;
        this.anoMesMovimento = anoMesMovimento;
        this.codigoSetorComercial = codigoSetorComercial;
        this.nomeCliente = nomeCliente;
        this.numeroQuadra = numeroQuadra;
        this.numeroLoteImovel = numeroLoteImovel;
        this.numeroSubloteImovel = numeroSubloteImovel;
        this.enderecoImovel = enderecoImovel;
        this.descricaoAbreviadaCategoriaImovel = descricaoAbreviadaCategoriaImovel;
        this.numeroHidrometro = numeroHidrometro;
        this.quantidadeEconomias = quantidadeEconomias;
        this.numeroLeituraAnterior = numeroLeituraAnterior;
        this.numeroFaixaLeituraEsperadaInicial = numeroFaixaLeituraEsperadaInicial;
        this.numeroFaixaLeituraEsperadaFinal = numeroFaixaLeituraEsperadaFinal;
        this.numeroLeituraHidrometro = numeroLeituraHidrometro;
        this.indicadorConfirmacaoLeitura = indicadorConfirmacaoLeitura;
        this.tempoLeitura = tempoLeitura;
        this.ultimaAlteracao = ultimaAlteracao;
        this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
        this.leituraAnormalidade = leituraAnormalidade;
        this.localidade = localidade;
        this.imovel = imovel;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.imovelPerfil = imovelPerfil;
        this.faturamentoGrupo = faturamentoGrupo;
        this.hidrometroProtecao = hidrometroProtecao;
        this.hidrometroMarca = hidrometroMarca;
        this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
        this.roteiroEmpresa = roteiroEmpresa;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.empresa = empresa;
        this.hidrometroCapacidade = hidrometroCapacidade;
        this.medicaoTipo = medicaoTipo;
    }

    /** default constructor */
    public MovimentoRoteiroEmpresa() {
    }

    /** minimal constructor */
    public MovimentoRoteiroEmpresa(Integer id, Integer anoMesMovimento, Integer codigoSetorComercial, 
    		Integer numeroQuadra, String numeroLoteImovel, String numeroSubloteImovel, Date ultimaAlteracao, 
    		LeituraAnormalidade leituraAnormalidade, Localidade localidade, Imovel imovel, 
    		LigacaoEsgotoSituacao ligacaoEsgotoSituacao, ImovelPerfil imovelPerfil, FaturamentoGrupo faturamentoGrupo, 
    		HidrometroProtecao hidrometroProtecao, HidrometroMarca hidrometroMarca, 
    		HidrometroLocalInstalacao hidrometroLocalInstalacao, gcom.micromedicao.RoteiroEmpresa roteiroEmpresa, 
    		LigacaoAguaSituacao ligacaoAguaSituacao, Empresa empresa, HidrometroCapacidade hidrometroCapacidade, 
    		MedicaoTipo medicaoTipo) {
        this.id = id;
        this.anoMesMovimento = anoMesMovimento;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.numeroLoteImovel = numeroLoteImovel;
        this.numeroSubloteImovel = numeroSubloteImovel;
        this.ultimaAlteracao = ultimaAlteracao;
        this.leituraAnormalidade = leituraAnormalidade;
        this.localidade = localidade;
        this.imovel = imovel;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.imovelPerfil = imovelPerfil;
        this.faturamentoGrupo = faturamentoGrupo;
        this.hidrometroProtecao = hidrometroProtecao;
        this.hidrometroMarca = hidrometroMarca;
        this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
        this.roteiroEmpresa = roteiroEmpresa;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
        this.empresa = empresa;
        this.hidrometroCapacidade = hidrometroCapacidade;
        this.medicaoTipo = medicaoTipo;
    }
    public MovimentoRoteiroEmpresa(Imovel imovel,
    	Localidade localidade,
    	Integer codigoSetorComercial,
    	Integer numeroQuadra, 
    	String numeroLoteImovel, 
    	String numeroSubloteImovel,
    	MedicaoTipo medicaoTipo,
    	Integer numeroLeituraHidrometro,
    	LeituraAnormalidade leituraAnormalidade,
    	Date tempoLeitura,
    	Short indicadorConfirmacaoLeitura) {
        
    	this.imovel = imovel;
    	this.localidade = localidade;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.numeroLoteImovel = numeroLoteImovel;
        this.numeroSubloteImovel = numeroSubloteImovel;
        this.medicaoTipo = medicaoTipo;
        this.numeroLeituraHidrometro = numeroLeituraHidrometro;
        this.leituraAnormalidade = leituraAnormalidade;
        this.tempoLeitura = tempoLeitura;
        this.indicadorConfirmacaoLeitura = indicadorConfirmacaoLeitura;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoMesMovimento() {
        return this.anoMesMovimento;
    }

    public void setAnoMesMovimento(Integer anoMesMovimento) {
        this.anoMesMovimento = anoMesMovimento;
    }

    public Integer getCodigoSetorComercial() {
        return this.codigoSetorComercial;
    }

    public void setCodigoSetorComercial(Integer codigoSetorComercial) {
        this.codigoSetorComercial = codigoSetorComercial;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(Integer numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public String getNumeroLoteImovel() {
        return this.numeroLoteImovel;
    }

    public void setNumeroLoteImovel(String numeroLoteImovel) {
        this.numeroLoteImovel = numeroLoteImovel;
    }

    public String getNumeroSubloteImovel() {
        return this.numeroSubloteImovel;
    }

    public void setNumeroSubloteImovel(String numeroSubloteImovel) {
        this.numeroSubloteImovel = numeroSubloteImovel;
    }

    public String getEnderecoImovel() {
        return this.enderecoImovel;
    }

    public void setEnderecoImovel(String enderecoImovel) {
        this.enderecoImovel = enderecoImovel;
    }

    public String getDescricaoAbreviadaCategoriaImovel() {
        return this.descricaoAbreviadaCategoriaImovel;
    }

    public void setDescricaoAbreviadaCategoriaImovel(String descricaoAbreviadaCategoriaImovel) {
        this.descricaoAbreviadaCategoriaImovel = descricaoAbreviadaCategoriaImovel;
    }

    public String getNumeroHidrometro() {
        return this.numeroHidrometro;
    }

    public void setNumeroHidrometro(String numeroHidrometro) {
        this.numeroHidrometro = numeroHidrometro;
    }

    public Short getQuantidadeEconomias() {
        return this.quantidadeEconomias;
    }

    public void setQuantidadeEconomias(Short quantidadeEconomias) {
        this.quantidadeEconomias = quantidadeEconomias;
    }

    public Integer getNumeroLeituraAnterior() {
        return this.numeroLeituraAnterior;
    }

    public void setNumeroLeituraAnterior(Integer numeroLeituraAnterior) {
        this.numeroLeituraAnterior = numeroLeituraAnterior;
    }

    public Integer getNumeroFaixaLeituraEsperadaInicial() {
        return this.numeroFaixaLeituraEsperadaInicial;
    }

    public void setNumeroFaixaLeituraEsperadaInicial(Integer numeroFaixaLeituraEsperadaInicial) {
        this.numeroFaixaLeituraEsperadaInicial = numeroFaixaLeituraEsperadaInicial;
    }

    public Integer getNumeroFaixaLeituraEsperadaFinal() {
        return this.numeroFaixaLeituraEsperadaFinal;
    }

    public void setNumeroFaixaLeituraEsperadaFinal(Integer numeroFaixaLeituraEsperadaFinal) {
        this.numeroFaixaLeituraEsperadaFinal = numeroFaixaLeituraEsperadaFinal;
    }

    public Integer getNumeroLeituraHidrometro() {
        return this.numeroLeituraHidrometro;
    }

    public void setNumeroLeituraHidrometro(Integer numeroLeituraHidrometro) {
        this.numeroLeituraHidrometro = numeroLeituraHidrometro;
    }

    public Short getIndicadorConfirmacaoLeitura() {
        return this.indicadorConfirmacaoLeitura;
    }

    public void setIndicadorConfirmacaoLeitura(Short indicadorConfirmacaoLeitura) {
        this.indicadorConfirmacaoLeitura = indicadorConfirmacaoLeitura;
    }

    public Date getTempoLeitura() {
        return this.tempoLeitura;
    }

    public void setTempoLeitura(Date tempoLeitura) {
        this.tempoLeitura = tempoLeitura;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Date getDataInstalacaoHidrometro() {
        return this.dataInstalacaoHidrometro;
    }

    public void setDataInstalacaoHidrometro(Date dataInstalacaoHidrometro) {
        this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
    }

    public LeituraAnormalidade getLeituraAnormalidade() {
        return this.leituraAnormalidade;
    }

    public void setLeituraAnormalidade(LeituraAnormalidade leituraAnormalidade) {
        this.leituraAnormalidade = leituraAnormalidade;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
        return this.ligacaoEsgotoSituacao;
    }

    public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
    }

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public HidrometroProtecao getHidrometroProtecao() {
        return this.hidrometroProtecao;
    }

    public void setHidrometroProtecao(HidrometroProtecao hidrometroProtecao) {
        this.hidrometroProtecao = hidrometroProtecao;
    }

    public HidrometroMarca getHidrometroMarca() {
        return this.hidrometroMarca;
    }

    public void setHidrometroMarca(HidrometroMarca hidrometroMarca) {
        this.hidrometroMarca = hidrometroMarca;
    }

    public HidrometroLocalInstalacao getHidrometroLocalInstalacao() {
        return this.hidrometroLocalInstalacao;
    }

    public void setHidrometroLocalInstalacao(HidrometroLocalInstalacao hidrometroLocalInstalacao) {
        this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
    }

    public gcom.micromedicao.RoteiroEmpresa getRoteiroEmpresa() {
        return this.roteiroEmpresa;
    }

    public void setRoteiroEmpresa(gcom.micromedicao.RoteiroEmpresa roteiroEmpresa) {
        this.roteiroEmpresa = roteiroEmpresa;
    }

    public LigacaoAguaSituacao getLigacaoAguaSituacao() {
        return this.ligacaoAguaSituacao;
    }

    public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public HidrometroCapacidade getHidrometroCapacidade() {
        return this.hidrometroCapacidade;
    }

    public void setHidrometroCapacidade(HidrometroCapacidade hidrometroCapacidade) {
        this.hidrometroCapacidade = hidrometroCapacidade;
    }

    public MedicaoTipo getMedicaoTipo() {
        return this.medicaoTipo;
    }

    public void setMedicaoTipo(MedicaoTipo medicaoTipo) {
        this.medicaoTipo = medicaoTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Rota getRota() {
		return rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	/**
	 * @return Returns the indicadorAtualizacaoLeitura.
	 */
	public Integer getIndicadorAtualizacaoLeitura() {
		return indicadorAtualizacaoLeitura;
	}

	/**
	 * @param indicadorAtualizacaoLeitura The indicadorAtualizacaoLeitura to set.
	 */
	public void setIndicadorAtualizacaoLeitura(Integer indicadorAtualizacaoLeitura) {
		this.indicadorAtualizacaoLeitura = indicadorAtualizacaoLeitura;
	}
	
	public String getInscricao(){
		char separator = '.';
        StringBuffer buffer = new StringBuffer();
        buffer.append(Util.adicionarZerosEsquedaNumero(3,this.getLocalidade().getId().toString()));
        buffer.append(separator);
        buffer.append(Util.adicionarZerosEsquedaNumero(3,this.getCodigoSetorComercial().toString()));
        buffer.append(separator);
        buffer.append(Util.adicionarZerosEsquedaNumero(3,this.getNumeroQuadra().toString()));
        buffer.append(separator);
        buffer.append(Util.adicionarZerosEsquedaNumero(4,this.getNumeroLoteImovel().toString()));
        buffer.append(separator);
        buffer.append(Util.adicionarZerosEsquedaNumero(3,this.getNumeroSubloteImovel().toString()));
		return buffer.toString();
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getCodigoSubcategoria1() {
		return codigoSubcategoria1;
	}

	public void setCodigoSubcategoria1(Integer codigoSubcategoria1) {
		this.codigoSubcategoria1 = codigoSubcategoria1;
	}

	public Integer getCodigoSubcategoria2() {
		return codigoSubcategoria2;
	}

	public void setCodigoSubcategoria2(Integer codigoSubcategoria2) {
		this.codigoSubcategoria2 = codigoSubcategoria2;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	

	public String getDescricaoConsumoTipo() {
		return descricaoConsumoTipo;
	}

	public void setDescricaoConsumoTipo(String descricaoConsumoTipo) {
		this.descricaoConsumoTipo = descricaoConsumoTipo;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNomeLeiturista() {
		return nomeLeiturista;
	}

	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public Integer getNumeroConsumoFaturadoMenos1Mes() {
		return numeroConsumoFaturadoMenos1Mes;
	}

	public void setNumeroConsumoFaturadoMenos1Mes(
			Integer numeroConsumoFaturadoMenos1Mes) {
		this.numeroConsumoFaturadoMenos1Mes = numeroConsumoFaturadoMenos1Mes;
	}

	public Integer getNumeroConsumoFaturadoMenos2Meses() {
		return numeroConsumoFaturadoMenos2Meses;
	}

	public void setNumeroConsumoFaturadoMenos2Meses(
			Integer numeroConsumoFaturadoMenos2Meses) {
		this.numeroConsumoFaturadoMenos2Meses = numeroConsumoFaturadoMenos2Meses;
	}

	public Integer getNumeroConsumoFaturadoMenos3Meses() {
		return numeroConsumoFaturadoMenos3Meses;
	}

	public void setNumeroConsumoFaturadoMenos3Meses(
			Integer numeroConsumoFaturadoMenos3Meses) {
		this.numeroConsumoFaturadoMenos3Meses = numeroConsumoFaturadoMenos3Meses;
	}

	public Integer getNumeroConsumoFaturadoMenos4Meses() {
		return numeroConsumoFaturadoMenos4Meses;
	}

	public void setNumeroConsumoFaturadoMenos4Meses(
			Integer numeroConsumoFaturadoMenos4Meses) {
		this.numeroConsumoFaturadoMenos4Meses = numeroConsumoFaturadoMenos4Meses;
	}

	public Integer getNumeroConsumoFaturadoMenos5Meses() {
		return numeroConsumoFaturadoMenos5Meses;
	}

	public void setNumeroConsumoFaturadoMenos5Meses(
			Integer numeroConsumoFaturadoMenos5Meses) {
		this.numeroConsumoFaturadoMenos5Meses = numeroConsumoFaturadoMenos5Meses;
	}

	public Integer getNumeroConsumoFaturadoMenos6Meses() {
		return numeroConsumoFaturadoMenos6Meses;
	}

	public void setNumeroConsumoFaturadoMenos6Meses(
			Integer numeroConsumoFaturadoMenos6Meses) {
		this.numeroConsumoFaturadoMenos6Meses = numeroConsumoFaturadoMenos6Meses;
	}

	public Integer getNumeroConsumoMedio() {
		return numeroConsumoMedio;
	}

	public void setNumeroConsumoMedio(Integer numeroConsumoMedio) {
		this.numeroConsumoMedio = numeroConsumoMedio;
	}

	public String getNumeroImovel() {
		return numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public String getNumeroLacreLigacaoAgua() {
		return numeroLacreLigacaoAgua;
	}

	public void setNumeroLacreLigacaoAgua(String numeroLacreLigacaoAgua) {
		this.numeroLacreLigacaoAgua = numeroLacreLigacaoAgua;
	}

	public Integer getNumeroSequencialRota() {
		return numeroSequencialRota;
	}

	public void setNumeroSequencialRota(Integer numeroSequencialRota) {
		this.numeroSequencialRota = numeroSequencialRota;
	}

	public Short getQuantidadeEconomias2() {
		return quantidadeEconomias2;
	}

	public void setQuantidadeEconomias2(Short quantidadeEconomias2) {
		this.quantidadeEconomias2 = quantidadeEconomias2;
	}

	public MovimentoRoteiroEmpresa(Integer id, Integer anoMesMovimento, Integer codigoSetorComercial, 
			String nomeCliente, Integer numeroQuadra, String numeroLoteImovel, String numeroSubloteImovel,
			String enderecoImovel, String descricaoAbreviadaCategoriaImovel, String numeroHidrometro, 
			Short quantidadeEconomias, Integer numeroLeituraAnterior, 
			Integer numeroFaixaLeituraEsperadaInicial, Integer numeroFaixaLeituraEsperadaFinal, 
			Integer numeroLeituraHidrometro, Short indicadorConfirmacaoLeitura, Date tempoLeitura,
			Date ultimaAlteracao, Date dataInstalacaoHidrometro, LeituraAnormalidade leituraAnormalidade, 
			Localidade localidade, Imovel imovel, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, 
			ImovelPerfil imovelPerfil, FaturamentoGrupo faturamentoGrupo, HidrometroProtecao hidrometroProtecao,
			HidrometroMarca hidrometroMarca, HidrometroLocalInstalacao hidrometroLocalInstalacao, 
			RoteiroEmpresa roteiroEmpresa, LigacaoAguaSituacao ligacaoAguaSituacao, 
			Empresa empresa, HidrometroCapacidade hidrometroCapacidade, MedicaoTipo medicaoTipo, 
			Rota rota, Date dataHoraProcessamento, Integer indicadorAtualizacaoLeitura, 
			Integer codigoSubcategoria1, Categoria categoria2, 
			Short quantidadeEconomias2, Integer codigoSubcategoria2, Integer numeroConsumoFaturadoMenos1Mes, 
			Integer numeroConsumoFaturadoMenos2Meses, Integer numeroConsumoFaturadoMenos3Meses, 
			String descricaoConsumoTipo, String numeroLacreLigacaoAgua, Integer numeroSequencialRota, 
			Integer numeroConsumoFaturadoMenos4Meses, Integer numeroConsumoFaturadoMenos5Meses, 
			Integer numeroConsumoFaturadoMenos6Meses, Integer numeroConsumoMedio, Short codigoRota, 
			Logradouro logradouro, String nomeLogradouro, String numeroImovel, String complementoEndereco, 
			String inscricaoImovel, String nomeLeiturista, GerenciaRegional gerenciaRegional, 
			String nomeBairro,LeituraTipo leituraTipo, String descricaoAbreviadaLogradouroTitulo, String descricaoAbreviadaLogradouroTipo) {
		  
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.anoMesMovimento = anoMesMovimento;
		this.codigoSetorComercial = codigoSetorComercial;
		this.nomeCliente = nomeCliente;
		this.numeroQuadra = numeroQuadra;
		this.numeroLoteImovel = numeroLoteImovel;
		this.numeroSubloteImovel = numeroSubloteImovel;
		this.enderecoImovel = enderecoImovel;
		this.descricaoAbreviadaCategoriaImovel = descricaoAbreviadaCategoriaImovel;
		this.numeroHidrometro = numeroHidrometro;
		this.quantidadeEconomias = quantidadeEconomias;
		this.numeroLeituraAnterior = numeroLeituraAnterior;
		this.numeroFaixaLeituraEsperadaInicial = numeroFaixaLeituraEsperadaInicial;
		this.numeroFaixaLeituraEsperadaFinal = numeroFaixaLeituraEsperadaFinal;
		this.numeroLeituraHidrometro = numeroLeituraHidrometro;
		this.indicadorConfirmacaoLeitura = indicadorConfirmacaoLeitura;
		this.tempoLeitura = tempoLeitura;
		this.ultimaAlteracao = ultimaAlteracao;
		this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
		this.leituraAnormalidade = leituraAnormalidade;
		this.localidade = localidade;
		this.imovel = imovel;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.imovelPerfil = imovelPerfil;
		this.faturamentoGrupo = faturamentoGrupo;
		this.hidrometroProtecao = hidrometroProtecao;
		this.hidrometroMarca = hidrometroMarca;
		this.hidrometroLocalInstalacao = hidrometroLocalInstalacao;
		this.roteiroEmpresa = roteiroEmpresa;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.empresa = empresa;
		this.hidrometroCapacidade = hidrometroCapacidade;
		this.medicaoTipo = medicaoTipo;
		this.rota = rota;
		this.dataHoraProcessamento = dataHoraProcessamento;
		this.indicadorAtualizacaoLeitura = indicadorAtualizacaoLeitura;
		this.codigoSubcategoria1 = codigoSubcategoria1;
		this.categoria2 = categoria2;
		this.quantidadeEconomias2 = quantidadeEconomias2;
		this.codigoSubcategoria2 = codigoSubcategoria2;
		this.numeroConsumoFaturadoMenos1Mes = numeroConsumoFaturadoMenos1Mes;
		this.numeroConsumoFaturadoMenos2Meses = numeroConsumoFaturadoMenos2Meses;
		this.numeroConsumoFaturadoMenos3Meses = numeroConsumoFaturadoMenos3Meses;
		this.descricaoConsumoTipo = descricaoConsumoTipo;
		this.numeroLacreLigacaoAgua = numeroLacreLigacaoAgua;
		this.numeroSequencialRota = numeroSequencialRota;
		this.numeroConsumoFaturadoMenos4Meses = numeroConsumoFaturadoMenos4Meses;
		this.numeroConsumoFaturadoMenos5Meses = numeroConsumoFaturadoMenos5Meses;
		this.numeroConsumoFaturadoMenos6Meses = numeroConsumoFaturadoMenos6Meses;
		this.numeroConsumoMedio = numeroConsumoMedio;
		this.codigoRota = codigoRota;
		this.logradouro = logradouro;
		this.nomeLogradouro = nomeLogradouro;
		this.numeroImovel = numeroImovel;
		this.complementoEndereco = complementoEndereco;
		this.inscricaoImovel = inscricaoImovel;
		this.nomeLeiturista = nomeLeiturista;
		this.gerenciaRegional = gerenciaRegional;
		this.nomeBairro = nomeBairro;
		this.leituraTipo = leituraTipo;
		this.descricaoAbreviadaLogradouroTitulo = descricaoAbreviadaLogradouroTitulo;
		this.descricaoAbreviadaLogradouroTipo = descricaoAbreviadaLogradouroTipo;
	}

	

	public Categoria getCategoria2() {
		return categoria2;
	}

	public void setCategoria2(Categoria categoria2) {
		this.categoria2 = categoria2;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getCodigoQuadraFace() {
		return codigoQuadraFace;
	}

	public void setCodigoQuadraFace(Integer codigoQuadraFace) {
		this.codigoQuadraFace = codigoQuadraFace;
	}
	
	public String getDescricaoLigacaoAguaSituacao() {
		return descricaoLigacaoAguaSituacao;
	}

	public void setDescricaoLigacaoAguaSituacao(String descricaoLigacaoAguaSituacao) {
		this.descricaoLigacaoAguaSituacao = descricaoLigacaoAguaSituacao;
	}

	public Integer getNumeroMoradores() {
		return numeroMoradores;
	}

	public void setNumeroMoradores(Integer numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}

	public Integer getCodigoAnormalidadeAnterior() {
		return codigoAnormalidadeAnterior;
	}

	public void setCodigoAnormalidadeAnterior(Integer codigoAnormalidadeAnterior) {
		this.codigoAnormalidadeAnterior = codigoAnormalidadeAnterior;
	}

	public String getDescricaoAnormalidadeAnterior() {
		return descricaoAnormalidadeAnterior;
	}

	public void setDescricaoAnormalidadeAnterior(
			String descricaoAnormalidadeAnterior) {
		this.descricaoAnormalidadeAnterior = descricaoAnormalidadeAnterior;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getDescricaoHidrometroMarca() {
		return descricaoHidrometroMarca;
	}

	public void setDescricaoHidrometroMarca(String descricaoHidrometroMarca) {
		this.descricaoHidrometroMarca = descricaoHidrometroMarca;
	}

	public Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	public Date getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(Date dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}
}