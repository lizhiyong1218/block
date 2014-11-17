function initDictionary(comboxId,dictionaryValue){
		$("#"+comboxId).combobox({
		    url:basePath+'/dictionary/getDictionaryItems.do?dictionaryValue='+dictionaryValue,
			valueField:'itemValue',
			textField:'itemLabel',
			method:'get'
		});
	}

 