package consumoapi;

/*
En la clase ConsumoApiMoneda realiza una descripción legera sobre el funcionamiento del código, tenga en cuenta que
para poder trabajar debe agregar la dependencia(libreria) json-20220320.jar a su proyecto.

Dependencia requerida para trabajar con Json: https://mvnrepository.com/artifact/org.json/json/20220320
Descarga directa: https://repo1.maven.org/maven2/org/json/json/20220320/json-20220320.jar
 */
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class ConsumoApiObject {

    public static void main(String[] args) {

        String urlApi = "https://jsonplaceholder.typicode.com/posts/1";

        try {

            URL url = new URL(urlApi);
            HttpURLConnection urlConexion = (HttpURLConnection) url.openConnection();
            urlConexion.setRequestMethod("GET");
            urlConexion.connect();

            int responseCode = urlConexion.getResponseCode();

            if (responseCode != 200) {

                throw new RuntimeException("Ha ocurrido un error " + responseCode);

            } else {

                StringBuilder almacenDatos = new StringBuilder();
                Scanner flujoDatos = new Scanner(urlConexion.getInputStream());

                while (flujoDatos.hasNext()) {

                    almacenDatos.append(flujoDatos.nextLine());

                }

                flujoDatos.close();

                JSONObject jsonObject = new JSONObject(almacenDatos.toString());

                System.out.println(jsonObject.getInt("id") + " : " + jsonObject.getString("title"));

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
