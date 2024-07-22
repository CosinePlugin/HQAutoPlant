package kr.cosine.autoplant.database.dto

data class AutoPlantDTO(
    private var count: Int,
    private var isEnabled: Boolean
) {

    var isChanged = false

    fun isCountEmpty(): Boolean {
        return getCount() == 0
    }

    fun getCount(): Int = count

    fun setCount(count: Int) {
        this.count = count
        isChanged = true
    }

    fun addCount(count: Int = 1) {
        setCount(getCount() + count)
    }

    fun subtractCount(count: Int = 1) {
        setCount(getCount() - count)
    }

    fun isEnabled(): Boolean = isEnabled

    fun setEnabled(isEnabled: Boolean) {
        this.isEnabled = isEnabled
        isChanged = true
    }

    fun switchEnabled(): Boolean {
        val newEnabled = !isEnabled()
        setEnabled(newEnabled)
        return newEnabled
    }
}