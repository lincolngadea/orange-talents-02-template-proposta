[31mc250568[m [1m7 hours ago[m [32m<Lincoln Gadea>[m chore: realiza algumas configurações de segurança para conexão do banco de dados no docker (em andamento)
[31ma85cae6[m [1m25 hours ago[m [32m<Lincoln Gadea>[m chore: cria associação de cartão a alguma carteira
[31m1a2452d[m [1m29 hours ago[m [32m<Lincoln Gadea>[m chore: habilita endpoints para usar o prometheus
[31m5c2a6bd[m [1m30 hours ago[m [32m<Lincoln Gadea>[m chore: cria notificação de bloqueio e inicia implementação de avisos de viagem
[31m7551f11[m [1m2 days ago[m [32m<Lincoln Gadea>[m chore: cria bloqueio de cartão
[31m92eb087[m [1m2 days ago[m [32m<Lincoln Gadea>[m refactor: ajusta a criação da biometria com base nas restrições
[31m0183a51[m [1m3 days ago[m [32m<Lincoln Gadea>[m refactor: finaliza criação do cartão associado a proposta
[31m2f32d12[m [1m4 days ago[m [32m<Lincoln Gadea>[m refactor: corrige forma como o cartão é gerado e implementa o salvamento automático do cartão com base na Proposta
[31mdf5942a[m [1m5 days ago[m [32m<Lincoln Gadea>[m refactor: cria uma NovoCartaoResponse para retornar os valores gravados no banco
[31m2fe3755[m [1m6 days ago[m [32m<Lincoln Gadea>[m refactor: adiciona novos campos na entidade cartão e muda a forma como recebe o id do cartao gerado
[31mb3dbd6a[m [1m6 days ago[m [32m<Lincoln Gadea>[m chore: cria integração com o servidor de autenticação (Keycloak)
[31m4d69b13[m [1m7 days ago[m [32m<Lincoln Gadea>[m chore: cria biometria, vincula a um cartão e devolve no header a uri com informações da biometria
[31m0224eac[m [1m7 days ago[m [32m<Lincoln Gadea>[m chore: cria variáveis de ambiente para realizar a build da imagem da aplicação para o docker
[31mb65458a[m [1m8 days ago[m [32m<Lincoln Gadea>[m chore: cria endpoint para buscar proposta por id
[31ma835725[m [1m8 days ago[m [32m<Lincoln Gadea>[m chore: cria automação para gerar número de cartão na proposta a cada 30 minutos de acordo com o status da proposta
[31mc693f8a[m [1m10 days ago[m [32m<Lincoln Gadea>[m chore: gera o cartão e salva na base de dados juntamente com o id da proposta
[31mb58db50[m [1m11 days ago[m [32m<Lincoln Gadea>[m chore: consome api do sistema legado e retorna nova proposta com dados do cartão
[31me07fe3e[m [1m12 days ago[m [32m<Lincoln Gadea>[m Merge branch 'main' of github.com:lincolngadea/orange-talents-02-template-proposta into main
[31m53f31f4[m [1m12 days ago[m [32m<Lincoln Gadea>[m refactor: optimiza o código da PropostaController, criando um méto para análise da submissão da proposta
[31m3a1e520[m [1m12 days ago[m [32m<Lincoln Gadea>[m chore: cria uma proposta no feign e trata exceções de conexão recusada e proposta não elegível
[31m9f1c108[m [1m12 days ago[m [32m<Lincoln Gadea>[m refactor: adiciona campos LocalDateTime e PropostaStatus na Proposta
[31m3443ae2[m [1m13 days ago[m [32m<Lincoln Gadea>[m chore: garante que seja criada apenas uma proposta por documento CPF|CNPJ e altera a persistência para usar um repository
[31m8ec1354[m [1m13 days ago[m [32m<Lincoln Gadea>[m refactor: garante que o teste de criação de proposta verifique o retorno da location e do status para 201 conforme requisitos
[31m7da2add[m [1m2 weeks ago[m [32m<Lincoln Gadea>[m chore: cria arquivo de log Logback e testa no controller
[31m6ec4f03[m [1m2 weeks ago[m [32m<Lincoln Gadea>[m chore: cria NovaPropostaResponse
[31m74c281b[m [1m2 weeks ago[m [32m<Lincoln Gadea>[m chore: cria teste para retorno 201 ao criar uma proposta
[31mb81aa3c[m [1m2 weeks ago[m [32m<Lincoln Gadea>[m chore: retorna 201 com o header Location preenchido no ato da criação da proposta e  configura o banco h2 pra testes
[31m7f11801[m [1m2 weeks ago[m [32m<Lincoln Gadea>[m chore: cria validadore de CPF e ValorUnico e monta a estrutura inicial do Controller e NovaPropostaRequest
[31medc250e[m [1m2 weeks ago[m [32m<Lincoln Gadea>[m chore: first commit
[31m2e868c1[m [1m6 months ago[m [32m<Luram Archanjo>[m Update README.md