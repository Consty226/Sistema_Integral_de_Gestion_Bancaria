# Sistema Integral de Gestión Bancaria - VisionBank

Este proyecto tiene como objetivo el desarrollo de un software para la gestión interna de las operaciones del banco **VisionBank**. El sistema está diseñado para gestionar clientes, cuentas bancarias, tarjetas de crédito, préstamos, cajas de seguridad, operaciones bancarias y empleados, todo dentro de una estructura escalable, flexible y segura.

## Descripción

El sistema de gestión bancaria **VisionBank** permitirá a los empleados del banco gestionar diversas operaciones bancarias, incluyendo la creación y administración de cuentas, tarjetas de crédito, préstamos, y cajas de seguridad. Además, el sistema debe garantizar el control de acceso para los empleados y registrar todas las operaciones realizadas.

### Funcionalidades

- **Gestión de clientes:**  
  Permite gestionar clientes de tipo persona o empresa, con sus datos personales como nombre, identificación (DNI o CUIT), dirección y teléfonos de contacto. Cada cliente puede tener varias cuentas bancarias, tarjetas de crédito, préstamos y cajas de seguridad.

- **Cuentas bancarias:**  
  Se gestionan cuentas bancarias de diferentes tipos (Cuenta Corriente y Caja de Ahorro). Las cuentas permiten realizar depósitos, extracciones, y transferencias a otras cuentas, respetando las reglas de saldo y descubierto.

- **Tarjetas de crédito:**  
  Gestión de tarjetas de crédito, con atributos como número único, límite de crédito, saldo disponible y la capacidad de registrar consumos y pagos.

- **Préstamos:**  
  Los clientes pueden tener préstamos con un monto otorgado, saldo restante y tasa de interés. El saldo se reduce a medida que se realizan pagos.

- **Cajas de seguridad:**  
  Cada cliente puede tener asignada una caja de seguridad, con un número único y descripción de su contenido. Estas cajas son gestionadas por los empleados del banco.

- **Empleados:**  
  El sistema permite gestionar empleados del banco, que pueden ser cajeros o gerentes. Los cajeros pueden operar depósitos, extracciones y transferencias, mientras que los gerentes gestionan préstamos y cajas de seguridad.

- **Operaciones bancarias:**  
  El sistema registra todas las operaciones bancarias realizadas, como depósitos, extracciones, transferencias, consumos de tarjeta, pagos de tarjeta y pagos de préstamos.

- **Control de acceso:**  
  Los empleados tienen distintos niveles de acceso a los servicios según su rol (cajero o gerente).

## Estructura del Proyecto

### Paquetes

- **modelo:**  
  Contiene las clases que representan los modelos de datos, como `Cliente`, `Cuenta`, `TarjetaDeCredito`, `Prestamo`, `CajaDeSeguridad`, y `Empleado`.

- **servicio:**  
  Contiene las clases que gestionan la lógica de negocio, como la creación y gestión de clientes, cuentas, préstamos, tarjetas, etc.

- **vista:**  
  Contiene las clases encargadas de la interacción con el usuario (en este caso, por consola).

- **controlador:**  
  Contiene las clases que gestionan la comunicación entre la vista y el modelo, facilitando la interacción entre los componentes del sistema.

## Requisitos

- **Java 11 o superior.**

## Instalación

1. Clona este repositorio en tu máquina local:

   ```bash
   git clone https://github.com/tuusuario/visionbank.git
