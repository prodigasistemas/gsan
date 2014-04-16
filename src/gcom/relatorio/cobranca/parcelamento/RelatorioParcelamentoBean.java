package gcom.relatorio.cobranca.parcelamento;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * Bean do [UC0227] Gerar Relação de Débitos
 * 
 * @author Rafael Santos
 * @date 25/05/2006
 */
public class RelatorioParcelamentoBean implements RelatorioBean {
	
	private String matriculaImovel;

	private String nomeCliente;

	private String endereco;

	private String cpfCnpj;

	private String telefone;

	private String dataParcelamento;

	private String faturasEmAberto;

	private String servicosACobrar;

	private String atualizacaoMonetaria;

	private String jurosMora;

	private String multa;

	private String guiaPagamento;

	private String parcelamentoACobrar;

	private String totalDebitos;

	private String descontoAcrescimo;

	private String descontosAntiguidade;

	private String descontoInatividade;

	private String creditosARealizar;

	private String totalDescontos;

	private String valorASerNegociado;

	private String valorEntrada;

	private String numeroParcelas;

	private String valorParcela;

	private String valorASerParcelado;

	private String solicitacaoRestabelecimento;

	private String municipioData;

	private String idParcelamento;

	private String unidadeUsuario;

	private String idFuncionario;
	
	private String nomeClienteParcelamento;
	
	private String cpfClienteParcelamento;

	private String pagina;

	private JRBeanCollectionDataSource arrayJRDetalhamento;

	private ArrayList arrayRelatorioParcelamentoDetalhamentoBean;
	
	private String codigoEmpresaFebraban;
	
	private String rgCliente;
	
	private String nomeUsuario;
	
	private String matriculaUsuario;
	
	private String municipio;
	
	private String mesAnoInicioParcelamento;
	
	private String mesAnoFinalParcelamento;
	
	private String valorJuros;
	
	private String valorDescontoSancoesRegulamentares;
    
    private String valorDescontoTarifaSocial;
    
    private String cpfUsuario;
    
    private String nomeDiretorComercial;
    
    private String cpfDiretorComercial;
    
    private String profissao;
    
    private String nomeDevedor;
    
    private String cnpjDevedor;
    
    private String enderecoDevedor;
    
    private String telefoneDevedor;
    
    private Short indicadorPessoaJuridica;
    
	private Integer maiorAnoMesReferenciaDocumentos = new Integer(0);
	private Integer menorAnoMesReferenciaDocumentos = new Integer(0);    
    
    private String nomeUsuarioParcelamento;
    private String bairro;
    private String codigoRota;
    
    //------ CAEMA -------
    private String valorASerNegociadoSemDesconto;
    private String valorTotalDescontosSemValorCreditos;
    private String valorASerNegociadoExtenso;
    private String valorEntradaExtenso;
    private String valorParcelaExtenso;
    private String nomeTestemunha1;
    private String cpfTestemunha1;
    private String nomeTestemunha2;
    private String cpfTestemunha2;
    //--------------------
    
    //---------COSANPA-----------
    private String nomeFuncionario;
    private String cargoFuncionario;
    private String valorAserNegociadoPorExtenso;
    private String valorDeEntradaProExtenso;
    private String dataVencimentoGuiaPagamento;
    private String numeroDeParcelasPorExtenso;
    private String valorDaParcelaPorExtenso;
    private String valorTOTALDasParcelasPorExtenso;
    private String descontoNegociado;
    private String valorDescontoPorExtenso;
    private String setorComercialDescricao;
    private String localidadeDescricao;
    private String dddTelefone;
    private String mesExtenso;
    private String dia;
    private String ano;
    //---------------------------
    

	public String getCnpjDevedor() {
		return cnpjDevedor;
	}

	public void setCnpjDevedor(String cnpjDevedor) {
		this.cnpjDevedor = cnpjDevedor;
	}

	public String getEnderecoDevedor() {
		return enderecoDevedor;
	}

	public void setEnderecoDevedor(String enderecoDevedor) {
		this.enderecoDevedor = enderecoDevedor;
	}

	public String getNomeDevedor() {
		return nomeDevedor;
	}

	public void setNomeDevedor(String nomeDevedor) {
		this.nomeDevedor = nomeDevedor;
	}

	public String getTelefoneDevedor() {
		return telefoneDevedor;
	}

	public void setTelefoneDevedor(String telefoneDevedor) {
		this.telefoneDevedor = telefoneDevedor;
	}

	public String getCpfDiretorComercial() {
		return cpfDiretorComercial;
	}

	public void setCpfDiretorComercial(String cpfDiretorComercial) {
		this.cpfDiretorComercial = cpfDiretorComercial;
	}

	public String getNomeDiretorComercial() {
		return nomeDiretorComercial;
	}

