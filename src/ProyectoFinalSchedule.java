import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class ProyectoFinalSchedule
{

    static Scanner scanner = new Scanner(System.in);
    static final int maxUsuarios = 100;
    static final int maxActividades = 100;

    static String[][] dias = new String[maxUsuarios][maxActividades];
    static String[][] actividadesHorario = new String[maxUsuarios][maxActividades];
    static String[][] notasHorario = new String[maxUsuarios][maxActividades];
    static int[][][] hora = new int[maxUsuarios][2][maxActividades];
    static int[] contadorActividadesHorario = new int[maxUsuarios];

    static int[][][] fechas = new int[maxUsuarios][3][maxActividades];
    static String[][] actividadesCalendario = new String[maxUsuarios][maxActividades];
    static String[][] notasCalendario = new String[maxUsuarios][maxActividades];
    static int[] contadorActividadesCalendario = new int[maxUsuarios];

    static String[][] usuarios = new String[maxUsuarios][2];
    static int contadorUsuarios = 0;
    static String usuarioActual = null;

    public static void main(String[] args)
    {
        usuarios[0][0] = "AllissonAlejandra";
        usuarios[0][1] = "Meldrnts";
        usuarios[1][0] = "Marvin01";
        usuarios[1][1] = "ZamudioPerez";
        contadorUsuarios = 2;

        boolean ejecutar = true;
        while (ejecutar)
        {
            while (usuarioActual == null)
            {
                System.out.println("=== BIENVENID@ ===");
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Registrar nuevo usuario");
                System.out.println("3. Salir del programa");
                System.out.print("Elige una opción: ");
                int opcion = 0;

                if (scanner.hasNextInt())
                {
                    opcion = scanner.nextInt();
                }
                scanner.nextLine();

                switch (opcion)
                {
                    case 1:
                        limpiarPantalla();
                        System.out.print("Ingresa tu nombre de usuario: ");
                        String usuarioIniciado = scanner.nextLine().trim();
                        System.out.print("Ingresa tu contraseña: ");
                        String contraseñaIniciada = scanner.nextLine().trim();

                        boolean encontrado = false;
                        for (int i = 0; i < contadorUsuarios; i++)
                        {
                            if (usuarios[i][0].equals(usuarioIniciado) && usuarios[i][1].equals(contraseñaIniciada))
                            {
                                usuarioActual = usuarioIniciado;
                                System.out.println("Inicio de sesión exitoso. Bienvenido " + usuarioIniciado);
                                System.out.println("Presiona la tecla Enter para continuar");
                                scanner.nextLine();
                                encontrado = true;
                                limpiarPantalla();
                                break;
                            }
                        }
                        if (!encontrado)
                        {
                            System.out.println("Usuario o contraseña incorrectos. Intenta nuevamente");
                            System.out.println("Presiona la tecla Enter para continuar");
                            scanner.nextLine();
                            limpiarPantalla();
                        }
                        break;

                    case 2:
                        limpiarPantalla();
                        String usuario = "";
                        String contraseña = "";
                        boolean usuarioEncontrado = false;

                        while (true)
                        {
                            System.out.println("=== Registro de Nuevo Usuario ===");
                            System.out.print("Ingresa el nombre de usuario: ");
                            usuario = scanner.nextLine().trim();

                            if (usuario.isEmpty())
                            {
                                System.out.println("El nombre de usuario no puede estar vacío. Intenta nuevamente.");
                            }
                            else if (contadorUsuarios >= maxUsuarios)
                            {
                                System.out.println("Error: Capacidad máxima de usuarios alcanzada.");
                                usuario = "";
                                break;
                            }
                            else
                            {
                                usuarioEncontrado = false;
                                for (int i = 0; i < contadorUsuarios; i++)
                                {
                                    if (usuarios[i][0].equals(usuario))
                                    {
                                        System.out.println("Ese usuario ya existe, intenta nuevamente.");
                                        usuarioEncontrado = true;
                                        break;
                                    }
                                }
                                if (!usuarioEncontrado)
                                {
                                    break;
                                }
                            }
                            System.out.println("Presiona la tecla Enter para continuar");
                            scanner.nextLine();
                            limpiarPantalla();
                        }

                        if (usuarioEncontrado || usuario.isEmpty())
                        {
                            break;
                        }

                        while (true)
                        {
                            limpiarPantalla();
                            System.out.println("=== Registro de Nuevo Usuario ===");
                            System.out.println("Usuario seleccionado: " + usuario);
                            System.out.print("Ingresa la contraseña (mínimo 8 caracteres): ");
                            contraseña = scanner.nextLine();

                            if (contraseña.length() >= 8)
                            {
                                break;
                            }
                            else
                            {
                                System.out.println("La contraseña debe tener al menos 8 caracteres. Intente de nuevo.");
                                System.out.println("Presiona la tecla Enter para intentar nuevamente...");
                                scanner.nextLine();
                            }
                        }

                        usuarios[contadorUsuarios][0] = usuario;
                        usuarios[contadorUsuarios][1] = contraseña;
                        contadorUsuarios++;
                        System.out.println("Usuario registrado exitosamente: " + usuario);
                        System.out.println("Presiona la tecla Enter para continuar");
                        scanner.nextLine();
                        limpiarPantalla();
                        break;

                    case 3:
                        System.out.println("Saliendo del programa...");
                        return;

                    default:
                        System.out.println("Opción no válida. Intenta nuevamente.");
                        System.out.println("Presiona la tecla Enter para continuar");
                        scanner.nextLine();
                        limpiarPantalla();
                }
            }

            boolean continuar = true;
            while (continuar)
            {
                System.out.println("\n===== MENÚ PRINCIPAL =====");
                System.out.println("Usuario: " + usuarioActual);
                System.out.println("1.-CALENDARIO");
                System.out.println("2.-SALIR DEL PROGRAMA");
                System.out.println("3.-HORARIO");
                System.out.print("Elige una opción del 1 al 3: ");

                int opcion = -1;
                int tempOpcion = leerEnteroValidado("", 1, 3);
                if (tempOpcion != -1) opcion = tempOpcion;

                switch (opcion)
                {
                    case 1:
                        limpiarPantalla();
                        calendario();
                        break;
                    case 2:
                        limpiarPantalla();
                        int accionSalida = 0;
                        accionSalida = salida();

                        if (accionSalida == 1)
                        {
                            continuar = false;
                            usuarioActual = null;
                            System.out.println("Sesión cerrada. Volviendo a la pantalla de inicio.");
                            limpiarPantalla();
                        }
                        else if (accionSalida == 2)
                        {
                            ejecutar = false;
                            continuar = false;
                            System.out.println("Saliendo del programa...");
                            return;
                        }
                        break;
                    case 3:
                        limpiarPantalla();
                        horario();
                        break;
                    default:
                        if (opcion != -1)
                        {
                            System.out.println("Opción no válida, por favor selecciona un numero entre 1 y 3");
                        }
                        System.out.println("Presiona la tecla Enter para continuar.");
                        scanner.nextLine();
                }
            }
        }
    }

    private static int salida()
    {
        while (true)
        {
            System.out.println("\n===== MÓDULO SALIDA =====");
            System.out.println("Usuario: " + usuarioActual);
            System.out.println("1. Cerrar Sesión ");
            System.out.println("2. Volver al Menú Principal");
            System.out.println("3. Cerrar el programa (Salir Completamente)");
            System.out.print("Elige una opción: ");

            int opcionSalida = -1;
            int tempOpcion = leerEnteroValidado("", 1, 3);
            if (tempOpcion != -1) opcionSalida = tempOpcion;

            switch (opcionSalida)
            {
                case 1:
                    return 1;
                case 2:
                    return 0;
                case 3:
                    return 2;
                default:
                    if (opcionSalida != -1)
                    {
                        System.out.println("Opción no válida. Intenta nuevamente.");
                    }
                    System.out.println("Presiona la tecla Enter para continuar");
                    scanner.nextLine();
                    limpiarPantalla();
            }
        }
    }

    public static void horario()
    {
        int usuarioIndex = obtenerIndiceUsuarioActual();
        if (usuarioIndex == -1) return;

        int actividadesActuales = contadorActividadesHorario[usuarioIndex];
        boolean continuar = true;

        while (continuar)
        {
            System.out.println("\n===== MÓDULO HORARIO =====");
            System.out.println("Usuario: " + usuarioActual);
            System.out.println("Horario personalizable");
            System.out.println("Actividades registradas: " + actividadesActuales);
            System.out.println("Presiona la tecla Enter para continuar.");
            scanner.nextLine();
            limpiarPantalla();
            System.out.println("1.- Ingreso de actividades");
            System.out.println("2.- Gestión de actividades");
            System.out.println("3.- Eliminación de actividades");
            System.out.println("4.- Salir del módulo");
            System.out.print("Elige una opción: ");

            int opcion = -1;
            int tempOpcion = leerEnteroValidado("", 1, 4);
            if (tempOpcion != -1) opcion = tempOpcion;

            switch (opcion)
            {
                case 1:
                    if (actividadesActuales < maxActividades)
                    {
                        limpiarPantalla();
                        System.out.println("INGRESO DE ACTIVIDAD AL HORARIO");
                        System.out.println("Actividades registradas: " + actividadesActuales);
                        String diaIngresado;
                        boolean diaOk = false;

                        do
                        {
                            System.out.println("Ingresa el día ( Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo ) ");
                            diaIngresado = scanner.nextLine();
                            String diaNormalizado = diaIngresado.toLowerCase().replace("é", "e").replace("á", "a").replace("í", "i").replace("ó", "o").replace("ú", "u");

                            if (esDiaValido(diaNormalizado))
                            {
                                diaOk = true;
                                dias[usuarioIndex][actividadesActuales] = diaIngresado;
                            }
                            else
                            {
                                System.out.println("El día '" + diaIngresado + "' no es válido. Intenta de nuevo.");
                            }
                        } while (!diaOk);

                        int horaIngresada = -1;
                        int minutoIngresado = -1;

                        do
                        {
                            System.out.println("\n(Formato de 24 horas: HH:MM) ");
                            horaIngresada = leerEnteroValidado("Ingresa la hora (0-23): ", 0, 23);
                        } while (horaIngresada == -1);
                        hora[usuarioIndex][0][actividadesActuales] = horaIngresada;

                        do
                        {
                            minutoIngresado = leerEnteroValidado("Ingresa los minutos (0-59): ", 0, 59);
                        } while (minutoIngresado == -1);
                        hora[usuarioIndex][1][actividadesActuales] = minutoIngresado;

                        System.out.print("Actividad: ");
                        actividadesHorario[usuarioIndex][actividadesActuales] = scanner.nextLine();
                        System.out.print("Notas adicionales: ");
                        notasHorario[usuarioIndex][actividadesActuales] = scanner.nextLine();

                        contadorActividadesHorario[usuarioIndex]++;
                        actividadesActuales = contadorActividadesHorario[usuarioIndex];

                        System.out.println("¡Actividad agregada al horario!");
                        System.out.println("Total de actividades: " + actividadesActuales);
                        System.out.println("Presiona la tecla Enter para continuar.");
                        scanner.nextLine();
                    }
                    else
                    {
                        System.out.println("Límite de actividades del horario alcanzado");
                        System.out.println("Presiona la tecla Enter para continuar.");
                        scanner.nextLine();
                    }
                    break;

                case 2:
                    limpiarPantalla();
                    System.out.println("GESTIÓN DE ACTIVIDADES DEL HORARIO");
                    System.out.println("1.-Ver horario por fecha de registro");
                    System.out.println("2.-Añadir/modificar notas a actividad existente");
                    System.out.println("3.-Ver horario gráfico por proximidad");
                    System.out.print("Elige una opción: ");
                    int subOpcion = -1;
                    int tempSubOpcion = leerEnteroValidado("", 1, 3);
                    if (tempSubOpcion != -1) subOpcion = tempSubOpcion;

                    switch (subOpcion)
                    {
                        case 1:
                            limpiarPantalla();
                            if (actividadesActuales > 0)
                            {
                                System.out.println("=== HORARIO COMPLETO de " + usuarioActual + " ===");
                                for (int i = 0; i < actividadesActuales; i++)
                                {
                                    System.out.println("Indice -" + (i + 1));
                                    System.out.println("Día -" + dias[usuarioIndex][i]);
                                    System.out.println(String.format("Hora - %02d:%02d", hora[usuarioIndex][0][i], hora[usuarioIndex][1][i]));
                                    System.out.println("Actividad -" + actividadesHorario[usuarioIndex][i]);
                                    System.out.println("Notas -" + notasHorario[usuarioIndex][i]);
                                    System.out.println("-----------------");
                                }
                            }
                            else
                            {
                                System.out.println("No hay actividades en el horario");
                            }
                            break;

                        case 2:
                            limpiarPantalla();
                            if (actividadesActuales > 0)
                            {
                                System.out.println("MODIFICAR NOTAS DEL HORARIO");
                                System.out.println("Actividades en horario:");
                                for (int i = 0; i < actividadesActuales; i++)
                                {
                                    System.out.println("índice: " + (i + 1));
                                    System.out.println(dias[usuarioIndex][i]);
                                    System.out.println(String.format("Hora - %02d:%02d", hora[usuarioIndex][0][i], hora[usuarioIndex][1][i]));
                                    System.out.println(actividadesHorario[usuarioIndex][i]);
                                    System.out.println("--------------");
                                }

                                int indice = -1;
                                do
                                {
                                    indice = leerEnteroValidado("Ingresa el índice de la actividad: ", 1, actividadesActuales);
                                } while (indice == -1);

                                if (indice >= 1 && indice <= actividadesActuales)
                                {
                                    System.out.println("Actividad seleccionada: " + actividadesHorario[usuarioIndex][indice - 1]);
                                    System.out.println("Notas actuales: " + notasHorario[usuarioIndex][indice - 1]);
                                    System.out.print("Ingresa las nuevas notas: ");
                                    notasHorario[usuarioIndex][indice - 1] = scanner.nextLine();
                                    System.out.println("¡Notas actualizadas!");
                                }
                                else
                                {
                                    System.out.println("Indice no válido");
                                }
                            }
                            else
                            {
                                System.out.println("No hay actividades para registrar notas en el horario.");
                            }
                            break;

                        case 3:
                            limpiarPantalla();
                            if (actividadesActuales == 0)
                            {
                                System.out.println("No hay actividades registradas en tu horario.");
                            }
                            else
                            {
                                OrdenarBurbujaHorarioProximidad(usuarioIndex, actividadesActuales);
                                System.out.println("Presiona la tecla Enter para continuar.");
                                scanner.nextLine();
                            }
                            break;

                        default:
                            if (subOpcion != -1)
                            {
                                System.out.println("Opcion no válida");
                            }
                    }
                    System.out.println("Presiona la tecla Enter dos veces para continuar.");
                    scanner.nextLine();
                    break;

                case 3:
                    limpiarPantalla();
                    System.out.println("ELIMINACIÓN DE ACTIVIDADES DEL HORARIO");
                    System.out.println("Actividades registradas: " + actividadesActuales);
                    System.out.println("1. Eliminar una actividad específica");
                    System.out.println("2. Eliminar TODAS las actividades del horario");
                    System.out.print("Elige una opción (1-2): ");

                    int opcionEliminar = -1;
                    int tempOpcionEliminar = leerEnteroValidado("", 1, 2);
                    if (tempOpcionEliminar != -1) opcionEliminar = tempOpcionEliminar;

                    switch (opcionEliminar)
                    {
                        case 1:
                            if (actividadesActuales > 0)
                            {
                                System.out.println("\nACTIVIDADES DISPONIBLES PARA ELIMINAR:");
                                for (int i = 0; i < actividadesActuales; i++)
                                {
                                    System.out.println("Indice " + (i + 1) + ": " + dias[usuarioIndex][i] + " " +
                                            String.format("%02d:%02d", hora[usuarioIndex][0][i], hora[usuarioIndex][1][i]) + " " +
                                            actividadesHorario[usuarioIndex][i]);
                                }

                                int indice = -1;
                                do
                                {
                                    indice = leerEnteroValidado("\nIngresa el índice de la actividad a eliminar: ", 1, actividadesActuales);
                                } while (indice == -1);

                                if (indice >= 1 && indice <= actividadesActuales)
                                {
                                    System.out.println("\n--- ACTIVIDAD A ELIMINAR ---");
                                    System.out.println("Día: " + dias[usuarioIndex][indice - 1]);
                                    System.out.println("Hora: " + String.format("%02d:%02d", hora[usuarioIndex][0][indice - 1], hora[usuarioIndex][1][indice - 1]));
                                    System.out.println("Actividad: " + actividadesHorario[usuarioIndex][indice - 1]);
                                    System.out.println("Notas: " + notasHorario[usuarioIndex][indice - 1]);

                                    System.out.print("\n¿Estás seguro de eliminar esta actividad? (s/n): ");
                                    String confirmacion = scanner.nextLine().toLowerCase();

                                    if (confirmacion.equals("s") || confirmacion.equals("si"))
                                    {
                                        for (int i = indice - 1; i < actividadesActuales - 1; i++)
                                        {
                                            dias[usuarioIndex][i] = dias[usuarioIndex][i + 1];
                                            hora[usuarioIndex][0][i] = hora[usuarioIndex][0][i + 1];
                                            hora[usuarioIndex][1][i] = hora[usuarioIndex][1][i + 1];
                                            actividadesHorario[usuarioIndex][i] = actividadesHorario[usuarioIndex][i + 1];
                                            notasHorario[usuarioIndex][i] = notasHorario[usuarioIndex][i + 1];
                                        }
                                        contadorActividadesHorario[usuarioIndex]--;
                                        actividadesActuales = contadorActividadesHorario[usuarioIndex];

                                        System.out.println("\n¡Actividad eliminada del horario!");
                                        System.out.println("Total de actividades: " + actividadesActuales);
                                    }
                                    else
                                    {
                                        System.out.println("\nEliminación cancelada.");
                                    }
                                }
                                else
                                {
                                    System.out.println("Indice no valido");
                                }
                            }
                            else
                            {
                                System.out.println("No hay actividades para eliminar en el horario.");
                            }
                            break;

                        case 2:
                            if (actividadesActuales > 0)
                            {
                                System.out.println("\n=== ELIMINAR TODAS LAS ACTIVIDADES DEL HORARIO ===");
                                System.out.println("Vas a eliminar TODAS las actividades del horario.");
                                System.out.println("Total de actividades a eliminar: " + actividadesActuales);
                                System.out.println("\n¡ESTA ACCIÓN NO SE PUEDE DESHACER!");

                                System.out.println("\n--- RESUMEN DE ACTIVIDADES ---");
                                for (int i = 0; i < actividadesActuales; i++)
                                {
                                    System.out.println((i + 1) + ". " + dias[usuarioIndex][i] + " " +
                                            String.format("%02d:%02d", hora[usuarioIndex][0][i], hora[usuarioIndex][1][i]) + " - " +
                                            actividadesHorario[usuarioIndex][i]);
                                }

                                System.out.print("\n¿Estás SEGURO de que quieres eliminar TODAS las actividades? (escribe 'ELIMINAR' para confirmar): ");
                                String confirmacion = scanner.nextLine();

                                if (confirmacion.equalsIgnoreCase("ELIMINAR"))
                                {
                                    System.out.print("¿REALMENTE estás seguro? Esta acción borrará " + actividadesActuales + " actividades. (s/n): ");
                                    String confirmacion2 = scanner.nextLine().toLowerCase();

                                    if (confirmacion2.equals("s") || confirmacion2.equals("si"))
                                    {
                                        contadorActividadesHorario[usuarioIndex] = 0;
                                        actividadesActuales = 0;

                                        System.out.println("\n========================================");
                                        System.out.println("¡TODAS las actividades han sido eliminadas!");
                                        System.out.println("Total de actividades: 0");
                                        System.out.println("========================================");
                                    }
                                    else
                                    {
                                        System.out.println("\nEliminación cancelada.");
                                    }
                                }
                                else
                                {
                                    System.out.println("\nEliminación cancelada. No escribiste 'ELIMINAR'.");
                                }
                            }
                            else
                            {
                                System.out.println("No hay actividades para eliminar en el horario.");
                            }
                            break;

                        default:
                            if (opcionEliminar != -1)
                            {
                                System.out.println("Opción no válida.");
                            }
                    }
                    System.out.println("\nPresiona la tecla Enter para continuar.");
                    scanner.nextLine();
                    break;

                case 4:
                    continuar = false;
                    System.out.println("Saliendo del Módulo Horario....");
                    break;

                default:
                    if (opcion != -1)
                    {
                        System.out.println("Opcion no válida, intenta nuevamente");
                    }
                    System.out.println("Presiona la tecla Enter para continuar.");
                    scanner.nextLine();
                    break;
            }
        }
    }

    private static boolean debeIntercambiarHorarioProximidad(int usuarioIndice, int indice1, int indice2)
    {
        int valorDia1 = obtenerValorDia(dias[usuarioIndice][indice1]);
        int valorDia2 = obtenerValorDia(dias[usuarioIndice][indice2]);
        int diaActual = obtenerValorDiaSemanaActual();

        int diffDia1 = (valorDia1 >= diaActual) ? (valorDia1 - diaActual) : (7 - diaActual + valorDia1);
        int diffDia2 = (valorDia2 >= diaActual) ? (valorDia2 - diaActual) : (7 - diaActual + valorDia2);

        if (diffDia1 > diffDia2)
        {
            return true;
        }
        if (diffDia1 < diffDia2)
        {
            return false;
        }

        int hora1 = hora[usuarioIndice][0][indice1];
        int hora2 = hora[usuarioIndice][0][indice2];
        if (hora1 > hora2)
        {
            return true;
        }
        if (hora1 < hora2)
        {
            return false;
        }

        int minuto1 = hora[usuarioIndice][1][indice1];
        int minuto2 = hora[usuarioIndice][1][indice2];
        if (minuto1 > minuto2)
        {
            return true;
        }

        return false;
    }

    public static void OrdenarBurbujaHorarioProximidad(int usuarioIndice, int actividadesActuales)
    {
        if (actividadesActuales == 0)
        {
            System.out.println("No hay actividades registradas para ordenar.");
            return;
        }

        boolean intercambiado;
        for (int i = 0; i < actividadesActuales - 1; i++)
        {
            intercambiado = false;
            for (int j = 0; j < actividadesActuales - 1 - i; j++)
            {
                if (debeIntercambiarHorarioProximidad(usuarioIndice, j, j + 1))
                {
                    String tempDia = dias[usuarioIndice][j];
                    dias[usuarioIndice][j] = dias[usuarioIndice][j + 1];
                    dias[usuarioIndice][j + 1] = tempDia;

                    int tempHora = hora[usuarioIndice][0][j];
                    hora[usuarioIndice][0][j] = hora[usuarioIndice][0][j + 1];
                    hora[usuarioIndice][0][j + 1] = tempHora;

                    int tempMinuto = hora[usuarioIndice][1][j];
                    hora[usuarioIndice][1][j] = hora[usuarioIndice][1][j + 1];
                    hora[usuarioIndice][1][j + 1] = tempMinuto;

                    String tempActividad = actividadesHorario[usuarioIndice][j];
                    actividadesHorario[usuarioIndice][j] = actividadesHorario[usuarioIndice][j + 1];
                    actividadesHorario[usuarioIndice][j + 1] = tempActividad;

                    String tempNota = notasHorario[usuarioIndice][j];
                    notasHorario[usuarioIndice][j] = notasHorario[usuarioIndice][j + 1];
                    notasHorario[usuarioIndice][j + 1] = tempNota;

                    intercambiado = true;
                }
            }
            if (!intercambiado) break;
        }

        mostrarHorarioTabla(usuarioIndice, actividadesActuales);
    }

    public static void mostrarHorarioTabla(int usuarioIndice, int actividadesActuales)
    {
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        System.out.println("=== HORARIO ORGANIZADO POR PROXIMIDAD de " + usuarioActual + " ===");
        System.out.println("Fecha actual: " + String.format("%02d/%02d/%04d",
                fechaActual.getDayOfMonth(), fechaActual.getMonthValue(), fechaActual.getYear()));
        System.out.println("Hora actual: " + String.format("%02d:%02d", horaActual.getHour(), horaActual.getMinute()));
        System.out.println();

        String[] diasSemana = {"LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO"};

        System.out.println("┌───────────────────┬───────────────────┬───────────────────┬───────────────────┬───────────────────┬───────────────────┬───────────────────┐");
        System.out.print("│");
        for (int i = 0; i < 7; i++)
        {
            System.out.printf(" %-17s │", diasSemana[i]);
        }
        System.out.println("\n├───────────────────┼───────────────────┼───────────────────┼───────────────────┼───────────────────┼───────────────────┼───────────────────┤");

        String[][] actividadesPorDia = new String[7][maxActividades];
        int[] contadorPorDia = new int[7];

        for (int i = 0; i < actividadesActuales; i++)
        {
            int diaIndex = obtenerValorDia(dias[usuarioIndice][i]) - 1;
            if (diaIndex >= 0 && diaIndex < 7 && contadorPorDia[diaIndex] < maxActividades)
            {
                String horaStr = String.format("%02d:%02d", hora[usuarioIndice][0][i], hora[usuarioIndice][1][i]);
                String actividadCompleta = horaStr + " " + actividadesHorario[usuarioIndice][i];
                actividadesPorDia[diaIndex][contadorPorDia[diaIndex]] = actividadCompleta;
                contadorPorDia[diaIndex]++;
            }
        }

        int maxActividadesPorDia = 0;
        for (int i = 0; i < 7; i++)
        {
            if (contadorPorDia[i] > maxActividadesPorDia)
            {
                maxActividadesPorDia = contadorPorDia[i];
            }
        }

        if (maxActividadesPorDia == 0)
        {
            maxActividadesPorDia = 1;
        }

        int filasAMostrar = Math.min(maxActividadesPorDia, 10);

        for (int fila = 0; fila < filasAMostrar; fila++)
        {
            System.out.print("│");
            for (int col = 0; col < 7; col++)
            {
                if (fila < contadorPorDia[col])
                {
                    String actividad = actividadesPorDia[col][fila];
                    if (actividad.length() > 17)
                    {
                        actividad = actividad.substring(0, 14) + "...";
                    }
                    System.out.printf(" %-17s │", actividad);
                }
                else
                {
                    System.out.printf(" %-17s │", "");
                }
            }
            System.out.println();
        }

        if (maxActividadesPorDia > 10)
        {
            System.out.println("│ (hay más actividades...)                                                               │");
        }

        System.out.println("└───────────────────┴───────────────────┴───────────────────┴───────────────────┴───────────────────┴───────────────────┴───────────────────┘");

        System.out.println("\n=== DETALLES COMPLETOS DE ACTIVIDADES ===");
        if (actividadesActuales == 0)
        {
            System.out.println("No hay actividades registradas.");
        }
        else
        {
            for (int i = 0; i < actividadesActuales; i++)
            {
                String horaStr = String.format("%02d:%02d", hora[usuarioIndice][0][i], hora[usuarioIndice][1][i]);
                System.out.println("\n" + (i + 1) + ". " + dias[usuarioIndice][i] + " a las " + horaStr);
                System.out.println("   Actividad: " + actividadesHorario[usuarioIndice][i]);
                if (!notasHorario[usuarioIndice][i].isEmpty())
                {
                    System.out.println("   Notas: " + notasHorario[usuarioIndice][i]);
                }
                System.out.println("   ---");
            }
        }
    }

    public static void calendario()
    {
        int usuarioIndice = obtenerIndiceUsuarioActual();
        if (usuarioIndice == -1) return;

        int actividadesActuales = contadorActividadesCalendario[usuarioIndice];
        boolean continuar = true;

        while (continuar)
        {
            System.out.println("\n===== MÓDULO CALENDARIO =====");
            System.out.println("Usuario: " + usuarioActual);
            System.out.println("Organización de días y fechas importantes");
            System.out.println("Actividades registradas: " + actividadesActuales);
            System.out.println("Presiona la tecla Enter para continuar.");
            scanner.nextLine();
            limpiarPantalla();
            System.out.println("1. Ingreso de actividades");
            System.out.println("2. Gestión de actividades");
            System.out.println("3. Eliminación de actividades");
            System.out.println("4. Salir del módulo");
            System.out.print("Elige una opción (1-4): ");

            int opcion = -1;
            int tempOpcion = leerEnteroValidado("", 1, 4);
            if (tempOpcion != -1) opcion = tempOpcion;

            switch (opcion)
            {
                case 1:
                    limpiarPantalla();
                    if (actividadesActuales < maxActividades)
                    {
                        System.out.println("INGRESO DE ACTIVIDAD AL CALENDARIO");
                        System.out.println("Actividades registradas: " + actividadesActuales);

                        int dia = 0, mes = 0, año = 0;
                        boolean fechaOk = false;

                        do
                        {
                            System.out.println("--- Ingreso de Fecha ---");

                            dia = leerEnteroValidado("Ingresa el dia (DD): ", 1, 31);
                            if (dia == -1) continue;

                            mes = leerEnteroValidado("Ingresa el mes (MM): ", 1, 12);
                            if (mes == -1) continue;

                            año = leerEnteroValidado("Ingresa el año (AAAA): ", 1900, 5000);
                            if (año == -1) continue;

                            if (esFechaValida(dia, mes, año))
                            {
                                fechaOk = true;
                                System.out.println("Fecha válida. Continuando...");
                            }
                            else
                            {
                                System.out.println("La fecha " + dia + "/" + mes + "/" + año + " no es válida (ej. 31 de Febrero). Intenta de nuevo.");
                            }

                        } while (!fechaOk);

                        fechas[usuarioIndice][0][actividadesActuales] = dia;
                        fechas[usuarioIndice][1][actividadesActuales] = mes;
                        fechas[usuarioIndice][2][actividadesActuales] = año;

                        System.out.print("Actividad: ");
                        actividadesCalendario[usuarioIndice][actividadesActuales] = scanner.nextLine();
                        System.out.print("Notas adicionales: ");
                        notasCalendario[usuarioIndice][actividadesActuales] = scanner.nextLine();

                        contadorActividadesCalendario[usuarioIndice]++;
                        actividadesActuales = contadorActividadesCalendario[usuarioIndice];

                        System.out.println("¡Actividad registrada!");
                        System.out.println("Total de actividades: " + actividadesActuales);
                    }
                    else
                    {
                        System.out.println("Límite de actividades del calendario alcanzado (100)");
                    }
                    System.out.println("Presiona la tecla Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 2:
                    limpiarPantalla();
                    System.out.println("GESTIÓN DE ACTIVIDADES DEL CALENDARIO");
                    System.out.println("1. Ver todas las actividades registradas");
                    System.out.println("2. Añadir/modificar notas a actividad existente");
                    System.out.println("3. Ver actividades por proximidad");
                    System.out.print("Elige una opción (1-3): ");
                    int opcionNotas = -1;
                    int tempOpcionNotas = leerEnteroValidado("", 1, 3);
                    if (tempOpcionNotas != -1) opcionNotas = tempOpcionNotas;

                    switch (opcionNotas)
                    {
                        case 1:
                            limpiarPantalla();
                            if (actividadesActuales > 0)
                            {
                                System.out.println("=== ACTIVIDADES REGISTRADAS EN CALENDARIO de " + usuarioActual + " ===");
                                for (int i = 0; i < actividadesActuales; i++)
                                {
                                    System.out.println("Índice: " + (i + 1));
                                    System.out.println(String.format("Fecha: %02d / %02d / %d",
                                            fechas[usuarioIndice][0][i],
                                            fechas[usuarioIndice][1][i],
                                            fechas[usuarioIndice][2][i]
                                    ));
                                    System.out.println("Actividad: " + actividadesCalendario[usuarioIndice][i]);
                                    System.out.println("Notas: " + notasCalendario[usuarioIndice][i]);
                                    System.out.println("-----------------------------------");
                                }
                            }
                            else
                            {
                                System.out.println("No hay actividades registradas en el calendario.");
                            }
                            break;

                        case 2:
                            limpiarPantalla();
                            if (actividadesActuales > 0)
                            {
                                System.out.println("AÑADIR/MODIFICAR NOTAS");
                                System.out.println("Actividades disponibles:");
                                for (int i = 0; i < actividadesActuales; i++)
                                {
                                    System.out.println((i + 1) + ": " + String.format("Fecha: %02d / %02d / %d", fechas[usuarioIndice][0][i], fechas[usuarioIndice][1][i], fechas[usuarioIndice][2][i]) + " - " + actividadesCalendario[usuarioIndice][i]);
                                }

                                int indice = -1;
                                do
                                {
                                    indice = leerEnteroValidado("Ingresa el índice de la actividad: ", 1, actividadesActuales);
                                } while (indice == -1);

                                if (indice >= 1 && indice <= actividadesActuales)
                                {
                                    System.out.println("Actividad seleccionada: " + actividadesCalendario[usuarioIndice][indice - 1]);
                                    System.out.println("Notas actuales: " + notasCalendario[usuarioIndice][indice - 1]);
                                    System.out.print("Ingresa las nuevas notas: ");
                                    notasCalendario[usuarioIndice][indice - 1] = scanner.nextLine();
                                    System.out.println("¡Notas actualizadas!");
                                }
                                else
                                {
                                    System.out.println("Índice no válido.");
                                }
                            }
                            else
                            {
                                System.out.println("No hay actividades para añadir notas.");
                            }
                            break;

                        case 3:
                            limpiarPantalla();
                            if (actividadesActuales > 0)
                            {
                                OrdenarBurbujaCalendarioPorProximidad(usuarioIndice, actividadesActuales);
                            }
                            else
                            {
                                System.out.println("No hay actividades registradas en el calendario.");
                            }
                            break;

                        default:
                            if (opcionNotas != -1)
                            {
                                System.out.println("Opción no válida.");
                            }
                    }
                    System.out.println("Presiona la tecla Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 3:
                    limpiarPantalla();
                    System.out.println("ELIMINACIÓN DE ACTIVIDADES DEL CALENDARIO");
                    System.out.println("Actividades registradas: " + actividadesActuales);
                    System.out.println("1. Eliminar una actividad específica");
                    System.out.println("2. Eliminar TODAS las actividades del calendario");
                    System.out.print("Elige una opción (1-2): ");

                    int opcionEliminarCal = -1;
                    int tempOpcionEliminarCal = leerEnteroValidado("", 1, 2);
                    if (tempOpcionEliminarCal != -1) opcionEliminarCal = tempOpcionEliminarCal;

                    switch (opcionEliminarCal)
                    {
                        case 1:
                            if (actividadesActuales > 0)
                            {
                                System.out.println("\nACTIVIDADES DISPONIBLES PARA ELIMINAR:");
                                for (int i = 0; i < actividadesActuales; i++)
                                {
                                    System.out.println((i + 1) + ": " + String.format("%02d / %02d / %d",
                                            fechas[usuarioIndice][0][i], fechas[usuarioIndice][1][i], fechas[usuarioIndice][2][i]) +
                                            " - " + actividadesCalendario[usuarioIndice][i]);
                                }

                                int indice = -1;
                                do
                                {
                                    indice = leerEnteroValidado("\nIngresa el índice de la actividad a eliminar: ", 1, actividadesActuales);
                                } while (indice == -1);

                                if (indice >= 1 && indice <= actividadesActuales)
                                {
                                    System.out.println("\n--- ACTIVIDAD A ELIMINAR ---");
                                    System.out.println("Fecha: " + String.format("%02d/%02d/%04d",
                                            fechas[usuarioIndice][0][indice - 1],
                                            fechas[usuarioIndice][1][indice - 1],
                                            fechas[usuarioIndice][2][indice - 1]));
                                    System.out.println("Actividad: " + actividadesCalendario[usuarioIndice][indice - 1]);
                                    System.out.println("Notas: " + notasCalendario[usuarioIndice][indice - 1]);

                                    System.out.print("\n¿Estás seguro de eliminar esta actividad? (s/n): ");
                                    String confirmacion = scanner.nextLine().toLowerCase();

                                    if (confirmacion.equals("s") || confirmacion.equals("si"))
                                    {
                                        for (int i = indice - 1; i < actividadesActuales - 1; i++)
                                        {
                                            fechas[usuarioIndice][0][i] = fechas[usuarioIndice][0][i + 1];
                                            fechas[usuarioIndice][1][i] = fechas[usuarioIndice][1][i + 1];
                                            fechas[usuarioIndice][2][i] = fechas[usuarioIndice][2][i + 1];
                                            actividadesCalendario[usuarioIndice][i] = actividadesCalendario[usuarioIndice][i + 1];
                                            notasCalendario[usuarioIndice][i] = notasCalendario[usuarioIndice][i + 1];
                                        }
                                        contadorActividadesCalendario[usuarioIndice]--;
                                        actividadesActuales = contadorActividadesCalendario[usuarioIndice];

                                        System.out.println("\n¡Actividad eliminada del calendario!");
                                        System.out.println("Total de actividades: " + actividadesActuales);
                                    }
                                    else
                                    {
                                        System.out.println("\nEliminación cancelada.");
                                    }
                                }
                                else
                                {
                                    System.out.println("Índice no válido.");
                                }
                            }
                            else
                            {
                                System.out.println("No hay actividades para eliminar en el calendario.");
                            }
                            break;

                        case 2:
                            if (actividadesActuales > 0)
                            {
                                System.out.println("\n=== ELIMINAR TODAS LAS ACTIVIDADES DEL CALENDARIO ===");
                                System.out.println("Vas a eliminar TODAS las actividades del calendario.");
                                System.out.println("Total de actividades a eliminar: " + actividadesActuales);
                                System.out.println("\n¡ESTA ACCIÓN NO SE PUEDE DESHACER!");

                                System.out.println("\n--- RESUMEN DE ACTIVIDADES ---");
                                for (int i = 0; i < actividadesActuales; i++)
                                {
                                    System.out.println((i + 1) + ". " + String.format("%02d/%02d/%04d",
                                            fechas[usuarioIndice][0][i], fechas[usuarioIndice][1][i], fechas[usuarioIndice][2][i]) +
                                            " - " + actividadesCalendario[usuarioIndice][i]);
                                }

                                System.out.print("\n¿Estás SEGURO de que quieres eliminar TODAS las actividades? (escribe 'BORRAR' para confirmar): ");
                                String confirmacion = scanner.nextLine();

                                if (confirmacion.equalsIgnoreCase("BORRAR"))
                                {
                                    System.out.print("¿REALMENTE estás seguro? Esta acción borrará " + actividadesActuales + " actividades. (s/n): ");
                                    String confirmacion2 = scanner.nextLine().toLowerCase();

                                    if (confirmacion2.equals("s") || confirmacion2.equals("si"))
                                    {
                                        contadorActividadesCalendario[usuarioIndice] = 0;
                                        actividadesActuales = 0;

                                        System.out.println("\n========================================");
                                        System.out.println("¡TODAS las actividades han sido eliminadas!");
                                        System.out.println("Total de actividades: 0");
                                        System.out.println("========================================");
                                    }
                                    else
                                    {
                                        System.out.println("\nEliminación cancelada.");
                                    }
                                }
                                else
                                {
                                    System.out.println("\nEliminación cancelada. No escribiste 'BORRAR'.");
                                }
                            }
                            else
                            {
                                System.out.println("No hay actividades para eliminar en el calendario.");
                            }
                            break;

                        default:
                            if (opcionEliminarCal != -1)
                            {
                                System.out.println("Opción no válida.");
                            }
                    }
                    System.out.println("\nPresiona la tecla Enter para continuar...");
                    scanner.nextLine();
                    break;

                case 4:
                    continuar = false;
                    System.out.println("Saliendo del Módulo Calendario....");
                    break;

                default:
                    if (opcion != -1)
                    {
                        System.out.println("Opción no válida, intenta nuevamente");
                    }
                    System.out.println("Presiona la tecla Enter para continuar.");
                    scanner.nextLine();
                    break;
            }
        }
    }

    public static void OrdenarBurbujaCalendarioPorProximidad(int usuarioIndice, int actividadesActuales)
    {
        if (actividadesActuales == 0)
        {
            System.out.println("No hay actividades registradas para ordenar.");
            return;
        }

        LocalDate fechaActual = LocalDate.now();
        int diaActual = fechaActual.getDayOfMonth();
        int mesActual = fechaActual.getMonthValue();
        int añoActual = fechaActual.getYear();

        int[] diasFaltantes = new int[actividadesActuales];
        for (int i = 0; i < actividadesActuales; i++)
        {
            LocalDate fechaActividad = LocalDate.of(
                    fechas[usuarioIndice][2][i],
                    fechas[usuarioIndice][1][i],
                    fechas[usuarioIndice][0][i]
            );
            diasFaltantes[i] = (int) java.time.temporal.ChronoUnit.DAYS.between(fechaActual, fechaActividad);
        }

        for (int i = 0; i < actividadesActuales - 1; i++)
        {
            for (int j = 0; j < actividadesActuales - 1 - i; j++)
            {
                if (diasFaltantes[j] > diasFaltantes[j + 1])
                {
                    int tempDias = diasFaltantes[j];
                    diasFaltantes[j] = diasFaltantes[j + 1];
                    diasFaltantes[j + 1] = tempDias;

                    for (int k = 0; k < 3; k++)
                    {
                        int tempFecha = fechas[usuarioIndice][k][j];
                        fechas[usuarioIndice][k][j] = fechas[usuarioIndice][k][j + 1];
                        fechas[usuarioIndice][k][j + 1] = tempFecha;
                    }

                    String tempActividad = actividadesCalendario[usuarioIndice][j];
                    actividadesCalendario[usuarioIndice][j] = actividadesCalendario[usuarioIndice][j + 1];
                    actividadesCalendario[usuarioIndice][j + 1] = tempActividad;

                    String tempNota = notasCalendario[usuarioIndice][j];
                    notasCalendario[usuarioIndice][j] = notasCalendario[usuarioIndice][j + 1];
                    notasCalendario[usuarioIndice][j + 1] = tempNota;
                }
            }
        }

        System.out.println("=== ACTIVIDADES REGISTRADAS EN CALENDARIO de " + usuarioActual + " (Ordenadas por Proximidad) ===");
        System.out.println("Fecha actual: " + String.format("%02d/%02d/%04d", diaActual, mesActual, añoActual));
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < actividadesActuales; i++)
        {
            LocalDate fechaActividad = LocalDate.of(
                    fechas[usuarioIndice][2][i],
                    fechas[usuarioIndice][1][i],
                    fechas[usuarioIndice][0][i]
            );

            String estado = "";
            if (diasFaltantes[i] < 0)
                estado = " (PASADA)";
            else if (diasFaltantes[i] == 0)
                estado = " (HOY)";
            else if (diasFaltantes[i] == 1)
                estado = " (MAÑANA)";
            else if (diasFaltantes[i] <= 7)
                estado = " (PRÓXIMA SEMANA)";

            System.out.println("Posición: " + (i + 1) + estado);
            System.out.println(String.format("Fecha: %02d / %02d / %d",
                    fechas[usuarioIndice][0][i],
                    fechas[usuarioIndice][1][i],
                    fechas[usuarioIndice][2][i]
            ));
            System.out.println("Días faltantes: " + diasFaltantes[i] + " días");
            System.out.println("Actividad: " + actividadesCalendario[usuarioIndice][i]);
            System.out.println("Notas: " + notasCalendario[usuarioIndice][i]);
            System.out.println("-----------------------------------");
        }
    }

    private static int obtenerIndiceUsuarioActual()
    {
        if (usuarioActual == null)
        {
            return -1;
        }
        for (int i = 0; i < contadorUsuarios; i++)
        {
            if (usuarios[i][0].equals(usuarioActual))
            {
                return i;
            }
        }
        return -1;
    }

    public static int leerEnteroValidado(String mensaje, int min, int max)
    {
        System.out.print(mensaje);
        int valor = -1;

        if (scanner.hasNextInt())
        {
            valor = scanner.nextInt();
            scanner.nextLine();

            if (valor < min || valor > max)
            {
                System.out.println("ERROR: El valor debe estar entre " + min + " y " + max + ".");
                return -1;
            }
            return valor;
        }
        else
        {
            System.out.println("ERROR: Entrada no válida. Por favor, ingresa un número entero.");
            scanner.next();
            scanner.nextLine();
            return -1;
        }
    }

    public static void limpiarPantalla()
    {
        for (int i = 0; i < 50; i++)
        {
            System.out.print("\n");
        }
    }

    public static boolean esDiaValido(String dia)
    {
        String diaLowerCase = dia.toLowerCase()
                .replace("é", "e")
                .replace("á", "a")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u");

        String[] diasValidos = {"lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"};

        for (String d : diasValidos)
        {
            if (d.equals(diaLowerCase))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean esFechaValida(int dia, int mes, int año)
    {
        if (mes < 1 || mes > 12)
        {
            return false;
        }

        int diasEnMes;

        switch (mes)
        {
            case 2:
                boolean esBisiesto = (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
                diasEnMes = esBisiesto ? 29 : 28;
                break;

            case 4:
            case 6:
            case 9:
            case 11:
                diasEnMes = 30;
                break;

            default:
                diasEnMes = 31;
                break;
        }

        if (dia < 1 || dia > diasEnMes)
        {
            return false;
        }

        return true;
    }

    private static int obtenerValorDia(String dia)
    {
        String diaNormalizado = dia.toLowerCase()
                .replace("é", "e")
                .replace("á", "a")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u");

        switch (diaNormalizado)
        {
            case "lunes":
                return 1;
            case "martes":
                return 2;
            case "miercoles":
                return 3;
            case "jueves":
                return 4;
            case "viernes":
                return 5;
            case "sabado":
                return 6;
            case "domingo":
                return 7;
            default:
                return 0;
        }
    }

    private static int obtenerValorDiaSemanaActual()
    {
        LocalDate hoy = LocalDate.now();
        return hoy.getDayOfWeek().getValue();
    }
}