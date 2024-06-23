document.addEventListener('DOMContentLoaded', function() {
    loadProducts();
});

function loadProducts() {
    fetch('/api/products')
        .then(response => response.json())
        .then(data => {
            const productList = document.getElementById('product-list');
            productList.innerHTML = '';
            data.forEach(product => {
                const li = document.createElement('li');
                li.textContent = `${product.name} - ${product.price}`;
                productList.appendChild(li);
            });
        })
        .catch(error => console.error('Error fetching products:', error));
}

function searchProducts() {
    const query = document.getElementById('search').value;
    fetch(`/api/products/search?query=${query}`)
        .then(response => response.json())
        .then(data => {
            const productList = document.getElementById('product-list');
            productList.innerHTML = '';
            data.forEach(product => {
                const li = document.createElement('li');
                li.textContent = `${product.name} - ${product.price}`;
                productList.appendChild(li);
            });
        })
        .catch(error => console.error('Error fetching products:', error));
}
        $(document).ready(function() {
            // Função para carregar produtos
            function loadProducts() {
                $.ajax({
                    url: '/api/products',
                    method: 'GET',
                    success: function(products) {
                        const productSelect = $('#product_id');
                        productSelect.empty();
                        products.forEach(product => {
                            const option = `<option value="${product.id}">${product.name}</option>`;
                            productSelect.append(option);
                        });
                    }
                });
            }

            // Carregar produtos ao carregar a página
            loadProducts();

            // Função para registrar movimentação
            $('#movimentacao-form').on('submit', function(e) {
                e.preventDefault();
                const movimentacao = {
                    product_id: $('#product_id').val(),
                    tipo_movimentacao: $('#tipo_movimentacao').val(),
                    quantidade: $('#quantidade').val()
                };
                $.ajax({
                    url: '/api/movimentacoes',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(movimentacao),
                    success: function() {
                        $('#movimentacao-form')[0].reset();
                    }
                });
            });

            // Função para pesquisar produtos
            $('#search-form').on('submit', function(e) {
                e.preventDefault();
                const query = $('#search_query').val();
                $.ajax({
                    url: '/api/products/search',
                    method: 'GET',
                    data: { query: query },
                    success: function(products) {
                        const tableBody = $('#search-results');
                        tableBody.empty();
                        products.forEach(product => {
                            const row = `<tr>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${product.description}</td>
                                <td>${product.price}</td>
                                <td>${product.quantity}</td>
                                <td>${product.category}</td>
                                <td>${product.fornecedor}</td>
                            </tr>`;
                            tableBody.append(row);
                        });
                    }
                });
            });
        });

