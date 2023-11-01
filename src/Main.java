import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
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
       PMayor();
       PpromCarrera();


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
            List nlista = estudiantes;
            PrintStream imprimir = System.out;
            Objects.requireNonNull(imprimir);
            nlista.forEach(imprimir::println);
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
        System.out.println(" ");
        System.out.println("__________________________________________________________________________________________________________________:");
        System.out.println(" ");
        System.out.println("Estudiantes por carrera");
        EporCarrera= estudiantes.stream().collect(Collectors.groupingBy(Students::getCareer_aspiration, Collectors.counting()));
        EporCarrera.forEach((k,v)->
        {
            System.out.println("Carrera: " +k +"|"+ " Cantidad: " + v);
            estudiantes.stream().filter(x->x.getCareer_aspiration().equals(k)).forEach(x->System.out.println(x));

        });
    }

    static void MporCarrera()

    {
        System.out.println(" ");
        System.out.println("__________________________________________________________________________________________________________________:");
        System.out.println(" ");
        System.out.println("Mujeres por carrera");
        System.out.println(" ");
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
        System.out.println(" ");
        System.out.println("__________________________________________________________________________________________________________________:");
        System.out.println(" ");
        System.out.println("Hombres por carrera");
        System.out.println(" ");
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
        System.out.println(" ");
        System.out.println("__________________________________________________________________________________________________________________:");
        System.out.println(" ");
        System.out.println("Puntajes Máximos por carrera");
        System.out.println(" ");
        EporCarrera.forEach((k,v)->
        {
            System.out.println("_________________"+"\n");
            System.out.print("En la Carrera " +k + ": ");
            Stream<Students> nueva= estudiantes.stream().filter(x->x.getCareer_aspiration().equals(k));
            Integer max= nueva.map(x->x.getMath_score()).collect(Collectors.toList()).stream().max(Integer::compare).get();
            System.out.println(max);
            System.out.println(" ");
            estudiantes.stream().filter(x->x.getCareer_aspiration().equals(k)).filter(x->x.getMath_score()==max).forEach(x-> System.out.println(" El estudiante: "+x.getFirst_name() + " " + x.getLast_name()+ " tiene este puntaje."));
        });

    }
    static void PMayor()
    {
        System.out.println(" ");
        System.out.println("__________________________________________________________________________________________________________________:");
        System.out.println(" ");
        System.out.print("El mayor puntaje de todos es:  ");
        Integer max= estudiantes.stream().map(x->x.getMath_score()).collect(Collectors.toList()).stream().max(Integer::compare).get();
        System.out.println(max);
        System.out.println(" ");
        estudiantes.stream().filter(x->x.getMath_score()==max).forEach(x-> System.out.println("_________________"+"\n"+"El estudiante: "+ x.getFirst_name() + " " + x.getLast_name() + " tiene este puntaje."));
    }

    static void PpromCarrera()
    {
        System.out.println(" ");
        System.out.println("__________________________________________________________________________________________________________________:");
        System.out.println(" ");
        System.out.println("Puntajes promedio por carrera");
        System.out.println(" ");
        EporCarrera.forEach((k,v)->
        {
            System.out.print("En la Carrera " +k + ": ");
            Stream<Students> nueva= estudiantes.stream().filter(x->x.getCareer_aspiration().equals(k));
            Double promedio= nueva.mapToInt(x->(int)x.getMath_score()).average().getAsDouble();
            System.out.println(promedio);
            System.out.println(" ");
            System.out.println("_______________");

        });
    }
}