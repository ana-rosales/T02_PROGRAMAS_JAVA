/**
 * Autora: Ana Paula Rosales Olguin
 * Fecha: 07 de noviembre de 2025
 */

import java.util.Scanner;

import clases.NumerosParImpar;

public class Prueba {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un número: ");
        int numero = scanner.nextInt();
        NumerosParImpar.valida(numero);
        scanner.close();
    }
}