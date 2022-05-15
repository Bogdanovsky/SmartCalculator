fun main() {
    var mainCounter = 0
    var vowelCounter = 0
    var consCounter = 0
    val input = readln()
    val vowels = "aoueiy"
    val consonants = "bcdfghjklmnpqrstvwxz"
    
    for (ch in input) {
    if (vowels.contains(ch)) {
        vowelCounter++
        if (vowelCounter > 2) {
            mainCounter++
            vowelCounter = 1
        }
    } else {
        vowelCounter = 0
        }
    }
         
    for (ch in input) {
    if (consonants.contains(ch)) {
        consCounter++
        if (consCounter > 2) {
            mainCounter++
            consCounter = 1
        }
    } else {
        consCounter = 0
        }
    }
    print(mainCounter)
}
