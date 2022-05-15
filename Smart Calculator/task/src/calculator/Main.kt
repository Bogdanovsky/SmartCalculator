package calculator

fun main() {
    calc()
}

val variables = mutableMapOf<String, Int>()

fun calc() {
    val input = readln().trim()

    if ("/exit" == input) {
        println("Bye!")
    } else if ("/help" == input) {
        println("The program calculates numbers")
        calc()
    } else if (input.matches("/[a-z]+".toRegex())) {
        println("Unknown command")
        calc()
    } else if ("" == input) {
        calc()
    } else if (input.matches("[a-zA-Z]+".toRegex())) {
        if (variables.containsKey(input)) {
            println(variables[input])
        } else {
            println("Unknown variable")
        }
        calc()
    } else if (input.matches("[a-zA-Z]+\\s*=\\s*-*\\d+".toRegex())) {
        var (k, v) = input.split("\\s*=\\s*".toRegex())
        variables[k] = v.toInt()
        calc()
    } else if (input.matches("[a-zA-Z]+\\s*=\\s*[a-zA-Z]+".toRegex())) {
        var (k, k_0) = input.split("\\s*=\\s*".toRegex())
        if (variables.containsKey(k_0)) {
            variables.put(k, variables[k_0] ?: 0)
        } else {
            println("Unknown variable")
        }
        calc()
    } else if (input.matches(".+\\s*=\\s*\\w+".toRegex())) {
        println("Invalid identifier")
        calc()
    } else if (input.matches("[a-zA-Z]+\\s*=\\s*.+".toRegex())) {
        println("Invalid assignment")
        calc()
    } else if (invalidParentesisAndOperands(input)) {
        println("Invalid expression")
        calc()
    } else {
        val tokens = parseInput(input)
        println(calculate(tokens).toInt())
        calc()
    }
}

fun invalidParentesisAndOperands(input: String): Boolean {
    var openingPars = 0
    var closingPars = 0
    for (symbols in input) {
        if ('(' == symbols) openingPars++
        if (')' == symbols) closingPars++
    }
    if (openingPars != closingPars) return true
    if (input.matches(".*\\*\\*.*".toRegex())) return true
    if (input.matches(".*//.*".toRegex())) return true
    if (input.matches(".*\\*/.*".toRegex())) return true
    if (input.matches(".*/\\*.*".toRegex())) return true
    return false
}

fun parseInput(input: String): MutableList<String> {
    val mutList = (input.split("")).toMutableList()
    while (mutList.contains("") || mutList.contains(" ")) {
        mutList.remove("")
        mutList.remove(" ")
    }
    var needToCheck = true
    while (needToCheck) {
        needToCheck = false
        for (i in 0 .. mutList.lastIndex) {
                 if (i != 0 && mutList[i].matches("[a-zA-Z]".toRegex()) && mutList[i - 1].matches("[a-zA-Z]+".toRegex())) {
                mutList[i - 1] = mutList[i - 1] + mutList[i]
                mutList.removeAt(i)
                needToCheck = true
                break
            } else if (i != 0 && mutList[i].matches("\\d".toRegex()) && mutList[i - 1].matches("\\d+".toRegex())) {
                mutList[i - 1] = mutList[i - 1] + mutList[i]
                mutList.removeAt(i)
                needToCheck = true
                break
            } else if (i != 0 && mutList[i].matches("\\+".toRegex()) && mutList[i - 1].matches("\\+".toRegex())) {
                mutList.removeAt(i)
                needToCheck = true
                break
            } else if (i != 0 && mutList[i].matches("-".toRegex()) && mutList[i - 1].matches("-".toRegex())) {
                mutList[i - 1] = "+"
                mutList.removeAt(i)
                needToCheck = true
                break
            } else if (i != 0 && mutList[i].matches("-".toRegex()) && mutList[i - 1].matches("\\+".toRegex())) {
                mutList[i - 1] = "-"
                mutList.removeAt(i)
                needToCheck = true
                break
//            } else if ((i == 0 || mutList[i - 1].matches("[*/()^]".toRegex())) && mutList[i].matches("-".toRegex()) && mutList[i + 1].matches("[0-9]".toRegex())) {
//                mutList[i + 1] = "-" + mutList[i + 1]
//                mutList.removeAt(i)
//                needToCheck = true
//                break
            } else if (((i == 0) || mutList[i - 1].matches("[*/(^]".toRegex())) && (mutList[i].matches("\\+".toRegex()))) {
                mutList.removeAt(i)
                needToCheck = true
                break
            } else if (mutList[i].matches("[a-zA-Z]+".toRegex())) {
                if (variables.containsKey(mutList[i])) {
                    mutList[i] = variables[mutList[i]].toString()
                    needToCheck = true
                    break
                } else {
                    println("ERRORRRR")
                }
            }


        }
    }
    return mutList
}

