package gcom.relatorio.faturamento;

import gcom.faturamento.bean.FaturaClienteResponsavelHelper;
import gcom.faturamento.bean.FaturaItemClienteResponsavelHelper;
import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC ] 
 * @author Vivianne Sousa
 * @date 16/08/2007
 */
public class RelatorioFaturaClienteResponsavelBean implements RelatorioBean {
	
	private String sequencialFatura;
	
	private JRBeanCollectionDataSource arrayJRDetailFatura1;
	private ArrayList arrayRelatorioDetailFatura1Bean;
	
	private String nomeCliente1;
	private String endereco1;
	private String endereco1Linha2;
	private String endereco1Linha3;
	private String tipoResponsavel1;
	private String qtdeItens1;
	private String dataEmissao1;
	private String mesAno1;
	private String primeiroVencimento1;
	private String valorTotalAPagar1;
	private String numeroFatura1;
	private String representacaoNumericaCodBarraFormatada1;
	private String representacaoNumericaCodBarraSemDigito1;
	private String valorMedioTurbidez1;
	private String valorMedioPh1;
	private String valorMedioCor1;
	private String valorMedioCloro1;
	private String valorMedioFluor1;
	private String valorMedioFerro1;
	private String valorMedioColiformesTotais1;
	private String valorMedioColiformesfecais1;
	private String datasVencimento1; 
	private String indicadorCodigoBarras1;
	
	private String padraoTurbidez;
	private String padraoPh;
	private String padraoCor;
	private String padraoCloro;
	private String padraoFluor;
	private String padraoFerro;
	private String padraoColiformesTotais;
	private String padraoColiformesfecais;

	private JRBeanCollectionDataSource arrayJRDetailFatura2;
	private ArrayList arrayRelatorioDetailFatura2Bean;
	
	private String nomeCliente2;
	private String endereco2;
	private String endereco2Linha2;
	private String endereco2Linha3;
	private String tipoResponsavel2;
	private String qtdeItens2;
	private String dataEmissao2;
	private String mesAno2;
	private String primeiroVencimento2;
	private String valorTotalAPagar2;
	private String numeroFatura2;
	private String representacaoNumericaCodBarraFormatada2;
	private String representacaoNumericaCodBarraSemDigito2;
	private String valorMedioTurbidez2;
	private String valorMedioPh2;
	private String valorMedioCor2;
	private String valorMedioCloro2;
	private String valorMedioFluor2;
	private String valorMedioFerro2;
	private String valorMedioColiformesTotais2;
	private String valorMedioColiformesfecais2;
	private String datasVencimento2;
	private String indicadorCodigoBarras2;
	private String valorBruto1;
	private String valorImpostos1;
	private String valorBruto2;
	private String valorImpostos2;
	
