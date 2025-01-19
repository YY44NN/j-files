# Curso Intensivo de Bases de Datos Relacionales y SQL
#		Impulso Informatico -- Derechos Reservados 2024

## Introducción
Este curso te llevará a través de una historia de desarrollo de una base de datos para una tienda ficticia. Aprenderemos conceptos clave de bases de datos relacionales, teoría de conjuntos y SQL, todo interconectado en un caso práctico que simula los desafíos y decisiones reales al gestionar datos en un entorno empresarial.

Imagina que eres parte del equipo de desarrollo de software para una tienda llamada **Tienda XYZ**. Tu tarea será diseñar y administrar su base de datos, desde la teoría hasta la implementación.

El software que utilizaremos será **DBeaver**, una herramienta gráfica que nos permitirá interactuar con la base de datos **MySQL**, el motor de base de datos elegido por su robustez y simplicidad.

---

## **TEMA 1: Fundamentos Teóricos de Bases de Datos**

### **1. Teoría de Conjuntos**

#### Contexto en la Historia
La tienda **Tienda XYZ** está organizando sus datos iniciales. Los propietarios quieren analizar sus clientes y productos para identificar tendencias. Tienen dos listas:
- **Clientes registrados:** Personas que han comprado algo alguna vez.
- **Clientes activos:** Personas que realizaron una compra en los últimos 30 días.

Quieren saber:
- ¿Quiénes son todos los clientes?
- ¿Quiénes son los clientes activos?
- ¿Quiénes no han comprado recientemente?

#### Explicación y Analogía
La teoría de conjuntos nos ayuda a resolver este tipo de problemas. Un conjunto es una colección de elementos únicos. En este caso:
- Conjunto A: Clientes registrados.
- Conjunto B: Clientes activos.

Operaciones comunes en conjuntos:
1. **Unión (\(A \cup B\))**: Combina todos los elementos de dos conjuntos eliminando duplicados.
   - Analogía: Combinar ambas listas para tener un panorama general de los clientes.
   - Ejemplo: Si A = \{Juan, Ana\} y B = \{Ana, Luis\}, entonces \(A \cup B = \{Juan, Ana, Luis\}\).

2. **Intersección (\(A \cap B\))**: Encuentra los elementos comunes entre dos conjuntos.
   - Analogía: Identificar los clientes que están registrados y son activos.
   - Ejemplo: \(A \cap B = \{Ana\}\).

3. **Diferencia (\(A - B\))**: Devuelve los elementos que están en el primer conjunto pero no en el segundo.
   - Analogía: Encontrar clientes registrados que no son activos.
   - Ejemplo: \(A - B = \{Juan\}\).

#### Presentación de Sentencias SQL
Antes de implementar estas operaciones en SQL, presentamos las sentencias necesarias:
1. **`CREATE TABLE`**: Crea una tabla nueva.
   - Sintaxis:
     ```sql
     CREATE TABLE nombre_tabla (
         columna1 TIPO_DATO,
         columna2 TIPO_DATO
     );
     ```
   - Ejemplo:
     ```sql
     CREATE TABLE ClientesRegistrados (Nombre VARCHAR(50));
     ```

2. **`INSERT INTO`**: Inserta datos en una tabla.
   - Sintaxis:
     ```sql
     INSERT INTO nombre_tabla (columna1, columna2) VALUES (valor1, valor2);
     ```
   - Ejemplo:
     ```sql
     INSERT INTO ClientesRegistrados (Nombre) VALUES ('Juan');
     ```

3. **`SELECT` con `UNION`**: Combina los resultados de dos consultas eliminando duplicados.
   - Sintaxis:
     ```sql
     SELECT columna FROM tabla1
     UNION
     SELECT columna FROM tabla2;
     ```
   - Ejemplo:
     ```sql
     SELECT Nombre FROM ClientesRegistrados
     UNION
     SELECT Nombre FROM ClientesActivos;
     ```

4. **`SELECT` con `IN` y `NOT IN`**: Encuentra elementos presentes o ausentes en otro conjunto.
   - Sintaxis:
     ```sql
     SELECT columna FROM tabla1 WHERE columna IN (subconsulta);
     ```
   - Ejemplo:
     ```sql
     SELECT Nombre FROM ClientesRegistrados
     WHERE Nombre NOT IN (SELECT Nombre FROM ClientesActivos);
     ```

