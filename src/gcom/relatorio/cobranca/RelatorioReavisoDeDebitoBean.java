package gcom.relatorio.cobranca;

import gcom.cobranca.bean.ReavisoDeDebitoHelper;
import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC ] 
 * @author Vivianne Sousa
 * @date 26/07/2007
 */
public class RelatorioReavisoDeDebitoBean implements RelatorioBean {
	
	private JRBeanCollectionDataSource arrayJRDetail1;
	private ArrayList arrayRelatorioReavisoDeDebitoDetail1Bean;
	
	private String numero1;
	private String nomeCliente1;
	private String endereco1;
	private String endereco1Linha2;
	private String endereco1Linha3;
	private String dataEmissao1;
	private String rotaGrupo1;
	private String inscricao1;
	private String codigoAuxiliar1;
	private String numeroHidrometro1;
	private String vencimento1;
	private String totalAPagar1;
	private String representacaoNumericaCodBarraFormatada1;
	private String representacaoNumericaCodBarraSemDigito1;

	private JRBeanCollectionDataSource arrayJRDetail2;
	private ArrayList arrayRelatorioReavisoDeDebitoDetail2Bean;
	
	private String numero2;
	private String nomeCliente2;
	private String endereco2;
	private String endereco2Linha2;
	private String endereco2Linha3;
	private String dataEmissao2;
	private String rotaGrupo2;
	private String inscricao2;
	private String codigoAuxiliar2;
	private String numeroHidrometro2;
	private String vencimento2;
	private String totalAPagar2;
	private String representacaoNumericaCodBarraFormatada2;
	private String representacaoNumericaCodBarraSemDigito2;
	
    public RelatorioReavisoDeDebitoBean(ReavisoDeDebitoHelper reavisoDeDebitoHelper1,
    		ReavisoDeDebitoHelper reavisoDeDebitoHelper2) {
    	
		this.arrayRelatorioReavisoDeDebitoDetail1Bean = new ArrayList();
		this.arrayRelatorioReavisoDeDebitoDetail1Bean.addAll(
			this.gerarDetail(reavisoDeDebitoHelper1.getColecaoReavisoDeDebitoLinhasDescricaoDebitosHelper(),1));
		
		this.arrayJRDetail1 = new JRBeanCollectionDataSource(this.arrayRelatorioReavisoDeDebitoDetail1Bean);
		
		this.numero1 = reavisoDeDebitoHelper1.getNumero();
		this.nomeCliente1 = reavisoDeDebitoHelper1.getNomeCliente();
		this.endereco1 = reavisoDeDebitoHelper1.getEndereco();
		this.endereco1Linha2 = reavisoDeDebitoHelper1.getEnderecoLinha2();
		this.endereco1Linha3 = reavisoDeDebitoHelper1.getEnderecoLinha3();
		this.dataEmissao1 = reavisoDeDebitoHelper1.getDataEmissao();
		this.rotaGrupo1 = reavisoDeDebitoHelper1.getRotaGrupo();
		this.inscricao1 = reavisoDeDebitoHelper1.getInscricao();
		this.codigoAuxiliar1 = reavisoDeDebitoHelper1.getCodigoAuxiliar();
		this.numeroHidrometro1 = reavisoDeDebitoHelper1.getNumeroHidrometro();
		this.vencimento1 = reavisoDeDebitoHelper1.getVencimento();
		this.totalAPagar1 = reavisoDeDebitoHelper1.getTotalAPagar();
		this.representacaoNumericaCodBarraFormatada1 = reavisoDeDebitoHelper1.getRepresentacaoNumericaCodBarraFormatada();
		this.representacaoNumericaCodBarraSemDigito1 = reavisoDeDebitoHelper1.getRepresentacaoNumericaCodBarraSemDigito();

		this.arrayRelatorioReavisoDeDebitoDetail2Bean = new ArrayList();
		this.arrayRelatorioReavisoDeDebitoDetail2Bean.addAll(
			this.gerarDetail(reavisoDeDebitoHelper2.getColecaoReavisoDeDebitoLinhasDescricaoDebitosHelper(),2));				

		this.arrayJRDetail2 = new JRBeanCollectionDataSource(this.arrayRelatorioReavisoDeDebitoDetail2Bean);
    	
		this.numero2 = reavisoDeDebitoHelper2.getNumero();
		this.nomeCliente2 = reavisoDeDebitoHelper2.getNomeCliente();
		this.endereco2 = reavisoDeDebitoHelper2.getEndereco();
		this.endereco2Linha2 = reavisoDeDebitoHelper2.getEnderecoLinha2();
		this.endereco2Linha3 = reavisoDeDebitoHelper2.getEnderecoLinha3();
		this.dataEmissao2 = reavisoDeDebitoHelper2.getDataEmissao();
		this.rotaGrupo2 = reavisoDeDebitoHelper2.getRotaGrupo();
		this.inscricao2 = reavisoDeDebitoHelper2.getInscricao();
		this.codigoAuxiliar2 = reavisoDeDebitoHelper2.getCodigoAuxiliar();
		this.numeroHidrometro2 = reavisoDeDebitoHelper2.getNumeroHidrometro();
		this.vencimento2 = reavisoDeDebitoHelper2.getVencimento();
		this.totalAPagar2 = reavisoDeDebitoHelper2.getTotalAPagar();
		this.representacaoNumericaCodBarraFormatada2 = reavisoDeDebitoHelper2.getRepresentacaoNumericaCodBarraFormatada();
		this.representacaoNumericaCodBarraSemDigito2 = reavisoDeDebitoHelper2.getRepresentacaoNumericaCodBarraSemDigito();

   }

	
	public JRBeanCollectionDataSource getArrayJRDetail1() {
		return arrayJRDetail1;
	}


