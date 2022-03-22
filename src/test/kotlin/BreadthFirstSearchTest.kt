import kotlin.test.assertEquals
import kotlin.test.Test

class BFSTest {
    private val adjacentList =
        mapOf(1 to listOf(2, 7, 8), 2 to listOf(3, 6), 3 to listOf(4, 5), 8 to listOf(9, 12), 9 to listOf(10, 11))
    private val bfs = BFS(adjacentList)

    @Test
    fun search() {
        bfs.search(1)
        assertEquals(listOf(1, 2, 7, 8, 3, 6, 9, 12, 4, 5, 10, 11), bfs.order)
    }
}

class FileSearcherTest {
    private val files = mutableMapOf(
        "1.txt" to Pair("M", listOf("2.txt", "4.txt")),
        "2.txt" to Pair("A", listOf("1.txt", "3.txt", "5.txt")),
        "3.txt" to Pair("C", listOf("1.txt", "6.txt")),
        "4.txt" to Pair("D", listOf("3.txt", "6.txt", "7.txt")),
        "5.txt" to Pair("I", listOf("6.txt")),
        "6.txt" to Pair("T", listOf("3.txt")),
        "7.txt" to Pair("Y", listOf("6.txt")),
    )

    private fun getValue(key: String) = Pair(key, files[key]!!.first)

    private val bfs = BFS(files.entries.associate { it.key to it.value.second })

    // Additional testing for dfs
    private val dfs = DFS(files.entries.associate { it.key to it.value.second })

    @Test
    fun searchTest() {
        bfs.search("1.txt")
        assertEquals("MADCITY", bfs.order.joinToString("") { getValue(it).second })

        dfs.search("1.txt", DFS.DFSImpl.Recursive)
        assertEquals("MACTIDY", dfs.order.joinToString("") { getValue(it).second })
        dfs.search("1.txt", DFS.DFSImpl.Iterative)
        assertEquals("MACTIDY", dfs.order.joinToString("") { getValue(it).second })
    }
}