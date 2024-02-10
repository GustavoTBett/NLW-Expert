## Documentação da API

### Verifica se o usuário já realizou a prova

```http
  POST /students/verify
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `email` | `String` | O email do usuário a ser consultado |
| `tech` | `String` | A tecnologia da prova a ser consultada |

#### Exemplo de Requisição
```json
POST /students/verify
Content-Type: application/json

{
  "email": "exemplo@email.com",
  "tech": "JAVA"
}
```

### Retorna as questões da prova

```http
  GET /questions/techs/JAVA
```

### Verifica as questões, se acertou ou errou

```http
  POST /students/certification/answer
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `email` | `String` | O email do usuário |
| `tech` | `String` | A tecnologia da prova |
| `questionsAnswers` | `Object` | A lista com os IDs das perguntas e respostas |

#### Exemplo de Requisição
```json
POST /students/certification/answer
Content-Type: application/json

{
	"email": "exemplo@email.com",
	"tech": "JAVA",
	"questionsAnswers": [
		{
			"questionID": "c5f02721-6dc3-4fa6-b46d-6f2e8dca9c66",
			"alternativeID": "993a7d37-62a0-4040-810d-d667e3f7a891"
		},
		{
			"questionID": "b0ec9e6b-721c-43c7-9432-4d0b6eb15b01",
			"alternativeID": "f8e6e9b3-199b-4f0d-97ce-7e5bdc080da9"
		},
		{
			"questionID": "f85e9434-1711-4e02-9f9e-7831aa5c743a",
			"alternativeID": "d3e51a56-9b97-4bb8-9827-8bcf89f4b276"
		}
	]
}
```

### Retorna um ranking com as 10 melhores notas

```http
  GET /ranking/top-10
```
