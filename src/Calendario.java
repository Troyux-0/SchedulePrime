import java.util.Scanner;

public class Calendario {

    static int contadorActividades = 0;
    static String[] fechas = new String[100];
    static String[] actividades = new String[100];
    static String[] notas = new String[100];

    public static String[] Calendario()
    {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        int dia = 0;
        int mes = 0;
        int año = 0;

        while (continuar) {

            System.out.println("MÓDULO CALENDARIO");
            System.out.println("Organización de días y fechas importantes");
            System.out.println("Actividades registradas: " + contadorActividades);
            System.out.println();
            System.out.println("1. Ingreso de actividades");
            System.out.println("2. Gestión de notas");
            System.out.println("3. Eliminación de actividades");
            System.out.println("4. Salir del módulo");
            System.out.print("Elige una opción (1-4): ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (contadorActividades < 100) {
                        System.out.println("INGRESO DE ACTIVIDAD");
                        System.out.println("Actividades registradas: " + contadorActividades);
                        System.out.print("Fecha (DD/MM/AAAA): ");

                        System.out.println("Ingresa el Día DD");
                        dia = scanner.nextInt();
                        System.out.println("Ingresa el Mes MM");
                        mes = scanner.nextInt();
                        System.out.println("Ingresa el Año AAAA");
                        año = scanner.nextInt();
                        scanner.nextLine(); // limpiar buffer

                        fechas[contadorActividades] = dia + "/" + mes + "/" + año;

                        System.out.print("Actividad: ");
                        actividades[contadorActividades] = scanner.nextLine();
                        System.out.print("Notas adicionales: ");
                        notas[contadorActividades] = scanner.nextLine();

                        contadorActividades++;
                        System.out.println("¡Actividad registrada!");
                        System.out.println("Total de actividades: " + contadorActividades);
                    } else {
                        System.out.println("Límite de actividades alcanzado (100)");
                    }
                    break;

                case 2:
                    System.out.println("NOTAS");
                    System.out.println("1. Ver todas las actividades con notas");
                    System.out.println("2. Añadir/modificar notas a actividad existente");
                    System.out.print("Elige una opción (1-2): ");
                    int opcionNotas = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcionNotas) {
                        case 1:
                            if (contadorActividades > 0) {
                                System.out.println("=== ACTIVIDADES CON NOTAS ===");
                                for (int i = 0; i < contadorActividades; i++) {
                                    System.out.println("Índice: " + (i + 1));
                                    System.out.println("Fecha: " + fechas[i]);
                                    System.out.println("Actividad: " + actividades[i]);
                                    System.out.println("Notas: " + notas[i]);
                                    System.out.println("-----------------------------------");
                                }
                            } else {
                                System.out.println("No hay actividades registradas.");
                            }
                            break;

                        case 2:
                            if (contadorActividades > 0) {
                                System.out.println("AÑADIR NOTAS");
                                System.out.println("Actividades disponibles:");
                                for (int i = 0; i < contadorActividades; i++) {
                                    System.out.println((i + 1) + ": " + fechas[i] + " - " + actividades[i]);
                                }
                                System.out.print("Ingresa el índice de la actividad: ");
                                int indice = scanner.nextInt();
                                scanner.nextLine();

                                if (indice >= 1 && indice <= contadorActividades) {
                                    System.out.println("Actividad seleccionada: " + actividades[indice - 1]);
                                    System.out.println("Notas actuales: " + notas[indice - 1]);
                                    System.out.print("Ingresa las nuevas notas: ");
                                    notas[indice - 1] = scanner.nextLine();
                                    System.out.println("¡Notas actualizadas!");
                                } else {
                                    System.out.println("Índice no válido.");
                                }
                            } else {
                                System.out.println("No hay actividades para añadir notas.");
                            }
                            break;

                        default:
                            System.out.println("Opción no válida.");
                    }
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 3:
                    if (contadorActividades > 0) {
                        System.out.println("ELIMINACIÓN DE ACTIVIDAD");
                        System.out.println("Actividades registradas: " + contadorActividades);
                        System.out.println("Actividades disponibles para eliminar:");
                        for (int i = 0; i < contadorActividades; i++) {
                            System.out.println((i + 1) + ": " + fechas[i] + " - " + actividades[i]);
                        }
                        System.out.print("Ingresa el índice de la actividad a eliminar: ");
                        int indice = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        if (indice >= 1 && indice <= contadorActividades) {
                            for (int i = indice - 1; i < contadorActividades - 1; i++) {
                                fechas[i] = fechas[i + 1];
                                actividades[i] = actividades[i + 1];
                                notas[i] = notas[i + 1];
                            }
                            contadorActividades--;
                            System.out.println("¡Actividad eliminada!");
                            System.out.println("Total de actividades: " + contadorActividades);
                        } else {
                            System.out.println("Índice no válido.");
                        }
                    } else {
                        System.out.println("No hay actividades para eliminar.");
                    }
                    break;

                case 4:
                    continuar = false;
                    System.out.println("Saliendo del módulo calendario...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
        return fechas;
    }
}
