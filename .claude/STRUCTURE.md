# 📁 Estructura del Sistema de Agentes

## Vista de Árbol Completa

```
.claude/
│
├── 📘 README.md                           # Documentación general del sistema
├── 🚀 QUICKSTART.md                       # Guía rápida de inicio
├── 📖 USAGE.md                            # Manual de uso detallado
├── 🌲 STRUCTURE.md                        # Este archivo
│
├── 📋 issues/                             # Archivos de issues (input)
│   └── SCRUM-10_backend.md               # ✅ Issue de ejemplo creada
│
├── 🗂️ sessions/                           # Contextos de sesión compartidos
│   └── context_session_SCRUM-10.md       # ✅ Contexto creado automáticamente
│
├── 📄 doc/                                # Planes generados (output)
│   └── SCRUM-10/                         # Directorio por feature
│       └── backend.md                    # ⏳ Se generará con Warp Agent
│
└── 🛠️ scripts/                            # Scripts de automatización
    └── plan-developer-backend.ps1        # ✅ Script principal backend
```

---

## Descripción de Archivos

### 📘 Documentación

| Archivo | Propósito | Estado |
|---------|-----------|--------|
| `README.md` | Explicación general del sistema | ✅ Creado |
| `QUICKSTART.md` | Guía de inicio rápido | ✅ Creado |
| `USAGE.md` | Manual detallado de uso | ✅ Creado |
| `STRUCTURE.md` | Este archivo (estructura visual) | ✅ Creado |

### 📋 Issues (Input)

| Archivo | Tipo | Descripción | Estado |
|---------|------|-------------|--------|
| `SCRUM-10_backend.md` | Backend | Sistema de reservas completo | ✅ Ejemplo listo |

**Plantilla de nombres:**
- `SCRUM-{N}_backend.md` - Para features de backend
- `SCRUM-{N}_frontend.md` - Para features de frontend
- `SCRUM-{N}_test.md` - Para definición de pruebas

### 🗂️ Sessions (Contexto)

| Archivo | Feature | Generado por | Estado |
|---------|---------|--------------|--------|
| `context_session_SCRUM-10.md` | SCRUM-10 | Script automático | ✅ Creado |

**Propósito:** Punto de entrada para que los agentes obtengan contexto completo.

### 📄 Doc (Output - Planes)

| Archivo | Feature | Agente | Estado |
|---------|---------|--------|--------|
| `SCRUM-10/backend.md` | SCRUM-10 | Backend Architect | ⏳ Pendiente |

**Se generará cuando:** Ejecutes las instrucciones en Warp Agent.

### 🛠️ Scripts

| Script | Agente | Funcionalidad | Estado |
|--------|--------|---------------|--------|
| `plan-developer-backend.ps1` | Backend | Genera plan arquitectura hexagonal | ✅ Funcionando |

**Próximos scripts:**
- `plan-developer-frontend.ps1` - Para React/TypeScript
- `plan-test-cases.ps1` - Para definición de pruebas
- `plan-ui-analysis.ps1` - Para análisis UI/UX

---

## Flujo de Datos

```
┌─────────────────────┐
│   1. TÚ CREAS       │
│  Issue en issues/   │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  2. EJECUTAS        │
│  plan-backend.ps1   │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  3. SCRIPT CREA     │
│  - sessions/        │
│  - doc/{feature}/   │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  4. TÚ COPIAS       │
│  Instrucciones      │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  5. WARP AGENT      │
│  Lee & Analiza      │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  6. GENERA PLAN     │
│  doc/{feature}/     │
│  backend.md         │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  7. TÚ IMPLEMENTAS  │
│  Siguiendo el plan  │
└─────────────────────┘
```

---

## Estados de Archivos

### ✅ Listos para Usar
- Documentación completa
- Script backend funcional
- Issue de ejemplo SCRUM-10
- Contexto de sesión generado
- Estructura de directorios

### ⏳ Pendientes de Crear
- Plan de backend (requiere Warp Agent)
- Scripts frontend/test (opcional)
- Tus propias issues personalizadas

---

## Comandos de Navegación

```powershell
# Ver estructura
Get-ChildItem .claude -Recurse | Select-Object FullName

# Leer documentación
Get-Content .claude/QUICKSTART.md
Get-Content .claude/USAGE.md

# Ver issue de ejemplo
Get-Content .claude/issues/SCRUM-10_backend.md

# Ver contexto generado
Get-Content .claude/sessions/context_session_SCRUM-10.md

# Ejecutar script
.\.claude\scripts\plan-developer-backend.ps1 SCRUM-10_backend.md

# Ver plan generado (después de usar Warp Agent)
Get-Content .claude/doc/SCRUM-10/backend.md
```

---

## Tamaños Estimados

| Tipo | Archivo | Tamaño Aprox. |
|------|---------|---------------|
| 📘 Doc | README.md | ~2 KB |
| 🚀 Doc | QUICKSTART.md | ~5 KB |
| 📖 Doc | USAGE.md | ~6 KB |
| 📋 Issue | SCRUM-10_backend.md | ~12 KB |
| 🗂️ Context | context_session_*.md | ~0.5 KB |
| 🛠️ Script | plan-developer-backend.ps1 | ~4 KB |
| 📄 Plan | backend.md (generado) | ~20-50 KB |

**Total actual:** ~30 KB  
**Total con plan generado:** ~50-80 KB

---

## Expansión Futura

### Próximas Características
```
.claude/
├── scripts/
│   ├── plan-developer-backend.ps1      ✅ Listo
│   ├── plan-developer-frontend.ps1     📝 Por crear
│   ├── plan-test-cases.ps1             📝 Por crear
│   ├── plan-ui-analysis.ps1            📝 Por crear
│   └── implement-from-plan.ps1         📝 Por crear
│
└── templates/
    ├── issue-backend-template.md       📝 Por crear
    ├── issue-frontend-template.md      📝 Por crear
    └── issue-fullstack-template.md     📝 Por crear
```

---

## Integración con Git

### Archivos a Commitear
```gitignore
# Sí commitear
.claude/README.md
.claude/QUICKSTART.md
.claude/USAGE.md
.claude/STRUCTURE.md
.claude/scripts/*.ps1
.claude/issues/*.md          # Issues como documentación

# No commitear (opcional)
.claude/sessions/            # Contextos temporales
.claude/doc/*/              # Planes generados (depende)
```

### Sugerencia de .gitignore
```gitignore
# Claude Agent - Archivos temporales
.claude/sessions/
.claude/doc/*/

# Claude Agent - Mantener estructura
!.claude/doc/.gitkeep
```

---

## Métricas del Sistema

| Métrica | Valor |
|---------|-------|
| **Archivos creados** | 7 |
| **Scripts funcionando** | 1 |
| **Issues de ejemplo** | 1 |
| **Líneas de documentación** | ~600 |
| **Tiempo de setup** | 5 minutos |
| **Tiempo gen. plan** | 2-5 minutos |

---

## Checklist de Verificación

### ✅ Sistema Instalado
- [x] Estructura de directorios creada
- [x] Documentación completa
- [x] Script backend funcional
- [x] Issue de ejemplo lista
- [x] Contexto de sesión generado

### 🎯 Próximos Pasos
- [ ] Ejecutar script con SCRUM-10
- [ ] Copiar instrucciones a Warp Agent
- [ ] Generar plan de backend
- [ ] Revisar plan generado
- [ ] Crear tu primera issue personalizada

---

*Última actualización: 2025-01-12*  
*Versión del sistema: 1.0.0*