#### Implementación en SQL
Crearemos las tablas "ClientesRegistrados" y "ClientesActivos", y luego aplicaremos las operaciones de conjuntos.
```sql
CREATE TABLE ClientesRegistrados (Nombre VARCHAR(50));
CREATE TABLE ClientesActivos (Nombre VARCHAR(50));

INSERT INTO ClientesRegistrados (Nombre) VALUES ('Juan'), ('Ana'), ('Carlos');
INSERT INTO ClientesActivos (Nombre) VALUES ('Ana'), ('Luis');

-- Unión: Todos los clientes
SELECT Nombre FROM ClientesRegistrados
UNION
SELECT Nombre FROM ClientesActivos;

-- Intersección: Clientes activos registrados
SELECT Nombre FROM ClientesRegistrados
WHERE Nombre IN (SELECT Nombre FROM ClientesActivos);

-- Diferencia: Clientes registrados no activos
SELECT Nombre FROM ClientesRegistrados
WHERE Nombre NOT IN (SELECT Nombre FROM ClientesActivos);
```

#### Ejercicio Práctico 1
1. Inserta datos adicionales:
   - Registrados: Marta, Luis.
   - Activos: Marta, Carlos.
2. Responde:
   - Lista de todos los clientes (unión).
   - Clientes activos registrados (intersección).
   - Clientes registrados no activos (diferencia).

#### Ejercicios Adicionales:
1. Agrega una tabla "ClientesInactivos" basada en los resultados de la diferencia.
2. Encuentra clientes que están activos pero no registrados.
3. Combina los resultados en una tabla "ClientesCompletos" usando `UNION`.

---

### **2. Conceptos de Bases de Datos Relacionales**

#### Contexto en la Historia
**Tienda XYZ** decide organizar sus datos en tablas: una para los clientes, otra para los productos y una tercera para los pedidos. Cada cliente debe tener un identificador único (ID) para rastrear sus compras, y los productos también tendrán un ID único.

#### Explicación y Analogía
Imagina que cada tabla es como una hoja de cálculo en Excel:
- **Clientes:** Lista de todas las personas que compran en la tienda.
- **Productos:** Catálogo de artículos disponibles para la venta.
- **Pedidos:** Registro de qué cliente compró qué producto y en qué fecha.

Cada fila en una tabla representa un registro único, como un cliente o un producto. Las columnas representan atributos, como el nombre del cliente o el precio del producto.

#### Componentes Clave
1. **Clave primaria (Primary Key):**
   - Analogía: Es como el número de seguro social de una persona: identifica de manera única a cada registro.
   - Ejemplo: En la tabla "Clientes", el ID del cliente sería la clave primaria.

2. **Clave foránea (Foreign Key):**
   - Analogía: Es como una referencia cruzada entre tablas.
   - Ejemplo: En la tabla "Pedidos", el ID del cliente sería una clave foránea que apunta al registro correspondiente en la tabla "Clientes".

#### Ejercicio Práctico 2
Crea las tablas para **Clientes**, **Productos** y **Pedidos**:
```sql
CREATE TABLE Clientes (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100),
    Ciudad VARCHAR(50)
);

CREATE TABLE Productos (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100),
    Precio DECIMAL(10,2)
);

CREATE TABLE Pedidos (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ClienteID INT,
    ProductoID INT,
    Fecha DATE,
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ID),
    FOREIGN KEY (ProductoID) REFERENCES Productos(ID)
);
```

#### Ejercicios Adicionales
1. Agrega un campo "Correo" a la tabla "Clientes" y asegura que sea único.
2. Crea un índice en la tabla "Productos" para búsquedas rápidas por nombre.
3. Implementa un campo "Cantidad" en la tabla "Pedidos" para registrar las unidades compradas.

---

## **TEMA 2: Consultas y Operaciones CRUD Básicas**

### **1. Consultas con JOIN**

#### Presentación de Sentencia `JOIN`
El comando `JOIN` permite combinar datos de dos o más tablas basándose en una relación entre ellas.
- **`INNER JOIN`**: Devuelve solo las filas que tienen coincidencias en ambas tablas.
- **`LEFT JOIN`**: Devuelve todas las filas de la tabla izquierda, y las filas coincidentes de la derecha (o NULL si no hay coincidencia).

