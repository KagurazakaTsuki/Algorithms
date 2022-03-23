import kotlin.test.Test
import kotlin.test.assertEquals

class BinarySearchTest {

    @Test
    fun recursiveTest() {
        val numbers = (0..100).step(3).toList()

        assertEquals(
            numbers.indexOf(60),
            BinarySearch<Int>().binarySearch(numbers, 60, BinarySearch.BinarySearchImpl.Recursive)
        )
        assertEquals(
            numbers.indexOf(61),
            BinarySearch<Int>().binarySearch(numbers, 61, BinarySearch.BinarySearchImpl.Recursive)
        )
    }
}