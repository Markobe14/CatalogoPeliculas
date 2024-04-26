package servicio;

import dominio.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class ServicioPeliculasLista implements IServicioPeliculas{

    private final List<Pelicula> misPeliculas;

    public ServicioPeliculasLista(){
        misPeliculas = new ArrayList<>();
    }

    @Override
    public void listarPeliculas() {
        System.out.println("Lista de Peliculas...");

        //Forma normal
        for (Pelicula pelicula: misPeliculas) {
            System.out.println(pelicula);
        }

        //misPeliculas.forEach(System.out::println); //Imprimimos las peliculas de la lista, forma simplificada
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        misPeliculas.add(pelicula);
        System.out.println("Se agrego la pelicula: " + pelicula);
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        //Regresa el indice de la pelicula encontrada en la lista
        var indice = misPeliculas.indexOf(pelicula); //Funciona correctamente gracias al método equals y hashcode
        if (indice == -1)
            System.out.println("No se encontro la pelicula: " + pelicula);

        else
        System.out.println("Pelicula encontrada en el indice: " + indice);

    }

    public static void main(String[] args) {
        // Creamos algunos objetos de tipo Pelicula
        var peli1 = new Pelicula("Batman");
        var peli2 = new Pelicula("Superman");
        var peli3 = new Pelicula("Spiderman");
        //Creamos el servicio
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        servicioPeliculas.agregarPelicula(peli1);
        servicioPeliculas.agregarPelicula(peli2);

        servicioPeliculas.listarPeliculas();

        servicioPeliculas.buscarPelicula(peli1);
        servicioPeliculas.buscarPelicula(peli2);
        servicioPeliculas.buscarPelicula(peli3);
    }

}
