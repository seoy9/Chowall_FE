package hong.sy.chowall.recommend

data class ResultData(
    val img : Int,
    val name : String,
    val address : String = "",
    val phone : String = "",
    val time : String = "",
    var breakTime : String = ""
)
