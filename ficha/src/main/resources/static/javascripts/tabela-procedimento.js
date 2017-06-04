var procedimentos = {
    index : 0,
    tBody: document.getElementById('ListaProcedimentoBody'),
    procedimentos: {}, 
	adicionar: function(e) {
        // Impede de submeter a página.
        e.preventDefault();
    	
        var id = document.getElementById('id'),
        	codigo = document.getElementById('codigo'),
        	descricao = document.getElementById('descricao');
         
        // Cria estrutura de elementos para adicionar a tabela.
        var tr = document.createElement('tr'),
          //  tdId = document.createElement('td'),
    		tdCodigo = document.createElement('td'),
           	tdDescricao = document.createElement('td'),
            tdBtn  = document.createElement('td'),
            btn    = document.createElement('button');


	   
      
        // Faz o aninhamento dos elementos, atribui os valores adicionados e acrescenta a tabela.
      //  tdId.textContent = id.value,
     //   tdCodigo.textContent = codigo.value,
        tdDescricao.textContent = descricao.value;
    	
      
        
        // Limpa campos.
      //  codigo.value = '';
     //   descricao.value = '';
       
        btn.setAttribute('onclick', 'procedimentos.excluir(event, this);');
        btn.textContent = 'Excluir';
        
        tdBtn.appendChild(btn);
        tr.appendChild(tdCodigo);
        tr.appendChild(tdDescricao);
        tr.appendChild(tdBtn);
       
       	this.tBody.appendChild(tr);
    },
    excluir: function(e, elem) {
        // Impede de submeter a página.
        e.preventDefault();
        
        // Pega o elemento pai do elemento pai do botão e deleta da tabela.
        var a = elem.parentElement.parentElement;
        this.tBody.removeChild(a);
    },
    send: function(e) {
        // Impede de submeter a página.
        e.preventDefault();
        
        // Pega os valores de cada linha da tabela inclui em uma array, apos isto, adiciona no objeto procedimentos.
        var procedimento = [];
        
        Array.prototype.forEach.call(this.tBody.children, function(arr, index) {
            procedimento.push({'codigo': arr.children[0].textContent, 'descricao': arr.children[1].textContent});
        });
        
        this.procedimentos = { "procedimento" : procedimento };
        
        console.log(this.procedimentos);
    }
}

