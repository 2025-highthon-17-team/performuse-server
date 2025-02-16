class Library(
    val library: String,
    val version: String? = null,
    val group: String = library.split(":")[0],
    val module: String = library.split(":")[1],
) {
    override fun toString(): String = version?.let { "$library:$it" } ?: library
}
