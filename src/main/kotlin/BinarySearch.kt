class BinarySearch<T> {
    enum class BinarySearchImpl {
        Recursive
    }

    // parameter list should be sorted list
    fun binarySearch(list: List<Comparable<T>>, target: Comparable<T>, mode: BinarySearchImpl): Int {
        return when (mode) {
            BinarySearchImpl.Recursive -> binarySearchRecursive(list, target)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun binarySearchRecursive(
        list: List<Comparable<T>>, target: Comparable<T>,
        low: Int = 0, high: Int = list.size
    ): Int {
        if (low == high && list[low] == target) return low
        if (low == high && list[low] != target) return -1
        val pivot = (low + high) / 2

        var newLow = low
        var newHigh = high
        if (target > list[pivot] as T) newLow = pivot + 1
        if (target <= list[pivot] as T) newHigh = pivot

        return binarySearchRecursive(list, target, newLow, newHigh)
    }
}