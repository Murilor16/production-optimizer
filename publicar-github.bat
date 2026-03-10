@echo off
echo ========================================
echo   Publicar no GitHub - Production Optimizer
echo ========================================
echo.

echo [1/5] Inicializando Git...
git init
if %errorlevel% neq 0 (
    echo ERRO: Git nao esta instalado!
    echo Instale em: https://git-scm.com/download/win
    pause
    exit /b 1
)

echo.
echo [2/5] Adicionando arquivos...
git add .

echo.
echo [3/5] Criando commit...
git commit -m "Initial commit: Production Optimizer"

echo.
echo [4/5] Configurando branch main...
git branch -M main

echo.
echo ========================================
echo   ATENCAO: Proximo passo!
echo ========================================
echo.
echo Voce precisa:
echo 1. Criar repositorio no GitHub (se ainda nao criou)
echo 2. Copiar a URL do repositorio
echo.
set /p REPO_URL="Cole a URL do repositorio (ex: https://github.com/usuario/production-optimizer.git): "

echo.
echo [5/5] Conectando ao GitHub...
git remote add origin %REPO_URL%

echo.
echo Enviando codigo para o GitHub...
git push -u origin main

echo.
echo ========================================
echo   Sucesso!
echo ========================================
echo.
echo Seu codigo esta no GitHub!
echo Acesse: %REPO_URL%
echo.
pause
