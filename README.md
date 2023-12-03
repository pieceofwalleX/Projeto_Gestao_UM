--Projeto Programacao com Objetos Uminho

Classes:
    Administrador(
        Authenticator
    )
    Professor(
        Id
        Nome
        Numero Mecanografico
        Data de Inicio das Funcoes
        Lista de UCs
        
        Regente(
            UC
        )
        Diretor(
            Curso
        )
    )
    Aluno(
        Id
        Nome
        Numero Mecanografico
        Curso
    )
    UC(
        Designacao
        Regente
        Lista Professores
    )
    Curso(
        Lista de UCs
        Lista de Alunos
        Diretor
    )
    Sumario(
        Titulo
        Tipo
        Texto
    )

Funcoes:
    Admin(
        Adicionar/Remover/Editar Professores
        Criar/Editar Informacao de um Curso
        Listar Cursos/UCs/Alunos/Professores
    )
    Professor(
        Criar Sumarios
        Consulatar Lista Sumarios por UC e Tipo de aula
        Consultar Servico Docente
    )
    Regente(
        Adicionar/Remover alunos do curso
        Consultar Assiduidade de um Aluno
    )
    Diretor Curso(
        Alterar Designacao do Curso
        Listar numero de professores e alunos no curso
    )
    
        