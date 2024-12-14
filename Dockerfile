# Usa una imagen base con Java 21 basada en Ubuntu Jammy
FROM eclipse-temurin:21-jdk-jammy

# Define variables de entorno
ENV JPRO_VERSION=2024.4.1

# Instala herramientas necesarias y dependencias para JavaFX
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    unzip \
    maven \
    libpango1.0-0 \
    libgtk-3-0 \
    fonts-dejavu-core \
    pulseaudio \
    alsa-utils && \
    rm -rf /var/lib/apt/lists/*

# Configura PulseAudio para manejar audio
ENV PULSE_SERVER=unix:/run/user/1000/pulse/native

# Crea directorio de trabajo
WORKDIR /app

# Copia el contenido del proyecto al contenedor
COPY . .

# Copia los recursos musicales correctamente
COPY src/main/resources/Musica /app/resources/Musica

# Compila el proyecto usando Maven
RUN mvn clean install

# Expone el puerto usado por JPro
EXPOSE 8080

# Comando para iniciar el servidor JPro
CMD ["mvn", "jpro:run"]











