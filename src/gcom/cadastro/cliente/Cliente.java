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
package gcom.cadastro.cliente;

import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 * @created 22 de Julho de 2005
 */
@ControleAlteracao()
public class Cliente extends ObjetoTransacao {
	
	public static final int ATRIBUTOS_CLIENTE_INSERIR = 28; //Operacao.OPERACAO_CLIENTE_INSERIR
	public static final int ATRIBUTOS_CLIENTE_ATUALIZAR = 38; //Operacao.OPERACAO_CLIENTE_ATUALIZAR
	public static final int ATRIBUTOS_CLIENTE_REMOVER = 39; //Operacao.OPERACAO_CLIENTE_REMOVER
	public static final int ATRIBUTOS_ATUALIZAR_DADOS_CLIENTE_PROMAIS = 1662; //Operacao.OPERACAO_ATUALIZAR_DADOS_CLIENTE_PROMAIS
	public static final int OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL = 1509;
	public static final Short INDICADOR_NOME_FANTASIA = 1;
	public static final Short INDICADOR_NOME_RECEITA = 2;
	
	private static final long serialVersionUID = 1L;
	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR, ATRIBUTOS_ATUALIZAR_DADOS_CLIENTE_PROMAIS,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
    private String nome;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private String nomeAbreviado;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR, ATRIBUTOS_ATUALIZAR_DADOS_CLIENTE_PROMAIS,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
    private String cpf;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private String rg;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Date dataEmissaoRg;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Short dataVencimento;
    
    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Date dataNascimento;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR, ATRIBUTOS_ATUALIZAR_DADOS_CLIENTE_PROMAIS,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
    private String cnpj;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private String email;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Short indicadorUso;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Date ultimaAlteracao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Short indicadorAcaoCobranca;

