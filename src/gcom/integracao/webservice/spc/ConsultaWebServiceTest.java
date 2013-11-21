
/**
 * ConsultaWebServiceTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */
package gcom.integracao.webservice.spc;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.integracao.webservice.spc.ConsultaWebServiceStub.FiltroConsulta;
import gcom.integracao.webservice.spc.ConsultaWebServiceStub.PessoaFisica;
import gcom.integracao.webservice.spc.ConsultaWebServiceStub.PessoaJuridica;
import gcom.integracao.webservice.spc.ConsultaWebServiceStub.Resultado;
import gcom.integracao.webservice.spc.ConsultaWebServiceStub.TipoPessoa;
import gcom.seguranca.ConsultaCdl;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.Header;

/**
 * Auto generated test method
 */
public class ConsultaWebServiceTest {
	
	public ConsultaWebServiceTest() {}


	public ConsultaCdl consultarPessoaFisica(String nome, String cpf){

		
		ConsultaCdl resultadoConsultaCdl = new ConsultaCdl();
		
		ConsultaWebServiceStub stub;
		try {
			stub = new ConsultaWebServiceStub();
		
		
		ConsultaWebServiceStub.Filtro filtro1;
		
			filtro1 = (ConsultaWebServiceStub.Filtro) getTestObject(ConsultaWebServiceStub.Filtro.class);
		

		setarOpcoes(stub);

		FiltroConsulta filtroConsulta = new FiltroConsulta();
		filtroConsulta.setCodigoProduto("11");
		filtroConsulta.setTipoConsumidor(TipoPessoa.F);
		filtroConsulta.setDocumentoConsumidor(cpf);
		
		filtro1.setFiltro(filtroConsulta);
		
		Resultado resultado = stub.consultar(filtro1);
		
		String nomeReceita = resultado.getResultado().getConsumidor()
		.getConsumidorPessoaFisica().getNome();
		
		//Inserir dados da consulta na Receita Federal
		PessoaFisica consultaReceita = resultado.getResultado().getConsumidor()
		.getConsumidorPessoaFisica();
		
		if (consultaReceita.getTelefoneComercial() != null &&  
				consultaReceita.getTelefoneComercial().getNumeroDdd()+"" != null &&
					consultaReceita.getTelefoneComercial().getNumeroDdd() > 0){
		resultadoConsultaCdl.setCodigoDddComercial(new Integer(consultaReceita.getTelefoneComercial().getNumeroDdd()).toString());
		}
		
		if (consultaReceita.getTelefoneComercial() != null && 
				consultaReceita.getTelefoneComercial().getNumero()+"" != null && 
					consultaReceita.getTelefoneComercial().getNumero() > 0){
		resultadoConsultaCdl.setTelefoneComercialCliente(consultaReceita.getTelefoneComercial().getNumero()+"");
		}
		
		if (consultaReceita.getFax() != null && 
				consultaReceita.getFax().getNumeroDdd()+"" != null && 
					consultaReceita.getFax().getNumeroDdd() > 0){
		resultadoConsultaCdl.setCodigoDddFax(consultaReceita.getFax().getNumeroDdd()+"");
		}
		
		if (consultaReceita.getFax() != null &&
				consultaReceita.getFax().getNumero()+"" != null &&
					consultaReceita.getFax().getNumero() > 0){
		resultadoConsultaCdl.setNumeroFaxCliente(consultaReceita.getFax().getNumero()+"");
		}
		
		if (consultaReceita.getEndereco() != null && 
				consultaReceita.getEndereco().getLogradouro() != null){
		resultadoConsultaCdl.setLogradouroCliente(
				Util.truncarString(consultaReceita.getEndereco().getLogradouro(),50));
		}
		
		if (consultaReceita.getEndereco() != null &&
				consultaReceita.getEndereco().getNumero() != null){
			
			resultadoConsultaCdl.setNumeroImovelCliente(
					Util.truncarString(consultaReceita.getEndereco().getNumero(),5));
		}
		
		if (consultaReceita.getEndereco() != null && 
				consultaReceita.getEndereco().getComplemento() != null){
		resultadoConsultaCdl.setComplementoEnderecoCliente(
				Util.truncarString(consultaReceita.getEndereco().getComplemento(),50));
		}
		
		if (consultaReceita.getEndereco() != null && 
				consultaReceita.getEndereco().getBairro() != null){
		resultadoConsultaCdl.setBairroCliente(
				Util.truncarString(consultaReceita.getEndereco().getBairro(),30));
		}
		
		if (consultaReceita.getEndereco() != null && 
				consultaReceita.getEndereco().getCidade() != null && 
					consultaReceita.getEndereco().getCidade().getNome() != null){
		resultadoConsultaCdl.setCidadeCliente(
				Util.truncarString(consultaReceita.getEndereco().getCidade().getNome(),30));
		}
		
		if (consultaReceita.getEndereco() != null && 
				consultaReceita.getEndereco().getCep() != null){
		resultadoConsultaCdl.setCepCliente(consultaReceita.getEndereco().getCep().getCep());
		}
		
		if (consultaReceita.getEndereco() != null &&
				consultaReceita.getEndereco().getCidade() != null && 
					consultaReceita.getEndereco().getCidade().getEstado() != null && 
						consultaReceita.getEndereco().getCidade().getEstado().getSiglaUf() != null){
		resultadoConsultaCdl.setUf(consultaReceita.getEndereco().getCidade().getEstado().getSiglaUf());
		}
		
		resultadoConsultaCdl.setUltimaAlteracao(new Date());
		
		resultadoConsultaCdl.setCpfCliente(consultaReceita.getCpf().getNumero());
		
		if (consultaReceita.getSituacaoCpf() != null &&
				consultaReceita.getSituacaoCpf().getDescricaoSituacao() != null){
		resultadoConsultaCdl.setSituacaoCpf(consultaReceita.getSituacaoCpf().getDescricaoSituacao());
		}
		
		if (consultaReceita.getEstadoRg() != null &&
				consultaReceita.getEstadoRg().toString() != null){
		resultadoConsultaCdl.setSituacaoRg(
				Util.truncarString(consultaReceita.getEstadoRg().toString(),50));
		}
		
		if(consultaReceita.getDataNascimento() != null &&
				consultaReceita.getDataNascimento().getTime() != null){
		resultadoConsultaCdl.setDataNascimento(consultaReceita.getDataNascimento().getTime());
		}
		
		if (consultaReceita.getEstadoCivil() != null &&
				consultaReceita.getEstadoCivil().getValue() != null){
		resultadoConsultaCdl.setEstadoCivil(
				Util.truncarString(consultaReceita.getEstadoCivil().getValue(),20));
		}
		
		if(new Integer(consultaReceita.getIdade()) != null &&
				consultaReceita.getIdade() > 0){
		resultadoConsultaCdl.setIdadeCliente(consultaReceita.getIdade());
		}
		
		if(consultaReceita.getNomeMae() != null){
		resultadoConsultaCdl.setNomeMae(
				Util.truncarString(consultaReceita.getNomeMae(),50));
		}
		
		if(consultaReceita.getNomePai() != null){
		resultadoConsultaCdl.setNomePai(
				Util.truncarString(consultaReceita.getNomePai(),50));
		}
		
		if(consultaReceita.getNumeroRg() != null){
		resultadoConsultaCdl.setNumeroRg(consultaReceita.getNumeroRg());
		}
		
		if(consultaReceita.getNumeroTituloEleitor()+"" != null &&
				consultaReceita.getNumeroTituloEleitor() > 0){
		resultadoConsultaCdl.setTituloEleitor(consultaReceita.getNumeroTituloEleitor()+"");
		}
		
		if(consultaReceita.getSexo() != null &&
				consultaReceita.getSexo().getValue() != null){
		resultadoConsultaCdl.setSexoCliente(
				Util.truncarString(consultaReceita.getSexo().getValue(),20));
		}

		resultadoConsultaCdl.setNomeCliente(
				Util.truncarString(nomeReceita,50));

		if (consultaReceita.getTelefoneResidencial() != null &&
				consultaReceita.getTelefoneResidencial().getNumeroDdd()+"" != null && 
					consultaReceita.getTelefoneResidencial().getNumeroDdd() > 0){
		resultadoConsultaCdl.setCodigoDddResidencial(consultaReceita.getTelefoneResidencial().getNumeroDdd()+"");
		}
		
		if (consultaReceita.getTelefoneResidencial() != null && 
				consultaReceita.getTelefoneResidencial().getNumero()+"" != null &&
					consultaReceita.getTelefoneResidencial().getNumero() > 0){
		resultadoConsultaCdl.setTelefoneResidencialCliente(consultaReceita.getTelefoneResidencial().getNumero()+"");
		}
		
		if (consultaReceita.getTelefoneCelular() != null && 
				consultaReceita.getTelefoneCelular().getNumeroDdd()+"" != null &&
					consultaReceita.getTelefoneCelular().getNumeroDdd() > 0){
		resultadoConsultaCdl.setCodigoDddCelular(consultaReceita.getTelefoneCelular().getNumeroDdd()+"");
		}
		
		if (consultaReceita.getTelefoneCelular() != null && 
				consultaReceita.getTelefoneCelular().getNumero()+"" != null &&
					consultaReceita.getTelefoneCelular().getNumero() > 0){
		resultadoConsultaCdl.setTelefoneCelularCliente(consultaReceita.getTelefoneCelular().getNumero()+"");
		}
		
		} catch (AxisFault e) {
			e.printStackTrace();
			resultadoConsultaCdl.setMensagemRetorno(e.getMessage());		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultadoConsultaCdl;
	}
	
	public ConsultaCdl consultaPessoaJuridica(String razaoSocial, String cnpj){

		
		ConsultaCdl resultadoConsultaCdl = new ConsultaCdl();
		
		ConsultaWebServiceStub stub;
		try {
			stub = new ConsultaWebServiceStub();
		
		
		ConsultaWebServiceStub.Filtro filtro1;
		
			filtro1 = (ConsultaWebServiceStub.Filtro) getTestObject(ConsultaWebServiceStub.Filtro.class);
		

		setarOpcoes(stub);

		FiltroConsulta filtroConsulta = new FiltroConsulta();
		filtroConsulta.setCodigoProduto("103");
		filtroConsulta.setTipoConsumidor(TipoPessoa.J);
		filtroConsulta.setDocumentoConsumidor(cnpj);
		
		filtro1.setFiltro(filtroConsulta);
		
		Resultado resultado = stub.consultar(filtro1);
		
		String razaoSocialReceita = resultado.getResultado().getConsumidor()
		.getConsumidorPessoaJuridica().getRazaoSocial();
		
		//Inserir dados da consulta na Receita Federal 
		PessoaJuridica consultaReceita = resultado.getResultado().getConsumidor()
		.getConsumidorPessoaJuridica();
		
		
		if (consultaReceita.getTelefone() != null &&
				consultaReceita.getTelefone().getNumeroDdd()+"" != null &&
					consultaReceita.getTelefone().getNumeroDdd() > 0){
		resultadoConsultaCdl.setCodigoDddComercial(new Integer(consultaReceita.getTelefone().getNumeroDdd()).toString());
		}
		
		if (consultaReceita.getTelefone() != null &&
				consultaReceita.getTelefone().getNumero()+"" != null &&
					consultaReceita.getTelefone().getNumero() > 0){
		resultadoConsultaCdl.setTelefoneComercialCliente(consultaReceita.getTelefone().getNumero()+"");
		}
		
		if (consultaReceita.getFax() != null &&
				consultaReceita.getFax().getNumeroDdd()+"" != null &&
					consultaReceita.getFax().getNumeroDdd() > 0){
		resultadoConsultaCdl.setCodigoDddFax(consultaReceita.getFax().getNumeroDdd()+"");
		}
		
		if (consultaReceita.getFax() != null && 
				consultaReceita.getFax().getNumero()+"" != null &&
					consultaReceita.getFax().getNumero() > 0){
		resultadoConsultaCdl.setNumeroFaxCliente(consultaReceita.getFax().getNumero()+"");
		}
		
		if (consultaReceita.getEndereco() != null && 
				consultaReceita.getEndereco().getLogradouro() != null){
		resultadoConsultaCdl.setLogradouroCliente(
				Util.truncarString(consultaReceita.getEndereco().getLogradouro(),50));
		}
		
		if (consultaReceita.getEndereco() != null &&
				consultaReceita.getEndereco().getNumero() != null){
			
			resultadoConsultaCdl.setNumeroImovelCliente(
					Util.truncarString(consultaReceita.getEndereco().getNumero(),5));
		}
		
		if (consultaReceita.getEndereco() != null && 
				consultaReceita.getEndereco().getComplemento() != null){
		resultadoConsultaCdl.setComplementoEnderecoCliente(
				Util.truncarString(consultaReceita.getEndereco().getComplemento(),50));
		}
		
		if (consultaReceita.getEndereco() != null && 
				consultaReceita.getEndereco().getBairro() != null){
		resultadoConsultaCdl.setBairroCliente(
				Util.truncarString(consultaReceita.getEndereco().getBairro(),30));
		}
		
		if (consultaReceita.getEndereco() != null && 
				consultaReceita.getEndereco().getCidade() != null && 
					consultaReceita.getEndereco().getCidade().getNome() != null){
		resultadoConsultaCdl.setCidadeCliente(
				Util.truncarString(consultaReceita.getEndereco().getCidade().getNome(),30));
		}
		
		if (consultaReceita.getEndereco() != null && 
				consultaReceita.getEndereco().getCep() != null){
		resultadoConsultaCdl.setCepCliente(consultaReceita.getEndereco().getCep().getCep());
		}
		
		if (consultaReceita.getEndereco() != null &&
				consultaReceita.getEndereco().getCidade() != null && 
					consultaReceita.getEndereco().getCidade().getEstado() != null && 
						consultaReceita.getEndereco().getCidade().getEstado().getSiglaUf() != null){
		resultadoConsultaCdl.setUf(consultaReceita.getEndereco().getCidade().getEstado().getSiglaUf());
		}
		
		resultadoConsultaCdl.setUltimaAlteracao(new Date());
		
		resultadoConsultaCdl.setCnpjCliente(consultaReceita.getCnpj().getNumero());
		
		if (consultaReceita.getSituacaoCnpj() != null && 
				consultaReceita.getSituacaoCnpj().getDescricaoSituacao() != null){
		resultadoConsultaCdl.setSituacaoCnpj(consultaReceita.getSituacaoCnpj().getDescricaoSituacao());
		}
		
		if (consultaReceita.getSituacaoInscricaoEstadual() != null && 
				consultaReceita.getSituacaoInscricaoEstadual().getDescricaoSituacao() != null){
		resultadoConsultaCdl.setSituacaoInscricaoEstadual(
				Util.truncarString(consultaReceita.getSituacaoInscricaoEstadual().getDescricaoSituacao(),50));
		}
		
		if (consultaReceita.getNaturezaJuridica() != null && 
				consultaReceita.getNaturezaJuridica().getDescricao() != null){
		resultadoConsultaCdl.setNaturezaJuridica(
				Util.truncarString(consultaReceita.getNaturezaJuridica().getDescricao(),50));
		}
		
		if (consultaReceita.getAtividadeEconomicaPrincipal() != null && 
				consultaReceita.getAtividadeEconomicaPrincipal().getDescricao() != null){
			
			resultadoConsultaCdl.setAtividadeEconomicaPrincipal(
					Util.truncarString(consultaReceita.getAtividadeEconomicaPrincipal().getDescricao(),50));
		}
		
		if (consultaReceita.getAtividadeEconomicaSecundaria() != null){
		resultadoConsultaCdl.setAtividadeEconomicaSecundaria(
				Util.truncarString(consultaReceita.getAtividadeEconomicaSecundaria().toString(),50));
		}
		
		if (consultaReceita.getDataFundacao() != null && 
				consultaReceita.getDataFundacao().getTime() != null){
		resultadoConsultaCdl.setDataFundacao(consultaReceita.getDataFundacao().getTime());
		}
		
		if (consultaReceita.getInscricaoEstadual()+"" != null &&
				consultaReceita.getInscricaoEstadual() > 0){
		resultadoConsultaCdl.setInscricaoEstadual(consultaReceita.getInscricaoEstadual()+"");
		}
		
		if (consultaReceita.getNomeComercial() != null){
		resultadoConsultaCdl.setNomeComercial(
				Util.truncarString(consultaReceita.getNomeComercial(),50));
		}
		
		if (consultaReceita.getNumeroNIRENIRC()+"" != null &&
				consultaReceita.getNumeroNIRENIRC() > 0){
		resultadoConsultaCdl.setNumeroNireniec(consultaReceita.getNumeroNIRENIRC()+"");
		}
		
		resultadoConsultaCdl.setRazaoSocial(consultaReceita.getRazaoSocial());
		
		resultadoConsultaCdl.setNomeCliente(
				Util.truncarString(razaoSocialReceita,50));
		
		if (consultaReceita.getRazaoSocialAnterior() != null){
		resultadoConsultaCdl.setRazaoSocialAnterior(
				Util.truncarString(consultaReceita.getRazaoSocialAnterior(),50));
		}
		
		if (consultaReceita.getValorCapitalSocial() != null){
		resultadoConsultaCdl.setValorCapitalSocial(consultaReceita.getValorCapitalSocial());
		}
		
		} catch (AxisFault e) {
			e.printStackTrace();
			resultadoConsultaCdl.setMensagemRetorno(e.getMessage());		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultadoConsultaCdl;
	}

	
	
	/**
	 * Auto generated test method
	 */
	public void testconsultar() throws java.lang.Exception {

		ConsultaWebServiceStub stub2 = new ConsultaWebServiceStub();// the
					
		ConsultaWebServiceStub.Filtro filtro15 = (ConsultaWebServiceStub.Filtro) getTestObject(ConsultaWebServiceStub.Filtro.class);

		setarOpcoes(stub2);

		FiltroConsulta filtroConsulta = new FiltroConsulta();
		filtroConsulta.setCodigoProduto("11");
		filtroConsulta.setTipoConsumidor(TipoPessoa.F);
		filtroConsulta.setDocumentoConsumidor("03923151497");
		
		// 11F24970360472
		filtro15.setFiltro(filtroConsulta);
		// TODO : Fill in the filtro15 here

		// assertNotNull();
		try {
		
			Resultado resultado = stub2.consultar(filtro15);

		System.out.println(resultado.getResultado().getConsumidor()
				.getConsumidorPessoaFisica().getNome());

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(resultado.getResultado().getConsumidor().getConsumidorPessoaJuridica().getRazaoSocial());
	}



	// Create an ADBBean and provide it as the test object
	public org.apache.axis2.databinding.ADBBean getTestObject(
			java.lang.Class type) throws java.lang.Exception {
		return (org.apache.axis2.databinding.ADBBean) type.newInstance();
	}

	private void setarOpcoes(
			ConsultaWebServiceStub stub) {
		stub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);

		
		
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = null;
		
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
		
		sistemaParametro = (SistemaParametro)Util.retonarObjetoDeColecao(colecaoSistemaParametro);
		
		List<Header> headers = new ArrayList<Header>();
		headers.add(new Header("Authorization", "Basic "+ sistemaParametro.getLoginSenhaCdl())); //MzcwNDMzOjA5MDkyMDEw
		stub._getServiceClient()
				.getOptions()
				.setProperty(
						org.apache.axis2.transport.http.HTTPConstants.HTTP_HEADERS,
						headers);
	}
	

	public static void main(String[] args) {
		try {
			new ConsultaWebServiceTest().testconsultar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
