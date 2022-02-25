import kotlin.test.Test
import kotlin.test.assertEquals

class BinarySearchTreeTest {
    private val alphabet = ('A'..'Z').toList().map { it.toString() }
    private val alphabetLower = ('A'..'Z').toList().map { it.toString().lowercase() }

    @Test
    fun addTest() {
        val bst = BST<Int>()

        for (key in (0..23).toList().shuffled()) {
            bst.add(key, alphabet[key])
        }

        for (key in (0..23).toList().shuffled()) {
            assertEquals(1, bst.root?.lookup(key)?.size)
            assertEquals(alphabet[key], bst.root?.lookup(key)?.first())
        }
        assertEquals(24, bst.root?.size)
        assertEquals(24, bst.root?.nodeCount)

        bst.root?.height.also { println("BST height: $it") }
        bst.dump()
    }

    @Test
    fun addAllTest() {
        val bst = BST<Int>()

        for (key in (0..23).toList().shuffled()) {
            bst.addAll(key, listOf(alphabet[key], alphabetLower[key]))
        }

        for (key in (0..23).toList().shuffled()) {
            assertEquals(2, bst.root?.lookup(key)?.size)
            assertEquals(listOf(alphabet[key], alphabetLower[key]), bst.root?.lookup(key)?.toList())
        }

        assertEquals(48, bst.root?.size)
        assertEquals(24, bst.root?.nodeCount)

        bst.root?.height.also { println("BST height: $it") }
        bst.dump()
    }
}