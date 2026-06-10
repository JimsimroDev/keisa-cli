# Script de instalación automatizada para Keisa-CLI en Windows
$ErrorActionPreference = 'Stop'

# 1. Definir rutas estándar
$DirectorioInstalacion = "C:\mis-herramientas"
$UrlEjecutable = "https://github.com/JimsimroDev/keisa-cli/keisa-cli.exe"

# 2. Crear la carpeta si no existe
if (-not (Test-Path $DirectorioInstalacion)) {
    New-Item -ItemType Directory -Force -Path $DirectorioInstalacion | Out-Null
    Write-Host "✓ Carpeta de herramientas creada en $DirectorioInstalacion" -ForegroundColor Green
}

# 3. Descargar el archivo .exe desde tu repositorio remoto
Write-Host "Descargando keisa-cli.exe..." -ForegroundColor Cyan
Invoke-WebRequest -Uri $UrlEjecutable -OutFile "$DirectorioInstalacion\keisa-cli.exe"

# 4. Modificar el PATH de forma permanente en la máquina del usuario
$PathActual = [Environment]::GetEnvironmentVariable("Path", "User")
if ($PathActual -split ';' -notcontains $DirectorioInstalacion) {
    $NuevoPath = "$PathActual;$DirectorioInstalacion"
    [Environment]::SetEnvironmentVariable("Path", $NuevoPath, "User")
    Write-Host "✓ keisa-cli ha sido agregado al PATH de tu usuario." -ForegroundColor Green
} else {
    Write-Host "• La ruta ya existía en el PATH." -ForegroundColor Yellow
}

Write-Host "`n¡Instalación completada con éxito, Ingeniero!" -ForegroundColor Green
Write-Host "Por favor, reinicia tu terminal y ejecuta: keisa-cli" -ForegroundColor Magenta