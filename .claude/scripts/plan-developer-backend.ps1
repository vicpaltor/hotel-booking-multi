# plan-developer-backend.ps1
# Script para generar plan de implementación backend basado en arquitectura hexagonal

param(
    [Parameter(Mandatory=$true)]
    [string]$IssueFile
)

# Colores para output
$ColorSuccess = "Green"
$ColorError = "Red"
$ColorInfo = "Cyan"
$ColorWarning = "Yellow"

# Función para mostrar banner
function Show-Banner {
    Write-Host "`n╔════════════════════════════════════════════════════════════════╗" -ForegroundColor $ColorInfo
    Write-Host "║          🏗️  Backend Developer Agent - Hexagonal Arch         ║" -ForegroundColor $ColorInfo
    Write-Host "╚════════════════════════════════════════════════════════════════╝`n" -ForegroundColor $ColorInfo
}

# Función para validar archivo de issue
function Test-IssueFile {
    param([string]$FilePath)
    
    $issuesDir = ".claude/issues"
    $fullPath = Join-Path $issuesDir $FilePath
    
    if (-not (Test-Path $fullPath)) {
        Write-Host "❌ Error: No se encontró el archivo '$FilePath' en '$issuesDir'" -ForegroundColor $ColorError
        Write-Host "   Archivos disponibles:" -ForegroundColor $ColorWarning
        Get-ChildItem $issuesDir -Filter "*.md" | ForEach-Object {
            Write-Host "   - $($_.Name)" -ForegroundColor $ColorWarning
        }
        return $null
    }
    
    return $fullPath
}

# Función para extraer feature name del archivo
function Get-FeatureName {
    param([string]$IssueFile)
    
    # Extraer nombre base sin extensión (ej: SCRUM-10_backend -> SCRUM-10)
    $baseName = [System.IO.Path]::GetFileNameWithoutExtension($IssueFile)
    return $baseName -replace "_.*$", ""
}

# Main script
Show-Banner

Write-Host "📋 Procesando issue: $IssueFile" -ForegroundColor $ColorInfo

# Validar archivo
$issueFilePath = Test-IssueFile -FilePath $IssueFile
if (-not $issueFilePath) {
    exit 1
}

# Extraer feature name
$featureName = Get-FeatureName -IssueFile $IssueFile
Write-Host "🎯 Feature detectado: $featureName`n" -ForegroundColor $ColorSuccess

# Crear directorios necesarios
$sessionDir = ".claude/sessions"
$docDir = ".claude/doc/$featureName"

New-Item -ItemType Directory -Force -Path $sessionDir | Out-Null
New-Item -ItemType Directory -Force -Path $docDir | Out-Null

# Crear archivo de contexto de sesión
$contextFile = Join-Path $sessionDir "context_session_$featureName.md"
Write-Host "📝 Creando contexto de sesión: $contextFile" -ForegroundColor $ColorInfo

$contextContent = @"
# Contexto de Sesión - $featureName

## Fecha de creación
$(Get-Date -Format "yyyy-MM-dd HH:mm:ss")

## Issue relacionada
- **Archivo**: $IssueFile
- **Ruta completa**: $issueFilePath

## Estado
- ✅ Contexto de sesión creado
- ⏳ Plan de backend pendiente
- ⏳ Implementación pendiente
- ⏳ Testing pendiente

## Archivos generados
- [ ] .claude/doc/$featureName/backend.md

## Notas
Este archivo sirve como punto de entrada para que los agentes obtengan el contexto completo de la feature.

---
*Generado automáticamente por plan-developer-backend.ps1*
"@

Set-Content -Path $contextFile -Value $contextContent -Encoding UTF8

# Mensaje de instrucciones para el usuario
Write-Host "`n╔════════════════════════════════════════════════════════════════╗" -ForegroundColor $ColorSuccess
Write-Host "║                    ✅ Setup Completado                         ║" -ForegroundColor $ColorSuccess
Write-Host "╚════════════════════════════════════════════════════════════════╝`n" -ForegroundColor $ColorSuccess

Write-Host "📂 Estructura creada:" -ForegroundColor $ColorInfo
Write-Host "   ├── $contextFile" -ForegroundColor $ColorSuccess
Write-Host "   └── $docDir/ (preparado)`n" -ForegroundColor $ColorSuccess

Write-Host "🤖 Próximos pasos:" -ForegroundColor $ColorWarning
Write-Host "   1. El agente backend debe leer: $issueFilePath" -ForegroundColor White
Write-Host "   2. El agente debe consultar: $contextFile" -ForegroundColor White
Write-Host "   3. El agente generará: $docDir/backend.md`n" -ForegroundColor White

Write-Host "💡 Instrucciones para Warp Agent:" -ForegroundColor $ColorInfo
Write-Host @"
Por favor, actúa como un arquitecto backend experto en Java/Spring con arquitectura hexagonal.

1. Lee el archivo de issue: $issueFilePath
2. Consulta el contexto de sesión: $contextFile
3. Genera un plan de implementación detallado en: $docDir/backend.md

El plan debe incluir:
- Análisis de requisitos
- Diseño de agregados y value objects (DDD)
- Definición de puertos (interfaces)
- Definición de adaptadores
- Estructura de archivos a crear/modificar
- Código de ejemplo para clases clave
- Validaciones y reglas de negocio
- Manejo de errores
- Consideraciones de testing

IMPORTANTE: 
- NO implementes el código, solo genera el plan
- Usa arquitectura hexagonal estricta
- Separa claramente: domain, application, infrastructure
- Asegúrate de que el dominio no tenga dependencias de frameworks
- Sigue los principios DDD (Domain-Driven Design)

"@ -ForegroundColor White

Write-Host "`n📌 Para ejecutar este comando de nuevo:" -ForegroundColor $ColorInfo
Write-Host "   .\.claude\scripts\plan-developer-backend.ps1 $IssueFile`n" -ForegroundColor $ColorWarning
