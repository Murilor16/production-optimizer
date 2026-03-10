# 📤 Como Publicar no GitHub

## Passo 1: Criar Repositório no GitHub

1. Acesse: https://github.com
2. Faça login
3. Clique no **+** (canto superior direito) → **New repository**
4. Preencha:
   - **Repository name**: `production-optimizer`
   - **Description**: `Sistema de otimização de produção industrial`
   - **Public** (deixe marcado)
   - **NÃO** marque "Add a README file"
5. Clique em **Create repository**

## Passo 2: Abrir Terminal na Pasta do Projeto

Abra o terminal/cmd na pasta:
```
C:\Users\muril\OneDrive\Área de Trabalho\Projeto para emprego Projedata
```

## Passo 3: Executar Comandos

Cole estes comandos um por vez:

```bash
git init
```

```bash
git add .
```

```bash
git commit -m "Initial commit: Production Optimizer"
```

```bash
git branch -M main
```

```bash
git remote add origin https://github.com/SEU_USUARIO/production-optimizer.git
```
⚠️ **IMPORTANTE**: Substitua `SEU_USUARIO` pelo seu nome de usuário do GitHub!

```bash
git push -u origin main
```

## Passo 4: Autenticação

Quando pedir credenciais:
- **Username**: seu usuário do GitHub
- **Password**: use um **Personal Access Token** (não a senha)

### Como criar Token:
1. GitHub → Settings → Developer settings → Personal access tokens → Tokens (classic)
2. Generate new token → Marque `repo`
3. Copie o token e use como senha

## Passo 5: Verificar

Acesse: `https://github.com/SEU_USUARIO/production-optimizer`

Deve ver todos os arquivos!

## 🆘 Problemas Comuns

### "git não é reconhecido"
Instale o Git: https://git-scm.com/download/win

### "Permission denied"
Use Personal Access Token ao invés da senha

### "Repository not found"
Verifique se substituiu `SEU_USUARIO` corretamente

## ✅ Pronto!

Copie a URL do repositório e envie para a Projedata:
```
https://github.com/SEU_USUARIO/production-optimizer
```
