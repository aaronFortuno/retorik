# Documento de Requisitos - Retorik

## 1. Introducción

Este documento describe los requisitos funcionales y no funcionales de la aplicación "Retorik", una herramienta para practicar la retórica y habilidades de hablar en público.

## 2. Objetivos

*   Proporcionar un entorno de juego para practicar la retórica.
*   Dirigido a estudiantes de primaria, secundaria y adultos.
*   Fomentar la expresión oral y la improvisación.

## 3. Funcionalidades Principales

*   **Selección de Tema:**
    *   Listado de temas organizados por dificultad.
    *   Opción de elegir un tema al azar o seleccionar entre 2-3 opciones.
    *   Modo "adulto" con temas y frases más "picantes" (opcional).
*   **Presentación del Tema:**
    *   Mostrar entre 1 y 3 frases/citas célebres relacionadas con el tema (configurable por dificultad).
    *   Temporizador configurable para limitar el tiempo de presentación.
    *   Interfaz clara y concisa con el tema, frases y temporizador.
*   **Evaluación:**
    *   Mecanismo para que otros jugadores valoren la presentación (no especificado en detalle).
    *   Resumen al final del tiempo con puntuación (opcional).
*   **Administración:**
    *   Interfaz para añadir, modificar y eliminar temas y frases.
    *   Importación/exportación de temas y frases en formato CSV.
*   **Internacionalización:**
    *   Soporte para catalán, castellano e inglés.
    *   Posibilidad de añadir frases en cualquiera de estos idiomas.
*   **Configuración:**
    *   Landing page para elegir dificultad, tiempo e idioma.
    *   Tutorial o información del juego accesible desde la landing page.

## 4. Requisitos No Funcionales

*   **Plataformas:**
    *   Android
    *   Escritorio (JVM)
    *   Web (WASM/JS)
*   **Interfaz de Usuario:**
    *   Sencilla e intuitiva.
    *   Adaptable a diferentes tamaños de pantalla (móvil, escritorio, web).
    *   Una sola pantalla de juego sin scroll.
    *   Interfaz de administración como página separada o pop-up.
*   **Almacenamiento:**
    *   Almacenamiento local in-app (Room).
    *   Importación/exportación de datos para facilitar la migración entre dispositivos.
*   **Internacionalización:**
    *   Soporte para catalán, castellano e inglés.
*   **Accesibilidad:**
    *   Opción para modificar el tamaño del texto (opcional).
*   **Rendimiento:**
    *   Aplicación ágil y con buena respuesta.
    *   Optimización del tamaño de la base de datos.
*   **Arquitectura:**
    *   Escalable y multiplataforma.
    *   Basada en Jetpack Compose.
    *   MVVM con StateFlow.
    *   Clean Architecture.

## 5. Casos de uso (Use Cases)

### 1. Gestión de Temas
- GetRandomTopicUseCase:
  - Descripción: Obtiene un tema aleatorio de la base de datos.
  - Entrada: isSpicy (apto para adultos), difficulty (nivel de dificultad), language (idioma).
  - Salida: Topic (el tema aleatorio).
- GetTopicByIdUseCase:
  - Descripción: Obtiene un tema por su ID.
  - Entrada: id (ID del tema).
  - Salida: Topic (el tema).
- GetAllTopicsUseCase:
  - Descripción: Obtiene todos los temas de la base de datos.
  - Entrada: Ninguna.
  - Salida: List<Topic> (lista de temas).
- GetAllTopicsByDifficultyUseCase:
  - Descripción: Obtiene todos los temas de la base de datos para un nivel de dificultad dado.
  - Entrada: Difficulty (nivel de dificultad).
  - Salida: List<Topic> (lista de temas).
- AddTopicUseCase:
  - Descripción: Añade un nuevo tema a la base de datos.
  - Entrada: Topic (el tema a añadir).
  - Salida: Boolean (true si se añadió correctamente, false si no).
- UpdateTopicUseCase:
  - Descripción: Modifica un tema existente en la base de datos.
  - Entrada: Topic (el tema a modificar).
  - Salida: Boolean (true si se modificó correctamente, false si no).
- DeleteTopicUseCase:
  - Descripción: Elimina un tema de la base de datos.
  - Entrada: id (ID del tema a eliminar).
  - Salida: Boolean (true si se eliminó correctamente, false si no).

### 2. Gestión de Frases
-GetPhrasesForDifficultyUseCase:
  - Descripción: Obtiene una lista de frases asociadas a un nivel de dificultad específico.
  - Entrada: Difficulty (nivel de dificultad).
  - Salida: List<Phrase> (lista de frases).
- AddPhraseUseCase:
  - Descripción: Añade una nueva frase a la base de datos.
  - Entrada: Phrase (la frase a añadir).
  - Salida: Boolean (true si se añadió correctamente, false si no).
- UpdatePhraseUseCase:
  - Descripción: Modifica una frase existente en la base de datos.
  - Entrada: Phrase (la frase a modificar).
  - Salida: Boolean (true si se modificó correctamente, false si no).
- DeletePhraseUseCase:
  - Descripción: Elimina una frase de la base de datos.
  - Entrada: id (ID de la frase a eliminar).
  - Salida: Boolean (true si se eliminó correctamente, false si no).

### 3. Importación/Exportación
- ImportThemesFromCsvUseCase:
  - Descripción: Importa temas y frases desde un archivo CSV. 
  - Entrada: file (archivo CSV). 
  - Salida: Boolean (true si se importó correctamente, false si no). 
