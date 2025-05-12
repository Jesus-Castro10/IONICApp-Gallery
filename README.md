# 📸 Mi Galería - Ionic + Android Widget

Una aplicación híbrida desarrollada con **Ionic Framework** (Angular) y un **widget nativo en Android (Java)** que permite al usuario capturar, guardar y visualizar imágenes de forma elegante.

---

## ✨ Características

- 📷 Captura o selección de imágenes desde la galería
- 📝 Agrega descripciones personalizadas
- 🗂️ Visualización en **lista** o **cuadrícula**
- 🔍 Búsqueda por texto en tiempo real
- 🧽 Eliminación individual de imágenes
- 🧭 Navegación entre lista y detalle
- 📲 **Widget Android nativo** que muestra imágenes actualizadas automáticamente
- ⚡ Integración con `@capacitor/preferences` para compartir datos entre app y widget

---

## 📱 Tecnologías usadas

- **Ionic Framework (Angular)**
- **Capacitor**
- **Capacitor Preferences**
- **Firebase/Supabase** (opcional para persistencia)
- **Java + XML** para widget nativo
- Diseño moderno: `Poppins` font, estilos limpios y animaciones sutiles

---

Características:
- Muestra una imagen actualizada desde la app
- Incluye un texto descriptivo
- Refresca automáticamente con `updatePeriodMillis`
- Estilo limpio con borde redondeado (`drawable/widget_background.xml`)

---

## ⚙️ Instalación

```
npm install
npx cap sync
ionic build
npx cap copy android
npx cap open android
```

