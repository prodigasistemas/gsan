package gcom.cobranca.controladores;

import gcom.arrecadacao.IRepositorioArrecadacao;
import gcom.arrecadacao.RepositorioArrecadacaoHBM;
import gcom.batch.Processo;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.IRepositorioImovel;
import gcom.cadastro.imovel.RepositorioImovelHBM;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.IRepositorioCobranca;
import gcom.cobranca.RepositorioCobrancaHBM;
import gcom.cobranca.cobrancaporresultado.ArquivoTextoNegociacaoCobrancaEmpresaBuilder;
import gcom.cobranca.cobrancaporresultado.ArquivoTextoNegociacaoCobrancaEmpresaHelper;
import gcom.cobranca.cobrancaporresultado.NegociacaoCobrancaEmpresa;
import gcom.cobranca.cobrancaporresultado.NegociacaoContaCobrancaEmpresa;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.conta.ContaGeral;
import gcom.micromedicao.IRepositorioMicromedicao;
import gcom.micromedicao.RepositorioMicromedicaoHBM;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.IoUtil;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.email.ServicosEmail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;

public class ControladorCobrancaPorResultado extends ControladorComum {

	private static final long serialVersionUID = 4498794060506412760L;

	//private static Logger logger = Logger.getLogger(ControladorCobrancaPorResultado.class);

	protected IRepositorioFaturamento repositorioFaturamento;
	protected IRepositorioMicromedicao repositorioMicromedicao;
	protected IRepositorioArrecadacao repositorioArrecadacao;
	protected IRepositorioImovel repositorioImovel;
	protected IRepositorioCobranca repositorioCobranca;
	
	public void ejbCreate() throws CreateException {
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
		repositorioMicromedicao = RepositorioMicromedicaoHBM.getInstancia();
		repositorioArrecadacao = RepositorioArrecadacaoHBM.getInstancia();
		repositorioImovel = RepositorioImovelHBM.getInstancia();
		repositorioCobranca = RepositorioCobrancaHBM.getInstancia();
	}