- ExportThemesToCsvUseCase:
  - Descripción: Exporta temas y frases a un archivo CSV.
  - Entrada: file (archivo CSV).
  - Salida: Boolean (true si se exportó correctamente, false si no).

### 4. Configuración
- GetSettingsUseCase:
  - Descripción: Obtiene la configuración de la aplicación (idioma, dificultad por defecto, etc.).
  - Entrada: Ninguna.
  - Salida: Settings (objeto con la configuración). 
- SaveSettingsUseCase:
  - Descripción: Guarda la configuración de la aplicación.
  - Entrada: Settings (objeto con la configuración).
  - Salida: Boolean (true si se guardó correctamente, false si no).

## 5. Arquitectura

Basada en MVVM (Model-View-ViewModel) con StateFlow para la gestión de estado, y Clean Architecture para la separación de responsabilidades.

*   **`commonMain`:**
    *   **`domain`:** Capa con casos de uso (use cases) que definen la lógica de negocio.
    *   **`data`:** Capa con repositorios que abstraen el acceso a los datos (Room, archivos CSV).
    *   **`model`:** Clases de datos (data classes) que representan los temas, frases, etc.
    *   **`presentation`:** ViewModels que gestionan el estado de la UI y exponen `StateFlow`s para que la View observe.
*   **`androidApp`, `desktopApp`, `webApp`:**
    *   Views (Composables) que observan los `StateFlow`s de los ViewModels y renderizan la UI.
    *   Implementaciones específicas de plataforma si es necesario.

**Tecnologías:**

*   **Base de datos local:** Room (KMP).
*   **Inyección de dependencias:** Kodein.
*   **Navegación:** StateFlow.

## 6. Modelos de datos

data class Topic(
    val id: Long = 0,
    val name: String,
    val difficulty: Difficulty,
    val language: Language,
    val isSpicy: Boolean = false
)

data class Phrase(
    val id: Long = 0,
    val text: String,
    val difficulty: Difficulty,
    val language: Language,
    val isSpicy: Boolean = false
)

enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}

enum class Language {
    CATALAN,
    SPANISH,
    ENGLISH
}

data class Settings(
    val language: Language = Language.CATALAN,
    val difficulty: Difficulty = Difficulty.MEDIUM
)

## 6. Estructura de carpetas

Retorik/
├── androidApp/
├── desktopApp/
├── webApp/
└── shared/
    ├── src/
    │   ├── commonMain/
    │   │   ├── kotlin/
    │   │   │   └── com/example/retorik/
    │   │   │       ├── domain/
    │   │   │       │   ├── usecases/
    │   │   │       │   │   ├── GetRandomThemeUseCase.kt
    │   │   │       │   │   └── ...
    │   │   │       ├── data/
    │   │   │       │   ├── repository/
    │   │   │       │   │   ├── ThemeRepository.kt
    │   │   │       │   │   └── ...
    │   │   │       │   ├── local/
    │   │   │       │   │   ├── database/
    │   │   │       │   │   │   ├── AppDatabase.kt
    │   │   │       │   │   │   └── ...
    │   │   │       │   │   ├── dao/
    │   │   │       │   │   │   ├── ThemeDao.kt
    │   │   │       │   │   │   └── ...
    │   │   │       │   ├── csv/
    │   │   │       │   │   ├── CsvImporter.kt
    │   │   │       │   │   └── ...
    │   │   │       ├── model/
    │   │   │       │   ├── Theme.kt
    │   │   │       │   ├── Phrase.kt
    │   │   │       │   └── ...
    │   │   │       ├── presentation/
    │   │   │       │   ├── viewmodel/
    │   │   │       │   │   ├── GameViewModel.kt
    │   │   │       │   │   └── ...
    │   │   │       │   ├── state/
    │   │   │       │   │   ├── GameState.kt
    │   │   │       │   │   └── ...
    │   │   │       ├── di/
    │   │   │       │   ├── AppModule.kt
    │   │   │       │   └── ...
    │   │   │       ├── ui/
    │   │   │       │   ├── components/
    │   │   │       │   │   ├── ThemeCard.kt
    │   │   │       │   │   └── ...
    │   │   │       │   ├── screens/
    │   │   │       │   │   ├── GameScreen.kt
    │   │   │       │   │   └── ...
    │   │   │       ├── App.kt  (Composable principal)
    │   │   │       └── Platform.kt
    │   ├── androidMain/
    │   ├── desktopMain/
    │   └── jsMain/
    └── build.gradle.kts

### Explicación de las carpetas:

- domain: Contiene los casos de uso (use cases) que definen la lógica de negocio de la aplicación. Los casos de uso son independientes de cualquier framework o tecnología específica.
- data: Contiene los repositorios que abstraen el acceso a los datos. Los repositorios pueden acceder a datos de una base de datos local (Room), archivos CSV, o cualquier otra fuente de datos.
- model: Contiene las clases de datos (data classes) que representan los temas, frases, etc.
- presentation: Contiene los ViewModels que gestionan el estado de la UI y exponen StateFlows para que la View observe. También contiene las clases de estado (state classes) que definen el estado de la UI.
- di: Contiene los módulos de Kodein para la inyección de dependencias.
- ui: Contiene los Composables que definen la interfaz de usuario. Se divide en components (Composables reutilizables) y screens (Composables que representan pantallas completas).

## 7. Próximos Pasos

1.  Refinar este documento de requisitos.
2.  Definir los casos de uso (use cases) principales.
3.  Crear los modelos de datos (data classes).
4.  Implementar los repositorios (acceso a la base de datos local).
5.  Crear los ViewModels y los `StateFlow`s.
6.  Implementar las Views (Composables) en cada plataforma.