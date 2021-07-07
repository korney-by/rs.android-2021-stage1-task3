package subtask3

fun main() {
    val tf = TelephoneFinder()
    tf.findAllNumbersFromGivenNumber("-8675309")?.let {
        for (i in it.indices) {
            println(it[i])
        }
    }
}

class TelephoneFinder {

    fun findAllNumbersFromGivenNumber(number: String): Array<String>? {
        if (number.any { !it.isDigit() }) {
            return null
        }
        val arrNeighborsNumbers: MutableList<String> = ArrayList()
        for (i in number.indices) {
            arrNeighborsNumbers.addAll(getNeighborsForOneDigit(number, i))
        }
        return arrNeighborsNumbers.toTypedArray()
    }

    private fun getNeighborsForOneDigit(number: String, numberDigit: Int): List<String> {
        val neighborsList: MutableList<String> = ArrayList()
        getNeighborDigits(number[numberDigit]).let {
            for (i in it.indices) {
                neighborsList.add(replaceChar(number, numberDigit, it[i]))
            }
        }
        return neighborsList.toList()
    }

    private fun replaceChar(string: String, numberChar: Int, char: Char): String {
        val sb = StringBuilder()
        sb.append(string, 0, numberChar)
        sb.append(char)
        sb.append(string, numberChar + 1, string.length)
        return sb.toString()
    }

    private fun getNeighborDigits(digit: Char): Array<Char> {
        return when (digit) {
            '1' -> arrayOf('2', '4')
            '2' -> arrayOf('1', '3', '5')
            '3' -> arrayOf('2', '6')
            '4' -> arrayOf('1', '5', '7')
            '5' -> arrayOf('2', '4', '6', '8')
            '6' -> arrayOf('3', '5', '9')
            '7' -> arrayOf('4', '8')
            '8' -> arrayOf('0', '5', '7', '9')
            '9' -> arrayOf('6', '8')
            else -> arrayOf('8') // 0
        }
    }
}
