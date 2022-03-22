import kotlin.test.Test
import kotlin.test.assertEquals

class MergeSortTest {

    @Test
    fun topDownTest() {
        val numbers = (0..100).step(3).toList().shuffled()

        val sorted = MergeSort<Int>().mergeSort(numbers, MergeSort.MergeSortImpl.TopDown)

        assertEquals((0..100).step(3).toList(), sorted)
    }
}