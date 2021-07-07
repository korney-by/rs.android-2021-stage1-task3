package subtask2

fun main() {
    val sd = SquareDecomposer()
    val ar = sd.decomposeNumber(15)
    if (ar == null) {
        println("Impossible")
    } else {
        print("Best result: ")
        printArr(ar, ar.size)
    }
}

fun printArr(arrDigits: Array<Int>, count: Int) {
    for (i in 0 until count) {
        print("${arrDigits[i]} ")
    }
    println()
}

class SquareDecomposer {
    // Fast Recursive algorithm
    private var baseSquare: Long = 0
    private val arrResult: MutableList<Int> = ArrayList()

    // TODO: Complete the following function
    fun decomposeNumber(number: Int): Array<Int>? {
        if (number <= 0) {
            return null
        }
        baseSquare = getSquare(number)
        return startRecursiveFind(number)
    }

    private fun addToResult(number: Int) {
        arrResult.add(number)
    }

    private fun startRecursiveFind(number: Int): Array<Int>? {

        for (i in 1..number) {
            arrResult.clear()
            if (getNextNumber(number - i, 0)) {
                return arrResult.toTypedArray()
            }
        }
        return null
    }

    private fun getNextNumber(number: Int, currentSumma: Long): Boolean {
        // если равно
        val newSumma = currentSumma + getSquare(number)
        if (newSumma == baseSquare) {
            addToResult(number)
            return true
        }

        if (number == 0 || newSumma > baseSquare) {
            return false
        }

        //если нашлось то запоминаем
        for (i in 1..number) {
            if (getNextNumber(number - i, newSumma)) {
                addToResult(number)
                return true
            }
        }
        return false
    }

    private fun getSquare(number: Int): Long {
        return number.toLong() * number
    }
}

class SquareDecomposerSlow {
    // very slow algorithm with full search of combinations
    // good works only for small numbers (<30)

    fun decomposeNumber(number: Int): Array<Int>? {
        if (number <= 0) {
            return null
        }

        val arrDigits = getArrPossibleDigits(number)
        if (isSquaresEqual(arrDigits, getSquare(number))) {
            return arrDigits
        }
        return getMaxValueVariant(number)

    }

    private fun getSquare(number: Int): Long {
        return number.toLong() * number
    }

    private fun getMaxValueVariant(number: Int): Array<Int>? {
        var maxValue = 0
        var resultIndex = -1
        getTrueCombinations(number).let {
            for (i in it.indices) {
                val array = it[i]
                printArr(array, array.size)
                // checked only MAX value in array, that rightmost value
                if (array[array.size - 1] > maxValue) {
                    maxValue = array[array.size - 1]
                    resultIndex = i
                }
            }
            if (resultIndex >= 0) {
                return it[resultIndex]
            }
        }
        return null
    }


    private fun getTrueCombinations(number: Int): List<Array<Int>> {
        val trueCombinations: MutableList<Array<Int>> = ArrayList()
        val numberSquare: Long = (number * number).toLong()

        for (i in number - 2 downTo 0) {
            val arrDigits = getArrPossibleDigits(number)
            val possibleCombinations = getCombinations(arrDigits, i).toMutableList()
            possibleCombinations.forEach {
                if (isSquaresEqual(it, numberSquare)) {
                    trueCombinations.add(it)
                }
            }
        }
        return trueCombinations.toList()
    }

    private fun getCombinations(arrDigits: Array<Int>, count: Int): List<Array<Int>> {
        val arrayList: MutableList<Array<Int>> = ArrayList()

        //printArr(arrDigits, count)
        while (getNextCombinations(arrDigits, count)) {
            arrayList.add(getPartArray(arrDigits, count))
            //printArr(arrDigits, count)
        }
        return arrayList.toList()
    }

    private fun getPartArray(arrDigits: Array<Int>, count: Int): Array<Int> {
        return Array(count) { i -> arrDigits[i] }
    }

    private fun getNextCombinations(arrDigits: Array<Int>, count: Int): Boolean {
        val n = arrDigits.size
        for (i in count - 1 downTo 0) {
            if (arrDigits[i] < n - count + i + 1) {
                ++arrDigits[i]
                for (j in i + 1 until count) {
                    arrDigits[j] = arrDigits[j - 1] + 1
                }
                return true
            }
        }
        return false
    }

    private fun isSquaresEqual(arrDigits: Array<Int>, valueSquare: Long): Boolean {
        var squareSumma = 0L
        arrDigits.let {
            for (i in it.size - 1 downTo 0) {
                squareSumma += it[i] * it[i]
                if (squareSumma > valueSquare) {
                    return false
                }
            }
        }
        return (squareSumma == valueSquare)
    }

    private fun getArrPossibleDigits(number: Int): Array<Int> {
        return Array(number - 1) { i -> i + 1 }
    }

}
