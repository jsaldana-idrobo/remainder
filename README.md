# Remainder Technical Test App
Esta es una aplicación de ejemplo para un test técnico utilizando Java Spring.

# Descripción
La aplicación está diseñada para calcular el resto de una operación matemática, utilizando los parámetros de entrada proporcionados por el usuario.
Proporciona endpoints REST para realizar cálculos y obtener resultados en formatos JSON o XML.

# Instalación
- Clona este repositorio en tu máquina local: git clone: https://github.com/jsaldana-idrobo/remainder.git
- Abre el proyecto con tu IDE favorito (Recomendado Intellij)
- Espera que compile y descargue dependencias
- Navega hasta RemainderApplication en src > main > java > technical.test.remainder
- Corre la aplicacion
La aplicación se ejecutará en http://localhost:8080.


# Endpoints
- GET /: Retorna un mensaje de saludo.
- GET /remainder: Calcula una operacion matematica que resuelve el algoritmo.

  Ejemplo: GET /remainder?x=7&y=5&n=12345 ~ Respuesta: 12339

- POST /remainder: Calcula la misma operacion matematica para resolver el algoritmo.

  Ejemplo: POST /remainder ~ Headers: [Accept: application/json], Content-Type: application/json ~ { "x": 2, "y": 3, "n": 10 }
  Respuesta: 0



# Explicacion del proyecto
- RemainderController
Controlador principal de la aplicacion donde estan expuesto 3 endpoints. 2 get y 1 post
La primera funcion es solo para testear que la aplicacion corre bien y me muestra un mensaje de bienvenida
La segunda funcion getRemainders recibe en los params de la URL los valores x, y, n para mandarselos al servicio RemainderService y ejecute la funcion calculateRemainder para devolverme el resultado de la operacion.
La tercera funcion postRemainders hace lo mismo que getRemainders pero recibe los parametros a traves del body.

- TestCase
Es la clase que defini para llamar a los objetos que llegan en los endpoints

- RemainderService
Es la interfaz que separa la logica de negocio para posibles llamados en otros controladores o un mejor testeo

- RemainderServiceImpl
Es el servicio que implementa la interfaz anteriormente nombrada, donde esta la logica de negocio.
En este servicio tenemos checkData que se encarga de validar el input acorde a lo explicado en el algoritmo
Y tenemos a la funcion calculateRemainder que es donde aplicamos la formula para la resolucion del algoritmo
((n - y) / x) * x + y es la formula que utilizamos para esta solucion y explico a continuacion:
x: El divisor.
y: El dividendo.
n: El número del cual se quiere calcular el resto.

n - y: Calcula la diferencia entre el número n y el dividendo y.
Es la diferencia entre el número total y la parte que ya ha sido dividida.

((n - y) / x): Divide la diferencia calculada en el paso anterior entre el divisor x.
Esto nos dará cuántas veces el divisor x cabe en la diferencia restante.

((n - y) / x) * x: Multiplica el resultado del paso anterior por el divisor x.
Esto nos dará la parte entera del resultado de la división.

((n - y) / x) * x + y: Al final, agrega el dividendo y al resultado calculado en el paso anterior.
Esto compensa el valor del dividendo que fue restado al principio para calcular la diferencia.
El resultado final es el valor que queda después de realizar la división y obtener el residuo.

En otras palabras, esta fórmula está diseñada para calcular el residuo de la división entre n y x, utilizando y como dividendo.
Es otra forma de obtener el resto de una división sin usar el operador de módulo (%), y funciona para cualquier valor de x, y y n que cumpla con las restricciones establecidas en el input.
Tome esta decision por que es una funcion que ya habia utilizado en el pasado y tiene mejores compatibilidades con posibles lenguajes, el operador % no tiene soporte  para todos los modulos o en ocasiones es menos eficientes.
Y nada, creo que al usar una formula matematica se puede leer un poco mejor para alguien que no sepa que es el operador %.

- ResponseSerializer
Es una clase de utilidades que utilice para serializar la respuesta a JSON o XML segun lo indique en mi endpoint.
Solo verifica si en el header enviado contiene la palabra JSON o XML para ver de que tipo devuelve la respuesta.


# Tests
En este apartado, cabe aclarar que use los test de Junit con Jupiter para mejor la interpretacion del mockito
Son tests muy simples.
En el controlador principal solo valida que la funcion se cumpla, que no se cumpla y posibles resultados de error para cada funcion principal, se realiza la funcion asJsonString para convertir el body enviado en un JSON y no enviar un string {x,y,n}
Solo fue una añadidura, pude haber enviado el string y ya, pero queria darle un manejo mas elegante.
Asi mismo los test para la clase principal, es decir sus funciones equals y hash.
El servicio que se prueba cada funcion igualmente tanto con un resultado valido e invalido.
Y por ultimo el serializer que tuve un par de problemitas a la hora de probar la opcion del catch, pues el mockito no reconocia el catch y tuve que hacer una injeccion del objeto xmlMapper y sobreescribir su funcion en el test para forzar la excepcion.

