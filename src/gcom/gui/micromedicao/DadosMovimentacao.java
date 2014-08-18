package gcom.gui.micromedicao;

import gcom.micromedicao.ArquivoTextoRoteiroEmpresa;
import gcom.util.Util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DadosMovimentacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long imei;
	private Integer codigoAnormalidade;
	private Integer localidade;
	private Integer setorComercial;
	private Integer numeroQuadra;
	private Integer numeroLote;
	private Integer numeroSubLote;
	private Integer tipoMedicao;
	private Integer grupoFaturamento;
	private Integer matriculaImovel;
	private Integer perfilImovel;
	private String nomeCliente;
	private String endereco;
	private String marcaHidrometro;
	private String numeroHidrometro;
	private String capacidadeHidrometro;
	private String localInstalacao;
	private Date dataInstalacao;
	private String protecaoHidrometro;
	private Integer situacaoLigacaoAgua;
	private Integer situacaoLigacaoEsgoto;
	private String decricaoAbreviadaImovel;
	private Integer quantidadeEconomias;
	private Integer leituraAnterior;
	private Integer faixaLeituraEsperadaInferior;
	private Integer faixaLeituraEsperadaSuperior;
	private Integer leituraHidrometro;
	private String stringDataLeitura;
	private Date dataLeituraCampo;
	private String horaLeituraCampo;
	private Byte indicadorConfirmacaoLeitura;
	private Integer matriculaOperador;
	private String inscricao;
	private Integer codigoRota;
	private Integer numeroSequencialRota;
	private String msgImovelSuprimidoOuHidrometroRetirado;
	private boolean naoPermitirAlterar;
	private ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa;
	private Date dataLeituraCronograma;
	
	public DadosMovimentacao(){}
	
	public DadosMovimentacao(Integer imovel, Integer leitura,
			Integer anormalidade, Date data, Long imei, Byte indicador,
			Integer tipoMedicao) {
		this.codigoAnormalidade = anormalidade;
		this.dataLeituraCampo = data;
		this.leituraHidrometro = leitura;
		this.matriculaImovel = imovel;
		this.imei = imei;
		this.indicadorConfirmacaoLeitura = indicador;
		this.tipoMedicao = tipoMedicao;
	}

	public String getCapacidadeHidrometro() {
		return capacidadeHidrometro;
	}

	public void setCapacidadeHidrometro(String capacidadeHidrometro) {
		this.capacidadeHidrometro = capacidadeHidrometro;
	}	

	public Date getDataInstalacao() {
		return dataInstalacao;
	}

	public void setDataInstalacao(Date dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	public String getDecricaoAbreviadaImovel() {
		return decricaoAbreviadaImovel;
	}

	public void setDecricaoAbreviadaImovel(String decricaoAbreviadaImovel) {
		this.decricaoAbreviadaImovel = decricaoAbreviadaImovel;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getFaixaLeituraEsperadaInferior() {
		return faixaLeituraEsperadaInferior;
	}

	public void setFaixaLeituraEsperadaInferior(Integer faixaLeituraEsperadaInferior) {
		this.faixaLeituraEsperadaInferior = faixaLeituraEsperadaInferior;
	}

	public Integer getFaixaLeituraEsperadaSuperior() {
		return faixaLeituraEsperadaSuperior;
	}

	public void setFaixaLeituraEsperadaSuperior(Integer faixaLeituraEsperadaSuperior) {
		this.faixaLeituraEsperadaSuperior = faixaLeituraEsperadaSuperior;
	}

	public Integer getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(Integer grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public Integer getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(Integer leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public Integer getLeituraHidrometro() {
		return leituraHidrometro;
	}

	public void setLeituraHidrometro(Integer leituraHidrometro) {
		this.leituraHidrometro = leituraHidrometro;
	}

	public Integer getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}

	public String getLocalInstalacao() {
		return localInstalacao;
	}

	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}

	public String getMarcaHidrometro() {
		return marcaHidrometro;
	}

	public void setMarcaHidrometro(String marcaHidrometro) {
		this.marcaHidrometro = marcaHidrometro;
	}

	public Integer getMatriculaOperador() {
		return matriculaOperador;
	}

	public void setMatriculaOperador(Integer matriculaOperador) {
		this.matriculaOperador = matriculaOperador;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}

	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}

	public Integer getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(Integer numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getNumeroSubLote() {
		return numeroSubLote;
	}

	public void setNumeroSubLote(Integer numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	public Integer getPerfilImovel() {
		return perfilImovel;
	}

	public void setPerfilImovel(Integer perfilImovel) {
		this.perfilImovel = perfilImovel;
	}

	public String getProtecaoHidrometro() {
		return protecaoHidrometro;
	}

	public void setProtecaoHidrometro(String protecaoHidrometro) {
		this.protecaoHidrometro = protecaoHidrometro;
	}

	public Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public Integer getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(Integer setorComercial) {
		this.setorComercial = setorComercial;
	}

	public Integer getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(Integer situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public Integer getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(Integer situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public Integer getTipoMedicao() {
		return tipoMedicao;
	}

	public void setTipoMedicao(Integer tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}
	
	public String getStringDataLeitura() {
		return stringDataLeitura;
	}

	public void setStringDataLeitura(String stringDataLeitura) {
		this.stringDataLeitura = stringDataLeitura;
	}

	public void gerarDadosImovel(String linha) throws NumberFormatException, StringIndexOutOfBoundsException{
		
			this.setLocalidade(new Integer(linha.substring(0, 3)));
			this.setSetorComercial(new Integer(linha.substring(3, 6)));
			this.setNumeroQuadra(new Integer(linha.substring(6, 9)));
			this.setNumeroLote(new Integer(linha.substring (9, 13).replace(" ", "")));
			this.setNumeroSubLote(new Integer(linha.substring(13, 16).replace(" ", "")));
			this.setTipoMedicao(new Integer(linha.substring(16, 17)));
			this.setGrupoFaturamento(new Integer(linha.substring(17, 19)));
			this.setMatriculaImovel(new Integer(linha.substring(19, 27)));
			this.setPerfilImovel(new Integer(linha.substring(27,28)));
			this.setNomeCliente(linha.substring(28, 53));
			this.setEndereco(linha.substring (53, 103));
			this.setMarcaHidrometro(linha.substring(103, 105));
			this.setNumeroHidrometro(linha.substring(105, 115));
			this.setCapacidadeHidrometro(linha.substring (115, 117));
			this.setLocalInstalacao(linha.substring(117, 119));
			StringBuffer dataInstalacao = new StringBuffer(linha.substring(119, 125));
			this.setDataInstalacao(this.stringParaData(dataInstalacao.toString()));
			this.setProtecaoHidrometro(linha.substring (125, 127));
			this.setSituacaoLigacaoAgua(new Integer(linha.substring(127, 129)));
			this.setSituacaoLigacaoEsgoto(new Integer(linha.substring(129, 131)));
			this.setDecricaoAbreviadaImovel(linha.substring(131, 134));
			this.setQuantidadeEconomias(new Integer(linha.substring(134, 137)));
			this.setLeituraAnterior(new Integer(this.decrypt(linha.substring(137, 143))));
			this.setFaixaLeituraEsperadaInferior(new Integer(this.decrypt(linha.substring(143, 149))));
			this.setFaixaLeituraEsperadaSuperior(new Integer(this.decrypt(linha.substring(149,155))));
			this.setCodigoRota(new Integer(linha.substring(155,161)));
			this.setNumeroSequencialRota(new Integer(linha.substring(161,165)));
			this.setMatriculaOperador(new Integer(linha.substring(165,173)));
			this.setLeituraHidrometro(new Integer(linha.substring(173,179)));
			this.setCodigoAnormalidade(new Integer(linha.substring(179,181)));
			this.setStringDataLeitura(this.decrypt(linha.substring(181,187)));
			this.setHoraLeituraCampo(this.decrypt(linha.substring(187,193)));
			this.setIndicadorConfirmacaoLeitura(new Byte(linha.substring(193,194)));			
			this.setDataLeituraCampo(this.stringParaDataHora(this.getStringDataLeitura()+this.getHoraLeituraCampo()));
	}
	
	public String decrypt(String str) {
        int tab[] = {77,110,70,114,90,100,86,103,111,75};
        int i;
        int j;
        int value = 0;
        int len = str.length();
        String response = "";
        
        for (i=0; i < len; i++) {
            value = (int) str.charAt(i);
            for (j = 0; j < 10; j++) {
                if (value == tab[j]) {
                        response += String.valueOf(j).trim();
                }
            }
        }
        return response;
    }
	
    public Date stringParaData(String data) {
    	Date dataString = null;

    	try{
            Calendar calendar = Calendar.getInstance();

            char[] dataArray = data.toCharArray();

            int dia = Integer.parseInt(dataArray[4] + "" + dataArray[5]);
            int mes = Integer.parseInt(dataArray[2] + "" + dataArray[3]);
            int ano =Integer.parseInt(dataArray[0] + "" + dataArray[1]);

            calendar.set(Calendar.DAY_OF_MONTH, dia);
            calendar.set(Calendar.MONTH, mes -1);
            calendar.set(Calendar.YEAR, ano + 2000);
            
                                  
            dataString = calendar.getTime();
            
            if(dataString.after(new Date())){
            	calendar.set(Calendar.YEAR, ano + 1900);
            	dataString = calendar.getTime();
            }
    	}catch (Exception e) {
    		 return null;
		}
            return dataString;
    }
    
	public Date stringParaDataHora(String data){
            Date dataString = null;

            try{
	            Calendar calendar = Calendar.getInstance();
	
	            char[] dataArray = data.toCharArray();
	
	            int dia =Integer.parseInt(dataArray[0] + "" + dataArray[1]);
	            int mes = Integer.parseInt(dataArray[2] + "" + dataArray[3]);
	            int ano = Integer.parseInt(dataArray[4] + "" + dataArray[5]);
	            int hora = Integer.parseInt(dataArray[6] + "" + dataArray[7]);
	            int minuto = Integer.parseInt(dataArray[8] + "" + dataArray[9]);
	            int segundo = Integer.parseInt(dataArray[10] + "" + dataArray[11]);

	            calendar.set(Calendar.DAY_OF_MONTH, dia);
	            calendar.set(Calendar.MONTH, mes -1);
	            calendar.set(Calendar.YEAR, ano + 2000);
	            calendar.set(Calendar.HOUR_OF_DAY,hora);
	            calendar.set(Calendar.MINUTE,minuto);
	            calendar.set(Calendar.SECOND,segundo);
	                                  
	            dataString = calendar.getTime();
	            
	            if(calendar.get(Calendar.YEAR) > Util.getAno(new Date())){
	            	calendar.set(Calendar.YEAR, ano + 1900);
	            	dataString = calendar.getTime();
	            }
            }catch (Exception e) {
				return null;
			}

            return dataString;
    }
    
    public static String encrypt(String str) {
        int tab[] = {77,110,70,114,90,100,86,103,111,75};
        int i;
        int value = 0;
        int len = str.length();
        
        String response = "";
        for (i=0; i < len; i++) {
            value = (int) str.charAt(i);
            response += (char) tab[ (value - 48) ];
        }
        
        return response;
    }

	public Integer getCodigoAnormalidade() {
		return codigoAnormalidade;
	}

	public void setCodigoAnormalidade(Integer anormalidade) {
		this.codigoAnormalidade = anormalidade;
	}

	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer imovel) {
		this.matriculaImovel = imovel;
	}

	public Long getImei() {
		return imei;
	}

	public void setImei(Long imei) {
		this.imei = imei;
	}

	public Byte getIndicadorConfirmacaoLeitura() {
		return indicadorConfirmacaoLeitura;
	}

	public void setIndicadorConfirmacaoLeitura(Byte indicador) {
		this.indicadorConfirmacaoLeitura = indicador;
	}
	
	public Integer getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Integer codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getNumeroSequencialRota() {
		return numeroSequencialRota;
	}

	public void setNumeroSequencialRota(Integer numerosequencialRota) {
		this.numeroSequencialRota = numerosequencialRota;
	}

	public String getInscricao() {
		if(inscricao==null){
			char separator = '.';
	        StringBuffer buffer = new StringBuffer();
	        buffer.append(this.zerosEsquerda(this.getLocalidade(),3));
	        buffer.append(separator);
	        buffer.append(this.zerosEsquerda(this.getSetorComercial(),3));
	        buffer.append(separator);
	        buffer.append(this.zerosEsquerda(this.getNumeroQuadra(),3));
	        buffer.append(separator);
	        buffer.append(this.zerosEsquerda(this.getNumeroLote(),4));
	        buffer.append(separator);
	        buffer.append(this.zerosEsquerda(this.getNumeroSubLote(),3));
		    this.inscricao = buffer.toString(); 
	    }      
	   return this.inscricao;
	}
	
	private String zerosEsquerda(Integer valor, int tamanho){
         String retorno = new String(valor.toString());
         if(tamanho > retorno.length()){
             for(int i =0; i<(tamanho - valor.toString().length());i++ ){
                  retorno = "0" + retorno;
             }
         }
         return retorno;
  }

	public String getMsgImovelSuprimidoOuHidrometroRetirado() {
		return msgImovelSuprimidoOuHidrometroRetirado;
	}

	public void setMsgImovelSuprimidoOuHidrometroRetirado(
			String msgImovelSuprimidoOuHidrometroRetirado) {
		this.msgImovelSuprimidoOuHidrometroRetirado = msgImovelSuprimidoOuHidrometroRetirado;
	}

	public boolean isNaoPermitirAlterar() {
		return naoPermitirAlterar;
	}

	public void setNaoPermitirAlterar(boolean naoPermitirAlterar) {
		this.naoPermitirAlterar = naoPermitirAlterar;
	}

	public ArquivoTextoRoteiroEmpresa getArquivoTextoRoteiroEmpresa() {
		return arquivoTextoRoteiroEmpresa;
	}

	public void setArquivoTextoRoteiroEmpresa(
			ArquivoTextoRoteiroEmpresa arquivoTextoRoteiroEmpresa) {
		this.arquivoTextoRoteiroEmpresa = arquivoTextoRoteiroEmpresa;
	}

	public String getHoraLeituraCampo() {
		return horaLeituraCampo;
	}

	public void setHoraLeituraCampo(String horaLeituraCampo) {
		this.horaLeituraCampo = horaLeituraCampo;
	}

	public Date getDataLeituraCampo() {
		return dataLeituraCampo;
	}

	public void setDataLeituraCampo(Date dataLeituraCampo) {
		this.dataLeituraCampo = dataLeituraCampo;
	}

	public Date getDataLeituraCronograma() {
		return dataLeituraCronograma;
	}

	public void setDataLeituraCronograma(Date dataLeituraCronograma) {
		this.dataLeituraCronograma = dataLeituraCronograma;
	}
}