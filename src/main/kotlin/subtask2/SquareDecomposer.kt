package subtask2

fun main() {
    val sd = SquareDecomposer()
    val ar = sd.decomposeNumber(5)
    if (ar == null) {
        println("Никак")
    } else {
        print("Best result: ")
        sd.printArr(ar, ar.size)
    }
}

class SquareDecomposer {

    // TODO: Complete the following function
    fun decomposeNumber(number: Int): Array<Int>? {
        if (number <= 0) {
            return null
        }

        val arrDigits = getArrPossibleDigits(number)
        val numberSquare = number.toLong() * number
        if (isSquaresEqual( arrDigits,  numberSquare)) {
            return arrDigits
        }

        return getMaxValueVariant(number)
    }

    fun getMaxValueVariant(number: Int): Array<Int>? {
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
        val numberSquare:Long = (number * number).toLong()

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

    fun getPartArray(arrDigits: Array<Int>, count: Int): Array<Int> {
        return Array(count) { i -> arrDigits[i] }
    }

    fun printArr(arrDigits: Array<Int>, count: Int) {
        for (i in 0..count - 1) {
            print("${arrDigits[i]} ")
        }
        println()
    }

    fun getNextCombinations(arrDigits: Array<Int>, count: Int): Boolean {
        val n = arrDigits.size
        val k = count
        for (i in k - 1 downTo 0) {
            if (arrDigits[i] < n - k + i + 1) {
                ++arrDigits[i]
                for (j in i + 1 until k) {
                    arrDigits[j] = arrDigits[j - 1] + 1
                }
                return true
            }
        }
        return false
    }

    fun isSquaresEqual(arrDigits: Array<Int>, valueSquare: Long): Boolean {
        var SquareSumm = 0L
        arrDigits.let {
            for (i in it.size - 1 downTo 0) {
                SquareSumm += it[i]*it[i]
                if (SquareSumm>valueSquare) {
                    return false
                }
            }
        }
        return (SquareSumm==valueSquare)
    }

    fun getArrPossibleDigits(number: Int): Array<Int> {
        return Array(number - 1) { i -> i + 1 }
    }

}
