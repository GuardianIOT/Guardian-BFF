# **Guardian - Sistema Inteligente de Monitoramento Climático para Aerogeradores**

## **Descrição do Projeto**
O projeto **Guardian** utiliza sensores avançados para monitorar condições climáticas em tempo real e promover maior agilidade na manutenção de aerogeradores em regiões estratégicas do Brasil.  
As informações coletadas — como quedas de raios, velocidade dos ventos, pluviosidade e previsões climáticas — são integradas ao desempenho dos aerogeradores, permitindo análises preventivas e corretivas.

### **Benefícios Alcançados para o Negócio**
- **Redução de Períodos de Inatividade**: Rápida identificação e resposta a falhas operacionais.
- **Aumento da Segurança Estrutural**: Ações proativas, como desligamento de aerogeradores em condições extremas.
- **Otimização da Eficiência Energética**: Operação adaptada às condições climáticas em tempo real.
- **Integração de Dados Avançada**: Histórico detalhado para análise e estratégias de manutenção.

---

## **Arquitetura Implementada**
A solução integra tecnologias avançadas, incluindo o uso de APIs climáticas da Xweather da Vaisala e containers Docker para uma infraestrutura robusta e escalável.
> Acesse o diagrama de arquitetura: [Azure Architecture Diagram](./evidencias/arquitetura.png)

---

## **Documentação do Projeto**

### **Scripts e Arquivos**
1. **Banco de Dados**
    - Arquivo: `script.sql`  
      Contém o DDL necessário para a criação das tabelas utilizadas na solução.

2. **Código-Fonte**  
   O código-fonte está disponível na raiz do repositório, garantindo a integridade e a funcionalidade do sistema.

5. **API**
    - A API conta com uma documentação feita com o uso do Swagger-ui e Api-docs

6. **Azure CLI**  
   Scripts de criação de recursos:
   ```bash  
   az group create --name guardian --location canadaeast  
   az appservice plan create --name guardian-plan --resource-group guardian --location canadaeast --sku F1 --is-linux  
   az webapp create --name guardian --resource-group guardian --plan guardian-plan --runtime "JAVA:17-java17"  

---

### **Comandos Docker**
Inicie os serviços essenciais

1. **RabbitMQ**  
   Scripts de criação de recursos:
   ```bash  
   docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management

2. **Ollama Llama**
    ```bash
    docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama  
    docker exec -it ollama ollama run llama3.2

---

## Prints e Evidências
Os prints do pipeline finalizado e das integrações entre Repos e Pipeline estão disponíveis na pasta evidencias/ do repositório.

---

Com essa solução, o Guardian consolida uma plataforma robusta para gerenciamento climático e operacional de aerogeradores, maximizando a eficiência e reduzindo custos.