    public RelatorioFaturaClienteResponsavelBean(
    	FaturaClienteResponsavelHelper faturaClienteResponsavelHelper1,
    	FaturaClienteResponsavelHelper faturaClienteResponsavelHelper2,
    	String sequencialFatura) {
    	
    	this.sequencialFatura = sequencialFatura;
    	
		this.arrayRelatorioDetailFatura1Bean = new ArrayList();
		this.arrayRelatorioDetailFatura1Bean.addAll(
			this.gerarDetail(faturaClienteResponsavelHelper1.getColecaoFaturaItemClienteResponsavelHelper(),1));
		
		this.arrayJRDetailFatura1 = new JRBeanCollectionDataSource(this.arrayRelatorioDetailFatura1Bean);
		
		this.nomeCliente1 = faturaClienteResponsavelHelper1.getNomeCliente();
		this.endereco1 = faturaClienteResponsavelHelper1.getEndereco();
		this.endereco1Linha2 = faturaClienteResponsavelHelper1.getEnderecoLinha2();
		this.endereco1Linha3 = faturaClienteResponsavelHelper1.getEnderecoLinha3();
		this.tipoResponsavel1 = faturaClienteResponsavelHelper1.getTipoResponsavel();
		this.qtdeItens1 = faturaClienteResponsavelHelper1.getQtdeItens();
		this.dataEmissao1 = faturaClienteResponsavelHelper1.getDataEmissao();
		this.mesAno1 = faturaClienteResponsavelHelper1.getMesAno();
		this.primeiroVencimento1 = faturaClienteResponsavelHelper1.getPrimeiroVencimento();
		this.valorTotalAPagar1 = faturaClienteResponsavelHelper1.getValorTotalAPagar();
		this.numeroFatura1 = faturaClienteResponsavelHelper1.getNumeroFatura();
		this.representacaoNumericaCodBarraFormatada1 = faturaClienteResponsavelHelper1.getRepresentacaoNumericaCodBarraFormatada();
		this.representacaoNumericaCodBarraSemDigito1 = faturaClienteResponsavelHelper1.getRepresentacaoNumericaCodBarraSemDigito();
		this.valorMedioTurbidez1 = faturaClienteResponsavelHelper1.getValorMedioTurbidez();
		this.valorMedioPh1 = faturaClienteResponsavelHelper1.getValorMedioPh();
		this.valorMedioCor1 = faturaClienteResponsavelHelper1.getValorMedioCor();
		this.valorMedioCloro1 = faturaClienteResponsavelHelper1.getValorMedioCloro();
		this.valorMedioFluor1 = faturaClienteResponsavelHelper1.getValorMedioFluor();
		this.valorMedioFerro1 = faturaClienteResponsavelHelper1.getValorMedioFerro();
		this.valorMedioColiformesTotais1 = faturaClienteResponsavelHelper1.getValorMedioColiformesTotais();
		this.valorMedioColiformesfecais1 = faturaClienteResponsavelHelper1.getValorMedioColiformesfecais();
		this.datasVencimento1 = faturaClienteResponsavelHelper1.getDatasVencimentos();
		this.indicadorCodigoBarras1 = faturaClienteResponsavelHelper1.getIndicadorCodigoBarras();
		
		this.padraoTurbidez = faturaClienteResponsavelHelper1.getPadraoTurbidez();
		this.padraoPh = faturaClienteResponsavelHelper1.getPadraoPh();
		this.padraoCor = faturaClienteResponsavelHelper1.getPadraoCor();
		this.padraoCloro = faturaClienteResponsavelHelper1.getPadraoCloro();
		this.padraoFluor = faturaClienteResponsavelHelper1.getPadraoFluor();
		this.padraoFerro = faturaClienteResponsavelHelper1.getPadraoFerro();
		this.padraoColiformesTotais = faturaClienteResponsavelHelper1.getPadraoColiformesTotais();
		this.padraoColiformesfecais = faturaClienteResponsavelHelper1.getPadraoColiformesfecais();
		this.valorBruto1 = faturaClienteResponsavelHelper1.getValorBruto();
		this.valorImpostos1 = faturaClienteResponsavelHelper1.getValorImposto();
		
		
		this.arrayRelatorioDetailFatura2Bean = new ArrayList();
		this.arrayRelatorioDetailFatura2Bean.addAll(
			this.gerarDetail(faturaClienteResponsavelHelper2.getColecaoFaturaItemClienteResponsavelHelper(),2));				

		this.arrayJRDetailFatura2 = new JRBeanCollectionDataSource(this.arrayRelatorioDetailFatura2Bean);
    	
		this.nomeCliente2 = faturaClienteResponsavelHelper2.getNomeCliente();
		this.endereco2 = faturaClienteResponsavelHelper2.getEndereco();
		this.endereco2Linha2 = faturaClienteResponsavelHelper2.getEnderecoLinha2();
		this.endereco2Linha3 = faturaClienteResponsavelHelper2.getEnderecoLinha3();
		this.tipoResponsavel2 = faturaClienteResponsavelHelper2.getTipoResponsavel();
		this.qtdeItens2 = faturaClienteResponsavelHelper2.getQtdeItens();
		this.dataEmissao2 = faturaClienteResponsavelHelper2.getDataEmissao();
		this.mesAno2 = faturaClienteResponsavelHelper2.getMesAno();
		this.primeiroVencimento2 = faturaClienteResponsavelHelper2.getPrimeiroVencimento();
		this.valorTotalAPagar2 = faturaClienteResponsavelHelper2.getValorTotalAPagar();
		this.numeroFatura2 = faturaClienteResponsavelHelper2.getNumeroFatura();
		this.representacaoNumericaCodBarraFormatada2 = faturaClienteResponsavelHelper2.getRepresentacaoNumericaCodBarraFormatada();
		this.representacaoNumericaCodBarraSemDigito2 = faturaClienteResponsavelHelper2.getRepresentacaoNumericaCodBarraSemDigito();
		this.valorMedioTurbidez2 = faturaClienteResponsavelHelper2.getValorMedioTurbidez();
		this.valorMedioPh2 = faturaClienteResponsavelHelper2.getValorMedioPh();
		this.valorMedioCor2 = faturaClienteResponsavelHelper2.getValorMedioCor();
		this.valorMedioCloro2 = faturaClienteResponsavelHelper2.getValorMedioCloro();
		this.valorMedioFluor2 = faturaClienteResponsavelHelper2.getValorMedioFluor();
		this.valorMedioFerro2 = faturaClienteResponsavelHelper2.getValorMedioFerro();
		this.valorMedioColiformesTotais2 = faturaClienteResponsavelHelper2.getValorMedioColiformesTotais();
		this.valorMedioColiformesfecais2 = faturaClienteResponsavelHelper2.getValorMedioColiformesfecais();
		this.datasVencimento2 = faturaClienteResponsavelHelper2.getDatasVencimentos();
		this.indicadorCodigoBarras2 = faturaClienteResponsavelHelper2.getIndicadorCodigoBarras();
		this.valorBruto2 = faturaClienteResponsavelHelper2.getValorBruto();
		this.valorImpostos2 = faturaClienteResponsavelHelper2.getValorImposto();
   }

    
    private Collection gerarDetail(Collection colecaoFaturaItem,int tipoRelatorio){
    	
		Collection colecaoDetail = new ArrayList();
		
		if(colecaoFaturaItem != null && !colecaoFaturaItem.isEmpty()){
	    	Iterator iterator = colecaoFaturaItem.iterator();		
			while (iterator.hasNext()) {
				
				FaturaItemClienteResponsavelHelper faturaItemClienteResponsavelHelper 
					= (FaturaItemClienteResponsavelHelper) iterator.next();
				
				Object relatorio =  null;
				
				if(tipoRelatorio == 1){
					relatorio = new RelatorioDetailFatura1Bean(
							faturaItemClienteResponsavelHelper.getNome(), 
							faturaItemClienteResponsavelHelper.getMatricula(),
							faturaItemClienteResponsavelHelper.getConsumo(),
							faturaItemClienteResponsavelHelper.getValor());
					
				}else{
					relatorio = new RelatorioDetailFatura2Bean(
							faturaItemClienteResponsavelHelper.getNome(), 
							faturaItemClienteResponsavelHelper.getMatricula(),
							faturaItemClienteResponsavelHelper.getConsumo(),
							faturaItemClienteResponsavelHelper.getValor());
					
					
				}
			
				colecaoDetail.add(relatorio);
	    	
			}
		}
		
		return colecaoDetail;
    }


