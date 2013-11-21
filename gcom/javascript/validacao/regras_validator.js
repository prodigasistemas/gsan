function validateMatricula(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oMatricula = new matricula();
                for (x in oMatricula) {
                    if ((form[oMatricula[x][0]].type == 'text' ||
                         form[oMatricula[x][0]].type == 'textarea') &&
                        (form[oMatricula[x][0]].value.length > 0)) {
                        if (!validacaoMatricula(form[oMatricula[x][0]].value)) {
                            if (i == 0) {
                                focusField = form[oMatricula[x][0]];
                            }
                            fields[i++] = oMatricula[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return bValid;
            }

	function validacaoMatricula(c){
		s = c;
		var c = s.substr(0,7);
		var dv = s.substr(7,1);
		var soma = 0;
		var resto = 0;
		var digito = 0;

		if (s.length != 8) {
                 return false;
                }
	        else {

		  soma = (c.charAt(0)* 2) + (c.charAt(1)* 7) + (c.charAt(2)* 6) + (c.charAt(3) * 5) + (c.charAt(4) * 4) + (c.charAt(5) * 3) + (c.charAt(6) * 2);
		  soma = soma * 10;
		  resto = (soma % 11);

		  if (resto == 10){
		    digito = 0;
		  }
		  else {
		    digito = resto;
		  }

		  if (dv != digito) {
		    return false;
		  }
		  else {
      		    return true;
		  }
	        }
	}
function validateInteger(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oInteger = new IntegerValidations();
                
                for (x in oInteger) {
                	var field = form[oInteger[x][0]];
                	
                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {

                        var value;

						if (field.type == "select-one") {
							var si = field.selectedIndex;
							value = field.options[si].value;
						} else {
							value = trim(field.value);
							field.value = value;
						}

                        if (value.length > 0) {

	                        var iValue = parseInt(value);
	                        if (isNaN(iValue) || !(iValue > 0 && iValue <= 2147483647)) {
	                            if (i == 0) {
	                                focusField = field;
	                            }
	                            fields[i++] = oInteger[x][1];
	                            bValid = false;
	                       }
                       }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }
            
       function trim(inputString) {
				   // Removes leading and trailing spaces from the passed string. Also removes
			   	   // consecutive spaces and replaces it with one space. If something besides
				   // a string is passed in (null, custom object, etc.) then return the input.

				   if (typeof inputString != "string") { return inputString; }
			   	      var retValue = inputString;
					var ch = retValue.substring(0, 1);
				   while (ch == " ") { // Check for spaces at the beginning of the string
					retValue = retValue.substring(1, retValue.length);
			        ch = retValue.substring(0, 1);
		   	   }

			   ch = retValue.substring(retValue.length-1, retValue.length);

			   while (ch == " ") { // Check for spaces at the end of the string
		      retValue = retValue.substring(0, retValue.length-1);
    	  	      ch = retValue.substring(retValue.length-1, retValue.length);
   	   	}

	   		while (retValue.indexOf("  ") != -1) { // Note that there are two spaces in the string - look for multiple spaces within the string
	      	retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length); // Again, there are two spaces in each of the strings
   	   }

	   	return retValue; // Return the trimmed string back to the user
	} // Ends the "trim" function
function validateRange(form) {
                return validateIntRange(form);
            }
function validateDecimalZeroPositivo(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oDecimalZeroPositivo = new DecimalZeroPositivoValidations();
                for (x in oDecimalZeroPositivo) {
                	var field = form[oDecimalZeroPositivo[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {

                    	var value;

                        if (field.type == "select-one") {
                                var si = field.selectedIndex;
                                value = field.options[si].value;
                        } else {
                                
                                value = field.value;
				value = value.replace(/\./g, '');
				value = value.replace(/,/g, '.');
                        }

						
                        if (value.length > 0) {

	                        var iValue = parseFloat(value);
	                    //    if (isNaN(iValue)) {
        			if (isNaN(value) || value < parseFloat(0)) {
	                            if (i == 0) {
	                                focusField = field;
	                            }
	                            fields[i++] = oDecimalZeroPositivo[x][1];
	                            
	                            bValid = false;
	                        }
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateLong(form) {
                        var bValid = true;
                        var focusField = null;
                        var i = 0;
                        var fields = new Array();
                        oInteger = new IntegerValidations();
                        for (x in oInteger) {
                                var field = form[oInteger[x][0]];

                            if (field.type == 'text' ||
                                field.type == 'textarea' ||
                                field.type == 'select-one' ||
                                field.type == 'radio') {

                                var value;

                                if (field.type == "select-one") {
                                        var si = field.selectedIndex;
                                        value = field.options[si].value;
                                } else {
                                        value = trim(field.value);
                                        field.value = value;
                                }

                                if (value.length > 0) {

                                        //var iValue = parseInt(value);
                                        if (isNaN(value) || value.indexOf('.') != -1 || !(value > 0 && value <= 9223372036854775807)) {   
                                        
                                            if (i == 0) {
                                                focusField = field;
                                            }
                                            fields[i++] = oInteger[x][1];
                                            bValid = false;
                                       }

                               }
                            }
                        }
                        if (fields.length > 0) {
                           focusField.focus();
                           alert(fields.join('\n'));
                        }
                        return bValid;
                    }
                    
       function trim(inputString) {
					   // Removes leading and trailing spaces from the passed string. Also removes
				   	   // consecutive spaces and replaces it with one space. If something besides
					   // a string is passed in (null, custom object, etc.) then return the input.

					   if (typeof inputString != "string") { return inputString; }
				   	      var retValue = inputString;
		      			  var ch = retValue.substring(0, 1);
						   while (ch == " ") { // Check for spaces at the beginning of the string
							retValue = retValue.substring(1, retValue.length);
					        ch = retValue.substring(0, 1);
				   	   }

					   ch = retValue.substring(retValue.length-1, retValue.length);

					   while (ch == " ") { // Check for spaces at the end of the string
					      retValue = retValue.substring(0, retValue.length-1);
		    	  	      ch = retValue.substring(retValue.length-1, retValue.length);
	   	   	}

	   		while (retValue.indexOf("  ") != -1) { // Note that there are two spaces in the string - look for multiple spaces within the string
	      	retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length); // Again, there are two spaces in each of the strings
   	   }

	   	return retValue; // Return the trimmed string back to the user
	} // Ends the "trim" function
function validateShort(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oShort = new ShortValidations();
                for (x in oShort) {
                	var field = form[oShort[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {

                        var value;

						if (field.type == "select-one") {
							var si = field.selectedIndex;
							value = field.options[si].value;
						} else {
							value = field.value;
						}

                        if (value.length > 0) {

	                        var iValue = parseInt(value);
	                        if (isNaN(iValue) || !(iValue >= 0 && iValue <= 32767)) {
	                            if (i == 0) {
	                                focusField = field;
	                            }
	                            fields[i++] = oShort[x][1];
	                            bValid = false;
	                       }
                       }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateInteiroZeroPositivo(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oInteger = new InteiroZeroPositivoValidations();
                for (x in oInteger) {
                	var field = form[oInteger[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {

                        var value;

						if (field.type == "select-one") {
							var si = field.selectedIndex;
							value = field.options[si].value;
						} else {
							value = trim(field.value);
							field.value = value;
						}

                        if (value.length > 0) {

	                        var iValue = parseInt(value);
	                        if (isNaN(iValue) || !(iValue >= 0 && iValue <= 2147483647)) {
	                            if (i == 0) {
	                                focusField = field;
	                            }
	                            
	                            fields[i++] = oInteger[x][1];
	                            bValid = false;
	                       }
                       }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }
            
       function trim(inputString) {
				   // Removes leading and trailing spaces from the passed string. Also removes
			   	   // consecutive spaces and replaces it with one space. If something besides
				   // a string is passed in (null, custom object, etc.) then return the input.

				   if (typeof inputString != "string") { return inputString; }
			   	      var retValue = inputString;
					var ch = retValue.substring(0, 1);
				   while (ch == " ") { // Check for spaces at the beginning of the string
					retValue = retValue.substring(1, retValue.length);
			        ch = retValue.substring(0, 1);
		   	   }

			   ch = retValue.substring(retValue.length-1, retValue.length);

			   while (ch == " ") { // Check for spaces at the end of the string
		      retValue = retValue.substring(0, retValue.length-1);
    	  	      ch = retValue.substring(retValue.length-1, retValue.length);
   	   	}

	   		while (retValue.indexOf("  ") != -1) { // Note that there are two spaces in the string - look for multiple spaces within the string
	      	retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length); // Again, there are two spaces in each of the strings
   	   }

	   	return retValue; // Return the trimmed string back to the user
	} // Ends the "trim" function
function validateRadioRequired(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oRadioRequired = new RadioRequiredValidations();
                for (x in oRadioRequired) {

                   var field = form[oRadioRequired[x][0]];
				   var indice;
				   var selecionado = false;

				   for(indice = 0; indice < form.elements.length; indice++){

					  if (form.elements[indice].type == 'radio' && form.elements[indice].checked == true
					  && form.elements[indice].name == oRadioRequired[x][0]) {
					    	selecionado = true;
					    	indice = form.elements.length;
					  }
				   }    
				   if(!selecionado){
			           if (i == 0) {
                              focusField = field;
                          }
                          fields[i++] = oRadioRequired[x][1];
                          bValid = false;					    		
				   }	
				}// fim do for
                if (fields.length > 0) {
                    //focusField.focus();
                    alert(fields.join('\n'));
                }
                return bValid;
            }
function validateMinLength(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oMinLength = new minlength();
                for (x in oMinLength) {
                    if (form[oMinLength[x][0]].type == 'text' ||
                        form[oMinLength[x][0]].type == 'textarea') {
                        var iMin = parseInt(oMinLength[x][2]("minlength"));
                        if (form[oMinLength[x][0]].value.length < iMin) {
                            if (i == 0) {
                                focusField = form[oMinLength[x][0]];
                            }
                            fields[i++] = oMinLength[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateHoraMinuto(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oHoraMinuto = new HoraMinutoValidations();
                for (x in oHoraMinuto) {
                	var field = form[oHoraMinuto[x][0]];
                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {
					
                        var value;

						if (field.type == "select-one") {
							var si = field.selectedIndex;
							value = field.options[si].value;
						} else {
							value = trim(field.value);
							field.value = value;
						}	
						if (field.value.length > 0) {
							if (horaMinuto != "") {
						    	var horaMinuto = field.value.split(':');
						    	if (horaMinuto.length == 2) {
						      		if(!(horaMinuto[0] <= 23 && horaMinuto[0] >= 0 && horaMinuto[1] <= 59 && horaMinuto[1] >= 0)){
			                            if (i == 0) {
			                                focusField = field;
			                            }
			                            
			                            fields[i++] = oHoraMinuto[x][1];
			                            bValid = false;					        		
						      		}
								}else {
		                            if (i == 0) {
		                                focusField = field;
		                            }
		                            
		                            fields[i++] = oHoraMinuto[x][1];
		                            bValid = false;					        		
						    	}
						    } else {
	                            if (i == 0) {
	                                focusField = field;
	                            }
	                            
	                            fields[i++] = oHoraMinuto[x][1];
	                            bValid = false;					        		
						    }//fim do if (horaMinuto != "") 
						}//fim do if (field.length > 0) 
					}//fim if do tipo de campo
				    
				}//fim do for
				if (fields.length > 0) {
                	focusField.focus();

	                alert(fields.join('\n'));
    	        }
    	        
            	return bValid;				    
            }
function validateDate(form) {
               var bValid = true;
               var focusField = null;
               var i = 0;
               var fields = new Array();
               oDate = new DateValidations();
               for (x in oDate) {
                   var value = form[oDate[x][0]].value;
                   var datePattern = oDate[x][2]("datePattern");
                   if ((form[oDate[x][0]].type == 'text' ||
                        form[oDate[x][0]].type == 'textarea') &&
                       (value.length > 0) &&
                       (datePattern.length > 0)) {
                     var MONTH = "MM";
                     var DAY = "dd";
                     var YEAR = "yyyy";
                     var orderMonth = datePattern.indexOf(MONTH);
                     var orderDay = datePattern.indexOf(DAY);
                     var orderYear = datePattern.indexOf(YEAR);
                     if ((orderDay < orderYear && orderDay > orderMonth)) {
                         var iDelim1 = orderMonth + MONTH.length;
                         var iDelim2 = orderDay + DAY.length;
                         var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
                         var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
                         if (iDelim1 == orderDay && iDelim2 == orderYear) {
                            dateRegexp = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                         } else if (iDelim1 == orderDay) {
                            dateRegexp = new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$");
                         } else if (iDelim2 == orderYear) {
                            dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$");
                         } else {
                            dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$");
                         }
                         var matched = dateRegexp.exec(value);
                         if(matched != null) {
                            if (!isValidDate(matched[2], matched[1], matched[3])) {
                               if (i == 0) {
                                   focusField = form[oDate[x][0]];
                               }
                               fields[i++] = oDate[x][1];
                               bValid =  false;
                            }
                         } else {
                            if (i == 0) {
                                focusField = form[oDate[x][0]];
                            }
                            fields[i++] = oDate[x][1];
                            bValid =  false;
                         }
                     } else if ((orderMonth < orderYear && orderMonth > orderDay)) {
                         var iDelim1 = orderDay + DAY.length;
                         var iDelim2 = orderMonth + MONTH.length;
                         var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
                         var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
                         if (iDelim1 == orderMonth && iDelim2 == orderYear) {
                             dateRegexp = new RegExp("^(\\d{2})(\\d{2})(\\d{4})$");
                         } else if (iDelim1 == orderMonth) {
                             dateRegexp = new RegExp("^(\\d{2})(\\d{2})[" + delim2 + "](\\d{4})$");
                         } else if (iDelim2 == orderYear) {
                             dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})(\\d{4})$");
                         } else {
                             dateRegexp = new RegExp("^(\\d{2})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{4})$");
                         }
                         var matched = dateRegexp.exec(value);
                         if(matched != null) {
                             if (!isValidDate(matched[1], matched[2], matched[3])) {
                                 if (i == 0) {
                                     focusField = form[oDate[x][0]];
                                 }
                                 fields[i++] = oDate[x][1];
                                 bValid =  false;
                              }
                         } else {
                             if (i == 0) {
                                 focusField = form[oDate[x][0]];
                             }
                             fields[i++] = oDate[x][1];
                             bValid =  false;
                         }
                     } else if ((orderMonth > orderYear && orderMonth < orderDay)) {
                         var iDelim1 = orderYear + YEAR.length;
                         var iDelim2 = orderMonth + MONTH.length;
                         var delim1 = datePattern.substring(iDelim1, iDelim1 + 1);
                         var delim2 = datePattern.substring(iDelim2, iDelim2 + 1);
                         if (iDelim1 == orderMonth && iDelim2 == orderDay) {
                             dateRegexp = new RegExp("^(\\d{4})(\\d{2})(\\d{2})$");
                         } else if (iDelim1 == orderMonth) {
                             dateRegexp = new RegExp("^(\\d{4})(\\d{2})[" + delim2 + "](\\d{2})$");
                         } else if (iDelim2 == orderDay) {
                             dateRegexp = new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})(\\d{2})$");
                         } else {
                             dateRegexp = new RegExp("^(\\d{4})[" + delim1 + "](\\d{2})[" + delim2 + "](\\d{2})$");
                         }
                         var matched = dateRegexp.exec(value);
                         if(matched != null) {
                             if (!isValidDate(matched[3], matched[2], matched[1])) {
                                 if (i == 0) {
                                     focusField = form[oDate[x][0]];
                                  }
                                  fields[i++] = oDate[x][1];
                                  bValid =  false;
                              }
                          } else {
                              if (i == 0) {
                                  focusField = form[oDate[x][0]];
                              }
                              fields[i++] = oDate[x][1];
                              bValid =  false;
                          }
                     } else {
                         if (i == 0) {
                             focusField = form[oDate[x][0]];
                         }
                         fields[i++] = oDate[x][1];
                         bValid =  false;
                     }
                  }
               }
               if (fields.length > 0) {
                  focusField.focus();
                  alert(fields.join('\n'));
               }
               return bValid;
            }

	    function isValidDate(day, month, year) {
	        if (month < 1 || month > 12) {
                    return false;
                }
                if (day < 1 || day > 31) {
                    return false;
                }
                if ((month == 4 || month == 6 || month == 9 || month == 11) &&
                    (day == 31)) {
                    return false;
                }
                if (month == 2) {
                    var leap = (year % 4 == 0 &&
                               (year % 100 != 0 || year % 400 == 0));
                    if (day>29 || (day == 29 && !leap)) {
                        return false;
                    }
                }
                return true;
            }
function validateMask(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oMasked = new mask();
                for (x in oMasked) {
                    if ((form[oMasked[x][0]].type == 'text' ||
                         form[oMasked[x][0]].type == 'textarea' ||
                         form[oMasked[x][0]].type == 'password') &&
                        (form[oMasked[x][0]].value.length > 0)) {
                        if (!matchPattern(form[oMasked[x][0]].value, oMasked[x][2]("mask"))) {
                            if (i == 0) {
                                focusField = form[oMasked[x][0]];
                            }
                            fields[i++] = oMasked[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }

            function matchPattern(value, mask) {
               var bMatched = mask.exec(value);
               if (!bMatched) {
                   return false;
               }
               return true;
            }
function validateCaracterEspecial(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oCaracterEspecial = new caracteresespeciais();
                for (x in oCaracterEspecial) {
                    if ((form[oCaracterEspecial[x][0]].type == 'text' ||
                         form[oCaracterEspecial[x][0]].type == 'textarea' ||
                         form[oCaracterEspecial[x][0]].type == 'password') &&
                        (form[oCaracterEspecial[x][0]].value.length > 0)) {
                        if (validacaoCaracterEspecial(form[oCaracterEspecial[x][0]].value)) {
                            if (i == 0) {
                                focusField = form[oCaracterEspecial[x][0]];
                            }
                            fields[i++] = oCaracterEspecial[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return bValid;
            }

    	function validacaoCaracterEspecial(c){
        	var achou = false;

		var indesejaveis = "~{}^%$[]@|`\\<¨\#?!;*>\"\'+()&¢¬=¡²³¤€¼½¾‘’¥×áßð";

		for (var i=0; i<indesejaveis.length; i++) {
			if ((c.indexOf(indesejaveis.charAt(i))) != -1 ){
				achou = true;
			}
      	}

		return achou;
	}
function validateCreditCard(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oCreditCard = new creditCard();
                for (x in oCreditCard) {
                    if ((form[oCreditCard[x][0]].type == 'text' ||
                         form[oCreditCard[x][0]].type == 'textarea') &&
                        (form[oCreditCard[x][0]].value.length > 0)) {
                        if (!luhnCheck(form[oCreditCard[x][0]].value)) {
                            if (i == 0) {
                                focusField = form[oCreditCard[x][0]];
                            }
                            fields[i++] = oCreditCard[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return bValid;
            }

            /**
             * Reference: http://www.ling.nwu.edu/~sburke/pub/luhn_lib.pl
             */
            function luhnCheck(cardNumber) {
                if (isLuhnNum(cardNumber)) {
                    var no_digit = cardNumber.length;
                    var oddoeven = no_digit & 1;
                    var sum = 0;
                    for (var count = 0; count < no_digit; count++) {
                        var digit = parseInt(cardNumber.charAt(count));
                        if (!((count & 1) ^ oddoeven)) {
                            digit *= 2;
                            if (digit > 9) digit -= 9;
                        };
                        sum += digit;
                    };
                    if (sum == 0) return false;
                    if (sum % 10 == 0) return true;
                };
                return false;
            }

            function isLuhnNum(argvalue) {
                argvalue = argvalue.toString();
                if (argvalue.length == 0) {
                    return false;
                }
                for (var n = 0; n < argvalue.length; n++) {
                    if ((argvalue.substring(n, n+1) < "0") ||
                        (argvalue.substring(n,n+1) > "9")) {
                        return false;
                    }
                }
                return true;
            }
function validateCnpj(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oCnpj = new cnpj();
                for (x in oCnpj) {
                    if ((form[oCnpj[x][0]].type == 'text' ||
                         form[oCnpj[x][0]].type == 'textarea') &&
                        (form[oCnpj[x][0]].value.length > 0)) {
                        if (!validacaoCnpj(form[oCnpj[x][0]].value)) {
                            if (i == 0) {
                                focusField = form[oCnpj[x][0]];
                            }
                            fields[i++] = oCnpj[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return bValid;
            }


	function validacaoCnpj(s){
		var i;
		var c = s.substr(0,12);
		var dv = s.substr(12,2);
		var d1 = 0;
		for (i = 0; i < 12; i++)
		{
			d1 += c.charAt(11-i)*(2+(i % 8));
		}
        	if (d1 == 0) return false;
        	d1 = 11 - (d1 % 11);
		if (d1 > 9) d1 = 0;
		if (dv.charAt(0) != d1)
      		{
		return false;
		}

		d1 *= 2;
		for (i = 0; i < 12; i++)
		{
			d1 += c.charAt(11-i)*(2+((i+1) % 8));
		}
		d1 = 11 - (d1 % 11);
		if (d1 > 9) d1 = 0;
		if (dv.charAt(1) != d1)
		{
			return false;
		}
		return true;
	}
function validateRequired(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oRequired = new required();
                for (x in oRequired) {
                	var field = form[oRequired[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio' ||
                        field.type == 'password' ||
			field.type == 'hidden') {

                        var value;

                      if (field.type == "select-one") {
                              var si = field.selectedIndex;
                              value = field.options[si].value;
							  if (value == -1) {
							    focusField = field;
								fields[i++] = oRequired[x][1];
		                        bValid = false;
															  	
							  }	
								                              
                              //value = field.value;
                      } else {
                      		
                              value = field.value;
                              //if (value == -1) {
							   // focusField = field;
								//fields[i++] = oRequired[x][1];
		                        //bValid = false;
															  	
							  //}	
                              
                      }

                        if (trim(value) == '') {

	                        if (i == 0) {
	                            focusField = field;
	                        }
	                        fields[i++] = oRequired[x][1];
	                        bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
		  if (focusField.type != 'hidden') {
                   focusField.focus();
		  }
                   alert(fields.join('\n'));
                }
                return bValid;
}

	function trim(inputString) {
	   // Removes leading and trailing spaces from the passed string. Also removes
   	   // consecutive spaces and replaces it with one space. If something besides
	   // a string is passed in (null, custom object, etc.) then return the input.

	   if (typeof inputString != "string") { return inputString; }
   		var retValue = inputString;
		var ch = retValue.substring(0, 1);
	   while (ch == " ") { // Check for spaces at the beginning of the string
		retValue = retValue.substring(1, retValue.length);
	        ch = retValue.substring(0, 1);
   	   }

	   ch = retValue.substring(retValue.length-1, retValue.length);

	   while (ch == " ") { // Check for spaces at the end of the string
	      retValue = retValue.substring(0, retValue.length-1);
      	      ch = retValue.substring(retValue.length-1, retValue.length);
   	   }

	   while (retValue.indexOf("  ") != -1) { // Note that there are two spaces in the string - look for multiple spaces within the string
	      retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length); // Again, there are two spaces in each of the strings
   	   }

	   return retValue; // Return the trimmed string back to the user
	} // Ends the "trim" function
function validateBigInteger(form) {
                        var bValid = true;
                        var focusField = null;
                        var i = 0;
                        var fields = new Array();
                        oBigInteger = new BigIntegerValidations();
                        for (x in oBigInteger) {
                                var field = form[oBigInteger[x][0]];

                            if (field.type == 'text' ||
                                field.type == 'textarea' ||
                                field.type == 'select-one' ||
                                field.type == 'radio') {

                                var value;

                                if (field.type == "select-one") {
                                        var si = field.selectedIndex;
                                        value = field.options[si].value;
                                } else {
                                        value = trim(field.value);
                                        field.value = value;
                                }

                                if (value.length > 0) {

                                        //var iValue = parseInt(value);
                                        if (isNaN(value) || value.indexOf('.') != -1 || !(value > 0 && value <= 99999999999999999999)) {   
                                        
                                            if (i == 0) {
                                                focusField = field;
                                            }
                                            fields[i++] = oBigInteger[x][1];
                                            bValid = false;
                                       }

                               }
                            }
                        }
                        if (fields.length > 0) {
                           focusField.focus();
                           alert(fields.join('\n'));
                        }
                        return bValid;
                    }
                    
       function trim(inputString) {
					   // Removes leading and trailing spaces from the passed string. Also removes
				   	   // consecutive spaces and replaces it with one space. If something besides
					   // a string is passed in (null, custom object, etc.) then return the input.

					   if (typeof inputString != "string") { return inputString; }
				   	      var retValue = inputString;
		      			  var ch = retValue.substring(0, 1);
						   while (ch == " ") { // Check for spaces at the beginning of the string
							retValue = retValue.substring(1, retValue.length);
					        ch = retValue.substring(0, 1);
				   	   }

					   ch = retValue.substring(retValue.length-1, retValue.length);

					   while (ch == " ") { // Check for spaces at the end of the string
					      retValue = retValue.substring(0, retValue.length-1);
		    	  	      ch = retValue.substring(retValue.length-1, retValue.length);
	   	   	}

	   		while (retValue.indexOf("  ") != -1) { // Note that there are two spaces in the string - look for multiple spaces within the string
	      	retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length); // Again, there are two spaces in each of the strings
   	   }

	   	return retValue; // Return the trimmed string back to the user
	} // Ends the "trim" function
function validateFloat(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oFloat = new FloatValidations();
                for (x in oFloat) {
                	var field = form[oFloat[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {

                    	var value;

                        if (field.type == "select-one") {
                                var si = field.selectedIndex;
                                value = field.options[si].value;
                             
                        } else {
                                value = field.replace(',', '.');
                                value = trim(value);
                                field.value = value;
                        }

                        if (value.length > 0) {

	                        var iValue = parseFloat(value);
	                        if (isNaN(iValue)) {
	                            if (i == 0) {
	                                focusField = field;
	                            }
	                            fields[i++] = oFloat[x][1];
	                            bValid = false;
	                        }
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }
            
                   function trim(inputString) {
					   // Removes leading and trailing spaces from the passed string. Also removes
				   	   // consecutive spaces and replaces it with one space. If something besides
					   // a string is passed in (null, custom object, etc.) then return the input.

					   if (typeof inputString != "string") { return inputString; }
				   	      var retValue = inputString;
		      			  var ch = retValue.substring(0, 1);
						   while (ch == " ") { // Check for spaces at the beginning of the string
							retValue = retValue.substring(1, retValue.length);
					        ch = retValue.substring(0, 1);
				   	   }

					   ch = retValue.substring(retValue.length-1, retValue.length);

					   while (ch == " ") { // Check for spaces at the end of the string
					      retValue = retValue.substring(0, retValue.length-1);
		    	  	      ch = retValue.substring(retValue.length-1, retValue.length);
	   	   	}

	   		while (retValue.indexOf("  ") != -1) { // Note that there are two spaces in the string - look for multiple spaces within the string
	      	retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length); // Again, there are two spaces in each of the strings
   	   }

	   	return retValue; // Return the trimmed string back to the user
	} // Ends the "trim" function
function validateEmail(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oEmail = new email();
                for (x in oEmail) {
                    if ((form[oEmail[x][0]].type == 'text' ||
                         form[oEmail[x][0]].type == 'textarea') &&
                        (form[oEmail[x][0]].value.length > 0)) {
                        if (!checkEmail(form[oEmail[x][0]].value)) {
                            if (i == 0) {
                                focusField = form[oEmail[x][0]];
                            }
                            fields[i++] = oEmail[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return bValid;
            }

            /**
             * Reference: Sandeep V. Tamhankar (stamhankar@hotmail.com),
             * http://javascript.internet.com
             */
            function checkEmail(emailStr) {
               if (emailStr.length == 0) {
                   return true;
               }
               var emailPat=/^(.+)@(.+)$/;
               var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
               var validChars="\[^\\s" + specialChars + "\]";
               var quotedUser="(\"[^\"]*\")";
               var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
               var atom=validChars + '+';
               var word="(" + atom + "|" + quotedUser + ")";
               var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
               var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
               var matchArray=emailStr.match(emailPat);
               if (matchArray == null) {
                   return false;
               }
               var user=matchArray[1];
               var domain=matchArray[2];
               if (user.match(userPat) == null) {
                   return false;
               }
               var IPArray = domain.match(ipDomainPat);
               if (IPArray != null) {
                   for (var i = 1; i <= 4; i++) {
                      if (IPArray[i] > 255) {
                         return false;
                      }
                   }
                   return true;
               }
               var domainArray=domain.match(domainPat);
               if (domainArray == null) {
                   return false;
               }
               var atomPat=new RegExp(atom,"g");
               var domArr=domain.match(atomPat);
               var len=domArr.length;
               if ((domArr[domArr.length-1].length < 2) ||
                   (domArr[domArr.length-1].length > 3)) {
                   return false;
               }
               if (len < 2) {
                   return false;
               }
               return true;
            }
function validateDecimalNegativoZeroPositivo(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oDecimalNegativoZeroPositivo = new DecimalNegativoZeroPositivoValidations();
                
                for (x in oDecimalNegativoZeroPositivo) {
                	var field = form[oDecimalNegativoZeroPositivo[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {

                    	var value;

                        if (field.type == "select-one") {
                                var si = field.selectedIndex;
                                value = field.options[si].value;
                        } else {
                                
                                value = field.value;
								value = value.replace(/\./g, '');
								value = value.replace(/,/g, '.');
                        }

						
                        if (value.length > 0) {

	                        var iValue = parseFloat(value);
        					if (isNaN(value)) {
	                            if (i == 0) {
	                                focusField = field;
	                            }
	                            fields[i++] = oDecimalNegativoZeroPositivo[x][1];
	                            
	                            bValid = false;
	                        }
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateInteiroNegativoZeroPositivo(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oInteger = new InteiroNegativoZeroPositivoValidations();
                for (x in oInteger) {
                	var field = form[oInteger[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {

                        var value;

						if (field.type == "select-one") {
							var si = field.selectedIndex;
							value = field.options[si].value;
						} else {
							value = trim(field.value);
							field.value = value;
						}

                        if (value.length > 0) {

	                        var iValue = parseInt(value);
	                        if (isNaN(iValue) || !(iValue >=-2147483647  && iValue <= 2147483647)) {
	                            if (i == 0) {
	                                focusField = field;
	                            }
	                            
	                            fields[i++] = oInteger[x][1];
	                            bValid = false;
	                       }
                       }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }
            
       function trim(inputString) {
				   // Removes leading and trailing spaces from the passed string. Also removes
			   	   // consecutive spaces and replaces it with one space. If something besides
				   // a string is passed in (null, custom object, etc.) then return the input.

				   if (typeof inputString != "string") { return inputString; }
			   	      var retValue = inputString;
					var ch = retValue.substring(0, 1);
				   while (ch == " ") { // Check for spaces at the beginning of the string
					retValue = retValue.substring(1, retValue.length);
			        ch = retValue.substring(0, 1);
		   	   }

			   ch = retValue.substring(retValue.length-1, retValue.length);

			   while (ch == " ") { // Check for spaces at the end of the string
		      retValue = retValue.substring(0, retValue.length-1);
    	  	      ch = retValue.substring(retValue.length-1, retValue.length);
   	   	}

	   		while (retValue.indexOf("  ") != -1) { // Note that there are two spaces in the string - look for multiple spaces within the string
	      	retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length); // Again, there are two spaces in each of the strings
   	   }

	   	return retValue; // Return the trimmed string back to the user
	} // Ends the "trim" function
function validateCpf(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oCpf = new cpf();
                for (x in oCpf) {
                    if ((form[oCpf[x][0]].type == 'text' ||
                         form[oCpf[x][0]].type == 'textarea') &&
                        (form[oCpf[x][0]].value.length > 0)) {
                        if (!validacaoCpf(form[oCpf[x][0]].value)) {
                            if (i == 0) {
                                focusField = form[oCpf[x][0]];
                            }
                            fields[i++] = oCpf[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return bValid;
            }

	function validacaoCpf(c){
		var i;
		s = c;
		var c = s.substr(0,9);
		var dv = s.substr(9,2);
		var d1 = 0;
		var v = false;
		for (i = 0; i < 9; i++) {
			d1 += c.charAt(i)*(10-i);
		}
		if (d1 == 0){
		v = true;
		return false;
		}
		d1 = 11 - (d1 % 11);
		if (d1 > 9) d1 = 0;
		if (dv.charAt(0) != d1) {
		v = true;
		return false;
		}

		d1 *= 2;
		for (i = 0; i < 9; i++) {
			d1 += c.charAt(i)*(11-i);
		}
		d1 = 11 - (d1 % 11);
		if (d1 > 9) d1 = 0;
		if (dv.charAt(1) != d1) {
			v = true;
		return false;
		}
		if (!v) {
			return true;
		}

	}
function validateNumeroSelo(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oNumeroSelo = new caracteresespeciais();
                for (x in oNumeroSelo) {
                    if ((form[oNumeroSelo[x][0]].type == 'text' ||
                         form[oNumeroSelo[x][0]].type == 'textarea' ||
                         form[oNumeroSelo[x][0]].type == 'password') &&
                        (form[oNumeroSelo[x][0]].value.length > 0)) {
                        if (validacaoNumeroSelo(form[oNumeroSelo[x][0]].value)) {
                            if (i == 0) {
                                focusField = form[oNumeroSelo[x][0]];
                            }
                            fields[i++] = oNumeroSelo[x][1];
                            bValid = false;
                        }
                    }
                }

                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return bValid;
            }

	    	function validacaoNumeroSelo(value){
	        	var achou = false;
	
                if (value.length > 0) {

                 	if (isNaN(value)) {
		               	var iValue = parseInt(value);                 		
		               	if(iValue == 0){
		               		achou = true;
		               	}
                    }else{
                    	achou = false;
                    }
                }
				return achou;
			}
function validateDecimal(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oFloat = new FloatValidations();
                for (x in oFloat) {
                	var field = form[oFloat[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {

                    	var value;

                        if (field.type == "select-one") {
                                var si = field.selectedIndex;
                                value = field.options[si].value;
                        } else {
                                
                                value = field.value;
								value = value.replace(/\./g, '');
								value = value.replace(/,/g, '.');
                        }

                        if (value.length > 0) {

	                        var iValue = parseFloat(value);
	                    //    if (isNaN(iValue)) {
        				if (isNaN(value) || value <= parseFloat(0)) {
	                            if (i == 0) {
	                                focusField = field;
	                            }
	                            fields[i++] = oFloat[x][1];
	                            bValid = false;
	                        }
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateDecimalComZero(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oFloat = new FloatValidations();
                for (x in oFloat) {
                	var field = form[oFloat[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {

                    	var value;

                        if (field.type == "select-one") {
                                var si = field.selectedIndex;
                                value = field.options[si].value;
                        } else {
                                
                                value = field.value;
								value = value.replace(/\./g, '');
								value = value.replace(/,/g, '.');
                        }

                        if (value.length > 0) {

	                        var iValue = parseFloat(value);
	                        if (isNaN(iValue)) {
        				//    if (isNaN(value) || value <= parseFloat(0)) {
	                            if (i == 0) {
	                                focusField = field;
	                            }
	                            fields[i++] = oFloat[x][1];
	                            bValid = false;
	                        }
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
}            
function validateIntRange(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oRange = new intRange();
                for (x in oRange) {
                    if ((form[oRange[x][0]].type == 'text' ||
                         form[oRange[x][0]].type == 'textarea') &&
                        (form[oRange[x][0]].value.length > 0)) {
                        var iMin = parseInt(oRange[x][2]("min"));
                        var iMax = parseInt(oRange[x][2]("max"));
                        var iValue = parseInt(form[oRange[x][0]].value);
                        if (!(iValue >= iMin && iValue <= iMax)) {
                            if (i == 0) {
                                focusField = form[oRange[x][0]];
                            }
                            fields[i++] = oRange[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return bValid;
            }
function validateMaxLength(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oMaxLength = new maxlength();
                for (x in oMaxLength) {
                    if (form[oMaxLength[x][0]].type == 'text' ||
                        form[oMaxLength[x][0]].type == 'textarea') {
                        var iMax = parseInt(oMaxLength[x][2]("maxlength"));
                        if (form[oMaxLength[x][0]].value.length > iMax) {
                            if (i == 0) {
                                focusField = form[oMaxLength[x][0]];
                            }
                            fields[i++] = oMaxLength[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateByte(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oByte = new ByteValidations();
                for (x in oByte) {
                	var field = form[oByte[x][0]];

                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
						field.type == 'radio') {

						var value;

						if (field.type == "select-one") {
							var si = field.selectedIndex;
							value = field.options[si].value;
						} else {
							value = field.value;
						}

                        if (value.length > 0) {

	                        var iValue = parseInt(value);
	                        if (isNaN(iValue) || !(iValue >= 0 && iValue <= 127)) {
	                            if (i == 0) {
	                                focusField = field;
	                            }
	                            fields[i++] = oByte[x][1];
	                            bValid = false;
	                        }
						}

                    }
                }
                if (fields.length > 0) {
                   focusField.focus();
                   alert(fields.join('\n'));
                }
                return bValid;
            }
function validateMesAno(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oMesAno = new MesAnoValidations();
                for (x in oMesAno) {
                	var field = form[oMesAno[x][0]];
                    if (field.type == 'text' ||
                        field.type == 'textarea' ||
                        field.type == 'select-one' ||
                        field.type == 'radio') {
					
                        var value;

						if (field.type == "select-one") {
							var si = field.selectedIndex;
							value = field.options[si].value;
						} else {
							value = trim(field.value);
							field.value = value;
						}	
						if (field.value.length > 0) {

							if (field.value.length == 7) {
								dia = 1;
						    	mes = value.substring(0,2); 
						    	ano = value.substring(3,7); 
						
						    	if ((!isNaN(mes) || !isNaN(ano)) && (mes.indexOf('.') == -1 && mes.indexOf(',') == -1 && mes.indexOf('/') == -1) &&
									(ano.indexOf('.') == -1 && ano.indexOf(',') == -1 && ano.indexOf('/') == -1)) {
						    	
						    		// verifica se o mes e valido 
							    	if ((mes * 1) >= 1 && (mes * 1) <= 12 ) { 
							        
							        	// verifica se o ano e valido
							        	if ((ano * 1) != 0 && (ano * 1) >= 1980) {
							        	
							        		// verifica se e ano bissexto 
							    			if ((mes * 1) == 2 && ((dia * 1) < 1 || (dia * 1) > 29 
							    				|| ((dia * 1) > 28 && (((ano * 1) / 4) != (ano * 1) / 4)))) { 
							       				 
					                            if (i == 0) {
					                                focusField = field;
					                            }
					                            
					                            fields[i++] = oMesAno[x][1];
					                            bValid = false;					       				
							    			}
							        	}else{
				                            if (i == 0) {
				                                focusField = field;
				                            }
				                            
				                            fields[i++] = oMesAno[x][1];
				                            bValid = false;					        		
							        	}
							    	}else{
			                            if (i == 0) {
			                                focusField = field;
			                            }
			                            
			                            fields[i++] = oMesAno[x][1];
			                            bValid = false;					    		
							    	} 
								}else{
		                            if (i == 0) {
		                                focusField = field;
		                            }
		                            
		                            fields[i++] = oMesAno[x][1];
		                            bValid = false;							
								}
						    }else{
		                        if (i == 0) {
		                            focusField = field;
		                        }
		                        
		                        fields[i++] = oMesAno[x][1];
		                        bValid = false;				    	
						    }//fim do if (field.length == 7) 
					    }//fim do if (field.length > 0) 
					}//fim if do tipo de campo
				    
				}//fim do for
				if (fields.length > 0) {
                	focusField.focus();

	                alert(fields.join('\n'));
    	        }
    	        
            	return bValid;				    
            }
            
	       function trim(inputString) {
					   // Removes leading and trailing spaces from the passed string. Also removes
				   	   // consecutive spaces and replaces it with one space. If something besides
					   // a string is passed in (null, custom object, etc.) then return the input.
	
					   if (typeof inputString != "string") { return inputString; }
				   	      var retValue = inputString;
						var ch = retValue.substring(0, 1);
					   while (ch == " ") { // Check for spaces at the beginning of the string
						retValue = retValue.substring(1, retValue.length);
				        ch = retValue.substring(0, 1);
			   	   }
	
				   ch = retValue.substring(retValue.length-1, retValue.length);
	
				   while (ch == " ") { // Check for spaces at the end of the string
			      retValue = retValue.substring(0, retValue.length-1);
	    	  	      ch = retValue.substring(retValue.length-1, retValue.length);
	   	   	}
	
		   		while (retValue.indexOf("  ") != -1) { // Note that there are two spaces in the string - look for multiple spaces within the string
		      	retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length); // Again, there are two spaces in each of the strings
	   	   }
	
		   	return retValue; // Return the trimmed string back to the user
		} // Ends the "trim" function
function validateCheckRequired(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oCheckRequired = new CheckRequiredValidations();
                for (x in oCheckRequired) {

                	var field = form[oCheckRequired[x][0]];
					var indice;
					   
				    var selecionado = false;
				    for(indice = 0; indice < form.elements.length; indice++){
					   if (form.elements[indice].type == 'checkbox' && form.elements[indice].checked == true 
					   && form.elements[indice].name == oCheckRequired[x][0]) {

					    	selecionado = true;
					    	indice = form.elements.length;
					   }
				    }    
				    if(!selecionado){
                         if (i == 0) {
                             focusField = field;
                         }
                         
                         fields[i++] = oCheckRequired[x][1];
                         bValid = false;					    		
				    }	
				}// fim do for
				if (fields.length > 0) {
                	//focusField.focus();

	                alert(fields.join('\n'));
    	        }
    	        
            	return bValid;				    
            }
function validateFloatRange(form) {
                var bValid = true;
                var focusField = null;
                var i = 0;
                var fields = new Array();
                oRange = new floatRange();
                for (x in oRange) {
                    if ((form[oRange[x][0]].type == 'text' ||
                         form[oRange[x][0]].type == 'textarea') &&
                        (form[oRange[x][0]].value.length > 0)) {
                        var fMin = parseFloat(oRange[x][2]("min"));
                        var fMax = parseFloat(oRange[x][2]("max"));
                        var fValue = parseFloat(form[oRange[x][0]].value);
                        if (!(fValue >= fMin && fValue <= fMax)) {
                            if (i == 0) {
                                focusField = form[oRange[x][0]];
                            }
                            fields[i++] = oRange[x][1];
                            bValid = false;
                        }
                    }
                }
                if (fields.length > 0) {
                    focusField.focus();
                    alert(fields.join('\n'));
                }
                return bValid;
            }
