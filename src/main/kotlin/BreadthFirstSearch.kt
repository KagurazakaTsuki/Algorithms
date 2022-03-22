class BFS<T>(private val adjacencyList: Map<T, List<T>>) {
    var order = mutableListOf<T>()

    fun search(node: T) {
        order = mutableListOf()
        val visited = mutableSetOf<T>()
        val queue = ArrayDeque<T>()
        visited.add(node)
        queue.add(node)
        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            order.add(curr)
            val children = adjacencyList[curr] ?: listOf()
            for (child in children)
                if (child !in visited) {
                    visited.add(child)
                    queue.add(child)
                }
        }
    }
}