	public String getSequencialFatura() {
		return sequencialFatura;
	}


	public void setSequencialFatura(String sequencialFatura) {
		this.sequencialFatura = sequencialFatura;
	}


	public JRBeanCollectionDataSource getArrayJRDetailFatura1() {
		return arrayJRDetailFatura1;
	}


	public void setArrayJRDetailFatura1(
			JRBeanCollectionDataSource arrayJRDetailFatura1) {
		this.arrayJRDetailFatura1 = arrayJRDetailFatura1;
	}


	public JRBeanCollectionDataSource getArrayJRDetailFatura2() {
		return arrayJRDetailFatura2;
	}


	public void setArrayJRDetailFatura2(
			JRBeanCollectionDataSource arrayJRDetailFatura2) {
		this.arrayJRDetailFatura2 = arrayJRDetailFatura2;
	}


	public ArrayList getArrayRelatorioDetailFatura1Bean() {
		return arrayRelatorioDetailFatura1Bean;
	}


	public void setArrayRelatorioDetailFatura1Bean(
			ArrayList arrayRelatorioDetailFatura1Bean) {
		this.arrayRelatorioDetailFatura1Bean = arrayRelatorioDetailFatura1Bean;
	}


	public ArrayList getArrayRelatorioDetailFatura2Bean() {
		return arrayRelatorioDetailFatura2Bean;
	}


	public void setArrayRelatorioDetailFatura2Bean(
			ArrayList arrayRelatorioDetailFatura2Bean) {
		this.arrayRelatorioDetailFatura2Bean = arrayRelatorioDetailFatura2Bean;
	}


