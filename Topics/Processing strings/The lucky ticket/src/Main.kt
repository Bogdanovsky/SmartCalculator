fun main() {
    val input = readln()
    var halfOne = 0
    var halfTwo = 0
    for (i in 0..2) {
        halfOne = halfOne + input[i].toInt()
        halfTwo = halfTwo + input[5 - i].toInt()
    }
    fun x() = if (halfOne == halfTwo) {
        print("Lucky")
    } else {
        print("Regular")
    }    
    x()
}
