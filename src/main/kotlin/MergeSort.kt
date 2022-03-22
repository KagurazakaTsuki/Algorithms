class MergeSort<T> {
    enum class MergeSortImpl {
        TopDown
    }

    fun mergeSort(list: List<Comparable<T>>, mode: MergeSortImpl): List<Comparable<T>> {
        return when (mode) {
            MergeSortImpl.TopDown -> mergeSortTopDown(ArrayDeque(list))
        }
    }

    // parameters left and right should be sorted list
    @Suppress("UNCHECKED_CAST")
    private fun merge(left: ArrayDeque<Comparable<T>>, right: ArrayDeque<Comparable<T>>): ArrayDeque<Comparable<T>> {
        val result = ArrayDeque<Comparable<T>>()

        // Puts the smaller element in left or right to result's front until one list empty
        while (left.isNotEmpty() && right.isNotEmpty()) {
            if (left.first() <= right.first() as T)
                result.add(left.removeFirst())
            else
                result.add(right.removeFirst())
        }

        // Either left or right may have elements left; consume them.
        // (Only one of the following condition will actually be entered.)
        if (left.isNotEmpty()) result.addAll(left)
        if (right.isNotEmpty()) result.addAll(right)

        return result
    }

    private fun mergeSortTopDown(list: ArrayDeque<Comparable<T>>): ArrayDeque<Comparable<T>> {
        // Base case. A list of zero or one element is sorted, by definition.
        if (list.size == 1)
            return list

        var left = ArrayDeque<Comparable<T>>()
        var right = ArrayDeque<Comparable<T>>()

        // Recursive case. Divide the list into two equal-sized sub-lists
        for (i in list.indices) {
            if (i < list.size / 2)
                left.add(list[i])
            else
                right.add(list[i])
        }

        // Recursively sort both sub-lists.
        left = mergeSortTopDown(left)
        right = mergeSortTopDown(right)

        // Merge the now-sorted sub-lists.
        return merge(left, right)
    }
}