fun calculate(tokens: MutableList<String>): String {
    val operatorsPrecedence = mapOf(
        "(" to 4,
        "^" to 1,
        "*" to 2,
        "/" to 2,
        "+" to 3,
        "-" to 3,
    )
    val operatorStack = mutableListOf<String>()
    val outputQueue = mutableListOf<String>()
    for (token in tokens) {
        if (token.matches("[-]*[0-9]+".toRegex())) {
            outputQueue.add(token)
        } else if (operatorStack.isEmpty() || "(" == token) {
            operatorStack.add(token)
        } else if (")" == token) {
            while (operatorStack.last() != "(") {
                outputQueue.add(operatorStack.last())
                operatorStack.removeAt(operatorStack.lastIndex)
            }
            operatorStack.removeAt(operatorStack.lastIndex)
        } else if (operatorsPrecedence[token]!! >= operatorsPrecedence[operatorStack.last()]!!) {
            do {
                outputQueue.add(operatorStack.last())
                operatorStack.removeAt(operatorStack.lastIndex)
            } while (operatorStack.isNotEmpty() && "(" != operatorStack.last() && operatorsPrecedence[token]!! >= operatorsPrecedence[operatorStack.last()]!!)
            operatorStack.add(token)
        } else {
            operatorStack.add(token)
        }
    }
    while (operatorStack.isNotEmpty()) {
        outputQueue.add(operatorStack.last())
        operatorStack.removeAt(operatorStack.lastIndex)
    }
    val result = mutableListOf<String>()
    for (token in outputQueue) {
        if (token.matches("[-]*[0-9]+".toRegex())) {
            result.add(token)
        } else {
            if ("*" == token) {
                val temp = result[result.lastIndex - 1].toInt() * result[result.lastIndex].toInt()
                result.removeAt(result.lastIndex - 1)
                result.removeAt(result.lastIndex)
                result.add(temp.toString())
            } else if ("/" == token) {
                val temp = result[result.lastIndex - 1].toInt() / result[result.lastIndex].toInt()
                result.removeAt(result.lastIndex - 1)
                result.removeAt(result.lastIndex)
                result.add(temp.toString())
            } else if ("+" == token) {
                val temp = result[result.lastIndex - 1].toInt() + result[result.lastIndex].toInt()
                result.removeAt(result.lastIndex - 1)
                result.removeAt(result.lastIndex)
                result.add(temp.toString())
            } else if ("-" == token) {
                val temp = result[result.lastIndex - 1].toInt() - result[result.lastIndex].toInt()
                result.removeAt(result.lastIndex - 1)
                result.removeAt(result.lastIndex)
                result.add(temp.toString())
            } else if ("^" == token) {
                val temp = power(result[result.lastIndex - 1].toInt(), result[result.lastIndex].toInt())
                result.removeAt(result.lastIndex - 1)
                result.removeAt(result.lastIndex)
                result.add(temp.toString())
            }
        }
    }
    return result[0]
}

fun power(base: Int, power: Int): Int {
    var result = 1
    for (i in 0 until power) {
        result *= base
    }
    return result
}