	public String getDataEmissao1() {
		return dataEmissao1;
	}


	public void setDataEmissao1(String dataEmissao1) {
		this.dataEmissao1 = dataEmissao1;
	}


	public String getDataEmissao2() {
		return dataEmissao2;
	}


	public void setDataEmissao2(String dataEmissao2) {
		this.dataEmissao2 = dataEmissao2;
	}


	public String getEndereco1() {
		return endereco1;
	}


	public void setEndereco1(String endereco1) {
		this.endereco1 = endereco1;
	}


	public String getEndereco1Linha2() {
		return endereco1Linha2;
	}


	public void setEndereco1Linha2(String endereco1Linha2) {
		this.endereco1Linha2 = endereco1Linha2;
	}


	public String getEndereco1Linha3() {
		return endereco1Linha3;
	}


	public void setEndereco1Linha3(String endereco1Linha3) {
		this.endereco1Linha3 = endereco1Linha3;
	}


	public String getEndereco2() {
		return endereco2;
	}


	public void setEndereco2(String endereco2) {
		this.endereco2 = endereco2;
	}


	public String getEndereco2Linha2() {
		return endereco2Linha2;
	}


	public void setEndereco2Linha2(String endereco2Linha2) {
		this.endereco2Linha2 = endereco2Linha2;
	}


	public String getEndereco2Linha3() {
		return endereco2Linha3;
	}


	public void setEndereco2Linha3(String endereco2Linha3) {
		this.endereco2Linha3 = endereco2Linha3;
	}


	public String getMesAno1() {
		return mesAno1;
	}


	public void setMesAno1(String mesAno1) {
		this.mesAno1 = mesAno1;
	}


	public String getMesAno2() {
		return mesAno2;
	}


	public void setMesAno2(String mesAno2) {
		this.mesAno2 = mesAno2;
	}


	public String getNomeCliente1() {
		return nomeCliente1;
	}


	public void setNomeCliente1(String nomeCliente1) {
		this.nomeCliente1 = nomeCliente1;
	}


	public String getNomeCliente2() {
		return nomeCliente2;
	}


	public void setNomeCliente2(String nomeCliente2) {
		this.nomeCliente2 = nomeCliente2;
	}


	public String getNumeroFatura1() {
		return numeroFatura1;
	}


	public void setNumeroFatura1(String numeroFatura1) {
		this.numeroFatura1 = numeroFatura1;
	}


	public String getNumeroFatura2() {
		return numeroFatura2;
	}


	public void setNumeroFatura2(String numeroFatura2) {
		this.numeroFatura2 = numeroFatura2;
	}


	public String getPadraoCloro() {
		return padraoCloro;
	}


	public void setPadraoCloro(String padraoCloro) {
		this.padraoCloro = padraoCloro;
	}


	public String getPadraoColiformesfecais() {
		return padraoColiformesfecais;
	}


	public void setPadraoColiformesfecais(String padraoColiformesfecais) {
		this.padraoColiformesfecais = padraoColiformesfecais;
	}


	public String getPadraoColiformesTotais() {
		return padraoColiformesTotais;
	}


	public void setPadraoColiformesTotais(String padraoColiformesTotais) {
		this.padraoColiformesTotais = padraoColiformesTotais;
	}


	public String getPadraoCor() {
		return padraoCor;
	}


	public void setPadraoCor(String padraoCor) {
		this.padraoCor = padraoCor;
	}


	public String getPadraoFerro() {
		return padraoFerro;
	}


	public void setPadraoFerro(String padraoFerro) {
		this.padraoFerro = padraoFerro;
	}


	public String getPadraoFluor() {
		return padraoFluor;
	}


	public void setPadraoFluor(String padraoFluor) {
		this.padraoFluor = padraoFluor;
	}


	public String getPadraoPh() {
		return padraoPh;
	}


	public void setPadraoPh(String padraoPh) {
		this.padraoPh = padraoPh;
	}


	public String getPadraoTurbidez() {
		return padraoTurbidez;
	}


	public void setPadraoTurbidez(String padraoTurbidez) {
		this.padraoTurbidez = padraoTurbidez;
	}


	public String getPrimeiroVencimento1() {
		return primeiroVencimento1;
	}


