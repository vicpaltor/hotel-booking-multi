# Guía de Uso - Claude Agents para Hotel Booking System

## 🚀 Comandos Disponibles

### 1. Plan de Backend (`/plan-developer-backend`)

Genera un plan de implementación backend siguiendo arquitectura hexagonal.

**Uso:**
```powershell
.\.claude\scripts\plan-developer-backend.ps1 SCRUM-10_backend.md
```

**Alias sugerido (PowerShell Profile):**
```powershell
function plan-backend { .\.claude\scripts\plan-developer-backend.ps1 $args }
```

Después puedes usar:
```powershell
plan-backend SCRUM-10_backend.md
```

---

### 2. Plan de Frontend (`/plan-developer-frontend`)

Genera un plan de implementación frontend React con feature-based architecture.

**Uso:**
```powershell
.\.claude\scripts\plan-developer-frontend.ps1 SCRUM-10_frontend.md
```

---

### 3. Plan de Test Cases (`/plan-test-cases`)

Genera definición exhaustiva de casos de prueba.

**Uso:**
```powershell
.\.claude\scripts\plan-test-cases.ps1 SCRUM-10_test.md
```

---

## 📝 Formato de Issues

Coloca tus issues en `.claude/issues/` con el siguiente formato:

### Ejemplo: `SCRUM-10_backend.md`

```markdown
# [SCRUM-10] Título de la Feature

## Descripción
[Descripción detallada]

## Requisitos Funcionales
- [ ] Requisito 1
- [ ] Requisito 2

## Requisitos Técnicos
- Stack: Java 17, Spring Boot 3.x
- Arquitectura: Hexagonal

## Criterios de Aceptación
**Dado** [contexto]
**Cuando** [acción]
**Entonces** [resultado esperado]
```

---

## 🔧 Configuración de Alias en PowerShell

Para usar comandos cortos, agrega esto a tu perfil de PowerShell:

```powershell
# Abrir el perfil
notepad $PROFILE

# Agregar estos alias:
function plan-backend { .\.claude\scripts\plan-developer-backend.ps1 $args }
function plan-frontend { .\.claude\scripts\plan-developer-frontend.ps1 $args }
function plan-tests { .\.claude\scripts\plan-test-cases.ps1 $args }
```

Reinicia PowerShell y usa:
```powershell
plan-backend SCRUM-10_backend.md
plan-frontend SCRUM-10_frontend.md
plan-tests SCRUM-10_test.md
```

---

## 🎯 Flujo de Trabajo Completo

### Paso 1: Crear Issue
```powershell
# Crea tu issue en .claude/issues/
New-Item .claude/issues/SCRUM-15_backend.md
```

### Paso 2: Ejecutar Agente
```powershell
plan-backend SCRUM-15_backend.md
```

### Paso 3: Revisar Output
El script creará:
- `.claude/sessions/context_session_SCRUM-15.md` - Contexto de sesión
- `.claude/doc/SCRUM-15/` - Directorio para planes

### Paso 4: Solicitar Plan a Warp Agent
Copia las instrucciones que muestra el script y pégalas en Warp para que el agente genere el plan detallado.

### Paso 5: Implementar
Una vez generado `.claude/doc/SCRUM-15/backend.md`, úsalo como guía para implementar.

---

## 📂 Estructura de Archivos Resultante

```
.claude/
├── issues/
│   ├── SCRUM-10_backend.md        # Issue original
│   └── SCRUM-10_frontend.md       # Issue frontend
├── sessions/
│   └── context_session_SCRUM-10.md  # Contexto compartido
└── doc/
    └── SCRUM-10/
        ├── backend.md              # Plan backend generado
        ├── frontend.md             # Plan frontend generado
        └── test_cases.md           # Casos de prueba generados
```

---

## 🤖 Interacción con Warp Agent

Cuando ejecutas el script, obtendrás instrucciones como esta:

```
💡 Instrucciones para Warp Agent:

Por favor, actúa como un arquitecto backend experto en Java/Spring con arquitectura hexagonal.

1. Lee el archivo de issue: .claude/issues/SCRUM-10_backend.md
2. Consulta el contexto de sesión: .claude/sessions/context_session_SCRUM-10.md
3. Genera un plan de implementación detallado en: .claude/doc/SCRUM-10/backend.md
```

**Copia y pega estas instrucciones en Warp** para que el agente experto genere el plan.

---

## 💡 Tips

1. **Nombra tus issues consistentemente**: `SCRUM-XX_tipo.md`
2. **Un feature, múltiples archivos**: 
   - `SCRUM-10_backend.md`
   - `SCRUM-10_frontend.md`
   - `SCRUM-10_test.md`
3. **Reutiliza el contexto**: Todos los agentes consultan el mismo `context_session_SCRUM-XX.md`
4. **Iteración**: Puedes regenerar planes ejecutando el script de nuevo

---

## 🐛 Troubleshooting

### Error: "No se encontró el archivo"
- Verifica que el archivo esté en `.claude/issues/`
- Usa el nombre exacto del archivo con extensión `.md`

### El agente no genera el plan
- Asegúrate de copiar las instrucciones completas al agente
- Verifica que el archivo de issue tenga el formato correcto
- Confirma que el contexto de sesión se haya creado

### Permisos en Windows
```powershell
Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy RemoteSigned
```

---

## 📚 Recursos

- [Documentación Arquitectura Hexagonal](./README.md)
- [Reglas de Warp](../.warp/rules/)
- [Ejemplos de Issues](./issues/)

---

*Última actualización: 2025-01-12*
