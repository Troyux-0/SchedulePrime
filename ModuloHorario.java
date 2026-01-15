//Esqueleto del módulo horario (Marvin)
import java.util.Scanner;

public class ModuloHorario
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String [] dias = new String [100];
        String [] horas = new String [100];
        String [] actividades = new String [100];
        String [] notas = new String [100];

        int contadorActividades = 0;
        boolean continuar = true;
        while (continuar)
        {
            System.out.println("MÓDULO HORARIO");
            System.out.println("Creacion de horario personalizado");
            System.out.println("Actividades registradas: "+ contadorActividades);
            System.out.println();

            System.out.println("1.- Ingreso");
            System.out.println("2.-Gestión de notas");
            System.out.println("3.- Eliminación");
            System.out.println("4.- Salir del módulo");
            System.out.println("Elige una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion)
            {
                case 1 :
                    if(contadorActividades<100) {
                        System.out.println("INGRESO");
                        System.out.println("Actividades registradas " + contadorActividades);
                        System.out.println("Día (Lunes, Martes, Miércoles, etc) ");
                        dias[contadorActividades] = sc.nextLine();
                        System.out.println("Hora (Formato: HH:MM): ");
                        horas[contadorActividades] = sc.nextLine();
                        System.out.println("Actividad: ");
                        actividades[contadorActividades] = sc.nextLine();
                        System.out.println("Notas adicionales: ");
                        notas[contadorActividades] = sc.nextLine();
                        contadorActividades++;
                        System.out.println("¡Actividad agregada al horario!");
                        System.out.println("Total de actividades: " + contadorActividades);
                    } else
                        {
                            System.out.println("Límite de actividades alcanzado");
                        }
                        break;
                case 2:
                    System.out.println("GESTIÓN DE NOTAS");
                    System.out.println("1.- Ver horario con notas");
                    System.out.println("2.-Añadir/modificar notas a actividad existente");
                    System.out.println("Elige una opción: ");
                    opcion = sc.nextInt();
                    switch (opcion)
                    {
                        case 1:
                        if (contadorActividades > 0)
                        {
                            System.out.println("HORARIO COMPLETO");
                            for(int i = 0;i < contadorActividades; i++)
                            {
                                System.out.println("Indice:" + (i+1));
                                System.out.println("Día: " + dias[i]);
                                System.out.println("Hora: " + horas[i]);
                                System.out.println("Actividad: " + actividades[i]);
                                System.out.println("Notas: " + notas[i]);
                                System.out.println("-----------------");
                            }

                        }
                        else
                        {
                            System.out.println("No hay actividades en el horario");
                        }
                        break;
                        case 2:
                        if (contadorActividades > 0)
                        {
                            System.out.println("NOTAS");
                            System.out.println("Actividades en horario:");
                            for(int i = 0;i<contadorActividades; i ++)
                            {
                                System.out.println("índice: "+ (i+1));
                                System.out.println(dias[i]);
                                System.out.println(horas[i]);
                                System.out.println(actividades[i]);
                                System.out.println("--------------");
                            }
                            System.out.println("Ingresa el índice de la actividad: ");
                            int indice = sc.nextInt();
                            sc.nextLine();
                            if(indice >= 1 && indice<= contadorActividades)
                            {
                                System.out.println("Actividad seleccionada: "+actividades[indice-1]);
                                System.out.println("Notas actuales: "+ notas[indice-1]);
                                System.out.println("Ingresa las nuevas notas: ");
                                notas[indice-1]= sc.nextLine();
                                System.out.println("¡Notas actualizadas!");
                            }
                            else
                            {
                                System.out.println("Indice no válido");
                            }
                            break;

                        }
                        else
                        {
                            System.out.println("No hay actividades para registrar");
                        }
                        break;
                        default:
                            System.out.println("Opcion no válida");
                    }
                    sc.nextLine();
                    break;
                case 3:
                    if (contadorActividades >0 )
                    {
                        System.out.println("ELIMINACIÓN");
                        System.out.println("Actividades registradas: "+ contadorActividades);
                        System.out.println("Actividades disponibles para eliminar: " );
                        for (int i = 0; i < contadorActividades; i++)
                        {
                            System.out.println("Indice " + (i+1)+ ":"+ dias[i]+ "  " +horas[i]+" "+actividades[i]);
                        }
                        System.out.println("Ingresa el índice de la actividad a eliminar");
                        int indice=sc.nextInt();
                        sc.nextLine();

                        if (indice >= 1 && indice <= contadorActividades)
                        {
                            for (int i=indice-1; i < contadorActividades-1;i++)
                            {
                                dias[i] = dias[i+1];
                                horas[i] = horas [i+1];
                                actividades[i] = actividades[i+1];
                                notas[i] = notas [i+1];
                            }
                            contadorActividades= contadorActividades-1;
                            System.out.println("¡Actividad eliminada del horario!");
                            System.out.println("Total de actividades: "+ contadorActividades);
                        } else
                        {
                            System.out.println("Indice no valido");
                        }

                    }
                    break;

                case 4:
                    continuar = false;
                    System.out.println("Saliendo del Módulo Horario....");
                    break;
                default:
                    System.out.println("Opcion no válida, intenta nuevamente");
                    break;

                    }

            }
        }
    }