	public void setPrimeiroVencimento1(String primeiroVencimento1) {
		this.primeiroVencimento1 = primeiroVencimento1;
	}


	public String getPrimeiroVencimento2() {
		return primeiroVencimento2;
	}


	public void setPrimeiroVencimento2(String primeiroVencimento2) {
		this.primeiroVencimento2 = primeiroVencimento2;
	}


	public String getQtdeItens1() {
		return qtdeItens1;
	}


	public void setQtdeItens1(String qtdeItens1) {
		this.qtdeItens1 = qtdeItens1;
	}


	public String getQtdeItens2() {
		return qtdeItens2;
	}


	public void setQtdeItens2(String qtdeItens2) {
		this.qtdeItens2 = qtdeItens2;
	}


	public String getRepresentacaoNumericaCodBarraFormatada1() {
		return representacaoNumericaCodBarraFormatada1;
	}


	public void setRepresentacaoNumericaCodBarraFormatada1(
			String representacaoNumericaCodBarraFormatada1) {
		this.representacaoNumericaCodBarraFormatada1 = representacaoNumericaCodBarraFormatada1;
	}


	public String getRepresentacaoNumericaCodBarraFormatada2() {
		return representacaoNumericaCodBarraFormatada2;
	}


	public void setRepresentacaoNumericaCodBarraFormatada2(
			String representacaoNumericaCodBarraFormatada2) {
		this.representacaoNumericaCodBarraFormatada2 = representacaoNumericaCodBarraFormatada2;
	}


	public String getRepresentacaoNumericaCodBarraSemDigito1() {
		return representacaoNumericaCodBarraSemDigito1;
	}


	public void setRepresentacaoNumericaCodBarraSemDigito1(
			String representacaoNumericaCodBarraSemDigito1) {
		this.representacaoNumericaCodBarraSemDigito1 = representacaoNumericaCodBarraSemDigito1;
	}


	public String getRepresentacaoNumericaCodBarraSemDigito2() {
		return representacaoNumericaCodBarraSemDigito2;
	}


	public void setRepresentacaoNumericaCodBarraSemDigito2(
			String representacaoNumericaCodBarraSemDigito2) {
		this.representacaoNumericaCodBarraSemDigito2 = representacaoNumericaCodBarraSemDigito2;
	}


	public String getTipoResponsavel1() {
		return tipoResponsavel1;
	}


	public void setTipoResponsavel1(String tipoResponsavel1) {
		this.tipoResponsavel1 = tipoResponsavel1;
	}


	public String getTipoResponsavel2() {
		return tipoResponsavel2;
	}


	public void setTipoResponsavel2(String tipoResponsavel2) {
		this.tipoResponsavel2 = tipoResponsavel2;
	}


	public String getValorMedioCloro1() {
		return valorMedioCloro1;
	}


	public void setValorMedioCloro1(String valorMedioCloro1) {
		this.valorMedioCloro1 = valorMedioCloro1;
	}


	public String getValorMedioCloro2() {
		return valorMedioCloro2;
	}


	public void setValorMedioCloro2(String valorMedioCloro2) {
		this.valorMedioCloro2 = valorMedioCloro2;
	}


	public String getValorMedioColiformesfecais1() {
		return valorMedioColiformesfecais1;
	}


	public void setValorMedioColiformesfecais1(String valorMedioColiformesfecais1) {
		this.valorMedioColiformesfecais1 = valorMedioColiformesfecais1;
	}


	public String getValorMedioColiformesfecais2() {
		return valorMedioColiformesfecais2;
	}


	public void setValorMedioColiformesfecais2(String valorMedioColiformesfecais2) {
		this.valorMedioColiformesfecais2 = valorMedioColiformesfecais2;
	}


	public String getValorMedioColiformesTotais1() {
		return valorMedioColiformesTotais1;
	}


	public void setValorMedioColiformesTotais1(String valorMedioColiformesTotais1) {
		this.valorMedioColiformesTotais1 = valorMedioColiformesTotais1;
	}


	public String getValorMedioColiformesTotais2() {
		return valorMedioColiformesTotais2;
	}


	public void setValorMedioColiformesTotais2(String valorMedioColiformesTotais2) {
		this.valorMedioColiformesTotais2 = valorMedioColiformesTotais2;
	}