	public void gerarNegociacoesCobrancaEmpresa(int idFuncionalidadeIniciada, Integer idEmpresa) throws ControladorException {
		int idUnidadeIniciada = 0;
		
		try {
			List<Integer> negociacoes = new ArrayList<Integer>();
			
			idUnidadeIniciada = getControladorBatch().iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
					UnidadeProcessamento.EMPRESA, idEmpresa);
			
			gerarNegociacoesParcelamentos(idEmpresa, negociacoes);
			gerarNegociacoesExtrato(idEmpresa, negociacoes);
			gerarNegociacoesGuias(idEmpresa, negociacoes);
			
			gerarArquivoTextoNegociacoesCobrancaEmpresa(idEmpresa,negociacoes);
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
			
		} catch (Exception e) {
			e.printStackTrace();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(e, idUnidadeIniciada, true);
		}
		
	}

	private void gerarNegociacoesParcelamentos(Integer idEmpresa, List<Integer> negociacoes) throws ErroRepositorioException, ControladorException {
		List<Parcelamento> parcelamentos = repositorioCobranca.obterParcelamentosCobrancaEmpresa(idEmpresa);
		
		for (Parcelamento parcelamento : parcelamentos ) {
			NegociacaoCobrancaEmpresa negociacao = new NegociacaoCobrancaEmpresa(parcelamento, new Empresa(idEmpresa), new Date());
			
			Integer idNegociacao = (Integer) getControladorUtil().inserir(negociacao);
			negociacao.setId(idNegociacao);
			negociacoes.add(idNegociacao);
			
			List<ContaGeral> contas = repositorioCobranca.obterContasParcelamentosCobrancaEmpresa(parcelamento.getId(), idEmpresa);
			gerarNegociacoesContas(contas, negociacao);
		}
	}
	
	private void gerarNegociacoesExtrato(Integer idEmpresa, List<Integer> negociacoes) throws ErroRepositorioException, ControladorException {
		List<CobrancaDocumento> documentos = repositorioCobranca.obterExtratosCobrancaEmpresa(idEmpresa);
		
		for (CobrancaDocumento documento : documentos ) {
			NegociacaoCobrancaEmpresa negociacao = new NegociacaoCobrancaEmpresa(documento, new Empresa(idEmpresa), new Date());
			
			Integer idNegociacao = (Integer) getControladorUtil().inserir(negociacao);
			negociacao.setId(idNegociacao);
			negociacoes.add(idNegociacao);
			
			List<ContaGeral> contas = repositorioCobranca.obterContasExtratosCobrancaEmpresa(documento.getId(), idEmpresa);
			gerarNegociacoesContas(contas, negociacao);
		}
	}
	
	private void gerarNegociacoesGuias(Integer idEmpresa, List<Integer> negociacoes) throws ErroRepositorioException, ControladorException {
		List<GuiaPagamentoGeral> guias = repositorioCobranca.obterGuiasCobrancaEmpresa(idEmpresa);
		
		for (GuiaPagamentoGeral guia : guias ) {
			NegociacaoCobrancaEmpresa negociacao = new NegociacaoCobrancaEmpresa(guia, new Empresa(idEmpresa), new Date());
			
			Integer idNegociacao = (Integer) getControladorUtil().inserir(negociacao);
			negociacao.setId(idNegociacao);
			negociacoes.add(idNegociacao);
			
			List<ContaGeral> contas = repositorioCobranca.obterContasGuiaCobrancaEmpresa(guia.getId(), idEmpresa);
			gerarNegociacoesContas(contas, negociacao);
		}
	}
	
	private void gerarNegociacoesContas(List<ContaGeral> contas, NegociacaoCobrancaEmpresa negociacao) {
		try {
			for (ContaGeral contaGeral : contas) {
				NegociacaoContaCobrancaEmpresa contaCobranca = new NegociacaoContaCobrancaEmpresa(negociacao, contaGeral, new Date());
					getControladorUtil().inserir(contaCobranca);
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}
	}
	
	public void gerarArquivoTextoNegociacoesCobrancaEmpresa(Integer idEmpresa, List<Integer> idNegociacoes) throws ControladorException {

		if (!idNegociacoes.isEmpty()) {
			
			try {
				List<NegociacaoCobrancaEmpresa> negociacoes = obterNegociacoesEmpresa(idNegociacoes);
				
				StringBuilder arquivoTxt = new StringBuilder();
				
				for (NegociacaoCobrancaEmpresa negociacao : negociacoes) {
					
					ArquivoTextoNegociacaoCobrancaEmpresaHelper helper = new ArquivoTextoNegociacaoCobrancaEmpresaBuilder(negociacao).buildHelper();
					
					this.montarArquivoTextoNegociacoes(arquivoTxt, helper);
					
					helper = null;
				}
				
				enviarEmailArquivoContasCobrancaEmpresa(idEmpresa, arquivoTxt);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	private List<NegociacaoCobrancaEmpresa> obterNegociacoesEmpresa(List<Integer> idNegociacoes) throws ControladorException {
		List<NegociacaoCobrancaEmpresa> retorno = new ArrayList<NegociacaoCobrancaEmpresa>();
		
		try {
			retorno = repositorioCobranca.obterNegociacoesEmpresa(idNegociacoes);
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	private void montarArquivoTextoNegociacoes(StringBuilder arquivoTxt,
			ArquivoTextoNegociacaoCobrancaEmpresaHelper helper) {

		arquivoTxt.append(1 + ";");
		arquivoTxt.append(helper.getTipoNegociacao() + ";");
		
		if (helper.getIdNegociacao() != null) {
			arquivoTxt.append(Util.truncarString(helper.getIdNegociacao().toString(), 9) + ";");
		} else {
			arquivoTxt.append(";");
		}
		
		if (helper.getDataNegociacao() != null) {
			arquivoTxt.append(Util.truncarString(Util.formatarData(helper.getDataNegociacao()).toString(), 10) + ";");
		} else {
			arquivoTxt.append(";");
		}
		
		if (helper.getDataVencimentoNegociacao() != null) {
			arquivoTxt.append(Util.truncarString(Util.formatarData(helper.getDataVencimentoNegociacao()).toString(), 10) + ";");
		} else {
			arquivoTxt.append(";");
		}
		
		if (helper.getValorDivida() != null) {
			arquivoTxt.append(Util.formatarBigDecimalComPonto(helper.getValorDivida()) + ";");
		} else {
			arquivoTxt.append(";");
		}
		
		if (helper.getValorDescontos() != null) {
			arquivoTxt.append(Util.formatarBigDecimalComPonto(helper.getValorDescontos()) + ";");
		} else {
			arquivoTxt.append(";");
		}
		
		if (helper.getValorEntrada() != null) {
			arquivoTxt.append(Util.formatarBigDecimalComPonto(helper.getValorEntrada()) + ";");
		} else {
			arquivoTxt.append(";");
		}
		
		if (helper.getValorParcela() != null) {
			arquivoTxt.append(Util.formatarBigDecimalComPonto(helper.getValorParcela()) + ";");
		} else {
			arquivoTxt.append(";");
		}
		
		if (helper.getQuantidadePrestacoes() != null) {
			arquivoTxt.append(Util.truncarString(helper.getQuantidadePrestacoes().toString(), 1) + ";");
		} else {
			arquivoTxt.append(";");
		}
		
		arquivoTxt.append(System.getProperty("line.separator"));
		
		montarArquivoTextoContasNegociadas(arquivoTxt, helper);
	}
	
	private void montarArquivoTextoContasNegociadas(StringBuilder arquivoTxt,
			ArquivoTextoNegociacaoCobrancaEmpresaHelper helper) {

		arquivoTxt.append(2 + ";");
		
		for (Integer idConta : helper.getIdsContas()) {
			arquivoTxt.append(idConta + ";");
		}
		
		arquivoTxt.append(System.getProperty("line.separator"));
	}
	
	private void enviarEmailArquivoContasCobrancaEmpresa(Integer idEmpresa, StringBuilder registros) throws ControladorException {
		String dataHora = Util.formatarDataAAAAMMDD(new Date()) + "-" + Util.formatarDataHHMM(new Date());

		String nomeArquivo = "movimento_contas_cobranca_empresa_" + idEmpresa + "_" + dataHora;
		
		EnvioEmail envioEmail = getControladorCadastro().pesquisarEnvioEmail(EnvioEmail.COBRANCA_EMPRESA);
		
		String titulo = "Negociações da Empresa de Cobrança - " + idEmpresa ;
		String corpo = "Negociações da Empresa de Cobrança : " + idEmpresa ;
		
		ServicosEmail.enviarArquivoCompactado(nomeArquivo, registros, envioEmail.getEmailReceptor(), envioEmail.getEmailRemetente(), titulo, corpo);
	}
}