    /** persistent field */
    @ControleAlteracao(value=FiltroCliente.ORGAO_EXPEDIDOR_RG, 
    		funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg;

    /** persistent field */
    private gcom.cadastro.cliente.Cliente cliente;

    /** persistent field */
    @ControleAlteracao(value=FiltroCliente.SEXO, 
    		funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
    private gcom.cadastro.cliente.PessoaSexo pessoaSexo;

    /** persistent field */
    @ControleAlteracao(value=FiltroCliente.PROFISSAO, 
    		funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
    private gcom.cadastro.cliente.Profissao profissao;

    /** persistent field */
    @ControleAlteracao(value=FiltroCliente.UNIDADE_FEDERACAO, 
    		funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private UnidadeFederacao unidadeFederacao;

    /** persistent field */
    @ControleAlteracao(value=FiltroCliente.CLIENTE_TIPO,
    		funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
    private gcom.cadastro.cliente.ClienteTipo clienteTipo;

    /** persistent field */
    @ControleAlteracao(value=FiltroCliente.RAMO_ATIVIDADE, 
    		funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
    private gcom.cadastro.cliente.RamoAtividade ramoAtividade;

    /** persistent field */
    @ControleAlteracao(value=FiltroCliente.CLIENTE_TELEFONES, 
    		funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR, ATRIBUTOS_ATUALIZAR_DADOS_CLIENTE_PROMAIS,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
    private Set clienteFones;

    /** persistent field */
//    @ControleAlteracao(value=FiltroCliente.CLIENTE_RESPONSAVEL, 
//    		funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Set clienteImoveis;

    /** persistent field */
    @ControleAlteracao(value=FiltroCliente.CLIENTE_ENDERECOS, 
    		funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Set clienteEnderecos;

	/** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Short diaVencimento;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private String nomeMae;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Short indicadorAcrescimos;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Short indicadorGeraArquivoTexto;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Short indicadorGeraFaturaAntecipada;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CLIENTE_INSERIR, ATRIBUTOS_CLIENTE_ATUALIZAR})
    private Short indicadorVencimentoMesSeguinte;
    
    
    private Short indicadorUsoNomeFantasiaConta;
    
    private Short indicadorPermiteNegativacao;
    
    //public static final Integer CODIGO_CLIENTE_MARIO_GOUVEIA = 6548350;
    
    public Short getIndicadorUsoNomeFantasiaConta() {
		return indicadorUsoNomeFantasiaConta;
	}

	public void setIndicadorUsoNomeFantasiaConta(Short indicadorUsoNomeFantasiaConta) {
		this.indicadorUsoNomeFantasiaConta = indicadorUsoNomeFantasiaConta;
	}

	public static final Short GERA_ARQUIVO_TEXTO_SIM = 1;

	

	/**
	 * full constructor
	 * 
	 * @param nome
	 *            Descrição do parâmetro
	 * @param nomeAbreviado
	 *            Descrição do parâmetro
	 * @param cpf
	 *            Descrição do parâmetro
	 * @param rg
	 *            Descrição do parâmetro
	 * @param dataEmissaoRg
	 *            Descrição do parâmetro
	 * @param dataNascimento
	 *            Descrição do parâmetro
	 * @param cnpj
	 *            Descrição do parâmetro
	 * @param email
	 *            Descrição do parâmetro
	 * @param indicadorUso
	 *            Descrição do parâmetro
	 * @param ultimaAlteracao
	 *            Descrição do parâmetro
	 * @param orgaoExpedidorRg
	 *            Descrição do parâmetro
	 * @param cliente
	 *            Descrição do parâmetro
	 * @param pessoaSexo
	 *            Descrição do parâmetro
	 * @param profissao
	 *            Descrição do parâmetro
	 * @param unidadeFederacao
	 *            Descrição do parâmetro
	 * @param clienteTipo
	 *            Descrição do parâmetro
	 * @param ramoAtividade
	 *            Descrição do parâmetro
	 */
	public Cliente(String nome, String nomeAbreviado, String cpf, String rg,
			Date dataEmissaoRg, Date dataNascimento, String cnpj, String email,
			Short indicadorUso, Date ultimaAlteracao,
			gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg,
			gcom.cadastro.cliente.Cliente cliente,
			gcom.cadastro.cliente.PessoaSexo pessoaSexo,
			gcom.cadastro.cliente.Profissao profissao,
			UnidadeFederacao unidadeFederacao,
			gcom.cadastro.cliente.ClienteTipo clienteTipo,
			gcom.cadastro.cliente.RamoAtividade ramoAtividade, Short diaVencimento) {
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.cpf = cpf;
		this.rg = rg;
		this.dataEmissaoRg = dataEmissaoRg;
		this.dataNascimento = dataNascimento;
		this.cnpj = cnpj;
		this.email = email;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.orgaoExpedidorRg = orgaoExpedidorRg;
		this.cliente = cliente;
		this.pessoaSexo = pessoaSexo;
		this.profissao = profissao;
		this.unidadeFederacao = unidadeFederacao;
		this.clienteTipo = clienteTipo;
		this.ramoAtividade = ramoAtividade;
		this.diaVencimento = diaVencimento;
	}
	
	/**
	 * full constructor
	 */
	public Cliente(String nome, String nomeAbreviado, String cpf, String rg,
			Date dataEmissaoRg, Date dataNascimento, String cnpj, String email,
			Short indicadorUso,  Date ultimaAlteracao,
			gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg,
			gcom.cadastro.cliente.Cliente cliente,
			gcom.cadastro.cliente.PessoaSexo pessoaSexo,
			gcom.cadastro.cliente.Profissao profissao,
			UnidadeFederacao unidadeFederacao,
			gcom.cadastro.cliente.ClienteTipo clienteTipo,
			gcom.cadastro.cliente.RamoAtividade ramoAtividade) {
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.cpf = cpf;
		this.rg = rg;
		this.dataEmissaoRg = dataEmissaoRg;
		this.dataNascimento = dataNascimento;
		this.cnpj = cnpj;
		this.email = email;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.orgaoExpedidorRg = orgaoExpedidorRg;
		this.cliente = cliente;
		this.pessoaSexo = pessoaSexo;
		this.profissao = profissao;
		this.unidadeFederacao = unidadeFederacao;
		this.clienteTipo = clienteTipo;
		this.ramoAtividade = ramoAtividade;
	}
	
	public Cliente(String nome, String nomeAbreviado, String cpf, String rg,
			Date dataEmissaoRg, Date dataNascimento, String cnpj, String email,
			Short indicadorUso,  Date ultimaAlteracao,
			gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg,
			gcom.cadastro.cliente.Cliente cliente,
			gcom.cadastro.cliente.PessoaSexo pessoaSexo,
			gcom.cadastro.cliente.Profissao profissao,
			UnidadeFederacao unidadeFederacao,
			gcom.cadastro.cliente.ClienteTipo clienteTipo,
			Short indicadorUsoNomeFantasiaConta,
			gcom.cadastro.cliente.RamoAtividade ramoAtividade
			) {
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.cpf = cpf;
		this.rg = rg;
		this.dataEmissaoRg = dataEmissaoRg;
		this.dataNascimento = dataNascimento;
		this.cnpj = cnpj;
		this.email = email;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.orgaoExpedidorRg = orgaoExpedidorRg;
		this.cliente = cliente;
		this.pessoaSexo = pessoaSexo;
		this.profissao = profissao;
		this.unidadeFederacao = unidadeFederacao;
		this.clienteTipo = clienteTipo;
		this.indicadorUsoNomeFantasiaConta = indicadorUsoNomeFantasiaConta;
		this.ramoAtividade = ramoAtividade;
		
	}
	
	/**
	 * full constructor
	 */
	public Cliente(String nome, String nomeAbreviado, String cpf, String rg,
			Date dataEmissaoRg, Date dataNascimento, String cnpj, String email,
			Short indicadorUso, Short indicadorAcrescimos, Date ultimaAlteracao,
			gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg,
			gcom.cadastro.cliente.Cliente cliente,
			gcom.cadastro.cliente.PessoaSexo pessoaSexo,
			gcom.cadastro.cliente.Profissao profissao,
			UnidadeFederacao unidadeFederacao,
			gcom.cadastro.cliente.ClienteTipo clienteTipo,
			gcom.cadastro.cliente.RamoAtividade ramoAtividade,
			Short indicadorUsoNomeFantasiaConta) {
		this.nome = nome;
		this.nomeAbreviado = nomeAbreviado;
		this.cpf = cpf;
		this.rg = rg;
		this.dataEmissaoRg = dataEmissaoRg;
		this.dataNascimento = dataNascimento;
		this.cnpj = cnpj;
		this.email = email;
		this.indicadorUso = indicadorUso;
		this.indicadorAcrescimos = indicadorAcrescimos;
		this.ultimaAlteracao = ultimaAlteracao;
		this.orgaoExpedidorRg = orgaoExpedidorRg;
		this.cliente = cliente;
		this.pessoaSexo = pessoaSexo;
		this.profissao = profissao;
		this.unidadeFederacao = unidadeFederacao;
		this.clienteTipo = clienteTipo;
		this.ramoAtividade = ramoAtividade;
		this.indicadorUsoNomeFantasiaConta = indicadorUsoNomeFantasiaConta;
	}
	
	/**
	 * full constructor para atualização cadastral
	 */
	public Cliente(Cliente clienteCadastrado) {
		this.nome = clienteCadastrado.getNome();
		//this.nomeAbreviado = clienteCadastrado.getNomeAbreviado();
		this.cpf = clienteCadastrado.getCpf();
		//this.rg = clienteCadastrado.getRg();
		//this.dataEmissaoRg = clienteCadastrado.getDataEmissaoRg();
		//this.dataNascimento = clienteCadastrado.getDataNascimento();
		this.cnpj = clienteCadastrado.getCnpj();
		//this.email = clienteCadastrado.getEmail();
		this.indicadorUso = clienteCadastrado.getIndicadorUso();
		this.indicadorAcrescimos = clienteCadastrado.getIndicadorAcrescimos();
		this.ultimaAlteracao = clienteCadastrado.getUltimaAlteracao();
		this.orgaoExpedidorRg = clienteCadastrado.getOrgaoExpedidorRg();
		this.cliente = clienteCadastrado.getCliente();
		this.pessoaSexo = clienteCadastrado.getPessoaSexo();
		this.profissao = clienteCadastrado.getProfissao();
		this.unidadeFederacao = clienteCadastrado.getUnidadeFederacao();
		this.clienteTipo = clienteCadastrado.getClienteTipo();
		this.ramoAtividade = clienteCadastrado.getRamoAtividade();
		this.indicadorUsoNomeFantasiaConta = clienteCadastrado.getIndicadorUsoNomeFantasiaConta();
		this.indicadorAcaoCobranca = ConstantesSistema.NAO;
		this.indicadorGeraArquivoTexto = ConstantesSistema.NAO;
		this.indicadorGeraFaturaAntecipada = ConstantesSistema.NAO;
		this.indicadorPermiteNegativacao = ConstantesSistema.NAO;
		this.indicadorVencimentoMesSeguinte = ConstantesSistema.NAO;
	}

	/**
	 * full constructor
	 * 
	 * @param nome
	 *            Descrição do parâmetro
	 */
	public Cliente(String nome) {
		this.nome = nome;

	}

	/**
	 * full constructor
	 * 
	 * @param nome
	 *            Descrição do parâmetro
	 */
	public Cliente(String nome, Integer id) {
		this.nome = nome;
		this.id = id;
	}

	/**
	 * full constructor
	 * 
	 * @param id
	 *            Description of the Parameter
	 * @param nome
	 *            Descrição do parâmetro
	 * @param clienteTipo
	 *            Description of the Parameter
	 * @param cpf
	 *            Description of the Parameter
	 * @param cnpj
	 *            Description of the Parameter
	 */
	public Cliente(Integer id, String nome,
			gcom.cadastro.cliente.ClienteTipo clienteTipo, String cpf,
			String cnpj) {
		this.id = id;
		this.nome = nome;
		this.clienteTipo = clienteTipo;
		this.cpf = cpf;
		this.cnpj = cnpj;
	}

	/**
	 * default constructor
	 */
	public Cliente() {
	}

	/**
	 * minimal constructor
	 * 
	 * @param orgaoExpedidorRg
	 *            Descrição do parâmetro
	 * @param cliente
	 *            Descrição do parâmetro
	 * @param pessoaSexo
	 *            Descrição do parâmetro
	 * @param profissao
	 *            Descrição do parâmetro
	 * @param unidadeFederacao
	 *            Descrição do parâmetro
	 * @param clienteTipo
	 *            Descrição do parâmetro
	 * @param ramoAtividade
	 *            Descrição do parâmetro
	 */
	public Cliente(gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg,
			gcom.cadastro.cliente.Cliente cliente,
			gcom.cadastro.cliente.PessoaSexo pessoaSexo,
			gcom.cadastro.cliente.Profissao profissao,
			UnidadeFederacao unidadeFederacao,
			gcom.cadastro.cliente.ClienteTipo clienteTipo,
			gcom.cadastro.cliente.RamoAtividade ramoAtividade) {
		this.orgaoExpedidorRg = orgaoExpedidorRg;
		this.cliente = cliente;
		this.pessoaSexo = pessoaSexo;
		this.profissao = profissao;
		this.unidadeFederacao = unidadeFederacao;
		this.clienteTipo = clienteTipo;
		this.ramoAtividade = ramoAtividade;
	}
	
	public Cliente(Integer id, String nome, Short dataVencimento) {
		this.id = id;
		this.nome = nome;
		this.dataVencimento = dataVencimento;
	}
	
	public Cliente(Integer id, String nome, Short dataVencimento, Short indicadorVencimentoMesSeguinte) {
		this.id = id;
		this.nome = nome;
		this.dataVencimento = dataVencimento;
		this.indicadorVencimentoMesSeguinte = indicadorVencimentoMesSeguinte;
	}

	/**
	 * Retorna o valor de id
	 * 
	 * @return O valor de id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Seta o valor de id
	 * 
	 * @param id
	 *            O novo valor de id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Retorna o valor de nome
	 * 
	 * @return O valor de nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Seta o valor de nome
	 * 
	 * @param nome
	 *            O novo valor de nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o valor de nomeAbreviado
	 * 
	 * @return O valor de nomeAbreviado
	 */
	public String getNomeAbreviado() {
		return this.nomeAbreviado;
	}

	/**
	 * Seta o valor de nomeAbreviado
	 * 
	 * @param nomeAbreviado
	 *            O novo valor de nomeAbreviado
	 */
	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	/**
	 * Retorna o valor de cpf
	 * 
	 * @return O valor de cpf
	 */
	public String getCpf() {
		return this.cpf;
	}

	/**
	 * Seta o valor de cpf
	 * 
	 * @param cpf
	 *            O novo valor de cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Retorna o valor de rg
	 * 
	 * @return O valor de rg
	 */
	public String getRg() {
		return this.rg;
	}

	/**
	 * Seta o valor de rg
	 * 
	 * @param rg
	 *            O novo valor de rg
	 */
	public void setRg(String rg) {
		this.rg = rg;
	}

	/**
	 * Retorna o valor de dataEmissaoRg
	 * 
	 * @return O valor de dataEmissaoRg
	 */
	public Date getDataEmissaoRg() {
		return this.dataEmissaoRg;
	}

	/**
	 * Seta o valor de dataEmissaoRg
	 * 
	 * @param dataEmissaoRg
	 *            O novo valor de dataEmissaoRg
	 */
	public void setDataEmissaoRg(Date dataEmissaoRg) {
		this.dataEmissaoRg = dataEmissaoRg;
	}

	/**
	 * Retorna o valor de dataNascimento
	 * 
	 * @return O valor de dataNascimento
	 */
	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	/**
	 * Seta o valor de dataNascimento
	 * 
	 * @param dataNascimento
	 *            O novo valor de dataNascimento
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Retorna o valor de cnpj
	 * 
	 * @return O valor de cnpj
	 */
	public String getCnpj() {
		return this.cnpj;
	}

	/**
	 * Seta o valor de cnpj
	 * 
	 * @param cnpj
	 *            O novo valor de cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * Retorna o valor de email
	 * 
	 * @return O valor de email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Seta o valor de email
	 * 
	 * @param email
	 *            O novo valor de email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna o valor de indicadorUso
	 * 
	 * @return O valor de indicadorUso
	 */
	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	/**
	 * Seta o valor de indicadorUso
	 * 
	 * @param indicadorUso
	 *            O novo valor de indicadorUso
	 */
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * Retorna o valor de ultimaAlteracao
	 * 
	 * @return O valor de ultimaAlteracao
	 */
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	/**
	 * Seta o valor de ultimaAlteracao
	 * 
	 * @param ultimaAlteracao
	 *            O novo valor de ultimaAlteracao
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * Retorna o valor de orgaoExpedidorRg
	 * 
	 * @return O valor de orgaoExpedidorRg
	 */
	public gcom.cadastro.cliente.OrgaoExpedidorRg getOrgaoExpedidorRg() {
		return this.orgaoExpedidorRg;
	}

	/**
	 * Seta o valor de orgaoExpedidorRg
	 * 
	 * @param orgaoExpedidorRg
	 *            O novo valor de orgaoExpedidorRg
	 */
	public void setOrgaoExpedidorRg(
			gcom.cadastro.cliente.OrgaoExpedidorRg orgaoExpedidorRg) {
		this.orgaoExpedidorRg = orgaoExpedidorRg;
	}

	/**
	 * Retorna o valor de cliente
	 * 
	 * @return O valor de cliente
	 */
	public gcom.cadastro.cliente.Cliente getCliente() {
		return this.cliente;
	}

	/**
	 * Seta o valor de cliente
	 * 
	 * @param cliente
	 *            O novo valor de cliente
	 */
	public void setCliente(gcom.cadastro.cliente.Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Retorna o valor de pessoaSexo
	 * 
	 * @return O valor de pessoaSexo
	 */
	public gcom.cadastro.cliente.PessoaSexo getPessoaSexo() {
		return this.pessoaSexo;
	}

	/**
	 * Seta o valor de pessoaSexo
	 * 
	 * @param pessoaSexo
	 *            O novo valor de pessoaSexo
	 */
	public void setPessoaSexo(gcom.cadastro.cliente.PessoaSexo pessoaSexo) {
		this.pessoaSexo = pessoaSexo;
	}

	/**
	 * Retorna o valor de profissao
	 * 
	 * @return O valor de profissao
	 */
	public gcom.cadastro.cliente.Profissao getProfissao() {
		return this.profissao;
	}

	/**
	 * Seta o valor de profissao
	 * 
	 * @param profissao
	 *            O novo valor de profissao
	 */
	public void setProfissao(gcom.cadastro.cliente.Profissao profissao) {
		this.profissao = profissao;
	}

	/**
	 * Retorna o valor de unidadeFederacao
	 * 
	 * @return O valor de unidadeFederacao
	 */
	public UnidadeFederacao getUnidadeFederacao() {
		return this.unidadeFederacao;
	}

	/**
	 * Seta o valor de unidadeFederacao
	 * 
	 * @param unidadeFederacao
	 *            O novo valor de unidadeFederacao
	 */
	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	/**
	 * Retorna o valor de clienteTipo
	 * 
	 * @return O valor de clienteTipo
	 */
	public gcom.cadastro.cliente.ClienteTipo getClienteTipo() {
		return this.clienteTipo;
	}

	/**
	 * Seta o valor de clienteTipo
	 * 
	 * @param clienteTipo
	 *            O novo valor de clienteTipo
	 */
	public void setClienteTipo(gcom.cadastro.cliente.ClienteTipo clienteTipo) {
		this.clienteTipo = clienteTipo;
	}

	/**
	 * Retorna o valor de ramoAtividade
	 * 
	 * @return O valor de ramoAtividade
	 */
	public gcom.cadastro.cliente.RamoAtividade getRamoAtividade() {
		return this.ramoAtividade;
	}

	/**
	 * Seta o valor de ramoAtividade
	 * 
	 * @param ramoAtividade
	 *            O novo valor de ramoAtividade
	 */
	public void setRamoAtividade(
			gcom.cadastro.cliente.RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @return Descrição do retorno
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param other
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof Cliente)) {
			return false;
		}
		Cliente castOther = (Cliente) other;

		return (this.getId().equals(castOther.getId()));
	}

	/**
	 * Retorna o valor de cpfFormatado
	 * 
	 * @return O valor de cpfFormatado
	 */
	public String getCpfFormatado() {
		String cpfFormatado = this.cpf;

		if (cpfFormatado != null && cpfFormatado.length() == 11) {

			cpfFormatado = cpfFormatado.substring(0, 3) + "."
					+ cpfFormatado.substring(3, 6) + "."
					+ cpfFormatado.substring(6, 9) + "-"
					+ cpfFormatado.substring(9, 11);
		}
		
		return cpfFormatado;
	}

	/**
	 * Retorna o valor de cnpjFormatado
	 * 
	 * @return O valor de cnpjFormatado
	 */
	public String getCnpjFormatado() {
		String cnpjFormatado = this.cnpj;
		String zeros = "";
		
		if (cnpjFormatado != null) {
			
			for (int a = 0; a < (14 - cnpjFormatado.length()); a++) {
				zeros = zeros.concat("0");
			}
			// concatena os zeros ao numero
			// caso o numero seja diferente de nulo
			cnpjFormatado = zeros.concat(cnpjFormatado);
			
			cnpjFormatado = cnpjFormatado.substring(0, 2) + "."
					+ cnpjFormatado.substring(2, 5) + "."
					+ cnpjFormatado.substring(5, 8) + "/"
					+ cnpjFormatado.substring(8, 12) + "-"
					+ cnpjFormatado.substring(12, 14);
		}
		
		return cnpjFormatado;
	}

	/**
	 * Retorna o valor de clienteEnderecos
	 * 
	 * @return O valor de clienteEnderecos
	 */
	public Set getClienteEnderecos() {
		return clienteEnderecos;
	}

	/**
	 * Seta o valor de clienteEnderecos
	 * 
	 * @param clienteEnderecos
	 *            O novo valor de clienteEnderecos
	 */
	public void setClienteEnderecos(Set clienteEnderecos) {
		this.clienteEnderecos = clienteEnderecos;
	}

	/**
	 * Retorna o valor de clienteFones
	 * 
	 * @return O valor de clienteFones
	 */
	public Set getClienteFones() {
		return clienteFones;
	}

	/**
	 * Seta o valor de clienteFones
	 * 
	 * @param clienteFones
	 *            O novo valor de clienteFones
	 */
	public void setClienteFones(Set clienteFones) {
		this.clienteFones = clienteFones;
	}

	public Set getClienteImoveis() {
		return clienteImoveis;
	}

	public void setClienteImoveis(Set clienteImoveis) {
		this.clienteImoveis = clienteImoveis;
	}

	public Short getIndicadorAcaoCobranca() {
		return indicadorAcaoCobranca;
	}

	public void setIndicadorAcaoCobranca(Short indicadorAcaoCobranca) {
		this.indicadorAcaoCobranca = indicadorAcaoCobranca;
	}
	
	/**
	 * @return Returns the diaVencimento.
	 */
	public Short getDiaVencimento() {
		return diaVencimento;
	}

	/**
	 * @param diaVencimento The diaVencimento to set.
	 */
	public void setDiaVencimento(Short diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public Filtro retornaFiltro() {
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,this.getId()));
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("orgaoExpedidorRg");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("pessoaSexo");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteEnderecos");
		return filtroCliente;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public Short getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Short dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }
    
    public Short getIndicadorAcrescimos() {
		return indicadorAcrescimos;
	}

	public void setIndicadorAcrescimos(Short indicadorCobrancaAcrescimos) {
		this.indicadorAcrescimos = indicadorCobrancaAcrescimos;
	}

	public Short getIndicadorGeraArquivoTexto() {
		return indicadorGeraArquivoTexto;
	}

	public void setIndicadorGeraArquivoTexto(Short indicadorGeraArquivoTexto) {
		this.indicadorGeraArquivoTexto = indicadorGeraArquivoTexto;
	}
	
	public String getDescricao(){
		return this.nome;
	}
	
	/**
	 * @return Retorna o campo indicadorGeraFaturaAntecipada.
	 */
	public Short getIndicadorGeraFaturaAntecipada() {
		return indicadorGeraFaturaAntecipada;
	}
	
	/**
	 * @param indicadorGeraFaturaAntecipada O indicadorGeraFaturaAntecipada a ser setado.
	 */
	public void setIndicadorGeraFaturaAntecipada(Short indicadorGeraFaturaAntecipada) {
		this.indicadorGeraFaturaAntecipada = indicadorGeraFaturaAntecipada;
	}
	
	/**
	 * @return Retorna o campo indicadorVencimentoMesSeguinte.
	 */
	public Short getIndicadorVencimentoMesSeguinte() {
		return indicadorVencimentoMesSeguinte;
	}

	/**
	 * @param indicadorVencimentoMesSeguinte O indicadorVencimentoMesSeguinte a ser setado.
	 */
	public void setIndicadorVencimentoMesSeguinte(
			Short indicadorVencimentoMesSeguinte) {
		this.indicadorVencimentoMesSeguinte = indicadorVencimentoMesSeguinte;
	}

	public String getCodigoNome(){
		return this.id + " - " + this.nome;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getNome();
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"cpf","cnpj","nome"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"CPF", "CNPJ", "Nome"};
		return labels;		
	}

	public Short getIndicadorPermiteNegativacao() {
		return indicadorPermiteNegativacao;
	}

	public void setIndicadorPermiteNegativacao(Short indicadorPermiteNegativacao) {
		this.indicadorPermiteNegativacao = indicadorPermiteNegativacao;
	}
	
	
	
}