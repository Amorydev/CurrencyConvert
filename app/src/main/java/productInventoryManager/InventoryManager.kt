package productInventoryManager

import java.text.NumberFormat
import java.util.Locale
import java.util.Scanner

fun main() {
    val product = initProduct()
    println("---------------MENU---------------")
    println("1. Calculator total value of products")
    println("2. Find the most expensive product")
    println("3. Check if a product name exists")

    val scanner = Scanner(System.`in`)

    print("Enter your choice: ")
    val choice = scanner.nextInt()

    when (choice) {
        1 -> calculatorTotalValueProduct(product)
        2 -> findExpensiveProduct(product)
        3 -> existsProductName(product)
        else -> println("Invalid choice")
    }
}

fun initProduct(): List<Product> {
    val product = listOf(
        Product("Laptop", 999.99, 5),
        Product("Smartphone", 499.99, 10),
        Product("Tablet", 299.99, 0),
        Product("Smartwatch", 199.99, 3)
    )
    return product
}

fun existsProductName(product: List<Product>) {
    val scanner = Scanner(System.`in`)
    print("Enter the product name to check if it exists:")
    val name = scanner.nextLine()
    val exits = product.any { it.name.equals(name, ignoreCase = true) }
    if (exits) {
        println("True")
    } else {
        println("False")
    }
}

fun findExpensiveProduct(product: List<Product>) {
    val expensiveProduct = product.maxBy { it.price }
    println("The most expensive product: ${expensiveProduct.name}")
}

fun calculatorTotalValueProduct(product: List<Product>) {
    val totalValue = product.filter {
        it.quantity > 0
    }.sumOf {
        it.price * it.quantity
    }
    println(
        "Total value of products: ${
            NumberFormat.getNumberInstance(Locale.US).format(totalValue)
        }"
    )
}
