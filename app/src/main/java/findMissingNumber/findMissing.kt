package findMissingNumber

import java.util.ArrayList
import java.util.Scanner

fun main() {
    val array = insertArray()
    findNumberMissing(array)
}

fun insertArray(): ArrayList<Int> {
    val scanner = Scanner(System.`in`)
    println("Input size of array:")
    val size = scanner.nextInt()
    val numbers = ArrayList<Int>()
    println("Input array:")
    for (i in 0 until size) {
        numbers.add(scanner.nextInt())
    }
    return numbers
}

fun findNumberMissing(array: ArrayList<Int>) {
    array.sort()
    print("Missing number: ")
    for (i in 0 until array.size - 1) {
        if (array[i + 1] - array[i] != 1) {
            print("${array[i] + 1} ")
        }
    }
}
