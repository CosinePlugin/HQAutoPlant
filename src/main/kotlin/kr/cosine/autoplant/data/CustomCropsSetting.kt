package kr.cosine.autoplant.data

data class CustomCropsSetting(
    val isEnabled: Boolean,
    private val allowed: List<String>
) {

    fun isAllowed(key: String): Boolean = allowed.contains(key)

    companion object {
        fun initWithDefault(): CustomCropsSetting {
            return CustomCropsSetting(true, emptyList())
        }
    }
}