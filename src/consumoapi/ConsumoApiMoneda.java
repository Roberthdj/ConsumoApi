package consumoapi;

/*
Para poder trabajar debe agregar la dependencia(libreria) json-20220320.jar a su proyecto.

Dependencia requerida para trabajar con Json: https://mvnrepository.com/artifact/org.json/json/20220320
Descarga directa: https://repo1.maven.org/maven2/org/json/json/20220320/json-20220320.jar
 */
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class ConsumoApiMoneda {

    public static void main(String[] args) {

        String urlApi = "https://api.apilayer.com/fixer/latest?base=COP&symbols=USD,EUR,GBP,JPY,KRW";

        try {
            // creaando la url
            URL url = new URL(urlApi);
            // creando la conexion a la url y abriendola
            HttpURLConnection urlConexion = (HttpURLConnection) url.openConnection();
            // agregando Headers con datos de seguridad para ingresar a la url
            urlConexion.setRequestProperty("apikey", "rl5nCfpm6dB4CeSpJggv420pKdAcwvQo");
            // agregando el metodo para la peticion
            urlConexion.setRequestMethod("GET");
            // conectando con la url
            urlConexion.connect();

            // obteniendo el codigo de respuesta http
            int responseCode = urlConexion.getResponseCode();

            if (responseCode != 200) {

                throw new RuntimeException("Ha ocurrido un error " + responseCode);

            } else {

                // creando clase para almacenar flujo de datos entrante
                StringBuilder almacenDatos = new StringBuilder();
                // recojiendo flujo de datos entrante
                Scanner flujoDatos = new Scanner(urlConexion.getInputStream());

                // almacenando el flujo de datos entrante  recojido por el Scanner en StringBuilder
                while (flujoDatos.hasNext()) {

                    almacenDatos.append(flujoDatos.nextLine());

                }

                // cerrando el flujo de datos
                flujoDatos.close();

                // convirtiendo la informacion del StringBuilder en un Json con ayuda de la clase JSONObject
                JSONObject jsonObject = new JSONObject(almacenDatos.toString());

                // Asignando los valores de las claves requeridas a una variable
                double dolar = jsonObject.getJSONObject("rates").getDouble("USD"),
                        euro = jsonObject.getJSONObject("rates").getDouble("EUR"),
                        libraEsterlina = jsonObject.getJSONObject("rates").getDouble("GBP"),
                        yen = jsonObject.getJSONObject("rates").getDouble("JPY"),
                        won = jsonObject.getJSONObject("rates").getDouble("KRW");

                System.out.println("Dolar: " + dolar + "\n"
                        + "Euro: " + euro + "\n"
                        + "Libra Esterlina: " + libraEsterlina + "\n"
                        + "Yen Japones: " + yen + "\n"
                        + "Won Coreano: " + won);
            }

        } catch (IOException | RuntimeException ex) {
            System.out.println(ex);
        }

    }

}
