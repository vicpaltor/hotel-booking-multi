# Claude Agents - Hotel Booking System

Este directorio contiene la estructura para gestionar issues, planes de implementación y documentación generada por agentes especializados.

## Estructura

```
.claude/
├── issues/              # Archivos de issues del proyecto (SCRUM-XX_*.md)
├── sessions/            # Contexto de sesiones por feature
├── doc/                 # Planes de implementación generados
│   └── {feature_name}/
│       ├── backend.md
│       ├── frontend.md
│       ├── test_cases.md
│       ├── ui_analysis.md
│       └── shadcn_ui.md
└── scripts/             # Scripts de automatización
```

## Comandos Disponibles

### `/plan-developer-backend <issue_file>`
Genera un plan de implementación backend basado en arquitectura hexagonal.

**Ejemplo:**
```
/plan-developer-backend SCRUM-10_backend.md
```

### `/plan-developer-frontend <issue_file>`
Genera un plan de implementación frontend basado en React features.

**Ejemplo:**
```
/plan-developer-frontend SCRUM-10_frontend.md
```

### `/plan-test-cases <issue_file>`
Genera definición de casos de prueba exhaustivos.

**Ejemplo:**
```
/plan-test-cases SCRUM-10_test.md
```

## Formato de Issues

Los archivos de issues deben seguir este formato:

```markdown
# [SCRUM-XX] Título de la Issue

## Descripción
[Descripción detallada de la funcionalidad]

## Requisitos Funcionales
- [ ] Requisito 1
- [ ] Requisito 2

## Requisitos Técnicos
- Stack: Java, Spring Boot
- Arquitectura: Hexagonal
- Base de datos: [especificar]

## Criterios de Aceptación
1. Dado [contexto]
   Cuando [acción]
   Entonces [resultado esperado]

## Notas Adicionales
[Información relevante]
```

## Flujo de Trabajo

1. **Crear issue**: Colocar archivo en `.claude/issues/`
2. **Ejecutar agente**: Usar comando apropiado
3. **Revisar plan**: Consultar `.claude/doc/{feature_name}/`
4. **Implementar**: Seguir el plan generado
5. **Validar**: Ejecutar pruebas definidas
