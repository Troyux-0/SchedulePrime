 import java.util.Scanner;

    public class Menú
    {
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);

            boolean continuar = true;

            while (continuar) {
                System.out.println("===== MENÚ PRINCIPAL =====");
                System.out.println("1.-CALENDARIO");
                System.out.println("2.-SALIDA");
                System.out.println("3.-HORARIO");
                System.out.println("Elige una opción del 1 al 3");

                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        calendario();
                        break;
                    case 2:
                        continuar = false;
                        System.out.println("Saliendo el programa");
                        break;
                    case 3:
                        horario();

                        break;

                    default:
                        System.out.println("Opción no válida, por favor selecciona un numero entre 1 y 3");


                }
            }

        }

        private static void horario() {
        }

        private static void calendario() {

        }

    }