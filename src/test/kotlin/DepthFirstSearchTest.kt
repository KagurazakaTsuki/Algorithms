import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toMap
import kotlin.test.Test
import kotlin.test.assertEquals

class DFSTest {
    private val adjacentList =
        mapOf(1 to listOf(2, 7, 8), 2 to listOf(3, 6), 3 to listOf(4, 5), 8 to listOf(9, 12), 9 to listOf(10, 11))
    private val dfs = DFS(adjacentList)

    private val adjacentListLooped = adjacentList + mapOf(6 to listOf(9))
    private val dfsLooped = DFS(adjacentListLooped)

    @Test
    fun search() {
        dfs.search(1, DFS.DFSImpl.Recursive)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), dfs.order)
        dfs.search(1, DFS.DFSImpl.Iterative)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), dfs.order)

        dfsLooped.search(1, DFS.DFSImpl.Recursive)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 9, 10, 11, 7, 8, 12), dfsLooped.order)
        dfsLooped.search(1, DFS.DFSImpl.Iterative)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 9, 10, 11, 7, 8, 12), dfsLooped.order)
    }
}


class MatrixSearcher {
    // retrieve adjacency list of a node
    private fun go(node: String): List<String> {
        val children = mutableListOf<String>()
        df[i[node]!!].toMap().filter { map -> map.key != "i" }.map { map -> map.key to map.value.toString().toInt() }
            .forEach { (node, has_edge) ->
                if (has_edge == 1)
                    children.add(node)
            }
        return children
    }

    // 1 means an edge exists between node (row index name) -> connected nodes (header)
    private val df = dataFrameOf("i", "a", "b", "c", "d")(
        "a", 0, 1, 0, 0,
        "b", 0, 0, 1, 1,
        "c", 0, 0, 0, 1,
        "d", 0, 0, 1, 0
    )
    private val i = mapOf("a" to 0, "b" to 1, "c" to 2, "d" to 3)

    private val dfs = DFS(i.keys.associateWith { go(it) })

    @Test
    fun goTest() {
        assertEquals(listOf("b"), go("a"))
        assertEquals(listOf("c", "d"), go("b"))
        assertEquals(listOf("d"), go("c"))
        assertEquals(listOf("c"), go("d"))
    }

    @Test
    fun visitRecTest() {
        dfs.search("c", DFS.DFSImpl.Recursive)
        assertEquals(listOf("c", "d"), dfs.order)
        dfs.search("d", DFS.DFSImpl.Recursive)
        assertEquals(listOf("d", "c"), dfs.order)
        dfs.search("b", DFS.DFSImpl.Recursive)
        assertEquals(listOf("b", "c", "d"), dfs.order)
        dfs.search("a", DFS.DFSImpl.Recursive)
        assertEquals(listOf("a", "b", "c", "d"), dfs.order)
    }

    @Test
    fun visitIterTest() {
        dfs.search("c", DFS.DFSImpl.Iterative)
        assertEquals(listOf("c", "d"), dfs.order)
        dfs.search("d", DFS.DFSImpl.Iterative)
        assertEquals(listOf("d", "c"), dfs.order)
        dfs.search("b", DFS.DFSImpl.Iterative)
        assertEquals(listOf("b", "c", "d"), dfs.order)
        dfs.search("a", DFS.DFSImpl.Iterative)
        assertEquals(listOf("a", "b", "c", "d"), dfs.order)
    }
}

