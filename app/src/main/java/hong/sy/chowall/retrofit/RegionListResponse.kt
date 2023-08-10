package hong.sy.chowall.retrofit

data class RegionListResponse(
    val `data`: List<Data>,
    val success: Boolean
) {
    data class Data(
        val province: String,
        val provinceInfoList: List<ProvinceInfo>
    ) {
        data class ProvinceInfo(
            val city: String
        )
    }
}