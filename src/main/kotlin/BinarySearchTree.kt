class Node<T>(
    val key: Comparable<T>,
    var left: Node<T>? = null, var right: Node<T>? = null
) {

    val values = mutableListOf<Any>()

    val size: Int
        get() {
            var size: Int = values.size
            if (left != null) size += left!!.size
            if (right != null) size += right!!.size
            return size
        }


    private fun height(node: Node<T>?): Int {
        if (node == null)
            return 0
        return maxOf(height(node.left), height(node.right)) + 1
    }

    val height: Int
        get() = height(this)

    private fun nodeCount(node: Node<T>?): Int {
        if (node == null)
            return 0
        return nodeCount(node.left) + nodeCount(node.right) + 1
    }

    val nodeCount: Int
        get() = nodeCount(this)

    @Suppress("UNCHECKED_CAST")
    fun lookup(key: Comparable<T>): MutableList<Any>? = when {
        (key == this.key) -> values
        (key < this.key as T && left != null) -> left!!.lookup(key)
        (key > this.key as T && right != null) -> right!!.lookup(key)
        else -> null
    }
}

class BST<T> {
    var root: Node<T>? = null

    fun addAll(key: Comparable<T>, values: Collection<Any>) = add(key, values, true)

    @Suppress("UNCHECKED_CAST")
    fun add(key: Comparable<T>, value: Any, flattenCollection: Boolean = false) {
        if (root == null)
            root = Node(key)

        var curr = root!!
        while (true) {
            if (key < curr.key as T) {
                if (curr.left == null) curr.left = Node(key)
                curr = curr.left!!
            } else if (key > curr.key as T) {
                if (curr.right == null) curr.right = Node(key)
                curr = curr.right!!
            } else {
                assert(curr.key == key)
                break
            }
        }

        if (flattenCollection)
            curr.values.addAll(value as Collection<Any>)
        else
            curr.values.add(value)
    }

    private fun dump(node: Node<T>?) {
        if (node == null)
            return
        dump(node.left)
        println("${node.key}:${node.values}")
        dump(node.right)
    }

    fun dump() = dump(root)
}