	public String getValorMedioCor1() {
		return valorMedioCor1;
	}


	public void setValorMedioCor1(String valorMedioCor1) {
		this.valorMedioCor1 = valorMedioCor1;
	}


	public String getValorMedioCor2() {
		return valorMedioCor2;
	}


	public void setValorMedioCor2(String valorMedioCor2) {
		this.valorMedioCor2 = valorMedioCor2;
	}


	public String getValorMedioFerro1() {
		return valorMedioFerro1;
	}


	public void setValorMedioFerro1(String valorMedioFerro1) {
		this.valorMedioFerro1 = valorMedioFerro1;
	}


	public String getValorMedioFerro2() {
		return valorMedioFerro2;
	}


	public void setValorMedioFerro2(String valorMedioFerro2) {
		this.valorMedioFerro2 = valorMedioFerro2;
	}


	public String getValorMedioFluor1() {
		return valorMedioFluor1;
	}


	public void setValorMedioFluor1(String valorMedioFluor1) {
		this.valorMedioFluor1 = valorMedioFluor1;
	}


	public String getValorMedioFluor2() {
		return valorMedioFluor2;
	}


	public void setValorMedioFluor2(String valorMedioFluor2) {
		this.valorMedioFluor2 = valorMedioFluor2;
	}


	public String getValorMedioPh1() {
		return valorMedioPh1;
	}


	public void setValorMedioPh1(String valorMedioPh1) {
		this.valorMedioPh1 = valorMedioPh1;
	}


	public String getValorMedioPh2() {
		return valorMedioPh2;
	}


	public void setValorMedioPh2(String valorMedioPh2) {
		this.valorMedioPh2 = valorMedioPh2;
	}


	public String getValorMedioTurbidez1() {
		return valorMedioTurbidez1;
	}


	public void setValorMedioTurbidez1(String valorMedioTurbidez1) {
		this.valorMedioTurbidez1 = valorMedioTurbidez1;
	}


	public String getValorMedioTurbidez2() {
		return valorMedioTurbidez2;
	}


	public void setValorMedioTurbidez2(String valorMedioTurbidez2) {
		this.valorMedioTurbidez2 = valorMedioTurbidez2;
	}


	public String getValorTotalAPagar1() {
		return valorTotalAPagar1;
	}


	public void setValorTotalAPagar1(String valorTotalAPagar1) {
		this.valorTotalAPagar1 = valorTotalAPagar1;
	}


	public String getValorTotalAPagar2() {
		return valorTotalAPagar2;
	}


	public void setValorTotalAPagar2(String valorTotalAPagar2) {
		this.valorTotalAPagar2 = valorTotalAPagar2;
	}


	public String getDatasVencimento1() {
		return datasVencimento1;
	}


	public void setDatasVencimento1(String datasVencimento1) {
		this.datasVencimento1 = datasVencimento1;
	}


	public String getDatasVencimento2() {
		return datasVencimento2;
	}


	public void setDatasVencimento2(String datasVencimento2) {
		this.datasVencimento2 = datasVencimento2;
	}


	public String getIndicadorCodigoBarras1() {
		return indicadorCodigoBarras1;
	}


	public void setIndicadorCodigoBarras1(String indicadorCodigoBarras1) {
		this.indicadorCodigoBarras1 = indicadorCodigoBarras1;
	}


	public String getIndicadorCodigoBarras2() {
		return indicadorCodigoBarras2;
	}


	public void setIndicadorCodigoBarras2(String indicadorCodigoBarras2) {
		this.indicadorCodigoBarras2 = indicadorCodigoBarras2;
	}


	public String getValorBruto1() {
		return valorBruto1;
	}


	public void setValorBruto1(String valorBruto1) {
		this.valorBruto1 = valorBruto1;
	}


	public String getValorImpostos1() {
		return valorImpostos1;
	}


	public void setValorImpostos1(String valorImpostos1) {
		this.valorImpostos1 = valorImpostos1;
	}


	public String getValorBruto2() {
		return valorBruto2;
	}


	public void setValorBruto2(String valorBruto2) {
		this.valorBruto2 = valorBruto2;
	}


	public String getValorImpostos2() {
		return valorImpostos2;
	}


	public void setValorImpostos2(String valorImpostos2) {
		this.valorImpostos2 = valorImpostos2;
	}

	
}
