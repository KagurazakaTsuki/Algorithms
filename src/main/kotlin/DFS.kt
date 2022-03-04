class DFS<T>(private val adjacencyList: Map<T, List<T>>) {
    enum class DFSImpl {
        Recursive, Iterative
    }

    fun search(node: T, mode: DFSImpl) {
        order = mutableListOf()
        visited = mutableSetOf()
        when (mode) {
            DFSImpl.Recursive -> visitRec(node)
            DFSImpl.Iterative -> visitIter(node)
        }
    }

    var order = mutableListOf<T>()
    private var visited = mutableSetOf<T>()

    private fun visitRec(node: T) {
        if (node in visited) return
        visited.add(node)
        order.add(node)
        val children = adjacencyList[node] ?: listOf()
        for (child in children)
            visitRec(child)
    }

    private fun visitIter(node: T) {
        val queue = ArrayDeque<T>()
        queue.add(node)
        while (queue.isNotEmpty()) {
            val curr = queue.removeLast()
            if (curr in visited) continue
            visited.add(curr)
            order.add(curr)
            val children = adjacencyList[curr] ?: listOf()
            for (child in children.reversed())
                if (child !in visited) queue.add(child)
        }
    }
}