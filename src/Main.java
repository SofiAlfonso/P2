import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    static List<Students> estudiantes;
    static  Map <String, Long> EporCarrera;


    public static void main(String[] args) throws IOException
    {
       CargarArchivo();
       AporCarrera();
       MporCarrera();
       HporCarrera();
       PMporCarrera();


    }
    static void CargarArchivo() throws IOException
    {
        Pattern coma = Pattern.compile(",");
        String archivo = "student-scores.csv";
        Stream var2 = Files.lines(Path.of(archivo));

        try {
            estudiantes = (List)var2.skip(1L).map((linea) -> {
                String[] partes = coma.split(linea.toString());
                return new Students(partes[0], partes[1],partes[2], partes[4],partes[9],Integer.parseInt(partes[10]));
            }).collect(Collectors.toList());
            //List nlista = estudiantes;
            //PrintStream imprimir = System.out;
            //Objects.requireNonNull(imprimir);
            //nlista.forEach(imprimir::println);
        } catch (Throwable var6) {
            if (var2 != null) {
                try {
                    var2.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }
            }

            throw var6;
        }

        if (var2 != null) {
            var2.close();
        }
    }

    static void AporCarrera()
    {
        System.out.println("Estudiantes por carrera");
        EporCarrera= estudiantes.stream().collect(Collectors.groupingBy(Students::getCareer_aspiration, Collectors.counting()));
        EporCarrera.forEach((k,v)->
        {
            System.out.println("Carrera: " +k +"|"+ " Cantidad: " + v);
            Stream<Students> nueva= estudiantes.stream().filter(x->x.getCareer_aspiration().equals(k));
            nueva.forEach(x-> System.out.println(x));

        });
    }

    static void MporCarrera()
    {
        System.out.println("Mujeres por carrera");
        EporCarrera.forEach((k,v)->
        {
            System.out.print(" En la Carrera " +k );
            Stream<Students> nueva= estudiantes.stream().filter(x->x.getCareer_aspiration().equals(k)).filter(x->x.getGender().equals("female"));
            long cantidad= nueva.count();
            System.out.println(" hay "+ cantidad + " mujeres");

        });
    }

    static void HporCarrera()
    {
        System.out.println("Hombres por carrera");
        EporCarrera.forEach((k,v)->
        {
            System.out.print(" En la Carrera " +k );
            Stream<Students> nueva= estudiantes.stream().filter(x->x.getCareer_aspiration().equals(k)).filter(x->x.getGender().equals("male"));
            long cantidad= nueva.count();
            System.out.println(" hay "+ cantidad + " hombres");

        });
    }

    static void PMporCarrera()
    {
        System.out.println("Puntajes Máximos por carrera");
        EporCarrera.forEach((k,v)->
        {
            System.out.print(" En la Carrera " +k );
            Stream<Students> nueva= estudiantes.stream().filter(x->x.getCareer_aspiration().equals(k));
            ArrayList<Integer> n = new ArrayList<Integer>();
            nueva.forEach(x->n.add(x.getMath_score()));
        });
    }



}