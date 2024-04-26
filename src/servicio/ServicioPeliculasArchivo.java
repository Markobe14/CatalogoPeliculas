package servicio;

import dominio.Pelicula;

import java.io.*;

public class ServicioPeliculasArchivo implements IServicioPeliculas{

    private final String NOMBRE_ARCHIVO = "peliculas.txt";

    public ServicioPeliculasArchivo(){
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //Si ya existe el archivo NO se vuelve a crear
            if (archivo.exists()){
                System.out.println("El archivo ya existe!");
            }
            else {
                //Si NO existe, se crea vacio
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close(); //Se tiene que cerrar el archivo para que se guarde
                System.out.println("Se ha creado el archivo");
            }
        } catch (Exception e){
            System.out.println("Ocurrio un error al abrir el archivo: " + e.getMessage());
        }
    }

    @Override
    public void listarPeliculas() {
        // 1. Volvemos a abrir el archivo
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            System.out.println("Listado de Peliculas");
            //2. Abrimos el archivo para lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            //3. Leemos linea a linea el archivo
            String linea;
            linea = entrada.readLine();
            //4. Leemos todas las lineas disponibles
            while (linea != null){
                //5. Imprimimos las peliculas
                var pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                // Antes de terminar el ciclo volvemos a leer la siguiente linea
                linea = entrada.readLine();
            }
            //6. Cerramos el archivo
            entrada.close();

        } catch (Exception e){
            System.out.println("Ocurrio un error al leer el archivo: " + e.getMessage());
        }
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //1. Revisamos si existe el archivo
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            //2. Agregamos la pelicula (toString)
            salida.println(pelicula);
            //3. Cerramos el archivo
            salida.close();
            System.out.println("Se agrego al archivo: " + pelicula);

        } catch (Exception e){
            System.out.println("Ocurrio un error al agregar pelicula: " + e.getMessage());
        }
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //1. Abrimos el archivo para lectura linea a linea
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto = entrada.readLine();
            var indice = 1;
            var encontrada = false;
            var peliculaBuscar = pelicula.getNombre();

            while (lineaTexto != null){
                //Buscamos sin importar Mayusculas/Minusculas
                if (peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
                    encontrada = true;
                    break;
                }
                //Leemos la siguiente linea antes de la siguiente iteracion
                lineaTexto = entrada.readLine();
                indice++;
            }
            //Imprimimos los resultados de la busqueda
            if (encontrada){
                System.out.println("Pelicula " + lineaTexto + " encontrada - linea " + indice);
            }
            else {
                System.out.println("No se ha encontrado la pelicula: " + peliculaBuscar);
            }
            entrada.close();
        } catch (Exception e){
            System.out.println("Ocurrio un error al buscar en el archivo: " + e.getMessage());
        }
    }

}
