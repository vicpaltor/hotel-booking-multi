# 🚀 Quick Start - Sistema de Comandos de Agentes

## Instalación Rápida

### 1. Ejecuta el Script de Ejemplo

```powershell
.\.claude\scripts\plan-developer-backend.ps1 SCRUM-10_backend.md
```

### 2. Resultado Esperado

Verás un banner como este:

```
╔════════════════════════════════════════════════════════════════╗
║          🏗️  Backend Developer Agent - Hexagonal Arch         ║
╚════════════════════════════════════════════════════════════════╝

📋 Procesando issue: SCRUM-10_backend.md
🎯 Feature detectado: SCRUM-10

✅ Setup Completado
```

### 3. Copia las Instrucciones

El script te dará instrucciones específicas para Warp Agent:

```
💡 Instrucciones para Warp Agent:

Por favor, actúa como un arquitecto backend experto en Java/Spring 
con arquitectura hexagonal.

1. Lee el archivo de issue: .claude\issues\SCRUM-10_backend.md
2. Consulta el contexto de sesión: .claude\sessions\context_session_SCRUM-10.md
3. Genera un plan de implementación detallado en: .claude/doc/SCRUM-10/backend.md
```

### 4. Pega en Warp y Ejecuta

**Copia todo el bloque de instrucciones** y pégalo aquí en Warp Agent. El agente:

1. ✅ Leerá el archivo de issue detallado
2. ✅ Consultará el contexto de sesión
3. ✅ Analizará los requisitos
4. ✅ Generará un plan completo de implementación
5. ✅ Lo guardará en `.claude/doc/SCRUM-10/backend.md`

---

## 📋 Estructura Creada

```
hotel-booking-multi/
└── .claude/
    ├── README.md                        # Documentación general
    ├── USAGE.md                         # Guía detallada de uso
    ├── QUICKSTART.md                    # Esta guía
    ├── issues/
    │   └── SCRUM-10_backend.md         # Issue de ejemplo (ya creado)
    ├── sessions/
    │   └── context_session_SCRUM-10.md # Contexto (ya creado)
    ├── doc/
    │   └── SCRUM-10/                   # Planes generados aquí
    └── scripts/
        └── plan-developer-backend.ps1  # Script principal
```

---

## 🎯 Tu Primer Comando

### Opción A: Usa el Ejemplo SCRUM-10

Ya tienes todo listo con el ejemplo `SCRUM-10_backend.md`:

```powershell
# 1. Ejecuta el script
.\.claude\scripts\plan-developer-backend.ps1 SCRUM-10_backend.md

# 2. Copia las instrucciones que aparecen
# 3. Pégalas en Warp Agent
# 4. El agente generará el plan en .claude/doc/SCRUM-10/backend.md
```

### Opción B: Crea tu Propia Issue

```powershell
# 1. Crea tu archivo de issue
New-Item .claude/issues/SCRUM-15_backend.md

# 2. Edítalo con tu contenido (usa SCRUM-10_backend.md como template)
code .claude/issues/SCRUM-15_backend.md

# 3. Ejecuta el script
.\.claude\scripts\plan-developer-backend.ps1 SCRUM-15_backend.md

# 4. Sigue las instrucciones
```

---

## 💡 Crear Alias (Opcional pero Recomendado)

Para usar comandos cortos como `plan-backend`:

```powershell
# 1. Abre tu perfil de PowerShell
notepad $PROFILE

# 2. Agrega esta línea:
function plan-backend { .\.claude\scripts\plan-developer-backend.ps1 $args }

# 3. Guarda y reinicia PowerShell

# 4. Ahora puedes usar:
plan-backend SCRUM-10_backend.md
```

---

## 🔥 Comandos Útiles

```powershell
# Ver issues disponibles
Get-ChildItem .claude/issues/*.md

# Ver planes generados
Get-ChildItem .claude/doc -Recurse

# Ver contexto de una feature
Get-Content .claude/sessions/context_session_SCRUM-10.md

# Ver el plan generado
Get-Content .claude/doc/SCRUM-10/backend.md

# Ejecutar el script de nuevo (regenera el plan)
.\.claude\scripts\plan-developer-backend.ps1 SCRUM-10_backend.md
```

---

## ✅ Checklist de Verificación

Después de ejecutar el script, deberías tener:

- [x] `.claude/issues/SCRUM-10_backend.md` existe
- [x] `.claude/sessions/context_session_SCRUM-10.md` creado
- [x] `.claude/doc/SCRUM-10/` directorio creado
- [ ] `.claude/doc/SCRUM-10/backend.md` generado por Warp Agent

---

## 🤖 Ejemplo Completo de Interacción

### Terminal (tú)
```powershell
PS> .\.claude\scripts\plan-developer-backend.ps1 SCRUM-10_backend.md
```

### Script Output
```
🎯 Feature detectado: SCRUM-10
✅ Setup Completado

💡 Instrucciones para Warp Agent:
[... instrucciones detalladas ...]
```

### Tú copias y pegas en Warp Agent
```
Por favor, actúa como un arquitecto backend experto...
1. Lee el archivo de issue: .claude\issues\SCRUM-10_backend.md
2. Consulta el contexto de sesión: ...
3. Genera un plan de implementación...
```

### Warp Agent
```
[Lee los archivos]
[Analiza los requisitos]
[Genera el plan]
✅ He creado el plan en .claude/doc/SCRUM-10/backend.md
```

---

## 📚 Próximos Pasos

1. **Lee el plan generado**: `.claude/doc/SCRUM-10/backend.md`
2. **Revisa la estructura propuesta**: Domain, Application, Infrastructure
3. **Implementa siguiendo el plan**: Usa el plan como guía de implementación
4. **Genera pruebas**: Crea `SCRUM-10_test.md` y ejecuta el script de tests
5. **Itera**: Si necesitas cambios, actualiza la issue y regenera el plan

---

## 🆘 Soporte

- **Documentación completa**: [.claude/USAGE.md](.claude/USAGE.md)
- **Arquitectura general**: [.claude/README.md](.claude/README.md)
- **Issue de ejemplo**: [.claude/issues/SCRUM-10_backend.md](.claude/issues/SCRUM-10_backend.md)

---

## 🎉 ¡Listo!

Ahora tienes un sistema completo de agentes para generar planes de implementación. 

**Tu flujo de trabajo será:**
1. 📝 Crear issue → 
2. 🤖 Ejecutar script → 
3. 📋 Copiar instrucciones → 
4. 🚀 Warp Agent genera plan → 
5. ⚡ Implementar

---

*Sistema creado: 2025-01-12*
*Versión: 1.0.0*