	public void setArrayJRDetail1(JRBeanCollectionDataSource arrayJRDetail1) {
		this.arrayJRDetail1 = arrayJRDetail1;
	}


	public JRBeanCollectionDataSource getArrayJRDetail2() {
		return arrayJRDetail2;
	}


	public void setArrayJRDetail2(JRBeanCollectionDataSource arrayJRDetail2) {
		this.arrayJRDetail2 = arrayJRDetail2;
	}


	public ArrayList getArrayRelatorioReavisoDeDebitoDetail1Bean() {
		return arrayRelatorioReavisoDeDebitoDetail1Bean;
	}


	public void setArrayRelatorioReavisoDeDebitoDetail1Bean(
			ArrayList arrayRelatorioReavisoDeDebitoDetail1Bean) {
		this.arrayRelatorioReavisoDeDebitoDetail1Bean = arrayRelatorioReavisoDeDebitoDetail1Bean;
	}


	public ArrayList getArrayRelatorioReavisoDeDebitoDetail2Bean() {
		return arrayRelatorioReavisoDeDebitoDetail2Bean;
	}


	public void setArrayRelatorioReavisoDeDebitoDetail2Bean(
			ArrayList arrayRelatorioReavisoDeDebitoDetail2Bean) {
		this.arrayRelatorioReavisoDeDebitoDetail2Bean = arrayRelatorioReavisoDeDebitoDetail2Bean;
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


	public String getCodigoAuxiliar1() {
		return codigoAuxiliar1;
	}

	public void setCodigoAuxiliar1(String codigoAuxiliar1) {
		this.codigoAuxiliar1 = codigoAuxiliar1;
	}

	public String getCodigoAuxiliar2() {
		return codigoAuxiliar2;
	}

	public void setCodigoAuxiliar2(String codigoAuxiliar2) {
		this.codigoAuxiliar2 = codigoAuxiliar2;
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

	public String getEndereco2() {
		return endereco2;
	}

	public void setEndereco2(String endereco2) {
		this.endereco2 = endereco2;
	}

	public String getInscricao1() {
		return inscricao1;
	}

	public void setInscricao1(String inscricao1) {
		this.inscricao1 = inscricao1;
	}

	public String getInscricao2() {
		return inscricao2;
	}

	public void setInscricao2(String inscricao2) {
		this.inscricao2 = inscricao2;
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

	public String getNumero1() {
		return numero1;
	}

	public void setNumero1(String numero1) {
		this.numero1 = numero1;
	}

	public String getNumero2() {
		return numero2;
	}

	public void setNumero2(String numero2) {
		this.numero2 = numero2;
	}

	public String getNumeroHidrometro1() {
		return numeroHidrometro1;
	}

	public void setNumeroHidrometro1(String numeroHidrometro1) {
		this.numeroHidrometro1 = numeroHidrometro1;
	}

	public String getNumeroHidrometro2() {
		return numeroHidrometro2;
	}

	public void setNumeroHidrometro2(String numeroHidrometro2) {
		this.numeroHidrometro2 = numeroHidrometro2;
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

	public String getRotaGrupo1() {
		return rotaGrupo1;
	}

	public void setRotaGrupo1(String rotaGrupo1) {
		this.rotaGrupo1 = rotaGrupo1;
	}

	public String getRotaGrupo2() {
		return rotaGrupo2;
	}

	public void setRotaGrupo2(String rotaGrupo2) {
		this.rotaGrupo2 = rotaGrupo2;
	}

	public String getTotalAPagar1() {
		return totalAPagar1;
	}

	public void setTotalAPagar1(String totalAPagar1) {
		this.totalAPagar1 = totalAPagar1;
	}

	public String getTotalAPagar2() {
		return totalAPagar2;
	}

	public void setTotalAPagar2(String totalAPagar2) {
		this.totalAPagar2 = totalAPagar2;
	}

	public String getVencimento1() {
		return vencimento1;
	}

	public void setVencimento1(String vencimento1) {
		this.vencimento1 = vencimento1;
	}

	public String getVencimento2() {
		return vencimento2;
	}

	public void setVencimento2(String vencimento2) {
		this.vencimento2 = vencimento2;
	}

    
    private Collection gerarDetail(Collection colecaoDescricaoDebitos,int tipoRelatorio){
    	
		Collection colecaoDetail = new ArrayList();
		
		if(colecaoDescricaoDebitos != null && !colecaoDescricaoDebitos.isEmpty()){
	    	Iterator iterator = colecaoDescricaoDebitos.iterator();		
			while (iterator.hasNext()) {
				
				ReavisoDeDebitoLinhasDescricaoDebitosHelper linhasDescricaoDebitosHelper 
					= (ReavisoDeDebitoLinhasDescricaoDebitosHelper) iterator.next();
				
				Object relatorio =  null;
						
				if(tipoRelatorio == 1){
					relatorio = new RelatorioReavisoDeDebitoDetail1Bean(
							linhasDescricaoDebitosHelper.getMesAno(),
							linhasDescricaoDebitosHelper.getOrigem(), 
							linhasDescricaoDebitosHelper.getVencimento(), 
							linhasDescricaoDebitosHelper.getValor());
					
				}else{
					relatorio = new RelatorioReavisoDeDebitoDetail2Bean(
							linhasDescricaoDebitosHelper.getMesAno(),
							linhasDescricaoDebitosHelper.getOrigem(), 
							linhasDescricaoDebitosHelper.getVencimento(), 
							linhasDescricaoDebitosHelper.getValor());
					
				}
				
				colecaoDetail.add(relatorio);
	    	
			}
		}
		
		return colecaoDetail;
    }
	
	
}