#### Ejemplo:
Imagina que en **Tienda XYZ** queremos saber qué productos han comprado los clientes y cuándo lo hicieron. Para obtener esta información, usamos `JOIN`:
```sql
-- Obtener información completa de los pedidos
SELECT Pedidos.ID AS PedidoID, Clientes.Nombre AS Cliente, Productos.Nombre AS Producto, Pedidos.Fecha
FROM Pedidos
INNER JOIN Clientes ON Pedidos.ClienteID = Clientes.ID
INNER JOIN Productos ON Pedidos.ProductoID = Productos.ID;
```

#### Ejercicio Práctico 1: Consultas con JOIN
1. Encuentra todos los pedidos realizados junto con el nombre del cliente y el producto.
2. Usa un `LEFT JOIN` para listar todos los clientes, incluso si no han realizado pedidos.
3. Modifica el ejemplo para incluir el precio del producto en la consulta.

---

### **2. Operaciones CRUD Básicas**

Las operaciones CRUD (Create, Read, Update, Delete) son las más comunes en la administración de bases de datos. Son esenciales para cualquier aplicación que interactúe con datos.

#### **CREATE**: Agregar nuevos registros
La operación `CREATE` se realiza mediante el comando `INSERT INTO`. Esta operación se usa para añadir nuevos registros a una tabla.
- **Ejemplo:**
```sql
-- Agregar un nuevo cliente
INSERT INTO Clientes (Nombre, Ciudad) VALUES ('Luis', 'Guadalajara');
```

#### Ejercicio Práctico 2: CREATE
1. Agrega un nuevo cliente "María" de Monterrey.
2. Inserta un producto "Pluma" con un precio de $15.00.
3. Registra un pedido de "María" para comprar una "Pluma" el 2024-01-01.

---

#### **READ**: Consultar registros
La operación `READ` se realiza mediante el comando `SELECT`. Esta operación se usa para recuperar información de una tabla.
- **Ejemplo:**
```sql
-- Leer todos los clientes
SELECT * FROM Clientes;

-- Leer productos con precio mayor a $20
SELECT * FROM Productos WHERE Precio > 20;
```

#### Ejercicio Práctico 3: READ
1. Consulta todos los pedidos realizados por "María".
2. Lista todos los productos cuyo precio sea menor a $50.
3. Encuentra el cliente que realizó el pedido más reciente.

---

#### **UPDATE**: Modificar registros existentes
La operación `UPDATE` permite modificar uno o más registros en una tabla.
- **Ejemplo:**
```sql
-- Actualizar la ciudad de un cliente
UPDATE Clientes SET Ciudad = 'Monterrey' WHERE Nombre = 'Luis';

-- Incrementar el precio de un producto
UPDATE Productos SET Precio = Precio + 5 WHERE Nombre = 'Pluma';
```

#### Ejercicio Práctico 4: UPDATE
1. Cambia el nombre del cliente "Luis" a "Luis Pérez".
2. Incrementa el precio de todos los productos en un 10%.
3. Actualiza la fecha de un pedido al 2024-01-05.

---

#### **DELETE**: Eliminar registros
La operación `DELETE` elimina registros de una tabla. **¡Cuidado con olvidar el `WHERE`!** Sin esta cláusula, se eliminarán todos los registros.
- **Ejemplo:**
```sql
-- Eliminar un cliente
DELETE FROM Clientes WHERE Nombre = 'Luis Pérez';

-- Eliminar productos con precio mayor a $100
DELETE FROM Productos WHERE Precio > 100;
```

#### **Tip Anecdótico:**
En una ocasión, un desarrollador olvidó usar `WHERE` en un `DELETE`:
```sql
DELETE FROM Pedidos;
```
Resultado: ¡Se borraron todos los pedidos de la tienda! Este error, conocido como "El Gran Borrón", enseña la importancia de siempre verificar las condiciones antes de ejecutar el comando.

#### Ejercicio Práctico 5: DELETE
1. Elimina el cliente "María".
2. Borra los pedidos realizados antes del 2024-01-01.
3. Elimina los productos cuyo precio sea menor a $10.


Referencias introductorias:

Solo los videos del 1 al 12:
https://www.youtube.com/watch?v=KmcRMlv9_T4&list=PLeySRPnY35dHACeGz_7oiU5Wo11AUt964&index=1


Solo los videos del 1 al 11:
https://www.youtube.com/watch?v=giqlIGRG0RY&list=PL6kQim6ljTJsL9mC1dE8GDoZmFxtLU_bh&index=1