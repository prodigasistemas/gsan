
/**
 * ConsultaWebServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

    package gcom.integracao.webservice.spc;

    /**
     *  ConsultaWebServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ConsultaWebServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ConsultaWebServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ConsultaWebServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for detalharProduto method
            * override this method for handling normal response from detalharProduto operation
            */
           public void receiveResultdetalharProduto(
                    ConsultaWebServiceStub.ProdutoE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from detalharProduto operation
           */
            public void receiveErrordetalharProduto(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultar method
            * override this method for handling normal response from consultar operation
            */
           public void receiveResultconsultar(
                    ConsultaWebServiceStub.Resultado result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultar operation
           */
            public void receiveErrorconsultar(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for listarProdutos method
            * override this method for handling normal response from listarProdutos operation
            */
           public void receiveResultlistarProdutos(
                    ConsultaWebServiceStub.Produtos result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from listarProdutos operation
           */
            public void receiveErrorlistarProdutos(java.lang.Exception e) {
            }
                


    }
    