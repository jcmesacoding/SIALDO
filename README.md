# Sistema de Alquiler Libre 🏠

Este es un sistema de gestión de alquileres desarrollado en **JavaFX**, que permite manejar múltiples roles de usuario (ADMIN, SUPERVISOR, CLIENTE) con una interfaz gráfica moderna, conexión a base de datos PostgreSQL, y control de sesión.

---

## 🚀 Características principales

- 🔐 Inicio de sesión con roles (ADMIN, SUPERVISOR, CLIENTE).
- 🎨 Interfaz gráfica moderna usando **FXML + CSS personalizado**.
- 📁 Contenido dinámico que se adapta al rol desde un layout principal (`inicio.fxml`).
- 🔄 Navegación modular desde un sidebar.
- 💾 Conexión a **PostgreSQL** para datos reales.
- 🔐 Gestión de sesión de usuario con `SessionManager`.

---

## 🧑‍🤝‍🧑 Roles y funcionalidades

| Rol        | Funciones principales |
|------------|-----------------------|
| **ADMIN**      | Gestión de usuarios, estadísticas, configuraciones del sistema. |
| **SUPERVISOR** | Panel de monitoreo y operaciones. |
| **CLIENTE**    | Visualización de pagos, deudas y detalles personales. |

---

## 🛠 Tecnologías utilizadas

- **Java 17+**
- **JavaFX SDK** (v24+)
- **FXML** + **CSS personalizado**
- **PostgreSQL 15+**
- **JDBC Driver** para PostgreSQL
- **Scene Builder** (opcional)
- **Maven/Gradle** (si lo usas)

---

## 🧩 Estructura del proyecto

![image](https://github.com/user-attachments/assets/d75c309c-2d25-4e6d-9c47-3533f8d80dab)

---

## 🛢 Configuración de la base de datos

1. Instala **PostgreSQL** y crea una base de datos, por ejemplo:

CREATE DATABASE alquilerdb;

2. Crea la tabla de usuarios:

![image](https://github.com/user-attachments/assets/bec80eff-0c42-4f47-a380-e8889205afc1)

3. Inserta algunos datos de prueba:

![image](https://github.com/user-attachments/assets/185461d6-01fd-40b6-b674-38f4d13784f3)

Si usas Maven, añade:

xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.1</version>
</dependency>

Si no, descarga el .jar desde https://jdbc.postgresql.org y añádelo manualmente al classpath.

## 🔐 Credenciales de prueba
Usuario: admin      | Contraseña: 123

Usuario: supervisor | Contraseña: 456

Usuario: cliente    | Contraseña: 789

## 🧪 Cómo ejecutar el proyecto

- Asegúrate de tener JavaFX configurado en tu IDE (ej. IntelliJ).

- Verifica la conexión a PostgreSQL (credenciales, puerto, etc).

- Corre Main.java para lanzar la aplicación.

- (Opcional) Configura VM Options:
--module-path /ruta/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml

## 📌 Futuras mejoras

- Validación avanzada de formularios.

- Panel CRUD para usuarios y contratos.

- Dashboard con estadísticas visuales.

- Soporte multilenguaje.

- Seguridad con hash de contraseñas (BCrypt o similar).

## 👨‍💻 Autor
Juan Carlos Mesa – @jcmesacoding

Email: mesacruzjc@gmail.com

¡Gracias por probar el sistema! 💙 Si te parece útil, considera dejar una estrella ⭐
