<p class="has-line-data" data-line-start="1" data-line-end="7">Projeto de Gestão de Empresas<br>
Introdução<br>
Este projeto consiste na criação de uma aplicação utilizando o framework Spring Boot para implementar serviços REST básicos. A aplicação gerencia informações sobre empresas, clientes, funcionários, proprietários, produtos e fornecedores. Utilizamos JPA para persistência de dados e o banco de dados H2.<br>
Gerenciamento de Dependências<br>
Para gerenciar as dependências do projeto, utilizamos o Maven. Abaixo estão listadas as dependências escolhidas e a justificativa para cada uma:<br>
Spring Boot Starter Web:</p>
<p class="has-line-data" data-line-start="8" data-line-end="10">Justificativa: Necessário para criar aplicações web, incluindo RESTful services usando Spring MVC.<br>
Spring Boot Starter Data JPA:</p>
<p class="has-line-data" data-line-start="11" data-line-end="13">Justificativa: Fornece integração com JPA para persistência de dados.<br>
H2 Database:</p>
<p class="has-line-data" data-line-start="14" data-line-end="16">Justificativa: Banco de dados em memória para facilitar o desenvolvimento e testes.<br>
Spring Boot Starter Validation:</p>
<p class="has-line-data" data-line-start="17" data-line-end="19">Justificativa: Necessário para a validação dos DTOs.<br>
Spring Boot Starter Test:</p>
<p class="has-line-data" data-line-start="21" data-line-end="23">Justificativa: Inclui diversas bibliotecas para testes, como JUnit, Hamcrest e Mockito.<br>
Lombok:</p>
<p class="has-line-data" data-line-start="24" data-line-end="28">Justificativa: Reduz a verbosidade do código ao gerar getters, setters, construtores etc.<br>
Implementação dos Serviços REST com Persistência<br>
Os serviços REST foram implementados utilizando Spring Boot. A seguir, estão os detalhes dos endpoints e suas respectivas funcionalidades:<br>
Endpoints</p>
<ol>
<li class="has-line-data" data-line-start="28" data-line-end="35">
<p class="has-line-data" data-line-start="28" data-line-end="34">Cliente (Client):<br>
o   GET /client: Retorna todos os clientes.<br>
o   GET /client/{id}: Retorna um cliente específico pelo ID.<br>
o   POST /client: Cria um novo cliente.<br>
o   PUT /client/{id}: Atualiza um cliente existente.<br>
o   DELETE /client/{id}: Deleta um cliente.</p>
</li>
<li class="has-line-data" data-line-start="35" data-line-end="42">
<p class="has-line-data" data-line-start="35" data-line-end="41">Empresa (Company):<br>
o   GET /company: Retorna todas as empresas.<br>
o   GET /company/{id}: Retorna uma empresa específica pelo ID.<br>
o   POST /company: Cria uma nova empresa.<br>
o   PUT /company/{id}: Atualiza uma empresa existente.<br>
o   DELETE /company/{id}: Deleta uma empresa.</p>
</li>
<li class="has-line-data" data-line-start="42" data-line-end="49">
<p class="has-line-data" data-line-start="42" data-line-end="48">Funcionário (Employee):<br>
o   GET /employee: Retorna todos os funcionários.<br>
o   GET /employee/{id}: Retorna um funcionário específico pelo ID.<br>
o   POST /employee: Cria um novo funcionário.<br>
o   PUT /employee/{id}: Atualiza um funcionário existente.<br>
o   DELETE /employee/{id}: Deleta um funcionário.</p>
</li>
<li class="has-line-data" data-line-start="49" data-line-end="56">
<p class="has-line-data" data-line-start="49" data-line-end="55">Proprietário (Owner):<br>
o   GET /owner: Retorna todos os proprietários.<br>
o   GET /owner/{id}: Retorna um proprietário específico pelo ID.<br>
o   POST /owner: Cria um novo proprietário.<br>
o   PUT /owner/{id}: Atualiza um proprietário existente.<br>
o   DELETE /owner/{id}: Deleta um proprietário.</p>
</li>
<li class="has-line-data" data-line-start="56" data-line-end="63">
<p class="has-line-data" data-line-start="56" data-line-end="62">Produto (Product):<br>
o   GET /products: Retorna todos os produtos.<br>
o   GET /products/{id}: Retorna um produto específico pelo ID.<br>
o   POST /products: Cria um novo produto.<br>
o   PUT /products/{id}: Atualiza um produto existente.<br>
o   DELETE /products/{id}: Deleta um produto.</p>
</li>
<li class="has-line-data" data-line-start="63" data-line-end="70">
<p class="has-line-data" data-line-start="63" data-line-end="69">Fornecedor (Supplier):<br>
o   GET /suppliers: Retorna todos os fornecedores.<br>
o   GET /suppliers/{id}: Retorna um fornecedor específico pelo ID.<br>
o   POST /suppliers: Cria um novo fornecedor.<br>
o   PUT /suppliers/{id}: Atualiza um fornecedor existente.<br>
o   DELETE /suppliers/{id}: Deleta um fornecedor.</p>
</li>
</ol>
<p class="has-line-data" data-line-start="70" data-line-end="71">Validação dos Códigos de Status HTTP</p>
<ol>
<li class="has-line-data" data-line-start="71" data-line-end="72">GET - 200 OK: Utilizado para requisições bem-sucedidas que retornam dados. Justificativa: Conforme especificações REST, um código 200 indica que a requisição foi bem-sucedida.</li>
<li class="has-line-data" data-line-start="72" data-line-end="73">POST - 201 Created: Utilizado quando um novo recurso é criado. Justificativa: O código 201 indica que um novo recurso foi criado com sucesso no servidor.</li>
<li class="has-line-data" data-line-start="73" data-line-end="74">PUT - 200 OK: Utilizado para atualizações bem-sucedidas. Justificativa: Um código 200 indica que a atualização foi bem-sucedida e retorna os dados atualizados.</li>
<li class="has-line-data" data-line-start="74" data-line-end="75">DELETE - 204 No Content: Utilizado quando um recurso é deletado com sucesso. Justificativa: O código 204 indica que a requisição foi processada com sucesso, mas não há conteúdo para retornar.</li>
<li class="has-line-data" data-line-start="75" data-line-end="76">400 Bad Request: Utilizado quando há erros de validação ou dados de entrada inválidos. Justificativa: O código 400 indica que a requisição não pôde ser processada devido a erro do cliente (dados inválidos).</li>
<li class="has-line-data" data-line-start="76" data-line-end="78">404 Not Found: Utilizado quando um recurso não é encontrado. Justificativa: O código 404 indica que o recurso solicitado não existe.</li>
</ol>
<p class="has-line-data" data-line-start="78" data-line-end="81">Desenvolvimento de Testes nos Serviços REST<br>
Foram implementados testes unitários e de integração para garantir o correto funcionamento das funcionalidades desenvolvidas.<br>
Exemplos de Testes:</p>
<ol>
<li class="has-line-data" data-line-start="81" data-line-end="88">
<p class="has-line-data" data-line-start="81" data-line-end="87">ClientControllerTest:<br>
o   shouldGetAllClientsSuccessfully: Testa a listagem de todos os clientes.<br>
o   shouldGetClientByIdSuccessfully: Testa a obtenção de um cliente pelo ID.<br>
o   shouldCreateClientSuccessfully: Testa a criação de um novo cliente.<br>
o   shouldUpdateClientSuccessfully: Testa a atualização de um cliente.<br>
o   shouldDeleteClientSuccessfully: Testa a exclusão de um cliente.</p>
</li>
<li class="has-line-data" data-line-start="88" data-line-end="95">
<p class="has-line-data" data-line-start="88" data-line-end="94">CompanyControllerTest:<br>
o   shouldGetAllCompaniesSuccessfully: Testa a listagem de todas as empresas.<br>
o   shouldGetCompanyByIdSuccessfully: Testa a obtenção de uma empresa pelo ID.<br>
o   shouldCreateCompanySuccessfully: Testa a criação de uma nova empresa.<br>
o   shouldUpdateCompanySuccessfully: Testa a atualização de uma empresa.<br>
o   shouldDeleteCompanySuccessfully: Testa a exclusão de uma empresa.</p>
</li>
<li class="has-line-data" data-line-start="95" data-line-end="102">
<p class="has-line-data" data-line-start="95" data-line-end="101">EmployeeControllerTest:<br>
o   shouldGetAllEmployeesSuccessfully: Testa a listagem de todos os funcionários.<br>
o   shouldGetEmployeeByIdSuccessfully: Testa a obtenção de um funcionário pelo ID.<br>
o   shouldCreateEmployeeSuccessfully: Testa a criação de um novo funcionário.<br>
o   shouldUpdateEmployeeSuccessfully: Testa a atualização de um funcionário.<br>
o   shouldDeleteEmployeeSuccessfully: Testa a exclusão de um funcionário.</p>
</li>
<li class="has-line-data" data-line-start="102" data-line-end="109">
<p class="has-line-data" data-line-start="102" data-line-end="108">OwnerControllerTest:<br>
o   shouldGetAllOwnersSuccessfully: Testa a listagem de todos os proprietários.<br>
o   shouldGetOwnerByIdSuccessfully: Testa a obtenção de um proprietário pelo ID.<br>
o   shouldCreateOwnerSuccessfully: Testa a criação de um novo proprietári<br>
o   shouldUpdateOwnerSuccessfully: Testa a atualização de um proprietário.<br>
o   shouldDeleteOwnerSuccessfully: Testa a exclusão de um proprietário.</p>
</li>
<li class="has-line-data" data-line-start="109" data-line-end="116">
<p class="has-line-data" data-line-start="109" data-line-end="115">ProductControllerTest:<br>
o   shouldGetAllProductsSuccessfully: Testa a listagem de todos os produtos.<br>
o   shouldGetProductByIdSuccessfully: Testa a obtenção de um produto pelo ID.<br>
o   shouldCreateProductSuccessfully: Testa a criação de um novo produto.<br>
o   shouldUpdateProductSuccessfully: Testa a atualização de um produto.<br>
o   shouldDeleteProductSuccessfully: Testa a exclusão de um produto.</p>
</li>
<li class="has-line-data" data-line-start="116" data-line-end="123">
<p class="has-line-data" data-line-start="116" data-line-end="122">SupplierControllerTest:<br>
o   shouldGetAllSuppliersSuccessfully: Testa a listagem de todos os fornecedores.<br>
o   shouldGetSupplierByIdSuccessfully: Testa a obtenção de um fornecedor pelo ID.<br>
o   shouldCreateSupplierSuccessfully: Testa a criação de um novo fornecedor.<br>
o   shouldUpdateSupplierSuccessfully: Testa a atualização de um fornecedor.<br>
o   shouldDeleteSupplierSuccessfully: Testa a exclusão de um fornecedor.</p>
</li>
</ol>
<p class="has-line-data" data-line-start="123" data-line-end="130">Conclusão<br>
Este projeto foi desenvolvido seguindo as melhores práticas de desenvolvimento de software, aplicando princípios SOLID, DDD e utilizando técnicas eficazes de gerenciamento de dependências. Todas as funcionalidades foram testadas para garantir a qualidade do software. A documentação detalha cada etapa do projeto, as escolhas técnicas feitas e suas justificativas.<br>
Referências<br>
•   Eric Evans, Domain-Driven Design: Tackling Complexity in the Heart of Software<br>
•   Robert C. Martin, Clean Code: A Handbook of Agile Software Craftsmanship<br>
•   Spring Framework Documentation: <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/">https://docs.spring.io/spring-framework/docs/current/reference/html/</a><br>
•   Spring Boot Documentation: <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/">https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/</a></p>