	public void setNomeDiretorComercial(String nomeDiretorComercial) {
		this.nomeDiretorComercial = nomeDiretorComercial;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	/**
	 * Construtor de RelatorioGerarRelacaoDebitosBean
	 */
	public RelatorioParcelamentoBean(String matriculaImovel,
			String nomeCliente,
			String endereco, 
			String cpfCnpj,
			String telefone, 
			String dataParcelamento, 
			String faturasEmAberto,
			String servicosACobrar, 
			String atualizacaoMonetaria,
			String jurosMora, 
			String multa, 
			String guiaPagamento,
			String parcelamentoACobrar, 
			String totalDebitos,
			String descontoAcrescimo, 
			String descontosAntiguidade,
			String descontoInatividade, 
			String creditosARealizar,
			String totalDescontos, 
			String valorASerNegociado,
			String valorEntrada, 
			String numeroParcelas, 
			String valorParcela,
			String valorASerParcelado, 
			String solicitacaoRestabelecimento,
			String municipioData, 
			String idParcelamento, 
			String unidadeUsuario,
			String idFuncionario, 
			String nomeClienteParcelamento,
			String cpfClienteParcelamento, 
			String pagina,
			Collection colecaoRelatorioParcelamentoDetalhamentosBean,
			String codigoEmpresaFebraban,
			String rgCliente,
			String nomeUsuario,
			String matriculaUsuario,
			String municipio,
			String mesAnoInicioParcelamento,
			String mesAnoFinalParcelamento,
			String valorJuros,
			String valorDescontoSancoesRegulamentares,
            String valorDescontoTarifaSocial,
            String cpfUsuario,
            String nomeDiretorComercial,
            String cpfDiretorComercial,
            String profissao,
            String nomeDevedor,    
    		String cnpjDevedor,    
    		String enderecoDevedor,    
    		String telefoneDevedor,
    		Short indicadorPessoaJuridica,
    		Integer maiorAnoMesReferenciaDocumentos,
    		Integer menorAnoMesReferenciaDocumentos,
            String nomeUsuarioParcelamento){
		this.matriculaImovel = matriculaImovel;
		this.nomeCliente = nomeCliente;
		this.endereco = endereco;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
		this.dataParcelamento = dataParcelamento;
		this.faturasEmAberto = faturasEmAberto;
		this.servicosACobrar = servicosACobrar;
		this.atualizacaoMonetaria = atualizacaoMonetaria;
		this.jurosMora = jurosMora;
		this.multa = multa;
		this.guiaPagamento = guiaPagamento;
		this.parcelamentoACobrar = parcelamentoACobrar;
		this.totalDebitos = totalDebitos;
		this.descontoAcrescimo = descontoAcrescimo;
		this.descontosAntiguidade = descontosAntiguidade;
		this.descontoInatividade = descontoInatividade;
		this.creditosARealizar = creditosARealizar;
		this.totalDescontos = totalDescontos;
		this.valorASerNegociado = valorASerNegociado;
		this.valorEntrada = valorEntrada;
		this.numeroParcelas = numeroParcelas;
		this.valorParcela = valorParcela;
		this.valorASerParcelado = valorASerParcelado;
		this.solicitacaoRestabelecimento = solicitacaoRestabelecimento;
		this.municipioData = municipioData;
		this.idParcelamento = idParcelamento;
		this.unidadeUsuario = unidadeUsuario;
		this.idFuncionario = idFuncionario;
		this.nomeClienteParcelamento = nomeClienteParcelamento;
		this.cpfClienteParcelamento = cpfClienteParcelamento;
		this.pagina = pagina;
		this.codigoEmpresaFebraban = codigoEmpresaFebraban;
		this.rgCliente = rgCliente;
		this.nomeUsuario = nomeUsuario;
		this.matriculaUsuario = matriculaUsuario;
		this.municipio = municipio;
		this.mesAnoInicioParcelamento = mesAnoInicioParcelamento;
		this.mesAnoFinalParcelamento = mesAnoFinalParcelamento;
		
		this.arrayRelatorioParcelamentoDetalhamentoBean = new ArrayList();
		this.arrayRelatorioParcelamentoDetalhamentoBean
				.addAll(colecaoRelatorioParcelamentoDetalhamentosBean);
		this.arrayJRDetalhamento = new JRBeanCollectionDataSource(
				this.arrayRelatorioParcelamentoDetalhamentoBean);
	
		this.valorJuros = valorJuros;
	
		this.valorDescontoSancoesRegulamentares = valorDescontoSancoesRegulamentares;
        this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
        this.cpfUsuario = cpfUsuario;
        this.nomeDiretorComercial = nomeDiretorComercial;
        this.cpfDiretorComercial = cpfDiretorComercial;
        this.profissao = profissao;
        this.nomeDevedor = nomeDevedor;        
        this.cnpjDevedor = cnpjDevedor;        
        this.enderecoDevedor = enderecoDevedor;        
        this.telefoneDevedor = telefoneDevedor;    
        this.indicadorPessoaJuridica = indicadorPessoaJuridica;
        if(maiorAnoMesReferenciaDocumentos == null){
        	this.maiorAnoMesReferenciaDocumentos = new Integer(0);
        }else{
        	this.maiorAnoMesReferenciaDocumentos = maiorAnoMesReferenciaDocumentos;
        }
        
        if(menorAnoMesReferenciaDocumentos == null){
        	this.menorAnoMesReferenciaDocumentos = new Integer(0);
        }else{
        	this.menorAnoMesReferenciaDocumentos = menorAnoMesReferenciaDocumentos;
        }
        this.nomeUsuarioParcelamento = nomeUsuarioParcelamento;
	}
	
	
	/**
	 * Construtor de RelatorioGerarRelacaoDebitosBean CAEMA
	 */
	public RelatorioParcelamentoBean(String matriculaImovel,
			String nomeCliente,
			String endereco, 
			String cpfCnpj,
			String telefone, 
			String dataParcelamento, 
			String faturasEmAberto,
			String servicosACobrar, 
			String atualizacaoMonetaria,
			String jurosMora, 
			String multa, 
			String guiaPagamento,
			String parcelamentoACobrar, 
			String totalDebitos,
			String descontoAcrescimo, 
			String descontosAntiguidade,
			String descontoInatividade, 
			String creditosARealizar,
			String totalDescontos, 
			String valorASerNegociado,
			String valorEntrada, 
			String numeroParcelas, 
			String valorParcela,
			String valorASerParcelado, 
			String solicitacaoRestabelecimento,
			String municipioData, 
			String idParcelamento, 
			
//			String unidadeUsuario,
//			String idFuncionario, 
//			String nomeClienteParcelamento,
//			String cpfClienteParcelamento, 
			String pagina,
			Collection colecaoRelatorioParcelamentoDetalhamentosBean,
			String codigoEmpresaFebraban,
//			String rgCliente,
//			String nomeUsuario,
//			String matriculaUsuario,
//			String municipio,
//			String mesAnoInicioParcelamento,
//			String mesAnoFinalParcelamento,
			String valorJuros,
			String valorDescontoSancoesRegulamentares,
            String valorDescontoTarifaSocial,
//          String cpfUsuario,
//          String nomeDiretorComercial,
//          String cpfDiretorComercial,
//          String profissao,
//          String nomeDevedor,    
//    		String cnpjDevedor,    
//    		String enderecoDevedor,    
//    		String telefoneDevedor,
//    		Short indicadorPessoaJuridica,
//    		Integer maiorAnoMesReferenciaDocumentos,
//    		Integer menorAnoMesReferenciaDocumentos,
//          String nomeUsuarioParcelamento,
            String valorASerNegociadoSemDesconto,
			String valorTotalDescontosSemValorCreditos,
			String valorASerNegociadoExtenso,
			String valorEntradaExtenso, 
			String valorParcelaExtenso,
		    String nomeTestemunha1,
     		String cpfTestemunha1,
     		String nomeTestemunha2,
     		String cpfTestemunha2
			){
		
		
		this.matriculaImovel = matriculaImovel;
		this.nomeCliente = nomeCliente;
		this.endereco = endereco;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
		this.dataParcelamento = dataParcelamento;
		this.faturasEmAberto = faturasEmAberto;
		this.servicosACobrar = servicosACobrar;
		this.atualizacaoMonetaria = atualizacaoMonetaria;
		this.jurosMora = jurosMora;
		this.multa = multa;
		this.guiaPagamento = guiaPagamento;
		this.parcelamentoACobrar = parcelamentoACobrar;
		this.totalDebitos = totalDebitos;
		this.descontoAcrescimo = descontoAcrescimo;
		this.descontosAntiguidade = descontosAntiguidade;
		this.descontoInatividade = descontoInatividade;
		this.creditosARealizar = creditosARealizar;
		this.totalDescontos = totalDescontos;
		this.valorASerNegociado = valorASerNegociado;
		this.valorEntrada = valorEntrada;
		this.numeroParcelas = numeroParcelas;
		this.valorParcela = valorParcela;
		this.valorASerParcelado = valorASerParcelado;
		this.solicitacaoRestabelecimento = solicitacaoRestabelecimento;
		this.municipioData = municipioData;
		this.idParcelamento = idParcelamento;
//		this.unidadeUsuario = unidadeUsuario;
//		this.idFuncionario = idFuncionario;
//		this.nomeClienteParcelamento = nomeClienteParcelamento;
//		this.cpfClienteParcelamento = cpfClienteParcelamento;
		this.pagina = pagina;
		this.codigoEmpresaFebraban = codigoEmpresaFebraban;
//		this.rgCliente = rgCliente;
//		this.nomeUsuario = nomeUsuario;
//		this.matriculaUsuario = matriculaUsuario;
//		this.municipio = municipio;
//		this.mesAnoInicioParcelamento = mesAnoInicioParcelamento;
//		this.mesAnoFinalParcelamento = mesAnoFinalParcelamento;
		
		this.arrayRelatorioParcelamentoDetalhamentoBean = new ArrayList();
		this.arrayRelatorioParcelamentoDetalhamentoBean
				.addAll(colecaoRelatorioParcelamentoDetalhamentosBean);
		this.arrayJRDetalhamento = new JRBeanCollectionDataSource(
				this.arrayRelatorioParcelamentoDetalhamentoBean);
	
		this.valorJuros = valorJuros;
	
		this.valorDescontoSancoesRegulamentares = valorDescontoSancoesRegulamentares;
        this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
//        this.cpfUsuario = cpfUsuario;
//        this.nomeDiretorComercial = nomeDiretorComercial;
//        this.cpfDiretorComercial = cpfDiretorComercial;
//        this.profissao = profissao;
//        this.nomeDevedor = nomeDevedor;        
//        this.cnpjDevedor = cnpjDevedor;        
//        this.enderecoDevedor = enderecoDevedor;        
//        this.telefoneDevedor = telefoneDevedor;    
//        this.indicadorPessoaJuridica = indicadorPessoaJuridica;
//        this.maiorAnoMesReferenciaDocumentos = maiorAnoMesReferenciaDocumentos;
//        this.menorAnoMesReferenciaDocumentos = menorAnoMesReferenciaDocumentos;
//        this.nomeUsuarioParcelamento = nomeUsuarioParcelamento;
        this.valorASerNegociadoSemDesconto = valorASerNegociadoSemDesconto;
		this.valorTotalDescontosSemValorCreditos = valorTotalDescontosSemValorCreditos;
		this.valorASerNegociadoExtenso = valorASerNegociadoExtenso;
		this.valorEntradaExtenso = valorEntradaExtenso; 
		this.valorParcelaExtenso = valorParcelaExtenso;
		this.nomeTestemunha1 = nomeTestemunha1;
		this.cpfTestemunha1 = cpfTestemunha1;
		this.nomeTestemunha2 = nomeTestemunha2;
		this.cpfTestemunha2 = cpfTestemunha2;
	}

	/**
	 * Construtor de RelatorioGerarRelacaoDebitosBean CAER novo layout
	 */
	public RelatorioParcelamentoBean(String matriculaImovel,
			String nomeCliente,
			String endereco, 
			String cpfCnpj,
			String telefone, 
			String dataParcelamento, 
			String faturasEmAberto,
			String servicosACobrar, 
			String atualizacaoMonetaria,
			String jurosMora, 
			String multa, 
			String guiaPagamento,
			String parcelamentoACobrar, 
			String totalDebitos,
			String descontoAcrescimo, 
			String descontosAntiguidade,
			String descontoInatividade, 
			String creditosARealizar,
			String totalDescontos, 
			String valorASerNegociado,
			String valorEntrada, 
			String numeroParcelas, 
			String valorParcela,
			String valorASerParcelado, 
			String solicitacaoRestabelecimento,
			String municipioData, 
			String idParcelamento, 
			String unidadeUsuario,
			String idFuncionario, 
			String nomeClienteParcelamento,
			String cpfClienteParcelamento, 
			String pagina,
			Collection colecaoRelatorioParcelamentoDetalhamentosBean,
			String codigoEmpresaFebraban,
			String rgCliente,
			String nomeUsuario,
			String matriculaUsuario,
			String municipio,
			String mesAnoInicioParcelamento,
			String mesAnoFinalParcelamento,
			String valorJuros,
			String valorDescontoSancoesRegulamentares,
            String valorDescontoTarifaSocial,
            String cpfUsuario,
            String nomeDiretorComercial,
            String cpfDiretorComercial,
            String profissao,
            String nomeDevedor,    
    		String cnpjDevedor,    
    		String enderecoDevedor,    
    		String telefoneDevedor,
    		Short indicadorPessoaJuridica,
    		Integer maiorAnoMesReferenciaDocumentos,
    		Integer menorAnoMesReferenciaDocumentos,
            String nomeUsuarioParcelamento,
            String bairro,
            String codigoRota){
		this.matriculaImovel = matriculaImovel;
		this.nomeCliente = nomeCliente;
		this.endereco = endereco;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
		this.dataParcelamento = dataParcelamento;
		this.faturasEmAberto = faturasEmAberto;
		this.servicosACobrar = servicosACobrar;
		this.atualizacaoMonetaria = atualizacaoMonetaria;
		this.jurosMora = jurosMora;
		this.multa = multa;
		this.guiaPagamento = guiaPagamento;
		this.parcelamentoACobrar = parcelamentoACobrar;
		this.totalDebitos = totalDebitos;
		this.descontoAcrescimo = descontoAcrescimo;
		this.descontosAntiguidade = descontosAntiguidade;
		this.descontoInatividade = descontoInatividade;
		this.creditosARealizar = creditosARealizar;
		this.totalDescontos = totalDescontos;
		this.valorASerNegociado = valorASerNegociado;
		this.valorEntrada = valorEntrada;
		this.numeroParcelas = numeroParcelas;
		this.valorParcela = valorParcela;
		this.valorASerParcelado = valorASerParcelado;
		this.solicitacaoRestabelecimento = solicitacaoRestabelecimento;
		this.municipioData = municipioData;
		this.idParcelamento = idParcelamento;
		this.unidadeUsuario = unidadeUsuario;
		this.idFuncionario = idFuncionario;
		this.nomeClienteParcelamento = nomeClienteParcelamento;
		this.cpfClienteParcelamento = cpfClienteParcelamento;
		this.pagina = pagina;
		this.codigoEmpresaFebraban = codigoEmpresaFebraban;
		this.rgCliente = rgCliente;
		this.nomeUsuario = nomeUsuario;
		this.matriculaUsuario = matriculaUsuario;
		this.municipio = municipio;
		this.mesAnoInicioParcelamento = mesAnoInicioParcelamento;
		this.mesAnoFinalParcelamento = mesAnoFinalParcelamento;
		
		this.arrayRelatorioParcelamentoDetalhamentoBean = new ArrayList();
		this.arrayRelatorioParcelamentoDetalhamentoBean
				.addAll(colecaoRelatorioParcelamentoDetalhamentosBean);
		this.arrayJRDetalhamento = new JRBeanCollectionDataSource(
				this.arrayRelatorioParcelamentoDetalhamentoBean);
	
		this.valorJuros = valorJuros;
	
		this.valorDescontoSancoesRegulamentares = valorDescontoSancoesRegulamentares;
        this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
        this.cpfUsuario = cpfUsuario;
        this.nomeDiretorComercial = nomeDiretorComercial;
        this.cpfDiretorComercial = cpfDiretorComercial;
        this.profissao = profissao;
        this.nomeDevedor = nomeDevedor;        
        this.cnpjDevedor = cnpjDevedor;        
        this.enderecoDevedor = enderecoDevedor;        
        this.telefoneDevedor = telefoneDevedor;    
        this.indicadorPessoaJuridica = indicadorPessoaJuridica;
        if (maiorAnoMesReferenciaDocumentos == null) {
			this.maiorAnoMesReferenciaDocumentos = new Integer(0);
		} else {
			this.maiorAnoMesReferenciaDocumentos = maiorAnoMesReferenciaDocumentos;
		}

		if (menorAnoMesReferenciaDocumentos == null) {
			this.menorAnoMesReferenciaDocumentos = new Integer(0);
		} else {
			this.menorAnoMesReferenciaDocumentos = menorAnoMesReferenciaDocumentos;
		}
        this.nomeUsuarioParcelamento = nomeUsuarioParcelamento;
        this.bairro = bairro;
        this.codigoRota = codigoRota;
	}	
	
	/**
	 * Construtor de RelatorioGerarRelacaoDebitosBean COSANPA
	 */
	public RelatorioParcelamentoBean(String matriculaImovel,
			String nomeCliente,
			String endereco, 
			String cpfCnpj,
			String telefone, 
			String dataParcelamento, 
			String faturasEmAberto,
			String servicosACobrar, 
			String atualizacaoMonetaria,
			String jurosMora, 
			String multa, 
			String guiaPagamento,
			String parcelamentoACobrar, 
			String totalDebitos,
			String descontoAcrescimo, 
			String descontosAntiguidade,
			String descontoInatividade, 
			String creditosARealizar,
			String totalDescontos, 
			String valorASerNegociado,
			String valorEntrada, 
			String numeroParcelas, 
			String valorParcela,
			String valorASerParcelado, 
			String solicitacaoRestabelecimento,
			String municipioData, 
			String idParcelamento, 
			String unidadeUsuario,
			String idFuncionario, 
			String nomeClienteParcelamento,
			String cpfClienteParcelamento, 
			String pagina,
			Collection colecaoRelatorioParcelamentoDetalhamentosBean,
			String codigoEmpresaFebraban,
			String rgCliente,
			String nomeUsuario,
			String matriculaUsuario,
			String municipio,
			String mesAnoInicioParcelamento,
			String mesAnoFinalParcelamento,
			String valorJuros,
			String valorDescontoSancoesRegulamentares,
            String valorDescontoTarifaSocial,
            String cpfUsuario,
            String nomeDiretorComercial,
            String cpfDiretorComercial,
            String profissao,
            String nomeDevedor,    
    		String cnpjDevedor,    
    		String enderecoDevedor,    
    		String telefoneDevedor,
    		Short indicadorPessoaJuridica,
            String nomeUsuarioParcelamento,
            String nomeFuncionario,
            String cargoFuncionario,
            Integer maiorAnoMesReferenciaDocumentos,
    		Integer menorAnoMesReferenciaDocumentos,
    		String valorAserNegociadoPorExtenso,
    		String valorDeEntradaProExtenso,
    		String dataVencimentoGuiaPagamento,
    		String numeroDeParcelasPorExtenso,
    		String valorDaParcelaPorExtenso,
    		String valorTOTALDasParcelasPorExtenso,
    		String descontoNegociado,
    		String valorDescontoPorExtenso,
    		String codigoRota,
    		String nomeTestemunha1,
    		String cpfTestemunha1,
    		String nomeTestemunha2,
    		String cpfTestemunha2,
    		String localidadeDescricao,
    		String setorComercialDescricao,
    		String dddTelefone,
    		String mesExtenso,
    		String dia,
    		String ano){
		this.matriculaImovel = matriculaImovel;
		this.nomeCliente = nomeCliente;
		this.endereco = endereco;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
		this.dataParcelamento = dataParcelamento;
		this.faturasEmAberto = faturasEmAberto;
		this.servicosACobrar = servicosACobrar;
		this.atualizacaoMonetaria = atualizacaoMonetaria;
		this.jurosMora = jurosMora;
		this.multa = multa;
		this.guiaPagamento = guiaPagamento;
		this.parcelamentoACobrar = parcelamentoACobrar;
		this.totalDebitos = totalDebitos;
		this.descontoAcrescimo = descontoAcrescimo;
		this.descontosAntiguidade = descontosAntiguidade;
		this.descontoInatividade = descontoInatividade;
		this.creditosARealizar = creditosARealizar;
		this.totalDescontos = totalDescontos;
		this.valorASerNegociado = valorASerNegociado;
		this.valorEntrada = valorEntrada;
		this.numeroParcelas = numeroParcelas;
		this.valorParcela = valorParcela;
		this.valorASerParcelado = valorASerParcelado;
		this.solicitacaoRestabelecimento = solicitacaoRestabelecimento;
		this.municipioData = municipioData;
		this.idParcelamento = idParcelamento;
		this.unidadeUsuario = unidadeUsuario;
		this.idFuncionario = idFuncionario;
		this.nomeClienteParcelamento = nomeClienteParcelamento;
		this.cpfClienteParcelamento = cpfClienteParcelamento;
		this.pagina = pagina;
		this.codigoEmpresaFebraban = codigoEmpresaFebraban;
		this.rgCliente = rgCliente;
		this.nomeUsuario = nomeUsuario;
		this.matriculaUsuario = matriculaUsuario;
		this.municipio = municipio;
		this.mesAnoInicioParcelamento = mesAnoInicioParcelamento;
		this.mesAnoFinalParcelamento = mesAnoFinalParcelamento;
		this.arrayRelatorioParcelamentoDetalhamentoBean = new ArrayList();
		this.arrayRelatorioParcelamentoDetalhamentoBean
				.addAll(colecaoRelatorioParcelamentoDetalhamentosBean);
		this.arrayJRDetalhamento = new JRBeanCollectionDataSource(
				this.arrayRelatorioParcelamentoDetalhamentoBean);
		this.valorJuros = valorJuros;
		this.valorDescontoSancoesRegulamentares = valorDescontoSancoesRegulamentares;
        this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
        this.cpfUsuario = cpfUsuario;
        this.nomeDiretorComercial = nomeDiretorComercial;
        this.cpfDiretorComercial = cpfDiretorComercial;
        this.profissao = profissao;
        this.nomeDevedor = nomeDevedor;        
        this.cnpjDevedor = cnpjDevedor;        
        this.enderecoDevedor = enderecoDevedor;        
        this.telefoneDevedor = telefoneDevedor;    
        this.indicadorPessoaJuridica = indicadorPessoaJuridica;
        this.nomeUsuarioParcelamento = nomeUsuarioParcelamento;
        this.nomeFuncionario = nomeFuncionario;
        this.cargoFuncionario = cargoFuncionario;
        if (maiorAnoMesReferenciaDocumentos == null) {
			this.maiorAnoMesReferenciaDocumentos = new Integer(0);
		} else {
			this.maiorAnoMesReferenciaDocumentos = maiorAnoMesReferenciaDocumentos;
		}

		if (menorAnoMesReferenciaDocumentos == null) {
			this.menorAnoMesReferenciaDocumentos = new Integer(0);
		} else {
			this.menorAnoMesReferenciaDocumentos = menorAnoMesReferenciaDocumentos;
		}

        this.valorAserNegociadoPorExtenso = valorAserNegociadoPorExtenso;
        this.valorDeEntradaProExtenso = valorDeEntradaProExtenso;
        this.dataVencimentoGuiaPagamento = dataVencimentoGuiaPagamento;
        this.numeroDeParcelasPorExtenso = numeroDeParcelasPorExtenso;
        this.valorDaParcelaPorExtenso = valorDaParcelaPorExtenso;
        this.valorTOTALDasParcelasPorExtenso = valorTOTALDasParcelasPorExtenso;
        this.descontoNegociado = descontoNegociado;
        this.valorDescontoPorExtenso = valorDescontoPorExtenso;
        this.codigoRota = codigoRota;
        this.nomeTestemunha1 = nomeTestemunha1;
        this.nomeTestemunha2 = nomeTestemunha2;
        this.cpfTestemunha1 = cpfTestemunha1;
        this.cpfTestemunha2 = cpfTestemunha2;
        this.localidadeDescricao = localidadeDescricao;
        this.setorComercialDescricao = setorComercialDescricao;
        this.dddTelefone = dddTelefone;
        this.mesExtenso = mesExtenso;
        this.dia = dia;
        this.ano = ano;
	}
	
	
	/**
	 * @return Returns the cargoFuncionario.
	 */
	public String getCargoFuncionario() {
		return cargoFuncionario;
	}

	/**
	 * @param cargoFuncionario The cargoFuncionario to set.
	 */
	public void setCargoFuncionario(String cargoFuncionario) {
		this.cargoFuncionario = cargoFuncionario;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

	public JRBeanCollectionDataSource getArrayJRDetalhamento() {
		return arrayJRDetalhamento;
	}

	public void setArrayJRDetalhamento(
			JRBeanCollectionDataSource arrayJRDetalhamento) {
		this.arrayJRDetalhamento = arrayJRDetalhamento;
	}

	public ArrayList getArrayRelatorioParcelamentoDetalhamentoBean() {
		return arrayRelatorioParcelamentoDetalhamentoBean;
	}

	public void setArrayRelatorioEfetuarAnaliseParcelamentoDetalhamentoBean(
			ArrayList arrayRelatorioParcelamentoDetalhamentoBean) {
		this.arrayRelatorioParcelamentoDetalhamentoBean = arrayRelatorioParcelamentoDetalhamentoBean;
	}

	public String getAtualizacaoMonetaria() {
		return atualizacaoMonetaria;
	}

	public void setAtualizacaoMonetaria(String atualizacaoMonetaria) {
		this.atualizacaoMonetaria = atualizacaoMonetaria;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getCreditosARealizar() {
		return creditosARealizar;
	}

	public void setCreditosARealizar(String creditosARealizar) {
		this.creditosARealizar = creditosARealizar;
	}

	public String getDataParcelamento() {
		return dataParcelamento;
	}

	public void setDataParcelamento(String dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}

	public String getDescontoAcrescimo() {
		return descontoAcrescimo;
	}

	public void setDescontoAcrescimo(String descontoAcrescimos) {
		this.descontoAcrescimo = descontoAcrescimos;
	}

	public String getDescontoInatividade() {
		return descontoInatividade;
	}

	public void setDescontoInatividade(String descontoInatividade) {
		this.descontoInatividade = descontoInatividade;
	}

	public String getDescontosAntiguidade() {
		return descontosAntiguidade;
	}

	public void setDescontosAntiguidade(String descontosAntiguidade) {
		this.descontosAntiguidade = descontosAntiguidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getFaturasEmAberto() {
		return faturasEmAberto;
	}

	public void setFaturasEmAberto(String faturasEmAberto) {
		this.faturasEmAberto = faturasEmAberto;
	}

	public String getGuiaPagamento() {
		return guiaPagamento;
	}

	public void setGuiaPagamento(String guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(String idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	public String getJurosMora() {
		return jurosMora;
	}

	public void setJurosMora(String jurosMora) {
		this.jurosMora = jurosMora;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getMulta() {
		return multa;
	}

	public void setMulta(String multa) {
		this.multa = multa;
	}

	public String getMunicipioData() {
		return municipioData;
	}

	public void setMunicipioData(String municipioData) {
		this.municipioData = municipioData;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(String numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getParcelamentoACobrar() {
		return parcelamentoACobrar;
	}

	public void setParcelamentoACobrar(String parcelamentoACobrar) {
		this.parcelamentoACobrar = parcelamentoACobrar;
	}

	public String getServicosACobrar() {
		return servicosACobrar;
	}

	public void setServicosACobrar(String servicosACobrar) {
		this.servicosACobrar = servicosACobrar;
	}

	public String getSolicitacaoRestabelecimento() {
		return solicitacaoRestabelecimento;
	}

	public void setSolicitacaoRestabelecimento(
			String solicitacaoRestabelecimento) {
		this.solicitacaoRestabelecimento = solicitacaoRestabelecimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTotalDebitos() {
		return totalDebitos;
	}

	public void setTotalDebitos(String totalDebitos) {
		this.totalDebitos = totalDebitos;
	}

	public String getTotalDescontos() {
		return totalDescontos;
	}

	public void setTotalDescontos(String totalDescontos) {
		this.totalDescontos = totalDescontos;
	}

	public String getUnidadeUsuario() {
		return unidadeUsuario;
	}

	public void setUnidadeUsuario(String unidadeUsuario) {
		this.unidadeUsuario = unidadeUsuario;
	}

	public String getValorASerNegociado() {
		return valorASerNegociado;
	}

	public void setValorASerNegociado(String valorASerNegociado) {
		this.valorASerNegociado = valorASerNegociado;
	}

	public String getValorASerParcelado() {
		return valorASerParcelado;
	}

	public void setValorASerParcelado(String valorASerParcelado) {
		this.valorASerParcelado = valorASerParcelado;
	}

	public String getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(String valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public String getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(String valorParcela) {
		this.valorParcela = valorParcela;
	}

	/**
	 * @return Retorna o campo cpfClienteParcelamento.
	 */
	public String getCpfClienteParcelamento() {
		return cpfClienteParcelamento;
	}

	/**
	 * @param cpfClienteParcelamento O cpfClienteParcelamento a ser setado.
	 */
	public void setCpfClienteParcelamento(String cpfClienteParcelamento) {
		this.cpfClienteParcelamento = cpfClienteParcelamento;
	}

	/**
	 * @return Retorna o campo nomeClienteParcelamento.
	 */
	public String getNomeClienteParcelamento() {
		return nomeClienteParcelamento;
	}

	/**
	 * @param nomeClienteParcelamento O nomeClienteParcelamento a ser setado.
	 */
	public void setNomeClienteParcelamento(String nomeClienteParcelamento) {
		this.nomeClienteParcelamento = nomeClienteParcelamento;
	}

	/**
	 * @param arrayRelatorioParcelamentoDetalhamentoBean O arrayRelatorioParcelamentoDetalhamentoBean a ser setado.
	 */
	public void setArrayRelatorioParcelamentoDetalhamentoBean(
			ArrayList arrayRelatorioParcelamentoDetalhamentoBean) {
		this.arrayRelatorioParcelamentoDetalhamentoBean = arrayRelatorioParcelamentoDetalhamentoBean;
	}

	public String getCodigoEmpresaFebraban() {
		return codigoEmpresaFebraban;
	}

	public void setCodigoEmpresaFebraban(String codigoEmpresaFebraban) {
		this.codigoEmpresaFebraban = codigoEmpresaFebraban;
	}

	public String getRgCliente() {
		return rgCliente;
	}

	public void setRgCliente(String rgCliente) {
		this.rgCliente = rgCliente;
	}

	public String getMatriculaUsuario() {
		return matriculaUsuario;
	}

	public void setMatriculaUsuario(String matriculaUsuario) {
		this.matriculaUsuario = matriculaUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getMesAnoFinalParcelamento() {
		return mesAnoFinalParcelamento;
	}

	public void setMesAnoFinalParcelamento(String mesAnoFinalParcelamento) {
		this.mesAnoFinalParcelamento = mesAnoFinalParcelamento;
	}

	public String getMesAnoInicioParcelamento() {
		return mesAnoInicioParcelamento;
	}

	public void setMesAnoInicioParcelamento(String mesAnoInicioParcelamento) {
		this.mesAnoInicioParcelamento = mesAnoInicioParcelamento;
	}

	public String getValorJuros() {
		return valorJuros;
	}

	public void setValorJuros(String valorJuros) {
		this.valorJuros = valorJuros;
	}

	public String getValorDescontoSancoesRegulamentares() {
		return valorDescontoSancoesRegulamentares;
	}

	public void setValorDescontoSancoesRegulamentares(
			String valorDescontoSancoesRegulamentares) {
		this.valorDescontoSancoesRegulamentares = valorDescontoSancoesRegulamentares;
	}

    public String getValorDescontoTarifaSocial() {
        return valorDescontoTarifaSocial;
    }

    public void setValorDescontoTarifaSocial(String valorDescontoTarifaSocial) {
        this.valorDescontoTarifaSocial = valorDescontoTarifaSocial;
    }

    public String getParagrafoDevedor(){
    	
    	String retorno = "";
    	
    	if ( indicadorPessoaJuridica == 1 ){
    		if ( this.getNomeCliente() != null && !this.getNomeCliente().equals( "" ) ){
    			retorno = "          <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DEVEDOR(a): " + 
    				this.getNomeCliente() + "</style>";
    		}
    		
    		if ( this.getProfissao() != null  && !this.getProfissao().equals( "" )){
    			retorno += "<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">, " + this.getProfissao() + "</style>";
    		}
    		
    		if ( this.getRgCliente() != null  && !this.getRgCliente().equals( "" )){
    			retorno += " Carteira de Identidade n°. <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getRgCliente() + "</style>,";

    		}
    		
    		if ( this.getCpfClienteParcelamento() != null  && !this.getCpfClienteParcelamento().equals( "" )){
    			retorno += " C.P.F. n°. <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getCpfClienteParcelamento() + "</style>";
    		}
    		
    		if ( this.getEndereco() != null  && !this.getEndereco().equals( "" )){
    			retorno += ", capaz, residente e domiciliado na " +
    					   "<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getEndereco() + "</style>,";    			
    		}
    		
    		if ( this.getMunicipio() != null && !this.getMunicipio().equals( "" ) ){
    			retorno += " Município de <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getMunicipio() +"</style>";
    		}
    		
    		if ( this.getTelefone() != null && !this.getTelefone().equals( "" ) ){
    			retorno += ", telefone: <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getTelefone() + "</style>";    			
    		}
    		
    		retorno += ", Estado de Roraima.";
    		
    		return retorno;
    		
    	} else {
    		
    		retorno = "          <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">DEVEDOR: " + this.getNomeDevedor() + ",</style>" +
    			"Pessoa Jurídica de Direito Privado, inscrita no CNPJ/MF so o N°.<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" +
    			this.getCnpjDevedor() + "</style>";
    		
    		if ( this.getMunicipio() != null ){
    			retorno += " com sede na cidade de <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getMunicipio() +
        		" - RR</style>";
    		}
    		
    		if ( this.getEndereco() != null ){
    			retorno += " na <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getEnderecoDevedor() + "</style>"; 
    		}
    		
    		if ( this.getNomeDevedor() != null ){
	    		retorno +=  
	    			" representada neste ato por seu legítimo procurador ou representante legal, <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getNomeCliente() + "</style>";
    		}    		
    		
    		if ( this.getProfissao() != null  && !this.getProfissao().equals( "" )){
    			retorno += "<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">, " + this.getProfissao() + "</style>";
    		}
    		
    		if ( this.getEndereco() != null ){
    			retorno +=
    				" ,capaz, residente e domiciliado na <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getEndereco() + "</style>";
    		}
    		    		
    		if ( this.getRgCliente() != null  && !this.getRgCliente().equals( "" )){
    			retorno += ", portador Carteira de Identidade n°. <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getRgCliente() + "</style>";

    		}
    		
    		if ( this.getCpfClienteParcelamento() != null  && !this.getCpfClienteParcelamento().equals( "" )){
    			retorno += ", C.P.F. n°. <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getCpfClienteParcelamento() + "</style>";
    		}

    		if ( this.getTelefone() != null && !this.getTelefone().equals( "" ) ){
    			retorno += ", telefone: <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getTelefone() + "</style>";    			
    		}
    		
    		retorno += "."; 
    		
    		return retorno;
    		
//    		if ( this.getEndereco() != null )
//    		return "           +
//    		"Pessoa Jurídica de Direito Privado, inscrita no CNPJ/MF so o N°.<style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" +
//    		this.getCpfCnpj() + "</style> com sede na cidade de <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getMunicipio() +
//    		" - RR</style> na <style isBold=\"true\" pdfFontName=\"Helvetica-Bold\">" + this.getEndereco() + "</style>";  		
    	}
    }

	public Short getIndicadorPessoaJuridica() {
		return indicadorPessoaJuridica;
	}

	public void setIndicadorPessoaJuridica(Short indicadorPessoaJuridica) {
		this.indicadorPessoaJuridica = indicadorPessoaJuridica;
	}

	public String getMaiorAnoMesReferenciaDocumentos() {
		return Util.formatarAnoMesParaMesAno( maiorAnoMesReferenciaDocumentos );
	}

	public void setMaiorAnoMesReferenciaDocumentos(
			Integer maiorAnoMesReferenciaDocumentos) {
		this.maiorAnoMesReferenciaDocumentos = maiorAnoMesReferenciaDocumentos;
	}

	public String getValorASerNegociadoSemDesconto() {
		return valorASerNegociadoSemDesconto;
	}

	public void setValorASerNegociadoSemDesconto(
			String valorASerNegociadoSemDesconto) {
		this.valorASerNegociadoSemDesconto = valorASerNegociadoSemDesconto;
	}

	public String getValorTotalDescontosSemValorCreditos() {
		return valorTotalDescontosSemValorCreditos;
	}

	public void setValorTotalDescontosSemValorCreditos(
			String valorTotalDescontosSemValorCreditos) {
		this.valorTotalDescontosSemValorCreditos = valorTotalDescontosSemValorCreditos;
	}

	public String getMenorAnoMesReferenciaDocumentos() {
		return Util.formatarAnoMesParaMesAno( menorAnoMesReferenciaDocumentos );
	}

	public void setMenorAnoMesReferenciaDocumentos(
			Integer menorAnoMesReferenciaDocumentos) {
		this.menorAnoMesReferenciaDocumentos = menorAnoMesReferenciaDocumentos;
	}

    public String getNomeUsuarioParcelamento() {
        return nomeUsuarioParcelamento;
    }

    public void setNomeUsuarioParcelamento(String nomeUsuarioParcelamento) {
        this.nomeUsuarioParcelamento = nomeUsuarioParcelamento;
    }

	public String getValorASerNegociadoExtenso() {
		return valorASerNegociadoExtenso;
	}

	public void setValorASerNegociadoExtenso(String valorASerNegociadoExtenso) {
		this.valorASerNegociadoExtenso = valorASerNegociadoExtenso;
	}

	public String getValorEntradaExtenso() {
		return valorEntradaExtenso;
	}

	public void setValorEntradaExtenso(String valorEntradaExtenso) {
		this.valorEntradaExtenso = valorEntradaExtenso;
	}

	public String getValorParcelaExtenso() {
		return valorParcelaExtenso;
	}

	public void setValorParcelaExtenso(String valorParcelaExtenso) {
		this.valorParcelaExtenso = valorParcelaExtenso;
	}

	

	public String getCpfTestemunha1() {
		return cpfTestemunha1;
	}

	public void setCpfTestemunha1(String cpfTestemunha1) {
		this.cpfTestemunha1 = cpfTestemunha1;
	}

	public String getCpfTestemunha2() {
		return cpfTestemunha2;
	}

	public void setCpfTestemunha2(String cpfTestemunha2) {
		this.cpfTestemunha2 = cpfTestemunha2;
	}

	public String getNomeTestemunha1() {
		return nomeTestemunha1;
	}

	public void setNomeTestemunha1(String nomeTestemunha1) {
		this.nomeTestemunha1 = nomeTestemunha1;
	}

	public String getNomeTestemunha2() {
		return nomeTestemunha2;
	}

	public void setNomeTestemunha2(String nomeTestemunha2) {
		this.nomeTestemunha2 = nomeTestemunha2;
	}
	
	/**
	 * @return Returns the nomeFuncionario.
	 */
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	/**
	 * @param nomeFuncionario The nomeFuncionario to set.
	 */
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	/**
	 * @return Returns the valorAserNegociadoPorExtenso.
	 */
	public String getValorAserNegociadoPorExtenso() {
		return valorAserNegociadoPorExtenso;
	}

	/**
	 * @param valorAserNegociadoPorExtenso The valorAserNegociadoPorExtenso to set.
	 */
	public void setValorAserNegociadoPorExtenso(String valorAserNegociadoPorExtenso) {
		this.valorAserNegociadoPorExtenso = valorAserNegociadoPorExtenso;
	}

	/**
	 * @return Returns the valorDeEntradaProExtenso.
	 */
	public String getValorDeEntradaProExtenso() {
		return valorDeEntradaProExtenso;
	}

	/**
	 * @param valorDeEntradaProExtenso The valorDeEntradaProExtenso to set.
	 */
	public void setValorDeEntradaProExtenso(String valorDeEntradaProExtenso) {
		this.valorDeEntradaProExtenso = valorDeEntradaProExtenso;
	}

	/**
	 * @return Returns the dataVencimentoGuiaPagamento.
	 */
	public String getDataVencimentoGuiaPagamento() {
		return dataVencimentoGuiaPagamento;
	}

	/**
	 * @param dataVencimentoGuiaPagamento The dataVencimentoGuiaPagamento to set.
	 */
	public void setDataVencimentoGuiaPagamento(String dataVencimentoGuiaPagamento) {
		this.dataVencimentoGuiaPagamento = dataVencimentoGuiaPagamento;
	}

	/**
	 * @return Returns the numeroDeParcelasPorExtenso.
	 */
	public String getNumeroDeParcelasPorExtenso() {
		return numeroDeParcelasPorExtenso;
	}

	/**
	 * @param numeroDeParcelasPorExtenso The numeroDeParcelasPorExtenso to set.
	 */
	public void setNumeroDeParcelasPorExtenso(String numeroDeParcelasPorExtenso) {
		this.numeroDeParcelasPorExtenso = numeroDeParcelasPorExtenso;
	}

	/**
	 * @return Returns the valorDaParcelaPorExtenso.
	 */
	public String getValorDaParcelaPorExtenso() {
		return valorDaParcelaPorExtenso;
	}

	/**
	 * @param valorDaParcelaPorExtenso The valorDaParcelaPorExtenso to set.
	 */
	public void setValorDaParcelaPorExtenso(String valorDaParcelaPorExtenso) {
		this.valorDaParcelaPorExtenso = valorDaParcelaPorExtenso;
	}

	/**
	 * @return Returns the valorTOTALDasParcelasPorExtenso.
	 */
	public String getValorTOTALDasParcelasPorExtenso() {
		return valorTOTALDasParcelasPorExtenso;
	}

	/**
	 * @param valorTOTALDasParcelasPorExtenso The valorTOTALDasParcelasPorExtenso to set.
	 */
	public void setValorTOTALDasParcelasPorExtenso(
			String valorTOTALDasParcelasPorExtenso) {
		this.valorTOTALDasParcelasPorExtenso = valorTOTALDasParcelasPorExtenso;
	}

	/**
	 * @return Returns the descontoNegociado.
	 */
	public String getDescontoNegociado() {
		return descontoNegociado;
	}

	/**
	 * @param descontoNegociado The descontoNegociado to set.
	 */
	public void setDescontoNegociado(String descontoNegociado) {
		this.descontoNegociado = descontoNegociado;
	}

	/**
	 * @return Returns the valorDescontoPorExtenso.
	 */
	public String getValorDescontoPorExtenso() {
		return valorDescontoPorExtenso;
	}

	/**
	 * @param valorDescontoPorExtenso The valorDescontoPorExtenso to set.
	 */
	public void setValorDescontoPorExtenso(String valorDescontoPorExtenso) {
		this.valorDescontoPorExtenso = valorDescontoPorExtenso;
	}

	/**
	 * @return Returns the localidadeDescricao.
	 */
	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}

	/**
	 * @param localidadeDescricao The localidadeDescricao to set.
	 */
	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}

	/**
	 * @return Returns the setorComercialDescricao.
	 */
	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	/**
	 * @param setorComercialDescricao The setorComercialDescricao to set.
	 */
	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}

	/**
	 * @return Returns the dddTelefone.
	 */
	public String getDddTelefone() {
		return dddTelefone;
	}

	/**
	 * @param dddTelefone The dddTelefone to set.
	 */
	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	/**
	 * @return Returns the mesExtenso.
	 */
	public String getMesExtenso() {
		return mesExtenso;
	}

	/**
	 * @param mesExtenso The mesExtenso to set.
	 */
	public void setMesExtenso(String mesExtenso) {
		this.mesExtenso = mesExtenso;
	}

	/**
	 * @return Returns the ano.
	 */
	public String getAno() {
		return ano;
	}

	/**
	 * @param ano The ano to set.
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/**
	 * @return Returns the dia.
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * @param dia The dia to set.
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}


	
	
    
}
